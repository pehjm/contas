/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.specification.RedirectSpecification;
import io.restassured.specification.RequestSpecification;
import java.lang.ref.SoftReference;
import java.util.Map;
import org.apache.http.client.params.ClientPNames;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class RedirectSpecificationImpl
implements RedirectSpecification,
GroovyObject {
    private final RequestSpecification requestSpecification;
    private final Map httpClientParams;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729361;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public RedirectSpecificationImpl(RequestSpecification requestSpecification, Map map) {
        void httpClientParams;
        void requestSpecification2;
        MetaClass metaClass;
        CallSite[] callSiteArray = RedirectSpecificationImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var5_5 = requestSpecification2;
        this.requestSpecification = (RequestSpecification)ScriptBytecodeAdapter.castToType(var5_5, RequestSpecification.class);
        void var6_6 = httpClientParams;
        this.httpClientParams = (Map)ScriptBytecodeAdapter.castToType(var6_6, Map.class);
    }

    public RequestSpecification max(int maxNumberOfRedirect) {
        CallSite[] callSiteArray = RedirectSpecificationImpl.$getCallSiteArray();
        callSiteArray[0].call(this.httpClientParams, callSiteArray[1].callGetProperty(ClientPNames.class), maxNumberOfRedirect);
        return this.requestSpecification;
    }

    public RequestSpecification follow(boolean followRedirects) {
        CallSite[] callSiteArray = RedirectSpecificationImpl.$getCallSiteArray();
        callSiteArray[2].call(this.httpClientParams, callSiteArray[3].callGetProperty(ClientPNames.class), followRedirects);
        return this.requestSpecification;
    }

    public RequestSpecification allowCircular(boolean allowCircularRedirects) {
        CallSite[] callSiteArray = RedirectSpecificationImpl.$getCallSiteArray();
        callSiteArray[4].call(this.httpClientParams, callSiteArray[5].callGetProperty(ClientPNames.class), allowCircularRedirects);
        return this.requestSpecification;
    }

    public RequestSpecification rejectRelative(boolean rejectRelativeRedirects) {
        CallSite[] callSiteArray = RedirectSpecificationImpl.$getCallSiteArray();
        callSiteArray[6].call(this.httpClientParams, callSiteArray[7].callGetProperty(ClientPNames.class), rejectRelativeRedirects);
        return this.requestSpecification;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = RedirectSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RedirectSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = RedirectSpecificationImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, RedirectSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = RedirectSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(RedirectSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RedirectSpecificationImpl.class) {
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
        CallSite[] callSiteArray = RedirectSpecificationImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        RedirectSpecificationImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729361 = l2 = 0L;
        __timeStamp = l = 1702030729361L;
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
        stringArray[0] = "put";
        stringArray[1] = "MAX_REDIRECTS";
        stringArray[2] = "put";
        stringArray[3] = "HANDLE_REDIRECTS";
        stringArray[4] = "put";
        stringArray[5] = "ALLOW_CIRCULAR_REDIRECTS";
        stringArray[6] = "put";
        stringArray[7] = "REJECT_RELATIVE_REDIRECT";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[8];
        RedirectSpecificationImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(RedirectSpecificationImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = RedirectSpecificationImpl.$createCallSiteArray();
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

