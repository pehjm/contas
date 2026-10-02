/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.common.mapper.resolver;

import io.restassured.internal.common.classpath.ClassPathResolver;

public class ObjectMapperResolver {
    private static final boolean isJackson1Present = ClassPathResolver.existInCP("org.codehaus.jackson.map.ObjectMapper") && ClassPathResolver.existInCP("org.codehaus.jackson.JsonGenerator");
    private static final boolean isJackson2Present = ClassPathResolver.existInCP("com.fasterxml.jackson.databind.ObjectMapper") && ClassPathResolver.existInCP("com.fasterxml.jackson.core.JsonGenerator");
    private static final boolean isJaxbPresent = ClassPathResolver.existInCP("javax.xml.bind.Binder");
    private static final boolean isJakartaEEPresent = ClassPathResolver.existInCP("jakarta.xml.bind.Binder");
    private static final boolean isGsonPresent = ClassPathResolver.existInCP("com.google.gson.Gson");
    private static final boolean isJohnzonPresent = ClassPathResolver.existInCP("org.apache.johnzon.mapper.Mapper");
    private static final boolean isYassonPresent = ClassPathResolver.existInCP("org.eclipse.yasson.JsonBindingProvider");

    public static boolean isJackson1InClassPath() {
        return isJackson1Present;
    }

    public static boolean isJackson2InClassPath() {
        return isJackson2Present;
    }

    public static boolean isJAXBInClassPath() {
        return isJaxbPresent;
    }

    public static boolean isJakartaEEInClassPath() {
        return isJakartaEEPresent;
    }

    public static boolean isGsonInClassPath() {
        return isGsonPresent;
    }

    public static boolean isJohnzonInClassPath() {
        return isJohnzonPresent;
    }

    public static boolean isYassonInClassPath() {
        return isYassonPresent;
    }
}

