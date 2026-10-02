/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.codehaus.jackson.map.ObjectMapper
 *  org.codehaus.jackson.type.JavaType
 */
package io.restassured.internal.path.json.mapping;

import io.restassured.common.mapper.ObjectDeserializationContext;
import io.restassured.path.json.mapper.factory.Jackson1ObjectMapperFactory;
import io.restassured.path.json.mapping.JsonPathObjectDeserializer;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JsonPathJackson1ObjectDeserializer
implements JsonPathObjectDeserializer {
    private final Jackson1ObjectMapperFactory factory;

    public JsonPathJackson1ObjectDeserializer(Jackson1ObjectMapperFactory factory) {
        this.factory = factory;
    }

    private ObjectMapper createJacksonObjectMapper(Type cls, String charset) {
        return (ObjectMapper)this.factory.create(cls, charset);
    }

    @Override
    public <T> T deserialize(ObjectDeserializationContext ctx) {
        String object = ctx.getDataToDeserialize().asString();
        Type cls = ctx.getType();
        ObjectMapper mapper = this.createJacksonObjectMapper(cls, ctx.getCharset());
        JavaType javaType = mapper.constructType(cls);
        try {
            return (T)mapper.readValue(object, javaType);
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

