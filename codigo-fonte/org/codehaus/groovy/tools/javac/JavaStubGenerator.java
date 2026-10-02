/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.tools.javac;

import groovy.transform.PackageScope;
import groovy.transform.PackageScopeTarget;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import org.apache.groovy.ast.tools.AnnotatedNodeUtils;
import org.apache.groovy.ast.tools.ConstructorNodeUtils;
import org.apache.groovy.ast.tools.ExpressionUtils;
import org.apache.groovy.io.StringBuilderWriter;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.CodeVisitorSupport;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.DynamicVariable;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.ImportNode;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.decompiled.DecompiledClassNode;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.ast.tools.GenericsUtils;
import org.codehaus.groovy.ast.tools.ParameterUtils;
import org.codehaus.groovy.ast.tools.WideningCategories;
import org.codehaus.groovy.classgen.FinalVariableAnalyzer;
import org.codehaus.groovy.classgen.Verifier;
import org.codehaus.groovy.classgen.VerifierCodeVisitor;
import org.codehaus.groovy.runtime.FormatHelper;
import org.codehaus.groovy.tools.Utilities;
import org.codehaus.groovy.tools.javac.MemJavaFileObject;
import org.codehaus.groovy.tools.javac.RawJavaFileObject;
import org.codehaus.groovy.transform.trait.Traits;

public class JavaStubGenerator {
    private final boolean java5;
    private final String encoding;
    private final File outputPath;
    private final boolean requireSuperResolved;
    private final List<ConstructorNode> constructors = new ArrayList<ConstructorNode>();
    private final Map<String, MethodNode> propertyMethods = new LinkedHashMap<String, MethodNode>();
    private static final ClassNode PACKAGE_SCOPE_TYPE = ClassHelper.makeCached(PackageScope.class);
    private ModuleNode currentModule;
    private final Set<JavaFileObject> javaStubCompilationUnitSet = new HashSet<JavaFileObject>();

    public JavaStubGenerator(File outputPath) {
        this(outputPath, false, Charset.defaultCharset().name());
    }

    public JavaStubGenerator(File outputPath, boolean requireSuperResolved, String encoding) {
        this(outputPath, requireSuperResolved, true, encoding);
    }

    @Deprecated
    public JavaStubGenerator(File outputPath, boolean requireSuperResolved, boolean java5, String encoding) {
        this.outputPath = outputPath;
        this.requireSuperResolved = requireSuperResolved;
        this.java5 = java5;
        this.encoding = encoding;
        if (null != outputPath) {
            outputPath.mkdirs();
        }
    }

    private static void mkdirs(File parent, String relativeFile) {
        int index = relativeFile.lastIndexOf(47);
        if (index == -1) {
            return;
        }
        File dir = new File(parent, relativeFile.substring(0, index));
        dir.mkdirs();
    }

    public void generateClass(ClassNode classNode) throws FileNotFoundException {
        if (this.requireSuperResolved && !classNode.getSuperClass().isResolved()) {
            return;
        }
        if (classNode instanceof InnerClassNode) {
            return;
        }
        if ((classNode.getModifiers() & 2) != 0) {
            return;
        }
        if (null == this.outputPath) {
            this.generateMemStub(classNode);
        } else {
            this.generateFileStub(classNode);
        }
    }

    private void generateMemStub(ClassNode classNode) {
        this.javaStubCompilationUnitSet.add(new MemJavaFileObject(classNode, this.generateStubContent(classNode)));
    }

    private void generateFileStub(ClassNode classNode) throws FileNotFoundException {
        String fileName = classNode.getName().replace('.', '/');
        JavaStubGenerator.mkdirs(this.outputPath, fileName);
        File file = this.createJavaStubFile(fileName);
        OutputStreamWriter writer = new OutputStreamWriter((OutputStream)new FileOutputStream(file), Charset.forName(this.encoding));
        try (PrintWriter out = new PrintWriter(writer);){
            out.print(this.generateStubContent(classNode));
        }
        this.javaStubCompilationUnitSet.add(new RawJavaFileObject(this.createJavaStubFile(fileName).toPath().toUri()));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String generateStubContent(ClassNode classNode) {
        StringBuilderWriter writer = new StringBuilderWriter(8192);
        try (PrintWriter out = new PrintWriter(writer);){
            boolean packageInfo = "package-info".equals(classNode.getNameWithoutPackage());
            String packageName = classNode.getPackageName();
            this.currentModule = classNode.getModule();
            if (packageName != null) {
                if (packageInfo) {
                    this.printAnnotations(out, classNode.getPackage());
                }
                out.println("package " + packageName + ";");
            }
            if (!packageInfo) {
                this.printImports(out);
                this.printClassContents(out, classNode);
            }
        }
        finally {
            this.currentModule = null;
        }
        return ((Object)writer).toString();
    }

    private static Iterable<ClassNode> findTraits(ClassNode classNode) {
        LinkedHashSet<ClassNode> traits = new LinkedHashSet<ClassNode>();
        LinkedList todo = new LinkedList();
        Collections.addAll(todo, classNode.getInterfaces());
        while (!todo.isEmpty()) {
            ClassNode next = (ClassNode)todo.removeLast();
            if (Traits.isTrait(next)) {
                traits.add(next);
            }
            Collections.addAll(todo, next.getInterfaces());
        }
        return traits;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void printClassContents(PrintWriter out, ClassNode classNode) {
        if (classNode instanceof InnerClassNode && ((InnerClassNode)classNode).isAnonymous()) {
            return;
        }
        try {
            ClassNode[] interfaces;
            boolean isEnum;
            Verifier verifier = new Verifier(){

                @Override
                public void visitClass(ClassNode node) {
                    ArrayList<Statement> savedStatements = new ArrayList<Statement>(node.getObjectInitializerStatements());
                    super.visitClass(node);
                    node.getObjectInitializerStatements().addAll(savedStatements);
                    for (ClassNode trait : JavaStubGenerator.findTraits(node)) {
                        Map<String, ClassNode> generics = trait.isUsingGenerics() ? GenericsUtils.createGenericsSpec(trait) : null;
                        for (PropertyNode traitProperty : trait.getProperties()) {
                            ClassNode traitPropertyType = traitProperty.getType();
                            traitProperty.setType(GenericsUtils.correctToGenericsSpecRecurse(generics, traitPropertyType));
                            super.visitProperty(traitProperty);
                            traitProperty.setType(traitPropertyType);
                        }
                    }
                }

                @Override
                public void visitConstructor(ConstructorNode node) {
                    Statement stmt = node.getCode();
                    if (stmt != null) {
                        stmt.visit(new VerifierCodeVisitor(this.getClassNode()));
                    }
                }

                @Override
                public void visitProperty(PropertyNode pn) {
                    if (!pn.isStatic() || !Traits.isTrait(pn.getDeclaringClass())) {
                        super.visitProperty(pn);
                    }
                }

                @Override
                public void addCovariantMethods(ClassNode cn) {
                }

                @Override
                protected void addInitialization(ClassNode cn) {
                }

                @Override
                protected void addInitialization(ClassNode cn, ConstructorNode c) {
                }

                @Override
                protected void addPropertyMethod(MethodNode mn) {
                    this.doAddMethod(mn);
                }

                @Override
                protected void addReturnIfNeeded(MethodNode mn) {
                }

                @Override
                protected MethodNode addMethod(ClassNode cn, boolean shouldBeSynthetic, String name, int modifiers, ClassNode returnType, Parameter[] parameters, ClassNode[] exceptions, Statement code) {
                    return this.doAddMethod(new MethodNode(name, modifiers, returnType, parameters, exceptions, code));
                }

                @Override
                protected void addConstructor(Parameter[] params, ConstructorNode ctor, Statement code, ClassNode node) {
                    if (!(code instanceof BlockStatement)) {
                        Statement stmt = code;
                        code = new BlockStatement();
                        ((BlockStatement)code).addStatement(stmt);
                    }
                    ConstructorNode newCtor = new ConstructorNode(ctor.getModifiers(), params, ctor.getExceptions(), code);
                    newCtor.setDeclaringClass(node);
                    AnnotatedNodeUtils.markAsGenerated(node, newCtor);
                    JavaStubGenerator.this.constructors.add(newCtor);
                }

                @Override
                protected void addDefaultParameters(Verifier.DefaultArgsAction action, MethodNode method) {
                    int i;
                    Parameter[] parameters = method.getParameters();
                    Expression[] saved = new Expression[parameters.length];
                    for (i = 0; i < parameters.length; ++i) {
                        if (!parameters[i].hasInitialExpression()) continue;
                        saved[i] = parameters[i].getInitialExpression();
                    }
                    super.addDefaultParameters(action, method);
                    for (i = 0; i < parameters.length; ++i) {
                        if (saved[i] == null) continue;
                        parameters[i].setInitialExpression(saved[i]);
                    }
                }

                private MethodNode doAddMethod(MethodNode method) {
                    JavaStubGenerator.this.propertyMethods.putIfAbsent(method.getTypeDescriptor(), method);
                    return method;
                }

                @Override
                protected void addDefaultConstructor(ClassNode node) {
                }

                @Override
                protected FinalVariableAnalyzer.VariableNotFinalCallback getFinalVariablesCallback() {
                    return null;
                }
            };
            int origNumConstructors = classNode.getDeclaredConstructors().size();
            verifier.visitClass(classNode);
            if (origNumConstructors == 0 && classNode.getDeclaredConstructors().size() == 1) {
                classNode.getDeclaredConstructors().clear();
            }
            boolean isInterface = !(isEnum = classNode.isEnum()) && JavaStubGenerator.isInterfaceOrTrait(classNode);
            boolean isAnnotationDefinition = classNode.isAnnotationDefinition();
            this.printAnnotations(out, classNode);
            int flags = classNode.getModifiers();
            if (isEnum) {
                flags &= 0xFFFFFFEF;
            }
            if (isEnum || isInterface) {
                flags &= 0xFFFFFBFF;
            }
            if (classNode.isSyntheticPublic() && this.hasPackageScopeXform(classNode, PackageScopeTarget.CLASS)) {
                flags &= 0xFFFFFFFE;
            }
            JavaStubGenerator.printModifiers(out, flags);
            if (isInterface) {
                if (isAnnotationDefinition) {
                    out.print("@");
                }
                out.print("interface ");
            } else if (isEnum) {
                out.print("enum ");
            } else {
                out.print("class ");
            }
            String className = classNode.getNameWithoutPackage();
            if (classNode instanceof InnerClassNode) {
                className = className.substring(className.lastIndexOf(36) + 1);
            }
            out.println(className);
            this.printGenericsBounds(out, classNode, true);
            ClassNode superClass = classNode.getUnresolvedSuperClass(false);
            if (!isInterface && !isEnum) {
                out.print("  extends ");
                this.printType(out, superClass);
            }
            if ((interfaces = classNode.getInterfaces()) != null && interfaces.length > 0 && !isAnnotationDefinition) {
                if (isInterface) {
                    out.println("  extends");
                } else {
                    out.println("  implements");
                }
                for (int i = 0; i < interfaces.length - 1; ++i) {
                    out.print("    ");
                    this.printType(out, interfaces[i]);
                    out.print(",");
                }
                out.print("    ");
                this.printType(out, interfaces[interfaces.length - 1]);
            }
            out.println(" {");
            this.printFields(out, classNode, isInterface);
            this.printMethods(out, classNode, isEnum);
            Iterator<InnerClassNode> inner = classNode.getInnerClasses();
            while (inner.hasNext()) {
                this.constructors.clear();
                this.propertyMethods.clear();
                this.printClassContents(out, inner.next());
            }
            out.println("}");
        }
        finally {
            this.constructors.clear();
            this.propertyMethods.clear();
        }
    }

    private void printFields(PrintWriter out, ClassNode classNode, boolean ifaceOrTrait) {
        List<FieldNode> fields = classNode.getFields();
        if (!fields.isEmpty()) {
            ArrayList<FieldNode> enumFields = new ArrayList<FieldNode>();
            ArrayList<FieldNode> normalFields = new ArrayList<FieldNode>();
            for (FieldNode field : fields) {
                int flags = field.getModifiers();
                if (this.hasPackageScopeXform(field, PackageScopeTarget.FIELDS)) {
                    List<AnnotationNode> annotations = field.getAnnotations();
                    field = new FieldNode(field.getName(), flags &= 0xFFFFEFFD, field.getType(), field.getOwner(), field.getInitialExpression());
                    field.setDeclaringClass(classNode);
                    field.addAnnotations(annotations);
                }
                if ((flags & 0x4000) != 0) {
                    enumFields.add(field);
                    continue;
                }
                if ((flags & 0x1002) != 0) continue;
                normalFields.add(field);
            }
            JavaStubGenerator.printEnumFields(out, enumFields);
            for (FieldNode normalField : normalFields) {
                this.printField(out, normalField, ifaceOrTrait);
            }
        }
    }

    private static void printEnumFields(PrintWriter out, List<FieldNode> fields) {
        if (!fields.isEmpty()) {
            int i = 0;
            for (FieldNode field : fields) {
                if (i++ != 0) {
                    out.print(", ");
                }
                out.print(field.getName());
            }
        }
        out.println(';');
    }

    private void printField(PrintWriter out, FieldNode field, boolean ifaceOrTrait) {
        this.printAnnotations(out, field);
        if (!ifaceOrTrait) {
            JavaStubGenerator.printModifiers(out, field.getModifiers());
        }
        ClassNode type = field.getType();
        this.printType(out, type);
        out.print(' ');
        out.print(field.getName());
        if (ifaceOrTrait || field.isFinal()) {
            out.print(" = ");
            if (field.isStatic()) {
                Expression value = ExpressionUtils.transformInlineConstants(field.getInitialValueExpression(), type);
                if (value instanceof ConstantExpression) {
                    if (ClassHelper.isPrimitiveType(type)) {
                        value = Verifier.transformToPrimitiveConstantIfPossible((ConstantExpression)value);
                    }
                    if ((type.equals(value.getType()) || WideningCategories.isLongCategory(type) && ClassHelper.isPrimitiveInt(value.getType()) || WideningCategories.isFloatingCategory(type) && ClassHelper.isBigDecimalType(value.getType())) && (ClassHelper.isPrimitiveBoolean(type) || ClassHelper.isStaticConstantInitializerType(type))) {
                        this.printValue(out, type, value);
                        out.println(';');
                        return;
                    }
                }
                if (ClassHelper.isPrimitiveType(type) || ClassHelper.isStringType(type)) {
                    out.print("new " + ClassHelper.getWrapper(type) + "(");
                    this.printValue(out, type, GeneralUtils.defaultValueX(type));
                    out.print(')');
                } else {
                    out.print("null");
                }
            } else if (ClassHelper.isPrimitiveType(type)) {
                if (ClassHelper.isPrimitiveBoolean(type)) {
                    out.print("false");
                } else {
                    out.print('(');
                    this.printTypeName(out, type);
                    out.print(')');
                    out.print('0');
                }
            } else {
                out.print("null");
            }
        }
        out.println(';');
    }

    private void printMethods(PrintWriter out, ClassNode classNode, boolean isEnum) {
        if (!isEnum) {
            this.printConstructors(out, classNode);
        }
        ArrayList<MethodNode> methods = new ArrayList<MethodNode>(this.propertyMethods.values());
        methods.addAll(classNode.getMethods());
        for (MethodNode method : methods) {
            if (isEnum && method.isSynthetic()) {
                String name = method.getName();
                Parameter[] params = method.getParameters();
                if (params.length == 0 && name.equals("values") || params.length == 1 && name.equals("valueOf") && ClassHelper.isStringType(params[0].getType())) continue;
            }
            this.printMethod(out, classNode, method);
        }
        for (ClassNode trait : JavaStubGenerator.findTraits(classNode)) {
            Map<String, ClassNode> generics = trait.isUsingGenerics() ? GenericsUtils.createGenericsSpec(trait) : null;
            List<MethodNode> traitMethods = trait.getMethods();
            for (MethodNode traitOrigMethod : traitMethods) {
                MethodNode traitMethod = GenericsUtils.correctToGenericsSpec(generics, traitOrigMethod);
                MethodNode existingMethod = classNode.getMethod(traitMethod.getName(), traitMethod.getParameters());
                if (existingMethod != null) continue;
                for (MethodNode propertyMethod : this.propertyMethods.values()) {
                    boolean sameParams;
                    if (!propertyMethod.getName().equals(traitMethod.getName()) || !(sameParams = JavaStubGenerator.sameParameterTypes(propertyMethod, traitMethod))) continue;
                    existingMethod = propertyMethod;
                    break;
                }
                if (existingMethod != null || !JavaStubGenerator.isConcreteTraitMethod(trait, traitMethod)) continue;
                this.printMethod(out, classNode, traitMethod);
            }
        }
    }

    private static boolean sameParameterTypes(MethodNode firstMethod, MethodNode secondMethod) {
        return JavaStubGenerator.sameParameterTypes(firstMethod.getParameters(), secondMethod.getParameters());
    }

    private static boolean sameParameterTypes(Parameter[] firstParams, Parameter[] secondParams) {
        return ParameterUtils.parametersEqual(firstParams, secondParams);
    }

    private void printConstructors(PrintWriter out, ClassNode classNode) {
        ArrayList<ConstructorNode> constructors = new ArrayList<ConstructorNode>(this.constructors);
        constructors.addAll(classNode.getDeclaredConstructors());
        for (ConstructorNode constructor : constructors) {
            this.printConstructor(out, classNode, constructor);
        }
    }

    private void printConstructor(PrintWriter out, ClassNode clazz, ConstructorNode constructorNode) {
        this.printAnnotations(out, constructorNode);
        out.print("public ");
        String className = clazz.getNameWithoutPackage();
        if (clazz instanceof InnerClassNode) {
            className = className.substring(className.lastIndexOf(36) + 1);
        }
        out.println(className);
        this.printParams(out, constructorNode);
        ClassNode[] exceptions = constructorNode.getExceptions();
        this.printExceptions(out, exceptions);
        ConstructorCallExpression constrCall = ConstructorNodeUtils.getFirstIfSpecialConstructorCall(constructorNode.getCode());
        if (constrCall == null) {
            out.println(" {}");
        } else {
            out.println(" {");
            this.printSpecialConstructorArgs(out, constructorNode, constrCall);
            out.println("}");
        }
    }

    private static Parameter[] selectAccessibleConstructorFromSuper(ConstructorNode source) {
        ClassNode superType = source.getDeclaringClass().getUnresolvedSuperClass();
        Map<String, ClassNode> superTypeGenerics = GenericsUtils.createGenericsSpec(superType);
        Parameter[] bestMatch = null;
        for (ConstructorNode target : superType.getDeclaredConstructors()) {
            if (!target.isPublic() && !target.isProtected()) continue;
            Parameter[] parameters = target.getParameters();
            Parameter[] normalized = (Parameter[])Arrays.stream(parameters).map(parameter -> {
                ClassNode normalizedType = parameter.getOriginType();
                normalizedType = superType.getGenericsTypes() == null && superType.redirect().getGenericsTypes() != null ? normalizedType.getPlainNodeReference() : GenericsUtils.correctToGenericsSpecRecurse(superTypeGenerics, normalizedType);
                return new Parameter(normalizedType, parameter.getName());
            }).toArray(Parameter[]::new);
            if (JavaStubGenerator.noExceptionToAvoid(source, target)) {
                return normalized;
            }
            if (bestMatch != null) continue;
            bestMatch = normalized;
        }
        if (bestMatch != null) {
            return bestMatch;
        }
        if (superType.isPrimaryClassNode()) {
            return Parameter.EMPTY_ARRAY;
        }
        return null;
    }

    private static boolean noExceptionToAvoid(ConstructorNode fromStub, ConstructorNode fromSuper) {
        ClassNode[] superExceptions = fromSuper.getExceptions();
        if (superExceptions == null || superExceptions.length == 0) {
            return true;
        }
        ClassNode[] stubExceptions = fromStub.getExceptions();
        if (stubExceptions == null || stubExceptions.length == 0) {
            return false;
        }
        block0: for (ClassNode superExc : superExceptions) {
            for (ClassNode stub : stubExceptions) {
                if (stub.isDerivedFrom(superExc)) continue block0;
            }
            return false;
        }
        return true;
    }

    private void printSpecialConstructorArgs(PrintWriter out, ConstructorNode ctor, ConstructorCallExpression ctorCall) {
        Parameter[] params = JavaStubGenerator.selectAccessibleConstructorFromSuper(ctor);
        if (params != null) {
            out.print("super (");
            int n = params.length;
            for (int i = 0; i < n; ++i) {
                this.printDefaultValue(out, params[i].getType());
                if (i + 1 >= n) continue;
                out.print(", ");
            }
            out.println(");");
            return;
        }
        Expression arguments = ctorCall.getArguments();
        if (ctorCall.isSuperCall()) {
            out.print("super(");
        } else {
            out.print("this(");
        }
        if (arguments instanceof ArgumentListExpression) {
            List<Expression> args = ((ArgumentListExpression)arguments).getExpressions();
            int i = 0;
            int n = args.size();
            for (Expression arg : args) {
                if (arg instanceof ConstantExpression) {
                    Object value = ((ConstantExpression)arg).getValue();
                    if (value instanceof String) {
                        out.print("(String)null");
                    } else {
                        out.print(arg.getText());
                    }
                } else {
                    this.printDefaultValue(out, JavaStubGenerator.getConstructorArgumentType(arg, ctor));
                }
                if (++i >= n) continue;
                out.print(", ");
            }
        }
        out.println(");");
    }

    private static ClassNode getConstructorArgumentType(Expression arg, ConstructorNode ctor) {
        if (arg instanceof VariableExpression) {
            Variable variable = ((VariableExpression)arg).getAccessedVariable();
            if (variable instanceof DynamicVariable) {
                return ClassHelper.CLASS_Type.getPlainNodeReference();
            }
            return variable.getType();
        }
        if (arg instanceof PropertyExpression) {
            if ("class".equals(((PropertyExpression)arg).getPropertyAsString())) {
                return ClassHelper.CLASS_Type.getPlainNodeReference();
            }
            return null;
        }
        if (arg instanceof MethodCallExpression) {
            MethodNode mn;
            MethodCallExpression mce = (MethodCallExpression)arg;
            if (ExpressionUtils.isThisExpression(mce.getObjectExpression()) && (mn = ctor.getDeclaringClass().tryFindPossibleMethod(mce.getMethodAsString(), mce.getArguments())) != null) {
                return mn.getReturnType();
            }
            return null;
        }
        return arg.getType();
    }

    private void printMethod(PrintWriter out, ClassNode classNode, MethodNode methodNode) {
        if (methodNode.isStaticConstructor()) {
            return;
        }
        if (methodNode.isPrivate() || !Utilities.isJavaIdentifier(methodNode.getName())) {
            return;
        }
        if (methodNode.isSynthetic() && (methodNode.getName().equals("$getStaticMetaClass") || methodNode.getName().equals("$getLookup"))) {
            return;
        }
        this.printAnnotations(out, methodNode);
        if (!JavaStubGenerator.isInterfaceOrTrait(classNode)) {
            int modifiers = methodNode.getModifiers();
            if (JavaStubGenerator.isDefaultTraitImpl(methodNode)) {
                modifiers ^= 0x400;
            }
            JavaStubGenerator.printModifiers(out, modifiers & ~(classNode.isEnum() ? 1024 : 0));
        }
        JavaStubGenerator.printGenericsBounds(out, methodNode.getGenericsTypes());
        out.print(" ");
        this.printType(out, methodNode.getReturnType());
        out.print(" ");
        out.print(methodNode.getName());
        this.printParams(out, methodNode);
        ClassNode[] exceptions = methodNode.getExceptions();
        this.printExceptions(out, exceptions);
        if (Traits.isTrait(classNode)) {
            out.println(";");
        } else if (JavaStubGenerator.isAbstract(methodNode) && !classNode.isEnum()) {
            Statement fs;
            if (classNode.isAnnotationDefinition() && methodNode.hasAnnotationDefault() && (fs = methodNode.getFirstStatement()) instanceof ExpressionStatement) {
                ExpressionStatement es = (ExpressionStatement)fs;
                Expression re = es.getExpression();
                ClassNode rt = methodNode.getReturnType();
                Consumer<Expression> valuePrinter = value -> {
                    if (ClassHelper.isClassType(rt) || rt.isArray() && ClassHelper.isClassType(rt.getComponentType())) {
                        if (value.getType().getName().equals("groovy.lang.Closure")) {
                            out.print("groovy.lang.Closure.class");
                            return;
                        }
                        String valueText = value.getText();
                        out.print(valueText);
                        if (!valueText.endsWith(".class")) {
                            out.print(".class");
                        }
                    } else if (value instanceof ConstantExpression) {
                        this.printValue(out, rt, (Expression)value);
                    } else {
                        out.print(value.getText());
                    }
                };
                out.print(" default ");
                if (re instanceof ListExpression) {
                    out.print("{ ");
                    ListExpression le = (ListExpression)re;
                    boolean first = true;
                    for (Expression e : le.getExpressions()) {
                        if (first) {
                            first = false;
                        } else {
                            out.print(", ");
                        }
                        valuePrinter.accept(e);
                    }
                    out.print(" }");
                } else {
                    valuePrinter.accept(re);
                }
            }
            out.println(";");
        } else {
            out.print(" { ");
            ClassNode type = methodNode.getReturnType();
            if (!ClassHelper.isPrimitiveVoid(type)) {
                out.print("return ");
                this.printDefaultValue(out, type);
                out.print(";");
            }
            out.println("}");
        }
    }

    private void printExceptions(PrintWriter out, ClassNode[] exceptions) {
        for (int i = 0; i < exceptions.length; ++i) {
            ClassNode exception = exceptions[i];
            if (i == 0) {
                out.print("throws ");
            } else {
                out.print(", ");
            }
            this.printType(out, exception);
        }
    }

    private void printValue(PrintWriter out, ClassNode type, Expression value) {
        ClassNode valueType = ClassHelper.getUnwrapper(value.getType());
        if (ClassHelper.isPrimitiveChar(valueType)) {
            out.print("'");
            out.print(JavaStubGenerator.escapeSpecialChars(value.getText().substring(0, 1)));
            out.print("'");
        } else if (ClassHelper.isStringType(valueType)) {
            out.print('\"');
            out.print(JavaStubGenerator.escapeSpecialChars(value.getText()));
            out.print('\"');
        } else if (ClassHelper.isPrimitiveDouble(valueType)) {
            out.print(value.getText());
            out.print('d');
        } else if (ClassHelper.isPrimitiveFloat(valueType)) {
            out.print(value.getText());
            out.print('f');
        } else if (ClassHelper.isPrimitiveLong(valueType)) {
            out.print(value.getText());
            out.print('L');
        } else {
            if (!(ClassHelper.isPrimitiveInt(valueType) || ClassHelper.isPrimitiveBoolean(valueType) || ClassHelper.isBigDecimalType(valueType))) {
                out.print('(');
                this.printType(out, type);
                out.print(')');
            }
            out.print(value.getText());
        }
    }

    private void printDefaultValue(PrintWriter out, ClassNode type) {
        if (type != null && !ClassHelper.isPrimitiveBoolean(type)) {
            out.print("(");
            this.printType(out, type);
            out.print(")");
        }
        if (type != null && ClassHelper.isPrimitiveType(type)) {
            if (ClassHelper.isPrimitiveBoolean(type)) {
                out.print("false");
            } else {
                out.print("0");
            }
        } else {
            out.print("null");
        }
    }

    private void printType(PrintWriter out, ClassNode type) {
        if (type.isArray()) {
            this.printType(out, type.getComponentType());
            out.print("[]");
        } else if (this.java5 && type.isGenericsPlaceHolder()) {
            out.print(type.getUnresolvedName());
        } else {
            this.printGenericsBounds(out, type, false);
        }
    }

    private void printTypeName(PrintWriter out, ClassNode type) {
        if (ClassHelper.isPrimitiveType(type)) {
            if (ClassHelper.isPrimitiveBoolean(type)) {
                out.print("boolean");
            } else if (ClassHelper.isPrimitiveChar(type)) {
                out.print("char");
            } else if (ClassHelper.isPrimitiveInt(type)) {
                out.print("int");
            } else if (ClassHelper.isPrimitiveShort(type)) {
                out.print("short");
            } else if (ClassHelper.isPrimitiveLong(type)) {
                out.print("long");
            } else if (ClassHelper.isPrimitiveFloat(type)) {
                out.print("float");
            } else if (ClassHelper.isPrimitiveDouble(type)) {
                out.print("double");
            } else if (ClassHelper.isPrimitiveByte(type)) {
                out.print("byte");
            } else {
                out.print("void");
            }
        } else {
            String name = type.getName();
            ClassNode alias = this.currentModule.getImportType(name);
            if (alias != null) {
                name = alias.getName();
            }
            out.print(name.replace('$', '.'));
        }
    }

    private void printGenericsBounds(PrintWriter out, ClassNode type, boolean skipName) {
        if (!skipName) {
            this.printTypeName(out, type);
        }
        if (this.java5 && !ClassHelper.isCachedType(type)) {
            JavaStubGenerator.printGenericsBounds(out, type.getGenericsTypes());
        }
    }

    private static void printGenericsBounds(PrintWriter out, GenericsType[] genericsTypes) {
        if (genericsTypes == null || genericsTypes.length == 0) {
            return;
        }
        out.print('<');
        for (int i = 0; i < genericsTypes.length; ++i) {
            if (i != 0) {
                out.print(", ");
            }
            out.print(genericsTypes[i].toString().replace("$", "."));
        }
        out.print('>');
    }

    private void printParams(PrintWriter out, MethodNode methodNode) {
        out.print("(");
        Parameter[] parameters = methodNode.getParameters();
        if (parameters != null && parameters.length != 0) {
            int lastIndex = parameters.length - 1;
            boolean vararg = parameters[lastIndex].getType().isArray();
            for (int i = 0; i != parameters.length; ++i) {
                this.printAnnotations(out, parameters[i]);
                if (i == lastIndex && vararg) {
                    this.printType(out, parameters[i].getType().getComponentType());
                    out.print("...");
                } else {
                    this.printType(out, parameters[i].getType());
                }
                out.print(" ");
                out.print(parameters[i].getName());
                if (i + 1 >= parameters.length) continue;
                out.print(", ");
            }
        }
        out.print(")");
    }

    private void printAnnotations(PrintWriter out, AnnotatedNode annotated) {
        if (!this.java5) {
            return;
        }
        for (AnnotationNode annotation : annotated.getAnnotations()) {
            if (annotation.getClassNode().equals(PACKAGE_SCOPE_TYPE)) continue;
            this.printAnnotation(out, annotation);
        }
    }

    private void printAnnotation(PrintWriter out, AnnotationNode annotation) {
        out.print("@" + annotation.getClassNode().getName().replace('$', '.') + "(");
        boolean first = true;
        Map<String, Expression> members = annotation.getMembers();
        for (Map.Entry<String, Expression> entry : members.entrySet()) {
            String key = entry.getKey();
            if (first) {
                first = false;
            } else {
                out.print(", ");
            }
            out.print(key + "=" + this.getAnnotationValue(entry.getValue()));
        }
        out.print(") ");
    }

    private String getAnnotationValue(Object memberValue) {
        String val = "null";
        boolean replaceDollars = true;
        if (memberValue instanceof ListExpression) {
            StringBuilder sb = new StringBuilder("{");
            boolean first = true;
            ListExpression le = (ListExpression)memberValue;
            for (Expression e : le.getExpressions()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append(this.getAnnotationValue(e));
            }
            sb.append("}");
            val = sb.toString();
        } else if (memberValue instanceof ConstantExpression) {
            ConstantExpression ce = (ConstantExpression)memberValue;
            Object constValue = ce.getValue();
            if (constValue instanceof AnnotationNode) {
                StringBuilderWriter writer = new StringBuilderWriter();
                PrintWriter out = new PrintWriter(writer);
                this.printAnnotation(out, (AnnotationNode)constValue);
                val = ((Object)writer).toString();
            } else if (constValue instanceof Number || constValue instanceof Boolean) {
                val = constValue.toString();
            } else if (constValue instanceof Enum) {
                val = constValue.getClass().getName() + "." + constValue.toString();
            } else {
                val = "\"" + JavaStubGenerator.escapeSpecialChars(constValue.toString()) + "\"";
                replaceDollars = false;
            }
        } else if (memberValue instanceof PropertyExpression) {
            val = ((Expression)memberValue).getText();
        } else if (memberValue instanceof VariableExpression) {
            val = ((Expression)memberValue).getText();
            ImportNode alias = this.currentModule.getStaticImports().get(val);
            if (alias != null) {
                val = alias.getClassName() + "." + alias.getFieldName();
            }
        } else if (memberValue instanceof ClosureExpression) {
            val = "groovy.lang.Closure.class";
        } else if (memberValue instanceof ClassExpression) {
            val = ((Expression)memberValue).getText() + ".class";
        }
        return replaceDollars ? val.replace('$', '.') : val;
    }

    private static void printModifiers(PrintWriter out, int modifiers) {
        if ((modifiers & 1) != 0) {
            out.print("public ");
        }
        if ((modifiers & 4) != 0) {
            out.print("protected ");
        }
        if ((modifiers & 2) != 0) {
            out.print("private ");
        }
        if ((modifiers & 8) != 0) {
            out.print("static ");
        }
        if ((modifiers & 0x20) != 0) {
            out.print("synchronized ");
        }
        if ((modifiers & 0x10) != 0) {
            out.print("final ");
        }
        if ((modifiers & 0x400) != 0) {
            out.print("abstract ");
        }
    }

    private void printImports(PrintWriter out) {
        Map<String, ImportNode> staticImports = this.currentModule.getStaticImports();
        Map<String, ImportNode> staticStarImports = this.currentModule.getStaticStarImports();
        for (Map.Entry<String, ImportNode> entry : staticImports.entrySet()) {
            String memberName = entry.getKey();
            if (!memberName.equals(entry.getValue().getFieldName()) || Character.isLowerCase(memberName.charAt(0))) continue;
            out.println("import static " + entry.getValue().getType().getName().replace('$', '.') + "." + memberName + ";");
        }
        for (ImportNode ssi : staticStarImports.values()) {
            out.println("import static " + ssi.getType().getName().replace('$', '.') + ".*;");
        }
        out.println();
        if (!Boolean.TRUE.equals(this.currentModule.getNodeMetaData("require.imports"))) {
            return;
        }
        for (ImportNode i : this.currentModule.getImports()) {
            if (!i.getType().hasPackageName() || i.getAlias() != null && !i.getAlias().equals(i.getType().getNameWithoutPackage())) continue;
            out.println("import " + i.getType().getName().replace('$', '.') + ";");
        }
        for (ImportNode si : this.currentModule.getStarImports()) {
            out.println("import " + si.getPackageName() + "*;");
        }
        out.println();
    }

    private File createJavaStubFile(String path) {
        return new File(this.outputPath, path + ".java");
    }

    private static String escapeSpecialChars(String value) {
        return FormatHelper.escapeBackslashes(value).replace("\"", "\\\"");
    }

    private static boolean isInterfaceOrTrait(ClassNode cn) {
        return cn.isInterface() || Traits.isTrait(cn);
    }

    private static boolean isAbstract(MethodNode methodNode) {
        return methodNode.isAbstract() && !JavaStubGenerator.isDefaultTraitImpl(methodNode);
    }

    private static boolean isDefaultTraitImpl(MethodNode methodNode) {
        return Traits.isTrait(methodNode.getDeclaringClass()) && Traits.hasDefaultImplementation(methodNode);
    }

    private static boolean isConcreteTraitMethod(ClassNode trait, MethodNode method) {
        boolean isSynthetic;
        if (!(trait.redirect() instanceof DecompiledClassNode)) {
            return !method.isAbstract();
        }
        boolean bl = isSynthetic = (method.getModifiers() & 0x1000) != 0;
        if (!isSynthetic && !method.getName().contains("$")) {
            for (MethodNode helperMethod : Traits.findHelper(trait).getMethods(method.getName())) {
                Parameter[] params = helperMethod.getParameters();
                if (!JavaStubGenerator.sameParameterTypes(params = Arrays.copyOfRange(params, 1, params.length), method.getParameters())) continue;
                return true;
            }
        }
        return false;
    }

    private boolean hasPackageScopeXform(AnnotatedNode node, final PackageScopeTarget type) {
        boolean member = !(node instanceof ClassNode) && type != PackageScopeTarget.CLASS;
        for (AnnotationNode anno : node.getAnnotations()) {
            if (!anno.getClassNode().equals(PACKAGE_SCOPE_TYPE)) continue;
            Expression expr = anno.getMember("value");
            if (expr == null) {
                return member || node instanceof ClassNode && type == PackageScopeTarget.CLASS;
            }
            final boolean[] val = new boolean[1];
            expr.visit(new CodeVisitorSupport(){

                @Override
                public void visitPropertyExpression(PropertyExpression property) {
                    if (property.getObjectExpression().getText().equals("groovy.transform.PackageScopeTarget") && property.getPropertyAsString().equals(type.name())) {
                        val[0] = true;
                    }
                }

                @Override
                public void visitVariableExpression(VariableExpression variable) {
                    if (variable.getName().equals(type.name())) {
                        ImportNode imp = JavaStubGenerator.this.currentModule.getStaticImports().get(type.name());
                        if (imp != null && imp.getType().getName().equals("groovy.transform.PackageScopeTarget")) {
                            val[0] = true;
                        } else if (imp == null && JavaStubGenerator.this.currentModule.getStaticStarImports().get("groovy.transform.PackageScopeTarget") != null) {
                            val[0] = true;
                        }
                    }
                }
            });
            return val[0];
        }
        if (member) {
            return this.hasPackageScopeXform(node.getDeclaringClass(), type);
        }
        return false;
    }

    public Set<JavaFileObject> getJavaStubCompilationUnitSet() {
        return this.javaStubCompilationUnitSet;
    }

    public void clean() {
        Stream javaFileObjectStream = this.javaStubCompilationUnitSet.size() < 2 ? this.javaStubCompilationUnitSet.stream() : this.javaStubCompilationUnitSet.parallelStream();
        javaFileObjectStream.forEach(FileObject::delete);
        this.javaStubCompilationUnitSet.clear();
    }
}

