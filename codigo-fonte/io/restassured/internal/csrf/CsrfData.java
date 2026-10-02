/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.csrf;

import io.restassured.config.CsrfConfig;

public class CsrfData {
    public final String inputFieldOrHeaderName;
    public final String token;
    public final CsrfConfig.CsrfPrioritization csrfPrioritization;

    public CsrfData(String inputFieldOrHeaderName, String token, CsrfConfig.CsrfPrioritization csrfPrioritization) {
        this.inputFieldOrHeaderName = inputFieldOrHeaderName;
        this.token = token;
        this.csrfPrioritization = csrfPrioritization;
    }

    public boolean shouldSendTokenAs(CsrfConfig.CsrfPrioritization csrfPrioritization) {
        return this.csrfPrioritization == csrfPrioritization;
    }
}

