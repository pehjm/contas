/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter.cookie;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.RFC6265StrictSpec;
import org.apache.http.message.BasicHeader;

public class CookieFilter
implements Filter {
    private final boolean allowMultipleCookiesWithTheSameName;
    private final CookieSpec cookieSpec;
    private final BasicCookieStore cookieStore;

    public CookieFilter() {
        this(false);
    }

    public CookieFilter(boolean allowMultipleCookiesWithTheSameName) {
        this.allowMultipleCookiesWithTheSameName = allowMultipleCookiesWithTheSameName;
        this.cookieSpec = new RFC6265StrictSpec();
        this.cookieStore = new BasicCookieStore();
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        CookieOrigin cookieOrigin = this.cookieOriginFromUri(requestSpec.getURI());
        for (Cookie cookie : this.cookieStore.getCookies()) {
            if (!this.cookieSpec.match(cookie, cookieOrigin) || !this.allowMultipleCookiesWithTheSameNameOrCookieNotPreviouslyDefined(requestSpec, cookie)) continue;
            requestSpec.cookie(cookie.getName(), cookie.getValue(), new Object[0]);
        }
        Response response = ctx.next(requestSpec, responseSpec);
        List<Cookie> responseCookies = this.extractResponseCookies(response, cookieOrigin);
        this.cookieStore.addCookies(responseCookies.toArray(new Cookie[0]));
        return response;
    }

    private boolean allowMultipleCookiesWithTheSameNameOrCookieNotPreviouslyDefined(FilterableRequestSpecification requestSpec, Cookie cookie) {
        return this.allowMultipleCookiesWithTheSameName || !requestSpec.getCookies().hasCookieWithName(cookie.getName());
    }

    private List<Cookie> extractResponseCookies(Response response, CookieOrigin cookieOrigin) {
        ArrayList<Cookie> cookies = new ArrayList<Cookie>();
        for (String cookieValue : response.getHeaders().getValues("Set-Cookie")) {
            BasicHeader setCookieHeader = new BasicHeader("Set-Cookie", cookieValue);
            try {
                cookies.addAll(this.cookieSpec.parse(setCookieHeader, cookieOrigin));
            }
            catch (MalformedCookieException malformedCookieException) {}
        }
        return cookies;
    }

    private CookieOrigin cookieOriginFromUri(String uri) {
        try {
            URL parsedUrl = new URL(uri);
            int port = parsedUrl.getPort() != -1 ? parsedUrl.getPort() : 80;
            return new CookieOrigin(parsedUrl.getHost(), port, parsedUrl.getPath(), "https".equals(parsedUrl.getProtocol()));
        }
        catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public CookieStore getCookieStore() {
        return this.cookieStore;
    }
}

