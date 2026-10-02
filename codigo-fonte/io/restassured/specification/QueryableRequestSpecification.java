/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;
import java.util.List;
import java.util.Map;
import org.apache.http.client.HttpClient;

public interface QueryableRequestSpecification {
    public String getBaseUri();

    public String getBasePath();

    public String getDerivedPath();

    public String getUserDefinedPath();

    public String getMethod();

    public String getURI();

    public int getPort();

    public String getContentType();

    public AuthenticationScheme getAuthenticationScheme();

    public Map<String, String> getRequestParams();

    public Map<String, String> getFormParams();

    public Map<String, String> getPathParams();

    public Map<String, String> getNamedPathParams();

    public Map<String, String> getUnnamedPathParams();

    public List<String> getUnnamedPathParamValues();

    public Map<String, String> getQueryParams();

    public List<MultiPartSpecification> getMultiPartParams();

    public Headers getHeaders();

    public Cookies getCookies();

    public <T> T getBody();

    public List<Filter> getDefinedFilters();

    public RestAssuredConfig getConfig();

    public HttpClient getHttpClient();

    public ProxySpecification getProxySpecification();

    public List<String> getUndefinedPathParamPlaceholders();

    public List<String> getPathParamPlaceholders();
}

