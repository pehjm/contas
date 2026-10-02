/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.authentication;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.internal.http.HTTPBuilder;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class NTLMAuthScheme
implements AuthenticationScheme,
GroovyObject {
    private String userName;
    private String password;
    private String workstation;
    private String domain;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729344;
    private static /* synthetic */ SoftReference $callSiteArray;

    public NTLMAuthScheme() {
        MetaClass metaClass;
        CallSite[] callSiteArray = NTLMAuthScheme.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public void authenticate(HTTPBuilder httpBuilder) {
        CallSite[] callSiteArray = NTLMAuthScheme.$getCallSiteArray();
        callSiteArray[0].call(callSiteArray[1].callGetProperty(httpBuilder), this.userName, this.password, this.workstation, this.domain);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = NTLMAuthScheme.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(NTLMAuthScheme.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = NTLMAuthScheme.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, NTLMAuthScheme.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = NTLMAuthScheme.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(NTLMAuthScheme.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != NTLMAuthScheme.class) {
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
        CallSite[] callSiteArray = NTLMAuthScheme.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        NTLMAuthScheme.__$swapInit();
        __timeStamp__239_neverHappen1702030729344 = l2 = 0L;
        __timeStamp = l = 1702030729344L;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String string) {
        this.userName = string;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String string) {
        this.password = string;
    }

    public String getWorkstation() {
        return this.workstation;
    }

    public void setWorkstation(String string) {
        this.workstation = string;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String string) {
        this.domain = string;
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
        stringArray[0] = "ntlm";
        stringArray[1] = "auth";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[2];
        NTLMAuthScheme.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(NTLMAuthScheme.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = NTLMAuthScheme.$createCallSiteArray();
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

