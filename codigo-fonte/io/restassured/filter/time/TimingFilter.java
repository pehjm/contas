/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter.time;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class TimingFilter
implements Filter {
    public static final String RESPONSE_TIME_MILLISECONDS = "RA_RESPONSE_TIME_MILLIS";
    private final boolean shouldConsumeStream;

    public TimingFilter() {
        this(false);
    }

    public TimingFilter(boolean consumeIfInputStream) {
        this.shouldConsumeStream = consumeIfInputStream;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        long start = System.currentTimeMillis();
        Response response = ctx.next(requestSpec, responseSpec);
        if (this.shouldConsumeStream && response instanceof RestAssuredResponseImpl && ((RestAssuredResponseImpl)response).isInputStream()) {
            response.asByteArray();
        }
        long end = System.currentTimeMillis();
        long responseTime = end - start;
        ctx.setValue(RESPONSE_TIME_MILLISECONDS, responseTime);
        return response;
    }

    public static TimingFilter measureTime() {
        return new TimingFilter();
    }
}

