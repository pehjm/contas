/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;

public class RedirectConfig
implements Config {
    private final boolean followRedirects;
    private final boolean allowCircularRedirects;
    private final boolean rejectRelativeRedirect;
    private final int maxRedirects;
    private final boolean isUserConfigured;

    public RedirectConfig() {
        this(true, false, false, 100, false);
    }

    public RedirectConfig(boolean followRedirects, boolean allowCircularRedirects, boolean rejectRelativeRedirect, int maxRedirects) {
        this(followRedirects, allowCircularRedirects, rejectRelativeRedirect, maxRedirects, true);
    }

    private RedirectConfig(boolean followRedirects, boolean allowCircularRedirects, boolean rejectRelativeRedirect, int maxRedirects, boolean isUserConfigured) {
        this.followRedirects = followRedirects;
        this.allowCircularRedirects = allowCircularRedirects;
        this.rejectRelativeRedirect = rejectRelativeRedirect;
        this.maxRedirects = maxRedirects;
        this.isUserConfigured = isUserConfigured;
    }

    public RedirectConfig followRedirects(boolean value) {
        return new RedirectConfig(value, this.allowCircularRedirects, this.rejectRelativeRedirect, this.maxRedirects, true);
    }

    public RedirectConfig allowCircularRedirects(boolean value) {
        return new RedirectConfig(this.followRedirects, value, this.rejectRelativeRedirect, this.maxRedirects, true);
    }

    public RedirectConfig rejectRelativeRedirect(boolean value) {
        return new RedirectConfig(this.followRedirects, this.allowCircularRedirects, value, this.maxRedirects, true);
    }

    public RedirectConfig maxRedirects(int value) {
        return new RedirectConfig(this.followRedirects, this.allowCircularRedirects, this.rejectRelativeRedirect, value, true);
    }

    public RedirectConfig and() {
        return this;
    }

    public boolean followsRedirects() {
        return this.followRedirects;
    }

    public boolean allowsCircularRedirects() {
        return this.allowCircularRedirects;
    }

    public boolean rejectRelativeRedirects() {
        return this.rejectRelativeRedirect;
    }

    public int maxRedirects() {
        return this.maxRedirects;
    }

    public static RedirectConfig redirectConfig() {
        return new RedirectConfig();
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }
}

