/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.path.xml.mapping;

import io.restassured.common.mapper.ObjectDeserializationContext;

public interface XmlPathObjectDeserializer {
    public <T> T deserialize(ObjectDeserializationContext var1);
}

