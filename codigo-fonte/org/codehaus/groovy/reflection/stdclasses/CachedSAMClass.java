/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.reflection.stdclasses;

import groovy.lang.Closure;
import groovy.util.ProxyGenerator;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessControlException;
import java.security.AccessController;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.codehaus.groovy.GroovyBugError;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.runtime.ConvertedClosure;
import org.codehaus.groovy.transform.trait.Traits;

public class CachedSAMClass
extends CachedClass {
    private final Method method;
    private static final Method[] EMPTY_METHOD_ARRAY = new Method[0];
    private static final int PUBLIC_OR_PROTECTED = 5;
    private static final int ABSTRACT_STATIC_PRIVATE = 1034;
    private static final Set<String> OBJECT_METHOD_NAMES = Arrays.stream(Object.class.getMethods()).map(Method::getName).collect(Collectors.toSet());

    public CachedSAMClass(Class clazz, ClassInfo classInfo) {
        super(clazz, classInfo);
        this.method = CachedSAMClass.getSAMMethod(clazz);
        if (this.method == null) {
            throw new GroovyBugError("assigned method should not have been null!");
        }
    }

    @Override
    public boolean isAssignableFrom(Class argument) {
        return argument == null || Closure.class.isAssignableFrom(argument) || ReflectionCache.isAssignableFrom(this.getTheClass(), argument);
    }

    @Override
    public Object coerceArgument(Object argument) {
        if (argument instanceof Closure) {
            Class clazz = this.getTheClass();
            return CachedSAMClass.coerceToSAM((Closure)argument, this.method, clazz);
        }
        return argument;
    }

    public static Object coerceToSAM(Closure argument, Method method, Class clazz) {
        return CachedSAMClass.coerceToSAM(argument, method, clazz, clazz.isInterface());
    }

    public static Object coerceToSAM(Closure argument, Method method, Class clazz, boolean isInterface) {
        if (argument != null && clazz.isAssignableFrom(argument.getClass())) {
            return argument;
        }
        if (!isInterface) {
            return ProxyGenerator.INSTANCE.instantiateAggregateFromBaseClass(Collections.singletonMap(method.getName(), argument), clazz);
        }
        if (method != null && CachedSAMClass.isOrImplementsTrait(clazz)) {
            return ProxyGenerator.INSTANCE.instantiateAggregate(Collections.singletonMap(method.getName(), argument), Collections.singletonList(clazz));
        }
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ConvertedClosure(argument));
    }

    private static boolean isOrImplementsTrait(Class<?> c) {
        if (Traits.isTrait(c)) {
            return true;
        }
        ArrayDeque todo = new ArrayDeque(Arrays.asList(c.getInterfaces()));
        HashSet<Class> done = new HashSet<Class>();
        while ((c = (Class)todo.poll()) != null) {
            if (!done.add(c)) continue;
            if (Traits.isTrait(c)) {
                return true;
            }
            Collections.addAll(todo, c.getInterfaces());
        }
        return false;
    }

    private static Method[] getDeclaredMethods(Class<?> c) {
        try {
            Method[] methods = AccessController.doPrivileged(c::getDeclaredMethods);
            if (methods != null) {
                return methods;
            }
        }
        catch (AccessControlException accessControlException) {
            // empty catch block
        }
        return EMPTY_METHOD_ARRAY;
    }

    private static void getAbstractMethods(Class<?> c, List<Method> current) {
        if (c == null || !Modifier.isAbstract(c.getModifiers())) {
            return;
        }
        CachedSAMClass.getAbstractMethods(c.getSuperclass(), current);
        for (Class<?> clazz : c.getInterfaces()) {
            CachedSAMClass.getAbstractMethods(clazz, current);
        }
        for (GenericDeclaration genericDeclaration : CachedSAMClass.getDeclaredMethods(c)) {
            int modifiers = ((Method)genericDeclaration).getModifiers();
            if (Modifier.isPrivate(modifiers) || !Modifier.isAbstract(modifiers)) continue;
            current.add((Method)genericDeclaration);
        }
    }

    private static boolean hasUsableImplementation(Class<?> c, Method m) {
        if (c == m.getDeclaringClass()) {
            return false;
        }
        try {
            Method found = c.getMethod(m.getName(), m.getParameterTypes());
            int modifiers = found.getModifiers();
            int asp = modifiers & 0x40A;
            int visible = modifiers & 5;
            if (visible != 0 && asp == 0) {
                return true;
            }
        }
        catch (NoSuchMethodException noSuchMethodException) {
            // empty catch block
        }
        if (c == Object.class) {
            return false;
        }
        return CachedSAMClass.hasUsableImplementation(c.getSuperclass(), m);
    }

    private static Method getSingleNonDuplicateMethod(List<Method> current) {
        int size = current.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return current.get(0);
        }
        Method m = current.remove(0);
        for (Method m2 : current) {
            if (m.getName().equals(m2.getName()) && Arrays.equals(m.getParameterTypes(), m2.getParameterTypes())) continue;
            return null;
        }
        return m;
    }

    public static Method getSAMMethod(Class<?> c) {
        if (!Modifier.isAbstract(c.getModifiers())) {
            return null;
        }
        try {
            if (c.isInterface()) {
                Method res = null;
                for (Method mi : c.getMethods()) {
                    if (!Modifier.isAbstract(mi.getModifiers()) || mi.getAnnotation(Traits.Implemented.class) != null) continue;
                    String name = mi.getName();
                    if (OBJECT_METHOD_NAMES.contains(name)) {
                        try {
                            Object.class.getMethod(name, mi.getParameterTypes());
                            continue;
                        }
                        catch (NoSuchMethodException noSuchMethodException) {
                            // empty catch block
                        }
                    }
                    if (res != null) {
                        return null;
                    }
                    res = mi;
                }
                return res;
            }
            LinkedList<Method> methods = new LinkedList<Method>();
            CachedSAMClass.getAbstractMethods(c, methods);
            if (methods.isEmpty()) {
                return null;
            }
            methods.removeIf(m -> CachedSAMClass.hasUsableImplementation(c, m));
            return CachedSAMClass.getSingleNonDuplicateMethod(methods);
        }
        catch (NoClassDefFoundError ignore) {
            return null;
        }
    }
}

