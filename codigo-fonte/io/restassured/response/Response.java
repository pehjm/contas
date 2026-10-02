/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.response;

import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;

public interface Response
extends ResponseBody<Response>,
ResponseOptions<Response>,
Validatable<ValidatableResponse, Response> {
}

