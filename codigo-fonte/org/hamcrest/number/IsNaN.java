/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class IsNaN
extends TypeSafeMatcher<Double> {
    private IsNaN() {
    }

    @Override
    public boolean matchesSafely(Double item) {
        return Double.isNaN(item);
    }

    @Override
    public void describeMismatchSafely(Double item, Description mismatchDescription) {
        mismatchDescription.appendText("was ").appendValue(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a double value of NaN");
    }

    public static Matcher<Double> notANumber() {
        return new IsNaN();
    }
}

