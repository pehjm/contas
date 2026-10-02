/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import groovy.lang.Closure;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.config.OAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.internal.http.AuthConfig;
import io.restassured.internal.http.ContentEncoding;
import io.restassured.internal.http.ContentEncodingRegistry;
import io.restassured.internal.http.EncoderRegistry;
import io.restassured.internal.http.HttpContextDecorator;
import io.restassured.internal.http.HttpRequestFactory;
import io.restassured.internal.http.HttpResponseContentTypeFinder;
import io.restassured.internal.http.HttpResponseDecorator;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.internal.http.ResponseParseException;
import io.restassured.internal.http.Status;
import io.restassured.internal.http.StringHashMap;
import io.restassured.internal.http.URIBuilder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;
import org.codehaus.groovy.runtime.IOGroovyMethods;
import org.codehaus.groovy.runtime.MethodClosure;

public abstract class HTTPBuilder {
    protected AbstractHttpClient client;
    protected URIBuilder defaultURI = null;
    protected AuthConfig auth;
    protected final Log log = LogFactory.getLog(this.getClass());
    protected Object defaultContentType = ContentType.ANY;
    protected Object defaultRequestContentType = null;
    protected final Map<Object, Closure> defaultResponseHandlers = new StringHashMap<Closure>(this.buildDefaultResponseHandlers());
    protected ContentEncodingRegistry contentEncodingHandler;
    protected final Map<Object, Object> defaultRequestHeaders = new StringHashMap<Object>();
    protected EncoderRegistry encoders;
    protected HttpResponseContentTypeFinder parsers = new HttpResponseContentTypeFinder();
    private EncoderConfig encoderConfig;
    private boolean urlEncodingEnabled;

    public HTTPBuilder(boolean urlEncodingEnabled, EncoderConfig encoderConfig, DecoderConfig decoderConfig, OAuthConfig oAuthConfig, AbstractHttpClient client) {
        this.client = client;
        this.auth = new AuthConfig(this, oAuthConfig);
        this.contentEncodingHandler = new ContentEncodingRegistry(decoderConfig);
        this.setContentEncoding(new Object[]{ContentEncoding.Type.GZIP, ContentEncoding.Type.DEFLATE});
        this.encoderConfig = encoderConfig == null ? new EncoderConfig() : encoderConfig;
        this.urlEncodingEnabled = urlEncodingEnabled;
        this.encoders = new EncoderRegistry();
        if (this.encoderConfig.hasContentEncoders()) {
            Map<String, ContentType> customEncoders = this.encoderConfig.contentEncoders();
            for (Map.Entry<String, ContentType> entry : customEncoders.entrySet()) {
                Closure actualEncoder = this.encoders.getAt(entry.getValue().getContentTypeStrings()[0]);
                this.encoders.putAt(entry.getKey(), actualEncoder);
            }
        }
    }

    public HTTPBuilder(Object defaultURI, boolean urlEncodingEnabled, EncoderConfig encoderConfig, DecoderConfig decoderConfig, OAuthConfig oAuthConfig, AbstractHttpClient client) {
        this(urlEncodingEnabled, encoderConfig, decoderConfig, oAuthConfig, client);
        try {
            this.defaultURI = new URIBuilder(URIBuilder.convertToURI(defaultURI), this.urlEncodingEnabled, this.encoderConfig);
        }
        catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public HTTPBuilder(Object defaultURI, Object defaultContentType, boolean urlEncodingEnabled, EncoderConfig encoderConfig, DecoderConfig decoderConfig, OAuthConfig oAuthConfig, AbstractHttpClient client) throws URISyntaxException {
        this(urlEncodingEnabled, encoderConfig, decoderConfig, oAuthConfig, client);
        this.defaultURI = new URIBuilder(URIBuilder.convertToURI(defaultURI), urlEncodingEnabled, this.encoderConfig);
        this.defaultContentType = defaultContentType;
    }

    public Object get(Map<String, ?> args) throws ClientProtocolException, IOException, URISyntaxException {
        return this.get(args, null);
    }

    public Object get(Map<String, ?> args, Closure responseClosure) throws ClientProtocolException, IOException, URISyntaxException {
        RequestConfigDelegate delegate = new RequestConfigDelegate(new HttpGet(), this.defaultContentType, this.defaultRequestHeaders, this.defaultResponseHandlers);
        delegate.setPropertiesFromMap(args);
        if (responseClosure != null) {
            delegate.getResponse().put((Object)Status.SUCCESS, responseClosure);
        }
        return this.doRequest(delegate);
    }

    public Object post(Map<String, ?> args) throws ClientProtocolException, URISyntaxException, IOException {
        return this.post(args, null);
    }

    public Object post(Map<String, ?> args, Closure responseClosure) throws URISyntaxException, ClientProtocolException, IOException {
        RequestConfigDelegate delegate = new RequestConfigDelegate(new HttpPost(), this.defaultContentType, this.defaultRequestHeaders, this.defaultResponseHandlers);
        delegate.setRequestContentType(ContentType.URLENC.toString());
        delegate.setPropertiesFromMap(args);
        if (responseClosure != null) {
            delegate.getResponse().put(Status.SUCCESS.toString(), responseClosure);
        }
        return this.doRequest(delegate);
    }

    public Object patch(Map<String, ?> args) throws ClientProtocolException, URISyntaxException, IOException {
        return this.patch(args, null);
    }

    public Object patch(Map<String, ?> args, Closure responseClosure) throws URISyntaxException, ClientProtocolException, IOException {
        RequestConfigDelegate delegate = new RequestConfigDelegate(new HttpPatch(), this.defaultContentType, this.defaultRequestHeaders, this.defaultResponseHandlers);
        if (((Boolean)args.get("allowContentType")).booleanValue()) {
            delegate.setRequestContentType(ContentType.URLENC.toString());
            delegate.setPropertiesFromMap(args);
        }
        if (responseClosure != null) {
            delegate.getResponse().put(Status.SUCCESS.toString(), responseClosure);
            delegate.getResponse().put(Status.FAILURE.toString(), responseClosure);
        }
        return this.doRequest(delegate);
    }

    public Object request(String method, boolean hasBody, Closure configClosure) throws ClientProtocolException, IOException {
        return this.doRequest(this.defaultURI.toURI(), method, this.defaultContentType, hasBody, configClosure);
    }

    public Object request(String method, Object contentType, boolean hasBody, Closure configClosure) throws ClientProtocolException, IOException {
        return this.doRequest(this.defaultURI.toURI(), method, contentType, hasBody, configClosure);
    }

    public Object request(Object uri, String method, Object contentType, boolean hasBody, Closure configClosure) throws ClientProtocolException, IOException, URISyntaxException {
        return this.doRequest(URIBuilder.convertToURI(uri), method, contentType, hasBody, configClosure);
    }

    protected Object doRequest(URI uri, String method, Object contentType, boolean hasBody, Closure configClosure) throws IOException {
        HttpRequestBase reqMethod = HttpRequestFactory.createHttpRequest(uri, method, hasBody);
        RequestConfigDelegate delegate = new RequestConfigDelegate(reqMethod, contentType, this.defaultRequestHeaders, this.defaultResponseHandlers);
        configClosure.setDelegate(delegate);
        configClosure.setResolveStrategy(1);
        configClosure.call((Object)reqMethod);
        return this.doRequest(delegate);
    }

    protected abstract Object doRequest(RequestConfigDelegate var1) throws ClientProtocolException, IOException;

    protected Object parseResponse(Object resp, Object contentType) throws IOException {
        HttpResponse httpResponse = (HttpResponse)resp;
        if (httpResponse.getEntity() == null) {
            this.log.debug("Response contains no entity.  Parsed data is null.");
            return null;
        }
        String responseContentType = contentType.toString();
        try {
            if (ContentType.ANY.toString().equals(responseContentType)) {
                responseContentType = HttpResponseContentTypeFinder.findContentType(httpResponse);
            }
        }
        catch (RuntimeException ex) {
            responseContentType = ContentType.BINARY.toString();
        }
        this.log.debug("Parsing response as: " + responseContentType);
        InputStream parsedData = httpResponse.getEntity().getContent();
        if (parsedData == null) {
            this.log.debug("Parser returned null!");
        } else {
            this.log.debug("Parsed data to instance of: " + parsedData.getClass());
        }
        return parsedData;
    }

    protected Map<Object, Closure> buildDefaultResponseHandlers() {
        StringHashMap<Closure> map = new StringHashMap<Closure>();
        map.put(Status.SUCCESS, new MethodClosure((Object)this, "defaultSuccessHandler"));
        map.put(Status.FAILURE, new MethodClosure((Object)this, "defaultFailureHandler"));
        return map;
    }

    protected Object defaultSuccessHandler(HttpResponseDecorator resp, Object parsedData) throws ResponseParseException {
        try {
            if (parsedData instanceof InputStream) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                IOGroovyMethods.leftShift((OutputStream)buffer, (InputStream)parsedData);
                parsedData = new ByteArrayInputStream(buffer.toByteArray());
            } else if (parsedData instanceof Reader) {
                StringWriter buffer = new StringWriter();
                IOGroovyMethods.leftShift(buffer, (Object)((Reader)parsedData));
                parsedData = new StringReader(buffer.toString());
            } else if (parsedData instanceof Closeable) {
                this.log.debug("Parsed data is streaming, but will be accessible after the network connection is closed.  Use at your own risk!");
            }
            return parsedData;
        }
        catch (IOException ex) {
            throw new ResponseParseException(resp, (Throwable)ex);
        }
    }

    protected void defaultFailureHandler(HttpResponseDecorator resp) throws HttpResponseException {
        throw new HttpResponseException(resp);
    }

    public Map<?, Closure> getHandler() {
        return this.defaultResponseHandlers;
    }

    public HttpResponseContentTypeFinder getParser() {
        return this.parsers;
    }

    public void setContentType(Object ct) {
        this.defaultContentType = ct;
    }

    public Object getContentType() {
        return this.defaultContentType;
    }

    public void setContentEncoding(Object ... encodings) {
        this.contentEncodingHandler.setInterceptors(this.client, encodings);
    }

    public void setUri(Object uri) throws URISyntaxException {
        this.defaultURI = new URIBuilder(URIBuilder.convertToURI(uri), this.urlEncodingEnabled, this.encoderConfig);
    }

    public Object getUri() {
        return this.defaultURI;
    }

    public void setHeaders(Map<?, ?> headers) {
        this.defaultRequestHeaders.clear();
        if (headers == null) {
            return;
        }
        for (Object key : headers.keySet()) {
            Object val = headers.get(key);
            if (val == null) continue;
            this.defaultRequestHeaders.put(key.toString(), val.toString());
        }
    }

    public Map<?, ?> getHeaders() {
        return this.defaultRequestHeaders;
    }

    public AbstractHttpClient getClient() {
        return this.client;
    }

    public void setContentEncodingRegistry(ContentEncodingRegistry cer) {
        this.contentEncodingHandler = cer;
    }

    public void setProxy(String host, int port, String scheme) {
        this.getClient().getParams().setParameter("http.route.default-proxy", new HttpHost(host, port, scheme));
    }

    public void shutdown() {
        this.client.getConnectionManager().shutdown();
    }

    protected class RequestConfigDelegate {
        private HttpRequestBase request;
        private Object contentType;
        private String requestContentType;
        private boolean allowContentType;
        private Map<Object, Closure> responseHandlers = new StringHashMap<Closure>();
        public URIBuilder uri;
        private Map<Object, Object> headers = new StringHashMap<Object>();
        private HttpContextDecorator context = new HttpContextDecorator();

        public RequestConfigDelegate(HttpRequestBase request, Object contentType, Map<?, ?> defaultRequestHeaders, Map<?, Closure> defaultResponseHandlers) {
            if (request == null) {
                throw new IllegalArgumentException("Internal error - HttpRequest instance cannot be null");
            }
            this.request = request;
            this.headers.putAll(defaultRequestHeaders);
            this.contentType = contentType;
            if (HTTPBuilder.this.defaultRequestContentType != null) {
                this.requestContentType = HTTPBuilder.this.defaultRequestContentType.toString();
            }
            this.responseHandlers.putAll(defaultResponseHandlers);
            URI uri = request.getURI();
            if (uri != null) {
                this.uri = new URIBuilder(uri, HTTPBuilder.this.urlEncodingEnabled, HTTPBuilder.this.encoderConfig);
            }
        }

        public RequestConfigDelegate(Map<String, ?> args, HttpRequestBase request, Closure successHandler) throws URISyntaxException {
            this(request, this$0.defaultContentType, this$0.defaultRequestHeaders, this$0.defaultResponseHandlers);
            if (successHandler != null) {
                this.responseHandlers.put(Status.SUCCESS.toString(), successHandler);
            }
            this.setPropertiesFromMap(args);
        }

        public URIBuilder getUri() {
            return this.uri;
        }

        public void setUri(Object uri) throws URISyntaxException {
            if (uri instanceof URIBuilder) {
                this.uri = (URIBuilder)uri;
            }
            this.uri = new URIBuilder(URIBuilder.convertToURI(uri), HTTPBuilder.this.urlEncodingEnabled, HTTPBuilder.this.encoderConfig);
        }

        public HttpRequestBase getRequest() {
            return this.request;
        }

        public Object getContentType() {
            return this.contentType;
        }

        protected void setContentType(Object ct) {
            this.contentType = ct == null ? HTTPBuilder.this.defaultContentType : ct;
        }

        public String getRequestContentType() {
            if (this.requestContentType != null) {
                return this.requestContentType;
            }
            return this.getContentType().toString();
        }

        protected void setRequestContentType(String ct) {
            this.requestContentType = ct;
        }

        protected void setAllowContentType(boolean allowContentType) {
            this.allowContentType = allowContentType;
        }

        protected void setPropertiesFromMap(Map<String, ?> args) throws URISyntaxException {
            Object path;
            Map headers;
            if (args == null) {
                return;
            }
            if (args.get("url") != null) {
                throw new IllegalArgumentException("The 'url' parameter is deprecated; use 'uri' instead");
            }
            Object uri = args.get("uri");
            if (uri == null) {
                uri = HTTPBuilder.this.defaultURI;
            }
            if (uri == null) {
                throw new IllegalStateException("Default URI is null, and no 'uri' parameter was given");
            }
            this.uri = new URIBuilder(URIBuilder.convertToURI(uri), HTTPBuilder.this.urlEncodingEnabled, HTTPBuilder.this.encoderConfig);
            Map query = (Map)args.get("params");
            if (query != null) {
                HTTPBuilder.this.log.debug("'params' argument is deprecated; use 'query' instead.");
                this.uri.setQuery(query);
            }
            if ((query = (Map)args.get("query")) != null) {
                this.uri.setQuery(query);
            }
            if ((headers = (Map)args.get("headers")) != null) {
                this.getHeaders().putAll(headers);
            }
            if ((path = args.get("path")) != null) {
                this.uri.setPath(path.toString());
            }
            if (((Boolean)args.get("allowContentType")).booleanValue()) {
                Object contentType = args.get("contentType");
                if (contentType != null) {
                    this.setContentType(contentType);
                }
                if ((contentType = args.get("requestContentType")) != null) {
                    this.setRequestContentType(contentType.toString());
                }
            } else {
                this.setContentType(null);
                this.setRequestContentType(null);
            }
            Object body = args.get("body");
            if (body != null) {
                this.setBody(this.getRequestContentType(), body);
            }
        }

        public void setHeaders(Map<?, ?> newHeaders) {
            this.headers.putAll(newHeaders);
        }

        public Map<?, ?> getHeaders() {
            return this.headers;
        }

        public void send(Object contentType, Object requestBody) {
            this.setRequestContentType(contentType.toString());
            this.setBody(requestBody);
        }

        private void setBody(Object requestBody) {
            this.setBody(this.getRequestContentType(), requestBody);
        }

        public void setBody(Object requestContentType, Object body) {
            if (!(this.request instanceof HttpEntityEnclosingRequest)) {
                throw new IllegalArgumentException("Cannot set a request body for a " + this.request.getMethod() + " method");
            }
            Closure encoder = HTTPBuilder.this.encoders.getAt(requestContentType);
            HttpEntity entity = (HttpEntity)encoder.call(requestContentType, body);
            ((HttpEntityEnclosingRequest)((Object)this.request)).setEntity(entity);
        }

        public Closure findResponseHandler(int statusCode) {
            Closure handler = this.getResponse().get(Integer.toString(statusCode));
            if (handler == null) {
                handler = this.getResponse().get(Status.find(statusCode).toString());
            }
            return handler;
        }

        public Map<Object, Closure> getResponse() {
            return this.responseHandlers;
        }

        public HttpContextDecorator getContext() {
            return this.context;
        }

        public void setContext(HttpContext ctx) {
            this.context = new HttpContextDecorator(ctx);
        }
    }
}

