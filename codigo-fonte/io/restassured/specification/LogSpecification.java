/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.filter.log.LogDetail;

public interface LogSpecification<T> {
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

