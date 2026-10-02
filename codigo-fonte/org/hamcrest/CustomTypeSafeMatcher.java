/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public abstract class CustomTypeSafeMatcher<T>
extends TypeSafeMatcher<T> {
    private final String fixedDescription;

    public CustomTypeSafeMatcher(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description must be non null!");
        }
        this.fixedDescription = description;
    }

    @Override
    public final void describeTo(Description description) {
        description.appendText(this.fixedDescription);
    }
}

