/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.matcher;

import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import java.util.ArrayList;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class ResponseAwareMatcherComposer {
    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> and(ResponseAwareMatcher<T> matcher1, ResponseAwareMatcher<T> matcher2) {
        return ResponseAwareMatcherComposer.and(matcher1, matcher2, new ResponseAwareMatcher[0]);
    }

    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> and(ResponseAwareMatcher<T> matcher1, ResponseAwareMatcher<T> matcher2, ResponseAwareMatcher<T> ... additionalMatchers) {
        return response -> {
            Matcher<?>[] matchers = ResponseAwareMatcherComposer.toHamcrestMatchers(response, matcher1, matcher2, additionalMatchers);
            return Matchers.allOf(matchers);
        };
    }

    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> and(ResponseAwareMatcher<T> matcher1, Matcher matcher2, ResponseAwareMatcher<T> ... additionalMatchers) {
        return ResponseAwareMatcherComposer.and(matcher1, (T response) -> matcher2, additionalMatchers);
    }

    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> and(ResponseAwareMatcher<T> matcher1, Matcher matcher2) {
        return ResponseAwareMatcherComposer.and(matcher1, matcher2, new ResponseAwareMatcher[0]);
    }

    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> and(Matcher matcher1, ResponseAwareMatcher<T> matcher2, ResponseAwareMatcher<T> ... additionalMatchers) {
        return ResponseAwareMatcherComposer.and(matcher2, matcher1, additionalMatchers);
    }

    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> or(ResponseAwareMatcher<T> matcher1, ResponseAwareMatcher<T> matcher2) {
        return ResponseAwareMatcherComposer.or(matcher1, matcher2, new ResponseAwareMatcher[0]);
    }

    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> or(ResponseAwareMatcher<T> matcher1, ResponseAwareMatcher<T> matcher2, ResponseAwareMatcher<T> ... additionalMatchers) {
        return response -> {
            Matcher<?>[] matchers = ResponseAwareMatcherComposer.toHamcrestMatchers(response, matcher1, matcher2, additionalMatchers);
            return Matchers.anyOf(matchers);
        };
    }

    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> or(ResponseAwareMatcher<T> matcher1, Matcher matcher2, ResponseAwareMatcher<T> ... additionalMatchers) {
        return ResponseAwareMatcherComposer.or(matcher1, (T response) -> matcher2, additionalMatchers);
    }

    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> or(ResponseAwareMatcher<T> matcher1, Matcher matcher2) {
        return ResponseAwareMatcherComposer.or(matcher1, matcher2, new ResponseAwareMatcher[0]);
    }

    public static <T extends ResponseBody<T> & ResponseOptions<T>> ResponseAwareMatcher<T> or(Matcher matcher1, ResponseAwareMatcher<T> matcher2, ResponseAwareMatcher<T> ... additionalMatchers) {
        return ResponseAwareMatcherComposer.or(matcher2, matcher1, additionalMatchers);
    }

    private static <T extends ResponseBody<T> & ResponseOptions<T>> Matcher<?>[] toHamcrestMatchers(T response, ResponseAwareMatcher<T> matcher1, ResponseAwareMatcher<T> matcher2, ResponseAwareMatcher<T>[] additionalMatchers) throws Exception {
        AssertParameter.notNull(matcher1, ResponseAwareMatcher.class);
        AssertParameter.notNull(matcher2, ResponseAwareMatcher.class);
        ArrayList hamcrestMatchers = new ArrayList();
        hamcrestMatchers.add(matcher1.matcher(response));
        hamcrestMatchers.add(matcher2.matcher(response));
        if (additionalMatchers != null) {
            for (ResponseAwareMatcher<T> additionalMatcher : additionalMatchers) {
                hamcrestMatchers.add(additionalMatcher.matcher(response));
            }
        }
        return hamcrestMatchers.toArray(new Matcher[hamcrestMatchers.size()]);
    }
}

