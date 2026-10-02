/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;

public class SpecificationQuerier {
    public static QueryableRequestSpecification query(RequestSpecification specification) {
        if (specification instanceof QueryableRequestSpecification) {
            return (QueryableRequestSpecification)((Object)specification);
        }
        throw new IllegalArgumentException("Cannot convert " + specification + " to a " + QueryableRequestSpecification.class.getName());
    }
}

