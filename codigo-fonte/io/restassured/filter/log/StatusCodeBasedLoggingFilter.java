/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter.log;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.hamcrest.Matcher;

class StatusCodeBasedLoggingFilter
implements Filter {
    private final PrintStream stream;
    private final Matcher<?> matcher;
    private final LogDetail logDetail;
    private final boolean shouldPrettyPrint;
    private final Set<String> blacklistedHeaders;

    public StatusCodeBasedLoggingFilter(Matcher<? super Integer> matcher) {
        this(System.out, matcher);
    }

    public StatusCodeBasedLoggingFilter(PrintStream stream, Matcher<? super Integer> matcher) {
        this(LogDetail.ALL, stream, matcher);
    }

    public StatusCodeBasedLoggingFilter(LogDetail logDetail, PrintStream stream, Matcher<? super Integer> matcher) {
        this(logDetail, StatusCodeBasedLoggingFilter.isPrettyPrintingEnabled(), stream, matcher);
    }

    public StatusCodeBasedLoggingFilter(LogDetail logDetail, boolean prettyPrint, PrintStream stream, Matcher<? super Integer> matcher) {
        this(logDetail, prettyPrint, stream, matcher, Collections.emptySet());
    }

    public StatusCodeBasedLoggingFilter(LogDetail logDetail, boolean prettyPrint, PrintStream stream, Matcher<? super Integer> matcher, Set<String> blacklistedHeaders) {
        Validate.notNull(logDetail, "Log details cannot be null", new Object[0]);
        Validate.notNull(stream, "Print stream cannot be null", new Object[0]);
        Validate.notNull(matcher, "Matcher cannot be null", new Object[0]);
        Validate.notNull(blacklistedHeaders, "Blacklisted headers cannot be null", new Object[0]);
        if (logDetail == LogDetail.PARAMS || logDetail == LogDetail.URI || logDetail == LogDetail.METHOD) {
            throw new IllegalArgumentException(String.format("%s is not a valid %s for a response.", new Object[]{logDetail, LogDetail.class.getSimpleName()}));
        }
        this.shouldPrettyPrint = prettyPrint;
        this.logDetail = logDetail;
        this.stream = stream;
        this.matcher = matcher;
        this.blacklistedHeaders = new HashSet<String>(blacklistedHeaders);
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        int statusCode = response.statusCode();
        if (this.matcher.matches(statusCode)) {
            ResponsePrinter.print(response, response, this.stream, this.logDetail, this.shouldPrettyPrint, this.blacklistedHeaders);
            Object responseBody = this.logDetail == LogDetail.BODY || this.logDetail == LogDetail.ALL ? response.asByteArray() : null;
            response = this.cloneResponseIfNeeded(response, (byte[])responseBody);
        }
        return response;
    }

    private Response cloneResponseIfNeeded(Response response, byte[] responseAsString) {
        if (responseAsString != null && response instanceof RestAssuredResponseImpl && !((RestAssuredResponseImpl)response).getHasExpectations()) {
            Response build = new ResponseBuilder().clone(response).setBody(responseAsString).build();
            ((RestAssuredResponseImpl)build).setHasExpectations(true);
            return build;
        }
        return response;
    }

    private static boolean isPrettyPrintingEnabled() {
        return RestAssured.config == null || RestAssured.config.getLogConfig().isPrettyPrintingEnabled();
    }

    private void throwIAE(LogDetail params) {
        throw new IllegalArgumentException(String.format("%s is not a valid %s for a response.", new Object[]{params, LogDetail.class.getSimpleName()}));
    }
}

