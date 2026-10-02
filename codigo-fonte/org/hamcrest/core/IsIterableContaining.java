/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import java.util.ArrayList;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsEqual;

public class IsIterableContaining<T>
extends TypeSafeDiagnosingMatcher<Iterable<? super T>> {
    private final Matcher<? super T> elementMatcher;

    public IsIterableContaining(Matcher<? super T> elementMatcher) {
        this.elementMatcher = elementMatcher;
    }

    @Override
    protected boolean matchesSafely(Iterable<? super T> collection, Description mismatchDescription) {
        if (this.isEmpty(collection)) {
            mismatchDescription.appendText("was empty");
            return false;
        }
        for (T item : collection) {
            if (!this.elementMatcher.matches(item)) continue;
            return true;
        }
        mismatchDescription.appendText("mismatches were: [");
        boolean isPastFirst = false;
        for (T item : collection) {
            if (isPastFirst) {
                mismatchDescription.appendText(", ");
            }
            this.elementMatcher.describeMismatch(item, mismatchDescription);
            isPastFirst = true;
        }
        mismatchDescription.appendText("]");
        return false;
    }

    private boolean isEmpty(Iterable<? super T> iterable) {
        return !iterable.iterator().hasNext();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a collection containing ").appendDescriptionOf(this.elementMatcher);
    }

    public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> itemMatcher) {
        return new IsIterableContaining<T>(itemMatcher);
    }

    public static <T> Matcher<Iterable<? super T>> hasItem(T item) {
        return new IsIterableContaining<T>(IsEqual.equalTo(item));
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T> ... itemMatchers) {
        ArrayList all = new ArrayList(itemMatchers.length);
        for (Matcher<? super T> elementMatcher : itemMatchers) {
            all.add(new IsIterableContaining<T>(elementMatcher));
        }
        return AllOf.allOf(all);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> hasItems(T ... items) {
        ArrayList all = new ArrayList(items.length);
        for (T item : items) {
            all.add(IsIterableContaining.hasItem(item));
        }
        return AllOf.allOf(all);
    }
}

