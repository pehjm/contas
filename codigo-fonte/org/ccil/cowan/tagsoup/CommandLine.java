/*
 * Decompiled with CFR 0.152.
 */
package org.ccil.cowan.tagsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.PYXScanner;
import org.ccil.cowan.tagsoup.PYXWriter;
import org.ccil.cowan.tagsoup.Parser;
import org.ccil.cowan.tagsoup.XMLWriter;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public class CommandLine {
    static Hashtable options = new Hashtable();
    private static Parser theParser;
    private static HTMLSchema theSchema;
    private static String theOutputEncoding;

    public static void main(String[] argv) throws IOException, SAXException {
        int optind = CommandLine.getopts(options, argv);
        if (CommandLine.hasOption(options, "--help")) {
            CommandLine.doHelp();
            return;
        }
        if (CommandLine.hasOption(options, "--version")) {
            System.err.println("TagSoup version 1.2.1");
            return;
        }
        if (argv.length == optind) {
            CommandLine.process("", System.out);
        } else if (CommandLine.hasOption(options, "--files")) {
            for (int i = optind; i < argv.length; ++i) {
                String src = argv[i];
                int j = src.lastIndexOf(46);
                String dst = j == -1 ? src + ".xhtml" : (src.endsWith(".xhtml") ? src + "_" : src.substring(0, j) + ".xhtml");
                System.err.println("src: " + src + " dst: " + dst);
                FileOutputStream os = new FileOutputStream(dst);
                CommandLine.process(src, os);
            }
        } else {
            for (int i = optind; i < argv.length; ++i) {
                System.err.println("src: " + argv[i]);
                CommandLine.process(argv[i], System.out);
            }
        }
    }

    private static void doHelp() {
        System.err.print("usage: java -jar tagsoup-*.jar ");
        System.err.print(" [ ");
        boolean first = true;
        Enumeration e = options.keys();
        while (e.hasMoreElements()) {
            if (!first) {
                System.err.print("| ");
            }
            first = false;
            String key = (String)e.nextElement();
            System.err.print(key);
            if (key.endsWith("=")) {
                System.err.print("?");
            }
            System.err.print(" ");
        }
        System.err.println("]*");
    }

    private static void process(String src, OutputStream os) throws IOException, SAXException {
        String encoding;
        Parser r;
        if (CommandLine.hasOption(options, "--reuse")) {
            if (theParser == null) {
                theParser = new Parser();
            }
            r = theParser;
        } else {
            r = new Parser();
        }
        theSchema = new HTMLSchema();
        r.setProperty("http://www.ccil.org/~cowan/tagsoup/properties/schema", theSchema);
        if (CommandLine.hasOption(options, "--nocdata")) {
            r.setFeature("http://www.ccil.org/~cowan/tagsoup/features/cdata-elements", false);
        }
        if (CommandLine.hasOption(options, "--nons") || CommandLine.hasOption(options, "--html")) {
            r.setFeature("http://xml.org/sax/features/namespaces", false);
        }
        if (CommandLine.hasOption(options, "--nobogons")) {
            r.setFeature("http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons", true);
        }
        if (CommandLine.hasOption(options, "--any")) {
            r.setFeature("http://www.ccil.org/~cowan/tagsoup/features/bogons-empty", false);
        } else if (CommandLine.hasOption(options, "--emptybogons")) {
            r.setFeature("http://www.ccil.org/~cowan/tagsoup/features/bogons-empty", true);
        }
        if (CommandLine.hasOption(options, "--norootbogons")) {
            r.setFeature("http://www.ccil.org/~cowan/tagsoup/features/root-bogons", false);
        }
        if (CommandLine.hasOption(options, "--nodefaults")) {
            r.setFeature("http://www.ccil.org/~cowan/tagsoup/features/default-attributes", false);
        }
        if (CommandLine.hasOption(options, "--nocolons")) {
            r.setFeature("http://www.ccil.org/~cowan/tagsoup/features/translate-colons", true);
        }
        if (CommandLine.hasOption(options, "--norestart")) {
            r.setFeature("http://www.ccil.org/~cowan/tagsoup/features/restart-elements", false);
        }
        if (CommandLine.hasOption(options, "--ignorable")) {
            r.setFeature("http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace", true);
        }
        if (CommandLine.hasOption(options, "--pyxin")) {
            r.setProperty("http://www.ccil.org/~cowan/tagsoup/properties/scanner", new PYXScanner());
        }
        OutputStreamWriter w = theOutputEncoding == null ? new OutputStreamWriter(os) : new OutputStreamWriter(os, theOutputEncoding);
        ContentHandler h = CommandLine.chooseContentHandler(w);
        r.setContentHandler(h);
        if (CommandLine.hasOption(options, "--lexical") && h instanceof LexicalHandler) {
            r.setProperty("http://xml.org/sax/properties/lexical-handler", h);
        }
        InputSource s = new InputSource();
        if (src != "") {
            s.setSystemId(src);
        } else {
            s.setByteStream(System.in);
        }
        if (CommandLine.hasOption(options, "--encoding=") && (encoding = (String)options.get("--encoding=")) != null) {
            s.setEncoding(encoding);
        }
        r.parse(s);
    }

    private static ContentHandler chooseContentHandler(Writer w) {
        String doctype_system;
        String doctype_public;
        String method;
        if (CommandLine.hasOption(options, "--pyx")) {
            return new PYXWriter(w);
        }
        XMLWriter x = new XMLWriter(w);
        if (CommandLine.hasOption(options, "--html")) {
            x.setOutputProperty("method", "html");
            x.setOutputProperty("omit-xml-declaration", "yes");
        }
        if (CommandLine.hasOption(options, "--method=") && (method = (String)options.get("--method=")) != null) {
            x.setOutputProperty("method", method);
        }
        if (CommandLine.hasOption(options, "--doctype-public=") && (doctype_public = (String)options.get("--doctype-public=")) != null) {
            x.setOutputProperty("doctype-public", doctype_public);
        }
        if (CommandLine.hasOption(options, "--doctype-system=") && (doctype_system = (String)options.get("--doctype-system=")) != null) {
            x.setOutputProperty("doctype-system", doctype_system);
        }
        if (CommandLine.hasOption(options, "--output-encoding=") && (theOutputEncoding = (String)options.get("--output-encoding=")) != null) {
            x.setOutputProperty("encoding", theOutputEncoding);
        }
        if (CommandLine.hasOption(options, "--omit-xml-declaration")) {
            x.setOutputProperty("omit-xml-declaration", "yes");
        }
        x.setPrefix(theSchema.getURI(), "");
        return x;
    }

    private static int getopts(Hashtable options, String[] argv) {
        int optind;
        for (optind = 0; optind < argv.length; ++optind) {
            String arg = argv[optind];
            String value = null;
            if (arg.charAt(0) != '-') break;
            int eqsign = arg.indexOf(61);
            if (eqsign != -1) {
                value = arg.substring(eqsign + 1, arg.length());
                arg = arg.substring(0, eqsign + 1);
            }
            if (options.containsKey(arg)) {
                if (value == null) {
                    options.put(arg, Boolean.TRUE);
                    continue;
                }
                options.put(arg, value);
                continue;
            }
            System.err.print("Unknown option ");
            System.err.println(arg);
            System.exit(1);
        }
        return optind;
    }

    private static boolean hasOption(Hashtable options, String option) {
        if (Boolean.getBoolean(option)) {
            return true;
        }
        return options.get(option) != Boolean.FALSE;
    }

    static {
        options.put("--nocdata", Boolean.FALSE);
        options.put("--files", Boolean.FALSE);
        options.put("--reuse", Boolean.FALSE);
        options.put("--nons", Boolean.FALSE);
        options.put("--nobogons", Boolean.FALSE);
        options.put("--any", Boolean.FALSE);
        options.put("--emptybogons", Boolean.FALSE);
        options.put("--norootbogons", Boolean.FALSE);
        options.put("--pyxin", Boolean.FALSE);
        options.put("--lexical", Boolean.FALSE);
        options.put("--pyx", Boolean.FALSE);
        options.put("--html", Boolean.FALSE);
        options.put("--method=", Boolean.FALSE);
        options.put("--doctype-public=", Boolean.FALSE);
        options.put("--doctype-system=", Boolean.FALSE);
        options.put("--output-encoding=", Boolean.FALSE);
        options.put("--omit-xml-declaration", Boolean.FALSE);
        options.put("--encoding=", Boolean.FALSE);
        options.put("--help", Boolean.FALSE);
        options.put("--version", Boolean.FALSE);
        options.put("--nodefaults", Boolean.FALSE);
        options.put("--nocolons", Boolean.FALSE);
        options.put("--norestart", Boolean.FALSE);
        options.put("--ignorable", Boolean.FALSE);
        theParser = null;
        theSchema = null;
        theOutputEncoding = null;
    }
}

