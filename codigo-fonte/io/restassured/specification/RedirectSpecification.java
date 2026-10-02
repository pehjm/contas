/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.specification.RequestSpecification;

public interface RedirectSpecification {
    public RequestSpecification max(int var1);

    public RequestSpecification follow(boolean var1);

    public RequestSpecification allowCircular(boolean var1);

    public RequestSpecification rejectRelative(boolean var1);
}

