/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.number;

import org.hamcrest.Matcher;
import org.hamcrest.comparator.ComparatorMatcherBuilder;

public class OrderingComparison {
    private OrderingComparison() {
    }

    public static <T extends Comparable<T>> Matcher<T> comparesEqualTo(T value) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().comparesEqualTo(value);
    }

    public static <T extends Comparable<T>> Matcher<T> greaterThan(T value) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().greaterThan(value);
    }

    public static <T extends Comparable<T>> Matcher<T> greaterThanOrEqualTo(T value) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().greaterThanOrEqualTo(value);
    }

    public static <T extends Comparable<T>> Matcher<T> lessThan(T value) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().lessThan(value);
    }

    public static <T extends Comparable<T>> Matcher<T> lessThanOrEqualTo(T value) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().lessThanOrEqualTo(value);
    }
}

