/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.http;

import io.restassured.http.Header;
import io.restassured.internal.MultiValueEntity;
import io.restassured.internal.common.assertion.AssertParameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Headers
implements Iterable<Header> {
    private final MultiValueEntity<Header> headers;

    public Headers(Header ... headers) {
        this(Arrays.asList(headers));
    }

    public Headers(List<Header> headers) {
        AssertParameter.notNull(headers, "Headers");
        this.headers = new MultiValueEntity<Header>(headers);
    }

    public int size() {
        return this.headers.size();
    }

    public boolean exist() {
        return this.headers.exist();
    }

    public boolean hasHeaderWithName(String headerName) {
        return this.headers.hasEntityWithName(headerName);
    }

    protected List<Header> list() {
        return this.headers.list();
    }

    public Header get(String headerName) {
        AssertParameter.notNull(headerName, "Header name");
        return this.headers.get(headerName);
    }

    public String getValue(String headerName) {
        return this.headers.getValue(headerName);
    }

    public List<String> getValues(String headerName) {
        return this.headers.getValues(headerName);
    }

    public List<Header> getList(String headerName) {
        return this.headers.getList(headerName);
    }

    @Override
    public Iterator<Header> iterator() {
        return this.headers.iterator();
    }

    public List<Header> asList() {
        return this.headers.asList();
    }

    public static Headers headers(Header header, Header ... additionalHeaders) {
        AssertParameter.notNull(header, "Header");
        LinkedList<Header> headerList = new LinkedList<Header>();
        headerList.add(header);
        if (additionalHeaders != null) {
            Collections.addAll(headerList, additionalHeaders);
        }
        return new Headers(headerList);
    }

    public String toString() {
        return this.headers.toString();
    }
}

