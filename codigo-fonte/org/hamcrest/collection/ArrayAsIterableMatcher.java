/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.Arrays;
import java.util.Collection;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.TypeSafeMatcher;

public class ArrayAsIterableMatcher<E>
extends TypeSafeMatcher<E[]> {
    protected final TypeSafeDiagnosingMatcher<Iterable<? extends E>> iterableMatcher;
    private final String message;
    protected final Collection<Matcher<? super E>> matchers;

    public ArrayAsIterableMatcher(TypeSafeDiagnosingMatcher<Iterable<? extends E>> iterableMatcher, Collection<Matcher<? super E>> matchers, String message) {
        this.matchers = matchers;
        this.iterableMatcher = iterableMatcher;
        this.message = message;
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
        description.appendList("[", ", ", "]", this.matchers).appendText(" ").appendText(this.message);
    }
}

