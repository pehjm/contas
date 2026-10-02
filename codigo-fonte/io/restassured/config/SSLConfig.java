/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.util.SafeExceptionRethrower;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.lang3.Validate;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

public class SSLConfig
implements Config {
    private static final String SSL = "SSL";
    private static final int UNDEFINED_PORT = -1;
    private final Object pathToKeyStore;
    private final Object pathToTrustStore;
    private final String keyStorePassword;
    private final String trustStorePassword;
    private final String keyStoreType;
    private final String trustStoreType;
    private final int port;
    private final KeyStore trustStore;
    private final KeyStore keyStore;
    private final X509HostnameVerifier x509HostnameVerifier;
    private final boolean isUserConfigured;
    private final SSLSocketFactory sslSocketFactory;

    public SSLConfig keyStore(String pathToJks, String password) {
        Validate.notNull(pathToJks, "Path to JKS on the file system cannot be null", new Object[0]);
        Validate.notEmpty(password, "Password cannot be empty", new Object[0]);
        return new SSLConfig(pathToJks, this.pathToTrustStore, password, this.trustStorePassword, this.keyStoreType, this.trustStoreType, this.port, this.keyStore, this.trustStore, this.x509HostnameVerifier, this.sslSocketFactory, true);
    }

    public SSLConfig keyStore(File pathToJks, String password) {
        Validate.notNull(pathToJks, "Path to JKS on the file system cannot be null", new Object[0]);
        Validate.notEmpty(password, "Password cannot be empty", new Object[0]);
        return new SSLConfig(pathToJks, this.pathToTrustStore, password, this.trustStorePassword, this.keyStoreType, this.trustStoreType, this.port, this.keyStore, this.trustStore, this.x509HostnameVerifier, this.sslSocketFactory, true);
    }

    public SSLConfig keyStore(String password) {
        Validate.notEmpty(password, "Password cannot be empty", new Object[0]);
        return this.keyStore(System.getProperty("user.home") + File.separatorChar + ".keystore", password);
    }

    public SSLConfig trustStore(String pathToTrustStore, String password) {
        Validate.notNull(pathToTrustStore, "Path to trust store on the file system cannot be null", new Object[0]);
        Validate.notEmpty(password, "Password cannot be empty", new Object[0]);
        return new SSLConfig(this.pathToKeyStore, pathToTrustStore, this.keyStorePassword, password, this.keyStoreType, this.trustStoreType, this.port, this.keyStore, this.trustStore, this.x509HostnameVerifier, this.sslSocketFactory, true);
    }

    public SSLConfig trustStore(File pathToTrustStore, String password) {
        Validate.notNull(pathToTrustStore, "Path to trust store on the file system cannot be null", new Object[0]);
        Validate.notEmpty(password, "Password cannot be empty", new Object[0]);
        return new SSLConfig(this.pathToKeyStore, pathToTrustStore, this.keyStorePassword, password, this.keyStoreType, this.trustStoreType, this.port, this.keyStore, this.trustStore, this.x509HostnameVerifier, this.sslSocketFactory, true);
    }

    public SSLConfig() {
        this(null, null, null, null, KeyStore.getDefaultType(), KeyStore.getDefaultType(), -1, null, null, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER, null, false);
    }

    private SSLConfig(Object pathToKeyStore, Object pathToTrustStore, String keyStorePassword, String trustStorePassword, String keyStoreType, String trustStoreType, int port, KeyStore keystore, KeyStore trustStore, X509HostnameVerifier x509HostnameVerifier, SSLSocketFactory sslSocketFactory, boolean isUserConfigured) {
        AssertParameter.notNull(keyStoreType, "Certificate type");
        AssertParameter.notNull(x509HostnameVerifier, X509HostnameVerifier.class);
        this.pathToKeyStore = pathToKeyStore;
        this.keyStorePassword = keyStorePassword;
        this.trustStorePassword = trustStorePassword;
        this.pathToTrustStore = pathToTrustStore;
        this.keyStoreType = keyStoreType;
        this.trustStoreType = trustStoreType;
        this.port = port;
        this.trustStore = trustStore;
        this.keyStore = keystore;
        this.x509HostnameVerifier = x509HostnameVerifier;
        this.isUserConfigured = isUserConfigured;
        this.sslSocketFactory = sslSocketFactory;
    }

    public SSLConfig keystoreType(String keystoreType) {
        return new SSLConfig(this.pathToKeyStore, this.pathToTrustStore, this.keyStorePassword, this.trustStorePassword, keystoreType, this.trustStoreType, this.port, this.keyStore, this.trustStore, this.x509HostnameVerifier, this.sslSocketFactory, true);
    }

    public SSLConfig trustStoreType(String trustStoreType) {
        return new SSLConfig(this.pathToKeyStore, this.pathToTrustStore, this.keyStorePassword, this.trustStorePassword, this.keyStoreType, trustStoreType, this.port, this.keyStore, this.trustStore, this.x509HostnameVerifier, this.sslSocketFactory, true);
    }

    public SSLConfig port(int port) {
        return new SSLConfig(this.pathToKeyStore, this.pathToTrustStore, this.keyStorePassword, this.trustStorePassword, this.keyStoreType, this.trustStoreType, port, this.keyStore, this.trustStore, this.x509HostnameVerifier, this.sslSocketFactory, true);
    }

    public SSLConfig trustStore(KeyStore trustStore) {
        return new SSLConfig(this.pathToKeyStore, this.pathToTrustStore, this.keyStorePassword, this.trustStorePassword, this.keyStoreType, this.trustStoreType, this.port, this.keyStore, trustStore, this.x509HostnameVerifier, this.sslSocketFactory, true);
    }

    public SSLConfig relaxedHTTPSValidation() {
        return this.relaxedHTTPSValidation(SSL);
    }

    public SSLConfig relaxedHTTPSValidation(String protocol) {
        SSLContext sslContext;
        AssertParameter.notNull(protocol, "Protocol");
        try {
            sslContext = SSLContext.getInstance(protocol);
        }
        catch (NoSuchAlgorithmException e) {
            return (SSLConfig)SafeExceptionRethrower.safeRethrow(e);
        }
        try {
            KeyStore relaxedKeyStore = this.loadKeyStore();
            KeyManager[] keyManagers = null;
            if (relaxedKeyStore != null) {
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(relaxedKeyStore, this.keyStorePassword.toCharArray());
                keyManagers = kmf.getKeyManagers();
            }
            sslContext.init(keyManagers, new TrustManager[]{new X509TrustManager(){

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }}, new SecureRandom());
        }
        catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            return (SSLConfig)SafeExceptionRethrower.safeRethrow(e);
        }
        SSLSocketFactory sf = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return this.sslSocketFactory(sf);
    }

    public SSLConfig sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        AssertParameter.notNull(sslSocketFactory, SSLSocketFactory.class);
        return new SSLConfig(this.pathToKeyStore, this.pathToTrustStore, this.keyStorePassword, this.trustStorePassword, this.keyStoreType, this.trustStoreType, this.port, this.keyStore, this.trustStore, this.x509HostnameVerifier, sslSocketFactory, true);
    }

    public SSLConfig x509HostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        return new SSLConfig(this.pathToKeyStore, this.pathToTrustStore, this.keyStorePassword, this.trustStorePassword, this.keyStoreType, this.trustStoreType, this.port, this.keyStore, this.trustStore, x509HostnameVerifier, this.sslSocketFactory, true);
    }

    public SSLConfig strictHostnames() {
        return new SSLConfig(this.pathToKeyStore, this.pathToTrustStore, this.keyStorePassword, this.trustStorePassword, this.keyStoreType, this.trustStoreType, this.port, this.keyStore, this.trustStore, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER, this.sslSocketFactory, true);
    }

    public SSLConfig allowAllHostnames() {
        return new SSLConfig(this.pathToKeyStore, this.pathToTrustStore, this.keyStorePassword, this.trustStorePassword, this.keyStoreType, this.trustStoreType, this.port, this.keyStore, this.trustStore, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER, this.sslSocketFactory, true);
    }

    public static SSLConfig sslConfig() {
        return new SSLConfig();
    }

    public SSLConfig and() {
        return this;
    }

    public SSLConfig with() {
        return this;
    }

    public SSLConfig using() {
        return this;
    }

    public Object getPathToKeyStore() {
        return this.pathToKeyStore;
    }

    public Object getPathToTrustStore() {
        return this.pathToTrustStore;
    }

    public String getKeyStorePassword() {
        return this.keyStorePassword;
    }

    public String getTrustStorePassword() {
        return this.trustStorePassword;
    }

    public String getKeyStoreType() {
        return this.keyStoreType;
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

    public X509HostnameVerifier getX509HostnameVerifier() {
        return this.x509HostnameVerifier;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserConfigured;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private KeyStore loadKeyStore() {
        InputStream resource;
        if (this.pathToKeyStore == null) {
            return null;
        }
        try {
            if (this.pathToKeyStore instanceof String) {
                String keyStorePath = (String)this.pathToKeyStore;
                if (keyStorePath.trim().isEmpty()) {
                    return null;
                }
                resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStorePath);
                if (resource == null) {
                    resource = this.getClass().getResourceAsStream(keyStorePath);
                }
                if (resource == null) {
                    resource = new FileInputStream(keyStorePath);
                }
            } else {
                resource = new FileInputStream((File)this.pathToKeyStore);
            }
        }
        catch (FileNotFoundException e) {
            return (KeyStore)SafeExceptionRethrower.safeRethrow(e);
        }
        try {
            KeyStore relaxedKeyStore = KeyStore.getInstance(this.keyStoreType);
            relaxedKeyStore.load(resource, this.keyStorePassword.toCharArray());
            KeyStore keyStore = relaxedKeyStore;
            return keyStore;
        }
        catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            KeyStore keyStore = (KeyStore)SafeExceptionRethrower.safeRethrow(e);
            return keyStore;
        }
        finally {
            try {
                resource.close();
            }
            catch (IOException iOException) {}
        }
    }
}

