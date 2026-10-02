/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package io.restassured.internal.path.json.mapping;

import com.google.gson.Gson;
import io.restassured.common.mapper.ObjectDeserializationContext;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import io.restassured.path.json.mapping.JsonPathObjectDeserializer;

public class JsonPathGsonObjectDeserializer
implements JsonPathObjectDeserializer {
    private final GsonObjectMapperFactory factory;

    public JsonPathGsonObjectDeserializer(GsonObjectMapperFactory factory) {
        AssertParameter.notNull(factory, "GsonObjectMapperFactory");
        this.factory = factory;
    }

    @Override
    public <T> T deserialize(ObjectDeserializationContext ctx) {
        return (T)((Gson)this.factory.create(ctx.getType(), ctx.getCharset())).fromJson(ctx.getDataToDeserialize().asString(), ctx.getType());
    }
}

