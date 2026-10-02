/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.http.Method;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.http.CustomHttpMethod;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;

public class HttpRequestFactory {
    private static final Map<String, Class<? extends HttpRequestBase>> HTTP_METHOD_TO_HTTP_REQUEST_TYPE = new HashMap<String, Class<? extends HttpRequestBase>>(){
        {
            this.put(Method.GET.name(), HttpGet.class);
            this.put(Method.PUT.name(), HttpPut.class);
            this.put(Method.POST.name(), HttpPost.class);
            this.put(Method.DELETE.name(), HttpDelete.class);
            this.put(Method.HEAD.name(), HttpHead.class);
            this.put(Method.TRACE.name(), HttpTrace.class);
            this.put(Method.OPTIONS.name(), HttpOptions.class);
            this.put(Method.PATCH.name(), HttpPatch.class);
        }
    };

    static HttpRequestBase createHttpRequest(URI uri, String httpMethod, boolean hasBody) {
        HttpRequestBase httpRequest;
        String method = AssertParameter.notNull(StringUtils.upperCase(StringUtils.trimToNull(httpMethod)), "Http method");
        Class<? extends HttpRequestBase> type = HTTP_METHOD_TO_HTTP_REQUEST_TYPE.get(method);
        if (type == null || !type.isInstance(HttpEntityEnclosingRequest.class) && hasBody) {
            httpRequest = new CustomHttpMethod(method, uri);
        } else {
            try {
                httpRequest = type.newInstance();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            httpRequest.setURI(uri);
        }
        return httpRequest;
    }
}

