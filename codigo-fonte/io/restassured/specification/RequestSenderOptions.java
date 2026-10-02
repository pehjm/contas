/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.http.Method;
import io.restassured.response.ResponseOptions;
import java.net.URI;
import java.net.URL;
import java.util.Map;

public interface RequestSenderOptions<R extends ResponseOptions<R>> {
    public R get(String var1, Object ... var2);

    public R get(String var1, Map<String, ?> var2);

    public R post(String var1, Object ... var2);

    public R post(String var1, Map<String, ?> var2);

    public R put(String var1, Object ... var2);

    public R put(String var1, Map<String, ?> var2);

    public R delete(String var1, Object ... var2);

    public R delete(String var1, Map<String, ?> var2);

    public R head(String var1, Object ... var2);

    public R head(String var1, Map<String, ?> var2);

    public R patch(String var1, Object ... var2);

    public R patch(String var1, Map<String, ?> var2);

    public R options(String var1, Object ... var2);

    public R options(String var1, Map<String, ?> var2);

    public R get(URI var1);

    public R post(URI var1);

    public R put(URI var1);

    public R delete(URI var1);

    public R head(URI var1);

    public R patch(URI var1);

    public R options(URI var1);

    public R get(URL var1);

    public R post(URL var1);

    public R put(URL var1);

    public R delete(URL var1);

    public R head(URL var1);

    public R patch(URL var1);

    public R options(URL var1);

    public R get();

    public R post();

    public R put();

    public R delete();

    public R head();

    public R patch();

    public R options();

    public R request(Method var1);

    public R request(String var1);

    public R request(Method var1, String var2, Object ... var3);

    public R request(String var1, String var2, Object ... var3);

    public R request(Method var1, URI var2);

    public R request(Method var1, URL var2);

    public R request(String var1, URI var2);

    public R request(String var1, URL var2);
}

