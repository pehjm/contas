/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.Iterator;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

public class IsIterableWithSize<E>
extends FeatureMatcher<Iterable<E>, Integer> {
    public IsIterableWithSize(Matcher<? super Integer> sizeMatcher) {
        super(sizeMatcher, "an iterable with size", "iterable size");
    }

    @Override
    protected Integer featureValueOf(Iterable<E> actual) {
        int size = 0;
        Iterator<E> iterator = actual.iterator();
        while (iterator.hasNext()) {
            ++size;
            iterator.next();
        }
        return size;
    }

    public static <E> Matcher<Iterable<E>> iterableWithSize(Matcher<? super Integer> sizeMatcher) {
        return new IsIterableWithSize<E>(sizeMatcher);
    }

    public static <E> Matcher<Iterable<E>> iterableWithSize(int size) {
        return IsIterableWithSize.iterableWithSize(IsEqual.equalTo(size));
    }
}

