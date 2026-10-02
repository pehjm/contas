/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.authentication;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.OAuthSignature;
import io.restassured.internal.http.HTTPBuilder;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class OAuthScheme
implements AuthenticationScheme,
GroovyObject {
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String secretToken;
    private OAuthSignature signature;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729349;
    private static /* synthetic */ SoftReference $callSiteArray;

    public OAuthScheme() {
        MetaClass metaClass;
        CallSite[] callSiteArray = OAuthScheme.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public void authenticate(HTTPBuilder httpBuilder) {
        CallSite[] callSiteArray = OAuthScheme.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual((Object)this.signature, null)) {
                callSiteArray[0].call(callSiteArray[1].callGetProperty(httpBuilder), ArrayUtil.createArray(this.consumerKey, this.consumerSecret, this.accessToken, this.secretToken, (Object)this.signature));
            } else {
                callSiteArray[2].call(callSiteArray[3].callGetProperty(httpBuilder), this.consumerKey, this.consumerSecret, this.accessToken, this.secretToken);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual((Object)this.signature, null)) {
            callSiteArray[4].call(callSiteArray[5].callGetProperty(httpBuilder), ArrayUtil.createArray(this.consumerKey, this.consumerSecret, this.accessToken, this.secretToken, (Object)this.signature));
        } else {
            callSiteArray[6].call(callSiteArray[7].callGetProperty(httpBuilder), this.consumerKey, this.consumerSecret, this.accessToken, this.secretToken);
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = OAuthScheme.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(OAuthScheme.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = OAuthScheme.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, OAuthScheme.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = OAuthScheme.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(OAuthScheme.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != OAuthScheme.class) {
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
        CallSite[] callSiteArray = OAuthScheme.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        OAuthScheme.__$swapInit();
        __timeStamp__239_neverHappen1702030729349 = l2 = 0L;
        __timeStamp = l = 1702030729349L;
    }

    public String getConsumerKey() {
        return this.consumerKey;
    }

    public void setConsumerKey(String string) {
        this.consumerKey = string;
    }

    public String getConsumerSecret() {
        return this.consumerSecret;
    }

    public void setConsumerSecret(String string) {
        this.consumerSecret = string;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String string) {
        this.accessToken = string;
    }

    public String getSecretToken() {
        return this.secretToken;
    }

    public void setSecretToken(String string) {
        this.secretToken = string;
    }

    public OAuthSignature getSignature() {
        return this.signature;
    }

    public void setSignature(OAuthSignature oAuthSignature) {
        this.signature = oAuthSignature;
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
        stringArray[0] = "oauth";
        stringArray[1] = "auth";
        stringArray[2] = "oauth";
        stringArray[3] = "auth";
        stringArray[4] = "oauth";
        stringArray[5] = "auth";
        stringArray[6] = "oauth";
        stringArray[7] = "auth";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[8];
        OAuthScheme.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(OAuthScheme.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = OAuthScheme.$createCallSiteArray();
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

