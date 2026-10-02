/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.serialization;

import groovy.lang.GString;
import java.util.Locale;
import java.util.UUID;

public class SerializationSupport {
    public static boolean isSerializableCandidate(Object object) {
        if (object == null) {
            return false;
        }
        Class<?> clazz = object.getClass();
        return !Number.class.isAssignableFrom(clazz) && !String.class.isAssignableFrom(clazz) && !GString.class.isAssignableFrom(clazz) && !Boolean.class.isAssignableFrom(clazz) && !Character.class.isAssignableFrom(clazz) && !clazz.isEnum() && !Locale.class.isAssignableFrom(clazz) && !Class.class.isAssignableFrom(clazz) && !UUID.class.isAssignableFrom(clazz);
    }
}

