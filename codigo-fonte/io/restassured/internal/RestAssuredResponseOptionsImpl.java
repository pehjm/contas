/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import io.restassured.common.mapper.TypeRef;
import io.restassured.config.DecoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import io.restassured.internal.log.LogRepository;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;
import io.restassured.response.ResponseOptions;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RestAssuredResponseOptionsImpl<R extends ResponseOptions<R>>
implements ExtractableResponse<R> {
    private LogRepository logRepository;
    protected RestAssuredResponseOptionsGroovyImpl groovyResponse = new RestAssuredResponseOptionsGroovyImpl();

    public void setResponseHeaders(Object responseHeaders) {
        this.groovyResponse.setResponseHeaders(responseHeaders);
    }

    public void setCookies(Cookies cookies) {
        this.groovyResponse.setCookies(cookies);
    }

    public void setContent(Object content) {
        this.groovyResponse.setContent(content);
    }

    public void setContentType(Object contentType) {
        this.groovyResponse.setContentType(contentType);
    }

    public void setStatusLine(Object statusLine) {
        this.groovyResponse.setStatusLine(statusLine);
    }

    public void setStatusCode(Object statusCode) {
        this.groovyResponse.setStatusCode(statusCode);
    }

    public void setSessionIdName(Object sessionIdName) {
        this.groovyResponse.setSessionIdName(sessionIdName);
    }

    public void setFilterContextProperties(Map filterContextProperties) {
        this.groovyResponse.setFilterContextProperties(filterContextProperties);
    }

    public void setConnectionManager(Object connectionManager) {
        this.groovyResponse.setConnectionManager(connectionManager);
    }

    public void setDefaultContentType(String defaultContentType) {
        this.groovyResponse.setDefaultContentType(defaultContentType);
    }

    public void setRpr(ResponseParserRegistrar rpr) {
        this.groovyResponse.setRpr(rpr);
    }

    public void setDecoderConfig(DecoderConfig decoderConfig) {
        this.groovyResponse.setDecoderConfig(decoderConfig);
    }

    public void setHasExpectations(boolean hasExpectations) {
        this.groovyResponse.setHasExpectations(hasExpectations);
    }

    public void setConfig(RestAssuredConfig config) {
        this.groovyResponse.setConfig(config);
    }

    public ResponseParserRegistrar getRpr() {
        return this.groovyResponse.getRpr();
    }

    public RestAssuredConfig getConfig() {
        return this.groovyResponse.getConfig();
    }

    @Override
    public ResponseBody body() {
        return (ResponseBody)((Object)this);
    }

    @Override
    public Headers headers() {
        return this.groovyResponse.headers();
    }

    @Override
    public String header(String name) {
        return this.groovyResponse.header(name);
    }

    @Override
    public Map<String, String> cookies() {
        return this.groovyResponse.cookies();
    }

    @Override
    public Cookies detailedCookies() {
        return this.groovyResponse.detailedCookies();
    }

    @Override
    public String cookie(String name) {
        return this.groovyResponse.cookie(name);
    }

    @Override
    public Cookie detailedCookie(String name) {
        return this.groovyResponse.detailedCookie(name);
    }

    @Override
    public String contentType() {
        return this.groovyResponse.contentType();
    }

    @Override
    public String statusLine() {
        return this.groovyResponse.statusLine();
    }

    @Override
    public String sessionId() {
        return this.groovyResponse.sessionId();
    }

    @Override
    public int statusCode() {
        return this.groovyResponse.statusCode();
    }

    @Override
    public R response() {
        return (R)((ResponseOptions)((Object)this));
    }

    @Override
    public <T> T as(Class<T> cls) {
        return this.groovyResponse.as(cls, (ResponseBodyData)this);
    }

    @Override
    public <T> T as(Class<T> cls, ObjectMapperType mapperType) {
        return this.groovyResponse.as(cls, mapperType, this);
    }

    @Override
    public <T> T as(Class<T> cls, ObjectMapper mapper) {
        return this.groovyResponse.as(cls, mapper);
    }

    @Override
    public <T> T as(TypeRef<T> typeRef) {
        return this.groovyResponse.as(typeRef, (ResponseBodyData)this);
    }

    @Override
    public <T> T as(Type cls) {
        return this.groovyResponse.as(cls, (ResponseBodyData)this);
    }

    @Override
    public <T> T as(Type cls, ObjectMapperType mapperType) {
        return this.groovyResponse.as(cls, mapperType, this);
    }

    @Override
    public <T> T as(Type cls, ObjectMapper mapper) {
        return this.groovyResponse.as(cls, mapper);
    }

    @Override
    public JsonPath jsonPath() {
        return this.groovyResponse.jsonPath();
    }

    @Override
    public JsonPath jsonPath(JsonPathConfig config) {
        return this.groovyResponse.jsonPath(config);
    }

    @Override
    public XmlPath xmlPath() {
        return this.groovyResponse.xmlPath();
    }

    @Override
    public XmlPath xmlPath(XmlPathConfig config) {
        return this.groovyResponse.xmlPath(config);
    }

    @Override
    public XmlPath xmlPath(XmlPath.CompatibilityMode compatibilityMode) {
        return this.groovyResponse.xmlPath(compatibilityMode);
    }

    @Override
    public XmlPath htmlPath() {
        return this.groovyResponse.htmlPath();
    }

    @Override
    public <T> T path(String path, String ... arguments) {
        return this.groovyResponse.path(path, arguments);
    }

    @Override
    public String asString() {
        return this.groovyResponse.asString();
    }

    public String asString(boolean forcePlatformDefaultCharsetIfNoCharsetIsSpecifiedInResponse) {
        return this.groovyResponse.asString(forcePlatformDefaultCharsetIfNoCharsetIsSpecifiedInResponse);
    }

    @Override
    public String asPrettyString() {
        return this.groovyResponse.asPrettyString((ResponseOptions)((Object)this), (ResponseBody)((Object)this));
    }

    @Override
    public byte[] asByteArray() {
        return this.groovyResponse.asByteArray();
    }

    @Override
    public InputStream asInputStream() {
        return this.groovyResponse.asInputStream();
    }

    public boolean isInputStream() {
        return this.groovyResponse.isInputStream();
    }

    public String print() {
        return this.groovyResponse.print();
    }

    public String prettyPrint() {
        return this.groovyResponse.prettyPrint((ResponseOptions)((Object)this), (ResponseBody)((Object)this));
    }

    public R peek() {
        this.groovyResponse.peek((ResponseOptions)((Object)this), (ResponseBody)((Object)this));
        return (R)((ResponseOptions)((Object)this));
    }

    public R prettyPeek() {
        this.groovyResponse.prettyPeek((ResponseOptions)((Object)this), (ResponseBody)((Object)this));
        return (R)((ResponseOptions)((Object)this));
    }

    public R andReturn() {
        return (R)((ResponseOptions)((Object)this));
    }

    public R thenReturn() {
        return (R)((ResponseOptions)((Object)this));
    }

    public ResponseBody getBody() {
        return (ResponseBody)((Object)this);
    }

    public Headers getHeaders() {
        return this.groovyResponse.getHeaders();
    }

    public String getHeader(String name) {
        return this.groovyResponse.getHeader(name);
    }

    public Cookies getDetailedCookies() {
        return this.groovyResponse.getDetailedCookies();
    }

    public String getCookie(String name) {
        return this.groovyResponse.getCookie(name);
    }

    public Cookie getDetailedCookie(String name) {
        return this.groovyResponse.getDetailedCookie(name);
    }

    public String getSessionId() {
        return this.groovyResponse.getSessionId();
    }

    public Map<String, String> getCookies() {
        return this.groovyResponse.getCookies();
    }

    public String getContentType() {
        return this.groovyResponse.getContentType();
    }

    public String getStatusLine() {
        return this.groovyResponse.getStatusLine();
    }

    public int getStatusCode() {
        return this.groovyResponse.getStatusCode();
    }

    public Object getContent() {
        return this.groovyResponse.getContent();
    }

    public boolean getHasExpectations() {
        return this.groovyResponse.getHasExpectations();
    }

    public String getDefaultContentType() {
        return this.groovyResponse.getDefaultContentType();
    }

    public DecoderConfig getDecoderConfig() {
        return this.groovyResponse.getDecoderConfig();
    }

    public Object getSessionIdName() {
        return this.groovyResponse.getSessionIdName();
    }

    public Object getConnectionManager() {
        return this.groovyResponse.getConnectionManager();
    }

    public Object getResponseHeaders() {
        return this.groovyResponse.getResponseHeaders();
    }

    public LogRepository getLogRepository() {
        return this.logRepository;
    }

    public void setLogRepository(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public RestAssuredResponseOptionsGroovyImpl getGroovyResponse() {
        return this.groovyResponse;
    }

    public Map getFilterContextProperties() {
        return this.groovyResponse.getFilterContextProperties();
    }

    public void setGroovyResponse(RestAssuredResponseOptionsGroovyImpl groovyResponse) {
        this.groovyResponse = groovyResponse;
    }

    @Override
    public long time() {
        return this.groovyResponse.time();
    }

    @Override
    public long timeIn(TimeUnit timeUnit) {
        return this.groovyResponse.timeIn(timeUnit);
    }

    public long getTime() {
        return this.groovyResponse.time();
    }

    public long getTimeIn(TimeUnit timeUnit) {
        return this.groovyResponse.timeIn(timeUnit);
    }
}

