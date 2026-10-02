/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.authentication;

import io.restassured.internal.http.HTTPBuilder;

public interface AuthenticationScheme {
    public void authenticate(HTTPBuilder var1);
}

