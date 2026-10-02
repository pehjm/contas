/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.core;

import org.hamcrest.Matcher;
import org.hamcrest.core.SubstringMatcher;

public class StringContains
extends SubstringMatcher {
    public StringContains(String substring) {
        this(false, substring);
    }

    public StringContains(boolean ignoringCase, String substring) {
        super("containing", ignoringCase, substring);
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return this.converted(s).contains(this.converted(this.substring));
    }

    public static Matcher<String> containsString(String substring) {
        return new StringContains(false, substring);
    }

    public static Matcher<String> containsStringIgnoringCase(String substring) {
        return new StringContains(true, substring);
    }
}

