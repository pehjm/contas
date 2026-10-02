/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.internal.http.HttpResponseDecorator;
import io.restassured.internal.http.HttpResponseException;

public class ResponseParseException
extends HttpResponseException {
    private static final long serialVersionUID = -1398234959324603287L;
    private Throwable cause;

    public ResponseParseException(HttpResponseDecorator response, Throwable cause) {
        super(response);
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
}

