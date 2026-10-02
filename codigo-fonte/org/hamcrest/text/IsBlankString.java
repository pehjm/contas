/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.text;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsNull;

public final class IsBlankString
extends TypeSafeMatcher<String> {
    private static final IsBlankString BLANK_INSTANCE = new IsBlankString();
    private static final Matcher<String> NULL_OR_BLANK_INSTANCE = AnyOf.anyOf(IsNull.nullValue(), BLANK_INSTANCE);
    private static final Pattern REGEX_WHITESPACE = Pattern.compile("\\s*");

    private IsBlankString() {
    }

    @Override
    public boolean matchesSafely(String item) {
        return REGEX_WHITESPACE.matcher(item).matches();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a blank string");
    }

    public static Matcher<String> blankString() {
        return BLANK_INSTANCE;
    }

    public static Matcher<String> blankOrNullString() {
        return NULL_OR_BLANK_INSTANCE;
    }
}

