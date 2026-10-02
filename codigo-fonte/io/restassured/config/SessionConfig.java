/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class SessionConfig
implements Config {
    public static final String DEFAULT_SESSION_ID_NAME = "JSESSIONID";
    private final String sessionIdName;
    private final String sessionIdValue;
    private final boolean isUserDefined;

    public SessionConfig() {
        this(DEFAULT_SESSION_ID_NAME, null, false);
    }

    public SessionConfig(String sessionIdValue) {
        this(DEFAULT_SESSION_ID_NAME, sessionIdValue, true);
    }

    public SessionConfig(String sessionIdName, String sessionIdValue) {
        this(sessionIdName, sessionIdValue, true);
    }

    private SessionConfig(String sessionIdName, String sessionIdValue, boolean isUserDefined) {
        Validate.notEmpty(sessionIdName, "Session id name cannot be empty.", new Object[0]);
        this.sessionIdName = sessionIdName;
        this.sessionIdValue = sessionIdValue;
        this.isUserDefined = isUserDefined;
    }

    public boolean isSessionIdValueDefined() {
        return !StringUtils.isBlank(this.sessionIdValue);
    }

    public SessionConfig sessionIdValue(String defaultSessionId) {
        return new SessionConfig(this.sessionIdName, defaultSessionId, true);
    }

    public SessionConfig sessionIdName(String sessionIdName) {
        return new SessionConfig(sessionIdName, this.sessionIdValue, true);
    }

    public String sessionIdName() {
        return this.sessionIdName;
    }

    public String sessionIdValue() {
        return this.sessionIdValue;
    }

    public static SessionConfig sessionConfig() {
        return new SessionConfig();
    }

    public SessionConfig and() {
        return this;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserDefined;
    }
}

