/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListHashMap<K, V>
implements Map<K, V> {
    private final K[] keys;
    private final V[] values;
    private Map<K, V> innerMap;
    private volatile int size;

    public ListHashMap() {
        this(3);
    }

    public ListHashMap(int listSize) {
        this.keys = new Object[listSize];
        this.values = new Object[listSize];
    }

    @Override
    public void clear() {
        this.innerMap = null;
        this.clearArrays();
        this.size = 0;
    }

    private void clearArrays() {
        int n = this.keys.length;
        for (int i = 0; i < n; ++i) {
            this.values[i] = null;
            this.keys[i] = null;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        if (key != null) {
            if (this.innerMap != null) {
                return this.innerMap.containsKey(key);
            }
            for (int i = 0; i < this.size; ++i) {
                if (!key.equals(this.keys[i])) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value != null) {
            if (this.innerMap != null) {
                return this.innerMap.containsValue(value);
            }
            for (int i = 0; i < this.size; ++i) {
                if (!value.equals(this.values[i])) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return (this.innerMap != null ? Collections.unmodifiableMap(this.innerMap) : this.toMap()).entrySet();
    }

    @Override
    public V get(Object key) {
        if (key != null) {
            if (this.innerMap != null) {
                return this.innerMap.get(key);
            }
            for (int i = 0; i < this.size; ++i) {
                if (!key.equals(this.keys[i])) continue;
                return this.values[i];
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Set<K> keySet() {
        return (this.innerMap != null ? Collections.unmodifiableMap(this.innerMap) : this.toMap()).keySet();
    }

    @Override
    public V put(K key, V value) {
        if (key != null) {
            if (value == null) {
                return this.remove(key);
            }
            if (this.innerMap != null) {
                V old = this.innerMap.put(key, value);
                this.size = this.innerMap.size();
                return old;
            }
            for (int i = 0; i < this.size; ++i) {
                if (!key.equals(this.keys[i])) continue;
                V old = this.values[i];
                this.values[i] = value;
                return old;
            }
            if (this.size < this.keys.length) {
                this.values[this.size] = value;
                this.keys[this.size] = key;
            } else {
                Map<K, V> map = this.toMap();
                map.put(key, value);
                this.innerMap = map;
                this.clearArrays();
            }
            ++this.size;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> entry : m.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        if (key != null) {
            if (this.innerMap != null) {
                V value = this.innerMap.remove(key);
                if (value != null) {
                    this.size = this.innerMap.size();
                    if (this.size <= this.keys.length) {
                        this.size = 0;
                        Set<Map.Entry<K, V>> entries = this.innerMap.entrySet();
                        this.innerMap = null;
                        for (Map.Entry<K, V> entry : entries) {
                            this.values[this.size] = entry.getValue();
                            this.keys[this.size] = entry.getKey();
                            ++this.size;
                        }
                    }
                }
                return value;
            }
            for (int i = 0; i < this.size; ++i) {
                if (!key.equals(this.keys[i])) continue;
                V value = this.values[i];
                --this.size;
                if (i < this.size) {
                    this.values[i] = this.values[this.size];
                    this.keys[i] = this.keys[this.size];
                }
                this.values[this.size] = null;
                this.keys[this.size] = null;
                return value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    private Map<K, V> toMap() {
        HashMap<K, V> m = new HashMap<K, V>();
        for (int i = 0; i < this.size; ++i) {
            m.put(this.keys[i], this.values[i]);
        }
        return m;
    }

    @Override
    public Collection<V> values() {
        return (this.innerMap != null ? Collections.unmodifiableMap(this.innerMap) : this.toMap()).values();
    }
}

