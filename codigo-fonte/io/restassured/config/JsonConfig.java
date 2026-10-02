/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.path.json.config.JsonPathConfig;
import org.apache.commons.lang3.Validate;

public class JsonConfig
implements Config {
    private final JsonPathConfig.NumberReturnType numberReturnType;
    private final boolean isUserDefined;

    public JsonConfig() {
        this(JsonPathConfig.NumberReturnType.FLOAT_AND_DOUBLE, false);
    }

    public JsonConfig(JsonPathConfig.NumberReturnType numberReturnType) {
        this(numberReturnType, true);
    }

    public JsonConfig(JsonPathConfig.NumberReturnType numberReturnType, boolean isUserDefined) {
        Validate.notNull(numberReturnType, "numberReturnType cannot be null", new Object[0]);
        this.numberReturnType = numberReturnType;
        this.isUserDefined = isUserDefined;
    }

    public JsonPathConfig.NumberReturnType numberReturnType() {
        return this.numberReturnType;
    }

    public boolean shouldRepresentJsonNumbersAsBigDecimal() {
        return this.numberReturnType() == JsonPathConfig.NumberReturnType.BIG_DECIMAL;
    }

    public JsonConfig numberReturnType(JsonPathConfig.NumberReturnType numberReturnType) {
        return new JsonConfig(numberReturnType, true);
    }

    public JsonConfig with() {
        return this;
    }

    public static JsonConfig jsonConfig() {
        return new JsonConfig();
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserDefined;
    }
}

