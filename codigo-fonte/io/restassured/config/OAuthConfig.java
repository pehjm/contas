/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;

public class OAuthConfig
implements Config {
    private final boolean addEmptyAccessTokenToBaseString;
    private final boolean isUserConfigured;

    public OAuthConfig() {
        this(false, false);
    }

    private OAuthConfig(boolean addEmptyAccessTokenToBaseString, boolean isUserConfigured) {
        this.addEmptyAccessTokenToBaseString = addEmptyAccessTokenToBaseString;
        this.isUserConfigured = isUserConfigured;
    }

    public OAuthConfig addEmptyAccessTokenToBaseString(boolean addEmptyAccessTokenToBaseString) {
        return new OAuthConfig(addEmptyAccessTokenToBaseString, true);
    }

    public boolean shouldAddEmptyAccessOAuthTokenToBaseString() {
        return this.addEmptyAccessTokenToBaseString;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }

    public static OAuthConfig oauthConfig() {
        return new OAuthConfig();
    }

    public OAuthConfig and() {
        return this;
    }

    public OAuthConfig with() {
        return this;
    }
}

