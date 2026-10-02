/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.johnzon.mapper.Mapper
 *  org.apache.johnzon.mapper.MapperBuilder
 */
package io.restassured.path.json.mapper.factory;

import io.restassured.path.json.mapper.factory.JohnzonObjectMapperFactory;
import java.lang.reflect.Type;
import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;

public class DefaultJohnzonObjectMapperFactory
implements JohnzonObjectMapperFactory {
    @Override
    public Mapper create(Type cls, String charset) {
        return new MapperBuilder().setAccessModeName("field").setSupportHiddenAccess(true).build();
    }
}

