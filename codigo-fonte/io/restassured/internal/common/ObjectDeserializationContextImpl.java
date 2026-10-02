/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.common;

import io.restassured.common.mapper.DataToDeserialize;
import io.restassured.common.mapper.ObjectDeserializationContext;
import java.lang.reflect.Type;

public class ObjectDeserializationContextImpl
implements ObjectDeserializationContext {
    private DataToDeserialize dataToDeserialize;
    private Type type;
    private String charset;

    public ObjectDeserializationContextImpl() {
    }

    public ObjectDeserializationContextImpl(DataToDeserialize dataToDeserialize, Type type, String charset) {
        this.dataToDeserialize = dataToDeserialize;
        this.type = type;
        this.charset = charset;
    }

    @Override
    public DataToDeserialize getDataToDeserialize() {
        return this.dataToDeserialize;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getCharset() {
        return this.charset;
    }

    public void setDataToDeserialize(DataToDeserialize dataToDeserialize) {
        this.dataToDeserialize = dataToDeserialize;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}

