/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.authentication;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.internal.http.HTTPBuilder;
import java.lang.ref.SoftReference;
import java.security.KeyStore;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class CertAuthScheme
implements AuthenticationScheme,
GroovyObject {
    private Object pathToKeyStore;
    private String keyStorePassword;
    private String keystoreType;
    private Object pathToTrustStore;
    private String trustStorePassword;
    private String trustStoreType;
    private int port;
    private KeyStore trustStore;
    private KeyStore keyStore;
    private X509HostnameVerifier x509HostnameVerifier;
    private SSLSocketFactory sslSocketFactory;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729288;
    private static /* synthetic */ SoftReference $callSiteArray;

    public CertAuthScheme() {
        MetaClass metaClass;
        int n;
        CallSite[] callSiteArray = CertAuthScheme.$getCallSiteArray();
        Object object = callSiteArray[0].call(KeyStore.class);
        this.keystoreType = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        Object object2 = callSiteArray[1].call(KeyStore.class);
        this.trustStoreType = (String)ScriptBytecodeAdapter.castToType(object2, String.class);
        this.port = n = -1;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public void authenticate(HTTPBuilder httpBuilder) {
        CallSite[] callSiteArray = CertAuthScheme.$getCallSiteArray();
        callSiteArray[2].call(callSiteArray[3].callGetProperty(httpBuilder), ArrayUtil.createArray(this.pathToKeyStore, this.keyStorePassword, this.keystoreType, this.keyStore, this.pathToTrustStore, this.trustStorePassword, this.trustStoreType, this.trustStore, this.port, this.x509HostnameVerifier, this.sslSocketFactory));
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = CertAuthScheme.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(CertAuthScheme.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = CertAuthScheme.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, CertAuthScheme.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = CertAuthScheme.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(CertAuthScheme.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != CertAuthScheme.class) {
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
        CallSite[] callSiteArray = CertAuthScheme.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        CertAuthScheme.__$swapInit();
        __timeStamp__239_neverHappen1702030729288 = l2 = 0L;
        __timeStamp = l = 1702030729288L;
    }

    public Object getPathToKeyStore() {
        return this.pathToKeyStore;
    }

    public void setPathToKeyStore(Object object) {
        this.pathToKeyStore = object;
    }

    public String getKeyStorePassword() {
        return this.keyStorePassword;
    }

    public void setKeyStorePassword(String string) {
        this.keyStorePassword = string;
    }

    public String getKeystoreType() {
        return this.keystoreType;
    }

    public void setKeystoreType(String string) {
        this.keystoreType = string;
    }

    public Object getPathToTrustStore() {
        return this.pathToTrustStore;
    }

    public void setPathToTrustStore(Object object) {
        this.pathToTrustStore = object;
    }

    public String getTrustStorePassword() {
        return this.trustStorePassword;
    }

    public void setTrustStorePassword(String string) {
        this.trustStorePassword = string;
    }

    public String getTrustStoreType() {
        return this.trustStoreType;
    }

    public void setTrustStoreType(String string) {
        this.trustStoreType = string;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int n) {
        this.port = n;
    }

    public KeyStore getTrustStore() {
        return this.trustStore;
    }

    public void setTrustStore(KeyStore keyStore) {
        this.trustStore = keyStore;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public X509HostnameVerifier getX509HostnameVerifier() {
        return this.x509HostnameVerifier;
    }

    public void setX509HostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        this.x509HostnameVerifier = x509HostnameVerifier;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public void setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.sslSocketFactory = sSLSocketFactory;
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
        stringArray[0] = "getDefaultType";
        stringArray[1] = "getDefaultType";
        stringArray[2] = "certificate";
        stringArray[3] = "auth";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[4];
        CertAuthScheme.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(CertAuthScheme.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = CertAuthScheme.$createCallSiteArray();
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

