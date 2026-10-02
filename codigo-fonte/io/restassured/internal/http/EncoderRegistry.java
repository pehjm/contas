/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import groovy.json.JsonBuilder;
import groovy.lang.Closure;
import groovy.lang.GString;
import groovy.lang.Writable;
import groovy.xml.StreamingMarkupBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.internal.http.CharsetExtractor;
import io.restassured.internal.http.ContentTypeExtractor;
import io.restassured.internal.support.FileReader;
import io.restassured.internal.util.IOUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.groovy.runtime.IOGroovyMethods;
import org.codehaus.groovy.runtime.MethodClosure;

public class EncoderRegistry {
    private Map<String, Closure> registeredEncoders = this.buildDefaultEncoderMap();
    private EncoderConfig encoderConfig = new EncoderConfig();

    public void setEncoderConfig(EncoderConfig encoderConfig) {
        this.encoderConfig = encoderConfig;
    }

    public InputStreamEntity encodeStream(Object contentType, Object data) throws UnsupportedEncodingException {
        InputStreamEntity entity = null;
        if (data instanceof ByteArrayInputStream) {
            ByteArrayInputStream in = (ByteArrayInputStream)data;
            entity = new InputStreamEntity((InputStream)in, in.available());
        } else if (data instanceof InputStream) {
            entity = new InputStreamEntity((InputStream)data, -1L);
        } else if (data instanceof File) {
            FileInputStream fileInputStream;
            File file = (File)data;
            try {
                fileInputStream = new FileInputStream(file);
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException("File " + file.getPath() + " not found", e);
            }
            entity = new InputStreamEntity((InputStream)fileInputStream, file.length());
        } else if (data instanceof byte[]) {
            byte[] out = (byte[])data;
            entity = new InputStreamEntity((InputStream)new ByteArrayInputStream(out), out.length);
        } else if (data instanceof ByteArrayOutputStream) {
            ByteArrayOutputStream out = (ByteArrayOutputStream)data;
            entity = new InputStreamEntity((InputStream)new ByteArrayInputStream(out.toByteArray()), out.size());
        } else if (data instanceof Closure) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ((Closure)data).call((Object)out);
            entity = new InputStreamEntity((InputStream)new ByteArrayInputStream(out.toByteArray()), out.size());
        }
        if (entity == null) {
            throw new IllegalArgumentException("Don't know how to encode " + data + " as a byte stream.\n\nPlease use EncoderConfig (EncoderConfig#encodeContentTypeAs) to specify how to serialize data for this content-type.\nFor example: \"given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs(\"" + ContentTypeExtractor.getContentTypeWithoutCharset(this.contentTypeToString(contentType)) + "\", ContentType.TEXT))). ..\"");
        }
        entity.setContentType(this.contentTypeToString(contentType));
        return entity;
    }

    public HttpEntity encodeText(Object contentType, Object data) throws IOException {
        StringWriter out;
        String contentTypeAsString = this.contentTypeToString(contentType);
        if (data instanceof Closure) {
            out = new StringWriter();
            PrintWriter writer = new PrintWriter(out);
            ((Closure)data).call((Object)writer);
            writer.close();
            out.flush();
            data = out;
        } else if (data instanceof Writable) {
            out = new StringWriter();
            ((Writable)data).writeTo(out);
            out.flush();
            data = out;
        } else if (data instanceof Reader && !(data instanceof BufferedReader)) {
            data = new BufferedReader((Reader)data);
        } else if (data instanceof File) {
            data = this.toString((File)data, contentTypeAsString);
        }
        if (data instanceof BufferedReader) {
            out = new StringWriter();
            IOGroovyMethods.leftShift(out, (Object)((BufferedReader)data));
            data = out;
        }
        return this.createEntity(contentTypeAsString, data);
    }

    public UrlEncodedFormEntity encodeForm(Map<?, ?> params) throws UnsupportedEncodingException {
        ArrayList<BasicNameValuePair> paramList = new ArrayList<BasicNameValuePair>();
        for (Object key : params.keySet()) {
            Object val = params.get(key);
            if (val instanceof List) {
                for (Object subVal : (List)val) {
                    paramList.add(new BasicNameValuePair(key.toString(), subVal == null ? "" : subVal.toString()));
                }
                continue;
            }
            paramList.add(new BasicNameValuePair(key.toString(), val == null ? "" : val.toString()));
        }
        return new UrlEncodedFormEntity(paramList, this.encoderConfig.defaultContentCharset());
    }

    public HttpEntity encodeForm(Object contentType, String formData) throws UnsupportedEncodingException {
        return this.createEntity(this.contentTypeToString(contentType), formData);
    }

    public HttpEntity encodeXML(Object contentType, Object xml) throws UnsupportedEncodingException {
        String contentTypeAsString = this.contentTypeToString(contentType);
        if (xml instanceof Closure) {
            StreamingMarkupBuilder smb = new StreamingMarkupBuilder();
            xml = smb.bind(xml);
        } else if (xml instanceof File) {
            xml = this.toString((File)xml, contentTypeAsString);
        }
        return this.createEntity(contentTypeAsString, xml);
    }

    public HttpEntity encodeJSON(Object contentType, Object model) throws IOException {
        Object json;
        String contentTypeAsString = this.contentTypeToString(contentType);
        if (model instanceof Map || model instanceof Collection) {
            json = new JsonBuilder(model);
        } else if (model instanceof Closure) {
            Closure closure = (Closure)model;
            closure.setDelegate(new JsonBuilder());
            json = closure.call();
        } else if (model instanceof String || model instanceof GString || model instanceof byte[]) {
            json = model;
        } else if (model instanceof File) {
            json = this.toString((File)model, contentTypeAsString);
        } else if (model instanceof InputStream) {
            json = IOUtils.toByteArray((InputStream)model);
        } else {
            throw new UnsupportedOperationException("Internal error: Can't encode " + model + " to JSON.");
        }
        return this.createEntity(contentTypeAsString, json);
    }

    private HttpEntity createEntity(String ct, Object object) throws UnsupportedEncodingException {
        if (object instanceof byte[]) {
            return this.createEntity(ct, (byte[])object);
        }
        if (object instanceof InputStream) {
            return this.createEntity(ct, (InputStream)object);
        }
        return this.createEntity(ct, object.toString());
    }

    protected HttpEntity createEntity(String ct, byte[] byteArray) {
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(byteArray);
        byteArrayEntity.setContentType(ct);
        return byteArrayEntity;
    }

    protected HttpEntity createEntity(String ct, InputStream inputStream) {
        InputStreamEntity inputStreamEntity = new InputStreamEntity(inputStream);
        inputStreamEntity.setContentType(ct);
        return inputStreamEntity;
    }

    protected HttpEntity createEntity(String ct, String data) throws UnsupportedEncodingException {
        String charset = CharsetExtractor.getCharsetFromContentType(ct);
        if (charset == null) {
            charset = this.encoderConfig.hasDefaultCharsetForContentType(ct) ? this.encoderConfig.defaultCharsetForContentType(ct) : this.encoderConfig.defaultContentCharset();
        }
        StringEntity entity = new StringEntity(data, charset);
        entity.setContentType(ct);
        return entity;
    }

    protected Map<String, Closure> buildDefaultEncoderMap() {
        HashMap<String, Closure> encoders = new HashMap<String, Closure>();
        encoders.put(ContentType.BINARY.toString(), new MethodClosure((Object)this, "encodeStream"));
        encoders.put(ContentType.TEXT.toString(), new MethodClosure((Object)this, "encodeText"));
        encoders.put(ContentType.URLENC.toString(), new MethodClosure((Object)this, "encodeForm"));
        MethodClosure encClosure = new MethodClosure((Object)this, "encodeXML");
        for (String ct : ContentType.XML.getContentTypeStrings()) {
            encoders.put(ct, encClosure);
        }
        encoders.put(ContentType.HTML.toString(), encClosure);
        encClosure = new MethodClosure((Object)this, "encodeJSON");
        for (String ct : ContentType.JSON.getContentTypeStrings()) {
            encoders.put(ct, encClosure);
        }
        return encoders;
    }

    public Closure getAt(Object contentType) {
        ContentType foundCt;
        Closure closure;
        String ct = contentType.toString();
        int idx = ct.indexOf(59);
        if (idx > 0) {
            ct = ct.substring(0, idx);
        }
        if ((closure = this.registeredEncoders.get(ct)) == null && (foundCt = ContentType.fromContentType(ct)) != null) {
            closure = this.registeredEncoders.get(foundCt.toString());
        }
        if (closure == null) {
            closure = this.tryToFindMatchingEncoder(ct);
        }
        if (closure == null) {
            return this.getAt(ContentType.BINARY.toString());
        }
        return closure;
    }

    private Closure tryToFindMatchingEncoder(String contentType) {
        MethodClosure closure = contentType == null ? null : (StringUtils.startsWithIgnoreCase(contentType, "text/") || StringUtils.containsIgnoreCase(contentType, "+text") ? new MethodClosure((Object)this, "encodeText") : null);
        return closure;
    }

    public void putAt(Object contentType, Closure value) {
        if (contentType instanceof ContentType) {
            for (String ct : ((ContentType)((Object)contentType)).getContentTypeStrings()) {
                this.registeredEncoders.put(ct, value);
            }
        } else {
            this.registeredEncoders.put(this.contentTypeToString(contentType), value);
        }
    }

    public Closure propertyMissing(Object key) {
        return this.getAt(key);
    }

    public void propertyMissing(Object key, Closure value) {
        this.putAt(key, value);
    }

    public Iterator<Map.Entry<String, Closure>> iterator() {
        return this.registeredEncoders.entrySet().iterator();
    }

    private String contentTypeToString(Object contentType) {
        return contentType == null ? null : contentType.toString();
    }

    private String toString(File model, String contentTypeAsString) {
        String charset = CharsetExtractor.getCharsetFromContentType(contentTypeAsString);
        return FileReader.readToString(model, charset == null ? this.encoderConfig.defaultCharsetForContentType(contentTypeAsString) : charset);
    }
}

