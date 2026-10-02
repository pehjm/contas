/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.response;

import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponseOptions;

public interface Validatable<T extends ValidatableResponseOptions<T, R>, R extends ResponseBody<R> & ResponseOptions<R>> {
    public T then();
}

