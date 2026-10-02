/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.internal.http.HttpContextDecorator;
import io.restassured.internal.http.HttpResponseContentTypeFinder;
import io.restassured.internal.http.Status;
import java.util.Iterator;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.params.HttpParams;

public class HttpResponseDecorator
implements HttpResponse {
    HeadersDecorator headers = null;
    HttpResponse responseBase;
    HttpContextDecorator context;
    Object responseData;

    public HttpResponseDecorator(HttpResponse base, Object parsedResponse) {
        this(base, null, parsedResponse);
    }

    public HttpResponseDecorator(HttpResponse base, HttpContextDecorator context, Object parsedResponse) {
        this.responseBase = base;
        this.context = context;
        this.responseData = parsedResponse;
    }

    public HeadersDecorator getHeaders() {
        if (this.headers == null) {
            this.headers = new HeadersDecorator();
        }
        return this.headers;
    }

    public boolean isSuccess() {
        return Status.find(this.getStatus()) == Status.SUCCESS;
    }

    public int getStatus() {
        return this.responseBase.getStatusLine().getStatusCode();
    }

    public String getContentType() {
        return HttpResponseContentTypeFinder.findContentType(this.responseBase);
    }

    public Object getData() {
        return this.responseData;
    }

    void setData(Object responseData) {
        this.responseData = responseData;
    }

    public HttpContextDecorator getContext() {
        return this.context;
    }

    @Override
    public HttpEntity getEntity() {
        return this.responseBase.getEntity();
    }

    @Override
    public Locale getLocale() {
        return this.responseBase.getLocale();
    }

    @Override
    public StatusLine getStatusLine() {
        return this.responseBase.getStatusLine();
    }

    @Override
    public void setEntity(HttpEntity arg0) {
        this.responseBase.setEntity(arg0);
    }

    @Override
    public void setLocale(Locale arg0) {
        this.responseBase.setLocale(arg0);
    }

    @Override
    public void setReasonPhrase(String arg0) throws IllegalStateException {
        this.responseBase.setReasonPhrase(arg0);
    }

    @Override
    public void setStatusCode(int arg0) throws IllegalStateException {
        this.responseBase.setStatusCode(arg0);
    }

    @Override
    public void setStatusLine(StatusLine arg0) {
        this.responseBase.setStatusLine(arg0);
    }

    @Override
    public void setStatusLine(ProtocolVersion arg0, int arg1) {
        this.responseBase.setStatusLine(arg0, arg1);
    }

    @Override
    public void setStatusLine(ProtocolVersion arg0, int arg1, String arg2) {
        this.responseBase.setStatusLine(arg0, arg1, arg2);
    }

    @Override
    public void addHeader(Header arg0) {
        this.responseBase.addHeader(arg0);
    }

    @Override
    public void addHeader(String arg0, String arg1) {
        this.responseBase.addHeader(arg0, arg1);
    }

    @Override
    public boolean containsHeader(String arg0) {
        return this.responseBase.containsHeader(arg0);
    }

    @Override
    public Header[] getAllHeaders() {
        return this.responseBase.getAllHeaders();
    }

    @Override
    public Header getFirstHeader(String arg0) {
        return this.responseBase.getFirstHeader(arg0);
    }

    @Override
    public Header[] getHeaders(String arg0) {
        return this.responseBase.getHeaders(arg0);
    }

    @Override
    public Header getLastHeader(String arg0) {
        return this.responseBase.getLastHeader(arg0);
    }

    @Override
    public HttpParams getParams() {
        return this.responseBase.getParams();
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return this.responseBase.getProtocolVersion();
    }

    @Override
    public HeaderIterator headerIterator() {
        return this.responseBase.headerIterator();
    }

    @Override
    public HeaderIterator headerIterator(String arg0) {
        return this.responseBase.headerIterator(arg0);
    }

    @Override
    public void removeHeader(Header arg0) {
        this.responseBase.removeHeader(arg0);
    }

    @Override
    public void removeHeaders(String arg0) {
        this.responseBase.removeHeaders(arg0);
    }

    @Override
    public void setHeader(Header arg0) {
        this.responseBase.setHeader(arg0);
    }

    @Override
    public void setHeader(String arg0, String arg1) {
        this.responseBase.setHeader(arg0, arg1);
    }

    @Override
    public void setHeaders(Header[] arg0) {
        this.responseBase.setHeaders(arg0);
    }

    @Override
    public void setParams(HttpParams arg0) {
        this.responseBase.setParams(arg0);
    }

    public final class HeadersDecorator
    implements Iterable<Object> {
        public Header getAt(String name) {
            return HttpResponseDecorator.this.responseBase.getFirstHeader(name);
        }

        protected String propertyMissing(String name) {
            Header h = this.getAt(name);
            return h != null ? h.getValue() : null;
        }

        @Override
        public Iterator<Object> iterator() {
            return HttpResponseDecorator.this.responseBase.headerIterator();
        }
    }
}

