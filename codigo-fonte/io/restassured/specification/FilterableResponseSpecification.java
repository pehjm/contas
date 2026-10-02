/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

public interface FilterableResponseSpecification
extends ResponseSpecification {
    public Matcher<Integer> getStatusCode();

    public Matcher<String> getStatusLine();

    public boolean hasHeaderAssertions();

    public boolean hasCookieAssertions();

    public String getResponseContentType();

    public String getRootPath();

    public LogDetail getLogDetail();
}

