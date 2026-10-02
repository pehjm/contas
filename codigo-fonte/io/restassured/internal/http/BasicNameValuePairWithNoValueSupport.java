/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import io.restassured.internal.NoParameterValue;
import java.io.Serializable;
import org.apache.http.NameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.LangUtils;

public class BasicNameValuePairWithNoValueSupport
implements NameValuePair,
Cloneable,
Serializable {
    private final String name;
    private final String value;
    private final boolean noValueParam;

    public BasicNameValuePairWithNoValueSupport(String name, Object value) {
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name;
        this.value = value == null ? "" : value.toString();
        this.noValueParam = value instanceof NoParameterValue;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public boolean hasValue() {
        return !this.noValueParam;
    }

    public String toString() {
        if (this.value == null) {
            return this.name;
        }
        int len = this.name.length() + 1 + this.value.length();
        CharArrayBuffer buffer = new CharArrayBuffer(len);
        buffer.append(this.name);
        if (!this.noValueParam) {
            buffer.append("=");
            buffer.append(this.value);
        }
        return buffer.toString();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof NameValuePair) {
            BasicNameValuePairWithNoValueSupport that = (BasicNameValuePairWithNoValueSupport)object;
            return this.name.equals(that.name) && LangUtils.equals(this.value, that.value);
        }
        return false;
    }

    public int hashCode() {
        int hash = 17;
        hash = LangUtils.hashCode(hash, this.name);
        hash = LangUtils.hashCode(hash, this.value);
        return hash;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

