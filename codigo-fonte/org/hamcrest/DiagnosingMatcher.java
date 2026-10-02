/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public abstract class DiagnosingMatcher<T>
extends BaseMatcher<T> {
    @Override
    public final boolean matches(Object item) {
        return this.matches(item, Description.NONE);
    }

    @Override
    public final void describeMismatch(Object item, Description mismatchDescription) {
        this.matches(item, mismatchDescription);
    }

    protected abstract boolean matches(Object var1, Description var2);
}

