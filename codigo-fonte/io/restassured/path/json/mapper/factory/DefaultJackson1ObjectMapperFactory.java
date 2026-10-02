/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.codehaus.jackson.map.ObjectMapper
 */
package io.restassured.path.json.mapper.factory;

import io.restassured.path.json.mapper.factory.Jackson1ObjectMapperFactory;
import java.lang.reflect.Type;
import org.codehaus.jackson.map.ObjectMapper;

public class DefaultJackson1ObjectMapperFactory
implements Jackson1ObjectMapperFactory {
    @Override
    public ObjectMapper create(Type cls, String charset) {
        return new ObjectMapper();
    }
}

