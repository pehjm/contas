/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.core.IsEqual;

public class IsArrayContainingInOrder<E>
extends TypeSafeMatcher<E[]> {
    private final Collection<Matcher<? super E>> matchers;
    private final IsIterableContainingInOrder<E> iterableMatcher;

    public IsArrayContainingInOrder(List<Matcher<? super E>> matchers) {
        this.iterableMatcher = new IsIterableContainingInOrder<E>(matchers);
        this.matchers = matchers;
    }

    @Override
    public boolean matchesSafely(E[] item) {
        return this.iterableMatcher.matches(Arrays.asList(item));
    }

    @Override
    public void describeMismatchSafely(E[] item, Description mismatchDescription) {
        this.iterableMatcher.describeMismatch(Arrays.asList(item), mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendList("[", ", ", "]", this.matchers);
    }

    public static <E> Matcher<E[]> arrayContaining(E ... items) {
        ArrayList<Matcher<Matcher<E>>> matchers = new ArrayList<Matcher<Matcher<E>>>();
        for (E item : items) {
            matchers.add(IsEqual.equalTo(item));
        }
        return IsArrayContainingInOrder.arrayContaining(matchers);
    }

    public static <E> Matcher<E[]> arrayContaining(Matcher<? super E> ... itemMatchers) {
        return IsArrayContainingInOrder.arrayContaining(Arrays.asList(itemMatchers));
    }

    public static <E> Matcher<E[]> arrayContaining(List<Matcher<? super E>> itemMatchers) {
        return new IsArrayContainingInOrder<E>(itemMatchers);
    }
}

