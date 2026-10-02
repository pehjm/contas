/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsIterableContaining;

public class HasItemInArray<T>
extends TypeSafeMatcher<T[]> {
    private final Matcher<? super T> elementMatcher;
    private final TypeSafeDiagnosingMatcher<Iterable<? super T>> collectionMatcher;

    public HasItemInArray(Matcher<? super T> elementMatcher) {
        this.elementMatcher = elementMatcher;
        this.collectionMatcher = new IsIterableContaining<T>(elementMatcher);
    }

    @Override
    public boolean matchesSafely(T[] actual) {
        return this.collectionMatcher.matches(Arrays.asList(actual));
    }

    @Override
    public void describeMismatchSafely(T[] actual, Description mismatchDescription) {
        this.collectionMatcher.describeMismatch(Arrays.asList(actual), mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an array containing ").appendDescriptionOf(this.elementMatcher);
    }
}

