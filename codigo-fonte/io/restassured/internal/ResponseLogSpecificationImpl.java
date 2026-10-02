/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.internal.LogSpecificationImpl;
import io.restassured.internal.log.LogRepository;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseLogSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class ResponseLogSpecificationImpl
extends LogSpecificationImpl
implements ResponseLogSpecification {
    private ResponseSpecification responseSpecification;
    private LogRepository logRepository;

    @Override
    public ResponseSpecification body() {
        return this.body(this.shouldPrettyPrint());
    }

    @Override
    public ResponseSpecification body(boolean shouldPrettyPrint) {
        return this.logWith(LogDetail.BODY, shouldPrettyPrint);
    }

    @Override
    public ResponseSpecification all() {
        return this.all(this.shouldPrettyPrint());
    }

    @Override
    public ResponseSpecification all(boolean shouldPrettyPrint) {
        return this.logWith(LogDetail.ALL, shouldPrettyPrint);
    }

    @Override
    public ResponseSpecification everything() {
        return this.all();
    }

    @Override
    public ResponseSpecification everything(boolean shouldPrettyPrint) {
        return this.all(shouldPrettyPrint);
    }

    @Override
    public ResponseSpecification headers() {
        return this.logWith(LogDetail.HEADERS);
    }

    @Override
    public ResponseSpecification cookies() {
        return this.logWith(LogDetail.COOKIES);
    }

    @Override
    public ResponseSpecification ifValidationFails() {
        return this.ifValidationFails(LogDetail.ALL);
    }

    @Override
    public ResponseSpecification ifValidationFails(LogDetail logDetail) {
        return this.ifValidationFails(logDetail, this.shouldPrettyPrint());
    }

    @Override
    public ResponseSpecification ifValidationFails(LogDetail logDetail, boolean shouldPrettyPrint) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        this.logRepository.registerResponseLog(baos);
        return this.logWith(logDetail, shouldPrettyPrint, ps);
    }

    @Override
    public ResponseSpecification status() {
        return this.logWith(LogDetail.STATUS);
    }

    @Override
    public ResponseSpecification ifError() {
        return this.logWith(new ResponseLoggingFilter(this.getPrintStream(), Matchers.greaterThanOrEqualTo(400)));
    }

    @Override
    public ResponseSpecification ifStatusCodeIsEqualTo(int statusCode) {
        return this.logWith(new ResponseLoggingFilter(this.getPrintStream(), Matchers.equalTo(statusCode)));
    }

    @Override
    public ResponseSpecification ifStatusCodeMatches(Matcher<Integer> matcher) {
        return this.logWith(new ResponseLoggingFilter(this.getPrintStream(), matcher));
    }

    private ResponseSpecification logWith(LogDetail logDetail) {
        return this.logWith(new ResponseLoggingFilter(logDetail, this.getPrintStream()));
    }

    private ResponseSpecification logWith(LogDetail logDetail, boolean prettyPrintingEnabled) {
        return this.logWith(logDetail, prettyPrintingEnabled, this.getPrintStream());
    }

    private ResponseSpecification logWith(LogDetail logDetail, boolean prettyPrintingEnabled, PrintStream printStream) {
        return this.logWith(new ResponseLoggingFilter(logDetail, prettyPrintingEnabled, printStream));
    }

    private ResponseSpecification logWith(ResponseLoggingFilter filter) {
        this.responseSpecification.request().filter(filter);
        return this.responseSpecification;
    }

    private PrintStream getPrintStream() {
        RequestSpecification requestSpecification = this.responseSpecification.request();
        if (requestSpecification == null) {
            throw new IllegalStateException("Cannot configure logging since request specification is not defined. You may be misusing the API.");
        }
        return super.getPrintStream(requestSpecification);
    }

    private boolean shouldPrettyPrint() {
        RequestSpecification responseSpecification = this.responseSpecification.request();
        if (responseSpecification == null) {
            throw new IllegalStateException("Cannot configure logging since response specification is not defined. You may be misusing the API.");
        }
        return super.shouldPrettyPrint(responseSpecification);
    }
}

