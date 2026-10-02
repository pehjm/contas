/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.internal.ValidatableResponseImpl;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class RestAssuredResponseImpl
extends RestAssuredResponseOptionsImpl<Response>
implements Response {
    public void parseResponse(Object httpResponse, Object content, boolean hasBodyAssertions, ResponseParserRegistrar responseParserRegistrar) {
        this.groovyResponse.parseResponse(httpResponse, content, hasBodyAssertions, responseParserRegistrar);
    }

    @Override
    public ValidatableResponse then() {
        return new ValidatableResponseImpl(this.getContentType(), this.getRpr(), this.getConfig(), this, this, this.getLogRepository());
    }
}

