/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

import io.restassured.internal.common.assertion.AssertParameter;
import org.apache.commons.lang3.StringUtils;

public class ProxySpecification {
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static final String DEFAULT_SCHEME = "http";
    private static final String DEFAULT_USERNAME = null;
    private static final String DEFAULT_PASSWORD = null;
    private static final int DEFAULT_PORT = 8888;
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_HTTP_PORT = 80;
    private static final int DEFAULT_HTTPS_PORT = 443;
    private final String host;
    private final int port;
    private final String scheme;
    private final String username;
    private final String password;

    public ProxySpecification(String host, int port, String scheme) {
        this(host, port, scheme, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    private ProxySpecification(String host, int port, String scheme, String username, String password) {
        this.host = StringUtils.trimToNull(host);
        this.scheme = StringUtils.trimToNull(scheme);
        AssertParameter.notNull(this.host, "Proxy host");
        AssertParameter.notNull(this.scheme, "Proxy scheme");
        if (port < 1) {
            if (scheme.equalsIgnoreCase("http")) {
                port = 80;
            } else if (scheme.equalsIgnoreCase(HTTPS)) {
                port = 443;
            } else {
                throw new IllegalArgumentException("Cannot determine proxy port");
            }
        }
        this.username = StringUtils.trimToNull(username);
        this.password = StringUtils.trimToNull(password);
        this.port = port;
    }

    public static ProxySpecification host(String host) {
        return new ProxySpecification(host, 8888, "http", DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public static ProxySpecification port(int port) {
        return new ProxySpecification(DEFAULT_HOST, port, "http", DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public static ProxySpecification auth(String username, String password) {
        AssertParameter.notNull(username, "username");
        AssertParameter.notNull(password, "password");
        return new ProxySpecification(DEFAULT_HOST, 8888, "http", username, password);
    }

    public ProxySpecification withPort(int port) {
        return new ProxySpecification(this.host, port, this.scheme, this.username, this.password);
    }

    public ProxySpecification withHost(String host) {
        return new ProxySpecification(host, this.port, this.scheme, this.username, this.password);
    }

    public ProxySpecification withAuth(String username, String password) {
        AssertParameter.notNull(username, "username");
        AssertParameter.notNull(password, "password");
        return new ProxySpecification(this.host, this.port, this.scheme, username, password);
    }

    public ProxySpecification withScheme(String scheme) {
        return new ProxySpecification(this.host, this.port, scheme);
    }

    public ProxySpecification and() {
        return this;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    boolean hasAuth() {
        return this.username != null || this.password != null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProxySpecification)) {
            return false;
        }
        ProxySpecification that = (ProxySpecification)o;
        if (this.port != that.port) {
            return false;
        }
        if (this.host != null ? !this.host.equals(that.host) : that.host != null) {
            return false;
        }
        if (this.scheme != null ? !this.scheme.equals(that.scheme) : that.scheme != null) {
            return false;
        }
        if (this.username != null ? !this.username.equals(that.username) : that.username != null) {
            return false;
        }
        return !(this.password == null ? that.password != null : !this.password.equals(that.password));
    }

    public int hashCode() {
        int result = this.host != null ? this.host.hashCode() : 0;
        result = 31 * result + this.port;
        result = 31 * result + (this.scheme != null ? this.scheme.hashCode() : 0);
        result = 31 * result + (this.username != null ? this.username.hashCode() : 0);
        result = 31 * result + (this.password != null ? this.password.hashCode() : 0);
        return result;
    }

    public String toString() {
        return this.scheme + "://" + this.host + ":" + this.port;
    }
}

