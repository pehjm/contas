/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.internal.LogSpecificationImpl;
import io.restassured.internal.log.LogRepository;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.ref.SoftReference;
import java.util.Set;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RequestLogSpecificationImpl
extends LogSpecificationImpl
implements RequestLogSpecification {
    private RequestSpecification requestSpecification;
    private LogRepository logRepository;
    private Set<String> blacklistedHeaders;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729393;
    private static /* synthetic */ SoftReference $callSiteArray;

    public RequestLogSpecificationImpl() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
    }

    @Override
    public RequestSpecification params() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[0].callCurrent((GroovyObject)this, callSiteArray[1].callGetProperty(LogDetail.class)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification parameters() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[2].callCurrent((GroovyObject)this, callSiteArray[3].callGetProperty(LogDetail.class)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification uri() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[4].callCurrent((GroovyObject)this, callSiteArray[5].callGetProperty(LogDetail.class)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification method() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[6].callCurrent((GroovyObject)this, callSiteArray[7].callGetProperty(LogDetail.class)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification body() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[8].callCurrent((GroovyObject)this, callSiteArray[9].callCurrent((GroovyObject)this, this.requestSpecification)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification body(boolean shouldPrettyPrint) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[10].callCurrent(this, callSiteArray[11].callGetProperty(LogDetail.class), shouldPrettyPrint), RequestSpecification.class);
    }

    @Override
    public RequestSpecification all(boolean shouldPrettyPrint) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[12].callCurrent(this, callSiteArray[13].callGetProperty(LogDetail.class), shouldPrettyPrint), RequestSpecification.class);
    }

    @Override
    public RequestSpecification everything(boolean shouldPrettyPrint) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[14].callCurrent((GroovyObject)this, shouldPrettyPrint), RequestSpecification.class);
        }
        return this.all(shouldPrettyPrint);
    }

    @Override
    public RequestSpecification all() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[15].callCurrent((GroovyObject)this, callSiteArray[16].callCurrent((GroovyObject)this, this.requestSpecification)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification everything() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[17].callCurrent(this), RequestSpecification.class);
        }
        return this.all();
    }

    @Override
    public RequestSpecification headers() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[18].callCurrent((GroovyObject)this, callSiteArray[19].callGetProperty(LogDetail.class)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification cookies() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[20].callCurrent((GroovyObject)this, callSiteArray[21].callGetProperty(LogDetail.class)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification ifValidationFails() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[22].callCurrent((GroovyObject)this, callSiteArray[23].callGetProperty(LogDetail.class)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification ifValidationFails(LogDetail logDetail) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[24].callCurrent(this, (Object)logDetail, callSiteArray[25].callCurrent((GroovyObject)this, this.requestSpecification)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification ifValidationFails(LogDetail logDetail, boolean shouldPrettyPrint) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        ByteArrayOutputStream baos = (ByteArrayOutputStream)ScriptBytecodeAdapter.castToType(callSiteArray[26].callConstructor(ByteArrayOutputStream.class), ByteArrayOutputStream.class);
        PrintStream ps = (PrintStream)ScriptBytecodeAdapter.castToType(callSiteArray[27].callConstructor(PrintStream.class, baos), PrintStream.class);
        callSiteArray[28].call((Object)this.logRepository, baos);
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[29].callCurrent(this, (Object)logDetail, shouldPrettyPrint, ps), RequestSpecification.class);
    }

    private Object logWith(LogDetail logDetail) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return callSiteArray[30].callCurrent(this, (Object)logDetail, callSiteArray[31].callCurrent((GroovyObject)this, this.requestSpecification));
    }

    private Object logWith(LogDetail logDetail, boolean prettyPrintingEnabled) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return callSiteArray[32].callCurrent(this, (Object)logDetail, prettyPrintingEnabled, callSiteArray[33].callCurrent((GroovyObject)this, this.requestSpecification));
    }

    private Object logWith(LogDetail logDetail, boolean prettyPrintingEnabled, PrintStream printStream) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        callSiteArray[34].call((Object)this.requestSpecification, callSiteArray[35].callConstructor((Object)RequestLoggingFilter.class, ArrayUtil.createArray((Object)logDetail, prettyPrintingEnabled, printStream, callSiteArray[36].callCurrent((GroovyObject)this, this.requestSpecification), this.blacklistedHeaders)));
        return this.requestSpecification;
    }

    public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RequestLogSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$2(String name, Object value) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, RequestLogSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$2(String name) {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(RequestLogSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RequestLogSpecificationImpl.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = RequestLogSpecificationImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        RequestLogSpecificationImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729393 = l2 = 0L;
        __timeStamp = l = 1702030729393L;
    }

    public RequestSpecification getRequestSpecification() {
        return this.requestSpecification;
    }

    public void setRequestSpecification(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public LogRepository getLogRepository() {
        return this.logRepository;
    }

    public void setLogRepository(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Set<String> getBlacklistedHeaders() {
        return this.blacklistedHeaders;
    }

    public void setBlacklistedHeaders(Set<String> set) {
        this.blacklistedHeaders = set;
    }

    public /* synthetic */ Object this$3$logWith(LogDetail logDetail) {
        return this.logWith(logDetail);
    }

    public /* synthetic */ Object this$3$logWith(LogDetail logDetail, boolean bl) {
        return this.logWith(logDetail, bl);
    }

    public /* synthetic */ Object this$3$logWith(LogDetail logDetail, boolean bl, PrintStream printStream) {
        return this.logWith(logDetail, bl, printStream);
    }

    public /* synthetic */ PrintStream super$2$getPrintStream(RequestSpecification requestSpecification) {
        return super.getPrintStream(requestSpecification);
    }

    @Override
    public /* synthetic */ int super$1$hashCode() {
        return super.hashCode();
    }

    public /* synthetic */ void super$2$setProperty(String string, Object object) {
        super.setProperty(string, object);
    }

    @Override
    public /* synthetic */ void super$1$finalize() {
        super.finalize();
    }

    public /* synthetic */ Object super$2$this$dist$invoke$1(String string, Object object) {
        return super.this$dist$invoke$1(string, object);
    }

    @Override
    public /* synthetic */ String super$1$toString() {
        return super.toString();
    }

    public /* synthetic */ void super$2$setMetaClass(MetaClass metaClass) {
        super.setMetaClass(metaClass);
    }

    public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
        return super.$getStaticMetaClass();
    }

    @Override
    public /* synthetic */ void super$1$notify() {
        super.notify();
    }

    public /* synthetic */ Object super$2$invokeMethod(String string, Object object) {
        return super.invokeMethod(string, object);
    }

    public /* synthetic */ boolean super$2$shouldUrlEncodeRequestUri(RequestSpecification requestSpecification) {
        return super.shouldUrlEncodeRequestUri(requestSpecification);
    }

    @Override
    public /* synthetic */ Object super$1$clone() {
        return super.clone();
    }

    public /* synthetic */ void super$2$this$dist$set$1(String string, Object object) {
        super.this$dist$set$1(string, object);
    }

    public /* synthetic */ MetaClass super$2$getMetaClass() {
        return super.getMetaClass();
    }

    public /* synthetic */ Object super$2$this$dist$get$1(String string) {
        return super.this$dist$get$1(string);
    }

    @Override
    public /* synthetic */ void super$1$notifyAll() {
        super.notifyAll();
    }

    public /* synthetic */ Object super$2$getProperty(String string) {
        return super.getProperty(string);
    }

    public /* synthetic */ boolean super$2$shouldPrettyPrint(RequestSpecification requestSpecification) {
        return super.shouldPrettyPrint(requestSpecification);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
        stringArray[0] = "logWith";
        stringArray[1] = "PARAMS";
        stringArray[2] = "logWith";
        stringArray[3] = "PARAMS";
        stringArray[4] = "logWith";
        stringArray[5] = "URI";
        stringArray[6] = "logWith";
        stringArray[7] = "METHOD";
        stringArray[8] = "body";
        stringArray[9] = "shouldPrettyPrint";
        stringArray[10] = "logWith";
        stringArray[11] = "BODY";
        stringArray[12] = "logWith";
        stringArray[13] = "ALL";
        stringArray[14] = "all";
        stringArray[15] = "all";
        stringArray[16] = "shouldPrettyPrint";
        stringArray[17] = "all";
        stringArray[18] = "logWith";
        stringArray[19] = "HEADERS";
        stringArray[20] = "logWith";
        stringArray[21] = "COOKIES";
        stringArray[22] = "ifValidationFails";
        stringArray[23] = "ALL";
        stringArray[24] = "ifValidationFails";
        stringArray[25] = "shouldPrettyPrint";
        stringArray[26] = "<$constructor$>";
        stringArray[27] = "<$constructor$>";
        stringArray[28] = "registerRequestLog";
        stringArray[29] = "logWith";
        stringArray[30] = "logWith";
        stringArray[31] = "shouldPrettyPrint";
        stringArray[32] = "logWith";
        stringArray[33] = "getPrintStream";
        stringArray[34] = "filter";
        stringArray[35] = "<$constructor$>";
        stringArray[36] = "shouldUrlEncodeRequestUri";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[37];
        RequestLogSpecificationImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(RequestLogSpecificationImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = RequestLogSpecificationImpl.$createCallSiteArray();
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

