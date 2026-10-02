/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.DescribedAs;
import org.hamcrest.core.IsEqual;

public class IsArrayWithSize<E>
extends FeatureMatcher<E[], Integer> {
    public IsArrayWithSize(Matcher<? super Integer> sizeMatcher) {
        super(sizeMatcher, "an array with size", "array size");
    }

    @Override
    protected Integer featureValueOf(E[] actual) {
        return actual.length;
    }

    public static <E> Matcher<E[]> arrayWithSize(Matcher<? super Integer> sizeMatcher) {
        return new IsArrayWithSize<E>(sizeMatcher);
    }

    public static <E> Matcher<E[]> arrayWithSize(int size) {
        return IsArrayWithSize.arrayWithSize(IsEqual.equalTo(size));
    }

    public static <E> Matcher<E[]> emptyArray() {
        return DescribedAs.describedAs("an empty array", IsArrayWithSize.arrayWithSize(0), new Object[0]);
    }
}

