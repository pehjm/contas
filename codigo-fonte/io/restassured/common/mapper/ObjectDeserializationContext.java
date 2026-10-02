/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.common.mapper;

import io.restassured.common.mapper.DataToDeserialize;
import java.lang.reflect.Type;

public interface ObjectDeserializationContext {
    public DataToDeserialize getDataToDeserialize();

    public Type getType();

    public String getCharset();
}

