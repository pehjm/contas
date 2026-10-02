/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsEqual;

public class IsIterableContainingInAnyOrder<T>
extends TypeSafeDiagnosingMatcher<Iterable<? extends T>> {
    private final Collection<Matcher<? super T>> matchers;

    public IsIterableContainingInAnyOrder(Collection<Matcher<? super T>> matchers) {
        this.matchers = matchers;
    }

    @Override
    protected boolean matchesSafely(Iterable<? extends T> items, Description mismatchDescription) {
        Matching<T> matching = new Matching<T>(this.matchers, mismatchDescription);
        for (T item : items) {
            if (matching.matches(item)) continue;
            return false;
        }
        return matching.isFinished(items);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("iterable with items ").appendList("[", ", ", "]", this.matchers).appendText(" in any order");
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(Matcher<? super T> ... itemMatchers) {
        return IsIterableContainingInAnyOrder.containsInAnyOrder(Arrays.asList(itemMatchers));
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(T ... items) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>();
        for (T item : items) {
            matchers.add(IsEqual.equalTo(item));
        }
        return new IsIterableContainingInAnyOrder<T>(matchers);
    }

    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(Collection<Matcher<? super T>> itemMatchers) {
        return new IsIterableContainingInAnyOrder<T>(itemMatchers);
    }

    private static class Matching<S> {
        private final Collection<Matcher<? super S>> matchers;
        private final Description mismatchDescription;

        public Matching(Collection<Matcher<? super S>> matchers, Description mismatchDescription) {
            this.matchers = new ArrayList<Matcher<S>>(matchers);
            this.mismatchDescription = mismatchDescription;
        }

        public boolean matches(S item) {
            if (this.matchers.isEmpty()) {
                this.mismatchDescription.appendText("no match for: ").appendValue(item);
                return false;
            }
            return this.isMatched(item);
        }

        public boolean isFinished(Iterable<? extends S> items) {
            if (this.matchers.isEmpty()) {
                return true;
            }
            this.mismatchDescription.appendText("no item matches: ").appendList("", ", ", "", this.matchers).appendText(" in ").appendValueList("[", ", ", "]", items);
            return false;
        }

        private boolean isMatched(S item) {
            for (Matcher<S> matcher : this.matchers) {
                if (!matcher.matches(item)) continue;
                this.matchers.remove(matcher);
                return true;
            }
            this.mismatchDescription.appendText("not matched: ").appendValue(item);
            return false;
        }
    }
}

