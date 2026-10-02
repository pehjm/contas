/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.AssertParameter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class DecoderConfig
implements Config {
    private static final boolean DEFAULT_NO_WRAP_FOR_INFLATE_ENCODED_STREAMS = false;
    private static final String UTF_8 = "UTF-8";
    private static final Map<String, String> DEFAULT_CHARSET_FOR_CONTENT_TYPE = new HashMap<String, String>(){
        {
            this.put(ContentType.JSON.toString(), DecoderConfig.UTF_8);
            this.put("text/json", DecoderConfig.UTF_8);
        }
    };
    private final String defaultContentCharset;
    private final List<ContentDecoder> contentDecoders;
    private final Map<String, String> contentTypeToDefaultCharset;
    private final boolean useNoWrapForInflateDecoding;
    private final boolean isUserConfigured;

    public DecoderConfig() {
        this(Charset.defaultCharset().toString(), false, false, DEFAULT_CHARSET_FOR_CONTENT_TYPE, DecoderConfig.defaultContentEncoders());
    }

    public DecoderConfig(String defaultContentCharset) {
        this(defaultContentCharset, false, true, DEFAULT_CHARSET_FOR_CONTENT_TYPE, DecoderConfig.defaultContentEncoders());
    }

    public DecoderConfig(ContentDecoder contentDecoder, ContentDecoder ... additionalContentDecoders) {
        this(Charset.defaultCharset().toString(), false, true, DEFAULT_CHARSET_FOR_CONTENT_TYPE, DecoderConfig.merge(contentDecoder, additionalContentDecoders));
    }

    private DecoderConfig(String defaultContentCharset, boolean useNoWrapForInflateDecoding, boolean isUserConfigured, Map<String, String> contentTypeToDefaultCharset, ContentDecoder ... contentDecoders) {
        this(defaultContentCharset, useNoWrapForInflateDecoding, isUserConfigured, contentDecoders == null ? Collections.emptyList() : Arrays.asList(contentDecoders), contentTypeToDefaultCharset);
    }

    private DecoderConfig(String defaultContentCharset, boolean useNoWrapForInflateDecoding, boolean isUserConfigured, List<ContentDecoder> contentDecoders, Map<String, String> contentTypeToDefaultCharset) {
        Validate.notBlank(defaultContentCharset, "Default decoder content charset to cannot be blank", new Object[0]);
        this.contentTypeToDefaultCharset = new HashMap<String, String>(contentTypeToDefaultCharset);
        this.defaultContentCharset = defaultContentCharset;
        this.contentDecoders = Collections.unmodifiableList(contentDecoders == null ? Collections.emptyList() : contentDecoders);
        this.useNoWrapForInflateDecoding = useNoWrapForInflateDecoding;
        this.isUserConfigured = isUserConfigured;
    }

    public DecoderConfig defaultCharsetForContentType(String charset, String contentType) {
        AssertParameter.notNull(charset, "Charset");
        AssertParameter.notNull(contentType, "ContentType");
        HashMap<String, String> map = new HashMap<String, String>(this.contentTypeToDefaultCharset);
        map.put(StringUtils.trim(contentType).toLowerCase(), StringUtils.trim(charset));
        return new DecoderConfig(charset, this.useNoWrapForInflateDecoding, true, this.contentDecoders, map);
    }

    public DecoderConfig defaultCharsetForContentType(Charset charset, String contentType) {
        AssertParameter.notNull(charset, "Charset");
        return this.defaultCharsetForContentType(charset.toString(), contentType);
    }

    public DecoderConfig defaultCharsetForContentType(Charset charset, ContentType contentType) {
        AssertParameter.notNull(charset, "Charset");
        return this.defaultCharsetForContentType(charset.toString(), contentType);
    }

    public DecoderConfig defaultCharsetForContentType(String charset, ContentType contentType) {
        AssertParameter.notNull(charset, "Charset");
        AssertParameter.notNull(contentType, ContentType.class);
        HashMap<String, String> map = new HashMap<String, String>(this.contentTypeToDefaultCharset);
        for (String ct : contentType.getContentTypeStrings()) {
            map.put(ct.toLowerCase(), StringUtils.trim(charset));
        }
        return new DecoderConfig(charset, this.useNoWrapForInflateDecoding, true, this.contentDecoders, map);
    }

    public String defaultCharsetForContentType(String contentType) {
        if (StringUtils.isEmpty(contentType)) {
            return this.defaultContentCharset();
        }
        String charset = this.contentTypeToDefaultCharset.get(StringUtils.trim(contentType).toLowerCase());
        if (charset == null) {
            return this.defaultContentCharset();
        }
        return charset;
    }

    public String defaultCharsetForContentType(ContentType contentType) {
        if (contentType == null) {
            return this.defaultContentCharset();
        }
        return this.defaultCharsetForContentType(contentType.toString());
    }

    public boolean hasDefaultCharsetForContentType(String contentType) {
        return !StringUtils.isBlank(contentType) && this.contentTypeToDefaultCharset.containsKey(StringUtils.trim(contentType).toLowerCase());
    }

    public String defaultContentCharset() {
        return this.defaultContentCharset;
    }

    public List<ContentDecoder> contentDecoders() {
        return this.contentDecoders;
    }

    public DecoderConfig useNoWrapForInflateDecoding(boolean nowrap) {
        return new DecoderConfig(this.defaultContentCharset, nowrap, true, this.contentDecoders, this.contentTypeToDefaultCharset);
    }

    public boolean shouldUseNoWrapForInflateDecoding() {
        return this.useNoWrapForInflateDecoding;
    }

    public DecoderConfig defaultContentCharset(String charset) {
        return new DecoderConfig(charset, this.useNoWrapForInflateDecoding, true, this.contentDecoders, this.contentTypeToDefaultCharset);
    }

    public DecoderConfig defaultContentCharset(Charset charset) {
        String charsetAsString = AssertParameter.notNull(charset, Charset.class).toString();
        return new DecoderConfig(charsetAsString, this.useNoWrapForInflateDecoding, true, this.contentDecoders, this.contentTypeToDefaultCharset);
    }

    public DecoderConfig contentDecoders(ContentDecoder contentDecoder, ContentDecoder ... additionalContentDecoders) {
        return new DecoderConfig(this.defaultContentCharset, this.useNoWrapForInflateDecoding, true, this.contentTypeToDefaultCharset, DecoderConfig.merge(contentDecoder, additionalContentDecoders));
    }

    public DecoderConfig noContentDecoders() {
        return new DecoderConfig(this.defaultContentCharset, this.useNoWrapForInflateDecoding, true, this.contentTypeToDefaultCharset, new ContentDecoder[0]);
    }

    public static DecoderConfig decoderConfig() {
        return new DecoderConfig();
    }

    public DecoderConfig and() {
        return this;
    }

    public DecoderConfig with() {
        return this;
    }

    private static ContentDecoder[] defaultContentEncoders() {
        return new ContentDecoder[]{ContentDecoder.GZIP, ContentDecoder.DEFLATE};
    }

    private static ContentDecoder[] merge(ContentDecoder contentDecoder, ContentDecoder[] additionalContentDecoders) {
        ContentDecoder[] contentDecoders;
        Validate.notNull(contentDecoder, "Content decoder cannot be null", new Object[0]);
        if (additionalContentDecoders == null || additionalContentDecoders.length == 0) {
            contentDecoders = new ContentDecoder[]{contentDecoder};
        } else {
            contentDecoders = new ContentDecoder[additionalContentDecoders.length + 1];
            contentDecoders[0] = contentDecoder;
            System.arraycopy(additionalContentDecoders, 0, contentDecoders, 1, additionalContentDecoders.length);
        }
        return contentDecoders;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }

    public static enum ContentDecoder {
        GZIP,
        DEFLATE;

    }
}

