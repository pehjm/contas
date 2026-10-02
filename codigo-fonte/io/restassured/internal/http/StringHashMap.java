/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import java.util.HashMap;
import java.util.Map;

class StringHashMap<V>
extends HashMap<Object, V> {
    private static final long serialVersionUID = -92935672093270924L;

    public StringHashMap() {
    }

    public StringHashMap(Map<?, ? extends V> contents) {
        this.putAll(contents);
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            return false;
        }
        return super.containsKey(key.toString());
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            return null;
        }
        return super.get(key.toString());
    }

    @Override
    public V put(Object key, V value) {
        return key != null ? super.put(key.toString(), value) : value;
    }

    @Override
    public void putAll(Map<?, ? extends V> m) {
        for (Object key : m.keySet()) {
            this.put(key, m.get(key));
        }
    }

    @Override
    public V remove(Object key) {
        if (key == null) {
            return null;
        }
        return super.remove(key.toString());
    }
}

