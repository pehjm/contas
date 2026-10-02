/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.mapper;

import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;

public interface ObjectMapper {
    public Object deserialize(ObjectMapperDeserializationContext var1);

    public Object serialize(ObjectMapperSerializationContext var1);
}

