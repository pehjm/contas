/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSender;

public interface FilterContext {
    public void setValue(String var1, Object var2);

    public <T> T getValue(String var1);

    public boolean hasValue(String var1);

    public boolean hasValue(String var1, Object var2);

    public Response send(RequestSender var1);

    public Response next(FilterableRequestSpecification var1, FilterableResponseSpecification var2);
}

