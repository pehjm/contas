/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

public class AllOf<T>
extends DiagnosingMatcher<T> {
    private final Iterable<Matcher<? super T>> matchers;

    @SafeVarargs
    public AllOf(Matcher<? super T> ... matchers) {
        this(Arrays.asList(matchers));
    }

    public AllOf(Iterable<Matcher<? super T>> matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Object o, Description mismatch) {
        for (Matcher<T> matcher : this.matchers) {
            if (matcher.matches(o)) continue;
            mismatch.appendDescriptionOf(matcher).appendText(" ");
            matcher.describeMismatch(o, mismatch);
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendList("(", " and ", ")", this.matchers);
    }

    public static <T> Matcher<T> allOf(Iterable<Matcher<? super T>> matchers) {
        return new AllOf<T>(matchers);
    }

    @SafeVarargs
    public static <T> Matcher<T> allOf(Matcher<? super T> ... matchers) {
        return AllOf.allOf(Arrays.asList(matchers));
    }
}

