/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.util;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public class MatcherErrorMessageBuilder<T, M extends Matcher<T>> {
    private final String name;

    public MatcherErrorMessageBuilder(String name) {
        this.name = name;
    }

    public String buildError(T actual, M matcher) {
        StringDescription descriptionBuilder = new StringDescription();
        descriptionBuilder.appendText("Expected ").appendText(this.name).appendText(" ");
        matcher.describeTo(descriptionBuilder);
        descriptionBuilder.appendText(" but ");
        matcher.describeMismatch(actual, descriptionBuilder);
        String description = descriptionBuilder.toString().replaceAll("[.\\n]+$", "");
        return description + ".\n";
    }
}

