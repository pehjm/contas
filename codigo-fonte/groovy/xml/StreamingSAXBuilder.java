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
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;

public class StreamingSAXBuilder
extends AbstractStreamingBuilder {
    private Object pendingStack;
    private Object commentClosure;
    private Object piClosure;
    private Object noopClosure;
    private Object tagClosure;
    private Object builder;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;

    public StreamingSAXBuilder() {
        List list = ScriptBytecodeAdapter.createList(new Object[0]);
        this.pendingStack = list;
        _closure1 _closure16 = new _closure1(this, this);
        this.commentClosure = _closure16;
        _closure2 _closure26 = new _closure2(this, this);
        this.piClosure = _closure26;
        _closure3 _closure35 = new _closure3(this, this);
        this.noopClosure = _closure35;
        _closure4 _closure42 = new _closure4(this, this);
        this.tagClosure = _closure42;
        Object var6_6 = null;
        this.builder = var6_6;
        IndyInterface.bootstrap("invoke", "putAll", 0, IndyInterface.bootstrap("getProperty", "specialTags", 12, this), ScriptBytecodeAdapter.createMap(new Object[]{"yield", this.noopClosure, "yieldUnescaped", this.noopClosure, "comment", this.commentClosure, "pi", this.piClosure}));
        Map nsSpecificTags = ScriptBytecodeAdapter.createMap(new Object[]{":", ScriptBytecodeAdapter.createList(new Object[]{this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0])}), "http://www.w3.org/XML/1998/namespace", ScriptBytecodeAdapter.createList(new Object[]{this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0])}), "http://www.codehaus.org/Groovy/markup/keywords", ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("getProperty", "badTagClosure", 12, this), this.tagClosure, IndyInterface.bootstrap("getProperty", "specialTags", 12, this)})});
        CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, BaseMarkupBuilder.class, nsSpecificTags);
        this.builder = callSite;
    }

    private Object addAttributes(AttributesImpl attributes, Object key, Object value, Object namespaces) {
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "contains", 0, key, "$")) != false) {
            CallSite parts = IndyInterface.bootstrap("invoke", "tokenize", 0, key, "$");
            if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "containsKey", 0, namespaces, IndyInterface.bootstrap("invoke", "getAt", 0, parts, false))) != false) {
                CallSite namespaceUri = IndyInterface.bootstrap("invoke", "getAt", 0, namespaces, IndyInterface.bootstrap("invoke", "getAt", 0, parts, false));
                return IndyInterface.bootstrap("invoke", "addAttribute", 0, attributes, namespaceUri, IndyInterface.bootstrap("invoke", "getAt", 0, parts, true), new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, parts, false), IndyInterface.bootstrap("invoke", "getAt", 0, parts, true)}, new String[]{"", ":", ""}), "CDATA", new GStringImpl(new Object[]{value}, new String[]{"", ""}));
            }
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, GroovyRuntimeException.class, new GStringImpl(new Object[]{key}, new String[]{"bad attribute namespace tag in ", ""})));
        }
        return IndyInterface.bootstrap("invoke", "addAttribute", 0, attributes, "", key, key, "CDATA", new GStringImpl(new Object[]{value}, new String[]{"", ""}));
    }

    /*
     * WARNING - void declaration
     */
    private Object processBody(Object body, Object doc, Object contentHandler) {
        void var3_3;
        Reference<Object> doc2 = new Reference<Object>(doc);
        Reference<void> contentHandler2 = new Reference<void>(var3_3);
        if (body instanceof Closure) {
            CallSite body1 = IndyInterface.bootstrap("invoke", "clone", 0, body);
            Object object = doc2.get();
            ScriptBytecodeAdapter.setProperty(object, null, body1, "delegate");
            return IndyInterface.bootstrap("invoke", "call", 0, body1, doc2.get());
        }
        if (body instanceof Buildable) {
            return IndyInterface.bootstrap("invoke", "build", 0, body, doc2.get());
        }
        public final class _processBody_closure5
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference doc;
            private /* synthetic */ Reference contentHandler;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _processBody_closure5(Object _outerInstance, Object _thisObject, Reference doc, Reference contentHandler) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.doc = reference2 = doc;
                this.contentHandler = reference = contentHandler;
            }

            public Object doCall(Object it) {
                return IndyInterface.bootstrap("invoke", "processBodyPart", 2, this, it, this.doc.get(), this.contentHandler.get());
            }

            @Generated
            public Object getDoc() {
                return this.doc.get();
            }

            @Generated
            public Object getContentHandler() {
                return this.contentHandler.get();
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _processBody_closure5.class) {
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
        return IndyInterface.bootstrap("invoke", "each", 0, body, new _processBody_closure5(this, this, doc2, contentHandler2));
    }

    private Object processBodyPart(Object part, Object doc, Object contentHandler) {
        if (part instanceof Closure) {
            CallSite body1 = IndyInterface.bootstrap("invoke", "clone", 0, part);
            Object object = doc;
            ScriptBytecodeAdapter.setProperty(object, null, body1, "delegate");
            return IndyInterface.bootstrap("invoke", "call", 0, body1, doc);
        }
        if (part instanceof Buildable) {
            return IndyInterface.bootstrap("invoke", "build", 0, part, doc);
        }
        CallSite chars = IndyInterface.bootstrap("invoke", "toCharArray", 0, part);
        return IndyInterface.bootstrap("invoke", "characters", 0, contentHandler, chars, false, IndyInterface.bootstrap("invoke", "size", 0, chars));
    }

    public Object bind(Object closure) {
        Reference<CallSite> boundClosure = new Reference<CallSite>(IndyInterface.bootstrap("invoke", "bind", 0, this.builder, closure));
        public final class _bind_closure6
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference boundClosure;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _bind_closure6(Object _outerInstance, Object _thisObject, Reference boundClosure) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.boundClosure = reference = boundClosure;
            }

            public Object doCall(Object contentHandler) {
                IndyInterface.bootstrap("invoke", "startDocument", 0, contentHandler);
                Object object = contentHandler;
                ScriptBytecodeAdapter.setProperty(object, null, this.boundClosure.get(), "trigger");
                return IndyInterface.bootstrap("invoke", "endDocument", 0, contentHandler);
            }

            @Generated
            public Object getBoundClosure() {
                return this.boundClosure.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _bind_closure6.class) {
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
        return new _bind_closure6(this, this, boundClosure);
    }

    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != StreamingSAXBuilder.class) {
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

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object contentHandler) {
            if (contentHandler instanceof LexicalHandler) {
                return IndyInterface.bootstrap("invoke", "comment", 0, contentHandler, IndyInterface.bootstrap("invoke", "toCharArray", 0, body), false, IndyInterface.bootstrap("invoke", "size", 0, body));
            }
            return null;
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object contentHandler) {
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, contentHandler);
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

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object contentHandler) {
            Reference<Object> contentHandler2 = new Reference<Object>(contentHandler);
            public final class _closure7
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference contentHandler;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure7(Object _outerInstance, Object _thisObject, Reference contentHandler) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    this.contentHandler = reference = contentHandler;
                }

                public Object doCall(Object target, Object instruction) {
                    if (instruction instanceof Map) {
                        return IndyInterface.bootstrap("invoke", "processingInstruction", 0, this.contentHandler.get(), target, IndyInterface.bootstrap("invoke", "toMapStringClosure", 2, this, instruction));
                    }
                    return IndyInterface.bootstrap("invoke", "processingInstruction", 0, this.contentHandler.get(), target, instruction);
                }

                @Generated
                public Object call(Object target, Object instruction) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, target, instruction);
                }

                @Generated
                public Object getContentHandler() {
                    return this.contentHandler.get();
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
            return IndyInterface.bootstrap("invoke", "each", 0, attrs, new _closure7(this, this.getThisObject(), contentHandler2));
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object contentHandler) {
            Reference<Object> contentHandler2 = new Reference<Object>(contentHandler);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, contentHandler2.get());
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

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object contentHandler) {
            if (ScriptBytecodeAdapter.compareNotEqual(body, null)) {
                return IndyInterface.bootstrap("invoke", "processBody", 2, this, body, doc, contentHandler);
            }
            return null;
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object contentHandler) {
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, attrs, body, contentHandler);
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

        public Object doCall(Object tag, Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object contentHandler) {
            Reference<Object> namespaces2 = new Reference<Object>(namespaces);
            Reference<Object> contentHandler2 = new Reference<Object>(contentHandler);
            Reference<CallSite> attributes = new Reference<CallSite>(IndyInterface.bootstrap("init", "<init>", 0, AttributesImpl.class));
            public final class _closure8
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference attributes;
                private /* synthetic */ Reference namespaces;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure8(Object _outerInstance, Object _thisObject, Reference attributes, Reference namespaces) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    this.attributes = reference2 = attributes;
                    this.namespaces = reference = namespaces;
                }

                public Object doCall(Object key, Object value) {
                    return IndyInterface.bootstrap("invoke", "addAttributes", 2, this, this.attributes.get(), key, value, this.namespaces.get());
                }

                @Generated
                public Object call(Object key, Object value) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, key, value);
                }

                @Generated
                public Object getAttributes() {
                    return this.attributes.get();
                }

                @Generated
                public Object getNamespaces() {
                    return this.namespaces.get();
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
            IndyInterface.bootstrap("invoke", "each", 0, attrs, new _closure8(this, this.getThisObject(), attributes, namespaces2));
            Reference<Map> hiddenNamespaces = new Reference<Map>(ScriptBytecodeAdapter.createMap(new Object[0]));
            public final class _closure9
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference hiddenNamespaces;
                private /* synthetic */ Reference namespaces;
                private /* synthetic */ Reference attributes;
                private /* synthetic */ Reference contentHandler;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure9(Object _outerInstance, Object _thisObject, Reference hiddenNamespaces, Reference namespaces, Reference attributes, Reference contentHandler) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    Reference reference4;
                    this.hiddenNamespaces = reference4 = hiddenNamespaces;
                    this.namespaces = reference3 = namespaces;
                    this.attributes = reference2 = attributes;
                    this.contentHandler = reference = contentHandler;
                }

                public Object doCall(Object key, Object value) {
                    Object k = ScriptBytecodeAdapter.compareEqual(key, ":") ? "" : key;
                    CallSite callSite = IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaces.get(), key);
                    IndyInterface.bootstrap("invoke", "putAt", 0, this.hiddenNamespaces.get(), k, callSite);
                    Object object = value;
                    IndyInterface.bootstrap("invoke", "putAt", 0, this.namespaces.get(), k, object);
                    IndyInterface.bootstrap("invoke", "addAttribute", 0, this.attributes.get(), "http://www.w3.org/2000/xmlns/", k, new GStringImpl(new Object[]{ScriptBytecodeAdapter.compareEqual(k, "") ? "" : new GStringImpl(new Object[]{k}, new String[]{":", ""})}, new String[]{"xmlns", ""}), "CDATA", new GStringImpl(new Object[]{value}, new String[]{"", ""}));
                    return IndyInterface.bootstrap("invoke", "startPrefixMapping", 0, this.contentHandler.get(), k, value);
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
                public Object getAttributes() {
                    return this.attributes.get();
                }

                @Generated
                public Object getContentHandler() {
                    return this.contentHandler.get();
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
            IndyInterface.bootstrap("invoke", "each", 0, pendingNamespaces, new _closure9(this, this.getThisObject(), hiddenNamespaces, namespaces2, attributes, contentHandler2));
            Object uri = "";
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
            IndyInterface.bootstrap("invoke", "startElement", 0, contentHandler2.get(), uri, tag, qualifiedName, attributes.get());
            if (ScriptBytecodeAdapter.compareNotEqual(body, null)) {
                IndyInterface.bootstrap("invoke", "add", 0, IndyInterface.bootstrap("getProperty", "pendingStack", 12, this), IndyInterface.bootstrap("invoke", "clone", 0, pendingNamespaces));
                IndyInterface.bootstrap("invoke", "clear", 0, pendingNamespaces);
                IndyInterface.bootstrap("invoke", "processBody", 2, this, body, doc, contentHandler2.get());
                IndyInterface.bootstrap("invoke", "clear", 0, pendingNamespaces);
                IndyInterface.bootstrap("invoke", "putAll", 0, pendingNamespaces, IndyInterface.bootstrap("invoke", "pop", 0, IndyInterface.bootstrap("getProperty", "pendingStack", 12, this)));
            }
            IndyInterface.bootstrap("invoke", "endElement", 0, contentHandler2.get(), uri, tag, qualifiedName);
            public final class _closure10
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference contentHandler;
                private /* synthetic */ Reference namespaces;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure10(Object _outerInstance, Object _thisObject, Reference contentHandler, Reference namespaces) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    this.contentHandler = reference2 = contentHandler;
                    this.namespaces = reference = namespaces;
                }

                public Object doCall(Object key, Object value) {
                    IndyInterface.bootstrap("invoke", "endPrefixMapping", 0, this.contentHandler.get(), key);
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
                public Object getContentHandler() {
                    return this.contentHandler.get();
                }

                @Generated
                public Object getNamespaces() {
                    return this.namespaces.get();
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
            return IndyInterface.bootstrap("invoke", "each", 0, hiddenNamespaces.get(), new _closure10(this, this.getThisObject(), contentHandler2, namespaces2));
        }

        @Generated
        public Object call(Object tag, Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object body, Object contentHandler) {
            Reference<Object> namespaces2 = new Reference<Object>(namespaces);
            Reference<Object> contentHandler2 = new Reference<Object>(contentHandler);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, tag, doc, pendingNamespaces, namespaces2.get(), namespaceSpecificTags, prefix, attrs, body, contentHandler2.get());
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

