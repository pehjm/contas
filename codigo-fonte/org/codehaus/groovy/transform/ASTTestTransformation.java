/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.transform;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.MetaClass;
import groovy.lang.MissingMethodException;
import groovy.lang.MissingPropertyException;
import groovy.lang.Reference;
import groovy.transform.CompilationUnitAware;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.beans.Transient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.Janitor;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.MethodClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
public class ASTTestTransformation
implements ASTTransformation,
CompilationUnitAware,
GroovyObject {
    private CompilationUnit compilationUnit;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public ASTTestTransformation() {
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        CallSite annotationNode = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, nodes, false));
        CallSite member = IndyInterface.bootstrap("invoke", "getMember", 0, annotationNode, "phase");
        CallSite phase = null;
        if (IndyInterface.bootstrap("cast", "()", 0, member) != false) {
            if (member instanceof VariableExpression) {
                phase = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "valueOf", 0, CompilePhase.class, IndyInterface.bootstrap("getProperty", "text", 0, member)));
            } else if (member instanceof PropertyExpression) {
                phase = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "valueOf", 0, CompilePhase.class, IndyInterface.bootstrap("getProperty", "propertyAsString", 0, member)));
            }
            IndyInterface.bootstrap("invoke", "setMember", 0, annotationNode, "phase", IndyInterface.bootstrap("invoke", "propX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "classX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, CompilePhase.class)), IndyInterface.bootstrap("invoke", "toString", 0, phase)));
            if (ScriptBytecodeAdapter.compareLessThan(IndyInterface.bootstrap("getProperty", "phaseNumber", 0, phase), IndyInterface.bootstrap("getProperty", "phase", 0, this.compilationUnit))) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, SyntaxException.class, IndyInterface.bootstrap("invoke", "plus", 0, "ASTTest phase must be at least ", IndyInterface.bootstrap("invoke", "fromPhaseNumber", 0, CompilePhase.class, IndyInterface.bootstrap("getProperty", "phase", 0, this.compilationUnit))), member));
            }
        }
        member = IndyInterface.bootstrap("invoke", "getMember", 0, annotationNode, "value");
        if (DefaultTypeTransformation.booleanUnbox(member) && !(member instanceof ClosureExpression)) {
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, SyntaxException.class, "ASTTest value must be a closure", member));
        }
        if (IndyInterface.bootstrap("cast", "()", 0, member) == false && IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getNodeMetaData", 0, annotationNode, ASTTestTransformation.class)) == false) {
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, SyntaxException.class, "Missing test expression", annotationNode));
        }
        IndyInterface.bootstrap("invoke", "setNodeMetaData", 0, annotationNode, ASTTestTransformation.class, member);
        IndyInterface.bootstrap("invoke", "setMember", 0, annotationNode, "value", IndyInterface.bootstrap("init", "<init>", 0, ClosureExpression.class, IndyInterface.bootstrap("getProperty", "EMPTY_ARRAY", 0, Parameter.class), IndyInterface.bootstrap("getProperty", "INSTANCE", 0, EmptyStatement.class)));
        Object var6_6 = null;
        ScriptBytecodeAdapter.setField(var6_6, ASTTestTransformation.class, IndyInterface.bootstrap("getProperty", "variableScope", 0, member), "parent");
        CallSite astTester = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, ASTTester.class, this, ScriptBytecodeAdapter.createMap(new Object[]{"astNode", IndyInterface.bootstrap("invoke", "getAt", 0, nodes, true), "sourceUnit", source, "testClosure", IndyInterface.bootstrap("invoke", "getNodeMetaData", 0, annotationNode, ASTTestTransformation.class)})));
        CallSite callSite = phase;
        int p = DefaultTypeTransformation.intUnbox(IndyInterface.bootstrap("getProperty", "phaseNumber", 0, IndyInterface.bootstrap("cast", "()", 0, callSite) != false ? callSite : IndyInterface.bootstrap("getProperty", "SEMANTIC_ANALYSIS", 0, CompilePhase.class)));
        CallSite callSite2 = phase;
        int q = DefaultTypeTransformation.intUnbox(IndyInterface.bootstrap("getProperty", "phaseNumber", 0, IndyInterface.bootstrap("cast", "()", 0, callSite2) != false ? callSite2 : IndyInterface.bootstrap("getProperty", "FINALIZATION", 0, CompilePhase.class)));
        while (p <= q) {
            IndyInterface.bootstrap("invoke", "addNewPhaseOperation", 0, this.compilationUnit, astTester, p);
            p = DefaultTypeTransformation.intUnbox(IndyInterface.bootstrap("invoke", "plus", 0, p, true));
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        if (!(args instanceof Object[])) {
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ASTTestTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), new Object[]{args});
        }
        if (ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) {
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ASTTestTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
        }
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ASTTestTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectProperty(object, ASTTestTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        return ScriptBytecodeAdapter.getGroovyObjectProperty(ASTTestTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ASTTestTransformation.class) {
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
    public CompilationUnit getCompilationUnit() {
        return this.compilationUnit;
    }

    @Override
    @Generated
    public void setCompilationUnit(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    private class ASTTester
    implements CompilationUnit.ISourceUnitOperation,
    GroovyObject {
        private ASTNode astNode;
        private SourceUnit sourceUnit;
        private ClosureExpression testClosure;
        private final Binding binding;
        final /* synthetic */ ASTTestTransformation this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        @Generated
        public ASTTester(ASTTestTransformation $p$) {
            MetaClass metaClass;
            ASTTestTransformation aSTTestTransformation;
            this.this$0 = aSTTestTransformation = $p$;
            CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, Binding.class, IndyInterface.bootstrap("invoke", "withDefault", 0, ScriptBytecodeAdapter.createMap(new Object[0]), new _closure1(this, this)));
            this.binding = IndyInterface.bootstrap("cast", "()", 0, callSite);
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        @Override
        public void call(SourceUnit source) {
            if (ScriptBytecodeAdapter.compareEqual(source, this.sourceUnit)) {
                IndyInterface.bootstrap("invoke", "test", 2, this);
            }
        }

        private void test() {
            CallSite sb = IndyInterface.bootstrap("init", "<init>", 0, StringBuilder.class);
            int i = DefaultTypeTransformation.intUnbox(IndyInterface.bootstrap("getProperty", "lineNumber", 0, this.testClosure));
            int n = DefaultTypeTransformation.intUnbox(IndyInterface.bootstrap("getProperty", "lastLineNumber", 0, this.testClosure));
            while (i <= n) {
                IndyInterface.bootstrap("invoke", "append", 0, IndyInterface.bootstrap("invoke", "append", 0, sb, IndyInterface.bootstrap("invoke", "getLine", 0, IndyInterface.bootstrap("getProperty", "source", 0, this.sourceUnit), i, IndyInterface.bootstrap("init", "<init>", 0, Janitor.class))), "\n");
                i = DefaultTypeTransformation.intUnbox(IndyInterface.bootstrap("invoke", "plus", 0, i, true));
            }
            sb = IndyInterface.bootstrap("invoke", "getAt", 0, sb, ScriptBytecodeAdapter.createRange(IndyInterface.bootstrap("getProperty", "columnNumber", 0, this.testClosure), IndyInterface.bootstrap("invoke", "length", 0, sb), false, true));
            CallSite testSource = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, sb, ScriptBytecodeAdapter.createRange(0, IndyInterface.bootstrap("invoke", "lastIndexOf", 0, sb, "}"), false, true)));
            ASTNode aSTNode = this.astNode;
            IndyInterface.bootstrap("invoke", "putAt", 0, this.binding, "node", aSTNode);
            SourceUnit sourceUnit = this.sourceUnit;
            IndyInterface.bootstrap("invoke", "putAt", 0, this.binding, "sourceUnit", sourceUnit);
            CallSite callSite = IndyInterface.bootstrap("getProperty", "compilationUnit", 12, this);
            IndyInterface.bootstrap("invoke", "putAt", 0, this.binding, "compilationUnit", callSite);
            CallSite callSite2 = IndyInterface.bootstrap("invoke", "fromPhaseNumber", 0, CompilePhase.class, IndyInterface.bootstrap("getProperty", "phase", 0, IndyInterface.bootstrap("getProperty", "compilationUnit", 12, this)));
            IndyInterface.bootstrap("invoke", "putAt", 0, this.binding, "compilePhase", callSite2);
            CallSite callSite3 = IndyInterface.bootstrap("invoke", "curry", 0, IndyInterface.bootstrap("init", "<init>", 0, MethodClosure.class, LabelFinder.class, "lookup"), this.astNode);
            IndyInterface.bootstrap("invoke", "putAt", 0, this.binding, "lookup", callSite3);
            Reference<CallSite> customizer = new Reference<CallSite>(IndyInterface.bootstrap("init", "<init>", 0, ImportCustomizer.class));
            public final class _test_closure2
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference customizer;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _test_closure2(Object _outerInstance, Object _thisObject, Reference customizer) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.customizer = reference = customizer;
                }

                public Object doCall(Object it) {
                    return IndyInterface.bootstrap("invoke", "addImport", 0, this.customizer.get(), IndyInterface.bootstrap("getProperty", "alias", 0, it), IndyInterface.bootstrap("getProperty", "name", 0, IndyInterface.bootstrap("getProperty", "type", 0, it)));
                }

                @Generated
                public Object getCustomizer() {
                    return this.customizer.get();
                }

                @Generated
                public Object doCall() {
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _test_closure2.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("getProperty", "imports", 0, IndyInterface.bootstrap("getProperty", "AST", 0, this.sourceUnit)), new _test_closure2(this, this, customizer));
            public final class _test_closure3
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference customizer;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _test_closure3(Object _outerInstance, Object _thisObject, Reference customizer) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.customizer = reference = customizer;
                }

                public Object doCall(Object it) {
                    return IndyInterface.bootstrap("invoke", "addStarImports", 0, this.customizer.get(), IndyInterface.bootstrap("getProperty", "packageName", 0, it));
                }

                @Generated
                public Object getCustomizer() {
                    return this.customizer.get();
                }

                @Generated
                public Object doCall() {
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _test_closure3.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("getProperty", "starImports", 0, IndyInterface.bootstrap("getProperty", "AST", 0, this.sourceUnit)), new _test_closure3(this, this, customizer));
            public final class _test_closure4
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference customizer;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _test_closure4(Object _outerInstance, Object _thisObject, Reference customizer) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.customizer = reference = customizer;
                }

                public Object doCall(Object it) {
                    return IndyInterface.bootstrap("invoke", "addStaticImport", 0, this.customizer.get(), IndyInterface.bootstrap("getProperty", "alias", 0, IndyInterface.bootstrap("getProperty", "value", 0, it)), IndyInterface.bootstrap("getProperty", "name", 0, IndyInterface.bootstrap("getProperty", "type", 0, IndyInterface.bootstrap("getProperty", "value", 0, it))), IndyInterface.bootstrap("getProperty", "fieldName", 0, IndyInterface.bootstrap("getProperty", "value", 0, it)));
                }

                @Generated
                public Object getCustomizer() {
                    return this.customizer.get();
                }

                @Generated
                public Object doCall() {
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _test_closure4.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("getProperty", "staticImports", 0, IndyInterface.bootstrap("getProperty", "AST", 0, this.sourceUnit)), new _test_closure4(this, this, customizer));
            public final class _test_closure5
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference customizer;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _test_closure5(Object _outerInstance, Object _thisObject, Reference customizer) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.customizer = reference = customizer;
                }

                public Object doCall(Object it) {
                    return IndyInterface.bootstrap("invoke", "addStaticStars", 0, this.customizer.get(), IndyInterface.bootstrap("getProperty", "className", 0, IndyInterface.bootstrap("getProperty", "value", 0, it)));
                }

                @Generated
                public Object getCustomizer() {
                    return this.customizer.get();
                }

                @Generated
                public Object doCall() {
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _test_closure5.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("getProperty", "staticStarImports", 0, IndyInterface.bootstrap("getProperty", "AST", 0, this.sourceUnit)), new _test_closure5(this, this, customizer));
            CallSite config = IndyInterface.bootstrap("init", "<init>", 0, CompilerConfiguration.class);
            IndyInterface.bootstrap("invoke", "addCompilationCustomizers", 0, config, customizer.get());
            CallSite loader = IndyInterface.bootstrap("getProperty", "transformLoader", 0, IndyInterface.bootstrap("getProperty", "compilationUnit", 12, this));
            IndyInterface.bootstrap("invoke", "evaluate", 0, IndyInterface.bootstrap("init", "<init>", 0, GroovyShell.class, loader, this.binding, config), testSource);
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            try {
                return this.this$0.this$dist$invoke$1(name, args);
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "arguments", 0, notFound)));
            }
        }

        /*
         * Unable to fully structure code
         */
        public static /* synthetic */ Object $static_methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(ASTTester.class, ASTTestTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(ASTTester.class, ASTTestTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(ASTTester.class, ASTTestTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), ASTTester.class, IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                this.this$0.this$dist$set$1(name, value);
                return;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, ASTTestTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), ASTTester.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            try {
                return this.this$0.this$dist$get$1(name);
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(ASTTester.class, ASTTestTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), ASTTester.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ASTTester.class) {
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

        public /* synthetic */ MethodHandles.Lookup $getLookup() {
            return MethodHandles.lookup();
        }

        @Generated
        public ASTNode getAstNode() {
            return this.astNode;
        }

        @Generated
        public void setAstNode(ASTNode aSTNode) {
            this.astNode = aSTNode;
        }

        @Generated
        public SourceUnit getSourceUnit() {
            return this.sourceUnit;
        }

        @Generated
        public void setSourceUnit(SourceUnit sourceUnit) {
            this.sourceUnit = sourceUnit;
        }

        @Generated
        public ClosureExpression getTestClosure() {
            return this.testClosure;
        }

        @Generated
        public void setTestClosure(ClosureExpression closureExpression) {
            this.testClosure = closureExpression;
        }

        public final class _closure1
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _closure1(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                return null;
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _closure1.class) {
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
    }

    public static class LabelFinder
    extends ClassCodeVisitorSupport
    implements GroovyObject {
        private final String label;
        private final SourceUnit unit;
        private final List<Statement> targets;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        public LabelFinder(String label, SourceUnit unit) {
            SourceUnit sourceUnit;
            String string;
            MetaClass metaClass;
            LinkedList linkedList;
            this.targets = linkedList = (LinkedList)((Object)IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[0]), LinkedList.class));
            this.metaClass = metaClass = this.$getStaticMetaClass();
            this.label = string = label;
            this.unit = sourceUnit = unit;
        }

        public static List<Statement> lookup(MethodNode node, String label) {
            CallSite finder = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, LabelFinder.class, label, null));
            IndyInterface.bootstrap("invoke", "visit", 0, IndyInterface.bootstrap("getProperty", "code", 0, node), finder);
            return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "targets", 4, finder));
        }

        public static List<Statement> lookup(ClassNode node, String label) {
            CallSite finder = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, LabelFinder.class, label, null));
            ScriptBytecodeAdapter.invokeMethodNSpreadSafe(LabelFinder.class, ScriptBytecodeAdapter.getPropertySpreadSafe(LabelFinder.class, IndyInterface.bootstrap("getProperty", "methods", 0, node), "code"), "visit", new Object[]{finder});
            ScriptBytecodeAdapter.invokeMethodNSpreadSafe(LabelFinder.class, ScriptBytecodeAdapter.getPropertySpreadSafe(LabelFinder.class, IndyInterface.bootstrap("getProperty", "declaredConstructors", 0, node), "code"), "visit", new Object[]{finder});
            return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "targets", 4, finder));
        }

        @Override
        protected SourceUnit getSourceUnit() {
            return this.unit;
        }

        @Override
        protected void visitStatement(Statement statement) {
            ScriptBytecodeAdapter.invokeMethodOnSuperN(LabelFinder.class, this, "visitStatement", new Object[]{statement});
            if (ScriptBytecodeAdapter.isCase(this.label, IndyInterface.bootstrap("getProperty", "statementLabels", 0, statement))) {
                IndyInterface.bootstrap("invoke", "leftShift", 0, this.targets, statement);
            }
        }

        public List<Statement> getTargets() {
            return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "unmodifiableList", 0, Collections.class, this.targets));
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(LabelFinder.class, ASTTestTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(LabelFinder.class, ASTTestTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(LabelFinder.class, ASTTestTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var5_5;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        /*
         * Unable to fully structure code
         */
        public static /* synthetic */ Object $static_methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(LabelFinder.class, ASTTestTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(LabelFinder.class, ASTTestTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(LabelFinder.class, ASTTestTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), LabelFinder.class, IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, ASTTestTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, ASTTestTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), LabelFinder.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(LabelFinder.class, ASTTestTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(LabelFinder.class, ASTTestTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), LabelFinder.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != LabelFinder.class) {
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

        public /* synthetic */ void super$3$visitStatement(Statement statement) {
            super.visitStatement(statement);
        }
    }
}

