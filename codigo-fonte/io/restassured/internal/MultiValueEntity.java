/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import io.restassured.internal.NameAndValue;
import io.restassured.internal.common.assertion.AssertParameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MultiValueEntity<T extends NameAndValue>
implements Iterable<T> {
    private final List<T> entities;

    public MultiValueEntity(List<T> entities) {
        AssertParameter.notNull(entities, "Entities");
        this.entities = entities;
    }

    public int size() {
        return this.entities.size();
    }

    public boolean exist() {
        return !this.entities.isEmpty();
    }

    public boolean hasEntityWithName(String entityName) {
        return this.get(entityName) != null;
    }

    public List<T> list() {
        return Collections.unmodifiableList(this.entities);
    }

    public T get(String entityName) {
        AssertParameter.notNull(entityName, "Entity name");
        List<T> copyOfEntities = this.reverse();
        for (NameAndValue entity : copyOfEntities) {
            if (!entity.getName().equalsIgnoreCase(entityName)) continue;
            return (T)entity;
        }
        return null;
    }

    private List<T> reverse() {
        ArrayList<T> copy = new ArrayList<T>(this.entities);
        Collections.reverse(copy);
        return copy;
    }

    public String getValue(String entityName) {
        AssertParameter.notNull(entityName, "Entity name");
        T entity = this.get(entityName);
        if (entity == null) {
            return null;
        }
        return entity.getValue();
    }

    public List<T> getList(String entityName) {
        AssertParameter.notNull(entityName, "Entity name");
        ArrayList<NameAndValue> entityList = new ArrayList<NameAndValue>();
        for (NameAndValue entity : this.entities) {
            if (!entity.getName().equalsIgnoreCase(entityName)) continue;
            entityList.add(entity);
        }
        return Collections.unmodifiableList(entityList);
    }

    public List<String> getValues(String entityName) {
        List<T> list = this.getList(entityName);
        LinkedList<String> stringList = new LinkedList<String>();
        for (NameAndValue entity : list) {
            stringList.add(entity.getValue());
        }
        return Collections.unmodifiableList(stringList);
    }

    @Override
    public Iterator<T> iterator() {
        return this.entities.iterator();
    }

    public List<T> asList() {
        return Collections.unmodifiableList(this.entities);
    }

    public String toString() {
        if (!this.exist()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (NameAndValue entity : this.entities) {
            builder.append(entity).append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}

