/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import org.hamcrest.Matcher;
import org.hamcrest.core.SubstringMatcher;

public class StringEndsWith
extends SubstringMatcher {
    public StringEndsWith(String substring) {
        this(false, substring);
    }

    public StringEndsWith(boolean ignoringCase, String substring) {
        super("ending with", ignoringCase, substring);
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return this.converted(s).endsWith(this.converted(this.substring));
    }

    public static Matcher<String> endsWith(String suffix) {
        return new StringEndsWith(false, suffix);
    }

    public static Matcher<String> endsWithIgnoringCase(String suffix) {
        return new StringEndsWith(true, suffix);
    }
}

