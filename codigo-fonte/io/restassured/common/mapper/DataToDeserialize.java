/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.common.mapper;

import java.io.InputStream;

public interface DataToDeserialize {
    public String asString();

    public byte[] asByteArray();

    public InputStream asInputStream();
}

