/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.support;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.xml.XmlParser;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.internal.path.json.JsonPrettifier;
import io.restassured.internal.path.xml.XmlPrettifier;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.FilterableRequestSpecification;
import java.lang.ref.SoftReference;
import org.apache.commons.lang3.StringUtils;
import org.ccil.cowan.tagsoup.Parser;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class Prettifier
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729371;
    private static /* synthetic */ SoftReference $callSiteArray;

    public Prettifier() {
        MetaClass metaClass;
        CallSite[] callSiteArray = Prettifier.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public String getPrettifiedBodyIfPossible(FilterableRequestSpecification request) {
        CallSite[] callSiteArray = Prettifier.$getCallSiteArray();
        Object body = callSiteArray[0].call(request);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(body, null)) {
                return (String)ScriptBytecodeAdapter.castToType(null, String.class);
            }
            if (!(body instanceof String)) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1].call(body), String.class);
            }
        } else {
            if (ScriptBytecodeAdapter.compareEqual(body, null)) {
                return (String)ScriptBytecodeAdapter.castToType(null, String.class);
            }
            if (!(body instanceof String)) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[2].call(body), String.class);
            }
        }
        Object parser = callSiteArray[3].call(io.restassured.parsing.Parser.class, callSiteArray[4].call(request));
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[5].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper((String)ScriptBytecodeAdapter.asType(body, String.class), String.class), parser), String.class);
    }

    public String getPrettifiedBodyIfPossible(ResponseOptions responseOptions, ResponseBody responseBody) {
        CallSite[] callSiteArray = Prettifier.$getCallSiteArray();
        Object contentType = callSiteArray[6].call(responseOptions);
        Object responseAsString = callSiteArray[7].call(responseBody);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? DefaultTypeTransformation.booleanUnbox(callSiteArray[8].callStatic(StringUtils.class, contentType)) || !(responseOptions instanceof RestAssuredResponseOptionsImpl) : DefaultTypeTransformation.booleanUnbox(callSiteArray[9].callStatic(StringUtils.class, contentType)) || !(responseOptions instanceof RestAssuredResponseOptionsImpl)) {
            return (String)ScriptBytecodeAdapter.castToType(responseAsString, String.class);
        }
        RestAssuredResponseOptionsImpl responseImpl = (RestAssuredResponseOptionsImpl)ScriptBytecodeAdapter.asType(responseOptions, RestAssuredResponseOptionsImpl.class);
        Object rpr = callSiteArray[10].call(responseImpl);
        Object parser = callSiteArray[11].call(rpr, contentType);
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[12].callCurrent(this, responseAsString, parser), String.class);
    }

    public String prettify(String body, io.restassured.parsing.Parser parser) {
        String prettifiedBody;
        block7: {
            CallSite[] callSiteArray = Prettifier.$getCallSiteArray();
            prettifiedBody = null;
            try {
                String string;
                io.restassured.parsing.Parser parser2 = parser;
                if (ScriptBytecodeAdapter.isCase((Object)parser2, callSiteArray[13].callGetProperty(io.restassured.parsing.Parser.class))) {
                    Object object = callSiteArray[14].call(JsonPrettifier.class, body);
                    prettifiedBody = (String)ScriptBytecodeAdapter.castToType(object, String.class);
                    break block7;
                }
                if (ScriptBytecodeAdapter.isCase((Object)parser2, callSiteArray[15].callGetProperty(io.restassured.parsing.Parser.class))) {
                    Object object = callSiteArray[16].call(XmlPrettifier.class, callSiteArray[17].callConstructor(XmlParser.class, false, false), body);
                    prettifiedBody = (String)ScriptBytecodeAdapter.castToType(object, String.class);
                    break block7;
                }
                if (ScriptBytecodeAdapter.isCase((Object)parser2, callSiteArray[18].callGetProperty(io.restassured.parsing.Parser.class))) {
                    Object object = callSiteArray[19].call(XmlPrettifier.class, callSiteArray[20].callConstructor(XmlParser.class, callSiteArray[21].callConstructor(Parser.class)), body);
                    prettifiedBody = (String)ScriptBytecodeAdapter.castToType(object, String.class);
                    break block7;
                }
                prettifiedBody = string = body;
            }
            catch (Exception e) {
                String string;
                prettifiedBody = string = body;
            }
        }
        return prettifiedBody;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = Prettifier.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(Prettifier.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = Prettifier.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, Prettifier.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = Prettifier.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(Prettifier.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != Prettifier.class) {
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
        CallSite[] callSiteArray = Prettifier.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        Prettifier.__$swapInit();
        __timeStamp__239_neverHappen1702030729371 = l2 = 0L;
        __timeStamp = l = 1702030729371L;
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
        stringArray[0] = "getBody";
        stringArray[1] = "toString";
        stringArray[2] = "toString";
        stringArray[3] = "fromContentType";
        stringArray[4] = "getRequestContentType";
        stringArray[5] = "prettify";
        stringArray[6] = "getContentType";
        stringArray[7] = "asString";
        stringArray[8] = "isBlank";
        stringArray[9] = "isBlank";
        stringArray[10] = "getRpr";
        stringArray[11] = "getParser";
        stringArray[12] = "prettify";
        stringArray[13] = "JSON";
        stringArray[14] = "prettifyJson";
        stringArray[15] = "XML";
        stringArray[16] = "prettify";
        stringArray[17] = "<$constructor$>";
        stringArray[18] = "HTML";
        stringArray[19] = "prettify";
        stringArray[20] = "<$constructor$>";
        stringArray[21] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[22];
        Prettifier.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(Prettifier.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = Prettifier.$createCallSiteArray();
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

