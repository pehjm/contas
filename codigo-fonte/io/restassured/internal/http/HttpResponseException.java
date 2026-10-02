/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.internal.http.HttpResponseDecorator;

public class HttpResponseException
extends org.apache.http.client.HttpResponseException {
    private static final long serialVersionUID = -34809347677236L;
    HttpResponseDecorator response;

    public HttpResponseException(HttpResponseDecorator resp) {
        super(resp.getStatusLine().getStatusCode(), resp.getStatusLine().getReasonPhrase());
        this.response = resp;
    }

    public HttpResponseDecorator getResponse() {
        return this.response;
    }
}

