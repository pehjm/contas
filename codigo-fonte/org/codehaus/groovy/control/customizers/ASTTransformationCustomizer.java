/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.control.customizers;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.transform.CompilationUnitAware;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.beans.Transient;
import java.lang.annotation.Annotation;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.customizers.CompilationCustomizer;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformationClass;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class ASTTransformationCustomizer
extends CompilationCustomizer
implements CompilationUnitAware,
GroovyObject {
    private boolean applied;
    protected CompilationUnit compilationUnit;
    private final AnnotationNode annotationNode;
    private final ASTTransformation transformation;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    public ASTTransformationCustomizer(Class<? extends Annotation> transformationAnnotation, String astTransformationClassName, ClassLoader transformationClassLoader) {
        super(ASTTransformationCustomizer.findPhase(transformationAnnotation, astTransformationClassName, transformationClassLoader));
        AnnotationNode annotationNode;
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        Class<ASTTransformation> clazz = ASTTransformationCustomizer.findASTTransformationClass(transformationAnnotation, astTransformationClassName, transformationClassLoader);
        ASTTransformation aSTTransformation = DefaultGroovyMethods.newInstance(clazz);
        this.transformation = IndyInterface.bootstrap("cast", "()", 0, aSTTransformation);
        this.annotationNode = annotationNode = new AnnotationNode(ClassHelper.make(transformationAnnotation));
    }

    public ASTTransformationCustomizer(Class<? extends Annotation> transformationAnnotation, String astTransformationClassName) {
        this(transformationAnnotation, astTransformationClassName, transformationAnnotation.getClassLoader());
    }

    public ASTTransformationCustomizer(Map annotationParams, Class<? extends Annotation> transformationAnnotation, String astTransformationClassName, ClassLoader transformationClassLoader) {
        super(ASTTransformationCustomizer.findPhase(transformationAnnotation, astTransformationClassName, transformationClassLoader));
        AnnotationNode annotationNode;
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        Class<ASTTransformation> clazz = ASTTransformationCustomizer.findASTTransformationClass(transformationAnnotation, astTransformationClassName, transformationClassLoader);
        ASTTransformation aSTTransformation = DefaultGroovyMethods.newInstance(clazz);
        this.transformation = IndyInterface.bootstrap("cast", "()", 0, aSTTransformation);
        this.annotationNode = annotationNode = new AnnotationNode(ClassHelper.make(transformationAnnotation));
        Map map = annotationParams;
        this.setAnnotationParameters(map);
    }

    public ASTTransformationCustomizer(Map annotationParams, Class<? extends Annotation> transformationAnnotation, String astTransformationClassName) {
        this(annotationParams, transformationAnnotation, transformationAnnotation.getClassLoader());
    }

    public ASTTransformationCustomizer(Class<? extends Annotation> transformationAnnotation, ClassLoader transformationClassLoader) {
        super(ASTTransformationCustomizer.findPhase(transformationAnnotation, transformationClassLoader));
        AnnotationNode annotationNode;
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        Class<ASTTransformation> clazz = ASTTransformationCustomizer.findASTTransformationClass(transformationAnnotation, transformationClassLoader);
        ASTTransformation aSTTransformation = DefaultGroovyMethods.newInstance(clazz);
        this.transformation = IndyInterface.bootstrap("cast", "()", 0, aSTTransformation);
        this.annotationNode = annotationNode = new AnnotationNode(ClassHelper.make(transformationAnnotation));
    }

    public ASTTransformationCustomizer(Class<? extends Annotation> transformationAnnotation) {
        this(transformationAnnotation, transformationAnnotation.getClassLoader());
    }

    public ASTTransformationCustomizer(ASTTransformation transformation) {
        super(ASTTransformationCustomizer.findPhase(transformation));
        ASTTransformation aSTTransformation;
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        this.transformation = aSTTransformation = transformation;
        Object var4_4 = null;
        this.annotationNode = IndyInterface.bootstrap("cast", "()", 0, var4_4);
    }

    public ASTTransformationCustomizer(Map annotationParams, Class<? extends Annotation> transformationAnnotation, ClassLoader transformationClassLoader) {
        super(ASTTransformationCustomizer.findPhase(transformationAnnotation, transformationClassLoader));
        AnnotationNode annotationNode;
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        Class<ASTTransformation> clazz = ASTTransformationCustomizer.findASTTransformationClass(transformationAnnotation, transformationClassLoader);
        ASTTransformation aSTTransformation = DefaultGroovyMethods.newInstance(clazz);
        this.transformation = IndyInterface.bootstrap("cast", "()", 0, aSTTransformation);
        this.annotationNode = annotationNode = new AnnotationNode(ClassHelper.make(transformationAnnotation));
        Map map = annotationParams;
        this.setAnnotationParameters(map);
    }

    public ASTTransformationCustomizer(Map annotationParams, Class<? extends Annotation> transformationAnnotation) {
        this(annotationParams, transformationAnnotation, transformationAnnotation.getClassLoader());
    }

    public ASTTransformationCustomizer(Map annotationParams, ASTTransformation transformation) {
        this(transformation);
        Map map = annotationParams;
        this.setAnnotationParameters(map);
    }

    @Override
    public void setCompilationUnit(CompilationUnit unit) {
        CompilationUnit compilationUnit;
        this.compilationUnit = compilationUnit = unit;
    }

    private static Class<ASTTransformation> findASTTransformationClass(Class<? extends Annotation> anAnnotationClass, ClassLoader transformationClassLoader) {
        ClassLoader classLoader;
        CallSite annotation = IndyInterface.bootstrap("cast", "()", 0, anAnnotationClass.getAnnotation(GroovyASTTransformationClass.class));
        if (annotation == null) {
            throw (Throwable)new IllegalArgumentException("Provided class doesn't look like an AST @interface");
        }
        Object[] classes = annotation.classes();
        Object[] classesAsStrings = annotation.value();
        if (classes.length + classesAsStrings.length > 1) {
            throw (Throwable)new IllegalArgumentException("AST transformation customizer doesn't support AST transforms with multiple classes");
        }
        return (classes == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, classes)) != false ? IndyInterface.bootstrap("cast", "()", 0, BytecodeInterface8.objectArrayGet(classes, 0)) : Class.forName((String)((Object)IndyInterface.bootstrap("cast", "()", 0, BytecodeInterface8.objectArrayGet(classesAsStrings, 0))), true, IndyInterface.bootstrap("cast", "()", 0, classLoader = transformationClassLoader) != false ? classLoader : anAnnotationClass.getClassLoader());
    }

    private static Class<ASTTransformation> findASTTransformationClass(Class<? extends Annotation> anAnnotationClass, String astTransformationClassName, ClassLoader transformationClassLoader) {
        ClassLoader classLoader = transformationClassLoader;
        return Class.forName(astTransformationClassName, true, IndyInterface.bootstrap("cast", "()", 0, classLoader) != false ? classLoader : anAnnotationClass.getClassLoader());
    }

    private static CompilePhase findPhase(ASTTransformation transformation) {
        if (transformation == null) {
            throw (Throwable)new IllegalArgumentException("Provided transformation must not be null");
        }
        Class<?> clazz = transformation.getClass();
        CallSite annotation = IndyInterface.bootstrap("cast", "()", 0, clazz.getAnnotation(GroovyASTTransformation.class));
        if (annotation == null) {
            throw (Throwable)new IllegalArgumentException((String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{GroovyASTTransformation.class.getName()}, new String[]{"Provided ast transformation is not annotated with ", ""}))));
        }
        return annotation.phase();
    }

    private static CompilePhase findPhase(Class<? extends Annotation> annotationClass, ClassLoader transformationClassLoader) {
        Class<ASTTransformation> clazz = ASTTransformationCustomizer.findASTTransformationClass(annotationClass, transformationClassLoader);
        return ASTTransformationCustomizer.findPhase((ASTTransformation)((Object)IndyInterface.bootstrap("cast", "()", 0, DefaultGroovyMethods.newInstance(clazz))));
    }

    private static CompilePhase findPhase(Class<? extends Annotation> annotationClass, String astTransformationClassName, ClassLoader transformationClassLoader) {
        Class<ASTTransformation> clazz = ASTTransformationCustomizer.findASTTransformationClass(annotationClass, astTransformationClassName, transformationClassLoader);
        return ASTTransformationCustomizer.findPhase((ASTTransformation)((Object)IndyInterface.bootstrap("cast", "()", 0, DefaultGroovyMethods.newInstance(clazz))));
    }

    public void setAnnotationParameters(Map<String, Object> params) {
        if (ScriptBytecodeAdapter.compareEqual(this.annotationNode, null) || ScriptBytecodeAdapter.compareEqual(params, null) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isEmpty", 0, params))) {
            return;
        }
        public final class _setAnnotationParameters_closure1
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _setAnnotationParameters_closure1(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object name, Object value) {
                if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getMethod", 0, IndyInterface.bootstrap("getProperty", "classNode", 0, IndyInterface.bootstrap("getProperty", "annotationNode", 12, this)), name)) == false) {
                    throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "name", 0, IndyInterface.bootstrap("getProperty", "classNode", 0, IndyInterface.bootstrap("getProperty", "annotationNode", 12, this))), name}, new String[]{"", " does not accept any [", "] parameter"})));
                }
                if (value instanceof Closure) {
                    throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class, "Direct usage of closure is not supported by the AST compilation customizer. Please use ClosureExpression instead."));
                }
                CallSite valueExpression = null;
                if (value instanceof Expression) {
                    valueExpression = IndyInterface.bootstrap("cast", "()", 0, value);
                    int n = 0;
                    ScriptBytecodeAdapter.setProperty(n, null, value, "lineNumber");
                    int n2 = 0;
                    ScriptBytecodeAdapter.setProperty(n2, null, value, "lastLineNumber");
                } else {
                    public final class _closure2
                    extends Closure
                    implements GeneratedClosure {
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;

                        public _closure2(Object _outerInstance, Object _thisObject) {
                            super(_outerInstance, _thisObject);
                        }

                        public Object doCall(Object it) {
                            return it instanceof Class ? IndyInterface.bootstrap("invoke", "classX", 0, GeneralUtils.class, it) : IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, it);
                        }

                        @Generated
                        public Object doCall() {
                            return this.doCall(null);
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _closure2.class) {
                                return ScriptBytecodeAdapter.initMetaClass(this);
                            }
                            ClassInfo classInfo = $staticClassInfo;
                            if (classInfo == null) {
                                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                            }
                            return classInfo.getMetaClass();
                        }

                        public /* synthetic */ MethodHandles.Lookup $getLookup() {
                            return MethodHandles.lookup();
                        }
                    }
                    valueExpression = value instanceof Class ? IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "classX", 0, GeneralUtils.class, value)) : (value instanceof Enum ? IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "propX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "classX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("invoke", "getClass", 0, value))), IndyInterface.bootstrap("invoke", "toString", 0, value))) : (value instanceof List || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isArray", 0, IndyInterface.bootstrap("invoke", "getClass", 0, value))) ? IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "listX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "collect", 0, value, new _closure2(this, this.getThisObject())))) : IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, value))));
                }
                return IndyInterface.bootstrap("invoke", "addMember", 0, IndyInterface.bootstrap("getProperty", "annotationNode", 12, this), name, valueExpression);
            }

            @Generated
            public Object call(Object name, Object value) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, name, value);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _setAnnotationParameters_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public /* synthetic */ MethodHandles.Lookup $getLookup() {
                return MethodHandles.lookup();
            }
        }
        IndyInterface.bootstrap("invoke", "each", 0, params, new _setAnnotationParameters_closure1(this, this));
    }

    @Override
    public void call(SourceUnit source, GeneratorContext context, ClassNode classNode) {
        boolean bl;
        if (this.transformation instanceof CompilationUnitAware) {
            CompilationUnit compilationUnit = this.compilationUnit;
            IndyInterface.bootstrap("cast", "()", 0, this.transformation).setCompilationUnit(compilationUnit);
        }
        if (this.annotationNode != null) {
            ClassNode classNode2 = classNode;
            this.annotationNode.setSourcePosition(classNode2);
            this.transformation.visit(new ASTNode[]{this.annotationNode, classNode}, source);
        } else if (!this.applied) {
            this.transformation.visit(null, source);
        }
        this.applied = bl = true;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ASTTransformationCustomizer.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    @Override
    @Generated
    @Internal
    @Transient
    public MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    @Override
    @Generated
    @Internal
    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public static /* synthetic */ MethodHandles.Lookup $getLookup() {
        return MethodHandles.lookup();
    }

    @Generated
    public final ASTTransformation getTransformation() {
        return this.transformation;
    }
}

