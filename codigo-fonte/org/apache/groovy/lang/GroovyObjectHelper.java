/*
 * Decompiled with CFR 0.152.
 */
package org.apache.groovy.lang;

import groovy.lang.GroovyObject;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import org.codehaus.groovy.GroovyBugError;

@Deprecated
public class GroovyObjectHelper {
    private static final String GET_LOOKUP_METHOD_NAME = "$getLookup";
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final MethodHandles.Lookup NULL_LOOKUP = MethodHandles.lookup();
    private static final ClassValue<AtomicReference<MethodHandles.Lookup>> LOOKUP_MAP;

    public static Optional<MethodHandles.Lookup> lookup(GroovyObject groovyObject) {
        Class<?> groovyObjectClass = groovyObject.getClass();
        AtomicReference<MethodHandles.Lookup> lookupAtomicRef = LOOKUP_MAP.get(groovyObjectClass);
        MethodHandles.Lookup lookup = lookupAtomicRef.get();
        if (null != lookup) {
            if (NULL_LOOKUP == lookup) {
                return Optional.empty();
            }
            return Optional.of(lookup);
        }
        if (groovyObject.getMetaClass().respondsTo(groovyObject, GET_LOOKUP_METHOD_NAME).isEmpty()) {
            lookupAtomicRef.set(NULL_LOOKUP);
            return Optional.empty();
        }
        if (groovyObjectClass.isMemberClass() && Modifier.isStatic(groovyObjectClass.getModifiers())) {
            ArrayList classList = new ArrayList(3);
            for (Class<?> clazz = groovyObjectClass; null != clazz; clazz = clazz.getEnclosingClass()) {
                if (GroovyObjectHelper.isNonStaticInnerClass(clazz)) {
                    return Optional.empty();
                }
                classList.add(clazz);
            }
            MethodHandles.Lookup caller = LOOKUP;
            for (int i = classList.size() - 1; i >= 0; --i) {
                Class c = (Class)classList.get(i);
                caller = GroovyObjectHelper.doLookup(c, caller);
                if (null != caller) continue;
                return Optional.empty();
            }
            lookup = caller;
        } else {
            lookup = GroovyObjectHelper.doLookup(groovyObject);
        }
        if (null != lookup) {
            lookupAtomicRef.set(lookup);
        }
        return Optional.ofNullable(lookup);
    }

    private static boolean isNonStaticInnerClass(Class<?> clazz) {
        return clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers());
    }

    private static MethodHandles.Lookup doLookup(GroovyObject groovyObject) {
        MethodHandles.Lookup lookup;
        try {
            Class<?> groovyObjectClass = groovyObject.getClass();
            lookup = groovyObjectClass.isAnonymousClass() || GroovyObjectHelper.isNonStaticInnerClass(groovyObjectClass) ? LOOKUP.unreflect(GroovyObjectHelper.findGetLookupMethod(groovyObjectClass)).bindTo(groovyObject).invokeExact() : GroovyObjectHelper.doLookup(groovyObjectClass);
        }
        catch (Throwable e) {
            lookup = null;
        }
        return lookup;
    }

    private static MethodHandles.Lookup doLookup(Class<?> groovyObjectClass) {
        return GroovyObjectHelper.doLookup(groovyObjectClass, LOOKUP);
    }

    private static MethodHandles.Lookup doLookup(Class<?> groovyObjectClass, MethodHandles.Lookup caller) {
        try {
            return caller.unreflect(GroovyObjectHelper.findGetLookupMethod(groovyObjectClass)).invokeExact();
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    private static Method findGetLookupMethod(Class<?> groovyObjectClass) throws NoSuchMethodException {
        return groovyObjectClass.getDeclaredMethod(GET_LOOKUP_METHOD_NAME, new Class[0]);
    }

    private GroovyObjectHelper() {
    }

    static {
        if (NULL_LOOKUP == LOOKUP) {
            throw new GroovyBugError("`MethodHandles.lookup()` returns the same `Lookup` instance");
        }
        LOOKUP_MAP = new ClassValue<AtomicReference<MethodHandles.Lookup>>(){

            @Override
            protected AtomicReference<MethodHandles.Lookup> computeValue(Class<?> type) {
                return new AtomicReference<MethodHandles.Lookup>();
            }
        };
    }
}

