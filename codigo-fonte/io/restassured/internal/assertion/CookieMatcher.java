/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.assertion;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.utils.DateUtils;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public class CookieMatcher {
    private static final Log log = LogFactory.getLog(CookieMatcher.class);
    private Object cookieName;
    private Matcher<String> matcher;

    public Map<String, Object> validateCookies(List<String> headerWithCookieList, Cookies responseCookies) {
        boolean success = true;
        String errorMessage = "";
        if (headerWithCookieList == null || headerWithCookieList.isEmpty() && !responseCookies.exist()) {
            success = false;
            errorMessage = "No cookies defined in the response\n";
        } else {
            Cookies cookiesInHeader = CookieMatcher.getCookies(headerWithCookieList);
            ArrayList<Cookie> mergedCookies = new ArrayList<Cookie>();
            cookiesInHeader.forEach(mergedCookies::add);
            for (Cookie responseCookie : responseCookies == null ? new Cookies(new Cookie[0]) : responseCookies) {
                if (cookiesInHeader.hasCookieWithName(responseCookie.getName())) continue;
                mergedCookies.add(responseCookie);
            }
            Cookies raCookies = new Cookies(mergedCookies);
            Cookie cookie = raCookies.get((String)this.cookieName);
            if (cookie == null) {
                String cookiesAsString = raCookies.toString();
                success = false;
                errorMessage = "Cookie \"" + this.getCookieName() + "\" was not defined in the response. Cookies are: \n" + cookiesAsString + "\n";
            } else {
                String value = cookie.getValue();
                if (!this.matcher.matches(value)) {
                    success = false;
                    String expectedDescription = CookieMatcher.getExpectedDescription(this.matcher);
                    String mismatchDescription = CookieMatcher.getMismatchDescription(this.matcher, value);
                    errorMessage = "Expected cookie \"" + this.getCookieName() + "\" was not " + expectedDescription + ", " + mismatchDescription + ".\n";
                }
            }
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(2);
        map.put("success", success);
        map.put("errorMessage", errorMessage);
        return map;
    }

    public static String getExpectedDescription(Matcher<?> matcher) {
        StringDescription expectedDescription = new StringDescription();
        matcher.describeTo(expectedDescription);
        return expectedDescription.toString();
    }

    public static String getMismatchDescription(Matcher<?> matcher, Object value) {
        StringDescription mismatchDescription = new StringDescription();
        matcher.describeMismatch(value, mismatchDescription);
        return mismatchDescription.toString();
    }

    public static Cookies getCookies(List<String> headerWithCookieList) {
        ArrayList<Cookie> cookieList = new ArrayList<Cookie>();
        headerWithCookieList.forEach(it -> {
            String[] cookieStrings = StringUtils.split(it, ";");
            Cookie.Builder cookieBuilder = null;
            for (int i = 0; i < cookieStrings.length; ++i) {
                String cookieValue;
                String cookieKey;
                Iterator<String> iterator;
                String part = cookieStrings[i];
                if (i == 0) {
                    if (part.contains("=")) {
                        iterator = CookieMatcher.getKeyAndValueOfCookie(part).iterator();
                        cookieKey = iterator.hasNext() ? iterator.next() : null;
                        cookieValue = iterator.hasNext() ? iterator.next() : null;
                        cookieBuilder = new Cookie.Builder(cookieKey, cookieValue);
                        continue;
                    }
                    cookieBuilder = new Cookie.Builder(part, null);
                    continue;
                }
                if (part.contains("=")) {
                    iterator = CookieMatcher.getKeyAndValueOfCookie(part).iterator();
                    cookieKey = iterator.hasNext() ? iterator.next() : null;
                    cookieValue = iterator.hasNext() ? iterator.next() : null;
                    CookieMatcher.setCookieProperty(cookieBuilder, cookieKey, cookieValue);
                    continue;
                }
                CookieMatcher.setCookieProperty(cookieBuilder, part, null);
            }
            cookieList.add(cookieBuilder == null ? null : cookieBuilder.build());
        });
        return new Cookies(cookieList);
    }

    public static List<String> getKeyAndValueOfCookie(String part) {
        String cookieValue;
        String cookieKey;
        int indexOfEqual = StringUtils.indexOf((CharSequence)part, "=");
        if (indexOfEqual > -1) {
            cookieKey = StringUtils.substring(part, 0, indexOfEqual);
            cookieValue = StringUtils.substring(part, indexOfEqual + 1);
        } else {
            cookieKey = part;
            cookieValue = null;
        }
        return new ArrayList<String>(Arrays.asList(StringUtils.trim(cookieKey), StringUtils.trim(cookieValue)));
    }

    private static void setCookieProperty(Cookie.Builder builder, String name, String value) {
        name = StringUtils.trim(name);
        if (value != null || StringUtils.equalsIgnoreCase(name, "Secure") || StringUtils.equalsIgnoreCase(name, "HttpOnly")) {
            if (StringUtils.equalsIgnoreCase(name, "Comment")) {
                builder.setComment(value);
            } else if (StringUtils.equalsIgnoreCase(name, "Version")) {
                if (NumberUtils.isDigits(value = StringUtils.trim(StringUtils.remove(value, "\"")))) {
                    builder.setVersion(Integer.parseInt(value));
                }
            } else if (StringUtils.equalsIgnoreCase(name, "Path")) {
                builder.setPath(value);
            } else if (StringUtils.equalsIgnoreCase(name, "Domain")) {
                builder.setDomain(value);
            } else if (StringUtils.equalsIgnoreCase(name, "Max-Age")) {
                builder.setMaxAge(Long.parseLong(value));
            } else if (StringUtils.equalsIgnoreCase(name, "Secure")) {
                builder.setSecured(true);
            } else if (StringUtils.equalsIgnoreCase(name, "HttpOnly")) {
                builder.setHttpOnly(true);
            } else if (StringUtils.equalsIgnoreCase(name, "Expires")) {
                Date parsedDate = DateUtils.parseDate(value = StringUtils.trim(StringUtils.remove(value, "\"")));
                if (parsedDate != null) {
                    builder.setExpiryDate(parsedDate);
                } else {
                    log.warn("Ignoring unparsable 'Expires' attribute value: " + value);
                }
            } else if (StringUtils.equalsIgnoreCase(name, "SameSite")) {
                builder.setSameSite(value);
            }
        }
    }

    public Object getCookieName() {
        return this.cookieName;
    }

    public void setCookieName(Object cookieName) {
        this.cookieName = cookieName;
    }

    public Matcher<String> getMatcher() {
        return this.matcher;
    }

    public void setMatcher(Matcher<String> matcher) {
        this.matcher = matcher;
    }
}

