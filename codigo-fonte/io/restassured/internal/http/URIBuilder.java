/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.config.EncoderConfig;
import io.restassured.internal.http.BasicNameValuePairWithNoValueSupport;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class URIBuilder
implements Cloneable {
    private static final String PARAMETER_SEPARATOR = "&";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PLUS = "+";
    private static final String PERCENTAGE_20 = "%20";
    protected URI base;
    private String enc;
    private final boolean isUrlEncodingEnabled;

    public URIBuilder(URI uri, boolean urlEncodingEnabled, EncoderConfig config) throws IllegalArgumentException {
        Validate.notNull(uri, "uri cannot be null", new Object[0]);
        Validate.notNull(config, "encoder config cannot be null", new Object[0]);
        this.base = uri;
        this.enc = config.defaultQueryParameterCharset();
        this.isUrlEncodingEnabled = urlEncodingEnabled;
    }

    public static URI convertToURI(Object uri) throws URISyntaxException {
        if (uri instanceof URI) {
            return (URI)uri;
        }
        if (uri instanceof URL) {
            return ((URL)uri).toURI();
        }
        if (uri instanceof URIBuilder) {
            return ((URIBuilder)uri).toURI();
        }
        return new URI(uri.toString());
    }

    public URIBuilder setScheme(String scheme) throws URISyntaxException {
        this.base = new URI(scheme, this.base.getUserInfo(), this.base.getHost(), this.base.getPort(), this.base.getPath(), this.base.getQuery(), this.base.getFragment());
        return this;
    }

    public URIBuilder setPort(int port) throws URISyntaxException {
        this.base = new URI(this.base.getScheme(), this.base.getUserInfo(), this.base.getHost(), port, this.base.getPath(), this.base.getQuery(), this.base.getFragment());
        return this;
    }

    public URIBuilder setHost(String host) throws URISyntaxException {
        this.base = new URI(this.base.getScheme(), this.base.getUserInfo(), host, this.base.getPort(), this.base.getPath(), this.base.getQuery(), this.base.getFragment());
        return this;
    }

    public URIBuilder setPath(String path) throws URISyntaxException {
        String frag;
        String query;
        StringBuilder sb = new StringBuilder();
        if (path != null) {
            sb.append(path);
        }
        if ((query = this.base.getQuery()) != null) {
            sb.append('?');
            sb.append(query);
        }
        if ((frag = this.base.getRawFragment()) != null) {
            sb.append('#').append(frag);
        }
        this.base = this.base.resolve(sb.toString());
        return this;
    }

    protected URIBuilder setQueryNVP(List<NameValuePair> nvp) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        String path = this.base.getRawPath();
        if (path != null) {
            sb.append(path);
        }
        sb.append('?');
        sb.append(URIBuilder.format(nvp, this.isUrlEncodingEnabled, this.enc));
        String frag = this.base.getRawFragment();
        if (frag != null) {
            sb.append('#').append(frag);
        }
        this.base = this.base.resolve(sb.toString());
        return this;
    }

    public URIBuilder setQuery(Map<?, ?> params) throws URISyntaxException {
        if (params != null && params.size() >= 1) {
            ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>(params.size());
            for (Object key : params.keySet()) {
                Object value = params.get(key);
                if (value instanceof List) {
                    for (Object val : (List)value) {
                        nvp.add(new BasicNameValuePairWithNoValueSupport(key.toString(), val));
                    }
                    continue;
                }
                nvp.add(new BasicNameValuePairWithNoValueSupport(key.toString(), value));
            }
            this.setQueryNVP(nvp);
        }
        return this;
    }

    public Map<String, Object> getQuery() {
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        List<NameValuePair> pairs = this.getQueryNVP();
        for (NameValuePair pair : pairs) {
            String key = pair.getName();
            Object existing = params.get(key);
            if (existing == null) {
                params.put(key, pair.getValue());
                continue;
            }
            if (existing instanceof List) {
                ((List)existing).add(pair.getValue());
                continue;
            }
            ArrayList<String> vals = new ArrayList<String>(2);
            vals.add((String)existing);
            vals.add(pair.getValue());
            params.put(key, vals);
        }
        return params;
    }

    protected List<NameValuePair> getQueryNVP() {
        List<NameValuePair> nvps = this.parse(this.base);
        ArrayList<NameValuePair> newList = new ArrayList<NameValuePair>();
        if (nvps != null) {
            newList.addAll(nvps);
        }
        return newList;
    }

    public boolean hasQueryParam(String name) {
        return this.getQuery().get(name) != null;
    }

    public URIBuilder removeQueryParam(String param) throws URISyntaxException {
        List<NameValuePair> params = this.getQueryNVP();
        NameValuePair found = null;
        for (NameValuePair nvp : params) {
            if (!nvp.getName().equals(param)) continue;
            found = nvp;
            break;
        }
        if (found == null) {
            throw new IllegalArgumentException("Param '" + param + "' not found");
        }
        params.remove(found);
        this.setQueryNVP(params);
        return this;
    }

    protected URIBuilder addQueryParams(List<NameValuePair> nvp) throws URISyntaxException {
        List<NameValuePair> params = this.getQueryNVP();
        params.addAll(nvp);
        this.setQueryNVP(params);
        return this;
    }

    public URIBuilder addQueryParams(Map<?, ?> params) throws URISyntaxException {
        ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
        for (Object key : params.keySet()) {
            Object value = params.get(key);
            if (value instanceof List) {
                for (Object val : (List)value) {
                    nvp.add(new BasicNameValuePairWithNoValueSupport(key.toString(), val));
                }
                continue;
            }
            nvp.add(new BasicNameValuePairWithNoValueSupport(key.toString(), value));
        }
        this.addQueryParams(nvp);
        return this;
    }

    public URIBuilder setFragment(String fragment) throws URISyntaxException {
        this.base = new URI(this.base.getScheme(), this.base.getUserInfo(), this.base.getHost(), this.base.getPort(), this.base.getPath(), this.base.getQuery(), fragment);
        return this;
    }

    public String toString() {
        return this.base.toString();
    }

    public URL toURL() throws MalformedURLException {
        return this.base.toURL();
    }

    public URI toURI() {
        return this.base;
    }

    public Object asType(Class<?> type) throws MalformedURLException {
        if (type == URI.class) {
            return this.toURI();
        }
        if (type == URL.class) {
            return this.toURL();
        }
        if (type == String.class) {
            return this.toString();
        }
        throw new ClassCastException("Cannot cast instance of URIBuilder to class " + type);
    }

    protected URIBuilder clone() {
        return new URIBuilder(this.base, this.isUrlEncodingEnabled, EncoderConfig.encoderConfig().defaultQueryParameterCharset(this.enc));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof URIBuilder)) {
            return false;
        }
        return this.base.equals(((URIBuilder)obj).toURI());
    }

    private static String format(List<? extends NameValuePair> parameters, boolean isUrlEncodingEnabled, String encoding) {
        StringBuilder result = new StringBuilder();
        for (NameValuePair nameValuePair : parameters) {
            if (result.length() > 0) {
                result.append(PARAMETER_SEPARATOR);
            }
            String encodedName = isUrlEncodingEnabled ? URIBuilder.encode(nameValuePair.getName(), encoding) : nameValuePair.getName();
            result.append(encodedName);
            if (!URIBuilder.hasValue(nameValuePair)) continue;
            String value = nameValuePair.getValue();
            String encodedValue = value != null ? (isUrlEncodingEnabled ? URIBuilder.encode(value, encoding) : value) : "";
            result.append(NAME_VALUE_SEPARATOR);
            result.append(encodedValue);
        }
        return result.toString();
    }

    private static boolean hasValue(NameValuePair parameter) {
        if (!(parameter instanceof BasicNameValuePairWithNoValueSupport)) {
            return true;
        }
        return ((BasicNameValuePairWithNoValueSupport)parameter).hasValue();
    }

    public static String encode(String content, String encoding) {
        try {
            String encoded = URLEncoder.encode(content, encoding != null ? encoding : Charset.defaultCharset().toString());
            encoded = StringUtils.replace(encoded, PLUS, PERCENTAGE_20);
            return encoded;
        }
        catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

    private List<NameValuePair> parse(URI uri) {
        ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
        String query = uri.getRawQuery();
        if (query != null && query.length() > 0) {
            Scanner scanner = new Scanner(query);
            scanner.useDelimiter(PARAMETER_SEPARATOR);
            while (scanner.hasNext()) {
                String name;
                String value = null;
                String token = scanner.next();
                int i = token.indexOf(NAME_VALUE_SEPARATOR);
                if (i != -1) {
                    name = token.substring(0, i).trim();
                    value = token.substring(i + 1).trim();
                } else {
                    name = token.trim();
                }
                parameters.add(new BasicNameValuePair(name, value));
            }
        }
        return parameters;
    }
}

