/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.common.assertion.AssertParameter;
import java.net.URI;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;

public class CsrfConfig
implements Config {
    public static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";
    public static final String DEFAULT_CSRF_INPUT_FIELD_NAME = "_csrf";
    public static final String DEFAULT_CSRF_META_TAG_NAME = "_csrf_header";
    private final boolean isUserConfigured;
    private final String csrfTokenPath;
    private final String csrfInputFieldName;
    private final String csrfMetaTagName;
    private final String csrfHeaderName;
    private final CsrfPrioritization csrfPrioritization;
    private final LogConfig logConfig;
    private final LogDetail logDetail;

    public CsrfConfig() {
        this(null, DEFAULT_CSRF_INPUT_FIELD_NAME, DEFAULT_CSRF_META_TAG_NAME, DEFAULT_CSRF_HEADER_NAME, CsrfPrioritization.HEADER, null, null, false);
    }

    public CsrfConfig(String csrfTokenPath) {
        this(AssertParameter.notNull(StringUtils.trimToNull(csrfTokenPath), "csrfTokenPath"), DEFAULT_CSRF_INPUT_FIELD_NAME, DEFAULT_CSRF_META_TAG_NAME, DEFAULT_CSRF_HEADER_NAME, CsrfPrioritization.HEADER, null, null, true);
    }

    public CsrfConfig(URI csrfTokenPath) {
        this(AssertParameter.notNull(csrfTokenPath, "csrfTokenPath").toString());
    }

    public CsrfConfig(URL csrfTokenPath) {
        this(AssertParameter.notNull(csrfTokenPath, "csrfTokenPath").toString());
    }

    private CsrfConfig(String csrfTokenPath, String csrfInputFieldName, String csrfMetaTagName, String csrfHeaderName, CsrfPrioritization csrfPrioritization, LogConfig logConfig, LogDetail logDetail, boolean isUserConfigured) {
        AssertParameter.notNull(csrfPrioritization, CsrfPrioritization.class);
        AssertParameter.notNull(StringUtils.trimToNull(csrfInputFieldName), "csrfInputFieldName");
        AssertParameter.notNull(StringUtils.trimToNull(csrfMetaTagName), "csrfMetaTagName");
        AssertParameter.notNull(StringUtils.trimToNull(csrfHeaderName), "csrfHeaderName");
        this.csrfTokenPath = StringUtils.trimToNull(csrfTokenPath);
        this.csrfInputFieldName = StringUtils.trimToNull(csrfInputFieldName);
        this.csrfMetaTagName = StringUtils.trimToNull(csrfMetaTagName);
        this.csrfHeaderName = StringUtils.trimToNull(csrfHeaderName);
        this.csrfPrioritization = csrfPrioritization;
        this.logConfig = logConfig;
        this.logDetail = logDetail;
        this.isUserConfigured = isUserConfigured;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }

    public boolean isCsrfEnabled() {
        return this.csrfTokenPath != null;
    }

    public static CsrfConfig csrfConfig() {
        return new CsrfConfig();
    }

    public CsrfConfig csrfMetaTagName(String csrfMetaTagName) {
        AssertParameter.notNull(StringUtils.trimToNull(csrfMetaTagName), "CSRF meta tag name");
        return new CsrfConfig(this.csrfTokenPath, csrfMetaTagName, csrfMetaTagName, this.csrfHeaderName, this.csrfPrioritization, this.logConfig, this.logDetail, true);
    }

    public CsrfConfig csrfInputFieldName(String inputFieldName) {
        AssertParameter.notNull(StringUtils.trimToNull(inputFieldName), "CSRF input field name");
        return new CsrfConfig(this.csrfTokenPath, inputFieldName, this.csrfMetaTagName, this.csrfHeaderName, this.csrfPrioritization, this.logConfig, this.logDetail, true);
    }

    public CsrfConfig loggingEnabled() {
        return this.loggingEnabled(LogDetail.ALL);
    }

    public CsrfConfig loggingEnabled(LogDetail logDetail) {
        return this.loggingEnabled(logDetail, new LogConfig());
    }

    public CsrfConfig loggingEnabled(LogConfig logConfig) {
        return this.loggingEnabled(LogDetail.ALL, logConfig);
    }

    public CsrfConfig loggingEnabled(LogDetail logDetail, LogConfig logConfig) {
        AssertParameter.notNull(logDetail, LogDetail.class);
        AssertParameter.notNull(logConfig, LogConfig.class);
        return new CsrfConfig(this.csrfTokenPath, this.csrfInputFieldName, this.csrfMetaTagName, this.csrfHeaderName, this.csrfPrioritization, logConfig, logDetail, true);
    }

    public CsrfConfig csrfHeaderName(String csrfHeaderName) {
        AssertParameter.notNull(StringUtils.trimToNull(csrfHeaderName), "CSRF header name");
        return new CsrfConfig(this.csrfTokenPath, this.csrfInputFieldName, this.csrfMetaTagName, csrfHeaderName, this.csrfPrioritization, this.logConfig, this.logDetail, true);
    }

    public CsrfConfig with() {
        return this;
    }

    public CsrfConfig and() {
        return this;
    }

    public CsrfPrioritization getCsrfPrioritization() {
        return this.csrfPrioritization;
    }

    public boolean isCsrfPrioritization(CsrfPrioritization csrfPrioritization) {
        return this.csrfPrioritization == csrfPrioritization;
    }

    public CsrfConfig csrfPrioritization(CsrfPrioritization csrfPrioritization) {
        return new CsrfConfig(this.csrfTokenPath, this.csrfInputFieldName, this.csrfMetaTagName, this.csrfHeaderName, csrfPrioritization, this.logConfig, this.logDetail, true);
    }

    public CsrfConfig csrfTokenPath(String csrfTokenPath) {
        return new CsrfConfig(AssertParameter.notNull(StringUtils.trimToNull(csrfTokenPath), "csrfTokenPath"), this.csrfInputFieldName, this.csrfMetaTagName, this.csrfHeaderName, this.csrfPrioritization, this.logConfig, this.logDetail, true);
    }

    public CsrfConfig csrfTokenPath(URI csrfTokenPath) {
        return this.csrfTokenPath(AssertParameter.notNull(csrfTokenPath, "csrfTokenPath").toString());
    }

    public CsrfConfig csrfTokenPath(URL csrfTokenPath) {
        return this.csrfTokenPath(AssertParameter.notNull(csrfTokenPath, "csrfTokenPath").toString());
    }

    public String getCsrfTokenPath() {
        return this.csrfTokenPath;
    }

    public String getCsrfMetaTagName() {
        return this.csrfMetaTagName;
    }

    public String getCsrfHeaderName() {
        return this.csrfHeaderName;
    }

    public String getCsrfInputFieldName() {
        return this.csrfInputFieldName;
    }

    public LogConfig getLogConfig() {
        return this.logConfig;
    }

    public boolean isLoggingEnabled() {
        return this.logConfig != null && this.logDetail != null;
    }

    public LogDetail getLogDetail() {
        return this.logDetail;
    }

    public static enum CsrfPrioritization {
        FORM,
        HEADER;

    }
}

