/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.mapping;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.internal.path.json.mapping.JsonPathJsonbObjectDeserializer;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import io.restassured.path.json.mapping.JsonPathObjectDeserializer;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class JsonbMapper
implements ObjectMapper,
GroovyObject {
    private JsonbObjectMapperFactory factory;
    private JsonPathObjectDeserializer deserializer;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729349;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public JsonbMapper(JsonbObjectMapperFactory jsonbObjectMapperFactory) {
        void factory;
        MetaClass metaClass;
        CallSite[] callSiteArray = JsonbMapper.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var4_4 = factory;
        this.factory = (JsonbObjectMapperFactory)ScriptBytecodeAdapter.castToType(var4_4, JsonbObjectMapperFactory.class);
        Object object = callSiteArray[0].callConstructor(JsonPathJsonbObjectDeserializer.class, factory);
        this.deserializer = (JsonPathObjectDeserializer)ScriptBytecodeAdapter.castToType(object, JsonPathObjectDeserializer.class);
    }

    public Object deserialize(ObjectMapperDeserializationContext context) {
        CallSite[] callSiteArray = JsonbMapper.$getCallSiteArray();
        return callSiteArray[1].call((Object)this.deserializer, context);
    }

    public Object serialize(ObjectMapperSerializationContext context) {
        CallSite[] callSiteArray = JsonbMapper.$getCallSiteArray();
        Reference<Object> object = new Reference<Object>(callSiteArray[2].call(context));
        Reference<Object> mapper = new Reference<Object>(callSiteArray[3].call(this.factory, callSiteArray[4].call(object.get()), callSiteArray[5].call(context)));
        return callSiteArray[6].call(callSiteArray[7].callConstructor(StringWriter.class), new GeneratedClosure(this, this, mapper, object){
            private /* synthetic */ Reference mapper;
            private /* synthetic */ Reference object;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _serialize_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.mapper = reference2 = mapper;
                this.object = reference = object;
            }

            public Object doCall(Object out) {
                CallSite[] callSiteArray = _serialize_closure1.$getCallSiteArray();
                callSiteArray[0].call(out, callSiteArray[1].call(this.mapper.get(), this.object.get()));
                return callSiteArray[2].call(out);
            }

            public Object getMapper() {
                CallSite[] callSiteArray = _serialize_closure1.$getCallSiteArray();
                return this.mapper.get();
            }

            public Object getObject() {
                CallSite[] callSiteArray = _serialize_closure1.$getCallSiteArray();
                return this.object.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _serialize_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _serialize_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _serialize_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "write";
                stringArray[1] = "toJson";
                stringArray[2] = "toString";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _serialize_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_serialize_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _serialize_closure1.$createCallSiteArray();
                    $callSiteArray = new SoftReference<CallSiteArray>(callSiteArray);
                }
                return callSiteArray.array;
            }

            static /* synthetic */ Class class$(String string) {
                try {
                    return Class.forName(string);
                }
                catch (ClassNotFoundException classNotFoundException) {
                    throw new NoClassDefFoundError(classNotFoundException.getMessage());
                }
            }
        });
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = JsonbMapper.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(JsonbMapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = JsonbMapper.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, JsonbMapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = JsonbMapper.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(JsonbMapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != JsonbMapper.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    public /* synthetic */ void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public /* synthetic */ Object invokeMethod(String string, Object object) {
        return this.getMetaClass().invokeMethod((Object)this, string, object);
    }

    public /* synthetic */ Object getProperty(String string) {
        return this.getMetaClass().getProperty(this, string);
    }

    public /* synthetic */ void setProperty(String string, Object object) {
        this.getMetaClass().setProperty(this, string, object);
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = JsonbMapper.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        JsonbMapper.__$swapInit();
        __timeStamp__239_neverHappen1702030729349 = l2 = 0L;
        __timeStamp = l = 1702030729349L;
    }

    public /* synthetic */ void super$1$notify() {
        super.notify();
    }

    public /* synthetic */ int super$1$hashCode() {
        return super.hashCode();
    }

    public /* synthetic */ String super$1$toString() {
        return super.toString();
    }

    public /* synthetic */ Object super$1$clone() {
        return super.clone();
    }

    public /* synthetic */ void super$1$wait(long l, int n) {
        super.wait(l, n);
    }

    public /* synthetic */ void super$1$wait(long l) {
        super.wait(l);
    }

    public /* synthetic */ void super$1$wait() {
        super.wait();
    }

    public /* synthetic */ void super$1$notifyAll() {
        super.notifyAll();
    }

    public /* synthetic */ boolean super$1$equals(Object object) {
        return super.equals(object);
    }

    public /* synthetic */ void super$1$finalize() {
        super.finalize();
    }

    public /* synthetic */ Class super$1$getClass() {
        return super.getClass();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
        stringArray[0] = "<$constructor$>";
        stringArray[1] = "deserialize";
        stringArray[2] = "getObjectToSerialize";
        stringArray[3] = "create";
        stringArray[4] = "getClass";
        stringArray[5] = "getCharset";
        stringArray[6] = "withWriter";
        stringArray[7] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[8];
        JsonbMapper.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(JsonbMapper.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = JsonbMapper.$createCallSiteArray();
            $callSiteArray = new SoftReference<CallSiteArray>(callSiteArray);
        }
        return callSiteArray.array;
    }

    static /* synthetic */ Class class$(String string) {
        try {
            return Class.forName(string);
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw new NoClassDefFoundError(classNotFoundException.getMessage());
        }
    }
}

