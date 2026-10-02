/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.internal.http.ContentEncoding;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

public class DeflateEncoding
extends ContentEncoding {
    private final boolean useNoWrapForInflateDecoding;

    public DeflateEncoding(boolean useNoWrapForInflateDecoding) {
        this.useNoWrapForInflateDecoding = useNoWrapForInflateDecoding;
    }

    @Override
    public String getContentEncoding() {
        return ContentEncoding.Type.DEFLATE.toString();
    }

    @Override
    public HttpEntity wrapResponseEntity(HttpEntity raw) {
        return new InflaterEntity(raw, this.useNoWrapForInflateDecoding);
    }

    public static class InflaterEntity
    extends HttpEntityWrapper {
        private final boolean useNoWrapForInflateDecoding;

        public InflaterEntity(HttpEntity entity, boolean useNoWrapForInflateDecoding) {
            super(entity);
            this.useNoWrapForInflateDecoding = useNoWrapForInflateDecoding;
        }

        @Override
        public InputStream getContent() throws IOException, IllegalStateException {
            return new InflaterInputStream(this.wrappedEntity.getContent(), new Inflater(this.useNoWrapForInflateDecoding));
        }

        @Override
        public long getContentLength() {
            return -1L;
        }
    }
}

