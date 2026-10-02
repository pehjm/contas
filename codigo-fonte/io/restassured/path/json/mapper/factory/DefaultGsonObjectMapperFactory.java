/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package io.restassured.path.json.mapper.factory;

import com.google.gson.Gson;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import java.lang.reflect.Type;

public class DefaultGsonObjectMapperFactory
implements GsonObjectMapperFactory {
    @Override
    public Gson create(Type cls, String charset) {
        return new Gson();
    }
}

