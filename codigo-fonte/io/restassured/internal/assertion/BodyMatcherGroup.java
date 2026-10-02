/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.assertion;

import io.restassured.config.RestAssuredConfig;
import io.restassured.internal.assertion.BodyMatcher;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BodyMatcherGroup {
    private final List<BodyMatcher> bodyAssertions = new ArrayList<BodyMatcher>();

    public void add(BodyMatcherGroup group) {
        this.bodyAssertions.addAll(group.bodyAssertions);
    }

    public void add(BodyMatcher bodyMatcher) {
        this.bodyAssertions.add(bodyMatcher);
    }

    public void reset() {
        this.bodyAssertions.clear();
    }

    public int size() {
        return this.bodyAssertions.size();
    }

    public List<Object> validate(Response response, Object contentParser, RestAssuredConfig config) {
        return this.bodyAssertions.stream().map(bodyMatcher -> bodyMatcher.validate(response, contentParser, config)).collect(Collectors.toList());
    }

    public boolean containsMatchers() {
        return !this.bodyAssertions.isEmpty();
    }

    public boolean requiresTextParsing() {
        return this.bodyAssertions.stream().anyMatch(BodyMatcher::requiresTextParsing);
    }

    public boolean requiresPathParsing() {
        return this.bodyAssertions.stream().anyMatch(BodyMatcher::requiresPathParsing);
    }
}

