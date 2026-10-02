/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.internal.common.assertion.AssertParameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientConfig
implements Config {
    private static final boolean SHOULD_REUSE_HTTP_CLIENT_INSTANCE_BY_DEFAULT = false;
    private static final HttpClient NO_HTTP_CLIENT = null;
    private final boolean shouldReuseHttpClientInstance;
    private final Map<String, ?> httpClientParams;
    private final HttpMultipartMode httpMultipartMode;
    private final HttpClientFactory httpClientFactory;
    private final boolean isUserConfigured;
    private volatile HttpClient httpClient;

    public HttpClientConfig() {
        this.httpClientFactory = HttpClientConfig.defaultHttpClientFactory();
        this.httpClientParams = new HashMap<String, Object>(){
            {
                this.put("http.protocol.cookie-policy", "ignoreCookies");
                this.put("http.protocol.cookie-datepatterns", Arrays.asList("EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd MMM yyyy HH:mm:ss z"));
            }
        };
        this.httpMultipartMode = HttpMultipartMode.STRICT;
        this.shouldReuseHttpClientInstance = false;
        this.httpClient = null;
        this.isUserConfigured = false;
    }

    private HttpClientConfig(HttpClientFactory httpClientFactory, Map<String, ?> httpClientParams, HttpMultipartMode httpMultipartMode, boolean shouldReuseHttpClientInstance, HttpClient abstractHttpClient, boolean isUserConfigured) {
        AssertParameter.notNull(httpClientParams, "httpClientParams");
        AssertParameter.notNull(httpMultipartMode, "httpMultipartMode");
        AssertParameter.notNull(httpClientFactory, "Http Client factory");
        this.shouldReuseHttpClientInstance = shouldReuseHttpClientInstance;
        this.httpClientFactory = httpClientFactory;
        this.httpClientParams = new HashMap(httpClientParams);
        this.httpMultipartMode = httpMultipartMode;
        this.httpClient = abstractHttpClient;
        this.isUserConfigured = isUserConfigured;
    }

    public HttpClientConfig(Map<String, ?> httpClientParams) {
        this(HttpClientConfig.defaultHttpClientFactory(), httpClientParams, HttpMultipartMode.STRICT, false, NO_HTTP_CLIENT, true);
    }

    public Map<String, ?> params() {
        return Collections.unmodifiableMap(this.httpClientParams);
    }

    public HttpClientConfig and() {
        return this;
    }

    public HttpClientConfig reuseHttpClientInstance() {
        return new HttpClientConfig(this.httpClientFactory, this.httpClientParams, this.httpMultipartMode, true, this.httpClient, true);
    }

    public HttpClientConfig dontReuseHttpClientInstance() {
        return new HttpClientConfig(this.httpClientFactory, this.httpClientParams, this.httpMultipartMode, false, NO_HTTP_CLIENT, true);
    }

    public boolean isConfiguredToReuseTheSameHttpClientInstance() {
        return this.shouldReuseHttpClientInstance;
    }

    public <T> HttpClientConfig setParam(String parameterName, T parameterValue) {
        AssertParameter.notNull(parameterName, "Parameter name");
        HashMap newParams = new HashMap(this.httpClientParams);
        newParams.put(parameterName, parameterValue);
        return new HttpClientConfig(this.httpClientFactory, newParams, this.httpMultipartMode, this.shouldReuseHttpClientInstance, NO_HTTP_CLIENT, true);
    }

    public HttpClientConfig withParams(Map<String, ?> httpClientParams) {
        return new HttpClientConfig(this.httpClientFactory, httpClientParams, this.httpMultipartMode, this.shouldReuseHttpClientInstance, NO_HTTP_CLIENT, true);
    }

    public HttpClientConfig setParams(Map<String, ?> httpClientParams) {
        return this.withParams(httpClientParams);
    }

    public HttpClientConfig addParams(Map<String, ?> httpClientParams) {
        AssertParameter.notNull(httpClientParams, "httpClientParams");
        HashMap newParams = new HashMap(this.httpClientParams);
        newParams.putAll(httpClientParams);
        return new HttpClientConfig(this.httpClientFactory, newParams, this.httpMultipartMode, this.shouldReuseHttpClientInstance, NO_HTTP_CLIENT, true);
    }

    public HttpClientConfig httpClientFactory(HttpClientFactory httpClientFactory) {
        return new HttpClientConfig(httpClientFactory, this.httpClientParams, this.httpMultipartMode, this.shouldReuseHttpClientInstance, NO_HTTP_CLIENT, true);
    }

    public HttpClient httpClientInstance() {
        if (this.isConfiguredToReuseTheSameHttpClientInstance()) {
            if (this.httpClient == NO_HTTP_CLIENT) {
                this.httpClient = this.httpClientFactory.createHttpClient();
            }
            return this.httpClient;
        }
        return this.httpClientFactory.createHttpClient();
    }

    public HttpClientConfig httpMultipartMode(HttpMultipartMode httpMultipartMode) {
        return new HttpClientConfig(this.httpClientFactory, this.httpClientParams, httpMultipartMode, this.shouldReuseHttpClientInstance, this.httpClient, true);
    }

    public static HttpClientConfig httpClientConfig() {
        return new HttpClientConfig();
    }

    public HttpMultipartMode httpMultipartMode() {
        return this.httpMultipartMode;
    }

    private static HttpClientFactory defaultHttpClientFactory() {
        return new HttpClientFactory(){

            @Override
            public HttpClient createHttpClient() {
                return new DefaultHttpClient();
            }
        };
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }

    public static interface HttpClientFactory {
        public HttpClient createHttpClient();
    }
}

