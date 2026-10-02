/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.xml.bind.JAXBContext
 *  javax.xml.bind.Marshaller
 */
package io.restassured.internal.mapping;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.path.xml.mapping.XmlPathJaxbObjectDeserializer;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.path.xml.mapper.factory.JAXBObjectMapperFactory;
import io.restassured.path.xml.mapping.XmlPathObjectDeserializer;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class JaxbMapper
implements ObjectMapper,
GroovyObject {
    private final JAXBObjectMapperFactory factory;
    private XmlPathObjectDeserializer deserializer;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729281;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public JaxbMapper(JAXBObjectMapperFactory jAXBObjectMapperFactory) {
        void factory;
        MetaClass metaClass;
        CallSite[] callSiteArray = JaxbMapper.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var4_4 = factory;
        this.factory = (JAXBObjectMapperFactory)ScriptBytecodeAdapter.castToType(var4_4, JAXBObjectMapperFactory.class);
        Object object = callSiteArray[0].callConstructor(XmlPathJaxbObjectDeserializer.class, factory);
        this.deserializer = (XmlPathObjectDeserializer)ScriptBytecodeAdapter.castToType(object, XmlPathObjectDeserializer.class);
    }

    public Object deserialize(ObjectMapperDeserializationContext context) {
        CallSite[] callSiteArray = JaxbMapper.$getCallSiteArray();
        return callSiteArray[1].call((Object)this.deserializer, context);
    }

    public Object serialize(ObjectMapperSerializationContext context) {
        CallSite[] callSiteArray = JaxbMapper.$getCallSiteArray();
        Object object = callSiteArray[2].call(context);
        Object charset = callSiteArray[3].call(context);
        JAXBContext jaxbContext = (JAXBContext)ScriptBytecodeAdapter.castToType(callSiteArray[4].call(this.factory, callSiteArray[5].call(object), charset), JAXBContext.class);
        Marshaller marshaller = (Marshaller)ScriptBytecodeAdapter.castToType(callSiteArray[6].call(jaxbContext), Marshaller.class);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(charset, null)) {
                callSiteArray[7].call(marshaller, callSiteArray[8].callGetProperty(Marshaller.class), charset);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(charset, null)) {
            callSiteArray[9].call(marshaller, callSiteArray[10].callGetProperty(Marshaller.class), charset);
        }
        StringWriter sw = (StringWriter)ScriptBytecodeAdapter.castToType(callSiteArray[11].callConstructor(StringWriter.class), StringWriter.class);
        callSiteArray[12].call(marshaller, object, sw);
        return callSiteArray[13].call(sw);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = JaxbMapper.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(JaxbMapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = JaxbMapper.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, JaxbMapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = JaxbMapper.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(JaxbMapper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != JaxbMapper.class) {
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
        CallSite[] callSiteArray = JaxbMapper.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        JaxbMapper.__$swapInit();
        __timeStamp__239_neverHappen1702030729281 = l2 = 0L;
        __timeStamp = l = 1702030729281L;
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
        stringArray[3] = "getCharset";
        stringArray[4] = "create";
        stringArray[5] = "getClass";
        stringArray[6] = "createMarshaller";
        stringArray[7] = "setProperty";
        stringArray[8] = "JAXB_ENCODING";
        stringArray[9] = "setProperty";
        stringArray[10] = "JAXB_ENCODING";
        stringArray[11] = "<$constructor$>";
        stringArray[12] = "marshal";
        stringArray[13] = "toString";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[14];
        JaxbMapper.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(JaxbMapper.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = JaxbMapper.$createCallSiteArray();
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

