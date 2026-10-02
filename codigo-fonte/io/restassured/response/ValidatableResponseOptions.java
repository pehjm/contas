/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.response;

import io.restassured.http.ContentType;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.parsing.Parser;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponseLogSpec;
import io.restassured.specification.Argument;
import io.restassured.specification.ResponseSpecification;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.hamcrest.Matcher;

public interface ValidatableResponseOptions<T extends ValidatableResponseOptions<T, R>, R extends ResponseBody<R> & ResponseOptions<R>> {
    public T body(String var1, List<Argument> var2, Matcher var3, Object ... var4);

    public T body(List<Argument> var1, Matcher var2, Object ... var3);

    public T body(List<Argument> var1, ResponseAwareMatcher<R> var2);

    public T statusCode(Matcher<? super Integer> var1);

    public T statusCode(int var1);

    public T statusLine(Matcher<? super String> var1);

    public T statusLine(String var1);

    public T headers(Map<String, ?> var1);

    public T headers(String var1, Object var2, Object ... var3);

    public T header(String var1, Matcher<?> var2);

    public T header(String var1, ResponseAwareMatcher<R> var2);

    public <V> T header(String var1, Function<String, V> var2, Matcher<? super V> var3);

    public T header(String var1, String var2);

    public T cookies(Map<String, ?> var1);

    public T cookie(String var1);

    public T cookies(String var1, Object var2, Object ... var3);

    public T cookie(String var1, Matcher<?> var2);

    public T cookie(String var1, DetailedCookieMatcher var2);

    public T cookie(String var1, Object var2);

    public T rootPath(String var1);

    public T rootPath(String var1, List<Argument> var2);

    @Deprecated
    public T root(String var1, List<Argument> var2);

    @Deprecated
    public T root(String var1);

    @Deprecated
    public T noRoot();

    public T noRootPath();

    public T appendRootPath(String var1);

    @Deprecated
    default public T appendRoot(String pathToAppend) {
        return this.appendRootPath(pathToAppend);
    }

    public T appendRootPath(String var1, List<Argument> var2);

    @Deprecated
    default public T appendRoot(String pathToAppend, List<Argument> arguments) {
        return this.appendRootPath(pathToAppend, arguments);
    }

    public T detachRootPath(String var1);

    @Deprecated
    default public T detachRoot(String pathToDetach) {
        return this.detachRootPath(pathToDetach);
    }

    public T contentType(ContentType var1);

    public T contentType(String var1);

    public T contentType(Matcher<? super String> var1);

    public T body(Matcher<?> var1, Matcher<?> ... var2);

    public T body(String var1, List<Argument> var2, ResponseAwareMatcher<R> var3);

    public T body(String var1, ResponseAwareMatcher<R> var2);

    public T body(String var1, Matcher<?> var2, Object ... var3);

    public T and();

    public T using();

    public T assertThat();

    public T spec(ResponseSpecification var1);

    public T parser(String var1, Parser var2);

    public T defaultParser(Parser var1);

    public ExtractableResponse<R> extract();

    public ValidatableResponseLogSpec<T, R> log();

    public T time(Matcher<Long> var1);

    public T time(Matcher<Long> var1, TimeUnit var2);

    public T onFailMessage(String var1);
}

