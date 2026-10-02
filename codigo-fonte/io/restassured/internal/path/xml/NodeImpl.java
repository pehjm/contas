/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.xml;

import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.path.xml.NodeBase;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.internal.path.xml.XMLAssertion;
import io.restassured.path.xml.element.Node;
import io.restassured.path.xml.element.NodeChildren;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class NodeImpl
extends NodeBase
implements Node {
    Map<String, String> attributes = new HashMap<String, String>();
    NodeChildrenImpl children = new NodeChildrenImpl();
    String name;
    String value = null;
    Object groovyNode;

    NodeImpl() {
    }

    @Override
    public Map<String, String> attributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Iterator<String> iterator() {
        if (!this.children.isEmpty()) {
            return this.children.iterator();
        }
        return new ValueIterator();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.children.isEmpty()) {
            builder.append(this.value);
        } else {
            for (String next : this.children) {
                builder.append((Object)next);
            }
        }
        return builder.toString();
    }

    public Node get(int index) {
        return this.children.get(index);
    }

    @Override
    public NodeChildren children() {
        return this.children;
    }

    @Override
    public String getAttribute(String name) {
        AssertParameter.notNull(name, "name");
        if (!name.startsWith("@")) {
            name = "@" + name;
        }
        return (String)this.get(name);
    }

    float getFloat(String name) {
        return ((Float)this.get(name)).floatValue();
    }

    double getDouble(String name) {
        return (Double)this.get(name);
    }

    char getChar(String name) {
        return ((Character)this.get(name)).charValue();
    }

    boolean getBoolean(String name) {
        return (Boolean)this.get(name);
    }

    long getLong(String name) {
        return (Long)this.get(name);
    }

    int getInt(String name) {
        return (Integer)this.get(name);
    }

    short getShort(String name) {
        return (Short)this.get(name);
    }

    byte getByte(String name) {
        return (Byte)this.get(name);
    }

    @Override
    public Object get(String name) {
        if (name.startsWith("@")) {
            return this.attributes.get(name.substring(1));
        }
        return NodeImpl.get(name, this.children.nodeIterator(), false);
    }

    @Override
    public Object getPath(String path) {
        XMLAssertion xmlAssertion = new XMLAssertion();
        xmlAssertion.setKey(path);
        return xmlAssertion.getChildResultAsJavaObject(this.groovyNode);
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public <T> List<T> getList(String name) {
        return (List)NodeImpl.get(name, this.children.nodeIterator(), true);
    }

    @Override
    public Object getBackingGroovyObject() {
        return this.groovyNode;
    }

    Object leftShift(Node node) {
        return this.children.leftShift(node);
    }

    class ValueIterator
    implements Iterator<String> {
        boolean hasNext = true;

        ValueIterator() {
        }

        @Override
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override
        public String next() {
            this.hasNext = false;
            return NodeImpl.this.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

