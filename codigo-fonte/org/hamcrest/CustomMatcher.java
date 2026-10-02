/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public abstract class CustomMatcher<T>
extends BaseMatcher<T> {
    private final String fixedDescription;

    public CustomMatcher(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description should be non null!");
        }
        this.fixedDescription = description;
    }

    @Override
    public final void describeTo(Description description) {
        description.appendText(this.fixedDescription);
    }
}

