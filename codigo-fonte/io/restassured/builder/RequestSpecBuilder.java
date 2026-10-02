/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.builder;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.SpecificationMerger;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.log.LogRepository;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RequestSpecBuilder {
    private static final String SSL = "SSL";
    private RequestSpecificationImpl spec = (RequestSpecificationImpl)new RequestSpecificationImpl(RestAssured.baseURI, RestAssured.port, RestAssured.basePath, RestAssured.authentication, RestAssured.filters(), RestAssured.requestSpecification, RestAssured.urlEncodingEnabled, RestAssured.config, new LogRepository(), RestAssured.proxy, true, true).config(RestAssured.config());

    public RequestSpecBuilder setBody(String body) {
        this.spec.body(body);
        return this;
    }

    public RequestSpecBuilder setBody(byte[] body) {
        this.spec.body(body);
        return this;
    }

    public RequestSpecBuilder setBody(Object object) {
        this.spec.body(object);
        return this;
    }

    public RequestSpecBuilder setBody(Object object, ObjectMapper mapper) {
        this.spec.body(object, mapper);
        return this;
    }

    public RequestSpecBuilder setBody(Object object, ObjectMapperType mapperType) {
        this.spec.body(object, mapperType);
        return this;
    }

    public RequestSpecBuilder addCookies(Map<String, ?> cookies) {
        this.spec.cookies((Map)cookies);
        return this;
    }

    public RequestSpecBuilder addCookie(Cookie cookie) {
        this.spec.cookie(cookie);
        return this;
    }

    public RequestSpecBuilder addCookie(String key, Object value, Object ... cookieNameValuePairs) {
        this.spec.cookie(key, value, cookieNameValuePairs);
        return this;
    }

    public RequestSpecBuilder addCookie(String name) {
        this.spec.cookie(name);
        return this;
    }

    public RequestSpecBuilder addCookies(Cookies cookies) {
        this.spec.cookies(cookies);
        return this;
    }

    public RequestSpecBuilder addFilter(Filter filter) {
        this.spec.filter(filter);
        return this;
    }

    public RequestSpecBuilder addFilters(List<Filter> filters) {
        this.spec.filters(filters);
        return this;
    }

    public RequestSpecBuilder addParams(Map<String, ?> parametersMap) {
        this.spec.params((Map)parametersMap);
        return this;
    }

    public RequestSpecBuilder addParam(String parameterName, Object ... parameterValues) {
        this.spec.param(parameterName, parameterValues);
        return this;
    }

    public RequestSpecBuilder addParam(String parameterName, Collection<?> parameterValues) {
        this.spec.param(parameterName, parameterValues);
        return this;
    }

    public RequestSpecBuilder removeParam(String parameterName) {
        this.spec.removeParam(parameterName);
        return this;
    }

    public RequestSpecBuilder addQueryParam(String parameterName, Collection<?> parameterValues) {
        this.spec.queryParam(parameterName, parameterValues);
        return this;
    }

    public RequestSpecBuilder addQueryParams(Map<String, ?> parametersMap) {
        this.spec.queryParams((Map)parametersMap);
        return this;
    }

    public RequestSpecBuilder addQueryParam(String parameterName, Object ... parameterValues) {
        this.spec.queryParam(parameterName, parameterValues);
        return this;
    }

    public RequestSpecBuilder removeQueryParam(String parameterName) {
        this.spec.removeQueryParam(parameterName);
        return this;
    }

    public RequestSpecBuilder addFormParam(String parameterName, Collection<?> parameterValues) {
        this.spec.formParam(parameterName, parameterValues);
        return this;
    }

    public RequestSpecBuilder addFormParams(Map<String, ?> parametersMap) {
        this.spec.formParams((Map)parametersMap);
        return this;
    }

    public RequestSpecBuilder addFormParam(String parameterName, Object ... parameterValues) {
        this.spec.formParam(parameterName, parameterValues);
        return this;
    }

    public RequestSpecBuilder removeFormParam(String parameterName) {
        this.spec.removeFormParam(parameterName);
        return this;
    }

    public RequestSpecBuilder addPathParam(String parameterName, Object parameterValue) {
        this.spec.pathParam(parameterName, parameterValue);
        return this;
    }

    public RequestSpecBuilder addPathParams(String firstParameterName, Object firstParameterValue, Object ... parameterNameValuePairs) {
        this.spec.pathParams(firstParameterName, firstParameterValue, parameterNameValuePairs);
        return this;
    }

    public RequestSpecBuilder addPathParams(Map<String, ?> parameterNameValuePairs) {
        this.spec.pathParams((Map)parameterNameValuePairs);
        return this;
    }

    public RequestSpecBuilder removePathParam(String parameterName) {
        this.spec.removePathParam(parameterName);
        return this;
    }

    public RequestSpecBuilder setKeyStore(String pathToJks, String password) {
        this.spec.keyStore(pathToJks, password);
        return this;
    }

    public RequestSpecBuilder setTrustStore(String pathToJks, String password) {
        this.spec.trustStore(pathToJks, password);
        return this;
    }

    public RequestSpecBuilder setTrustStore(File pathToJks, String password) {
        this.spec.trustStore(pathToJks, password);
        return this;
    }

    public RequestSpecBuilder addHeaders(Map<String, String> headers) {
        this.spec.headers((Map)headers);
        return this;
    }

    public RequestSpecBuilder addHeader(String headerName, String headerValue) {
        this.spec.header(headerName, headerValue, new Object[0]);
        return this;
    }

    public RequestSpecBuilder setContentType(ContentType contentType) {
        this.spec.contentType(contentType);
        return this;
    }

    public RequestSpecBuilder setContentType(String contentType) {
        this.spec.contentType(contentType);
        return this;
    }

    public RequestSpecBuilder noContentType() {
        this.spec.noContentType();
        return this;
    }

    RequestSpecBuilder csrf(String csrfTokenPath) {
        this.spec.csrf(csrfTokenPath);
        return this;
    }

    RequestSpecBuilder csrf(String csrfTokenPath, String csrfFieldName) {
        this.spec.csrf(csrfTokenPath, csrfFieldName);
        return this;
    }

    public RequestSpecBuilder disableCsrf() {
        this.spec.disableCsrf();
        return this;
    }

    public RequestSpecBuilder setAccept(ContentType contentType) {
        this.spec.accept(contentType);
        return this;
    }

    public RequestSpecBuilder setAccept(String mediaTypes) {
        this.spec.accept(mediaTypes);
        return this;
    }

    public RequestSpecBuilder addMultiPart(MultiPartSpecification multiPartSpecification) {
        this.spec.multiPart(multiPartSpecification);
        return this;
    }

    public RequestSpecBuilder addMultiPart(File file) {
        this.spec.multiPart(file);
        return this;
    }

    public RequestSpecBuilder addMultiPart(String controlName, File file) {
        this.spec.multiPart(controlName, file);
        return this;
    }

    public RequestSpecBuilder addMultiPart(String controlName, File file, String mimeType) {
        this.spec.multiPart(controlName, file, mimeType);
        return this;
    }

    public RequestSpecBuilder addMultiPart(String controlName, String fileName, byte[] bytes) {
        this.spec.multiPart(controlName, fileName, bytes);
        return this;
    }

    public RequestSpecBuilder addMultiPart(String controlName, String fileName, byte[] bytes, String mimeType) {
        this.spec.multiPart(controlName, fileName, bytes, mimeType);
        return this;
    }

    public RequestSpecBuilder addMultiPart(String controlName, String fileName, InputStream stream) {
        this.spec.multiPart(controlName, fileName, stream);
        return this;
    }

    public RequestSpecBuilder addMultiPart(String controlName, String fileName, InputStream stream, String mimeType) {
        this.spec.multiPart(controlName, fileName, stream, mimeType);
        return this;
    }

    public RequestSpecBuilder addMultiPart(String controlName, String contentBody) {
        this.spec.multiPart(controlName, contentBody);
        return this;
    }

    public RequestSpecBuilder addMultiPart(String controlName, String contentBody, String mimeType) {
        this.spec.multiPart(controlName, contentBody, mimeType);
        return this;
    }

    public RequestSpecBuilder setAuth(AuthenticationScheme auth) {
        this.spec.setAuthenticationScheme(auth);
        return this;
    }

    public RequestSpecBuilder setPort(int port) {
        this.spec.port(port);
        return this;
    }

    public RequestSpecBuilder setUrlEncodingEnabled(boolean isEnabled) {
        this.spec.urlEncodingEnabled(isEnabled);
        return this;
    }

    public RequestSpecBuilder setSessionId(String sessionIdValue) {
        this.spec.sessionId(sessionIdValue);
        return this;
    }

    public RequestSpecBuilder setSessionId(String sessionIdName, String sessionIdValue) {
        this.spec.sessionId(sessionIdName, sessionIdValue);
        return this;
    }

    public RequestSpecBuilder addRequestSpecification(RequestSpecification specification) {
        if (!(specification instanceof RequestSpecificationImpl)) {
            throw new IllegalArgumentException("Specification must be of type " + RequestSpecificationImpl.class.getClass() + ".");
        }
        RequestSpecificationImpl rs = (RequestSpecificationImpl)specification;
        SpecificationMerger.merge(this.spec, rs);
        return this;
    }

    public RequestSpecBuilder setConfig(RestAssuredConfig config) {
        this.spec.config(config);
        return this;
    }

    public RequestSpecification build() {
        return this.spec;
    }

    public RequestSpecBuilder setBaseUri(String uri) {
        this.spec.baseUri(uri);
        return this;
    }

    public RequestSpecBuilder setBaseUri(URI uri) {
        return this.setBaseUri(AssertParameter.notNull(uri, "Base URI").toString());
    }

    public RequestSpecBuilder setBasePath(String path) {
        this.spec.basePath(path);
        return this;
    }

    public RequestSpecBuilder log(LogDetail logDetail) {
        AssertParameter.notNull(logDetail, LogDetail.class);
        RestAssuredConfig restAssuredConfig = this.spec.getConfig();
        LogConfig logConfig = restAssuredConfig == null ? new RestAssuredConfig().getLogConfig() : restAssuredConfig.getLogConfig();
        PrintStream printStream = logConfig.defaultStream();
        boolean prettyPrintingEnabled = logConfig.isPrettyPrintingEnabled();
        boolean shouldUrlEncodeRequestUri = logConfig.shouldUrlEncodeRequestUri();
        Set<String> blacklistedHeaders = logConfig.blacklistedHeaders();
        this.spec.filter(new RequestLoggingFilter(logDetail, prettyPrintingEnabled, printStream, shouldUrlEncodeRequestUri, blacklistedHeaders));
        return this;
    }

    public RequestSpecBuilder setTrustStore(KeyStore trustStore) {
        this.spec.trustStore(trustStore);
        return this;
    }

    public RequestSpecBuilder setKeyStore(KeyStore keyStore) {
        this.spec.keyStore(keyStore);
        return this;
    }

    public RequestSpecBuilder setRelaxedHTTPSValidation() {
        return this.setRelaxedHTTPSValidation(SSL);
    }

    public RequestSpecBuilder setRelaxedHTTPSValidation(String protocol) {
        this.spec.relaxedHTTPSValidation(protocol);
        return this;
    }

    public RequestSpecBuilder setProxy(String host, int port) {
        this.spec.proxy(host, port);
        return this;
    }

    public RequestSpecBuilder setProxy(String host) {
        this.spec.proxy(host);
        return this;
    }

    public RequestSpecBuilder setProxy(int port) {
        this.spec.proxy(port);
        return this;
    }

    public RequestSpecBuilder setProxy(String host, int port, String scheme) {
        this.spec.proxy(host, port, scheme);
        return this;
    }

    public RequestSpecBuilder setProxy(URI uri) {
        this.spec.proxy(uri);
        return this;
    }

    public RequestSpecBuilder setProxy(ProxySpecification proxySpecification) {
        this.spec.proxy(proxySpecification);
        return this;
    }

    public RequestSpecBuilder and() {
        return this;
    }
}

