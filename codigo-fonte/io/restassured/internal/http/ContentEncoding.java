/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

public abstract class ContentEncoding {
    public static final String ACCEPT_ENC_HDR = "Accept-Encoding";
    public static final String CONTENT_ENC_HDR = "Content-Encoding";

    protected abstract String getContentEncoding();

    protected abstract HttpEntity wrapResponseEntity(HttpEntity var1);

    public HttpRequestInterceptor getRequestInterceptor() {
        return new RequestInterceptor();
    }

    public HttpResponseInterceptor getResponseInterceptor() {
        return new ResponseInterceptor();
    }

    protected class ResponseInterceptor
    implements HttpResponseInterceptor {
        protected ResponseInterceptor() {
        }

        @Override
        public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
            if (this.hasEncoding(response, ContentEncoding.this.getContentEncoding())) {
                response.setEntity(ContentEncoding.this.wrapResponseEntity(response.getEntity()));
            }
        }

        protected boolean hasEncoding(HttpResponse response, String encoding) {
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return false;
            }
            Header ceHeader = entity.getContentEncoding();
            if (ceHeader == null) {
                return false;
            }
            HeaderElement[] codecs = ceHeader.getElements();
            for (int i = 0; i < codecs.length; ++i) {
                if (!encoding.equalsIgnoreCase(codecs[i].getName())) continue;
                return true;
            }
            return false;
        }
    }

    protected class RequestInterceptor
    implements HttpRequestInterceptor {
        protected RequestInterceptor() {
        }

        @Override
        public void process(HttpRequest req, HttpContext context) throws HttpException, IOException {
            String encoding = ContentEncoding.this.getContentEncoding();
            if (!req.containsHeader(ContentEncoding.ACCEPT_ENC_HDR)) {
                req.addHeader(ContentEncoding.ACCEPT_ENC_HDR, encoding);
            } else {
                StringBuilder values = new StringBuilder();
                for (Header h : req.getHeaders(ContentEncoding.ACCEPT_ENC_HDR)) {
                    values.append(h.getValue()).append(",");
                }
                String encList = !values.toString().contains(encoding) ? values.append(encoding).toString() : values.toString().substring(0, values.lastIndexOf(","));
                req.setHeader(ContentEncoding.ACCEPT_ENC_HDR, encList);
            }
        }
    }

    public static enum Type {
        GZIP,
        COMPRESS,
        DEFLATE;


        public String toString() {
            return this.name().toLowerCase();
        }
    }
}

