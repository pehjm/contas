/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public class MatcherAssert {
    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat("", actual, matcher);
    }

    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        if (!matcher.matches(actual)) {
            StringDescription description = new StringDescription();
            description.appendText(reason).appendText(System.lineSeparator()).appendText("Expected: ").appendDescriptionOf(matcher).appendText(System.lineSeparator()).appendText("     but: ");
            matcher.describeMismatch(actual, description);
            throw new AssertionError((Object)((Object)description).toString());
        }
    }

    public static void assertThat(String reason, boolean assertion) {
        if (!assertion) {
            throw new AssertionError((Object)reason);
        }
    }
}

