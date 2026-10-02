/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.common.classpath;

public class ClassPathResolver {
    public static boolean existInCP(String className) {
        return ClassPathResolver.existsInCP(className, ClassPathResolver.class.getClassLoader()) || ClassPathResolver.existsInCP(className, Thread.currentThread().getContextClassLoader());
    }

    private static boolean existsInCP(String className, ClassLoader classLoader) {
        try {
            Class.forName(className, false, classLoader);
            return true;
        }
        catch (Throwable e) {
            return false;
        }
    }
}

