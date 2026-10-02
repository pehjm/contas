/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RedirectSpecification;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSender;
import io.restassured.specification.ResponseSpecification;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RequestSpecification
extends RequestSender {
    public RequestSpecification body(String var1);

    public RequestSpecification body(byte[] var1);

    public RequestSpecification body(File var1);

    public RequestSpecification body(InputStream var1);

    public RequestSpecification body(Object var1);

    public RequestSpecification body(Object var1, ObjectMapper var2);

    public RequestSpecification body(Object var1, ObjectMapperType var2);

    public RedirectSpecification redirects();

    public RequestSpecification cookies(String var1, Object var2, Object ... var3);

    public RequestSpecification cookies(Map<String, ?> var1);

    public RequestSpecification cookies(Cookies var1);

    public RequestSpecification cookie(String var1, Object var2, Object ... var3);

    public RequestSpecification cookie(String var1);

    public RequestSpecification cookie(Cookie var1);

    public RequestSpecification params(String var1, Object var2, Object ... var3);

    public RequestSpecification params(Map<String, ?> var1);

    public RequestSpecification param(String var1, Object ... var2);

    public RequestSpecification param(String var1, Collection<?> var2);

    public RequestSpecification queryParams(String var1, Object var2, Object ... var3);

    public RequestSpecification queryParams(Map<String, ?> var1);

    public RequestSpecification queryParam(String var1, Object ... var2);

    public RequestSpecification queryParam(String var1, Collection<?> var2);

    public RequestSpecification formParams(String var1, Object var2, Object ... var3);

    public RequestSpecification formParams(Map<String, ?> var1);

    public RequestSpecification formParam(String var1, Object ... var2);

    public RequestSpecification formParam(String var1, Collection<?> var2);

    public RequestSpecification pathParam(String var1, Object var2);

    public RequestSpecification pathParams(String var1, Object var2, Object ... var3);

    public RequestSpecification pathParams(Map<String, ?> var1);

    public RequestSpecification config(RestAssuredConfig var1);

    public RequestSpecification keyStore(String var1, String var2);

    public RequestSpecification keyStore(File var1, String var2);

    public RequestSpecification trustStore(String var1, String var2);

    public RequestSpecification trustStore(File var1, String var2);

    public RequestSpecification trustStore(KeyStore var1);

    public RequestSpecification keyStore(KeyStore var1);

    public RequestSpecification relaxedHTTPSValidation();

    public RequestSpecification relaxedHTTPSValidation(String var1);

    public RequestSpecification headers(String var1, Object var2, Object ... var3);

    public RequestSpecification headers(Map<String, ?> var1);

    public RequestSpecification headers(Headers var1);

    public RequestSpecification header(String var1, Object var2, Object ... var3);

    public RequestSpecification header(Header var1);

    public RequestSpecification contentType(ContentType var1);

    public RequestSpecification contentType(String var1);

    public RequestSpecification noContentType();

    public RequestSpecification accept(ContentType var1);

    public RequestSpecification accept(String var1);

    public RequestSpecification multiPart(MultiPartSpecification var1);

    public RequestSpecification multiPart(File var1);

    public RequestSpecification multiPart(String var1, File var2);

    public RequestSpecification multiPart(String var1, File var2, String var3);

    public RequestSpecification multiPart(String var1, Object var2);

    public RequestSpecification multiPart(String var1, Object var2, String var3);

    public RequestSpecification multiPart(String var1, String var2, Object var3, String var4);

    public RequestSpecification multiPart(String var1, String var2, byte[] var3);

    public RequestSpecification multiPart(String var1, String var2, byte[] var3, String var4);

    public RequestSpecification multiPart(String var1, String var2, InputStream var3);

    public RequestSpecification multiPart(String var1, String var2, InputStream var3, String var4);

    public RequestSpecification multiPart(String var1, String var2);

    public RequestSpecification multiPart(String var1, String var2, String var3);

    public AuthenticationSpecification auth();

    public RequestSpecification csrf(String var1);

    public RequestSpecification csrf(String var1, String var2);

    public RequestSpecification disableCsrf();

    public RequestSpecification port(int var1);

    public RequestSpecification spec(RequestSpecification var1);

    public RequestSpecification sessionId(String var1);

    public RequestSpecification sessionId(String var1, String var2);

    public RequestSpecification urlEncodingEnabled(boolean var1);

    public RequestSpecification filter(Filter var1);

    public RequestSpecification filters(List<Filter> var1);

    public RequestSpecification filters(Filter var1, Filter ... var2);

    public RequestSpecification noFilters();

    public <T extends Filter> RequestSpecification noFiltersOfType(Class<T> var1);

    public RequestLogSpecification log();

    public ResponseSpecification response();

    public RequestSpecification and();

    public RequestSpecification with();

    public ResponseSpecification then();

    public ResponseSpecification expect();

    public RequestSpecification when();

    public RequestSpecification given();

    public RequestSpecification that();

    public RequestSpecification request();

    public RequestSpecification baseUri(String var1);

    public RequestSpecification basePath(String var1);

    public RequestSpecification proxy(String var1, int var2);

    public RequestSpecification proxy(String var1);

    public RequestSpecification proxy(int var1);

    public RequestSpecification proxy(String var1, int var2, String var3);

    public RequestSpecification proxy(URI var1);

    public RequestSpecification proxy(ProxySpecification var1);
}

