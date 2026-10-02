/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.path.xml.element;

import io.restassured.path.xml.element.Node;
import io.restassured.path.xml.element.PathElement;
import java.util.Iterator;
import java.util.List;

public interface NodeChildren
extends PathElement {
    public Node get(int var1);

    public int size();

    public boolean isEmpty();

    public Iterable<Node> nodeIterable();

    public Iterator<Node> nodeIterator();

    public List<Node> list();
}

