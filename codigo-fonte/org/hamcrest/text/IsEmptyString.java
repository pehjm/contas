/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.text;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsNull;

public final class IsEmptyString
extends TypeSafeMatcher<String> {
    private static final IsEmptyString INSTANCE = new IsEmptyString();
    private static final Matcher<String> NULL_OR_EMPTY_INSTANCE = AnyOf.anyOf(IsNull.nullValue(), INSTANCE);

    private IsEmptyString() {
    }

    @Override
    public boolean matchesSafely(String item) {
        return item.equals("");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an empty string");
    }

    @Deprecated
    public static Matcher<String> isEmptyString() {
        return IsEmptyString.emptyString();
    }

    public static Matcher<String> emptyString() {
        return INSTANCE;
    }

    @Deprecated
    public static Matcher<String> isEmptyOrNullString() {
        return IsEmptyString.emptyOrNullString();
    }

    public static Matcher<String> emptyOrNullString() {
        return NULL_OR_EMPTY_INSTANCE;
    }
}

