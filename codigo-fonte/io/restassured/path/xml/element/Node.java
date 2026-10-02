/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.path.xml.element;

import io.restassured.path.xml.element.NodeChildren;
import io.restassured.path.xml.element.PathElement;
import java.util.Map;

public interface Node
extends PathElement {
    public Map<String, String> attributes();

    public NodeChildren children();

    public String name();

    public String value();

    public String getAttribute(String var1);
}

