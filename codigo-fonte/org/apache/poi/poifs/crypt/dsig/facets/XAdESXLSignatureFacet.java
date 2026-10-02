/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.xml.security.c14n.Canonicalizer
 *  org.bouncycastle.asn1.ASN1OctetString
 *  org.bouncycastle.asn1.DERTaggedObject
 *  org.bouncycastle.asn1.ocsp.ResponderID
 *  org.bouncycastle.asn1.x500.X500Name
 *  org.bouncycastle.cert.ocsp.BasicOCSPResp
 *  org.bouncycastle.cert.ocsp.OCSPResp
 *  org.bouncycastle.cert.ocsp.RespID
 */
package org.apache.poi.poifs.crypt.dsig.facets;

import java.io.OutputStream;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.crypto.MarshalException;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.XAdESSignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.RevocationData;
import org.apache.poi.poifs.crypt.dsig.services.RevocationDataService;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xmlbeans.XmlException;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.RespID;
import org.etsi.uri.x01903.v13.CRLIdentifierType;
import org.etsi.uri.x01903.v13.CRLRefType;
import org.etsi.uri.x01903.v13.CRLRefsType;
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;
import org.etsi.uri.x01903.v13.OCSPIdentifierType;
import org.etsi.uri.x01903.v13.OCSPRefType;
import org.etsi.uri.x01903.v13.OCSPRefsType;
import org.etsi.uri.x01903.v13.OCSPValuesType;
import org.etsi.uri.x01903.v13.QualifyingPropertiesDocument;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.ResponderIDType;
import org.etsi.uri.x01903.v13.RevocationValuesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;
import org.etsi.uri.x01903.v14.ValidationDataType;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XAdESXLSignatureFacet
implements SignatureFacet {
    private static final Logger LOG = LogManager.getLogger(XAdESXLSignatureFacet.class);
    private final CertificateFactory certificateFactory;

    public XAdESXLSignatureFacet() {
        try {
            this.certificateFactory = CertificateFactory.getInstance("X.509");
        }
        catch (CertificateException e) {
            throw new RuntimeException("X509 JCA error: " + e.getMessage(), e);
        }
    }

    @Override
    public void postSign(SignatureInfo signatureInfo, Document document) throws MarshalException {
        LOG.atDebug().log("XAdES-X-L post sign phase");
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        NodeList qualNl = document.getElementsByTagNameNS("http://uri.etsi.org/01903/v1.3.2#", "QualifyingProperties");
        QualifyingPropertiesType qualProps = this.getQualProps(qualNl);
        UnsignedPropertiesType unsignedProps = Optional.ofNullable(qualProps.getUnsignedProperties()).orElseGet(qualProps::addNewUnsignedProperties);
        UnsignedSignaturePropertiesType unsignedSigProps = Optional.ofNullable(unsignedProps.getUnsignedSignatureProperties()).orElseGet(unsignedProps::addNewUnsignedSignatureProperties);
        NodeList nlSigVal = document.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "SignatureValue");
        XAdESTimeStampType signatureTimeStamp = this.addTimestamp(nlSigVal, signatureInfo, unsignedSigProps);
        RevocationDataService revDataSvc = signatureConfig.getRevocationDataService();
        if (revDataSvc != null) {
            CompleteCertificateRefsType completeCertificateRefs = this.completeCertificateRefs(unsignedSigProps, signatureConfig);
            RevocationData revocationData = revDataSvc.getRevocationData(signatureConfig.getSigningCertificateChain());
            CompleteRevocationRefsType completeRevocationRefs = unsignedSigProps.addNewCompleteRevocationRefs();
            this.addRevocationCRL(completeRevocationRefs, signatureConfig, revocationData);
            this.addRevocationOCSP(completeRevocationRefs, signatureConfig, revocationData);
            this.addTimestampX(unsignedSigProps, signatureInfo, nlSigVal, signatureTimeStamp, completeCertificateRefs, completeRevocationRefs);
            this.addCertificateValues(unsignedSigProps, signatureConfig);
            RevocationValuesType revocationValues = unsignedSigProps.addNewRevocationValues();
            this.createRevocationValues(revocationValues, revocationData);
        }
        Node n = document.importNode(qualProps.getDomNode(), true);
        qualNl.item(0).getParentNode().replaceChild(n, qualNl.item(0));
    }

    private QualifyingPropertiesType getQualProps(NodeList qualNl) throws MarshalException {
        if (qualNl.getLength() != 1) {
            throw new MarshalException("no XAdES-BES extension present");
        }
        try {
            Node first = qualNl.item(0);
            QualifyingPropertiesDocument qualDoc = (QualifyingPropertiesDocument)QualifyingPropertiesDocument.Factory.parse(first, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            return qualDoc.getQualifyingProperties();
        }
        catch (XmlException e) {
            throw new MarshalException(e);
        }
    }

    private XAdESTimeStampType addTimestamp(NodeList nlSigVal, SignatureInfo signatureInfo, UnsignedSignaturePropertiesType unsignedSigProps) {
        if (nlSigVal.getLength() != 1) {
            throw new IllegalArgumentException("SignatureValue is not set.");
        }
        RevocationData tsaRevocationDataXadesT = new RevocationData();
        LOG.atDebug().log("creating XAdES-T time-stamp");
        XAdESTimeStampType signatureTimeStamp = this.createXAdESTimeStamp(signatureInfo, Collections.singletonList(nlSigVal.item(0)), tsaRevocationDataXadesT);
        unsignedSigProps.addNewSignatureTimeStamp().set(signatureTimeStamp);
        if (tsaRevocationDataXadesT.hasRevocationDataEntries()) {
            ValidationDataType validationData = this.createValidationData(tsaRevocationDataXadesT);
            XAdESSignatureFacet.insertXChild(unsignedSigProps, validationData);
        }
        return signatureTimeStamp;
    }

    private CompleteCertificateRefsType completeCertificateRefs(UnsignedSignaturePropertiesType unsignedSigProps, SignatureConfig signatureConfig) {
        CompleteCertificateRefsType completeCertificateRefs = unsignedSigProps.addNewCompleteCertificateRefs();
        CertIDListType certIdList = completeCertificateRefs.addNewCertRefs();
        List<X509Certificate> certChain = signatureConfig.getSigningCertificateChain();
        certChain.stream().skip(1L).forEachOrdered(cert -> XAdESSignatureFacet.setCertID(certIdList.addNewCert(), signatureConfig, false, cert));
        return completeCertificateRefs;
    }

    private void addRevocationCRL(CompleteRevocationRefsType completeRevocationRefs, SignatureConfig signatureConfig, RevocationData revocationData) {
        if (revocationData.hasCRLs()) {
            CRLRefsType crlRefs = completeRevocationRefs.addNewCRLRefs();
            completeRevocationRefs.setCRLRefs(crlRefs);
            for (byte[] encodedCrl : revocationData.getCRLs()) {
                X509CRL crl;
                CRLRefType crlRef = crlRefs.addNewCRLRef();
                try {
                    crl = (X509CRL)this.certificateFactory.generateCRL(new UnsynchronizedByteArrayInputStream(encodedCrl));
                }
                catch (CRLException e) {
                    throw new RuntimeException("CRL parse error: " + e.getMessage(), e);
                }
                CRLIdentifierType crlIdentifier = crlRef.addNewCRLIdentifier();
                String issuerName = crl.getIssuerDN().getName().replace(",", ", ");
                crlIdentifier.setIssuer(issuerName);
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
                cal.setTime(crl.getThisUpdate());
                crlIdentifier.setIssueTime(cal);
                crlIdentifier.setNumber(this.getCrlNumber(crl));
                DigestAlgAndValueType digestAlgAndValue = crlRef.addNewDigestAlgAndValue();
                XAdESSignatureFacet.setDigestAlgAndValue(digestAlgAndValue, encodedCrl, signatureConfig.getDigestAlgo());
            }
        }
    }

    private void addRevocationOCSP(CompleteRevocationRefsType completeRevocationRefs, SignatureConfig signatureConfig, RevocationData revocationData) {
        if (revocationData.hasOCSPs()) {
            OCSPRefsType ocspRefs = completeRevocationRefs.addNewOCSPRefs();
            for (byte[] ocsp : revocationData.getOCSPs()) {
                try {
                    OCSPRefType ocspRef = ocspRefs.addNewOCSPRef();
                    DigestAlgAndValueType digestAlgAndValue = ocspRef.addNewDigestAlgAndValue();
                    XAdESSignatureFacet.setDigestAlgAndValue(digestAlgAndValue, ocsp, signatureConfig.getDigestAlgo());
                    OCSPIdentifierType ocspIdentifier = ocspRef.addNewOCSPIdentifier();
                    OCSPResp ocspResp = new OCSPResp(ocsp);
                    BasicOCSPResp basicOcspResp = (BasicOCSPResp)ocspResp.getResponseObject();
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
                    cal.setTime(basicOcspResp.getProducedAt());
                    ocspIdentifier.setProducedAt(cal);
                    ResponderIDType responderId = ocspIdentifier.addNewResponderID();
                    RespID respId = basicOcspResp.getResponderId();
                    ResponderID ocspResponderId = respId.toASN1Primitive();
                    DERTaggedObject derTaggedObject = (DERTaggedObject)ocspResponderId.toASN1Primitive();
                    if (2 == derTaggedObject.getTagNo()) {
                        ASN1OctetString keyHashOctetString = (ASN1OctetString)derTaggedObject.getObject();
                        byte[] key = keyHashOctetString.getOctets();
                        responderId.setByKey(key);
                        continue;
                    }
                    X500Name name = X500Name.getInstance((Object)derTaggedObject.getObject());
                    String nameStr = name.toString();
                    responderId.setByName(nameStr);
                }
                catch (Exception e) {
                    throw new RuntimeException("OCSP decoding error: " + e.getMessage(), e);
                }
            }
        }
    }

    private void addTimestampX(UnsignedSignaturePropertiesType unsignedSigProps, SignatureInfo signatureInfo, NodeList nlSigVal, XAdESTimeStampType signatureTimeStamp, CompleteCertificateRefsType completeCertificateRefs, CompleteRevocationRefsType completeRevocationRefs) {
        ArrayList<Node> timeStampNodesXadesX1 = new ArrayList<Node>();
        timeStampNodesXadesX1.add(nlSigVal.item(0));
        timeStampNodesXadesX1.add(signatureTimeStamp.getDomNode());
        timeStampNodesXadesX1.add(completeCertificateRefs.getDomNode());
        timeStampNodesXadesX1.add(completeRevocationRefs.getDomNode());
        RevocationData tsaRevocationDataXadesX1 = new RevocationData();
        LOG.atDebug().log("creating XAdES-X time-stamp");
        XAdESTimeStampType timeStampXadesX1 = this.createXAdESTimeStamp(signatureInfo, timeStampNodesXadesX1, tsaRevocationDataXadesX1);
        if (tsaRevocationDataXadesX1.hasRevocationDataEntries()) {
            ValidationDataType timeStampXadesX1ValidationData = this.createValidationData(tsaRevocationDataXadesX1);
            XAdESSignatureFacet.insertXChild(unsignedSigProps, timeStampXadesX1ValidationData);
        }
        unsignedSigProps.addNewSigAndRefsTimeStamp().set(timeStampXadesX1);
    }

    private void addCertificateValues(UnsignedSignaturePropertiesType unsignedSigProps, SignatureConfig signatureConfig) {
        CertificateValuesType certificateValues = unsignedSigProps.addNewCertificateValues();
        for (X509Certificate certificate : signatureConfig.getSigningCertificateChain()) {
            EncapsulatedPKIDataType encapsulatedPKIDataType = certificateValues.addNewEncapsulatedX509Certificate();
            try {
                encapsulatedPKIDataType.setByteArrayValue(certificate.getEncoded());
            }
            catch (CertificateEncodingException e) {
                throw new RuntimeException("certificate encoding error: " + e.getMessage(), e);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static byte[] getC14nValue(List<Node> nodeList, String c14nAlgoId) {
        try (UnsynchronizedByteArrayOutputStream c14nValue = new UnsynchronizedByteArrayOutputStream();){
            for (Node node : nodeList) {
                Canonicalizer c14n = Canonicalizer.getInstance((String)c14nAlgoId);
                c14n.canonicalizeSubtree(node, (OutputStream)c14nValue);
            }
            Object object = c14nValue.toByteArray();
            return object;
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("c14n error: " + e.getMessage(), e);
        }
    }

    /*
     * Exception decompiling
     */
    private BigInteger getCrlNumber(X509CRL crl) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 3 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private XAdESTimeStampType createXAdESTimeStamp(SignatureInfo signatureInfo, List<Node> nodeList, RevocationData revocationData) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        byte[] c14nSignatureValueElement = XAdESXLSignatureFacet.getC14nValue(nodeList, signatureConfig.getXadesCanonicalizationMethod());
        return this.createXAdESTimeStamp(signatureInfo, c14nSignatureValueElement, revocationData);
    }

    private XAdESTimeStampType createXAdESTimeStamp(SignatureInfo signatureInfo, byte[] data, RevocationData revocationData) {
        byte[] timeStampToken;
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        try {
            timeStampToken = signatureConfig.getTspService().timeStamp(signatureInfo, data, revocationData);
        }
        catch (Exception e) {
            throw new RuntimeException("error while creating a time-stamp: " + e.getMessage(), e);
        }
        XAdESTimeStampType xadesTimeStamp = XAdESTimeStampType.Factory.newInstance();
        xadesTimeStamp.setId("time-stamp-" + UUID.randomUUID());
        CanonicalizationMethodType c14nMethod = xadesTimeStamp.addNewCanonicalizationMethod();
        c14nMethod.setAlgorithm(signatureConfig.getXadesCanonicalizationMethod());
        EncapsulatedPKIDataType encapsulatedTimeStamp = xadesTimeStamp.addNewEncapsulatedTimeStamp();
        encapsulatedTimeStamp.setByteArrayValue(timeStampToken);
        encapsulatedTimeStamp.setId("time-stamp-token-" + UUID.randomUUID());
        return xadesTimeStamp;
    }

    private ValidationDataType createValidationData(RevocationData revocationData) {
        ValidationDataType validationData = ValidationDataType.Factory.newInstance();
        RevocationValuesType revocationValues = validationData.addNewRevocationValues();
        this.createRevocationValues(revocationValues, revocationData);
        return validationData;
    }

    private void createRevocationValues(RevocationValuesType revocationValues, RevocationData revocationData) {
        if (revocationData.hasCRLs()) {
            CRLValuesType crlValues = revocationValues.addNewCRLValues();
            for (byte[] crl : revocationData.getCRLs()) {
                EncapsulatedPKIDataType encapsulatedCrlValue = crlValues.addNewEncapsulatedCRLValue();
                encapsulatedCrlValue.setByteArrayValue(crl);
            }
        }
        if (revocationData.hasOCSPs()) {
            OCSPValuesType ocspValues = revocationValues.addNewOCSPValues();
            for (byte[] ocsp : revocationData.getOCSPs()) {
                EncapsulatedPKIDataType encapsulatedOcspValue = ocspValues.addNewEncapsulatedOCSPValue();
                encapsulatedOcspValue.setByteArrayValue(ocsp);
            }
        }
    }
}

