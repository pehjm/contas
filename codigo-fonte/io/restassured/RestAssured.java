/*
 * Decompiled with CFR 0.152.
 */
package io.restassured;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.authentication.CertAuthScheme;
import io.restassured.authentication.CertificateAuthSettings;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.authentication.FormAuthScheme;
import io.restassured.authentication.NTLMAuthScheme;
import io.restassured.authentication.NoAuthScheme;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.authentication.OAuthScheme;
import io.restassured.authentication.OAuthSignature;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Method;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.internal.TestSpecificationImpl;
import io.restassured.internal.UriValidator;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.log.LogRepository;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class RestAssured {
    private static final String SSL = "SSL";
    private static ResponseParserRegistrar RESPONSE_PARSER_REGISTRAR = new ResponseParserRegistrar();
    public static final String DEFAULT_URI = "http://localhost";
    public static final String DEFAULT_BODY_ROOT_PATH = "";
    public static final int DEFAULT_PORT = 8080;
    public static final int UNDEFINED_PORT = -1;
    public static final String DEFAULT_PATH = "";
    public static final AuthenticationScheme DEFAULT_AUTH = new NoAuthScheme();
    public static final boolean DEFAULT_URL_ENCODING_ENABLED = true;
    public static final String DEFAULT_SESSION_ID_VALUE = null;
    public static String baseURI = "http://localhost";
    public static int port = -1;
    public static String basePath = "";
    public static boolean urlEncodingEnabled = true;
    public static AuthenticationScheme authentication = DEFAULT_AUTH;
    public static RestAssuredConfig config = new RestAssuredConfig();
    public static String rootPath = "";
    public static RequestSpecification requestSpecification = null;
    public static Parser defaultParser = null;
    public static ResponseSpecification responseSpecification = null;
    public static String sessionId = DEFAULT_SESSION_ID_VALUE;
    public static ProxySpecification proxy = null;
    private static List<Filter> filters = new LinkedList<Filter>();

    public static void filters(List<Filter> filters) {
        Validate.notNull(filters, "Filter list cannot be null", new Object[0]);
        RestAssured.filters.addAll(filters);
    }

    public static void filters(Filter filter, Filter ... additionalFilters) {
        Validate.notNull(filter, "Filter cannot be null", new Object[0]);
        filters.add(filter);
        if (additionalFilters != null) {
            Collections.addAll(filters, additionalFilters);
        }
    }

    public static void replaceFiltersWith(List<Filter> filters) {
        Validate.notNull(filters, "Filter list cannot be null", new Object[0]);
        RestAssured.filters.clear();
        RestAssured.filters(filters);
    }

    public static void replaceFiltersWith(Filter filter, Filter ... additionalFilters) {
        Validate.notNull(filter, "Filter cannot be null", new Object[0]);
        filters.clear();
        RestAssured.filters(filter, additionalFilters);
    }

    public static List<Filter> filters() {
        return Collections.unmodifiableList(filters);
    }

    public static ResponseSpecification expect() {
        return RestAssured.createTestSpecification().getResponseSpecification();
    }

    public static RequestSpecification with() {
        return RestAssured.given();
    }

    public static List<Argument> withArgs(Object firstArgument, Object ... additionalArguments) {
        Validate.notNull(firstArgument, "You need to supply at least one argument", new Object[0]);
        LinkedList<Argument> arguments = new LinkedList<Argument>();
        arguments.add(Argument.arg(firstArgument));
        if (additionalArguments != null && additionalArguments.length > 0) {
            for (Object additionalArgument : additionalArguments) {
                arguments.add(Argument.arg(additionalArgument));
            }
        }
        return Collections.unmodifiableList(arguments);
    }

    public static List<Argument> withNoArgs() {
        return Collections.unmodifiableList(Collections.emptyList());
    }

    public static RequestSpecification given() {
        return RestAssured.createTestSpecification().getRequestSpecification();
    }

    public static RequestSender when() {
        return RestAssured.createTestSpecification().getRequestSpecification();
    }

    public static RequestSender given(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        return new TestSpecificationImpl(requestSpecification, responseSpecification);
    }

    public static RequestSpecification given(RequestSpecification requestSpecification) {
        return RestAssured.given().spec(requestSpecification);
    }

    public static Response get(String path, Object ... pathParams) {
        return (Response)RestAssured.given().get(path, pathParams);
    }

    public static Response get(String path, Map<String, ?> pathParams) {
        return (Response)RestAssured.given().get(path, pathParams);
    }

    public static Response post(String path, Object ... pathParams) {
        return (Response)RestAssured.given().post(path, pathParams);
    }

    public static Response post(String path, Map<String, ?> pathParams) {
        return (Response)RestAssured.given().post(path, pathParams);
    }

    public static Response put(String path, Object ... pathParams) {
        return (Response)RestAssured.given().put(path, pathParams);
    }

    public static Response delete(String path, Object ... pathParams) {
        return (Response)RestAssured.given().delete(path, pathParams);
    }

    public static Response delete(String path, Map<String, ?> pathParams) {
        return (Response)RestAssured.given().delete(path, pathParams);
    }

    public static Response head(String path, Object ... pathParams) {
        return (Response)RestAssured.given().head(path, pathParams);
    }

    public static Response head(String path, Map<String, ?> pathParams) {
        return (Response)RestAssured.given().head(path, pathParams);
    }

    public static Response patch(String path, Object ... pathParams) {
        return (Response)RestAssured.given().patch(path, pathParams);
    }

    public static Response patch(String path, Map<String, ?> pathParams) {
        return (Response)RestAssured.given().patch(path, pathParams);
    }

    public static Response options(String path, Object ... pathParams) {
        return (Response)RestAssured.given().options(path, pathParams);
    }

    public static Response options(String path, Map<String, ?> pathParams) {
        return (Response)RestAssured.given().options(path, pathParams);
    }

    public static Response get(URI uri) {
        return (Response)RestAssured.given().get(uri);
    }

    public static Response post(URI uri) {
        return (Response)RestAssured.given().post(uri);
    }

    public static Response put(URI uri) {
        return (Response)RestAssured.given().put(uri);
    }

    public static Response delete(URI uri) {
        return (Response)RestAssured.given().delete(uri);
    }

    public static Response head(URI uri) {
        return (Response)RestAssured.given().head(uri);
    }

    public static Response patch(URI uri) {
        return (Response)RestAssured.given().patch(uri);
    }

    public static Response options(URI uri) {
        return (Response)RestAssured.given().options(uri);
    }

    public static Response get(URL url) {
        return (Response)RestAssured.given().get(url);
    }

    public static Response post(URL url) {
        return (Response)RestAssured.given().post(url);
    }

    public static Response put(URL url) {
        return (Response)RestAssured.given().put(url);
    }

    public static Response delete(URL url) {
        return (Response)RestAssured.given().delete(url);
    }

    public static Response head(URL url) {
        return (Response)RestAssured.given().head(url);
    }

    public static Response patch(URL url) {
        return (Response)RestAssured.given().patch(url);
    }

    public static Response options(URL url) {
        return (Response)RestAssured.given().options(url);
    }

    public static Response get() {
        return (Response)RestAssured.given().get();
    }

    public static Response post() {
        return (Response)RestAssured.given().post();
    }

    public static Response put() {
        return (Response)RestAssured.given().put();
    }

    public static Response delete() {
        return (Response)RestAssured.given().delete();
    }

    public static Response head() {
        return (Response)RestAssured.given().head();
    }

    public static Response patch() {
        return (Response)RestAssured.given().patch();
    }

    public static Response options() {
        return (Response)RestAssured.given().options();
    }

    public static Response request(Method method) {
        return (Response)RestAssured.given().request(method);
    }

    public static Response request(String method) {
        return (Response)RestAssured.given().request(method);
    }

    public static Response request(Method method, String path, Object ... pathParams) {
        return (Response)RestAssured.given().request(method, path, pathParams);
    }

    public static Response request(String method, String path, Object ... pathParams) {
        return (Response)RestAssured.given().request(method, path, pathParams);
    }

    public static Response request(Method method, URI uri) {
        return (Response)RestAssured.given().request(method, uri);
    }

    public static Response request(Method method, URL url) {
        return (Response)RestAssured.given().request(method, url);
    }

    public static Response request(String method, URI uri) {
        return (Response)RestAssured.given().request(method, uri);
    }

    public static Response request(String method, URL url) {
        return (Response)RestAssured.given().request(method, url);
    }

    public static AuthenticationScheme basic(String userName, String password) {
        BasicAuthScheme scheme = new BasicAuthScheme();
        scheme.setUserName(userName);
        scheme.setPassword(password);
        return scheme;
    }

    public static AuthenticationScheme ntlm(String userName, String password, String workstation, String domain) {
        NTLMAuthScheme scheme = new NTLMAuthScheme();
        scheme.setUserName(userName);
        scheme.setPassword(password);
        scheme.setWorkstation(workstation);
        scheme.setDomain(domain);
        return scheme;
    }

    public static AuthenticationScheme form(String userName, String password) {
        return RestAssured.form(userName, password, null);
    }

    public static AuthenticationScheme form(String userName, String password, FormAuthConfig config) {
        if (userName == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        FormAuthScheme scheme = new FormAuthScheme();
        scheme.setUserName(userName);
        scheme.setPassword(password);
        scheme.setConfig(config);
        return scheme;
    }

    public static PreemptiveAuthProvider preemptive() {
        return new PreemptiveAuthProvider();
    }

    public static AuthenticationScheme certificate(String certURL, String password) {
        SSLConfig sslConfig = RestAssured.config().getSSLConfig();
        return RestAssured.certificate(certURL, password, CertificateAuthSettings.certAuthSettings().keyStoreType(sslConfig.getKeyStoreType()).trustStore(sslConfig.getTrustStore()).keyStore(sslConfig.getKeyStore()).trustStoreType(sslConfig.getTrustStoreType()).x509HostnameVerifier(sslConfig.getX509HostnameVerifier()).port(sslConfig.getPort()).sslSocketFactory(sslConfig.getSSLSocketFactory()));
    }

    public static AuthenticationScheme certificate(String certURL, String password, CertificateAuthSettings certificateAuthSettings) {
        return RestAssured.certificate(certURL, password, "", "", certificateAuthSettings);
    }

    public static AuthenticationScheme certificate(String trustStorePath, String trustStorePassword, String keyStorePath, String keyStorePassword, CertificateAuthSettings certificateAuthSettings) {
        AssertParameter.notNull(keyStorePath, "Keystore path");
        AssertParameter.notNull(keyStorePassword, "Keystore password");
        AssertParameter.notNull(trustStorePath, "Trust store path");
        AssertParameter.notNull(trustStorePassword, "Keystore password");
        AssertParameter.notNull(certificateAuthSettings, CertificateAuthSettings.class);
        CertAuthScheme scheme = new CertAuthScheme();
        scheme.setPathToKeyStore(keyStorePath);
        scheme.setKeyStorePassword(keyStorePassword);
        scheme.setKeystoreType(certificateAuthSettings.getKeyStoreType());
        scheme.setKeyStore(certificateAuthSettings.getKeyStore());
        scheme.setPort(certificateAuthSettings.getPort());
        scheme.setTrustStore(certificateAuthSettings.getTrustStore());
        scheme.setTrustStoreType(certificateAuthSettings.getTrustStoreType());
        scheme.setPathToTrustStore(trustStorePath);
        scheme.setTrustStorePassword(trustStorePassword);
        scheme.setX509HostnameVerifier(certificateAuthSettings.getX509HostnameVerifier());
        scheme.setSslSocketFactory(certificateAuthSettings.getSSLSocketFactory());
        return scheme;
    }

    public static AuthenticationScheme digest(String userName, String password) {
        return RestAssured.basic(userName, password);
    }

    public static AuthenticationScheme oauth(String consumerKey, String consumerSecret, String accessToken, String secretToken) {
        OAuthScheme scheme = new OAuthScheme();
        scheme.setConsumerKey(consumerKey);
        scheme.setConsumerSecret(consumerSecret);
        scheme.setAccessToken(accessToken);
        scheme.setSecretToken(secretToken);
        return scheme;
    }

    public static AuthenticationScheme oauth(String consumerKey, String consumerSecret, String accessToken, String secretToken, OAuthSignature signature) {
        OAuthScheme scheme = new OAuthScheme();
        scheme.setConsumerKey(consumerKey);
        scheme.setConsumerSecret(consumerSecret);
        scheme.setAccessToken(accessToken);
        scheme.setSecretToken(secretToken);
        scheme.setSignature(signature);
        return scheme;
    }

    public static AuthenticationScheme oauth2(String accessToken) {
        PreemptiveOAuth2HeaderScheme myScheme = new PreemptiveOAuth2HeaderScheme();
        myScheme.setAccessToken(accessToken);
        return myScheme;
    }

    public static AuthenticationScheme oauth2(String accessToken, OAuthSignature signature) {
        OAuth2Scheme scheme = new OAuth2Scheme();
        scheme.setAccessToken(accessToken);
        scheme.setSignature(signature);
        return scheme;
    }

    public static void registerParser(String contentType, Parser parser) {
        RESPONSE_PARSER_REGISTRAR.registerParser(contentType, parser);
    }

    public static void unregisterParser(String contentType) {
        RESPONSE_PARSER_REGISTRAR.unregisterParser(contentType);
    }

    public static void reset() {
        baseURI = DEFAULT_URI;
        port = -1;
        basePath = "";
        authentication = DEFAULT_AUTH;
        rootPath = "";
        filters = new LinkedList<Filter>();
        requestSpecification = null;
        responseSpecification = null;
        urlEncodingEnabled = true;
        RESPONSE_PARSER_REGISTRAR = new ResponseParserRegistrar();
        defaultParser = null;
        config = new RestAssuredConfig();
        sessionId = DEFAULT_SESSION_ID_VALUE;
        proxy = null;
    }

    private static TestSpecificationImpl createTestSpecification() {
        if (defaultParser != null) {
            RESPONSE_PARSER_REGISTRAR.registerDefaultParser(defaultParser);
        }
        ResponseParserRegistrar responseParserRegistrar = new ResponseParserRegistrar(RESPONSE_PARSER_REGISTRAR);
        RestAssured.applySessionIdIfApplicable();
        LogRepository logRepository = new LogRepository();
        RestAssuredConfig restAssuredConfig = RestAssured.config();
        return new TestSpecificationImpl(new RequestSpecificationImpl(baseURI, port, basePath, authentication, filters, requestSpecification, urlEncodingEnabled, restAssuredConfig, logRepository, proxy, true, true), new ResponseSpecificationImpl(rootPath, responseSpecification, responseParserRegistrar, restAssuredConfig, logRepository));
    }

    private static void applySessionIdIfApplicable() {
        if (!StringUtils.equals(sessionId, DEFAULT_SESSION_ID_VALUE)) {
            RestAssuredConfig configToUse = config == null ? new RestAssuredConfig() : config;
            config = configToUse.sessionConfig(configToUse.getSessionConfig().sessionIdValue(sessionId));
        }
    }

    public static void useRelaxedHTTPSValidation() {
        RestAssured.useRelaxedHTTPSValidation(SSL);
    }

    public static void useRelaxedHTTPSValidation(String protocol) {
        config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation(protocol));
    }

    public static void enableLoggingOfRequestAndResponseIfValidationFails() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    public static void enableLoggingOfRequestAndResponseIfValidationFails(LogDetail logDetail) {
        LogConfig logConfig = LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(logDetail);
        config = RestAssured.config().logConfig(logConfig);
        if (requestSpecification != null && requestSpecification instanceof RequestSpecificationImpl) {
            RestAssuredConfig restAssuredConfig = ((RequestSpecificationImpl)requestSpecification).getConfig();
            if (restAssuredConfig == null) {
                restAssuredConfig = config;
            } else {
                LogConfig logConfigForRequestSpec = restAssuredConfig.getLogConfig().enableLoggingOfRequestAndResponseIfValidationFails(logDetail);
                restAssuredConfig = restAssuredConfig.logConfig(logConfigForRequestSpec);
            }
            requestSpecification.config(restAssuredConfig);
        }
    }

    public static void keyStore(String pathToJks, String password) {
        Validate.notEmpty(password, "Password cannot be empty", new Object[0]);
        RestAssured.applyKeyStore(pathToJks, password);
    }

    public static void trustStore(String pathToJks, String password) {
        Validate.notEmpty(password, "Password cannot be empty", new Object[0]);
        RestAssured.applyTrustStore(pathToJks, password);
    }

    public static void trustStore(KeyStore truststore) {
        Validate.notNull(truststore, "Truststore cannot be null", new Object[0]);
        config = RestAssured.config().sslConfig(SSLConfig.sslConfig().trustStore(truststore));
    }

    public static void keyStore(File pathToJks, String password) {
        Validate.notNull(pathToJks, "Path to JKS on the file system cannot be null", new Object[0]);
        RestAssured.applyKeyStore(pathToJks, password);
    }

    public static void trustStore(File pathToJks, String password) {
        Validate.notNull(pathToJks, "Path to JKS on the file system cannot be null", new Object[0]);
        RestAssured.applyTrustStore(pathToJks, password);
    }

    public static void keyStore(String password) {
        RestAssured.applyKeyStore(null, password);
    }

    public static void proxy(String host, int port) {
        RestAssured.proxy(ProxySpecification.host(host).withPort(port));
    }

    public static void proxy(String host) {
        if (UriValidator.isUri(host)) {
            try {
                RestAssured.proxy(new URI(host));
            }
            catch (URISyntaxException e) {
                throw new RuntimeException("Internal error in REST Assured when constructing URI for Proxy.", e);
            }
        } else {
            RestAssured.proxy(ProxySpecification.host(host));
        }
    }

    public static void proxy(int port) {
        RestAssured.proxy(ProxySpecification.port(port));
    }

    public static void proxy(String host, int port, String scheme) {
        RestAssured.proxy(new ProxySpecification(host, port, scheme));
    }

    public static void proxy(URI uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Proxy URI cannot be null");
        }
        RestAssured.proxy(new ProxySpecification(uri.getHost(), uri.getPort(), uri.getScheme()));
    }

    public static void proxy(ProxySpecification proxySpecification) {
        proxy = proxySpecification;
    }

    private static void applyKeyStore(Object pathToJks, String password) {
        RestAssuredConfig restAssuredConfig = RestAssured.config();
        SSLConfig updatedSSLConfig = pathToJks instanceof File ? restAssuredConfig.getSSLConfig().keyStore((File)pathToJks, password) : restAssuredConfig.getSSLConfig().keyStore((String)pathToJks, password);
        config = RestAssured.config().sslConfig(updatedSSLConfig.allowAllHostnames());
    }

    private static void applyTrustStore(Object pathToJks, String password) {
        RestAssuredConfig restAssuredConfig = RestAssured.config();
        SSLConfig updatedSSLConfig = pathToJks instanceof File ? restAssuredConfig.getSSLConfig().trustStore((File)pathToJks, password) : restAssuredConfig.getSSLConfig().trustStore((String)pathToJks, password);
        config = RestAssured.config().sslConfig(updatedSSLConfig.allowAllHostnames());
    }

    public static RestAssuredConfig config() {
        return config == null ? new RestAssuredConfig() : config;
    }
}

