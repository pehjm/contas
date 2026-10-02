/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.authentication.CertAuthScheme;
import io.restassured.authentication.CertificateAuthSettings;
import io.restassured.authentication.ExplicitNoAuthScheme;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.authentication.FormAuthScheme;
import io.restassured.authentication.NTLMAuthScheme;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.authentication.OAuthScheme;
import io.restassured.authentication.OAuthSignature;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.internal.PreemptiveAuthSpecImpl;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.PreemptiveAuthSpec;
import io.restassured.specification.RequestSpecification;
import io.restassured.spi.AuthFilter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class AuthenticationSpecificationImpl
implements AuthenticationSpecification,
GroovyObject {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private RequestSpecification requestSpecification;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729350;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public AuthenticationSpecificationImpl(RequestSpecification requestSpecification) {
        void requestSpecification2;
        MetaClass metaClass;
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var4_4 = requestSpecification2;
        this.requestSpecification = (RequestSpecification)ScriptBytecodeAdapter.castToType(var4_4, RequestSpecification.class);
    }

    public RequestSpecification basic(String userName, String password) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        callSiteArray[0].callStatic(AssertParameter.class, userName, "userName");
        callSiteArray[1].callStatic(AssertParameter.class, password, "password");
        Object object = callSiteArray[2].callConstructor(BasicAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"userName", userName, "password", password}));
        ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        return this.requestSpecification;
    }

    public RequestSpecification ntlm(String userName, String password, String workstation, String domain) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        callSiteArray[3].callStatic(AssertParameter.class, userName, "userName");
        callSiteArray[4].callStatic(AssertParameter.class, password, "password");
        callSiteArray[5].callStatic(AssertParameter.class, workstation, "workstation");
        callSiteArray[6].callStatic(AssertParameter.class, domain, "domain");
        Object object = callSiteArray[7].callConstructor(NTLMAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"userName", userName, "password", password, "workstation", workstation, "domain", domain}));
        ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        return this.requestSpecification;
    }

    public RequestSpecification digest(String userName, String password) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        callSiteArray[8].callStatic(AssertParameter.class, userName, "userName");
        callSiteArray[9].callStatic(AssertParameter.class, password, "password");
        Object object = callSiteArray[10].callConstructor(BasicAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"userName", userName, "password", password}));
        ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        return this.requestSpecification;
    }

    public RequestSpecification certificate(String certURL, String password) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[11].callCurrent(this, certURL, password, callSiteArray[12].callStatic(CertificateAuthSettings.class)), RequestSpecification.class);
    }

    public RequestSpecification certificate(String certURL, String password, CertificateAuthSettings settings) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        callSiteArray[13].callStatic(AssertParameter.class, certURL, "certURL");
        callSiteArray[14].callStatic(AssertParameter.class, password, "password");
        callSiteArray[15].callStatic(AssertParameter.class, settings, CertificateAuthSettings.class);
        Object object = callSiteArray[16].callConstructor(CertAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"keystoreType", callSiteArray[17].callGetProperty(settings), "keyStore", callSiteArray[18].callGetProperty(settings), "pathToTrustStore", certURL, "trustStorePassword", password, "trustStoreType", callSiteArray[19].callGetProperty(settings), "trustStore", callSiteArray[20].callGetProperty(settings), "port", callSiteArray[21].callGetProperty(settings), "x509HostnameVerifier", callSiteArray[22].call(settings), "sslSocketFactory", callSiteArray[23].call(settings)}));
        ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        return this.requestSpecification;
    }

    public RequestSpecification oauth(String consumerKey, String consumerSecret, String accessToken, String secretToken) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        callSiteArray[24].callStatic(AssertParameter.class, consumerKey, "consumerKey");
        callSiteArray[25].callStatic(AssertParameter.class, consumerSecret, "consumerSecret");
        callSiteArray[26].callStatic(AssertParameter.class, accessToken, "accessToken");
        callSiteArray[27].callStatic(AssertParameter.class, secretToken, "secretToken");
        Object object = callSiteArray[28].callConstructor(OAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"consumerKey", consumerKey, "consumerSecret", consumerSecret, "accessToken", accessToken, "secretToken", secretToken}));
        ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        return this.requestSpecification;
    }

    public RequestSpecification oauth(String consumerKey, String consumerSecret, String accessToken, String secretToken, OAuthSignature signature) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        callSiteArray[29].callStatic(AssertParameter.class, consumerKey, "consumerKey");
        callSiteArray[30].callStatic(AssertParameter.class, consumerSecret, "consumerSecret");
        callSiteArray[31].callStatic(AssertParameter.class, accessToken, "accessToken");
        callSiteArray[32].callStatic(AssertParameter.class, secretToken, "secretToken");
        callSiteArray[33].callStatic(AssertParameter.class, (Object)signature, "signature");
        Object object = callSiteArray[34].callConstructor(OAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"consumerKey", consumerKey, "consumerSecret", consumerSecret, "accessToken", accessToken, "secretToken", secretToken, "signature", signature}));
        ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        return this.requestSpecification;
    }

    public RequestSpecification oauth2(String accessToken) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[35].callCurrent(this, accessToken, callSiteArray[36].callGetProperty(OAuthSignature.class)), RequestSpecification.class);
    }

    public RequestSpecification oauth2(String accessToken, OAuthSignature signature) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        callSiteArray[37].callStatic(AssertParameter.class, accessToken, "accessToken");
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual((Object)signature, callSiteArray[38].callGetProperty(OAuthSignature.class))) {
                Object object = callSiteArray[39].callConstructor(PreemptiveOAuth2HeaderScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"accessToken", accessToken}));
                ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
            } else {
                Object object = callSiteArray[40].callConstructor(OAuth2Scheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"accessToken", accessToken, "signature", signature}));
                ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
            }
        } else if (ScriptBytecodeAdapter.compareEqual((Object)signature, callSiteArray[41].callGetProperty(OAuthSignature.class))) {
            Object object = callSiteArray[42].callConstructor(PreemptiveOAuth2HeaderScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"accessToken", accessToken}));
            ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        } else {
            Object object = callSiteArray[43].callConstructor(OAuth2Scheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"accessToken", accessToken, "signature", signature}));
            ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        }
        return this.requestSpecification;
    }

    public RequestSpecification none() {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[44].callConstructor(ExplicitNoAuthScheme.class);
        ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        callSiteArray[45].call(callSiteArray[46].callGetProperty(this.requestSpecification), new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _none_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _none_closure1.$getCallSiteArray();
                return it instanceof AuthFilter;
            }

            public Object doCall() {
                CallSite[] callSiteArray = _none_closure1.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _none_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _none_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _none_closure1.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[]{};
                return new CallSiteArray(_none_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _none_closure1.$createCallSiteArray();
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
        callSiteArray[47].call((Object)this.requestSpecification, AUTHORIZATION_HEADER_NAME);
        return this.requestSpecification;
    }

    public PreemptiveAuthSpec preemptive() {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        return (PreemptiveAuthSpec)ScriptBytecodeAdapter.castToType(callSiteArray[48].callConstructor(PreemptiveAuthSpecImpl.class, this.requestSpecification), PreemptiveAuthSpec.class);
    }

    public RequestSpecification form(String userName, String password) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[49].callCurrent(this, userName, password, null), RequestSpecification.class);
    }

    public RequestSpecification form(String userName, String password, FormAuthConfig config) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[50].callConstructor(FormAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"userName", userName, "password", password, "config", config}));
        ScriptBytecodeAdapter.setProperty(object, null, this.requestSpecification, "authenticationScheme");
        return this.requestSpecification;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(AuthenticationSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, AuthenticationSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(AuthenticationSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != AuthenticationSpecificationImpl.class) {
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
        CallSite[] callSiteArray = AuthenticationSpecificationImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        AuthenticationSpecificationImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729350 = l2 = 0L;
        __timeStamp = l = 1702030729350L;
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
        stringArray[2] = "<$constructor$>";
        stringArray[3] = "notNull";
        stringArray[4] = "notNull";
        stringArray[5] = "notNull";
        stringArray[6] = "notNull";
        stringArray[7] = "<$constructor$>";
        stringArray[8] = "notNull";
        stringArray[9] = "notNull";
        stringArray[10] = "<$constructor$>";
        stringArray[11] = "certificate";
        stringArray[12] = "certAuthSettings";
        stringArray[13] = "notNull";
        stringArray[14] = "notNull";
        stringArray[15] = "notNull";
        stringArray[16] = "<$constructor$>";
        stringArray[17] = "keyStoreType";
        stringArray[18] = "keyStore";
        stringArray[19] = "trustStoreType";
        stringArray[20] = "trustStore";
        stringArray[21] = "port";
        stringArray[22] = "getX509HostnameVerifier";
        stringArray[23] = "getSSLSocketFactory";
        stringArray[24] = "notNull";
        stringArray[25] = "notNull";
        stringArray[26] = "notNull";
        stringArray[27] = "notNull";
        stringArray[28] = "<$constructor$>";
        stringArray[29] = "notNull";
        stringArray[30] = "notNull";
        stringArray[31] = "notNull";
        stringArray[32] = "notNull";
        stringArray[33] = "notNull";
        stringArray[34] = "<$constructor$>";
        stringArray[35] = "oauth2";
        stringArray[36] = "HEADER";
        stringArray[37] = "notNull";
        stringArray[38] = "HEADER";
        stringArray[39] = "<$constructor$>";
        stringArray[40] = "<$constructor$>";
        stringArray[41] = "HEADER";
        stringArray[42] = "<$constructor$>";
        stringArray[43] = "<$constructor$>";
        stringArray[44] = "<$constructor$>";
        stringArray[45] = "removeAll";
        stringArray[46] = "filters";
        stringArray[47] = "removeHeader";
        stringArray[48] = "<$constructor$>";
        stringArray[49] = "form";
        stringArray[50] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[51];
        AuthenticationSpecificationImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(AuthenticationSpecificationImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = AuthenticationSpecificationImpl.$createCallSiteArray();
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

