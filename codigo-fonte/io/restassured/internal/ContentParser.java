/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.xml.XmlSlurper;
import groovy.xml.slurpersupport.GPathResult;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.XmlConfig;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.http.CharsetExtractor;
import io.restassured.internal.path.json.ConfigurableJsonSlurper;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.lang.ref.SoftReference;
import org.ccil.cowan.tagsoup.Parser;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class ContentParser
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729351;
    private static /* synthetic */ SoftReference $callSiteArray;

    public ContentParser() {
        MetaClass metaClass;
        CallSite[] callSiteArray = ContentParser.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public Object parse(Response response, ResponseParserRegistrar rpr, RestAssuredConfig config, boolean parseAsString) {
        CallSite[] callSiteArray = ContentParser.$getCallSiteArray();
        io.restassured.parsing.Parser parser = (io.restassured.parsing.Parser)((Object)ScriptBytecodeAdapter.castToType(callSiteArray[0].call((Object)rpr, callSiteArray[1].call(response)), io.restassured.parsing.Parser.class));
        Object content = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual((Object)parser, null)) {
                Object object;
                content = object = callSiteArray[2].call(response);
            } else {
                io.restassured.parsing.Parser parser2 = parser;
                if (ScriptBytecodeAdapter.isCase((Object)parser2, callSiteArray[3].callGetProperty(io.restassured.parsing.Parser.class))) {
                    Object slurper = callSiteArray[4].callConstructor(ConfigurableJsonSlurper.class, callSiteArray[5].call(callSiteArray[6].call(config)));
                    if (parseAsString) {
                        Object object;
                        content = object = callSiteArray[7].call(slurper, callSiteArray[8].call(response));
                    } else {
                        Object object;
                        Object object2 = callSiteArray[9].call(CharsetExtractor.class, callSiteArray[10].call(response));
                        Object charset = DefaultTypeTransformation.booleanUnbox(object2) ? object2 : callSiteArray[11].call(callSiteArray[12].call(config), callSiteArray[13].call(response));
                        content = object = callSiteArray[14].call(slurper, callSiteArray[15].callConstructor(InputStreamReader.class, callSiteArray[16].callConstructor(BufferedInputStream.class, callSiteArray[17].call(response)), charset));
                    }
                } else if (ScriptBytecodeAdapter.isCase((Object)parser2, callSiteArray[18].callGetProperty(io.restassured.parsing.Parser.class))) {
                    Object object;
                    Object xmlConfig = callSiteArray[19].call(config);
                    Object slurper = callSiteArray[20].callStatic(ContentParser.class, callSiteArray[21].callConstructor(XmlSlurper.class, callSiteArray[22].call(xmlConfig), callSiteArray[23].call(xmlConfig), callSiteArray[24].call(xmlConfig)), xmlConfig);
                    content = object = callSiteArray[25].callStatic(ContentParser.class, callSiteArray[26].callStatic(ContentParser.class, slurper, response, parseAsString), xmlConfig);
                } else if (ScriptBytecodeAdapter.isCase((Object)parser2, callSiteArray[27].callGetProperty(io.restassured.parsing.Parser.class))) {
                    Object object;
                    Object xmlConfig = callSiteArray[28].call(config);
                    Object slurper = callSiteArray[29].callStatic(ContentParser.class, callSiteArray[30].callConstructor(XmlSlurper.class, callSiteArray[31].callConstructor(Parser.class)), xmlConfig);
                    content = object = callSiteArray[32].callStatic(ContentParser.class, callSiteArray[33].callStatic(ContentParser.class, slurper, response, parseAsString), xmlConfig);
                } else {
                    Object object;
                    if (ScriptBytecodeAdapter.isCase((Object)parser2, callSiteArray[34].callGetProperty(io.restassured.parsing.Parser.class))) {
                        // empty if block
                    }
                    content = object = callSiteArray[35].call(response);
                }
            }
        } else if (ScriptBytecodeAdapter.compareEqual((Object)parser, null)) {
            Object object;
            content = object = callSiteArray[36].call(response);
        } else {
            io.restassured.parsing.Parser parser3 = parser;
            if (ScriptBytecodeAdapter.isCase((Object)parser3, callSiteArray[37].callGetProperty(io.restassured.parsing.Parser.class))) {
                Object slurper = callSiteArray[38].callConstructor(ConfigurableJsonSlurper.class, callSiteArray[39].call(callSiteArray[40].call(config)));
                if (parseAsString) {
                    Object object;
                    content = object = callSiteArray[41].call(slurper, callSiteArray[42].call(response));
                } else {
                    Object object;
                    Object object3 = callSiteArray[43].call(CharsetExtractor.class, callSiteArray[44].call(response));
                    Object charset = DefaultTypeTransformation.booleanUnbox(object3) ? object3 : callSiteArray[45].call(callSiteArray[46].call(config), callSiteArray[47].call(response));
                    content = object = callSiteArray[48].call(slurper, callSiteArray[49].callConstructor(InputStreamReader.class, callSiteArray[50].callConstructor(BufferedInputStream.class, callSiteArray[51].call(response)), charset));
                }
            } else if (ScriptBytecodeAdapter.isCase((Object)parser3, callSiteArray[52].callGetProperty(io.restassured.parsing.Parser.class))) {
                Object object;
                Object xmlConfig = callSiteArray[53].call(config);
                Object slurper = callSiteArray[54].callStatic(ContentParser.class, callSiteArray[55].callConstructor(XmlSlurper.class, callSiteArray[56].call(xmlConfig), callSiteArray[57].call(xmlConfig), callSiteArray[58].call(xmlConfig)), xmlConfig);
                content = object = callSiteArray[59].callStatic(ContentParser.class, callSiteArray[60].callStatic(ContentParser.class, slurper, response, parseAsString), xmlConfig);
            } else if (ScriptBytecodeAdapter.isCase((Object)parser3, callSiteArray[61].callGetProperty(io.restassured.parsing.Parser.class))) {
                Object object;
                Object xmlConfig = callSiteArray[62].call(config);
                Object slurper = callSiteArray[63].callStatic(ContentParser.class, callSiteArray[64].callConstructor(XmlSlurper.class, callSiteArray[65].callConstructor(Parser.class)), xmlConfig);
                content = object = callSiteArray[66].callStatic(ContentParser.class, callSiteArray[67].callStatic(ContentParser.class, slurper, response, parseAsString), xmlConfig);
            } else {
                Object object;
                if (ScriptBytecodeAdapter.isCase((Object)parser3, callSiteArray[68].callGetProperty(io.restassured.parsing.Parser.class))) {
                    // empty if block
                }
                content = object = callSiteArray[69].call(response);
            }
        }
        return content;
    }

    private static GPathResult parseXml(XmlSlurper xmlSlurper, ResponseBodyExtractionOptions response, boolean parseAsString) {
        CallSite[] callSiteArray = ContentParser.$getCallSiteArray();
        if (parseAsString) {
            return (GPathResult)ScriptBytecodeAdapter.castToType(callSiteArray[70].call((Object)xmlSlurper, callSiteArray[71].call((Object)response, true)), GPathResult.class);
        }
        return (GPathResult)ScriptBytecodeAdapter.castToType(callSiteArray[72].call((Object)xmlSlurper, callSiteArray[73].call(response)), GPathResult.class);
    }

    private static GPathResult declareNamespacesIfNeeded(GPathResult gPathResult, XmlConfig xmlConfig) {
        CallSite[] callSiteArray = ContentParser.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[74].call(xmlConfig))) {
            callSiteArray[75].call((Object)gPathResult, callSiteArray[76].call(xmlConfig));
        }
        return gPathResult;
    }

    private static XmlSlurper configureXmlSlurper(XmlSlurper xmlSlurper, XmlConfig xmlConfig) {
        Reference<XmlSlurper> xmlSlurper2 = new Reference<XmlSlurper>(xmlSlurper);
        CallSite[] callSiteArray = ContentParser.$getCallSiteArray();
        Object features = callSiteArray[77].call(xmlConfig);
        Object properties = callSiteArray[78].call(xmlConfig);
        callSiteArray[79].call(properties, new GeneratedClosure(ContentParser.class, ContentParser.class, xmlSlurper2){
            private /* synthetic */ Reference xmlSlurper;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _configureXmlSlurper_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.xmlSlurper = reference = xmlSlurper;
            }

            public Object doCall(Object name, Object value) {
                CallSite[] callSiteArray = _configureXmlSlurper_closure1.$getCallSiteArray();
                return callSiteArray[0].call(this.xmlSlurper.get(), name, value);
            }

            public Object call(Object name, Object value) {
                CallSite[] callSiteArray = _configureXmlSlurper_closure1.$getCallSiteArray();
                return callSiteArray[1].callCurrent(this, name, value);
            }

            public XmlSlurper getXmlSlurper() {
                CallSite[] callSiteArray = _configureXmlSlurper_closure1.$getCallSiteArray();
                return (XmlSlurper)ScriptBytecodeAdapter.castToType(this.xmlSlurper.get(), XmlSlurper.class);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _configureXmlSlurper_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _configureXmlSlurper_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _configureXmlSlurper_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "setProperty";
                stringArray[1] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _configureXmlSlurper_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_configureXmlSlurper_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _configureXmlSlurper_closure1.$createCallSiteArray();
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
        callSiteArray[80].call(features, new GeneratedClosure(ContentParser.class, ContentParser.class, xmlSlurper2){
            private /* synthetic */ Reference xmlSlurper;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _configureXmlSlurper_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.xmlSlurper = reference = xmlSlurper;
            }

            public Object doCall(Object name, Object isEnabled) {
                CallSite[] callSiteArray = _configureXmlSlurper_closure2.$getCallSiteArray();
                return callSiteArray[0].call(this.xmlSlurper.get(), name, isEnabled);
            }

            public Object call(Object name, Object isEnabled) {
                CallSite[] callSiteArray = _configureXmlSlurper_closure2.$getCallSiteArray();
                return callSiteArray[1].callCurrent(this, name, isEnabled);
            }

            public XmlSlurper getXmlSlurper() {
                CallSite[] callSiteArray = _configureXmlSlurper_closure2.$getCallSiteArray();
                return (XmlSlurper)ScriptBytecodeAdapter.castToType(this.xmlSlurper.get(), XmlSlurper.class);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _configureXmlSlurper_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _configureXmlSlurper_closure2.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _configureXmlSlurper_closure2.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "setFeature";
                stringArray[1] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _configureXmlSlurper_closure2.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_configureXmlSlurper_closure2.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _configureXmlSlurper_closure2.$createCallSiteArray();
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
        return xmlSlurper2.get();
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = ContentParser.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ContentParser.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = ContentParser.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ContentParser.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = ContentParser.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ContentParser.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ContentParser.class) {
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
        CallSite[] callSiteArray = ContentParser.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        ContentParser.__$swapInit();
        __timeStamp__239_neverHappen1702030729351 = l2 = 0L;
        __timeStamp = l = 1702030729351L;
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
        stringArray[0] = "getParser";
        stringArray[1] = "contentType";
        stringArray[2] = "asInputStream";
        stringArray[3] = "JSON";
        stringArray[4] = "<$constructor$>";
        stringArray[5] = "numberReturnType";
        stringArray[6] = "getJsonConfig";
        stringArray[7] = "parseText";
        stringArray[8] = "asString";
        stringArray[9] = "getCharsetFromContentType";
        stringArray[10] = "getContentType";
        stringArray[11] = "defaultCharsetForContentType";
        stringArray[12] = "getDecoderConfig";
        stringArray[13] = "getContentType";
        stringArray[14] = "parse";
        stringArray[15] = "<$constructor$>";
        stringArray[16] = "<$constructor$>";
        stringArray[17] = "asInputStream";
        stringArray[18] = "XML";
        stringArray[19] = "getXmlConfig";
        stringArray[20] = "configureXmlSlurper";
        stringArray[21] = "<$constructor$>";
        stringArray[22] = "isValidating";
        stringArray[23] = "isNamespaceAware";
        stringArray[24] = "isAllowDocTypeDeclaration";
        stringArray[25] = "declareNamespacesIfNeeded";
        stringArray[26] = "parseXml";
        stringArray[27] = "HTML";
        stringArray[28] = "getXmlConfig";
        stringArray[29] = "configureXmlSlurper";
        stringArray[30] = "<$constructor$>";
        stringArray[31] = "<$constructor$>";
        stringArray[32] = "declareNamespacesIfNeeded";
        stringArray[33] = "parseXml";
        stringArray[34] = "TEXT";
        stringArray[35] = "asInputStream";
        stringArray[36] = "asInputStream";
        stringArray[37] = "JSON";
        stringArray[38] = "<$constructor$>";
        stringArray[39] = "numberReturnType";
        stringArray[40] = "getJsonConfig";
        stringArray[41] = "parseText";
        stringArray[42] = "asString";
        stringArray[43] = "getCharsetFromContentType";
        stringArray[44] = "getContentType";
        stringArray[45] = "defaultCharsetForContentType";
        stringArray[46] = "getDecoderConfig";
        stringArray[47] = "getContentType";
        stringArray[48] = "parse";
        stringArray[49] = "<$constructor$>";
        stringArray[50] = "<$constructor$>";
        stringArray[51] = "asInputStream";
        stringArray[52] = "XML";
        stringArray[53] = "getXmlConfig";
        stringArray[54] = "configureXmlSlurper";
        stringArray[55] = "<$constructor$>";
        stringArray[56] = "isValidating";
        stringArray[57] = "isNamespaceAware";
        stringArray[58] = "isAllowDocTypeDeclaration";
        stringArray[59] = "declareNamespacesIfNeeded";
        stringArray[60] = "parseXml";
        stringArray[61] = "HTML";
        stringArray[62] = "getXmlConfig";
        stringArray[63] = "configureXmlSlurper";
        stringArray[64] = "<$constructor$>";
        stringArray[65] = "<$constructor$>";
        stringArray[66] = "declareNamespacesIfNeeded";
        stringArray[67] = "parseXml";
        stringArray[68] = "TEXT";
        stringArray[69] = "asInputStream";
        stringArray[70] = "parseText";
        stringArray[71] = "asString";
        stringArray[72] = "parse";
        stringArray[73] = "asInputStream";
        stringArray[74] = "isNamespaceAware";
        stringArray[75] = "declareNamespace";
        stringArray[76] = "declaredNamespaces";
        stringArray[77] = "features";
        stringArray[78] = "properties";
        stringArray[79] = "each";
        stringArray[80] = "each";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[81];
        ContentParser.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(ContentParser.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ContentParser.$createCallSiteArray();
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

