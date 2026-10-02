/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.specification.LogSpecification;
import io.restassured.specification.RequestSpecification;

public interface RequestLogSpecification
extends LogSpecification<RequestSpecification> {
    public RequestSpecification params();

    public RequestSpecification parameters();

    public RequestSpecification uri();

    public RequestSpecification method();
}

