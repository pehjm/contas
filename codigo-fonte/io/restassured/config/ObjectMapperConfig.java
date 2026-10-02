/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.common.mapper.resolver.ObjectMapperResolver;
import io.restassured.config.Config;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.mapper.factory.DefaultGsonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.DefaultJackson1ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.DefaultJackson2ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.DefaultJohnzonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.DefaultYassonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson1ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.JohnzonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import io.restassured.path.xml.mapper.factory.DefaultJAXBObjectMapperFactory;
import io.restassured.path.xml.mapper.factory.DefaultJakartaEEObjectMapperFactory;
import io.restassured.path.xml.mapper.factory.JAXBObjectMapperFactory;
import io.restassured.path.xml.mapper.factory.JakartaEEObjectMapperFactory;
import org.apache.commons.lang3.Validate;

public class ObjectMapperConfig
implements Config {
    private final ObjectMapper defaultObjectMapper;
    private final ObjectMapperType defaultObjectMapperType;
    private final GsonObjectMapperFactory gsonObjectMapperFactory;
    private final Jackson1ObjectMapperFactory jackson1ObjectMapperFactory;
    private final Jackson2ObjectMapperFactory jackson2ObjectMapperFactory;
    private final JAXBObjectMapperFactory jaxbObjectMapperFactory;
    private final JohnzonObjectMapperFactory johnzonObjectMapperFactory;
    private final JsonbObjectMapperFactory jsonbObjectMapperFactory;
    private final JakartaEEObjectMapperFactory jakartaEEObjectMapperFactory;
    private final boolean isUserConfigured;

    public ObjectMapperConfig() {
        this.defaultObjectMapper = null;
        this.defaultObjectMapperType = null;
        this.gsonObjectMapperFactory = new DefaultGsonObjectMapperFactory();
        this.jackson1ObjectMapperFactory = new DefaultJackson1ObjectMapperFactory();
        this.jackson2ObjectMapperFactory = new DefaultJackson2ObjectMapperFactory();
        this.jaxbObjectMapperFactory = ObjectMapperConfig.newJaxbObjectMapperFactoryOrNullIfNotInClasspath();
        this.johnzonObjectMapperFactory = new DefaultJohnzonObjectMapperFactory();
        this.jsonbObjectMapperFactory = new DefaultYassonObjectMapperFactory();
        this.jakartaEEObjectMapperFactory = ObjectMapperConfig.newJakartaEEObjectMapperFactoryOrNullIfNotInClasspath();
        this.isUserConfigured = false;
    }

    public ObjectMapperConfig(ObjectMapperType defaultObjectMapperType) {
        this(null, defaultObjectMapperType, new DefaultGsonObjectMapperFactory(), new DefaultJackson1ObjectMapperFactory(), new DefaultJackson2ObjectMapperFactory(), ObjectMapperConfig.newJaxbObjectMapperFactoryOrNullIfNotInClasspath(), new DefaultJohnzonObjectMapperFactory(), new DefaultYassonObjectMapperFactory(), ObjectMapperConfig.newJakartaEEObjectMapperFactoryOrNullIfNotInClasspath(), true);
    }

    public ObjectMapperConfig(ObjectMapper defaultObjectMapper) {
        this(defaultObjectMapper, null, new DefaultGsonObjectMapperFactory(), new DefaultJackson1ObjectMapperFactory(), new DefaultJackson2ObjectMapperFactory(), ObjectMapperConfig.newJaxbObjectMapperFactoryOrNullIfNotInClasspath(), new DefaultJohnzonObjectMapperFactory(), new DefaultYassonObjectMapperFactory(), ObjectMapperConfig.newJakartaEEObjectMapperFactoryOrNullIfNotInClasspath(), true);
    }

    private ObjectMapperConfig(ObjectMapper defaultObjectMapper, ObjectMapperType defaultObjectMapperType, GsonObjectMapperFactory gsonObjectMapperFactory, Jackson1ObjectMapperFactory jackson1ObjectMapperFactory, Jackson2ObjectMapperFactory jackson2ObjectMapperFactory, JAXBObjectMapperFactory jaxbObjectMapperFactory, JohnzonObjectMapperFactory johnzonObjectMapperFactory, JsonbObjectMapperFactory jsonbObjectMapperFactory, JakartaEEObjectMapperFactory jakartaEEObjectMapperFactory, boolean isUserConfigured) {
        Validate.notNull(gsonObjectMapperFactory, GsonObjectMapperFactory.class.getSimpleName() + " cannot be null", new Object[0]);
        Validate.notNull(jackson1ObjectMapperFactory, Jackson1ObjectMapperFactory.class.getSimpleName() + " cannot be null", new Object[0]);
        Validate.notNull(jackson2ObjectMapperFactory, Jackson2ObjectMapperFactory.class.getSimpleName() + " cannot be null", new Object[0]);
        this.defaultObjectMapperType = defaultObjectMapperType;
        this.defaultObjectMapper = defaultObjectMapper;
        this.gsonObjectMapperFactory = gsonObjectMapperFactory;
        this.jackson1ObjectMapperFactory = jackson1ObjectMapperFactory;
        this.jackson2ObjectMapperFactory = jackson2ObjectMapperFactory;
        this.jaxbObjectMapperFactory = jaxbObjectMapperFactory;
        this.johnzonObjectMapperFactory = johnzonObjectMapperFactory;
        this.jsonbObjectMapperFactory = jsonbObjectMapperFactory;
        this.jakartaEEObjectMapperFactory = jakartaEEObjectMapperFactory;
        this.isUserConfigured = isUserConfigured;
    }

    public ObjectMapperType defaultObjectMapperType() {
        return this.defaultObjectMapperType;
    }

    public boolean hasDefaultObjectMapperType() {
        return this.defaultObjectMapperType != null;
    }

    public ObjectMapperConfig defaultObjectMapperType(ObjectMapperType defaultObjectMapperType) {
        return new ObjectMapperConfig(this.defaultObjectMapper, defaultObjectMapperType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.jaxbObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.jakartaEEObjectMapperFactory, true);
    }

    public ObjectMapper defaultObjectMapper() {
        return this.defaultObjectMapper;
    }

    public boolean hasDefaultObjectMapper() {
        return this.defaultObjectMapper != null;
    }

    public ObjectMapperConfig defaultObjectMapper(ObjectMapper defaultObjectMapper) {
        return new ObjectMapperConfig(defaultObjectMapper);
    }

    public GsonObjectMapperFactory gsonObjectMapperFactory() {
        return this.gsonObjectMapperFactory;
    }

    public ObjectMapperConfig gsonObjectMapperFactory(GsonObjectMapperFactory gsonObjectMapperFactory) {
        return new ObjectMapperConfig(this.defaultObjectMapper, this.defaultObjectMapperType, gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.jaxbObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.jakartaEEObjectMapperFactory, true);
    }

    public Jackson1ObjectMapperFactory jackson1ObjectMapperFactory() {
        return this.jackson1ObjectMapperFactory;
    }

    public ObjectMapperConfig jackson1ObjectMapperFactory(Jackson1ObjectMapperFactory jackson1ObjectMapperFactory) {
        return new ObjectMapperConfig(this.defaultObjectMapper, this.defaultObjectMapperType, this.gsonObjectMapperFactory, jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.jaxbObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.jakartaEEObjectMapperFactory, true);
    }

    public Jackson2ObjectMapperFactory jackson2ObjectMapperFactory() {
        return this.jackson2ObjectMapperFactory;
    }

    public ObjectMapperConfig jackson2ObjectMapperFactory(Jackson2ObjectMapperFactory jackson2ObjectMapperFactory) {
        return new ObjectMapperConfig(this.defaultObjectMapper, this.defaultObjectMapperType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, jackson2ObjectMapperFactory, this.jaxbObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.jakartaEEObjectMapperFactory, true);
    }

    public JAXBObjectMapperFactory jaxbObjectMapperFactory() {
        return this.jaxbObjectMapperFactory;
    }

    public JakartaEEObjectMapperFactory jakartaEEObjectMapperFactory() {
        return this.jakartaEEObjectMapperFactory;
    }

    public JohnzonObjectMapperFactory johnzonObjectMapperFactory() {
        return this.johnzonObjectMapperFactory;
    }

    public JsonbObjectMapperFactory jsonbObjectMapperFactory() {
        return this.jsonbObjectMapperFactory;
    }

    public ObjectMapperConfig jsonbObjectMapperFactory(JsonbObjectMapperFactory jsonbObjectMapperFactory) {
        return new ObjectMapperConfig(this.defaultObjectMapper, this.defaultObjectMapperType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.jaxbObjectMapperFactory, this.johnzonObjectMapperFactory, jsonbObjectMapperFactory, this.jakartaEEObjectMapperFactory, true);
    }

    public ObjectMapperConfig jaxbObjectMapperFactory(JAXBObjectMapperFactory jaxbObjectMapperFactory) {
        return new ObjectMapperConfig(this.defaultObjectMapper, this.defaultObjectMapperType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, jaxbObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, this.jakartaEEObjectMapperFactory, true);
    }

    public ObjectMapperConfig jakartaEEObjectMapperFactory(JakartaEEObjectMapperFactory jakartaEEObjectMapperFactory) {
        return new ObjectMapperConfig(this.defaultObjectMapper, this.defaultObjectMapperType, this.gsonObjectMapperFactory, this.jackson1ObjectMapperFactory, this.jackson2ObjectMapperFactory, this.jaxbObjectMapperFactory, this.johnzonObjectMapperFactory, this.jsonbObjectMapperFactory, jakartaEEObjectMapperFactory, true);
    }

    public static ObjectMapperConfig objectMapperConfig() {
        return new ObjectMapperConfig();
    }

    public ObjectMapperConfig and() {
        return this;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }

    private static JAXBObjectMapperFactory newJaxbObjectMapperFactoryOrNullIfNotInClasspath() {
        return ObjectMapperResolver.isJAXBInClassPath() ? new DefaultJAXBObjectMapperFactory() : null;
    }

    private static JakartaEEObjectMapperFactory newJakartaEEObjectMapperFactoryOrNullIfNotInClasspath() {
        return ObjectMapperResolver.isJakartaEEInClassPath() ? new DefaultJakartaEEObjectMapperFactory() : null;
    }
}

