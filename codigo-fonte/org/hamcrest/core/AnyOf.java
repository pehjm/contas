/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.ShortcutCombination;

public class AnyOf<T>
extends ShortcutCombination<T> {
    @SafeVarargs
    public AnyOf(Matcher<? super T> ... matchers) {
        this(Arrays.asList(matchers));
    }

    public AnyOf(Iterable<Matcher<? super T>> matchers) {
        super(matchers);
    }

    @Override
    public boolean matches(Object o) {
        return this.matches(o, true);
    }

    @Override
    public void describeTo(Description description) {
        this.describeTo(description, "or");
    }

    public static <T> AnyOf<T> anyOf(Iterable<Matcher<? super T>> matchers) {
        return new AnyOf<T>(matchers);
    }

    @SafeVarargs
    public static <T> AnyOf<T> anyOf(Matcher<? super T> ... matchers) {
        return AnyOf.anyOf(Arrays.asList(matchers));
    }
}

