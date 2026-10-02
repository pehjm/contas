/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;

public interface FilterableRequestSpecification
extends QueryableRequestSpecification,
RequestSpecification {
    public FilterableRequestSpecification path(String var1);

    public FilterableRequestSpecification removeFormParam(String var1);

    public FilterableRequestSpecification removePathParam(String var1);

    public FilterableRequestSpecification removeNamedPathParam(String var1);

    public FilterableRequestSpecification removeUnnamedPathParam(String var1);

    public FilterableRequestSpecification removeUnnamedPathParamByValue(String var1);

    public FilterableRequestSpecification removeParam(String var1);

    public FilterableRequestSpecification removeQueryParam(String var1);

    public FilterableRequestSpecification removeHeader(String var1);

    public FilterableRequestSpecification removeCookie(String var1);

    public FilterableRequestSpecification removeCookie(Cookie var1);

    public FilterableRequestSpecification replaceHeader(String var1, String var2);

    public FilterableRequestSpecification replaceCookie(String var1, String var2);

    public FilterableRequestSpecification replaceCookie(Cookie var1);

    public FilterableRequestSpecification replaceHeaders(Headers var1);

    public FilterableRequestSpecification replaceCookies(Cookies var1);

    public FilterableRequestSpecification removeHeaders();

    public FilterableRequestSpecification removeCookies();
}

