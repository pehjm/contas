/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.xml.bind.JAXBContext
 *  javax.xml.bind.JAXBElement
 *  javax.xml.bind.Unmarshaller
 *  javax.xml.bind.annotation.XmlRootElement
 */
package io.restassured.internal.path.xml.mapping;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.common.mapper.ObjectDeserializationContext;
import io.restassured.path.xml.mapper.factory.JAXBObjectMapperFactory;
import io.restassured.path.xml.mapping.XmlPathObjectDeserializer;
import java.io.StringReader;
import java.lang.ref.SoftReference;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class XmlPathJaxbObjectDeserializer
implements XmlPathObjectDeserializer,
GroovyObject {
    private final JAXBObjectMapperFactory factory;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030715482;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public XmlPathJaxbObjectDeserializer(JAXBObjectMapperFactory jAXBObjectMapperFactory) {
        void factory;
        MetaClass metaClass;
        CallSite[] callSiteArray = XmlPathJaxbObjectDeserializer.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var4_4 = factory;
        this.factory = (JAXBObjectMapperFactory)ScriptBytecodeAdapter.castToType(var4_4, JAXBObjectMapperFactory.class);
    }

    public Object deserialize(ObjectDeserializationContext context) {
        CallSite[] callSiteArray = XmlPathJaxbObjectDeserializer.$getCallSiteArray();
        Object cls = callSiteArray[0].call(context);
        Object object = callSiteArray[1].call(callSiteArray[2].call(context));
        JAXBContext jaxbContext = (JAXBContext)ScriptBytecodeAdapter.castToType(callSiteArray[3].call(this.factory, cls, callSiteArray[4].call(context)), JAXBContext.class);
        Unmarshaller unmarshaller = (Unmarshaller)ScriptBytecodeAdapter.castToType(callSiteArray[5].call(jaxbContext), Unmarshaller.class);
        Object reader = callSiteArray[6].callConstructor(StringReader.class, object);
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call(cls, XmlRootElement.class))) {
            return callSiteArray[8].call((Object)unmarshaller, reader);
        }
        JAXBElement jaxbElement = (JAXBElement)ScriptBytecodeAdapter.castToType(callSiteArray[9].call(unmarshaller, callSiteArray[10].callConstructor(StreamSource.class, reader), cls), JAXBElement.class);
        return callSiteArray[11].call(jaxbElement);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = XmlPathJaxbObjectDeserializer.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(XmlPathJaxbObjectDeserializer.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = XmlPathJaxbObjectDeserializer.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, XmlPathJaxbObjectDeserializer.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = XmlPathJaxbObjectDeserializer.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(XmlPathJaxbObjectDeserializer.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != XmlPathJaxbObjectDeserializer.class) {
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
        CallSite[] callSiteArray = XmlPathJaxbObjectDeserializer.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        XmlPathJaxbObjectDeserializer.__$swapInit();
        __timeStamp__239_neverHappen1702030715482 = l2 = 0L;
        __timeStamp = l = 1702030715482L;
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
        stringArray[0] = "getType";
        stringArray[1] = "asString";
        stringArray[2] = "getDataToDeserialize";
        stringArray[3] = "create";
        stringArray[4] = "getCharset";
        stringArray[5] = "createUnmarshaller";
        stringArray[6] = "<$constructor$>";
        stringArray[7] = "isAnnotationPresent";
        stringArray[8] = "unmarshal";
        stringArray[9] = "unmarshal";
        stringArray[10] = "<$constructor$>";
        stringArray[11] = "getValue";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[12];
        XmlPathJaxbObjectDeserializer.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(XmlPathJaxbObjectDeserializer.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = XmlPathJaxbObjectDeserializer.$createCallSiteArray();
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

