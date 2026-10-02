/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.http;

import io.restassured.internal.NameAndValue;
import io.restassured.internal.common.assertion.AssertParameter;

public class Header
implements NameAndValue {
    private final String name;
    private final String value;

    public Header(String name, String value) {
        AssertParameter.notNull(name, "Header name");
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public boolean hasSameNameAs(Header header) {
        AssertParameter.notNull(header, Header.class);
        return this.name.equalsIgnoreCase(header.getName());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Header header = (Header)o;
        if (this.name != null ? !this.name.equalsIgnoreCase(header.name) : header.name != null) {
            return false;
        }
        return !(this.value != null ? !this.value.equalsIgnoreCase(header.value) : header.value != null);
    }

    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + (this.value != null ? this.value.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name);
        if (this.value != null) {
            builder.append("=").append(this.value);
        }
        return builder.toString();
    }
}

