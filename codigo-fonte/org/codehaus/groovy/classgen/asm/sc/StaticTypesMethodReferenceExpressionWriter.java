/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.classgen.asm.sc;

import groovyjarjarasm.asm.Handle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.MethodReferenceExpression;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.ast.tools.GenericsUtils;
import org.codehaus.groovy.ast.tools.ParameterUtils;
import org.codehaus.groovy.classgen.asm.BytecodeHelper;
import org.codehaus.groovy.classgen.asm.MethodReferenceExpressionWriter;
import org.codehaus.groovy.classgen.asm.WriterController;
import org.codehaus.groovy.classgen.asm.sc.AbstractFunctionalInterfaceWriter;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.syntax.RuntimeParserException;
import org.codehaus.groovy.transform.sc.StaticCompilationMetadataKeys;
import org.codehaus.groovy.transform.stc.ExtensionMethodNode;
import org.codehaus.groovy.transform.stc.StaticTypeCheckingSupport;
import org.codehaus.groovy.transform.stc.StaticTypesMarker;

public class StaticTypesMethodReferenceExpressionWriter
extends MethodReferenceExpressionWriter
implements AbstractFunctionalInterfaceWriter {
    public StaticTypesMethodReferenceExpressionWriter(WriterController controller) {
        super(controller);
    }

    @Override
    public void writeMethodReferenceExpression(MethodReferenceExpression methodReferenceExpression) {
        Parameter[] parameterArray;
        MethodNode methodRefMethod;
        ClassNode functionalInterfaceType = this.getFunctionalInterfaceType(methodReferenceExpression);
        MethodNode abstractMethod = ClassHelper.findSAM(functionalInterfaceType);
        if (abstractMethod == null || !functionalInterfaceType.isInterface()) {
            super.writeMethodReferenceExpression(methodReferenceExpression);
            return;
        }
        ClassNode classNode = this.controller.getClassNode();
        Expression typeOrTargetRef = methodReferenceExpression.getExpression();
        boolean isClassExpression = typeOrTargetRef instanceof ClassExpression;
        boolean targetIsArgument = false;
        ClassNode typeOrTargetRefType = isClassExpression ? typeOrTargetRef.getType() : this.controller.getTypeChooser().resolveType(typeOrTargetRef, classNode);
        ClassNode[] methodReferenceParamTypes = (ClassNode[])methodReferenceExpression.getNodeMetaData((Object)StaticTypesMarker.CLOSURE_ARGUMENTS);
        Parameter[] parametersWithExactType = this.createParametersWithExactType(abstractMethod, methodReferenceParamTypes);
        String methodRefName = methodReferenceExpression.getMethodName().getText();
        boolean isConstructorReference = StaticTypesMethodReferenceExpressionWriter.isConstructorReference(methodRefName);
        if (isConstructorReference) {
            methodRefName = this.controller.getContext().getNextConstructorReferenceSyntheticMethodName(this.controller.getMethodNode());
            methodRefMethod = this.addSyntheticMethodForConstructorReference(methodRefName, typeOrTargetRefType, parametersWithExactType);
        } else {
            methodRefMethod = this.findMethodRefMethod(methodRefName, parametersWithExactType, typeOrTargetRef, typeOrTargetRefType);
        }
        this.validate(methodReferenceExpression, typeOrTargetRefType, methodRefName, methodRefMethod, parametersWithExactType, StaticTypeCheckingSupport.resolveClassNodeGenerics(GenericsUtils.extractPlaceholders(functionalInterfaceType), null, abstractMethod.getReturnType()));
        if (StaticTypesMethodReferenceExpressionWriter.isExtensionMethod(methodRefMethod)) {
            ExtensionMethodNode extensionMethodNode = (ExtensionMethodNode)methodRefMethod;
            methodRefMethod = extensionMethodNode.getExtensionMethodNode();
            boolean isStatic = extensionMethodNode.isStaticExtension();
            if (isStatic) {
                methodRefMethod = this.addSyntheticMethodForDGSM(methodRefMethod);
            }
            if (isStatic || isClassExpression) {
                typeOrTargetRefType = methodRefMethod.getDeclaringClass();
                typeOrTargetRef = StaticTypesMethodReferenceExpressionWriter.makeClassTarget(typeOrTargetRefType, typeOrTargetRef);
            } else {
                targetIsArgument = true;
            }
        } else if (ParameterUtils.isVargs(methodRefMethod.getParameters())) {
            int mParameters = abstractMethod.getParameters().length;
            int nParameters = methodRefMethod.getParameters().length;
            if (StaticTypesMethodReferenceExpressionWriter.isTypeReferringInstanceMethod(typeOrTargetRef, methodRefMethod)) {
                ++nParameters;
            }
            if (mParameters > nParameters || mParameters == nParameters - 1 || mParameters == nParameters && !StaticTypeCheckingSupport.isAssignableTo(DefaultGroovyMethods.last(parametersWithExactType).getType(), DefaultGroovyMethods.last(methodRefMethod.getParameters()).getType())) {
                if (!(isClassExpression || methodRefMethod.isStatic() || methodRefMethod.getDeclaringClass().equals(classNode))) {
                    targetIsArgument = true;
                    ++mParameters;
                }
                if ((methodRefMethod = this.addSyntheticMethodForVariadicReference(methodRefMethod, mParameters, isClassExpression || targetIsArgument)).isStatic() && !targetIsArgument) {
                    typeOrTargetRefType = methodRefMethod.getDeclaringClass();
                    typeOrTargetRef = StaticTypesMethodReferenceExpressionWriter.makeClassTarget(typeOrTargetRefType, typeOrTargetRef);
                }
            }
        }
        if (!isClassExpression) {
            if (isConstructorReference) {
                this.addFatalError("Constructor reference must be TypeName::new", methodReferenceExpression);
            } else if (methodRefMethod.isStatic() && !targetIsArgument) {
                typeOrTargetRef = StaticTypesMethodReferenceExpressionWriter.makeClassTarget(typeOrTargetRefType, typeOrTargetRef);
                isClassExpression = true;
            } else {
                typeOrTargetRef.visit(this.controller.getAcg());
            }
        }
        int referenceKind = isConstructorReference || methodRefMethod.isStatic() ? 6 : (methodRefMethod.getDeclaringClass().isInterface() ? 9 : 5);
        String methodName = abstractMethod.getName();
        ClassNode classNode2 = functionalInterfaceType.redirect();
        if (isClassExpression) {
            parameterArray = Parameter.EMPTY_ARRAY;
        } else {
            Parameter[] parameterArray2 = new Parameter[1];
            parameterArray = parameterArray2;
            parameterArray2[0] = new Parameter(typeOrTargetRefType, "__METHODREF_EXPR_INSTANCE");
        }
        String methodDesc = BytecodeHelper.getMethodDescriptor(classNode2, parameterArray);
        Handle bootstrapMethod = this.createBootstrapMethod(classNode.isInterface(), false);
        Object[] bootstrapArgs = this.createBootstrapMethodArguments(this.createMethodDescriptor(abstractMethod), referenceKind, methodRefMethod.getDeclaringClass(), methodRefMethod, parametersWithExactType, false);
        this.controller.getMethodVisitor().visitInvokeDynamicInsn(methodName, methodDesc, bootstrapMethod, bootstrapArgs);
        if (isClassExpression) {
            this.controller.getOperandStack().push(functionalInterfaceType);
        } else {
            this.controller.getOperandStack().replace(functionalInterfaceType, 1);
        }
    }

    private void validate(MethodReferenceExpression methodReference, ClassNode targetType, String methodName, MethodNode methodNode, Parameter[] samParameters, ClassNode samReturnType) {
        if (methodNode == null) {
            String error;
            if (!(methodReference.getExpression() instanceof ClassExpression)) {
                error = "Failed to find method '%s(%s)'";
            } else {
                error = "Failed to find class method '%s(%s)'";
                if (samParameters.length > 0) {
                    error = error + " or instance method '%1$s(" + Arrays.stream(samParameters).skip(1L).map(e -> e.getType().toString(false)).collect(Collectors.joining(",")) + ")'";
                }
            }
            error = String.format(error + " for the type: %s", methodName, Arrays.stream(samParameters).map(e -> e.getType().toString(false)).collect(Collectors.joining(",")), targetType.toString(false));
            this.addFatalError(error, methodReference);
        } else if (methodNode.isVoidMethod() && !ClassHelper.isPrimitiveVoid(samReturnType)) {
            this.addFatalError("Invalid return type: void is not convertible to " + samReturnType.getText(), methodReference);
        } else if (samParameters.length > 0 && StaticTypesMethodReferenceExpressionWriter.isTypeReferringInstanceMethod(methodReference.getExpression(), methodNode) && !StaticTypeCheckingSupport.isAssignableTo(samParameters[0].getType(), targetType)) {
            throw new RuntimeParserException("Invalid receiver type: " + samParameters[0].getType().getText() + " is not compatible with " + targetType.getText(), methodReference.getExpression());
        }
    }

    private MethodNode addSyntheticMethodForDGSM(MethodNode mn) {
        Parameter[] parameters = StaticTypesMethodReferenceExpressionWriter.removeFirstParameter(mn.getParameters());
        ArgumentListExpression args = new ArgumentListExpression(parameters);
        args.getExpressions().add(0, GeneralUtils.nullX());
        MethodCallExpression methodCall = GeneralUtils.callX((Expression)GeneralUtils.classX(mn.getDeclaringClass()), mn.getName(), (Expression)args);
        methodCall.setImplicitThis(false);
        methodCall.setMethodTarget(mn);
        methodCall.putNodeMetaData((Object)StaticTypesMarker.DIRECT_METHOD_CALL_TARGET, mn);
        String methodName = "dgsm$$" + mn.getParameters()[0].getType().getName().replace('.', '$') + "$$" + mn.getName();
        MethodNode delegateMethod = this.addSyntheticMethod(methodName, mn.getReturnType(), methodCall, parameters, mn.getExceptions());
        delegateMethod.putNodeMetaData((Object)StaticCompilationMetadataKeys.STATIC_COMPILE_NODE, Boolean.TRUE);
        return delegateMethod;
    }

    private MethodNode addSyntheticMethodForVariadicReference(MethodNode mn, int samParameters, boolean isStaticTarget) {
        Expression receiver;
        ArgumentListExpression arguments;
        Parameter[] parameters = new Parameter[samParameters];
        if (mn.isStatic()) {
            int j = mn.getParameters().length - 1;
            for (int i = 0; i < samParameters; ++i) {
                ClassNode t = mn.getParameters()[Math.min(i, j)].getType();
                if (i >= j) {
                    t = t.getComponentType();
                }
                parameters[i] = new Parameter(t, "p" + i);
            }
            arguments = new ArgumentListExpression(parameters);
            receiver = GeneralUtils.classX(mn.getDeclaringClass());
        } else {
            int p = 0;
            if (isStaticTarget) {
                parameters[p++] = new Parameter(mn.getDeclaringClass(), "target");
            }
            int j = mn.getParameters().length - 1;
            int n = samParameters - p;
            for (int i = 0; i < n; ++i) {
                ClassNode t = mn.getParameters()[Math.min(i, j)].getType();
                if (i >= j) {
                    t = t.getComponentType();
                }
                parameters[p] = new Parameter(t, "p" + p);
                ++p;
            }
            if (isStaticTarget) {
                arguments = new ArgumentListExpression(StaticTypesMethodReferenceExpressionWriter.removeFirstParameter(parameters));
                receiver = GeneralUtils.varX(parameters[0]);
            } else {
                arguments = new ArgumentListExpression(parameters);
                receiver = GeneralUtils.varX("this", this.controller.getClassNode());
            }
        }
        MethodCallExpression methodCall = GeneralUtils.callX(receiver, mn.getName(), (Expression)arguments);
        methodCall.setImplicitThis(false);
        methodCall.setMethodTarget(mn);
        methodCall.putNodeMetaData((Object)StaticTypesMarker.DIRECT_METHOD_CALL_TARGET, mn);
        String methodName = "adapt$" + mn.getDeclaringClass().getNameWithoutPackage() + "$" + mn.getName() + "$" + System.nanoTime();
        MethodNode delegateMethod = this.addSyntheticMethod(methodName, mn.getReturnType(), methodCall, parameters, mn.getExceptions());
        if (!isStaticTarget && !mn.isStatic()) {
            delegateMethod.setModifiers(delegateMethod.getModifiers() & 0xFFFFFFF7);
        }
        delegateMethod.putNodeMetaData((Object)StaticCompilationMetadataKeys.STATIC_COMPILE_NODE, Boolean.TRUE);
        delegateMethod.setGenericsTypes(mn.getGenericsTypes());
        return delegateMethod;
    }

    private MethodNode addSyntheticMethodForConstructorReference(String methodName, ClassNode returnType, Parameter[] parametersWithExactType) {
        ArgumentListExpression ctorArgs = new ArgumentListExpression(parametersWithExactType);
        Expression returnValue = returnType.isArray() ? new ArrayExpression(returnType.getComponentType(), null, ctorArgs.getExpressions()) : GeneralUtils.ctorX(returnType, ctorArgs);
        MethodNode delegateMethod = this.addSyntheticMethod(methodName, returnType, returnValue, parametersWithExactType, ClassNode.EMPTY_ARRAY);
        delegateMethod.putNodeMetaData((Object)StaticCompilationMetadataKeys.STATIC_COMPILE_NODE, Boolean.FALSE);
        return delegateMethod;
    }

    private MethodNode addSyntheticMethod(String methodName, ClassNode returnType, Expression returnValue, Parameter[] parameters, ClassNode[] exceptions) {
        return this.controller.getClassNode().addSyntheticMethod(methodName, 26, returnType, parameters, exceptions, GeneralUtils.returnS(returnValue));
    }

    private Parameter[] createParametersWithExactType(MethodNode abstractMethod, ClassNode[] inferredParamTypes) {
        Parameter[] parameters = GeneralUtils.cloneParams(abstractMethod.getParameters());
        if (inferredParamTypes != null) {
            int n = parameters.length;
            for (int i = 0; i < n; ++i) {
                ClassNode inferredParamType;
                ClassNode classNode = inferredParamType = i < inferredParamTypes.length ? inferredParamTypes[i] : parameters[i].getType();
                if (inferredParamType == null) continue;
                Parameter parameter = parameters[i];
                ClassNode type = this.convertParameterType(parameter.getType(), inferredParamType);
                parameter.setOriginType(type);
                parameter.setType(type);
            }
        }
        return parameters;
    }

    private MethodNode findMethodRefMethod(String methodName, Parameter[] samParameters, Expression typeOrTargetRef, ClassNode typeOrTargetRefType) {
        List<MethodNode> methods = this.findVisibleMethods(methodName, typeOrTargetRefType);
        ToIntFunction<Parameter[]> distance = parameters -> StaticTypeCheckingSupport.allParametersAndArgumentsMatch(parameters, (ClassNode[])Arrays.stream(samParameters).map(Parameter::getType).toArray(ClassNode[]::new));
        int[] distances = methods.stream().mapToInt(method -> {
            Parameter[] parameters = method.getParameters();
            if (StaticTypesMethodReferenceExpressionWriter.isTypeReferringInstanceMethod(typeOrTargetRef, method)) {
                ClassNode firstParamType = method.getDeclaringClass();
                int n = parameters.length;
                Parameter[] plusOne = new Parameter[n + 1];
                plusOne[0] = new Parameter(firstParamType, "");
                System.arraycopy(parameters, 0, plusOne, 1, n);
                parameters = plusOne;
            }
            if (parameters.length > 0 && !method.isStatic() && !StaticTypesMethodReferenceExpressionWriter.isExtensionMethod(method) && typeOrTargetRefType.redirect().getGenericsTypes() != null) {
                Map<GenericsType.GenericsTypeName, GenericsType> spec = GenericsUtils.extractPlaceholders(typeOrTargetRefType);
                parameters = (Parameter[])Arrays.stream(parameters).map(p -> new Parameter(StaticTypeCheckingSupport.resolveClassNodeGenerics(spec, null, p.getType()), p.getName())).toArray(Parameter[]::new);
            }
            if (ParameterUtils.parametersCompatible(samParameters, parameters)) {
                return distance.applyAsInt(parameters);
            }
            if (ParameterUtils.isVargs(parameters)) {
                int nParameters = parameters.length;
                if (samParameters.length == nParameters - 1) {
                    if (ParameterUtils.parametersCompatible(samParameters, parameters = Arrays.copyOf(parameters, nParameters - 1))) {
                        return distance.applyAsInt(parameters);
                    }
                } else if (samParameters.length >= nParameters) {
                    Parameter p2 = new Parameter(parameters[nParameters - 1].getType().getComponentType(), "");
                    parameters = Arrays.copyOf(parameters, samParameters.length);
                    for (int i = nParameters - 1; i < parameters.length; ++i) {
                        parameters[i] = p2;
                    }
                    if (ParameterUtils.parametersCompatible(samParameters, parameters)) {
                        return distance.applyAsInt(parameters);
                    }
                }
            }
            return -1;
        }).toArray();
        int i = 0;
        int bestDistance = Arrays.stream(distances).filter(d -> d >= 0).min().orElse(0);
        Iterator<MethodNode> it = methods.iterator();
        while (it.hasNext()) {
            it.next();
            if (distances[i++] == bestDistance) continue;
            it.remove();
        }
        if (methods.isEmpty()) {
            return null;
        }
        if (methods.size() == 1) {
            return methods.get(0);
        }
        return this.chooseMethodRefMethod(methods, typeOrTargetRef, typeOrTargetRefType);
    }

    private MethodNode chooseMethodRefMethod(List<MethodNode> methods, Expression typeOrTargetRef, ClassNode typeOrTargetRefType) {
        return methods.stream().max(Comparator.comparingInt(mn -> {
            int score = 9;
            for (ClassNode cn = typeOrTargetRefType; cn != null && !cn.equals(mn.getDeclaringClass()); cn = cn.getSuperClass()) {
                --score;
            }
            if (score < 0) {
                score = 0;
            }
            score *= 10;
            if (typeOrTargetRef instanceof ClassExpression == StaticTypesMethodReferenceExpressionWriter.isStaticMethod(mn)) {
                score += 9;
            }
            return score;
        }).thenComparing(StaticTypesMethodReferenceExpressionWriter::isExtensionMethod)).get();
    }

    private List<MethodNode> findVisibleMethods(String name, ClassNode type) {
        List<MethodNode> methods = type.getMethods(name);
        for (ClassNode cn : GeneralUtils.getInterfacesAndSuperInterfaces(type)) {
            for (MethodNode mn : cn.getDeclaredMethods(name)) {
                if (!mn.isDefault()) continue;
                methods.add(mn);
            }
        }
        methods.addAll(StaticTypeCheckingSupport.findDGMMethodsForClassNode(this.controller.getSourceUnit().getClassLoader(), type, name));
        return StaticTypeCheckingSupport.filterMethodsByVisibility(methods, this.controller.getClassNode());
    }

    private void addFatalError(String msg, ASTNode node) {
        this.controller.getSourceUnit().addFatalError(msg, node);
        throw new MultipleCompilationErrorsException(this.controller.getSourceUnit().getErrorCollector());
    }

    private static boolean isConstructorReference(String name) {
        return "new".equals(name);
    }

    private static boolean isExtensionMethod(MethodNode mn) {
        return mn instanceof ExtensionMethodNode;
    }

    private static boolean isStaticMethod(MethodNode mn) {
        return StaticTypesMethodReferenceExpressionWriter.isExtensionMethod(mn) ? ((ExtensionMethodNode)mn).isStaticExtension() : mn.isStatic();
    }

    private static boolean isTypeReferringInstanceMethod(Expression typeOrTargetRef, MethodNode mn) {
        return typeOrTargetRef instanceof ClassExpression && mn != null && !StaticTypesMethodReferenceExpressionWriter.isStaticMethod(mn);
    }

    private static Expression makeClassTarget(ClassNode target, Expression source) {
        ClassExpression expression = GeneralUtils.classX(target);
        expression.setSourcePosition(source);
        return expression;
    }

    private static Parameter[] removeFirstParameter(Parameter[] parameters) {
        return Arrays.copyOfRange(parameters, 1, parameters.length);
    }
}

