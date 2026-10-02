/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.internal.common.assertion.AssertParameter;
import java.net.URI;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class CustomHttpMethod
extends HttpEntityEnclosingRequestBase {
    private final String methodName;

    public CustomHttpMethod(String methodName, String uri) {
        this(methodName, URI.create(uri));
    }

    public CustomHttpMethod(String methodName, URI uri) {
        AssertParameter.notNull(methodName, "Method");
        this.methodName = StringUtils.trim(methodName).toUpperCase();
        this.setURI(uri);
    }

    @Override
    public String getMethod() {
        return this.methodName;
    }
}

