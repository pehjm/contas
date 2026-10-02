/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.object;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

public class HasToString<T>
extends FeatureMatcher<T, String> {
    public HasToString(Matcher<? super String> toStringMatcher) {
        super(toStringMatcher, "with toString()", "toString()");
    }

    @Override
    protected String featureValueOf(T actual) {
        return String.valueOf(actual);
    }

    public static <T> Matcher<T> hasToString(Matcher<? super String> toStringMatcher) {
        return new HasToString<T>(toStringMatcher);
    }

    public static <T> Matcher<T> hasToString(String expectedToString) {
        return new HasToString<T>(IsEqual.equalTo(expectedToString));
    }
}

