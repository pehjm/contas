/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.matcher;

import io.restassured.http.Cookie;
import java.util.Date;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.CombinableMatcher;

public class DetailedCookieMatcher
extends CombinableMatcher<Cookie> {
    DetailedCookieMatcher() {
        this((Matcher<? super Cookie>)Matchers.notNullValue());
    }

    private DetailedCookieMatcher(Matcher<? super Cookie> m) {
        super(m);
    }

    public DetailedCookieMatcher value(String expectedValue) {
        return this.value(Matchers.equalTo(expectedValue));
    }

    public DetailedCookieMatcher value(Matcher<? super String> valueMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("value", valueMatcher)));
    }

    public DetailedCookieMatcher comment(String expectedComment) {
        return this.comment(Matchers.equalTo(expectedComment));
    }

    public DetailedCookieMatcher comment(Matcher<? super String> commentMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("comment", commentMatcher)));
    }

    public DetailedCookieMatcher expiryDate(Date expectedExpiryDate) {
        return this.expiryDate(Matchers.equalTo(expectedExpiryDate));
    }

    public DetailedCookieMatcher expiryDate(Matcher<? super Date> expiryDateMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("expiryDate", expiryDateMatcher)));
    }

    public DetailedCookieMatcher domain(String expectedDomain) {
        return this.domain(Matchers.equalTo(expectedDomain));
    }

    public DetailedCookieMatcher domain(Matcher<? super String> domainMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("domain", domainMatcher)));
    }

    public DetailedCookieMatcher path(String expectedPath) {
        return this.path(Matchers.equalTo(expectedPath));
    }

    public DetailedCookieMatcher path(Matcher<? super String> pathMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("path", pathMatcher)));
    }

    public DetailedCookieMatcher secured(boolean expectedSecuredValue) {
        return this.secured(Matchers.equalTo(expectedSecuredValue));
    }

    public DetailedCookieMatcher secured(Matcher<? super Boolean> securedMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("secured", securedMatcher)));
    }

    public DetailedCookieMatcher httpOnly(boolean expectedHttpOnlyValue) {
        return this.httpOnly(Matchers.equalTo(expectedHttpOnlyValue));
    }

    public DetailedCookieMatcher httpOnly(Matcher<? super Boolean> httpOnlyMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("httpOnly", httpOnlyMatcher)));
    }

    public DetailedCookieMatcher version(int expectedVersionValue) {
        return this.version(Matchers.equalTo(expectedVersionValue));
    }

    public DetailedCookieMatcher version(Matcher<? super Integer> versionMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("version", versionMatcher)));
    }

    public DetailedCookieMatcher maxAge(long expectedMaxAgeValue) {
        return this.maxAge(Matchers.equalTo(expectedMaxAgeValue));
    }

    public DetailedCookieMatcher maxAge(Matcher<? super Integer> maxAgeMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("maxAge", maxAgeMatcher)));
    }

    public DetailedCookieMatcher sameSite(String expectedSameSiteAttribute) {
        return this.sameSite(Matchers.equalTo(expectedSameSiteAttribute));
    }

    public DetailedCookieMatcher sameSite(Matcher<? super String> sameSiteMatcher) {
        return new DetailedCookieMatcher(this.and(Matchers.hasProperty("sameSite", sameSiteMatcher)));
    }
}

