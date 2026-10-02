/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.internal.TrustAndKeystoreSpec;
import io.restassured.internal.http.HTTPBuilder;
import java.io.File;
import java.lang.ref.SoftReference;
import java.security.KeyStore;
import org.apache.commons.lang3.Validate;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class TrustAndKeystoreSpecImpl
implements TrustAndKeystoreSpec,
GroovyObject {
    private Object keyStorePath;
    private String keyStorePassword;
    private String keyStoreType;
    private KeyStore keyStore;
    private Object trustStorePath;
    private String trustStorePassword;
    private String trustStoreType;
    private KeyStore trustStore;
    private int port;
    private SSLSocketFactory factory;
    private X509HostnameVerifier x509HostnameVerifier;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729357;
    private static /* synthetic */ SoftReference $callSiteArray;

    public TrustAndKeystoreSpecImpl() {
        MetaClass metaClass;
        CallSite[] callSiteArray = TrustAndKeystoreSpecImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public void apply(HTTPBuilder builder, int port) {
        CallSite[] callSiteArray = TrustAndKeystoreSpecImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(this.factory, null)) {
                KeyStore keyStore = this.keyStore;
                KeyStore keyStore2 = DefaultTypeTransformation.booleanUnbox(keyStore) ? keyStore : callSiteArray[0].callCurrent(this, this.keyStoreType, this.keyStorePath, this.keyStorePassword);
                KeyStore keyStore3 = this.trustStore;
                KeyStore trustStore = DefaultTypeTransformation.booleanUnbox(keyStore3) ? keyStore3 : callSiteArray[1].callCurrent(this, this.trustStoreType, this.trustStorePath, this.trustStorePassword);
                Object object = callSiteArray[2].callStatic(TrustAndKeystoreSpecImpl.class, trustStore, keyStore2, this.keyStorePassword);
                this.factory = (SSLSocketFactory)ScriptBytecodeAdapter.castToType(object, SSLSocketFactory.class);
                X509HostnameVerifier x509HostnameVerifier = this.x509HostnameVerifier;
                callSiteArray[3].call((Object)this.factory, DefaultTypeTransformation.booleanUnbox(x509HostnameVerifier) ? x509HostnameVerifier : callSiteArray[4].callGetProperty(SSLSocketFactory.class));
            }
        } else if (ScriptBytecodeAdapter.compareEqual(this.factory, null)) {
            KeyStore keyStore = this.keyStore;
            KeyStore keyStore4 = DefaultTypeTransformation.booleanUnbox(keyStore) ? keyStore : callSiteArray[5].callCurrent(this, this.keyStoreType, this.keyStorePath, this.keyStorePassword);
            KeyStore keyStore5 = this.trustStore;
            KeyStore trustStore = DefaultTypeTransformation.booleanUnbox(keyStore5) ? keyStore5 : callSiteArray[6].callCurrent(this, this.trustStoreType, this.trustStorePath, this.trustStorePassword);
            Object object = callSiteArray[7].callStatic(TrustAndKeystoreSpecImpl.class, trustStore, keyStore4, this.keyStorePassword);
            this.factory = (SSLSocketFactory)ScriptBytecodeAdapter.castToType(object, SSLSocketFactory.class);
            X509HostnameVerifier x509HostnameVerifier = this.x509HostnameVerifier;
            callSiteArray[8].call((Object)this.factory, DefaultTypeTransformation.booleanUnbox(x509HostnameVerifier) ? x509HostnameVerifier : callSiteArray[9].callGetProperty(SSLSocketFactory.class));
        }
        int portToUse = 0;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Integer n = ScriptBytecodeAdapter.compareEqual(this.port, -1) ? Integer.valueOf(port) : Integer.valueOf(this.port);
            portToUse = DefaultTypeTransformation.intUnbox(n);
        } else {
            Integer n = ScriptBytecodeAdapter.compareEqual(this.port, -1) ? Integer.valueOf(port) : Integer.valueOf(this.port);
            portToUse = DefaultTypeTransformation.intUnbox(n);
        }
        callSiteArray[10].call(callSiteArray[11].callGetProperty(callSiteArray[12].callGetProperty(callSiteArray[13].callGetProperty(builder))), callSiteArray[14].callConstructor(Scheme.class, "https", portToUse, this.factory));
    }

    private static Object createSSLSocketFactory(KeyStore truststore, KeyStore keyStore, String keyPassword) {
        CallSite[] callSiteArray = TrustAndKeystoreSpecImpl.$getCallSiteArray();
        SSLSocketFactory ssl = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(truststore, null)) {
                Object object = callSiteArray[15].call(SSLSocketFactory.class);
                ssl = (SSLSocketFactory)ScriptBytecodeAdapter.castToType(object, SSLSocketFactory.class);
            } else {
                Object object = callSiteArray[16].callConstructor(SSLSocketFactory.class, callSiteArray[17].call(callSiteArray[18].call(callSiteArray[19].call(callSiteArray[20].call(SSLContexts.class), keyStore, ScriptBytecodeAdapter.compareNotEqual(keyPassword, null) ? callSiteArray[21].call(keyPassword) : null), truststore)), callSiteArray[22].callGetProperty(SSLSocketFactory.class));
                ssl = (SSLSocketFactory)ScriptBytecodeAdapter.castToType(object, SSLSocketFactory.class);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(truststore, null)) {
            Object object = callSiteArray[23].call(SSLSocketFactory.class);
            ssl = (SSLSocketFactory)ScriptBytecodeAdapter.castToType(object, SSLSocketFactory.class);
        } else {
            Object object = callSiteArray[24].callConstructor(SSLSocketFactory.class, callSiteArray[25].call(callSiteArray[26].call(callSiteArray[27].call(callSiteArray[28].call(SSLContexts.class), keyStore, ScriptBytecodeAdapter.compareNotEqual(keyPassword, null) ? callSiteArray[29].call(keyPassword) : null), truststore)), callSiteArray[30].callGetProperty(SSLSocketFactory.class));
            ssl = (SSLSocketFactory)ScriptBytecodeAdapter.castToType(object, SSLSocketFactory.class);
        }
        return ssl;
    }

    public KeyStore createStore(Object keyStoreType, Object keyStorePath, Object keyStorePassword) {
        Reference<Object> keyStorePassword2 = new Reference<Object>(keyStorePassword);
        CallSite[] callSiteArray = TrustAndKeystoreSpecImpl.$getCallSiteArray();
        Reference<Object> keyStore = new Reference<Object>(callSiteArray[31].call(KeyStore.class, keyStoreType));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(keyStorePath, null) || keyStorePath instanceof String && DefaultTypeTransformation.booleanUnbox(callSiteArray[32].call(keyStorePath)) : ScriptBytecodeAdapter.compareEqual(keyStorePath, null) || keyStorePath instanceof String && DefaultTypeTransformation.booleanUnbox(callSiteArray[33].call(keyStorePath))) {
            return (KeyStore)ScriptBytecodeAdapter.castToType(null, KeyStore.class);
        }
        Object resource = null;
        if (keyStorePath instanceof File) {
            Object object;
            resource = object = keyStorePath;
        } else {
            Object object;
            resource = object = callSiteArray[34].callSafe(callSiteArray[35].call(callSiteArray[36].call(Thread.class)), keyStorePath);
            if (ScriptBytecodeAdapter.compareEqual(resource, null)) {
                Object object2;
                resource = object2 = callSiteArray[37].call(callSiteArray[38].callCurrent(this), keyStorePath);
            }
            if (ScriptBytecodeAdapter.compareEqual(resource, null)) {
                Object object3;
                resource = object3 = callSiteArray[39].callConstructor(File.class, keyStorePath);
            }
        }
        callSiteArray[40].call(Validate.class, resource, new GStringImpl(new Object[]{keyStorePath}, new String[]{"Couldn't find java keystore file at '", "'."}));
        callSiteArray[41].call(resource, new GeneratedClosure(this, this, keyStorePassword2, keyStore){
            private /* synthetic */ Reference keyStorePassword;
            private /* synthetic */ Reference keyStore;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _createStore_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.keyStorePassword = reference2 = keyStorePassword;
                this.keyStore = reference = keyStore;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _createStore_closure1.$getCallSiteArray();
                return callSiteArray[0].call(this.keyStore.get(), it, callSiteArray[1].callSafe(this.keyStorePassword.get()));
            }

            public Object getKeyStorePassword() {
                CallSite[] callSiteArray = _createStore_closure1.$getCallSiteArray();
                return this.keyStorePassword.get();
            }

            public Object getKeyStore() {
                CallSite[] callSiteArray = _createStore_closure1.$getCallSiteArray();
                return this.keyStore.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _createStore_closure1.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _createStore_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _createStore_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _createStore_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "load";
                stringArray[1] = "toCharArray";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _createStore_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_createStore_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _createStore_closure1.$createCallSiteArray();
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
        });
        return (KeyStore)ScriptBytecodeAdapter.castToType(keyStore.get(), KeyStore.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = TrustAndKeystoreSpecImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(TrustAndKeystoreSpecImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = TrustAndKeystoreSpecImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, TrustAndKeystoreSpecImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = TrustAndKeystoreSpecImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(TrustAndKeystoreSpecImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TrustAndKeystoreSpecImpl.class) {
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
        CallSite[] callSiteArray = TrustAndKeystoreSpecImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        TrustAndKeystoreSpecImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729357 = l2 = 0L;
        __timeStamp = l = 1702030729357L;
    }

    public Object getKeyStorePath() {
        return this.keyStorePath;
    }

    public void setKeyStorePath(Object object) {
        this.keyStorePath = object;
    }

    public String getKeyStorePassword() {
        return this.keyStorePassword;
    }

    public void setKeyStorePassword(String string) {
        this.keyStorePassword = string;
    }

    public String getKeyStoreType() {
        return this.keyStoreType;
    }

    public void setKeyStoreType(String string) {
        this.keyStoreType = string;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public Object getTrustStorePath() {
        return this.trustStorePath;
    }

    public void setTrustStorePath(Object object) {
        this.trustStorePath = object;
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

    public KeyStore getTrustStore() {
        return this.trustStore;
    }

    public void setTrustStore(KeyStore keyStore) {
        this.trustStore = keyStore;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int n) {
        this.port = n;
    }

    public SSLSocketFactory getFactory() {
        return this.factory;
    }

    public void setFactory(SSLSocketFactory sSLSocketFactory) {
        this.factory = sSLSocketFactory;
    }

    public X509HostnameVerifier getX509HostnameVerifier() {
        return this.x509HostnameVerifier;
    }

    public void setX509HostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        this.x509HostnameVerifier = x509HostnameVerifier;
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
        stringArray[0] = "createStore";
        stringArray[1] = "createStore";
        stringArray[2] = "createSSLSocketFactory";
        stringArray[3] = "setHostnameVerifier";
        stringArray[4] = "ALLOW_ALL_HOSTNAME_VERIFIER";
        stringArray[5] = "createStore";
        stringArray[6] = "createStore";
        stringArray[7] = "createSSLSocketFactory";
        stringArray[8] = "setHostnameVerifier";
        stringArray[9] = "ALLOW_ALL_HOSTNAME_VERIFIER";
        stringArray[10] = "register";
        stringArray[11] = "schemeRegistry";
        stringArray[12] = "connectionManager";
        stringArray[13] = "client";
        stringArray[14] = "<$constructor$>";
        stringArray[15] = "getSocketFactory";
        stringArray[16] = "<$constructor$>";
        stringArray[17] = "build";
        stringArray[18] = "loadTrustMaterial";
        stringArray[19] = "loadKeyMaterial";
        stringArray[20] = "custom";
        stringArray[21] = "toCharArray";
        stringArray[22] = "BROWSER_COMPATIBLE_HOSTNAME_VERIFIER";
        stringArray[23] = "getSocketFactory";
        stringArray[24] = "<$constructor$>";
        stringArray[25] = "build";
        stringArray[26] = "loadTrustMaterial";
        stringArray[27] = "loadKeyMaterial";
        stringArray[28] = "custom";
        stringArray[29] = "toCharArray";
        stringArray[30] = "BROWSER_COMPATIBLE_HOSTNAME_VERIFIER";
        stringArray[31] = "getInstance";
        stringArray[32] = "isEmpty";
        stringArray[33] = "isEmpty";
        stringArray[34] = "getResource";
        stringArray[35] = "getContextClassLoader";
        stringArray[36] = "currentThread";
        stringArray[37] = "getResource";
        stringArray[38] = "getClass";
        stringArray[39] = "<$constructor$>";
        stringArray[40] = "notNull";
        stringArray[41] = "withInputStream";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[42];
        TrustAndKeystoreSpecImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(TrustAndKeystoreSpecImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = TrustAndKeystoreSpecImpl.$createCallSiteArray();
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

