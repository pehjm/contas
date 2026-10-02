/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.comparator;

import java.util.Comparator;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class ComparatorMatcherBuilder<T> {
    private final Comparator<T> comparator;
    private final boolean includeComparatorInDescription;

    public static <T extends Comparable<T>> ComparatorMatcherBuilder<T> usingNaturalOrdering() {
        return new ComparatorMatcherBuilder<T>(new Comparator<T>(){

            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        }, false);
    }

    public static <T> ComparatorMatcherBuilder<T> comparedBy(Comparator<T> comparator) {
        return new ComparatorMatcherBuilder<T>(comparator, true);
    }

    private ComparatorMatcherBuilder(Comparator<T> comparator, boolean includeComparatorInDescription) {
        this.comparator = comparator;
        this.includeComparatorInDescription = includeComparatorInDescription;
    }

    public Matcher<T> comparesEqualTo(T value) {
        return new ComparatorMatcher(this.comparator, value, 0, 0, this.includeComparatorInDescription);
    }

    public Matcher<T> greaterThan(T value) {
        return new ComparatorMatcher(this.comparator, value, 1, 1, this.includeComparatorInDescription);
    }

    public Matcher<T> greaterThanOrEqualTo(T value) {
        return new ComparatorMatcher(this.comparator, value, 0, 1, this.includeComparatorInDescription);
    }

    public Matcher<T> lessThan(T value) {
        return new ComparatorMatcher(this.comparator, value, -1, -1, this.includeComparatorInDescription);
    }

    public Matcher<T> lessThanOrEqualTo(T value) {
        return new ComparatorMatcher(this.comparator, value, -1, 0, this.includeComparatorInDescription);
    }

    private static final class ComparatorMatcher<T>
    extends TypeSafeMatcher<T> {
        private static final int LESS_THAN = -1;
        private static final int GREATER_THAN = 1;
        private static final int EQUAL = 0;
        private final Comparator<T> comparator;
        private final T expected;
        private final int minCompare;
        private final int maxCompare;
        private final boolean includeComparatorInDescription;
        private static final String[] comparisonDescriptions = new String[]{"less than", "equal to", "greater than"};

        private ComparatorMatcher(Comparator<T> comparator, T expected, int minCompare, int maxCompare, boolean includeComparatorInDescription) {
            this.comparator = comparator;
            this.expected = expected;
            this.minCompare = minCompare;
            this.maxCompare = maxCompare;
            this.includeComparatorInDescription = includeComparatorInDescription;
        }

        @Override
        public boolean matchesSafely(T actual) {
            try {
                int compare = Integer.signum(this.comparator.compare(actual, this.expected));
                return this.minCompare <= compare && compare <= this.maxCompare;
            }
            catch (ClassCastException e) {
                return false;
            }
        }

        @Override
        public void describeMismatchSafely(T actual, Description mismatchDescription) {
            mismatchDescription.appendValue(actual).appendText(" was ").appendText(ComparatorMatcher.asText(this.comparator.compare(actual, this.expected))).appendText(" ").appendValue(this.expected);
            if (this.includeComparatorInDescription) {
                mismatchDescription.appendText(" when compared by ").appendValue(this.comparator);
            }
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("a value ").appendText(ComparatorMatcher.asText(this.minCompare));
            if (this.minCompare != this.maxCompare) {
                description.appendText(" or ").appendText(ComparatorMatcher.asText(this.maxCompare));
            }
            description.appendText(" ").appendValue(this.expected);
            if (this.includeComparatorInDescription) {
                description.appendText(" when compared by ").appendValue(this.comparator);
            }
        }

        private static String asText(int comparison) {
            return comparisonDescriptions[Integer.signum(comparison) + 1];
        }
    }
}

