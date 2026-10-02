/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.mapper;

public interface ObjectMapperSerializationContext {
    public Object getObjectToSerialize();

    public <T> T getObjectToSerializeAs(Class<T> var1);

    public String getContentType();

    public String getCharset();
}

