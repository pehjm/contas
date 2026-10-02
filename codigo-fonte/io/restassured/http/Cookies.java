/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.http;

import io.restassured.http.Cookie;
import io.restassured.internal.MultiValueEntity;
import io.restassured.internal.common.assertion.AssertParameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Cookies
implements Iterable<Cookie> {
    private final MultiValueEntity<Cookie> cookies;

    public Cookies(Cookie ... cookies) {
        this(Arrays.asList(cookies));
    }

    public Cookies(List<Cookie> cookies) {
        AssertParameter.notNull(cookies, "Cookies");
        this.cookies = new MultiValueEntity<Cookie>(cookies);
    }

    public int size() {
        return this.cookies.size();
    }

    public boolean exist() {
        return this.cookies.exist();
    }

    public boolean hasCookieWithName(String cookieName) {
        return this.cookies.hasEntityWithName(cookieName);
    }

    protected List<Cookie> list() {
        return this.cookies.list();
    }

    public Cookie get(String cookieName) {
        AssertParameter.notNull(cookieName, "Cookie name");
        return this.cookies.get(cookieName);
    }

    public String getValue(String cookieName) {
        AssertParameter.notNull(cookieName, "Cookie name");
        return this.cookies.getValue(cookieName);
    }

    public List<Cookie> getList(String cookieName) {
        return this.cookies.getList(cookieName);
    }

    public List<String> getValues(String cookieName) {
        return this.cookies.getValues(cookieName);
    }

    public List<Cookie> asList() {
        return this.cookies.asList();
    }

    @Override
    public Iterator<Cookie> iterator() {
        return this.cookies.iterator();
    }

    public static Cookies cookies(Cookie cookie, Cookie ... additionalCookies) {
        AssertParameter.notNull(cookie, "Cookie");
        LinkedList<Cookie> cookieList = new LinkedList<Cookie>();
        cookieList.add(cookie);
        Collections.addAll(cookieList, additionalCookies);
        return new Cookies(cookieList);
    }

    public String toString() {
        return this.cookies.toString();
    }
}

