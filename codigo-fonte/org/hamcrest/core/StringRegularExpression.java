/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class StringRegularExpression
extends TypeSafeDiagnosingMatcher<String> {
    private Pattern pattern;

    protected StringRegularExpression(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string matching the pattern ").appendValue(this.pattern);
    }

    @Override
    protected boolean matchesSafely(String actual, Description mismatchDescription) {
        if (!this.pattern.matcher(actual).matches()) {
            mismatchDescription.appendText("the string was ").appendValue(actual);
            return false;
        }
        return true;
    }

    public static Matcher<String> matchesRegex(Pattern pattern) {
        return new StringRegularExpression(pattern);
    }

    public static Matcher<String> matchesRegex(String regex) {
        return StringRegularExpression.matchesRegex(Pattern.compile(regex));
    }
}

