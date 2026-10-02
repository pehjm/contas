/*
 * Decompiled with CFR 0.152.
 */
package groovy.xml.streamingmarkupsupport;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.beans.Transient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class AbstractStreamingBuilder
implements GroovyObject {
    private Object badTagClosure;
    private Object namespaceSetupClosure;
    private Object aliasSetupClosure;
    private Object getNamespaceClosure;
    private Object toMapStringClosure;
    private Object specialTags;
    private Object builder;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public AbstractStreamingBuilder() {
        MetaClass metaClass;
        _closure1 _closure16 = new _closure1(this, this);
        this.badTagClosure = _closure16;
        _closure2 _closure26 = new _closure2(this, this);
        this.namespaceSetupClosure = _closure26;
        _closure3 _closure35 = new _closure3(this, this);
        this.aliasSetupClosure = _closure35;
        _closure4 _closure42 = new _closure4(this, this);
        this.getNamespaceClosure = _closure42;
        _closure5 _closure52 = new _closure5(this, this);
        this.toMapStringClosure = _closure52;
        Map map = ScriptBytecodeAdapter.createMap(new Object[]{"declareNamespace", this.namespaceSetupClosure, "declareAlias", this.aliasSetupClosure, "getNamespaces", this.getNamespaceClosure});
        this.specialTags = map;
        Object var7_7 = null;
        this.builder = var7_7;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != AbstractStreamingBuilder.class) {
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
    public Object getBadTagClosure() {
        return this.badTagClosure;
    }

    @Generated
    public void setBadTagClosure(Object object) {
        this.badTagClosure = object;
    }

    @Generated
    public Object getNamespaceSetupClosure() {
        return this.namespaceSetupClosure;
    }

    @Generated
    public void setNamespaceSetupClosure(Object object) {
        this.namespaceSetupClosure = object;
    }

    @Generated
    public Object getAliasSetupClosure() {
        return this.aliasSetupClosure;
    }

    @Generated
    public void setAliasSetupClosure(Object object) {
        this.aliasSetupClosure = object;
    }

    @Generated
    public Object getGetNamespaceClosure() {
        return this.getNamespaceClosure;
    }

    @Generated
    public void setGetNamespaceClosure(Object object) {
        this.getNamespaceClosure = object;
    }

    @Generated
    public Object getToMapStringClosure() {
        return this.toMapStringClosure;
    }

    @Generated
    public void setToMapStringClosure(Object object) {
        this.toMapStringClosure = object;
    }

    @Generated
    public Object getSpecialTags() {
        return this.specialTags;
    }

    @Generated
    public void setSpecialTags(Object object) {
        this.specialTags = object;
    }

    @Generated
    public Object getBuilder() {
        return this.builder;
    }

    @Generated
    public void setBuilder(Object object) {
        this.builder = object;
    }

    public final class _closure1
    extends Closure
    implements GeneratedClosure {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        public _closure1(Object _outerInstance, Object _thisObject) {
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object tag, Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object ... rest) {
            CallSite uri = IndyInterface.bootstrap("invoke", "getAt", 0, pendingNamespaces, prefix);
            if (ScriptBytecodeAdapter.compareEqual(uri, null)) {
                uri = IndyInterface.bootstrap("invoke", "getAt", 0, namespaces, prefix);
            }
            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, GroovyRuntimeException.class, new GStringImpl(new Object[]{tag, uri}, new String[]{"Tag ", " is not allowed in namespace ", ""})));
        }

        @Generated
        public Object call(Object tag, Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object ... rest) {
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, tag, doc, pendingNamespaces, namespaces, namespaceSpecificTags, prefix, rest);
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

        /*
         * WARNING - void declaration
         */
        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object ... rest) {
            void var3_3;
            Reference<Object> pendingNamespaces2 = new Reference<Object>(pendingNamespaces);
            Reference<void> namespaces2 = new Reference<void>(var3_3);
            Reference<Object> namespaceSpecificTags2 = new Reference<Object>(namespaceSpecificTags);
            public final class _closure6
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference namespaces;
                private /* synthetic */ Reference pendingNamespaces;
                private /* synthetic */ Reference namespaceSpecificTags;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure6(Object _outerInstance, Object _thisObject, Reference namespaces, Reference pendingNamespaces, Reference namespaceSpecificTags) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    this.namespaces = reference3 = namespaces;
                    this.pendingNamespaces = reference2 = pendingNamespaces;
                    this.namespaceSpecificTags = reference = namespaceSpecificTags;
                }

                public Object doCall(Object key, Object value) {
                    if (ScriptBytecodeAdapter.compareEqual(key, "")) {
                        key = ":";
                    }
                    value = IndyInterface.bootstrap("invoke", "toString", 0, value);
                    if (ScriptBytecodeAdapter.compareNotEqual(IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaces.get(), key), value)) {
                        Object object = value;
                        IndyInterface.bootstrap("invoke", "putAt", 0, this.pendingNamespaces.get(), key, object);
                    }
                    if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "containsKey", 0, this.namespaceSpecificTags.get(), value)) == false) {
                        CallSite baseEntry = IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaceSpecificTags.get(), ":");
                        CallSite callSite = IndyInterface.bootstrap("invoke", "toArray", 0, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, baseEntry, false), IndyInterface.bootstrap("invoke", "getAt", 0, baseEntry, true), ScriptBytecodeAdapter.createMap(new Object[0])}));
                        IndyInterface.bootstrap("invoke", "putAt", 0, this.namespaceSpecificTags.get(), value, callSite);
                        return callSite;
                    }
                    return null;
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
                public Object getNamespaceSpecificTags() {
                    return this.namespaceSpecificTags.get();
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
            return IndyInterface.bootstrap("invoke", "each", 0, attrs, new _closure6(this, this.getThisObject(), namespaces2, pendingNamespaces2, namespaceSpecificTags2));
        }

        /*
         * WARNING - void declaration
         */
        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object ... rest) {
            void var3_3;
            Reference<Object> pendingNamespaces2 = new Reference<Object>(pendingNamespaces);
            Reference<void> namespaces2 = new Reference<void>(var3_3);
            Reference<Object> namespaceSpecificTags2 = new Reference<Object>(namespaceSpecificTags);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces2.get(), namespaces2.get(), namespaceSpecificTags2.get(), prefix, attrs, rest);
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

        /*
         * WARNING - void declaration
         */
        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object ... rest) {
            void var3_3;
            Reference<Object> pendingNamespaces2 = new Reference<Object>(pendingNamespaces);
            Reference<void> namespaces2 = new Reference<void>(var3_3);
            Reference<Object> namespaceSpecificTags2 = new Reference<Object>(namespaceSpecificTags);
            public final class _closure7
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference namespaces;
                private /* synthetic */ Reference namespaceSpecificTags;
                private /* synthetic */ Reference pendingNamespaces;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure7(Object _outerInstance, Object _thisObject, Reference namespaces, Reference namespaceSpecificTags, Reference pendingNamespaces) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    this.namespaces = reference3 = namespaces;
                    this.namespaceSpecificTags = reference2 = namespaceSpecificTags;
                    this.pendingNamespaces = reference = pendingNamespaces;
                }

                public Object doCall(Object key, Object value) {
                    if (value instanceof Map) {
                        Reference<Object> info = new Reference<Object>(null);
                        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "containsKey", 0, this.namespaces.get(), key)) != false) {
                            info.set(IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaceSpecificTags.get(), IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaces.get(), key)));
                        } else if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "containsKey", 0, this.pendingNamespaces.get(), key)) != false) {
                            info.set(IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaceSpecificTags.get(), IndyInterface.bootstrap("invoke", "getAt", 0, this.pendingNamespaces.get(), key)));
                        } else {
                            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, GroovyRuntimeException.class, new GStringImpl(new Object[]{key}, new String[]{"namespace prefix ", " has not been declared"})));
                        }
                        public final class _closure8
                        extends Closure
                        implements GeneratedClosure {
                            private /* synthetic */ Reference info;
                            private static /* synthetic */ ClassInfo $staticClassInfo;
                            public static transient /* synthetic */ boolean __$stMC;

                            public _closure8(Object _outerInstance, Object _thisObject, Reference info) {
                                super(_outerInstance, _thisObject);
                                Reference reference;
                                this.info = reference = info;
                            }

                            public Object doCall(Object to, Object from) {
                                CallSite callSite = IndyInterface.bootstrap("invoke", "curry", 0, IndyInterface.bootstrap("invoke", "getAt", 0, this.info.get(), true), from);
                                IndyInterface.bootstrap("invoke", "putAt", 0, IndyInterface.bootstrap("invoke", "getAt", 0, this.info.get(), 2), to, callSite);
                                return callSite;
                            }

                            @Generated
                            public Object call(Object to, Object from) {
                                return IndyInterface.bootstrap("invoke", "doCall", 2, this, to, from);
                            }

                            @Generated
                            public Object getInfo() {
                                return this.info.get();
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
                        return IndyInterface.bootstrap("invoke", "each", 0, value, new _closure8(this, this.getThisObject(), info));
                    }
                    CallSite info = IndyInterface.bootstrap("invoke", "getAt", 0, this.namespaceSpecificTags.get(), ":");
                    CallSite callSite = IndyInterface.bootstrap("invoke", "curry", 0, IndyInterface.bootstrap("invoke", "getAt", 0, info, true), value);
                    IndyInterface.bootstrap("invoke", "putAt", 0, IndyInterface.bootstrap("invoke", "getAt", 0, info, 2), key, callSite);
                    return callSite;
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
                public Object getNamespaceSpecificTags() {
                    return this.namespaceSpecificTags.get();
                }

                @Generated
                public Object getPendingNamespaces() {
                    return this.pendingNamespaces.get();
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
            return IndyInterface.bootstrap("invoke", "each", 0, attrs, new _closure7(this, this.getThisObject(), namespaces2, namespaceSpecificTags2, pendingNamespaces2));
        }

        /*
         * WARNING - void declaration
         */
        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object namespaceSpecificTags, Object prefix, Object attrs, Object ... rest) {
            void var3_3;
            Reference<Object> pendingNamespaces2 = new Reference<Object>(pendingNamespaces);
            Reference<void> namespaces2 = new Reference<void>(var3_3);
            Reference<Object> namespaceSpecificTags2 = new Reference<Object>(namespaceSpecificTags);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces2.get(), namespaces2.get(), namespaceSpecificTags2.get(), prefix, attrs, rest);
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

        public Object doCall(Object doc, Object pendingNamespaces, Object namespaces, Object ... rest) {
            return ScriptBytecodeAdapter.createList(new Object[]{namespaces, pendingNamespaces});
        }

        @Generated
        public Object call(Object doc, Object pendingNamespaces, Object namespaces, Object ... rest) {
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, doc, pendingNamespaces, namespaces, rest);
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

        public Object doCall(Map instruction, Object checkDoubleQuotationMarks) {
            Reference<Object> checkDoubleQuotationMarks2 = new Reference<Object>(checkDoubleQuotationMarks);
            Reference<CallSite> buf = new Reference<CallSite>(IndyInterface.bootstrap("init", "<init>", 0, StringBuilder.class));
            public final class _closure9
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference checkDoubleQuotationMarks;
                private /* synthetic */ Reference buf;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure9(Object _outerInstance, Object _thisObject, Reference checkDoubleQuotationMarks, Reference buf) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    this.checkDoubleQuotationMarks = reference2 = checkDoubleQuotationMarks;
                    this.buf = reference = buf;
                }

                public Object doCall(Object name, Object value) {
                    if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "call", 0, this.checkDoubleQuotationMarks.get(), value)) != false) {
                        return IndyInterface.bootstrap("invoke", "append", 0, this.buf.get(), new GStringImpl(new Object[]{name, value}, new String[]{" ", "=\"", "\""}));
                    }
                    return IndyInterface.bootstrap("invoke", "append", 0, this.buf.get(), new GStringImpl(new Object[]{name, value}, new String[]{" ", "='", "'"}));
                }

                @Generated
                public Object call(Object name, Object value) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, name, value);
                }

                @Generated
                public Object getCheckDoubleQuotationMarks() {
                    return this.checkDoubleQuotationMarks.get();
                }

                @Generated
                public Object getBuf() {
                    return this.buf.get();
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
            IndyInterface.bootstrap("invoke", "each", 0, instruction, new _closure9(this, this.getThisObject(), checkDoubleQuotationMarks2, buf));
            return IndyInterface.bootstrap("invoke", "toString", 0, buf.get());
        }

        @Generated
        public Object call(Map instruction, Object checkDoubleQuotationMarks) {
            Reference<Object> checkDoubleQuotationMarks2 = new Reference<Object>(checkDoubleQuotationMarks);
            return IndyInterface.bootstrap("invoke", "doCall", 2, this, instruction, checkDoubleQuotationMarks2.get());
        }

        @Generated
        public Object doCall(Map instruction) {
            public final class _closure10
            extends Closure
            implements GeneratedClosure {
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _closure10(Object _outerInstance, Object _thisObject) {
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object value) {
                    return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "contains", 0, IndyInterface.bootstrap("invoke", "toString", 0, value), "\"")) == false;
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
            return this.doCall(instruction, new _closure10(this, this.getThisObject()));
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
}

