/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.path.json.mapping;

import io.restassured.common.mapper.ObjectDeserializationContext;

@FunctionalInterface
public interface JsonPathObjectDeserializer {
    public <T> T deserialize(ObjectDeserializationContext var1);
}

