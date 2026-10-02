/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.internal.common.assertion.AssertParameter;

public class MatcherConfig
implements Config {
    private final ErrorDescriptionType errorDescriptionType;
    private final boolean isUserDefined;

    public MatcherConfig() {
        this(ErrorDescriptionType.REST_ASSURED, false);
    }

    public MatcherConfig(ErrorDescriptionType errorDescriptionType) {
        this(errorDescriptionType, true);
    }

    private MatcherConfig(ErrorDescriptionType errorDescriptionType, boolean isUserDefined) {
        AssertParameter.notNull(errorDescriptionType, ErrorDescriptionType.class);
        this.errorDescriptionType = errorDescriptionType;
        this.isUserDefined = isUserDefined;
    }

    public MatcherConfig errorDescriptionType(ErrorDescriptionType errorDescriptionType) {
        return new MatcherConfig(errorDescriptionType, true);
    }

    public ErrorDescriptionType errorDescriptionType() {
        return this.errorDescriptionType;
    }

    public boolean hasErrorDescriptionType(ErrorDescriptionType errorDescriptionType) {
        return this.errorDescriptionType == errorDescriptionType;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserDefined;
    }

    public static MatcherConfig matcherConfig() {
        return new MatcherConfig();
    }

    public static enum ErrorDescriptionType {
        REST_ASSURED,
        HAMCREST;

    }
}

