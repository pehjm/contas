/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsEqual;

public class IsIterableContainingInRelativeOrder<E>
extends TypeSafeDiagnosingMatcher<Iterable<? extends E>> {
    private final List<Matcher<? super E>> matchers;

    public IsIterableContainingInRelativeOrder(List<Matcher<? super E>> matchers) {
        this.matchers = matchers;
    }

    @Override
    protected boolean matchesSafely(Iterable<? extends E> iterable, Description mismatchDescription) {
        MatchSeriesInRelativeOrder<? extends E> matchSeriesInRelativeOrder = new MatchSeriesInRelativeOrder<E>(this.matchers, mismatchDescription);
        matchSeriesInRelativeOrder.processItems(iterable);
        return matchSeriesInRelativeOrder.isFinished();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("iterable containing ").appendList("[", ", ", "]", this.matchers).appendText(" in relative order");
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> containsInRelativeOrder(E ... items) {
        ArrayList<Matcher<Matcher<E>>> matchers = new ArrayList<Matcher<Matcher<E>>>();
        for (E item : items) {
            matchers.add(IsEqual.equalTo(item));
        }
        return IsIterableContainingInRelativeOrder.containsInRelativeOrder(matchers);
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> containsInRelativeOrder(Matcher<? super E> ... itemMatchers) {
        return IsIterableContainingInRelativeOrder.containsInRelativeOrder(Arrays.asList(itemMatchers));
    }

    public static <E> Matcher<Iterable<? extends E>> containsInRelativeOrder(List<Matcher<? super E>> itemMatchers) {
        return new IsIterableContainingInRelativeOrder<E>(itemMatchers);
    }

    private static class MatchSeriesInRelativeOrder<F> {
        public final List<Matcher<? super F>> matchers;
        private final Description mismatchDescription;
        private int nextMatchIx = 0;
        private F lastMatchedItem = null;

        public MatchSeriesInRelativeOrder(List<Matcher<? super F>> matchers, Description mismatchDescription) {
            this.mismatchDescription = mismatchDescription;
            if (matchers.isEmpty()) {
                throw new IllegalArgumentException("Should specify at least one expected element");
            }
            this.matchers = matchers;
        }

        public void processItems(Iterable<? extends F> iterable) {
            for (F item : iterable) {
                Matcher<F> matcher;
                if (this.nextMatchIx >= this.matchers.size() || !(matcher = this.matchers.get(this.nextMatchIx)).matches(item)) continue;
                this.lastMatchedItem = item;
                ++this.nextMatchIx;
            }
        }

        public boolean isFinished() {
            if (this.nextMatchIx < this.matchers.size()) {
                this.mismatchDescription.appendDescriptionOf(this.matchers.get(this.nextMatchIx)).appendText(" was not found");
                if (this.lastMatchedItem != null) {
                    this.mismatchDescription.appendText(" after ").appendValue(this.lastMatchedItem);
                }
                return false;
            }
            return true;
        }
    }
}

