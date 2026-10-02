/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.common.mapper.factory;

import java.lang.reflect.Type;

public interface ObjectMapperFactory<T> {
    public T create(Type var1, String var2);
}

