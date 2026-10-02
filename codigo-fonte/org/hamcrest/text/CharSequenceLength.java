/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.text;

import org.hamcrest.CoreMatchers;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class CharSequenceLength
extends FeatureMatcher<CharSequence, Integer> {
    public CharSequenceLength(Matcher<? super Integer> lengthMatcher) {
        super(lengthMatcher, "a CharSequence with length", "length");
    }

    @Override
    protected Integer featureValueOf(CharSequence actual) {
        return actual.length();
    }

    public static Matcher<CharSequence> hasLength(int length) {
        return new CharSequenceLength(CoreMatchers.equalTo(length));
    }

    public static Matcher<CharSequence> hasLength(Matcher<? super Integer> lengthMatcher) {
        return new CharSequenceLength(lengthMatcher);
    }
}

