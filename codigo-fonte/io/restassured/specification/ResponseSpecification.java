/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseLogSpecification;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.hamcrest.Matcher;

public interface ResponseSpecification {
    public Response validate(Response var1);

    public ResponseSpecification time(Matcher<Long> var1);

    public ResponseSpecification time(Matcher<Long> var1, TimeUnit var2);

    public ResponseSpecification body(String var1, List<Argument> var2, Matcher var3, Object ... var4);

    public ResponseSpecification body(List<Argument> var1, Matcher var2, Object ... var3);

    public ResponseSpecification statusCode(Matcher<? super Integer> var1);

    public ResponseSpecification statusCode(int var1);

    public ResponseSpecification statusLine(Matcher<? super String> var1);

    public ResponseSpecification statusLine(String var1);

    public ResponseSpecification headers(Map<String, ?> var1);

    public ResponseSpecification headers(String var1, Object var2, Object ... var3);

    public ResponseSpecification header(String var1, Matcher<?> var2);

    public <T> ResponseSpecification header(String var1, Function<String, T> var2, Matcher<? super T> var3);

    public ResponseSpecification header(String var1, String var2);

    public ResponseSpecification cookies(Map<String, ?> var1);

    public ResponseSpecification cookie(String var1);

    public ResponseSpecification cookies(String var1, Object var2, Object ... var3);

    public ResponseSpecification cookie(String var1, Matcher<?> var2);

    public ResponseSpecification cookie(String var1, DetailedCookieMatcher var2);

    public ResponseSpecification cookie(String var1, Object var2);

    public ResponseLogSpecification log();

    public ResponseSpecification rootPath(String var1, List<Argument> var2);

    @Deprecated
    default public ResponseSpecification root(String rootPath, List<Argument> arguments) {
        return this.rootPath(rootPath);
    }

    public ResponseSpecification rootPath(String var1);

    @Deprecated
    default public ResponseSpecification root(String rootPath) {
        return this.rootPath(rootPath);
    }

    public ResponseSpecification noRootPath();

    @Deprecated
    default public ResponseSpecification noRoot() {
        return this.noRootPath();
    }

    public ResponseSpecification appendRootPath(String var1);

    public ResponseSpecification appendRootPath(String var1, List<Argument> var2);

    default public ResponseSpecification appendRoot(String pathToAppend, List<Argument> arguments) {
        return this.appendRootPath(pathToAppend, arguments);
    }

    @Deprecated
    default public ResponseSpecification appendRoot(String pathToAppend) {
        return this.detachRootPath(pathToAppend);
    }

    public ResponseSpecification detachRootPath(String var1);

    @Deprecated
    default public ResponseSpecification detachRoot(String pathToDetach) {
        return this.detachRootPath(pathToDetach);
    }

    public ResponseSpecification contentType(ContentType var1);

    public ResponseSpecification contentType(String var1);

    public ResponseSpecification contentType(Matcher<? super String> var1);

    public ResponseSpecification body(Matcher<?> var1, Matcher<?> ... var2);

    public ResponseSpecification body(String var1, Matcher<?> var2, Object ... var3);

    public RequestSender when();

    public RequestSpecification given();

    public ResponseSpecification that();

    public RequestSpecification request();

    public ResponseSpecification response();

    public ResponseSpecification and();

    public RequestSpecification with();

    public ResponseSpecification then();

    public ResponseSpecification expect();

    public ResponseSpecification spec(ResponseSpecification var1);

    public ResponseSpecification parser(String var1, Parser var2);

    public ResponseSpecification defaultParser(Parser var1);

    public ResponseSpecification logDetail(LogDetail var1);

    public ResponseSpecification onFailMessage(String var1);
}

