/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import io.restassured.config.RestAssuredConfig;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.ValidatableResponseOptionsImpl;
import io.restassured.internal.log.LogRepository;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ValidatableResponseImpl
extends ValidatableResponseOptionsImpl<ValidatableResponse, Response>
implements ValidatableResponse {
    ValidatableResponseImpl(String contentType, ResponseParserRegistrar rpr, RestAssuredConfig config, Response response, ExtractableResponse<Response> extractableResponse, LogRepository logRepository) {
        super(rpr, config, response, extractableResponse, logRepository);
    }

    @Override
    public Response originalResponse() {
        return this.response;
    }
}

