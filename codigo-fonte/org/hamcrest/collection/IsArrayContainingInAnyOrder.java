/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.core.IsEqual;

@Deprecated
public class IsArrayContainingInAnyOrder<E>
extends TypeSafeMatcher<E[]> {
    private final IsIterableContainingInAnyOrder<E> iterableMatcher;
    private final Collection<Matcher<? super E>> matchers;

    public IsArrayContainingInAnyOrder(Collection<Matcher<? super E>> matchers) {
        this.iterableMatcher = new IsIterableContainingInAnyOrder(matchers);
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
        description.appendList("[", ", ", "]", this.matchers).appendText(" in any order");
    }

    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Matcher<? super E> ... itemMatchers) {
        return IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(Arrays.asList(itemMatchers));
    }

    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Collection<Matcher<? super E>> itemMatchers) {
        return new IsArrayContainingInAnyOrder<E>(itemMatchers);
    }

    public static <E> Matcher<E[]> arrayContainingInAnyOrder(E ... items) {
        ArrayList<Matcher<Matcher<E>>> matchers = new ArrayList<Matcher<Matcher<E>>>();
        for (E item : items) {
            matchers.add(IsEqual.equalTo(item));
        }
        return new IsArrayContainingInAnyOrder<E>(matchers);
    }
}

