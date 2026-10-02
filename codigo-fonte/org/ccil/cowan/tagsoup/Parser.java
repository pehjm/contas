/*
 * Decompiled with CFR 0.152.
 */
package org.ccil.cowan.tagsoup;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import org.ccil.cowan.tagsoup.AttributesImpl;
import org.ccil.cowan.tagsoup.AutoDetector;
import org.ccil.cowan.tagsoup.Element;
import org.ccil.cowan.tagsoup.ElementType;
import org.ccil.cowan.tagsoup.HTMLScanner;
import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.ScanHandler;
import org.ccil.cowan.tagsoup.Scanner;
import org.ccil.cowan.tagsoup.Schema;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public class Parser
extends DefaultHandler
implements ScanHandler,
XMLReader,
LexicalHandler {
    private ContentHandler theContentHandler = this;
    private LexicalHandler theLexicalHandler = this;
    private DTDHandler theDTDHandler = this;
    private ErrorHandler theErrorHandler = this;
    private EntityResolver theEntityResolver = this;
    private Schema theSchema;
    private Scanner theScanner;
    private AutoDetector theAutoDetector;
    private static boolean DEFAULT_NAMESPACES = true;
    private static boolean DEFAULT_IGNORE_BOGONS = false;
    private static boolean DEFAULT_BOGONS_EMPTY = false;
    private static boolean DEFAULT_ROOT_BOGONS = true;
    private static boolean DEFAULT_DEFAULT_ATTRIBUTES = true;
    private static boolean DEFAULT_TRANSLATE_COLONS = false;
    private static boolean DEFAULT_RESTART_ELEMENTS = true;
    private static boolean DEFAULT_IGNORABLE_WHITESPACE = false;
    private static boolean DEFAULT_CDATA_ELEMENTS = true;
    private boolean namespaces = DEFAULT_NAMESPACES;
    private boolean ignoreBogons = DEFAULT_IGNORE_BOGONS;
    private boolean bogonsEmpty = DEFAULT_BOGONS_EMPTY;
    private boolean rootBogons = DEFAULT_ROOT_BOGONS;
    private boolean defaultAttributes = DEFAULT_DEFAULT_ATTRIBUTES;
    private boolean translateColons = DEFAULT_TRANSLATE_COLONS;
    private boolean restartElements = DEFAULT_RESTART_ELEMENTS;
    private boolean ignorableWhitespace = DEFAULT_IGNORABLE_WHITESPACE;
    private boolean CDATAElements = DEFAULT_CDATA_ELEMENTS;
    public static final String namespacesFeature = "http://xml.org/sax/features/namespaces";
    public static final String namespacePrefixesFeature = "http://xml.org/sax/features/namespace-prefixes";
    public static final String externalGeneralEntitiesFeature = "http://xml.org/sax/features/external-general-entities";
    public static final String externalParameterEntitiesFeature = "http://xml.org/sax/features/external-parameter-entities";
    public static final String isStandaloneFeature = "http://xml.org/sax/features/is-standalone";
    public static final String lexicalHandlerParameterEntitiesFeature = "http://xml.org/sax/features/lexical-handler/parameter-entities";
    public static final String resolveDTDURIsFeature = "http://xml.org/sax/features/resolve-dtd-uris";
    public static final String stringInterningFeature = "http://xml.org/sax/features/string-interning";
    public static final String useAttributes2Feature = "http://xml.org/sax/features/use-attributes2";
    public static final String useLocator2Feature = "http://xml.org/sax/features/use-locator2";
    public static final String useEntityResolver2Feature = "http://xml.org/sax/features/use-entity-resolver2";
    public static final String validationFeature = "http://xml.org/sax/features/validation";
    public static final String unicodeNormalizationCheckingFeature = "http://xml.org/sax/features/unicode-normalization-checking";
    public static final String xmlnsURIsFeature = "http://xml.org/sax/features/xmlns-uris";
    public static final String XML11Feature = "http://xml.org/sax/features/xml-1.1";
    public static final String ignoreBogonsFeature = "http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons";
    public static final String bogonsEmptyFeature = "http://www.ccil.org/~cowan/tagsoup/features/bogons-empty";
    public static final String rootBogonsFeature = "http://www.ccil.org/~cowan/tagsoup/features/root-bogons";
    public static final String defaultAttributesFeature = "http://www.ccil.org/~cowan/tagsoup/features/default-attributes";
    public static final String translateColonsFeature = "http://www.ccil.org/~cowan/tagsoup/features/translate-colons";
    public static final String restartElementsFeature = "http://www.ccil.org/~cowan/tagsoup/features/restart-elements";
    public static final String ignorableWhitespaceFeature = "http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace";
    public static final String CDATAElementsFeature = "http://www.ccil.org/~cowan/tagsoup/features/cdata-elements";
    public static final String lexicalHandlerProperty = "http://xml.org/sax/properties/lexical-handler";
    public static final String scannerProperty = "http://www.ccil.org/~cowan/tagsoup/properties/scanner";
    public static final String schemaProperty = "http://www.ccil.org/~cowan/tagsoup/properties/schema";
    public static final String autoDetectorProperty = "http://www.ccil.org/~cowan/tagsoup/properties/auto-detector";
    private HashMap theFeatures = new HashMap();
    private Element theNewElement;
    private String theAttributeName;
    private boolean theDoctypeIsPresent;
    private String theDoctypePublicId;
    private String theDoctypeSystemId;
    private String theDoctypeName;
    private String thePITarget;
    private Element theStack;
    private Element theSaved;
    private Element thePCDATA;
    private int theEntity;
    private static char[] etagchars = new char[]{'<', '/', '>'};
    private boolean virginStack;
    private static String legal = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-'()+,./:=?;!*#@$_%";
    private char[] theCommentBuffer;

    public Parser() {
        this.theFeatures.put(namespacesFeature, Parser.truthValue(DEFAULT_NAMESPACES));
        this.theFeatures.put(namespacePrefixesFeature, Boolean.FALSE);
        this.theFeatures.put(externalGeneralEntitiesFeature, Boolean.FALSE);
        this.theFeatures.put(externalParameterEntitiesFeature, Boolean.FALSE);
        this.theFeatures.put(isStandaloneFeature, Boolean.FALSE);
        this.theFeatures.put(lexicalHandlerParameterEntitiesFeature, Boolean.FALSE);
        this.theFeatures.put(resolveDTDURIsFeature, Boolean.TRUE);
        this.theFeatures.put(stringInterningFeature, Boolean.TRUE);
        this.theFeatures.put(useAttributes2Feature, Boolean.FALSE);
        this.theFeatures.put(useLocator2Feature, Boolean.FALSE);
        this.theFeatures.put(useEntityResolver2Feature, Boolean.FALSE);
        this.theFeatures.put(validationFeature, Boolean.FALSE);
        this.theFeatures.put(xmlnsURIsFeature, Boolean.FALSE);
        this.theFeatures.put(xmlnsURIsFeature, Boolean.FALSE);
        this.theFeatures.put(XML11Feature, Boolean.FALSE);
        this.theFeatures.put(ignoreBogonsFeature, Parser.truthValue(DEFAULT_IGNORE_BOGONS));
        this.theFeatures.put(bogonsEmptyFeature, Parser.truthValue(DEFAULT_BOGONS_EMPTY));
        this.theFeatures.put(rootBogonsFeature, Parser.truthValue(DEFAULT_ROOT_BOGONS));
        this.theFeatures.put(defaultAttributesFeature, Parser.truthValue(DEFAULT_DEFAULT_ATTRIBUTES));
        this.theFeatures.put(translateColonsFeature, Parser.truthValue(DEFAULT_TRANSLATE_COLONS));
        this.theFeatures.put(restartElementsFeature, Parser.truthValue(DEFAULT_RESTART_ELEMENTS));
        this.theFeatures.put(ignorableWhitespaceFeature, Parser.truthValue(DEFAULT_IGNORABLE_WHITESPACE));
        this.theFeatures.put(CDATAElementsFeature, Parser.truthValue(DEFAULT_CDATA_ELEMENTS));
        this.theNewElement = null;
        this.theAttributeName = null;
        this.theDoctypeIsPresent = false;
        this.theDoctypePublicId = null;
        this.theDoctypeSystemId = null;
        this.theDoctypeName = null;
        this.thePITarget = null;
        this.theStack = null;
        this.theSaved = null;
        this.thePCDATA = null;
        this.theEntity = 0;
        this.virginStack = true;
        this.theCommentBuffer = new char[2000];
    }

    private static Boolean truthValue(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        Boolean b = (Boolean)this.theFeatures.get(name);
        if (b == null) {
            throw new SAXNotRecognizedException("Unknown feature " + name);
        }
        return b;
    }

    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        Boolean b = (Boolean)this.theFeatures.get(name);
        if (b == null) {
            throw new SAXNotRecognizedException("Unknown feature " + name);
        }
        if (value) {
            this.theFeatures.put(name, Boolean.TRUE);
        } else {
            this.theFeatures.put(name, Boolean.FALSE);
        }
        if (name.equals(namespacesFeature)) {
            this.namespaces = value;
        } else if (name.equals(ignoreBogonsFeature)) {
            this.ignoreBogons = value;
        } else if (name.equals(bogonsEmptyFeature)) {
            this.bogonsEmpty = value;
        } else if (name.equals(rootBogonsFeature)) {
            this.rootBogons = value;
        } else if (name.equals(defaultAttributesFeature)) {
            this.defaultAttributes = value;
        } else if (name.equals(translateColonsFeature)) {
            this.translateColons = value;
        } else if (name.equals(restartElementsFeature)) {
            this.restartElements = value;
        } else if (name.equals(ignorableWhitespaceFeature)) {
            this.ignorableWhitespace = value;
        } else if (name.equals(CDATAElementsFeature)) {
            this.CDATAElements = value;
        }
    }

    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals(lexicalHandlerProperty)) {
            return this.theLexicalHandler == this ? null : this.theLexicalHandler;
        }
        if (name.equals(scannerProperty)) {
            return this.theScanner;
        }
        if (name.equals(schemaProperty)) {
            return this.theSchema;
        }
        if (name.equals(autoDetectorProperty)) {
            return this.theAutoDetector;
        }
        throw new SAXNotRecognizedException("Unknown property " + name);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals(lexicalHandlerProperty)) {
            if (value == null) {
                this.theLexicalHandler = this;
                return;
            } else {
                if (!(value instanceof LexicalHandler)) throw new SAXNotSupportedException("Your lexical handler is not a LexicalHandler");
                this.theLexicalHandler = (LexicalHandler)value;
            }
            return;
        } else if (name.equals(scannerProperty)) {
            if (!(value instanceof Scanner)) throw new SAXNotSupportedException("Your scanner is not a Scanner");
            this.theScanner = (Scanner)value;
            return;
        } else if (name.equals(schemaProperty)) {
            if (!(value instanceof Schema)) throw new SAXNotSupportedException("Your schema is not a Schema");
            this.theSchema = (Schema)value;
            return;
        } else {
            if (!name.equals(autoDetectorProperty)) throw new SAXNotRecognizedException("Unknown property " + name);
            if (!(value instanceof AutoDetector)) throw new SAXNotSupportedException("Your auto-detector is not an AutoDetector");
            this.theAutoDetector = (AutoDetector)value;
        }
    }

    public void setEntityResolver(EntityResolver resolver) {
        this.theEntityResolver = resolver == null ? this : resolver;
    }

    public EntityResolver getEntityResolver() {
        return this.theEntityResolver == this ? null : this.theEntityResolver;
    }

    public void setDTDHandler(DTDHandler handler) {
        this.theDTDHandler = handler == null ? this : handler;
    }

    public DTDHandler getDTDHandler() {
        return this.theDTDHandler == this ? null : this.theDTDHandler;
    }

    public void setContentHandler(ContentHandler handler) {
        this.theContentHandler = handler == null ? this : handler;
    }

    public ContentHandler getContentHandler() {
        return this.theContentHandler == this ? null : this.theContentHandler;
    }

    public void setErrorHandler(ErrorHandler handler) {
        this.theErrorHandler = handler == null ? this : handler;
    }

    public ErrorHandler getErrorHandler() {
        return this.theErrorHandler == this ? null : this.theErrorHandler;
    }

    public void parse(InputSource input) throws IOException, SAXException {
        this.setup();
        Reader r = this.getReader(input);
        this.theContentHandler.startDocument();
        this.theScanner.resetDocumentLocator(input.getPublicId(), input.getSystemId());
        if (this.theScanner instanceof Locator) {
            this.theContentHandler.setDocumentLocator((Locator)((Object)this.theScanner));
        }
        if (!this.theSchema.getURI().equals("")) {
            this.theContentHandler.startPrefixMapping(this.theSchema.getPrefix(), this.theSchema.getURI());
        }
        this.theScanner.scan(r, this);
    }

    public void parse(String systemid) throws IOException, SAXException {
        this.parse(new InputSource(systemid));
    }

    private void setup() {
        if (this.theSchema == null) {
            this.theSchema = new HTMLSchema();
        }
        if (this.theScanner == null) {
            this.theScanner = new HTMLScanner();
        }
        if (this.theAutoDetector == null) {
            this.theAutoDetector = new AutoDetector(){

                public Reader autoDetectingReader(InputStream i) {
                    return new InputStreamReader(i);
                }
            };
        }
        this.theStack = new Element(this.theSchema.getElementType("<root>"), this.defaultAttributes);
        this.thePCDATA = new Element(this.theSchema.getElementType("<pcdata>"), this.defaultAttributes);
        this.theNewElement = null;
        this.theAttributeName = null;
        this.thePITarget = null;
        this.theSaved = null;
        this.theEntity = 0;
        this.virginStack = true;
        this.theDoctypeSystemId = null;
        this.theDoctypePublicId = null;
        this.theDoctypeName = null;
    }

    private Reader getReader(InputSource s) throws SAXException, IOException {
        Reader r = s.getCharacterStream();
        InputStream i = s.getByteStream();
        String encoding = s.getEncoding();
        String publicid = s.getPublicId();
        String systemid = s.getSystemId();
        if (r == null) {
            if (i == null) {
                i = this.getInputStream(publicid, systemid);
            }
            if (encoding == null) {
                r = this.theAutoDetector.autoDetectingReader(i);
            } else {
                try {
                    r = new InputStreamReader(i, encoding);
                }
                catch (UnsupportedEncodingException e) {
                    r = new InputStreamReader(i);
                }
            }
        }
        return r;
    }

    private InputStream getInputStream(String publicid, String systemid) throws IOException, SAXException {
        URL basis = new URL("file", "", System.getProperty("user.dir") + "/.");
        URL url = new URL(basis, systemid);
        URLConnection c = url.openConnection();
        return c.getInputStream();
    }

    public void adup(char[] buff, int offset, int length) throws SAXException {
        if (this.theNewElement == null || this.theAttributeName == null) {
            return;
        }
        this.theNewElement.setAttribute(this.theAttributeName, null, this.theAttributeName);
        this.theAttributeName = null;
    }

    public void aname(char[] buff, int offset, int length) throws SAXException {
        if (this.theNewElement == null) {
            return;
        }
        this.theAttributeName = this.makeName(buff, offset, length).toLowerCase();
    }

    public void aval(char[] buff, int offset, int length) throws SAXException {
        if (this.theNewElement == null || this.theAttributeName == null) {
            return;
        }
        String value = new String(buff, offset, length);
        value = this.expandEntities(value);
        this.theNewElement.setAttribute(this.theAttributeName, null, value);
        this.theAttributeName = null;
    }

    private String expandEntities(String src) {
        int refStart = -1;
        int len = src.length();
        char[] dst = new char[len];
        int dstlen = 0;
        for (int i = 0; i < len; ++i) {
            char ch = src.charAt(i);
            dst[dstlen++] = ch;
            if (ch == '&' && refStart == -1) {
                refStart = dstlen;
                continue;
            }
            if (refStart == -1 || Character.isLetter(ch) || Character.isDigit(ch) || ch == '#') continue;
            if (ch == ';') {
                int ent = this.lookupEntity(dst, refStart, dstlen - refStart - 1);
                if (ent > 65535) {
                    dst[refStart - 1] = (char)(((ent -= 65536) >> 10) + 55296);
                    dst[refStart] = (char)((ent & 0x3FF) + 56320);
                    dstlen = refStart + 1;
                } else if (ent != 0) {
                    dst[refStart - 1] = (char)ent;
                    dstlen = refStart;
                }
                refStart = -1;
                continue;
            }
            refStart = -1;
        }
        return new String(dst, 0, dstlen);
    }

    public void entity(char[] buff, int offset, int length) throws SAXException {
        this.theEntity = this.lookupEntity(buff, offset, length);
    }

    private int lookupEntity(char[] buff, int offset, int length) {
        int result = 0;
        if (length < 1) {
            return result;
        }
        if (buff[offset] == '#') {
            if (length > 1 && (buff[offset + 1] == 'x' || buff[offset + 1] == 'X')) {
                try {
                    return Integer.parseInt(new String(buff, offset + 2, length - 2), 16);
                }
                catch (NumberFormatException e) {
                    return 0;
                }
            }
            try {
                return Integer.parseInt(new String(buff, offset + 1, length - 1), 10);
            }
            catch (NumberFormatException e) {
                return 0;
            }
        }
        return this.theSchema.getEntity(new String(buff, offset, length));
    }

    public void eof(char[] buff, int offset, int length) throws SAXException {
        if (this.virginStack) {
            this.rectify(this.thePCDATA);
        }
        while (this.theStack.next() != null) {
            this.pop();
        }
        if (!this.theSchema.getURI().equals("")) {
            this.theContentHandler.endPrefixMapping(this.theSchema.getPrefix());
        }
        this.theContentHandler.endDocument();
    }

    public void etag(char[] buff, int offset, int length) throws SAXException {
        if (this.etag_cdata(buff, offset, length)) {
            return;
        }
        this.etag_basic(buff, offset, length);
    }

    public boolean etag_cdata(char[] buff, int offset, int length) throws SAXException {
        String currentName = this.theStack.name();
        if (this.CDATAElements && (this.theStack.flags() & 2) != 0) {
            boolean realTag;
            boolean bl = realTag = length == currentName.length();
            if (realTag) {
                for (int i = 0; i < length; ++i) {
                    if (Character.toLowerCase(buff[offset + i]) == Character.toLowerCase(currentName.charAt(i))) continue;
                    realTag = false;
                    break;
                }
            }
            if (!realTag) {
                this.theContentHandler.characters(etagchars, 0, 2);
                this.theContentHandler.characters(buff, offset, length);
                this.theContentHandler.characters(etagchars, 2, 1);
                this.theScanner.startCDATA();
                return true;
            }
        }
        return false;
    }

    public void etag_basic(char[] buff, int offset, int length) throws SAXException {
        Element sp;
        String name;
        this.theNewElement = null;
        if (length != 0) {
            name = this.makeName(buff, offset, length);
            ElementType type = this.theSchema.getElementType(name);
            if (type == null) {
                return;
            }
            name = type.name();
        } else {
            name = this.theStack.name();
        }
        boolean inNoforce = false;
        for (sp = this.theStack; sp != null && !sp.name().equals(name); sp = sp.next()) {
            if ((sp.flags() & 4) == 0) continue;
            inNoforce = true;
        }
        if (sp == null) {
            return;
        }
        if (sp.next() == null || sp.next().next() == null) {
            return;
        }
        if (inNoforce) {
            sp.preclose();
        } else {
            while (this.theStack != sp) {
                this.restartablyPop();
            }
            this.pop();
        }
        while (this.theStack.isPreclosed()) {
            this.pop();
        }
        this.restart(null);
    }

    private void restart(Element e) throws SAXException {
        while (this.theSaved != null && this.theStack.canContain(this.theSaved) && (e == null || this.theSaved.canContain(e))) {
            Element next = this.theSaved.next();
            this.push(this.theSaved);
            this.theSaved = next;
        }
    }

    private void pop() throws SAXException {
        if (this.theStack == null) {
            return;
        }
        String name = this.theStack.name();
        String localName = this.theStack.localName();
        String namespace = this.theStack.namespace();
        String prefix = this.prefixOf(name);
        if (!this.namespaces) {
            localName = "";
            namespace = "";
        }
        this.theContentHandler.endElement(namespace, localName, name);
        if (this.foreign(prefix, namespace)) {
            this.theContentHandler.endPrefixMapping(prefix);
        }
        AttributesImpl atts = this.theStack.atts();
        for (int i = atts.getLength() - 1; i >= 0; --i) {
            String attNamespace = atts.getURI(i);
            String attPrefix = this.prefixOf(atts.getQName(i));
            if (!this.foreign(attPrefix, attNamespace)) continue;
            this.theContentHandler.endPrefixMapping(attPrefix);
        }
        this.theStack = this.theStack.next();
    }

    private void restartablyPop() throws SAXException {
        Element popped = this.theStack;
        this.pop();
        if (this.restartElements && (popped.flags() & 1) != 0) {
            popped.anonymize();
            popped.setNext(this.theSaved);
            this.theSaved = popped;
        }
    }

    private void push(Element e) throws SAXException {
        String name = e.name();
        String localName = e.localName();
        String namespace = e.namespace();
        String prefix = this.prefixOf(name);
        e.clean();
        if (!this.namespaces) {
            localName = "";
            namespace = "";
        }
        if (this.virginStack && localName.equalsIgnoreCase(this.theDoctypeName)) {
            try {
                this.theEntityResolver.resolveEntity(this.theDoctypePublicId, this.theDoctypeSystemId);
            }
            catch (IOException ew) {
                // empty catch block
            }
        }
        if (this.foreign(prefix, namespace)) {
            this.theContentHandler.startPrefixMapping(prefix, namespace);
        }
        AttributesImpl atts = e.atts();
        int len = atts.getLength();
        for (int i = 0; i < len; ++i) {
            String attNamespace = atts.getURI(i);
            String attPrefix = this.prefixOf(atts.getQName(i));
            if (!this.foreign(attPrefix, attNamespace)) continue;
            this.theContentHandler.startPrefixMapping(attPrefix, attNamespace);
        }
        this.theContentHandler.startElement(namespace, localName, name, e.atts());
        e.setNext(this.theStack);
        this.theStack = e;
        this.virginStack = false;
        if (this.CDATAElements && (this.theStack.flags() & 2) != 0) {
            this.theScanner.startCDATA();
        }
    }

    private String prefixOf(String name) {
        int i = name.indexOf(58);
        String prefix = "";
        if (i != -1) {
            prefix = name.substring(0, i);
        }
        return prefix;
    }

    private boolean foreign(String prefix, String namespace) {
        boolean foreign = !prefix.equals("") && !namespace.equals("") && !namespace.equals(this.theSchema.getURI());
        return foreign;
    }

    public void decl(char[] buff, int offset, int length) throws SAXException {
        String s = new String(buff, offset, length);
        String name = null;
        String systemid = null;
        String publicid = null;
        String[] v = Parser.split(s);
        if (v.length > 0 && "DOCTYPE".equalsIgnoreCase(v[0])) {
            if (this.theDoctypeIsPresent) {
                return;
            }
            this.theDoctypeIsPresent = true;
            if (v.length > 1) {
                name = v[1];
                if (v.length > 3 && "SYSTEM".equals(v[2])) {
                    systemid = v[3];
                } else if (v.length > 3 && "PUBLIC".equals(v[2])) {
                    publicid = v[3];
                    systemid = v.length > 4 ? v[4] : "";
                }
            }
        }
        publicid = Parser.trimquotes(publicid);
        systemid = Parser.trimquotes(systemid);
        if (name != null) {
            publicid = this.cleanPublicid(publicid);
            this.theLexicalHandler.startDTD(name, publicid, systemid);
            this.theLexicalHandler.endDTD();
            this.theDoctypeName = name;
            this.theDoctypePublicId = publicid;
            if (this.theScanner instanceof Locator) {
                this.theDoctypeSystemId = ((Locator)((Object)this.theScanner)).getSystemId();
                try {
                    this.theDoctypeSystemId = new URL(new URL(this.theDoctypeSystemId), systemid).toString();
                }
                catch (Exception e) {
                    // empty catch block
                }
            }
        }
    }

    private static String trimquotes(String in) {
        char e;
        if (in == null) {
            return in;
        }
        int length = in.length();
        if (length == 0) {
            return in;
        }
        char s = in.charAt(0);
        if (s == (e = in.charAt(length - 1)) && (s == '\'' || s == '\"')) {
            in = in.substring(1, in.length() - 1);
        }
        return in;
    }

    private static String[] split(String val) throws IllegalArgumentException {
        if ((val = val.trim()).length() == 0) {
            return new String[0];
        }
        ArrayList<String> l = new ArrayList<String>();
        int s = 0;
        int e = 0;
        boolean sq = false;
        boolean dq = false;
        int lastc = 0;
        int len = val.length();
        for (e = 0; e < len; ++e) {
            char c = val.charAt(e);
            if (!dq && c == '\'' && lastc != 92) {
                boolean bl = sq = !sq;
                if (s < 0) {
                    s = e;
                }
            } else if (!sq && c == '\"' && lastc != 92) {
                boolean bl = dq = !dq;
                if (s < 0) {
                    s = e;
                }
            } else if (!sq && !dq) {
                if (Character.isWhitespace(c)) {
                    if (s >= 0) {
                        l.add(val.substring(s, e));
                    }
                    s = -1;
                } else if (s < 0 && c != ' ') {
                    s = e;
                }
            }
            lastc = c;
        }
        l.add(val.substring(s, e));
        return l.toArray(new String[0]);
    }

    private String cleanPublicid(String src) {
        if (src == null) {
            return null;
        }
        int len = src.length();
        StringBuffer dst = new StringBuffer(len);
        boolean suppressSpace = true;
        for (int i = 0; i < len; ++i) {
            char ch = src.charAt(i);
            if (legal.indexOf(ch) != -1) {
                dst.append(ch);
                suppressSpace = false;
                continue;
            }
            if (suppressSpace) continue;
            dst.append(' ');
            suppressSpace = true;
        }
        return dst.toString().trim();
    }

    public void gi(char[] buff, int offset, int length) throws SAXException {
        if (this.theNewElement != null) {
            return;
        }
        String name = this.makeName(buff, offset, length);
        if (name == null) {
            return;
        }
        ElementType type = this.theSchema.getElementType(name);
        if (type == null) {
            if (this.ignoreBogons) {
                return;
            }
            int bogonModel = this.bogonsEmpty ? 0 : -1;
            int bogonMemberOf = this.rootBogons ? -1 : Integer.MAX_VALUE;
            this.theSchema.elementType(name, bogonModel, bogonMemberOf, 0);
            if (!this.rootBogons) {
                this.theSchema.parent(name, this.theSchema.rootElementType().name());
            }
            type = this.theSchema.getElementType(name);
        }
        this.theNewElement = new Element(type, this.defaultAttributes);
    }

    public void cdsect(char[] buff, int offset, int length) throws SAXException {
        this.theLexicalHandler.startCDATA();
        this.pcdata(buff, offset, length);
        this.theLexicalHandler.endCDATA();
    }

    public void pcdata(char[] buff, int offset, int length) throws SAXException {
        if (length == 0) {
            return;
        }
        boolean allWhite = true;
        for (int i = 0; i < length; ++i) {
            if (Character.isWhitespace(buff[offset + i])) continue;
            allWhite = false;
        }
        if (allWhite && !this.theStack.canContain(this.thePCDATA)) {
            if (this.ignorableWhitespace) {
                this.theContentHandler.ignorableWhitespace(buff, offset, length);
            }
        } else {
            this.rectify(this.thePCDATA);
            this.theContentHandler.characters(buff, offset, length);
        }
    }

    public void pitarget(char[] buff, int offset, int length) throws SAXException {
        if (this.theNewElement != null) {
            return;
        }
        this.thePITarget = this.makeName(buff, offset, length).replace(':', '_');
    }

    public void pi(char[] buff, int offset, int length) throws SAXException {
        if (this.theNewElement != null || this.thePITarget == null) {
            return;
        }
        if ("xml".equalsIgnoreCase(this.thePITarget)) {
            return;
        }
        if (length > 0 && buff[length - 1] == '?') {
            --length;
        }
        this.theContentHandler.processingInstruction(this.thePITarget, new String(buff, offset, length));
        this.thePITarget = null;
    }

    public void stagc(char[] buff, int offset, int length) throws SAXException {
        if (this.theNewElement == null) {
            return;
        }
        this.rectify(this.theNewElement);
        if (this.theStack.model() == 0) {
            this.etag_basic(buff, offset, length);
        }
    }

    public void stage(char[] buff, int offset, int length) throws SAXException {
        if (this.theNewElement == null) {
            return;
        }
        this.rectify(this.theNewElement);
        this.etag_basic(buff, offset, length);
    }

    public void cmnt(char[] buff, int offset, int length) throws SAXException {
        this.theLexicalHandler.comment(buff, offset, length);
    }

    private void rectify(Element e) throws SAXException {
        Element sp;
        while (true) {
            ElementType parentType;
            for (sp = this.theStack; sp != null && !sp.canContain(e); sp = sp.next()) {
            }
            if (sp != null || (parentType = e.parent()) == null) break;
            Element parent = new Element(parentType, this.defaultAttributes);
            parent.setNext(e);
            e = parent;
        }
        if (sp == null) {
            return;
        }
        while (this.theStack != sp && this.theStack != null && this.theStack.next() != null && this.theStack.next().next() != null) {
            this.restartablyPop();
        }
        while (e != null) {
            Element nexte = e.next();
            if (!e.name().equals("<pcdata>")) {
                this.push(e);
            }
            e = nexte;
            this.restart(e);
        }
        this.theNewElement = null;
    }

    public int getEntity() {
        return this.theEntity;
    }

    private String makeName(char[] buff, int offset, int length) {
        StringBuffer dst = new StringBuffer(length + 2);
        boolean seenColon = false;
        boolean start = true;
        while (length-- > 0) {
            char ch = buff[offset];
            if (Character.isLetter(ch) || ch == '_') {
                start = false;
                dst.append(ch);
            } else if (Character.isDigit(ch) || ch == '-' || ch == '.') {
                if (start) {
                    dst.append('_');
                }
                start = false;
                dst.append(ch);
            } else if (ch == ':' && !seenColon) {
                seenColon = true;
                if (start) {
                    dst.append('_');
                }
                start = true;
                dst.append(this.translateColons ? (char)'_' : ch);
            }
            ++offset;
        }
        int dstLength = dst.length();
        if (dstLength == 0 || dst.charAt(dstLength - 1) == ':') {
            dst.append('_');
        }
        return dst.toString().intern();
    }

    public void comment(char[] ch, int start, int length) throws SAXException {
    }

    public void endCDATA() throws SAXException {
    }

    public void endDTD() throws SAXException {
    }

    public void endEntity(String name) throws SAXException {
    }

    public void startCDATA() throws SAXException {
    }

    public void startDTD(String name, String publicid, String systemid) throws SAXException {
    }

    public void startEntity(String name) throws SAXException {
    }
}

