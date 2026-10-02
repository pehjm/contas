/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.classgen.asm.sc;

import groovyjarjarasm.asm.Handle;
import groovyjarjarasm.asm.Type;
import java.util.Arrays;
import java.util.List;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.classgen.asm.BytecodeHelper;
import org.codehaus.groovy.syntax.RuntimeParserException;
import org.codehaus.groovy.transform.stc.StaticTypesMarker;

public interface AbstractFunctionalInterfaceWriter {
    @Deprecated
    public static final String ORIGINAL_PARAMETERS_WITH_EXACT_TYPE = "__ORIGINAL_PARAMETERS_WITH_EXACT_TYPE";

    @Deprecated
    default public ClassNode getFunctionalInterfaceType(Expression expression) {
        ClassNode type = (ClassNode)expression.getNodeMetaData((Object)StaticTypesMarker.PARAMETER_TYPE);
        if (type == null) {
            type = (ClassNode)expression.getNodeMetaData((Object)StaticTypesMarker.INFERRED_FUNCTIONAL_INTERFACE_TYPE);
        }
        return type;
    }

    default public String createMethodDescriptor(MethodNode abstractMethodNode) {
        return BytecodeHelper.getMethodDescriptor(abstractMethodNode.getReturnType().getTypeClass(), (Class[])Arrays.stream(abstractMethodNode.getParameters()).map(e -> e.getType().getTypeClass()).toArray(Class[]::new));
    }

    default public Handle createBootstrapMethod(boolean isInterface, boolean serializable) {
        if (serializable) {
            return new Handle(6, "java/lang/invoke/LambdaMetafactory", "altMetafactory", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", isInterface);
        }
        return new Handle(6, "java/lang/invoke/LambdaMetafactory", "metafactory", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", isInterface);
    }

    @Deprecated
    default public Object[] createBootstrapMethodArguments(String abstractMethodDesc, int insn, ClassNode methodOwner, MethodNode methodNode, boolean serializable) {
        return this.createBootstrapMethodArguments(abstractMethodDesc, insn, methodOwner, methodNode, (Parameter[])methodNode.getNodeMetaData(ORIGINAL_PARAMETERS_WITH_EXACT_TYPE), serializable);
    }

    default public Object[] createBootstrapMethodArguments(String abstractMethodDesc, int insn, ClassNode methodOwner, MethodNode methodNode, Parameter[] parameters, boolean serializable) {
        Object[] objectArray;
        ClassNode returnType = methodNode.getReturnType();
        switch (Type.getReturnType(abstractMethodDesc).getSort()) {
            case 1: {
                if (!returnType.isGenericsPlaceHolder()) break;
                returnType = ClassHelper.Boolean_TYPE;
                break;
            }
            case 3: {
                if (!returnType.isGenericsPlaceHolder()) break;
                returnType = ClassHelper.Byte_TYPE;
                break;
            }
            case 2: {
                if (!returnType.isGenericsPlaceHolder()) break;
                returnType = ClassHelper.Character_TYPE;
                break;
            }
            case 8: {
                if (!returnType.isGenericsPlaceHolder()) break;
                returnType = ClassHelper.Double_TYPE;
                break;
            }
            case 6: {
                if (!returnType.isGenericsPlaceHolder()) break;
                returnType = ClassHelper.Float_TYPE;
                break;
            }
            case 5: {
                if (!returnType.isGenericsPlaceHolder()) break;
                returnType = ClassHelper.Integer_TYPE;
                break;
            }
            case 7: {
                if (!returnType.isGenericsPlaceHolder()) break;
                returnType = ClassHelper.Long_TYPE;
                break;
            }
            case 4: {
                if (!returnType.isGenericsPlaceHolder()) break;
                returnType = ClassHelper.Short_TYPE;
                break;
            }
            case 0: {
                returnType = ClassHelper.VOID_TYPE;
            }
        }
        if (!serializable) {
            objectArray = new Object[3];
        } else {
            Object[] objectArray2 = new Object[5];
            objectArray2[0] = null;
            objectArray2[1] = null;
            objectArray2[2] = null;
            objectArray2[3] = 5;
            objectArray = objectArray2;
            objectArray2[4] = 0;
        }
        Object[] arguments = objectArray;
        arguments[0] = Type.getMethodType(abstractMethodDesc);
        arguments[1] = new Handle(insn, BytecodeHelper.getClassInternalName(methodOwner.getName()), methodNode.getName(), BytecodeHelper.getMethodDescriptor(methodNode), methodOwner.isInterface());
        arguments[2] = Type.getMethodType(BytecodeHelper.getMethodDescriptor(returnType, parameters));
        return arguments;
    }

    default public ClassNode convertParameterType(ClassNode parameterType, ClassNode inferredType) {
        return this.convertParameterType(parameterType, parameterType, inferredType);
    }

    default public ClassNode convertParameterType(ClassNode targetType, ClassNode parameterType, ClassNode inferredType) {
        if (!ClassHelper.getWrapper(inferredType).isDerivedFrom(ClassHelper.getWrapper(parameterType))) {
            throw new RuntimeParserException("The inferred type[" + inferredType.redirect() + "] is not compatible with the parameter type[" + parameterType.redirect() + "]", parameterType);
        }
        ClassNode type = inferredType;
        if (ClassHelper.isPrimitiveType(parameterType)) {
            if (!ClassHelper.isPrimitiveType(inferredType)) {
                type = ClassHelper.getUnwrapper(inferredType);
            }
        } else if (!(!ClassHelper.isPrimitiveType(inferredType) || ClassHelper.isDynamicTyped(parameterType) && ClassHelper.isPrimitiveType(targetType) || !parameterType.equals(ClassHelper.getUnwrapper(parameterType)) && !inferredType.equals(ClassHelper.getWrapper(inferredType)))) {
            type = ClassHelper.getWrapper(inferredType);
        }
        if (type.isGenericsPlaceHolder()) {
            type = type.redirect();
        }
        return type;
    }

    default public Parameter prependParameter(List<Parameter> parameterList, String parameterName, ClassNode parameterType) {
        Parameter parameter = new Parameter(parameterType, parameterName);
        parameter.setClosureSharedVariable(false);
        parameterList.add(0, parameter);
        return parameter;
    }
}

