/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.proxy;

import io.restassured.specification.ProxySpecification;
import java.net.ProxySelector;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.apache.http.protocol.HttpContext;

public class RestAssuredProxySelectorRoutePlanner
extends ProxySelectorRoutePlanner {
    private final String scheme;

    public RestAssuredProxySelectorRoutePlanner(SchemeRegistry schemeRegistry, ProxySelector proxySelector, ProxySpecification proxySpecification) {
        super(schemeRegistry, proxySelector);
        this.scheme = proxySpecification == null ? null : proxySpecification.getScheme();
    }

    @Override
    protected HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
        HttpHost httpHost = super.determineProxy(target, request, context);
        if (this.scheme != null && !this.scheme.equalsIgnoreCase(httpHost.getSchemeName())) {
            httpHost = new HttpHost(httpHost.getHostName(), httpHost.getPort(), this.scheme);
        }
        return httpHost;
    }
}

