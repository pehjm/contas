/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.log.LogRepository;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.internal.util.SafeExceptionRethrower;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.parsing.Parser;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponseLogSpec;
import io.restassured.response.ValidatableResponseOptions;
import io.restassured.specification.Argument;
import io.restassured.specification.ResponseSpecification;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.hamcrest.Matcher;

public abstract class ValidatableResponseOptionsImpl<T extends ValidatableResponseOptions<T, R>, R extends ResponseBody<R> & ResponseOptions<R>>
implements ValidatableResponseLogSpec<T, R> {
    public final ResponseSpecificationImpl responseSpec;
    private final ExtractableResponse<R> extractableResponse;
    protected final Response response;
    private final RestAssuredConfig config;

    public ValidatableResponseOptionsImpl(ResponseParserRegistrar rpr, RestAssuredConfig config, Response response, ExtractableResponse<R> extractableResponse, LogRepository logRepository) {
        this.config = config == null ? RestAssuredConfig.config() : config;
        this.response = response;
        this.responseSpec = new ResponseSpecificationImpl(RestAssured.rootPath, RestAssured.responseSpecification, rpr, this.config, response, logRepository);
        this.extractableResponse = extractableResponse;
    }

    public T body(List<Argument> arguments, ResponseAwareMatcher<R> responseAwareMatcher) {
        return this.body(arguments, this.getMatcherFromResponseAwareMatcher(responseAwareMatcher), new Object[0]);
    }

    public T body(String key, List<Argument> arguments, ResponseAwareMatcher<R> responseAwareMatcher) {
        return this.body(key, arguments, this.getMatcherFromResponseAwareMatcher(responseAwareMatcher), new Object[0]);
    }

    public T body(String key, ResponseAwareMatcher<R> responseAwareMatcher) {
        AssertParameter.notNull(responseAwareMatcher, ResponseAwareMatcher.class);
        return this.body(key, this.getMatcherFromResponseAwareMatcher(responseAwareMatcher), new Object[0]);
    }

    public T body(String path, List<Argument> arguments, Matcher matcher, Object ... additionalKeyMatcherPairs) {
        this.responseSpec.body(path, arguments, matcher, additionalKeyMatcherPairs);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T body(List<Argument> arguments, Matcher matcher, Object ... additionalKeyMatcherPairs) {
        this.responseSpec.body(arguments, matcher, additionalKeyMatcherPairs);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T statusCode(Matcher<? super Integer> expectedStatusCode) {
        this.responseSpec.statusCode(expectedStatusCode);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T statusCode(int expectedStatusCode) {
        this.responseSpec.statusCode(expectedStatusCode);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T statusLine(Matcher<? super String> expectedStatusLine) {
        this.responseSpec.statusLine(expectedStatusLine);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T statusLine(String expectedStatusLine) {
        this.responseSpec.statusLine(expectedStatusLine);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T headers(Map<String, ?> expectedHeaders) {
        this.responseSpec.headers((Map)expectedHeaders);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T headers(String firstExpectedHeaderName, Object firstExpectedHeaderValue, Object ... expectedHeaders) {
        this.responseSpec.headers(firstExpectedHeaderName, firstExpectedHeaderValue, expectedHeaders);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T header(String headerName, Matcher<?> expectedValueMatcher) {
        this.responseSpec.header(headerName, (Matcher)expectedValueMatcher);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T header(String headerName, ResponseAwareMatcher<R> r) {
        this.responseSpec.header(headerName, (Matcher)this.getMatcherFromResponseAwareMatcher(r));
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T header(String headerName, String expectedValue) {
        this.responseSpec.header(headerName, expectedValue);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public <U> T header(String headerName, Function<String, U> f, Matcher<? super U> matcher) {
        this.responseSpec.header(headerName, f, matcher);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T cookies(Map<String, ?> expectedCookies) {
        this.responseSpec.cookies((Map)expectedCookies);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T cookie(String cookieName) {
        this.responseSpec.cookie(cookieName);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T cookies(String firstExpectedCookieName, Object firstExpectedCookieValue, Object ... expectedCookieNameValuePairs) {
        this.responseSpec.cookies(firstExpectedCookieName, firstExpectedCookieValue, expectedCookieNameValuePairs);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T cookie(String cookieName, Matcher<?> expectedValueMatcher) {
        this.responseSpec.cookie(cookieName, (Matcher)expectedValueMatcher);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T cookie(String cookieName, Object expectedValue) {
        this.responseSpec.cookie(cookieName, expectedValue);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T cookie(String cookieName, DetailedCookieMatcher detailedCookieMatcher) {
        this.responseSpec.cookie(cookieName, detailedCookieMatcher);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T rootPath(String rootPath) {
        this.responseSpec.rootPath(rootPath);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T rootPath(String rootPath, List<Argument> arguments) {
        this.responseSpec.rootPath(rootPath, arguments);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T root(String rootPath, List<Argument> arguments) {
        this.responseSpec.root(rootPath, arguments);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T root(String rootPath) {
        this.responseSpec.rootPath(rootPath);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T noRoot() {
        this.responseSpec.noRootPath();
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T noRootPath() {
        this.responseSpec.noRootPath();
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T appendRootPath(String pathToAppend) {
        this.responseSpec.appendRootPath(pathToAppend);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T appendRootPath(String pathToAppend, List<Argument> arguments) {
        this.responseSpec.appendRootPath(pathToAppend, arguments);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T detachRootPath(String pathToDetach) {
        this.responseSpec.detachRootPath(pathToDetach);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T contentType(ContentType contentType) {
        this.responseSpec.contentType(contentType);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T contentType(String contentType) {
        this.responseSpec.contentType(contentType);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T contentType(Matcher<? super String> contentType) {
        this.responseSpec.contentType(contentType);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T body(Matcher<?> matcher, Matcher<?> ... additionalMatchers) {
        this.responseSpec.body((Matcher)matcher, (Matcher[])additionalMatchers);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T body(String path, Matcher<?> matcher, Object ... additionalKeyMatcherPairs) {
        this.responseSpec.body(path, (Matcher)matcher, additionalKeyMatcherPairs);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T content(String path, List<Argument> arguments, Matcher matcher, Object ... additionalKeyMatcherPairs) {
        this.responseSpec.body(path, arguments, matcher, additionalKeyMatcherPairs);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T and() {
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T using() {
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T assertThat() {
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T spec(ResponseSpecification responseSpecification) {
        this.response.asString();
        if (responseSpecification instanceof ResponseSpecificationImpl) {
            ResponseSpecificationImpl impl = (ResponseSpecificationImpl)responseSpecification;
            LogConfig globalLogConfig = this.responseSpec.getConfig().getLogConfig();
            impl.setConfig(this.config);
            if (globalLogConfig.isLoggingOfRequestAndResponseIfValidationFailsEnabled()) {
                impl.setLogRepository(this.responseSpec.getLogRepository());
            }
            if (impl.getLogDetail() != null) {
                this.logResponse(impl.getLogDetail(), globalLogConfig.isPrettyPrintingEnabled(), globalLogConfig.defaultStream());
            }
        }
        responseSpecification.validate(this.response);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T parser(String contentType, Parser parser) {
        this.responseSpec.parser(contentType, parser);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T defaultParser(Parser parser) {
        this.responseSpec.defaultParser(parser);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public ExtractableResponse<R> extract() {
        return this.extractableResponse;
    }

    public ValidatableResponseLogSpec log() {
        return this;
    }

    @Override
    public T status() {
        return this.logResponse(LogDetail.STATUS);
    }

    @Override
    public T ifError() {
        if (this.response.statusCode() >= 400) {
            return this.logResponse(LogDetail.ALL);
        }
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    @Override
    public T ifStatusCodeIsEqualTo(int statusCode) {
        if (this.response.statusCode() == statusCode) {
            return this.logResponse(LogDetail.ALL);
        }
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    @Override
    public T ifStatusCodeMatches(Matcher<Integer> matcher) {
        AssertParameter.notNull(matcher, "Matcher");
        if (matcher.matches(this.response.statusCode())) {
            return this.logResponse(LogDetail.ALL);
        }
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    @Override
    public T body() {
        return this.logResponse(LogDetail.BODY);
    }

    @Override
    public T body(boolean shouldPrettyPrint) {
        return this.logResponse(LogDetail.BODY, shouldPrettyPrint);
    }

    @Override
    public T all() {
        return this.logResponse(LogDetail.ALL);
    }

    @Override
    public T all(boolean shouldPrettyPrint) {
        return this.logResponse(LogDetail.ALL, shouldPrettyPrint);
    }

    @Override
    public T everything() {
        return this.all();
    }

    @Override
    public T everything(boolean shouldPrettyPrint) {
        return this.all(shouldPrettyPrint);
    }

    @Override
    public T headers() {
        return this.logResponse(LogDetail.HEADERS);
    }

    @Override
    public T cookies() {
        return this.logResponse(LogDetail.COOKIES);
    }

    @Override
    public T ifValidationFails() {
        return this.ifValidationFails(LogDetail.ALL);
    }

    @Override
    public T ifValidationFails(LogDetail logDetail) {
        return this.ifValidationFails(logDetail, this.config.getLogConfig().isPrettyPrintingEnabled());
    }

    @Override
    public T ifValidationFails(LogDetail logDetail, boolean shouldPrettyPrint) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        this.responseSpec.getLogRepository().registerResponseLog(baos);
        return this.logResponse(logDetail, shouldPrettyPrint, ps);
    }

    private T logResponse(LogDetail logDetail) {
        return this.logResponse(logDetail, this.config.getLogConfig().isPrettyPrintingEnabled());
    }

    private T logResponse(LogDetail logDetail, boolean shouldPrettyPrint) {
        return this.logResponse(logDetail, shouldPrettyPrint, this.config.getLogConfig().defaultStream());
    }

    private T logResponse(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream printStream) {
        ResponsePrinter.print(this.response, this.response, printStream, logDetail, shouldPrettyPrint, this.config.getLogConfig().blacklistedHeaders());
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T time(Matcher<Long> matcher) {
        return this.time(matcher, TimeUnit.MILLISECONDS);
    }

    public T time(Matcher<Long> matcher, TimeUnit timeUnit) {
        this.responseSpec.time(matcher, timeUnit);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public T onFailMessage(String message) {
        this.responseSpec.onFailMessage(message);
        return (T)((ValidatableResponseOptions)((Object)this));
    }

    public abstract R originalResponse();

    private Matcher<?> getMatcherFromResponseAwareMatcher(ResponseAwareMatcher<R> responseAwareMatcher) {
        AssertParameter.notNull(responseAwareMatcher, ResponseAwareMatcher.class);
        try {
            return responseAwareMatcher.matcher(this.originalResponse());
        }
        catch (Exception e) {
            return (Matcher)SafeExceptionRethrower.safeRethrow(e);
        }
    }
}

