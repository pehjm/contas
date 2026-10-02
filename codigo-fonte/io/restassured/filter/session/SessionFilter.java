/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter.session;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.StringUtils;

public class SessionFilter
implements Filter {
    private final AtomicReference<String> sessionId = new AtomicReference();

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response;
        String sessionIdInResponse;
        if (this.hasStoredSessionId() && !this.requestHasSessionIdDefined(requestSpec)) {
            requestSpec.sessionId(this.sessionId.get());
        }
        if (StringUtils.isNotBlank(sessionIdInResponse = (response = ctx.next(requestSpec, responseSpec)).sessionId())) {
            this.sessionId.set(sessionIdInResponse);
        }
        return response;
    }

    private boolean requestHasSessionIdDefined(FilterableRequestSpecification requestSpec) {
        return requestSpec.getCookies().hasCookieWithName(requestSpec.getConfig().getSessionConfig().sessionIdName());
    }

    private boolean hasStoredSessionId() {
        return this.sessionId.get() != null;
    }

    public String getSessionId() {
        return this.sessionId.get();
    }

    public boolean hasSessionId() {
        return StringUtils.isNotBlank(this.sessionId.get());
    }
}

