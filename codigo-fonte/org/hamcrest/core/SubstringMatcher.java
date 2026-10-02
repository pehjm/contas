/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public abstract class SubstringMatcher
extends TypeSafeMatcher<String> {
    private final String relationship;
    private final boolean ignoringCase;
    protected final String substring;

    protected SubstringMatcher(String relationship, boolean ignoringCase, String substring) {
        this.relationship = relationship;
        this.ignoringCase = ignoringCase;
        this.substring = substring;
        if (null == substring) {
            throw new IllegalArgumentException("missing substring");
        }
    }

    @Override
    public boolean matchesSafely(String item) {
        return this.evalSubstringOf(this.ignoringCase ? item.toLowerCase() : item);
    }

    @Override
    public void describeMismatchSafely(String item, Description mismatchDescription) {
        mismatchDescription.appendText("was \"").appendText(item).appendText("\"");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string ").appendText(this.relationship).appendText(" ").appendValue(this.substring);
        if (this.ignoringCase) {
            description.appendText(" ignoring case");
        }
    }

    protected String converted(String arg) {
        return this.ignoringCase ? arg.toLowerCase() : arg;
    }

    protected abstract boolean evalSubstringOf(String var1);
}

