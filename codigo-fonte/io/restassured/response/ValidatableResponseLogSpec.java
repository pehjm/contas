/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.response;

import io.restassured.filter.log.LogDetail;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponseOptions;
import org.hamcrest.Matcher;

public interface ValidatableResponseLogSpec<T extends ValidatableResponseOptions<T, R>, R extends ResponseBody<R> & ResponseOptions<R>> {
    public T status();

    public T ifError();

    public T ifStatusCodeIsEqualTo(int var1);

    public T ifStatusCodeMatches(Matcher<Integer> var1);

    public T body();

    public T body(boolean var1);

    public T all();

    public T all(boolean var1);

    public T everything();

    public T everything(boolean var1);

    public T headers();

    public T cookies();

    public T ifValidationFails();

    public T ifValidationFails(LogDetail var1);

    public T ifValidationFails(LogDetail var1, boolean var2);
}

