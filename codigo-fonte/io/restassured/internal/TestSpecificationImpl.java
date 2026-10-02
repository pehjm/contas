/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.http.Method;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class TestSpecificationImpl
implements RequestSender,
GroovyObject {
    private final RequestSpecification requestSpecification;
    private final ResponseSpecification responseSpecification;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729345;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public TestSpecificationImpl(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        void responseSpecification2;
        void requestSpecification2;
        MetaClass metaClass;
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        callSiteArray[0].call(AssertParameter.class, requestSpecification2, "requestSpecification");
        callSiteArray[1].call(AssertParameter.class, responseSpecification2, "responseSpecification");
        void var5_5 = requestSpecification2;
        this.requestSpecification = (RequestSpecification)ScriptBytecodeAdapter.castToType(var5_5, RequestSpecification.class);
        void var6_6 = responseSpecification2;
        this.responseSpecification = (ResponseSpecification)ScriptBytecodeAdapter.castToType(var6_6, ResponseSpecification.class);
        void var7_7 = requestSpecification2;
        ScriptBytecodeAdapter.setProperty(var7_7, null, responseSpecification2, "requestSpecification");
        void var8_8 = responseSpecification2;
        ScriptBytecodeAdapter.setProperty(var8_8, null, requestSpecification2, "responseSpecification");
    }

    public Response get(String path, Object ... pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[2].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response post(String path, Object ... pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[3].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response put(String path, Object ... pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[4].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response delete(String path, Object ... pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[5].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response head(String path, Object ... pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[6].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response patch(String path, Object ... pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[7].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response options(String path, Object ... pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[8].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response get(URI uri) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[9].callCurrent((GroovyObject)this, callSiteArray[10].call(callSiteArray[11].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    public Response post(URI uri) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[12].callCurrent((GroovyObject)this, callSiteArray[13].call(callSiteArray[14].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    public Response put(URI uri) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[15].callCurrent((GroovyObject)this, callSiteArray[16].call(callSiteArray[17].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    public Response delete(URI uri) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[18].callCurrent((GroovyObject)this, callSiteArray[19].call(callSiteArray[20].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    public Response head(URI uri) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[21].callCurrent((GroovyObject)this, callSiteArray[22].call(callSiteArray[23].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    public Response patch(URI uri) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[24].callCurrent((GroovyObject)this, callSiteArray[25].call(callSiteArray[26].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    public Response options(URI uri) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[27].callCurrent((GroovyObject)this, callSiteArray[28].call(callSiteArray[29].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    public Response get(URL url) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[30].callCurrent((GroovyObject)this, callSiteArray[31].call(callSiteArray[32].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    public Response post(URL url) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[33].callCurrent((GroovyObject)this, callSiteArray[34].call(callSiteArray[35].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    public Response put(URL url) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[36].callCurrent((GroovyObject)this, callSiteArray[37].call(callSiteArray[38].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    public Response delete(URL url) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[39].callCurrent((GroovyObject)this, callSiteArray[40].call(callSiteArray[41].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    public Response head(URL url) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[42].callCurrent((GroovyObject)this, callSiteArray[43].call(callSiteArray[44].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    public Response patch(URL url) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[45].callCurrent((GroovyObject)this, callSiteArray[46].call(callSiteArray[47].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    public Response options(URL url) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[48].callCurrent((GroovyObject)this, callSiteArray[49].call(callSiteArray[50].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    public Response get() {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[51].callCurrent((GroovyObject)this, ""), Response.class);
    }

    public Response post() {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[52].callCurrent((GroovyObject)this, ""), Response.class);
    }

    public Response put() {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[53].callCurrent((GroovyObject)this, ""), Response.class);
    }

    public Response delete() {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[54].callCurrent((GroovyObject)this, ""), Response.class);
    }

    public Response head() {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[55].callCurrent((GroovyObject)this, ""), Response.class);
    }

    public Response patch() {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[56].callCurrent((GroovyObject)this, ""), Response.class);
    }

    public Response options() {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[57].callCurrent((GroovyObject)this, ""), Response.class);
    }

    public Response request(Method method) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[58].callCurrent((GroovyObject)this, callSiteArray[59].call(callSiteArray[60].callStatic(AssertParameter.class, (Object)method, Method.class))), Response.class);
    }

    public Response request(String method) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[61].callCurrent(this, method, ""), Response.class);
    }

    public Response request(Method method, String path, Object ... pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[62].callCurrent(this, callSiteArray[63].call(callSiteArray[64].callStatic(AssertParameter.class, (Object)method, Method.class)), path, pathParams), Response.class);
    }

    public Response request(String method, String path, Object ... pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[65].call(this.requestSpecification, method, path, pathParams), Response.class);
    }

    public Response request(Method method, URI uri) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[66].call(this.requestSpecification, (Object)method, uri), Response.class);
    }

    public Response request(Method method, URL url) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[67].call(this.requestSpecification, (Object)method, url), Response.class);
    }

    public Response request(String method, URI uri) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[68].call(this.requestSpecification, method, uri), Response.class);
    }

    public Response request(String method, URL url) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[69].call(this.requestSpecification, method, url), Response.class);
    }

    public Response get(String path, Map pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[70].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response post(String path, Map pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[71].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response put(String path, Map pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[72].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response delete(String path, Map pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[73].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response head(String path, Map pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[74].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response patch(String path, Map pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[75].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public Response options(String path, Map pathParams) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[76].call(this.requestSpecification, path, pathParams), Response.class);
    }

    public RequestSpecification getRequestSpecification() {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return this.requestSpecification;
    }

    public ResponseSpecification getResponseSpecification() {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return this.responseSpecification;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(TestSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, TestSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(TestSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TestSpecificationImpl.class) {
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
        CallSite[] callSiteArray = TestSpecificationImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        TestSpecificationImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729345 = l2 = 0L;
        __timeStamp = l = 1702030729345L;
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
        stringArray[1] = "notNull";
        stringArray[2] = "get";
        stringArray[3] = "post";
        stringArray[4] = "put";
        stringArray[5] = "delete";
        stringArray[6] = "head";
        stringArray[7] = "patch";
        stringArray[8] = "options";
        stringArray[9] = "get";
        stringArray[10] = "toString";
        stringArray[11] = "notNull";
        stringArray[12] = "post";
        stringArray[13] = "toString";
        stringArray[14] = "notNull";
        stringArray[15] = "put";
        stringArray[16] = "toString";
        stringArray[17] = "notNull";
        stringArray[18] = "delete";
        stringArray[19] = "toString";
        stringArray[20] = "notNull";
        stringArray[21] = "head";
        stringArray[22] = "toString";
        stringArray[23] = "notNull";
        stringArray[24] = "patch";
        stringArray[25] = "toString";
        stringArray[26] = "notNull";
        stringArray[27] = "options";
        stringArray[28] = "toString";
        stringArray[29] = "notNull";
        stringArray[30] = "get";
        stringArray[31] = "toString";
        stringArray[32] = "notNull";
        stringArray[33] = "post";
        stringArray[34] = "toString";
        stringArray[35] = "notNull";
        stringArray[36] = "put";
        stringArray[37] = "toString";
        stringArray[38] = "notNull";
        stringArray[39] = "delete";
        stringArray[40] = "toString";
        stringArray[41] = "notNull";
        stringArray[42] = "head";
        stringArray[43] = "toString";
        stringArray[44] = "notNull";
        stringArray[45] = "patch";
        stringArray[46] = "toString";
        stringArray[47] = "notNull";
        stringArray[48] = "options";
        stringArray[49] = "toString";
        stringArray[50] = "notNull";
        stringArray[51] = "get";
        stringArray[52] = "post";
        stringArray[53] = "put";
        stringArray[54] = "delete";
        stringArray[55] = "head";
        stringArray[56] = "patch";
        stringArray[57] = "options";
        stringArray[58] = "request";
        stringArray[59] = "toString";
        stringArray[60] = "notNull";
        stringArray[61] = "request";
        stringArray[62] = "request";
        stringArray[63] = "toString";
        stringArray[64] = "notNull";
        stringArray[65] = "request";
        stringArray[66] = "request";
        stringArray[67] = "request";
        stringArray[68] = "request";
        stringArray[69] = "request";
        stringArray[70] = "get";
        stringArray[71] = "post";
        stringArray[72] = "put";
        stringArray[73] = "delete";
        stringArray[74] = "head";
        stringArray[75] = "patch";
        stringArray[76] = "options";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[77];
        TestSpecificationImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(TestSpecificationImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = TestSpecificationImpl.$createCallSiteArray();
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

