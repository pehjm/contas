/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.http;

import io.restassured.internal.NameAndValue;
import io.restassured.internal.common.assertion.AssertParameter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Cookie
implements NameAndValue {
    public static final String COMMENT = "Comment";
    public static final String PATH = "Path";
    public static final String DOMAIN = "Domain";
    public static final String MAX_AGE = "Max-Age";
    public static final String SECURE = "Secure";
    public static final String HTTP_ONLY = "HttpOnly";
    public static final String EXPIRES = "Expires";
    public static final String VERSION = "Version";
    public static final String SAME_SITE = "SameSite";
    private static final String COOKIE_ATTRIBUTE_SEPARATOR = ";";
    private static final String EQUALS = "=";
    private static final int UNDEFINED = -1;
    private static final long UNDEFINED_LONG = -1L;
    private final String name;
    private final String value;
    private final String comment;
    private final Date expiryDate;
    private final String domain;
    private final String path;
    private final boolean secured;
    private final boolean httpOnly;
    private final int version;
    private final long maxAge;
    private final String sameSite;

    private Cookie(String name, String value, String comment, Date expiryDate, String domain, String path, boolean secured, boolean httpOnly, int version, long maxAge, String sameSite) {
        this.name = name;
        this.value = value;
        this.comment = comment;
        this.expiryDate = expiryDate;
        this.domain = domain;
        this.path = path;
        this.secured = secured;
        this.httpOnly = httpOnly;
        this.version = version < 0 ? -1 : version;
        this.maxAge = maxAge;
        this.sameSite = sameSite;
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
        return this.value != null;
    }

    public String getComment() {
        return this.comment;
    }

    public boolean hasComment() {
        return this.comment != null;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public boolean hasExpiryDate() {
        return this.expiryDate != null;
    }

    public String getDomain() {
        return this.domain;
    }

    public boolean hasDomain() {
        return this.domain != null;
    }

    public String getPath() {
        return this.path;
    }

    public boolean hasPath() {
        return this.path != null;
    }

    public boolean isSecured() {
        return this.secured;
    }

    public boolean isHttpOnly() {
        return this.httpOnly;
    }

    public int getVersion() {
        return this.version;
    }

    public boolean hasVersion() {
        return this.version != -1;
    }

    public long getMaxAge() {
        return this.maxAge;
    }

    public boolean hasMaxAge() {
        return this.maxAge != -1L;
    }

    public String getSameSite() {
        return this.sameSite;
    }

    public boolean hasSameSite() {
        return this.sameSite != null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Cookie cookie = (Cookie)o;
        if (this.maxAge != cookie.maxAge) {
            return false;
        }
        if (this.secured != cookie.secured) {
            return false;
        }
        if (this.httpOnly != cookie.httpOnly) {
            return false;
        }
        if (this.version != cookie.version) {
            return false;
        }
        if (this.comment != null ? !this.comment.equals(cookie.comment) : cookie.comment != null) {
            return false;
        }
        if (this.domain != null ? !this.domain.equals(cookie.domain) : cookie.domain != null) {
            return false;
        }
        if (this.expiryDate != null ? !this.expiryDate.equals(cookie.expiryDate) : cookie.expiryDate != null) {
            return false;
        }
        if (this.name != null ? !this.name.equals(cookie.name) : cookie.name != null) {
            return false;
        }
        if (this.path != null ? !this.path.equals(cookie.path) : cookie.path != null) {
            return false;
        }
        if (this.value != null ? !this.value.equals(cookie.value) : cookie.value != null) {
            return false;
        }
        return !(this.sameSite != null ? !this.sameSite.equals(cookie.sameSite) : cookie.sameSite != null);
    }

    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + (this.value != null ? this.value.hashCode() : 0);
        result = 31 * result + (this.comment != null ? this.comment.hashCode() : 0);
        result = 31 * result + (this.expiryDate != null ? this.expiryDate.hashCode() : 0);
        result = 31 * result + (this.domain != null ? this.domain.hashCode() : 0);
        result = 31 * result + (this.path != null ? this.path.hashCode() : 0);
        result = 31 * result + (this.secured ? 1 : 0);
        result = 31 * result + (this.httpOnly ? 1 : 0);
        result = 31 * result + this.version;
        result = 31 * result + (int)this.maxAge;
        result = 31 * result + (this.sameSite != null ? this.sameSite.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name);
        if (this.hasValue()) {
            builder.append(EQUALS).append(this.value);
        }
        if (this.hasComment()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(COMMENT).append(EQUALS).append(this.comment);
        }
        if (this.hasPath()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(PATH).append(EQUALS).append(this.path);
        }
        if (this.hasDomain()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(DOMAIN).append(EQUALS).append(this.domain);
        }
        if (this.hasMaxAge()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(MAX_AGE).append(EQUALS).append(this.maxAge);
        }
        if (this.isSecured()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(SECURE);
        }
        if (this.isHttpOnly()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(HTTP_ONLY);
        }
        if (this.hasExpiryDate()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(EXPIRES).append(EQUALS).append(simpleDateFormat.format(this.expiryDate));
        }
        if (this.hasVersion()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(VERSION).append(EQUALS).append(this.version);
        }
        if (this.hasSameSite()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(SAME_SITE).append(EQUALS).append(this.sameSite);
        }
        return builder.toString();
    }

    public static class Builder {
        private final String name;
        private final String value;
        private String comment;
        private Date expiryDate;
        private String domain;
        private String path;
        private boolean secured = false;
        private boolean httpOnly = false;
        private int version = -1;
        private long maxAge = -1L;
        private String sameSite;

        public Builder(String name) {
            this(name, null);
        }

        public Builder(String name, String value) {
            AssertParameter.notNull(name, "Cookie name");
            this.name = name;
            this.value = value;
        }

        public Builder(Cookie cookie) {
            AssertParameter.notNull(cookie, "Cookie");
            this.name = cookie.name;
            this.value = cookie.value;
            this.comment = cookie.comment;
            this.expiryDate = cookie.expiryDate;
            this.domain = cookie.domain;
            this.path = cookie.path;
            this.secured = cookie.secured;
            this.httpOnly = cookie.httpOnly;
            this.version = cookie.version;
            this.maxAge = cookie.maxAge;
            this.sameSite = cookie.sameSite;
        }

        public Builder setComment(String comment) {
            AssertParameter.notNull(this.name, "Cookie name");
            this.comment = comment;
            return this;
        }

        public Builder setExpiryDate(Date date) {
            AssertParameter.notNull(date, "Cookie expiry date");
            this.expiryDate = date;
            return this;
        }

        public Builder setDomain(String domain) {
            AssertParameter.notNull(domain, "Cookie domain");
            this.domain = domain;
            return this;
        }

        public Builder setPath(String path) {
            AssertParameter.notNull(path, "Cookie path");
            this.path = path;
            return this;
        }

        public Builder setMaxAge(long maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        public Builder setSecured(boolean secured) {
            this.secured = secured;
            return this;
        }

        public Builder setHttpOnly(boolean httpOnly) {
            this.httpOnly = httpOnly;
            return this;
        }

        public Builder setVersion(int version) {
            if (version < 0) {
                throw new IllegalArgumentException("Version cannot be less than 0");
            }
            if (version > 1) {
                throw new IllegalArgumentException("Version cannot be greater than 1");
            }
            this.version = version;
            return this;
        }

        public Builder setSameSite(String sameSite) {
            AssertParameter.notNull(sameSite, "Cookie SameSite attribute");
            this.sameSite = sameSite;
            return this;
        }

        public Cookie build() {
            return new Cookie(this.name, this.value, this.comment, this.expiryDate, this.domain, this.path, this.secured, this.httpOnly, this.version, this.maxAge, this.sameSite);
        }
    }
}

