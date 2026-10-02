/*
 * Decompiled with CFR 0.152.
 */
package groovy.xml;

import groovy.lang.Buildable;
import groovy.lang.Closure;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.xml.streamingmarkupsupport.AbstractStreamingBuilder;
import groovy.xml.streamingmarkupsupport.BaseMarkupBuilder;
import groovy.xml.streamingmarkupsupport.StreamingMarkupWriter;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class StreamingMarkupBuilder
extends AbstractStreamingBuilder {
    private boolean useDoubleQuotes;
    private boolean expandEmptyElements;
    private Object pendingStack;
    private Object commentClosure;
    private Object piClosure;
    private Object declarationClosure;
    private Object noopClosure;
    private Object unescapedClosure;
    private Object tagClosure;
    private Object builder;
    private Object encoding;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;

    public StreamingMarkupBuilder() {
        boolean bl;
        boolean bl2;
        this.useDoubleQuotes = bl2 = false;
        this.expandEmptyElements = bl = false;
        List list = ScriptBytecodeAdapter.createList(new Object[0]);
        this.pendingStack = list;
        _closure1 _closure16 = new _closure1(this, this);
        this.commentClosure = _closure16;
        _closure2 _closure26 = new _closure2(this, this);
        this.piClosure = _closure26;
        _closure3 _closure35 = new _closure3(this, this);
        this.declarationClosure = _closure35;
        _closure4 _closure42 = new _closure4(this, this);
        this.noopClosure = _closure42;
        _closure5 _closure52 = new _closure5(this, this);
        this.unescapedClosure = _closure52;
        _closure6 _closure62 = new _closure6(this, this);
        this.tagClosure = _closure62;
        Object var10_10 = null;
        this.builder = var10_10;
        Object var11_11 = null;
        this.encoding = var11_11;
        IndyInterface.bootstrap("invoke", "putAll", 0, IndyInterface.bootstrap("getProperty", "specialTags", 12, this), ScriptBytecodeAdapter.createMap(new Object[]{"yield", this.noopClosure, "yieldUnescaped", this.unescapedClosure, "xmlDeclaration", this.declarationClosure, "comment", this.commentClosure, "pi", this.piClosure}));
        Map nsSpecificTags = ScriptBytecodeAdapter.createMap(new Object[]{":", ScriptBytecodeAdapter.createList(new Object[]{this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0])}), "http://www.w3.org/XML/1998/namespace", ScriptBytecodeAdapter.createList(new Object[]{this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0])}), "http://www.codehaus.org/Groovy/markup/keywords", ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("getProperty", "badTagClosure", 12, this), this.tagClosure, IndyInterface.bootstrap("getProperty", "specialTags", 12, this)})});
        CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, BaseMarkupBuilder.class, nsSpecificTags);
        this.builder = callSite;
    }

    public Object getQt() {
        return this.useDoubleQuotes ? "\"" : "'";
    }

    public Object bind(Object closure) {
        Reference<CallSite> boundClosure = new Reference<CallSite>(IndyInterface.bootstrap("invoke", "bind", 0, this.builder, closure));
        Reference<Object> enc = new Reference<Object>(this.encoding);
        public final class _bind_closure7
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference enc;
            private /* synthetic */ Reference boundClosure;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _bind_closure7(Object _outerInstance, Object _thisObject, Reference enc, Reference boundClosure) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.enc = reference2 = enc;
                this.boundClosure = reference = boundClosure;
            }

            public Object doCall(Object out) {
                out = IndyInterface.bootstrap("init", "<init>", 0, StreamingMarkupWriter.class, out, this.enc.get(), IndyInterface.bootstrap("getProperty", "useDoubleQuotes", 12, this));
                Object object = out;
                ScriptBytecodeAdapter.setProperty(object, null, this.boundClosure.get(), "trigger");
                return IndyInterface.bootstrap("invoke", "flush", 0, out);
            }

            @Generated
            public Object getEnc() {
                return this.enc.get();
            }

            @Generated
            public Object getBoundClosure() {
                return this.boundClosure.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _bind_closure7.class) {
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
        return IndyInterface.bootstrap("invoke", "asWritable", 0, new _bind_closure7(this, this, enc, boundClosure));
    }

    public Object bindNode(Object node) {
        Reference<Object> node2 = new Reference<Object>(node);
        public final class _bindNode_closure8
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference node;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _bindNode_closure8(Object _outerInstance, Object _thisObject, Reference node) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.node = reference = node;
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("getProperty", "out", 12, this), this.node.get());
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
                if (this.getClass() != _bindNode_closure8.class) {
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
        return IndyInterface.bootstrap("invoke", "bind", 2, this, new _bindNode_closure8(this, this, node2));
    }

    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != StreamingMarkupBuilder.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    public static /* synthetic */ MethodHandles.Lookup $getLookup() {
        return MethodHandles.lookup();
    }

    @Generated
    public boolean getUseDoubleQuotes() {
        return this.useDoubleQuotes;
    }

    @Generated
    public boolean isUseDoubleQuotes() {
        return this.useDoubleQuotes;
    }

    @Generated
    public void setUseDoubleQuotes(boolean bl) {
        this.useDoubleQuotes = bl;
    }

    @Generated
    public boolean getExpandEmptyElements() {
        return this.expandEmptyElements;
    }

    @Generated
    public boolean isExpandEmptyElements() {
        return this.expandEmptyElements;
    }

    @Generated
    public void setExpandEmptyElements(boolean bl) {
        this.expandEmptyElements = bl;
    }

    @Generated
    public Object getPendingStack() {
        return this.pendingStack;
    }

    @Generated
    public void setPendingStack(Object object) {
        this.pendingStack = object;
    }

    @Generated
    public Object getCommentClosure() {
        return this.commentClosure;
    }

    @Generated
    public void setCommentClosure(Object object) {
        this.commentClosure = object;
    }

    @Generated
    public Object getPiClosure() {
        return this.piClosure;
    }

    @Generated
    public void setPiClosure(Object object) {
        this.piClosure = object;
    }

    @Generated
    public Object getDeclarationClosure() {
        return this.declarationClosure;
    }

    @Generated
    public void setDeclarationClosure(Object object) {
        this.declarationClosure = object;
    }

    @Generated
    public Object getNoopClosure() {
        return this.noopClosure;
    }

    @Generated
    public void setNoopClosure(Object object) {
        this.noopClosure = object;
    }

    @Generated
    public Object getUnescapedClosure() {
        return this.unescapedClosure;
    }

    @Generated
    public void setUnescapedClosure(Object object) {
        this.unescapedClosure = object;
    }

    @Generated
    public Object getTagClosure() {
        return this.tagClosure;
    }

    @Generated
    public void setTagClosure(Object object) {
        this.tagClosure = object;
    }

    @Override
    @Generated
    public Object getBuilder() {
        return this.builder;
    }

    @Override
    @Generated
    public void setBuilder(Object object) {
        this.builder = object;
    }

    @Generated
    public Object getEncoding() {
        return this.encoding;
    }

    @Generated
    public void setEncoding(Object object) {
        this.encoding = object;
    }

    public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
        return super.$getStaticMetaClass();
    }

    public /* synthetic */ Object super$2$getBuilder() {
        return super.getBuilder();
    }

    public /* synthetic */ void super$2$setBuilder(Object object) {
        super.setBuilder(object);
    }

    public final class _closure1
    extends Closure
    implements GeneratedClosure {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        public _closure1(Object _outerInstance, Object _thisObject) {
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, out), "<!--");
            IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "escaped", 0, out), body);
            return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, out), "-->");
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, out);
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

    public final class _closure2
    extends Closure
    implements GeneratedClosure {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        public _closure2(Object _outerInstance, Object _thisObject) {
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            Reference<Object> out2 = new Reference<Object>(out);
            public final class _closure9
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference out;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure9(Object _outerInstance, Object _thisObject, Reference out) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.out = reference = out;
                }

                public Object doCall(Object target, Object instruction) {
                    IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, this.out.get()), "<?");
                    if (instruction instanceof Map) {
                        IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, this.out.get()), target);
                        public final class _closure10
                        extends Closure
                        implements GeneratedClosure {
                            private static /* synthetic */ ClassInfo $staticClassInfo;
                            public static transient /* synthetic */ boolean __$stMC;

                            public _closure10(Object _outerInstance, Object _thisObject) {
                                super(_outerInstance, _thisObject);
                            }

                            public Object doCall(Object value) {
                                CallSite valueStr = IndyInterface.bootstrap("invoke", "toString", 0, value);
                                return DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "contains", 0, valueStr, "'")) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("getProperty", "useDoubleQuotes", 12, this)) && IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "contains", 0, valueStr, "\"")) == false;
                            }

                            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                if (this.getClass() != _closure10.class) {
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
                        IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, this.out.get()), IndyInterface.bootstrap("invoke", "toMapStringClosure", 2, this, instruction, new _closure10(this, this.getThisObject())));
                    } else {
                        IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, this.out.get()), new GStringImpl(new Object[]{target, instruction}, new String[]{"", " ", ""}));
                    }
                    return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, this.out.get()), "?>");
                }

                @Generated
                public Object call(Object target, Object instruction) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, target, instruction);
                }

                @Generated
                public Object getOut() {
                    return this.out.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _closure9.class) {
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
            return IndyInterface.bootstrap("invoke", "each", 0, attrs, new _closure9(this, this.getThisObject(), out2));
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            Reference<Object> out2 = new Reference<Object>(out);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, out2.get());
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

    public final class _closure3
    extends Closure
    implements GeneratedClosure {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        public _closure3(Object _outerInstance, Object _thisObject) {
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, out), IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, "<?xml version=", IndyInterface.bootstrap("getProperty", "qt", 12, this)), "1.0"), IndyInterface.bootstrap("getProperty", "qt", 12, this)));
            if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "encodingKnown", 0, out)) != false) {
                IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "escaped", 0, out), IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, " encoding=", IndyInterface.bootstrap("getProperty", "qt", 12, this)), IndyInterface.bootstrap("getProperty", "encoding", 0, out)), IndyInterface.bootstrap("getProperty", "qt", 12, this)));
            }
            return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, out), "?>\n");
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, out);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != _closure3.class) {
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

    public final class _closure4
    extends Closure
    implements GeneratedClosure {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        public _closure4(Object _outerInstance, Object _thisObject) {
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            Reference<Object> doc2 = new Reference<Object>(doc);
            Reference<Object> out2 = new Reference<Object>(out);
            public final class _closure11
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference doc;
                private /* synthetic */ Reference out;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure11(Object _outerInstance, Object _thisObject, Reference doc, Reference out) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    this.doc = reference2 = doc;
                    this.out = reference = out;
                }

                public Object doCall(Object it) {
                    if (it instanceof Closure) {
                        CallSite body1 = IndyInterface.bootstrap("invoke", "clone", 0, it);
                        Object t = this.doc.get();
                        ScriptBytecodeAdapter.setProperty(t, null, body1, "delegate");
                        return IndyInterface.bootstrap("invoke", "call", 0, body1, this.doc.get());
                    }
                    if (it instanceof Buildable) {
                        return IndyInterface.bootstrap("invoke", "build", 0, it, this.doc.get());
                    }
                    return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "escaped", 0, this.out.get()), it);
                }

                @Generated
                public Object getDoc() {
                    return this.doc.get();
                }

                @Generated
                public Object getOut() {
                    return this.out.get();
                }

                @Generated
                public Object doCall() {
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _closure11.class) {
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
            return IndyInterface.bootstrap("invoke", "each", 0, body, new _closure11(this, this.getThisObject(), doc2, out2));
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            Reference<Object> doc2 = new Reference<Object>(doc);
            Reference<Object> out2 = new Reference<Object>(out);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc2.get(), pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, out2.get());
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != _closure4.class) {
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

    public final class _closure5
    extends Closure
    implements GeneratedClosure {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        public _closure5(Object _outerInstance, Object _thisObject) {
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, out), body);
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, out);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != _closure5.class) {
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

    public final class _closure6
    extends Closure
    implements GeneratedClosure {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        public _closure6(Object _outerInstance, Object _thisObject) {
            super(_outerInstance, _thisObject);
        }

        /*
         * WARNING - void declaration
         */
        public Object doCall(Object tag, Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            void var3_3;
            Reference<Object> doc2 = new Reference<Object>(doc);
            Reference<void> pendingNamespaces2 = new Reference<void>(var3_3);
            Reference<Object> namespaces2 = new Reference<Object>(namespaces);
            Reference<Object> out2 = new Reference<Object>(out);
            boolean pendingIsDefaultNamespace = DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "containsKey", 0, pendingNamespaces2.get(), prefix)) && IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, pendingNamespaces2.get(), prefix)) == false;
            if (ScriptBytecodeAdapter.compareNotEqual(prefix, "")) {
                if (!(DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "containsKey", 0, namespaces2.get(), prefix)) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "containsKey", 0, pendingNamespaces2.get(), prefix)))) {
                    throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, GroovyRuntimeException.class, new GStringImpl(new Object[]{prefix}, new String[]{"Namespace prefix: ", " is not bound to a URI"})));
                }
                if (ScriptBytecodeAdapter.compareNotEqual(prefix, ":") && !pendingIsDefaultNamespace) {
                    tag = IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, prefix, ":"), tag);
                }
            }
            out2.set(IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "unescaped", 0, out2.get()), new GStringImpl(new Object[]{tag}, new String[]{"<", ""})));
            public final class _closure12
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference namespaces;
                private /* synthetic */ Reference pendingNamespaces;
                private /* synthetic */ Reference out;
                private /* synthetic */ Reference doc;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure12(Object _outerInstance, Object _thisObject, Reference namespaces, Reference pendingNamespaces, Reference out, Reference doc) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    Reference reference4;
                    this.namespaces = reference4 = namespaces;
                    this.pendingNamespaces = reference3 = pendingNamespaces;
                    this.out = reference2 = out;
                    this.doc = reference = doc;
                }

                public Object doCall(Object key, Object value) {
                    if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "contains", 0, key, "$")) != false) {
                        CallSite parts = IndyInterface.bootstrap("invoke", "tokenize", 0, key, "$");
                        CallSite localpart = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "contains", 0, IndyInterface.bootstrap("invoke", "getAt", 0, parts, true), "}")) != false ? IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("invoke", "tokenize", 0, IndyInterface.bootstrap("invoke", "getAt", 0, parts, true), "}"), true) : IndyInterface.bootstrap("invoke", "getAt", 0, parts, true));
                        if (DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "containsKey", 0, this.namespaces.get(), IndyInterface.bootstrap("invoke", "getAt", 0, parts, false))) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "containsKey", 0, this.pendingNamespaces.get(), IndyInterface.bootstrap("invoke", "getAt", 0, parts, false)))) {
                            key = IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "getAt", 0, parts, false), ":"), localpart);
                        } else {
                            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, GroovyRuntimeException.class, new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, parts, false), key}, new String[]{"bad attribute namespace tag: ", " in ", ""})));
                        }
                    }
                    IndyInterface.bootstrap("invoke", "leftShift", 0, this.out.get(), IndyInterface.bootstrap("invoke", "plus", 0, new GStringImpl(new Object[]{key}, new String[]{" ", "="}), IndyInterface.bootstrap("getProperty", "qt", 12, this)));
                    boolean bl = true;
                    ScriptBytecodeAdapter.setProperty(bl, null, this.out.get(), "writingAttribute");
                    IndyInterface.bootstrap("invoke", "build", 0, new GStringImpl(new Object[]{value}, new String[]{"", ""}), this.doc.get());
                    boolean bl2 = false;
                    ScriptBytecodeAdapter.setProperty(bl2, null, this.out.get(), "writingAttribute");
                    return IndyInterface.bootstrap("invoke", "leftShift", 0, this.out.get(), IndyInterface.bootstrap("getProperty", "qt", 12, this));
                }

                @Generated
                public Object call(Object key, Object value) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, key, value);
                }

                @Generated
                public Object getNamespaces() {
                    return this.namespaces.get();
                }

                @Generated
                public Object getPendingNamespaces() {
                    return this.pendingNamespaces.get();
                }

                @Generated
                public Object getOut() {
                    return this.out.get();
                }

                @Generated
                public Object getDoc() {
                    return this.doc.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _closure12.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, attrs, new _closure12(this, this.getThisObject(), namespaces2, pendingNamespaces2, out2, doc2));
            Reference<Map> hiddenNamespaces = new Reference<Map>(ScriptBytecodeAdapter.createMap(new Object[0]));
            public final class _closure13
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference hiddenNamespaces;
                private /* synthetic */ Reference namespaces;
                private /* synthetic */ Reference out;
                private /* synthetic */ Reference doc;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure13(Object _outerInstance, Object _thisObject, Reference hiddenNamespaces, Reference namespaces, Reference out, Reference doc) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    Reference reference4;
                    this.hiddenNamespaces = reference4 = hiddenNamespaces;
                    this.namespaces = reference3 = namespaces;
                    this.out = reference2 = out;
                    this.doc = reference = doc;
                }

                public Object doCall(Object key, Object value) {
                    if (IndyInterface.bootstrap("cast", "()", 0, value) != false) {
                        CallSite callSite = IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaces.get(), key);
                        IndyInterface.bootstrap("invoke", "putAt", 0, this.hiddenNamespaces.get(), key, callSite);
                        Object object = value;
                        IndyInterface.bootstrap("invoke", "putAt", 0, this.namespaces.get(), key, object);
                        IndyInterface.bootstrap("invoke", "leftShift", 0, this.out.get(), ScriptBytecodeAdapter.compareEqual(key, ":") ? IndyInterface.bootstrap("invoke", "plus", 0, " xmlns=", IndyInterface.bootstrap("getProperty", "qt", 12, this)) : IndyInterface.bootstrap("invoke", "plus", 0, new GStringImpl(new Object[]{key}, new String[]{" xmlns:", "="}), IndyInterface.bootstrap("getProperty", "qt", 12, this)));
                        boolean bl = true;
                        ScriptBytecodeAdapter.setProperty(bl, null, this.out.get(), "writingAttribute");
                        IndyInterface.bootstrap("invoke", "build", 0, new GStringImpl(new Object[]{value}, new String[]{"", ""}), this.doc.get());
                        boolean bl2 = false;
                        ScriptBytecodeAdapter.setProperty(bl2, null, this.out.get(), "writingAttribute");
                        return IndyInterface.bootstrap("invoke", "leftShift", 0, this.out.get(), IndyInterface.bootstrap("getProperty", "qt", 12, this));
                    }
                    return null;
                }

                @Generated
                public Object call(Object key, Object value) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, key, value);
                }

                @Generated
                public Object getHiddenNamespaces() {
                    return this.hiddenNamespaces.get();
                }

                @Generated
                public Object getNamespaces() {
                    return this.namespaces.get();
                }

                @Generated
                public Object getOut() {
                    return this.out.get();
                }

                @Generated
                public Object getDoc() {
                    return this.doc.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _closure13.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, pendingNamespaces2.get(), new _closure13(this, this.getThisObject(), hiddenNamespaces, namespaces2, out2, doc2));
            if (ScriptBytecodeAdapter.compareEqual(body, null) && IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "expandEmptyElements", 12, this)) == false) {
                IndyInterface.bootstrap("invoke", "leftShift", 0, out2.get(), "/>");
            } else {
                IndyInterface.bootstrap("invoke", "leftShift", 0, out2.get(), ">");
                IndyInterface.bootstrap("invoke", "push", 0, IndyInterface.bootstrap("getProperty", "pendingStack", 12, this), IndyInterface.bootstrap("invoke", "clone", 0, pendingNamespaces2.get()));
                IndyInterface.bootstrap("invoke", "clear", 0, pendingNamespaces2.get());
                public final class _closure14
                extends Closure
                implements GeneratedClosure {
                    private /* synthetic */ Reference doc;
                    private /* synthetic */ Reference out;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _closure14(Object _outerInstance, Object _thisObject, Reference doc, Reference out) {
                        super(_outerInstance, _thisObject);
                        Reference reference;
                        Reference reference2;
                        this.doc = reference2 = doc;
                        this.out = reference = out;
                    }

                    public Object doCall(Object it) {
                        if (it instanceof Closure) {
                            CallSite body1 = IndyInterface.bootstrap("invoke", "clone", 0, it);
                            Object t = this.doc.get();
                            ScriptBytecodeAdapter.setProperty(t, null, body1, "delegate");
                            return IndyInterface.bootstrap("invoke", "call", 0, body1, this.doc.get());
                        }
                        if (it instanceof Buildable) {
                            return IndyInterface.bootstrap("invoke", "build", 0, it, this.doc.get());
                        }
                        return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("invoke", "escaped", 0, this.out.get()), it);
                    }

                    @Generated
                    public Object getDoc() {
                        return this.doc.get();
                    }

                    @Generated
                    public Object getOut() {
                        return this.out.get();
                    }

                    @Generated
                    public Object doCall() {
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _closure14.class) {
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
                IndyInterface.bootstrap("invoke", "each", 0, body, new _closure14(this, this.getThisObject(), doc2, out2));
                IndyInterface.bootstrap("invoke", "clear", 0, pendingNamespaces2.get());
                IndyInterface.bootstrap("invoke", "putAll", 0, pendingNamespaces2.get(), IndyInterface.bootstrap("invoke", "pop", 0, IndyInterface.bootstrap("getProperty", "pendingStack", 12, this)));
                IndyInterface.bootstrap("invoke", "leftShift", 0, out2.get(), new GStringImpl(new Object[]{tag}, new String[]{"</", ">"}));
            }
            public final class _closure15
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference namespaces;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure15(Object _outerInstance, Object _thisObject, Reference namespaces) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.namespaces = reference = namespaces;
                }

                public Object doCall(Object key, Object value) {
                    if (ScriptBytecodeAdapter.compareEqual(value, null)) {
                        return IndyInterface.bootstrap("invoke", "remove", 0, this.namespaces.get(), key);
                    }
                    Object object = value;
                    IndyInterface.bootstrap("invoke", "putAt", 0, this.namespaces.get(), key, object);
                    return object;
                }

                @Generated
                public Object call(Object key, Object value) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, key, value);
                }

                @Generated
                public Object getNamespaces() {
                    return this.namespaces.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _closure15.class) {
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
            return IndyInterface.bootstrap("invoke", "each", 0, hiddenNamespaces.get(), new _closure15(this, this.getThisObject(), namespaces2));
        }

        /*
         * WARNING - void declaration
         */
        @Generated
        public Object call(Object tag, Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object out) {
            void var3_3;
            Reference<Object> doc2 = new Reference<Object>(doc);
            Reference<void> pendingNamespaces2 = new Reference<void>(var3_3);
            Reference<Object> namespaces2 = new Reference<Object>(namespaces);
            Reference<Object> out2 = new Reference<Object>(out);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, tag, doc2.get(), pendingNamespaces2.get(), namespaces2.get(), namespaceSpecificTags, prefix, attrs, body, out2.get());
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != _closure6.class) {
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

