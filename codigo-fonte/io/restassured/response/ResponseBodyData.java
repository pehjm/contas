/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.response;

import java.io.InputStream;

public interface ResponseBodyData {
    public String asString();

    public String asPrettyString();

    public byte[] asByteArray();

    public InputStream asInputStream();
}

