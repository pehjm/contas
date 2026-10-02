/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.path.xml.element;

import io.restassured.path.xml.element.Node;
import java.util.List;

public interface PathElement
extends Iterable<String> {
    public <T> T get(String var1);

    public <T> T getPath(String var1);

    public <T> T getPath(String var1, Class<T> var2);

    public Node getNode(String var1);

    public List<Node> getNodes(String var1);
}

