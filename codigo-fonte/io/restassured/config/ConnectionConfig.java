/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.internal.common.assertion.AssertParameter;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;

public class ConnectionConfig
implements Config {
    private final CloseIdleConnectionConfig closeIdleConnectionConfig;
    private final boolean isUserConfigured;

    public ConnectionConfig() {
        this(null, false);
    }

    public ConnectionConfig(CloseIdleConnectionConfig closeIdleConnectionConfig) {
        this(AssertParameter.notNull(closeIdleConnectionConfig, CloseIdleConnectionConfig.class), true);
    }

    private ConnectionConfig(CloseIdleConnectionConfig closeIdleConnectionConfig, boolean isUserConfigured) {
        this.closeIdleConnectionConfig = closeIdleConnectionConfig;
        this.isUserConfigured = isUserConfigured;
    }

    public ConnectionConfig closeIdleConnectionsAfterEachResponse() {
        return new ConnectionConfig(new CloseIdleConnectionConfig(0L, TimeUnit.NANOSECONDS));
    }

    public ConnectionConfig closeIdleConnectionsAfterEachResponseAfter(long idleTime, TimeUnit timeUnit) {
        return new ConnectionConfig(new CloseIdleConnectionConfig(idleTime, timeUnit));
    }

    public ConnectionConfig closeIdleConnectionsAfterEachResponseAfter(CloseIdleConnectionConfig closeIdleConnectionConfig) {
        return new ConnectionConfig(closeIdleConnectionConfig);
    }

    public ConnectionConfig dontCloseIdleConnectionsAfterEachResponse() {
        return new ConnectionConfig(null);
    }

    public CloseIdleConnectionConfig closeIdleConnectionConfig() {
        return this.closeIdleConnectionConfig;
    }

    public boolean shouldCloseIdleConnectionsAfterEachResponse() {
        return this.closeIdleConnectionConfig() != null;
    }

    public static ConnectionConfig connectionConfig() {
        return new ConnectionConfig();
    }

    public ConnectionConfig and() {
        return this;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }

    public static class CloseIdleConnectionConfig {
        private final long idleTime;
        private final TimeUnit timeUnit;

        public CloseIdleConnectionConfig(long idleTime, TimeUnit timeUnit) {
            if (idleTime < 0L) {
                throw new IllegalArgumentException("Idle time cannot be less than 0.");
            }
            Validate.notNull(timeUnit, "Timeunit cannot be null", new Object[0]);
            this.idleTime = idleTime;
            this.timeUnit = timeUnit;
        }

        public long getIdleTime() {
            return this.idleTime;
        }

        public TimeUnit getTimeUnit() {
            return this.timeUnit;
        }
    }
}

