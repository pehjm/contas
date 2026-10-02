/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter.log;

import io.restassured.filter.Filter;
import io.restassured.filter.log.StatusCodeBasedLoggingFilter;
import java.io.PrintStream;
import org.hamcrest.Matchers;

public class ErrorLoggingFilter
extends StatusCodeBasedLoggingFilter {
    public ErrorLoggingFilter() {
        this(System.out);
    }

    public ErrorLoggingFilter(PrintStream stream) {
        super(stream, Matchers.allOf(Matchers.greaterThanOrEqualTo(400), Matchers.lessThanOrEqualTo(500)));
    }

    public static Filter errorLogger() {
        return new ErrorLoggingFilter();
    }

    public static Filter logErrorsTo(PrintStream stream) {
        return new ErrorLoggingFilter(stream);
    }
}

