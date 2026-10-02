/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.xml;

import io.restassured.internal.path.xml.NodeBase;
import io.restassured.internal.path.xml.XMLAssertion;
import io.restassured.path.xml.element.Node;
import io.restassured.path.xml.element.NodeChildren;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class NodeChildrenImpl
extends NodeBase
implements NodeChildren {
    public List<Node> nodeList = new ArrayList<Node>();
    public Object groovyNodes;

    NodeChildrenImpl() {
    }

    @Override
    public Node get(int index) {
        return this.nodeList.get(index);
    }

    @Override
    public int size() {
        return this.nodeList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.nodeList.isEmpty();
    }

    @Override
    public Iterator<String> iterator() {
        return new NodeListIterator(this.nodeList.iterator());
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.nodeList.forEach(it -> builder.append(it.toString()));
        return builder.toString();
    }

    @Override
    public Iterable<Node> nodeIterable() {
        return this.nodeList;
    }

    @Override
    public Object get(String name) {
        return NodeChildrenImpl.get(name, this.nodeList.iterator(), false);
    }

    @Override
    public Object getPath(String path) {
        XMLAssertion xmlAssertion = new XMLAssertion();
        xmlAssertion.setKey(path);
        return xmlAssertion.getChildResultAsJavaObject(this.groovyNodes);
    }

    @Override
    public Iterator<Node> nodeIterator() {
        return this.nodeList.iterator();
    }

    @Override
    public List<Node> list() {
        return Collections.unmodifiableList(this.nodeList);
    }

    @Override
    public <T> List<T> getList(String name) {
        return (List)NodeChildrenImpl.get(name, this.nodeList.iterator(), true);
    }

    @Override
    public Object getBackingGroovyObject() {
        return this.groovyNodes;
    }

    Object leftShift(Node node) {
        this.nodeList.add(node);
        return this.nodeList;
    }

    static class NodeListIterator
    implements Iterator<String> {
        private final Iterator<Node> iterator;

        public NodeListIterator(Iterator<Node> nodeList) {
            this.iterator = nodeList;
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public String next() {
            return this.iterator.next().toString();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

