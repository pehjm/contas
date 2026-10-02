/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.http;

import io.restassured.internal.http.ContentTypeExtractor;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public enum ContentType {
    ANY("*/*"),
    TEXT("text/plain"),
    JSON("application/json", "application/javascript", "text/javascript", "text/json"),
    XML("application/xml", "text/xml", "application/xhtml+xml"),
    HTML("text/html"),
    URLENC("application/x-www-form-urlencoded"),
    BINARY("application/octet-stream"),
    MULTIPART("multipart/form-data", "multipart/alternative", "multipart/byteranges", "multipart/digest", "multipart/mixed", "multipart/parallel", "multipart/related", "multipart/report", "multipart/signed", "multipart/encrypted");

    private static final String PLUS_XML = "+xml";
    private static final String PLUS_JSON = "+json";
    private static final String PLUS_HTML = "+html";
    private final String[] ctStrings;

    public String[] getContentTypeStrings() {
        return this.ctStrings;
    }

    public String toString() {
        return this.ctStrings[0];
    }

    public String getAcceptHeader() {
        Iterator<String> iter = Arrays.asList(this.ctStrings).iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            sb.append(iter.next());
            if (!iter.hasNext()) continue;
            sb.append(", ");
        }
        return sb.toString();
    }

    public String withCharset(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset cannot be null");
        }
        return this.withCharset(charset.toString());
    }

    public String withCharset(String charset) {
        if (StringUtils.isBlank(charset)) {
            throw new IllegalArgumentException("charset cannot be empty");
        }
        return String.format("%s; charset=%s", this.toString(), StringUtils.trim(charset));
    }

    private ContentType(String ... contentTypes) {
        this.ctStrings = contentTypes;
    }

    public static ContentType fromContentType(String contentType) {
        if (contentType == null) {
            return null;
        }
        ContentType foundContentType = ArrayUtils.contains(ContentType.XML.ctStrings, contentType = ContentTypeExtractor.getContentTypeWithoutCharset(contentType.toLowerCase())) || StringUtils.endsWithIgnoreCase(contentType, PLUS_XML) ? XML : (ArrayUtils.contains(ContentType.JSON.ctStrings, contentType) || StringUtils.endsWithIgnoreCase(contentType, PLUS_JSON) ? JSON : (ArrayUtils.contains(ContentType.TEXT.ctStrings, contentType) ? TEXT : (ArrayUtils.contains(ContentType.HTML.ctStrings, contentType) || StringUtils.endsWithIgnoreCase(contentType, PLUS_HTML) ? HTML : (ArrayUtils.contains(ContentType.URLENC.ctStrings, contentType) ? URLENC : (ArrayUtils.contains(ContentType.BINARY.ctStrings, contentType) ? BINARY : (ArrayUtils.contains(ContentType.ANY.ctStrings, contentType) ? ANY : (ArrayUtils.contains(ContentType.MULTIPART.ctStrings, contentType) ? MULTIPART : null)))))));
        return foundContentType;
    }

    public boolean matches(String contentType) {
        String expectedContentType = StringUtils.trimToNull(contentType);
        if (expectedContentType == null) {
            return false;
        }
        for (String supportedContentType : this.getContentTypeStrings()) {
            if (!supportedContentType.equalsIgnoreCase(expectedContentType)) continue;
            return true;
        }
        return false;
    }
}

