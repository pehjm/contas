/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.Closure;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.internal.RestAssuredHttpBuilderGroovyHelper;
import io.restassured.internal.http.HTTPBuilder;
import io.restassured.internal.http.HttpResponseContentTypeFinder;
import io.restassured.internal.http.HttpResponseDecorator;
import io.restassured.internal.http.ResponseParseException;
import io.restassured.internal.http.Status;
import io.restassured.internal.util.SafeExceptionRethrower;
import io.restassured.parsing.Parser;
import io.restassured.specification.FilterableResponseSpecification;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

class RestAssuredHttpBuilder
extends HTTPBuilder {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String MULTIPART = "multipart";
    private static final String MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH = "multipart/";
    private Map<String, String> queryParameters;
    private Headers requestHeaders;
    private RestAssuredConfig config;
    private boolean allowContentType;
    private Parser parser;
    FilterableResponseSpecification responseSpecification;
    Object assertionClosure;

    RestAssuredHttpBuilder(FilterableResponseSpecification responseSpecification, Headers requestHeaders, LinkedHashMap<String, String> queryParameters, Object defaultURI, Object assertionClosure, boolean urlEncodingEnabled, RestAssuredConfig config, AbstractHttpClient client, boolean allowContentType, Parser parser) {
        super(defaultURI, urlEncodingEnabled, RestAssuredHttpBuilder.orNull(config, RestAssuredConfig::getEncoderConfig), RestAssuredHttpBuilder.orNull(config, RestAssuredConfig::getDecoderConfig), RestAssuredHttpBuilder.orNull(config, RestAssuredConfig::getOAuthConfig), client);
        this.responseSpecification = responseSpecification;
        this.requestHeaders = requestHeaders;
        this.queryParameters = queryParameters;
        this.assertionClosure = assertionClosure;
        this.config = config;
        this.allowContentType = allowContentType;
        this.parser = parser;
    }

    private static <E, T> T orNull(E instance, Function<E, T> s) {
        return instance == null ? null : (T)s.apply(instance);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected Object doRequest(HTTPBuilder.RequestConfigDelegate delegate) throws IOException {
        if (delegate.getRequest() instanceof HttpPost) {
            if (this.assertionClosure != null) {
                Closure closureThatCallsAssertionClosure = RestAssuredHttpBuilderGroovyHelper.createClosureThatCalls(this.assertionClosure);
                delegate.getResponse().put(Status.FAILURE.toString(), closureThatCallsAssertionClosure);
            }
            try {
                delegate.uri.setQuery(this.queryParameters);
            }
            catch (URISyntaxException e) {
                return SafeExceptionRethrower.safeRethrow(e);
            }
        }
        HttpRequestBase reqMethod = delegate.getRequest();
        Object acceptContentType = delegate.getContentType();
        if (!this.requestHeaders.hasHeaderWithName("Accept")) {
            String acceptContentTypes = acceptContentType.toString();
            if (acceptContentType instanceof ContentType) {
                acceptContentTypes = ((ContentType)((Object)acceptContentType)).getAcceptHeader();
            }
            reqMethod.setHeader("Accept", acceptContentTypes);
        }
        reqMethod.setURI(delegate.getUri().toURI());
        if (this.shouldApplyContentTypeFromRestAssuredConfigDelegate(delegate, reqMethod)) {
            String contentTypeToUse = StringUtils.trim(delegate.getRequestContentType());
            reqMethod.setHeader(CONTENT_TYPE, contentTypeToUse);
        }
        if (reqMethod.getURI() == null) {
            throw new IllegalStateException("Request URI cannot be null");
        }
        Map<?, ?> headers1 = delegate.getHeaders();
        for (Object key : headers1.keySet()) {
            if (key == null) continue;
            Object val = headers1.get(key);
            if (val == null) {
                reqMethod.removeHeaders(key.toString());
                continue;
            }
            if (key.toString().equalsIgnoreCase(CONTENT_TYPE) && !this.allowContentType) {
                reqMethod.removeHeaders(key.toString());
                continue;
            }
            if (key.toString().equalsIgnoreCase(CONTENT_TYPE) && val.toString().startsWith(MULTIPART_CONTENT_TYPE_PREFIX_WITH_SLASH)) continue;
            String keyAsString = key.toString();
            if (val instanceof Collection) {
                Collection<String> flattened = RestAssuredHttpBuilderGroovyHelper.flattenToString((Collection)val);
                flattened.forEach(it -> reqMethod.addHeader(keyAsString, (String)it));
                continue;
            }
            reqMethod.setHeader(keyAsString, val.toString());
        }
        HttpResponseDecorator resp = new HttpResponseDecorator(this.client.execute((HttpUriRequest)reqMethod, delegate.getContext()), delegate.getContext(), null);
        try {
            Object returnVal;
            int status = resp.getStatusLine().getStatusCode();
            Closure responseClosure = delegate.findResponseHandler(status);
            switch (responseClosure.getMaximumNumberOfParameters()) {
                case 1: {
                    returnVal = responseClosure.call((Object)resp);
                    break;
                }
                case 2: {
                    HttpEntity entity = resp.getEntity();
                    try {
                        if (entity == null || entity.getContentLength() == 0L) {
                            returnVal = responseClosure.call(resp, "");
                            break;
                        }
                        returnVal = responseClosure.call(resp, this.parseResponse(resp, acceptContentType));
                        break;
                    }
                    catch (Exception ex) {
                        throw new ResponseParseException(resp, (Throwable)ex);
                    }
                }
                default: {
                    throw new IllegalArgumentException("Response closure must accept one or two parameters");
                }
            }
            Object v = returnVal;
            return v;
        }
        finally {
            ConnectionConfig connectionConfig;
            HttpEntity entity;
            if (this.responseSpecification instanceof ResponseSpecificationImpl && ((ResponseSpecificationImpl)this.responseSpecification).hasBodyAssertionsDefined() && (entity = resp.getEntity()) != null) {
                EntityUtils.consumeQuietly(entity);
            }
            if ((connectionConfig = this.connectionConfig()).shouldCloseIdleConnectionsAfterEachResponse()) {
                ConnectionConfig.CloseIdleConnectionConfig closeConnectionConfig = connectionConfig.closeIdleConnectionConfig();
                this.client.getConnectionManager().closeIdleConnections(closeConnectionConfig.getIdleTime(), closeConnectionConfig.getTimeUnit());
            }
        }
    }

    private boolean doesntHaveEntity(HttpRequestBase reqMethod) {
        if (!(reqMethod instanceof HttpEntityEnclosingRequestBase)) {
            return true;
        }
        HttpEntity entity = ((HttpEntityEnclosingRequestBase)reqMethod).getEntity();
        return entity == null || entity.getContentType() == null;
    }

    private boolean shouldApplyContentTypeFromRestAssuredConfigDelegate(HTTPBuilder.RequestConfigDelegate delegate, HttpRequestBase reqMethod) {
        String requestContentType = delegate.getRequestContentType();
        return this.allowContentType && requestContentType != null && !requestContentType.equals(ContentType.ANY.toString()) && this.doesntHaveEntity(reqMethod) && Arrays.stream(reqMethod.getAllHeaders()).noneMatch(header -> header.getName().equalsIgnoreCase(CONTENT_TYPE));
    }

    protected Object parseResponse(HttpResponse resp, Object contentType) throws IOException {
        block3: {
            if (this.parser != null && ContentType.ANY.toString().equals(contentType.toString())) {
                try {
                    HttpResponseContentTypeFinder.findContentType(resp);
                }
                catch (IllegalArgumentException ignored) {
                    HttpEntity entity = resp.getEntity();
                    if (entity == null) break block3;
                    resp.setEntity(new HttpEntityWrapper(entity){

                        @Override
                        public Header getContentType() {
                            return new BasicHeader(RestAssuredHttpBuilder.CONTENT_TYPE, RestAssuredHttpBuilder.this.parser.getContentType());
                        }
                    });
                }
            }
        }
        return super.parseResponse(resp, contentType);
    }

    private ConnectionConfig connectionConfig() {
        return this.config == null ? ConnectionConfig.connectionConfig() : this.config.getConnectionConfig();
    }
}

