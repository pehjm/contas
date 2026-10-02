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
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;
import org.w3c.dom.Node;

public class StreamingDOMBuilder
extends AbstractStreamingBuilder {
    private Object pendingStack;
    private Object defaultNamespaceStack;
    private Object commentClosure;
    private Object piClosure;
    private Object noopClosure;
    private Object tagClosure;
    private Object builder;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;

    public StreamingDOMBuilder() {
        List list = ScriptBytecodeAdapter.createList(new Object[0]);
        this.pendingStack = list;
        List list2 = ScriptBytecodeAdapter.createList(new Object[]{""});
        this.defaultNamespaceStack = list2;
        _closure1 _closure16 = new _closure1(this, this);
        this.commentClosure = _closure16;
        _closure2 _closure26 = new _closure2(this, this);
        this.piClosure = _closure26;
        _closure3 _closure35 = new _closure3(this, this);
        this.noopClosure = _closure35;
        _closure4 _closure42 = new _closure4(this, this);
        this.tagClosure = _closure42;
        Object var7_7 = null;
        this.builder = var7_7;
        IndyInterface.bootstrap("invoke", "putAll", 0, IndyInterface.bootstrap("getProperty", "specialTags", 12, this), ScriptBytecodeAdapter.createMap(new Object[]{"yield", this.noopClosure, "yieldUnescaped", this.noopClosure, "comment", this.commentClosure, "pi", this.piClosure}));
        Map nsSpecificTags = ScriptBytecodeAdapter.createMap(new Object[]{":", ScriptBytecodeAdapter.createList(new Object[]{this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0])}), "http://www.w3.org/2000/xmlns/", ScriptBytecodeAdapter.createList(new Object[]{this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0])}), "http://www.codehaus.org/Groovy/markup/keywords", ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("getProperty", "badTagClosure", 12, this), this.tagClosure, IndyInterface.bootstrap("getProperty", "specialTags", 12, this)})});
        CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, BaseMarkupBuilder.class, nsSpecificTags);
        this.builder = callSite;
    }

    public Object bind(Object closure) {
        Reference<CallSite> boundClosure = new Reference<CallSite>(IndyInterface.bootstrap("invoke", "bind", 0, this.builder, closure));
        public final class _bind_closure5
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference boundClosure;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _bind_closure5(Object _outerInstance, Object _thisObject, Reference boundClosure) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.boundClosure = reference = boundClosure;
            }

            public Object doCall(Object it) {
                if (it instanceof Node) {
                    CallSite document = IndyInterface.bootstrap("invoke", "getOwnerDocument", 0, it);
                    Map map = ScriptBytecodeAdapter.createMap(new Object[]{"document", document, "element", it});
                    ScriptBytecodeAdapter.setProperty(map, null, this.boundClosure.get(), "trigger");
                    return document;
                }
                CallSite dBuilder = IndyInterface.bootstrap("invoke", "newInstance", 0, DocumentBuilderFactory.class);
                boolean bl = true;
                ScriptBytecodeAdapter.setProperty(bl, null, dBuilder, "namespaceAware");
                CallSite newDocument = IndyInterface.bootstrap("invoke", "newDocument", 0, IndyInterface.bootstrap("invoke", "newDocumentBuilder", 0, dBuilder));
                Map map = ScriptBytecodeAdapter.createMap(new Object[]{"document", newDocument, "element", newDocument});
                ScriptBytecodeAdapter.setProperty(map, null, this.boundClosure.get(), "trigger");
                return newDocument;
            }

            @Generated
            public Object getBoundClosure() {
                return this.boundClosure.get();
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _bind_closure5.class) {
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
        return new _bind_closure5(this, this, boundClosure);
    }

    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != StreamingDOMBuilder.class) {
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
    public Object getPendingStack() {
        return this.pendingStack;
    }

    @Generated
    public void setPendingStack(Object object) {
        this.pendingStack = object;
    }

    @Generated
    public Object getDefaultNamespaceStack() {
        return this.defaultNamespaceStack;
    }

    @Generated
    public void setDefaultNamespaceStack(Object object) {
        this.defaultNamespaceStack = object;
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
    public Object getNoopClosure() {
        return this.noopClosure;
    }

    @Generated
    public void setNoopClosure(Object object) {
        this.noopClosure = object;
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

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object dom) {
            CallSite comment = IndyInterface.bootstrap("invoke", "createComment", 0, IndyInterface.bootstrap("getProperty", "document", 0, dom), body);
            if (ScriptBytecodeAdapter.compareNotEqual(comment, null)) {
                return IndyInterface.bootstrap("invoke", "appendChild", 0, IndyInterface.bootstrap("getProperty", "element", 0, dom), comment);
            }
            return null;
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object dom) {
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, dom);
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

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object dom) {
            Reference<Object> dom2 = new Reference<Object>(dom);
            public final class _closure6
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference dom;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure6(Object _outerInstance, Object _thisObject, Reference dom) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.dom = reference = dom;
                }

                public Object doCall(Object target, Object instruction) {
                    CallSite pi = null;
                    pi = instruction instanceof Map ? IndyInterface.bootstrap("invoke", "createProcessingInstruction", 0, IndyInterface.bootstrap("getProperty", "document", 0, this.dom.get()), target, IndyInterface.bootstrap("invoke", "toMapStringClosure", 2, this, instruction)) : IndyInterface.bootstrap("invoke", "createProcessingInstruction", 0, IndyInterface.bootstrap("getProperty", "document", 0, this.dom.get()), target, instruction);
                    if (ScriptBytecodeAdapter.compareNotEqual(pi, null)) {
                        return IndyInterface.bootstrap("invoke", "appendChild", 0, IndyInterface.bootstrap("getProperty", "element", 0, this.dom.get()), pi);
                    }
                    return null;
                }

                @Generated
                public Object call(Object target, Object instruction) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, target, instruction);
                }

                @Generated
                public Object getDom() {
                    return this.dom.get();
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
            return IndyInterface.bootstrap("invoke", "each", 0, attrs, new _closure6(this, this.getThisObject(), dom2));
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object dom) {
            Reference<Object> dom2 = new Reference<Object>(dom);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, dom2.get());
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

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object dom) {
            Reference<Object> doc2 = new Reference<Object>(doc);
            Reference<Object> dom2 = new Reference<Object>(dom);
            if (body instanceof Closure) {
                CallSite body1 = IndyInterface.bootstrap("invoke", "clone", 0, body);
                Object object = doc2.get();
                ScriptBytecodeAdapter.setProperty(object, null, body1, "delegate");
                return IndyInterface.bootstrap("invoke", "call", 0, body1, doc2.get());
            }
            if (body instanceof Buildable) {
                return IndyInterface.bootstrap("invoke", "build", 0, body, doc2.get());
            }
            if (ScriptBytecodeAdapter.compareNotEqual(body, null)) {
                public final class _closure7
                extends Closure
                implements GeneratedClosure {
                    private /* synthetic */ Reference doc;
                    private /* synthetic */ Reference dom;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _closure7(Object _outerInstance, Object _thisObject, Reference doc, Reference dom) {
                        super(_outerInstance, _thisObject);
                        Reference reference;
                        Reference reference2;
                        this.doc = reference2 = doc;
                        this.dom = reference = dom;
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
                        return IndyInterface.bootstrap("invoke", "appendChild", 0, IndyInterface.bootstrap("getProperty", "element", 0, this.dom.get()), IndyInterface.bootstrap("invoke", "createTextNode", 0, IndyInterface.bootstrap("getProperty", "document", 0, this.dom.get()), it));
                    }

                    @Generated
                    public Object getDoc() {
                        return this.doc.get();
                    }

                    @Generated
                    public Object getDom() {
                        return this.dom.get();
                    }

                    @Generated
                    public Object doCall() {
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _closure7.class) {
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
                return IndyInterface.bootstrap("invoke", "each", 0, body, new _closure7(this, this.getThisObject(), doc2, dom2));
            }
            return null;
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object dom) {
            Reference<Object> doc2 = new Reference<Object>(doc);
            Reference<Object> dom2 = new Reference<Object>(dom);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc2.get(), pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, dom2.get());
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

        public Object doCall(Object tag, Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object dom) {
            Reference<Object> doc2 = new Reference<Object>(doc);
            Reference<Object> namespaces2 = new Reference<Object>(namespaces);
            Reference<Object> dom2 = new Reference<Object>(dom);
            Reference<List> attributes = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[0]));
            Reference<List> nsAttributes = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[0]));
            Reference<CallSite> defaultNamespace = new Reference<CallSite>(IndyInterface.bootstrap("invoke", "last", 0, IndyInterface.bootstrap("getProperty", "defaultNamespaceStack", 12, this)));
            public final class _closure8
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference namespaces;
                private /* synthetic */ Reference nsAttributes;
                private /* synthetic */ Reference attributes;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure8(Object _outerInstance, Object _thisObject, Reference namespaces, Reference nsAttributes, Reference attributes) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    this.namespaces = reference3 = namespaces;
                    this.nsAttributes = reference2 = nsAttributes;
                    this.attributes = reference = attributes;
                }

                public Object doCall(Object key, Object value) {
                    if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "contains", 0, key, "$")) != false) {
                        CallSite parts = IndyInterface.bootstrap("invoke", "tokenize", 0, key, "$");
                        CallSite namespaceUri = null;
                        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "containsKey", 0, this.namespaces.get(), IndyInterface.bootstrap("invoke", "getAt", 0, parts, false))) != false) {
                            namespaceUri = IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaces.get(), IndyInterface.bootstrap("invoke", "getAt", 0, parts, false));
                            return IndyInterface.bootstrap("invoke", "add", 0, this.nsAttributes.get(), ScriptBytecodeAdapter.createList(new Object[]{namespaceUri, new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, parts, false), IndyInterface.bootstrap("invoke", "getAt", 0, parts, true)}, new String[]{"", ":", ""}), new GStringImpl(new Object[]{value}, new String[]{"", ""})}));
                        }
                        throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, GroovyRuntimeException.class, new GStringImpl(new Object[]{key}, new String[]{"bad attribute namespace tag in ", ""})));
                    }
                    return IndyInterface.bootstrap("invoke", "add", 0, this.attributes.get(), ScriptBytecodeAdapter.createList(new Object[]{key, value}));
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
                public Object getNsAttributes() {
                    return this.nsAttributes.get();
                }

                @Generated
                public Object getAttributes() {
                    return this.attributes.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _closure8.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, attrs, new _closure8(this, this.getThisObject(), namespaces2, nsAttributes, attributes));
            Reference<Map> hiddenNamespaces = new Reference<Map>(ScriptBytecodeAdapter.createMap(new Object[0]));
            public final class _closure9
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference defaultNamespace;
                private /* synthetic */ Reference nsAttributes;
                private /* synthetic */ Reference hiddenNamespaces;
                private /* synthetic */ Reference namespaces;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure9(Object _outerInstance, Object _thisObject, Reference defaultNamespace, Reference nsAttributes, Reference hiddenNamespaces, Reference namespaces) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    Reference reference4;
                    this.defaultNamespace = reference4 = defaultNamespace;
                    this.nsAttributes = reference3 = nsAttributes;
                    this.hiddenNamespaces = reference2 = hiddenNamespaces;
                    this.namespaces = reference = namespaces;
                }

                public Object doCall(Object key, Object value) {
                    if (ScriptBytecodeAdapter.compareEqual(key, ":")) {
                        GStringImpl gStringImpl = new GStringImpl(new Object[]{value}, new String[]{"", ""});
                        this.defaultNamespace.set(gStringImpl);
                        return IndyInterface.bootstrap("invoke", "add", 0, this.nsAttributes.get(), ScriptBytecodeAdapter.createList(new Object[]{"http://www.w3.org/2000/xmlns/", "xmlns", this.defaultNamespace.get()}));
                    }
                    CallSite callSite = IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaces.get(), key);
                    IndyInterface.bootstrap("invoke", "putAt", 0, this.hiddenNamespaces.get(), key, callSite);
                    Object object = value;
                    IndyInterface.bootstrap("invoke", "putAt", 0, this.namespaces.get(), key, object);
                    return IndyInterface.bootstrap("invoke", "add", 0, this.nsAttributes.get(), ScriptBytecodeAdapter.createList(new Object[]{"http://www.w3.org/2000/xmlns/", new GStringImpl(new Object[]{key}, new String[]{"xmlns:", ""}), new GStringImpl(new Object[]{value}, new String[]{"", ""})}));
                }

                @Generated
                public Object call(Object key, Object value) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, key, value);
                }

                @Generated
                public Object getDefaultNamespace() {
                    return this.defaultNamespace.get();
                }

                @Generated
                public Object getNsAttributes() {
                    return this.nsAttributes.get();
                }

                @Generated
                public Object getHiddenNamespaces() {
                    return this.hiddenNamespaces.get();
                }

                @Generated
                public Object getNamespaces() {
                    return this.namespaces.get();
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
            IndyInterface.bootstrap("invoke", "each", 0, pendingNamespaces, new _closure9(this, this.getThisObject(), defaultNamespace, nsAttributes, hiddenNamespaces, namespaces2));
            CallSite uri = defaultNamespace.get();
            Object qualifiedName = tag;
            if (ScriptBytecodeAdapter.compareNotEqual(prefix, "")) {
                if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "containsKey", 0, namespaces2.get(), prefix)) != false) {
                    uri = IndyInterface.bootstrap("invoke", "getAt", 0, namespaces2.get(), prefix);
                } else if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "containsKey", 0, pendingNamespaces, prefix)) != false) {
                    uri = IndyInterface.bootstrap("invoke", "getAt", 0, pendingNamespaces, prefix);
                } else {
                    throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, GroovyRuntimeException.class, new GStringImpl(new Object[]{prefix}, new String[]{"Namespace prefix: ", " is not bound to a URI"})));
                }
                if (ScriptBytecodeAdapter.compareNotEqual(prefix, ":")) {
                    qualifiedName = IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, prefix, ":"), tag);
                }
            }
            Reference<CallSite> element = new Reference<CallSite>(IndyInterface.bootstrap("invoke", "createElementNS", 0, IndyInterface.bootstrap("getProperty", "document", 0, dom2.get()), uri, qualifiedName));
            public final class _closure10
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference element;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure10(Object _outerInstance, Object _thisObject, Reference element) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.element = reference = element;
                }

                public Object doCall(Object it) {
                    return IndyInterface.bootstrap("invoke", "setAttributeNS", 0, this.element.get(), IndyInterface.bootstrap("invoke", "getAt", 0, it, false), IndyInterface.bootstrap("invoke", "getAt", 0, it, true), IndyInterface.bootstrap("invoke", "getAt", 0, it, 2));
                }

                @Generated
                public Object getElement() {
                    return this.element.get();
                }

                @Generated
                public Object doCall() {
                    return this.doCall(null);
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
            IndyInterface.bootstrap("invoke", "each", 0, nsAttributes.get(), new _closure10(this, this.getThisObject(), element));
            public final class _closure11
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference element;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure11(Object _outerInstance, Object _thisObject, Reference element) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.element = reference = element;
                }

                public Object doCall(Object it) {
                    return IndyInterface.bootstrap("invoke", "setAttribute", 0, this.element.get(), IndyInterface.bootstrap("invoke", "getAt", 0, it, false), IndyInterface.bootstrap("invoke", "getAt", 0, it, true));
                }

                @Generated
                public Object getElement() {
                    return this.element.get();
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
            IndyInterface.bootstrap("invoke", "each", 0, attributes.get(), new _closure11(this, this.getThisObject(), element));
            IndyInterface.bootstrap("invoke", "appendChild", 0, IndyInterface.bootstrap("getProperty", "element", 0, dom2.get()), element.get());
            CallSite callSite = element.get();
            ScriptBytecodeAdapter.setProperty(callSite, null, dom2.get(), "element");
            if (ScriptBytecodeAdapter.compareNotEqual(body, null)) {
                IndyInterface.bootstrap("invoke", "push", 0, IndyInterface.bootstrap("getProperty", "defaultNamespaceStack", 12, this), defaultNamespace.get());
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "pendingStack", 12, this), IndyInterface.bootstrap("invoke", "clone", 0, pendingNamespaces));
                IndyInterface.bootstrap("invoke", "clear", 0, pendingNamespaces);
                if (body instanceof Closure) {
                    CallSite body1 = IndyInterface.bootstrap("invoke", "clone", 0, body);
                    Object object = doc2.get();
                    ScriptBytecodeAdapter.setProperty(object, null, body1, "delegate");
                    IndyInterface.bootstrap("invoke", "call", 0, body1, doc2.get());
                } else if (body instanceof Buildable) {
                    IndyInterface.bootstrap("invoke", "build", 0, body, doc2.get());
                } else {
                    public final class _closure12
                    extends Closure
                    implements GeneratedClosure {
                        private /* synthetic */ Reference doc;
                        private /* synthetic */ Reference dom;
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;

                        public _closure12(Object _outerInstance, Object _thisObject, Reference doc, Reference dom) {
                            super(_outerInstance, _thisObject);
                            Reference reference;
                            Reference reference2;
                            this.doc = reference2 = doc;
                            this.dom = reference = dom;
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
                            return IndyInterface.bootstrap("invoke", "appendChild", 0, IndyInterface.bootstrap("getProperty", "element", 0, this.dom.get()), IndyInterface.bootstrap("invoke", "createTextNode", 0, IndyInterface.bootstrap("getProperty", "document", 0, this.dom.get()), it));
                        }

                        @Generated
                        public Object getDoc() {
                            return this.doc.get();
                        }

                        @Generated
                        public Object getDom() {
                            return this.dom.get();
                        }

                        @Generated
                        public Object doCall() {
                            return this.doCall(null);
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
                    IndyInterface.bootstrap("invoke", "each", 0, body, new _closure12(this, this.getThisObject(), doc2, dom2));
                }
                IndyInterface.bootstrap("invoke", "clear", 0, pendingNamespaces);
                IndyInterface.bootstrap("invoke", "putAll", 0, pendingNamespaces, IndyInterface.bootstrap("invoke", "pop", 0, IndyInterface.bootstrap("getProperty", "pendingStack", 12, this)));
                IndyInterface.bootstrap("invoke", "pop", 0, IndyInterface.bootstrap("getProperty", "defaultNamespaceStack", 12, this));
            }
            CallSite callSite2 = IndyInterface.bootstrap("invoke", "getParentNode", 0, IndyInterface.bootstrap("getProperty", "element", 0, dom2.get()));
            ScriptBytecodeAdapter.setProperty(callSite2, null, dom2.get(), "element");
            public final class _closure13
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference namespaces;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure13(Object _outerInstance, Object _thisObject, Reference namespaces) {
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
            return IndyInterface.bootstrap("invoke", "each", 0, hiddenNamespaces.get(), new _closure13(this, this.getThisObject(), namespaces2));
        }

        @Generated
        public Object call(Object tag, Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object dom) {
            Reference<Object> doc2 = new Reference<Object>(doc);
            Reference<Object> namespaces2 = new Reference<Object>(namespaces);
            Reference<Object> dom2 = new Reference<Object>(dom);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, tag, doc2.get(), pendingNamespaces, namespaces2.get(), namespaceSpecificTags, prefix, attrs, body, dom2.get());
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
}

