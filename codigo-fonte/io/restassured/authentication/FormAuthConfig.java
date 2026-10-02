/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.authentication;

import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.common.assertion.AssertParameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class FormAuthConfig {
    private final String formAction;
    private final String userInputTagName;
    private final String passwordInputTagName;
    private final LogConfig logConfig;
    private final LogDetail logDetail;
    private final List<String> additionalInputFieldNames;

    public FormAuthConfig(String formAction, String userNameInputTagName, String passwordInputTagName) {
        this(formAction, userNameInputTagName, passwordInputTagName, null, null, Collections.emptyList());
    }

    public FormAuthConfig() {
        this(null, null, null);
    }

    private FormAuthConfig(String formAction, String userNameInputTagName, String passwordInputTagName, LogDetail logDetail, LogConfig logConfig, List<String> additionalInputFieldNames) {
        this.formAction = formAction;
        this.userInputTagName = userNameInputTagName;
        this.passwordInputTagName = passwordInputTagName;
        this.logDetail = logDetail;
        this.logConfig = logConfig;
        this.additionalInputFieldNames = Collections.unmodifiableList(additionalInputFieldNames);
    }

    public static FormAuthConfig springSecurity() {
        return new FormAuthConfig("/j_spring_security_check", "j_username", "j_password");
    }

    public FormAuthConfig withAdditionalField(String fieldName) {
        AssertParameter.notNull(fieldName, "Additional field name");
        ArrayList<String> list = new ArrayList<String>(this.additionalInputFieldNames);
        list.add(fieldName);
        return new FormAuthConfig(this.formAction, this.userInputTagName, this.passwordInputTagName, this.logDetail, this.logConfig, list);
    }

    public FormAuthConfig withAdditionalFields(String firstFieldName, String secondFieldName, String ... additionalFieldNames) {
        AssertParameter.notNull(firstFieldName, "First additional field name");
        AssertParameter.notNull(secondFieldName, "Second additional field name");
        ArrayList<String> list = new ArrayList<String>(this.additionalInputFieldNames);
        list.add(firstFieldName);
        list.add(secondFieldName);
        if (additionalFieldNames != null && additionalFieldNames.length > 0) {
            list.addAll(Arrays.asList(additionalFieldNames));
        }
        return new FormAuthConfig(this.formAction, this.userInputTagName, this.passwordInputTagName, this.logDetail, this.logConfig, list);
    }

    public FormAuthConfig withLoggingEnabled() {
        return this.withLoggingEnabled(LogDetail.ALL);
    }

    public FormAuthConfig withLoggingEnabled(LogDetail logDetail) {
        return this.withLoggingEnabled(logDetail, new LogConfig());
    }

    public FormAuthConfig withLoggingEnabled(LogConfig logConfig) {
        return this.withLoggingEnabled(LogDetail.ALL, logConfig);
    }

    public FormAuthConfig withLoggingEnabled(LogDetail logDetail, LogConfig logConfig) {
        AssertParameter.notNull(logDetail, LogDetail.class);
        AssertParameter.notNull(logConfig, LogConfig.class);
        return new FormAuthConfig(this.formAction, this.userInputTagName, this.passwordInputTagName, logDetail, logConfig, this.additionalInputFieldNames);
    }

    public static FormAuthConfig formAuthConfig() {
        return new FormAuthConfig(null, null, null);
    }

    public FormAuthConfig and() {
        return this;
    }

    public String getFormAction() {
        return this.formAction;
    }

    public String getUserInputTagName() {
        return this.userInputTagName;
    }

    public String getPasswordInputTagName() {
        return this.passwordInputTagName;
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

    public List<String> getAdditionalInputFieldNames() {
        return this.additionalInputFieldNames;
    }

    public boolean hasAdditionalInputFieldNames() {
        return !this.additionalInputFieldNames.isEmpty();
    }

    public boolean hasUserInputTagName() {
        return StringUtils.isNotBlank(this.userInputTagName);
    }

    public boolean hasPasswordInputTagName() {
        return StringUtils.isNotBlank(this.passwordInputTagName);
    }

    public boolean hasFormAction() {
        return StringUtils.isNotBlank(this.formAction);
    }

    public boolean requiresParsingOfLoginPage() {
        return !this.hasFormAction() || !this.hasUserInputTagName() || !this.hasPasswordInputTagName() || this.hasAdditionalInputFieldNames();
    }
}

