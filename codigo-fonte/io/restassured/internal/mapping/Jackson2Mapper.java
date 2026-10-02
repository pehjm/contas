/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.core.JsonEncoding
 *  com.fasterxml.jackson.core.JsonGenerator
 *  com.fasterxml.jackson.databind.ObjectMapper
 */
package io.restassured.internal.mapping;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.path.json.mapping.JsonPathJackson2ObjectDeserializer;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.path.json.mapping.JsonPathObjectDeserializer;
import java.io.ByteArrayOutputStream;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class Jackson2Mapper
implements io.restassured.mapper.ObjectMapper,
GroovyObject {
    private final Jackson2ObjectMapperFactory factory;
    private JsonPathObjectDeserializer deserializer;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729368;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public Jackson2Mapper(Jackson2ObjectMapperFactory jackson2ObjectMapperFactory) {
        void factory;
        MetaClass metaClass;
        CallSite[] callSiteArray = Jackson2Mapper.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var4_4 = factory;
        this.factory = (Jackson2ObjectMapperFactory)ScriptBytecodeAdapter.castToType(var4_4, Jackson2ObjectMapperFactory.class);
        Object object = callSiteArray[0].callConstructor(JsonPathJackson2ObjectDeserializer.class, factory);
        this.deserializer = (JsonPathObjectDeserializer)ScriptBytecodeAdapter.castToType(object, JsonPathObjectDeserializer.class);
    }

    private ObjectMapper createJackson2ObjectMapper(Class cls, String charset) {
        CallSite[] callSiteArray = Jackson2Mapper.$getCallSiteArray();
        return (ObjectMapper)ScriptBytecodeAdapter.castToType(callSiteArray[1].call(this.factory, cls, charset), ObjectMapper.class);
    }

    public String serialize(ObjectMapperSerializationContext context) {
        CallSite[] callSiteArray = Jackson2Mapper.$getCallSiteArray();
        Object object = callSiteArray[2].call(context);
        JsonEncoding jsonEncoding = (JsonEncoding)ScriptBytecodeAdapter.castToType(callSiteArray[3].callCurrent((GroovyObject)this, callSiteArray[4].call(context)), JsonEncoding.class);
        Object mapper = callSiteArray[5].callCurrent(this, callSiteArray[6].call(object), callSiteArray[7].call(context));
        Object stream = callSiteArray[8].callConstructor(ByteArrayOutputStream.class);
        JsonGenerator jsonGenerator = (JsonGenerator)ScriptBytecodeAdapter.castToType(callSiteArray[9].call(callSiteArray[10].call(mapper), stream, jsonEncoding), JsonGenerator.class);
        callSiteArray[11].call(mapper, jsonGenerator, object);
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[12].call(stream, callSiteArray[13].call(jsonEncoding)), String.class);
    }

    public Object deserialize(ObjectMapperDeserializationContext context) {
        CallSite[] callSiteArray = Jackson2Mapper.$getCallSiteArray();
        return callSiteArray[14].call((Object)this.deserializer, context);
    }

    private JsonEncoding getEncoding(String charset) {
        Object foundEncoding;
        block3: {
            CallSite[] callSiteArray;
            block4: {
                callSiteArray = Jackson2Mapper.$getCallSiteArray();
                foundEncoding = callSiteArray[15].callGetProperty(JsonEncoding.class);
                if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) break block4;
                if (!ScriptBytecodeAdapter.compareNotEqual(charset, null)) break block3;
                JsonEncoding encoding = null;
                Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(callSiteArray[16].call(callSiteArray[17].call(JsonEncoding.class)), Iterator.class);
                while (iterator.hasNext()) {
                    encoding = (JsonEncoding)ScriptBytecodeAdapter.castToType(iterator.next(), JsonEncoding.class);
                    if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[18].call((Object)charset, callSiteArray[19].call(encoding)))) continue;
                    JsonEncoding jsonEncoding = encoding;
                    foundEncoding = jsonEncoding;
                    break block3;
                }
                break block3;
            }
            if (ScriptBytecodeAdapter.compareNotEqual(charset, null)) {
                JsonEncoding encoding = null;
                Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(callSiteArray[20].call(callSiteArray[21].call(JsonEncoding.class)), Iterator.class);
                while (iterator.hasNext()) {
                    encoding = (JsonEncoding)ScriptBytecodeAdapter.castToType(iterator.next(), JsonEncoding.class);
                    if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[22].call((Object)charset, callSiteArray[23].call(encoding)))) continue;
                    JsonEncoding jsonEncoding = encoding;
                    foundEncoding = jsonEncoding;
                    break;
                }
            }
        }
        return (JsonEncoding)ScriptBytecodeAdapter.castToType(foundEncoding, JsonEncoding.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = Jackson2Mapper.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(Jackson2Mapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = Jackson2Mapper.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, Jackson2Mapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = Jackson2Mapper.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(Jackson2Mapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != Jackson2Mapper.class) {
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
        CallSite[] callSiteArray = Jackson2Mapper.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        Jackson2Mapper.__$swapInit();
        __timeStamp__239_neverHappen1702030729368 = l2 = 0L;
        __timeStamp = l = 1702030729368L;
    }

    public /* synthetic */ ObjectMapper this$2$createJackson2ObjectMapper(Class clazz, String string) {
        return this.createJackson2ObjectMapper(clazz, string);
    }

    public /* synthetic */ JsonEncoding this$2$getEncoding(String string) {
        return this.getEncoding(string);
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
        stringArray[1] = "create";
        stringArray[2] = "getObjectToSerialize";
        stringArray[3] = "getEncoding";
        stringArray[4] = "getCharset";
        stringArray[5] = "createJackson2ObjectMapper";
        stringArray[6] = "getClass";
        stringArray[7] = "getCharset";
        stringArray[8] = "<$constructor$>";
        stringArray[9] = "createJsonGenerator";
        stringArray[10] = "getJsonFactory";
        stringArray[11] = "writeValue";
        stringArray[12] = "toString";
        stringArray[13] = "getJavaName";
        stringArray[14] = "deserialize";
        stringArray[15] = "UTF8";
        stringArray[16] = "iterator";
        stringArray[17] = "values";
        stringArray[18] = "equals";
        stringArray[19] = "getJavaName";
        stringArray[20] = "iterator";
        stringArray[21] = "values";
        stringArray[22] = "equals";
        stringArray[23] = "getJavaName";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[24];
        Jackson2Mapper.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(Jackson2Mapper.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = Jackson2Mapper.$createCallSiteArray();
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

