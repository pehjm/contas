/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter;

import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

@FunctionalInterface
public interface Filter {
    public Response filter(FilterableRequestSpecification var1, FilterableResponseSpecification var2, FilterContext var3);
}

