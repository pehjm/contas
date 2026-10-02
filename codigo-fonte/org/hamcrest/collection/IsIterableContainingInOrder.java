/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.collection.ArrayMatching;
import org.hamcrest.internal.NullSafety;

public class IsIterableContainingInOrder<E>
extends TypeSafeDiagnosingMatcher<Iterable<? extends E>> {
    private final List<Matcher<? super E>> matchers;

    public IsIterableContainingInOrder(List<Matcher<? super E>> matchers) {
        this.matchers = matchers;
    }

    @Override
    protected boolean matchesSafely(Iterable<? extends E> iterable, Description mismatchDescription) {
        MatchSeries<E> matchSeries = new MatchSeries<E>(this.matchers, mismatchDescription);
        for (E item : iterable) {
            if (matchSeries.matches(item)) continue;
            return false;
        }
        return matchSeries.isFinished();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("iterable containing ").appendList("[", ", ", "]", this.matchers);
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> contains(E ... items) {
        return IsIterableContainingInOrder.contains(ArrayMatching.asEqualMatchers(items));
    }

    public static <E> Matcher<Iterable<? extends E>> contains(Matcher<? super E> itemMatcher) {
        return IsIterableContainingInOrder.contains(new ArrayList<Matcher<? super E>>(Collections.singletonList(itemMatcher)));
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> contains(Matcher<? super E> ... itemMatchers) {
        List<Matcher<? super E>> nullSafeWithExplicitTypeMatchers = NullSafety.nullSafe(itemMatchers);
        return IsIterableContainingInOrder.contains(nullSafeWithExplicitTypeMatchers);
    }

    public static <E> Matcher<Iterable<? extends E>> contains(List<Matcher<? super E>> itemMatchers) {
        return new IsIterableContainingInOrder<E>(itemMatchers);
    }

    private static class MatchSeries<F> {
        private final List<Matcher<? super F>> matchers;
        private final Description mismatchDescription;
        private int nextMatchIx = 0;

        public MatchSeries(List<Matcher<? super F>> matchers, Description mismatchDescription) {
            this.mismatchDescription = mismatchDescription;
            if (matchers.isEmpty()) {
                throw new IllegalArgumentException("Should specify at least one expected element");
            }
            this.matchers = matchers;
        }

        public boolean matches(F item) {
            if (this.matchers.size() <= this.nextMatchIx) {
                this.mismatchDescription.appendText("not matched: ").appendValue(item);
                return false;
            }
            return this.isMatched(item);
        }

        public boolean isFinished() {
            if (this.nextMatchIx < this.matchers.size()) {
                this.mismatchDescription.appendText("no item was ").appendDescriptionOf(this.matchers.get(this.nextMatchIx));
                return false;
            }
            return true;
        }

        private boolean isMatched(F item) {
            Matcher<F> matcher = this.matchers.get(this.nextMatchIx);
            if (!matcher.matches(item)) {
                this.describeMismatch(matcher, item);
                return false;
            }
            ++this.nextMatchIx;
            return true;
        }

        private void describeMismatch(Matcher<? super F> matcher, F item) {
            this.mismatchDescription.appendText("item " + this.nextMatchIx + ": ");
            matcher.describeMismatch(item, this.mismatchDescription);
        }
    }
}

