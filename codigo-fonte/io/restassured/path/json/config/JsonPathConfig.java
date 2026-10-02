/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.path.json.config;

import io.restassured.path.json.config.JsonParserType;
import io.restassured.path.json.mapper.factory.DefaultGsonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.DefaultJackson1ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.DefaultJackson2ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.DefaultJohnzonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.DefaultYassonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson1ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.JohnzonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import io.restassured.path.json.mapping.JsonPathObjectDeserializer;
import java.nio.charset.Charset;
import org.apache.commons.lang3.StringUtils;

public class JsonPathConfig {
    private final NumberReturnType numberReturnType;
    private final JsonPathObjectDeserializer defaultDeserializer;
    private final JsonParserType defaultParserType;
    private final GsonObjectMapperFactory gsonObjectMapperFactory;
    private final Jackson1ObjectMapperFactory jackson1ObjectMapperFactory;
    private final Jackson2ObjectMapperFactory jackson2ObjectMapperFactory;
    private final JohnzonObjectMapperFactory johnzonObjectMapperFactory;
    private final JsonbObjectMapperFactory jsonbObjectMapperFactory;
    private final String charset;

    public JsonPathConfig(JsonPathConfig config) {
        this(config.numberReturnType(), config.defaultParserType(), config.gsonObjectMapperFactory(), config.jackson1ObjectMapperFactory(), config.jackson2ObjectMapperFactory(), config.johnzonObjectMapperFactory(), config.jsonbObjectMapperFactory(), config.defaultDeserializer(), config.charset());
    }

    public JsonPathConfig() {
        this(NumberReturnType.FLOAT_AND_DOUBLE, null, new DefaultGsonObjectMapperFactory(), new DefaultJackson1ObjectMapperFactory(), new DefaultJackson2ObjectMapperFactory(), new DefaultJohnzonObjectMapperFactory(), new DefaultYassonObjectMapperFactory(), null, JsonPathConfig.defaultCharset());
    }

    public JsonPathConfig(NumberReturnType numberReturnType) {
        this(numberReturnType, null, new DefaultGsonObjectMapperFactory(), new DefaultJackson1ObjectMapperFactory(), new DefaultJackson2ObjectMapperFactory(), new DefaultJohnzonObjectMapperFactory(), new DefaultYassonObjectMapperFactory(), null, JsonPathConfig.defaultCharset());
    }

    public JsonPathConfig(String defaultCharset) {
        this(NumberReturnType.FLOAT_AND_DOUBLE, null, new DefaultGsonObjectMapperFactory(), new DefaultJackson1ObjectMapperFactory(), new DefaultJackson2ObjectMapperFactory(), new DefaultJohnzonObjectMapperFactory(), new DefaultYassonObjectMapperFactory(), null, defaultCharset);
    }

    private JsonPathConfig(NumberReturnType numberReturnType, JsonParserType parserType, GsonObjectMapperFactory gsonObjectMapperFactory, Jackson1ObjectMapperFactory jackson1ObjectMapperFactory, Jackson2ObjectMapperFactory jackson2ObjectMapperFactory, JohnzonObjectMapperFactory johnzonObjectMapperFactory, JsonbObjectMapperFactory jsonbObjectMapperFactory, JsonPathObjectDeserializer defaultDeserializer, String charset) {
        if (numberReturnType == null) {
            throw new IllegalArgumentException("numberReturnType cannot be null");
        }
        if ((charset = StringUtils.trimToNull(charset)) == null) {
            throw new IllegalArgumentException("Charset cannot be empty");
        }
        this.charset = charset;
        this.numberReturnType = numberReturnType;
        this.defaultDeserializer = defaultDeserializer;
        this.defaultParserType = parserType;
        this.gsonObjectMapperFactory = gsonObjectMapperFactory;
        this.jackson1ObjectMapperFactory = jackson1ObjectMapperFactory;
        this.jackson2ObjectMapperFactory = jackson2ObjectMapperFactory;
        this.johnzonObjectMapperFactory = johnzonObjectMapperFactory;
        this.jsonbObjectMapperFactory = jsonbObjectMapperFactory;
    }

    private static String defaultCharset() {
        return Charset.defaultCharset().name();
    }

    public String charset() {
        return this.charset;
    }

    public JsonPathConfig charset(String charset) {
        return new JsonPathConfig(this.numberReturnType, this.defaultParserType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.defaultDeserializer, charset);
    }

    public NumberReturnType numberReturnType() {
        return this.numberReturnType;
    }

    public JsonPathConfig numberReturnType(NumberReturnType numberReturnType) {
        return new JsonPathConfig(numberReturnType, this.defaultParserType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.defaultDeserializer, this.charset);
    }

    public boolean shouldRepresentJsonNumbersAsBigDecimal() {
        return this.numberReturnType() == NumberReturnType.BIG_DECIMAL;
    }

    public JsonParserType defaultParserType() {
        return this.defaultParserType;
    }

    public boolean hasDefaultParserType() {
        return this.defaultParserType != null;
    }

    public boolean hasCustomGsonObjectMapperFactory() {
        return this.gsonObjectMapperFactory() != null && this.gsonObjectMapperFactory().getClass() != DefaultGsonObjectMapperFactory.class;
    }

    public boolean hasCustomJackson10ObjectMapperFactory() {
        return this.jackson1ObjectMapperFactory() != null && this.jackson1ObjectMapperFactory().getClass() != DefaultJackson1ObjectMapperFactory.class;
    }

    public boolean hasCustomJackson20ObjectMapperFactory() {
        return this.jackson2ObjectMapperFactory() != null && this.jackson2ObjectMapperFactory().getClass() != DefaultJackson2ObjectMapperFactory.class;
    }

    public boolean hasCustomJohnzonObjectMapperFactory() {
        return this.johnzonObjectMapperFactory() != null && this.johnzonObjectMapperFactory().getClass() != DefaultJohnzonObjectMapperFactory.class;
    }

    public boolean hasCustomJsonbObjectMapperFactory() {
        return this.jsonbObjectMapperFactory() != null && this.jsonbObjectMapperFactory().getClass() != DefaultYassonObjectMapperFactory.class;
    }

    public JsonPathConfig defaultParserType(JsonParserType defaultParserType) {
        return new JsonPathConfig(this.numberReturnType, defaultParserType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.defaultDeserializer, this.charset);
    }

    public JsonPathObjectDeserializer defaultDeserializer() {
        return this.defaultDeserializer;
    }

    public boolean hasDefaultDeserializer() {
        return this.defaultDeserializer != null;
    }

    public JsonPathConfig defaultObjectDeserializer(JsonPathObjectDeserializer defaultObjectDeserializer) {
        return new JsonPathConfig(this.numberReturnType, null, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, defaultObjectDeserializer, this.charset);
    }

    public GsonObjectMapperFactory gsonObjectMapperFactory() {
        return this.gsonObjectMapperFactory;
    }

    public JsonPathConfig gsonObjectMapperFactory(GsonObjectMapperFactory gsonObjectMapperFactory) {
        return new JsonPathConfig(this.numberReturnType, this.defaultParserType, gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.defaultDeserializer, this.charset);
    }

    public Jackson1ObjectMapperFactory jackson1ObjectMapperFactory() {
        return this.jackson1ObjectMapperFactory;
    }

    public JsonPathConfig jackson1ObjectMapperFactory(Jackson1ObjectMapperFactory jackson1ObjectMapperFactory) {
        return new JsonPathConfig(this.numberReturnType, this.defaultParserType, this.gsonObjectMapperFactory, jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.defaultDeserializer, this.charset);
    }

    public Jackson2ObjectMapperFactory jackson2ObjectMapperFactory() {
        return this.jackson2ObjectMapperFactory;
    }

    public JohnzonObjectMapperFactory johnzonObjectMapperFactory() {
        return this.johnzonObjectMapperFactory;
    }

    public JsonPathConfig jackson2ObjectMapperFactory(Jackson2ObjectMapperFactory jackson2ObjectMapperFactory) {
        return new JsonPathConfig(this.numberReturnType, this.defaultParserType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, jackson2ObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.defaultDeserializer, this.charset);
    }

    public JsonbObjectMapperFactory jsonbObjectMapperFactory() {
        return this.jsonbObjectMapperFactory;
    }

    public JsonPathConfig jsonbObjectMapperFactory(JsonbObjectMapperFactory jsonbObjectMapperFactory) {
        return new JsonPathConfig(this.numberReturnType, this.defaultParserType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.johnzonObjectMapperFactory, jsonbObjectMapperFactory, this.defaultDeserializer, this.charset);
    }

    public static JsonPathConfig jsonPathConfig() {
        return new JsonPathConfig();
    }

    public JsonPathConfig with() {
        return this;
    }

    public JsonPathConfig and() {
        return this;
    }

    public static enum NumberReturnType {
        FLOAT_AND_DOUBLE,
        BIG_DECIMAL,
        DOUBLE,
        BIG_INTEGER;


        public final boolean isFloatOrDouble() {
            return this.equals((Object)FLOAT_AND_DOUBLE) || this.equals((Object)DOUBLE);
        }
    }
}

