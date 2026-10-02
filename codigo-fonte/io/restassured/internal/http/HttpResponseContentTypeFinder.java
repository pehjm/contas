/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class HttpResponseContentTypeFinder {
    public static String findContentType(HttpResponse resp) {
        Header contentTypeHeader = resp.getFirstHeader("Content-Type");
        if (contentTypeHeader == null) {
            throw new IllegalArgumentException("Response does not have a content-type header");
        }
        try {
            return contentTypeHeader.getValue();
        }
        catch (RuntimeException ex) {
            throw new IllegalArgumentException("Could not parse content-type from response");
        }
    }
}

