/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import org.hamcrest.Matcher;
import org.hamcrest.core.SubstringMatcher;

public class StringStartsWith
extends SubstringMatcher {
    public StringStartsWith(String substring) {
        this(false, substring);
    }

    public StringStartsWith(boolean ignoringCase, String substring) {
        super("starting with", ignoringCase, substring);
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return this.converted(s).startsWith(this.converted(this.substring));
    }

    public static Matcher<String> startsWith(String prefix) {
        return new StringStartsWith(false, prefix);
    }

    public static Matcher<String> startsWithIgnoringCase(String prefix) {
        return new StringStartsWith(true, prefix);
    }
}

