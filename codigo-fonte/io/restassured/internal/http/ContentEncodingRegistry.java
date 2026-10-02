/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.config.DecoderConfig;
import io.restassured.internal.http.ContentEncoding;
import io.restassured.internal.http.DeflateEncoding;
import io.restassured.internal.http.GZIPEncoding;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.impl.client.AbstractHttpClient;

public class ContentEncodingRegistry {
    private final boolean useNoWrapForInflateDecoding;
    protected Map<String, ContentEncoding> availableEncoders;

    public ContentEncodingRegistry(DecoderConfig decoderConfig) {
        this.useNoWrapForInflateDecoding = (decoderConfig == null ? DecoderConfig.decoderConfig() : decoderConfig).shouldUseNoWrapForInflateDecoding();
        this.availableEncoders = this.getDefaultEncoders();
    }

    protected Map<String, ContentEncoding> getDefaultEncoders() {
        HashMap<String, ContentEncoding> map = new HashMap<String, ContentEncoding>();
        map.put(ContentEncoding.Type.GZIP.toString(), new GZIPEncoding());
        map.put(ContentEncoding.Type.DEFLATE.toString(), new DeflateEncoding(this.useNoWrapForInflateDecoding));
        return map;
    }

    void setInterceptors(AbstractHttpClient client, Object ... encodings) {
        client.removeRequestInterceptorByClass(ContentEncoding.RequestInterceptor.class);
        client.removeResponseInterceptorByClass(ContentEncoding.ResponseInterceptor.class);
        for (Object encName : encodings) {
            ContentEncoding enc = this.availableEncoders.get(encName.toString());
            if (enc == null) continue;
            client.addRequestInterceptor(enc.getRequestInterceptor());
            client.addResponseInterceptor(enc.getResponseInterceptor());
        }
    }
}

