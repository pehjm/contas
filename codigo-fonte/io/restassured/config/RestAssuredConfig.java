/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.CsrfConfig;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.config.FailureConfig;
import io.restassured.config.HeaderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.JsonConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.MatcherConfig;
import io.restassured.config.MultiPartConfig;
import io.restassured.config.OAuthConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.ParamConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.SSLConfig;
import io.restassured.config.SessionConfig;
import io.restassured.config.XmlConfig;
import io.restassured.internal.common.assertion.AssertParameter;
import java.util.HashMap;
import java.util.Map;

public class RestAssuredConfig
implements Config {
    final Map<Class<? extends Config>, Config> configs = new HashMap<Class<? extends Config>, Config>();

    public RestAssuredConfig() {
        this(new RedirectConfig(), new HttpClientConfig(), new LogConfig(), new EncoderConfig(), new DecoderConfig(), new SessionConfig(), new ObjectMapperConfig(), new ConnectionConfig(), new JsonConfig(), new XmlConfig(), new SSLConfig(), new MatcherConfig(), new HeaderConfig(), new MultiPartConfig(), new ParamConfig(), new OAuthConfig(), new FailureConfig(), new CsrfConfig());
    }

    public RestAssuredConfig(RedirectConfig redirectConfig, HttpClientConfig httpClientConfig, LogConfig logConfig, EncoderConfig encoderConfig, DecoderConfig decoderConfig, SessionConfig sessionConfig, ObjectMapperConfig objectMapperConfig, ConnectionConfig connectionConfig, JsonConfig jsonConfig, XmlConfig xmlConfig, SSLConfig sslConfig, MatcherConfig matcherConfig, HeaderConfig headerConfig, MultiPartConfig multiPartConfig, ParamConfig paramConfig, OAuthConfig oAuthConfig, FailureConfig failureConfig, CsrfConfig csrfConfig) {
        AssertParameter.notNull(redirectConfig, "Redirect Config");
        AssertParameter.notNull(httpClientConfig, "HTTP Client Config");
        AssertParameter.notNull(logConfig, "Log config");
        AssertParameter.notNull(encoderConfig, "Encoder config");
        AssertParameter.notNull(decoderConfig, "Decoder config");
        AssertParameter.notNull(sessionConfig, "Session config");
        AssertParameter.notNull(objectMapperConfig, "Object mapper config");
        AssertParameter.notNull(connectionConfig, "Connection config");
        AssertParameter.notNull(jsonConfig, "Json config");
        AssertParameter.notNull(xmlConfig, "Xml config");
        AssertParameter.notNull(sslConfig, "SSL config");
        AssertParameter.notNull(matcherConfig, "Matcher config");
        AssertParameter.notNull(headerConfig, "Header config");
        AssertParameter.notNull(multiPartConfig, "Multipart config");
        AssertParameter.notNull(paramConfig, "Param config");
        AssertParameter.notNull(oAuthConfig, "OAuth config");
        AssertParameter.notNull(failureConfig, "Failure config");
        AssertParameter.notNull(csrfConfig, "CSRF config");
        this.configs.put(HttpClientConfig.class, httpClientConfig);
        this.configs.put(RedirectConfig.class, redirectConfig);
        this.configs.put(LogConfig.class, logConfig);
        this.configs.put(EncoderConfig.class, encoderConfig);
        this.configs.put(DecoderConfig.class, decoderConfig);
        this.configs.put(SessionConfig.class, sessionConfig);
        this.configs.put(ObjectMapperConfig.class, objectMapperConfig);
        this.configs.put(ConnectionConfig.class, connectionConfig);
        this.configs.put(JsonConfig.class, jsonConfig);
        this.configs.put(XmlConfig.class, xmlConfig);
        this.configs.put(SSLConfig.class, sslConfig);
        this.configs.put(MatcherConfig.class, matcherConfig);
        this.configs.put(HeaderConfig.class, headerConfig);
        this.configs.put(MultiPartConfig.class, multiPartConfig);
        this.configs.put(ParamConfig.class, paramConfig);
        this.configs.put(OAuthConfig.class, oAuthConfig);
        this.configs.put(FailureConfig.class, failureConfig);
        this.configs.put(CsrfConfig.class, csrfConfig);
    }

    public RestAssuredConfig redirect(RedirectConfig redirectConfig) {
        AssertParameter.notNull(redirectConfig, "Redirect config");
        return new RestAssuredConfig(redirectConfig, this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig httpClient(HttpClientConfig httpClientConfig) {
        AssertParameter.notNull(httpClientConfig, "HTTP Client Config");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), httpClientConfig, this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig logConfig(LogConfig logConfig) {
        AssertParameter.notNull(logConfig, "Log config");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), logConfig, this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig encoderConfig(EncoderConfig encoderConfig) {
        AssertParameter.notNull(encoderConfig, "Encoder config");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), encoderConfig, this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig decoderConfig(DecoderConfig decoderConfig) {
        AssertParameter.notNull(decoderConfig, "Decoder config");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), decoderConfig, this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig sessionConfig(SessionConfig sessionConfig) {
        AssertParameter.notNull(sessionConfig, "Session config");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), sessionConfig, this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig objectMapperConfig(ObjectMapperConfig objectMapperConfig) {
        AssertParameter.notNull(objectMapperConfig, "Object mapper config");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), objectMapperConfig, this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig connectionConfig(ConnectionConfig connectionConfig) {
        AssertParameter.notNull(connectionConfig, "Connection config");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), connectionConfig, this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig jsonConfig(JsonConfig jsonConfig) {
        AssertParameter.notNull(jsonConfig, "JsonConfig");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), jsonConfig, this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig xmlConfig(XmlConfig xmlConfig) {
        AssertParameter.notNull(xmlConfig, "XmlConfig");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), xmlConfig, this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig sslConfig(SSLConfig sslConfig) {
        AssertParameter.notNull(sslConfig, "SSLConfig");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), sslConfig, this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig matcherConfig(MatcherConfig matcherConfig) {
        AssertParameter.notNull(matcherConfig, "MatcherConfig");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), matcherConfig, this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig headerConfig(HeaderConfig headerConfig) {
        AssertParameter.notNull(headerConfig, "HeaderConfig");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), headerConfig, this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig multiPartConfig(MultiPartConfig multiPartConfig) {
        AssertParameter.notNull(multiPartConfig, "MultiPartConfig");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), multiPartConfig, this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig paramConfig(ParamConfig paramConfig) {
        AssertParameter.notNull(paramConfig, ParamConfig.class);
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), paramConfig, this.conf(OAuthConfig.class), this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig oauthConfig(OAuthConfig oauthConfig) {
        AssertParameter.notNull(oauthConfig, OAuthConfig.class);
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), oauthConfig, this.conf(FailureConfig.class), this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig failureConfig(FailureConfig failureConfig) {
        AssertParameter.notNull(failureConfig, FailureConfig.class);
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), failureConfig, this.conf(CsrfConfig.class));
    }

    public RestAssuredConfig csrfConfig(CsrfConfig csrfConfig) {
        AssertParameter.notNull(csrfConfig, "CSRF config");
        return new RestAssuredConfig(this.conf(RedirectConfig.class), this.conf(HttpClientConfig.class), this.conf(LogConfig.class), this.conf(EncoderConfig.class), this.conf(DecoderConfig.class), this.conf(SessionConfig.class), this.conf(ObjectMapperConfig.class), this.conf(ConnectionConfig.class), this.conf(JsonConfig.class), this.conf(XmlConfig.class), this.conf(SSLConfig.class), this.conf(MatcherConfig.class), this.conf(HeaderConfig.class), this.conf(MultiPartConfig.class), this.conf(ParamConfig.class), this.conf(OAuthConfig.class), this.conf(FailureConfig.class), csrfConfig);
    }

    public RestAssuredConfig and() {
        return this;
    }

    public RestAssuredConfig set() {
        return this;
    }

    public RestAssuredConfig with() {
        return this;
    }

    public RedirectConfig getRedirectConfig() {
        return this.conf(RedirectConfig.class);
    }

    public LogConfig getLogConfig() {
        return this.conf(LogConfig.class);
    }

    public HttpClientConfig getHttpClientConfig() {
        return this.conf(HttpClientConfig.class);
    }

    public EncoderConfig getEncoderConfig() {
        return this.conf(EncoderConfig.class);
    }

    public DecoderConfig getDecoderConfig() {
        return this.conf(DecoderConfig.class);
    }

    public SessionConfig getSessionConfig() {
        return this.conf(SessionConfig.class);
    }

    public ObjectMapperConfig getObjectMapperConfig() {
        return this.conf(ObjectMapperConfig.class);
    }

    public ConnectionConfig getConnectionConfig() {
        return this.conf(ConnectionConfig.class);
    }

    public JsonConfig getJsonConfig() {
        return this.conf(JsonConfig.class);
    }

    public XmlConfig getXmlConfig() {
        return this.conf(XmlConfig.class);
    }

    public SSLConfig getSSLConfig() {
        return this.conf(SSLConfig.class);
    }

    public MatcherConfig getMatcherConfig() {
        return this.conf(MatcherConfig.class);
    }

    public HeaderConfig getHeaderConfig() {
        return this.conf(HeaderConfig.class);
    }

    public MultiPartConfig getMultiPartConfig() {
        return this.conf(MultiPartConfig.class);
    }

    public ParamConfig getParamConfig() {
        return this.conf(ParamConfig.class);
    }

    public OAuthConfig getOAuthConfig() {
        return this.conf(OAuthConfig.class);
    }

    public FailureConfig getFailureConfig() {
        return this.conf(FailureConfig.class);
    }

    public CsrfConfig getCsrfConfig() {
        return this.conf(CsrfConfig.class);
    }

    public static RestAssuredConfig newConfig() {
        return new RestAssuredConfig();
    }

    public static RestAssuredConfig config() {
        return new RestAssuredConfig();
    }

    @Override
    public boolean isUserConfigured() {
        for (Config cfg : this.configs.values()) {
            if (!cfg.isUserConfigured()) continue;
            return true;
        }
        return false;
    }

    private <T extends Config> T conf(Class<T> type) {
        return (T)this.configs.get(type);
    }
}

