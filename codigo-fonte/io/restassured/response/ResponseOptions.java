/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.response;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.ResponseBody;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface ResponseOptions<T extends ResponseOptions<T>> {
    public T andReturn();

    public T thenReturn();

    public ResponseBody body();

    public ResponseBody getBody();

    public Headers headers();

    public Headers getHeaders();

    public String header(String var1);

    public String getHeader(String var1);

    public Map<String, String> cookies();

    public Cookies detailedCookies();

    public Map<String, String> getCookies();

    public Cookies getDetailedCookies();

    public String cookie(String var1);

    public String getCookie(String var1);

    public Cookie detailedCookie(String var1);

    public Cookie getDetailedCookie(String var1);

    public String contentType();

    public String getContentType();

    public String statusLine();

    public String getStatusLine();

    public String sessionId();

    public String getSessionId();

    public int statusCode();

    public int getStatusCode();

    public long time();

    public long timeIn(TimeUnit var1);

    public long getTime();

    public long getTimeIn(TimeUnit var1);
}

