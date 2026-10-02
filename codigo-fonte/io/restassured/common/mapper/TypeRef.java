/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.common.mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeRef<T>
implements Comparable<TypeRef<T>> {
    protected final Type type;

    protected TypeRef() {
        Type superClass = this.getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new IllegalArgumentException("No type info in TypeRef");
        }
        this.type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this.type;
    }

    public Class<T> getTypeAsClass() {
        return (Class)((ParameterizedType)this.type).getRawType();
    }

    @Override
    public int compareTo(TypeRef<T> ignored) {
        return 0;
    }
}

