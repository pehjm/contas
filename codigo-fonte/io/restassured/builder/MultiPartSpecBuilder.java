/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.builder;

import io.restassured.config.EncoderConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.internal.mapping.ObjectMapperSerializationContextImpl;
import io.restassured.internal.mapping.ObjectMapping;
import io.restassured.internal.multipart.MultiPartSpecificationImpl;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.MultiPartSpecification;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.lang3.Validate;

public class MultiPartSpecBuilder {
    private final ObjectMapper explicitObjectMapper;
    private final ObjectMapperType explicitObjectMapperType;
    private Object content;
    private String controlName;
    private String mimeType;
    private String charset;
    private String fileName;
    private Map<String, String> headers;
    private boolean isControlNameExplicit;
    private boolean isFileNameExplicit;

    public MultiPartSpecBuilder(Object content) {
        Validate.notNull(content, "Multi-part content cannot be null", new Object[0]);
        this.content = content;
        this.controlName = "file";
        this.isControlNameExplicit = false;
        this.isFileNameExplicit = false;
        this.explicitObjectMapper = null;
        this.explicitObjectMapperType = null;
        this.headers = new TreeMap<String, String>();
    }

    public MultiPartSpecBuilder(Object content, ObjectMapperType objectMapperType) {
        Validate.notNull(content, "Multi-part content cannot be null", new Object[0]);
        Validate.notNull(objectMapperType, "Object mapper type cannot be null", new Object[0]);
        this.explicitObjectMapperType = objectMapperType;
        this.explicitObjectMapper = null;
        this.content = content;
        this.controlName = "file";
        this.isControlNameExplicit = false;
        this.isFileNameExplicit = false;
        this.headers = new TreeMap<String, String>();
    }

    public MultiPartSpecBuilder(Object content, ObjectMapper objectMapper) {
        Validate.notNull(content, "Multi-part content cannot be null", new Object[0]);
        Validate.notNull(objectMapper, "Object mapper cannot be null", new Object[0]);
        this.explicitObjectMapper = objectMapper;
        this.explicitObjectMapperType = null;
        this.content = content;
        this.controlName = "file";
        this.isControlNameExplicit = false;
        this.isFileNameExplicit = false;
        this.headers = new TreeMap<String, String>();
    }

    public MultiPartSpecBuilder(InputStream content) {
        this((Object)content);
    }

    public MultiPartSpecBuilder(String content) {
        this((Object)content);
    }

    public MultiPartSpecBuilder(byte[] content) {
        this((Object)content);
    }

    public MultiPartSpecBuilder(File content) {
        this((Object)content);
    }

    public MultiPartSpecBuilder controlName(String controlName) {
        Validate.notEmpty(controlName, "Control name cannot be empty", new Object[0]);
        this.controlName = controlName;
        this.isControlNameExplicit = true;
        return this;
    }

    public MultiPartSpecBuilder fileName(String fileName) {
        this.fileName = fileName;
        this.isFileNameExplicit = true;
        return this;
    }

    public MultiPartSpecBuilder header(String name, String value) {
        Validate.notEmpty(name, "Header name cannot be empty", new Object[0]);
        Validate.notEmpty(value, "Header value cannot be empty", new Object[0]);
        Set<String> headerNames = this.headers.keySet();
        String trimmedName = name.trim();
        for (String headerName : headerNames) {
            if (!headerName.equalsIgnoreCase(trimmedName)) continue;
            this.headers.remove(headerName);
        }
        this.headers.put(name, value);
        return this;
    }

    public MultiPartSpecBuilder headers(Map<String, String> headers) {
        this.headers = headers == null ? new HashMap<String, String>() : new HashMap<String, String>(headers);
        return this;
    }

    public MultiPartSpecBuilder mimeType(String mimeType) {
        Validate.notEmpty(mimeType, "Mime-type cannot be empty", new Object[0]);
        this.mimeType = mimeType;
        return this;
    }

    public MultiPartSpecBuilder charset(String charset) {
        Validate.notEmpty(charset, "Charset cannot be empty", new Object[0]);
        if (this.content instanceof byte[] || this.content instanceof InputStream) {
            throw new IllegalArgumentException("Cannot specify charset input streams or byte arrays.");
        }
        this.charset = charset;
        return this;
    }

    public MultiPartSpecBuilder with() {
        return this;
    }

    public MultiPartSpecBuilder and() {
        return this;
    }

    public MultiPartSpecBuilder charset(Charset charset) {
        Validate.notNull(charset, "Charset cannot be null", new Object[0]);
        this.charset = charset.toString();
        return this;
    }

    public MultiPartSpecBuilder emptyFileName() {
        return this.fileName(null);
    }

    public MultiPartSpecification build() {
        MultiPartSpecificationImpl spec = new MultiPartSpecificationImpl();
        spec.setCharset(this.charset);
        this.applyContentToSpec(spec);
        spec.setControlName(this.controlName);
        spec.setFileName(this.fileName);
        spec.setMimeType(this.mimeType);
        spec.setControlNameSpecifiedExplicitly(this.isControlNameExplicit);
        spec.setFileNameSpecifiedExplicitly(this.isFileNameExplicit);
        spec.setHeaders(this.headers);
        return spec;
    }

    private void applyContentToSpec(MultiPartSpecificationImpl spec) {
        Object actualContent;
        if (this.explicitObjectMapper != null) {
            ObjectMapperSerializationContextImpl ctx = new ObjectMapperSerializationContextImpl();
            ctx.setObject(this.content);
            ctx.setContentType(this.mimeType);
            actualContent = this.explicitObjectMapper.serialize(ctx);
        } else {
            actualContent = this.explicitObjectMapperType != null ? ObjectMapping.serialize(this.content, this.mimeType, null, this.explicitObjectMapperType, new ObjectMapperConfig(), new EncoderConfig()) : this.content;
        }
        spec.setContent(actualContent);
    }
}

