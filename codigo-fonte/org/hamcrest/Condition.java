/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public abstract class Condition<T> {
    public static final NotMatched<Object> NOT_MATCHED = new NotMatched();

    private Condition() {
    }

    public abstract boolean matching(Matcher<T> var1, String var2);

    public abstract <U> Condition<U> and(Step<? super T, U> var1);

    public final boolean matching(Matcher<T> match) {
        return this.matching(match, "");
    }

    public final <U> Condition<U> then(Step<? super T, U> mapping) {
        return this.and(mapping);
    }

    public static <T> Condition<T> notMatched() {
        return NOT_MATCHED;
    }

    public static <T> Condition<T> matched(T theValue, Description mismatch) {
        return new Matched(theValue, mismatch);
    }

    private static final class NotMatched<T>
    extends Condition<T> {
        private NotMatched() {
        }

        @Override
        public boolean matching(Matcher<T> match, String message) {
            return false;
        }

        @Override
        public <U> Condition<U> and(Step<? super T, U> mapping) {
            return NotMatched.notMatched();
        }
    }

    private static final class Matched<T>
    extends Condition<T> {
        private final T theValue;
        private final Description mismatch;

        private Matched(T theValue, Description mismatch) {
            this.theValue = theValue;
            this.mismatch = mismatch;
        }

        @Override
        public boolean matching(Matcher<T> matcher, String message) {
            if (matcher.matches(this.theValue)) {
                return true;
            }
            this.mismatch.appendText(message);
            matcher.describeMismatch(this.theValue, this.mismatch);
            return false;
        }

        @Override
        public <U> Condition<U> and(Step<? super T, U> next) {
            return next.apply(this.theValue, this.mismatch);
        }
    }

    public static interface Step<I, O> {
        public Condition<O> apply(I var1, Description var2);
    }
}

