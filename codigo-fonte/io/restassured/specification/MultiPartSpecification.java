/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import java.util.Map;

public interface MultiPartSpecification {
    public Object getContent();

    public String getControlName();

    public String getMimeType();

    public Map<String, String> getHeaders();

    public String getCharset();

    public String getFileName();

    public boolean hasFileName();
}

