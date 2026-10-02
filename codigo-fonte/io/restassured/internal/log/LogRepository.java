/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.log;

import java.io.ByteArrayOutputStream;

public class LogRepository {
    private static final String EMPTY = "";
    private ByteArrayOutputStream requestLog;
    private ByteArrayOutputStream responseLog;

    public void registerRequestLog(ByteArrayOutputStream baos) {
        this.requestLog = baos;
    }

    public void registerResponseLog(ByteArrayOutputStream baos) {
        this.responseLog = baos;
    }

    public String getRequestLog() {
        if (this.requestLog == null) {
            return EMPTY;
        }
        return this.requestLog.toString();
    }

    public String getResponseLog() {
        if (this.responseLog == null) {
            return EMPTY;
        }
        return this.responseLog.toString();
    }
}

