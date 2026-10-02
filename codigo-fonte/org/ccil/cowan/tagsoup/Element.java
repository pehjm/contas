/*
 * Decompiled with CFR 0.152.
 */
package org.ccil.cowan.tagsoup;

import org.ccil.cowan.tagsoup.AttributesImpl;
import org.ccil.cowan.tagsoup.ElementType;

public class Element {
    private ElementType theType;
    private AttributesImpl theAtts;
    private Element theNext;
    private boolean preclosed;

    public Element(ElementType type, boolean defaultAttributes) {
        this.theType = type;
        this.theAtts = defaultAttributes ? new AttributesImpl(type.atts()) : new AttributesImpl();
        this.theNext = null;
        this.preclosed = false;
    }

    public ElementType type() {
        return this.theType;
    }

    public AttributesImpl atts() {
        return this.theAtts;
    }

    public Element next() {
        return this.theNext;
    }

    public void setNext(Element next) {
        this.theNext = next;
    }

    public String name() {
        return this.theType.name();
    }

    public String namespace() {
        return this.theType.namespace();
    }

    public String localName() {
        return this.theType.localName();
    }

    public int model() {
        return this.theType.model();
    }

    public int memberOf() {
        return this.theType.memberOf();
    }

    public int flags() {
        return this.theType.flags();
    }

    public ElementType parent() {
        return this.theType.parent();
    }

    public boolean canContain(Element other) {
        return this.theType.canContain(other.theType);
    }

    public void setAttribute(String name, String type, String value) {
        this.theType.setAttribute(this.theAtts, name, type, value);
    }

    public void anonymize() {
        for (int i = this.theAtts.getLength() - 1; i >= 0; --i) {
            if (!this.theAtts.getType(i).equals("ID") && !this.theAtts.getQName(i).equals("name")) continue;
            this.theAtts.removeAttribute(i);
        }
    }

    public void clean() {
        for (int i = this.theAtts.getLength() - 1; i >= 0; --i) {
            String name = this.theAtts.getLocalName(i);
            if (this.theAtts.getValue(i) != null && name != null && name.length() != 0) continue;
            this.theAtts.removeAttribute(i);
        }
    }

    public void preclose() {
        this.preclosed = true;
    }

    public boolean isPreclosed() {
        return this.preclosed;
    }
}

