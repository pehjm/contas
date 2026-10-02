/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.internal.common.assertion.AssertParameter;

public class ParamConfig
implements Config {
    private final boolean userConfigured;
    private final UpdateStrategy queryParamsUpdateStrategy;
    private final UpdateStrategy pathParamsUpdateStrategy;
    private final UpdateStrategy formParamsUpdateStrategy;
    private final UpdateStrategy requestParameterUpdateStrategy;

    public ParamConfig() {
        this(UpdateStrategy.MERGE, UpdateStrategy.REPLACE, UpdateStrategy.MERGE, UpdateStrategy.MERGE, false);
    }

    public ParamConfig(UpdateStrategy queryParamsUpdateStrategy, UpdateStrategy pathParamsUpdateStrategy, UpdateStrategy formParamsUpdateStrategy, UpdateStrategy requestParameterUpdateStrategy) {
        this(queryParamsUpdateStrategy, pathParamsUpdateStrategy, formParamsUpdateStrategy, requestParameterUpdateStrategy, true);
    }

    private ParamConfig(UpdateStrategy queryParamsUpdateStrategy, UpdateStrategy pathParamsUpdateStrategy, UpdateStrategy formParamsUpdateStrategy, UpdateStrategy requestParameterUpdateStrategy, boolean userConfigured) {
        AssertParameter.notNull(queryParamsUpdateStrategy, "Query param update strategy");
        AssertParameter.notNull(pathParamsUpdateStrategy, "Path param update strategy");
        AssertParameter.notNull(requestParameterUpdateStrategy, "Request param update strategy");
        AssertParameter.notNull(formParamsUpdateStrategy, "Form param update strategy");
        this.queryParamsUpdateStrategy = queryParamsUpdateStrategy;
        this.pathParamsUpdateStrategy = pathParamsUpdateStrategy;
        this.formParamsUpdateStrategy = formParamsUpdateStrategy;
        this.requestParameterUpdateStrategy = requestParameterUpdateStrategy;
        this.userConfigured = userConfigured;
    }

    public ParamConfig mergeAllParameters() {
        return new ParamConfig(UpdateStrategy.MERGE, UpdateStrategy.REPLACE, UpdateStrategy.MERGE, UpdateStrategy.MERGE, true);
    }

    public ParamConfig replaceAllParameters() {
        return new ParamConfig(UpdateStrategy.REPLACE, UpdateStrategy.REPLACE, UpdateStrategy.REPLACE, UpdateStrategy.REPLACE, true);
    }

    public ParamConfig formParamsUpdateStrategy(UpdateStrategy updateStrategy) {
        return new ParamConfig(this.queryParamsUpdateStrategy, this.pathParamsUpdateStrategy, updateStrategy, this.requestParameterUpdateStrategy, true);
    }

    public ParamConfig requestParamsUpdateStrategy(UpdateStrategy updateStrategy) {
        return new ParamConfig(this.queryParamsUpdateStrategy, this.pathParamsUpdateStrategy, this.formParamsUpdateStrategy, updateStrategy, true);
    }

    public ParamConfig queryParamsUpdateStrategy(UpdateStrategy updateStrategy) {
        return new ParamConfig(updateStrategy, this.pathParamsUpdateStrategy, this.formParamsUpdateStrategy, this.requestParameterUpdateStrategy, true);
    }

    public UpdateStrategy formParamsUpdateStrategy() {
        return this.formParamsUpdateStrategy;
    }

    public UpdateStrategy requestParamsUpdateStrategy() {
        return this.requestParameterUpdateStrategy;
    }

    public UpdateStrategy queryParamsUpdateStrategy() {
        return this.queryParamsUpdateStrategy;
    }

    public UpdateStrategy pathParamsUpdateStrategy() {
        return this.pathParamsUpdateStrategy;
    }

    @Override
    public boolean isUserConfigured() {
        return this.userConfigured;
    }

    public static ParamConfig paramConfig() {
        return new ParamConfig();
    }

    public ParamConfig and() {
        return this;
    }

    public ParamConfig with() {
        return this;
    }

    public static enum UpdateStrategy {
        MERGE,
        REPLACE;

    }
}

