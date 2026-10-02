/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.json.mapping;

import io.restassured.common.mapper.DataToDeserialize;
import io.restassured.common.mapper.ObjectDeserializationContext;
import io.restassured.common.mapper.resolver.ObjectMapperResolver;
import io.restassured.internal.common.ObjectDeserializationContextImpl;
import io.restassured.internal.path.json.mapping.JsonPathGsonObjectDeserializer;
import io.restassured.internal.path.json.mapping.JsonPathJackson1ObjectDeserializer;
import io.restassured.internal.path.json.mapping.JsonPathJackson2ObjectDeserializer;
import io.restassured.internal.path.json.mapping.JsonPathJohnzonObjectDeserializer;
import io.restassured.internal.path.json.mapping.JsonPathJsonbObjectDeserializer;
import io.restassured.path.json.config.JsonParserType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson1ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.JohnzonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.Validate;

public class JsonObjectDeserializer {
    public static <T> T deserialize(final String json, Class<T> cls, final JsonPathConfig jsonPathConfig) {
        Validate.notNull(jsonPathConfig, "JsonPath configuration wasn't specified, cannot deserialize.", new Object[0]);
        JsonParserType mapperType = jsonPathConfig.defaultParserType();
        DataToDeserialize dataToDeserialize = new DataToDeserialize(){

            @Override
            public String asString() {
                return json;
            }

            @Override
            public byte[] asByteArray() {
                try {
                    return json.getBytes(jsonPathConfig.charset());
                }
                catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public InputStream asInputStream() {
                return new ByteArrayInputStream(this.asByteArray());
            }
        };
        ObjectDeserializationContextImpl deserializationCtx = new ObjectDeserializationContextImpl(dataToDeserialize, cls, jsonPathConfig.charset());
        if (jsonPathConfig.hasDefaultDeserializer()) {
            return jsonPathConfig.defaultDeserializer().deserialize(deserializationCtx);
        }
        if (mapperType != null || jsonPathConfig.hasDefaultParserType()) {
            JsonParserType mapperTypeToUse = mapperType == null ? jsonPathConfig.defaultParserType() : mapperType;
            return JsonObjectDeserializer.deserializeWithObjectMapper(deserializationCtx, mapperTypeToUse, jsonPathConfig);
        }
        if (ObjectMapperResolver.isJackson2InClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithJackson2(deserializationCtx, jsonPathConfig.jackson2ObjectMapperFactory());
        }
        if (ObjectMapperResolver.isJackson1InClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithJackson1(deserializationCtx, jsonPathConfig.jackson1ObjectMapperFactory());
        }
        if (ObjectMapperResolver.isGsonInClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithGson(deserializationCtx, jsonPathConfig.gsonObjectMapperFactory());
        }
        if (ObjectMapperResolver.isJohnzonInClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithJohnzon(deserializationCtx, jsonPathConfig.johnzonObjectMapperFactory());
        }
        if (ObjectMapperResolver.isYassonInClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithJsonb(deserializationCtx, jsonPathConfig.jsonbObjectMapperFactory());
        }
        throw new IllegalStateException("Cannot deserialize object because no JSON deserializer found in classpath. Please put Jackson (Databind), Gson, Jackson, or Yasson in the classpath.");
    }

    private static <T> T deserializeWithObjectMapper(ObjectDeserializationContext ctx, JsonParserType mapperType, JsonPathConfig config) {
        if (mapperType == JsonParserType.JACKSON_2 && ObjectMapperResolver.isJackson2InClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithJackson2(ctx, config.jackson2ObjectMapperFactory());
        }
        if (mapperType == JsonParserType.JACKSON_1 && ObjectMapperResolver.isJackson1InClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithJackson1(ctx, config.jackson1ObjectMapperFactory());
        }
        if (mapperType == JsonParserType.GSON && ObjectMapperResolver.isGsonInClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithGson(ctx, config.gsonObjectMapperFactory());
        }
        if (mapperType == JsonParserType.JOHNZON && ObjectMapperResolver.isJohnzonInClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithJohnzon(ctx, config.johnzonObjectMapperFactory());
        }
        if (mapperType == JsonParserType.JSONB && ObjectMapperResolver.isYassonInClassPath()) {
            return (T)JsonObjectDeserializer.deserializeWithJsonb(ctx, config.jsonbObjectMapperFactory());
        }
        String lowerCase = mapperType.toString().toLowerCase();
        throw new IllegalArgumentException(String.format("Cannot deserialize object using %s because %s doesn't exist in the classpath.", new Object[]{mapperType, lowerCase}));
    }

    private static Object deserializeWithGson(ObjectDeserializationContext ctx, GsonObjectMapperFactory factory) {
        return new JsonPathGsonObjectDeserializer(factory).deserialize(ctx);
    }

    static Object deserializeWithJackson1(ObjectDeserializationContext ctx, Jackson1ObjectMapperFactory factory) {
        return new JsonPathJackson1ObjectDeserializer(factory).deserialize(ctx);
    }

    static Object deserializeWithJackson2(ObjectDeserializationContext ctx, Jackson2ObjectMapperFactory factory) {
        return new JsonPathJackson2ObjectDeserializer(factory).deserialize(ctx);
    }

    static Object deserializeWithJohnzon(ObjectDeserializationContext ctx, JohnzonObjectMapperFactory factory) {
        return new JsonPathJohnzonObjectDeserializer(factory).deserialize(ctx);
    }

    static Object deserializeWithJsonb(ObjectDeserializationContext ctx, JsonbObjectMapperFactory factory) {
        return new JsonPathJsonbObjectDeserializer(factory).deserialize(ctx);
    }
}

