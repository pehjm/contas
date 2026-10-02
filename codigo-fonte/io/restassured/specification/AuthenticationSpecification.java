/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.authentication.CertificateAuthSettings;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.authentication.OAuthSignature;
import io.restassured.specification.PreemptiveAuthSpec;
import io.restassured.specification.RequestSpecification;

public interface AuthenticationSpecification {
    public RequestSpecification basic(String var1, String var2);

    public RequestSpecification ntlm(String var1, String var2, String var3, String var4);

    public RequestSpecification digest(String var1, String var2);

    public RequestSpecification form(String var1, String var2);

    public RequestSpecification form(String var1, String var2, FormAuthConfig var3);

    public RequestSpecification certificate(String var1, String var2);

    public RequestSpecification certificate(String var1, String var2, CertificateAuthSettings var3);

    public RequestSpecification oauth2(String var1);

    public RequestSpecification oauth2(String var1, OAuthSignature var2);

    public RequestSpecification oauth(String var1, String var2, String var3, String var4);

    public RequestSpecification oauth(String var1, String var2, String var3, String var4, OAuthSignature var5);

    public PreemptiveAuthSpec preemptive();

    public RequestSpecification none();
}

