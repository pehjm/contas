/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.response;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ResponseOptions;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface ExtractableResponse<R extends ResponseOptions<R>>
extends ResponseBodyExtractionOptions {
    public ResponseBodyExtractionOptions body();

    public Headers headers();

    public String header(String var1);

    public Map<String, String> cookies();

    public Cookies detailedCookies();

    public String cookie(String var1);

    public Cookie detailedCookie(String var1);

    public String contentType();

    public String statusLine();

    public String sessionId();

    public int statusCode();

    public R response();

    public long time();

    public long timeIn(TimeUnit var1);
}

