/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

public enum Status {
    SUCCESS(100, 399),
    FAILURE(400, 999);

    private final int min;
    private final int max;

    public String toString() {
        return super.toString().toLowerCase();
    }

    public boolean matches(int code) {
        return this.min <= code && code <= this.max;
    }

    public static Status find(int code) {
        for (Status s : Status.values()) {
            if (!s.matches(code)) continue;
            return s;
        }
        throw new IllegalArgumentException("Unknown status: " + code);
    }

    private Status(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

