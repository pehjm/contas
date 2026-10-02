/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.authentication;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;

public class PreemptiveAuthProvider {
    public AuthenticationScheme basic(String userName, String password) {
        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName(userName);
        preemptiveBasicAuthScheme.setPassword(password);
        return preemptiveBasicAuthScheme;
    }
}

