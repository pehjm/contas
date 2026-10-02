/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.number;

import java.math.BigDecimal;
import java.math.MathContext;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class BigDecimalCloseTo
extends TypeSafeMatcher<BigDecimal> {
    private final BigDecimal delta;
    private final BigDecimal value;

    public BigDecimalCloseTo(BigDecimal value, BigDecimal error) {
        this.delta = error;
        this.value = value;
    }

    @Override
    public boolean matchesSafely(BigDecimal item) {
        return this.actualDelta(item).compareTo(BigDecimal.ZERO) <= 0;
    }

    @Override
    public void describeMismatchSafely(BigDecimal item, Description mismatchDescription) {
        mismatchDescription.appendValue(item).appendText(" differed by ").appendValue(this.actualDelta(item)).appendText(" more than delta ").appendValue(this.delta);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a numeric value within ").appendValue(this.delta).appendText(" of ").appendValue(this.value);
    }

    private BigDecimal actualDelta(BigDecimal item) {
        return item.subtract(this.value, MathContext.DECIMAL128).abs().subtract(this.delta, MathContext.DECIMAL128).stripTrailingZeros();
    }

    public static Matcher<BigDecimal> closeTo(BigDecimal operand, BigDecimal error) {
        return new BigDecimalCloseTo(operand, error);
    }
}

