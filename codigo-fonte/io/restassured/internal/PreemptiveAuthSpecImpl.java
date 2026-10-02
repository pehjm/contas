/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.specification.PreemptiveAuthSpec;
import io.restassured.specification.RequestSpecification;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class PreemptiveAuthSpecImpl
implements PreemptiveAuthSpec,
GroovyObject {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private RequestSpecification requestBuilder;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729366;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public PreemptiveAuthSpecImpl(RequestSpecification requestSpecification) {
        void requestBuilder;
        MetaClass metaClass;
        CallSite[] callSiteArray = PreemptiveAuthSpecImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var4_4 = requestBuilder;
        this.requestBuilder = (RequestSpecification)ScriptBytecodeAdapter.castToType(var4_4, RequestSpecification.class);
    }

    public RequestSpecification basic(String username, String password) {
        CallSite[] callSiteArray = PreemptiveAuthSpecImpl.$getCallSiteArray();
        callSiteArray[0].callStatic(AssertParameter.class, username, "userName");
        callSiteArray[1].callStatic(AssertParameter.class, password, "password");
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            callSiteArray[2].call(callSiteArray[3].callCurrent(this), AUTHORIZATION_HEADER_NAME, callSiteArray[4].call(callSiteArray[5].callConstructor(PreemptiveBasicAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"userName", username, "password", password}))));
        } else {
            callSiteArray[6].call(this.removePreviousAuth(), AUTHORIZATION_HEADER_NAME, callSiteArray[7].call(callSiteArray[8].callConstructor(PreemptiveBasicAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"userName", username, "password", password}))));
        }
        return this.requestBuilder;
    }

    public RequestSpecification oauth2(String accessToken) {
        CallSite[] callSiteArray = PreemptiveAuthSpecImpl.$getCallSiteArray();
        callSiteArray[9].callStatic(AssertParameter.class, accessToken, "accessToken");
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[10].call(callSiteArray[11].callCurrent(this), AUTHORIZATION_HEADER_NAME, callSiteArray[12].call(callSiteArray[13].callConstructor(PreemptiveOAuth2HeaderScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"accessToken", accessToken})))), RequestSpecification.class);
        }
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[14].call(this.removePreviousAuth(), AUTHORIZATION_HEADER_NAME, callSiteArray[15].call(callSiteArray[16].callConstructor(PreemptiveOAuth2HeaderScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"accessToken", accessToken})))), RequestSpecification.class);
    }

    private RequestSpecification removePreviousAuth() {
        CallSite[] callSiteArray = PreemptiveAuthSpecImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[17].call(callSiteArray[18].call(this.requestBuilder)), RequestSpecification.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = PreemptiveAuthSpecImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(PreemptiveAuthSpecImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = PreemptiveAuthSpecImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, PreemptiveAuthSpecImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = PreemptiveAuthSpecImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(PreemptiveAuthSpecImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != PreemptiveAuthSpecImpl.class) {
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
        CallSite[] callSiteArray = PreemptiveAuthSpecImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        PreemptiveAuthSpecImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729366 = l2 = 0L;
        __timeStamp = l = 1702030729366L;
    }

    public /* synthetic */ RequestSpecification this$2$removePreviousAuth() {
        return this.removePreviousAuth();
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
        stringArray[2] = "header";
        stringArray[3] = "removePreviousAuth";
        stringArray[4] = "generateAuthToken";
        stringArray[5] = "<$constructor$>";
        stringArray[6] = "header";
        stringArray[7] = "generateAuthToken";
        stringArray[8] = "<$constructor$>";
        stringArray[9] = "notNull";
        stringArray[10] = "header";
        stringArray[11] = "removePreviousAuth";
        stringArray[12] = "generateAuthToken";
        stringArray[13] = "<$constructor$>";
        stringArray[14] = "header";
        stringArray[15] = "generateAuthToken";
        stringArray[16] = "<$constructor$>";
        stringArray[17] = "none";
        stringArray[18] = "auth";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[19];
        PreemptiveAuthSpecImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(PreemptiveAuthSpecImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = PreemptiveAuthSpecImpl.$createCallSiteArray();
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

