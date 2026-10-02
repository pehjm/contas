/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.path.json;

import groovy.json.JsonBuilder;
import groovy.json.JsonOutput;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.common.path.ObjectConverter;
import io.restassured.internal.path.json.ConfigurableJsonSlurper;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.internal.path.json.JsonPrettifier;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.path.json.config.JsonParserType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson1ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JsonPath {
    public static JsonPathConfig config = null;
    private final JsonParser jsonParser;
    private JsonPathConfig jsonPathConfig = null;
    private String rootPath = "";
    private Map<String, Object> params;

    public JsonPath(String text) {
        this.jsonParser = this.parseText(text);
    }

    public JsonPath(URL url) {
        this.jsonParser = this.parseURL(url);
    }

    public JsonPath(InputStream stream) {
        this.jsonParser = this.parseInputStream(stream);
    }

    public JsonPath(File file) {
        this.jsonParser = this.parseFile(file);
    }

    public JsonPath(Reader reader) {
        this.jsonParser = this.parseReader(reader);
    }

    private JsonPath(JsonPath jsonPath, JsonPathConfig jsonPathConfig) {
        this.jsonPathConfig = jsonPathConfig;
        this.jsonParser = jsonPath.jsonParser;
        this.rootPath = jsonPath.rootPath;
        if (jsonPath.params != null) {
            this.params = new HashMap<String, Object>(jsonPath.params);
        }
    }

    public <T> T get() {
        return this.get("");
    }

    public <T> T get(String path) {
        JSONAssertion jsonAssertion = this.createJsonAssertion(path, this.params);
        Object json = this.jsonParser.parseWith(this.createConfigurableJsonSlurper());
        return (T)jsonAssertion.getResult(json, null);
    }

    public boolean getBoolean(String path) {
        return ObjectConverter.convertObjectTo(this.get(path), Boolean.class);
    }

    public char getChar(String path) {
        return ObjectConverter.convertObjectTo(this.get(path), Character.class).charValue();
    }

    public int getInt(String path) {
        Object value = this.get(path);
        if (value instanceof Integer) {
            return (Integer)value;
        }
        if (value instanceof Short) {
            return ((Short)value).intValue();
        }
        if (value instanceof Long) {
            return ((Long)value).intValue();
        }
        return ObjectConverter.convertObjectTo(value, Integer.class);
    }

    public byte getByte(String path) {
        Object value = this.get(path);
        if (value instanceof Byte) {
            return (Byte)value;
        }
        if (value instanceof Long) {
            return ((Long)value).byteValue();
        }
        if (value instanceof Integer) {
            return ((Integer)value).byteValue();
        }
        return ObjectConverter.convertObjectTo(value, Byte.class);
    }

    public short getShort(String path) {
        Object value = this.get(path);
        if (value instanceof Short) {
            return (Short)value;
        }
        if (value instanceof Long) {
            return ((Long)value).shortValue();
        }
        if (value instanceof Integer) {
            return ((Integer)value).shortValue();
        }
        return ObjectConverter.convertObjectTo(value, Short.class);
    }

    public float getFloat(String path) {
        Object value = this.get(path);
        if (value instanceof Double) {
            return ((Double)value).floatValue();
        }
        return ObjectConverter.convertObjectTo(value, Float.class).floatValue();
    }

    public double getDouble(String path) {
        Object value = this.get(path);
        if (value instanceof Double) {
            return (Double)value;
        }
        return ObjectConverter.convertObjectTo(value, Double.class);
    }

    public long getLong(String path) {
        Object value = this.get(path);
        if (value instanceof Long) {
            return (Long)value;
        }
        if (value instanceof Short) {
            return ((Short)value).longValue();
        }
        if (value instanceof Integer) {
            return ((Integer)value).longValue();
        }
        return ObjectConverter.convertObjectTo(value, Long.class);
    }

    public String getString(String path) {
        return ObjectConverter.convertObjectTo(this.get(path), String.class);
    }

    public UUID getUUID(String path) {
        return ObjectConverter.convertObjectTo(this.get(path), UUID.class);
    }

    public <T> List<T> getList(String path) {
        return (List)this.get(path);
    }

    public <T> List<T> getList(String path, Class<T> genericType) {
        if (genericType == null) {
            throw new IllegalArgumentException("Generic type cannot be null");
        }
        List original = (List)this.get(path);
        LinkedList<T> newList = new LinkedList<T>();
        if (original != null) {
            for (Object t : original) {
                Object e;
                if (t instanceof Map && !genericType.isAssignableFrom(Map.class)) {
                    String str = this.objectToString(t);
                    e = this.jsonStringToObject(str, genericType);
                } else {
                    e = ObjectConverter.convertObjectTo(t, genericType);
                }
                newList.add(e);
            }
        }
        return Collections.unmodifiableList(newList);
    }

    public <K, V> Map<K, V> getMap(String path) {
        return (Map)this.get(path);
    }

    public <K, V> Map<K, V> getMap(String path, Class<K> keyType, Class<V> valueType) {
        Map originalMap = (Map)this.get(path);
        HashMap<Object, Object> newMap = new HashMap<Object, Object>();
        for (Map.Entry entry : originalMap.entrySet()) {
            Object key = entry.getKey() == null ? null : (Object)ObjectConverter.convertObjectTo(entry.getKey(), keyType);
            Object value = entry.getValue() == null ? null : (Object)ObjectConverter.convertObjectTo(entry.getValue(), valueType);
            newMap.put(key, value);
        }
        return Collections.unmodifiableMap(newMap);
    }

    public <T> T getObject(String path, Class<T> objectType) {
        Object object = this.getJsonObject(path);
        if (object == null) {
            return null;
        }
        if (!(object instanceof List) && !(object instanceof Map)) {
            return ObjectConverter.convertObjectTo(object, objectType);
        }
        object = this.objectToString(object);
        if (!(object instanceof String)) {
            throw new IllegalStateException("Internal error: Json object was not an instance of String, please report to the REST Assured mailing-list.");
        }
        return (T)this.jsonStringToObject((String)object, objectType);
    }

    public <T> T getObject(String path, TypeRef<T> typeRef) {
        AssertParameter.notNull("objectType", "Type ref");
        return this.getObject(path, typeRef.getTypeAsClass());
    }

    public JsonPath param(String key, Object value) {
        JsonPath newP = new JsonPath(this, config);
        if (newP.params == null) {
            newP.params = new HashMap<String, Object>();
        }
        newP.params.put(key, value);
        return newP;
    }

    public JsonPath peek() {
        System.out.println(this.toJsonString());
        return this;
    }

    public JsonPath prettyPeek() {
        this.prettyPrint();
        return this;
    }

    public String prettify() {
        String jsonString = this.toJsonString();
        return JsonPrettifier.prettifyJson(jsonString);
    }

    public String prettyPrint() {
        String pretty = this.prettify();
        System.out.println(pretty);
        return pretty;
    }

    public JsonPath using(GsonObjectMapperFactory factory) {
        return new JsonPath(this, this.jsonPathConfig.gsonObjectMapperFactory(factory));
    }

    public JsonPath using(Jackson1ObjectMapperFactory factory) {
        return new JsonPath(this, this.getJsonPathConfig().jackson1ObjectMapperFactory(factory));
    }

    public JsonPath using(Jackson2ObjectMapperFactory factory) {
        return new JsonPath(this, this.getJsonPathConfig().jackson2ObjectMapperFactory(factory));
    }

    public JsonPath using(JsonPathConfig config) {
        return new JsonPath(this, config);
    }

    public JsonPath and() {
        return this;
    }

    public static JsonPath given(String text) {
        return new JsonPath(text);
    }

    public static JsonPath given(InputStream stream) {
        return new JsonPath(stream);
    }

    public static JsonPath given(File file) {
        return new JsonPath(file);
    }

    public static JsonPath given(Reader reader) {
        return new JsonPath(reader);
    }

    public static JsonPath given(URL url) {
        return new JsonPath(url);
    }

    public static JsonPath with(InputStream stream) {
        return new JsonPath(stream);
    }

    public static JsonPath with(String text) {
        return new JsonPath(text);
    }

    public static JsonPath with(File file) {
        return new JsonPath(file);
    }

    public static JsonPath with(Reader reader) {
        return new JsonPath(reader);
    }

    public static JsonPath with(URL url) {
        return new JsonPath(url);
    }

    public static JsonPath from(InputStream stream) {
        return new JsonPath(stream);
    }

    public static JsonPath from(String text) {
        return new JsonPath(text);
    }

    public static JsonPath from(File file) {
        return new JsonPath(file);
    }

    public static JsonPath from(Reader reader) {
        return new JsonPath(reader);
    }

    public static JsonPath from(URL url) {
        return new JsonPath(url);
    }

    @Deprecated
    public JsonPath setRoot(String rootPath) {
        return this.setRootPath(rootPath);
    }

    public JsonPath setRootPath(String rootPath) {
        AssertParameter.notNull(rootPath, "Root path");
        this.rootPath = rootPath;
        return this;
    }

    private JsonParser parseInputStream(final InputStream stream) {
        return new JsonParser(){

            @Override
            public Object doParseWith(final ConfigurableJsonSlurper slurper) {
                return new ExceptionCatcher(){

                    @Override
                    protected Object method() throws Exception {
                        return slurper.parse(JsonPath.this.toReader(stream));
                    }
                }.invoke();
            }
        };
    }

    private JsonParser parseReader(final Reader reader) {
        return new JsonParser(){

            @Override
            public Object doParseWith(final ConfigurableJsonSlurper slurper) {
                return new ExceptionCatcher(){

                    @Override
                    protected Object method() throws Exception {
                        return slurper.parse(reader);
                    }
                }.invoke();
            }
        };
    }

    private JsonParser parseFile(final File file) {
        return new JsonParser(){

            @Override
            public Object doParseWith(final ConfigurableJsonSlurper slurper) {
                return new ExceptionCatcher(){

                    @Override
                    protected Object method() throws Exception {
                        return slurper.parse(new FileReader(file));
                    }
                }.invoke();
            }
        };
    }

    private JsonParser parseText(final String text) {
        return new JsonParser(){

            @Override
            public Object doParseWith(final ConfigurableJsonSlurper slurper) {
                return new ExceptionCatcher(){

                    @Override
                    protected Object method() throws Exception {
                        return slurper.parseText(text);
                    }
                }.invoke();
            }
        };
    }

    private JsonParser parseURL(final URL url) {
        return new JsonParser(){

            @Override
            public Object doParseWith(final ConfigurableJsonSlurper slurper) {
                return new ExceptionCatcher(){

                    @Override
                    protected Object method() throws Exception {
                        return slurper.parse(JsonPath.this.toReader(url.openStream()));
                    }
                }.invoke();
            }
        };
    }

    private BufferedReader toReader(InputStream in) {
        JsonPathConfig cfg = this.getJsonPathConfig();
        try {
            return new BufferedReader(new InputStreamReader(in, cfg.charset()));
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Charset is invalid", e);
        }
    }

    public <T> T getJsonObject(String path) {
        JSONAssertion jsonAssertion = this.createJsonAssertion(path, this.params);
        Object json = this.jsonParser.parseWith(this.createConfigurableJsonSlurper());
        return (T)jsonAssertion.getAsJsonObject(json);
    }

    private JSONAssertion createJsonAssertion(String path, Map<String, Object> params) {
        AssertParameter.notNull(path, "path");
        JSONAssertion jsonAssertion = new JSONAssertion();
        boolean appendDot = !this.rootPath.equals("") && !this.rootPath.endsWith(".") && !path.startsWith("[");
        String root = appendDot ? this.rootPath + "." : this.rootPath;
        jsonAssertion.setKey(root + path);
        if (params != null) {
            jsonAssertion.setParams(params);
        }
        return jsonAssertion;
    }

    private ConfigurableJsonSlurper createConfigurableJsonSlurper() {
        JsonPathConfig cfg = this.getJsonPathConfig();
        return new ConfigurableJsonSlurper(cfg.numberReturnType());
    }

    private JsonPathConfig getJsonPathConfig() {
        JsonPathConfig cfg = config == null && this.jsonPathConfig == null ? new JsonPathConfig() : (this.jsonPathConfig != null ? this.jsonPathConfig : config);
        return cfg;
    }

    public static void reset() {
        config = null;
    }

    private String toJsonString() {
        Object json = this.jsonParser.parseWith(this.createConfigurableJsonSlurper());
        String jsonString = json instanceof Map ? JsonOutput.toJson((Map)json) : JsonOutput.toJson(json);
        return jsonString;
    }

    private String objectToString(Object object) {
        return new JsonBuilder(object).toString();
    }

    private Object jsonStringToObject(String object, Class objectType) {
        JsonPathConfig cfg = new JsonPathConfig(this.getJsonPathConfig());
        if (cfg.hasCustomJackson10ObjectMapperFactory()) {
            cfg = cfg.defaultParserType(JsonParserType.JACKSON_1);
        } else if (cfg.hasCustomGsonObjectMapperFactory()) {
            cfg = cfg.defaultParserType(JsonParserType.GSON);
        } else if (cfg.hasCustomJackson20ObjectMapperFactory()) {
            cfg = cfg.defaultParserType(JsonParserType.JACKSON_2);
        } else if (cfg.hasCustomJohnzonObjectMapperFactory()) {
            cfg = cfg.defaultParserType(JsonParserType.JOHNZON);
        } else if (cfg.hasCustomJsonbObjectMapperFactory()) {
            cfg = cfg.defaultParserType(JsonParserType.JSONB);
        }
        return JsonObjectDeserializer.deserialize(object, objectType, cfg);
    }

    private abstract class JsonParser {
        private Object json;

        private JsonParser() {
        }

        public final Object parseWith(ConfigurableJsonSlurper slurper) {
            if (this.json == null) {
                this.json = this.doParseWith(slurper);
            }
            return this.json;
        }

        abstract Object doParseWith(ConfigurableJsonSlurper var1);
    }

    private abstract class ExceptionCatcher {
        private ExceptionCatcher() {
        }

        protected abstract Object method() throws Exception;

        public Object invoke() {
            try {
                return this.method();
            }
            catch (Exception e) {
                throw new JsonPathException("Failed to parse the JSON document", e);
            }
        }
    }
}

