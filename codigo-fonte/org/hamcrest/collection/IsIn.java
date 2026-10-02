/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.Arrays;
import java.util.Collection;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsIn<T>
extends BaseMatcher<T> {
    private final Collection<T> collection;

    public IsIn(Collection<T> collection) {
        this.collection = collection;
    }

    public IsIn(T[] elements) {
        this.collection = Arrays.asList(elements);
    }

    @Override
    public boolean matches(Object o) {
        return this.collection.contains(o);
    }

    @Override
    public void describeTo(Description buffer) {
        buffer.appendText("one of ");
        buffer.appendValueList("{", ", ", "}", this.collection);
    }

    @Deprecated
    public static <T> Matcher<T> isIn(Collection<T> collection) {
        return IsIn.in(collection);
    }

    public static <T> Matcher<T> in(Collection<T> collection) {
        return new IsIn<T>(collection);
    }

    @Deprecated
    public static <T> Matcher<T> isIn(T[] elements) {
        return IsIn.in(elements);
    }

    public static <T> Matcher<T> in(T[] elements) {
        return new IsIn<T>(elements);
    }

    @SafeVarargs
    @Deprecated
    public static <T> Matcher<T> isOneOf(T ... elements) {
        return IsIn.oneOf(elements);
    }

    @SafeVarargs
    public static <T> Matcher<T> oneOf(T ... elements) {
        return IsIn.in(elements);
    }
}

