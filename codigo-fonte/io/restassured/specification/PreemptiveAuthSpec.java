/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.specification.RequestSpecification;

public interface PreemptiveAuthSpec {
    public RequestSpecification basic(String var1, String var2);

    public RequestSpecification oauth2(String var1);
}

