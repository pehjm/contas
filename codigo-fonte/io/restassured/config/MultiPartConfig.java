/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.internal.common.assertion.AssertParameter;
import java.nio.charset.Charset;
import org.apache.commons.lang3.StringUtils;

public class MultiPartConfig
implements Config {
    private static final String DEFAULT_CONTROL_NAME = "file";
    private static final String DEFAULT_FILE_NAME = "file";
    private static final String DEFAULT_SUBTYPE = "form-data";
    private static final String DEFAULT_CHARSET = null;
    private static final String DEFAULT_BOUNDARY = null;
    private final String defaultControlName;
    private final String defaultFileName;
    private final String defaultSubtype;
    private final String defaultBoundary;
    private final String defaultCharset;
    private final boolean isUserConfigured;

    public MultiPartConfig() {
        this("file", "file", DEFAULT_SUBTYPE, DEFAULT_BOUNDARY, DEFAULT_CHARSET, false);
    }

    private MultiPartConfig(String defaultControlName, String defaultFileName, String defaultSubtype, String defaultBoundary, String defaultCharset, boolean isUserConfigured) {
        this.defaultControlName = defaultControlName;
        this.defaultBoundary = defaultBoundary;
        this.defaultFileName = StringUtils.trimToNull(defaultFileName);
        this.defaultSubtype = StringUtils.trimToNull(defaultSubtype);
        this.defaultCharset = StringUtils.trimToNull(defaultCharset);
        AssertParameter.notNull(this.defaultControlName, "Default control name");
        AssertParameter.notNull(this.defaultSubtype, "Default subtype");
        this.isUserConfigured = isUserConfigured;
    }

    public MultiPartConfig defaultControlName(String defaultControlName) {
        return new MultiPartConfig(defaultControlName, this.defaultFileName, this.defaultSubtype, this.defaultBoundary, this.defaultCharset, true);
    }

    public MultiPartConfig defaultFileName(String defaultFileName) {
        return new MultiPartConfig(this.defaultControlName, defaultFileName, this.defaultSubtype, this.defaultBoundary, this.defaultCharset, true);
    }

    public MultiPartConfig defaultSubtype(String defaultSubtype) {
        return new MultiPartConfig(this.defaultControlName, this.defaultFileName, defaultSubtype, this.defaultBoundary, this.defaultCharset, true);
    }

    public MultiPartConfig emptyDefaultFileName() {
        return new MultiPartConfig(this.defaultControlName, null, this.defaultSubtype, this.defaultBoundary, this.defaultCharset, true);
    }

    public String defaultControlName() {
        return this.defaultControlName;
    }

    public String defaultFileName() {
        return this.defaultFileName;
    }

    public String defaultSubtype() {
        return this.defaultSubtype;
    }

    public MultiPartConfig defaultBoundary(String defaultBoundary) {
        return new MultiPartConfig(this.defaultControlName, this.defaultFileName, this.defaultSubtype, defaultBoundary, this.defaultCharset, true);
    }

    public MultiPartConfig defaultCharset(String defaultCharset) {
        return new MultiPartConfig(this.defaultControlName, this.defaultFileName, this.defaultSubtype, this.defaultBoundary, defaultCharset, true);
    }

    public MultiPartConfig defaultCharset(Charset defaultCharset) {
        String charsetAsString = AssertParameter.notNull(defaultCharset, Charset.class).toString();
        return new MultiPartConfig(this.defaultControlName, this.defaultFileName, this.defaultSubtype, this.defaultBoundary, charsetAsString, true);
    }

    public String defaultBoundary() {
        return this.defaultBoundary;
    }

    public String defaultCharset() {
        return this.defaultCharset;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }

    public static MultiPartConfig multiPartConfig() {
        return new MultiPartConfig();
    }

    public MultiPartConfig and() {
        return this;
    }

    public MultiPartConfig with() {
        return this;
    }
}

