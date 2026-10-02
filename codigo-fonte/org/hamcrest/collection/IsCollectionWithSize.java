/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.Collection;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

public class IsCollectionWithSize<E>
extends FeatureMatcher<Collection<? extends E>, Integer> {
    public IsCollectionWithSize(Matcher<? super Integer> sizeMatcher) {
        super(sizeMatcher, "a collection with size", "collection size");
    }

    @Override
    protected Integer featureValueOf(Collection<? extends E> actual) {
        return actual.size();
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(Matcher<? super Integer> sizeMatcher) {
        return new IsCollectionWithSize<E>(sizeMatcher);
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(int size) {
        return IsCollectionWithSize.hasSize(IsEqual.equalTo(size));
    }
}

