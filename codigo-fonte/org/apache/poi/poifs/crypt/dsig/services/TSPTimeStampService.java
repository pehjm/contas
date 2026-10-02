/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bouncycastle.asn1.ASN1ObjectIdentifier
 *  org.bouncycastle.asn1.cmp.PKIFailureInfo
 *  org.bouncycastle.asn1.nist.NISTObjectIdentifiers
 *  org.bouncycastle.asn1.x500.X500Name
 *  org.bouncycastle.asn1.x509.X509ObjectIdentifiers
 *  org.bouncycastle.cert.X509CertificateHolder
 *  org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
 *  org.bouncycastle.cms.CMSSignatureAlgorithmNameGenerator
 *  org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator
 *  org.bouncycastle.cms.SignerId
 *  org.bouncycastle.cms.SignerInformationVerifier
 *  org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder
 *  org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder
 *  org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder
 *  org.bouncycastle.operator.DigestAlgorithmIdentifierFinder
 *  org.bouncycastle.operator.DigestCalculatorProvider
 *  org.bouncycastle.operator.SignatureAlgorithmIdentifierFinder
 *  org.bouncycastle.operator.bc.BcDigestCalculatorProvider
 *  org.bouncycastle.tsp.TimeStampRequest
 *  org.bouncycastle.tsp.TimeStampRequestGenerator
 *  org.bouncycastle.tsp.TimeStampResponse
 *  org.bouncycastle.tsp.TimeStampToken
 */
package org.apache.poi.poifs.crypt.dsig.services;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.services.RevocationData;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampService;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.SignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;

public class TSPTimeStampService
implements TimeStampService {
    private static final Logger LOG = LogManager.getLogger(TSPTimeStampService.class);

    public ASN1ObjectIdentifier mapDigestAlgoToOID(HashAlgorithm digestAlgo) {
        switch (digestAlgo) {
            case sha1: {
                return X509ObjectIdentifiers.id_SHA1;
            }
            case sha256: {
                return NISTObjectIdentifiers.id_sha256;
            }
            case sha384: {
                return NISTObjectIdentifiers.id_sha384;
            }
            case sha512: {
                return NISTObjectIdentifiers.id_sha512;
            }
        }
        throw new IllegalArgumentException("unsupported digest algo: " + (Object)((Object)digestAlgo));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] timeStamp(SignatureInfo signatureInfo, byte[] data, RevocationData revocationData) throws Exception {
        byte[] responseBytes;
        String contentType;
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        MessageDigest messageDigest = CryptoFunctions.getMessageDigest(signatureConfig.getTspDigestAlgo());
        byte[] digest = messageDigest.digest(data);
        BigInteger nonce = new BigInteger(128, new SecureRandom());
        TimeStampRequestGenerator requestGenerator = new TimeStampRequestGenerator();
        requestGenerator.setCertReq(true);
        String requestPolicy = signatureConfig.getTspRequestPolicy();
        if (requestPolicy != null) {
            requestGenerator.setReqPolicy(new ASN1ObjectIdentifier(requestPolicy));
        }
        ASN1ObjectIdentifier digestAlgoOid = this.mapDigestAlgoToOID(signatureConfig.getTspDigestAlgo());
        TimeStampRequest request = requestGenerator.generate(digestAlgoOid, digest, nonce);
        byte[] encodedRequest = request.getEncoded();
        Proxy proxy = Proxy.NO_PROXY;
        if (signatureConfig.getProxyUrl() != null) {
            URL proxyUrl = new URL(signatureConfig.getProxyUrl());
            String host = proxyUrl.getHost();
            int port = proxyUrl.getPort();
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(InetAddress.getByName(host), port == -1 ? 80 : port));
        }
        HttpURLConnection huc = (HttpURLConnection)new URL(signatureConfig.getTspUrl()).openConnection(proxy);
        try {
            if (signatureConfig.getTspUser() != null) {
                String userPassword = signatureConfig.getTspUser() + ":" + signatureConfig.getTspPass();
                String encoding = Base64.getEncoder().encodeToString(userPassword.getBytes(StandardCharsets.ISO_8859_1));
                huc.setRequestProperty("Authorization", "Basic " + encoding);
            }
            huc.setRequestMethod("POST");
            huc.setConnectTimeout(20000);
            huc.setReadTimeout(20000);
            huc.setDoOutput(true);
            huc.setRequestProperty("User-Agent", signatureConfig.getUserAgent());
            huc.setRequestProperty("Content-Type", signatureConfig.isTspOldProtocol() ? "application/timestamp-request" : "application/timestamp-query");
            OutputStream hucOut = huc.getOutputStream();
            hucOut.write(encodedRequest);
            huc.connect();
            int statusCode = huc.getResponseCode();
            if (statusCode != 200) {
                String message = "Error contacting TSP server " + signatureConfig.getTspUrl() + ", had status code " + statusCode + "/" + huc.getResponseMessage();
                LOG.atError().log(message);
                throw new IOException(message);
            }
            contentType = huc.getHeaderField("Content-Type");
            if (null == contentType) {
                throw new RuntimeException("missing Content-Type header");
            }
            responseBytes = IOUtils.toByteArray(huc.getInputStream());
            LOG.atDebug().log(() -> new SimpleMessage("response content: " + HexDump.dump(responseBytes, 0L, 0)));
        }
        finally {
            huc.disconnect();
        }
        if (!contentType.startsWith(signatureConfig.isTspOldProtocol() ? "application/timestamp-response" : "application/timestamp-reply")) {
            throw new RuntimeException("invalid Content-Type: " + contentType + ": " + HexDump.dump(responseBytes, 0L, 0, 200));
        }
        if (responseBytes.length == 0) {
            throw new RuntimeException("Content-Length is zero");
        }
        TimeStampResponse timeStampResponse = new TimeStampResponse(responseBytes);
        timeStampResponse.validate(request);
        if (0 != timeStampResponse.getStatus()) {
            LOG.atDebug().log("status: {}", (Object)Unbox.box(timeStampResponse.getStatus()));
            LOG.atDebug().log("status string: {}", (Object)timeStampResponse.getStatusString());
            PKIFailureInfo failInfo = timeStampResponse.getFailInfo();
            if (null != failInfo) {
                LOG.atDebug().log("fail info int value: {}", (Object)Unbox.box(failInfo.intValue()));
                if (256 == failInfo.intValue()) {
                    LOG.atDebug().log("unaccepted policy");
                }
            }
            throw new RuntimeException("timestamp response status != 0: " + timeStampResponse.getStatus());
        }
        TimeStampToken timeStampToken = timeStampResponse.getTimeStampToken();
        SignerId signerId = timeStampToken.getSID();
        BigInteger signerCertSerialNumber = signerId.getSerialNumber();
        X500Name signerCertIssuer = signerId.getIssuer();
        LOG.atDebug().log("signer cert serial number: {}", (Object)signerCertSerialNumber);
        LOG.atDebug().log("signer cert issuer: {}", (Object)signerCertIssuer);
        Collection certificates = timeStampToken.getCertificates().getMatches(null);
        X509CertificateHolder signerCert = null;
        HashMap<X500Name, X509CertificateHolder> certificateMap = new HashMap<X500Name, X509CertificateHolder>();
        for (X509CertificateHolder certificate : certificates) {
            if (signerCertIssuer.equals((Object)certificate.getIssuer()) && signerCertSerialNumber.equals(certificate.getSerialNumber())) {
                signerCert = certificate;
            }
            certificateMap.put(certificate.getSubject(), certificate);
        }
        if (signerCert == null) {
            throw new RuntimeException("TSP response token has no signer certificate");
        }
        ArrayList<X509Certificate> tspCertificateChain = new ArrayList<X509Certificate>();
        JcaX509CertificateConverter x509converter = new JcaX509CertificateConverter();
        x509converter.setProvider("BC");
        X509CertificateHolder certificate = signerCert;
        do {
            LOG.atDebug().log("adding to certificate chain: {}", (Object)certificate.getSubject());
            tspCertificateChain.add(x509converter.getCertificate(certificate));
        } while (!certificate.getSubject().equals((Object)certificate.getIssuer()) && null != (certificate = (X509CertificateHolder)certificateMap.get(certificate.getIssuer())));
        X509CertificateHolder holder = new X509CertificateHolder(((X509Certificate)tspCertificateChain.get(0)).getEncoded());
        DefaultCMSSignatureAlgorithmNameGenerator nameGen = new DefaultCMSSignatureAlgorithmNameGenerator();
        DefaultSignatureAlgorithmIdentifierFinder sigAlgoFinder = new DefaultSignatureAlgorithmIdentifierFinder();
        DefaultDigestAlgorithmIdentifierFinder hashAlgoFinder = new DefaultDigestAlgorithmIdentifierFinder();
        BcDigestCalculatorProvider calculator = new BcDigestCalculatorProvider();
        BcRSASignerInfoVerifierBuilder verifierBuilder = new BcRSASignerInfoVerifierBuilder((CMSSignatureAlgorithmNameGenerator)nameGen, (SignatureAlgorithmIdentifierFinder)sigAlgoFinder, (DigestAlgorithmIdentifierFinder)hashAlgoFinder, (DigestCalculatorProvider)calculator);
        SignerInformationVerifier verifier = verifierBuilder.build(holder);
        timeStampToken.validate(verifier);
        if (signatureConfig.getTspValidator() != null) {
            signatureConfig.getTspValidator().validate(tspCertificateChain, revocationData);
        }
        LOG.atDebug().log("time-stamp token time: {}", (Object)timeStampToken.getTimeStampInfo().getGenTime());
        return timeStampToken.getEncoded();
    }
}

