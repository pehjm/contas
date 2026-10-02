/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.mapper;

import io.restassured.common.mapper.ObjectDeserializationContext;

public interface ObjectMapperDeserializationContext
extends ObjectDeserializationContext {
    public String getContentType();
}

