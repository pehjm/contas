/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.databind.ObjectMapper
 */
package io.restassured.path.json.mapper.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import java.lang.reflect.Type;

public class DefaultJackson2ObjectMapperFactory
implements Jackson2ObjectMapperFactory {
    @Override
    public ObjectMapper create(Type cls, String charset) {
        return new ObjectMapper().findAndRegisterModules();
    }
}

