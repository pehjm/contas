/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.authentication;

import io.restassured.internal.common.assertion.AssertParameter;
import java.security.KeyStore;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

public class CertificateAuthSettings {
    private static final int UNDEFINED_PORT = -1;
    private final String keystoreType;
    private final String trustStoreType;
    private final int port;
    private final KeyStore trustStore;
    private final KeyStore keyStore;
    private final X509HostnameVerifier x509HostnameVerifier;
    private final SSLSocketFactory sslSocketFactory;

    public CertificateAuthSettings() {
        this(KeyStore.getDefaultType(), KeyStore.getDefaultType(), -1, null, null, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER, null);
    }

    private CertificateAuthSettings(String keystoreType, String trustStoreType, int port, KeyStore trustStore, KeyStore keyStore, X509HostnameVerifier x509HostnameVerifier, SSLSocketFactory sslSocketFactory) {
        AssertParameter.notNull(keystoreType, "Certificate type");
        AssertParameter.notNull(trustStoreType, "Trust store type");
        this.keystoreType = keystoreType;
        this.keyStore = keyStore;
        this.trustStoreType = trustStoreType;
        this.port = port;
        this.trustStore = trustStore;
        this.x509HostnameVerifier = x509HostnameVerifier;
        this.sslSocketFactory = sslSocketFactory;
    }

    public String getKeyStoreType() {
        return this.keystoreType;
    }

    public String getTrustStoreType() {
        return this.trustStoreType;
    }

    public int getPort() {
        return this.port;
    }

    public KeyStore getTrustStore() {
        return this.trustStore;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.sslSocketFactory;
    }

    public CertificateAuthSettings strictHostnames() {
        return new CertificateAuthSettings(this.keystoreType, this.trustStoreType, this.port, this.trustStore, this.keyStore, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER, this.sslSocketFactory);
    }

    public CertificateAuthSettings allowAllHostnames() {
        return new CertificateAuthSettings(this.keystoreType, this.trustStoreType, this.port, this.trustStore, this.keyStore, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER, this.sslSocketFactory);
    }

    public CertificateAuthSettings x509HostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        return new CertificateAuthSettings(this.keystoreType, this.trustStoreType, this.port, this.trustStore, this.keyStore, x509HostnameVerifier, this.sslSocketFactory);
    }

    public X509HostnameVerifier getX509HostnameVerifier() {
        return this.x509HostnameVerifier;
    }

    public CertificateAuthSettings keyStoreType(String keystoreType) {
        return new CertificateAuthSettings(keystoreType, this.trustStoreType, this.port, this.trustStore, this.keyStore, this.x509HostnameVerifier, this.sslSocketFactory);
    }

    public CertificateAuthSettings trustStoreType(String trustStoreType) {
        return new CertificateAuthSettings(this.keystoreType, trustStoreType, this.port, this.trustStore, this.keyStore, this.x509HostnameVerifier, this.sslSocketFactory);
    }

    public CertificateAuthSettings port(int port) {
        return new CertificateAuthSettings(this.keystoreType, this.trustStoreType, port, this.trustStore, this.keyStore, this.x509HostnameVerifier, this.sslSocketFactory);
    }

    public CertificateAuthSettings trustStore(KeyStore trustStore) {
        return new CertificateAuthSettings(this.keystoreType, this.trustStoreType, this.port, trustStore, this.keyStore, this.x509HostnameVerifier, this.sslSocketFactory);
    }

    public CertificateAuthSettings keyStore(KeyStore keyStore) {
        return new CertificateAuthSettings(this.keystoreType, this.trustStoreType, this.port, this.trustStore, keyStore, this.x509HostnameVerifier, this.sslSocketFactory);
    }

    public CertificateAuthSettings sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        return new CertificateAuthSettings(this.keystoreType, this.trustStoreType, this.port, this.trustStore, this.keyStore, this.x509HostnameVerifier, sslSocketFactory);
    }

    public CertificateAuthSettings and() {
        return this;
    }

    public CertificateAuthSettings with() {
        return this;
    }

    public CertificateAuthSettings using() {
        return this;
    }

    public static CertificateAuthSettings certAuthSettings() {
        return new CertificateAuthSettings();
    }
}

