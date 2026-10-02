/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.johnzon.mapper.Mapper
 */
package io.restassured.internal.path.json.mapping;

import groovy.lang.Closure;
import io.restassured.common.mapper.ObjectDeserializationContext;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.path.json.mapper.factory.JohnzonObjectMapperFactory;
import io.restassured.path.json.mapping.JsonPathObjectDeserializer;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;
import org.apache.johnzon.mapper.Mapper;
import org.codehaus.groovy.runtime.IOGroovyMethods;

public class JsonPathJohnzonObjectDeserializer
implements JsonPathObjectDeserializer {
    private final JohnzonObjectMapperFactory factory;

    public JsonPathJohnzonObjectDeserializer(JohnzonObjectMapperFactory factory) {
        AssertParameter.notNull(factory, "JohnzonObjectMapperFactory");
        this.factory = factory;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public <T> T deserialize(ObjectDeserializationContext context) {
        final Type cls = context.getType();
        try (final Mapper mapper = (Mapper)this.factory.create(cls, context.getCharset());){
            Object t = IOGroovyMethods.withReader(context.getDataToDeserialize().asInputStream(), new Closure<T>(this, this){

                public Object doCall(Object reader) {
                    return mapper.readObject((Reader)reader, cls);
                }
            });
            return t;
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

