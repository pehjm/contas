/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;
import java.util.Collections;
import java.util.Iterator;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

public class NullObject
extends GroovyObjectSupport {
    private static final NullObject INSTANCE = new NullObject();

    public static NullObject getNullObject() {
        return INSTANCE;
    }

    private NullObject() {
        if (INSTANCE != null) {
            throw new RuntimeException("Can't instantiate NullObject. Use NullObject.getNullObject()");
        }
    }

    public Object clone() {
        throw new NullPointerException("Cannot invoke method clone() on null object");
    }

    public boolean equals(Object o) {
        return o == null;
    }

    public int hashCode() {
        throw new NullPointerException("Cannot invoke method hashCode() on null object");
    }

    public String toString() {
        return "null";
    }

    @Override
    public Object getProperty(String name) {
        throw new NullPointerException("Cannot get property '" + name + "' on null object");
    }

    @Override
    public void setProperty(String name, Object value) {
        throw new NullPointerException("Cannot set property '" + name + "' on null object");
    }

    @Override
    public Object invokeMethod(String name, Object arguments) {
        throw new NullPointerException("Cannot invoke method " + name + "() on null object");
    }

    public boolean asBoolean() {
        return false;
    }

    public Object asType(Class c) {
        return null;
    }

    public boolean is(Object o) {
        return this.equals(o);
    }

    public Iterator iterator() {
        return Collections.emptyIterator();
    }

    public Object plus(Object o) {
        throw new NullPointerException("Cannot execute null+" + o);
    }

    public Object plus(String s) {
        return this.getMetaClass().invokeMethod((Object)this, "toString", new Object[0]) + s;
    }

    public <T> T with(Closure<T> closure) {
        return DefaultGroovyMethods.with(null, closure);
    }
}

