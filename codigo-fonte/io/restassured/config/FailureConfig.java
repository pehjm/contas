/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.internal.LogRequestAndResponseOnFailListener;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.listener.ResponseValidationFailureListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FailureConfig
implements Config {
    private static ResponseValidationFailureListener DEFAULT_LOG_LISTENER = new LogRequestAndResponseOnFailListener();
    private List<ResponseValidationFailureListener> failureListeners = new ArrayList<ResponseValidationFailureListener>();
    private final boolean isUserConfigured;

    public FailureConfig() {
        this(new ArrayList<ResponseValidationFailureListener>(), false);
    }

    public FailureConfig(List<ResponseValidationFailureListener> failureListeners) {
        this(failureListeners, true);
    }

    private FailureConfig(List<ResponseValidationFailureListener> failureListeners, boolean isUserConfigured) {
        this.isUserConfigured = isUserConfigured;
        this.failureListeners = new ArrayList<ResponseValidationFailureListener>();
        this.failureListeners.addAll(failureListeners);
        this.failureListeners.add(DEFAULT_LOG_LISTENER);
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }

    public List<ResponseValidationFailureListener> getFailureListeners() {
        return this.failureListeners;
    }

    public static FailureConfig failureConfig() {
        return new FailureConfig();
    }

    public FailureConfig with() {
        return this;
    }

    public FailureConfig failureListeners(Collection<ResponseValidationFailureListener> responseValidationFailureListeners) {
        return new FailureConfig(new ArrayList<ResponseValidationFailureListener>(responseValidationFailureListeners), true);
    }

    public FailureConfig failureListeners(ResponseValidationFailureListener first, ResponseValidationFailureListener ... more) {
        AssertParameter.notNull(first, ResponseValidationFailureListener.class.getSimpleName());
        ArrayList<ResponseValidationFailureListener> listeners = new ArrayList<ResponseValidationFailureListener>();
        listeners.add(first);
        if (more != null) {
            Collections.addAll(listeners, more);
        }
        return this.failureListeners(listeners);
    }
}

