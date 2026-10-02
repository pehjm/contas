/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.builder;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.internal.SpecificationMerger;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.log.LogRepository;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.parsing.Parser;
import io.restassured.specification.Argument;
import io.restassured.specification.ResponseSpecification;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.hamcrest.Matcher;

public class ResponseSpecBuilder {
    private final ResponseSpecification spec = new ResponseSpecificationImpl(RestAssured.rootPath, null, this.getResponseParserRegistrar(), this.restAssuredConfig(), new LogRepository());

    public ResponseSpecBuilder expectStatusCode(Matcher<Integer> expectedStatusCode) {
        this.spec.statusCode(expectedStatusCode);
        return this;
    }

    public ResponseSpecBuilder expectStatusCode(int expectedStatusCode) {
        this.spec.statusCode(expectedStatusCode);
        return this;
    }

    public ResponseSpecBuilder expectStatusLine(Matcher<String> expectedStatusLine) {
        this.spec.statusLine(expectedStatusLine);
        return this;
    }

    public ResponseSpecBuilder expectStatusLine(String expectedStatusLine) {
        this.spec.statusLine(expectedStatusLine);
        return this;
    }

    public ResponseSpecBuilder expectHeaders(Map<String, Object> expectedHeaders) {
        this.spec.headers(expectedHeaders);
        return this;
    }

    public ResponseSpecBuilder expectHeader(String headerName, Matcher<String> expectedValueMatcher) {
        this.spec.header(headerName, expectedValueMatcher);
        return this;
    }

    public ResponseSpecBuilder expectHeader(String headerName, String expectedValue) {
        this.spec.header(headerName, expectedValue);
        return this;
    }

    public ResponseSpecBuilder expectCookies(Map<String, Object> expectedCookies) {
        this.spec.cookies(expectedCookies);
        return this;
    }

    public ResponseSpecBuilder expectCookie(String cookieName, Matcher<String> expectedValueMatcher) {
        this.spec.cookie(cookieName, expectedValueMatcher);
        return this;
    }

    public ResponseSpecBuilder expectCookie(String cookieName, DetailedCookieMatcher detailedCookieMatcher) {
        this.spec.cookie(cookieName, detailedCookieMatcher);
        return this;
    }

    public ResponseSpecBuilder expectCookie(String cookieName, String expectedValue) {
        this.spec.cookie(cookieName, expectedValue);
        return this;
    }

    public ResponseSpecBuilder expectCookie(String cookieName) {
        this.spec.cookie(cookieName);
        return this;
    }

    public ResponseSpecBuilder expectResponseTime(Matcher<Long> matcher) {
        this.spec.time(matcher);
        return this;
    }

    public ResponseSpecBuilder expectResponseTime(Matcher<Long> matcher, TimeUnit timeUnit) {
        this.spec.time(matcher, timeUnit);
        return this;
    }

    public ResponseSpecBuilder rootPath(String rootPath) {
        this.spec.rootPath(rootPath);
        return this;
    }

    public ResponseSpecBuilder rootPath(String rootPath, List<Argument> arguments) {
        this.spec.root(rootPath, arguments);
        return this;
    }

    public ResponseSpecBuilder appendRootPath(String pathToAppend) {
        this.spec.appendRootPath(pathToAppend);
        return this;
    }

    public ResponseSpecBuilder appendRootPath(String pathToAppend, List<Argument> arguments) {
        this.spec.appendRootPath(pathToAppend, arguments);
        return this;
    }

    public ResponseSpecBuilder noRootPath() {
        this.spec.noRootPath();
        return this;
    }

    public ResponseSpecBuilder detachRootPath(String pathToDetach) {
        this.spec.detachRootPath(pathToDetach);
        return this;
    }

    public ResponseSpecBuilder expectContentType(ContentType contentType) {
        this.spec.contentType(contentType);
        return this;
    }

    public ResponseSpecBuilder expectContentType(String contentType) {
        this.spec.contentType(contentType);
        return this;
    }

    public ResponseSpecBuilder expectBody(Matcher<?> matcher) {
        this.spec.body(matcher, new Matcher[0]);
        return this;
    }

    public ResponseSpecBuilder expectBody(String path, Matcher<?> matcher) {
        this.spec.body(path, matcher, new Object[0]);
        return this;
    }

    public ResponseSpecBuilder expectBody(String path, List<Argument> arguments, Matcher<?> matcher) {
        this.spec.body(path, arguments, matcher, new Object[0]);
        return this;
    }

    public ResponseSpecBuilder addResponseSpecification(ResponseSpecification specification) {
        if (!(specification instanceof ResponseSpecificationImpl)) {
            throw new IllegalArgumentException("specification must be of type " + ResponseSpecificationImpl.class.getClass() + ".");
        }
        ResponseSpecificationImpl rs = (ResponseSpecificationImpl)specification;
        SpecificationMerger.merge((ResponseSpecificationImpl)this.spec, rs);
        return this;
    }

    public ResponseSpecBuilder log(LogDetail logDetail) {
        AssertParameter.notNull(logDetail, LogDetail.class);
        this.spec.logDetail(logDetail);
        return this;
    }

    public ResponseSpecBuilder registerParser(String contentType, Parser parser) {
        this.spec.parser(contentType, parser);
        return this;
    }

    public ResponseSpecBuilder setDefaultParser(Parser parser) {
        this.spec.defaultParser(parser);
        return this;
    }

    public ResponseSpecification build() {
        return this.spec;
    }

    private ResponseParserRegistrar getResponseParserRegistrar() {
        ResponseParserRegistrar rpr;
        Field registrarField = null;
        try {
            registrarField = RestAssured.class.getDeclaredField("RESPONSE_PARSER_REGISTRAR");
            try {
                registrarField.setAccessible(true);
                rpr = (ResponseParserRegistrar)registrarField.get(RestAssured.class);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        finally {
            registrarField.setAccessible(false);
        }
        return new ResponseParserRegistrar(rpr);
    }

    private RestAssuredConfig restAssuredConfig() {
        return RestAssured.config == null ? new RestAssuredConfig() : RestAssured.config;
    }
}

