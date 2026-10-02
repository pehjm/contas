/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.Closure;
import groovy.lang.GroovyInterceptable;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.lang.Tuple2;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.CertAuthScheme;
import io.restassured.authentication.FormAuthScheme;
import io.restassured.authentication.NoAuthScheme;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.CsrfConfig;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.ParamConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SessionConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.OrderedFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.time.TimingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.internal.AuthenticationSpecificationImpl;
import io.restassured.internal.MapCreator;
import io.restassured.internal.NoParameterValue;
import io.restassured.internal.RedirectSpecificationImpl;
import io.restassured.internal.RequestLogSpecificationImpl;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.RestAssuredHttpBuilder;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.internal.SpecificationMerger;
import io.restassured.internal.UriValidator;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.filter.CsrfFilter;
import io.restassured.internal.filter.FilterContextImpl;
import io.restassured.internal.filter.FormAuthFilter;
import io.restassured.internal.filter.SendRequestFilter;
import io.restassured.internal.http.BoundaryExtractor;
import io.restassured.internal.http.CharsetExtractor;
import io.restassured.internal.http.ContentEncoding;
import io.restassured.internal.http.ContentTypeExtractor;
import io.restassured.internal.http.HTTPBuilder;
import io.restassured.internal.http.URIBuilder;
import io.restassured.internal.log.LogRepository;
import io.restassured.internal.mapping.ObjectMapperSerializationContextImpl;
import io.restassured.internal.mapping.ObjectMapping;
import io.restassured.internal.multipart.MultiPartInternal;
import io.restassured.internal.multipart.MultiPartSpecificationImpl;
import io.restassured.internal.multipart.RestAssuredMultiPartEntity;
import io.restassured.internal.proxy.RestAssuredProxySelector;
import io.restassured.internal.proxy.RestAssuredProxySelectorRoutePlanner;
import io.restassured.internal.serialization.SerializationSupport;
import io.restassured.internal.support.ParameterUpdater;
import io.restassured.internal.support.PathSupport;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RedirectSpecification;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.spi.AuthFilter;
import java.io.File;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RequestSpecificationImpl
implements FilterableRequestSpecification,
GroovyInterceptable {
    private static final int DEFAULT_HTTP_TEST_PORT = 8080;
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String DOUBLE_SLASH = "//";
    private static final String LOCALHOST = "localhost";
    private static final String CHARSET = "charset";
    private static final String ACCEPT_HEADER_NAME = "Accept";
    private static final String SSL = "SSL";
    private static final String MULTIPART = "multipart";
    private static final String MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH;
    private static final String MULTIPART_CONTENT_TYPE_PREFIX_WITH_PLUS;
    private static final String APPLICATION_JSON = "application/json";
    private static final String TEMPLATE_START;
    private static final String TEMPLATE_END;
    private String baseUri;
    private String path;
    private String method;
    private String basePath;
    private List<Tuple2<String, String>> unnamedPathParamsTuples;
    private AuthenticationScheme defaultAuthScheme;
    private int port;
    private Map<String, String> requestParameters;
    private Map<String, String> queryParameters;
    private Map<String, String> formParameters;
    private Map<String, String> namedPathParameters;
    private Map<String, String> httpClientParams;
    private AuthenticationScheme authenticationScheme;
    private FilterableResponseSpecification responseSpecification;
    private Headers requestHeaders;
    private Cookies cookies;
    private Object requestBody;
    private List<Filter> filters;
    private boolean urlEncodingEnabled;
    private RestAssuredConfig restAssuredConfig;
    private List<MultiPartInternal> multiParts;
    private ParameterUpdater parameterUpdater;
    private ProxySpecification proxySpecification;
    private LogRepository logRepository;
    private AbstractHttpClient httpClient;
    private boolean allowContentType;
    private boolean addCsrfFilter;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729300;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$io$restassured$internal$RestAssuredHttpBuilder;

    /*
     * WARNING - void declaration
     */
    public RequestSpecificationImpl(String string, int n, String string2, AuthenticationScheme authenticationScheme, List<Filter> list, RequestSpecification requestSpecification, boolean bl, RestAssuredConfig restAssuredConfig, LogRepository logRepository, ProxySpecification proxySpecification, boolean bl2, boolean bl3) {
        void addCsrfFilter;
        void allowContentType;
        void proxySpecification2;
        void logRepository2;
        void defaultSpec;
        void restAssuredConfig2;
        void requestPort;
        void urlEncode;
        void filters;
        void defaultAuthScheme;
        void basePath;
        void baseURI;
        MetaClass metaClass;
        List list2;
        List list3;
        Map map;
        Map map2;
        String string3;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        this.path = string3 = "";
        Object object = callSiteArray[0].callConstructor(ArrayList.class);
        this.unnamedPathParamsTuples = (List)ScriptBytecodeAdapter.castToType(object, List.class);
        Object object2 = callSiteArray[1].callConstructor(LinkedHashMap.class);
        this.requestParameters = (Map)ScriptBytecodeAdapter.castToType(object2, Map.class);
        Object object3 = callSiteArray[2].callConstructor(LinkedHashMap.class);
        this.queryParameters = (Map)ScriptBytecodeAdapter.castToType(object3, Map.class);
        Object object4 = callSiteArray[3].callConstructor(LinkedHashMap.class);
        this.formParameters = (Map)ScriptBytecodeAdapter.castToType(object4, Map.class);
        this.namedPathParameters = map2 = ScriptBytecodeAdapter.createMap(new Object[0]);
        this.httpClientParams = map = ScriptBytecodeAdapter.createMap(new Object[0]);
        Object object5 = callSiteArray[4].callConstructor(NoAuthScheme.class);
        this.authenticationScheme = (AuthenticationScheme)ScriptBytecodeAdapter.castToType(object5, AuthenticationScheme.class);
        Object object6 = callSiteArray[5].callConstructor(Headers.class, ScriptBytecodeAdapter.createList(new Object[0]));
        this.requestHeaders = (Headers)ScriptBytecodeAdapter.castToType(object6, Headers.class);
        Object object7 = callSiteArray[6].callConstructor(Cookies.class, ScriptBytecodeAdapter.createList(new Object[0]));
        this.cookies = (Cookies)ScriptBytecodeAdapter.castToType(object7, Cookies.class);
        this.filters = list3 = ScriptBytecodeAdapter.createList(new Object[0]);
        this.multiParts = list2 = ScriptBytecodeAdapter.createList(new Object[0]);
        Object object8 = callSiteArray[7].callConstructor(ParameterUpdater.class, new 1(this));
        this.parameterUpdater = (ParameterUpdater)ScriptBytecodeAdapter.castToType(object8, ParameterUpdater.class);
        Object var27_27 = null;
        this.proxySpecification = (ProxySpecification)ScriptBytecodeAdapter.castToType(var27_27, ProxySpecification.class);
        this.metaClass = metaClass = this.$getStaticMetaClass();
        callSiteArray[8].callStatic(AssertParameter.class, baseURI, "baseURI");
        callSiteArray[9].callStatic(AssertParameter.class, basePath, "basePath");
        callSiteArray[10].callStatic(AssertParameter.class, defaultAuthScheme, "defaultAuthScheme");
        callSiteArray[11].callStatic(AssertParameter.class, filters, "Filters");
        callSiteArray[12].callStatic(AssertParameter.class, (boolean)urlEncode, "URL Encode query params option");
        void var29_29 = baseURI;
        this.baseUri = (String)ScriptBytecodeAdapter.castToType(var29_29, String.class);
        void var30_30 = basePath;
        this.basePath = (String)ScriptBytecodeAdapter.castToType(var30_30, String.class);
        void var31_31 = defaultAuthScheme;
        this.defaultAuthScheme = (AuthenticationScheme)ScriptBytecodeAdapter.castToType(var31_31, AuthenticationScheme.class);
        callSiteArray[13].call(this.filters, filters);
        void var32_32 = urlEncode;
        this.urlEncodingEnabled = DefaultTypeTransformation.booleanUnbox((boolean)var32_32);
        callSiteArray[14].callCurrent((GroovyObject)this, (int)requestPort);
        void var33_33 = restAssuredConfig2;
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(var33_33, RestAssuredConfig.class);
        if (!BytecodeInterface8.isOrigZ() || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(defaultSpec, null)) {
                callSiteArray[15].callCurrent((GroovyObject)this, defaultSpec);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(defaultSpec, null)) {
            callSiteArray[16].callCurrent((GroovyObject)this, defaultSpec);
        }
        void var34_34 = logRepository2;
        this.logRepository = (LogRepository)ScriptBytecodeAdapter.castToType(var34_34, LogRepository.class);
        void var35_35 = proxySpecification2;
        this.proxySpecification = (ProxySpecification)ScriptBytecodeAdapter.castToType(var35_35, ProxySpecification.class);
        void var36_36 = allowContentType;
        this.allowContentType = DefaultTypeTransformation.booleanUnbox((boolean)var36_36);
        void var37_37 = addCsrfFilter;
        this.addCsrfFilter = DefaultTypeTransformation.booleanUnbox((boolean)var37_37);
    }

    @Override
    public RequestSpecification when() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public RequestSpecification given() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public RequestSpecification that() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public ResponseSpecification response() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.responseSpecification;
    }

    @Override
    public Response get(String path, Object ... pathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[17].callCurrent(this, callSiteArray[18].callGetProperty(Method.class), path, pathParams), Response.class);
    }

    @Override
    public Response post(String path, Object ... pathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[19].callCurrent(this, callSiteArray[20].callGetProperty(Method.class), path, pathParams), Response.class);
    }

    @Override
    public Response put(String path, Object ... pathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[21].callCurrent(this, callSiteArray[22].callGetProperty(Method.class), path, pathParams), Response.class);
    }

    @Override
    public Response delete(String path, Object ... pathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[23].callCurrent(this, callSiteArray[24].callGetProperty(Method.class), path, pathParams), Response.class);
    }

    @Override
    public Response head(String path, Object ... pathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[25].callCurrent(this, callSiteArray[26].callGetProperty(Method.class), path, pathParams), Response.class);
    }

    @Override
    public Response patch(String path, Object ... pathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[27].callCurrent(this, callSiteArray[28].callGetProperty(Method.class), path, pathParams), Response.class);
    }

    @Override
    public Response options(String path, Object ... pathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[29].callCurrent(this, callSiteArray[30].callGetProperty(Method.class), path, pathParams), Response.class);
    }

    @Override
    public Response get(URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[31].callCurrent((GroovyObject)this, callSiteArray[32].call(callSiteArray[33].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    @Override
    public Response post(URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[34].callCurrent((GroovyObject)this, callSiteArray[35].call(callSiteArray[36].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    @Override
    public Response put(URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[37].callCurrent((GroovyObject)this, callSiteArray[38].call(callSiteArray[39].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    @Override
    public Response delete(URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[40].callCurrent((GroovyObject)this, callSiteArray[41].call(callSiteArray[42].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    @Override
    public Response head(URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[43].callCurrent((GroovyObject)this, callSiteArray[44].call(callSiteArray[45].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    @Override
    public Response patch(URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[46].callCurrent((GroovyObject)this, callSiteArray[47].call(callSiteArray[48].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    @Override
    public Response options(URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[49].callCurrent((GroovyObject)this, callSiteArray[50].call(callSiteArray[51].callStatic(AssertParameter.class, uri, "URI"))), Response.class);
    }

    @Override
    public Response get(URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[52].callCurrent((GroovyObject)this, callSiteArray[53].call(callSiteArray[54].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    @Override
    public Response post(URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[55].callCurrent((GroovyObject)this, callSiteArray[56].call(callSiteArray[57].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    @Override
    public Response put(URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[58].callCurrent((GroovyObject)this, callSiteArray[59].call(callSiteArray[60].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    @Override
    public Response delete(URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[61].callCurrent((GroovyObject)this, callSiteArray[62].call(callSiteArray[63].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    @Override
    public Response head(URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[64].callCurrent((GroovyObject)this, callSiteArray[65].call(callSiteArray[66].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    @Override
    public Response patch(URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[67].callCurrent((GroovyObject)this, callSiteArray[68].call(callSiteArray[69].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    @Override
    public Response options(URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[70].callCurrent((GroovyObject)this, callSiteArray[71].call(callSiteArray[72].callStatic(AssertParameter.class, url, "URL"))), Response.class);
    }

    @Override
    public Response get() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[73].callCurrent((GroovyObject)this, ""), Response.class);
    }

    @Override
    public Response post() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[74].callCurrent((GroovyObject)this, ""), Response.class);
    }

    @Override
    public Response put() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[75].callCurrent((GroovyObject)this, ""), Response.class);
    }

    @Override
    public Response delete() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[76].callCurrent((GroovyObject)this, ""), Response.class);
    }

    @Override
    public Response head() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[77].callCurrent((GroovyObject)this, ""), Response.class);
    }

    @Override
    public Response patch() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[78].callCurrent((GroovyObject)this, ""), Response.class);
    }

    @Override
    public Response options() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[79].callCurrent((GroovyObject)this, ""), Response.class);
    }

    @Override
    public Response request(Method method) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[80].callCurrent((GroovyObject)this, callSiteArray[81].call(callSiteArray[82].callStatic(AssertParameter.class, (Object)method, Method.class))), Response.class);
    }

    @Override
    public Response request(String method) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[83].callCurrent(this, method, ""), Response.class);
    }

    @Override
    public Response request(Method method, String path, Object ... pathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[84].callCurrent(this, callSiteArray[85].call(callSiteArray[86].callStatic(AssertParameter.class, (Object)method, Method.class)), path, pathParams), Response.class);
    }

    @Override
    public Response request(String method, String path, Object ... pathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[87].callCurrent(this, method, path, pathParams), Response.class);
    }

    @Override
    public Response request(Method method, URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[88].callCurrent(this, (Object)method, callSiteArray[89].call(callSiteArray[90].callStatic(AssertParameter.class, uri, callSiteArray[91].callGetProperty(callSiteArray[92].callGroovyObjectGetProperty(this))))), Response.class);
    }

    @Override
    public Response request(Method method, URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[93].callCurrent(this, (Object)method, callSiteArray[94].call(callSiteArray[95].callStatic(AssertParameter.class, url, URL.class))), Response.class);
    }

    @Override
    public Response request(String method, URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[96].callCurrent(this, method, callSiteArray[97].call(callSiteArray[98].callStatic(AssertParameter.class, uri, callSiteArray[99].callGetProperty(callSiteArray[100].callGroovyObjectGetProperty(this))))), Response.class);
    }

    @Override
    public Response request(String method, URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[101].callCurrent(this, method, callSiteArray[102].call(callSiteArray[103].callStatic(AssertParameter.class, url, URL.class))), Response.class);
    }

    @Override
    public Response get(String path, Map pathParamsMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[104].callCurrent((GroovyObject)this, pathParamsMap);
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[105].callCurrent(this, callSiteArray[106].callGetProperty(Method.class), path), Response.class);
    }

    @Override
    public Response post(String path, Map pathParamsMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[107].callCurrent((GroovyObject)this, pathParamsMap);
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[108].callCurrent(this, callSiteArray[109].callGetProperty(Method.class), path), Response.class);
    }

    @Override
    public Response put(String path, Map pathParamsMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[110].callCurrent((GroovyObject)this, pathParamsMap);
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[111].callCurrent(this, callSiteArray[112].callGetProperty(Method.class), path), Response.class);
    }

    @Override
    public Response delete(String path, Map pathParamsMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[113].callCurrent((GroovyObject)this, pathParamsMap);
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[114].callCurrent(this, callSiteArray[115].callGetProperty(Method.class), path), Response.class);
    }

    @Override
    public Response head(String path, Map pathParamsMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[116].callCurrent((GroovyObject)this, pathParamsMap);
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[117].callCurrent(this, callSiteArray[118].callGetProperty(Method.class), path), Response.class);
    }

    @Override
    public Response patch(String path, Map pathParamsMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[119].callCurrent((GroovyObject)this, pathParamsMap);
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[120].callCurrent(this, callSiteArray[121].callGetProperty(Method.class), path), Response.class);
    }

    @Override
    public Response options(String path, Map pathParamsMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[122].callCurrent((GroovyObject)this, pathParamsMap);
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[123].callCurrent(this, callSiteArray[124].callGetProperty(Method.class), path), Response.class);
    }

    @Override
    public RequestSpecification params(String firstParameterName, Object firstParameterValue, Object ... parameterNameValuePairs) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[125].callStatic(AssertParameter.class, firstParameterName, "firstParameterName");
        callSiteArray[126].callStatic(AssertParameter.class, firstParameterValue, "firstParameterValue");
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[127].callCurrent((GroovyObject)this, callSiteArray[128].call(MapCreator.class, callSiteArray[129].callGetProperty(MapCreator.CollisionStrategy.class), firstParameterName, firstParameterValue, parameterNameValuePairs)), RequestSpecification.class);
    }

    public RequestSpecification params(Map parametersMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[130].callStatic(AssertParameter.class, parametersMap, "parametersMap");
        callSiteArray[131].call(this.parameterUpdater, callSiteArray[132].call(callSiteArray[133].callGetProperty(callSiteArray[134].callCurrent(this))), parametersMap, this.requestParameters);
        return this;
    }

    @Override
    public RequestSpecification param(String parameterName, Object ... parameterValues) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[135].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[136].call(this.parameterUpdater, callSiteArray[137].call(callSiteArray[138].callGetProperty(callSiteArray[139].callCurrent(this))), this.requestParameters, parameterName, parameterValues);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeParam(String parameterName) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[140].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[141].call(this.requestParameters, parameterName);
        return this;
    }

    @Override
    public RequestSpecification param(String parameterName, Collection<?> parameterValues) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[142].callStatic(AssertParameter.class, parameterValues, "parameterValues");
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[143].callCurrent(this, parameterName, callSiteArray[144].call(parameterValues)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification queryParam(String parameterName, Collection<?> parameterValues) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[145].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[146].callStatic(AssertParameter.class, parameterValues, "parameterValues");
        callSiteArray[147].call(this.parameterUpdater, callSiteArray[148].call(callSiteArray[149].call(callSiteArray[150].callCurrent(this))), this.queryParameters, parameterName, parameterValues);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeQueryParam(String parameterName) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[151].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[152].call(this.queryParameters, parameterName);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeHeader(String headerName) {
        Reference<String> headerName2 = new Reference<String>(headerName);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[153].callStatic(AssertParameter.class, headerName2.get(), "headerName");
        Object headersLeftAfterRemove = callSiteArray[154].call(callSiteArray[155].callGroovyObjectGetProperty(this), new GeneratedClosure(this, this, headerName2){
            private /* synthetic */ Reference headerName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _removeHeader_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.headerName = reference = headerName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _removeHeader_closure1.$getCallSiteArray();
                return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(this.headerName.get(), callSiteArray[1].call(it)));
            }

            public String getHeaderName() {
                CallSite[] callSiteArray = _removeHeader_closure1.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.headerName.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _removeHeader_closure1.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _removeHeader_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _removeHeader_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _removeHeader_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "equalsIgnoreCase";
                stringArray[1] = "getName";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _removeHeader_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_removeHeader_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _removeHeader_closure1.$createCallSiteArray();
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
        Object object = callSiteArray[156].callConstructor(Headers.class, headersLeftAfterRemove);
        this.requestHeaders = (Headers)ScriptBytecodeAdapter.castToType(object, Headers.class);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeCookie(String cookieName) {
        Reference<String> cookieName2 = new Reference<String>(cookieName);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[157].callStatic(AssertParameter.class, cookieName2.get(), "cookieName");
        Object cookiesLeftAfterRemove = callSiteArray[158].call((Object)this.cookies, new GeneratedClosure(this, this, cookieName2){
            private /* synthetic */ Reference cookieName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _removeCookie_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.cookieName = reference = cookieName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _removeCookie_closure2.$getCallSiteArray();
                return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(this.cookieName.get(), callSiteArray[1].call(it)));
            }

            public String getCookieName() {
                CallSite[] callSiteArray = _removeCookie_closure2.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.cookieName.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _removeCookie_closure2.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _removeCookie_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _removeCookie_closure2.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _removeCookie_closure2.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "equalsIgnoreCase";
                stringArray[1] = "getName";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _removeCookie_closure2.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_removeCookie_closure2.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _removeCookie_closure2.$createCallSiteArray();
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
        Object object = callSiteArray[159].callConstructor(Cookies.class, cookiesLeftAfterRemove);
        this.cookies = (Cookies)ScriptBytecodeAdapter.castToType(object, Cookies.class);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeCookie(Cookie cookie) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[160].callStatic(AssertParameter.class, cookie, "cookie");
        callSiteArray[161].callCurrent((GroovyObject)this, callSiteArray[162].call(cookie));
        return this;
    }

    @Override
    public FilterableRequestSpecification replaceHeader(String headerName, String newValue) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[163].callStatic(AssertParameter.class, headerName, "headerName");
        callSiteArray[164].callCurrent((GroovyObject)this, headerName);
        List headerList = ScriptBytecodeAdapter.createList(new Object[0]);
        callSiteArray[165].call((Object)headerList, callSiteArray[166].call(this.requestHeaders));
        callSiteArray[167].call((Object)headerList, callSiteArray[168].callConstructor(Header.class, headerName, newValue));
        Object object = callSiteArray[169].callConstructor(Headers.class, headerList);
        this.requestHeaders = (Headers)ScriptBytecodeAdapter.castToType(object, Headers.class);
        return this;
    }

    @Override
    public FilterableRequestSpecification replaceCookie(String cookieName, String value) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[170].callStatic(AssertParameter.class, cookieName, "cookieName");
        callSiteArray[171].callCurrent((GroovyObject)this, cookieName);
        callSiteArray[172].callCurrent(this, cookieName, value);
        return this;
    }

    @Override
    public FilterableRequestSpecification replaceCookie(Cookie cookie) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[173].callStatic(AssertParameter.class, cookie, "cookie");
        callSiteArray[174].callCurrent((GroovyObject)this, callSiteArray[175].call(cookie));
        callSiteArray[176].callCurrent((GroovyObject)this, cookie);
        return this;
    }

    @Override
    public FilterableRequestSpecification replaceHeaders(Headers headers) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[177].callStatic(AssertParameter.class, headers, "headers");
        Object object = callSiteArray[178].callConstructor(Headers.class, callSiteArray[179].call(headers));
        this.requestHeaders = (Headers)ScriptBytecodeAdapter.castToType(object, Headers.class);
        return this;
    }

    @Override
    public FilterableRequestSpecification replaceCookies(Cookies cookies) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[180].callStatic(AssertParameter.class, cookies, "cookies");
        Object object = callSiteArray[181].callConstructor(Cookies.class, callSiteArray[182].call(cookies));
        this.cookies = (Cookies)ScriptBytecodeAdapter.castToType(object, Cookies.class);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeHeaders() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[183].callConstructor(Headers.class, ScriptBytecodeAdapter.createList(new Object[0]));
        this.requestHeaders = (Headers)ScriptBytecodeAdapter.castToType(object, Headers.class);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeCookies() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[184].callConstructor(Cookies.class, ScriptBytecodeAdapter.createList(new Object[0]));
        this.cookies = (Cookies)ScriptBytecodeAdapter.castToType(object, Cookies.class);
        return this;
    }

    @Override
    public RequestSpecification queryParams(String firstParameterName, Object firstParameterValue, Object ... parameterNameValuePairs) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[185].callStatic(AssertParameter.class, firstParameterName, "firstParameterName");
        callSiteArray[186].callStatic(AssertParameter.class, firstParameterValue, "firstParameterValue");
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[187].callCurrent((GroovyObject)this, callSiteArray[188].call(MapCreator.class, callSiteArray[189].callGetProperty(MapCreator.CollisionStrategy.class), firstParameterName, firstParameterValue, parameterNameValuePairs)), RequestSpecification.class);
    }

    public RequestSpecification queryParams(Map parametersMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[190].callStatic(AssertParameter.class, parametersMap, "parametersMap");
        callSiteArray[191].call(this.parameterUpdater, callSiteArray[192].call(callSiteArray[193].callGetProperty(callSiteArray[194].callCurrent(this))), parametersMap, this.queryParameters);
        return this;
    }

    @Override
    public RequestSpecification queryParam(String parameterName, Object ... parameterValues) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[195].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[196].call(this.parameterUpdater, callSiteArray[197].call(callSiteArray[198].callGetProperty(callSiteArray[199].callCurrent(this))), this.queryParameters, parameterName, parameterValues);
        return this;
    }

    @Override
    public RequestSpecification formParam(String parameterName, Collection<?> parameterValues) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[200].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[201].callStatic(AssertParameter.class, parameterValues, "parameterValues");
        callSiteArray[202].call(this.parameterUpdater, callSiteArray[203].call(callSiteArray[204].callGetProperty(callSiteArray[205].callCurrent(this))), this.formParameters, parameterName, parameterValues);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeFormParam(String parameterName) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[206].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[207].call(this.formParameters, parameterName);
        return this;
    }

    @Override
    public RequestSpecification formParams(String firstParameterName, Object firstParameterValue, Object ... parameterNameValuePairs) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[208].callStatic(AssertParameter.class, firstParameterName, "firstParameterName");
        callSiteArray[209].callStatic(AssertParameter.class, firstParameterValue, "firstParameterValue");
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[210].callCurrent((GroovyObject)this, callSiteArray[211].call(MapCreator.class, callSiteArray[212].callGetProperty(MapCreator.CollisionStrategy.class), firstParameterName, firstParameterValue, parameterNameValuePairs)), RequestSpecification.class);
    }

    public RequestSpecification formParams(Map parametersMap) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[213].callStatic(AssertParameter.class, parametersMap, "parametersMap");
        callSiteArray[214].call(this.parameterUpdater, callSiteArray[215].call(callSiteArray[216].callGetProperty(callSiteArray[217].callCurrent(this))), parametersMap, this.formParameters);
        return this;
    }

    @Override
    public RequestSpecification formParam(String parameterName, Object ... additionalParameterValues) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[218].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[219].call(this.parameterUpdater, callSiteArray[220].call(callSiteArray[221].callGetProperty(callSiteArray[222].callCurrent(this))), this.formParameters, parameterName, additionalParameterValues);
        return this;
    }

    @Override
    public RequestSpecification urlEncodingEnabled(boolean isEnabled) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        boolean bl = isEnabled;
        this.urlEncodingEnabled = DefaultTypeTransformation.booleanUnbox(bl);
        return this;
    }

    @Override
    public RequestSpecification pathParam(String parameterName, Object parameterValue) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[223].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[224].callStatic(AssertParameter.class, parameterValue, "parameterValue");
        callSiteArray[225].call(this.parameterUpdater, callSiteArray[226].callGetProperty(ParamConfig.UpdateStrategy.class), this.namedPathParameters, parameterName, parameterValue);
        return this;
    }

    @Override
    public RequestSpecification pathParams(String firstParameterName, Object firstParameterValue, Object ... parameterNameValuePairs) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[227].callStatic(AssertParameter.class, firstParameterName, "firstParameterName");
        callSiteArray[228].callStatic(AssertParameter.class, firstParameterValue, "firstParameterValue");
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[229].callCurrent((GroovyObject)this, callSiteArray[230].call(MapCreator.class, callSiteArray[231].callGetProperty(MapCreator.CollisionStrategy.class), firstParameterName, firstParameterValue, parameterNameValuePairs)), RequestSpecification.class);
    }

    public RequestSpecification pathParams(Map parameterNameValuePairs) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[232].callStatic(AssertParameter.class, parameterNameValuePairs, "parameterNameValuePairs");
        callSiteArray[233].call(this.parameterUpdater, callSiteArray[234].callGetProperty(ParamConfig.UpdateStrategy.class), parameterNameValuePairs, this.namedPathParameters);
        return this;
    }

    @Override
    public FilterableRequestSpecification removePathParam(String parameterName) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[235].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[236].callCurrent((GroovyObject)this, parameterName);
        callSiteArray[237].callCurrent((GroovyObject)this, parameterName);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeNamedPathParam(String parameterName) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[238].callStatic(AssertParameter.class, parameterName, "parameterName");
        callSiteArray[239].call(this.namedPathParameters, parameterName);
        return this;
    }

    @Override
    public FilterableRequestSpecification removeUnnamedPathParam(String parameterName) {
        Reference<String> parameterName2 = new Reference<String>(parameterName);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[240].callStatic(AssertParameter.class, parameterName2.get(), "parameterName");
        Object indexOfParamName = callSiteArray[241].call(this.unnamedPathParamsTuples, new GeneratedClosure(this, this, parameterName2){
            private /* synthetic */ Reference parameterName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _removeUnnamedPathParam_closure3.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.parameterName = reference = parameterName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _removeUnnamedPathParam_closure3.$getCallSiteArray();
                if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                    return ScriptBytecodeAdapter.compareEqual(callSiteArray[0].callGetProperty(it), this.parameterName.get());
                }
                return ScriptBytecodeAdapter.compareEqual(callSiteArray[1].callGetProperty(it), this.parameterName.get());
            }

            public String getParameterName() {
                CallSite[] callSiteArray = _removeUnnamedPathParam_closure3.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.parameterName.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _removeUnnamedPathParam_closure3.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _removeUnnamedPathParam_closure3.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _removeUnnamedPathParam_closure3.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _removeUnnamedPathParam_closure3.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "v1";
                stringArray[1] = "v1";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _removeUnnamedPathParam_closure3.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_removeUnnamedPathParam_closure3.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _removeUnnamedPathParam_closure3.$createCallSiteArray();
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
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareGreaterThan(indexOfParamName, -1)) {
                callSiteArray[242].callCurrent((GroovyObject)this, indexOfParamName);
            }
        } else if (ScriptBytecodeAdapter.compareGreaterThan(indexOfParamName, -1)) {
            callSiteArray[243].callCurrent((GroovyObject)this, indexOfParamName);
        }
        return this;
    }

    @Override
    public FilterableRequestSpecification removeUnnamedPathParamByValue(String parameterValue) {
        Reference<String> parameterValue2 = new Reference<String>(parameterValue);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[244].callStatic(AssertParameter.class, parameterValue2.get(), "parameterValue");
        Object indexOfParamValue = callSiteArray[245].call(this.unnamedPathParamsTuples, new GeneratedClosure(this, this, parameterValue2){
            private /* synthetic */ Reference parameterValue;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _removeUnnamedPathParamByValue_closure4.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.parameterValue = reference = parameterValue;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _removeUnnamedPathParamByValue_closure4.$getCallSiteArray();
                if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                    return ScriptBytecodeAdapter.compareEqual(callSiteArray[0].callGetProperty(it), this.parameterValue.get());
                }
                return ScriptBytecodeAdapter.compareEqual(callSiteArray[1].callGetProperty(it), this.parameterValue.get());
            }

            public String getParameterValue() {
                CallSite[] callSiteArray = _removeUnnamedPathParamByValue_closure4.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.parameterValue.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _removeUnnamedPathParamByValue_closure4.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _removeUnnamedPathParamByValue_closure4.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _removeUnnamedPathParamByValue_closure4.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _removeUnnamedPathParamByValue_closure4.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "v2";
                stringArray[1] = "v2";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _removeUnnamedPathParamByValue_closure4.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_removeUnnamedPathParamByValue_closure4.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _removeUnnamedPathParamByValue_closure4.$createCallSiteArray();
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
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareGreaterThan(indexOfParamValue, -1)) {
                callSiteArray[246].callCurrent((GroovyObject)this, indexOfParamValue);
            }
        } else if (ScriptBytecodeAdapter.compareGreaterThan(indexOfParamValue, -1)) {
            callSiteArray[247].callCurrent((GroovyObject)this, indexOfParamValue);
        }
        return this;
    }

    @Override
    public RequestSpecification config(RestAssuredConfig config) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        RestAssuredConfig restAssuredConfig = config;
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(restAssuredConfig, RestAssuredConfig.class);
        RestAssuredConfig restAssuredConfig2 = config;
        ScriptBytecodeAdapter.setPropertySafe(restAssuredConfig2, null, this.responseSpecification, "config");
        return this;
    }

    @Override
    public RequestSpecification keyStore(String pathToJks, String password) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object sslConfig = callSiteArray[248].call(callSiteArray[249].callCurrent(this));
        Object object = callSiteArray[250].call(callSiteArray[251].callCurrent(this), callSiteArray[252].call(callSiteArray[253].call(sslConfig, pathToJks, password)));
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return this;
    }

    @Override
    public RequestSpecification keyStore(File pathToJks, String password) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object sslConfig = callSiteArray[254].call(callSiteArray[255].callCurrent(this));
        Object object = callSiteArray[256].call(callSiteArray[257].callCurrent(this), callSiteArray[258].call(callSiteArray[259].call(sslConfig, pathToJks, password)));
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return this;
    }

    @Override
    public RequestSpecification trustStore(String path, String password) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object sslConfig = callSiteArray[260].call(callSiteArray[261].callCurrent(this));
        Object object = callSiteArray[262].call(callSiteArray[263].callCurrent(this), callSiteArray[264].call(callSiteArray[265].call(sslConfig, path, password)));
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return this;
    }

    @Override
    public RequestSpecification trustStore(File path, String password) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object sslConfig = callSiteArray[266].call(callSiteArray[267].callCurrent(this));
        Object object = callSiteArray[268].call(callSiteArray[269].callCurrent(this), callSiteArray[270].call(callSiteArray[271].call(sslConfig, path, password)));
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return this;
    }

    @Override
    public RequestSpecification trustStore(KeyStore trustStore) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object sslConfig = callSiteArray[272].call(callSiteArray[273].callCurrent(this));
        Object object = callSiteArray[274].call(callSiteArray[275].callCurrent(this), callSiteArray[276].call(sslConfig, trustStore));
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return this;
    }

    @Override
    public RequestSpecification keyStore(KeyStore keyStore) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object sslConfig = callSiteArray[277].call(callSiteArray[278].callCurrent(this));
        Object object = callSiteArray[279].call(callSiteArray[280].callCurrent(this), callSiteArray[281].call(sslConfig, keyStore));
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return this;
    }

    @Override
    public RequestSpecification relaxedHTTPSValidation() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[282].callCurrent((GroovyObject)this, SSL), RequestSpecification.class);
    }

    @Override
    public RequestSpecification relaxedHTTPSValidation(String protocol) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object sslConfig = callSiteArray[283].call(callSiteArray[284].callCurrent(this));
        Object object = callSiteArray[285].call(callSiteArray[286].callCurrent(this), callSiteArray[287].call(sslConfig, protocol));
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return this;
    }

    @Override
    public RequestSpecification filter(Filter filter) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[288].callStatic(AssertParameter.class, filter, "Filter");
        callSiteArray[289].call(this.filters, filter);
        return this;
    }

    @Override
    public RequestSpecification filters(List<Filter> filters) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[290].callStatic(AssertParameter.class, filters, "Filters");
        callSiteArray[291].call(this.filters, filters);
        return this;
    }

    @Override
    public RequestSpecification filters(Filter filter, Filter ... additionalFilter) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[292].callStatic(AssertParameter.class, filter, "Filter");
        callSiteArray[293].call(this.filters, filter);
        callSiteArray[294].callSafe((Object)additionalFilter, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _filters_closure5.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _filters_closure5.$getCallSiteArray();
                return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this.getThisObject()), it);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _filters_closure5.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _filters_closure5.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _filters_closure5.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _filters_closure5.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "add";
                stringArray[1] = "filters";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _filters_closure5.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_filters_closure5.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _filters_closure5.$createCallSiteArray();
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
        return this;
    }

    @Override
    public RequestLogSpecification log() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object blacklistedHeaders = callSiteArray[295].call(callSiteArray[296].callGetProperty(callSiteArray[297].callCurrent(this)));
        return (RequestLogSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[298].callConstructor(RequestLogSpecificationImpl.class, ScriptBytecodeAdapter.createMap(new Object[]{"requestSpecification", this, "logRepository", this.logRepository, "blacklistedHeaders", blacklistedHeaders})), RequestLogSpecification.class);
    }

    @Override
    public RequestSpecification and() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public RequestSpecification request() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public RequestSpecification with() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public ResponseSpecification then() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.responseSpecification;
    }

    @Override
    public ResponseSpecification expect() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.responseSpecification;
    }

    @Override
    public AuthenticationSpecification auth() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (AuthenticationSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[299].callConstructor(AuthenticationSpecificationImpl.class, this), AuthenticationSpecification.class);
    }

    @Override
    public RequestSpecification csrf(String csrfTokenPath) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[300].call(callSiteArray[301].callCurrent(this), callSiteArray[302].call(callSiteArray[303].callCurrent(this), csrfTokenPath));
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return this;
    }

    @Override
    public RequestSpecification csrf(String csrfTokenPath, String csrfInputFieldName) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[304].call(callSiteArray[305].callCurrent(this), callSiteArray[306].call(callSiteArray[307].call(callSiteArray[308].callCurrent(this), csrfTokenPath), csrfInputFieldName));
        this.restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return this;
    }

    @Override
    public RequestSpecification disableCsrf() {
        boolean bl;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        this.addCsrfFilter = bl = false;
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[309].callCurrent((GroovyObject)this, CsrfFilter.class), RequestSpecification.class);
    }

    public AuthenticationSpecification authentication() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (AuthenticationSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[310].callCurrent(this), AuthenticationSpecification.class);
    }

    @Override
    public RequestSpecification port(int port) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (port < 1 && ScriptBytecodeAdapter.compareNotEqual(port, callSiteArray[311].callGetProperty(RestAssured.class))) {
                throw (Throwable)callSiteArray[312].callConstructor(IllegalArgumentException.class, "Port must be greater than 0");
            }
        } else if (port < 1 && ScriptBytecodeAdapter.compareNotEqual(port, callSiteArray[313].callGetProperty(RestAssured.class))) {
            throw (Throwable)callSiteArray[314].callConstructor(IllegalArgumentException.class, "Port must be greater than 0");
        }
        int n = port;
        this.port = DefaultTypeTransformation.intUnbox(n);
        return this;
    }

    @Override
    public RequestSpecification body(String body) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[315].callStatic(AssertParameter.class, body, "body");
        String string = body;
        this.requestBody = string;
        return this;
    }

    @Override
    public RequestSpecification baseUri(String baseUri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[316].callStatic(AssertParameter.class, baseUri, "Base URI");
        String string = baseUri;
        this.baseUri = (String)ScriptBytecodeAdapter.castToType(string, String.class);
        return this;
    }

    @Override
    public RequestSpecification basePath(String basePath) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[317].callStatic(AssertParameter.class, basePath, "Base Path");
        String string = basePath;
        this.basePath = (String)ScriptBytecodeAdapter.castToType(string, String.class);
        return this;
    }

    @Override
    public RequestSpecification proxy(String host, int port) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[318].callCurrent((GroovyObject)this, callSiteArray[319].call(callSiteArray[320].call(ProxySpecification.class, host), port)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification proxy(String host) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[321].call(UriValidator.class, host))) {
            return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[322].callCurrent((GroovyObject)this, callSiteArray[323].callConstructor(URI.class, host)), RequestSpecification.class);
        }
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[324].callCurrent((GroovyObject)this, callSiteArray[325].call(ProxySpecification.class, host)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification proxy(int port) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[326].callCurrent((GroovyObject)this, callSiteArray[327].call(ProxySpecification.class, port)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification proxy(String host, int port, String scheme) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[328].callCurrent((GroovyObject)this, callSiteArray[329].call(callSiteArray[330].call(callSiteArray[331].call(callSiteArray[332].call(callSiteArray[333].callConstructor(org.apache.http.client.utils.URIBuilder.class), host), port), scheme))), RequestSpecification.class);
    }

    @Override
    public RequestSpecification proxy(URI uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[334].callStatic(AssertParameter.class, uri, callSiteArray[335].callGetProperty(callSiteArray[336].callGroovyObjectGetProperty(this)));
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[337].callCurrent((GroovyObject)this, callSiteArray[338].callConstructor(ProxySpecification.class, callSiteArray[339].callGetProperty(uri), callSiteArray[340].callGetProperty(uri), callSiteArray[341].callGetProperty(uri))), RequestSpecification.class);
    }

    @Override
    public RequestSpecification proxy(ProxySpecification proxySpecification) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[342].callStatic(AssertParameter.class, proxySpecification, ProxySpecification.class);
        ProxySpecification proxySpecification2 = proxySpecification;
        this.proxySpecification = (ProxySpecification)ScriptBytecodeAdapter.castToType(proxySpecification2, ProxySpecification.class);
        return this;
    }

    @Override
    public RequestSpecification body(byte ... body) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[343].callStatic(AssertParameter.class, body, "body");
        byte[] byArray = body;
        this.requestBody = byArray;
        return this;
    }

    @Override
    public RequestSpecification body(File body) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[344].callStatic(AssertParameter.class, body, "body");
        File file = body;
        this.requestBody = file;
        return this;
    }

    @Override
    public RequestSpecification body(InputStream body) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[345].callStatic(AssertParameter.class, body, "body");
        InputStream inputStream = body;
        this.requestBody = inputStream;
        return this;
    }

    @Override
    public RequestSpecification body(Object object) {
        Object object2;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[346].callStatic(AssertParameter.class, object, "object");
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[347].callStatic(SerializationSupport.class, object))) {
            return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[348].callCurrent((GroovyObject)this, callSiteArray[349].call(object)), RequestSpecification.class);
        }
        this.requestBody = object2 = callSiteArray[350].call((Object)ObjectMapping.class, ArrayUtil.createArray(object, callSiteArray[351].callGroovyObjectGetProperty(this), callSiteArray[352].callCurrent((GroovyObject)this, callSiteArray[353].callGroovyObjectGetProperty(this)), null, callSiteArray[354].callCurrent(this), callSiteArray[355].call(callSiteArray[356].callCurrent(this))));
        return this;
    }

    @Override
    public RequestSpecification body(Object object, ObjectMapper mapper) {
        Object object2;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[357].callStatic(AssertParameter.class, object, "object");
        callSiteArray[358].callStatic(AssertParameter.class, mapper, "Object mapper");
        Object ctx = callSiteArray[359].callConstructor(ObjectMapperSerializationContextImpl.class);
        callSiteArray[360].call(ctx, object);
        callSiteArray[361].call(ctx, callSiteArray[362].callCurrent((GroovyObject)this, callSiteArray[363].callGroovyObjectGetProperty(this)));
        callSiteArray[364].call(ctx, callSiteArray[365].callGroovyObjectGetProperty(this));
        this.requestBody = object2 = callSiteArray[366].call((Object)mapper, ctx);
        return this;
    }

    @Override
    public RequestSpecification body(Object object, ObjectMapperType mapperType) {
        Object object2;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[367].callStatic(AssertParameter.class, object, "object");
        callSiteArray[368].callStatic(AssertParameter.class, (Object)mapperType, "Object mapper type");
        this.requestBody = object2 = callSiteArray[369].call((Object)ObjectMapping.class, ArrayUtil.createArray(object, callSiteArray[370].callGroovyObjectGetProperty(this), callSiteArray[371].callCurrent((GroovyObject)this, callSiteArray[372].callGroovyObjectGetProperty(this)), (Object)mapperType, callSiteArray[373].callCurrent(this), callSiteArray[374].call(callSiteArray[375].callCurrent(this))));
        return this;
    }

    @Override
    public RequestSpecification contentType(ContentType contentType) {
        boolean bl;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[376].callStatic(AssertParameter.class, (Object)contentType, ContentType.class);
        this.allowContentType = bl = true;
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[377].callCurrent(this, CONTENT_TYPE, (Object)contentType), RequestSpecification.class);
    }

    @Override
    public RequestSpecification contentType(String contentType) {
        boolean bl;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[378].callStatic(AssertParameter.class, contentType, "Content-Type header cannot be null");
        this.allowContentType = bl = true;
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[379].callCurrent(this, CONTENT_TYPE, contentType), RequestSpecification.class);
    }

    @Override
    public RequestSpecification noContentType() {
        boolean bl;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        this.allowContentType = bl = false;
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[380].callCurrent((GroovyObject)this, CONTENT_TYPE), RequestSpecification.class);
    }

    @Override
    public RequestSpecification accept(ContentType contentType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[381].callStatic(AssertParameter.class, (Object)contentType, "Accept header");
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[382].callCurrent((GroovyObject)this, callSiteArray[383].call((Object)contentType)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification accept(String mediaTypes) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[384].callStatic(AssertParameter.class, mediaTypes, "Accept header media range");
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[385].callCurrent(this, ACCEPT_HEADER_NAME, mediaTypes), RequestSpecification.class);
    }

    public RequestSpecification headers(Map headers) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[386].callStatic(AssertParameter.class, headers, "headers");
        Reference<List> headerList = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[0]));
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[387].call(this.requestHeaders))) {
            callSiteArray[388].call((Object)headerList.get(), callSiteArray[389].call(this.requestHeaders));
        }
        callSiteArray[390].call((Object)headers, new GeneratedClosure(this, this, headerList){
            private /* synthetic */ Reference headerList;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _headers_closure6.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.headerList = reference = headerList;
            }

            public Object doCall(Object it) {
                Reference<Object> it2 = new Reference<Object>(it);
                CallSite[] callSiteArray = _headers_closure6.$getCallSiteArray();
                if (callSiteArray[0].callGetProperty(it2.get()) instanceof List) {
                    return callSiteArray[1].call(callSiteArray[2].callGetProperty(it2.get()), new GeneratedClosure(this, this.getThisObject(), this.headerList, it2){
                        private /* synthetic */ Reference headerList;
                        private /* synthetic */ Reference it;
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;
                        private static /* synthetic */ SoftReference $callSiteArray;
                        {
                            Reference reference;
                            Reference reference2;
                            CallSite[] callSiteArray = _headers_closure6_closure49.$getCallSiteArray();
                            super(_outerInstance, _thisObject);
                            this.headerList = reference2 = headerList;
                            this.it = reference = it;
                        }

                        public Object doCall(Object val) {
                            CallSite[] callSiteArray = _headers_closure6_closure49.$getCallSiteArray();
                            return callSiteArray[0].call(this.headerList.get(), callSiteArray[1].callConstructor(Header.class, callSiteArray[2].callGetProperty(this.it.get()), callSiteArray[3].callCurrent((GroovyObject)this, val)));
                        }

                        public Object getHeaderList() {
                            CallSite[] callSiteArray = _headers_closure6_closure49.$getCallSiteArray();
                            return this.headerList.get();
                        }

                        public Object getIt() {
                            CallSite[] callSiteArray = _headers_closure6_closure49.$getCallSiteArray();
                            return this.it.get();
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _headers_closure6_closure49.class) {
                                return ScriptBytecodeAdapter.initMetaClass(this);
                            }
                            ClassInfo classInfo = $staticClassInfo;
                            if (classInfo == null) {
                                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                            }
                            return classInfo.getMetaClass();
                        }

                        public static /* synthetic */ void __$swapInit() {
                            CallSite[] callSiteArray = _headers_closure6_closure49.$getCallSiteArray();
                            $callSiteArray = null;
                        }

                        static {
                            _headers_closure6_closure49.__$swapInit();
                        }

                        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                            stringArray[0] = "leftShift";
                            stringArray[1] = "<$constructor$>";
                            stringArray[2] = "key";
                            stringArray[3] = "serializeIfNeeded";
                        }

                        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                            String[] stringArray = new String[4];
                            _headers_closure6_closure49.$createCallSiteArray_1(stringArray);
                            return new CallSiteArray(_headers_closure6_closure49.class, stringArray);
                        }

                        private static /* synthetic */ CallSite[] $getCallSiteArray() {
                            CallSiteArray callSiteArray;
                            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                callSiteArray = _headers_closure6_closure49.$createCallSiteArray();
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
                }
                return callSiteArray[3].call(this.headerList.get(), callSiteArray[4].callConstructor(Header.class, callSiteArray[5].callGetProperty(it2.get()), callSiteArray[6].callCurrent((GroovyObject)this, callSiteArray[7].callGetProperty(it2.get()))));
            }

            public Object getHeaderList() {
                CallSite[] callSiteArray = _headers_closure6.$getCallSiteArray();
                return this.headerList.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _headers_closure6.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _headers_closure6.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _headers_closure6.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _headers_closure6.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "value";
                stringArray[1] = "each";
                stringArray[2] = "value";
                stringArray[3] = "leftShift";
                stringArray[4] = "<$constructor$>";
                stringArray[5] = "key";
                stringArray[6] = "serializeIfNeeded";
                stringArray[7] = "value";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[8];
                _headers_closure6.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_headers_closure6.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _headers_closure6.$createCallSiteArray();
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
        Object object = callSiteArray[391].callCurrent((GroovyObject)this, headerList.get());
        headerList.set((List)object);
        Object object2 = callSiteArray[392].callConstructor(Headers.class, headerList.get());
        this.requestHeaders = (Headers)ScriptBytecodeAdapter.castToType(object2, Headers.class);
        return this;
    }

    @Override
    public RequestSpecification headers(Headers headers) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[393].callStatic(AssertParameter.class, headers, "headers");
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[394].call(headers))) {
            Object object;
            Object headerList = ScriptBytecodeAdapter.createList(new Object[0]);
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[395].call(this.requestHeaders))) {
                callSiteArray[396].call(headerList, callSiteArray[397].call(this.requestHeaders));
            }
            callSiteArray[398].call(headerList, callSiteArray[399].call(callSiteArray[400].callGetProperty(headers)));
            headerList = object = callSiteArray[401].callCurrent((GroovyObject)this, headerList);
            Object object2 = callSiteArray[402].callConstructor(Headers.class, headerList);
            this.requestHeaders = (Headers)ScriptBytecodeAdapter.castToType(object2, Headers.class);
        }
        return this;
    }

    private List removeMergedHeadersIfNeeded(List headerList) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object headers = callSiteArray[403].call(headerList, ScriptBytecodeAdapter.createList(new Object[0]), new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _removeMergedHeadersIfNeeded_closure7.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object acc, Object header) {
                CallSite[] callSiteArray = _removeMergedHeadersIfNeeded_closure7.$getCallSiteArray();
                Object headerConfig = callSiteArray[0].call(callSiteArray[1].callCurrent(this));
                Reference<String> headerName = new Reference<String>((String)ScriptBytecodeAdapter.castToType(callSiteArray[2].call(header), String.class));
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[3].call(headerConfig, headerName.get()))) {
                    Object object;
                    acc = object = callSiteArray[4].call(acc, new GeneratedClosure(this, this.getThisObject(), headerName){
                        private /* synthetic */ Reference headerName;
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;
                        private static /* synthetic */ SoftReference $callSiteArray;
                        {
                            Reference reference;
                            CallSite[] callSiteArray = _removeMergedHeadersIfNeeded_closure7_closure50.$getCallSiteArray();
                            super(_outerInstance, _thisObject);
                            this.headerName = reference = headerName;
                        }

                        public Object doCall(Object it) {
                            CallSite[] callSiteArray = _removeMergedHeadersIfNeeded_closure7_closure50.$getCallSiteArray();
                            return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(this.headerName.get(), callSiteArray[1].call(it)));
                        }

                        public String getHeaderName() {
                            CallSite[] callSiteArray = _removeMergedHeadersIfNeeded_closure7_closure50.$getCallSiteArray();
                            return (String)ScriptBytecodeAdapter.castToType(this.headerName.get(), String.class);
                        }

                        public Object doCall() {
                            CallSite[] callSiteArray = _removeMergedHeadersIfNeeded_closure7_closure50.$getCallSiteArray();
                            return this.doCall(null);
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _removeMergedHeadersIfNeeded_closure7_closure50.class) {
                                return ScriptBytecodeAdapter.initMetaClass(this);
                            }
                            ClassInfo classInfo = $staticClassInfo;
                            if (classInfo == null) {
                                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                            }
                            return classInfo.getMetaClass();
                        }

                        public static /* synthetic */ void __$swapInit() {
                            CallSite[] callSiteArray = _removeMergedHeadersIfNeeded_closure7_closure50.$getCallSiteArray();
                            $callSiteArray = null;
                        }

                        static {
                            _removeMergedHeadersIfNeeded_closure7_closure50.__$swapInit();
                        }

                        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                            stringArray[0] = "equalsIgnoreCase";
                            stringArray[1] = "getName";
                        }

                        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                            String[] stringArray = new String[2];
                            _removeMergedHeadersIfNeeded_closure7_closure50.$createCallSiteArray_1(stringArray);
                            return new CallSiteArray(_removeMergedHeadersIfNeeded_closure7_closure50.class, stringArray);
                        }

                        private static /* synthetic */ CallSite[] $getCallSiteArray() {
                            CallSiteArray callSiteArray;
                            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                callSiteArray = _removeMergedHeadersIfNeeded_closure7_closure50.$createCallSiteArray();
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
                }
                callSiteArray[5].call(acc, header);
                return acc;
            }

            public Object call(Object acc, Object header) {
                CallSite[] callSiteArray = _removeMergedHeadersIfNeeded_closure7.$getCallSiteArray();
                return callSiteArray[6].callCurrent(this, acc, header);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _removeMergedHeadersIfNeeded_closure7.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _removeMergedHeadersIfNeeded_closure7.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _removeMergedHeadersIfNeeded_closure7.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "getHeaderConfig";
                stringArray[1] = "restAssuredConfig";
                stringArray[2] = "getName";
                stringArray[3] = "shouldOverwriteHeaderWithName";
                stringArray[4] = "findAll";
                stringArray[5] = "add";
                stringArray[6] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[7];
                _removeMergedHeadersIfNeeded_closure7.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_removeMergedHeadersIfNeeded_closure7.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _removeMergedHeadersIfNeeded_closure7.$createCallSiteArray();
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
        return (List)ScriptBytecodeAdapter.castToType(headers, List.class);
    }

    @Override
    public RequestSpecification header(String headerName, Object headerValue, Object ... additionalHeaderValues) {
        Reference<String> headerName2 = new Reference<String>(headerName);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[404].callStatic(AssertParameter.class, headerName2.get(), "Header name");
        callSiteArray[405].callStatic(AssertParameter.class, headerValue, "Header value");
        Reference<List> headerList = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[406].callConstructor(Header.class, headerName2.get(), callSiteArray[407].callCurrent((GroovyObject)this, headerValue))}));
        callSiteArray[408].callSafe((Object)additionalHeaderValues, new GeneratedClosure(this, this, headerList, headerName2){
            private /* synthetic */ Reference headerList;
            private /* synthetic */ Reference headerName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _header_closure8.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.headerList = reference2 = headerList;
                this.headerName = reference = headerName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _header_closure8.$getCallSiteArray();
                return callSiteArray[0].call(this.headerList.get(), callSiteArray[1].callConstructor(Header.class, this.headerName.get(), callSiteArray[2].callCurrent((GroovyObject)this, it)));
            }

            public Object getHeaderList() {
                CallSite[] callSiteArray = _header_closure8.$getCallSiteArray();
                return this.headerList.get();
            }

            public String getHeaderName() {
                CallSite[] callSiteArray = _header_closure8.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.headerName.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _header_closure8.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _header_closure8.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _header_closure8.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _header_closure8.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "leftShift";
                stringArray[1] = "<$constructor$>";
                stringArray[2] = "serializeIfNeeded";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _header_closure8.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_header_closure8.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _header_closure8.$createCallSiteArray();
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
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[409].callCurrent((GroovyObject)this, callSiteArray[410].callConstructor(Headers.class, headerList.get())), RequestSpecification.class);
    }

    @Override
    public RequestSpecification header(Header header) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[411].callStatic(AssertParameter.class, header, "Header");
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[412].callCurrent((GroovyObject)this, callSiteArray[413].callConstructor(Headers.class, callSiteArray[414].callStatic(Arrays.class, header))), RequestSpecification.class);
    }

    @Override
    public RequestSpecification headers(String firstHeaderName, Object firstHeaderValue, Object ... headerNameValuePairs) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[415].callCurrent((GroovyObject)this, callSiteArray[416].call(MapCreator.class, callSiteArray[417].callGetProperty(MapCreator.CollisionStrategy.class), firstHeaderName, firstHeaderValue, headerNameValuePairs)), RequestSpecification.class);
    }

    @Override
    public RequestSpecification cookies(String firstCookieName, Object firstCookieValue, Object ... cookieNameValuePairs) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[418].callCurrent((GroovyObject)this, callSiteArray[419].call(MapCreator.class, callSiteArray[420].callGetProperty(MapCreator.CollisionStrategy.class), firstCookieName, firstCookieValue, cookieNameValuePairs)), RequestSpecification.class);
    }

    public RequestSpecification cookies(Map cookies) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[421].callStatic(AssertParameter.class, cookies, "cookies");
        Reference<List> cookieList = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[0]));
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[422].call(this.cookies))) {
            callSiteArray[423].call((Object)cookieList.get(), callSiteArray[424].call(this.cookies));
        }
        callSiteArray[425].call((Object)cookies, new GeneratedClosure(this, this, cookieList){
            private /* synthetic */ Reference cookieList;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _cookies_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.cookieList = reference = cookieList;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _cookies_closure9.$getCallSiteArray();
                return callSiteArray[0].call(this.cookieList.get(), callSiteArray[1].call(callSiteArray[2].callConstructor(Cookie.Builder.class, callSiteArray[3].callGetProperty(it), callSiteArray[4].callGetProperty(it))));
            }

            public Object getCookieList() {
                CallSite[] callSiteArray = _cookies_closure9.$getCallSiteArray();
                return this.cookieList.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _cookies_closure9.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _cookies_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _cookies_closure9.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _cookies_closure9.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "leftShift";
                stringArray[1] = "build";
                stringArray[2] = "<$constructor$>";
                stringArray[3] = "key";
                stringArray[4] = "value";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[5];
                _cookies_closure9.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_cookies_closure9.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _cookies_closure9.$createCallSiteArray();
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
        Object object = callSiteArray[426].callConstructor(Cookies.class, cookieList.get());
        this.cookies = (Cookies)ScriptBytecodeAdapter.castToType(object, Cookies.class);
        return this;
    }

    @Override
    public RequestSpecification cookies(Cookies cookies) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[427].callStatic(AssertParameter.class, cookies, "cookies");
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[428].call(cookies))) {
            List cookieList = ScriptBytecodeAdapter.createList(new Object[0]);
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[429].call(this.cookies))) {
                callSiteArray[430].call((Object)cookieList, callSiteArray[431].call(this.cookies));
            }
            callSiteArray[432].call((Object)cookieList, callSiteArray[433].call(callSiteArray[434].callGetProperty(cookies)));
            Object object = callSiteArray[435].callConstructor(Cookies.class, cookieList);
            this.cookies = (Cookies)ScriptBytecodeAdapter.castToType(object, Cookies.class);
        }
        return this;
    }

    @Override
    public RequestSpecification cookie(String cookieName, Object value, Object ... additionalValues) {
        Reference<String> cookieName2 = new Reference<String>(cookieName);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[436].callStatic(AssertParameter.class, cookieName2.get(), "Cookie name");
        Reference<List> cookieList = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[437].call(callSiteArray[438].callConstructor(Cookie.Builder.class, cookieName2.get(), callSiteArray[439].callCurrent((GroovyObject)this, value)))}));
        callSiteArray[440].callSafe((Object)additionalValues, new GeneratedClosure(this, this, cookieList, cookieName2){
            private /* synthetic */ Reference cookieList;
            private /* synthetic */ Reference cookieName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.cookieList = reference2 = cookieList;
                this.cookieName = reference = cookieName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                return callSiteArray[0].call(this.cookieList.get(), callSiteArray[1].call(callSiteArray[2].callConstructor(Cookie.Builder.class, this.cookieName.get(), callSiteArray[3].callCurrent((GroovyObject)this, it))));
            }

            public Object getCookieList() {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                return this.cookieList.get();
            }

            public String getCookieName() {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.cookieName.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _cookie_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _cookie_closure10.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "leftShift";
                stringArray[1] = "build";
                stringArray[2] = "<$constructor$>";
                stringArray[3] = "serializeIfNeeded";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[4];
                _cookie_closure10.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_cookie_closure10.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _cookie_closure10.$createCallSiteArray();
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
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[441].callCurrent((GroovyObject)this, callSiteArray[442].callConstructor(Cookies.class, cookieList.get())), RequestSpecification.class);
    }

    @Override
    public RequestSpecification cookie(Cookie cookie) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[443].callStatic(AssertParameter.class, cookie, "Cookie");
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[444].callCurrent((GroovyObject)this, callSiteArray[445].callConstructor(Cookies.class, callSiteArray[446].callStatic(Arrays.class, cookie))), RequestSpecification.class);
    }

    @Override
    public RequestSpecification cookie(String cookieName) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[447].callCurrent(this, cookieName, null), RequestSpecification.class);
    }

    @Override
    public RedirectSpecification redirects() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (RedirectSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[448].callConstructor(RedirectSpecificationImpl.class, this, this.httpClientParams), RedirectSpecification.class);
    }

    @Override
    public RequestSpecification spec(RequestSpecification requestSpecificationToMerge) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[449].call(SpecificationMerger.class, this, requestSpecificationToMerge);
        return this;
    }

    public RequestSpecification specification(RequestSpecification requestSpecificationToMerge) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[450].callCurrent((GroovyObject)this, requestSpecificationToMerge), RequestSpecification.class);
    }

    @Override
    public RequestSpecification sessionId(String sessionIdValue) {
        Object object;
        Object object2;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object sessionIdName = null;
        sessionIdName = !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? (object2 = ScriptBytecodeAdapter.compareEqual(callSiteArray[451].callGroovyObjectGetProperty(this), null) ? callSiteArray[452].callGetProperty(SessionConfig.class) : callSiteArray[453].call(callSiteArray[454].call(callSiteArray[455].callGroovyObjectGetProperty(this)))) : (object = ScriptBytecodeAdapter.compareEqual(callSiteArray[456].callGroovyObjectGetProperty(this), null) ? callSiteArray[457].callGetProperty(SessionConfig.class) : callSiteArray[458].call(callSiteArray[459].call(callSiteArray[460].callGroovyObjectGetProperty(this))));
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[461].callCurrent(this, sessionIdName, sessionIdValue), RequestSpecification.class);
    }

    @Override
    public RequestSpecification sessionId(String sessionIdName, String sessionIdValue) {
        Reference<String> sessionIdName2 = new Reference<String>(sessionIdName);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[462].callStatic(AssertParameter.class, sessionIdName2.get(), "Session id name");
        callSiteArray[463].callStatic(AssertParameter.class, sessionIdValue, "Session id value");
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[464].call((Object)this.cookies, sessionIdName2.get()))) {
            Object allOtherCookies = callSiteArray[465].call((Object)this.cookies, new GeneratedClosure(this, this, sessionIdName2){
                private /* synthetic */ Reference sessionIdName;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    CallSite[] callSiteArray = _sessionId_closure11.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.sessionIdName = reference = sessionIdName;
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _sessionId_closure11.$getCallSiteArray();
                    return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(callSiteArray[1].call(it), this.sessionIdName.get()));
                }

                public String getSessionIdName() {
                    CallSite[] callSiteArray = _sessionId_closure11.$getCallSiteArray();
                    return (String)ScriptBytecodeAdapter.castToType(this.sessionIdName.get(), String.class);
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _sessionId_closure11.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _sessionId_closure11.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _sessionId_closure11.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _sessionId_closure11.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "equalsIgnoreCase";
                    stringArray[1] = "getName";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[2];
                    _sessionId_closure11.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_sessionId_closure11.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _sessionId_closure11.$createCallSiteArray();
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
            callSiteArray[466].call(allOtherCookies, callSiteArray[467].call(callSiteArray[468].callConstructor(Cookie.Builder.class, sessionIdName2.get(), sessionIdValue)));
            Object object = callSiteArray[469].callConstructor(Cookies.class, allOtherCookies);
            this.cookies = (Cookies)ScriptBytecodeAdapter.castToType(object, Cookies.class);
        } else {
            callSiteArray[470].callCurrent(this, sessionIdName2.get(), sessionIdValue);
        }
        return this;
    }

    @Override
    public RequestSpecification multiPart(MultiPartSpecification multiPartSpec) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[471].callStatic(AssertParameter.class, multiPartSpec, "Multi-part specification");
        Object mimeType = callSiteArray[472].callGetProperty(multiPartSpec);
        Object content = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (callSiteArray[473].callGetProperty(multiPartSpec) instanceof File || callSiteArray[474].callGetProperty(multiPartSpec) instanceof InputStream || callSiteArray[475].callGetProperty(multiPartSpec) instanceof byte[]) {
                Object object;
                content = object = callSiteArray[476].callGetProperty(multiPartSpec);
            } else {
                Object object;
                if (ScriptBytecodeAdapter.compareEqual(mimeType, null)) {
                    Object object2;
                    mimeType = object2 = DefaultTypeTransformation.booleanUnbox(callSiteArray[477].call(callSiteArray[478].callGetProperty(ContentType.class), callSiteArray[479].callGroovyObjectGetProperty(this))) ? callSiteArray[480].call(callSiteArray[481].callGetProperty(ContentType.class)) : callSiteArray[482].callGroovyObjectGetProperty(this);
                }
                content = object = callSiteArray[483].callCurrent(this, callSiteArray[484].callGetProperty(multiPartSpec), mimeType);
            }
        } else if (callSiteArray[485].callGetProperty(multiPartSpec) instanceof File || callSiteArray[486].callGetProperty(multiPartSpec) instanceof InputStream || callSiteArray[487].callGetProperty(multiPartSpec) instanceof byte[]) {
            Object object;
            content = object = callSiteArray[488].callGetProperty(multiPartSpec);
        } else {
            Object object;
            if (ScriptBytecodeAdapter.compareEqual(mimeType, null)) {
                Object object3;
                mimeType = object3 = DefaultTypeTransformation.booleanUnbox(callSiteArray[489].call(callSiteArray[490].callGetProperty(ContentType.class), callSiteArray[491].callGroovyObjectGetProperty(this))) ? callSiteArray[492].call(callSiteArray[493].callGetProperty(ContentType.class)) : callSiteArray[494].callGroovyObjectGetProperty(this);
            }
            content = object = callSiteArray[495].callCurrent(this, callSiteArray[496].callGetProperty(multiPartSpec), mimeType);
        }
        String controlName = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (multiPartSpec instanceof MultiPartSpecificationImpl && !DefaultTypeTransformation.booleanUnbox(callSiteArray[497].call(multiPartSpec))) {
                Object object = callSiteArray[498].call(callSiteArray[499].call(callSiteArray[500].callCurrent(this)));
                controlName = (String)ScriptBytecodeAdapter.castToType(object, String.class);
            } else {
                Object object = callSiteArray[501].callGetProperty(multiPartSpec);
                controlName = (String)ScriptBytecodeAdapter.castToType(object, String.class);
            }
        } else if (multiPartSpec instanceof MultiPartSpecificationImpl && !DefaultTypeTransformation.booleanUnbox(callSiteArray[502].call(multiPartSpec))) {
            Object object = callSiteArray[503].call(callSiteArray[504].call(callSiteArray[505].callCurrent(this)));
            controlName = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        } else {
            Object object = callSiteArray[506].callGetProperty(multiPartSpec);
            controlName = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        }
        String fileName = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (multiPartSpec instanceof MultiPartSpecificationImpl && !DefaultTypeTransformation.booleanUnbox(callSiteArray[507].call(multiPartSpec))) {
                Object object = callSiteArray[508].call(callSiteArray[509].call(callSiteArray[510].callCurrent(this)));
                fileName = (String)ScriptBytecodeAdapter.castToType(object, String.class);
            } else {
                Object object = callSiteArray[511].callGetProperty(multiPartSpec);
                fileName = (String)ScriptBytecodeAdapter.castToType(object, String.class);
            }
        } else if (multiPartSpec instanceof MultiPartSpecificationImpl && !DefaultTypeTransformation.booleanUnbox(callSiteArray[512].call(multiPartSpec))) {
            Object object = callSiteArray[513].call(callSiteArray[514].call(callSiteArray[515].callCurrent(this)));
            fileName = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        } else {
            Object object = callSiteArray[516].callGetProperty(multiPartSpec);
            fileName = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        }
        Object headers = callSiteArray[517].callGetProperty(multiPartSpec);
        callSiteArray[518].call(this.multiParts, callSiteArray[519].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", controlName, "content", content, "fileName", fileName, CHARSET, callSiteArray[520].callGetProperty(multiPartSpec), "mimeType", mimeType, "headers", headers})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String controlName, File file) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[521].call(this.multiParts, callSiteArray[522].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", controlName, "content", file, "fileName", callSiteArray[523].call(file)})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(File file) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[524].call(this.multiParts, callSiteArray[525].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", callSiteArray[526].call(callSiteArray[527].call(callSiteArray[528].callCurrent(this))), "content", file, "fileName", callSiteArray[529].call(file)})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String controlName, File file, String mimeType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[530].call(this.multiParts, callSiteArray[531].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", controlName, "content", file, "mimeType", mimeType, "fileName", callSiteArray[532].call(file)})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String controlName, Object object) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object mimeType = DefaultTypeTransformation.booleanUnbox(callSiteArray[533].call(callSiteArray[534].callGetProperty(ContentType.class), callSiteArray[535].callGroovyObjectGetProperty(this))) ? callSiteArray[536].call(callSiteArray[537].callGetProperty(ContentType.class)) : callSiteArray[538].callGroovyObjectGetProperty(this);
        return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[539].callCurrent(this, controlName, object, mimeType), RequestSpecification.class);
    }

    @Override
    public RequestSpecification multiPart(String controlName, Object object, String mimeType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object possiblySerializedObject = callSiteArray[540].callCurrent(this, object, mimeType);
        callSiteArray[541].call(this.multiParts, callSiteArray[542].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", controlName, "content", possiblySerializedObject, "mimeType", mimeType, "fileName", callSiteArray[543].call(callSiteArray[544].call(callSiteArray[545].callCurrent(this)))})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String controlName, String filename, Object object, String mimeType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object possiblySerializedObject = callSiteArray[546].callCurrent(this, object, mimeType);
        callSiteArray[547].call(this.multiParts, callSiteArray[548].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", controlName, "content", possiblySerializedObject, "mimeType", mimeType, "fileName", filename})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String name, String fileName, byte ... bytes) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[549].call(this.multiParts, callSiteArray[550].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", name, "content", bytes, "fileName", fileName})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String name, String fileName, byte[] bytes, String mimeType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[551].call(this.multiParts, callSiteArray[552].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", name, "content", bytes, "mimeType", mimeType, "fileName", fileName})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String name, String fileName, InputStream stream) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[553].call(this.multiParts, callSiteArray[554].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", name, "content", stream, "fileName", fileName})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String name, String fileName, InputStream stream, String mimeType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[555].call(this.multiParts, callSiteArray[556].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", name, "content", stream, "mimeType", mimeType, "fileName", fileName})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String name, String contentBody) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[557].call(this.multiParts, callSiteArray[558].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", name, "content", contentBody, "fileName", callSiteArray[559].call(callSiteArray[560].call(callSiteArray[561].callCurrent(this)))})));
        return this;
    }

    public RequestSpecification multiPart(String name, NoParameterValue contentBody) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[562].call(this.multiParts, callSiteArray[563].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", name, "content", contentBody, "fileName", callSiteArray[564].call(callSiteArray[565].call(callSiteArray[566].callCurrent(this)))})));
        return this;
    }

    @Override
    public RequestSpecification multiPart(String name, String contentBody, String mimeType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[567].call(this.multiParts, callSiteArray[568].callConstructor(MultiPartInternal.class, ScriptBytecodeAdapter.createMap(new Object[]{"controlName", name, "content", contentBody, "mimeType", mimeType, "fileName", callSiteArray[569].call(callSiteArray[570].call(callSiteArray[571].callCurrent(this)))})));
        return this;
    }

    public Object newFilterContext(Object assertionClosure, Object filters, Object properties) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[572].callSafe((Object)this.path, "?"))) {
            throw (Throwable)callSiteArray[573].callConstructor(IllegalArgumentException.class, "Request URI cannot end with ?");
        }
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[574].call(callSiteArray[575].callGroovyObjectGetProperty(this), ACCEPT_HEADER_NAME))) {
            callSiteArray[576].callCurrent(this, ACCEPT_HEADER_NAME, callSiteArray[577].call(callSiteArray[578].callGetProperty(ContentType.class)));
        }
        Object tempContentType = callSiteArray[579].callCurrent((GroovyObject)this, this.method);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(tempContentType, null)) {
                callSiteArray[580].callCurrent(this, CONTENT_TYPE, tempContentType);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(tempContentType, null)) {
            callSiteArray[581].callCurrent(this, CONTENT_TYPE, tempContentType);
        }
        Object unnamedPathParamValues = callSiteArray[582].call(callSiteArray[583].call(this.unnamedPathParamsTuples, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _newFilterContext_closure12.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _newFilterContext_closure12.$getCallSiteArray();
                if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                    return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[0].callGetProperty(it), null);
                }
                return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[1].callGetProperty(it), null);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _newFilterContext_closure12.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _newFilterContext_closure12.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _newFilterContext_closure12.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _newFilterContext_closure12.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "v2";
                stringArray[1] = "v2";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _newFilterContext_closure12.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_newFilterContext_closure12.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _newFilterContext_closure12.$createCallSiteArray();
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
        }), new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _newFilterContext_closure13.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _newFilterContext_closure13.$getCallSiteArray();
                return callSiteArray[0].callGetProperty(it);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _newFilterContext_closure13.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _newFilterContext_closure13.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _newFilterContext_closure13.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _newFilterContext_closure13.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "v2";
                return new CallSiteArray(_newFilterContext_closure13.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _newFilterContext_closure13.$createCallSiteArray();
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
        Object uri = callSiteArray[584].callCurrent(this, this.path, true, unnamedPathParamValues);
        String requestUriForLogging = (String)ScriptBytecodeAdapter.castToType(callSiteArray[585].callCurrent(this, uri, this.method), String.class);
        return callSiteArray[586].callConstructor((Object)FilterContextImpl.class, ArrayUtil.createArray(requestUriForLogging, callSiteArray[587].callCurrent(this), callSiteArray[588].callStatic(RequestSpecificationImpl.class, uri), uri, this.path, callSiteArray[589].call(unnamedPathParamValues), this.method, assertionClosure, filters, properties));
    }

    private String generateRequestUriForLogging(Object uri, Object method) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object targetUri = null;
        Reference<Map> allQueryParams = new Reference<Map>(ScriptBytecodeAdapter.createMap(new Object[0]));
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[590].call(uri, "?"))) {
            Object object;
            Object object2;
            Object object3;
            Object uriToUse = null;
            uriToUse = DefaultTypeTransformation.booleanUnbox(callSiteArray[591].callStatic(PathSupport.class, uri)) ? (object3 = uri) : (object2 = callSiteArray[592].callCurrent((GroovyObject)this, uri));
            targetUri = object = callSiteArray[593].callStatic(StringUtils.class, uriToUse, "?");
            Object queryParamsDefinedInPath = callSiteArray[594].callStatic(StringUtils.class, uri, "?");
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[595].callStatic(StringUtils.class, queryParamsDefinedInPath))) {
                Object splittedQueryParams = callSiteArray[596].callStatic(StringUtils.class, queryParamsDefinedInPath, "&");
                callSiteArray[597].call(splittedQueryParams, new GeneratedClosure(this, this, allQueryParams){
                    private /* synthetic */ Reference allQueryParams;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _generateRequestUriForLogging_closure14.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.allQueryParams = reference = allQueryParams;
                    }

                    public Object doCall(Object queryNameWithPotentialValue) {
                        CallSite[] callSiteArray = _generateRequestUriForLogging_closure14.$getCallSiteArray();
                        Object[] splitted = (String[])ScriptBytecodeAdapter.castToType(callSiteArray[0].callStatic(StringUtils.class, queryNameWithPotentialValue, "=", 2), String[].class);
                        Boolean queryParamHasValueDefined = null;
                        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                            boolean bl = ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[1].call(splitted), 1) || DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call(queryNameWithPotentialValue, "="));
                            queryParamHasValueDefined = bl;
                        } else {
                            boolean bl = ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[3].call(splitted), 1) || DefaultTypeTransformation.booleanUnbox(callSiteArray[4].call(queryNameWithPotentialValue, "="));
                            queryParamHasValueDefined = bl;
                        }
                        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                            if (DefaultTypeTransformation.booleanUnbox(queryParamHasValueDefined)) {
                                String value = ScriptBytecodeAdapter.compareEqual(callSiteArray[5].call(splitted), 1) ? "" : (String)ScriptBytecodeAdapter.castToType(callSiteArray[6].call((Object)splitted, 1), String.class);
                                return callSiteArray[7].call(this.allQueryParams.get(), callSiteArray[8].call((Object)splitted, 0), value);
                            }
                            return callSiteArray[9].call(this.allQueryParams.get(), callSiteArray[10].call((Object)splitted, 0), callSiteArray[11].callConstructor(NoParameterValue.class));
                        }
                        if (DefaultTypeTransformation.booleanUnbox(queryParamHasValueDefined)) {
                            String value = ScriptBytecodeAdapter.compareEqual(callSiteArray[12].call(splitted), 1) ? "" : (String)ScriptBytecodeAdapter.castToType(BytecodeInterface8.objectArrayGet(splitted, 1), String.class);
                            return callSiteArray[13].call(this.allQueryParams.get(), BytecodeInterface8.objectArrayGet(splitted, 0), value);
                        }
                        return callSiteArray[14].call(this.allQueryParams.get(), BytecodeInterface8.objectArrayGet(splitted, 0), callSiteArray[15].callConstructor(NoParameterValue.class));
                    }

                    public Object getAllQueryParams() {
                        CallSite[] callSiteArray = _generateRequestUriForLogging_closure14.$getCallSiteArray();
                        return this.allQueryParams.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _generateRequestUriForLogging_closure14.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _generateRequestUriForLogging_closure14.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _generateRequestUriForLogging_closure14.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "split";
                        stringArray[1] = "size";
                        stringArray[2] = "contains";
                        stringArray[3] = "size";
                        stringArray[4] = "contains";
                        stringArray[5] = "size";
                        stringArray[6] = "getAt";
                        stringArray[7] = "put";
                        stringArray[8] = "getAt";
                        stringArray[9] = "put";
                        stringArray[10] = "getAt";
                        stringArray[11] = "<$constructor$>";
                        stringArray[12] = "size";
                        stringArray[13] = "put";
                        stringArray[14] = "put";
                        stringArray[15] = "<$constructor$>";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[16];
                        _generateRequestUriForLogging_closure14.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_generateRequestUriForLogging_closure14.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _generateRequestUriForLogging_closure14.$createCallSiteArray();
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
            }
        } else {
            Object object;
            targetUri = object = uri;
        }
        Object actualUri = callSiteArray[598].call(URIBuilder.class, callSiteArray[599].callCurrent((GroovyObject)this, targetUri));
        Object uriBuilder = callSiteArray[600].callConstructor(URIBuilder.class, actualUri, this.urlEncodingEnabled, callSiteArray[601].callCurrent(this));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[602].call(callSiteArray[603].call(callSiteArray[604].callGetProperty(Method.class)), method)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[605].callSafe(this.requestParameters))) {
                callSiteArray[606].call((Object)allQueryParams.get(), this.requestParameters);
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[607].call(callSiteArray[608].call(callSiteArray[609].callGetProperty(Method.class)), method)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[610].callSafe(this.requestParameters))) {
            callSiteArray[611].call((Object)allQueryParams.get(), this.requestParameters);
        }
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[612].callSafe(this.queryParameters))) {
            callSiteArray[613].call((Object)allQueryParams.get(), this.queryParameters);
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[614].call(callSiteArray[615].call(callSiteArray[616].callGetProperty(Method.class)), method)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[617].callSafe(this.formParameters))) {
                callSiteArray[618].call((Object)allQueryParams.get(), this.formParameters);
            }
        } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[619].call(callSiteArray[620].call(callSiteArray[621].callGetProperty(Method.class)), method)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[622].callSafe(this.formParameters))) {
            callSiteArray[623].call((Object)allQueryParams.get(), this.formParameters);
        }
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[624].call(allQueryParams.get()))) {
            callSiteArray[625].call(uriBuilder, allQueryParams.get());
        }
        Object requestUriForLogging = callSiteArray[626].call(uriBuilder);
        return (String)ScriptBytecodeAdapter.castToType(requestUriForLogging, String.class);
    }

    private Response sendRequest(Object path, Object assertionClosure, FilterableRequestSpecification requestSpecification, Map filterContextProperties) {
        Object object;
        Reference<Object> assertionClosure2 = new Reference<Object>(assertionClosure);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[627].callStatic(AssertParameter.class, path, "Path");
        path = object = callSiteArray[628].callCurrent((GroovyObject)this, path);
        Object method = callSiteArray[629].call(requestSpecification);
        Object targetUri = callSiteArray[630].callCurrent((GroovyObject)this, path);
        Object targetPath = callSiteArray[631].callCurrent((GroovyObject)this, path);
        callSiteArray[632].callCurrent(this);
        if (Boolean.valueOf(!DefaultTypeTransformation.booleanUnbox(callSiteArray[633].call(requestSpecification))) instanceof AbstractHttpClient) {
            throw (Throwable)callSiteArray[634].callConstructor(IllegalStateException.class, callSiteArray[635].callStatic(String.class, "Unfortunately Rest Assured only supports Http Client instances of type %s.", callSiteArray[636].call(AbstractHttpClient.class)));
        }
        Object http = callSiteArray[637].callConstructor((Object)RequestSpecificationImpl.$get$$class$io$restassured$internal$RestAssuredHttpBuilder(), ArrayUtil.createArray(this.responseSpecification, this.requestHeaders, this.queryParameters, targetUri, assertionClosure2.get(), this.urlEncodingEnabled, callSiteArray[638].callGroovyObjectGetProperty(this), ScriptBytecodeAdapter.createPojoWrapper((AbstractHttpClient)ScriptBytecodeAdapter.asType(callSiteArray[639].call(requestSpecification), AbstractHttpClient.class), AbstractHttpClient.class), this.allowContentType, callSiteArray[640].callGetProperty(callSiteArray[641].callGetProperty(this.responseSpecification))));
        callSiteArray[642].callCurrent((GroovyObject)this, http);
        callSiteArray[643].callCurrent((GroovyObject)this, http);
        callSiteArray[644].callCurrent((GroovyObject)this, http);
        callSiteArray[645].callCurrent((GroovyObject)this, http);
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[646].call(this.cookies))) {
            callSiteArray[647].call(callSiteArray[648].call(http), ScriptBytecodeAdapter.createMap(new Object[]{"Cookie", callSiteArray[649].call(callSiteArray[650].call((Object)this.cookies, new GeneratedClosure(this, this){
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    CallSite[] callSiteArray = _sendRequest_closure15.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _sendRequest_closure15.$getCallSiteArray();
                    return callSiteArray[0].call(callSiteArray[1].call(callSiteArray[2].callGetProperty(it), "="), callSiteArray[3].callGetProperty(it));
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _sendRequest_closure15.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _sendRequest_closure15.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _sendRequest_closure15.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _sendRequest_closure15.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "plus";
                    stringArray[1] = "plus";
                    stringArray[2] = "name";
                    stringArray[3] = "value";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[4];
                    _sendRequest_closure15.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_sendRequest_closure15.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _sendRequest_closure15.$createCallSiteArray();
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
            }), "; ")}));
        }
        Object restAssuredResponse = callSiteArray[651].callConstructor(RestAssuredResponseImpl.class, ScriptBytecodeAdapter.createMap(new Object[]{"logRepository", this.logRepository}));
        Object object2 = callSiteArray[652].callGroovyObjectGetProperty(this);
        RestAssuredConfig restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object2, RestAssuredConfig.class);
        RestAssuredConfig cfg = DefaultTypeTransformation.booleanUnbox(object2) ? restAssuredConfig : (RestAssuredConfig)ScriptBytecodeAdapter.castToType(callSiteArray[653].callConstructor(RestAssuredConfig.class), RestAssuredConfig.class);
        callSiteArray[654].call(restAssuredResponse, callSiteArray[655].call(callSiteArray[656].call(cfg)));
        callSiteArray[657].call(restAssuredResponse, callSiteArray[658].call(cfg));
        callSiteArray[659].call(restAssuredResponse, callSiteArray[660].callGetProperty(callSiteArray[661].callGetProperty(http)));
        callSiteArray[662].call(restAssuredResponse, cfg);
        callSiteArray[663].call(restAssuredResponse, filterContextProperties);
        Object object3 = restAssuredResponse;
        ScriptBytecodeAdapter.setProperty(object3, null, this.responseSpecification, "restAssuredResponse");
        Object acceptContentType = callSiteArray[664].call(assertionClosure2.get());
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[665].callCurrent(this, http, cfg))) {
            Object sslConfig = callSiteArray[666].call(cfg);
            callSiteArray[667].call(callSiteArray[668].callConstructor(CertAuthScheme.class, ScriptBytecodeAdapter.createMap(new Object[]{"pathToKeyStore", callSiteArray[669].call(sslConfig), "keyStorePassword", callSiteArray[670].call(sslConfig), "keystoreType", callSiteArray[671].call(sslConfig), "keyStore", callSiteArray[672].call(sslConfig), "pathToTrustStore", callSiteArray[673].call(sslConfig), "trustStorePassword", callSiteArray[674].call(sslConfig), "trustStoreType", callSiteArray[675].call(sslConfig), "trustStore", callSiteArray[676].call(sslConfig), "port", callSiteArray[677].call(sslConfig), "sslSocketFactory", callSiteArray[678].call(sslConfig), "x509HostnameVerifier", callSiteArray[679].call(sslConfig)})), http);
        }
        callSiteArray[680].call((Object)this.authenticationScheme, http);
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[681].callCurrent((GroovyObject)this, method))) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[682].callCurrent(this)) && ScriptBytecodeAdapter.compareNotEqual(this.requestBody, null)) {
                throw (Throwable)callSiteArray[683].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{method}, new String[]{"You can either send form parameters OR body content in ", ", not both!"}));
            }
            Object bodyContent = callSiteArray[684].callCurrent((GroovyObject)this, callSiteArray[685].callCurrent((GroovyObject)this, method));
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[686].call(callSiteArray[687].call(callSiteArray[688].callGetProperty(Method.class)), method))) {
                callSiteArray[689].call(http, ScriptBytecodeAdapter.createMap(new Object[]{"path", targetPath, "body", bodyContent, "allowContentType", this.allowContentType, "requestContentType", callSiteArray[690].call((Object)this.requestHeaders, CONTENT_TYPE), "contentType", acceptContentType}), new GeneratedClosure(this, this, assertionClosure2){
                    private /* synthetic */ Reference assertionClosure;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _sendRequest_closure16.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.assertionClosure = reference = assertionClosure;
                    }

                    public Object doCall(Object response, Object content) {
                        CallSite[] callSiteArray = _sendRequest_closure16.$getCallSiteArray();
                        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                            if (ScriptBytecodeAdapter.compareNotEqual(this.assertionClosure.get(), null)) {
                                return callSiteArray[0].call(this.assertionClosure.get(), response, content);
                            }
                            return null;
                        }
                        if (ScriptBytecodeAdapter.compareNotEqual(this.assertionClosure.get(), null)) {
                            return callSiteArray[1].call(this.assertionClosure.get(), response, content);
                        }
                        return null;
                    }

                    public Object call(Object response, Object content) {
                        CallSite[] callSiteArray = _sendRequest_closure16.$getCallSiteArray();
                        return callSiteArray[2].callCurrent(this, response, content);
                    }

                    public Object getAssertionClosure() {
                        CallSite[] callSiteArray = _sendRequest_closure16.$getCallSiteArray();
                        return this.assertionClosure.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _sendRequest_closure16.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _sendRequest_closure16.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _sendRequest_closure16.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "call";
                        stringArray[1] = "call";
                        stringArray[2] = "doCall";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[3];
                        _sendRequest_closure16.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_sendRequest_closure16.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _sendRequest_closure16.$createCallSiteArray();
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
            } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[691].call(callSiteArray[692].call(callSiteArray[693].callGetProperty(Method.class)), method))) {
                callSiteArray[694].call(http, ScriptBytecodeAdapter.createMap(new Object[]{"path", targetPath, "body", bodyContent, "allowContentType", this.allowContentType, "requestContentType", callSiteArray[695].call((Object)this.requestHeaders, CONTENT_TYPE), "contentType", acceptContentType}), new GeneratedClosure(this, this, assertionClosure2){
                    private /* synthetic */ Reference assertionClosure;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _sendRequest_closure17.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.assertionClosure = reference = assertionClosure;
                    }

                    public Object doCall(Object response, Object content) {
                        CallSite[] callSiteArray = _sendRequest_closure17.$getCallSiteArray();
                        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                            if (ScriptBytecodeAdapter.compareNotEqual(this.assertionClosure.get(), null)) {
                                return callSiteArray[0].call(this.assertionClosure.get(), response, content);
                            }
                            return null;
                        }
                        if (ScriptBytecodeAdapter.compareNotEqual(this.assertionClosure.get(), null)) {
                            return callSiteArray[1].call(this.assertionClosure.get(), response, content);
                        }
                        return null;
                    }

                    public Object call(Object response, Object content) {
                        CallSite[] callSiteArray = _sendRequest_closure17.$getCallSiteArray();
                        return callSiteArray[2].callCurrent(this, response, content);
                    }

                    public Object getAssertionClosure() {
                        CallSite[] callSiteArray = _sendRequest_closure17.$getCallSiteArray();
                        return this.assertionClosure.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _sendRequest_closure17.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _sendRequest_closure17.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _sendRequest_closure17.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "call";
                        stringArray[1] = "call";
                        stringArray[2] = "doCall";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[3];
                        _sendRequest_closure17.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_sendRequest_closure17.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _sendRequest_closure17.$createCallSiteArray();
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
            } else {
                Object object4;
                this.requestBody = object4 = bodyContent;
                callSiteArray[696].callCurrent((GroovyObject)this, ArrayUtil.createArray(http, method, acceptContentType, targetPath, assertionClosure2.get()));
            }
        } else {
            callSiteArray[697].callCurrent((GroovyObject)this, ArrayUtil.createArray(http, method, acceptContentType, targetPath, assertionClosure2.get()));
        }
        return (Response)ScriptBytecodeAdapter.castToType(restAssuredResponse, Response.class);
    }

    public void assertCorrectNumberOfPathParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[698].call(callSiteArray[699].callCurrent(this))) || !DefaultTypeTransformation.booleanUnbox(callSiteArray[700].call(callSiteArray[701].callCurrent(this))) || !DefaultTypeTransformation.booleanUnbox(callSiteArray[702].call(callSiteArray[703].callCurrent(this)))) {
                Object pathParamPlaceholderSize = callSiteArray[704].call(callSiteArray[705].callCurrent(this));
                Reference<Object> namedPathParams = new Reference<Object>(callSiteArray[706].callCurrent(this));
                Object pathParamSize = callSiteArray[707].call(callSiteArray[708].call(namedPathParams.get()), callSiteArray[709].call(callSiteArray[710].call(callSiteArray[711].call(this.unnamedPathParamsTuples, new GeneratedClosure(this, this){
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        CallSite[] callSiteArray = _assertCorrectNumberOfPathParams_closure18.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                    }

                    public Object doCall(Object it) {
                        CallSite[] callSiteArray = _assertCorrectNumberOfPathParams_closure18.$getCallSiteArray();
                        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                            return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[0].callGetProperty(it), null);
                        }
                        return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[1].callGetProperty(it), null);
                    }

                    public Object doCall() {
                        CallSite[] callSiteArray = _assertCorrectNumberOfPathParams_closure18.$getCallSiteArray();
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _assertCorrectNumberOfPathParams_closure18.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _assertCorrectNumberOfPathParams_closure18.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _assertCorrectNumberOfPathParams_closure18.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "v2";
                        stringArray[1] = "v2";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[2];
                        _assertCorrectNumberOfPathParams_closure18.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_assertCorrectNumberOfPathParams_closure18.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _assertCorrectNumberOfPathParams_closure18.$createCallSiteArray();
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
                }), new GeneratedClosure(this, this, namedPathParams){
                    private /* synthetic */ Reference namedPathParams;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _assertCorrectNumberOfPathParams_closure19.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.namedPathParams = reference = namedPathParams;
                    }

                    public Object doCall(Object it) {
                        CallSite[] callSiteArray = _assertCorrectNumberOfPathParams_closure19.$getCallSiteArray();
                        return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(this.namedPathParams.get(), callSiteArray[1].callGetProperty(it)));
                    }

                    public Object getNamedPathParams() {
                        CallSite[] callSiteArray = _assertCorrectNumberOfPathParams_closure19.$getCallSiteArray();
                        return this.namedPathParams.get();
                    }

                    public Object doCall() {
                        CallSite[] callSiteArray = _assertCorrectNumberOfPathParams_closure19.$getCallSiteArray();
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _assertCorrectNumberOfPathParams_closure19.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _assertCorrectNumberOfPathParams_closure19.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _assertCorrectNumberOfPathParams_closure19.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "containsKey";
                        stringArray[1] = "v2";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[2];
                        _assertCorrectNumberOfPathParams_closure19.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_assertCorrectNumberOfPathParams_closure19.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _assertCorrectNumberOfPathParams_closure19.$createCallSiteArray();
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
                })));
                Object redundantNamedPathParams = callSiteArray[712].callCurrent(this);
                Object redundantUnnamedPathParamValues = callSiteArray[713].callCurrent(this);
                Boolean hasRedundantNamedPathParams = ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[714].call(redundantNamedPathParams), 0);
                Boolean hasRedundantUnnamedPathParamValues = ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[715].call(redundantUnnamedPathParamValues), 0);
                String message = null;
                if (ScriptBytecodeAdapter.compareNotEqual(pathParamPlaceholderSize, pathParamSize)) {
                    GStringImpl gStringImpl = new GStringImpl(new Object[]{pathParamPlaceholderSize, pathParamSize}, new String[]{"Invalid number of path parameters. Expected ", ", was ", "."});
                    message = (String)ScriptBytecodeAdapter.castToType(gStringImpl, String.class);
                } else {
                    String string;
                    message = string = "Path parameters were not correctly defined.";
                }
                String redundantMessage = "";
                if (DefaultTypeTransformation.booleanUnbox(hasRedundantNamedPathParams) || DefaultTypeTransformation.booleanUnbox(hasRedundantUnnamedPathParamValues)) {
                    String string;
                    redundantMessage = string = " Redundant path parameters are: ";
                    if (DefaultTypeTransformation.booleanUnbox(hasRedundantNamedPathParams)) {
                        redundantMessage = (String)ScriptBytecodeAdapter.castToType(callSiteArray[716].call((Object)redundantMessage, new GStringImpl(new Object[]{callSiteArray[717].call(callSiteArray[718].call(redundantNamedPathParams), ", ")}, new String[]{"", ""})), String.class);
                    }
                    if (DefaultTypeTransformation.booleanUnbox(hasRedundantNamedPathParams) && DefaultTypeTransformation.booleanUnbox(hasRedundantUnnamedPathParamValues)) {
                        redundantMessage = (String)ScriptBytecodeAdapter.castToType(callSiteArray[719].call((Object)redundantMessage, " and "), String.class);
                    } else if (DefaultTypeTransformation.booleanUnbox(hasRedundantNamedPathParams) && !DefaultTypeTransformation.booleanUnbox(hasRedundantUnnamedPathParamValues)) {
                        redundantMessage = (String)ScriptBytecodeAdapter.castToType(callSiteArray[720].call((Object)redundantMessage, "."), String.class);
                    }
                    if (DefaultTypeTransformation.booleanUnbox(hasRedundantUnnamedPathParamValues)) {
                        redundantMessage = (String)ScriptBytecodeAdapter.castToType(callSiteArray[721].call((Object)redundantMessage, new GStringImpl(new Object[]{callSiteArray[722].call(redundantUnnamedPathParamValues, ", ")}, new String[]{"", "."})), String.class);
                    }
                }
                String undefinedMessage = "";
                if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[723].call(callSiteArray[724].callCurrent(this)))) {
                    GStringImpl gStringImpl = new GStringImpl(new Object[]{callSiteArray[725].call(callSiteArray[726].callCurrent(this), ", ")}, new String[]{" Undefined path parameters are: ", "."});
                    undefinedMessage = (String)ScriptBytecodeAdapter.castToType(gStringImpl, String.class);
                }
                throw (Throwable)callSiteArray[727].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{message, redundantMessage, undefinedMessage}, new String[]{"", "", "", ""}));
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[728].call(callSiteArray[729].callCurrent(this))) || !DefaultTypeTransformation.booleanUnbox(callSiteArray[730].call(callSiteArray[731].callCurrent(this))) || !DefaultTypeTransformation.booleanUnbox(callSiteArray[732].call(callSiteArray[733].callCurrent(this)))) {
            Object pathParamPlaceholderSize = callSiteArray[734].call(callSiteArray[735].callCurrent(this));
            Reference<Object> namedPathParams = new Reference<Object>(callSiteArray[736].callCurrent(this));
            Object pathParamSize = callSiteArray[737].call(callSiteArray[738].call(namedPathParams.get()), callSiteArray[739].call(callSiteArray[740].call(callSiteArray[741].call(this.unnamedPathParamsTuples, new /* invalid duplicate definition of identical inner class */), new /* invalid duplicate definition of identical inner class */)));
            Object redundantNamedPathParams = callSiteArray[742].callCurrent(this);
            Object redundantUnnamedPathParamValues = callSiteArray[743].callCurrent(this);
            Boolean hasRedundantNamedPathParams = ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[744].call(redundantNamedPathParams), 0);
            Boolean hasRedundantUnnamedPathParamValues = ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[745].call(redundantUnnamedPathParamValues), 0);
            String message = null;
            if (ScriptBytecodeAdapter.compareNotEqual(pathParamPlaceholderSize, pathParamSize)) {
                GStringImpl gStringImpl = new GStringImpl(new Object[]{pathParamPlaceholderSize, pathParamSize}, new String[]{"Invalid number of path parameters. Expected ", ", was ", "."});
                message = (String)ScriptBytecodeAdapter.castToType(gStringImpl, String.class);
            } else {
                String string;
                message = string = "Path parameters were not correctly defined.";
            }
            String redundantMessage = "";
            if (DefaultTypeTransformation.booleanUnbox(hasRedundantNamedPathParams) || DefaultTypeTransformation.booleanUnbox(hasRedundantUnnamedPathParamValues)) {
                String string;
                redundantMessage = string = " Redundant path parameters are: ";
                if (DefaultTypeTransformation.booleanUnbox(hasRedundantNamedPathParams)) {
                    redundantMessage = (String)ScriptBytecodeAdapter.castToType(callSiteArray[746].call((Object)redundantMessage, new GStringImpl(new Object[]{callSiteArray[747].call(callSiteArray[748].call(redundantNamedPathParams), ", ")}, new String[]{"", ""})), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(hasRedundantNamedPathParams) && DefaultTypeTransformation.booleanUnbox(hasRedundantUnnamedPathParamValues)) {
                    redundantMessage = (String)ScriptBytecodeAdapter.castToType(callSiteArray[749].call((Object)redundantMessage, " and "), String.class);
                } else if (DefaultTypeTransformation.booleanUnbox(hasRedundantNamedPathParams) && !DefaultTypeTransformation.booleanUnbox(hasRedundantUnnamedPathParamValues)) {
                    redundantMessage = (String)ScriptBytecodeAdapter.castToType(callSiteArray[750].call((Object)redundantMessage, "."), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(hasRedundantUnnamedPathParamValues)) {
                    redundantMessage = (String)ScriptBytecodeAdapter.castToType(callSiteArray[751].call((Object)redundantMessage, new GStringImpl(new Object[]{callSiteArray[752].call(redundantUnnamedPathParamValues, ", ")}, new String[]{"", "."})), String.class);
                }
            }
            String undefinedMessage = "";
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[753].call(callSiteArray[754].callCurrent(this)))) {
                GStringImpl gStringImpl = new GStringImpl(new Object[]{callSiteArray[755].call(callSiteArray[756].callCurrent(this), ", ")}, new String[]{" Undefined path parameters are: ", "."});
                undefinedMessage = (String)ScriptBytecodeAdapter.castToType(gStringImpl, String.class);
            }
            throw (Throwable)callSiteArray[757].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{message, redundantMessage, undefinedMessage}, new String[]{"", "", "", ""}));
        }
    }

    public boolean shouldApplySSLConfig(Object http, RestAssuredConfig cfg) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        URI uri = (URI)ScriptBytecodeAdapter.castToType(callSiteArray[758].call((URIBuilder)ScriptBytecodeAdapter.castToType(callSiteArray[759].call(http), URIBuilder.class)), URI.class);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(uri, null)) {
                throw (Throwable)callSiteArray[760].callConstructor(IllegalStateException.class, "a default URI must be set");
            }
        } else if (ScriptBytecodeAdapter.compareEqual(uri, null)) {
            throw (Throwable)callSiteArray[761].callConstructor(IllegalStateException.class, "a default URI must be set");
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareEqual(callSiteArray[762].callSafe(callSiteArray[763].call(uri)), "https") && DefaultTypeTransformation.booleanUnbox(callSiteArray[764].call(callSiteArray[765].call(cfg))) && !(this.authenticationScheme instanceof CertAuthScheme);
        }
        return ScriptBytecodeAdapter.compareEqual(callSiteArray[766].callSafe(callSiteArray[767].call(uri)), "https") && DefaultTypeTransformation.booleanUnbox(callSiteArray[768].call(callSiteArray[769].call(cfg))) && !(this.authenticationScheme instanceof CertAuthScheme);
    }

    public Object applyRestAssuredConfig(HTTPBuilder http) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[772].callSafe(this.restAssuredConfig);
        callSiteArray[770].callCurrent(this, http, callSiteArray[771].call(DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[773].callConstructor(DecoderConfig.class)));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(this.restAssuredConfig, null)) {
                callSiteArray[774].callCurrent((GroovyObject)this, callSiteArray[775].call(this.restAssuredConfig));
                callSiteArray[776].callCurrent((GroovyObject)this, callSiteArray[777].call(this.restAssuredConfig));
                callSiteArray[778].callCurrent(this, http, callSiteArray[779].call(this.restAssuredConfig));
                callSiteArray[780].callCurrent((GroovyObject)this, callSiteArray[781].call(this.restAssuredConfig));
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(this.restAssuredConfig, null)) {
            callSiteArray[782].callCurrent((GroovyObject)this, callSiteArray[783].call(this.restAssuredConfig));
            callSiteArray[784].callCurrent((GroovyObject)this, callSiteArray[785].call(this.restAssuredConfig));
            callSiteArray[786].callCurrent(this, http, callSiteArray[787].call(this.restAssuredConfig));
            callSiteArray[788].callCurrent((GroovyObject)this, callSiteArray[789].call(this.restAssuredConfig));
        }
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[790].call(this.httpClientParams))) {
            Reference<Object> p = new Reference<Object>(callSiteArray[791].call(callSiteArray[792].callGetProperty(http)));
            return callSiteArray[793].call(this.httpClientParams, new GeneratedClosure(this, this, p){
                private /* synthetic */ Reference p;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    CallSite[] callSiteArray = _applyRestAssuredConfig_closure20.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.p = reference = p;
                }

                public Object doCall(Object key, Object value) {
                    CallSite[] callSiteArray = _applyRestAssuredConfig_closure20.$getCallSiteArray();
                    return callSiteArray[0].call(this.p.get(), key, value);
                }

                public Object call(Object key, Object value) {
                    CallSite[] callSiteArray = _applyRestAssuredConfig_closure20.$getCallSiteArray();
                    return callSiteArray[1].callCurrent(this, key, value);
                }

                public Object getP() {
                    CallSite[] callSiteArray = _applyRestAssuredConfig_closure20.$getCallSiteArray();
                    return this.p.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _applyRestAssuredConfig_closure20.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _applyRestAssuredConfig_closure20.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _applyRestAssuredConfig_closure20.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "setParameter";
                    stringArray[1] = "doCall";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[2];
                    _applyRestAssuredConfig_closure20.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_applyRestAssuredConfig_closure20.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _applyRestAssuredConfig_closure20.$createCallSiteArray();
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
        }
        return null;
    }

    private Object applyContentDecoders(HTTPBuilder httpBuilder, List<DecoderConfig.ContentDecoder> contentDecoders) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object httpBuilderContentEncoders = callSiteArray[794].call(callSiteArray[795].call(contentDecoders, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _applyContentDecoders_closure21.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object contentDecoder) {
                CallSite[] callSiteArray = _applyContentDecoders_closure21.$getCallSiteArray();
                return callSiteArray[0].call(ContentEncoding.Type.class, callSiteArray[1].call(contentDecoder));
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _applyContentDecoders_closure21.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _applyContentDecoders_closure21.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _applyContentDecoders_closure21.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "valueOf";
                stringArray[1] = "toString";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _applyContentDecoders_closure21.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_applyContentDecoders_closure21.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _applyContentDecoders_closure21.$createCallSiteArray();
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
        }));
        return callSiteArray[796].call((Object)httpBuilder, httpBuilderContentEncoders);
    }

    public Object applySessionConfig(SessionConfig sessionConfig) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[797].call(sessionConfig)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[798].call((Object)this.cookies, callSiteArray[799].call(sessionConfig)))) {
                return callSiteArray[800].callCurrent(this, callSiteArray[801].call(sessionConfig), callSiteArray[802].call(sessionConfig));
            }
            return null;
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[803].call(sessionConfig)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[804].call((Object)this.cookies, callSiteArray[805].call(sessionConfig)))) {
            return callSiteArray[806].callCurrent(this, callSiteArray[807].call(sessionConfig), callSiteArray[808].call(sessionConfig));
        }
        return null;
    }

    public Object applyEncoderConfig(HTTPBuilder httpBuilder, EncoderConfig encoderConfig) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return callSiteArray[809].call(callSiteArray[810].callGetProperty(httpBuilder), encoderConfig);
    }

    public Object applyHttpClientConfig(HttpClientConfig httpClientConfig) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return callSiteArray[811].call(callSiteArray[812].call((Object)ScriptBytecodeAdapter.createMap(new Object[0]), callSiteArray[813].call(httpClientConfig)), new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _applyHttpClientConfig_closure22.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object key, Object value) {
                CallSite[] callSiteArray = _applyHttpClientConfig_closure22.$getCallSiteArray();
                return callSiteArray[0].callCurrent(this, callSiteArray[1].callGroovyObjectGetProperty(this), key, value);
            }

            public Object call(Object key, Object value) {
                CallSite[] callSiteArray = _applyHttpClientConfig_closure22.$getCallSiteArray();
                return callSiteArray[2].callCurrent(this, key, value);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _applyHttpClientConfig_closure22.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _applyHttpClientConfig_closure22.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _applyHttpClientConfig_closure22.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "putIfAbsent";
                stringArray[1] = "httpClientParams";
                stringArray[2] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _applyHttpClientConfig_closure22.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_applyHttpClientConfig_closure22.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _applyHttpClientConfig_closure22.$createCallSiteArray();
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
    }

    public Object applyRedirectConfig(RedirectConfig redirectConfig) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[814].callCurrent(this, this.httpClientParams, callSiteArray[815].callGetProperty(ClientPNames.class), callSiteArray[816].call(redirectConfig));
        callSiteArray[817].callCurrent(this, this.httpClientParams, callSiteArray[818].callGetProperty(ClientPNames.class), callSiteArray[819].call(redirectConfig));
        callSiteArray[820].callCurrent(this, this.httpClientParams, callSiteArray[821].callGetProperty(ClientPNames.class), callSiteArray[822].call(redirectConfig));
        return callSiteArray[823].callCurrent(this, this.httpClientParams, callSiteArray[824].callGetProperty(ClientPNames.class), callSiteArray[825].call(redirectConfig));
    }

    private Object putIfAbsent(Map map, Object key, Object value) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[826].call((Object)map, key))) {
            return callSiteArray[827].call(map, key, value);
        }
        return null;
    }

    public Object assembleBodyContent(Object httpMethod) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[828].callCurrent(this)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[829].call(callSiteArray[830].call(callSiteArray[831].callGetProperty(Method.class)), httpMethod))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[832].call(callSiteArray[833].call(callSiteArray[834].callGetProperty(Method.class)), httpMethod))) {
                    return callSiteArray[835].callCurrent(this, this.requestParameters, this.formParameters);
                }
                return this.formParameters;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[836].call(this.multiParts))) {
                return this.requestBody;
            }
            return new byte[0];
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[837].callCurrent(this)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[838].call(callSiteArray[839].call(callSiteArray[840].callGetProperty(Method.class)), httpMethod))) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[841].call(callSiteArray[842].call(callSiteArray[843].callGetProperty(Method.class)), httpMethod))) {
                return callSiteArray[844].callCurrent(this, this.requestParameters, this.formParameters);
            }
            return this.formParameters;
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[845].call(this.multiParts))) {
            return this.requestBody;
        }
        return new byte[0];
    }

    public Object mergeMapsAndRetainOrder(Map<String, Object> map1, Map<String, Object> map2) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object newMap = callSiteArray[846].callConstructor(LinkedHashMap.class);
        callSiteArray[847].call(newMap, map1);
        callSiteArray[848].call(newMap, map2);
        return newMap;
    }

    public Object setRequestHeadersToHttpBuilder(HTTPBuilder http) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Reference<Object> httpHeaders = new Reference<Object>(callSiteArray[849].call(http));
        return callSiteArray[850].call((Object)this.requestHeaders, new GeneratedClosure(this, this, httpHeaders){
            private /* synthetic */ Reference httpHeaders;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _setRequestHeadersToHttpBuilder_closure23.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.httpHeaders = reference = httpHeaders;
            }

            public Object doCall(Object header) {
                CallSite[] callSiteArray = _setRequestHeadersToHttpBuilder_closure23.$getCallSiteArray();
                Object headerName = callSiteArray[0].call(header);
                Object headerValue = callSiteArray[1].call(header);
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call(this.httpHeaders.get(), headerName))) {
                    List values = ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[3].call(this.httpHeaders.get(), headerName)});
                    callSiteArray[4].call((Object)values, headerValue);
                    Object headerVal = callSiteArray[5].call(values);
                    return callSiteArray[6].call(this.httpHeaders.get(), headerName, headerVal);
                }
                return callSiteArray[7].call(this.httpHeaders.get(), headerName, headerValue);
            }

            public Object getHttpHeaders() {
                CallSite[] callSiteArray = _setRequestHeadersToHttpBuilder_closure23.$getCallSiteArray();
                return this.httpHeaders.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _setRequestHeadersToHttpBuilder_closure23.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _setRequestHeadersToHttpBuilder_closure23.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _setRequestHeadersToHttpBuilder_closure23.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "getName";
                stringArray[1] = "getValue";
                stringArray[2] = "containsKey";
                stringArray[3] = "get";
                stringArray[4] = "leftShift";
                stringArray[5] = "flatten";
                stringArray[6] = "put";
                stringArray[7] = "put";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[8];
                _setRequestHeadersToHttpBuilder_closure23.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_setRequestHeadersToHttpBuilder_closure23.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _setRequestHeadersToHttpBuilder_closure23.$createCallSiteArray();
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
    }

    private Object createFormParamBodyContent(Object bodyContent) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return bodyContent instanceof Map ? callSiteArray[851].callCurrent((GroovyObject)this, bodyContent) : bodyContent;
    }

    private String getTargetPath(String path) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[852].callStatic(PathSupport.class, path))) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[853].call(callSiteArray[854].callConstructor(URL.class, path)), String.class);
        }
        Object baseUriPath = "";
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (!(ScriptBytecodeAdapter.compareEqual(this.baseUri, null) || ScriptBytecodeAdapter.compareEqual(this.baseUri, ""))) {
                Object object;
                Object uri = callSiteArray[855].callConstructor(URI.class, this.baseUri);
                baseUriPath = object = callSiteArray[856].call(uri);
            }
        } else if (!(ScriptBytecodeAdapter.compareEqual(this.baseUri, null) || ScriptBytecodeAdapter.compareEqual(this.baseUri, ""))) {
            Object object;
            Object uri = callSiteArray[857].callConstructor(URI.class, this.baseUri);
            baseUriPath = object = callSiteArray[858].call(uri);
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[859].callStatic(PathSupport.class, callSiteArray[860].callStatic(PathSupport.class, baseUriPath, this.basePath), path), String.class);
    }

    private Object registerRestAssuredEncoders(HTTPBuilder http) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[861].call(this.multiParts))) {
            return null;
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[862].callCurrent(this))) {
            callSiteArray[863].callCurrent(this);
        }
        Object contentTypeAsString = callSiteArray[864].call(callSiteArray[865].callGroovyObjectGetProperty(this), CONTENT_TYPE);
        Object ct = callSiteArray[866].call(ContentTypeExtractor.class, contentTypeAsString);
        Reference<Object> subType = new Reference<Object>(null);
        subType.get();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[867].callSafe(callSiteArray[868].callSafe(ct), MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH))) {
            Object object = callSiteArray[869].callStatic(StringUtils.class, ct, MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH);
            subType.set(object);
        } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[870].callSafe(callSiteArray[871].callSafe(ct), MULTIPART_CONTENT_TYPE_PREFIX_WITH_PLUS))) {
            Object object = callSiteArray[872].callStatic(StringUtils.class, callSiteArray[873].callStatic(StringUtils.class, ct, MULTIPART_CONTENT_TYPE_PREFIX_WITH_PLUS), "+");
            subType.set(object);
        } else {
            throw (Throwable)callSiteArray[874].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{ct, MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH, MULTIPART_CONTENT_TYPE_PREFIX_WITH_PLUS}, new String[]{"Content-Type ", " is not valid when using multiparts, it must start with \"", "\" or contain \"", "\"."}));
        }
        Object charsetFromContentType = callSiteArray[875].call(CharsetExtractor.class, contentTypeAsString);
        Reference<Object> charsetToUse = new Reference<Object>(DefaultTypeTransformation.booleanUnbox(callSiteArray[876].callStatic(StringUtils.class, charsetFromContentType)) ? callSiteArray[877].call(callSiteArray[878].call(callSiteArray[879].callCurrent(this))) : charsetFromContentType);
        Object boundaryFromContentType = callSiteArray[880].call(BoundaryExtractor.class, contentTypeAsString);
        Object object = boundaryFromContentType;
        Reference<String> boundaryToUse = new Reference<String>((String)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[881].call(callSiteArray[882].call(callSiteArray[883].callCurrent(this))), String.class));
        String string = boundaryToUse.get();
        String string2 = DefaultTypeTransformation.booleanUnbox(string) ? string : callSiteArray[884].callStatic(RequestSpecificationImpl.class);
        boundaryToUse.set((String)ScriptBytecodeAdapter.castToType(string2, String.class));
        if (!DefaultTypeTransformation.booleanUnbox(boundaryFromContentType)) {
            callSiteArray[885].callCurrent((GroovyObject)this, CONTENT_TYPE);
            callSiteArray[886].callCurrent((GroovyObject)this, callSiteArray[887].call(callSiteArray[888].call(callSiteArray[889].call(contentTypeAsString, "; boundary=\""), boundaryToUse.get()), "\""));
        }
        Reference<Object> multipartMode = new Reference<Object>(callSiteArray[890].call(callSiteArray[891].callCurrent(this)));
        return callSiteArray[892].call(callSiteArray[893].callGetProperty(http), ct, new GeneratedClosure(this, this, multipartMode, boundaryToUse, subType, charsetToUse){
            private /* synthetic */ Reference multipartMode;
            private /* synthetic */ Reference boundaryToUse;
            private /* synthetic */ Reference subType;
            private /* synthetic */ Reference charsetToUse;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.multipartMode = reference4 = multipartMode;
                this.boundaryToUse = reference3 = boundaryToUse;
                this.subType = reference2 = subType;
                this.charsetToUse = reference = charsetToUse;
            }

            public Object doCall(Object contentType, Object content) {
                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24.$getCallSiteArray();
                Reference<RestAssuredMultiPartEntity> entity = new Reference<RestAssuredMultiPartEntity>((RestAssuredMultiPartEntity)ScriptBytecodeAdapter.castToType(callSiteArray[0].callConstructor(RestAssuredMultiPartEntity.class, this.subType.get(), this.charsetToUse.get(), this.multipartMode.get(), this.boundaryToUse.get()), RestAssuredMultiPartEntity.class));
                callSiteArray[1].call(callSiteArray[2].callGroovyObjectGetProperty(this), new GeneratedClosure(this, this.getThisObject(), entity){
                    private /* synthetic */ Reference entity;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.entity = reference = entity;
                    }

                    public Object doCall(Object it) {
                        CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51.$getCallSiteArray();
                        Object body = callSiteArray[0].callGetProperty(it);
                        Object controlName = callSiteArray[1].callGetProperty(it);
                        Object headers = callSiteArray[2].callGetProperty(it);
                        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[3].call(headers))) {
                            return callSiteArray[4].call(this.entity.get(), controlName, body);
                        }
                        Reference<Object> builder = new Reference<Object>(callSiteArray[5].call(FormBodyPartBuilder.class, controlName, body));
                        callSiteArray[6].call(headers, new GeneratedClosure(this, this.getThisObject(), builder){
                            private /* synthetic */ Reference builder;
                            private static /* synthetic */ ClassInfo $staticClassInfo;
                            public static transient /* synthetic */ boolean __$stMC;
                            private static /* synthetic */ SoftReference $callSiteArray;
                            {
                                Reference reference;
                                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51_closure52.$getCallSiteArray();
                                super(_outerInstance, _thisObject);
                                this.builder = reference = builder;
                            }

                            public Object doCall(Object name, Object value) {
                                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51_closure52.$getCallSiteArray();
                                return callSiteArray[0].call(this.builder.get(), name, value);
                            }

                            public Object call(Object name, Object value) {
                                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51_closure52.$getCallSiteArray();
                                return callSiteArray[1].callCurrent(this, name, value);
                            }

                            public Object getBuilder() {
                                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51_closure52.$getCallSiteArray();
                                return this.builder.get();
                            }

                            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                if (this.getClass() != _registerRestAssuredEncoders_closure24_closure51_closure52.class) {
                                    return ScriptBytecodeAdapter.initMetaClass(this);
                                }
                                ClassInfo classInfo = $staticClassInfo;
                                if (classInfo == null) {
                                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                                }
                                return classInfo.getMetaClass();
                            }

                            public static /* synthetic */ void __$swapInit() {
                                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51_closure52.$getCallSiteArray();
                                $callSiteArray = null;
                            }

                            static {
                                _registerRestAssuredEncoders_closure24_closure51_closure52.__$swapInit();
                            }

                            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                                stringArray[0] = "addField";
                                stringArray[1] = "doCall";
                            }

                            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                                String[] stringArray = new String[2];
                                _registerRestAssuredEncoders_closure24_closure51_closure52.$createCallSiteArray_1(stringArray);
                                return new CallSiteArray(_registerRestAssuredEncoders_closure24_closure51_closure52.class, stringArray);
                            }

                            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                                CallSiteArray callSiteArray;
                                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                    callSiteArray = _registerRestAssuredEncoders_closure24_closure51_closure52.$createCallSiteArray();
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
                        return callSiteArray[7].call(this.entity.get(), callSiteArray[8].call(builder.get()));
                    }

                    public RestAssuredMultiPartEntity getEntity() {
                        CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51.$getCallSiteArray();
                        return (RestAssuredMultiPartEntity)ScriptBytecodeAdapter.castToType(this.entity.get(), RestAssuredMultiPartEntity.class);
                    }

                    public Object doCall() {
                        CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51.$getCallSiteArray();
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _registerRestAssuredEncoders_closure24_closure51.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24_closure51.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _registerRestAssuredEncoders_closure24_closure51.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "contentBody";
                        stringArray[1] = "controlName";
                        stringArray[2] = "headers";
                        stringArray[3] = "isEmpty";
                        stringArray[4] = "addPart";
                        stringArray[5] = "create";
                        stringArray[6] = "each";
                        stringArray[7] = "addPart";
                        stringArray[8] = "build";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[9];
                        _registerRestAssuredEncoders_closure24_closure51.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_registerRestAssuredEncoders_closure24_closure51.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _registerRestAssuredEncoders_closure24_closure51.$createCallSiteArray();
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
                return entity.get();
            }

            public Object call(Object contentType, Object content) {
                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24.$getCallSiteArray();
                return callSiteArray[3].callCurrent(this, contentType, content);
            }

            public Object getMultipartMode() {
                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24.$getCallSiteArray();
                return this.multipartMode.get();
            }

            public String getBoundaryToUse() {
                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.boundaryToUse.get(), String.class);
            }

            public Object getSubType() {
                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24.$getCallSiteArray();
                return this.subType.get();
            }

            public Object getCharsetToUse() {
                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24.$getCallSiteArray();
                return this.charsetToUse.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _registerRestAssuredEncoders_closure24.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _registerRestAssuredEncoders_closure24.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _registerRestAssuredEncoders_closure24.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "<$constructor$>";
                stringArray[1] = "each";
                stringArray[2] = "multiParts";
                stringArray[3] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[4];
                _registerRestAssuredEncoders_closure24.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_registerRestAssuredEncoders_closure24.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _registerRestAssuredEncoders_closure24.$createCallSiteArray();
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
    }

    private static String generateBoundary() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Reference<Object> alphabet = new Reference<Object>(callSiteArray[894].call(callSiteArray[895].call(callSiteArray[896].call(callSiteArray[897].call(callSiteArray[898].call((Object)ScriptBytecodeAdapter.createRange("a", "z", true), ScriptBytecodeAdapter.createRange("A", "Z", true)), ScriptBytecodeAdapter.createRange("0", "9", true)), "-"), "_")));
        Reference<Object> rand = new Reference<Object>(callSiteArray[899].callConstructor(Random.class));
        Object length = callSiteArray[900].call(callSiteArray[901].call(rand.get(), 11), 30);
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[902].call(callSiteArray[903].call((Object)ScriptBytecodeAdapter.createRange(1, length, true), new GeneratedClosure(RequestSpecificationImpl.class, RequestSpecificationImpl.class, rand, alphabet){
            private /* synthetic */ Reference rand;
            private /* synthetic */ Reference alphabet;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _generateBoundary_closure25.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.rand = reference2 = rand;
                this.alphabet = reference = alphabet;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _generateBoundary_closure25.$getCallSiteArray();
                return callSiteArray[0].call(this.alphabet.get(), callSiteArray[1].call(this.rand.get(), callSiteArray[2].call(this.alphabet.get())));
            }

            public Object getRand() {
                CallSite[] callSiteArray = _generateBoundary_closure25.$getCallSiteArray();
                return this.rand.get();
            }

            public Object getAlphabet() {
                CallSite[] callSiteArray = _generateBoundary_closure25.$getCallSiteArray();
                return this.alphabet.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _generateBoundary_closure25.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _generateBoundary_closure25.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _generateBoundary_closure25.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _generateBoundary_closure25.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "getAt";
                stringArray[1] = "nextInt";
                stringArray[2] = "length";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _generateBoundary_closure25.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_generateBoundary_closure25.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _generateBoundary_closure25.$createCallSiteArray();
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
        })), String.class);
    }

    private Object convertFormParamsToMultiPartParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object allFormParams = callSiteArray[904].callCurrent(this, this.requestParameters, this.formParameters);
        callSiteArray[905].call(allFormParams, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _convertFormParamsToMultiPartParams_closure26.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                Reference<Object> it2 = new Reference<Object>(it);
                CallSite[] callSiteArray = _convertFormParamsToMultiPartParams_closure26.$getCallSiteArray();
                if (callSiteArray[0].callGetProperty(it2.get()) instanceof List) {
                    return callSiteArray[1].call(callSiteArray[2].callGetProperty(it2.get()), new GeneratedClosure(this, this.getThisObject(), it2){
                        private /* synthetic */ Reference it;
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;
                        private static /* synthetic */ SoftReference $callSiteArray;
                        {
                            Reference reference;
                            CallSite[] callSiteArray = _convertFormParamsToMultiPartParams_closure26_closure53.$getCallSiteArray();
                            super(_outerInstance, _thisObject);
                            this.it = reference = it;
                        }

                        public Object doCall(Object val) {
                            CallSite[] callSiteArray = _convertFormParamsToMultiPartParams_closure26_closure53.$getCallSiteArray();
                            return callSiteArray[0].callCurrent(this, callSiteArray[1].callGetProperty(this.it.get()), val);
                        }

                        public Object getIt() {
                            CallSite[] callSiteArray = _convertFormParamsToMultiPartParams_closure26_closure53.$getCallSiteArray();
                            return this.it.get();
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _convertFormParamsToMultiPartParams_closure26_closure53.class) {
                                return ScriptBytecodeAdapter.initMetaClass(this);
                            }
                            ClassInfo classInfo = $staticClassInfo;
                            if (classInfo == null) {
                                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                            }
                            return classInfo.getMetaClass();
                        }

                        public static /* synthetic */ void __$swapInit() {
                            CallSite[] callSiteArray = _convertFormParamsToMultiPartParams_closure26_closure53.$getCallSiteArray();
                            $callSiteArray = null;
                        }

                        static {
                            _convertFormParamsToMultiPartParams_closure26_closure53.__$swapInit();
                        }

                        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                            stringArray[0] = "multiPart";
                            stringArray[1] = "key";
                        }

                        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                            String[] stringArray = new String[2];
                            _convertFormParamsToMultiPartParams_closure26_closure53.$createCallSiteArray_1(stringArray);
                            return new CallSiteArray(_convertFormParamsToMultiPartParams_closure26_closure53.class, stringArray);
                        }

                        private static /* synthetic */ CallSite[] $getCallSiteArray() {
                            CallSiteArray callSiteArray;
                            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                callSiteArray = _convertFormParamsToMultiPartParams_closure26_closure53.$createCallSiteArray();
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
                }
                return callSiteArray[3].callCurrent(this, callSiteArray[4].callGetProperty(it2.get()), callSiteArray[5].callGetProperty(it2.get()));
            }

            public Object doCall() {
                CallSite[] callSiteArray = _convertFormParamsToMultiPartParams_closure26.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _convertFormParamsToMultiPartParams_closure26.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _convertFormParamsToMultiPartParams_closure26.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _convertFormParamsToMultiPartParams_closure26.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "value";
                stringArray[1] = "each";
                stringArray[2] = "value";
                stringArray[3] = "multiPart";
                stringArray[4] = "key";
                stringArray[5] = "value";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[6];
                _convertFormParamsToMultiPartParams_closure26.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_convertFormParamsToMultiPartParams_closure26.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _convertFormParamsToMultiPartParams_closure26.$createCallSiteArray();
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
        callSiteArray[906].call(this.requestParameters);
        return callSiteArray[907].call(this.formParameters);
    }

    private Object sendHttpRequest(HTTPBuilder http, String method, Object responseContentType, Object targetPath, Object assertionClosure) {
        Reference<String> method2 = new Reference<String>(method);
        Reference<Object> targetPath2 = new Reference<Object>(targetPath);
        Reference<Object> assertionClosure2 = new Reference<Object>(assertionClosure);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Reference<Object> allQueryParams = new Reference<Object>(callSiteArray[908].callCurrent(this, this.requestParameters, this.queryParameters));
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[909].call((Object)method2.get(), callSiteArray[910].call(callSiteArray[911].callGetProperty(Method.class))))) {
            Object object = callSiteArray[912].callCurrent(this, allQueryParams.get(), this.formParameters);
            allQueryParams.set(object);
        }
        Reference<Object> hasBody = new Reference<Object>(null);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            boolean bl = ScriptBytecodeAdapter.compareNotEqual(this.requestBody, null);
            hasBody.set(bl);
        } else {
            boolean bl = ScriptBytecodeAdapter.compareNotEqual(this.requestBody, null);
            hasBody.set(bl);
        }
        return callSiteArray[913].call(http, method2.get(), responseContentType, hasBody.get(), new GeneratedClosure(this, this, method2, assertionClosure2, targetPath2, allQueryParams, hasBody){
            private /* synthetic */ Reference method;
            private /* synthetic */ Reference assertionClosure;
            private /* synthetic */ Reference targetPath;
            private /* synthetic */ Reference allQueryParams;
            private /* synthetic */ Reference hasBody;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                Reference reference5;
                CallSite[] callSiteArray = _sendHttpRequest_closure27.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.method = reference5 = method;
                this.assertionClosure = reference4 = assertionClosure;
                this.targetPath = reference3 = targetPath;
                this.allQueryParams = reference2 = allQueryParams;
                this.hasBody = reference = hasBody;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _sendHttpRequest_closure27.$getCallSiteArray();
                Object t = this.targetPath.get();
                ScriptBytecodeAdapter.setProperty(t, null, callSiteArray[0].callGroovyObjectGetProperty(this), "path");
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[1].callGroovyObjectGetProperty(this.getThisObject()))) {
                    callSiteArray[2].callCurrent((GroovyObject)this, callSiteArray[3].callCurrent((GroovyObject)this, this.method.get()));
                }
                if (DefaultTypeTransformation.booleanUnbox(this.hasBody.get())) {
                    Object object = callSiteArray[4].callGroovyObjectGetProperty(this);
                    ScriptBytecodeAdapter.setGroovyObjectProperty(object, _sendHttpRequest_closure27.class, this, "body");
                }
                Object t2 = this.allQueryParams.get();
                ScriptBytecodeAdapter.setProperty(t2, null, callSiteArray[5].callGroovyObjectGetProperty(this), "query");
                Closure closure = (Closure)ScriptBytecodeAdapter.castToType(callSiteArray[6].call(this.assertionClosure.get()), Closure.class);
                Closure closure2 = closure;
                ScriptBytecodeAdapter.setProperty(closure2, null, callSiteArray[7].callGroovyObjectGetProperty(this), "success");
                Closure closure3 = closure;
                ScriptBytecodeAdapter.setProperty(closure3, null, callSiteArray[8].callGroovyObjectGetProperty(this), "failure");
                return closure3;
            }

            public String getMethod() {
                CallSite[] callSiteArray = _sendHttpRequest_closure27.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.method.get(), String.class);
            }

            public Object getAssertionClosure() {
                CallSite[] callSiteArray = _sendHttpRequest_closure27.$getCallSiteArray();
                return this.assertionClosure.get();
            }

            public Object getTargetPath() {
                CallSite[] callSiteArray = _sendHttpRequest_closure27.$getCallSiteArray();
                return this.targetPath.get();
            }

            public Object getAllQueryParams() {
                CallSite[] callSiteArray = _sendHttpRequest_closure27.$getCallSiteArray();
                return this.allQueryParams.get();
            }

            public Object getHasBody() {
                CallSite[] callSiteArray = _sendHttpRequest_closure27.$getCallSiteArray();
                return this.hasBody.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _sendHttpRequest_closure27.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _sendHttpRequest_closure27.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _sendHttpRequest_closure27.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _sendHttpRequest_closure27.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "uri";
                stringArray[1] = "allowContentType";
                stringArray[2] = "setRequestContentType";
                stringArray[3] = "defineRequestContentTypeAsString";
                stringArray[4] = "requestBody";
                stringArray[5] = "uri";
                stringArray[6] = "getClosure";
                stringArray[7] = "response";
                stringArray[8] = "response";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[9];
                _sendHttpRequest_closure27.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_sendHttpRequest_closure27.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _sendHttpRequest_closure27.$createCallSiteArray();
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
    }

    private boolean hasFormParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return !(DefaultTypeTransformation.booleanUnbox(callSiteArray[914].call(this.requestParameters)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[915].call(this.formParameters)));
        }
        return !(DefaultTypeTransformation.booleanUnbox(callSiteArray[916].call(this.requestParameters)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[917].call(this.formParameters)));
    }

    private boolean mayHaveBody(Object method) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return DefaultTypeTransformation.booleanUnbox(callSiteArray[918].call(callSiteArray[919].call(callSiteArray[920].callGetProperty(Method.class)), method)) || ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[921].call(this.formParameters), 0) || ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[922].call(this.multiParts), 0);
        }
        return DefaultTypeTransformation.booleanUnbox(callSiteArray[923].call(callSiteArray[924].call(callSiteArray[925].callGetProperty(Method.class)), method)) || ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[926].call(this.formParameters), 0) || ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[927].call(this.multiParts), 0);
    }

    private String extractRequestParamsIfNeeded(String path) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[928].call((Object)path, "?"))) {
            Object indexOfQuestionMark = callSiteArray[929].call((Object)path, "?");
            String allParamAsString = (String)ScriptBytecodeAdapter.castToType(callSiteArray[930].call((Object)path, callSiteArray[931].call(indexOfQuestionMark, 1)), String.class);
            Reference<Object> keyValueParams = new Reference<Object>(callSiteArray[932].call((Object)allParamAsString, "&"));
            callSiteArray[933].call(keyValueParams.get(), new GeneratedClosure(this, this, keyValueParams){
                private /* synthetic */ Reference keyValueParams;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    CallSite[] callSiteArray = _extractRequestParamsIfNeeded_closure28.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.keyValueParams = reference = keyValueParams;
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _extractRequestParamsIfNeeded_closure28.$getCallSiteArray();
                    Object keyValue = callSiteArray[0].callStatic(StringUtils.class, it, "=", 2);
                    Object theKey = null;
                    Object theValue = null;
                    if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                        if (ScriptBytecodeAdapter.compareLessThan(callSiteArray[1].callGetProperty(keyValue), 1) || ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[2].callGetProperty(keyValue), 2)) {
                            throw (Throwable)callSiteArray[3].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{this.keyValueParams.get()}, new String[]{"Illegal parameters passed to REST Assured. Parameters was: ", ""}));
                        }
                        if (ScriptBytecodeAdapter.compareEqual(callSiteArray[4].callGetProperty(keyValue), 1)) {
                            Object object;
                            theKey = object = callSiteArray[5].call(keyValue, 0);
                            String string = DefaultTypeTransformation.booleanUnbox(callSiteArray[6].call(it, "=")) ? "" : callSiteArray[7].callConstructor(NoParameterValue.class);
                            theValue = string;
                        } else {
                            Object object;
                            Object object2;
                            theKey = object2 = callSiteArray[8].call(keyValue, 0);
                            theValue = object = callSiteArray[9].call(keyValue, 1);
                        }
                    } else {
                        if (ScriptBytecodeAdapter.compareLessThan(callSiteArray[10].callGetProperty(keyValue), 1) || ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[11].callGetProperty(keyValue), 2)) {
                            throw (Throwable)callSiteArray[12].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{this.keyValueParams.get()}, new String[]{"Illegal parameters passed to REST Assured. Parameters was: ", ""}));
                        }
                        if (ScriptBytecodeAdapter.compareEqual(callSiteArray[13].callGetProperty(keyValue), 1)) {
                            Object object;
                            theKey = object = callSiteArray[14].call(keyValue, 0);
                            String string = DefaultTypeTransformation.booleanUnbox(callSiteArray[15].call(it, "=")) ? "" : callSiteArray[16].callConstructor(NoParameterValue.class);
                            theValue = string;
                        } else {
                            Object object;
                            Object object3;
                            theKey = object3 = callSiteArray[17].call(keyValue, 0);
                            theValue = object = callSiteArray[18].call(keyValue, 1);
                        }
                    }
                    return callSiteArray[19].callCurrent(this, theKey, theValue);
                }

                public Object getKeyValueParams() {
                    CallSite[] callSiteArray = _extractRequestParamsIfNeeded_closure28.$getCallSiteArray();
                    return this.keyValueParams.get();
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _extractRequestParamsIfNeeded_closure28.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _extractRequestParamsIfNeeded_closure28.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _extractRequestParamsIfNeeded_closure28.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _extractRequestParamsIfNeeded_closure28.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "split";
                    stringArray[1] = "length";
                    stringArray[2] = "length";
                    stringArray[3] = "<$constructor$>";
                    stringArray[4] = "length";
                    stringArray[5] = "getAt";
                    stringArray[6] = "contains";
                    stringArray[7] = "<$constructor$>";
                    stringArray[8] = "getAt";
                    stringArray[9] = "getAt";
                    stringArray[10] = "length";
                    stringArray[11] = "length";
                    stringArray[12] = "<$constructor$>";
                    stringArray[13] = "length";
                    stringArray[14] = "getAt";
                    stringArray[15] = "contains";
                    stringArray[16] = "<$constructor$>";
                    stringArray[17] = "getAt";
                    stringArray[18] = "getAt";
                    stringArray[19] = "queryParam";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[20];
                    _extractRequestParamsIfNeeded_closure28.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_extractRequestParamsIfNeeded_closure28.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _extractRequestParamsIfNeeded_closure28.$createCallSiteArray();
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
            Object object = callSiteArray[934].call(path, 0, indexOfQuestionMark);
            path = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        }
        return path;
    }

    private Object defineRequestContentTypeAsString(String method) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return callSiteArray[935].callSafe(callSiteArray[936].callCurrent((GroovyObject)this, method));
    }

    private Object defineRequestContentType(String method) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object contentType = callSiteArray[937].call(callSiteArray[938].callGroovyObjectGetProperty(this), CONTENT_TYPE);
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(contentType, null)) {
                Object object;
                Object object2;
                Object object3;
                Object object4;
                Object object5;
                contentType = ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[939].call(this.multiParts), 0) ? (object5 = callSiteArray[940].call((Object)MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH, callSiteArray[941].call(callSiteArray[942].call(callSiteArray[943].callCurrent(this))))) : (DefaultTypeTransformation.booleanUnbox(callSiteArray[944].call(callSiteArray[945].call(callSiteArray[946].callGetProperty(Method.class)), method)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[947].call(this.formParameters)) ? (object4 = callSiteArray[948].callGetProperty(ContentType.class)) : (ScriptBytecodeAdapter.compareEqual(this.requestBody, null) ? (object3 = DefaultTypeTransformation.booleanUnbox(callSiteArray[949].callCurrent((GroovyObject)this, method)) ? callSiteArray[950].callGetProperty(ContentType.class) : null) : (this.requestBody instanceof byte[] || this.requestBody instanceof InputStream ? (object2 = callSiteArray[951].callGetProperty(ContentType.class)) : (object = callSiteArray[952].callGetProperty(ContentType.class)))));
            }
        } else if (ScriptBytecodeAdapter.compareEqual(contentType, null)) {
            Object object;
            Object object6;
            Object object7;
            Object object8;
            Object object9;
            contentType = ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[953].call(this.multiParts), 0) ? (object9 = callSiteArray[954].call((Object)MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH, callSiteArray[955].call(callSiteArray[956].call(callSiteArray[957].callCurrent(this))))) : (DefaultTypeTransformation.booleanUnbox(callSiteArray[958].call(callSiteArray[959].call(callSiteArray[960].callGetProperty(Method.class)), method)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[961].call(this.formParameters)) ? (object8 = callSiteArray[962].callGetProperty(ContentType.class)) : (ScriptBytecodeAdapter.compareEqual(this.requestBody, null) ? (object7 = DefaultTypeTransformation.booleanUnbox(callSiteArray[963].callCurrent((GroovyObject)this, method)) ? callSiteArray[964].callGetProperty(ContentType.class) : null) : (this.requestBody instanceof byte[] || this.requestBody instanceof InputStream ? (object6 = callSiteArray[965].callGetProperty(ContentType.class)) : (object = callSiteArray[966].callGetProperty(ContentType.class)))));
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[967].callCurrent((GroovyObject)this, contentType))) {
            Object object;
            Object object10;
            Object charset = callSiteArray[968].callCurrent((GroovyObject)this, callSiteArray[969].call(contentType));
            contentType = contentType instanceof String ? (object10 = callSiteArray[970].call(callSiteArray[971].call(callSiteArray[972].call(callSiteArray[973].call(contentType, "; "), CHARSET), "="), charset)) : (object = callSiteArray[974].call(contentType, charset));
        }
        return contentType;
    }

    private boolean shouldAppendCharsetToContentType(Object contentType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareNotEqual(contentType, null) && !(DefaultTypeTransformation.booleanUnbox(callSiteArray[975].callStatic(StringUtils.class, callSiteArray[976].call(contentType), MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[977].callStatic(StringUtils.class, callSiteArray[978].call(contentType), MULTIPART_CONTENT_TYPE_PREFIX_WITH_PLUS))) && DefaultTypeTransformation.booleanUnbox(callSiteArray[979].call(callSiteArray[980].callGetProperty(callSiteArray[981].callCurrent(this)))) && (DefaultTypeTransformation.booleanUnbox(callSiteArray[982].callCurrent((GroovyObject)this, contentType)) || !(DefaultTypeTransformation.booleanUnbox(callSiteArray[983].callStatic(StringUtils.class, callSiteArray[984].call(contentType), CHARSET)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[985].callStatic(StringUtils.class, callSiteArray[986].call(contentType), APPLICATION_JSON))));
        }
        return ScriptBytecodeAdapter.compareNotEqual(contentType, null) && !(DefaultTypeTransformation.booleanUnbox(callSiteArray[987].callStatic(StringUtils.class, callSiteArray[988].call(contentType), MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[989].callStatic(StringUtils.class, callSiteArray[990].call(contentType), MULTIPART_CONTENT_TYPE_PREFIX_WITH_PLUS))) && DefaultTypeTransformation.booleanUnbox(callSiteArray[991].call(callSiteArray[992].callGetProperty(callSiteArray[993].callCurrent(this)))) && (DefaultTypeTransformation.booleanUnbox(callSiteArray[994].callCurrent((GroovyObject)this, contentType)) || !(DefaultTypeTransformation.booleanUnbox(callSiteArray[995].callStatic(StringUtils.class, callSiteArray[996].call(contentType), CHARSET)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[997].callStatic(StringUtils.class, callSiteArray[998].call(contentType), APPLICATION_JSON))));
    }

    private boolean isApplicationJsonContentTypeWithDefaultCharsetDefined(Object contentType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[999].callStatic(StringUtils.class, callSiteArray[1000].call(callSiteArray[1001].call(contentType)), APPLICATION_JSON))) {
            return false;
        }
        Object encoderConfig = callSiteArray[1002].call(callSiteArray[1003].callCurrent(this));
        Object contentTypeOrNull = callSiteArray[1004].call(encoderConfig, callSiteArray[1005].call(contentType));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareNotEqual(contentTypeOrNull, null) && (ScriptBytecodeAdapter.compareNotEqual(contentTypeOrNull, callSiteArray[1006].call(callSiteArray[1007].callGetProperty(StandardCharsets.class))) || DefaultTypeTransformation.booleanUnbox(callSiteArray[1008].call(encoderConfig)));
        }
        return ScriptBytecodeAdapter.compareNotEqual(contentTypeOrNull, null) && (ScriptBytecodeAdapter.compareNotEqual(contentTypeOrNull, callSiteArray[1009].call(callSiteArray[1010].callGetProperty(StandardCharsets.class))) || DefaultTypeTransformation.booleanUnbox(callSiteArray[1011].call(encoderConfig)));
    }

    private String getTargetURI(String path) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object uri = null;
        Object pathHasScheme = callSiteArray[1012].callStatic(PathSupport.class, path);
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(pathHasScheme)) {
                Object object;
                Object url = callSiteArray[1013].callConstructor(URL.class, path);
                uri = object = callSiteArray[1014].callCurrent((GroovyObject)this, url);
            } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[1015].callStatic(PathSupport.class, this.baseUri))) {
                Object object;
                Object baseUriAsUrl = callSiteArray[1016].callConstructor(URL.class, this.baseUri);
                uri = object = callSiteArray[1017].callCurrent((GroovyObject)this, baseUriAsUrl);
            } else if (ScriptBytecodeAdapter.compareNotEqual(this.port, callSiteArray[1018].callGetProperty(RestAssured.class))) {
                GStringImpl gStringImpl = new GStringImpl(new Object[]{this.baseUri, this.port}, new String[]{"", ":", ""});
                uri = gStringImpl;
            } else {
                GStringImpl gStringImpl = new GStringImpl(new Object[]{this.baseUri}, new String[]{"", ""});
                uri = gStringImpl;
            }
        } else if (DefaultTypeTransformation.booleanUnbox(pathHasScheme)) {
            Object object;
            Object url = callSiteArray[1019].callConstructor(URL.class, path);
            uri = object = callSiteArray[1020].callCurrent((GroovyObject)this, url);
        } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[1021].callStatic(PathSupport.class, this.baseUri))) {
            Object object;
            Object baseUriAsUrl = callSiteArray[1022].callConstructor(URL.class, this.baseUri);
            uri = object = callSiteArray[1023].callCurrent((GroovyObject)this, baseUriAsUrl);
        } else if (ScriptBytecodeAdapter.compareNotEqual(this.port, callSiteArray[1024].callGetProperty(RestAssured.class))) {
            GStringImpl gStringImpl = new GStringImpl(new Object[]{this.baseUri, this.port}, new String[]{"", ":", ""});
            uri = gStringImpl;
        } else {
            GStringImpl gStringImpl = new GStringImpl(new Object[]{this.baseUri}, new String[]{"", ""});
            uri = gStringImpl;
        }
        return (String)ScriptBytecodeAdapter.castToType(uri, String.class);
    }

    private String getTargetUriFromUrl(URL url) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object protocol = callSiteArray[1025].call(url);
        boolean useDefaultHttps = false;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(this.port, callSiteArray[1026].callGetProperty(RestAssured.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[1027].call(protocol, "https"))) {
                boolean bl;
                useDefaultHttps = bl = true;
            }
        } else if (ScriptBytecodeAdapter.compareEqual(this.port, callSiteArray[1028].callGetProperty(RestAssured.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[1029].call(protocol, "https"))) {
            boolean bl;
            useDefaultHttps = bl = true;
        }
        Object builder = callSiteArray[1030].call(callSiteArray[1031].call(callSiteArray[1032].callConstructor(StringBuilder.class, protocol), "://"), callSiteArray[1033].call(url));
        Boolean hasSpecifiedPortExplicitly = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            boolean bl = ScriptBytecodeAdapter.compareNotEqual(this.port, callSiteArray[1034].callGetProperty(RestAssured.class));
            hasSpecifiedPortExplicitly = bl;
        } else {
            boolean bl = ScriptBytecodeAdapter.compareNotEqual(this.port, callSiteArray[1035].callGetProperty(RestAssured.class));
            hasSpecifiedPortExplicitly = bl;
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1036].callCurrent((GroovyObject)this, url)) && !useDefaultHttps) {
                if (DefaultTypeTransformation.booleanUnbox(hasSpecifiedPortExplicitly)) {
                    callSiteArray[1037].call(builder, ":");
                    callSiteArray[1038].call(builder, this.port);
                } else if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1039].callStatic(PathSupport.class, callSiteArray[1040].call(url))) || DefaultTypeTransformation.booleanUnbox(callSiteArray[1041].callCurrent((GroovyObject)this, url))) {
                    callSiteArray[1042].call(builder, ":");
                    callSiteArray[1043].call(builder, DEFAULT_HTTP_TEST_PORT);
                }
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1044].callCurrent((GroovyObject)this, url)) && !useDefaultHttps) {
            if (DefaultTypeTransformation.booleanUnbox(hasSpecifiedPortExplicitly)) {
                callSiteArray[1045].call(builder, ":");
                callSiteArray[1046].call(builder, this.port);
            } else if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1047].callStatic(PathSupport.class, callSiteArray[1048].call(url))) || DefaultTypeTransformation.booleanUnbox(callSiteArray[1049].callCurrent((GroovyObject)this, url))) {
                callSiteArray[1050].call(builder, ":");
                callSiteArray[1051].call(builder, DEFAULT_HTTP_TEST_PORT);
            }
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1052].call(builder), String.class);
    }

    private boolean hasAuthorityEqualToLocalhost(Object uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(callSiteArray[1053].call(callSiteArray[1054].call(callSiteArray[1055].call(uri)), LOCALHOST));
    }

    private boolean hasPortDefined(Object uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[1056].call(uri), -1);
        }
        return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[1057].call(uri), -1);
    }

    private Object serializeIfNeeded(Object object) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return callSiteArray[1058].callCurrent(this, object, callSiteArray[1059].callGroovyObjectGetProperty(this));
    }

    private Object serializeIfNeeded(Object object, Object contentType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(callSiteArray[1060].callStatic(SerializationSupport.class, object)) ? callSiteArray[1061].call((Object)ObjectMapping.class, ArrayUtil.createArray(object, contentType, callSiteArray[1062].callCurrent((GroovyObject)this, contentType), null, callSiteArray[1063].callCurrent(this), callSiteArray[1064].call(callSiteArray[1065].callCurrent(this)))) : callSiteArray[1066].call(object);
    }

    private Object applyPathParamsAndSendRequest(String method, String path, Object ... unnamedPathParams) {
        RestAssuredConfig restAssuredConfig;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[1067].callStatic(AssertParameter.class, path, "path");
        callSiteArray[1068].callStatic(AssertParameter.class, callSiteArray[1069].callStatic(StringUtils.class, method), "Method");
        callSiteArray[1070].callStatic(AssertParameter.class, unnamedPathParams, "Path params");
        Object object = callSiteArray[1071].call(callSiteArray[1072].call(method));
        this.method = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        String string = path;
        this.path = (String)ScriptBytecodeAdapter.castToType(string, String.class);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(unnamedPathParams, null)) {
                List nullParamIndices = ScriptBytecodeAdapter.createList(new Object[0]);
                int i = 0;
                while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[1073].callGetProperty(unnamedPathParams))) {
                    if (ScriptBytecodeAdapter.compareEqual(callSiteArray[1074].call((Object)unnamedPathParams, i), null)) {
                        callSiteArray[1075].call((Object)nullParamIndices, i);
                    }
                    int n = i;
                    i = DefaultTypeTransformation.intUnbox(callSiteArray[1076].call(n));
                }
                if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1077].call(nullParamIndices))) {
                    Boolean sizeOne = ScriptBytecodeAdapter.compareEqual(callSiteArray[1078].call(nullParamIndices), 1);
                    throw (Throwable)callSiteArray[1079].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{DefaultTypeTransformation.booleanUnbox(sizeOne) ? "" : "s", DefaultTypeTransformation.booleanUnbox(sizeOne) ? "index" : "indices", callSiteArray[1080].call((Object)nullParamIndices, ","), DefaultTypeTransformation.booleanUnbox(sizeOne) ? "is" : "are"}, new String[]{"Unnamed path parameter cannot be null (path parameter", " at ", " ", " ", " null)"}));
                }
                callSiteArray[1081].callCurrent((GroovyObject)this, (Object)unnamedPathParams);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(unnamedPathParams, null)) {
            List nullParamIndices = ScriptBytecodeAdapter.createList(new Object[0]);
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[1082].callGetProperty(unnamedPathParams))) {
                if (ScriptBytecodeAdapter.compareEqual(BytecodeInterface8.objectArrayGet(unnamedPathParams, i), null)) {
                    callSiteArray[1083].call((Object)nullParamIndices, i);
                }
                int n = i;
                int cfr_ignored_0 = n + 1;
            }
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1084].call(nullParamIndices))) {
                Boolean sizeOne = ScriptBytecodeAdapter.compareEqual(callSiteArray[1085].call(nullParamIndices), 1);
                throw (Throwable)callSiteArray[1086].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{DefaultTypeTransformation.booleanUnbox(sizeOne) ? "" : "s", DefaultTypeTransformation.booleanUnbox(sizeOne) ? "index" : "indices", callSiteArray[1087].call((Object)nullParamIndices, ","), DefaultTypeTransformation.booleanUnbox(sizeOne) ? "is" : "are"}, new String[]{"Unnamed path parameter cannot be null (path parameter", " at ", " ", " ", " null)"}));
            }
            callSiteArray[1088].callCurrent((GroovyObject)this, (Object)unnamedPathParams);
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (this.authenticationScheme instanceof NoAuthScheme && !(this.defaultAuthScheme instanceof NoAuthScheme)) {
                AuthenticationScheme authenticationScheme;
                this.authenticationScheme = authenticationScheme = this.defaultAuthScheme;
            }
        } else if (this.authenticationScheme instanceof NoAuthScheme && !(this.defaultAuthScheme instanceof NoAuthScheme)) {
            AuthenticationScheme authenticationScheme;
            this.authenticationScheme = authenticationScheme = this.defaultAuthScheme;
        }
        if (this.authenticationScheme instanceof FormAuthScheme) {
            FormAuthScheme formAuthScheme = (FormAuthScheme)ScriptBytecodeAdapter.asType(this.authenticationScheme, FormAuthScheme.class);
            callSiteArray[1089].call(this.filters, new GeneratedClosure(this, this){
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure29.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure29.$getCallSiteArray();
                    return callSiteArray[0].call(AuthFilter.class, callSiteArray[1].call(it));
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure29.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _applyPathParamsAndSendRequest_closure29.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure29.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _applyPathParamsAndSendRequest_closure29.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "isAssignableFrom";
                    stringArray[1] = "getClass";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[2];
                    _applyPathParamsAndSendRequest_closure29.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_applyPathParamsAndSendRequest_closure29.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _applyPathParamsAndSendRequest_closure29.$createCallSiteArray();
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
            callSiteArray[1090].call(this.filters, 0, callSiteArray[1091].callConstructor(FormAuthFilter.class, ScriptBytecodeAdapter.createMap(new Object[]{"userName", callSiteArray[1092].callGetProperty(formAuthScheme), "password", callSiteArray[1093].callGetProperty(formAuthScheme), "formAuthConfig", callSiteArray[1094].callGetProperty(formAuthScheme), "sessionConfig", callSiteArray[1095].callCurrent(this), "csrfConfig", callSiteArray[1096].callCurrent(this)})));
        }
        if (this.addCsrfFilter) {
            callSiteArray[1097].call(this.filters, callSiteArray[1098].callConstructor(CsrfFilter.class, ScriptBytecodeAdapter.createMap(new Object[]{"csrfConfig", callSiteArray[1099].callGetProperty(callSiteArray[1100].callCurrent(this))})));
        }
        Object logConfig = callSiteArray[1101].call(callSiteArray[1102].callCurrent(this));
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[1103].call(logConfig))) {
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1104].call(this.filters, new GeneratedClosure(this, this){
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure30.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure30.$getCallSiteArray();
                    return callSiteArray[0].call(RequestLoggingFilter.class, callSiteArray[1].call(it));
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure30.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _applyPathParamsAndSendRequest_closure30.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure30.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _applyPathParamsAndSendRequest_closure30.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "isAssignableFrom";
                    stringArray[1] = "getClass";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[2];
                    _applyPathParamsAndSendRequest_closure30.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_applyPathParamsAndSendRequest_closure30.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _applyPathParamsAndSendRequest_closure30.$createCallSiteArray();
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
            }))) {
                callSiteArray[1105].call(callSiteArray[1106].callCurrent(this), callSiteArray[1107].call(logConfig), callSiteArray[1108].call(logConfig));
            }
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1109].call(this.filters, new GeneratedClosure(this, this){
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure31.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure31.$getCallSiteArray();
                    return callSiteArray[0].call(ResponseLoggingFilter.class, callSiteArray[1].call(it));
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure31.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _applyPathParamsAndSendRequest_closure31.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure31.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _applyPathParamsAndSendRequest_closure31.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "isAssignableFrom";
                    stringArray[1] = "getClass";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[2];
                    _applyPathParamsAndSendRequest_closure31.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_applyPathParamsAndSendRequest_closure31.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _applyPathParamsAndSendRequest_closure31.$createCallSiteArray();
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
            }))) {
                callSiteArray[1110].call(callSiteArray[1111].call(this.responseSpecification), callSiteArray[1112].call(logConfig), callSiteArray[1113].call(logConfig));
            }
        }
        Object object2 = callSiteArray[1114].callGroovyObjectGetProperty(this);
        RestAssuredConfig restAssuredConfig2 = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object2, RestAssuredConfig.class);
        this.restAssuredConfig = restAssuredConfig = DefaultTypeTransformation.booleanUnbox(object2) ? restAssuredConfig2 : (RestAssuredConfig)ScriptBytecodeAdapter.castToType(callSiteArray[1115].callConstructor(RestAssuredConfig.class), RestAssuredConfig.class);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1116].call(this.filters, new GeneratedClosure(this, this){
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure32.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure32.$getCallSiteArray();
                    return callSiteArray[0].call(ResponseLoggingFilter.class, callSiteArray[1].call(it));
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure32.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _applyPathParamsAndSendRequest_closure32.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure32.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _applyPathParamsAndSendRequest_closure32.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "isAssignableFrom";
                    stringArray[1] = "getClass";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[2];
                    _applyPathParamsAndSendRequest_closure32.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_applyPathParamsAndSendRequest_closure32.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _applyPathParamsAndSendRequest_closure32.$createCallSiteArray();
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
            })) && DefaultTypeTransformation.booleanUnbox(callSiteArray[1117].callSafe(this.responseSpecification))) {
                callSiteArray[1118].call(this.filters, callSiteArray[1119].callConstructor(ResponseLoggingFilter.class, callSiteArray[1120].call(this.responseSpecification), callSiteArray[1121].call(logConfig), callSiteArray[1122].call(logConfig)));
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1123].call(this.filters, new /* invalid duplicate definition of identical inner class */)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[1124].callSafe(this.responseSpecification))) {
            callSiteArray[1125].call(this.filters, callSiteArray[1126].callConstructor(ResponseLoggingFilter.class, callSiteArray[1127].call(this.responseSpecification), callSiteArray[1128].call(logConfig), callSiteArray[1129].call(logConfig)));
        }
        Object object3 = callSiteArray[1130].call(this.filters, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure33.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object f1, Object f2) {
                CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure33.$getCallSiteArray();
                return ScriptBytecodeAdapter.compareTo(callSiteArray[0].callStatic(RequestSpecificationImpl.class, f1), callSiteArray[1].callStatic(RequestSpecificationImpl.class, f2));
            }

            public Object call(Object f1, Object f2) {
                CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure33.$getCallSiteArray();
                return callSiteArray[2].callCurrent(this, f1, f2);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _applyPathParamsAndSendRequest_closure33.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure33.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _applyPathParamsAndSendRequest_closure33.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "getFilterOrder";
                stringArray[1] = "getFilterOrder";
                stringArray[2] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _applyPathParamsAndSendRequest_closure33.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_applyPathParamsAndSendRequest_closure33.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _applyPathParamsAndSendRequest_closure33.$createCallSiteArray();
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
        this.filters = (List)ScriptBytecodeAdapter.castToType(object3, List.class);
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1131].call(ScriptBytecodeAdapter.invokeMethod0SpreadSafe(RequestSpecificationImpl.class, this.filters, "getClass"), new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure34.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure34.$getCallSiteArray();
                return callSiteArray[0].call(TimingFilter.class, it);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure34.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _applyPathParamsAndSendRequest_closure34.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _applyPathParamsAndSendRequest_closure34.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _applyPathParamsAndSendRequest_closure34.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "isAssignableFrom";
                return new CallSiteArray(_applyPathParamsAndSendRequest_closure34.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _applyPathParamsAndSendRequest_closure34.$createCallSiteArray();
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
        }))) {
            callSiteArray[1132].call(this.filters, callSiteArray[1133].callConstructor(TimingFilter.class));
        }
        callSiteArray[1134].call(this.filters, callSiteArray[1135].callConstructor(SendRequestFilter.class));
        Object ctx = callSiteArray[1136].callCurrent(this, callSiteArray[1137].callGetProperty(this.responseSpecification), callSiteArray[1138].call(this.filters), ScriptBytecodeAdapter.createMap(new Object[0]));
        Object object4 = callSiteArray[1139].call(callSiteArray[1140].callCurrent(this));
        this.httpClient = (AbstractHttpClient)ScriptBytecodeAdapter.castToType(object4, AbstractHttpClient.class);
        Object response = callSiteArray[1141].call(ctx, this, this.responseSpecification);
        callSiteArray[1142].call(callSiteArray[1143].callGetProperty(this.responseSpecification), response);
        return response;
    }

    private Object applyPathParamsAndSendRequest(Method method, String path, Object ... unnamedPathParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return callSiteArray[1144].callCurrent(this, callSiteArray[1145].call(callSiteArray[1146].callStatic(AssertParameter.class, (Object)method, Method.class)), path, unnamedPathParams);
    }

    public void buildUnnamedPathParameterTuples(Object ... unnamedPathParameterValues) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(unnamedPathParameterValues, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[1147].callGetProperty(unnamedPathParameterValues), 0)) {
                Object object = callSiteArray[1148].callConstructor(ArrayList.class);
                this.unnamedPathParamsTuples = (List)ScriptBytecodeAdapter.castToType(object, List.class);
            } else {
                Object keys = callSiteArray[1149].callCurrent(this);
                List list = (List)ScriptBytecodeAdapter.castToType(callSiteArray[1150].callConstructor(ArrayList.class), List.class);
                int i = 0;
                while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[1151].callGetProperty(unnamedPathParameterValues))) {
                    Object val = callSiteArray[1152].callCurrent((GroovyObject)this, callSiteArray[1153].call((Object)unnamedPathParameterValues, i));
                    Object key = ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[1154].call(keys)) ? callSiteArray[1155].call(keys, i) : null;
                    callSiteArray[1156].call((Object)list, callSiteArray[1157].callConstructor(Tuple2.class, key, val));
                    int n = i;
                    i = DefaultTypeTransformation.intUnbox(callSiteArray[1158].call(n));
                }
                List list2 = list;
                this.unnamedPathParamsTuples = (List)ScriptBytecodeAdapter.castToType(list2, List.class);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(unnamedPathParameterValues, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[1159].callGetProperty(unnamedPathParameterValues), 0)) {
            Object object = callSiteArray[1160].callConstructor(ArrayList.class);
            this.unnamedPathParamsTuples = (List)ScriptBytecodeAdapter.castToType(object, List.class);
        } else {
            Object keys = callSiteArray[1161].callCurrent(this);
            List list = (List)ScriptBytecodeAdapter.castToType(callSiteArray[1162].callConstructor(ArrayList.class), List.class);
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[1163].callGetProperty(unnamedPathParameterValues))) {
                Object val = callSiteArray[1164].callCurrent((GroovyObject)this, BytecodeInterface8.objectArrayGet(unnamedPathParameterValues, i));
                Object key = ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[1165].call(keys)) ? callSiteArray[1166].call(keys, i) : null;
                callSiteArray[1167].call((Object)list, callSiteArray[1168].callConstructor(Tuple2.class, key, val));
                int n = i;
                int cfr_ignored_0 = n + 1;
            }
            List list3 = list;
            this.unnamedPathParamsTuples = (List)ScriptBytecodeAdapter.castToType(list3, List.class);
        }
    }

    public String partiallyApplyPathParams(String path, boolean encodePath, List<String> unnamedPathParams) {
        Object object;
        Object object2;
        Object object3;
        Reference<List<String>> unnamedPathParams2 = new Reference<List<String>>(unnamedPathParams);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object object4 = callSiteArray[1169].callSafe(unnamedPathParams2.get());
        Reference<Object> unnamedPathParamSize = new Reference<Object>(DefaultTypeTransformation.booleanUnbox(object4) ? object4 : Integer.valueOf(0));
        Object host = callSiteArray[1170].callCurrent((GroovyObject)this, path);
        Object targetPath = callSiteArray[1171].callCurrent((GroovyObject)this, path);
        Object pathWithoutQueryParams = callSiteArray[1172].callStatic(StringUtils.class, targetPath, "?");
        Object shouldAppendSlashAfterEncoding = callSiteArray[1173].call(pathWithoutQueryParams, "/");
        Object queryParams = callSiteArray[1174].callStatic(StringUtils.class, path, "?");
        Reference<Integer> numberOfUnnamedPathParametersUsed = new Reference<Integer>(0);
        Reference<Object> pathParamNameUsageCount = new Reference<Object>(callSiteArray[1175].call((Object)ScriptBytecodeAdapter.createMap(new Object[0]), new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure35.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure35.$getCallSiteArray();
                return 0;
            }

            public Object doCall() {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure35.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _partiallyApplyPathParams_closure35.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure35.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _partiallyApplyPathParams_closure35.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[]{};
                return new CallSiteArray(_partiallyApplyPathParams_closure35.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _partiallyApplyPathParams_closure35.$createCallSiteArray();
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
        }));
        Object pathTemplate = ScriptBytecodeAdapter.bitwiseNegate(".*\\{\\w+\\}.*");
        Boolean hasPathParameterWithDoubleSlash = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            boolean bl = ScriptBytecodeAdapter.compareNotEqual(callSiteArray[1176].callStatic(StringUtils.class, pathWithoutQueryParams, DOUBLE_SLASH), -1);
            hasPathParameterWithDoubleSlash = bl;
        } else {
            boolean bl = ScriptBytecodeAdapter.compareNotEqual(callSiteArray[1177].callStatic(StringUtils.class, pathWithoutQueryParams, DOUBLE_SLASH), -1);
            hasPathParameterWithDoubleSlash = bl;
        }
        Object tempParams = null;
        tempParams = DefaultTypeTransformation.booleanUnbox(hasPathParameterWithDoubleSlash) ? (object3 = callSiteArray[1178].callStatic(StringUtils.class, pathWithoutQueryParams, DOUBLE_SLASH, "RA_double_slash__")) : (object2 = pathWithoutQueryParams);
        GeneratedClosure pathParamFiller = new GeneratedClosure(this, this, pathParamNameUsageCount, unnamedPathParamSize, unnamedPathParams2, numberOfUnnamedPathParametersUsed){
            private /* synthetic */ Reference pathParamNameUsageCount;
            private /* synthetic */ Reference unnamedPathParamSize;
            private /* synthetic */ Reference unnamedPathParams;
            private /* synthetic */ Reference numberOfUnnamedPathParametersUsed;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure36.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.pathParamNameUsageCount = reference4 = pathParamNameUsageCount;
                this.unnamedPathParamSize = reference3 = unnamedPathParamSize;
                this.unnamedPathParams = reference2 = unnamedPathParams;
                this.numberOfUnnamedPathParametersUsed = reference = numberOfUnnamedPathParametersUsed;
            }

            public Object doCall(String separator, boolean performEncode, String acc, String subresource) {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure36.$getCallSiteArray();
                Object indexOfStartBracket = null;
                Object indexOfEndBracket = 0;
                while (true) {
                    Object object;
                    Object object2;
                    indexOfStartBracket = object2 = callSiteArray[0].call(subresource, callSiteArray[1].callGetProperty(RequestSpecificationImpl.class), indexOfEndBracket);
                    if (!ScriptBytecodeAdapter.compareGreaterThanEqual(object2, 0)) break;
                    indexOfEndBracket = object = callSiteArray[2].call(subresource, callSiteArray[3].callGetProperty(RequestSpecificationImpl.class), indexOfStartBracket);
                    if (!(ScriptBytecodeAdapter.compareGreaterThanEqual(indexOfStartBracket, 0) && ScriptBytecodeAdapter.compareGreaterThanEqual(indexOfEndBracket, 0) && ScriptBytecodeAdapter.compareGreaterThanEqual(callSiteArray[4].call(subresource), 3))) continue;
                    Object pathParamValue = null;
                    Object pathParamName = callSiteArray[5].call(subresource, callSiteArray[6].call(indexOfStartBracket, 1), indexOfEndBracket);
                    Object value = callSiteArray[7].callCurrent(this, pathParamName, this.pathParamNameUsageCount.get());
                    if (ScriptBytecodeAdapter.compareEqual(value, null) && ScriptBytecodeAdapter.compareLessThan(this.numberOfUnnamedPathParametersUsed.get(), this.unnamedPathParamSize.get()) && ScriptBytecodeAdapter.compareNotEqual(callSiteArray[8].call(callSiteArray[9].call(this.unnamedPathParams.get(), this.numberOfUnnamedPathParametersUsed.get())), null)) {
                        Object object3;
                        pathParamValue = object3 = callSiteArray[10].call(callSiteArray[11].call(this.unnamedPathParams.get(), this.numberOfUnnamedPathParametersUsed.get()));
                        this.numberOfUnnamedPathParametersUsed.set((Integer)ScriptBytecodeAdapter.castToType(callSiteArray[12].call(this.numberOfUnnamedPathParametersUsed.get(), 1), Integer.class));
                    } else {
                        Object object4;
                        pathParamValue = object4 = ScriptBytecodeAdapter.compareEqual(value, null) ? callSiteArray[13].call(callSiteArray[14].call(callSiteArray[15].callGetProperty(RequestSpecificationImpl.class), pathParamName), callSiteArray[16].callGetProperty(RequestSpecificationImpl.class)) : value;
                    }
                    Object pathToPrepend = "";
                    if (ScriptBytecodeAdapter.compareNotEqual(indexOfStartBracket, 0)) {
                        Object object5;
                        pathToPrepend = object5 = callSiteArray[17].call(subresource, 0, indexOfStartBracket);
                    }
                    Object pathToAppend = "";
                    if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[18].call(subresource), indexOfEndBracket)) {
                        Object object6;
                        pathToAppend = object6 = callSiteArray[19].call(subresource, callSiteArray[20].call(indexOfEndBracket, 1), callSiteArray[21].call(subresource));
                    }
                    Object lengthOfTemplate = callSiteArray[22].call(callSiteArray[23].callStatic(StringUtils.class, pathParamName), 2);
                    Object lengthOfValue = callSiteArray[24].callStatic(StringUtils.class, pathParamValue);
                    if (ScriptBytecodeAdapter.compareNotEqual(lengthOfTemplate, lengthOfValue)) {
                        indexOfEndBracket = ScriptBytecodeAdapter.compareGreaterThan(lengthOfTemplate, lengthOfValue) ? callSiteArray[25].call(indexOfEndBracket, callSiteArray[26].call(lengthOfTemplate, lengthOfValue)) : callSiteArray[27].call(indexOfEndBracket, callSiteArray[28].call(lengthOfValue, lengthOfTemplate));
                    }
                    Object object7 = callSiteArray[29].call(callSiteArray[30].call(pathToPrepend, pathParamValue), pathToAppend);
                    subresource = (String)ScriptBytecodeAdapter.castToType(object7, String.class);
                }
                return callSiteArray[31].call(callSiteArray[32].callStatic(String.class, new GStringImpl(new Object[]{separator}, new String[]{"%s", "%s"}), acc, performEncode ? callSiteArray[33].callCurrent(this, subresource, callSiteArray[34].callGetProperty(EncodingTarget.class)) : subresource));
            }

            public Object call(String separator, boolean performEncode, String acc, String subresource) {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure36.$getCallSiteArray();
                return callSiteArray[35].callCurrent(this, separator, performEncode, acc, subresource);
            }

            public Object getPathParamNameUsageCount() {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure36.$getCallSiteArray();
                return this.pathParamNameUsageCount.get();
            }

            public Object getUnnamedPathParamSize() {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure36.$getCallSiteArray();
                return this.unnamedPathParamSize.get();
            }

            public List getUnnamedPathParams() {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure36.$getCallSiteArray();
                return (List)ScriptBytecodeAdapter.castToType(this.unnamedPathParams.get(), List.class);
            }

            public Integer getNumberOfUnnamedPathParametersUsed() {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure36.$getCallSiteArray();
                return (Integer)ScriptBytecodeAdapter.castToType(this.numberOfUnnamedPathParametersUsed.get(), Integer.class);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _partiallyApplyPathParams_closure36.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _partiallyApplyPathParams_closure36.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _partiallyApplyPathParams_closure36.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "indexOf";
                stringArray[1] = "TEMPLATE_START";
                stringArray[2] = "indexOf";
                stringArray[3] = "TEMPLATE_END";
                stringArray[4] = "length";
                stringArray[5] = "substring";
                stringArray[6] = "plus";
                stringArray[7] = "findNamedPathParamValue";
                stringArray[8] = "toString";
                stringArray[9] = "getAt";
                stringArray[10] = "toString";
                stringArray[11] = "getAt";
                stringArray[12] = "plus";
                stringArray[13] = "plus";
                stringArray[14] = "plus";
                stringArray[15] = "TEMPLATE_START";
                stringArray[16] = "TEMPLATE_END";
                stringArray[17] = "substring";
                stringArray[18] = "length";
                stringArray[19] = "substring";
                stringArray[20] = "plus";
                stringArray[21] = "length";
                stringArray[22] = "plus";
                stringArray[23] = "length";
                stringArray[24] = "length";
                stringArray[25] = "minus";
                stringArray[26] = "minus";
                stringArray[27] = "plus";
                stringArray[28] = "minus";
                stringArray[29] = "plus";
                stringArray[30] = "plus";
                stringArray[31] = "toString";
                stringArray[32] = "format";
                stringArray[33] = "encode";
                stringArray[34] = "QUERY";
                stringArray[35] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[36];
                _partiallyApplyPathParams_closure36.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_partiallyApplyPathParams_closure36.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _partiallyApplyPathParams_closure36.$createCallSiteArray();
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
        };
        pathWithoutQueryParams = object = callSiteArray[1179].call(callSiteArray[1180].callStatic(StringUtils.class, tempParams, "/"), "", callSiteArray[1181].call(pathParamFiller, "/", encodePath));
        if (DefaultTypeTransformation.booleanUnbox(hasPathParameterWithDoubleSlash)) {
            Object object5;
            pathWithoutQueryParams = object5 = callSiteArray[1182].callStatic(StringUtils.class, pathWithoutQueryParams, "RA_double_slash__", callSiteArray[1183].callCurrent(this, DOUBLE_SLASH, callSiteArray[1184].callGetProperty(EncodingTarget.class)));
        }
        if (DefaultTypeTransformation.booleanUnbox(shouldAppendSlashAfterEncoding)) {
            pathWithoutQueryParams = callSiteArray[1185].call(pathWithoutQueryParams, "/");
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[1186].call(queryParams, pathTemplate))) {
            Object object6;
            queryParams = object6 = callSiteArray[1187].call(callSiteArray[1188].call(callSiteArray[1189].callStatic(StringUtils.class, queryParams, "&"), "", callSiteArray[1190].call(pathParamFiller, "&", false)), 1);
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1191].call(host, DefaultTypeTransformation.booleanUnbox(callSiteArray[1192].callStatic(StringUtils.class, queryParams)) ? pathWithoutQueryParams : callSiteArray[1193].call(callSiteArray[1194].call(pathWithoutQueryParams, "?"), queryParams)), String.class);
    }

    private String findNamedPathParamValue(String pathParamName, Object pathParamNameUsageCount) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object pathParamValues = callSiteArray[1195].call(this.namedPathParameters, pathParamName);
        Object pathParamValue = null;
        if (pathParamValues instanceof Collection) {
            Object object;
            Object pathParamCount = callSiteArray[1196].call(pathParamNameUsageCount, pathParamName);
            Object object2 = pathParamCount;
            pathParamCount = callSiteArray[1197].call(object2);
            Object object3 = object2;
            callSiteArray[1198].call(pathParamNameUsageCount, pathParamName, object3);
            pathParamValue = object = callSiteArray[1199].call(pathParamValues, pathParamCount);
        } else {
            Object object;
            pathParamValue = object = pathParamValues;
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1200].callSafe(pathParamValue), String.class);
    }

    private String createFormParamBody(Map<String, Object> formParams) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        StringBuilder body = (StringBuilder)ScriptBytecodeAdapter.castToType(callSiteArray[1201].callConstructor(StringBuilder.class), StringBuilder.class);
        Map.Entry entry = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(callSiteArray[1202].call(callSiteArray[1203].call(formParams)), Iterator.class);
        while (iterator.hasNext()) {
            entry = (Map.Entry)ScriptBytecodeAdapter.castToType(iterator.next(), Map.Entry.class);
            callSiteArray[1204].call((Object)body, callSiteArray[1205].callCurrent(this, callSiteArray[1206].call(entry), callSiteArray[1207].callGetProperty(EncodingTarget.class)));
            if (!(callSiteArray[1208].call(entry) instanceof NoParameterValue)) {
                callSiteArray[1209].call(callSiteArray[1210].call((Object)body, "="), callSiteArray[1211].callCurrent((GroovyObject)this, entry));
            }
            callSiteArray[1212].call((Object)body, "&");
        }
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1213].call(formParams))) {
            callSiteArray[1214].call((Object)body, callSiteArray[1215].call(callSiteArray[1216].call(body), 1));
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1217].call(body), String.class);
    }

    private String encode(Object string, EncodingTarget encodingType) {
        Object object;
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        string = object = callSiteArray[1218].call(string);
        if (this.urlEncodingEnabled) {
            Object charset = null;
            if (ScriptBytecodeAdapter.compareEqual(encodingType, callSiteArray[1219].callGetProperty(EncodingTarget.class))) {
                Object object2;
                charset = object2 = callSiteArray[1220].call(callSiteArray[1221].callCurrent(this));
                Object contentType = callSiteArray[1222].call(callSiteArray[1223].callGroovyObjectGetProperty(this), CONTENT_TYPE);
                if (contentType instanceof String) {
                    Object tempCharset = callSiteArray[1224].call(CharsetExtractor.class, ScriptBytecodeAdapter.createPojoWrapper((String)ScriptBytecodeAdapter.asType(contentType, String.class), String.class));
                    if (ScriptBytecodeAdapter.compareNotEqual(tempCharset, null)) {
                        Object object3;
                        charset = object3 = tempCharset;
                    } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[1225].call(callSiteArray[1226].callCurrent(this), ScriptBytecodeAdapter.createPojoWrapper((String)ScriptBytecodeAdapter.asType(contentType, String.class), String.class)))) {
                        Object object4;
                        charset = object4 = callSiteArray[1227].call(callSiteArray[1228].callCurrent(this), ScriptBytecodeAdapter.createPojoWrapper((String)ScriptBytecodeAdapter.asType(contentType, String.class), String.class));
                    }
                }
            } else {
                Object object5;
                charset = object5 = callSiteArray[1229].call(callSiteArray[1230].callCurrent(this));
            }
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1231].call(URIBuilder.class, string, charset), String.class);
        }
        return (String)ScriptBytecodeAdapter.castToType(string, String.class);
    }

    private Object handleMultiValueParamsIfNeeded(Map.Entry<String, Object> entry) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Reference<Object> value = new Reference<Object>(callSiteArray[1232].call(entry));
        if (value.get() instanceof List) {
            Reference<Object> key = new Reference<Object>(callSiteArray[1233].callCurrent(this, callSiteArray[1234].call(entry), callSiteArray[1235].callGetProperty(EncodingTarget.class)));
            Reference<StringBuilder> multiValueList = new Reference<StringBuilder>((StringBuilder)ScriptBytecodeAdapter.castToType(callSiteArray[1236].callConstructor(StringBuilder.class), StringBuilder.class));
            callSiteArray[1237].call(value.get(), new GeneratedClosure(this, this, multiValueList, value, key){
                private /* synthetic */ Reference multiValueList;
                private /* synthetic */ Reference value;
                private /* synthetic */ Reference key;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    CallSite[] callSiteArray = _handleMultiValueParamsIfNeeded_closure37.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.multiValueList = reference3 = multiValueList;
                    this.value = reference2 = value;
                    this.key = reference = key;
                }

                public Object doCall(Object val, Object index) {
                    CallSite[] callSiteArray = _handleMultiValueParamsIfNeeded_closure37.$getCallSiteArray();
                    callSiteArray[0].call(this.multiValueList.get(), callSiteArray[1].callCurrent(this, callSiteArray[2].call(val), callSiteArray[3].callGetProperty(EncodingTarget.class)));
                    if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                        if (ScriptBytecodeAdapter.compareNotEqual(index, callSiteArray[4].call(callSiteArray[5].call(this.value.get()), 1))) {
                            return callSiteArray[6].call(callSiteArray[7].call(callSiteArray[8].call(this.multiValueList.get(), "&"), this.key.get()), "=");
                        }
                        return null;
                    }
                    if (ScriptBytecodeAdapter.compareNotEqual(index, callSiteArray[9].call(callSiteArray[10].call(this.value.get()), 1))) {
                        return callSiteArray[11].call(callSiteArray[12].call(callSiteArray[13].call(this.multiValueList.get(), "&"), this.key.get()), "=");
                    }
                    return null;
                }

                public Object call(Object val, Object index) {
                    CallSite[] callSiteArray = _handleMultiValueParamsIfNeeded_closure37.$getCallSiteArray();
                    return callSiteArray[14].callCurrent(this, val, index);
                }

                public StringBuilder getMultiValueList() {
                    CallSite[] callSiteArray = _handleMultiValueParamsIfNeeded_closure37.$getCallSiteArray();
                    return (StringBuilder)ScriptBytecodeAdapter.castToType(this.multiValueList.get(), StringBuilder.class);
                }

                public Object getValue() {
                    CallSite[] callSiteArray = _handleMultiValueParamsIfNeeded_closure37.$getCallSiteArray();
                    return this.value.get();
                }

                public Object getKey() {
                    CallSite[] callSiteArray = _handleMultiValueParamsIfNeeded_closure37.$getCallSiteArray();
                    return this.key.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _handleMultiValueParamsIfNeeded_closure37.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _handleMultiValueParamsIfNeeded_closure37.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _handleMultiValueParamsIfNeeded_closure37.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "append";
                    stringArray[1] = "encode";
                    stringArray[2] = "toString";
                    stringArray[3] = "BODY";
                    stringArray[4] = "minus";
                    stringArray[5] = "size";
                    stringArray[6] = "append";
                    stringArray[7] = "append";
                    stringArray[8] = "append";
                    stringArray[9] = "minus";
                    stringArray[10] = "size";
                    stringArray[11] = "append";
                    stringArray[12] = "append";
                    stringArray[13] = "append";
                    stringArray[14] = "doCall";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[15];
                    _handleMultiValueParamsIfNeeded_closure37.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_handleMultiValueParamsIfNeeded_closure37.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _handleMultiValueParamsIfNeeded_closure37.$createCallSiteArray();
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
            Object object = callSiteArray[1238].call(multiValueList.get());
            value.set(object);
        } else {
            Object object = callSiteArray[1239].callCurrent(this, value.get(), callSiteArray[1240].callGetProperty(EncodingTarget.class));
            value.set(object);
        }
        return value.get();
    }

    public void setResponseSpecification(ResponseSpecification responseSpecification) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        ResponseSpecification responseSpecification2 = responseSpecification;
        this.responseSpecification = (FilterableResponseSpecification)ScriptBytecodeAdapter.castToType(responseSpecification2, FilterableResponseSpecification.class);
    }

    @Override
    public String getBaseUri() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.baseUri;
    }

    @Override
    public String getBasePath() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.basePath;
    }

    @Override
    public String getDerivedPath() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object uri = callSiteArray[1241].callCurrent(this, this.path, true, callSiteArray[1242].call(this.unnamedPathParamsTuples, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _getDerivedPath_closure38.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _getDerivedPath_closure38.$getCallSiteArray();
                return callSiteArray[0].callGetProperty(it);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _getDerivedPath_closure38.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getDerivedPath_closure38.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _getDerivedPath_closure38.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _getDerivedPath_closure38.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "v2";
                return new CallSiteArray(_getDerivedPath_closure38.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _getDerivedPath_closure38.$createCallSiteArray();
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
        }));
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1243].callStatic(RequestSpecificationImpl.class, uri), String.class);
    }

    @Override
    public String getUserDefinedPath() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1244].call(PathSupport.class, this.path), String.class);
    }

    @Override
    public String getMethod() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.method;
    }

    @Override
    public String getURI() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object uri = callSiteArray[1245].callCurrent(this, this.path, true, callSiteArray[1246].call(this.unnamedPathParamsTuples, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _getURI_closure39.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _getURI_closure39.$getCallSiteArray();
                return callSiteArray[0].callGetProperty(it);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _getURI_closure39.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getURI_closure39.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _getURI_closure39.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _getURI_closure39.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "v2";
                return new CallSiteArray(_getURI_closure39.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _getURI_closure39.$createCallSiteArray();
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
        }));
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1247].callCurrent((GroovyObject)this, uri), String.class);
    }

    @Override
    public int getPort() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object host = callSiteArray[1248].callConstructor(URL.class, callSiteArray[1249].callCurrent((GroovyObject)this, this.path));
        return DefaultTypeTransformation.intUnbox(callSiteArray[1250].call(host));
    }

    @Override
    public Map<String, String> getFormParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[1251].call(Collections.class, this.formParameters), Map.class);
    }

    @Override
    public Map<String, String> getPathParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Reference<Object> namedPathParams = new Reference<Object>(callSiteArray[1252].callCurrent(this));
        Object map = callSiteArray[1253].callConstructor(LinkedHashMap.class, namedPathParams.get());
        callSiteArray[1254].call(map, callSiteArray[1255].call(callSiteArray[1256].callCurrent(this), new GeneratedClosure(this, this, namedPathParams){
            private /* synthetic */ Reference namedPathParams;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _getPathParams_closure40.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.namedPathParams = reference = namedPathParams;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _getPathParams_closure40.$getCallSiteArray();
                return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(callSiteArray[1].call(this.namedPathParams.get()), callSiteArray[2].callGetProperty(it)));
            }

            public Object getNamedPathParams() {
                CallSite[] callSiteArray = _getPathParams_closure40.$getCallSiteArray();
                return this.namedPathParams.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _getPathParams_closure40.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getPathParams_closure40.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _getPathParams_closure40.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _getPathParams_closure40.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "contains";
                stringArray[1] = "keySet";
                stringArray[2] = "key";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _getPathParams_closure40.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_getPathParams_closure40.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _getPathParams_closure40.$createCallSiteArray();
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
        }));
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[1257].call(Collections.class, map), Map.class);
    }

    @Override
    public Map<String, String> getNamedPathParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[1258].call(Collections.class, this.namedPathParameters), Map.class);
    }

    @Override
    public Map<String, String> getUnnamedPathParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object map = callSiteArray[1259].call(callSiteArray[1260].call(this.unnamedPathParamsTuples, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _getUnnamedPathParams_closure41.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _getUnnamedPathParams_closure41.$getCallSiteArray();
                if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                    return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[0].callGetProperty(it), null);
                }
                return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[1].callGetProperty(it), null);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _getUnnamedPathParams_closure41.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getUnnamedPathParams_closure41.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _getUnnamedPathParams_closure41.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _getUnnamedPathParams_closure41.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "v1";
                stringArray[1] = "v1";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _getUnnamedPathParams_closure41.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_getUnnamedPathParams_closure41.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _getUnnamedPathParams_closure41.$createCallSiteArray();
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
        }), ScriptBytecodeAdapter.createMap(new Object[0]), new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _getUnnamedPathParams_closure42.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object m, Object t) {
                CallSite[] callSiteArray = _getUnnamedPathParams_closure42.$getCallSiteArray();
                callSiteArray[0].call(m, callSiteArray[1].callGetProperty(t), callSiteArray[2].callGetProperty(t));
                return m;
            }

            public Object call(Object m, Object t) {
                CallSite[] callSiteArray = _getUnnamedPathParams_closure42.$getCallSiteArray();
                return callSiteArray[3].callCurrent(this, m, t);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getUnnamedPathParams_closure42.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _getUnnamedPathParams_closure42.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _getUnnamedPathParams_closure42.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "putAt";
                stringArray[1] = "v1";
                stringArray[2] = "v2";
                stringArray[3] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[4];
                _getUnnamedPathParams_closure42.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_getUnnamedPathParams_closure42.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _getUnnamedPathParams_closure42.$createCallSiteArray();
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
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[1261].call(Collections.class, map), Map.class);
    }

    @Override
    public List<String> getUnnamedPathParamValues() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1262].call(Collections.class, ScriptBytecodeAdapter.compareEqual(this.unnamedPathParamsTuples, null) ? callSiteArray[1263].call(Collections.class) : callSiteArray[1264].call(callSiteArray[1265].call(this.unnamedPathParamsTuples, new GeneratedClosure(this, this){
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    CallSite[] callSiteArray = _getUnnamedPathParamValues_closure43.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _getUnnamedPathParamValues_closure43.$getCallSiteArray();
                    if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                        return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[0].callGetProperty(it), null);
                    }
                    return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[1].callGetProperty(it), null);
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _getUnnamedPathParamValues_closure43.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _getUnnamedPathParamValues_closure43.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _getUnnamedPathParamValues_closure43.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _getUnnamedPathParamValues_closure43.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "v2";
                    stringArray[1] = "v2";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[2];
                    _getUnnamedPathParamValues_closure43.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_getUnnamedPathParamValues_closure43.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _getUnnamedPathParamValues_closure43.$createCallSiteArray();
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
            }), new GeneratedClosure(this, this){
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    CallSite[] callSiteArray = _getUnnamedPathParamValues_closure44.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _getUnnamedPathParamValues_closure44.$getCallSiteArray();
                    return callSiteArray[0].callGetProperty(it);
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _getUnnamedPathParamValues_closure44.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _getUnnamedPathParamValues_closure44.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _getUnnamedPathParamValues_closure44.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _getUnnamedPathParamValues_closure44.__$swapInit();
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[1];
                    stringArray[0] = "v2";
                    return new CallSiteArray(_getUnnamedPathParamValues_closure44.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _getUnnamedPathParamValues_closure44.$createCallSiteArray();
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
            })), List.class);
        }
        return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1266].call(Collections.class, ScriptBytecodeAdapter.compareEqual(this.unnamedPathParamsTuples, null) ? callSiteArray[1267].call(Collections.class) : callSiteArray[1268].call(callSiteArray[1269].call(this.unnamedPathParamsTuples, new /* invalid duplicate definition of identical inner class */), new /* invalid duplicate definition of identical inner class */)), List.class);
    }

    @Override
    public Map<String, String> getRequestParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[1270].call(Collections.class, this.requestParameters), Map.class);
    }

    @Override
    public Map<String, String> getQueryParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[1271].call(Collections.class, this.queryParameters), Map.class);
    }

    @Override
    public List<MultiPartSpecification> getMultiPartParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1272].call(this.multiParts, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _getMultiPartParams_closure45.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _getMultiPartParams_closure45.$getCallSiteArray();
                return callSiteArray[0].callConstructor(MultiPartSpecificationImpl.class, ScriptBytecodeAdapter.createMap(new Object[]{"content", callSiteArray[1].callGetProperty(it), RequestSpecificationImpl.CHARSET, callSiteArray[2].callGetProperty(it), "fileName", callSiteArray[3].callGetProperty(it), "mimeType", callSiteArray[4].callGetProperty(it), "controlName", callSiteArray[5].callGetProperty(it), "headers", callSiteArray[6].callGetProperty(it)}));
            }

            public Object doCall() {
                CallSite[] callSiteArray = _getMultiPartParams_closure45.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getMultiPartParams_closure45.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _getMultiPartParams_closure45.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _getMultiPartParams_closure45.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "<$constructor$>";
                stringArray[1] = "content";
                stringArray[2] = RequestSpecificationImpl.CHARSET;
                stringArray[3] = "fileName";
                stringArray[4] = "mimeType";
                stringArray[5] = "controlName";
                stringArray[6] = "headers";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[7];
                _getMultiPartParams_closure45.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_getMultiPartParams_closure45.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _getMultiPartParams_closure45.$createCallSiteArray();
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
        }), List.class);
    }

    @Override
    public Headers getHeaders() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.requestHeaders;
    }

    @Override
    public Cookies getCookies() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.cookies;
    }

    public Object getBody() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.requestBody;
    }

    @Override
    public List<Filter> getDefinedFilters() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1273].call(Collections.class, this.filters), List.class);
    }

    @Override
    public RestAssuredConfig getConfig() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.restAssuredConfig;
    }

    @Override
    public HttpClient getHttpClient() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.httpClient;
    }

    @Override
    public ProxySpecification getProxySpecification() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return this.proxySpecification;
    }

    @Override
    public FilterableRequestSpecification path(String path) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[1274].callStatic(AssertParameter.class, path, "Path");
        Object object = callSiteArray[1275].callStatic(StringUtils.class, path);
        this.path = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        return this;
    }

    @Override
    public List<String> getUndefinedPathParamPlaceholders() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object uri = callSiteArray[1276].callCurrent(this, this.path, false, callSiteArray[1277].call(this.unnamedPathParamsTuples, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _getUndefinedPathParamPlaceholders_closure46.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _getUndefinedPathParamPlaceholders_closure46.$getCallSiteArray();
                return callSiteArray[0].callGetProperty(it);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _getUndefinedPathParamPlaceholders_closure46.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getUndefinedPathParamPlaceholders_closure46.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _getUndefinedPathParamPlaceholders_closure46.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _getUndefinedPathParamPlaceholders_closure46.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "v2";
                return new CallSiteArray(_getUndefinedPathParamPlaceholders_closure46.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _getUndefinedPathParamPlaceholders_closure46.$createCallSiteArray();
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
        }));
        return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1278].callStatic(RequestSpecificationImpl.class, uri), List.class);
    }

    @Override
    public List<String> getPathParamPlaceholders() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object uri = callSiteArray[1279].callCurrent((GroovyObject)this, DefaultTypeTransformation.booleanUnbox(callSiteArray[1280].callStatic(StringUtils.class, this.path, "://")) ? callSiteArray[1281].callStatic(StringUtils.class, this.path, "://") : this.path);
        return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1282].callStatic(RequestSpecificationImpl.class, uri), List.class);
    }

    public String getRequestContentType() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1283].callCurrent(this), String.class);
    }

    @Override
    public String getContentType() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1284].call((Object)this.requestHeaders, CONTENT_TYPE), String.class);
    }

    @Override
    public RequestSpecification noFilters() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[1285].call(this.filters);
        return this;
    }

    public RequestSpecification noFiltersOfType(Class filterType) {
        Reference<Class> filterType2 = new Reference<Class>(filterType);
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[1286].callStatic(AssertParameter.class, filterType2.get(), "Filter type");
        callSiteArray[1287].call(this.filters, new GeneratedClosure(this, this, filterType2){
            private /* synthetic */ Reference filterType;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _noFiltersOfType_closure47.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.filterType = reference = filterType;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _noFiltersOfType_closure47.$getCallSiteArray();
                return callSiteArray[0].call(this.filterType.get(), callSiteArray[1].call(it));
            }

            public Class getFilterType() {
                CallSite[] callSiteArray = _noFiltersOfType_closure47.$getCallSiteArray();
                return (Class)ScriptBytecodeAdapter.castToType(this.filterType.get(), Class.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _noFiltersOfType_closure47.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _noFiltersOfType_closure47.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _noFiltersOfType_closure47.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _noFiltersOfType_closure47.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "isAssignableFrom";
                stringArray[1] = "getClass";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _noFiltersOfType_closure47.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_noFiltersOfType_closure47.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _noFiltersOfType_closure47.$createCallSiteArray();
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
        return this;
    }

    private Object applyProxySettings(RestAssuredHttpBuilder http) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[1288].callConstructor(RestAssuredProxySelectorRoutePlanner.class, callSiteArray[1289].callGetProperty(callSiteArray[1290].callGetProperty(callSiteArray[1291].callGetProperty(http))), callSiteArray[1292].callConstructor(RestAssuredProxySelector.class, ScriptBytecodeAdapter.createMap(new Object[]{"delegatingProxySelector", callSiteArray[1293].callGetProperty(ProxySelector.class), "proxySpecification", this.proxySpecification})), this.proxySpecification);
        ScriptBytecodeAdapter.setProperty(object, null, callSiteArray[1294].callGetProperty(http), "routePlanner");
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[1295].callSafe(this.proxySpecification))) {
            CredentialsProvider credsProvider = (CredentialsProvider)ScriptBytecodeAdapter.castToType(callSiteArray[1296].callConstructor(BasicCredentialsProvider.class), CredentialsProvider.class);
            Object address = callSiteArray[1297].callConstructor(InetSocketAddress.class, callSiteArray[1298].callGetProperty(this.proxySpecification), callSiteArray[1299].callGetProperty(this.proxySpecification));
            Object authScope = callSiteArray[1300].callConstructor(AuthScope.class, callSiteArray[1301].call(callSiteArray[1302].call(address)), callSiteArray[1303].call(this.proxySpecification));
            Object credentials = callSiteArray[1304].callConstructor(UsernamePasswordCredentials.class, callSiteArray[1305].callGetProperty(this.proxySpecification), callSiteArray[1306].callGetProperty(this.proxySpecification));
            callSiteArray[1307].call(credsProvider, authScope, credentials);
            return callSiteArray[1308].call(callSiteArray[1309].callGetProperty(http), credsProvider);
        }
        return null;
    }

    private String assembleCompleteTargetPath(Object requestPath) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object targetUri = null;
        Object targetPath = null;
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[1310].callStatic(PathSupport.class, requestPath))) {
            String string = "";
            targetUri = string;
            String string2 = "";
            targetPath = string2;
        } else {
            Object object;
            Object object2;
            targetUri = object2 = callSiteArray[1311].callCurrent((GroovyObject)this, this.path);
            targetPath = object = callSiteArray[1312].callStatic(StringUtils.class, callSiteArray[1313].callCurrent((GroovyObject)this, this.path), "?");
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1314].callStatic(PathSupport.class, callSiteArray[1315].callStatic(PathSupport.class, targetUri, targetPath), requestPath), String.class);
    }

    private String findEncoderCharsetOrReturnDefault(String contentType) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object charset = callSiteArray[1316].call(CharsetExtractor.class, contentType);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(charset, null)) {
                Object object;
                Object object2;
                EncoderConfig cfg = null;
                if (ScriptBytecodeAdapter.compareEqual(callSiteArray[1317].callGroovyObjectGetProperty(this), null)) {
                    Object object3 = callSiteArray[1318].callConstructor(EncoderConfig.class);
                    cfg = (EncoderConfig)ScriptBytecodeAdapter.castToType(object3, EncoderConfig.class);
                } else {
                    Object object4 = callSiteArray[1319].call(callSiteArray[1320].callGroovyObjectGetProperty(this));
                    cfg = (EncoderConfig)ScriptBytecodeAdapter.castToType(object4, EncoderConfig.class);
                }
                charset = DefaultTypeTransformation.booleanUnbox(callSiteArray[1321].call((Object)cfg, contentType)) ? (object2 = callSiteArray[1322].call((Object)cfg, contentType)) : (object = callSiteArray[1323].call(cfg));
            }
        } else if (ScriptBytecodeAdapter.compareEqual(charset, null)) {
            Object object;
            Object object5;
            EncoderConfig cfg = null;
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[1324].callGroovyObjectGetProperty(this), null)) {
                Object object6 = callSiteArray[1325].callConstructor(EncoderConfig.class);
                cfg = (EncoderConfig)ScriptBytecodeAdapter.castToType(object6, EncoderConfig.class);
            } else {
                Object object7 = callSiteArray[1326].call(callSiteArray[1327].callGroovyObjectGetProperty(this));
                cfg = (EncoderConfig)ScriptBytecodeAdapter.castToType(object7, EncoderConfig.class);
            }
            charset = DefaultTypeTransformation.booleanUnbox(callSiteArray[1328].call((Object)cfg, contentType)) ? (object5 = callSiteArray[1329].call((Object)cfg, contentType)) : (object = callSiteArray[1330].call(cfg));
        }
        return (String)ScriptBytecodeAdapter.castToType(charset, String.class);
    }

    private ObjectMapperConfig objectMappingConfig() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (ObjectMapperConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1331].callGroovyObjectGetProperty(this), null) ? callSiteArray[1332].call(ObjectMapperConfig.class) : callSiteArray[1333].call(callSiteArray[1334].callGroovyObjectGetProperty(this)), ObjectMapperConfig.class);
        }
        return (ObjectMapperConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1335].callGroovyObjectGetProperty(this), null) ? callSiteArray[1336].call(ObjectMapperConfig.class) : callSiteArray[1337].call(callSiteArray[1338].callGroovyObjectGetProperty(this)), ObjectMapperConfig.class);
    }

    private HttpClientConfig httpClientConfig() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (HttpClientConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1339].callGroovyObjectGetProperty(this), null) ? callSiteArray[1340].call(HttpClientConfig.class) : callSiteArray[1341].call(callSiteArray[1342].callGroovyObjectGetProperty(this)), HttpClientConfig.class);
        }
        return (HttpClientConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1343].callGroovyObjectGetProperty(this), null) ? callSiteArray[1344].call(HttpClientConfig.class) : callSiteArray[1345].call(callSiteArray[1346].callGroovyObjectGetProperty(this)), HttpClientConfig.class);
    }

    private ConnectionConfig connectionConfig() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (ConnectionConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1347].callGroovyObjectGetProperty(this), null) ? callSiteArray[1348].call(ConnectionConfig.class) : callSiteArray[1349].call(callSiteArray[1350].callGroovyObjectGetProperty(this)), ConnectionConfig.class);
        }
        return (ConnectionConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1351].callGroovyObjectGetProperty(this), null) ? callSiteArray[1352].call(ConnectionConfig.class) : callSiteArray[1353].call(callSiteArray[1354].callGroovyObjectGetProperty(this)), ConnectionConfig.class);
    }

    private EncoderConfig encoderConfig() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (EncoderConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1355].callGroovyObjectGetProperty(this), null) ? callSiteArray[1356].call(EncoderConfig.class) : callSiteArray[1357].call(callSiteArray[1358].callGroovyObjectGetProperty(this)), EncoderConfig.class);
        }
        return (EncoderConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1359].callGroovyObjectGetProperty(this), null) ? callSiteArray[1360].call(EncoderConfig.class) : callSiteArray[1361].call(callSiteArray[1362].callGroovyObjectGetProperty(this)), EncoderConfig.class);
    }

    private SessionConfig sessionConfig() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (SessionConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1363].callGroovyObjectGetProperty(this), null) ? callSiteArray[1364].call(SessionConfig.class) : callSiteArray[1365].call(callSiteArray[1366].callGroovyObjectGetProperty(this)), SessionConfig.class);
        }
        return (SessionConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1367].callGroovyObjectGetProperty(this), null) ? callSiteArray[1368].call(SessionConfig.class) : callSiteArray[1369].call(callSiteArray[1370].callGroovyObjectGetProperty(this)), SessionConfig.class);
    }

    private CsrfConfig csrfConfig() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (CsrfConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1371].callGroovyObjectGetProperty(this), null) ? callSiteArray[1372].call(CsrfConfig.class) : callSiteArray[1373].call(callSiteArray[1374].callGroovyObjectGetProperty(this)), CsrfConfig.class);
        }
        return (CsrfConfig)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(callSiteArray[1375].callGroovyObjectGetProperty(this), null) ? callSiteArray[1376].call(CsrfConfig.class) : callSiteArray[1377].call(callSiteArray[1378].callGroovyObjectGetProperty(this)), CsrfConfig.class);
    }

    public RestAssuredConfig restAssuredConfig() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[1379].callGroovyObjectGetProperty(this);
        RestAssuredConfig restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
        return DefaultTypeTransformation.booleanUnbox(object) ? restAssuredConfig : (RestAssuredConfig)ScriptBytecodeAdapter.castToType(callSiteArray[1380].callConstructor(RestAssuredConfig.class), RestAssuredConfig.class);
    }

    public static List getPlaceholders(String uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Pattern p = (Pattern)ScriptBytecodeAdapter.castToType(callSiteArray[1381].call(Pattern.class, callSiteArray[1382].call(callSiteArray[1383].call(callSiteArray[1384].call(Pattern.class, TEMPLATE_START), "(.*?)"), callSiteArray[1385].call(Pattern.class, TEMPLATE_END))), Pattern.class);
        Matcher m = (Matcher)ScriptBytecodeAdapter.castToType(callSiteArray[1386].call((Object)p, uri), Matcher.class);
        Object placeholders = callSiteArray[1387].callConstructor(LinkedHashSet.class);
        while (DefaultTypeTransformation.booleanUnbox(callSiteArray[1388].call(m))) {
            callSiteArray[1389].call(placeholders, callSiteArray[1390].callSafe(callSiteArray[1391].call((Object)m, 1)));
        }
        return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1392].call(Collections.class, callSiteArray[1393].callConstructor(ArrayList.class, placeholders)), List.class);
    }

    public static String getDerivedPath(String uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1394].call(PathSupport.class, uri), String.class);
    }

    public String getURI(String uri) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1395].callCurrent(this, uri, this.method), String.class);
    }

    public Map<String, String> getRedundantNamedPathParams() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Reference<Object> placeholders = new Reference<Object>(callSiteArray[1396].callCurrent(this));
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[1397].call(callSiteArray[1398].call(callSiteArray[1399].callCurrent(this), new GeneratedClosure(this, this, placeholders){
            private /* synthetic */ Reference placeholders;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _getRedundantNamedPathParams_closure48.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.placeholders = reference = placeholders;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _getRedundantNamedPathParams_closure48.$getCallSiteArray();
                return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(this.placeholders.get(), callSiteArray[1].callGetProperty(it)));
            }

            public Object getPlaceholders() {
                CallSite[] callSiteArray = _getRedundantNamedPathParams_closure48.$getCallSiteArray();
                return this.placeholders.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _getRedundantNamedPathParams_closure48.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getRedundantNamedPathParams_closure48.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _getRedundantNamedPathParams_closure48.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _getRedundantNamedPathParams_closure48.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "contains";
                stringArray[1] = "key";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _getRedundantNamedPathParams_closure48.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_getRedundantNamedPathParams_closure48.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _getRedundantNamedPathParams_closure48.$createCallSiteArray();
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
        })), Map.class);
    }

    public List<String> getRedundantUnnamedPathParamValues() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object allPathParams = callSiteArray[1400].callCurrent(this);
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[1401].call(callSiteArray[1402].call(callSiteArray[1403].call(callSiteArray[1404].callCurrent(this), callSiteArray[1405].call(allPathParams))), callSiteArray[1406].call(Math.class, callSiteArray[1407].call(callSiteArray[1408].call(callSiteArray[1409].callCurrent(this)), callSiteArray[1410].call(callSiteArray[1411].callCurrent(this))), 0)), 0)) {
                return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1412].call(callSiteArray[1413].call(callSiteArray[1414].callCurrent(this), callSiteArray[1415].call(allPathParams))), List.class);
            }
        } else if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[1416].call(callSiteArray[1417].call(callSiteArray[1418].call(callSiteArray[1419].callCurrent(this), callSiteArray[1420].call(allPathParams))), callSiteArray[1421].call(Math.class, callSiteArray[1422].call(callSiteArray[1423].call(callSiteArray[1424].callCurrent(this)), callSiteArray[1425].call(callSiteArray[1426].callCurrent(this))), 0)), 0)) {
            return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1427].call(callSiteArray[1428].call(callSiteArray[1429].callCurrent(this), callSiteArray[1430].call(allPathParams))), List.class);
        }
        return (List)ScriptBytecodeAdapter.castToType(callSiteArray[1431].call(Collections.class, callSiteArray[1432].call(Collections.class)), List.class);
    }

    public void removeUnnamedPathParamAtIndex(int indexOfParamName) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        callSiteArray[1433].call(this.unnamedPathParamsTuples, indexOfParamName);
        callSiteArray[1434].call(this.unnamedPathParamsTuples, indexOfParamName, callSiteArray[1435].callConstructor(Tuple2.class, null, null));
    }

    public void setMethod(String method) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object = ScriptBytecodeAdapter.compareEqual(method, null) ? null : callSiteArray[1436].call(method);
            this.method = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        } else {
            Object object = ScriptBytecodeAdapter.compareEqual(method, null) ? null : callSiteArray[1437].call(method);
            this.method = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        }
    }

    private static int getFilterOrder(Filter filter) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(filter instanceof OrderedFilter ? callSiteArray[1438].call((OrderedFilter)ScriptBytecodeAdapter.castToType(filter, OrderedFilter.class)) : callSiteArray[1439].callGetProperty(OrderedFilter.class));
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RequestSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, RequestSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(RequestSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RequestSpecificationImpl.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    @Override
    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    @Override
    public /* synthetic */ void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    @Override
    public /* synthetic */ Object invokeMethod(String string, Object object) {
        return this.getMetaClass().invokeMethod((Object)this, string, object);
    }

    @Override
    public /* synthetic */ Object getProperty(String string) {
        return this.getMetaClass().getProperty(this, string);
    }

    @Override
    public /* synthetic */ void setProperty(String string, Object object) {
        this.getMetaClass().setProperty(this, string, object);
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = RequestSpecificationImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        String string;
        String string2;
        long l;
        long l2;
        RequestSpecificationImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729300 = l2 = 0L;
        __timeStamp = l = 1702030729300L;
        TEMPLATE_END = string2 = "}";
        TEMPLATE_START = string = "{";
        Object object = RequestSpecificationImpl.$getCallSiteArray()[1440].call((Object)MULTIPART, "/");
        MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        Object object2 = RequestSpecificationImpl.$getCallSiteArray()[1441].call((Object)MULTIPART, "+");
        MULTIPART_CONTENT_TYPE_PREFIX_WITH_PLUS = (String)ScriptBytecodeAdapter.castToType(object2, String.class);
    }

    @Override
    public AuthenticationScheme getAuthenticationScheme() {
        return this.authenticationScheme;
    }

    public void setAuthenticationScheme(AuthenticationScheme authenticationScheme) {
        this.authenticationScheme = authenticationScheme;
    }

    public /* synthetic */ List this$2$removeMergedHeadersIfNeeded(List list) {
        return this.removeMergedHeadersIfNeeded(list);
    }

    public /* synthetic */ String this$2$generateRequestUriForLogging(Object object, Object object2) {
        return this.generateRequestUriForLogging(object, object2);
    }

    public /* synthetic */ Response this$2$sendRequest(Object object, Object object2, FilterableRequestSpecification filterableRequestSpecification, Map map) {
        return this.sendRequest(object, object2, filterableRequestSpecification, map);
    }

    public /* synthetic */ Object this$2$applyContentDecoders(HTTPBuilder hTTPBuilder, List list) {
        return this.applyContentDecoders(hTTPBuilder, list);
    }

    public /* synthetic */ Object this$2$putIfAbsent(Map map, Object object, Object object2) {
        return this.putIfAbsent(map, object, object2);
    }

    public /* synthetic */ Object this$2$createFormParamBodyContent(Object object) {
        return this.createFormParamBodyContent(object);
    }

    public /* synthetic */ String this$2$getTargetPath(String string) {
        return this.getTargetPath(string);
    }

    public /* synthetic */ Object this$2$registerRestAssuredEncoders(HTTPBuilder hTTPBuilder) {
        return this.registerRestAssuredEncoders(hTTPBuilder);
    }

    public /* synthetic */ Object this$2$convertFormParamsToMultiPartParams() {
        return this.convertFormParamsToMultiPartParams();
    }

    public /* synthetic */ Object this$2$sendHttpRequest(HTTPBuilder hTTPBuilder, String string, Object object, Object object2, Object object3) {
        return this.sendHttpRequest(hTTPBuilder, string, object, object2, object3);
    }

    public /* synthetic */ boolean this$2$hasFormParams() {
        return this.hasFormParams();
    }

    public /* synthetic */ boolean this$2$mayHaveBody(Object object) {
        return this.mayHaveBody(object);
    }

    public /* synthetic */ String this$2$extractRequestParamsIfNeeded(String string) {
        return this.extractRequestParamsIfNeeded(string);
    }

    public /* synthetic */ Object this$2$defineRequestContentTypeAsString(String string) {
        return this.defineRequestContentTypeAsString(string);
    }

    public /* synthetic */ Object this$2$defineRequestContentType(String string) {
        return this.defineRequestContentType(string);
    }

    public /* synthetic */ boolean this$2$shouldAppendCharsetToContentType(Object object) {
        return this.shouldAppendCharsetToContentType(object);
    }

    public /* synthetic */ boolean this$2$isApplicationJsonContentTypeWithDefaultCharsetDefined(Object object) {
        return this.isApplicationJsonContentTypeWithDefaultCharsetDefined(object);
    }

    public /* synthetic */ String this$2$getTargetURI(String string) {
        return this.getTargetURI(string);
    }

    public /* synthetic */ String this$2$getTargetUriFromUrl(URL uRL) {
        return this.getTargetUriFromUrl(uRL);
    }

    public /* synthetic */ boolean this$2$hasAuthorityEqualToLocalhost(Object object) {
        return this.hasAuthorityEqualToLocalhost(object);
    }

    public /* synthetic */ boolean this$2$hasPortDefined(Object object) {
        return this.hasPortDefined(object);
    }

    public /* synthetic */ Object this$2$serializeIfNeeded(Object object) {
        return this.serializeIfNeeded(object);
    }

    public /* synthetic */ Object this$2$serializeIfNeeded(Object object, Object object2) {
        return this.serializeIfNeeded(object, object2);
    }

    public /* synthetic */ Object this$2$applyPathParamsAndSendRequest(String string, String string2, Object[] objectArray) {
        return this.applyPathParamsAndSendRequest(string, string2, objectArray);
    }

    public /* synthetic */ Object this$2$applyPathParamsAndSendRequest(Method method, String string, Object[] objectArray) {
        return this.applyPathParamsAndSendRequest(method, string, objectArray);
    }

    public /* synthetic */ String this$2$findNamedPathParamValue(String string, Object object) {
        return this.findNamedPathParamValue(string, object);
    }

    public /* synthetic */ String this$2$createFormParamBody(Map map) {
        return this.createFormParamBody(map);
    }

    public /* synthetic */ String this$2$encode(Object object, EncodingTarget encodingTarget) {
        return this.encode(object, encodingTarget);
    }

    public /* synthetic */ Object this$2$handleMultiValueParamsIfNeeded(Map.Entry entry) {
        return this.handleMultiValueParamsIfNeeded(entry);
    }

    public /* synthetic */ Object this$2$applyProxySettings(RestAssuredHttpBuilder restAssuredHttpBuilder) {
        return this.applyProxySettings(restAssuredHttpBuilder);
    }

    public /* synthetic */ String this$2$assembleCompleteTargetPath(Object object) {
        return this.assembleCompleteTargetPath(object);
    }

    public /* synthetic */ String this$2$findEncoderCharsetOrReturnDefault(String string) {
        return this.findEncoderCharsetOrReturnDefault(string);
    }

    public /* synthetic */ ObjectMapperConfig this$2$objectMappingConfig() {
        return this.objectMappingConfig();
    }

    public /* synthetic */ HttpClientConfig this$2$httpClientConfig() {
        return this.httpClientConfig();
    }

    public /* synthetic */ ConnectionConfig this$2$connectionConfig() {
        return this.connectionConfig();
    }

    public /* synthetic */ EncoderConfig this$2$encoderConfig() {
        return this.encoderConfig();
    }

    public /* synthetic */ SessionConfig this$2$sessionConfig() {
        return this.sessionConfig();
    }

    public /* synthetic */ CsrfConfig this$2$csrfConfig() {
        return this.csrfConfig();
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
        stringArray[0] = "<$constructor$>";
        stringArray[1] = "<$constructor$>";
        stringArray[2] = "<$constructor$>";
        stringArray[3] = "<$constructor$>";
        stringArray[4] = "<$constructor$>";
        stringArray[5] = "<$constructor$>";
        stringArray[6] = "<$constructor$>";
        stringArray[7] = "<$constructor$>";
        stringArray[8] = "notNull";
        stringArray[9] = "notNull";
        stringArray[10] = "notNull";
        stringArray[11] = "notNull";
        stringArray[12] = "notNull";
        stringArray[13] = "addAll";
        stringArray[14] = "port";
        stringArray[15] = "spec";
        stringArray[16] = "spec";
        stringArray[17] = "applyPathParamsAndSendRequest";
        stringArray[18] = "GET";
        stringArray[19] = "applyPathParamsAndSendRequest";
        stringArray[20] = "POST";
        stringArray[21] = "applyPathParamsAndSendRequest";
        stringArray[22] = "PUT";
        stringArray[23] = "applyPathParamsAndSendRequest";
        stringArray[24] = "DELETE";
        stringArray[25] = "applyPathParamsAndSendRequest";
        stringArray[26] = "HEAD";
        stringArray[27] = "applyPathParamsAndSendRequest";
        stringArray[28] = "PATCH";
        stringArray[29] = "applyPathParamsAndSendRequest";
        stringArray[30] = "OPTIONS";
        stringArray[31] = "get";
        stringArray[32] = "toString";
        stringArray[33] = "notNull";
        stringArray[34] = "post";
        stringArray[35] = "toString";
        stringArray[36] = "notNull";
        stringArray[37] = "put";
        stringArray[38] = "toString";
        stringArray[39] = "notNull";
        stringArray[40] = "delete";
        stringArray[41] = "toString";
        stringArray[42] = "notNull";
        stringArray[43] = "head";
        stringArray[44] = "toString";
        stringArray[45] = "notNull";
        stringArray[46] = "patch";
        stringArray[47] = "toString";
        stringArray[48] = "notNull";
        stringArray[49] = "options";
        stringArray[50] = "toString";
        stringArray[51] = "notNull";
        stringArray[52] = "get";
        stringArray[53] = "toString";
        stringArray[54] = "notNull";
        stringArray[55] = "post";
        stringArray[56] = "toString";
        stringArray[57] = "notNull";
        stringArray[58] = "put";
        stringArray[59] = "toString";
        stringArray[60] = "notNull";
        stringArray[61] = "delete";
        stringArray[62] = "toString";
        stringArray[63] = "notNull";
        stringArray[64] = "head";
        stringArray[65] = "toString";
        stringArray[66] = "notNull";
        stringArray[67] = "patch";
        stringArray[68] = "toString";
        stringArray[69] = "notNull";
        stringArray[70] = "options";
        stringArray[71] = "toString";
        stringArray[72] = "notNull";
        stringArray[73] = "get";
        stringArray[74] = "post";
        stringArray[75] = "put";
        stringArray[76] = "delete";
        stringArray[77] = "head";
        stringArray[78] = "patch";
        stringArray[79] = "options";
        stringArray[80] = "request";
        stringArray[81] = "name";
        stringArray[82] = "notNull";
        stringArray[83] = "request";
        stringArray[84] = "request";
        stringArray[85] = "name";
        stringArray[86] = "notNull";
        stringArray[87] = "applyPathParamsAndSendRequest";
        stringArray[88] = "request";
        stringArray[89] = "toString";
        stringArray[90] = "notNull";
        stringArray[91] = "class";
        stringArray[92] = "URI";
        stringArray[93] = "request";
        stringArray[94] = "toString";
        stringArray[95] = "notNull";
        stringArray[96] = "request";
        stringArray[97] = "toString";
        stringArray[98] = "notNull";
        stringArray[99] = "class";
        stringArray[100] = "URI";
        stringArray[101] = "request";
        stringArray[102] = "toString";
        stringArray[103] = "notNull";
        stringArray[104] = "pathParams";
        stringArray[105] = "applyPathParamsAndSendRequest";
        stringArray[106] = "GET";
        stringArray[107] = "pathParams";
        stringArray[108] = "applyPathParamsAndSendRequest";
        stringArray[109] = "POST";
        stringArray[110] = "pathParams";
        stringArray[111] = "applyPathParamsAndSendRequest";
        stringArray[112] = "PUT";
        stringArray[113] = "pathParams";
        stringArray[114] = "applyPathParamsAndSendRequest";
        stringArray[115] = "DELETE";
        stringArray[116] = "pathParams";
        stringArray[117] = "applyPathParamsAndSendRequest";
        stringArray[118] = "HEAD";
        stringArray[119] = "pathParams";
        stringArray[120] = "applyPathParamsAndSendRequest";
        stringArray[121] = "PATCH";
        stringArray[122] = "pathParams";
        stringArray[123] = "applyPathParamsAndSendRequest";
        stringArray[124] = "OPTIONS";
        stringArray[125] = "notNull";
        stringArray[126] = "notNull";
        stringArray[127] = "params";
        stringArray[128] = "createMapFromParams";
        stringArray[129] = "OVERWRITE";
        stringArray[130] = "notNull";
        stringArray[131] = "updateParameters";
        stringArray[132] = "requestParamsUpdateStrategy";
        stringArray[133] = "paramConfig";
        stringArray[134] = "restAssuredConfig";
        stringArray[135] = "notNull";
        stringArray[136] = "updateZeroToManyParameters";
        stringArray[137] = "requestParamsUpdateStrategy";
        stringArray[138] = "paramConfig";
        stringArray[139] = "restAssuredConfig";
        stringArray[140] = "notNull";
        stringArray[141] = "remove";
        stringArray[142] = "notNull";
        stringArray[143] = "param";
        stringArray[144] = "toArray";
        stringArray[145] = "notNull";
        stringArray[146] = "notNull";
        stringArray[147] = "updateCollectionParameter";
        stringArray[148] = "queryParamsUpdateStrategy";
        stringArray[149] = "getParamConfig";
        stringArray[150] = "restAssuredConfig";
        stringArray[151] = "notNull";
        stringArray[152] = "remove";
        stringArray[153] = "notNull";
        stringArray[154] = "findAll";
        stringArray[155] = "headers";
        stringArray[156] = "<$constructor$>";
        stringArray[157] = "notNull";
        stringArray[158] = "findAll";
        stringArray[159] = "<$constructor$>";
        stringArray[160] = "notNull";
        stringArray[161] = "removeCookie";
        stringArray[162] = "getName";
        stringArray[163] = "notNull";
        stringArray[164] = "removeHeader";
        stringArray[165] = "addAll";
        stringArray[166] = "list";
        stringArray[167] = "add";
        stringArray[168] = "<$constructor$>";
        stringArray[169] = "<$constructor$>";
        stringArray[170] = "notNull";
        stringArray[171] = "removeCookie";
        stringArray[172] = "cookie";
        stringArray[173] = "notNull";
        stringArray[174] = "removeCookie";
        stringArray[175] = "getName";
        stringArray[176] = "cookie";
        stringArray[177] = "notNull";
        stringArray[178] = "<$constructor$>";
        stringArray[179] = "asList";
        stringArray[180] = "notNull";
        stringArray[181] = "<$constructor$>";
        stringArray[182] = "asList";
        stringArray[183] = "<$constructor$>";
        stringArray[184] = "<$constructor$>";
        stringArray[185] = "notNull";
        stringArray[186] = "notNull";
        stringArray[187] = "queryParams";
        stringArray[188] = "createMapFromParams";
        stringArray[189] = "OVERWRITE";
        stringArray[190] = "notNull";
        stringArray[191] = "updateParameters";
        stringArray[192] = "queryParamsUpdateStrategy";
        stringArray[193] = "paramConfig";
        stringArray[194] = "restAssuredConfig";
        stringArray[195] = "notNull";
        stringArray[196] = "updateZeroToManyParameters";
        stringArray[197] = "queryParamsUpdateStrategy";
        stringArray[198] = "paramConfig";
        stringArray[199] = "restAssuredConfig";
        stringArray[200] = "notNull";
        stringArray[201] = "notNull";
        stringArray[202] = "updateCollectionParameter";
        stringArray[203] = "formParamsUpdateStrategy";
        stringArray[204] = "paramConfig";
        stringArray[205] = "restAssuredConfig";
        stringArray[206] = "notNull";
        stringArray[207] = "remove";
        stringArray[208] = "notNull";
        stringArray[209] = "notNull";
        stringArray[210] = "formParams";
        stringArray[211] = "createMapFromParams";
        stringArray[212] = "OVERWRITE";
        stringArray[213] = "notNull";
        stringArray[214] = "updateParameters";
        stringArray[215] = "formParamsUpdateStrategy";
        stringArray[216] = "paramConfig";
        stringArray[217] = "restAssuredConfig";
        stringArray[218] = "notNull";
        stringArray[219] = "updateZeroToManyParameters";
        stringArray[220] = "formParamsUpdateStrategy";
        stringArray[221] = "paramConfig";
        stringArray[222] = "restAssuredConfig";
        stringArray[223] = "notNull";
        stringArray[224] = "notNull";
        stringArray[225] = "updateStandardParameter";
        stringArray[226] = "REPLACE";
        stringArray[227] = "notNull";
        stringArray[228] = "notNull";
        stringArray[229] = "pathParams";
        stringArray[230] = "createMapFromParams";
        stringArray[231] = "OVERWRITE";
        stringArray[232] = "notNull";
        stringArray[233] = "updateParameters";
        stringArray[234] = "REPLACE";
        stringArray[235] = "notNull";
        stringArray[236] = "removeNamedPathParam";
        stringArray[237] = "removeUnnamedPathParam";
        stringArray[238] = "notNull";
        stringArray[239] = "remove";
        stringArray[240] = "notNull";
        stringArray[241] = "findIndexOf";
        stringArray[242] = "removeUnnamedPathParamAtIndex";
        stringArray[243] = "removeUnnamedPathParamAtIndex";
        stringArray[244] = "notNull";
        stringArray[245] = "findIndexOf";
        stringArray[246] = "removeUnnamedPathParamAtIndex";
        stringArray[247] = "removeUnnamedPathParamAtIndex";
        stringArray[248] = "getSSLConfig";
        stringArray[249] = "restAssuredConfig";
        stringArray[250] = "sslConfig";
        stringArray[251] = "restAssuredConfig";
        stringArray[252] = "allowAllHostnames";
        stringArray[253] = "keyStore";
        stringArray[254] = "getSSLConfig";
        stringArray[255] = "restAssuredConfig";
        stringArray[256] = "sslConfig";
        stringArray[257] = "restAssuredConfig";
        stringArray[258] = "allowAllHostnames";
        stringArray[259] = "keyStore";
        stringArray[260] = "getSSLConfig";
        stringArray[261] = "restAssuredConfig";
        stringArray[262] = "sslConfig";
        stringArray[263] = "restAssuredConfig";
        stringArray[264] = "allowAllHostnames";
        stringArray[265] = "trustStore";
        stringArray[266] = "getSSLConfig";
        stringArray[267] = "restAssuredConfig";
        stringArray[268] = "sslConfig";
        stringArray[269] = "restAssuredConfig";
        stringArray[270] = "allowAllHostnames";
        stringArray[271] = "trustStore";
        stringArray[272] = "getSSLConfig";
        stringArray[273] = "restAssuredConfig";
        stringArray[274] = "sslConfig";
        stringArray[275] = "restAssuredConfig";
        stringArray[276] = "trustStore";
        stringArray[277] = "getSSLConfig";
        stringArray[278] = "restAssuredConfig";
        stringArray[279] = "sslConfig";
        stringArray[280] = "restAssuredConfig";
        stringArray[281] = "keyStore";
        stringArray[282] = "relaxedHTTPSValidation";
        stringArray[283] = "getSSLConfig";
        stringArray[284] = "restAssuredConfig";
        stringArray[285] = "sslConfig";
        stringArray[286] = "restAssuredConfig";
        stringArray[287] = "relaxedHTTPSValidation";
        stringArray[288] = "notNull";
        stringArray[289] = "leftShift";
        stringArray[290] = "notNull";
        stringArray[291] = "addAll";
        stringArray[292] = "notNull";
        stringArray[293] = "add";
        stringArray[294] = "each";
        stringArray[295] = "blacklistedHeaders";
        stringArray[296] = "logConfig";
        stringArray[297] = "restAssuredConfig";
        stringArray[298] = "<$constructor$>";
        stringArray[299] = "<$constructor$>";
        stringArray[300] = "csrfConfig";
        stringArray[301] = "restAssuredConfig";
        stringArray[302] = "csrfTokenPath";
        stringArray[303] = "csrfConfig";
        stringArray[304] = "csrfConfig";
        stringArray[305] = "restAssuredConfig";
        stringArray[306] = "csrfInputFieldName";
        stringArray[307] = "csrfTokenPath";
        stringArray[308] = "csrfConfig";
        stringArray[309] = "noFiltersOfType";
        stringArray[310] = "auth";
        stringArray[311] = "UNDEFINED_PORT";
        stringArray[312] = "<$constructor$>";
        stringArray[313] = "UNDEFINED_PORT";
        stringArray[314] = "<$constructor$>";
        stringArray[315] = "notNull";
        stringArray[316] = "notNull";
        stringArray[317] = "notNull";
        stringArray[318] = "proxy";
        stringArray[319] = "withPort";
        stringArray[320] = "host";
        stringArray[321] = "isUri";
        stringArray[322] = "proxy";
        stringArray[323] = "<$constructor$>";
        stringArray[324] = "proxy";
        stringArray[325] = "host";
        stringArray[326] = "proxy";
        stringArray[327] = "port";
        stringArray[328] = "proxy";
        stringArray[329] = "build";
        stringArray[330] = "setScheme";
        stringArray[331] = "setPort";
        stringArray[332] = "setHost";
        stringArray[333] = "<$constructor$>";
        stringArray[334] = "notNull";
        stringArray[335] = "class";
        stringArray[336] = "URI";
        stringArray[337] = "proxy";
        stringArray[338] = "<$constructor$>";
        stringArray[339] = "host";
        stringArray[340] = "port";
        stringArray[341] = "scheme";
        stringArray[342] = "notNull";
        stringArray[343] = "notNull";
        stringArray[344] = "notNull";
        stringArray[345] = "notNull";
        stringArray[346] = "notNull";
        stringArray[347] = "isSerializableCandidate";
        stringArray[348] = "body";
        stringArray[349] = "toString";
        stringArray[350] = "serialize";
        stringArray[351] = "requestContentType";
        stringArray[352] = "findEncoderCharsetOrReturnDefault";
        stringArray[353] = "requestContentType";
        stringArray[354] = "objectMappingConfig";
        stringArray[355] = "getEncoderConfig";
        stringArray[356] = "restAssuredConfig";
        stringArray[357] = "notNull";
        stringArray[358] = "notNull";
        stringArray[359] = "<$constructor$>";
        stringArray[360] = "setObject";
        stringArray[361] = "setCharset";
        stringArray[362] = "findEncoderCharsetOrReturnDefault";
        stringArray[363] = "requestContentType";
        stringArray[364] = "setContentType";
        stringArray[365] = "requestContentType";
        stringArray[366] = "serialize";
        stringArray[367] = "notNull";
        stringArray[368] = "notNull";
        stringArray[369] = "serialize";
        stringArray[370] = "requestContentType";
        stringArray[371] = "findEncoderCharsetOrReturnDefault";
        stringArray[372] = "requestContentType";
        stringArray[373] = "objectMappingConfig";
        stringArray[374] = "getEncoderConfig";
        stringArray[375] = "restAssuredConfig";
        stringArray[376] = "notNull";
        stringArray[377] = "header";
        stringArray[378] = "notNull";
        stringArray[379] = "header";
        stringArray[380] = "removeHeader";
        stringArray[381] = "notNull";
        stringArray[382] = "accept";
        stringArray[383] = "getAcceptHeader";
        stringArray[384] = "notNull";
        stringArray[385] = "header";
        stringArray[386] = "notNull";
        stringArray[387] = "exist";
        stringArray[388] = "addAll";
        stringArray[389] = "list";
        stringArray[390] = "each";
        stringArray[391] = "removeMergedHeadersIfNeeded";
        stringArray[392] = "<$constructor$>";
        stringArray[393] = "notNull";
        stringArray[394] = "exist";
        stringArray[395] = "exist";
        stringArray[396] = "addAll";
        stringArray[397] = "list";
        stringArray[398] = "addAll";
        stringArray[399] = "list";
        stringArray[400] = "headers";
        stringArray[401] = "removeMergedHeadersIfNeeded";
        stringArray[402] = "<$constructor$>";
        stringArray[403] = "inject";
        stringArray[404] = "notNull";
        stringArray[405] = "notNull";
        stringArray[406] = "<$constructor$>";
        stringArray[407] = "serializeIfNeeded";
        stringArray[408] = "each";
        stringArray[409] = "headers";
        stringArray[410] = "<$constructor$>";
        stringArray[411] = "notNull";
        stringArray[412] = "headers";
        stringArray[413] = "<$constructor$>";
        stringArray[414] = "asList";
        stringArray[415] = "headers";
        stringArray[416] = "createMapFromParams";
        stringArray[417] = "MERGE";
        stringArray[418] = "cookies";
        stringArray[419] = "createMapFromParams";
        stringArray[420] = "OVERWRITE";
        stringArray[421] = "notNull";
        stringArray[422] = "exist";
        stringArray[423] = "addAll";
        stringArray[424] = "list";
        stringArray[425] = "each";
        stringArray[426] = "<$constructor$>";
        stringArray[427] = "notNull";
        stringArray[428] = "exist";
        stringArray[429] = "exist";
        stringArray[430] = "addAll";
        stringArray[431] = "list";
        stringArray[432] = "addAll";
        stringArray[433] = "list";
        stringArray[434] = "cookies";
        stringArray[435] = "<$constructor$>";
        stringArray[436] = "notNull";
        stringArray[437] = "build";
        stringArray[438] = "<$constructor$>";
        stringArray[439] = "serializeIfNeeded";
        stringArray[440] = "each";
        stringArray[441] = "cookies";
        stringArray[442] = "<$constructor$>";
        stringArray[443] = "notNull";
        stringArray[444] = "cookies";
        stringArray[445] = "<$constructor$>";
        stringArray[446] = "asList";
        stringArray[447] = "cookie";
        stringArray[448] = "<$constructor$>";
        stringArray[449] = "merge";
        stringArray[450] = "spec";
        stringArray[451] = "config";
        stringArray[452] = "DEFAULT_SESSION_ID_NAME";
        stringArray[453] = "sessionIdName";
        stringArray[454] = "getSessionConfig";
        stringArray[455] = "config";
        stringArray[456] = "config";
        stringArray[457] = "DEFAULT_SESSION_ID_NAME";
        stringArray[458] = "sessionIdName";
        stringArray[459] = "getSessionConfig";
        stringArray[460] = "config";
        stringArray[461] = "sessionId";
        stringArray[462] = "notNull";
        stringArray[463] = "notNull";
        stringArray[464] = "hasCookieWithName";
        stringArray[465] = "findAll";
        stringArray[466] = "add";
        stringArray[467] = "build";
        stringArray[468] = "<$constructor$>";
        stringArray[469] = "<$constructor$>";
        stringArray[470] = "cookie";
        stringArray[471] = "notNull";
        stringArray[472] = "mimeType";
        stringArray[473] = "content";
        stringArray[474] = "content";
        stringArray[475] = "content";
        stringArray[476] = "content";
        stringArray[477] = "matches";
        stringArray[478] = "ANY";
        stringArray[479] = "requestContentType";
        stringArray[480] = "toString";
        stringArray[481] = "JSON";
        stringArray[482] = "requestContentType";
        stringArray[483] = "serializeIfNeeded";
        stringArray[484] = "content";
        stringArray[485] = "content";
        stringArray[486] = "content";
        stringArray[487] = "content";
        stringArray[488] = "content";
        stringArray[489] = "matches";
        stringArray[490] = "ANY";
        stringArray[491] = "requestContentType";
        stringArray[492] = "toString";
        stringArray[493] = "JSON";
        stringArray[494] = "requestContentType";
        stringArray[495] = "serializeIfNeeded";
        stringArray[496] = "content";
        stringArray[497] = "isControlNameSpecifiedExplicitly";
        stringArray[498] = "defaultControlName";
        stringArray[499] = "getMultiPartConfig";
        stringArray[500] = "restAssuredConfig";
        stringArray[501] = "controlName";
        stringArray[502] = "isControlNameSpecifiedExplicitly";
        stringArray[503] = "defaultControlName";
        stringArray[504] = "getMultiPartConfig";
        stringArray[505] = "restAssuredConfig";
        stringArray[506] = "controlName";
        stringArray[507] = "isFileNameSpecifiedExplicitly";
        stringArray[508] = "defaultFileName";
        stringArray[509] = "getMultiPartConfig";
        stringArray[510] = "restAssuredConfig";
        stringArray[511] = "fileName";
        stringArray[512] = "isFileNameSpecifiedExplicitly";
        stringArray[513] = "defaultFileName";
        stringArray[514] = "getMultiPartConfig";
        stringArray[515] = "restAssuredConfig";
        stringArray[516] = "fileName";
        stringArray[517] = "headers";
        stringArray[518] = "leftShift";
        stringArray[519] = "<$constructor$>";
        stringArray[520] = CHARSET;
        stringArray[521] = "leftShift";
        stringArray[522] = "<$constructor$>";
        stringArray[523] = "getName";
        stringArray[524] = "leftShift";
        stringArray[525] = "<$constructor$>";
        stringArray[526] = "defaultControlName";
        stringArray[527] = "getMultiPartConfig";
        stringArray[528] = "restAssuredConfig";
        stringArray[529] = "getName";
        stringArray[530] = "leftShift";
        stringArray[531] = "<$constructor$>";
        stringArray[532] = "getName";
        stringArray[533] = "matches";
        stringArray[534] = "ANY";
        stringArray[535] = "requestContentType";
        stringArray[536] = "toString";
        stringArray[537] = "JSON";
        stringArray[538] = "requestContentType";
        stringArray[539] = "multiPart";
        stringArray[540] = "serializeIfNeeded";
        stringArray[541] = "leftShift";
        stringArray[542] = "<$constructor$>";
        stringArray[543] = "defaultFileName";
        stringArray[544] = "getMultiPartConfig";
        stringArray[545] = "restAssuredConfig";
        stringArray[546] = "serializeIfNeeded";
        stringArray[547] = "leftShift";
        stringArray[548] = "<$constructor$>";
        stringArray[549] = "leftShift";
        stringArray[550] = "<$constructor$>";
        stringArray[551] = "leftShift";
        stringArray[552] = "<$constructor$>";
        stringArray[553] = "leftShift";
        stringArray[554] = "<$constructor$>";
        stringArray[555] = "leftShift";
        stringArray[556] = "<$constructor$>";
        stringArray[557] = "leftShift";
        stringArray[558] = "<$constructor$>";
        stringArray[559] = "defaultFileName";
        stringArray[560] = "getMultiPartConfig";
        stringArray[561] = "restAssuredConfig";
        stringArray[562] = "leftShift";
        stringArray[563] = "<$constructor$>";
        stringArray[564] = "defaultFileName";
        stringArray[565] = "getMultiPartConfig";
        stringArray[566] = "restAssuredConfig";
        stringArray[567] = "leftShift";
        stringArray[568] = "<$constructor$>";
        stringArray[569] = "defaultFileName";
        stringArray[570] = "getMultiPartConfig";
        stringArray[571] = "restAssuredConfig";
        stringArray[572] = "endsWith";
        stringArray[573] = "<$constructor$>";
        stringArray[574] = "hasHeaderWithName";
        stringArray[575] = "headers";
        stringArray[576] = "header";
        stringArray[577] = "getAcceptHeader";
        stringArray[578] = "ANY";
        stringArray[579] = "defineRequestContentTypeAsString";
        stringArray[580] = "header";
        stringArray[581] = "header";
        stringArray[582] = "collect";
        stringArray[583] = "findAll";
        stringArray[584] = "partiallyApplyPathParams";
        stringArray[585] = "generateRequestUriForLogging";
        stringArray[586] = "<$constructor$>";
        stringArray[587] = "getUserDefinedPath";
        stringArray[588] = "getDerivedPath";
        stringArray[589] = "toArray";
        stringArray[590] = "contains";
        stringArray[591] = "isFullyQualified";
        stringArray[592] = "getTargetPath";
        stringArray[593] = "substringBefore";
        stringArray[594] = "substringAfter";
        stringArray[595] = "isBlank";
        stringArray[596] = "split";
        stringArray[597] = "each";
        stringArray[598] = "convertToURI";
        stringArray[599] = "assembleCompleteTargetPath";
        stringArray[600] = "<$constructor$>";
        stringArray[601] = "encoderConfig";
        stringArray[602] = "equalsIgnoreCase";
        stringArray[603] = "name";
        stringArray[604] = "POST";
        stringArray[605] = "isEmpty";
        stringArray[606] = "leftShift";
        stringArray[607] = "equalsIgnoreCase";
        stringArray[608] = "name";
        stringArray[609] = "POST";
        stringArray[610] = "isEmpty";
        stringArray[611] = "leftShift";
        stringArray[612] = "isEmpty";
        stringArray[613] = "leftShift";
        stringArray[614] = "equalsIgnoreCase";
        stringArray[615] = "name";
        stringArray[616] = "GET";
        stringArray[617] = "isEmpty";
        stringArray[618] = "leftShift";
        stringArray[619] = "equalsIgnoreCase";
        stringArray[620] = "name";
        stringArray[621] = "GET";
        stringArray[622] = "isEmpty";
        stringArray[623] = "leftShift";
        stringArray[624] = "isEmpty";
        stringArray[625] = "addQueryParams";
        stringArray[626] = "toString";
        stringArray[627] = "notNull";
        stringArray[628] = "extractRequestParamsIfNeeded";
        stringArray[629] = "getMethod";
        stringArray[630] = "getTargetURI";
        stringArray[631] = "getTargetPath";
        stringArray[632] = "assertCorrectNumberOfPathParams";
        stringArray[633] = "getHttpClient";
        stringArray[634] = "<$constructor$>";
        stringArray[635] = "format";
        stringArray[636] = "getName";
        stringArray[637] = "<$constructor$>";
        stringArray[638] = "config";
        stringArray[639] = "getHttpClient";
        stringArray[640] = "defaultParser";
        stringArray[641] = "rpr";
        stringArray[642] = "applyProxySettings";
        stringArray[643] = "applyRestAssuredConfig";
        stringArray[644] = "registerRestAssuredEncoders";
        stringArray[645] = "setRequestHeadersToHttpBuilder";
        stringArray[646] = "exist";
        stringArray[647] = "leftShift";
        stringArray[648] = "getHeaders";
        stringArray[649] = "join";
        stringArray[650] = "collect";
        stringArray[651] = "<$constructor$>";
        stringArray[652] = "config";
        stringArray[653] = "<$constructor$>";
        stringArray[654] = "setSessionIdName";
        stringArray[655] = "sessionIdName";
        stringArray[656] = "getSessionConfig";
        stringArray[657] = "setDecoderConfig";
        stringArray[658] = "getDecoderConfig";
        stringArray[659] = "setConnectionManager";
        stringArray[660] = "connectionManager";
        stringArray[661] = "client";
        stringArray[662] = "setConfig";
        stringArray[663] = "setFilterContextProperties";
        stringArray[664] = "getResponseContentType";
        stringArray[665] = "shouldApplySSLConfig";
        stringArray[666] = "getSSLConfig";
        stringArray[667] = "authenticate";
        stringArray[668] = "<$constructor$>";
        stringArray[669] = "getPathToKeyStore";
        stringArray[670] = "getKeyStorePassword";
        stringArray[671] = "getKeyStoreType";
        stringArray[672] = "getKeyStore";
        stringArray[673] = "getPathToTrustStore";
        stringArray[674] = "getTrustStorePassword";
        stringArray[675] = "getTrustStoreType";
        stringArray[676] = "getTrustStore";
        stringArray[677] = "getPort";
        stringArray[678] = "getSSLSocketFactory";
        stringArray[679] = "getX509HostnameVerifier";
        stringArray[680] = "authenticate";
        stringArray[681] = "mayHaveBody";
        stringArray[682] = "hasFormParams";
        stringArray[683] = "<$constructor$>";
        stringArray[684] = "createFormParamBodyContent";
        stringArray[685] = "assembleBodyContent";
        stringArray[686] = "equalsIgnoreCase";
        stringArray[687] = "name";
        stringArray[688] = "POST";
        stringArray[689] = "post";
        stringArray[690] = "getValue";
        stringArray[691] = "equalsIgnoreCase";
        stringArray[692] = "name";
        stringArray[693] = "PATCH";
        stringArray[694] = "patch";
        stringArray[695] = "getValue";
        stringArray[696] = "sendHttpRequest";
        stringArray[697] = "sendHttpRequest";
        stringArray[698] = "isEmpty";
        stringArray[699] = "getRedundantNamedPathParams";
        stringArray[700] = "isEmpty";
        stringArray[701] = "getRedundantUnnamedPathParamValues";
        stringArray[702] = "isEmpty";
        stringArray[703] = "getUndefinedPathParamPlaceholders";
        stringArray[704] = "size";
        stringArray[705] = "getPathParamPlaceholders";
        stringArray[706] = "getNamedPathParams";
        stringArray[707] = "plus";
        stringArray[708] = "size";
        stringArray[709] = "size";
        stringArray[710] = "findAll";
        stringArray[711] = "findAll";
        stringArray[712] = "getRedundantNamedPathParams";
        stringArray[713] = "getRedundantUnnamedPathParamValues";
        stringArray[714] = "size";
        stringArray[715] = "size";
        stringArray[716] = "plus";
        stringArray[717] = "join";
        stringArray[718] = "entrySet";
        stringArray[719] = "plus";
        stringArray[720] = "plus";
        stringArray[721] = "plus";
        stringArray[722] = "join";
        stringArray[723] = "isEmpty";
        stringArray[724] = "getUndefinedPathParamPlaceholders";
        stringArray[725] = "join";
        stringArray[726] = "getUndefinedPathParamPlaceholders";
        stringArray[727] = "<$constructor$>";
        stringArray[728] = "isEmpty";
        stringArray[729] = "getRedundantNamedPathParams";
        stringArray[730] = "isEmpty";
        stringArray[731] = "getRedundantUnnamedPathParamValues";
        stringArray[732] = "isEmpty";
        stringArray[733] = "getUndefinedPathParamPlaceholders";
        stringArray[734] = "size";
        stringArray[735] = "getPathParamPlaceholders";
        stringArray[736] = "getNamedPathParams";
        stringArray[737] = "plus";
        stringArray[738] = "size";
        stringArray[739] = "size";
        stringArray[740] = "findAll";
        stringArray[741] = "findAll";
        stringArray[742] = "getRedundantNamedPathParams";
        stringArray[743] = "getRedundantUnnamedPathParamValues";
        stringArray[744] = "size";
        stringArray[745] = "size";
        stringArray[746] = "plus";
        stringArray[747] = "join";
        stringArray[748] = "entrySet";
        stringArray[749] = "plus";
        stringArray[750] = "plus";
        stringArray[751] = "plus";
        stringArray[752] = "join";
        stringArray[753] = "isEmpty";
        stringArray[754] = "getUndefinedPathParamPlaceholders";
        stringArray[755] = "join";
        stringArray[756] = "getUndefinedPathParamPlaceholders";
        stringArray[757] = "<$constructor$>";
        stringArray[758] = "toURI";
        stringArray[759] = "getUri";
        stringArray[760] = "<$constructor$>";
        stringArray[761] = "<$constructor$>";
        stringArray[762] = "toLowerCase";
        stringArray[763] = "getScheme";
        stringArray[764] = "isUserConfigured";
        stringArray[765] = "getSSLConfig";
        stringArray[766] = "toLowerCase";
        stringArray[767] = "getScheme";
        stringArray[768] = "isUserConfigured";
        stringArray[769] = "getSSLConfig";
        stringArray[770] = "applyContentDecoders";
        stringArray[771] = "contentDecoders";
        stringArray[772] = "getDecoderConfig";
        stringArray[773] = "<$constructor$>";
        stringArray[774] = "applyRedirectConfig";
        stringArray[775] = "getRedirectConfig";
        stringArray[776] = "applyHttpClientConfig";
        stringArray[777] = "getHttpClientConfig";
        stringArray[778] = "applyEncoderConfig";
        stringArray[779] = "getEncoderConfig";
        stringArray[780] = "applySessionConfig";
        stringArray[781] = "getSessionConfig";
        stringArray[782] = "applyRedirectConfig";
        stringArray[783] = "getRedirectConfig";
        stringArray[784] = "applyHttpClientConfig";
        stringArray[785] = "getHttpClientConfig";
        stringArray[786] = "applyEncoderConfig";
        stringArray[787] = "getEncoderConfig";
        stringArray[788] = "applySessionConfig";
        stringArray[789] = "getSessionConfig";
        stringArray[790] = "isEmpty";
        stringArray[791] = "getParams";
        stringArray[792] = "client";
        stringArray[793] = "each";
        stringArray[794] = "toArray";
        stringArray[795] = "collect";
        stringArray[796] = "setContentEncoding";
        stringArray[797] = "isSessionIdValueDefined";
        stringArray[798] = "hasCookieWithName";
        stringArray[799] = "sessionIdName";
        stringArray[800] = "cookie";
        stringArray[801] = "sessionIdName";
        stringArray[802] = "sessionIdValue";
        stringArray[803] = "isSessionIdValueDefined";
        stringArray[804] = "hasCookieWithName";
        stringArray[805] = "sessionIdName";
        stringArray[806] = "cookie";
        stringArray[807] = "sessionIdName";
        stringArray[808] = "sessionIdValue";
        stringArray[809] = "setEncoderConfig";
        stringArray[810] = "encoders";
        stringArray[811] = "each";
        stringArray[812] = "plus";
        stringArray[813] = "params";
        stringArray[814] = "putIfAbsent";
        stringArray[815] = "ALLOW_CIRCULAR_REDIRECTS";
        stringArray[816] = "allowsCircularRedirects";
        stringArray[817] = "putIfAbsent";
        stringArray[818] = "HANDLE_REDIRECTS";
        stringArray[819] = "followsRedirects";
        stringArray[820] = "putIfAbsent";
        stringArray[821] = "MAX_REDIRECTS";
        stringArray[822] = "maxRedirects";
        stringArray[823] = "putIfAbsent";
        stringArray[824] = "REJECT_RELATIVE_REDIRECT";
        stringArray[825] = "rejectRelativeRedirects";
        stringArray[826] = "containsKey";
        stringArray[827] = "put";
        stringArray[828] = "hasFormParams";
        stringArray[829] = "equalsIgnoreCase";
        stringArray[830] = "name";
        stringArray[831] = "GET";
        stringArray[832] = "equalsIgnoreCase";
        stringArray[833] = "name";
        stringArray[834] = "POST";
        stringArray[835] = "mergeMapsAndRetainOrder";
        stringArray[836] = "isEmpty";
        stringArray[837] = "hasFormParams";
        stringArray[838] = "equalsIgnoreCase";
        stringArray[839] = "name";
        stringArray[840] = "GET";
        stringArray[841] = "equalsIgnoreCase";
        stringArray[842] = "name";
        stringArray[843] = "POST";
        stringArray[844] = "mergeMapsAndRetainOrder";
        stringArray[845] = "isEmpty";
        stringArray[846] = "<$constructor$>";
        stringArray[847] = "putAll";
        stringArray[848] = "putAll";
        stringArray[849] = "getHeaders";
        stringArray[850] = "each";
        stringArray[851] = "createFormParamBody";
        stringArray[852] = "isFullyQualified";
        stringArray[853] = "getPath";
        stringArray[854] = "<$constructor$>";
        stringArray[855] = "<$constructor$>";
        stringArray[856] = "getPath";
        stringArray[857] = "<$constructor$>";
        stringArray[858] = "getPath";
        stringArray[859] = "mergeAndRemoveDoubleSlash";
        stringArray[860] = "mergeAndRemoveDoubleSlash";
        stringArray[861] = "isEmpty";
        stringArray[862] = "hasFormParams";
        stringArray[863] = "convertFormParamsToMultiPartParams";
        stringArray[864] = "getValue";
        stringArray[865] = "headers";
        stringArray[866] = "getContentTypeWithoutCharset";
        stringArray[867] = "startsWith";
        stringArray[868] = "toLowerCase";
        stringArray[869] = "substringAfter";
        stringArray[870] = "contains";
        stringArray[871] = "toLowerCase";
        stringArray[872] = "substringBefore";
        stringArray[873] = "substringAfter";
        stringArray[874] = "<$constructor$>";
        stringArray[875] = "getCharsetFromContentType";
        stringArray[876] = "isBlank";
        stringArray[877] = "defaultCharset";
        stringArray[878] = "getMultiPartConfig";
        stringArray[879] = "restAssuredConfig";
        stringArray[880] = "getBoundaryFromContentType";
        stringArray[881] = "defaultBoundary";
        stringArray[882] = "getMultiPartConfig";
        stringArray[883] = "restAssuredConfig";
        stringArray[884] = "generateBoundary";
        stringArray[885] = "removeHeader";
        stringArray[886] = "contentType";
        stringArray[887] = "plus";
        stringArray[888] = "plus";
        stringArray[889] = "plus";
        stringArray[890] = "httpMultipartMode";
        stringArray[891] = "httpClientConfig";
        stringArray[892] = "putAt";
        stringArray[893] = "encoders";
        stringArray[894] = "join";
        stringArray[895] = "plus";
        stringArray[896] = "plus";
        stringArray[897] = "plus";
        stringArray[898] = "plus";
        stringArray[899] = "<$constructor$>";
        stringArray[900] = "plus";
        stringArray[901] = "nextInt";
        stringArray[902] = "join";
        stringArray[903] = "collect";
        stringArray[904] = "mergeMapsAndRetainOrder";
        stringArray[905] = "each";
        stringArray[906] = "clear";
        stringArray[907] = "clear";
        stringArray[908] = "mergeMapsAndRetainOrder";
        stringArray[909] = "equals";
        stringArray[910] = "name";
        stringArray[911] = "GET";
        stringArray[912] = "mergeMapsAndRetainOrder";
        stringArray[913] = "request";
        stringArray[914] = "isEmpty";
        stringArray[915] = "isEmpty";
        stringArray[916] = "isEmpty";
        stringArray[917] = "isEmpty";
        stringArray[918] = "equals";
        stringArray[919] = "name";
        stringArray[920] = "POST";
        stringArray[921] = "size";
        stringArray[922] = "size";
        stringArray[923] = "equals";
        stringArray[924] = "name";
        stringArray[925] = "POST";
        stringArray[926] = "size";
        stringArray[927] = "size";
        stringArray[928] = "contains";
        stringArray[929] = "indexOf";
        stringArray[930] = "substring";
        stringArray[931] = "plus";
        stringArray[932] = "split";
        stringArray[933] = "each";
        stringArray[934] = "substring";
        stringArray[935] = "toString";
        stringArray[936] = "defineRequestContentType";
        stringArray[937] = "getValue";
        stringArray[938] = "headers";
        stringArray[939] = "size";
        stringArray[940] = "plus";
        stringArray[941] = "defaultSubtype";
        stringArray[942] = "getMultiPartConfig";
        stringArray[943] = "restAssuredConfig";
        stringArray[944] = "equals";
        stringArray[945] = "name";
        stringArray[946] = "GET";
        stringArray[947] = "isEmpty";
        stringArray[948] = "URLENC";
        stringArray[949] = "mayHaveBody";
        stringArray[950] = "URLENC";
        stringArray[951] = "BINARY";
        stringArray[952] = "TEXT";
        stringArray[953] = "size";
        stringArray[954] = "plus";
        stringArray[955] = "defaultSubtype";
        stringArray[956] = "getMultiPartConfig";
        stringArray[957] = "restAssuredConfig";
        stringArray[958] = "equals";
        stringArray[959] = "name";
        stringArray[960] = "GET";
        stringArray[961] = "isEmpty";
        stringArray[962] = "URLENC";
        stringArray[963] = "mayHaveBody";
        stringArray[964] = "URLENC";
        stringArray[965] = "BINARY";
        stringArray[966] = "TEXT";
        stringArray[967] = "shouldAppendCharsetToContentType";
        stringArray[968] = "findEncoderCharsetOrReturnDefault";
        stringArray[969] = "toString";
        stringArray[970] = "plus";
        stringArray[971] = "plus";
        stringArray[972] = "plus";
        stringArray[973] = "plus";
        stringArray[974] = "withCharset";
        stringArray[975] = "startsWith";
        stringArray[976] = "toString";
        stringArray[977] = "contains";
        stringArray[978] = "toString";
        stringArray[979] = "shouldAppendDefaultContentCharsetToContentTypeIfUndefined";
        stringArray[980] = "encoderConfig";
        stringArray[981] = "restAssuredConfig";
        stringArray[982] = "isApplicationJsonContentTypeWithDefaultCharsetDefined";
        stringArray[983] = "containsIgnoreCase";
        stringArray[984] = "toString";
        stringArray[985] = "equalsIgnoreCase";
        stringArray[986] = "toString";
        stringArray[987] = "startsWith";
        stringArray[988] = "toString";
        stringArray[989] = "contains";
        stringArray[990] = "toString";
        stringArray[991] = "shouldAppendDefaultContentCharsetToContentTypeIfUndefined";
        stringArray[992] = "encoderConfig";
        stringArray[993] = "restAssuredConfig";
        stringArray[994] = "isApplicationJsonContentTypeWithDefaultCharsetDefined";
        stringArray[995] = "containsIgnoreCase";
        stringArray[996] = "toString";
        stringArray[997] = "equalsIgnoreCase";
        stringArray[998] = "toString";
        stringArray[999] = "startsWith";
        stringArray[1000] = "toLowerCase";
        stringArray[1001] = "toString";
        stringArray[1002] = "getEncoderConfig";
        stringArray[1003] = "getConfig";
        stringArray[1004] = "defaultCharsetForContentTypeOrNull";
        stringArray[1005] = "toString";
        stringArray[1006] = "name";
        stringArray[1007] = "UTF_8";
        stringArray[1008] = "isUserConfigured";
        stringArray[1009] = "name";
        stringArray[1010] = "UTF_8";
        stringArray[1011] = "isUserConfigured";
        stringArray[1012] = "isFullyQualified";
        stringArray[1013] = "<$constructor$>";
        stringArray[1014] = "getTargetUriFromUrl";
        stringArray[1015] = "isFullyQualified";
        stringArray[1016] = "<$constructor$>";
        stringArray[1017] = "getTargetUriFromUrl";
        stringArray[1018] = "UNDEFINED_PORT";
        stringArray[1019] = "<$constructor$>";
        stringArray[1020] = "getTargetUriFromUrl";
        stringArray[1021] = "isFullyQualified";
        stringArray[1022] = "<$constructor$>";
        stringArray[1023] = "getTargetUriFromUrl";
        stringArray[1024] = "UNDEFINED_PORT";
        stringArray[1025] = "getProtocol";
        stringArray[1026] = "UNDEFINED_PORT";
        stringArray[1027] = "equalsIgnoreCase";
        stringArray[1028] = "UNDEFINED_PORT";
        stringArray[1029] = "equalsIgnoreCase";
        stringArray[1030] = "append";
        stringArray[1031] = "append";
        stringArray[1032] = "<$constructor$>";
        stringArray[1033] = "getAuthority";
        stringArray[1034] = "UNDEFINED_PORT";
        stringArray[1035] = "UNDEFINED_PORT";
        stringArray[1036] = "hasPortDefined";
        stringArray[1037] = "append";
        stringArray[1038] = "append";
        stringArray[1039] = "isFullyQualified";
        stringArray[1040] = "toString";
        stringArray[1041] = "hasAuthorityEqualToLocalhost";
        stringArray[1042] = "append";
        stringArray[1043] = "append";
        stringArray[1044] = "hasPortDefined";
        stringArray[1045] = "append";
        stringArray[1046] = "append";
        stringArray[1047] = "isFullyQualified";
        stringArray[1048] = "toString";
        stringArray[1049] = "hasAuthorityEqualToLocalhost";
        stringArray[1050] = "append";
        stringArray[1051] = "append";
        stringArray[1052] = "toString";
        stringArray[1053] = "equalsIgnoreCase";
        stringArray[1054] = "trim";
        stringArray[1055] = "getAuthority";
        stringArray[1056] = "getPort";
        stringArray[1057] = "getPort";
        stringArray[1058] = "serializeIfNeeded";
        stringArray[1059] = "requestContentType";
        stringArray[1060] = "isSerializableCandidate";
        stringArray[1061] = "serialize";
        stringArray[1062] = "findEncoderCharsetOrReturnDefault";
        stringArray[1063] = "objectMappingConfig";
        stringArray[1064] = "getEncoderConfig";
        stringArray[1065] = "restAssuredConfig";
        stringArray[1066] = "toString";
        stringArray[1067] = "notNull";
        stringArray[1068] = "notNull";
        stringArray[1069] = "trimToNull";
        stringArray[1070] = "notNull";
        stringArray[1071] = "toUpperCase";
        stringArray[1072] = "trim";
        stringArray[1073] = "length";
        stringArray[1074] = "getAt";
        stringArray[1075] = "leftShift";
        stringArray[1076] = "next";
        stringArray[1077] = "isEmpty";
        stringArray[1078] = "size";
        stringArray[1079] = "<$constructor$>";
        stringArray[1080] = "join";
        stringArray[1081] = "buildUnnamedPathParameterTuples";
        stringArray[1082] = "length";
        stringArray[1083] = "leftShift";
        stringArray[1084] = "isEmpty";
        stringArray[1085] = "size";
        stringArray[1086] = "<$constructor$>";
        stringArray[1087] = "join";
        stringArray[1088] = "buildUnnamedPathParameterTuples";
        stringArray[1089] = "removeAll";
        stringArray[1090] = "add";
        stringArray[1091] = "<$constructor$>";
        stringArray[1092] = "userName";
        stringArray[1093] = "password";
        stringArray[1094] = "config";
        stringArray[1095] = "sessionConfig";
        stringArray[1096] = "csrfConfig";
        stringArray[1097] = "add";
        stringArray[1098] = "<$constructor$>";
        stringArray[1099] = "csrfConfig";
        stringArray[1100] = "restAssuredConfig";
        stringArray[1101] = "getLogConfig";
        stringArray[1102] = "restAssuredConfig";
        stringArray[1103] = "isLoggingOfRequestAndResponseIfValidationFailsEnabled";
        stringArray[1104] = "any";
        stringArray[1105] = "ifValidationFails";
        stringArray[1106] = "log";
        stringArray[1107] = "logDetailOfRequestAndResponseIfValidationFails";
        stringArray[1108] = "isPrettyPrintingEnabled";
        stringArray[1109] = "any";
        stringArray[1110] = "ifValidationFails";
        stringArray[1111] = "log";
        stringArray[1112] = "logDetailOfRequestAndResponseIfValidationFails";
        stringArray[1113] = "isPrettyPrintingEnabled";
        stringArray[1114] = "config";
        stringArray[1115] = "<$constructor$>";
        stringArray[1116] = "any";
        stringArray[1117] = "getLogDetail";
        stringArray[1118] = "add";
        stringArray[1119] = "<$constructor$>";
        stringArray[1120] = "getLogDetail";
        stringArray[1121] = "isPrettyPrintingEnabled";
        stringArray[1122] = "defaultStream";
        stringArray[1123] = "any";
        stringArray[1124] = "getLogDetail";
        stringArray[1125] = "add";
        stringArray[1126] = "<$constructor$>";
        stringArray[1127] = "getLogDetail";
        stringArray[1128] = "isPrettyPrintingEnabled";
        stringArray[1129] = "defaultStream";
        stringArray[1130] = "toSorted";
        stringArray[1131] = "any";
        stringArray[1132] = "leftShift";
        stringArray[1133] = "<$constructor$>";
        stringArray[1134] = "leftShift";
        stringArray[1135] = "<$constructor$>";
        stringArray[1136] = "newFilterContext";
        stringArray[1137] = "assertionClosure";
        stringArray[1138] = "iterator";
        stringArray[1139] = "httpClientInstance";
        stringArray[1140] = "httpClientConfig";
        stringArray[1141] = "next";
        stringArray[1142] = "validate";
        stringArray[1143] = "assertionClosure";
        stringArray[1144] = "applyPathParamsAndSendRequest";
        stringArray[1145] = "name";
        stringArray[1146] = "notNull";
        stringArray[1147] = "length";
        stringArray[1148] = "<$constructor$>";
        stringArray[1149] = "getUndefinedPathParamPlaceholders";
        stringArray[1150] = "<$constructor$>";
        stringArray[1151] = "length";
        stringArray[1152] = "serializeIfNeeded";
        stringArray[1153] = "getAt";
        stringArray[1154] = "size";
        stringArray[1155] = "get";
        stringArray[1156] = "add";
        stringArray[1157] = "<$constructor$>";
        stringArray[1158] = "next";
        stringArray[1159] = "length";
        stringArray[1160] = "<$constructor$>";
        stringArray[1161] = "getUndefinedPathParamPlaceholders";
        stringArray[1162] = "<$constructor$>";
        stringArray[1163] = "length";
        stringArray[1164] = "serializeIfNeeded";
        stringArray[1165] = "size";
        stringArray[1166] = "get";
        stringArray[1167] = "add";
        stringArray[1168] = "<$constructor$>";
        stringArray[1169] = "size";
        stringArray[1170] = "getTargetURI";
        stringArray[1171] = "getTargetPath";
        stringArray[1172] = "substringBefore";
        stringArray[1173] = "endsWith";
        stringArray[1174] = "substringAfter";
        stringArray[1175] = "withDefault";
        stringArray[1176] = "indexOf";
        stringArray[1177] = "indexOf";
        stringArray[1178] = "replace";
        stringArray[1179] = "inject";
        stringArray[1180] = "split";
        stringArray[1181] = "curry";
        stringArray[1182] = "replace";
        stringArray[1183] = "encode";
        stringArray[1184] = "QUERY";
        stringArray[1185] = "plus";
        stringArray[1186] = "matches";
        stringArray[1187] = "substring";
        stringArray[1188] = "inject";
        stringArray[1189] = "split";
        stringArray[1190] = "curry";
        stringArray[1191] = "plus";
        stringArray[1192] = "isEmpty";
        stringArray[1193] = "plus";
        stringArray[1194] = "plus";
        stringArray[1195] = "get";
        stringArray[1196] = "getAt";
        stringArray[1197] = "next";
        stringArray[1198] = "putAt";
        stringArray[1199] = "get";
        stringArray[1200] = "toString";
        stringArray[1201] = "<$constructor$>";
        stringArray[1202] = "iterator";
        stringArray[1203] = "entrySet";
        stringArray[1204] = "append";
        stringArray[1205] = "encode";
        stringArray[1206] = "getKey";
        stringArray[1207] = "BODY";
        stringArray[1208] = "getValue";
        stringArray[1209] = "append";
        stringArray[1210] = "append";
        stringArray[1211] = "handleMultiValueParamsIfNeeded";
        stringArray[1212] = "append";
        stringArray[1213] = "isEmpty";
        stringArray[1214] = "deleteCharAt";
        stringArray[1215] = "minus";
        stringArray[1216] = "length";
        stringArray[1217] = "toString";
        stringArray[1218] = "toString";
        stringArray[1219] = "BODY";
        stringArray[1220] = "defaultContentCharset";
        stringArray[1221] = "encoderConfig";
        stringArray[1222] = "getValue";
        stringArray[1223] = "headers";
        stringArray[1224] = "getCharsetFromContentType";
        stringArray[1225] = "hasDefaultCharsetForContentType";
        stringArray[1226] = "encoderConfig";
        stringArray[1227] = "defaultCharsetForContentType";
        stringArray[1228] = "encoderConfig";
        stringArray[1229] = "defaultQueryParameterCharset";
        stringArray[1230] = "encoderConfig";
        stringArray[1231] = "encode";
        stringArray[1232] = "getValue";
        stringArray[1233] = "encode";
        stringArray[1234] = "getKey";
        stringArray[1235] = "BODY";
        stringArray[1236] = "<$constructor$>";
        stringArray[1237] = "eachWithIndex";
        stringArray[1238] = "toString";
        stringArray[1239] = "encode";
        stringArray[1240] = "BODY";
        stringArray[1241] = "partiallyApplyPathParams";
        stringArray[1242] = "collect";
        stringArray[1243] = "getDerivedPath";
        stringArray[1244] = "getPath";
        stringArray[1245] = "partiallyApplyPathParams";
        stringArray[1246] = "collect";
        stringArray[1247] = "getURI";
        stringArray[1248] = "<$constructor$>";
        stringArray[1249] = "getTargetURI";
        stringArray[1250] = "getPort";
        stringArray[1251] = "unmodifiableMap";
        stringArray[1252] = "getNamedPathParams";
        stringArray[1253] = "<$constructor$>";
        stringArray[1254] = "putAll";
        stringArray[1255] = "findAll";
        stringArray[1256] = "getUnnamedPathParams";
        stringArray[1257] = "unmodifiableMap";
        stringArray[1258] = "unmodifiableMap";
        stringArray[1259] = "inject";
        stringArray[1260] = "findAll";
        stringArray[1261] = "unmodifiableMap";
        stringArray[1262] = "unmodifiableList";
        stringArray[1263] = "emptyList";
        stringArray[1264] = "collect";
        stringArray[1265] = "findAll";
        stringArray[1266] = "unmodifiableList";
        stringArray[1267] = "emptyList";
        stringArray[1268] = "collect";
        stringArray[1269] = "findAll";
        stringArray[1270] = "unmodifiableMap";
        stringArray[1271] = "unmodifiableMap";
        stringArray[1272] = "collect";
        stringArray[1273] = "unmodifiableList";
        stringArray[1274] = "notNull";
        stringArray[1275] = "trimToEmpty";
        stringArray[1276] = "partiallyApplyPathParams";
        stringArray[1277] = "collect";
        stringArray[1278] = "getPlaceholders";
        stringArray[1279] = "getTargetPath";
        stringArray[1280] = "contains";
        stringArray[1281] = "substringAfter";
        stringArray[1282] = "getPlaceholders";
        stringArray[1283] = "getContentType";
        stringArray[1284] = "getValue";
        stringArray[1285] = "clear";
        stringArray[1286] = "notNull";
        stringArray[1287] = "removeAll";
        stringArray[1288] = "<$constructor$>";
        stringArray[1289] = "schemeRegistry";
        stringArray[1290] = "connectionManager";
        stringArray[1291] = "client";
        stringArray[1292] = "<$constructor$>";
        stringArray[1293] = "default";
        stringArray[1294] = "client";
        stringArray[1295] = "hasAuth";
        stringArray[1296] = "<$constructor$>";
        stringArray[1297] = "<$constructor$>";
        stringArray[1298] = "host";
        stringArray[1299] = "port";
        stringArray[1300] = "<$constructor$>";
        stringArray[1301] = "getHostAddress";
        stringArray[1302] = "getAddress";
        stringArray[1303] = "getPort";
        stringArray[1304] = "<$constructor$>";
        stringArray[1305] = "username";
        stringArray[1306] = "password";
        stringArray[1307] = "setCredentials";
        stringArray[1308] = "setCredentialsProvider";
        stringArray[1309] = "client";
        stringArray[1310] = "isFullyQualified";
        stringArray[1311] = "getTargetURI";
        stringArray[1312] = "substringBefore";
        stringArray[1313] = "getTargetPath";
        stringArray[1314] = "mergeAndRemoveDoubleSlash";
        stringArray[1315] = "mergeAndRemoveDoubleSlash";
        stringArray[1316] = "getCharsetFromContentType";
        stringArray[1317] = "config";
        stringArray[1318] = "<$constructor$>";
        stringArray[1319] = "getEncoderConfig";
        stringArray[1320] = "config";
        stringArray[1321] = "hasDefaultCharsetForContentType";
        stringArray[1322] = "defaultCharsetForContentType";
        stringArray[1323] = "defaultContentCharset";
        stringArray[1324] = "config";
        stringArray[1325] = "<$constructor$>";
        stringArray[1326] = "getEncoderConfig";
        stringArray[1327] = "config";
        stringArray[1328] = "hasDefaultCharsetForContentType";
        stringArray[1329] = "defaultCharsetForContentType";
        stringArray[1330] = "defaultContentCharset";
        stringArray[1331] = "config";
        stringArray[1332] = "objectMapperConfig";
        stringArray[1333] = "getObjectMapperConfig";
        stringArray[1334] = "config";
        stringArray[1335] = "config";
        stringArray[1336] = "objectMapperConfig";
        stringArray[1337] = "getObjectMapperConfig";
        stringArray[1338] = "config";
        stringArray[1339] = "config";
        stringArray[1340] = "httpClientConfig";
        stringArray[1341] = "getHttpClientConfig";
        stringArray[1342] = "config";
        stringArray[1343] = "config";
        stringArray[1344] = "httpClientConfig";
        stringArray[1345] = "getHttpClientConfig";
        stringArray[1346] = "config";
        stringArray[1347] = "config";
        stringArray[1348] = "connectionConfig";
        stringArray[1349] = "getConnectionConfig";
        stringArray[1350] = "config";
        stringArray[1351] = "config";
        stringArray[1352] = "connectionConfig";
        stringArray[1353] = "getConnectionConfig";
        stringArray[1354] = "config";
        stringArray[1355] = "config";
        stringArray[1356] = "encoderConfig";
        stringArray[1357] = "getEncoderConfig";
        stringArray[1358] = "config";
        stringArray[1359] = "config";
        stringArray[1360] = "encoderConfig";
        stringArray[1361] = "getEncoderConfig";
        stringArray[1362] = "config";
        stringArray[1363] = "config";
        stringArray[1364] = "sessionConfig";
        stringArray[1365] = "getSessionConfig";
        stringArray[1366] = "config";
        stringArray[1367] = "config";
        stringArray[1368] = "sessionConfig";
        stringArray[1369] = "getSessionConfig";
        stringArray[1370] = "config";
        stringArray[1371] = "config";
        stringArray[1372] = "csrfConfig";
        stringArray[1373] = "getCsrfConfig";
        stringArray[1374] = "config";
        stringArray[1375] = "config";
        stringArray[1376] = "csrfConfig";
        stringArray[1377] = "getCsrfConfig";
        stringArray[1378] = "config";
        stringArray[1379] = "config";
        stringArray[1380] = "<$constructor$>";
        stringArray[1381] = "compile";
        stringArray[1382] = "plus";
        stringArray[1383] = "plus";
        stringArray[1384] = "quote";
        stringArray[1385] = "quote";
        stringArray[1386] = "matcher";
        stringArray[1387] = "<$constructor$>";
        stringArray[1388] = "find";
        stringArray[1389] = "leftShift";
        stringArray[1390] = "trim";
        stringArray[1391] = "group";
        stringArray[1392] = "unmodifiableList";
        stringArray[1393] = "<$constructor$>";
        stringArray[1394] = "getPath";
        stringArray[1395] = "generateRequestUriForLogging";
        stringArray[1396] = "getPathParamPlaceholders";
        stringArray[1397] = "asImmutable";
        stringArray[1398] = "findAll";
        stringArray[1399] = "getNamedPathParams";
        stringArray[1400] = "getPathParams";
        stringArray[1401] = "plus";
        stringArray[1402] = "size";
        stringArray[1403] = "minus";
        stringArray[1404] = "getPathParamPlaceholders";
        stringArray[1405] = "keySet";
        stringArray[1406] = "max";
        stringArray[1407] = "minus";
        stringArray[1408] = "size";
        stringArray[1409] = "getUnnamedPathParamValues";
        stringArray[1410] = "size";
        stringArray[1411] = "getPathParamPlaceholders";
        stringArray[1412] = "asImmutable";
        stringArray[1413] = "minus";
        stringArray[1414] = "getUnnamedPathParamValues";
        stringArray[1415] = "values";
        stringArray[1416] = "plus";
        stringArray[1417] = "size";
        stringArray[1418] = "minus";
        stringArray[1419] = "getPathParamPlaceholders";
        stringArray[1420] = "keySet";
        stringArray[1421] = "max";
        stringArray[1422] = "minus";
        stringArray[1423] = "size";
        stringArray[1424] = "getUnnamedPathParamValues";
        stringArray[1425] = "size";
        stringArray[1426] = "getPathParamPlaceholders";
        stringArray[1427] = "asImmutable";
        stringArray[1428] = "minus";
        stringArray[1429] = "getUnnamedPathParamValues";
        stringArray[1430] = "values";
        stringArray[1431] = "unmodifiableList";
        stringArray[1432] = "emptyList";
        stringArray[1433] = "remove";
        stringArray[1434] = "add";
        stringArray[1435] = "<$constructor$>";
        stringArray[1436] = "toUpperCase";
        stringArray[1437] = "toUpperCase";
        stringArray[1438] = "getOrder";
        stringArray[1439] = "DEFAULT_PRECEDENCE";
        stringArray[1440] = "plus";
        stringArray[1441] = "plus";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[1442];
        RequestSpecificationImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(RequestSpecificationImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = RequestSpecificationImpl.$createCallSiteArray();
            $callSiteArray = new SoftReference<CallSiteArray>(callSiteArray);
        }
        return callSiteArray.array;
    }

    private static /* synthetic */ Class $get$$class$io$restassured$internal$RestAssuredHttpBuilder() {
        Class clazz = $class$io$restassured$internal$RestAssuredHttpBuilder;
        if (clazz == null) {
            clazz = $class$io$restassured$internal$RestAssuredHttpBuilder = RequestSpecificationImpl.class$("io.restassured.internal.RestAssuredHttpBuilder");
        }
        return clazz;
    }

    static /* synthetic */ Class class$(String string) {
        try {
            return Class.forName(string);
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw new NoClassDefFoundError(classNotFoundException.getMessage());
        }
    }

    public class 1
    implements ParameterUpdater.Serializer,
    GroovyObject {
        public /* synthetic */ RequestSpecificationImpl this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 1(RequestSpecificationImpl p0) {
            MetaClass metaClass;
            RequestSpecificationImpl requestSpecificationImpl;
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            this.this$0 = requestSpecificationImpl = p0;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        public String serializeIfNeeded(Object value) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[0].call((Object)this.this$0, value), String.class);
        }

        public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$1(String name, Object value) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$1(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return this.this$0.this$dist$invoke$1(name, args);
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            this.this$0.this$dist$set$1(name, val);
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return this.this$0.this$dist$get$1(name);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 1.class) {
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
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            1.__$swapInit();
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

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[1];
            stringArray[0] = "serializeIfNeeded";
            return new CallSiteArray(1.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 1.$createCallSiteArray();
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

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private final class EncodingTarget
    extends Enum<EncodingTarget>
    implements GroovyObject {
        public static final /* enum */ EncodingTarget BODY;
        public static final /* enum */ EncodingTarget QUERY;
        public static final EncodingTarget MIN_VALUE;
        public static final EncodingTarget MAX_VALUE;
        private static final /* synthetic */ EncodingTarget[] $VALUES;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;
        private static /* synthetic */ SoftReference $callSiteArray;

        public EncodingTarget() {
            MetaClass metaClass;
            CallSite[] callSiteArray = EncodingTarget.$getCallSiteArray();
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        public static final EncodingTarget[] values() {
            CallSite[] callSiteArray = EncodingTarget.$getCallSiteArray();
            return (EncodingTarget[])ScriptBytecodeAdapter.castToType($VALUES.clone(), EncodingTarget[].class);
        }

        public /* synthetic */ EncodingTarget next() {
            CallSite[] callSiteArray = EncodingTarget.$getCallSiteArray();
            Object ordinal = callSiteArray[0].call(callSiteArray[1].callCurrent(this));
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                if (ScriptBytecodeAdapter.compareGreaterThanEqual(ordinal, callSiteArray[2].call($VALUES))) {
                    int n = 0;
                    ordinal = n;
                }
            } else if (ScriptBytecodeAdapter.compareGreaterThanEqual(ordinal, callSiteArray[3].call($VALUES))) {
                int n = 0;
                ordinal = n;
            }
            return (EncodingTarget)ScriptBytecodeAdapter.castToType(callSiteArray[4].call((Object)$VALUES, ordinal), EncodingTarget.class);
        }

        public /* synthetic */ EncodingTarget previous() {
            CallSite[] callSiteArray = EncodingTarget.$getCallSiteArray();
            Object ordinal = callSiteArray[5].call(callSiteArray[6].callCurrent(this));
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                if (ScriptBytecodeAdapter.compareLessThan(ordinal, 0)) {
                    Object object;
                    ordinal = object = callSiteArray[7].call(callSiteArray[8].call($VALUES), 1);
                }
            } else if (ScriptBytecodeAdapter.compareLessThan(ordinal, 0)) {
                Object object;
                ordinal = object = callSiteArray[9].call(callSiteArray[10].call($VALUES), 1);
            }
            return (EncodingTarget)ScriptBytecodeAdapter.castToType(callSiteArray[11].call((Object)$VALUES, ordinal), EncodingTarget.class);
        }

        public static EncodingTarget valueOf(String name) {
            CallSite[] callSiteArray = EncodingTarget.$getCallSiteArray();
            return (EncodingTarget)ScriptBytecodeAdapter.castToType(callSiteArray[12].callStatic(ContentType.class, EncodingTarget.class, name), EncodingTarget.class);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public static final /* synthetic */ EncodingTarget $INIT(Object ... para) {
            CallSite[] callSiteArray = EncodingTarget.$getCallSiteArray();
            Object[] objectArray = ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{para}, new int[]{0});
            int n = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(objectArray, 1, EncodingTarget.class);
            Object[] objectArray2 = objectArray;
            if ((n & 1) != 0) {
                objectArray2 = (Object[])objectArray2[0];
            }
            switch (n >> 8) {
                case 0: {
                    return new EncodingTarget();
                }
                default: {
                    throw new IllegalArgumentException("illegal constructor number");
                }
            }
        }

        static {
            EncodingTarget encodingTarget;
            EncodingTarget encodingTarget2;
            EncodingTarget.__$swapInit();
            Object object = EncodingTarget.$getCallSiteArray()[13].callStatic(EncodingTarget.class, "BODY", 0);
            BODY = (EncodingTarget)ScriptBytecodeAdapter.castToType(object, EncodingTarget.class);
            Object object2 = EncodingTarget.$getCallSiteArray()[14].callStatic(EncodingTarget.class, "QUERY", 1);
            QUERY = (EncodingTarget)ScriptBytecodeAdapter.castToType(object2, EncodingTarget.class);
            MIN_VALUE = encodingTarget2 = BODY;
            MAX_VALUE = encodingTarget = QUERY;
            EncodingTarget[] encodingTargetArray = new EncodingTarget[]{BODY, QUERY};
            $VALUES = encodingTargetArray;
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != EncodingTarget.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return classInfo.getMetaClass();
        }

        @Override
        public /* synthetic */ MetaClass getMetaClass() {
            MetaClass metaClass = this.metaClass;
            if (metaClass != null) {
                return metaClass;
            }
            this.metaClass = this.$getStaticMetaClass();
            return this.metaClass;
        }

        @Override
        public /* synthetic */ void setMetaClass(MetaClass metaClass) {
            this.metaClass = metaClass;
        }

        @Override
        public /* synthetic */ Object invokeMethod(String string, Object object) {
            return this.getMetaClass().invokeMethod((Object)this, string, object);
        }

        @Override
        public /* synthetic */ Object getProperty(String string) {
            return this.getMetaClass().getProperty(this, string);
        }

        @Override
        public /* synthetic */ void setProperty(String string, Object object) {
            this.getMetaClass().setProperty(this, string, object);
        }

        public static /* synthetic */ void __$swapInit() {
            CallSite[] callSiteArray = EncodingTarget.$getCallSiteArray();
            $callSiteArray = null;
        }

        public /* synthetic */ void super$1$notify() {
            super.notify();
        }

        public /* synthetic */ int super$2$hashCode() {
            return super.hashCode();
        }

        public /* synthetic */ Object super$2$clone() {
            return super.clone();
        }

        public /* synthetic */ int super$2$ordinal() {
            return super.ordinal();
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

        public /* synthetic */ int super$2$compareTo(Object object) {
            return super.compareTo(object);
        }

        public /* synthetic */ boolean super$2$equals(Object object) {
            return super.equals(object);
        }

        public /* synthetic */ void super$2$finalize() {
            super.finalize();
        }

        public /* synthetic */ Class super$1$getClass() {
            return super.getClass();
        }

        public /* synthetic */ String super$2$name() {
            return super.name();
        }

        public /* synthetic */ String super$2$toString() {
            return super.toString();
        }

        public /* synthetic */ Class super$2$getDeclaringClass() {
            return super.getDeclaringClass();
        }

        public /* synthetic */ int super$2$compareTo(Enum enum_) {
            return super.compareTo(enum_);
        }

        public /* synthetic */ void super$1$notifyAll() {
            super.notifyAll();
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
            stringArray[0] = "next";
            stringArray[1] = "ordinal";
            stringArray[2] = "size";
            stringArray[3] = "size";
            stringArray[4] = "getAt";
            stringArray[5] = "previous";
            stringArray[6] = "ordinal";
            stringArray[7] = "minus";
            stringArray[8] = "size";
            stringArray[9] = "minus";
            stringArray[10] = "size";
            stringArray[11] = "getAt";
            stringArray[12] = "valueOf";
            stringArray[13] = "$INIT";
            stringArray[14] = "$INIT";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[15];
            EncodingTarget.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(EncodingTarget.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = EncodingTarget.$createCallSiteArray();
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
}

