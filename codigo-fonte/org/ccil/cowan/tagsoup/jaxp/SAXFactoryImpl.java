/*
 * Decompiled with CFR 0.152.
 */
package org.ccil.cowan.tagsoup.jaxp;

import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.ccil.cowan.tagsoup.jaxp.SAXParserImpl;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class SAXFactoryImpl
extends SAXParserFactory {
    private SAXParserImpl prototypeParser = null;
    private HashMap features = null;

    public SAXParser newSAXParser() throws ParserConfigurationException {
        try {
            return SAXParserImpl.newInstance(this.features);
        }
        catch (SAXException se) {
            throw new ParserConfigurationException(se.getMessage());
        }
    }

    public void setFeature(String name, boolean value) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        this.getPrototype().setFeature(name, value);
        if (this.features == null) {
            this.features = new LinkedHashMap();
        }
        this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
    }

    public boolean getFeature(String name) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        return this.getPrototype().getFeature(name);
    }

    private SAXParserImpl getPrototype() {
        if (this.prototypeParser == null) {
            this.prototypeParser = new SAXParserImpl();
        }
        return this.prototypeParser;
    }
}

