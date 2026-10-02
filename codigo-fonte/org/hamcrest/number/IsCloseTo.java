/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsCloseTo
extends TypeSafeMatcher<Double> {
    private final double delta;
    private final double value;

    public IsCloseTo(double value, double error) {
        this.delta = error;
        this.value = value;
    }

    @Override
    public boolean matchesSafely(Double item) {
        return this.actualDelta(item) <= 0.0;
    }

    @Override
    public void describeMismatchSafely(Double item, Description mismatchDescription) {
        mismatchDescription.appendValue(item).appendText(" differed by ").appendValue(this.actualDelta(item)).appendText(" more than delta ").appendValue(this.delta);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a numeric value within ").appendValue(this.delta).appendText(" of ").appendValue(this.value);
    }

    private double actualDelta(Double item) {
        return Math.abs(item - this.value) - this.delta;
    }

    public static Matcher<Double> closeTo(double operand, double error) {
        return new IsCloseTo(operand, error);
    }
}

