/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import groovy.lang.GeneratedGroovyProxy;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyRuntimeException;
import groovyjarjarasm.asm.ClassVisitor;
import groovyjarjarasm.asm.ClassWriter;
import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.Type;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.classgen.asm.BytecodeHelper;
import org.codehaus.groovy.classgen.asm.util.TypeUtil;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.ErrorCollector;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.reflection.ReflectionUtils;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.tools.GroovyClass;
import org.codehaus.groovy.transform.trait.Traits;

public class ProxyGeneratorAdapter
extends ClassVisitor {
    private static final Map<String, Boolean> EMPTY_DELEGATECLOSURE_MAP = Collections.emptyMap();
    private static final Object[] EMPTY_ARGS = new Object[0];
    private static final String CLOSURES_MAP_FIELD = "$closures$delegate$map";
    private static final String DELEGATE_OBJECT_FIELD = "$delegate";
    private static final AtomicLong proxyCounter = new AtomicLong();
    private static final List<Method> OBJECT_METHODS = ProxyGeneratorAdapter.getInheritedMethods(Object.class, new ArrayList<Method>());
    private static final List<Method> GROOVYOBJECT_METHODS = ProxyGeneratorAdapter.getInheritedMethods(GroovyObject.class, new ArrayList<Method>());
    private static final Set<String> GROOVYOBJECT_METHOD_NAMES;
    private final String proxyName;
    private final Class superClass;
    private final Class delegateClass;
    private final InnerLoader innerLoader;
    private final Set<Class> implClasses;
    private final Set<Object> visitedMethods;
    private final Set<String> objectDelegateMethods;
    private final Map<String, Boolean> delegatedClosures;
    private final boolean emptyBody;
    private final boolean hasWildcard;
    private final boolean generateDelegateField;
    private final Class cachedClass;
    private final Constructor cachedNoArgConstructor;

    public ProxyGeneratorAdapter(Map<Object, Object> closureMap, Class superClass, Class[] interfaces, ClassLoader proxyLoader, boolean emptyBody, Class delegateClass) {
        super(589824, new ClassWriter(3));
        Constructor constructor;
        Class[] classArray;
        this.innerLoader = proxyLoader != null ? ProxyGeneratorAdapter.createInnerLoader(proxyLoader, interfaces) : this.findClassLoader(superClass, interfaces);
        this.visitedMethods = new LinkedHashSet<Object>();
        this.delegatedClosures = closureMap.isEmpty() ? EMPTY_DELEGATECLOSURE_MAP : new HashMap();
        boolean wildcard = false;
        for (Map.Entry<Object, Object> entry : closureMap.entrySet()) {
            String name = entry.getKey().toString();
            if ("*".equals(name)) {
                wildcard = true;
            }
            this.delegatedClosures.put(name, Boolean.FALSE);
        }
        this.hasWildcard = wildcard;
        Class<?> fixedSuperClass = this.adjustSuperClass(superClass, interfaces);
        this.generateDelegateField = delegateClass != null;
        this.objectDelegateMethods = this.generateDelegateField ? ProxyGeneratorAdapter.createDelegateMethodList(fixedSuperClass, delegateClass, interfaces) : Collections.emptySet();
        this.delegateClass = delegateClass;
        this.superClass = fixedSuperClass;
        this.implClasses = new LinkedHashSet<Class>();
        this.implClasses.add(superClass);
        if (this.generateDelegateField) {
            this.implClasses.add(delegateClass);
            Arrays.stream(delegateClass.getInterfaces()).filter(i -> !ReflectionUtils.isSealed(i)).forEach(this.implClasses::add);
        }
        if (interfaces != null) {
            Collections.addAll(this.implClasses, interfaces);
        }
        this.proxyName = this.proxyName();
        this.emptyBody = emptyBody;
        ClassWriter writer = (ClassWriter)this.cv;
        this.visit(CompilerConfiguration.DEFAULT.getBytecodeVersion(), 1, this.proxyName, null, null, null);
        byte[] b = writer.toByteArray();
        this.cachedClass = this.innerLoader.defineClass(this.proxyName.replace('/', '.'), b);
        if (this.generateDelegateField) {
            Class[] classArray2 = new Class[2];
            classArray2[0] = Map.class;
            classArray = classArray2;
            classArray2[1] = delegateClass;
        } else {
            Class[] classArray3 = new Class[1];
            classArray = classArray3;
            classArray3[0] = Map.class;
        }
        Class[] args = classArray;
        try {
            constructor = this.cachedClass.getConstructor(args);
        }
        catch (NoSuchMethodException e) {
            constructor = null;
        }
        this.cachedNoArgConstructor = constructor;
    }

    private Class<?> adjustSuperClass(Class<?> superClass, Class<?>[] interfaces) {
        if (!superClass.isInterface()) {
            return superClass;
        }
        if (interfaces == null || interfaces.length == 0) {
            interfaces = new Class[]{superClass};
        }
        assert (Arrays.asList(interfaces).contains(superClass));
        Set<ClassNode> traits = ProxyGeneratorAdapter.collectTraits(interfaces);
        if (!traits.isEmpty()) {
            String name = superClass.getName() + "$TraitAdapter";
            ClassNode cn = new ClassNode(name, 1025, ClassHelper.OBJECT_TYPE, traits.toArray(ClassNode.EMPTY_ARRAY), null);
            CompilationUnit cu = new CompilationUnit(this.innerLoader);
            CompilerConfiguration config = new CompilerConfiguration();
            SourceUnit su = new SourceUnit(name + "wrapper", "", config, (GroovyClassLoader)this.innerLoader, new ErrorCollector(config));
            cu.addSource(su);
            cu.compile(3);
            su.getAST().addClass(cn);
            cu.compile(7);
            List<GroovyClass> classes = cu.getClasses();
            for (GroovyClass groovyClass : classes) {
                if (!groovyClass.getName().equals(name)) continue;
                return this.innerLoader.defineClass(name, groovyClass.getBytes());
            }
        }
        return Object.class;
    }

    private static Set<ClassNode> collectTraits(Class<?>[] interfaces) {
        LinkedHashSet<ClassNode> traits = new LinkedHashSet<ClassNode>();
        for (Class<?> face : interfaces) {
            for (ClassNode node : ClassHelper.make(face).getAllInterfaces()) {
                if (!Traits.isTrait(node)) continue;
                traits.add(node.getPlainNodeReference());
                LinkedHashSet<ClassNode> selfTypes = new LinkedHashSet<ClassNode>();
                Traits.collectSelfTypes(node, selfTypes, true, true);
                for (ClassNode selfType : selfTypes) {
                    if (!Traits.isTrait(selfType)) continue;
                    traits.add(selfType.getPlainNodeReference());
                }
            }
        }
        return traits;
    }

    private static InnerLoader createInnerLoader(ClassLoader parent, Class<?>[] interfaces) {
        return AccessController.doPrivileged(() -> new InnerLoader(parent, interfaces));
    }

    private InnerLoader findClassLoader(Class<?> clazz, Class<?>[] interfaces) {
        ClassLoader cl = clazz.getClassLoader();
        if (cl == null) {
            cl = this.getClass().getClassLoader();
        }
        return ProxyGeneratorAdapter.createInnerLoader(cl, interfaces);
    }

    private static Set<String> createDelegateMethodList(Class<?> superClass, Class<?> delegateClass, Class<?>[] interfaces) {
        HashSet<String> selectedMethods = new HashSet<String>();
        ArrayList<Method> interfaceMethods = new ArrayList<Method>();
        ArrayList<Method> superClassMethods = new ArrayList<Method>();
        Collections.addAll(superClassMethods, superClass.getDeclaredMethods());
        if (interfaces != null) {
            for (Class<?> thisInterface : interfaces) {
                ProxyGeneratorAdapter.getInheritedMethods(thisInterface, interfaceMethods);
            }
            for (Method method : interfaceMethods) {
                if (ProxyGeneratorAdapter.containsEquivalentMethod(superClassMethods, method)) continue;
                selectedMethods.add(method.getName() + Type.getMethodDescriptor(method));
            }
        }
        List<Method> additionalMethods = ProxyGeneratorAdapter.getInheritedMethods(delegateClass, new ArrayList<Method>());
        for (Method method : additionalMethods) {
            if (method.getName().indexOf(36) != -1 || ProxyGeneratorAdapter.containsEquivalentMethod(interfaceMethods, method) || ProxyGeneratorAdapter.containsEquivalentMethod(OBJECT_METHODS, method) || ProxyGeneratorAdapter.containsEquivalentMethod(GROOVYOBJECT_METHODS, method)) continue;
            selectedMethods.add(method.getName() + Type.getMethodDescriptor(method));
        }
        return selectedMethods;
    }

    private static List<Method> getInheritedMethods(Class<?> baseClass, List<Method> methods) {
        Collections.addAll(methods, baseClass.getMethods());
        for (Class<?> currentClass = baseClass; currentClass != null; currentClass = currentClass.getSuperclass()) {
            Method[] protectedMethods;
            for (Method method : protectedMethods = currentClass.getDeclaredMethods()) {
                if (method.getName().indexOf(36) != -1 || !Modifier.isProtected(method.getModifiers()) || ProxyGeneratorAdapter.containsEquivalentMethod(methods, method)) continue;
                methods.add(method);
            }
        }
        return methods;
    }

    private static boolean containsEquivalentMethod(Collection<Method> publicAndProtectedMethods, Method candidate) {
        for (Method method : publicAndProtectedMethods) {
            if (!candidate.getName().equals(method.getName()) || !candidate.getReturnType().equals(method.getReturnType()) || !ProxyGeneratorAdapter.hasMatchingParameterTypes(candidate, method)) continue;
            return true;
        }
        return false;
    }

    private static boolean hasMatchingParameterTypes(Method method, Method candidate) {
        Class<?>[] methodParamTypes;
        Class<?>[] candidateParamTypes = candidate.getParameterTypes();
        if (candidateParamTypes.length != (methodParamTypes = method.getParameterTypes()).length) {
            return false;
        }
        for (int i = 0; i < methodParamTypes.length; ++i) {
            if (candidateParamTypes[i].equals(methodParamTypes[i])) continue;
            return false;
        }
        return true;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        boolean addGroovyObjectSupport;
        LinkedHashSet<String> interfacesSet = new LinkedHashSet<String>();
        if (interfaces != null) {
            Collections.addAll(interfacesSet, interfaces);
        }
        for (Class extraInterface : this.implClasses) {
            if (!extraInterface.isInterface()) continue;
            interfacesSet.add(BytecodeHelper.getClassInternalName(extraInterface));
        }
        boolean bl = addGroovyObjectSupport = !GroovyObject.class.isAssignableFrom(this.superClass);
        if (addGroovyObjectSupport) {
            interfacesSet.add("groovy/lang/GroovyObject");
        }
        if (this.generateDelegateField) {
            this.implClasses.add(GeneratedGroovyProxy.class);
            interfacesSet.add("groovy/lang/GeneratedGroovyProxy");
        }
        super.visit(CompilerConfiguration.DEFAULT.getBytecodeVersion(), 1, this.proxyName, signature, BytecodeHelper.getClassInternalName(this.superClass), interfacesSet.toArray(new String[0]));
        this.visitMethod(1, "<init>", "()V", null, null);
        this.addDelegateFields();
        if (addGroovyObjectSupport) {
            this.createGroovyObjectSupport();
        }
        for (Class clazz : this.implClasses) {
            this.visitClass(clazz);
        }
    }

    private void visitClass(Class<?> clazz) {
        Method[] methods;
        for (Method method : methods = clazz.getDeclaredMethods()) {
            Class<?>[] classArray = method.getExceptionTypes();
            String[] exceptions = new String[classArray.length];
            for (int i = 0; i < exceptions.length; ++i) {
                exceptions[i] = BytecodeHelper.getClassInternalName(classArray[i]);
            }
            this.visitMethod(method.getModifiers(), method.getName(), BytecodeHelper.getMethodDescriptor(method.getReturnType(), method.getParameterTypes()), null, exceptions);
        }
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] exceptionTypes = constructor.getExceptionTypes();
            String[] exceptions = new String[exceptionTypes.length];
            for (int i = 0; i < exceptions.length; ++i) {
                exceptions[i] = BytecodeHelper.getClassInternalName(exceptionTypes[i]);
            }
            this.visitMethod(constructor.getModifiers(), "<init>", BytecodeHelper.getMethodDescriptor(Void.TYPE, constructor.getParameterTypes()), null, exceptions);
        }
        for (GenericDeclaration genericDeclaration : clazz.getInterfaces()) {
            this.visitClass((Class<?>)genericDeclaration);
        }
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            this.visitClass(superclass);
        }
        for (Map.Entry<String, Boolean> entry : this.delegatedClosures.entrySet()) {
            String name;
            Boolean bl = entry.getValue();
            if (bl.booleanValue() || "*".equals(name = entry.getKey())) continue;
            this.visitMethod(1, name, "([Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        }
    }

    private void createGroovyObjectSupport() {
        this.visitField(130, "metaClass", "Lgroovy/lang/MetaClass;", null, null);
        MethodVisitor mv = super.visitMethod(1, "getMetaClass", "()Lgroovy/lang/MetaClass;", null, null);
        mv.visitCode();
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitVarInsn(25, 0);
        mv.visitFieldInsn(180, this.proxyName, "metaClass", "Lgroovy/lang/MetaClass;");
        Label l1 = new Label();
        mv.visitJumpInsn(199, l1);
        Label l2 = new Label();
        mv.visitLabel(l2);
        mv.visitVarInsn(25, 0);
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/InvokerHelper", "getMetaClass", "(Ljava/lang/Class;)Lgroovy/lang/MetaClass;", false);
        mv.visitFieldInsn(181, this.proxyName, "metaClass", "Lgroovy/lang/MetaClass;");
        mv.visitLabel(l1);
        mv.visitVarInsn(25, 0);
        mv.visitFieldInsn(180, this.proxyName, "metaClass", "Lgroovy/lang/MetaClass;");
        mv.visitInsn(176);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = super.visitMethod(1, "setMetaClass", "(Lgroovy/lang/MetaClass;)V", null, null);
        mv.visitCode();
        l0 = new Label();
        mv.visitLabel(l0);
        mv.visitVarInsn(25, 0);
        mv.visitVarInsn(25, 1);
        mv.visitFieldInsn(181, this.proxyName, "metaClass", "Lgroovy/lang/MetaClass;");
        l1 = new Label();
        mv.visitLabel(l1);
        mv.visitInsn(177);
        l2 = new Label();
        mv.visitLabel(l2);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private void addDelegateFields() {
        this.visitField(18, CLOSURES_MAP_FIELD, "Ljava/util/Map;", null, null);
        if (this.generateDelegateField) {
            this.visitField(18, DELEGATE_OBJECT_FIELD, BytecodeHelper.getTypeDescription(this.delegateClass), null, null);
        }
    }

    private String proxyName() {
        int index;
        String name;
        String string = name = this.delegateClass != null ? this.delegateClass.getName() : this.superClass.getName();
        if (name.startsWith("[") && name.endsWith(";")) {
            name = name.substring(1, name.length() - 1) + "_array";
        }
        if ((index = name.lastIndexOf(46)) == -1) {
            return name + proxyCounter.incrementAndGet() + "_groovyProxy";
        }
        return name.substring(index + 1) + proxyCounter.incrementAndGet() + "_groovyProxy";
    }

    private static boolean isImplemented(Class<?> clazz, String name, String desc) {
        Method[] methods;
        for (Method method : methods = clazz.getDeclaredMethods()) {
            if (!method.getName().equals(name) || !desc.equals(Type.getMethodDescriptor(method))) continue;
            return !Modifier.isAbstract(method.getModifiers());
        }
        Class<?> parent = clazz.getSuperclass();
        return parent != null && ProxyGeneratorAdapter.isImplemented(parent, name, desc);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        boolean wildcardDelegate;
        if ((access & 0x1112) != 0) {
            return null;
        }
        List<String> key = Arrays.asList(name, desc);
        if (!this.visitedMethods.add(key)) {
            return null;
        }
        boolean objectDelegate = this.objectDelegateMethods.contains(name + desc);
        boolean closureDelegate = this.delegatedClosures.containsKey(name);
        boolean bl = wildcardDelegate = this.hasWildcard && !"<init>".equals(name);
        if ((objectDelegate || closureDelegate || wildcardDelegate) && !Modifier.isStatic(access)) {
            if (!GROOVYOBJECT_METHOD_NAMES.contains(name) && (!Modifier.isAbstract(this.superClass.getModifiers()) || !ProxyGeneratorAdapter.isImplemented(this.superClass, name, desc) || (objectDelegate || closureDelegate) && Arrays.stream(this.superClass.getMethods()).filter(m -> m.getName().equals(name)).mapToInt(Method::getModifiers).noneMatch(Modifier::isAbstract))) {
                if (closureDelegate || wildcardDelegate || !objectDelegate || !this.generateDelegateField) {
                    this.delegatedClosures.put(name, Boolean.TRUE);
                    return this.makeDelegateToClosureCall(name, desc, signature, exceptions, access & 0xFFFFFBFF);
                }
                return this.makeDelegateCall(name, desc, signature, exceptions, access & 0xFFFFFBFF);
            }
        } else {
            if ("getProxyTarget".equals(name) && "()Ljava/lang/Object;".equals(desc)) {
                return this.createGetProxyTargetMethod(access, name, desc, signature, exceptions);
            }
            if ("<init>".equals(name) && (Modifier.isPublic(access) || Modifier.isProtected(access))) {
                return this.createConstructor(access, name, desc, signature, exceptions);
            }
            if (Modifier.isAbstract(access) && !GROOVYOBJECT_METHOD_NAMES.contains(name) && !ProxyGeneratorAdapter.isImplemented(this.superClass, name, desc)) {
                MethodVisitor mv = super.visitMethod(access & 0xFFFFFBFF, name, desc, signature, exceptions);
                mv.visitCode();
                if (this.emptyBody) {
                    Type returnType = Type.getReturnType(desc);
                    if (returnType == Type.VOID_TYPE) {
                        mv.visitInsn(177);
                    } else {
                        int loadIns = ProxyGeneratorAdapter.getLoadInsn(returnType);
                        switch (loadIns) {
                            case 21: {
                                mv.visitInsn(3);
                                break;
                            }
                            case 22: {
                                mv.visitInsn(9);
                                break;
                            }
                            case 23: {
                                mv.visitInsn(11);
                                break;
                            }
                            case 24: {
                                mv.visitInsn(14);
                                break;
                            }
                            default: {
                                mv.visitInsn(1);
                            }
                        }
                        mv.visitInsn(ProxyGeneratorAdapter.getReturnInsn(returnType));
                        mv.visitMaxs(0, 0);
                    }
                } else {
                    mv.visitTypeInsn(187, "java/lang/UnsupportedOperationException");
                    mv.visitInsn(89);
                    mv.visitMethodInsn(183, "java/lang/UnsupportedOperationException", "<init>", "()V", false);
                    mv.visitInsn(191);
                    mv.visitMaxs(0, 0);
                }
                mv.visitEnd();
            }
        }
        return null;
    }

    private MethodVisitor createGetProxyTargetMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(17, name, desc, signature, exceptions);
        mv.visitCode();
        mv.visitIntInsn(25, 0);
        mv.visitFieldInsn(180, this.proxyName, DELEGATE_OBJECT_FIELD, BytecodeHelper.getTypeDescription(this.delegateClass));
        mv.visitInsn(176);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        return null;
    }

    private static int registerLen(Type[] args) {
        int i = 0;
        for (Type arg : args) {
            i += ProxyGeneratorAdapter.registerLen(arg);
        }
        return i;
    }

    private static int registerLen(Type arg) {
        return arg == Type.DOUBLE_TYPE || arg == Type.LONG_TYPE ? 2 : 1;
    }

    private MethodVisitor createConstructor(int access, String name, String desc, String signature, String[] exceptions) {
        Type[] args = Type.getArgumentTypes(desc);
        StringBuilder newDesc = new StringBuilder("(");
        for (Type arg : args) {
            newDesc.append(arg.getDescriptor());
        }
        newDesc.append("Ljava/util/Map;");
        if (this.generateDelegateField) {
            newDesc.append(BytecodeHelper.getTypeDescription(this.delegateClass));
        }
        newDesc.append(")V");
        MethodVisitor mv = super.visitMethod(access, name, newDesc.toString(), signature, exceptions);
        mv.visitCode();
        this.initializeDelegateClosure(mv, args);
        if (this.generateDelegateField) {
            this.initializeDelegateObject(mv, args);
        }
        mv.visitVarInsn(25, 0);
        int idx = 1;
        for (Type arg : args) {
            if (ProxyGeneratorAdapter.isPrimitive(arg)) {
                mv.visitIntInsn(ProxyGeneratorAdapter.getLoadInsn(arg), idx);
            } else {
                mv.visitVarInsn(25, idx);
            }
            idx += ProxyGeneratorAdapter.registerLen(arg);
        }
        mv.visitMethodInsn(183, BytecodeHelper.getClassInternalName(this.superClass), "<init>", desc, false);
        mv.visitInsn(177);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        return null;
    }

    private void initializeDelegateClosure(MethodVisitor mv, Type[] args) {
        int idx = 1 + ProxyGeneratorAdapter.getTypeArgsRegisterLength(args);
        mv.visitIntInsn(25, 0);
        mv.visitIntInsn(25, idx);
        mv.visitFieldInsn(181, this.proxyName, CLOSURES_MAP_FIELD, "Ljava/util/Map;");
    }

    private void initializeDelegateObject(MethodVisitor mv, Type[] args) {
        int idx = 2 + ProxyGeneratorAdapter.getTypeArgsRegisterLength(args);
        mv.visitIntInsn(25, 0);
        mv.visitIntInsn(25, idx);
        mv.visitFieldInsn(181, this.proxyName, DELEGATE_OBJECT_FIELD, BytecodeHelper.getTypeDescription(this.delegateClass));
    }

    private static int getTypeArgsRegisterLength(Type[] args) {
        int length = 0;
        for (Type type : args) {
            length += ProxyGeneratorAdapter.registerLen(type);
        }
        return length;
    }

    protected MethodVisitor makeDelegateCall(String name, String desc, String signature, String[] exceptions, int accessFlags) {
        MethodVisitor mv = super.visitMethod(accessFlags, name, desc, signature, exceptions);
        mv.visitVarInsn(25, 0);
        mv.visitFieldInsn(180, this.proxyName, DELEGATE_OBJECT_FIELD, BytecodeHelper.getTypeDescription(this.delegateClass));
        mv.visitLdcInsn(name);
        Type[] args = Type.getArgumentTypes(desc);
        BytecodeHelper.pushConstant(mv, args.length);
        mv.visitTypeInsn(189, "java/lang/Object");
        int size = 6;
        int idx = 1;
        for (int i = 0; i < args.length; ++i) {
            Type arg = args[i];
            mv.visitInsn(89);
            BytecodeHelper.pushConstant(mv, i);
            this.boxPrimitiveType(mv, idx, arg);
            size = Math.max(size, 5 + ProxyGeneratorAdapter.registerLen(arg));
            idx += ProxyGeneratorAdapter.registerLen(arg);
            mv.visitInsn(83);
        }
        mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/InvokerHelper", "invokeMethod", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", false);
        ProxyGeneratorAdapter.unwrapResult(mv, desc);
        mv.visitMaxs(0, 0);
        return mv;
    }

    protected MethodVisitor makeDelegateToClosureCall(String name, String desc, String signature, String[] exceptions, int accessFlags) {
        MethodVisitor mv = super.visitMethod(accessFlags, name, desc, signature, exceptions);
        mv.visitCode();
        Type[] args = Type.getArgumentTypes(desc);
        int arrayStore = args.length + 1;
        BytecodeHelper.pushConstant(mv, args.length);
        mv.visitTypeInsn(189, "java/lang/Object");
        int idx = 1;
        for (int i = 0; i < args.length; ++i) {
            Type arg = args[i];
            mv.visitInsn(89);
            BytecodeHelper.pushConstant(mv, i);
            this.boxPrimitiveType(mv, idx, arg);
            idx += ProxyGeneratorAdapter.registerLen(arg);
            mv.visitInsn(83);
        }
        mv.visitVarInsn(58, arrayStore);
        int arrayIndex = arrayStore++;
        mv.visitVarInsn(25, 0);
        mv.visitFieldInsn(180, this.proxyName, CLOSURES_MAP_FIELD, "Ljava/util/Map;");
        mv.visitLdcInsn(name);
        mv.visitMethodInsn(185, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
        mv.visitVarInsn(58, arrayStore);
        Label notNull = new Label();
        mv.visitIntInsn(25, arrayStore);
        mv.visitJumpInsn(199, notNull);
        mv.visitVarInsn(25, 0);
        mv.visitFieldInsn(180, this.proxyName, CLOSURES_MAP_FIELD, "Ljava/util/Map;");
        mv.visitLdcInsn("*");
        mv.visitMethodInsn(185, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
        mv.visitVarInsn(58, arrayStore);
        mv.visitLabel(notNull);
        mv.visitVarInsn(25, arrayStore);
        mv.visitMethodInsn(184, BytecodeHelper.getClassInternalName(this.getClass()), "ensureClosure", "(Ljava/lang/Object;)Lgroovy/lang/Closure;", false);
        mv.visitVarInsn(25, arrayIndex);
        mv.visitMethodInsn(182, "groovy/lang/Closure", "call", "([Ljava/lang/Object;)Ljava/lang/Object;", false);
        ProxyGeneratorAdapter.unwrapResult(mv, desc);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        return null;
    }

    private void boxPrimitiveType(MethodVisitor mv, int idx, Type arg) {
        if (ProxyGeneratorAdapter.isPrimitive(arg)) {
            mv.visitIntInsn(ProxyGeneratorAdapter.getLoadInsn(arg), idx);
            String wrappedType = ProxyGeneratorAdapter.getWrappedClassDescriptor(arg);
            mv.visitMethodInsn(184, wrappedType, "valueOf", "(" + arg.getDescriptor() + ")L" + wrappedType + ";", false);
        } else {
            mv.visitVarInsn(25, idx);
        }
    }

    private static void unwrapResult(MethodVisitor mv, String desc) {
        Type returnType = Type.getReturnType(desc);
        if (returnType == Type.VOID_TYPE) {
            mv.visitInsn(87);
            mv.visitInsn(177);
        } else {
            if (ProxyGeneratorAdapter.isPrimitive(returnType)) {
                BytecodeHelper.unbox(mv, ClassHelper.make(returnType.getClassName()));
            } else {
                mv.visitTypeInsn(192, returnType.getInternalName());
            }
            mv.visitInsn(ProxyGeneratorAdapter.getReturnInsn(returnType));
        }
    }

    public GroovyObject proxy(Map<Object, Object> map, Object ... constructorArgs) {
        if (constructorArgs == null && this.cachedNoArgConstructor != null) {
            try {
                return (GroovyObject)this.cachedNoArgConstructor.newInstance(map);
            }
            catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new GroovyRuntimeException(e);
            }
        }
        if (constructorArgs == null) {
            constructorArgs = EMPTY_ARGS;
        }
        Object[] values = new Object[constructorArgs.length + 1];
        System.arraycopy(constructorArgs, 0, values, 0, constructorArgs.length);
        values[values.length - 1] = map;
        return (GroovyObject)DefaultGroovyMethods.newInstance(this.cachedClass, values);
    }

    public GroovyObject delegatingProxy(Object delegate, Map<Object, Object> map, Object ... constructorArgs) {
        if (constructorArgs == null && this.cachedNoArgConstructor != null) {
            try {
                return (GroovyObject)this.cachedNoArgConstructor.newInstance(map, delegate);
            }
            catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new GroovyRuntimeException(e);
            }
        }
        if (constructorArgs == null) {
            constructorArgs = EMPTY_ARGS;
        }
        Object[] values = new Object[constructorArgs.length + 2];
        System.arraycopy(constructorArgs, 0, values, 0, constructorArgs.length);
        values[values.length - 2] = map;
        values[values.length - 1] = delegate;
        return (GroovyObject)DefaultGroovyMethods.newInstance(this.cachedClass, values);
    }

    public static Closure ensureClosure(Object o) {
        if (o == null) {
            throw new UnsupportedOperationException();
        }
        if (o instanceof Closure) {
            return (Closure)o;
        }
        return new ReturnValueWrappingClosure<Object>(o);
    }

    private static int getLoadInsn(Type type) {
        return TypeUtil.getLoadInsnByType(type);
    }

    private static int getReturnInsn(Type type) {
        return TypeUtil.getReturnInsnByType(type);
    }

    private static boolean isPrimitive(Type type) {
        return TypeUtil.isPrimitiveType(type);
    }

    private static String getWrappedClassDescriptor(Type type) {
        return TypeUtil.getWrappedClassDescriptor(type);
    }

    static {
        HashSet<String> names = new HashSet<String>();
        for (Method method : GroovyObject.class.getMethods()) {
            names.add(method.getName());
        }
        GROOVYOBJECT_METHOD_NAMES = Collections.unmodifiableSet(names);
    }

    private static class InnerLoader
    extends GroovyClassLoader {
        List<ClassLoader> internalClassLoaders = null;

        protected InnerLoader(ClassLoader parent, Class[] interfaces) {
            super(parent);
            if (interfaces != null) {
                for (Class c : interfaces) {
                    if (c.getClassLoader() == parent) continue;
                    if (this.internalClassLoaders == null) {
                        this.internalClassLoaders = new ArrayList<ClassLoader>(interfaces.length);
                    }
                    if (this.internalClassLoaders.contains(c.getClassLoader())) continue;
                    this.internalClassLoaders.add(c.getClassLoader());
                }
            }
        }

        @Override
        public Class defineClass(String name, byte[] data) {
            return super.defineClass(name, data, 0, data.length);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            Class<?> loadedClass = this.findLoadedClass(name);
            if (loadedClass != null) {
                return loadedClass;
            }
            try {
                loadedClass = this.findClass(name);
            }
            catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
            if (loadedClass != null) {
                return loadedClass;
            }
            ClassNotFoundException ex = null;
            try {
                loadedClass = super.loadClass(name);
            }
            catch (ClassNotFoundException e) {
                ex = e;
            }
            if (loadedClass != null) {
                return loadedClass;
            }
            if (this.internalClassLoaders != null) {
                for (ClassLoader i : this.internalClassLoaders) {
                    try {
                        loadedClass = i.loadClass(name);
                        if (loadedClass == null) continue;
                        return loadedClass;
                    }
                    catch (ClassNotFoundException classNotFoundException) {
                    }
                }
            }
            if (ex != null) {
                throw ex;
            }
            throw new ClassNotFoundException(name);
        }
    }

    private static class ReturnValueWrappingClosure<V>
    extends Closure<V> {
        private static final long serialVersionUID = 1313135457715304501L;
        private final V value;

        public ReturnValueWrappingClosure(V returnValue) {
            super(null);
            this.value = returnValue;
        }

        @Override
        public V call(Object ... args) {
            return this.value;
        }
    }
}

