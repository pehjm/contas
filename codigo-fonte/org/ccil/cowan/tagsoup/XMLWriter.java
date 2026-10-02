/*
 * Decompiled with CFR 0.152.
 */
package org.ccil.cowan.tagsoup;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;
import org.xml.sax.helpers.XMLFilterImpl;

public class XMLWriter
extends XMLFilterImpl
implements LexicalHandler {
    private String[] booleans = new String[]{"checked", "compact", "declare", "defer", "disabled", "ismap", "multiple", "nohref", "noresize", "noshade", "nowrap", "readonly", "selected"};
    private final Attributes EMPTY_ATTS = new AttributesImpl();
    public static final String CDATA_SECTION_ELEMENTS = "cdata-section-elements";
    public static final String DOCTYPE_PUBLIC = "doctype-public";
    public static final String DOCTYPE_SYSTEM = "doctype-system";
    public static final String ENCODING = "encoding";
    public static final String INDENT = "indent";
    public static final String MEDIA_TYPE = "media-type";
    public static final String METHOD = "method";
    public static final String OMIT_XML_DECLARATION = "omit-xml-declaration";
    public static final String STANDALONE = "standalone";
    public static final String VERSION = "version";
    private Hashtable prefixTable;
    private Hashtable forcedDeclTable;
    private Hashtable doneDeclTable;
    private int elementLevel = 0;
    private Writer output;
    private NamespaceSupport nsSupport;
    private int prefixCounter = 0;
    private Properties outputProperties;
    private boolean unicodeMode = false;
    private String outputEncoding = "";
    private boolean htmlMode = false;
    private boolean forceDTD = false;
    private boolean hasOutputDTD = false;
    private String overridePublic = null;
    private String overrideSystem = null;
    private String version = null;
    private String standalone = null;
    private boolean cdataElement = false;

    public XMLWriter() {
        this.init(null);
    }

    public XMLWriter(Writer writer) {
        this.init(writer);
    }

    public XMLWriter(XMLReader xmlreader) {
        super(xmlreader);
        this.init(null);
    }

    public XMLWriter(XMLReader xmlreader, Writer writer) {
        super(xmlreader);
        this.init(writer);
    }

    private void init(Writer writer) {
        this.setOutput(writer);
        this.nsSupport = new NamespaceSupport();
        this.prefixTable = new Hashtable();
        this.forcedDeclTable = new Hashtable();
        this.doneDeclTable = new Hashtable();
        this.outputProperties = new Properties();
    }

    public void reset() {
        this.elementLevel = 0;
        this.prefixCounter = 0;
        this.nsSupport.reset();
    }

    public void flush() throws IOException {
        this.output.flush();
    }

    public void setOutput(Writer writer) {
        this.output = writer == null ? new OutputStreamWriter(System.out) : writer;
    }

    public void setPrefix(String uri, String prefix) {
        this.prefixTable.put(uri, prefix);
    }

    public String getPrefix(String uri) {
        return (String)this.prefixTable.get(uri);
    }

    public void forceNSDecl(String uri) {
        this.forcedDeclTable.put(uri, Boolean.TRUE);
    }

    public void forceNSDecl(String uri, String prefix) {
        this.setPrefix(uri, prefix);
        this.forceNSDecl(uri);
    }

    public void startDocument() throws SAXException {
        this.reset();
        if (!"yes".equals(this.outputProperties.getProperty(OMIT_XML_DECLARATION, "no"))) {
            this.write("<?xml");
            if (this.version == null) {
                this.write(" version=\"1.0\"");
            } else {
                this.write(" version=\"");
                this.write(this.version);
                this.write("\"");
            }
            if (this.outputEncoding != null && this.outputEncoding != "") {
                this.write(" encoding=\"");
                this.write(this.outputEncoding);
                this.write("\"");
            }
            if (this.standalone == null) {
                this.write(" standalone=\"yes\"?>\n");
            } else {
                this.write(" standalone=\"");
                this.write(this.standalone);
                this.write("\"");
            }
        }
        super.startDocument();
    }

    public void endDocument() throws SAXException {
        this.write('\n');
        super.endDocument();
        try {
            this.flush();
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        ++this.elementLevel;
        this.nsSupport.pushContext();
        if (this.forceDTD && !this.hasOutputDTD) {
            this.startDTD(localName == null ? qName : localName, "", "");
        }
        this.write('<');
        this.writeName(uri, localName, qName, true);
        this.writeAttributes(atts);
        if (this.elementLevel == 1) {
            this.forceNSDecls();
        }
        this.writeNSDecls();
        this.write('>');
        if (this.htmlMode && (qName.equals("script") || qName.equals("style"))) {
            this.cdataElement = true;
        }
        super.startElement(uri, localName, qName, atts);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!(this.htmlMode && (uri.equals("http://www.w3.org/1999/xhtml") || uri.equals("")) && (qName.equals("area") || qName.equals("base") || qName.equals("basefont") || qName.equals("br") || qName.equals("col") || qName.equals("frame") || qName.equals("hr") || qName.equals("img") || qName.equals("input") || qName.equals("isindex") || qName.equals("link") || qName.equals("meta") || qName.equals("param")))) {
            this.write("</");
            this.writeName(uri, localName, qName, true);
            this.write('>');
        }
        this.cdataElement = false;
        super.endElement(uri, localName, qName);
        this.nsSupport.popContext();
        --this.elementLevel;
    }

    public void characters(char[] ch, int start, int len) throws SAXException {
        if (!this.cdataElement) {
            this.writeEsc(ch, start, len, false);
        } else {
            for (int i = start; i < start + len; ++i) {
                this.write(ch[i]);
            }
        }
        super.characters(ch, start, len);
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        this.writeEsc(ch, start, length, false);
        super.ignorableWhitespace(ch, start, length);
    }

    public void processingInstruction(String target, String data) throws SAXException {
        this.write("<?");
        this.write(target);
        this.write(' ');
        this.write(data);
        this.write("?>");
        if (this.elementLevel < 1) {
            this.write('\n');
        }
        super.processingInstruction(target, data);
    }

    public void emptyElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        this.nsSupport.pushContext();
        this.write('<');
        this.writeName(uri, localName, qName, true);
        this.writeAttributes(atts);
        if (this.elementLevel == 1) {
            this.forceNSDecls();
        }
        this.writeNSDecls();
        this.write("/>");
        super.startElement(uri, localName, qName, atts);
        super.endElement(uri, localName, qName);
    }

    public void startElement(String uri, String localName) throws SAXException {
        this.startElement(uri, localName, "", this.EMPTY_ATTS);
    }

    public void startElement(String localName) throws SAXException {
        this.startElement("", localName, "", this.EMPTY_ATTS);
    }

    public void endElement(String uri, String localName) throws SAXException {
        this.endElement(uri, localName, "");
    }

    public void endElement(String localName) throws SAXException {
        this.endElement("", localName, "");
    }

    public void emptyElement(String uri, String localName) throws SAXException {
        this.emptyElement(uri, localName, "", this.EMPTY_ATTS);
    }

    public void emptyElement(String localName) throws SAXException {
        this.emptyElement("", localName, "", this.EMPTY_ATTS);
    }

    public void dataElement(String uri, String localName, String qName, Attributes atts, String content) throws SAXException {
        this.startElement(uri, localName, qName, atts);
        this.characters(content);
        this.endElement(uri, localName, qName);
    }

    public void dataElement(String uri, String localName, String content) throws SAXException {
        this.dataElement(uri, localName, "", this.EMPTY_ATTS, content);
    }

    public void dataElement(String localName, String content) throws SAXException {
        this.dataElement("", localName, "", this.EMPTY_ATTS, content);
    }

    public void characters(String data) throws SAXException {
        char[] ch = data.toCharArray();
        this.characters(ch, 0, ch.length);
    }

    private void forceNSDecls() {
        Enumeration prefixes = this.forcedDeclTable.keys();
        while (prefixes.hasMoreElements()) {
            String prefix = (String)prefixes.nextElement();
            this.doPrefix(prefix, null, true);
        }
    }

    private String doPrefix(String uri, String qName, boolean isElement) {
        String defaultNS = this.nsSupport.getURI("");
        if ("".equals(uri)) {
            if (isElement && defaultNS != null) {
                this.nsSupport.declarePrefix("", "");
            }
            return null;
        }
        String prefix = isElement && defaultNS != null && uri.equals(defaultNS) ? "" : this.nsSupport.getPrefix(uri);
        if (prefix != null) {
            return prefix;
        }
        prefix = (String)this.doneDeclTable.get(uri);
        if (prefix != null && ((!isElement || defaultNS != null) && "".equals(prefix) || this.nsSupport.getURI(prefix) != null)) {
            prefix = null;
        }
        if (prefix == null && (prefix = (String)this.prefixTable.get(uri)) != null && ((!isElement || defaultNS != null) && "".equals(prefix) || this.nsSupport.getURI(prefix) != null)) {
            prefix = null;
        }
        if (prefix == null && qName != null && !"".equals(qName)) {
            int i = qName.indexOf(58);
            if (i == -1) {
                if (isElement && defaultNS == null) {
                    prefix = "";
                }
            } else {
                prefix = qName.substring(0, i);
            }
        }
        while (prefix == null || this.nsSupport.getURI(prefix) != null) {
            prefix = "__NS" + ++this.prefixCounter;
        }
        this.nsSupport.declarePrefix(prefix, uri);
        this.doneDeclTable.put(uri, prefix);
        return prefix;
    }

    private void write(char c) throws SAXException {
        try {
            this.output.write(c);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }

    private void write(String s) throws SAXException {
        try {
            this.output.write(s);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
    }

    private void writeAttributes(Attributes atts) throws SAXException {
        int len = atts.getLength();
        for (int i = 0; i < len; ++i) {
            char[] ch = atts.getValue(i).toCharArray();
            this.write(' ');
            this.writeName(atts.getURI(i), atts.getLocalName(i), atts.getQName(i), false);
            if (this.htmlMode && this.booleanAttribute(atts.getLocalName(i), atts.getQName(i), atts.getValue(i))) break;
            this.write("=\"");
            this.writeEsc(ch, 0, ch.length, true);
            this.write('\"');
        }
    }

    private boolean booleanAttribute(String localName, String qName, String value) {
        int i;
        String name = localName;
        if (name == null && (i = qName.indexOf(58)) != -1) {
            name = qName.substring(i + 1, qName.length());
        }
        if (!name.equals(value)) {
            return false;
        }
        for (int j = 0; j < this.booleans.length; ++j) {
            if (!name.equals(this.booleans[j])) continue;
            return true;
        }
        return false;
    }

    private void writeEsc(char[] ch, int start, int length, boolean isAttVal) throws SAXException {
        block6: for (int i = start; i < start + length; ++i) {
            switch (ch[i]) {
                case '&': {
                    this.write("&amp;");
                    continue block6;
                }
                case '<': {
                    this.write("&lt;");
                    continue block6;
                }
                case '>': {
                    this.write("&gt;");
                    continue block6;
                }
                case '\"': {
                    if (isAttVal) {
                        this.write("&quot;");
                        continue block6;
                    }
                    this.write('\"');
                    continue block6;
                }
                default: {
                    if (!this.unicodeMode && ch[i] > '\u007f') {
                        this.write("&#");
                        this.write(Integer.toString(ch[i]));
                        this.write(';');
                        continue block6;
                    }
                    this.write(ch[i]);
                }
            }
        }
    }

    private void writeNSDecls() throws SAXException {
        Enumeration prefixes = this.nsSupport.getDeclaredPrefixes();
        while (prefixes.hasMoreElements()) {
            String prefix = (String)prefixes.nextElement();
            String uri = this.nsSupport.getURI(prefix);
            if (uri == null) {
                uri = "";
            }
            char[] ch = uri.toCharArray();
            this.write(' ');
            if ("".equals(prefix)) {
                this.write("xmlns=\"");
            } else {
                this.write("xmlns:");
                this.write(prefix);
                this.write("=\"");
            }
            this.writeEsc(ch, 0, ch.length, true);
            this.write('\"');
        }
    }

    private void writeName(String uri, String localName, String qName, boolean isElement) throws SAXException {
        String prefix = this.doPrefix(uri, qName, isElement);
        if (prefix != null && !"".equals(prefix)) {
            this.write(prefix);
            this.write(':');
        }
        if (localName != null && !"".equals(localName)) {
            this.write(localName);
        } else {
            int i = qName.indexOf(58);
            this.write(qName.substring(i + 1, qName.length()));
        }
    }

    public void comment(char[] ch, int start, int length) throws SAXException {
        this.write("<!--");
        for (int i = start; i < start + length; ++i) {
            this.write(ch[i]);
            if (ch[i] != '-' || i + 1 > start + length || ch[i + 1] != '-') continue;
            this.write(' ');
        }
        this.write("-->");
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
        char sysquote;
        if (name == null) {
            return;
        }
        if (this.hasOutputDTD) {
            return;
        }
        this.hasOutputDTD = true;
        this.write("<!DOCTYPE ");
        this.write(name);
        if (systemid == null) {
            systemid = "";
        }
        if (this.overrideSystem != null) {
            systemid = this.overrideSystem;
        }
        char c = sysquote = systemid.indexOf(34) != -1 ? (char)'\'' : '\"';
        if (this.overridePublic != null) {
            publicid = this.overridePublic;
        }
        if (publicid != null && !"".equals(publicid)) {
            char pubquote = publicid.indexOf(34) != -1 ? (char)'\'' : '\"';
            this.write(" PUBLIC ");
            this.write(pubquote);
            this.write(publicid);
            this.write(pubquote);
            this.write(' ');
        } else {
            this.write(" SYSTEM ");
        }
        this.write(sysquote);
        this.write(systemid);
        this.write(sysquote);
        this.write(">\n");
    }

    public void startEntity(String name) throws SAXException {
    }

    public String getOutputProperty(String key) {
        return this.outputProperties.getProperty(key);
    }

    public void setOutputProperty(String key, String value) {
        this.outputProperties.setProperty(key, value);
        if (key.equals(ENCODING)) {
            this.outputEncoding = value;
            this.unicodeMode = value.substring(0, 3).equalsIgnoreCase("utf");
        } else if (key.equals(METHOD)) {
            this.htmlMode = value.equals("html");
        } else if (key.equals(DOCTYPE_PUBLIC)) {
            this.overridePublic = value;
            this.forceDTD = true;
        } else if (key.equals(DOCTYPE_SYSTEM)) {
            this.overrideSystem = value;
            this.forceDTD = true;
        } else if (key.equals(VERSION)) {
            this.version = value;
        } else if (key.equals(STANDALONE)) {
            this.standalone = value;
        }
    }
}

