/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.ast.builder;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.lang.GroovyInterceptable;
import groovy.lang.MetaClass;
import groovy.lang.Range;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.FromString;
import java.beans.Transient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.DynamicVariable;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.ImportNode;
import org.codehaus.groovy.ast.InnerClassNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.MixinNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.AnnotationConstantExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.AttributeExpression;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.ClosureListExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.FieldExpression;
import org.codehaus.groovy.ast.expr.GStringExpression;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.NamedArgumentListExpression;
import org.codehaus.groovy.ast.expr.NotExpression;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.RangeExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.SpreadMapExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.TernaryExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.codehaus.groovy.ast.stmt.CaseStatement;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.SwitchStatement;
import org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import org.codehaus.groovy.ast.stmt.ThrowStatement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.MethodClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.syntax.Token;
import org.codehaus.groovy.syntax.Types;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class AstSpecificationCompiler
implements GroovyInterceptable {
    private final List<ASTNode> expression;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    public AstSpecificationCompiler(@DelegatesTo(value=AstSpecificationCompiler.class) Closure spec) {
        MetaClass metaClass;
        List list;
        this.expression = list = ScriptBytecodeAdapter.createList(new Object[0]);
        this.metaClass = metaClass = this.$getStaticMetaClass();
        AstSpecificationCompiler astSpecificationCompiler = this;
        ScriptBytecodeAdapter.setGroovyObjectProperty(astSpecificationCompiler, AstSpecificationCompiler.class, spec, "delegate");
        IndyInterface.bootstrap("invoke", "call", 0, spec);
    }

    public List<ASTNode> getExpression() {
        return this.expression;
    }

    /*
     * WARNING - void declaration
     */
    private List<ASTNode> enforceConstraints(String methodName, List<Class> spec) {
        void var2_2;
        Reference<String> methodName2 = new Reference<String>(methodName);
        Reference<void> spec2 = new Reference<void>(var2_2);
        if (ScriptBytecodeAdapter.compareNotEqual(IndyInterface.bootstrap("invoke", "size", 0, (List)spec2.get()), IndyInterface.bootstrap("invoke", "size", 0, this.expression))) {
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class, new GStringImpl(new Object[]{methodName2.get(), (List)spec2.get(), ScriptBytecodeAdapter.getPropertySpreadSafe(AstSpecificationCompiler.class, this.expression, "class")}, new String[]{"", " could not be invoked. Expected to receive parameters ", " but found ", ""})));
        }
        public final class _enforceConstraints_closure1
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference spec;
            private /* synthetic */ Reference methodName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _enforceConstraints_closure1(Object _outerInstance, Object _thisObject, Reference spec, Reference methodName) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.spec = reference2 = spec;
                this.methodName = reference = methodName;
            }

            public Object doCall(int it) {
                CallSite actualClass = IndyInterface.bootstrap("getProperty", "class", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), it));
                CallSite expectedClass = IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, this.spec.get()), it);
                if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "isAssignableFrom", 0, expectedClass, actualClass)) == false) {
                    throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class, new GStringImpl(new Object[]{IndyInterface.bootstrap("cast", "()", 0, this.methodName.get()), IndyInterface.bootstrap("cast", "()", 0, this.spec.get()), ScriptBytecodeAdapter.getPropertySpreadSafe(_enforceConstraints_closure1.class, IndyInterface.bootstrap("getProperty", "expression", 12, this), "class")}, new String[]{"", " could not be invoked. Expected to receive parameters ", " but found ", ""})));
                }
                return IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), it);
            }

            @Generated
            public Object call(int it) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, it);
            }

            @Generated
            public List getSpec() {
                return IndyInterface.bootstrap("cast", "()", 0, this.spec.get());
            }

            @Generated
            public String getMethodName() {
                return IndyInterface.bootstrap("cast", "()", 0, this.methodName.get());
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _enforceConstraints_closure1.class) {
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
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "collect", 0, ScriptBytecodeAdapter.createRange(0, IndyInterface.bootstrap("invoke", "minus", 0, IndyInterface.bootstrap("invoke", "size", 0, (List)spec2.get()), true), false, false), new _enforceConstraints_closure1(this, this, spec2, methodName2)));
    }

    private void captureAndCreateNode(String name, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock, @ClosureParams(value=FromString.class, options={"java.util.List<org.codehaus.groovy.ast.ASTNode>"}) Closure ctorBlock) {
        Closure closure = argBlock;
        if ((closure == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, closure)) == false) {
            throw (Throwable)new IllegalArgumentException((String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"nodes of type ", " require arguments to be specified"}))));
        }
        ArrayList<ASTNode> oldProps = new ArrayList<ASTNode>(this.expression);
        this.expression.clear();
        new AstSpecificationCompiler(argBlock);
        Object result = ctorBlock.call((Object)this.expression);
        this.expression.clear();
        this.expression.addAll(oldProps);
        this.expression.add((ASTNode)result);
    }

    /*
     * WARNING - void declaration
     */
    private void makeNode(Class target, String typeAlias, List<Class<? super ASTNode>> ctorArgs, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        void var3_3;
        void var2_2;
        Reference<Class> target2 = new Reference<Class>(target);
        Reference<void> typeAlias2 = new Reference<void>(var2_2);
        Reference<void> ctorArgs2 = new Reference<void>(var3_3);
        public final class _makeNode_closure2
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference target;
            private /* synthetic */ Reference typeAlias;
            private /* synthetic */ Reference ctorArgs;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _makeNode_closure2(Object _outerInstance, Object _thisObject, Reference target, Reference typeAlias, Reference ctorArgs) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                Reference reference3;
                this.target = reference3 = target;
                this.typeAlias = reference2 = typeAlias;
                this.ctorArgs = reference = ctorArgs;
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("invoke", "newInstance", 16, IndyInterface.bootstrap("cast", "()", 0, this.target.get()), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{IndyInterface.bootstrap("invoke", "enforceConstraints", 2, this, IndyInterface.bootstrap("cast", "()", 0, this.typeAlias.get()), IndyInterface.bootstrap("cast", "()", 0, this.ctorArgs.get()))}, new int[]{0}));
            }

            @Generated
            public Class getTarget() {
                return IndyInterface.bootstrap("cast", "()", 0, this.target.get());
            }

            @Generated
            public String getTypeAlias() {
                return IndyInterface.bootstrap("cast", "()", 0, this.typeAlias.get());
            }

            @Generated
            public List getCtorArgs() {
                return IndyInterface.bootstrap("cast", "()", 0, this.ctorArgs.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _makeNode_closure2.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, IndyInterface.bootstrap("getProperty", "simpleName", 0, target2.get()), argBlock, new _makeNode_closure2(this, this, target2, typeAlias2, ctorArgs2));
    }

    private void makeNodeFromList(Class target, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<Class> target2 = new Reference<Class>(target);
        public final class _makeNodeFromList_closure3
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference target;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _makeNodeFromList_closure3(Object _outerInstance, Object _thisObject, Reference target) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.target = reference = target;
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("invoke", "newInstance", 0, IndyInterface.bootstrap("cast", "()", 0, this.target.get()), IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("getProperty", "expression", 12, this)));
            }

            @Generated
            public Class getTarget() {
                return IndyInterface.bootstrap("cast", "()", 0, this.target.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _makeNodeFromList_closure3.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, IndyInterface.bootstrap("getProperty", "simpleName", 0, target2.get()), argBlock, new _makeNodeFromList_closure3(this, this, target2));
    }

    private void makeListOfNodes(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock, String input) {
        public final class _makeListOfNodes_closure4
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _makeListOfNodes_closure4(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("getProperty", "expression", 12, this));
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _makeListOfNodes_closure4.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, input, argBlock, new _makeListOfNodes_closure4(this, this));
    }

    private void makeArrayOfNodes(Object target, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<Object> target2 = new Reference<Object>(target);
        public final class _makeArrayOfNodes_closure5
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference target;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _makeArrayOfNodes_closure5(Object _outerInstance, Object _thisObject, Reference target) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.target = reference = target;
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("invoke", "toArray", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), this.target.get());
            }

            @Generated
            public Object getTarget() {
                return this.target.get();
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _makeArrayOfNodes_closure5.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, IndyInterface.bootstrap("getProperty", "simpleName", 0, IndyInterface.bootstrap("getProperty", "class", 0, target2.get())), argBlock, new _makeArrayOfNodes_closure5(this, this, target2));
    }

    /*
     * WARNING - void declaration
     */
    private void makeNodeWithClassParameter(Class target, String alias, List<Class> spec, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock, Class type) {
        void var3_3;
        void var2_2;
        Reference<Class> target2 = new Reference<Class>(target);
        Reference<void> alias2 = new Reference<void>(var2_2);
        Reference<void> spec2 = new Reference<void>(var3_3);
        Reference<Class> type2 = new Reference<Class>(type);
        public final class _makeNodeWithClassParameter_closure6
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference type;
            private /* synthetic */ Reference target;
            private /* synthetic */ Reference alias;
            private /* synthetic */ Reference spec;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _makeNodeWithClassParameter_closure6(Object _outerInstance, Object _thisObject, Reference type, Reference target, Reference alias, Reference spec) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                this.type = reference4 = type;
                this.target = reference3 = target;
                this.alias = reference2 = alias;
                this.spec = reference = spec;
            }

            public Object doCall(Object it) {
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.type.get())));
                return IndyInterface.bootstrap("invoke", "newInstance", 16, IndyInterface.bootstrap("cast", "()", 0, this.target.get()), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{IndyInterface.bootstrap("invoke", "enforceConstraints", 2, this, IndyInterface.bootstrap("cast", "()", 0, this.alias.get()), IndyInterface.bootstrap("cast", "()", 0, this.spec.get()))}, new int[]{0}));
            }

            @Generated
            public Class getType() {
                return IndyInterface.bootstrap("cast", "()", 0, this.type.get());
            }

            @Generated
            public Class getTarget() {
                return IndyInterface.bootstrap("cast", "()", 0, this.target.get());
            }

            @Generated
            public String getAlias() {
                return IndyInterface.bootstrap("cast", "()", 0, this.alias.get());
            }

            @Generated
            public List getSpec() {
                return IndyInterface.bootstrap("cast", "()", 0, this.spec.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _makeNodeWithClassParameter_closure6.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, IndyInterface.bootstrap("getProperty", "simpleName", 0, target2.get()), argBlock, new _makeNodeWithClassParameter_closure6(this, this, type2, target2, alias2, spec2));
    }

    /*
     * WARNING - void declaration
     */
    private void makeNodeWithStringParameter(Class target, String alias, List<Class> spec, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock, String text) {
        void var3_3;
        void var2_2;
        Reference<Class> target2 = new Reference<Class>(target);
        Reference<void> alias2 = new Reference<void>(var2_2);
        Reference<void> spec2 = new Reference<void>(var3_3);
        Reference<String> text2 = new Reference<String>(text);
        public final class _makeNodeWithStringParameter_closure7
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference text;
            private /* synthetic */ Reference target;
            private /* synthetic */ Reference alias;
            private /* synthetic */ Reference spec;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _makeNodeWithStringParameter_closure7(Object _outerInstance, Object _thisObject, Reference text, Reference target, Reference alias, Reference spec) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                this.text = reference4 = text;
                this.target = reference3 = target;
                this.alias = reference2 = alias;
                this.spec = reference = spec;
            }

            public Object doCall(Object it) {
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("cast", "()", 0, this.text.get()));
                return IndyInterface.bootstrap("invoke", "newInstance", 16, IndyInterface.bootstrap("cast", "()", 0, this.target.get()), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{IndyInterface.bootstrap("invoke", "enforceConstraints", 2, this, IndyInterface.bootstrap("cast", "()", 0, this.alias.get()), IndyInterface.bootstrap("cast", "()", 0, this.spec.get()))}, new int[]{0}));
            }

            @Generated
            public String getText() {
                return IndyInterface.bootstrap("cast", "()", 0, this.text.get());
            }

            @Generated
            public Class getTarget() {
                return IndyInterface.bootstrap("cast", "()", 0, this.target.get());
            }

            @Generated
            public String getAlias() {
                return IndyInterface.bootstrap("cast", "()", 0, this.alias.get());
            }

            @Generated
            public List getSpec() {
                return IndyInterface.bootstrap("cast", "()", 0, this.spec.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _makeNodeWithStringParameter_closure7.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, IndyInterface.bootstrap("getProperty", "simpleName", 0, target2.get()), argBlock, new _makeNodeWithStringParameter_closure7(this, this, text2, target2, alias2, spec2));
    }

    public void cast(Class type, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNodeWithClassParameter", 2, this, CastExpression.class, "cast", ScriptBytecodeAdapter.createList(new Object[]{ClassNode.class, Expression.class}), argBlock, type);
    }

    public void constructorCall(Class type, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNodeWithClassParameter", 2, this, ConstructorCallExpression.class, "constructorCall", ScriptBytecodeAdapter.createList(new Object[]{ClassNode.class, Expression.class}), argBlock, type);
    }

    public void methodCall(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, MethodCallExpression.class, "methodCall", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Expression.class, Expression.class}), argBlock);
    }

    public void annotationConstant(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, AnnotationConstantExpression.class, "annotationConstant", ScriptBytecodeAdapter.createList(new Object[]{AnnotationNode.class}), argBlock);
    }

    public void postfix(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, PostfixExpression.class, "postfix", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Token.class}), argBlock);
    }

    public void field(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, FieldExpression.class, "field", ScriptBytecodeAdapter.createList(new Object[]{FieldNode.class}), argBlock);
    }

    public void map(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNodeFromList", 2, this, MapExpression.class, argBlock);
    }

    public void tuple(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNodeFromList", 2, this, TupleExpression.class, argBlock);
    }

    public void mapEntry(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, MapEntryExpression.class, "mapEntry", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Expression.class}), argBlock);
    }

    public void gString(String verbatimText, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNodeWithStringParameter", 2, this, GStringExpression.class, "gString", ScriptBytecodeAdapter.createList(new Object[]{String.class, List.class, List.class}), argBlock, verbatimText);
    }

    public void methodPointer(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, MethodPointerExpression.class, "methodPointer", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Expression.class}), argBlock);
    }

    public void property(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, PropertyExpression.class, "property", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Expression.class}), argBlock);
    }

    public void range(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, RangeExpression.class, "range", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Expression.class, Boolean.class}), argBlock);
    }

    public void empty() {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("getProperty", "INSTANCE", 0, EmptyStatement.class));
    }

    public void label(String label) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, label);
    }

    public void importNode(Class target, String alias) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, ImportNode.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, target), alias));
    }

    public void catchStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, CatchStatement.class, "catchStatement", ScriptBytecodeAdapter.createList(new Object[]{Parameter.class, Statement.class}), argBlock);
    }

    public void throwStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, ThrowStatement.class, "throwStatement", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    public void synchronizedStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, SynchronizedStatement.class, "synchronizedStatement", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Statement.class}), argBlock);
    }

    public void returnStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, ReturnStatement.class, "returnStatement", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    private void ternary(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, TernaryExpression.class, "ternary", ScriptBytecodeAdapter.createList(new Object[]{BooleanExpression.class, Expression.class, Expression.class}), argBlock);
    }

    public void elvisOperator(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, ElvisOperatorExpression.class, "elvisOperator", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Expression.class}), argBlock);
    }

    public void breakStatement(String label) {
        if (IndyInterface.bootstrap("cast", "()", 0, label) != false) {
            IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, BreakStatement.class, label));
        } else {
            IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, BreakStatement.class));
        }
    }

    public void continueStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        if (IndyInterface.bootstrap("cast", "()", 0, argBlock) == false) {
            IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, ContinueStatement.class));
        } else {
            IndyInterface.bootstrap("invoke", "makeNode", 2, this, ContinueStatement.class, "continueStatement", ScriptBytecodeAdapter.createList(new Object[]{String.class}), argBlock);
        }
    }

    public void caseStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, CaseStatement.class, "caseStatement", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Statement.class}), argBlock);
    }

    public void defaultCase(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "block", 2, this, argBlock);
    }

    public void prefix(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, PrefixExpression.class, "prefix", ScriptBytecodeAdapter.createList(new Object[]{Token.class, Expression.class}), argBlock);
    }

    public void not(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, NotExpression.class, "not", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    public void dynamicVariable(String variable, boolean isStatic) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, DynamicVariable.class, variable, isStatic));
    }

    public void exceptions(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeArrayOfNodes", 2, this, ScriptBytecodeAdapter.createPojoWrapper((ClassNode[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[0]), ClassNode[].class), ClassNode[].class), argBlock);
    }

    public void annotations(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<AnnotationNode>");
    }

    public void methods(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<MethodNode>");
    }

    public void constructors(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<ConstructorNode>");
    }

    public void properties(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<PropertyNode>");
    }

    public void fields(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<FieldNode>");
    }

    public void strings(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<ConstantExpression>");
    }

    public void values(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<Expression>");
    }

    public void inclusive(boolean value) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, value);
    }

    public void constant(Object value) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, value));
    }

    public void ifStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, IfStatement.class, "ifStatement", ScriptBytecodeAdapter.createList(new Object[]{BooleanExpression.class, Statement.class, Statement.class}), argBlock);
    }

    public void spread(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, SpreadExpression.class, "spread", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    public void spreadMap(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, SpreadMapExpression.class, "spreadMap", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    public void whileStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, WhileStatement.class, "whileStatement", ScriptBytecodeAdapter.createList(new Object[]{BooleanExpression.class, Statement.class}), argBlock);
    }

    public void forStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, ForStatement.class, "forStatement", ScriptBytecodeAdapter.createList(new Object[]{Parameter.class, Expression.class, Statement.class}), argBlock);
    }

    public void closureList(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNodeFromList", 2, this, ClosureListExpression.class, argBlock);
    }

    public void declaration(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, DeclarationExpression.class, "declaration", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Token.class, Expression.class}), argBlock);
    }

    public void list(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNodeFromList", 2, this, ListExpression.class, argBlock);
    }

    public void bitwiseNegation(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, BitwiseNegationExpression.class, "bitwiseNegation", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    public void closure(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, ClosureExpression.class, "closure", ScriptBytecodeAdapter.createList(new Object[]{Parameter[].class, Statement.class}), argBlock);
    }

    public void booleanExpression(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, BooleanExpression.class, "booleanExpression", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    public void binary(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, BinaryExpression.class, "binary", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Token.class, Expression.class}), argBlock);
    }

    public void unaryPlus(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, UnaryPlusExpression.class, "unaryPlus", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    public void classExpression(Class type) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, ClassExpression.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, type)));
    }

    public void unaryMinus(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, UnaryMinusExpression.class, "unaryMinus", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    public void attribute(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, AttributeExpression.class, "attribute", ScriptBytecodeAdapter.createList(new Object[]{Expression.class, Expression.class}), argBlock);
    }

    public void expression(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNode", 2, this, ExpressionStatement.class, "expression", ScriptBytecodeAdapter.createList(new Object[]{Expression.class}), argBlock);
    }

    public void namedArgumentList(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeNodeFromList", 2, this, NamedArgumentListExpression.class, argBlock);
    }

    public void interfaces(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<ClassNode>");
    }

    public void mixins(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<MixinNode>");
    }

    public void genericsTypes(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<GenericsTypes>");
    }

    public void classNode(Class target) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, target, false));
    }

    public void parameters(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeArrayOfNodes", 2, this, ScriptBytecodeAdapter.createPojoWrapper((Parameter[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[0]), Parameter[].class), Parameter[].class), argBlock);
    }

    public void block(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        public final class _block_closure8
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _block_closure8(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("invoke", "block", 0, GeneralUtils.class, IndyInterface.bootstrap("init", "<init>", 0, VariableScope.class), IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("getProperty", "expression", 12, this)));
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _block_closure8.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "BlockStatement", argBlock, new _block_closure8(this, this));
    }

    public void parameter(Map<String, Class> args, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<Closure> argBlock2 = new Reference<Closure>(argBlock);
        if (IndyInterface.bootstrap("cast", "()", 0, args) == false) {
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class));
        }
        if (ScriptBytecodeAdapter.compareGreaterThan(IndyInterface.bootstrap("invoke", "size", 0, args), 1)) {
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class));
        }
        if (IndyInterface.bootstrap("cast", "()", 0, argBlock2.get()) != false) {
            public final class _parameter_closure9
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference argBlock;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _parameter_closure9(Object _outerInstance, Object _thisObject, Reference argBlock) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.argBlock = reference = argBlock;
                }

                /*
                 * WARNING - void declaration
                 */
                public Object doCall(Object name, Object type) {
                    void var2_2;
                    Reference<Object> name2 = new Reference<Object>(name);
                    Reference<void> type2 = new Reference<void>(var2_2);
                    public final class _closure28
                    extends Closure
                    implements GeneratedClosure {
                        private /* synthetic */ Reference type;
                        private /* synthetic */ Reference name;
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;

                        public _closure28(Object _outerInstance, Object _thisObject, Reference type, Reference name) {
                            super(_outerInstance, _thisObject);
                            Reference reference;
                            Reference reference2;
                            this.type = reference2 = type;
                            this.name = reference = name;
                        }

                        public Object doCall(Object it) {
                            return IndyInterface.bootstrap("invoke", "param", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, this.type.get()), this.name.get(), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false));
                        }

                        @Generated
                        public Object getType() {
                            return this.type.get();
                        }

                        @Generated
                        public Object getName() {
                            return this.name.get();
                        }

                        @Generated
                        public Object doCall() {
                            return this.doCall(null);
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _closure28.class) {
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
                    return IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "Parameter", IndyInterface.bootstrap("cast", "()", 0, this.argBlock.get()), new _closure28(this, this.getThisObject(), type2, name2));
                }

                /*
                 * WARNING - void declaration
                 */
                @Generated
                public Object call(Object name, Object type) {
                    void var2_2;
                    Reference<Object> name2 = new Reference<Object>(name);
                    Reference<void> type2 = new Reference<void>(var2_2);
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, name2.get(), type2.get());
                }

                @Generated
                public Closure getArgBlock() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.argBlock.get());
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _parameter_closure9.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, args, new _parameter_closure9(this, this, argBlock2));
        } else {
            public final class _parameter_closure10
            extends Closure
            implements GeneratedClosure {
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _parameter_closure10(Object _outerInstance, Object _thisObject) {
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object name, Object type) {
                    return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), IndyInterface.bootstrap("invoke", "param", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, type), name));
                }

                @Generated
                public Object call(Object name, Object type) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, name, type);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _parameter_closure10.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, args, new _parameter_closure10(this, this));
        }
    }

    public void array(Class type, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<Class> type2 = new Reference<Class>(type);
        public final class _array_closure11
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference type;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _array_closure11(Object _outerInstance, Object _thisObject, Reference type) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.type = reference = type;
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("init", "<init>", 0, ArrayExpression.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.type.get())), IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("getProperty", "expression", 12, this)));
            }

            @Generated
            public Class getType() {
                return IndyInterface.bootstrap("cast", "()", 0, this.type.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _array_closure11.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "ArrayExpression", argBlock, new _array_closure11(this, this, type2));
    }

    public void genericsType(Class type, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<Class> type2 = new Reference<Class>(type);
        if (IndyInterface.bootstrap("cast", "()", 0, argBlock) != false) {
            public final class _genericsType_closure12
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference type;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _genericsType_closure12(Object _outerInstance, Object _thisObject, Reference type) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.type = reference = type;
                }

                public Object doCall(Object it) {
                    return IndyInterface.bootstrap("init", "<init>", 0, GenericsType.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.type.get())), ScriptBytecodeAdapter.createPojoWrapper((ClassNode[])IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false), ClassNode[].class), ClassNode[].class), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), true));
                }

                @Generated
                public Class getType() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.type.get());
                }

                @Generated
                public Object doCall() {
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _genericsType_closure12.class) {
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
            IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "GenericsType", argBlock, new _genericsType_closure12(this, this, type2));
        } else {
            IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, GenericsType.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, type2.get())));
        }
    }

    public void upperBound(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        IndyInterface.bootstrap("invoke", "makeListOfNodes", 2, this, argBlock, "List<ClassNode>");
    }

    public void lowerBound(Class target) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, target));
    }

    public void member(String name, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<String> name2 = new Reference<String>(name);
        public final class _member_closure13
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference name;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _member_closure13(Object _outerInstance, Object _thisObject, Reference name) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.name = reference = name;
            }

            public Object doCall(Object it) {
                return ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("cast", "()", 0, this.name.get()), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false)});
            }

            @Generated
            public String getName() {
                return IndyInterface.bootstrap("cast", "()", 0, this.name.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _member_closure13.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "Annotation Member", argBlock, new _member_closure13(this, this, name2));
    }

    public void argumentList(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        if (IndyInterface.bootstrap("cast", "()", 0, argBlock) == false) {
            IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, ArgumentListExpression.class));
        } else {
            IndyInterface.bootstrap("invoke", "makeNodeFromList", 2, this, ArgumentListExpression.class, argBlock);
        }
    }

    public void annotation(Class target, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<Class> target2 = new Reference<Class>(target);
        if (IndyInterface.bootstrap("cast", "()", 0, argBlock) != false) {
            public final class _annotation_closure14
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference target;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _annotation_closure14(Object _outerInstance, Object _thisObject, Reference target) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.target = reference = target;
                }

                public Object doCall(Object it) {
                    Reference<CallSite> node = new Reference<CallSite>(IndyInterface.bootstrap("init", "<init>", 0, AnnotationNode.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.target.get()))));
                    public final class _closure29
                    extends Closure
                    implements GeneratedClosure {
                        private /* synthetic */ Reference node;
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;

                        public _closure29(Object _outerInstance, Object _thisObject, Reference node) {
                            super(_outerInstance, _thisObject);
                            Reference reference;
                            this.node = reference = node;
                        }

                        public Object doCall(Object it) {
                            return IndyInterface.bootstrap("invoke", "addMember", 0, this.node.get(), IndyInterface.bootstrap("invoke", "getAt", 0, it, false), IndyInterface.bootstrap("invoke", "getAt", 0, it, true));
                        }

                        @Generated
                        public Object getNode() {
                            return this.node.get();
                        }

                        @Generated
                        public Object doCall() {
                            return this.doCall(null);
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _closure29.class) {
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
                    IndyInterface.bootstrap("invoke", "each", 1, IndyInterface.bootstrap("getProperty", "expression", 12, this), new _closure29(this, this.getThisObject(), node));
                    return node.get();
                }

                @Generated
                public Class getTarget() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.target.get());
                }

                @Generated
                public Object doCall() {
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _annotation_closure14.class) {
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
            IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "ArgumentListExpression", argBlock, new _annotation_closure14(this, this, target2));
        } else {
            IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, AnnotationNode.class, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, target2.get())));
        }
    }

    public void mixin(String name, int modifiers, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<String> name2 = new Reference<String>(name);
        Reference<Integer> modifiers2 = new Reference<Integer>(modifiers);
        public final class _mixin_closure15
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference name;
            private /* synthetic */ Reference modifiers;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _mixin_closure15(Object _outerInstance, Object _thisObject, Reference name, Reference modifiers) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.name = reference2 = name;
                this.modifiers = reference = modifiers;
            }

            public Object doCall(Object it) {
                if (ScriptBytecodeAdapter.compareGreaterThan(IndyInterface.bootstrap("invoke", "size", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this)), 1)) {
                    return IndyInterface.bootstrap("init", "<init>", 0, MixinNode.class, IndyInterface.bootstrap("cast", "()", 0, this.name.get()), IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false), ScriptBytecodeAdapter.createPojoWrapper((ClassNode[])IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), true)), ClassNode[].class), ClassNode[].class));
                }
                return IndyInterface.bootstrap("init", "<init>", 0, MixinNode.class, IndyInterface.bootstrap("cast", "()", 0, this.name.get()), IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false));
            }

            @Generated
            public String getName() {
                return IndyInterface.bootstrap("cast", "()", 0, this.name.get());
            }

            @Generated
            public int getModifiers() {
                return (Integer)((Object)IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()));
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _mixin_closure15.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "AttributeExpression", argBlock, new _mixin_closure15(this, this, name2, modifiers2));
    }

    public void classNode(String name, int modifiers, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<String> name2 = new Reference<String>(name);
        Reference<Integer> modifiers2 = new Reference<Integer>(modifiers);
        public final class _classNode_closure16
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference name;
            private /* synthetic */ Reference modifiers;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _classNode_closure16(Object _outerInstance, Object _thisObject, Reference name, Reference modifiers) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.name = reference2 = name;
                this.modifiers = reference = modifiers;
            }

            public Object doCall(Object it) {
                Reference<CallSite> result = new Reference<CallSite>(IndyInterface.bootstrap("init", "<init>", 0, ClassNode.class, IndyInterface.bootstrap("cast", "()", 0, this.name.get()), IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false), ScriptBytecodeAdapter.createPojoWrapper((ClassNode[])IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), true)), ClassNode[].class), ClassNode[].class), ScriptBytecodeAdapter.createPojoWrapper((MixinNode[])IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 2)), MixinNode[].class), MixinNode[].class)));
                while (ScriptBytecodeAdapter.compareGreaterThan(IndyInterface.bootstrap("invoke", "size", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this)), 3)) {
                    if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "isAssignableFrom", 0, List.class, IndyInterface.bootstrap("invoke", "getClass", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3)))) == false) {
                        throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class, IndyInterface.bootstrap("invoke", "plus", 0, "Expecting to find list of additional items instead found: ", IndyInterface.bootstrap("invoke", "getClass", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3)))));
                    }
                    if (ScriptBytecodeAdapter.compareGreaterThan(IndyInterface.bootstrap("invoke", "size", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3)), 0)) {
                        CallSite clazz = IndyInterface.bootstrap("invoke", "getClass", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3), false));
                        CallSite callSite = clazz;
                        if (ScriptBytecodeAdapter.isCase(callSite, GenericsType.class)) {
                            GenericsType[] genericsTypeArray = (GenericsType[])IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3)), GenericsType[].class);
                            ScriptBytecodeAdapter.setProperty(genericsTypeArray, null, result.get(), "genericsTypes");
                        } else if (ScriptBytecodeAdapter.isCase(callSite, MethodNode.class)) {
                            public final class _closure30
                            extends Closure
                            implements GeneratedClosure {
                                private /* synthetic */ Reference result;
                                private static /* synthetic */ ClassInfo $staticClassInfo;
                                public static transient /* synthetic */ boolean __$stMC;

                                public _closure30(Object _outerInstance, Object _thisObject, Reference result) {
                                    super(_outerInstance, _thisObject);
                                    Reference reference;
                                    this.result = reference = result;
                                }

                                public Object doCall(Object it) {
                                    return IndyInterface.bootstrap("invoke", "addMethod", 0, this.result.get(), it);
                                }

                                @Generated
                                public Object getResult() {
                                    return this.result.get();
                                }

                                @Generated
                                public Object doCall() {
                                    return this.doCall(null);
                                }

                                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                    if (this.getClass() != _closure30.class) {
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
                            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3), new _closure30(this, this.getThisObject(), result));
                        } else if (ScriptBytecodeAdapter.isCase(callSite, ConstructorNode.class)) {
                            public final class _closure31
                            extends Closure
                            implements GeneratedClosure {
                                private /* synthetic */ Reference result;
                                private static /* synthetic */ ClassInfo $staticClassInfo;
                                public static transient /* synthetic */ boolean __$stMC;

                                public _closure31(Object _outerInstance, Object _thisObject, Reference result) {
                                    super(_outerInstance, _thisObject);
                                    Reference reference;
                                    this.result = reference = result;
                                }

                                public Object doCall(Object it) {
                                    return IndyInterface.bootstrap("invoke", "addConstructor", 0, this.result.get(), it);
                                }

                                @Generated
                                public Object getResult() {
                                    return this.result.get();
                                }

                                @Generated
                                public Object doCall() {
                                    return this.doCall(null);
                                }

                                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                    if (this.getClass() != _closure31.class) {
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
                            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3), new _closure31(this, this.getThisObject(), result));
                        } else if (ScriptBytecodeAdapter.isCase(callSite, PropertyNode.class)) {
                            public final class _closure32
                            extends Closure
                            implements GeneratedClosure {
                                private /* synthetic */ Reference result;
                                private static /* synthetic */ ClassInfo $staticClassInfo;
                                public static transient /* synthetic */ boolean __$stMC;

                                public _closure32(Object _outerInstance, Object _thisObject, Reference result) {
                                    super(_outerInstance, _thisObject);
                                    Reference reference;
                                    this.result = reference = result;
                                }

                                public Object doCall(Object it) {
                                    Object t = this.result.get();
                                    ScriptBytecodeAdapter.setProperty(t, null, IndyInterface.bootstrap("getProperty", "field", 0, it), "owner");
                                    return IndyInterface.bootstrap("invoke", "addProperty", 0, this.result.get(), it);
                                }

                                @Generated
                                public Object getResult() {
                                    return this.result.get();
                                }

                                @Generated
                                public Object doCall() {
                                    return this.doCall(null);
                                }

                                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                    if (this.getClass() != _closure32.class) {
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
                            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3), new _closure32(this, this.getThisObject(), result));
                        } else if (ScriptBytecodeAdapter.isCase(callSite, FieldNode.class)) {
                            public final class _closure33
                            extends Closure
                            implements GeneratedClosure {
                                private /* synthetic */ Reference result;
                                private static /* synthetic */ ClassInfo $staticClassInfo;
                                public static transient /* synthetic */ boolean __$stMC;

                                public _closure33(Object _outerInstance, Object _thisObject, Reference result) {
                                    super(_outerInstance, _thisObject);
                                    Reference reference;
                                    this.result = reference = result;
                                }

                                public Object doCall(Object it) {
                                    Object t = this.result.get();
                                    ScriptBytecodeAdapter.setProperty(t, null, it, "owner");
                                    return IndyInterface.bootstrap("invoke", "addField", 0, this.result.get(), it);
                                }

                                @Generated
                                public Object getResult() {
                                    return this.result.get();
                                }

                                @Generated
                                public Object doCall() {
                                    return this.doCall(null);
                                }

                                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                    if (this.getClass() != _closure33.class) {
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
                            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3), new _closure33(this, this.getThisObject(), result));
                        } else if (ScriptBytecodeAdapter.isCase(callSite, AnnotationNode.class)) {
                            IndyInterface.bootstrap("invoke", "addAnnotations", 0, result.get(), IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3)));
                        } else {
                            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "name", 0, clazz)}, new String[]{"Unexpected item found in ClassNode spec. Expecting [Field|Method|Property|Constructor|Annotation|GenericsType] but found: ", ""})));
                        }
                    }
                    IndyInterface.bootstrap("invoke", "remove", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3);
                }
                return result.get();
            }

            @Generated
            public String getName() {
                return IndyInterface.bootstrap("cast", "()", 0, this.name.get());
            }

            @Generated
            public int getModifiers() {
                return (Integer)((Object)IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()));
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _classNode_closure16.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "ClassNode", argBlock, new _classNode_closure16(this, this, name2, modifiers2));
    }

    public void assertStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        public final class _assertStatement_closure17
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _assertStatement_closure17(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                if (ScriptBytecodeAdapter.compareLessThan(IndyInterface.bootstrap("invoke", "size", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this)), 2)) {
                    return IndyInterface.bootstrap("init", "<init>", 16, AssertStatement.class, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{IndyInterface.bootstrap("invoke", "enforceConstraints", 2, this, "assertStatement", ScriptBytecodeAdapter.createList(new Object[]{BooleanExpression.class}))}, new int[]{0}));
                }
                return IndyInterface.bootstrap("init", "<init>", 16, AssertStatement.class, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{IndyInterface.bootstrap("invoke", "enforceConstraints", 2, this, "assertStatement", ScriptBytecodeAdapter.createList(new Object[]{BooleanExpression.class, Expression.class}))}, new int[]{0}));
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _assertStatement_closure17.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "AssertStatement", argBlock, new _assertStatement_closure17(this, this));
    }

    public void tryCatch(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        public final class _tryCatch_closure18
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _tryCatch_closure18(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                Reference<CallSite> result = new Reference<CallSite>(IndyInterface.bootstrap("init", "<init>", 0, TryCatchStatement.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), true)));
                CallSite catchStatements = IndyInterface.bootstrap("invoke", "tail", 0, IndyInterface.bootstrap("invoke", "tail", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this)));
                public final class _closure34
                extends Closure
                implements GeneratedClosure {
                    private /* synthetic */ Reference result;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _closure34(Object _outerInstance, Object _thisObject, Reference result) {
                        super(_outerInstance, _thisObject);
                        Reference reference;
                        this.result = reference = result;
                    }

                    public Object doCall(Object statement) {
                        return IndyInterface.bootstrap("invoke", "addCatch", 0, this.result.get(), statement);
                    }

                    @Generated
                    public Object getResult() {
                        return this.result.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _closure34.class) {
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
                IndyInterface.bootstrap("invoke", "each", 0, catchStatements, new _closure34(this, this.getThisObject(), result));
                return result.get();
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _tryCatch_closure18.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "TryCatchStatement", argBlock, new _tryCatch_closure18(this, this));
    }

    public void variable(String variable) {
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, VariableExpression.class, variable));
    }

    /*
     * WARNING - void declaration
     */
    public void method(String name, int modifiers, Class returnType, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        void var3_3;
        Reference<String> name2 = new Reference<String>(name);
        Reference<Integer> modifiers2 = new Reference<Integer>(modifiers);
        Reference<void> returnType2 = new Reference<void>(var3_3);
        public final class _method_closure19
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference name;
            private /* synthetic */ Reference modifiers;
            private /* synthetic */ Reference returnType;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _method_closure19(Object _outerInstance, Object _thisObject, Reference name, Reference modifiers, Reference returnType) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                Reference reference3;
                this.name = reference3 = name;
                this.modifiers = reference2 = modifiers;
                this.returnType = reference = returnType;
            }

            public Object doCall(Object it) {
                CallSite result = IndyInterface.bootstrap("init", "<init>", 0, MethodNode.class, IndyInterface.bootstrap("cast", "()", 0, this.name.get()), IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()), IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.returnType.get())), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), true), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 2));
                if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3)) != false) {
                    IndyInterface.bootstrap("invoke", "addAnnotations", 0, result, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3)));
                }
                return result;
            }

            @Generated
            public String getName() {
                return IndyInterface.bootstrap("cast", "()", 0, this.name.get());
            }

            @Generated
            public int getModifiers() {
                return (Integer)((Object)IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()));
            }

            @Generated
            public Class getReturnType() {
                return IndyInterface.bootstrap("cast", "()", 0, this.returnType.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _method_closure19.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "MethodNode", argBlock, new _method_closure19(this, this, name2, modifiers2, returnType2));
    }

    public void token(String value) {
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class, "Null: value"));
        }
        CallSite tokenID = IndyInterface.bootstrap("invoke", "lookupKeyword", 0, Types.class, value);
        if (ScriptBytecodeAdapter.compareEqual(tokenID, IndyInterface.bootstrap("getProperty", "UNKNOWN", 0, Types.class))) {
            tokenID = IndyInterface.bootstrap("invoke", "lookupSymbol", 0, Types.class, value);
        }
        if (ScriptBytecodeAdapter.compareEqual(tokenID, IndyInterface.bootstrap("getProperty", "UNKNOWN", 0, Types.class))) {
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class, new GStringImpl(new Object[]{value}, new String[]{"could not find token for ", ""})));
        }
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, Token.class, tokenID, value, -1, -1));
    }

    public void range(Range range) {
        if (ScriptBytecodeAdapter.compareEqual(range, null)) {
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, IllegalArgumentException.class, "Null: range"));
        }
        IndyInterface.bootstrap("invoke", "leftShift", 0, this.expression, IndyInterface.bootstrap("init", "<init>", 0, RangeExpression.class, IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "from", 0, range)), IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "to", 0, range)), true));
    }

    public void switchStatement(@DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        public final class _switchStatement_closure20
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _switchStatement_closure20(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite switchExpression = IndyInterface.bootstrap("invoke", "head", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this));
                CallSite caseStatements = IndyInterface.bootstrap("invoke", "tail", 0, IndyInterface.bootstrap("invoke", "tail", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this)));
                CallSite defaultExpression = IndyInterface.bootstrap("invoke", "head", 0, IndyInterface.bootstrap("invoke", "tail", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this)));
                return IndyInterface.bootstrap("init", "<init>", 0, SwitchStatement.class, switchExpression, caseStatements, defaultExpression);
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _switchStatement_closure20.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "SwitchStatement", argBlock, new _switchStatement_closure20(this, this));
    }

    public void mapEntry(Map map) {
        public final class _mapEntry_closure21
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _mapEntry_closure21(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), IndyInterface.bootstrap("init", "<init>", 0, MapEntryExpression.class, IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "key", 0, it)), IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "value", 0, it))));
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _mapEntry_closure21.class) {
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
        IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("invoke", "entrySet", 0, map), new _mapEntry_closure21(this, this));
    }

    /*
     * WARNING - void declaration
     */
    public void fieldNode(String name, int modifiers, Class type, Class owner, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        void var3_3;
        Reference<String> name2 = new Reference<String>(name);
        Reference<Integer> modifiers2 = new Reference<Integer>(modifiers);
        Reference<void> type2 = new Reference<void>(var3_3);
        Reference<Class> owner2 = new Reference<Class>(owner);
        public final class _fieldNode_closure22
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference owner;
            private /* synthetic */ Reference type;
            private /* synthetic */ Reference modifiers;
            private /* synthetic */ Reference name;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _fieldNode_closure22(Object _outerInstance, Object _thisObject, Reference owner, Reference type, Reference modifiers, Reference name) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                this.owner = reference4 = owner;
                this.type = reference3 = type;
                this.modifiers = reference2 = modifiers;
                this.name = reference = name;
            }

            public Object doCall(Object it) {
                CallSite annotations = null;
                if (ScriptBytecodeAdapter.compareGreaterThan(IndyInterface.bootstrap("invoke", "size", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this)), 1)) {
                    annotations = IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), true);
                    IndyInterface.bootstrap("invoke", "remove", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), true);
                }
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.owner.get())));
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.type.get())));
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()));
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("cast", "()", 0, this.name.get()));
                CallSite result = IndyInterface.bootstrap("init", "<init>", 16, FieldNode.class, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{IndyInterface.bootstrap("invoke", "enforceConstraints", 2, this, "fieldNode", ScriptBytecodeAdapter.createList(new Object[]{String.class, Integer.class, ClassNode.class, ClassNode.class, Expression.class}))}, new int[]{0}));
                if (IndyInterface.bootstrap("cast", "()", 0, annotations) != false) {
                    IndyInterface.bootstrap("invoke", "addAnnotations", 0, result, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, annotations));
                }
                return result;
            }

            @Override
            @Generated
            public Class getOwner() {
                return IndyInterface.bootstrap("cast", "()", 0, this.owner.get());
            }

            @Generated
            public Class getType() {
                return IndyInterface.bootstrap("cast", "()", 0, this.type.get());
            }

            @Generated
            public int getModifiers() {
                return (Integer)((Object)IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()));
            }

            @Generated
            public String getName() {
                return IndyInterface.bootstrap("cast", "()", 0, this.name.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _fieldNode_closure22.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "FieldNode", argBlock, new _fieldNode_closure22(this, this, owner2, type2, modifiers2, name2));
    }

    public void innerClass(String name, int modifiers, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<String> name2 = new Reference<String>(name);
        Reference<Integer> modifiers2 = new Reference<Integer>(modifiers);
        public final class _innerClass_closure23
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference name;
            private /* synthetic */ Reference modifiers;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _innerClass_closure23(Object _outerInstance, Object _thisObject, Reference name, Reference modifiers) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.name = reference2 = name;
                this.modifiers = reference = modifiers;
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("init", "<init>", 0, InnerClassNode.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false), IndyInterface.bootstrap("cast", "()", 0, this.name.get()), IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), true), ScriptBytecodeAdapter.createPojoWrapper((ClassNode[])IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 2)), ClassNode[].class), ClassNode[].class), ScriptBytecodeAdapter.createPojoWrapper((MixinNode[])IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3)), MixinNode[].class), MixinNode[].class));
            }

            @Generated
            public String getName() {
                return IndyInterface.bootstrap("cast", "()", 0, this.name.get());
            }

            @Generated
            public int getModifiers() {
                return (Integer)((Object)IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()));
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _innerClass_closure23.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "InnerClassNode", argBlock, new _innerClass_closure23(this, this, name2, modifiers2));
    }

    /*
     * WARNING - void declaration
     */
    public void propertyNode(String name, int modifiers, Class type, Class owner, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        void var3_3;
        Reference<String> name2 = new Reference<String>(name);
        Reference<Integer> modifiers2 = new Reference<Integer>(modifiers);
        Reference<void> type2 = new Reference<void>(var3_3);
        Reference<Class> owner2 = new Reference<Class>(owner);
        public final class _propertyNode_closure24
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference name;
            private /* synthetic */ Reference modifiers;
            private /* synthetic */ Reference type;
            private /* synthetic */ Reference owner;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _propertyNode_closure24(Object _outerInstance, Object _thisObject, Reference name, Reference modifiers, Reference type, Reference owner) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                this.name = reference4 = name;
                this.modifiers = reference3 = modifiers;
                this.type = reference2 = type;
                this.owner = reference = owner;
            }

            public Object doCall(Object it) {
                CallSite annotations = null;
                if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "isAssignableFrom", 0, List.class, IndyInterface.bootstrap("invoke", "getClass", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), -1)))) != false) {
                    annotations = IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), -1);
                    IndyInterface.bootstrap("invoke", "remove", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), IndyInterface.bootstrap("invoke", "minus", 0, IndyInterface.bootstrap("invoke", "size", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this)), true));
                }
                CallSite result = IndyInterface.bootstrap("init", "<init>", 0, PropertyNode.class, IndyInterface.bootstrap("cast", "()", 0, this.name.get()), IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()), IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.type.get())), IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.owner.get())), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), true), IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 2));
                if (IndyInterface.bootstrap("cast", "()", 0, annotations) != false) {
                    IndyInterface.bootstrap("invoke", "addAnnotations", 0, result, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, annotations));
                }
                return result;
            }

            @Generated
            public String getName() {
                return IndyInterface.bootstrap("cast", "()", 0, this.name.get());
            }

            @Generated
            public int getModifiers() {
                return (Integer)((Object)IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()));
            }

            @Generated
            public Class getType() {
                return IndyInterface.bootstrap("cast", "()", 0, this.type.get());
            }

            @Override
            @Generated
            public Class getOwner() {
                return IndyInterface.bootstrap("cast", "()", 0, this.owner.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _propertyNode_closure24.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "PropertyNode", argBlock, new _propertyNode_closure24(this, this, name2, modifiers2, type2, owner2));
    }

    /*
     * WARNING - void declaration
     */
    public void staticMethodCall(Class target, String name, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        void var2_2;
        Reference<Class> target2 = new Reference<Class>(target);
        Reference<void> name2 = new Reference<void>(var2_2);
        public final class _staticMethodCall_closure25
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference name;
            private /* synthetic */ Reference target;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _staticMethodCall_closure25(Object _outerInstance, Object _thisObject, Reference name, Reference target) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.name = reference2 = name;
                this.target = reference = target;
            }

            public Object doCall(Object it) {
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("cast", "()", 0, this.name.get()));
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("cast", "()", 0, this.target.get())));
                return IndyInterface.bootstrap("init", "<init>", 16, StaticMethodCallExpression.class, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{IndyInterface.bootstrap("invoke", "enforceConstraints", 2, this, "staticMethodCall", ScriptBytecodeAdapter.createList(new Object[]{ClassNode.class, String.class, Expression.class}))}, new int[]{0}));
            }

            @Generated
            public String getName() {
                return IndyInterface.bootstrap("cast", "()", 0, this.name.get());
            }

            @Generated
            public Class getTarget() {
                return IndyInterface.bootstrap("cast", "()", 0, this.target.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _staticMethodCall_closure25.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "StaticMethodCallExpression", argBlock, new _staticMethodCall_closure25(this, this, name2, target2));
    }

    public void staticMethodCall(MethodClosure target, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<MethodClosure> target2 = new Reference<MethodClosure>(target);
        public final class _staticMethodCall_closure26
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference target;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _staticMethodCall_closure26(Object _outerInstance, Object _thisObject, Reference target) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.target = reference = target;
            }

            public Object doCall(Object it) {
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("getProperty", "method", 4, IndyInterface.bootstrap("cast", "()", 0, this.target.get())));
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("invoke", "makeWithoutCaching", 0, ClassHelper.class, IndyInterface.bootstrap("getProperty", "class", 0, IndyInterface.bootstrap("getProperty", "owner", 4, IndyInterface.bootstrap("cast", "()", 0, this.target.get()))), false));
                return IndyInterface.bootstrap("init", "<init>", 16, StaticMethodCallExpression.class, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{IndyInterface.bootstrap("invoke", "enforceConstraints", 2, this, "staticMethodCall", ScriptBytecodeAdapter.createList(new Object[]{ClassNode.class, String.class, Expression.class}))}, new int[]{0}));
            }

            @Generated
            public MethodClosure getTarget() {
                return IndyInterface.bootstrap("cast", "()", 0, this.target.get());
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _staticMethodCall_closure26.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "StaticMethodCallExpression", argBlock, new _staticMethodCall_closure26(this, this, target2));
    }

    public void constructor(int modifiers, @DelegatesTo(value=AstSpecificationCompiler.class) Closure argBlock) {
        Reference<Integer> modifiers2 = new Reference<Integer>(modifiers);
        public final class _constructor_closure27
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference modifiers;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _constructor_closure27(Object _outerInstance, Object _thisObject, Reference modifiers) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.modifiers = reference = modifiers;
            }

            public Object doCall(Object it) {
                CallSite annotations = null;
                if (ScriptBytecodeAdapter.compareGreaterThan(IndyInterface.bootstrap("invoke", "size", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this)), 3)) {
                    annotations = IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3);
                    IndyInterface.bootstrap("invoke", "remove", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), 3);
                }
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "expression", 12, this), false, IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()));
                CallSite result = IndyInterface.bootstrap("init", "<init>", 16, ConstructorNode.class, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{IndyInterface.bootstrap("invoke", "enforceConstraints", 2, this, "constructor", ScriptBytecodeAdapter.createList(new Object[]{Integer.class, Parameter[].class, ClassNode[].class, Statement.class}))}, new int[]{0}));
                if (IndyInterface.bootstrap("cast", "()", 0, annotations) != false) {
                    IndyInterface.bootstrap("invoke", "addAnnotations", 0, result, IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class, annotations));
                }
                return result;
            }

            @Generated
            public int getModifiers() {
                return (Integer)((Object)IndyInterface.bootstrap("cast", "()", 0, this.modifiers.get()));
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _constructor_closure27.class) {
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
        IndyInterface.bootstrap("invoke", "captureAndCreateNode", 2, this, "ConstructorNode", argBlock, new _constructor_closure27(this, this, modifiers2));
    }

    @Generated
    public void importNode(Class target) {
        this.importNode(target, null);
    }

    @Generated
    public void breakStatement() {
        this.breakStatement(null);
    }

    @Generated
    public void continueStatement() {
        this.continueStatement(null);
    }

    @Generated
    public void dynamicVariable(String variable) {
        this.dynamicVariable(variable, false);
    }

    @Generated
    public void parameter(Map<String, Class> args) {
        this.parameter(args, null);
    }

    @Generated
    public void genericsType(Class type) {
        Reference<Class> type2 = new Reference<Class>(type);
        this.genericsType(type2.get(), null);
    }

    @Generated
    public void annotation(Class target) {
        Reference<Class> target2 = new Reference<Class>(target);
        this.annotation(target2.get(), null);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != AstSpecificationCompiler.class) {
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
}

