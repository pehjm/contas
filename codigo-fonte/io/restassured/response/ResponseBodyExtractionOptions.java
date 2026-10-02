/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.response;

import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.ResponseBodyData;
import java.lang.reflect.Type;

public interface ResponseBodyExtractionOptions
extends ResponseBodyData {
    public <T> T as(Class<T> var1);

    public <T> T as(Class<T> var1, ObjectMapperType var2);

    public <T> T as(Class<T> var1, ObjectMapper var2);

    public <T> T as(TypeRef<T> var1);

    public <T> T as(Type var1);

    public <T> T as(Type var1, ObjectMapperType var2);

    public <T> T as(Type var1, ObjectMapper var2);

    public JsonPath jsonPath();

    public JsonPath jsonPath(JsonPathConfig var1);

    public XmlPath xmlPath();

    public XmlPath xmlPath(XmlPathConfig var1);

    public XmlPath xmlPath(XmlPath.CompatibilityMode var1);

    public XmlPath htmlPath();

    public <T> T path(String var1, String ... var2);
}

