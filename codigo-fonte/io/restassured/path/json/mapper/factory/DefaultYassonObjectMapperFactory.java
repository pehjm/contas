/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.json.bind.Jsonb
 *  org.eclipse.yasson.JsonBindingProvider
 */
package io.restassured.path.json.mapper.factory;

import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import java.lang.reflect.Type;
import javax.json.bind.Jsonb;
import org.eclipse.yasson.JsonBindingProvider;

public class DefaultYassonObjectMapperFactory
implements JsonbObjectMapperFactory {
    private static Object cachedJsonb = null;

    @Override
    public Jsonb create(Type cls, String charset) {
        if (cachedJsonb == null) {
            cachedJsonb = new JsonBindingProvider().create().build();
        }
        return (Jsonb)cachedJsonb;
    }
}

