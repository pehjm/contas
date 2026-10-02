/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.support;

import io.restassured.config.ConnectionConfig;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.conn.ClientConnectionManager;

public class CloseHTTPClientConnectionInputStreamWrapper
extends InputStream {
    private static final int MINUS_ONE = -1;
    private static final int ZERO = 0;
    private ConnectionConfig connectionConfig;
    private final ClientConnectionManager connectionManager;
    private final InputStream wrapped;

    public CloseHTTPClientConnectionInputStreamWrapper(ConnectionConfig connectionConfig, ClientConnectionManager connectionManager, InputStream wrapped) {
        this.connectionConfig = connectionConfig;
        this.connectionManager = connectionManager;
        this.wrapped = wrapped;
    }

    @Override
    public int read() throws IOException {
        return this.wrapped == null ? -1 : this.wrapped.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.wrapped == null ? -1 : this.wrapped.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.wrapped == null ? -1 : this.wrapped.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return this.wrapped == null ? 0L : this.wrapped.skip(n);
    }

    @Override
    public int available() throws IOException {
        return this.wrapped == null ? 0 : this.wrapped.available();
    }

    @Override
    public void close() throws IOException {
        if (this.connectionManager != null && this.connectionConfig.shouldCloseIdleConnectionsAfterEachResponse()) {
            this.connectionManager.closeIdleConnections(this.connectionConfig.closeIdleConnectionConfig().getIdleTime(), this.connectionConfig.closeIdleConnectionConfig().getTimeUnit());
        }
        if (this.wrapped != null) {
            this.wrapped.close();
        }
    }

    @Override
    public void mark(int readlimit) {
        if (this.wrapped != null) {
            this.wrapped.mark(readlimit);
        }
    }

    @Override
    public void reset() throws IOException {
        if (this.wrapped != null) {
            this.wrapped.reset();
        }
    }

    @Override
    public boolean markSupported() {
        return this.wrapped != null && this.wrapped.markSupported();
    }
}

