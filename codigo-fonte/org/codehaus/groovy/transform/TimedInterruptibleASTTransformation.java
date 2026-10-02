/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.transform;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.MissingMethodException;
import groovy.lang.MissingPropertyException;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovy.transform.TimedInterrupt;
import groovyjarjarasm.asm.Opcodes;
import java.beans.Transient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.DoWhileStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.LoopingStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.StringGroovyMethods;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.AbstractInterruptibleASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

@GroovyASTTransformation(phase=CompilePhase.CANONICALIZATION)
public class TimedInterruptibleASTTransformation
extends AbstractASTTransformation
implements GroovyObject {
    private static final ClassNode MY_TYPE;
    private static final String CHECK_METHOD_START_MEMBER = "checkOnMethodStart";
    private static final String APPLY_TO_ALL_CLASSES = "applyToAllClasses";
    private static final String APPLY_TO_ALL_MEMBERS = "applyToAllMembers";
    private static final String THROWN_EXCEPTION_TYPE = "thrown";
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public TimedInterruptibleASTTransformation() {
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        Reference<SourceUnit> source2 = new Reference<SourceUnit>(source);
        this.init(nodes, source2.get());
        Reference<CallSite> node = new Reference<CallSite>(IndyInterface.bootstrap("cast", "()", 0, BytecodeInterface8.objectArrayGet(nodes, 0)));
        CallSite annotatedNode = IndyInterface.bootstrap("cast", "()", 0, BytecodeInterface8.objectArrayGet(nodes, 1));
        if (!MY_TYPE.equals(((AnnotationNode)((Object)node.get())).getClassNode())) {
            TimedInterruptibleASTTransformation.internalError((String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{((AnnotationNode)((Object)node.get())).getClassNode().getName()}, new String[]{"Transformation called from wrong annotation: ", ""}))));
        }
        Reference<Object> checkOnMethodStart = new Reference<Object>(TimedInterruptibleASTTransformation.getConstantAnnotationParameter((AnnotationNode)((Object)node.get()), CHECK_METHOD_START_MEMBER, Boolean.TYPE, true));
        Reference<Object> applyToAllMembers = new Reference<Object>(TimedInterruptibleASTTransformation.getConstantAnnotationParameter((AnnotationNode)((Object)node.get()), APPLY_TO_ALL_MEMBERS, Boolean.TYPE, true));
        Object object = applyToAllMembers.get();
        Reference<Boolean> applyToAllClasses = new Reference<Boolean>((Boolean)((object == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, object)) != false ? TimedInterruptibleASTTransformation.getConstantAnnotationParameter((AnnotationNode)((Object)node.get()), APPLY_TO_ALL_CLASSES, Boolean.TYPE, true) : Boolean.valueOf(false)));
        Reference<Object> maximum = new Reference<Object>(TimedInterruptibleASTTransformation.getConstantAnnotationParameter((AnnotationNode)((Object)node.get()), "value", Long.TYPE, Long.MAX_VALUE));
        Reference<ClassNode> thrown = new Reference<ClassNode>(AbstractInterruptibleASTTransformation.getClassAnnotationParameter((AnnotationNode)((Object)node.get()), THROWN_EXCEPTION_TYPE, ClassHelper.make(TimeoutException.class)));
        Expression expression = ((AnnotationNode)((Object)node.get())).getMember("unit");
        Reference<Expression> unit = new Reference<Expression>(IndyInterface.bootstrap("cast", "()", 0, expression) != false ? expression : GeneralUtils.propX((Expression)GeneralUtils.classX(TimeUnit.class), "SECONDS"));
        Boolean bl = applyToAllClasses.get();
        if ((bl == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, bl)) != false) {
            ModuleNode moduleNode = source2.get().getAST();
            List<ClassNode> list = moduleNode != null ? moduleNode.getClasses() : null;
            public final class _visit_closure1
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference source;
                private /* synthetic */ Reference checkOnMethodStart;
                private /* synthetic */ Reference applyToAllClasses;
                private /* synthetic */ Reference applyToAllMembers;
                private /* synthetic */ Reference maximum;
                private /* synthetic */ Reference unit;
                private /* synthetic */ Reference thrown;
                private /* synthetic */ Reference node;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _visit_closure1(Object _outerInstance, Object _thisObject, Reference source, Reference checkOnMethodStart, Reference applyToAllClasses, Reference applyToAllMembers, Reference maximum, Reference unit, Reference thrown, Reference node) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    Reference reference4;
                    Reference reference5;
                    Reference reference6;
                    Reference reference7;
                    Reference reference8;
                    this.source = reference8 = source;
                    this.checkOnMethodStart = reference7 = checkOnMethodStart;
                    this.applyToAllClasses = reference6 = applyToAllClasses;
                    this.applyToAllMembers = reference5 = applyToAllMembers;
                    this.maximum = reference4 = maximum;
                    this.unit = reference3 = unit;
                    this.thrown = reference2 = thrown;
                    this.node = reference = node;
                }

                public Object doCall(ClassNode it) {
                    TimedInterruptionVisitor visitor = new TimedInterruptionVisitor((SourceUnit)((Object)IndyInterface.bootstrap("cast", "()", 0, this.source.get())), this.checkOnMethodStart.get(), this.applyToAllClasses.get(), this.applyToAllMembers.get(), this.maximum.get(), (Expression)((Object)IndyInterface.bootstrap("cast", "()", 0, this.unit.get())), (ClassNode)((Object)IndyInterface.bootstrap("cast", "()", 0, this.thrown.get())), IndyInterface.bootstrap("cast", "()", 0, this.node.get()).hashCode());
                    visitor.visitClass(it);
                    return null;
                }

                @Generated
                public Object call(ClassNode it) {
                    return this.doCall(it);
                }

                @Generated
                public SourceUnit getSource() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.source.get());
                }

                @Generated
                public Object getCheckOnMethodStart() {
                    return this.checkOnMethodStart.get();
                }

                @Generated
                public Object getApplyToAllClasses() {
                    return this.applyToAllClasses.get();
                }

                @Generated
                public Object getApplyToAllMembers() {
                    return this.applyToAllMembers.get();
                }

                @Generated
                public Object getMaximum() {
                    return this.maximum.get();
                }

                @Generated
                public Expression getUnit() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.unit.get());
                }

                @Generated
                public ClassNode getThrown() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.thrown.get());
                }

                @Generated
                public AnnotationNode getNode() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.node.get());
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _visit_closure1.class) {
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
            List<ClassNode> list2 = list != null ? DefaultGroovyMethods.each(list, (Closure)new _visit_closure1(this, this, source2, checkOnMethodStart, applyToAllClasses, applyToAllMembers, maximum, unit, thrown, node)) : null;
        } else if (annotatedNode instanceof ClassNode) {
            TimedInterruptionVisitor visitor = new TimedInterruptionVisitor(source2.get(), checkOnMethodStart.get(), applyToAllClasses.get(), applyToAllMembers.get(), maximum.get(), unit.get(), thrown.get(), ((AnnotationNode)((Object)node.get())).hashCode());
            visitor.visitClass((ClassNode)((Object)IndyInterface.bootstrap("cast", "()", 0, annotatedNode)));
        } else {
            Object object2 = applyToAllMembers.get();
            if ((object2 == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, object2)) == false && annotatedNode instanceof MethodNode) {
                TimedInterruptionVisitor visitor = new TimedInterruptionVisitor(source2.get(), checkOnMethodStart.get(), applyToAllClasses.get(), applyToAllMembers.get(), maximum.get(), unit.get(), thrown.get(), ((AnnotationNode)((Object)node.get())).hashCode());
                visitor.visitMethod((MethodNode)((Object)IndyInterface.bootstrap("cast", "()", 0, annotatedNode)));
                visitor.visitClass(((AnnotatedNode)((Object)annotatedNode)).getDeclaringClass());
            } else {
                Object object3 = applyToAllMembers.get();
                if ((object3 == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, object3)) == false && annotatedNode instanceof FieldNode) {
                    TimedInterruptionVisitor visitor = new TimedInterruptionVisitor(source2.get(), checkOnMethodStart.get(), applyToAllClasses.get(), applyToAllMembers.get(), maximum.get(), unit.get(), thrown.get(), ((AnnotationNode)((Object)node.get())).hashCode());
                    visitor.visitField((FieldNode)((Object)IndyInterface.bootstrap("cast", "()", 0, annotatedNode)));
                    visitor.visitClass(((AnnotatedNode)((Object)annotatedNode)).getDeclaringClass());
                } else {
                    Object object4 = applyToAllMembers.get();
                    if ((object4 == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, object4)) == false && annotatedNode instanceof DeclarationExpression) {
                        TimedInterruptionVisitor visitor = new TimedInterruptionVisitor(source2.get(), checkOnMethodStart.get(), applyToAllClasses.get(), applyToAllMembers.get(), maximum.get(), unit.get(), thrown.get(), ((AnnotationNode)((Object)node.get())).hashCode());
                        visitor.visitDeclarationExpression((DeclarationExpression)((Object)IndyInterface.bootstrap("cast", "()", 0, annotatedNode)));
                        visitor.visitClass(((AnnotatedNode)((Object)annotatedNode)).getDeclaringClass());
                    } else {
                        ModuleNode moduleNode = source2.get().getAST();
                        List<ClassNode> list = moduleNode != null ? moduleNode.getClasses() : null;
                        public final class _visit_closure2
                        extends Closure
                        implements GeneratedClosure {
                            private /* synthetic */ Reference source;
                            private /* synthetic */ Reference checkOnMethodStart;
                            private /* synthetic */ Reference applyToAllClasses;
                            private /* synthetic */ Reference applyToAllMembers;
                            private /* synthetic */ Reference maximum;
                            private /* synthetic */ Reference unit;
                            private /* synthetic */ Reference thrown;
                            private /* synthetic */ Reference node;
                            private static /* synthetic */ ClassInfo $staticClassInfo;
                            public static transient /* synthetic */ boolean __$stMC;

                            public _visit_closure2(Object _outerInstance, Object _thisObject, Reference source, Reference checkOnMethodStart, Reference applyToAllClasses, Reference applyToAllMembers, Reference maximum, Reference unit, Reference thrown, Reference node) {
                                super(_outerInstance, _thisObject);
                                Reference reference;
                                Reference reference2;
                                Reference reference3;
                                Reference reference4;
                                Reference reference5;
                                Reference reference6;
                                Reference reference7;
                                Reference reference8;
                                this.source = reference8 = source;
                                this.checkOnMethodStart = reference7 = checkOnMethodStart;
                                this.applyToAllClasses = reference6 = applyToAllClasses;
                                this.applyToAllMembers = reference5 = applyToAllMembers;
                                this.maximum = reference4 = maximum;
                                this.unit = reference3 = unit;
                                this.thrown = reference2 = thrown;
                                this.node = reference = node;
                            }

                            public Object doCall(ClassNode it) {
                                if (it.isScript()) {
                                    TimedInterruptionVisitor visitor = new TimedInterruptionVisitor((SourceUnit)((Object)IndyInterface.bootstrap("cast", "()", 0, this.source.get())), this.checkOnMethodStart.get(), this.applyToAllClasses.get(), this.applyToAllMembers.get(), this.maximum.get(), (Expression)((Object)IndyInterface.bootstrap("cast", "()", 0, this.unit.get())), (ClassNode)((Object)IndyInterface.bootstrap("cast", "()", 0, this.thrown.get())), IndyInterface.bootstrap("cast", "()", 0, this.node.get()).hashCode());
                                    visitor.visitClass(it);
                                    return null;
                                }
                                return null;
                            }

                            @Generated
                            public Object call(ClassNode it) {
                                return this.doCall(it);
                            }

                            @Generated
                            public SourceUnit getSource() {
                                return IndyInterface.bootstrap("cast", "()", 0, this.source.get());
                            }

                            @Generated
                            public Object getCheckOnMethodStart() {
                                return this.checkOnMethodStart.get();
                            }

                            @Generated
                            public Object getApplyToAllClasses() {
                                return this.applyToAllClasses.get();
                            }

                            @Generated
                            public Object getApplyToAllMembers() {
                                return this.applyToAllMembers.get();
                            }

                            @Generated
                            public Object getMaximum() {
                                return this.maximum.get();
                            }

                            @Generated
                            public Expression getUnit() {
                                return IndyInterface.bootstrap("cast", "()", 0, this.unit.get());
                            }

                            @Generated
                            public ClassNode getThrown() {
                                return IndyInterface.bootstrap("cast", "()", 0, this.thrown.get());
                            }

                            @Generated
                            public AnnotationNode getNode() {
                                return IndyInterface.bootstrap("cast", "()", 0, this.node.get());
                            }

                            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                if (this.getClass() != _visit_closure2.class) {
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
                        List<ClassNode> list3 = list != null ? DefaultGroovyMethods.each(list, (Closure)new _visit_closure2(this, this, source2, checkOnMethodStart, applyToAllClasses, applyToAllMembers, maximum, unit, thrown, node)) : null;
                    }
                }
            }
        }
    }

    public static Object getConstantAnnotationParameter(AnnotationNode node, String parameterName, Class type, Object defaultValue) {
        Expression member = node.getMember(parameterName);
        Expression expression = member;
        if ((expression == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, expression)) != false) {
            if (member instanceof ConstantExpression) {
                Object t = DefaultGroovyMethods.asType(((ConstantExpression)((Object)IndyInterface.bootstrap("cast", "()", 0, member))).getValue(), type);
                try {
                    return t;
                }
                catch (Exception ignore) {
                    TimedInterruptibleASTTransformation.internalError((String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{parameterName, member}, new String[]{"Expecting boolean value for ", " annotation parameter. Found ", ""}))));
                }
            } else {
                TimedInterruptibleASTTransformation.internalError((String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{parameterName, member}, new String[]{"Expecting boolean value for ", " annotation parameter. Found ", ""}))));
            }
        }
        return defaultValue;
    }

    private static void internalError(String message) {
        throw (Throwable)new RuntimeException((String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{message}, new String[]{"Internal error: ", ""}))));
    }

    public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
        if (!(args instanceof Object[])) {
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(TimedInterruptibleASTTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), new Object[]{args});
        }
        if (((CallSite)IndyInterface.bootstrap("cast", "()", 0, args)).length == 1) {
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(TimedInterruptibleASTTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), new Object[]{BytecodeInterface8.objectArrayGet((Object[])IndyInterface.bootstrap("cast", "()", 0, args), 0)});
        }
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(TimedInterruptibleASTTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$2(String name, Object value) {
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectProperty(object, TimedInterruptibleASTTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
    }

    public /* synthetic */ Object this$dist$get$2(String name) {
        return ScriptBytecodeAdapter.getGroovyObjectProperty(TimedInterruptibleASTTransformation.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TimedInterruptibleASTTransformation.class) {
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

    static {
        ClassNode classNode;
        MY_TYPE = classNode = ClassHelper.make(TimedInterrupt.class);
    }

    private static class TimedInterruptionVisitor
    extends ClassCodeVisitorSupport
    implements GroovyObject {
        private final SourceUnit sourceUnit;
        private final boolean checkOnMethodStart;
        private final boolean applyToAllClasses;
        private final boolean applyToAllMembers;
        private FieldNode expireTimeField;
        private FieldNode startTimeField;
        private final Expression unit;
        private final Object maximum;
        private final ClassNode thrown;
        private final String basename;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        public TimedInterruptionVisitor(SourceUnit source, Object checkOnMethodStart, Object applyToAllClasses, Object applyToAllMembers, Object maximum, Expression unit, ClassNode thrown, Object hash) {
            String string;
            ClassNode classNode;
            Object object;
            Expression expression;
            SourceUnit sourceUnit;
            MetaClass metaClass;
            Object var9_9 = null;
            this.expireTimeField = IndyInterface.bootstrap("cast", "()", 0, var9_9);
            Object var10_10 = null;
            this.startTimeField = IndyInterface.bootstrap("cast", "()", 0, var10_10);
            this.metaClass = metaClass = this.$getStaticMetaClass();
            this.sourceUnit = sourceUnit = source;
            Object object2 = checkOnMethodStart;
            this.checkOnMethodStart = DefaultTypeTransformation.booleanUnbox(object2);
            Object object3 = applyToAllClasses;
            this.applyToAllClasses = DefaultTypeTransformation.booleanUnbox(object3);
            Object object4 = applyToAllMembers;
            this.applyToAllMembers = DefaultTypeTransformation.booleanUnbox(object4);
            this.unit = expression = unit;
            this.maximum = object = maximum;
            this.thrown = classNode = thrown;
            this.basename = string = StringGroovyMethods.plus((CharSequence)"timedInterrupt", hash);
        }

        private Statement createInterruptStatement() {
            return GeneralUtils.ifS((Expression)GeneralUtils.ltX(GeneralUtils.propX((Expression)GeneralUtils.varX("this"), StringGroovyMethods.plus(this.basename, (CharSequence)"$expireTime")), GeneralUtils.callX(ClassHelper.make(System.class), "nanoTime")), GeneralUtils.throwS(GeneralUtils.ctorX(this.thrown, GeneralUtils.args(GeneralUtils.plusX(GeneralUtils.plusX(GeneralUtils.constX(StringGroovyMethods.plus(StringGroovyMethods.plus((CharSequence)"Execution timed out after ", this.maximum), (CharSequence)" ")), GeneralUtils.callX((Expression)GeneralUtils.callX(this.unit, "name"), "toLowerCase", (Expression)GeneralUtils.propX((Expression)GeneralUtils.classX(Locale.class), "US"))), GeneralUtils.plusX(GeneralUtils.constX(". Start time: "), GeneralUtils.propX((Expression)GeneralUtils.varX("this"), StringGroovyMethods.plus(this.basename, (CharSequence)"$startTime"))))))));
        }

        private BlockStatement wrapBlock(Statement statement) {
            Reference<Statement> statement2 = new Reference<Statement>(statement);
            public final class _wrapBlock_closure1
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference statement;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _wrapBlock_closure1(Object _outerInstance, Object _thisObject, Reference statement) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.statement = reference = statement;
                }

                public Object doCall(Object it) {
                    ((BlockStatement)this.getDelegate()).addStatement(((TimedInterruptionVisitor)((Object)IndyInterface.bootstrap("cast", "()", 0, this.getThisObject()))).createInterruptStatement());
                    ((BlockStatement)this.getDelegate()).addStatement((Statement)((Object)IndyInterface.bootstrap("cast", "()", 0, this.statement.get())));
                    return null;
                }

                @Generated
                public Statement getStatement() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.statement.get());
                }

                @Generated
                public Object call(Object args) {
                    return this.doCall(args);
                }

                @Override
                @Generated
                public Object call() {
                    return this.doCall(null);
                }

                @Generated
                public Object doCall() {
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _wrapBlock_closure1.class) {
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
            return IndyInterface.bootstrap("cast", "()", 0, DefaultGroovyMethods.tap(GeneralUtils.block(new Statement[0]), new _wrapBlock_closure1(this, this, statement2)));
        }

        @Override
        public void visitClass(ClassNode node) {
            FieldNode fieldNode;
            FieldNode fieldNode2;
            FieldNode fieldNode3 = node.getDeclaredField(StringGroovyMethods.plus(this.basename, (CharSequence)"$expireTime"));
            if ((fieldNode3 == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, fieldNode3)) != false) {
                return;
            }
            this.expireTimeField = fieldNode2 = node.addField(StringGroovyMethods.plus(this.basename, (CharSequence)"$expireTime"), Opcodes.ACC_FINAL | Opcodes.ACC_PRIVATE, ClassHelper.long_TYPE, GeneralUtils.plusX(GeneralUtils.callX(ClassHelper.make(System.class), "nanoTime"), GeneralUtils.callX((Expression)GeneralUtils.propX((Expression)GeneralUtils.classX(TimeUnit.class), "NANOSECONDS"), "convert", (Expression)GeneralUtils.args(GeneralUtils.constX(this.maximum, true), this.unit))));
            boolean bl = true;
            this.expireTimeField.setSynthetic(bl);
            ClassNode dateClass = ClassHelper.make(Date.class);
            this.startTimeField = fieldNode = node.addField(StringGroovyMethods.plus(this.basename, (CharSequence)"$startTime"), Opcodes.ACC_FINAL | Opcodes.ACC_PRIVATE, dateClass, GeneralUtils.ctorX(dateClass));
            boolean bl2 = true;
            this.startTimeField.setSynthetic(bl2);
            node.getFields().remove(this.expireTimeField);
            node.getFields().remove(this.startTimeField);
            node.getFields().add(0, this.startTimeField);
            node.getFields().add(0, this.expireTimeField);
            if (this.applyToAllMembers) {
                super.visitClass(node);
            }
        }

        @Override
        public void visitClosureExpression(ClosureExpression closureExpr) {
            Statement code = closureExpr.getCode();
            if (code instanceof BlockStatement) {
                ((BlockStatement)((Object)IndyInterface.bootstrap("cast", "()", 0, code))).getStatements().add(0, this.createInterruptStatement());
            } else {
                BlockStatement blockStatement = this.wrapBlock(code);
                closureExpr.setCode(blockStatement);
            }
            super.visitClosureExpression(closureExpr);
        }

        @Override
        public void visitField(FieldNode node) {
            if (!node.isStatic() && !node.isSynthetic()) {
                super.visitField(node);
            }
        }

        @Override
        public void visitProperty(PropertyNode node) {
            if (!node.isStatic() && !node.isSynthetic()) {
                super.visitProperty(node);
            }
        }

        private Object visitLoop(LoopingStatement loopStatement) {
            Statement statement = loopStatement.getLoopBlock();
            BlockStatement blockStatement = this.wrapBlock(statement);
            loopStatement.setLoopBlock(blockStatement);
            return blockStatement;
        }

        @Override
        public void visitForLoop(ForStatement forStatement) {
            this.visitLoop(forStatement);
            super.visitForLoop(forStatement);
        }

        @Override
        public void visitDoWhileLoop(DoWhileStatement doWhileStatement) {
            this.visitLoop(doWhileStatement);
            super.visitDoWhileLoop(doWhileStatement);
        }

        @Override
        public void visitWhileLoop(WhileStatement whileStatement) {
            this.visitLoop(whileStatement);
            super.visitWhileLoop(whileStatement);
        }

        @Override
        public void visitMethod(MethodNode node) {
            if (this.checkOnMethodStart && !node.isSynthetic() && !node.isStatic() && !node.isAbstract()) {
                Statement code = node.getCode();
                BlockStatement blockStatement = this.wrapBlock(code);
                node.setCode(blockStatement);
            }
            if (!node.isSynthetic() && !node.isStatic()) {
                super.visitMethod(node);
            }
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(TimedInterruptionVisitor.class, TimedInterruptibleASTTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!(((CallSite)IndyInterface.bootstrap("cast", "()", 0, args)).length == 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(TimedInterruptionVisitor.class, TimedInterruptibleASTTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{BytecodeInterface8.objectArrayGet((Object[])IndyInterface.bootstrap("cast", "()", 0, args), 0)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(TimedInterruptionVisitor.class, TimedInterruptibleASTTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var5_5;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)new MissingMethodException(notFound.getMethod(), this.getClass(), notFound.getArguments());
            }
        }

        /*
         * Unable to fully structure code
         */
        public static /* synthetic */ Object $static_methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(TimedInterruptionVisitor.class, TimedInterruptibleASTTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!(((CallSite)IndyInterface.bootstrap("cast", "()", 0, args)).length == 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(TimedInterruptionVisitor.class, TimedInterruptibleASTTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{BytecodeInterface8.objectArrayGet((Object[])IndyInterface.bootstrap("cast", "()", 0, args), 0)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(TimedInterruptionVisitor.class, TimedInterruptibleASTTransformation.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)new MissingMethodException(notFound.getMethod(), TimedInterruptionVisitor.class, notFound.getArguments());
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, TimedInterruptibleASTTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)new MissingPropertyException(notFound.getProperty(), this.getClass(), notFound.getCause());
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, TimedInterruptibleASTTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)new MissingPropertyException(notFound.getProperty(), TimedInterruptionVisitor.class, notFound.getCause());
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(TimedInterruptionVisitor.class, TimedInterruptibleASTTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)new MissingPropertyException(notFound.getProperty(), this.getClass(), notFound.getCause());
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(TimedInterruptionVisitor.class, TimedInterruptibleASTTransformation.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)new MissingPropertyException(notFound.getProperty(), TimedInterruptionVisitor.class, notFound.getCause());
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TimedInterruptionVisitor.class) {
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

        @Override
        @Generated
        public final SourceUnit getSourceUnit() {
            return this.sourceUnit;
        }
    }
}

