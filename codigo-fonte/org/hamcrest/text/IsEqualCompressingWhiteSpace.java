/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.text;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsEqualCompressingWhiteSpace
extends TypeSafeMatcher<String> {
    private final String string;

    public IsEqualCompressingWhiteSpace(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Non-null value required");
        }
        this.string = string;
    }

    @Override
    public boolean matchesSafely(String item) {
        return this.stripSpaces(this.string).equals(this.stripSpaces(item));
    }

    @Override
    public void describeMismatchSafely(String item, Description mismatchDescription) {
        mismatchDescription.appendText("was ").appendValue(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string equal to ").appendValue(this.string).appendText(" compressing white space");
    }

    public String stripSpaces(String toBeStripped) {
        return toBeStripped.replaceAll("\\s+", " ").trim();
    }

    public static Matcher<String> equalToIgnoringWhiteSpace(String expectedString) {
        return new IsEqualCompressingWhiteSpace(expectedString);
    }

    public static Matcher<String> equalToCompressingWhiteSpace(String expectedString) {
        return new IsEqualCompressingWhiteSpace(expectedString);
    }
}

