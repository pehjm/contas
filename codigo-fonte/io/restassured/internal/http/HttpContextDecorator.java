/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class HttpContextDecorator
implements HttpContext {
    protected HttpContext delegate;

    public HttpContextDecorator() {
        this.delegate = new BasicHttpContext();
    }

    public HttpContextDecorator(HttpContext delegate) {
        this.delegate = new BasicHttpContext(delegate);
    }

    public Object getAt(String name) {
        return this.getAttribute(name);
    }

    public void setAt(String name, Object val) {
        this.setAttribute(name, val);
    }

    @Override
    public Object getAttribute(String name) {
        return this.delegate.getAttribute(name);
    }

    @Override
    public Object removeAttribute(String name) {
        return this.delegate.removeAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object val) {
        this.delegate.setAttribute(name, val);
    }
}

