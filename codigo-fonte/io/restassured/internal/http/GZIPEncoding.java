/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.internal.http.ContentEncoding;
import io.restassured.internal.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

public class GZIPEncoding
extends ContentEncoding {
    @Override
    public String getContentEncoding() {
        return ContentEncoding.Type.GZIP.toString();
    }

    @Override
    public HttpEntity wrapResponseEntity(HttpEntity raw) {
        return new GZIPDecompressingEntity(raw);
    }

    protected static class GZIPDecompressingEntity
    extends HttpEntityWrapper {
        public GZIPDecompressingEntity(HttpEntity entity) {
            super(entity);
        }

        @Override
        public InputStream getContent() throws IOException, IllegalStateException {
            InputStream content = this.wrappedEntity.getContent();
            byte[] bytes = IOUtils.toByteArray(content);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            if (bytes.length > 0) {
                return new GZIPInputStream(inputStream);
            }
            return inputStream;
        }

        @Override
        public long getContentLength() {
            return -1L;
        }
    }
}

