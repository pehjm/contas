/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.xml.mapping;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.common.mapper.DataToDeserialize;
import io.restassured.common.mapper.ObjectDeserializationContext;
import io.restassured.common.mapper.resolver.ObjectMapperResolver;
import io.restassured.internal.common.ObjectDeserializationContextImpl;
import io.restassured.internal.path.xml.mapping.XmlPathJaxbObjectDeserializer;
import io.restassured.path.xml.config.XmlParserType;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.path.xml.mapper.factory.JAXBObjectMapperFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import org.apache.commons.lang3.Validate;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class XmlObjectDeserializer
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030715475;
    private static /* synthetic */ SoftReference $callSiteArray;

    public XmlObjectDeserializer() {
        MetaClass metaClass;
        CallSite[] callSiteArray = XmlObjectDeserializer.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    /*
     * WARNING - void declaration
     */
    public static <T> T deserialize(String xml, Class<T> cls, XmlPathConfig xmlPathConfig) {
        void var2_2;
        Reference<String> xml2 = new Reference<String>(xml);
        Reference<void> xmlPathConfig2 = new Reference<void>(var2_2);
        CallSite[] callSiteArray = XmlObjectDeserializer.$getCallSiteArray();
        callSiteArray[0].call(Validate.class, (XmlPathConfig)xmlPathConfig2.get(), "XmlPath configuration wasn't specified, cannot deserialize.");
        Object deserializationCtx = callSiteArray[1].callConstructor(ObjectDeserializationContextImpl.class);
        Object mapperType = callSiteArray[2].call((XmlPathConfig)xmlPathConfig2.get());
        Class<T> clazz = cls;
        ScriptBytecodeAdapter.setProperty(clazz, null, deserializationCtx, "type");
        Object object = callSiteArray[3].call((XmlPathConfig)xmlPathConfig2.get());
        ScriptBytecodeAdapter.setProperty(object, null, deserializationCtx, "charset");
        1 var10_10 = new 1(XmlObjectDeserializer.class, xml2, xmlPathConfig2);
        ScriptBytecodeAdapter.setProperty(var10_10, null, deserializationCtx, "dataToDeserialize");
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[4].call((XmlPathConfig)xmlPathConfig2.get()))) {
                return (T)callSiteArray[5].call(callSiteArray[6].call((XmlPathConfig)xmlPathConfig2.get()), deserializationCtx);
            }
            if (ScriptBytecodeAdapter.compareNotEqual(mapperType, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call((XmlPathConfig)xmlPathConfig2.get()))) {
                XmlParserType mapperTypeToUse = (XmlParserType)((Object)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(mapperType, null) ? callSiteArray[8].call((XmlPathConfig)xmlPathConfig2.get()) : mapperType, XmlParserType.class));
                return (T)callSiteArray[9].callStatic(XmlObjectDeserializer.class, deserializationCtx, (Object)mapperTypeToUse, (XmlPathConfig)xmlPathConfig2.get());
            }
        } else {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[10].call((XmlPathConfig)xmlPathConfig2.get()))) {
                return (T)callSiteArray[11].call(callSiteArray[12].call((XmlPathConfig)xmlPathConfig2.get()), deserializationCtx);
            }
            if (ScriptBytecodeAdapter.compareNotEqual(mapperType, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[13].call((XmlPathConfig)xmlPathConfig2.get()))) {
                XmlParserType mapperTypeToUse = (XmlParserType)((Object)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(mapperType, null) ? callSiteArray[14].call((XmlPathConfig)xmlPathConfig2.get()) : mapperType, XmlParserType.class));
                return (T)callSiteArray[15].callStatic(XmlObjectDeserializer.class, deserializationCtx, (Object)mapperTypeToUse, (XmlPathConfig)xmlPathConfig2.get());
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[16].callStatic(ObjectMapperResolver.class))) {
            return (T)callSiteArray[17].callStatic(XmlObjectDeserializer.class, deserializationCtx, callSiteArray[18].call((XmlPathConfig)xmlPathConfig2.get()));
        }
        throw (Throwable)callSiteArray[19].callConstructor(IllegalStateException.class, "Cannot deserialize object because no XML deserializer found in classpath. Please put JAXB in the classpath.");
    }

    private static <T> T deserializeWithObjectMapper(ObjectDeserializationContext ctx, XmlParserType mapperType, XmlPathConfig config) {
        CallSite[] callSiteArray = XmlObjectDeserializer.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[20].callGetProperty(XmlParserType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[21].callStatic(ObjectMapperResolver.class))) {
                return (T)callSiteArray[22].callStatic(XmlObjectDeserializer.class, ctx, callSiteArray[23].call(config));
            }
            Object lowerCase = callSiteArray[24].call(callSiteArray[25].call((Object)mapperType));
            throw (Throwable)callSiteArray[26].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{mapperType, lowerCase}, new String[]{"Cannot deserialize object using ", " because ", " doesn't exist in the classpath."}));
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[27].callGetProperty(XmlParserType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[28].callStatic(ObjectMapperResolver.class))) {
            return (T)callSiteArray[29].callStatic(XmlObjectDeserializer.class, ctx, callSiteArray[30].call(config));
        }
        Object lowerCase = callSiteArray[31].call(callSiteArray[32].call((Object)mapperType));
        throw (Throwable)callSiteArray[33].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{mapperType, lowerCase}, new String[]{"Cannot deserialize object using ", " because ", " doesn't exist in the classpath."}));
    }

    public static Object deserializeWithJaxb(ObjectDeserializationContext ctx, JAXBObjectMapperFactory factory) {
        CallSite[] callSiteArray = XmlObjectDeserializer.$getCallSiteArray();
        return callSiteArray[34].call(callSiteArray[35].callConstructor(XmlPathJaxbObjectDeserializer.class, factory), ctx);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = XmlObjectDeserializer.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(XmlObjectDeserializer.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = XmlObjectDeserializer.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, XmlObjectDeserializer.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = XmlObjectDeserializer.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(XmlObjectDeserializer.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != XmlObjectDeserializer.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    @Override
    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    @Override
    public /* synthetic */ void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    @Override
    public /* synthetic */ Object invokeMethod(String string, Object object) {
        return this.getMetaClass().invokeMethod((Object)this, string, object);
    }

    @Override
    public /* synthetic */ Object getProperty(String string) {
        return this.getMetaClass().getProperty(this, string);
    }

    @Override
    public /* synthetic */ void setProperty(String string, Object object) {
        this.getMetaClass().setProperty(this, string, object);
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = XmlObjectDeserializer.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        XmlObjectDeserializer.__$swapInit();
        __timeStamp__239_neverHappen1702030715475 = l2 = 0L;
        __timeStamp = l = 1702030715475L;
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
        stringArray[0] = "notNull";
        stringArray[1] = "<$constructor$>";
        stringArray[2] = "defaultParserType";
        stringArray[3] = "charset";
        stringArray[4] = "hasDefaultDeserializer";
        stringArray[5] = "deserialize";
        stringArray[6] = "defaultDeserializer";
        stringArray[7] = "hasDefaultParserType";
        stringArray[8] = "defaultParserType";
        stringArray[9] = "deserializeWithObjectMapper";
        stringArray[10] = "hasDefaultDeserializer";
        stringArray[11] = "deserialize";
        stringArray[12] = "defaultDeserializer";
        stringArray[13] = "hasDefaultParserType";
        stringArray[14] = "defaultParserType";
        stringArray[15] = "deserializeWithObjectMapper";
        stringArray[16] = "isJAXBInClassPath";
        stringArray[17] = "deserializeWithJaxb";
        stringArray[18] = "jaxbObjectMapperFactory";
        stringArray[19] = "<$constructor$>";
        stringArray[20] = "JAXB";
        stringArray[21] = "isJAXBInClassPath";
        stringArray[22] = "deserializeWithJaxb";
        stringArray[23] = "jaxbObjectMapperFactory";
        stringArray[24] = "toLowerCase";
        stringArray[25] = "toString";
        stringArray[26] = "<$constructor$>";
        stringArray[27] = "JAXB";
        stringArray[28] = "isJAXBInClassPath";
        stringArray[29] = "deserializeWithJaxb";
        stringArray[30] = "jaxbObjectMapperFactory";
        stringArray[31] = "toLowerCase";
        stringArray[32] = "toString";
        stringArray[33] = "<$constructor$>";
        stringArray[34] = "deserialize";
        stringArray[35] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[36];
        XmlObjectDeserializer.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(XmlObjectDeserializer.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = XmlObjectDeserializer.$createCallSiteArray();
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

    public class 1
    implements DataToDeserialize,
    GroovyObject {
        public /* synthetic */ Reference xmlPathConfig;
        public /* synthetic */ Reference xml;
        public /* synthetic */ Class this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 1(Class p0, Reference p1, Reference p2) {
            MetaClass metaClass;
            Reference reference;
            Reference reference2;
            Class clazz;
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            this.this$0 = clazz = p0;
            this.xmlPathConfig = reference2 = p2;
            this.xml = reference = p1;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        public String asString() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return (String)ScriptBytecodeAdapter.castToType(this.xml.get(), String.class);
        }

        public byte[] asByteArray() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return (byte[])ScriptBytecodeAdapter.castToType(callSiteArray[0].call(this.xml.get(), callSiteArray[1].call(this.xmlPathConfig.get())), byte[].class);
        }

        public InputStream asInputStream() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[2].callConstructor(ByteArrayInputStream.class, callSiteArray[3].callCurrent(this)), InputStream.class);
            }
            return (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[4].callConstructor(ByteArrayInputStream.class, (Object)this.asByteArray()), InputStream.class);
        }

        public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$1(String name, Object value) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$1(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(1.class, XmlObjectDeserializer.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, 1.class, XmlObjectDeserializer.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(1.class, XmlObjectDeserializer.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 1.class) {
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
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            1.__$swapInit();
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
            stringArray[0] = "getBytes";
            stringArray[1] = "charset";
            stringArray[2] = "<$constructor$>";
            stringArray[3] = "asByteArray";
            stringArray[4] = "<$constructor$>";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[5];
            1.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(1.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 1.$createCallSiteArray();
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
}

