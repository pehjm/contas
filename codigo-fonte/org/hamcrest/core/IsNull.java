/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsNot;

public class IsNull<T>
extends BaseMatcher<T> {
    @Override
    public boolean matches(Object o) {
        return o == null;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("null");
    }

    public static Matcher<Object> nullValue() {
        return new IsNull<Object>();
    }

    public static Matcher<Object> notNullValue() {
        return IsNot.not(IsNull.nullValue());
    }

    public static <T> Matcher<T> nullValue(Class<T> type) {
        return new IsNull<T>();
    }

    public static <T> Matcher<T> notNullValue(Class<T> type) {
        return IsNot.not(IsNull.nullValue(type));
    }
}

