/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.filter;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.RestAssured;
import io.restassured.config.CsrfConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.internal.csrf.CsrfTokenFinder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class CsrfFilter
implements Filter,
GroovyObject {
    private CsrfConfig csrfConfig;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729391;
    private static /* synthetic */ SoftReference $callSiteArray;

    public CsrfFilter() {
        MetaClass metaClass;
        CallSite[] callSiteArray = CsrfFilter.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        CallSite[] callSiteArray = CsrfFilter.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(this.csrfConfig)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call(callSiteArray[2].callGetProperty(requestSpec), "GET")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[3].call(callSiteArray[4].callGetProperty(requestSpec), "HEAD"))) {
                Object requestSpecification = callSiteArray[5].call(callSiteArray[6].call(callSiteArray[7].call(callSiteArray[8].call(callSiteArray[9].callStatic(RestAssured.class)))), callSiteArray[10].call(requestSpec));
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[11].call(this.csrfConfig))) {
                    Object logConfig = callSiteArray[12].call(this.csrfConfig);
                    Object logDetail = callSiteArray[13].call(this.csrfConfig);
                    if (ScriptBytecodeAdapter.compareNotEqual(logDetail, callSiteArray[14].callGetProperty(LogDetail.class))) {
                        callSiteArray[15].call(requestSpecification, callSiteArray[16].callConstructor((Object)RequestLoggingFilter.class, ArrayUtil.createArray(logDetail, callSiteArray[17].call(logConfig), callSiteArray[18].call(logConfig), callSiteArray[19].call(logConfig), callSiteArray[20].call(logConfig))));
                    }
                    if (ScriptBytecodeAdapter.compareNotEqual(logDetail, callSiteArray[21].callGetProperty(LogDetail.class))) {
                        callSiteArray[22].call(requestSpecification, callSiteArray[23].callConstructor(ResponseLoggingFilter.class, logDetail, callSiteArray[24].call(logConfig), callSiteArray[25].call(logConfig)));
                    }
                }
                Object pageThatContainsCsrfToken = callSiteArray[26].call(requestSpecification, callSiteArray[27].call(this.csrfConfig));
                Object csrfData = callSiteArray[28].call(CsrfTokenFinder.class, this.csrfConfig, pageThatContainsCsrfToken);
                if (!DefaultTypeTransformation.booleanUnbox(csrfData)) {
                    throw (Throwable)callSiteArray[29].callConstructor(IllegalArgumentException.class, callSiteArray[30].call((Object)new GStringImpl(new Object[]{callSiteArray[31].callGetProperty(this.csrfConfig), callSiteArray[32].callGetProperty(this.csrfConfig)}, new String[]{"Couldn't find a the CSRF token in response. Expecting either an input field with name \"", "\" or a meta tag with name \"", "\". "}), new GStringImpl(new Object[]{callSiteArray[33].call(pageThatContainsCsrfToken)}, new String[]{"Response was:\n", ""})));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[34].call(csrfData, callSiteArray[35].callGetProperty(CsrfConfig.CsrfPrioritization.class)))) {
                    callSiteArray[36].call(requestSpec, callSiteArray[37].callGetProperty(csrfData), callSiteArray[38].callGetProperty(csrfData));
                } else {
                    callSiteArray[39].call(requestSpec, callSiteArray[40].callGetProperty(csrfData), callSiteArray[41].callGetProperty(csrfData));
                }
            }
        } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[42].call(this.csrfConfig)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[43].call(callSiteArray[44].callGetProperty(requestSpec), "GET")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[45].call(callSiteArray[46].callGetProperty(requestSpec), "HEAD"))) {
            Object requestSpecification = callSiteArray[47].call(callSiteArray[48].call(callSiteArray[49].call(callSiteArray[50].call(callSiteArray[51].callStatic(RestAssured.class)))), callSiteArray[52].call(requestSpec));
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[53].call(this.csrfConfig))) {
                Object logConfig = callSiteArray[54].call(this.csrfConfig);
                Object logDetail = callSiteArray[55].call(this.csrfConfig);
                if (ScriptBytecodeAdapter.compareNotEqual(logDetail, callSiteArray[56].callGetProperty(LogDetail.class))) {
                    callSiteArray[57].call(requestSpecification, callSiteArray[58].callConstructor((Object)RequestLoggingFilter.class, ArrayUtil.createArray(logDetail, callSiteArray[59].call(logConfig), callSiteArray[60].call(logConfig), callSiteArray[61].call(logConfig), callSiteArray[62].call(logConfig))));
                }
                if (ScriptBytecodeAdapter.compareNotEqual(logDetail, callSiteArray[63].callGetProperty(LogDetail.class))) {
                    callSiteArray[64].call(requestSpecification, callSiteArray[65].callConstructor(ResponseLoggingFilter.class, logDetail, callSiteArray[66].call(logConfig), callSiteArray[67].call(logConfig)));
                }
            }
            Object pageThatContainsCsrfToken = callSiteArray[68].call(requestSpecification, callSiteArray[69].call(this.csrfConfig));
            Object csrfData = callSiteArray[70].call(CsrfTokenFinder.class, this.csrfConfig, pageThatContainsCsrfToken);
            if (!DefaultTypeTransformation.booleanUnbox(csrfData)) {
                throw (Throwable)callSiteArray[71].callConstructor(IllegalArgumentException.class, callSiteArray[72].call((Object)new GStringImpl(new Object[]{callSiteArray[73].callGetProperty(this.csrfConfig), callSiteArray[74].callGetProperty(this.csrfConfig)}, new String[]{"Couldn't find a the CSRF token in response. Expecting either an input field with name \"", "\" or a meta tag with name \"", "\". "}), new GStringImpl(new Object[]{callSiteArray[75].call(pageThatContainsCsrfToken)}, new String[]{"Response was:\n", ""})));
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[76].call(csrfData, callSiteArray[77].callGetProperty(CsrfConfig.CsrfPrioritization.class)))) {
                callSiteArray[78].call(requestSpec, callSiteArray[79].callGetProperty(csrfData), callSiteArray[80].callGetProperty(csrfData));
            } else {
                callSiteArray[81].call(requestSpec, callSiteArray[82].callGetProperty(csrfData), callSiteArray[83].callGetProperty(csrfData));
            }
        }
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[84].call(ctx, requestSpec, responseSpec), Response.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = CsrfFilter.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(CsrfFilter.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = CsrfFilter.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, CsrfFilter.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = CsrfFilter.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(CsrfFilter.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != CsrfFilter.class) {
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
        CallSite[] callSiteArray = CsrfFilter.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        CsrfFilter.__$swapInit();
        __timeStamp__239_neverHappen1702030729391 = l2 = 0L;
        __timeStamp = l = 1702030729391L;
    }

    public CsrfConfig getCsrfConfig() {
        return this.csrfConfig;
    }

    public void setCsrfConfig(CsrfConfig csrfConfig) {
        this.csrfConfig = csrfConfig;
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
        stringArray[0] = "isCsrfEnabled";
        stringArray[1] = "equalsIgnoreCase";
        stringArray[2] = "method";
        stringArray[3] = "equalsIgnoreCase";
        stringArray[4] = "method";
        stringArray[5] = "cookies";
        stringArray[6] = "disableCsrf";
        stringArray[7] = "none";
        stringArray[8] = "auth";
        stringArray[9] = "given";
        stringArray[10] = "getCookies";
        stringArray[11] = "isLoggingEnabled";
        stringArray[12] = "getLogConfig";
        stringArray[13] = "getLogDetail";
        stringArray[14] = "STATUS";
        stringArray[15] = "filter";
        stringArray[16] = "<$constructor$>";
        stringArray[17] = "isPrettyPrintingEnabled";
        stringArray[18] = "defaultStream";
        stringArray[19] = "shouldUrlEncodeRequestUri";
        stringArray[20] = "blacklistedHeaders";
        stringArray[21] = "PARAMS";
        stringArray[22] = "filter";
        stringArray[23] = "<$constructor$>";
        stringArray[24] = "isPrettyPrintingEnabled";
        stringArray[25] = "defaultStream";
        stringArray[26] = "get";
        stringArray[27] = "getCsrfTokenPath";
        stringArray[28] = "findInHtml";
        stringArray[29] = "<$constructor$>";
        stringArray[30] = "plus";
        stringArray[31] = "csrfInputFieldName";
        stringArray[32] = "csrfMetaTagName";
        stringArray[33] = "prettyPrint";
        stringArray[34] = "shouldSendTokenAs";
        stringArray[35] = "FORM";
        stringArray[36] = "formParam";
        stringArray[37] = "inputFieldOrHeaderName";
        stringArray[38] = "token";
        stringArray[39] = "header";
        stringArray[40] = "inputFieldOrHeaderName";
        stringArray[41] = "token";
        stringArray[42] = "isCsrfEnabled";
        stringArray[43] = "equalsIgnoreCase";
        stringArray[44] = "method";
        stringArray[45] = "equalsIgnoreCase";
        stringArray[46] = "method";
        stringArray[47] = "cookies";
        stringArray[48] = "disableCsrf";
        stringArray[49] = "none";
        stringArray[50] = "auth";
        stringArray[51] = "given";
        stringArray[52] = "getCookies";
        stringArray[53] = "isLoggingEnabled";
        stringArray[54] = "getLogConfig";
        stringArray[55] = "getLogDetail";
        stringArray[56] = "STATUS";
        stringArray[57] = "filter";
        stringArray[58] = "<$constructor$>";
        stringArray[59] = "isPrettyPrintingEnabled";
        stringArray[60] = "defaultStream";
        stringArray[61] = "shouldUrlEncodeRequestUri";
        stringArray[62] = "blacklistedHeaders";
        stringArray[63] = "PARAMS";
        stringArray[64] = "filter";
        stringArray[65] = "<$constructor$>";
        stringArray[66] = "isPrettyPrintingEnabled";
        stringArray[67] = "defaultStream";
        stringArray[68] = "get";
        stringArray[69] = "getCsrfTokenPath";
        stringArray[70] = "findInHtml";
        stringArray[71] = "<$constructor$>";
        stringArray[72] = "plus";
        stringArray[73] = "csrfInputFieldName";
        stringArray[74] = "csrfMetaTagName";
        stringArray[75] = "prettyPrint";
        stringArray[76] = "shouldSendTokenAs";
        stringArray[77] = "FORM";
        stringArray[78] = "formParam";
        stringArray[79] = "inputFieldOrHeaderName";
        stringArray[80] = "token";
        stringArray[81] = "header";
        stringArray[82] = "inputFieldOrHeaderName";
        stringArray[83] = "token";
        stringArray[84] = "next";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[85];
        CsrfFilter.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(CsrfFilter.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = CsrfFilter.$createCallSiteArray();
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

