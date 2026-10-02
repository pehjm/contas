/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.common.assertion;

public interface PathFragmentEscaper {
    public boolean shouldEscape(String var1);

    public String escape(String var1);
}

