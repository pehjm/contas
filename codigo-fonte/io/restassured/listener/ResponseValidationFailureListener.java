/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.listener;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

@FunctionalInterface
public interface ResponseValidationFailureListener {
    public void onFailure(RequestSpecification var1, ResponseSpecification var2, Response var3);
}

