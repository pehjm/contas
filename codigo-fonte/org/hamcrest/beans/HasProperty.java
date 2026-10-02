/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.beans;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.beans.PropertyUtil;

public class HasProperty<T>
extends TypeSafeMatcher<T> {
    private final String propertyName;

    public HasProperty(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public boolean matchesSafely(T obj) {
        try {
            return PropertyUtil.getPropertyDescriptor(this.propertyName, obj) != null;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public void describeMismatchSafely(T item, Description mismatchDescription) {
        mismatchDescription.appendText("no ").appendValue(this.propertyName).appendText(" in ").appendValue(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("hasProperty(").appendValue(this.propertyName).appendText(")");
    }

    public static <T> Matcher<T> hasProperty(String propertyName) {
        return new HasProperty<T>(propertyName);
    }
}

