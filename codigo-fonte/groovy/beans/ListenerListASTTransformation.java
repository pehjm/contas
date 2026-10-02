/*
 * Decompiled with CFR 0.152.
 */
package groovy.beans;

import groovy.beans.ListenerList;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovyjarjarasm.asm.Opcodes;
import java.beans.Transient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

@GroovyASTTransformation(phase=CompilePhase.CANONICALIZATION)
public class ListenerListASTTransformation
implements ASTTransformation,
Opcodes,
GroovyObject {
    private static final Class MY_CLASS;
    private static final ClassNode COLLECTION_TYPE;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public ListenerListASTTransformation() {
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        CallSite callSite;
        Reference<SourceUnit> source2 = new Reference<SourceUnit>(source);
        if (!(IndyInterface.bootstrap("invoke", "getAt", 0, nodes, false) instanceof AnnotationNode) || !(IndyInterface.bootstrap("invoke", "getAt", 0, nodes, true) instanceof AnnotatedNode)) {
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, RuntimeException.class, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "class", 0, IndyInterface.bootstrap("getProperty", "node", 12, this)), IndyInterface.bootstrap("getProperty", "class", 0, IndyInterface.bootstrap("getProperty", "parent", 12, this))}, new String[]{"Internal error: wrong types: ", " / ", ""})));
        }
        Reference<CallSite> node = new Reference<CallSite>(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, nodes, false)));
        Reference<CallSite> field = new Reference<CallSite>(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, nodes, true)));
        Reference<CallSite> declaringClass = new Reference<CallSite>(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "declaringClass", 0, IndyInterface.bootstrap("invoke", "getAt", 0, nodes, true))));
        CallSite parentClass = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "type", 0, (FieldNode)((Object)field.get())));
        boolean isCollection = DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isDerivedFrom", 0, parentClass, COLLECTION_TYPE)) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "implementsInterface", 0, parentClass, COLLECTION_TYPE));
        if (!isCollection) {
            IndyInterface.bootstrap("invoke", "addError", 0, ListenerListASTTransformation.class, (AnnotationNode)((Object)node.get()), source2.get(), IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, "@", IndyInterface.bootstrap("getProperty", "name", 0, MY_CLASS)), " can only annotate collection properties."));
            return;
        }
        Reference<CallSite> types = new Reference<CallSite>(IndyInterface.bootstrap("getProperty", "genericsTypes", 0, IndyInterface.bootstrap("getProperty", "type", 0, (FieldNode)((Object)field.get()))));
        if (IndyInterface.bootstrap("cast", "()", 0, types.get()) == false) {
            IndyInterface.bootstrap("invoke", "addError", 0, ListenerListASTTransformation.class, (AnnotationNode)((Object)node.get()), source2.get(), IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, "@", IndyInterface.bootstrap("getProperty", "name", 0, MY_CLASS)), " fields must have a generic type."));
            return;
        }
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "wildcard", 0, IndyInterface.bootstrap("invoke", "getAt", 0, types.get(), false))) != false) {
            IndyInterface.bootstrap("invoke", "addError", 0, ListenerListASTTransformation.class, (AnnotationNode)((Object)node.get()), source2.get(), IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, "@", IndyInterface.bootstrap("getProperty", "name", 0, MY_CLASS)), " fields with generic wildcards not yet supported."));
            return;
        }
        CallSite listener = IndyInterface.bootstrap("getProperty", "type", 0, IndyInterface.bootstrap("invoke", "getAt", 0, types.get(), false));
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "initialValueExpression", 0, (FieldNode)((Object)field.get()))) == false) {
            CallSite callSite2 = IndyInterface.bootstrap("init", "<init>", 0, ListExpression.class);
            ScriptBytecodeAdapter.setProperty(callSite2, null, (FieldNode)((Object)field.get()), "initialValueExpression");
        }
        CallSite name = IndyInterface.bootstrap("cast", "()", 0, callSite = IndyInterface.bootstrap("getProperty", "value", 1, IndyInterface.bootstrap("invoke", "getMember", 0, (AnnotationNode)((Object)node.get()), "name"))) != false ? callSite : IndyInterface.bootstrap("getProperty", "nameWithoutPackage", 0, listener);
        public final class _visit_closure1
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _visit_closure1(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(MethodNode m) {
                return DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isPublic", 0, m)) && IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "isSynthetic", 0, m)) == false && IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "isStatic", 0, m)) == false;
            }

            @Generated
            public Object call(MethodNode m) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, m);
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
        CallSite fireList = IndyInterface.bootstrap("invoke", "findAll", 0, IndyInterface.bootstrap("getProperty", "methods", 0, listener), new _visit_closure1(this, this));
        CallSite callSite3 = IndyInterface.bootstrap("getProperty", "value", 1, IndyInterface.bootstrap("invoke", "getMember", 0, (AnnotationNode)((Object)node.get()), "synchronize"));
        Reference<Object> synchronize = new Reference<Object>(IndyInterface.bootstrap("cast", "()", 0, callSite3) != false ? callSite3 : Boolean.valueOf(false));
        IndyInterface.bootstrap("invoke", "addAddListener", 2, this, source2.get(), (AnnotationNode)((Object)node.get()), (ClassNode)((Object)declaringClass.get()), (FieldNode)((Object)field.get()), listener, name, synchronize.get());
        IndyInterface.bootstrap("invoke", "addRemoveListener", 2, this, source2.get(), (AnnotationNode)((Object)node.get()), (ClassNode)((Object)declaringClass.get()), (FieldNode)((Object)field.get()), listener, name, synchronize.get());
        IndyInterface.bootstrap("invoke", "addGetListeners", 2, this, source2.get(), (AnnotationNode)((Object)node.get()), (ClassNode)((Object)declaringClass.get()), (FieldNode)((Object)field.get()), listener, name, synchronize.get());
        public final class _visit_closure2
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference source;
            private /* synthetic */ Reference node;
            private /* synthetic */ Reference declaringClass;
            private /* synthetic */ Reference field;
            private /* synthetic */ Reference types;
            private /* synthetic */ Reference synchronize;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _visit_closure2(Object _outerInstance, Object _thisObject, Reference source, Reference node, Reference declaringClass, Reference field, Reference types, Reference synchronize) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                Reference reference5;
                Reference reference6;
                this.source = reference6 = source;
                this.node = reference5 = node;
                this.declaringClass = reference4 = declaringClass;
                this.field = reference3 = field;
                this.types = reference2 = types;
                this.synchronize = reference = synchronize;
            }

            public Object doCall(MethodNode method) {
                return IndyInterface.bootstrap("invoke", "addFireMethods", 2, this, IndyInterface.bootstrap("cast", "()", 0, this.source.get()), IndyInterface.bootstrap("cast", "()", 0, this.node.get()), IndyInterface.bootstrap("cast", "()", 0, this.declaringClass.get()), IndyInterface.bootstrap("cast", "()", 0, this.field.get()), this.types.get(), this.synchronize.get(), method);
            }

            @Generated
            public Object call(MethodNode method) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, method);
            }

            @Generated
            public SourceUnit getSource() {
                return IndyInterface.bootstrap("cast", "()", 0, this.source.get());
            }

            @Generated
            public AnnotationNode getNode() {
                return IndyInterface.bootstrap("cast", "()", 0, this.node.get());
            }

            @Generated
            public ClassNode getDeclaringClass() {
                return IndyInterface.bootstrap("cast", "()", 0, this.declaringClass.get());
            }

            @Generated
            public FieldNode getField() {
                return IndyInterface.bootstrap("cast", "()", 0, this.field.get());
            }

            @Generated
            public Object getTypes() {
                return this.types.get();
            }

            @Generated
            public Object getSynchronize() {
                return this.synchronize.get();
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
        IndyInterface.bootstrap("invoke", "each", 0, fireList, new _visit_closure2(this, this, source2, node, declaringClass, field, types, synchronize));
    }

    private static Object addError(AnnotationNode node, SourceUnit source, String message) {
        return IndyInterface.bootstrap("invoke", "addError", 0, IndyInterface.bootstrap("getProperty", "errorCollector", 0, source), IndyInterface.bootstrap("init", "<init>", 0, SyntaxErrorMessage.class, IndyInterface.bootstrap("init", "<init>", 0, SyntaxException.class, message, IndyInterface.bootstrap("getProperty", "lineNumber", 0, node), IndyInterface.bootstrap("getProperty", "columnNumber", 0, node)), source));
    }

    public void addAddListener(SourceUnit source, AnnotationNode node, ClassNode declaringClass, FieldNode field, ClassNode listener, String name, Object synchronize) {
        CallSite methodModifiers = IndyInterface.bootstrap("cast", "()", 0, synchronize) != false ? IndyInterface.bootstrap("invoke", "or", 0, IndyInterface.bootstrap("getProperty", "ACC_PUBLIC", 12, this), IndyInterface.bootstrap("getProperty", "ACC_SYNCHRONIZED", 12, this)) : IndyInterface.bootstrap("getProperty", "ACC_PUBLIC", 12, this);
        CallSite methodReturnType = IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("getProperty", "TYPE", 0, Void.class));
        GStringImpl methodName = new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "capitalize", 0, name)}, new String[]{"add", ""});
        CallSite cn = IndyInterface.bootstrap("invoke", "makeWithoutCaching", 0, ClassHelper.class, IndyInterface.bootstrap("getProperty", "name", 0, listener));
        ClassNode classNode = listener;
        ScriptBytecodeAdapter.setProperty(classNode, null, cn, "redirect");
        Parameter[] methodParameter = (Parameter[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "param", 0, GeneralUtils.class, cn, "listener")}), Parameter[].class);
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasMethod", 0, declaringClass, methodName, methodParameter)) != false) {
            IndyInterface.bootstrap("invoke", "addError", 0, ListenerListASTTransformation.class, node, source, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "name", 0, MY_CLASS), IndyInterface.bootstrap("getProperty", "name", 0, declaringClass), methodName}, new String[]{"Conflict using @", ". Class ", " already has method ", ""}));
            return;
        }
        CallSite block = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, BlockStatement.class));
        IndyInterface.bootstrap("invoke", "addStatements", 0, block, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "ifS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "equalsNullX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, "listener")), IndyInterface.bootstrap("getProperty", "RETURN_NULL_OR_VOID", 0, ReturnStatement.class)), IndyInterface.bootstrap("invoke", "ifS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "equalsNullX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field))), IndyInterface.bootstrap("invoke", "assignS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field)), IndyInterface.bootstrap("init", "<init>", 0, ListExpression.class))), IndyInterface.bootstrap("invoke", "stmt", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "callX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field)), IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, "add"), IndyInterface.bootstrap("invoke", "args", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, "listener"))))}));
        IndyInterface.bootstrap("invoke", "addMethod", 0, declaringClass, IndyInterface.bootstrap("init", "<init>", 0, MethodNode.class, methodName, methodModifiers, methodReturnType, methodParameter, ScriptBytecodeAdapter.createPojoWrapper((ClassNode[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[0]), ClassNode[].class), ClassNode[].class), block));
    }

    public void addRemoveListener(SourceUnit source, AnnotationNode node, ClassNode declaringClass, FieldNode field, ClassNode listener, String name, Object synchronize) {
        CallSite methodModifiers = IndyInterface.bootstrap("cast", "()", 0, synchronize) != false ? IndyInterface.bootstrap("invoke", "or", 0, IndyInterface.bootstrap("getProperty", "ACC_PUBLIC", 12, this), IndyInterface.bootstrap("getProperty", "ACC_SYNCHRONIZED", 12, this)) : IndyInterface.bootstrap("getProperty", "ACC_PUBLIC", 12, this);
        CallSite methodReturnType = IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("getProperty", "TYPE", 0, Void.class));
        GStringImpl methodName = new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "capitalize", 0, name)}, new String[]{"remove", ""});
        CallSite cn = IndyInterface.bootstrap("invoke", "makeWithoutCaching", 0, ClassHelper.class, IndyInterface.bootstrap("getProperty", "name", 0, listener));
        ClassNode classNode = listener;
        ScriptBytecodeAdapter.setProperty(classNode, null, cn, "redirect");
        Parameter[] methodParameter = (Parameter[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "param", 0, GeneralUtils.class, cn, "listener")}), Parameter[].class);
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasMethod", 0, declaringClass, methodName, methodParameter)) != false) {
            IndyInterface.bootstrap("invoke", "addError", 0, ListenerListASTTransformation.class, node, source, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "name", 0, MY_CLASS), IndyInterface.bootstrap("getProperty", "name", 0, declaringClass), methodName}, new String[]{"Conflict using @", ". Class ", " already has method ", ""}));
            return;
        }
        CallSite block = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, BlockStatement.class));
        IndyInterface.bootstrap("invoke", "addStatements", 0, block, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "ifS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "equalsNullX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, "listener")), IndyInterface.bootstrap("getProperty", "RETURN_NULL_OR_VOID", 0, ReturnStatement.class)), IndyInterface.bootstrap("invoke", "ifS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "equalsNullX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field))), IndyInterface.bootstrap("invoke", "assignS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field)), IndyInterface.bootstrap("init", "<init>", 0, ListExpression.class))), IndyInterface.bootstrap("invoke", "stmt", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "callX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field)), IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, "remove"), IndyInterface.bootstrap("invoke", "args", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, "listener"))))}));
        IndyInterface.bootstrap("invoke", "addMethod", 0, declaringClass, IndyInterface.bootstrap("init", "<init>", 0, MethodNode.class, methodName, methodModifiers, methodReturnType, methodParameter, ScriptBytecodeAdapter.createPojoWrapper((ClassNode[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[0]), ClassNode[].class), ClassNode[].class), block));
    }

    public void addGetListeners(SourceUnit source, AnnotationNode node, ClassNode declaringClass, FieldNode field, ClassNode listener, String name, Object synchronize) {
        CallSite methodModifiers = IndyInterface.bootstrap("cast", "()", 0, synchronize) != false ? IndyInterface.bootstrap("invoke", "or", 0, IndyInterface.bootstrap("getProperty", "ACC_PUBLIC", 12, this), IndyInterface.bootstrap("getProperty", "ACC_SYNCHRONIZED", 12, this)) : IndyInterface.bootstrap("getProperty", "ACC_PUBLIC", 12, this);
        CallSite methodReturnType = IndyInterface.bootstrap("invoke", "makeArray", 0, listener);
        GStringImpl methodName = new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "capitalize", 0, name)}, new String[]{"get", "s"});
        Parameter[] methodParameter = (Parameter[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[0]), Parameter[].class);
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasMethod", 0, declaringClass, methodName, methodParameter)) != false) {
            IndyInterface.bootstrap("invoke", "addError", 0, ListenerListASTTransformation.class, node, source, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "name", 0, MY_CLASS), IndyInterface.bootstrap("getProperty", "name", 0, declaringClass), methodName}, new String[]{"Conflict using @", ". Class ", " already has method ", ""}));
            return;
        }
        CallSite block = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, BlockStatement.class));
        IndyInterface.bootstrap("invoke", "addStatements", 0, block, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "declS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "localVarX", 0, GeneralUtils.class, "__result", IndyInterface.bootstrap("invoke", "dynamicType", 0, ClassHelper.class)), IndyInterface.bootstrap("init", "<init>", 0, ListExpression.class)), IndyInterface.bootstrap("invoke", "ifS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "notNullX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field))), IndyInterface.bootstrap("invoke", "stmt", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "callX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, "__result"), IndyInterface.bootstrap("invoke", "constX", 0, GeneralUtils.class, "addAll"), IndyInterface.bootstrap("invoke", "args", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field)))))), IndyInterface.bootstrap("invoke", "returnS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "castX", 0, GeneralUtils.class, methodReturnType, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, "__result")))}));
        IndyInterface.bootstrap("invoke", "addMethod", 0, declaringClass, IndyInterface.bootstrap("init", "<init>", 0, MethodNode.class, methodName, methodModifiers, methodReturnType, methodParameter, ScriptBytecodeAdapter.createPojoWrapper((ClassNode[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[0]), ClassNode[].class), ClassNode[].class), block));
    }

    public void addFireMethods(SourceUnit source, AnnotationNode node, ClassNode declaringClass, FieldNode field, GenericsType[] types, boolean synchronize, MethodNode method) {
        CallSite methodReturnType = IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, IndyInterface.bootstrap("getProperty", "TYPE", 0, Void.class));
        GStringImpl methodName = new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "capitalize", 0, IndyInterface.bootstrap("getProperty", "name", 0, method))}, new String[]{"fire", ""});
        CallSite methodModifiers = synchronize ? IndyInterface.bootstrap("invoke", "or", 0, IndyInterface.bootstrap("getProperty", "ACC_PUBLIC", 12, this), IndyInterface.bootstrap("getProperty", "ACC_SYNCHRONIZED", 12, this)) : IndyInterface.bootstrap("getProperty", "ACC_PUBLIC", 12, this);
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasMethod", 0, declaringClass, methodName, IndyInterface.bootstrap("getProperty", "parameters", 0, method))) != false) {
            IndyInterface.bootstrap("invoke", "addError", 0, ListenerListASTTransformation.class, node, source, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "name", 0, MY_CLASS), IndyInterface.bootstrap("getProperty", "name", 0, declaringClass), methodName}, new String[]{"Conflict using @", ". Class ", " already has method ", ""}));
            return;
        }
        CallSite methodArgs = IndyInterface.bootstrap("invoke", "args", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "parameters", 0, method));
        CallSite block = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, BlockStatement.class));
        CallSite listenerListType = IndyInterface.bootstrap("getProperty", "plainNodeReference", 0, IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, ArrayList.class));
        GenericsType[] genericsTypeArray = types;
        ScriptBytecodeAdapter.setProperty(genericsTypeArray, null, listenerListType, "genericsTypes");
        IndyInterface.bootstrap("invoke", "addStatements", 0, block, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "ifS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "notNullX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field))), IndyInterface.bootstrap("init", "<init>", 0, BlockStatement.class, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "declS", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "localVarX", 0, GeneralUtils.class, "__list", listenerListType), IndyInterface.bootstrap("invoke", "ctorX", 0, GeneralUtils.class, listenerListType, IndyInterface.bootstrap("invoke", "args", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, IndyInterface.bootstrap("getProperty", "name", 0, field))))), IndyInterface.bootstrap("init", "<init>", 0, ForStatement.class, IndyInterface.bootstrap("invoke", "param", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "dynamicType", 0, ClassHelper.class), "listener"), IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, "__list"), IndyInterface.bootstrap("init", "<init>", 0, BlockStatement.class, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "stmt", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "callX", 0, GeneralUtils.class, IndyInterface.bootstrap("invoke", "varX", 0, GeneralUtils.class, "listener"), IndyInterface.bootstrap("getProperty", "name", 0, method), methodArgs))}), IndyInterface.bootstrap("init", "<init>", 0, VariableScope.class)))}), IndyInterface.bootstrap("init", "<init>", 0, VariableScope.class)))}));
        public final class _addFireMethods_closure3
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _addFireMethods_closure3(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite paramType = IndyInterface.bootstrap("invoke", "getWrapper", 0, ClassHelper.class, IndyInterface.bootstrap("getProperty", "type", 0, it));
                CallSite cn = IndyInterface.bootstrap("getProperty", "plainNodeReference", 0, paramType);
                CallSite callSite = paramType;
                ScriptBytecodeAdapter.setProperty(callSite, null, cn, "redirect");
                return IndyInterface.bootstrap("invoke", "param", 0, GeneralUtils.class, cn, IndyInterface.bootstrap("getProperty", "name", 0, it));
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _addFireMethods_closure3.class) {
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
        CallSite params = IndyInterface.bootstrap("invoke", "collect", 0, IndyInterface.bootstrap("getProperty", "parameters", 0, method), new _addFireMethods_closure3(this, this));
        IndyInterface.bootstrap("invoke", "addMethod", 0, declaringClass, methodName, methodModifiers, methodReturnType, ScriptBytecodeAdapter.createPojoWrapper((Parameter[])IndyInterface.bootstrap("invoke", "asType", 0, params, Parameter[].class), Parameter[].class), ScriptBytecodeAdapter.createPojoWrapper((ClassNode[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[0]), ClassNode[].class), ClassNode[].class), block);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ListenerListASTTransformation.class) {
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
        Class<ListenerList> clazz;
        MY_CLASS = clazz = ListenerList.class;
        CallSite callSite = IndyInterface.bootstrap("invoke", "make", 0, ClassHelper.class, Collection.class);
        COLLECTION_TYPE = IndyInterface.bootstrap("cast", "()", 0, callSite);
    }
}

