/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.mapping;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.internal.path.json.mapping.JsonPathJohnzonObjectDeserializer;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.path.json.mapper.factory.JohnzonObjectMapperFactory;
import io.restassured.path.json.mapping.JsonPathObjectDeserializer;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class JohnzonMapper
implements ObjectMapper,
GroovyObject {
    private JohnzonObjectMapperFactory factory;
    private JsonPathObjectDeserializer deserializer;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729294;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public JohnzonMapper(JohnzonObjectMapperFactory johnzonObjectMapperFactory) {
        void factory;
        MetaClass metaClass;
        CallSite[] callSiteArray = JohnzonMapper.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var4_4 = factory;
        this.factory = (JohnzonObjectMapperFactory)ScriptBytecodeAdapter.castToType(var4_4, JohnzonObjectMapperFactory.class);
        Object object = callSiteArray[0].callConstructor(JsonPathJohnzonObjectDeserializer.class, factory);
        this.deserializer = (JsonPathObjectDeserializer)ScriptBytecodeAdapter.castToType(object, JsonPathObjectDeserializer.class);
    }

    public Object deserialize(ObjectMapperDeserializationContext context) {
        CallSite[] callSiteArray = JohnzonMapper.$getCallSiteArray();
        return callSiteArray[1].call((Object)this.deserializer, context);
    }

    public Object serialize(ObjectMapperSerializationContext context) {
        Reference<ObjectMapperSerializationContext> context2 = new Reference<ObjectMapperSerializationContext>(context);
        CallSite[] callSiteArray = JohnzonMapper.$getCallSiteArray();
        Object object = callSiteArray[2].call(context2.get());
        Reference<Object> mapper = new Reference<Object>(callSiteArray[3].call(this.factory, callSiteArray[4].call(object), callSiteArray[5].call(context2.get())));
        return callSiteArray[6].call(callSiteArray[7].callConstructor(StringWriter.class), new GeneratedClosure(this, this, context2, mapper){
            private /* synthetic */ Reference context;
            private /* synthetic */ Reference mapper;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _serialize_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.context = reference2 = context;
                this.mapper = reference = mapper;
            }

            public Object doCall(Object out) {
                CallSite[] callSiteArray = _serialize_closure1.$getCallSiteArray();
                callSiteArray[0].call(this.mapper.get(), callSiteArray[1].call(this.context.get()), out);
                return callSiteArray[2].call(out);
            }

            public ObjectMapperSerializationContext getContext() {
                CallSite[] callSiteArray = _serialize_closure1.$getCallSiteArray();
                return (ObjectMapperSerializationContext)ScriptBytecodeAdapter.castToType(this.context.get(), ObjectMapperSerializationContext.class);
            }

            public Object getMapper() {
                CallSite[] callSiteArray = _serialize_closure1.$getCallSiteArray();
                return this.mapper.get();
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
                stringArray[0] = "writeObject";
                stringArray[1] = "getObjectToSerialize";
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
        CallSite[] callSiteArray = JohnzonMapper.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(JohnzonMapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = JohnzonMapper.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, JohnzonMapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = JohnzonMapper.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(JohnzonMapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != JohnzonMapper.class) {
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
        CallSite[] callSiteArray = JohnzonMapper.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        JohnzonMapper.__$swapInit();
        __timeStamp__239_neverHappen1702030729294 = l2 = 0L;
        __timeStamp = l = 1702030729294L;
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
        JohnzonMapper.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(JohnzonMapper.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = JohnzonMapper.$createCallSiteArray();
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

