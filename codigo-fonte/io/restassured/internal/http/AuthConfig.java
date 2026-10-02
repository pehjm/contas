/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.scribejava.core.builder.api.DefaultApi10a
 *  com.github.scribejava.core.builder.api.DefaultApi20
 *  com.github.scribejava.core.model.AbstractRequest
 *  com.github.scribejava.core.model.OAuth1AccessToken
 *  com.github.scribejava.core.model.OAuth1RequestToken
 *  com.github.scribejava.core.model.OAuth2AccessToken
 *  com.github.scribejava.core.model.OAuthConfig
 *  com.github.scribejava.core.model.OAuthRequest
 *  com.github.scribejava.core.model.SignatureType
 *  com.github.scribejava.core.model.Token
 *  com.github.scribejava.core.model.Verb
 *  com.github.scribejava.core.oauth.OAuth10aService
 *  com.github.scribejava.core.oauth.OAuth20Service
 *  com.github.scribejava.core.oauth.OAuthService
 */
package io.restassured.internal.http;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.SignatureType;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth.OAuthService;
import io.restassured.authentication.OAuthSignature;
import io.restassured.config.OAuthConfig;
import io.restassured.internal.TrustAndKeystoreSpecImpl;
import io.restassured.internal.http.HTTPBuilder;
import io.restassured.internal.http.URIBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.EnumUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.protocol.HttpContext;

public class AuthConfig {
    private static final int UNDEFINED_PORT = -1;
    private static final int DEFAULT_HTTPS_PORT = 443;
    protected HTTPBuilder builder;
    private final OAuthConfig raOAuthConfig;

    public AuthConfig(HTTPBuilder builder, OAuthConfig restAssuredOAuthConfig) {
        this.builder = builder;
        this.raOAuthConfig = restAssuredOAuthConfig;
    }

    public void basic(String user, String pass) {
        URI uri = ((URIBuilder)this.builder.getUri()).toURI();
        if (uri == null) {
            throw new IllegalStateException("a default URI must be set");
        }
        this.basic(uri.getHost(), uri.getPort(), user, pass);
    }

    public void basic(String host, int port, String user, String pass) {
        this.builder.getClient().getCredentialsProvider().setCredentials(new AuthScope(host, port), new UsernamePasswordCredentials(user, pass));
    }

    public void ntlm(String user, String pass, String workstation, String domain) {
        URI uri = ((URIBuilder)this.builder.getUri()).toURI();
        if (uri == null) {
            throw new IllegalStateException("a default URI must be set");
        }
        this.ntlm(uri.getHost(), uri.getPort(), user, pass, workstation, domain);
    }

    public void ntlm(String host, int port, String user, String pass, String workstation, String domain) {
        this.builder.getClient().getCredentialsProvider().setCredentials(new AuthScope(host, port), new NTCredentials(user, pass, workstation, domain));
    }

    public void certificate(Object keyStorePath, String keyStorePassword, String keyStoreType, KeyStore keyStore, Object trustStorePath, String trustStorePassword, String trustStoreType, KeyStore trustStore, int port, X509HostnameVerifier hostnameVerifier, SSLSocketFactory sslConnectionSocketFactory) {
        TrustAndKeystoreSpecImpl spec = new TrustAndKeystoreSpecImpl();
        URI uri = ((URIBuilder)this.builder.getUri()).toURI();
        if (uri == null) {
            throw new IllegalStateException("a default URI must be set");
        }
        spec.setKeyStoreType(keyStoreType);
        spec.setKeyStorePassword(keyStorePassword);
        spec.setKeyStorePath(keyStorePath);
        spec.setKeyStore(keyStore);
        spec.setTrustStoreType(trustStoreType);
        spec.setTrustStorePassword(trustStorePassword);
        spec.setTrustStorePath(trustStorePath);
        spec.setTrustStore(trustStore);
        spec.setPort(port);
        spec.setX509HostnameVerifier(hostnameVerifier);
        spec.setFactory(sslConnectionSocketFactory);
        int portSpecifiedInUri = uri.getPort();
        spec.apply(this.builder, portSpecifiedInUri == -1 ? 443 : portSpecifiedInUri);
    }

    public void oauth(String consumerKey, String consumerSecret, String accessToken, String secretToken) {
        this.builder.client.removeRequestInterceptorByClass(OAuthSigner.class);
        if (consumerKey != null) {
            this.builder.client.addRequestInterceptor(new OAuthSigner(consumerKey, consumerSecret, accessToken, secretToken, OAuthSignature.HEADER, this.raOAuthConfig.shouldAddEmptyAccessOAuthTokenToBaseString()));
        }
    }

    public void oauth(String consumerKey, String consumerSecret, String accessToken, String secretToken, OAuthSignature signature) {
        this.builder.client.removeRequestInterceptorByClass(OAuthSigner.class);
        if (consumerKey != null) {
            this.builder.client.addRequestInterceptor(new OAuthSigner(consumerKey, consumerSecret, accessToken, secretToken, signature, this.raOAuthConfig.shouldAddEmptyAccessOAuthTokenToBaseString()));
        }
    }

    public void oauth2(String accessToken) {
        this.builder.client.removeRequestInterceptorByClass(OAuthSigner.class);
        if (accessToken != null) {
            this.builder.client.addRequestInterceptor(new OAuthSigner(accessToken, OAuthSignature.HEADER));
        }
    }

    public void oauth2(String accessToken, OAuthSignature signature) {
        this.builder.client.removeRequestInterceptorByClass(OAuthSigner.class);
        if (accessToken != null) {
            this.builder.client.addRequestInterceptor(new OAuthSigner(accessToken, signature));
        }
    }

    static class OAuthSigner
    implements HttpRequestInterceptor {
        protected com.github.scribejava.core.model.OAuthConfig oauthConfig;
        protected Token token;
        protected OAuth10aService service;
        protected SignatureType type = SignatureType.Header;
        protected OAuthSignature signature;
        protected boolean isOAuth1 = true;
        protected boolean addEmptyTokenToBaseString;

        public OAuthSigner(String consumerKey, String consumerSecret, String accessToken, String secretToken, OAuthSignature signature, Boolean addEmptyTokenToBaseString) {
            this.oauthConfig = new com.github.scribejava.core.model.OAuthConfig(consumerKey, consumerSecret, null, OAuthSigner.getOAuthSignatureType(signature), null, null, null, null, null, null, null);
            this.token = new OAuth1AccessToken(accessToken, secretToken);
            this.signature = signature;
            this.addEmptyTokenToBaseString = addEmptyTokenToBaseString;
        }

        public OAuthSigner(String accessToken, OAuthSignature signature) {
            this.token = new OAuth2AccessToken(accessToken, "");
            this.signature = signature;
            this.isOAuth1 = false;
        }

        @Override
        public void process(HttpRequest request, HttpContext ctx) throws HttpException, IOException {
            try {
                Object entity;
                Verb verb = EnumUtils.getEnum(Verb.class, request.getRequestLine().getMethod().toUpperCase());
                if (verb == null) {
                    return;
                }
                HttpHost host = (HttpHost)ctx.getAttribute("http.target_host");
                URI requestURI = new URI(host.toURI()).resolve(request.getRequestLine().getUri());
                OAuthRequest oauthRequest = new OAuthRequest(verb, requestURI.toString(), null);
                this.service = (OAuth10aService)this.getOauthService(this.isOAuth1, this.addEmptyTokenToBaseString);
                if (request instanceof HttpEntityEnclosingRequest && (entity = ((HttpEntityEnclosingRequest)request).getEntity()) != null) {
                    List<NameValuePair> list = URLEncodedUtils.parse((HttpEntity)entity);
                    for (NameValuePair param : list) {
                        String value = param.getValue() == null ? "" : param.getValue();
                        oauthRequest.addBodyParameter(param.getName(), value);
                    }
                }
                this.service.signRequest((OAuth1AccessToken)this.token, (AbstractRequest)oauthRequest);
                if (this.signature == OAuthSignature.HEADER) {
                    for (Map.Entry entry : oauthRequest.getHeaders().entrySet()) {
                        request.setHeader((String)entry.getKey(), (String)entry.getValue());
                    }
                } else {
                    URI uri = new URI(oauthRequest.getCompleteUrl());
                    if (request instanceof RequestWrapper) {
                        ((RequestWrapper)request).setURI(uri);
                    } else if (request instanceof HttpRequestBase) {
                        ((HttpRequestBase)request).setURI(uri);
                    }
                }
            }
            catch (URISyntaxException ex) {
                throw new HttpException("Error rebuilding request URI", ex);
            }
        }

        private OAuthService getOauthService(boolean oauth1, final boolean useEmptyOAuthToken) {
            OAuth20Service service;
            if (oauth1) {
                DefaultApi10a api = new DefaultApi10a(){

                    public String getRequestTokenEndpoint() {
                        return null;
                    }

                    public String getAuthorizationUrl(OAuth1RequestToken arg0) {
                        return null;
                    }

                    public String getAccessTokenEndpoint() {
                        return null;
                    }

                    public boolean isEmptyOAuthTokenParamIsRequired() {
                        return useEmptyOAuthToken;
                    }
                };
                service = new OAuth10aService(api, this.oauthConfig);
            } else {
                DefaultApi20 api = new DefaultApi20(){

                    public String getAuthorizationUrl(com.github.scribejava.core.model.OAuthConfig arg0) {
                        return null;
                    }

                    public String getAccessTokenEndpoint() {
                        return null;
                    }
                };
                service = new OAuth20Service(api, this.oauthConfig);
            }
            return service;
        }

        private static SignatureType getOAuthSignatureType(OAuthSignature signature) {
            SignatureType signatureType = signature == OAuthSignature.HEADER ? SignatureType.Header : SignatureType.QueryString;
            return signatureType;
        }
    }
}

