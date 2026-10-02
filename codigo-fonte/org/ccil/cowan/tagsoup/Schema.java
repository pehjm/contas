/*
 * Decompiled with CFR 0.152.
 */
package org.ccil.cowan.tagsoup;

import java.util.HashMap;
import org.ccil.cowan.tagsoup.ElementType;

public abstract class Schema {
    public static final int M_ANY = -1;
    public static final int M_EMPTY = 0;
    public static final int M_PCDATA = 0x40000000;
    public static final int M_ROOT = Integer.MIN_VALUE;
    public static final int F_RESTART = 1;
    public static final int F_CDATA = 2;
    public static final int F_NOFORCE = 4;
    private HashMap theEntities = new HashMap();
    private HashMap theElementTypes = new HashMap();
    private String theURI = "";
    private String thePrefix = "";
    private ElementType theRoot = null;

    public void elementType(String name, int model, int memberOf, int flags) {
        ElementType e = new ElementType(name, model, memberOf, flags, this);
        this.theElementTypes.put(name.toLowerCase(), e);
        if (memberOf == Integer.MIN_VALUE) {
            this.theRoot = e;
        }
    }

    public ElementType rootElementType() {
        return this.theRoot;
    }

    public void attribute(String elemName, String attrName, String type, String value) {
        ElementType e = this.getElementType(elemName);
        if (e == null) {
            throw new Error("Attribute " + attrName + " specified for unknown element type " + elemName);
        }
        e.setAttribute(attrName, type, value);
    }

    public void parent(String name, String parentName) {
        ElementType child = this.getElementType(name);
        ElementType parent = this.getElementType(parentName);
        if (child == null) {
            throw new Error("No child " + name + " for parent " + parentName);
        }
        if (parent == null) {
            throw new Error("No parent " + parentName + " for child " + name);
        }
        child.setParent(parent);
    }

    public void entity(String name, int value) {
        this.theEntities.put(name, new Integer(value));
    }

    public ElementType getElementType(String name) {
        return (ElementType)this.theElementTypes.get(name.toLowerCase());
    }

    public int getEntity(String name) {
        Integer ch = (Integer)this.theEntities.get(name);
        if (ch == null) {
            return 0;
        }
        return ch;
    }

    public String getURI() {
        return this.theURI;
    }

    public String getPrefix() {
        return this.thePrefix;
    }

    public void setURI(String uri) {
        this.theURI = uri;
    }

    public void setPrefix(String prefix) {
        this.thePrefix = prefix;
    }
}

