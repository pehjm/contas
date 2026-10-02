/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.matcher;

import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import org.hamcrest.Matcher;

@FunctionalInterface
public interface ResponseAwareMatcher<T extends ResponseBody<T> & ResponseOptions<T>> {
    public Matcher<?> matcher(T var1) throws Exception;
}

