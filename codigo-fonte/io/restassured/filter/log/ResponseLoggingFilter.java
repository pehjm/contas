/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter.log;

import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.StatusCodeBasedLoggingFilter;
import java.io.PrintStream;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class ResponseLoggingFilter
extends StatusCodeBasedLoggingFilter {
    public ResponseLoggingFilter() {
        this(System.out);
    }

    public ResponseLoggingFilter(int statusCode) {
        this(System.out, Matchers.equalTo(statusCode));
    }

    public ResponseLoggingFilter(Matcher<Integer> matcher) {
        this(System.out, matcher);
    }

    public ResponseLoggingFilter(LogDetail logDetail) {
        this(logDetail, System.out);
    }

    public ResponseLoggingFilter(PrintStream stream) {
        this(stream, Matchers.any(Integer.class));
    }

    public ResponseLoggingFilter(LogDetail logDetail, PrintStream stream) {
        this(logDetail, stream, Matchers.any(Integer.class));
    }

    public ResponseLoggingFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream) {
        this(logDetail, shouldPrettyPrint, stream, Matchers.any(Integer.class));
    }

    public ResponseLoggingFilter(PrintStream stream, int statusCode) {
        this(stream, Matchers.equalTo(statusCode));
    }

    public ResponseLoggingFilter(PrintStream stream, Matcher<Integer> matcher) {
        this(LogDetail.ALL, stream, matcher);
    }

    public ResponseLoggingFilter(LogDetail logDetail, PrintStream stream, int statusCode) {
        this(logDetail, stream, Matchers.equalTo(statusCode));
    }

    public ResponseLoggingFilter(LogDetail logDetail, PrintStream stream, Matcher<Integer> matcher) {
        super(logDetail, stream, matcher);
    }

    public ResponseLoggingFilter(LogDetail logDetail, boolean prettyPrint, PrintStream stream, int statusCode) {
        this(logDetail, prettyPrint, stream, Matchers.equalTo(statusCode));
    }

    public ResponseLoggingFilter(LogDetail logDetail, boolean prettyPrint, PrintStream stream, Matcher<Integer> matcher) {
        super(logDetail, prettyPrint, stream, matcher);
    }

    public static Filter responseLogger() {
        return new ResponseLoggingFilter();
    }

    public static Filter logResponseTo(PrintStream stream) {
        return new ResponseLoggingFilter(stream);
    }

    public static Filter logResponseTo(PrintStream stream, LogDetail logDetail) {
        return new ResponseLoggingFilter(logDetail, stream);
    }

    public static Filter logResponseIfStatusCodeMatches(Matcher<Integer> matcher) {
        return new ResponseLoggingFilter(matcher);
    }

    public static Filter logResponseIfStatusCodeIs(int statusCode) {
        return new ResponseLoggingFilter(statusCode);
    }

    public static Filter logResponseToIfMatches(PrintStream stream, Matcher<Integer> matcher) {
        return new ResponseLoggingFilter(stream, matcher);
    }
}

