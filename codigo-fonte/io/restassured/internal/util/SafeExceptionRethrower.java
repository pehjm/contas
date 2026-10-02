/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.util;

public class SafeExceptionRethrower {
    public static <T> T safeRethrow(Throwable t) {
        SafeExceptionRethrower.safeRethrow0(t);
        return null;
    }

    private static <T extends Throwable> void safeRethrow0(Throwable t) throws T {
        throw t;
    }
}

