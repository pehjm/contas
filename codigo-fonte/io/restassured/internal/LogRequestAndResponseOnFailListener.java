/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.lang.ref.SoftReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class LogRequestAndResponseOnFailListener
implements ResponseValidationFailureListener,
GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729360;
    private static /* synthetic */ SoftReference $callSiteArray;

    public LogRequestAndResponseOnFailListener() {
        MetaClass metaClass;
        CallSite[] callSiteArray = LogRequestAndResponseOnFailListener.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public void onFailure(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, Response response) {
        CallSite[] callSiteArray = LogRequestAndResponseOnFailListener.$getCallSiteArray();
        if (Boolean.valueOf(!DefaultTypeTransformation.booleanUnbox(responseSpecification)) instanceof ResponseSpecificationImpl) {
            return;
        }
        ResponseSpecificationImpl respSpecImpl = (ResponseSpecificationImpl)ScriptBytecodeAdapter.castToType(responseSpecification, ResponseSpecificationImpl.class);
        Object logRepository = callSiteArray[0].call(respSpecImpl);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(logRepository, null)) {
                Object stream = callSiteArray[1].call(callSiteArray[2].call(callSiteArray[3].call(responseSpecification)));
                Object requestLog = callSiteArray[4].callGetProperty(logRepository);
                Object responseLog = callSiteArray[5].callGetProperty(logRepository);
                Object requestLogHasText = callSiteArray[6].call(StringUtils.class, requestLog);
                if (DefaultTypeTransformation.booleanUnbox(requestLogHasText)) {
                    callSiteArray[7].call(stream, requestLog);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[8].call(StringUtils.class, responseLog))) {
                    if (DefaultTypeTransformation.booleanUnbox(requestLogHasText)) {
                        callSiteArray[9].call(stream, callSiteArray[10].callGetProperty(SystemUtils.class));
                    }
                    callSiteArray[11].call(stream, responseLog);
                }
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(logRepository, null)) {
            Object stream = callSiteArray[12].call(callSiteArray[13].call(callSiteArray[14].call(responseSpecification)));
            Object requestLog = callSiteArray[15].callGetProperty(logRepository);
            Object responseLog = callSiteArray[16].callGetProperty(logRepository);
            Object requestLogHasText = callSiteArray[17].call(StringUtils.class, requestLog);
            if (DefaultTypeTransformation.booleanUnbox(requestLogHasText)) {
                callSiteArray[18].call(stream, requestLog);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[19].call(StringUtils.class, responseLog))) {
                if (DefaultTypeTransformation.booleanUnbox(requestLogHasText)) {
                    callSiteArray[20].call(stream, callSiteArray[21].callGetProperty(SystemUtils.class));
                }
                callSiteArray[22].call(stream, responseLog);
            }
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = LogRequestAndResponseOnFailListener.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(LogRequestAndResponseOnFailListener.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = LogRequestAndResponseOnFailListener.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, LogRequestAndResponseOnFailListener.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = LogRequestAndResponseOnFailListener.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(LogRequestAndResponseOnFailListener.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != LogRequestAndResponseOnFailListener.class) {
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
        CallSite[] callSiteArray = LogRequestAndResponseOnFailListener.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        LogRequestAndResponseOnFailListener.__$swapInit();
        __timeStamp__239_neverHappen1702030729360 = l2 = 0L;
        __timeStamp = l = 1702030729360L;
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
        stringArray[0] = "getLogRepository";
        stringArray[1] = "defaultStream";
        stringArray[2] = "getLogConfig";
        stringArray[3] = "getConfig";
        stringArray[4] = "requestLog";
        stringArray[5] = "responseLog";
        stringArray[6] = "isNotEmpty";
        stringArray[7] = "print";
        stringArray[8] = "isNotEmpty";
        stringArray[9] = "print";
        stringArray[10] = "LINE_SEPARATOR";
        stringArray[11] = "print";
        stringArray[12] = "defaultStream";
        stringArray[13] = "getLogConfig";
        stringArray[14] = "getConfig";
        stringArray[15] = "requestLog";
        stringArray[16] = "responseLog";
        stringArray[17] = "isNotEmpty";
        stringArray[18] = "print";
        stringArray[19] = "isNotEmpty";
        stringArray[20] = "print";
        stringArray[21] = "LINE_SEPARATOR";
        stringArray[22] = "print";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[23];
        LogRequestAndResponseOnFailListener.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(LogRequestAndResponseOnFailListener.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = LogRequestAndResponseOnFailListener.$createCallSiteArray();
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

