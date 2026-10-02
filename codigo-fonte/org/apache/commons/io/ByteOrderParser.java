/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.io;

import java.nio.ByteOrder;

public final class ByteOrderParser {
    private ByteOrderParser() {
    }

    public static ByteOrder parseByteOrder(String value) {
        if (ByteOrder.BIG_ENDIAN.toString().equals(value)) {
            return ByteOrder.BIG_ENDIAN;
        }
        if (ByteOrder.LITTLE_ENDIAN.toString().equals(value)) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        throw new IllegalArgumentException("Unsupported byte order setting: " + value + ", expected one of " + (Object)((Object)ByteOrder.LITTLE_ENDIAN) + ", " + (Object)((Object)ByteOrder.BIG_ENDIAN));
    }
}

