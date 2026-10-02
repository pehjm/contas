/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.AssertParameter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class EncoderConfig
implements Config {
    private static final String UTF_8 = "UTF-8";
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final Map<String, String> DEFAULT_CHARSET_FOR_CONTENT_TYPE = new HashMap<String, String>(){
        {
            this.put("application/json", StandardCharsets.UTF_8.name());
        }
    };
    private final String defaultContentCharset;
    private final String defaultQueryParameterCharset;
    private final boolean shouldAppendDefaultContentCharsetToContentTypeIfUndefined;
    private final Map<String, ContentType> contentEncoders;
    final Map<String, String> contentTypeToDefaultCharset;
    private final boolean isUserDefined;

    public EncoderConfig() {
        this(ISO_8859_1, UTF_8, true, new HashMap<String, ContentType>(), DEFAULT_CHARSET_FOR_CONTENT_TYPE, false);
    }

    public EncoderConfig(String defaultContentCharset, String defaultQueryParameterCharset) {
        this(defaultContentCharset, defaultQueryParameterCharset, true, new HashMap<String, ContentType>(), DEFAULT_CHARSET_FOR_CONTENT_TYPE, true);
    }

    private EncoderConfig(String defaultContentCharset, String defaultQueryParameterCharset, boolean shouldAppendDefaultContentCharsetToContentTypeIfUndefined, Map<String, ContentType> encoders, Map<String, String> contentTypeToDefaultCharset, boolean isUserDefined) {
        Validate.notBlank(defaultContentCharset, "Default encoder content charset to cannot be blank. See \"appendDefaultContentCharsetToContentTypeIfMissing\" method if you like to disable automatically appending the charset to the content-type.", new Object[0]);
        Validate.notBlank(defaultQueryParameterCharset, "Default protocol charset to cannot be blank.", new Object[0]);
        this.contentTypeToDefaultCharset = new HashMap<String, String>(contentTypeToDefaultCharset);
        this.defaultContentCharset = defaultContentCharset;
        this.defaultQueryParameterCharset = defaultQueryParameterCharset;
        this.shouldAppendDefaultContentCharsetToContentTypeIfUndefined = shouldAppendDefaultContentCharsetToContentTypeIfUndefined;
        this.contentEncoders = encoders;
        this.isUserDefined = isUserDefined;
    }

    public String defaultCharsetForContentType(String contentType) {
        if (StringUtils.isEmpty(contentType)) {
            return this.defaultContentCharset();
        }
        String charset = this.defaultCharsetForContentTypeOrNull(contentType);
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

    public String defaultQueryParameterCharset() {
        return this.defaultQueryParameterCharset;
    }

    public EncoderConfig defaultCharsetForContentType(String charset, String contentType) {
        AssertParameter.notNull(charset, "Charset");
        AssertParameter.notNull(contentType, "ContentType");
        HashMap<String, String> map = new HashMap<String, String>(this.contentTypeToDefaultCharset);
        map.put(StringUtils.trim(contentType).toLowerCase(), StringUtils.trim(charset));
        return new EncoderConfig(charset, this.defaultQueryParameterCharset, this.shouldAppendDefaultContentCharsetToContentTypeIfUndefined, this.contentEncoders, map, true);
    }

    public EncoderConfig defaultCharsetForContentType(String charset, ContentType contentType) {
        AssertParameter.notNull(charset, "Charset");
        AssertParameter.notNull(contentType, ContentType.class);
        HashMap<String, String> map = new HashMap<String, String>(this.contentTypeToDefaultCharset);
        for (String ct : contentType.getContentTypeStrings()) {
            map.put(ct.toLowerCase(), StringUtils.trim(charset));
        }
        return new EncoderConfig(charset, this.defaultQueryParameterCharset, this.shouldAppendDefaultContentCharsetToContentTypeIfUndefined, this.contentEncoders, map, true);
    }

    public EncoderConfig defaultCharsetForContentType(Charset charset, ContentType contentType) {
        AssertParameter.notNull(charset, "Charset");
        return this.defaultCharsetForContentType(charset.toString(), contentType);
    }

    public EncoderConfig defaultCharsetForContentType(Charset charset, String contentType) {
        AssertParameter.notNull(charset, "Charset");
        return this.defaultCharsetForContentType(charset.toString(), contentType);
    }

    public EncoderConfig defaultContentCharset(Charset charset) {
        String charsetAsString = AssertParameter.notNull(charset, Charset.class).toString();
        return new EncoderConfig(charsetAsString, this.defaultQueryParameterCharset, this.shouldAppendDefaultContentCharsetToContentTypeIfUndefined, this.contentEncoders, this.contentTypeToDefaultCharset, true);
    }

    public EncoderConfig defaultQueryParameterCharset(String charset) {
        return new EncoderConfig(this.defaultContentCharset, charset, this.shouldAppendDefaultContentCharsetToContentTypeIfUndefined, this.contentEncoders, this.contentTypeToDefaultCharset, true);
    }

    public EncoderConfig defaultQueryParameterCharset(Charset charset) {
        String charsetAsString = AssertParameter.notNull(charset, Charset.class).toString();
        return new EncoderConfig(this.defaultContentCharset, charsetAsString, this.shouldAppendDefaultContentCharsetToContentTypeIfUndefined, this.contentEncoders, this.contentTypeToDefaultCharset, true);
    }

    public EncoderConfig defaultContentCharset(String charset) {
        return new EncoderConfig(charset, this.defaultQueryParameterCharset, this.shouldAppendDefaultContentCharsetToContentTypeIfUndefined, this.contentEncoders, this.contentTypeToDefaultCharset, true);
    }

    public EncoderConfig appendDefaultContentCharsetToContentTypeIfUndefined(boolean shouldAddDefaultContentCharsetToContentTypeIfMissing) {
        return new EncoderConfig(this.defaultContentCharset, this.defaultQueryParameterCharset, shouldAddDefaultContentCharsetToContentTypeIfMissing, this.contentEncoders, this.contentTypeToDefaultCharset, true);
    }

    public boolean shouldAppendDefaultContentCharsetToContentTypeIfUndefined() {
        return this.shouldAppendDefaultContentCharsetToContentTypeIfUndefined;
    }

    public static EncoderConfig encoderConfig() {
        return new EncoderConfig();
    }

    public EncoderConfig and() {
        return this;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserDefined;
    }

    public boolean hasContentEncoders() {
        return !this.contentEncoders.isEmpty();
    }

    public Map<String, ContentType> contentEncoders() {
        return Collections.unmodifiableMap(this.contentEncoders);
    }

    public EncoderConfig encodeContentTypeAs(String contentType, ContentType encoder) {
        AssertParameter.notNull(contentType, "Content-Type to encode");
        AssertParameter.notNull(encoder, ContentType.class);
        HashMap<String, ContentType> newMap = new HashMap<String, ContentType>(this.contentEncoders);
        newMap.put(contentType, encoder);
        return new EncoderConfig(this.defaultContentCharset, this.defaultQueryParameterCharset, this.shouldAppendDefaultContentCharsetToContentTypeIfUndefined, newMap, this.contentTypeToDefaultCharset, true);
    }

    String defaultCharsetForContentTypeOrNull(String contentType) {
        return this.contentTypeToDefaultCharset.get(StringUtils.trim(contentType).toLowerCase());
    }
}

