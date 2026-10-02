/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsArray<T>
extends TypeSafeMatcher<T[]> {
    private final Matcher<? super T>[] elementMatchers;

    public IsArray(Matcher<? super T>[] elementMatchers) {
        this.elementMatchers = (Matcher[])elementMatchers.clone();
    }

    @Override
    public boolean matchesSafely(T[] array) {
        if (array.length != this.elementMatchers.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (this.elementMatchers[i].matches(array[i])) continue;
            return false;
        }
        return true;
    }

    @Override
    public void describeMismatchSafely(T[] actual, Description mismatchDescription) {
        if (actual.length != this.elementMatchers.length) {
            mismatchDescription.appendText("array length was ").appendValue(actual.length);
            return;
        }
        for (int i = 0; i < actual.length; ++i) {
            if (this.elementMatchers[i].matches(actual[i])) continue;
            mismatchDescription.appendText("element ").appendValue(i).appendText(" ");
            this.elementMatchers[i].describeMismatch(actual[i], mismatchDescription);
            return;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendList(this.descriptionStart(), this.descriptionSeparator(), this.descriptionEnd(), Arrays.asList(this.elementMatchers));
    }

    protected String descriptionStart() {
        return "[";
    }

    protected String descriptionSeparator() {
        return ", ";
    }

    protected String descriptionEnd() {
        return "]";
    }

    public static <T> IsArray<T> array(Matcher<? super T> ... elementMatchers) {
        return new IsArray<T>(elementMatchers);
    }
}

