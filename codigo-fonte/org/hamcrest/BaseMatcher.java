/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public abstract class BaseMatcher<T>
implements Matcher<T> {
    @Override
    @Deprecated
    public final void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText("was ").appendValue(item);
    }

    public String toString() {
        return StringDescription.toString(this);
    }

    protected static boolean isNotNull(Object actual, Description mismatch) {
        if (actual == null) {
            mismatch.appendText("was null");
            return false;
        }
        return true;
    }
}

