/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.builder;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.response.Response;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class ResponseBuilder {
    private RestAssuredResponseImpl restAssuredResponse = new RestAssuredResponseImpl();

    public ResponseBuilder clone(Response response) {
        if (this.isRestAssuredResponse(response)) {
            RestAssuredResponseImpl raResponse = this.raResponse(response);
            this.restAssuredResponse.setContent(raResponse.getContent());
            this.restAssuredResponse.setHasExpectations(raResponse.getHasExpectations());
            this.restAssuredResponse.setDefaultContentType(raResponse.getDefaultContentType());
            this.restAssuredResponse.setDecoderConfig(raResponse.getDecoderConfig());
            this.restAssuredResponse.setSessionIdName(raResponse.getSessionIdName());
            this.restAssuredResponse.setConnectionManager(raResponse.getConnectionManager());
            this.restAssuredResponse.setConfig(raResponse.getConfig());
            this.restAssuredResponse.setRpr(raResponse.getRpr());
            this.restAssuredResponse.setLogRepository(raResponse.getLogRepository());
            this.restAssuredResponse.setFilterContextProperties(raResponse.getFilterContextProperties());
        } else {
            this.restAssuredResponse.setContent(response.asInputStream());
        }
        this.restAssuredResponse.setContentType(response.getContentType());
        this.restAssuredResponse.setCookies(response.getDetailedCookies());
        this.restAssuredResponse.setResponseHeaders(response.getHeaders());
        this.restAssuredResponse.setStatusCode(response.getStatusCode());
        this.restAssuredResponse.setStatusLine(response.getStatusLine());
        return this;
    }

    public ResponseBuilder setBody(String stringBody) {
        AssertParameter.notNull(stringBody, "Response body");
        this.restAssuredResponse.setContent(stringBody);
        return this;
    }

    public ResponseBuilder setBody(InputStream inputStream) {
        AssertParameter.notNull(inputStream, "Response body");
        this.restAssuredResponse.setContent(inputStream);
        return this;
    }

    public ResponseBuilder setBody(byte[] bytes) {
        AssertParameter.notNull(bytes, "Response body");
        this.restAssuredResponse.setContent(bytes);
        return this;
    }

    public ResponseBuilder setHeaders(Headers headers) {
        AssertParameter.notNull(headers, "Headers");
        this.restAssuredResponse.setResponseHeaders(headers);
        return this;
    }

    public ResponseBuilder setCookies(Cookies cookies) {
        AssertParameter.notNull(cookies, "Cookies");
        this.restAssuredResponse.setCookies(cookies);
        return this;
    }

    public ResponseBuilder setContentType(String contentType) {
        AssertParameter.notNull(contentType, "Content type");
        this.restAssuredResponse.setContentType(contentType);
        this.setHeader("Content-Type", contentType);
        return this;
    }

    public ResponseBuilder setContentType(ContentType contentType) {
        AssertParameter.notNull(contentType, ContentType.class);
        return this.setContentType(contentType.toString());
    }

    public ResponseBuilder setHeader(String name, String value) {
        AssertParameter.notNull(name, "Header name");
        AssertParameter.notNull(value, "Header value");
        ArrayList<Header> newHeaders = new ArrayList<Header>(this.restAssuredResponse.headers().asList());
        newHeaders.add(new Header(name, value));
        this.restAssuredResponse.setResponseHeaders(new Headers(newHeaders));
        if (StringUtils.trim(name).equalsIgnoreCase("Content-Type")) {
            this.restAssuredResponse.setContentType(value);
        }
        return this;
    }

    public ResponseBuilder setStatusLine(String statusLine) {
        AssertParameter.notNull(statusLine, "Status line");
        this.restAssuredResponse.setStatusLine(statusLine);
        return this;
    }

    public ResponseBuilder setStatusCode(int statusCode) {
        this.restAssuredResponse.setStatusCode(statusCode);
        return this;
    }

    public Response build() {
        int statusCode = this.restAssuredResponse.statusCode();
        if (statusCode < 100 || statusCode >= 600) {
            throw new IllegalArgumentException(String.format("Status code must be greater than 100 and less than 600, was %d.", statusCode));
        }
        if (StringUtils.isBlank(this.restAssuredResponse.statusLine())) {
            this.restAssuredResponse.setStatusLine(this.restAssuredResponse.statusCode());
        }
        if (this.restAssuredResponse.getConfig() == null) {
            this.restAssuredResponse.setConfig(RestAssuredConfig.config());
        }
        this.restAssuredResponse.setRpr(new ResponseParserRegistrar());
        return this.restAssuredResponse;
    }

    private boolean isRestAssuredResponse(Response response) {
        return response instanceof RestAssuredResponseImpl;
    }

    private RestAssuredResponseImpl raResponse(Response response) {
        return (RestAssuredResponseImpl)response;
    }
}

