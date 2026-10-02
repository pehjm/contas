/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.assertion;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.http.ContentTypeExtractor;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.internal.path.xml.XMLAssertion;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class StreamVerifier
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729367;
    private static /* synthetic */ SoftReference $callSiteArray;

    public StreamVerifier() {
        MetaClass metaClass;
        CallSite[] callSiteArray = StreamVerifier.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object newAssertion(Response response, Object key, ResponseParserRegistrar rpr) {
        CallSite[] callSiteArray = StreamVerifier.$getCallSiteArray();
        Object contentType = callSiteArray[0].call(response);
        Object parserType = callSiteArray[1].call(Parser.class, contentType);
        Object assertion = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call((Object)rpr, contentType))) {
                Object object = callSiteArray[3].callStatic(StreamVerifier.class, rpr, contentType, key);
                return object;
            }
            if (ScriptBytecodeAdapter.compareEqual(parserType, callSiteArray[4].callGetProperty(Parser.class))) {
                Object object = callSiteArray[5].callConstructor(JSONAssertion.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", key}));
                return object;
            }
            if (ScriptBytecodeAdapter.compareEqual(parserType, callSiteArray[6].callGetProperty(Parser.class)) || ScriptBytecodeAdapter.compareEqual(parserType, callSiteArray[7].callGetProperty(Parser.class))) {
                Object object = callSiteArray[8].callConstructor(XMLAssertion.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", key}));
                return object;
            }
            Object content = callSiteArray[9].call(response);
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[10].callSafe(contentType))) {
                throw (Throwable)callSiteArray[11].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{content}, new String[]{"Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the response.\nTry registering a default parser using:\n   RestAssured.defaultParser(<parser type>);\nContent was:\n", "\n"}));
            }
            Object contentTypeWithoutCharset = callSiteArray[12].call(ContentTypeExtractor.class, contentType);
            throw (Throwable)callSiteArray[13].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{contentTypeWithoutCharset, contentTypeWithoutCharset, content}, new String[]{"Expected response body to be verified as JSON, HTML or XML but content-type '", "' is not supported out of the box.\nTry registering a custom parser using:\n   RestAssured.registerParser(\"", "\", <parser type>);\nContent was:\n", "\n"}));
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[14].call((Object)rpr, contentType))) {
            Object object = callSiteArray[15].callStatic(StreamVerifier.class, rpr, contentType, key);
            return object;
        }
        if (ScriptBytecodeAdapter.compareEqual(parserType, callSiteArray[16].callGetProperty(Parser.class))) {
            Object object = callSiteArray[17].callConstructor(JSONAssertion.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", key}));
            return object;
        }
        if (ScriptBytecodeAdapter.compareEqual(parserType, callSiteArray[18].callGetProperty(Parser.class)) || ScriptBytecodeAdapter.compareEqual(parserType, callSiteArray[19].callGetProperty(Parser.class))) {
            Object object = callSiteArray[20].callConstructor(XMLAssertion.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", key}));
            return object;
        }
        Object content = callSiteArray[21].call(response);
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[22].callSafe(contentType))) {
            throw (Throwable)callSiteArray[23].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{content}, new String[]{"Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the response.\nTry registering a default parser using:\n   RestAssured.defaultParser(<parser type>);\nContent was:\n", "\n"}));
        }
        Object contentTypeWithoutCharset = callSiteArray[24].call(ContentTypeExtractor.class, contentType);
        throw (Throwable)callSiteArray[25].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{contentTypeWithoutCharset, contentTypeWithoutCharset, content}, new String[]{"Expected response body to be verified as JSON, HTML or XML but content-type '", "' is not supported out of the box.\nTry registering a custom parser using:\n   RestAssured.registerParser(\"", "\", <parser type>);\nContent was:\n", "\n"}));
    }

    public static Object createAssertionForCustomParser(ResponseParserRegistrar rpr, String contentType, Object key) {
        Object assertion;
        block1: {
            Object object;
            Object object2;
            CallSite[] callSiteArray;
            block0: {
                Object object3;
                callSiteArray = StreamVerifier.$getCallSiteArray();
                Object parser = callSiteArray[26].call((Object)rpr, contentType);
                assertion = null;
                object2 = parser;
                if (!ScriptBytecodeAdapter.isCase(object2, callSiteArray[27].callGetProperty(Parser.class))) break block0;
                assertion = object3 = callSiteArray[28].callConstructor(JSONAssertion.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", key}));
                break block1;
            }
            if (!ScriptBytecodeAdapter.isCase(object2, callSiteArray[29].callGetProperty(Parser.class)) && !ScriptBytecodeAdapter.isCase(object2, callSiteArray[30].callGetProperty(Parser.class))) break block1;
            assertion = object = callSiteArray[31].callConstructor(XMLAssertion.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", key}));
        }
        return assertion;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = StreamVerifier.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(StreamVerifier.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = StreamVerifier.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, StreamVerifier.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = StreamVerifier.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(StreamVerifier.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != StreamVerifier.class) {
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
        CallSite[] callSiteArray = StreamVerifier.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        StreamVerifier.__$swapInit();
        __timeStamp__239_neverHappen1702030729367 = l2 = 0L;
        __timeStamp = l = 1702030729367L;
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
        stringArray[0] = "getContentType";
        stringArray[1] = "fromContentType";
        stringArray[2] = "hasCustomParser";
        stringArray[3] = "createAssertionForCustomParser";
        stringArray[4] = "JSON";
        stringArray[5] = "<$constructor$>";
        stringArray[6] = "XML";
        stringArray[7] = "HTML";
        stringArray[8] = "<$constructor$>";
        stringArray[9] = "asString";
        stringArray[10] = "isEmpty";
        stringArray[11] = "<$constructor$>";
        stringArray[12] = "getContentTypeWithoutCharset";
        stringArray[13] = "<$constructor$>";
        stringArray[14] = "hasCustomParser";
        stringArray[15] = "createAssertionForCustomParser";
        stringArray[16] = "JSON";
        stringArray[17] = "<$constructor$>";
        stringArray[18] = "XML";
        stringArray[19] = "HTML";
        stringArray[20] = "<$constructor$>";
        stringArray[21] = "asString";
        stringArray[22] = "isEmpty";
        stringArray[23] = "<$constructor$>";
        stringArray[24] = "getContentTypeWithoutCharset";
        stringArray[25] = "<$constructor$>";
        stringArray[26] = "getNonDefaultParser";
        stringArray[27] = "JSON";
        stringArray[28] = "<$constructor$>";
        stringArray[29] = "XML";
        stringArray[30] = "HTML";
        stringArray[31] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[32];
        StreamVerifier.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(StreamVerifier.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = StreamVerifier.$createCallSiteArray();
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

