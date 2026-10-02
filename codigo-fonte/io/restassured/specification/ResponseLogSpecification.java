/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.specification.LogSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

public interface ResponseLogSpecification
extends LogSpecification<ResponseSpecification> {
    public ResponseSpecification status();

    public ResponseSpecification ifError();

    public ResponseSpecification ifStatusCodeIsEqualTo(int var1);

    public ResponseSpecification ifStatusCodeMatches(Matcher<Integer> var1);
}

