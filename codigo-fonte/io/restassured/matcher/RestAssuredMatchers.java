/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.matcher;

import io.restassured.internal.matcher.xml.XmlDtdMatcher;
import io.restassured.internal.matcher.xml.XmlXsdMatcher;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class RestAssuredMatchers {
    public static XmlXsdMatcher matchesXsd(String xsd) {
        return XmlXsdMatcher.matchesXsd(xsd);
    }

    public static XmlXsdMatcher matchesXsd(InputStream xsd) {
        return XmlXsdMatcher.matchesXsd(xsd);
    }

    public static XmlXsdMatcher matchesXsd(Reader xsd) {
        return XmlXsdMatcher.matchesXsd(xsd);
    }

    public static XmlXsdMatcher matchesXsd(File xsd) {
        return XmlXsdMatcher.matchesXsd(xsd);
    }

    public static XmlXsdMatcher matchesXsdInClasspath(String path) {
        return XmlXsdMatcher.matchesXsdInClasspath(path);
    }

    public static Matcher<String> matchesDtd(String dtd) {
        return XmlDtdMatcher.matchesDtd(dtd);
    }

    public static Matcher<String> matchesDtd(InputStream dtd) {
        return XmlDtdMatcher.matchesDtd(dtd);
    }

    public static Matcher<String> matchesDtd(File dtd) {
        return XmlDtdMatcher.matchesDtd(dtd);
    }

    public static Matcher<String> matchesDtd(URL url) {
        return XmlDtdMatcher.matchesDtd(url);
    }

    public static Matcher<String> matchesDtdInClasspath(String path) {
        return XmlDtdMatcher.matchesDtdInClasspath(path);
    }

    public static ResponseAwareMatcher<Response> equalToPath(String path) {
        return response -> Matchers.equalTo(response.path(path, new String[0]));
    }

    public static ResponseAwareMatcher<Response> endsWithPath(String path) {
        return response -> Matchers.endsWith("" + response.path(path, new String[0]));
    }

    public static ResponseAwareMatcher<Response> startsWithPath(String path) {
        return response -> Matchers.startsWith("" + response.path(path, new String[0]));
    }

    public static ResponseAwareMatcher<Response> containsPath(String path) {
        return response -> Matchers.containsString((String)response.path(path, new String[0]));
    }

    public static DetailedCookieMatcher detailedCookie() {
        return new DetailedCookieMatcher();
    }
}

