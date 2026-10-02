/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.json.bind.Jsonb
 */
package io.restassured.internal.path.json.mapping;

import groovy.lang.Closure;
import io.restassured.common.mapper.ObjectDeserializationContext;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import io.restassured.path.json.mapping.JsonPathObjectDeserializer;
import java.io.Reader;
import java.lang.reflect.Type;
import javax.json.bind.Jsonb;
import org.codehaus.groovy.runtime.IOGroovyMethods;

public class JsonPathJsonbObjectDeserializer
implements JsonPathObjectDeserializer {
    private final JsonbObjectMapperFactory factory;

    public JsonPathJsonbObjectDeserializer(JsonbObjectMapperFactory factory) {
        AssertParameter.notNull(factory, "JsonbObjectMapperFactory");
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
        try (final Jsonb mapper = (Jsonb)this.factory.create(cls, context.getCharset());){
            Object t = IOGroovyMethods.withReader(context.getDataToDeserialize().asInputStream(), new Closure<T>(this, this){

                public Object doCall(Object reader) {
                    return mapper.fromJson((Reader)reader, cls);
                }
            });
            return t;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

