/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import groovy.lang.MetaMethod;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import org.codehaus.groovy.reflection.CachedConstructor;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.runtime.ArrayTypeUtils;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.MetaClassHelper;

public class MethodClosure
extends Closure {
    public static boolean ALLOW_RESOLVE;
    public static final String ANY_INSTANCE_METHOD_EXISTS = "anyInstanceMethodExists";
    public static final String NEW = "new";
    private static final long serialVersionUID = -2491254866810955844L;
    private boolean anyInstanceMethodExists;
    private final String method;

    public MethodClosure(Object owner, String method) {
        super(owner);
        Class<?> clazz;
        this.method = method;
        this.maximumNumberOfParameters = 0;
        this.parameterTypes = MetaClassHelper.EMPTY_TYPE_ARRAY;
        Class<?> clazz2 = clazz = owner.getClass() == Class.class ? (Class<?>)owner : owner.getClass();
        if (NEW.equals(method)) {
            if (clazz.isArray()) {
                Object[] sizeTypes = new Class[ArrayTypeUtils.dimension(clazz)];
                Arrays.fill(sizeTypes, Integer.TYPE);
                this.setParameterTypesAndNumber((Class[])sizeTypes);
            } else {
                for (CachedConstructor c : ReflectionCache.getCachedClass(clazz).getConstructors()) {
                    this.setParameterTypesAndNumber(c.getNativeParameterTypes());
                }
            }
        } else {
            for (MetaMethod m : InvokerHelper.getMetaClass(clazz).respondsTo(owner, method)) {
                this.setParameterTypesAndNumber(this.makeParameterTypes(owner, m));
                if (m.isStatic()) continue;
                this.anyInstanceMethodExists = true;
            }
        }
    }

    private void setParameterTypesAndNumber(Class[] newParameterTypes) {
        if (newParameterTypes.length <= this.maximumNumberOfParameters) {
            return;
        }
        this.maximumNumberOfParameters = newParameterTypes.length;
        this.parameterTypes = newParameterTypes;
    }

    private Class[] makeParameterTypes(Object owner, MetaMethod m) {
        Class[] newParameterTypes;
        if (owner instanceof Class && !m.isStatic()) {
            Class[] nativeParameterTypes = m.getNativeParameterTypes();
            newParameterTypes = new Class[nativeParameterTypes.length + 1];
            System.arraycopy(nativeParameterTypes, 0, newParameterTypes, 1, nativeParameterTypes.length);
            newParameterTypes[0] = (Class)owner;
        } else {
            newParameterTypes = m.getNativeParameterTypes();
        }
        return newParameterTypes;
    }

    public String getMethod() {
        return this.method;
    }

    @Override
    public Object getProperty(String property) {
        switch (property) {
            case "method": {
                return this.getMethod();
            }
            case "anyInstanceMethodExists": {
                return this.anyInstanceMethodExists;
            }
        }
        return super.getProperty(property);
    }

    protected Object doCall(Object arguments) {
        return InvokerHelper.invokeMethod(this.getOwner(), this.getMethod(), arguments);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        if (ALLOW_RESOLVE) {
            stream.defaultReadObject();
        }
        throw new UnsupportedOperationException();
    }

    private Object readResolve() {
        if (ALLOW_RESOLVE) {
            return this;
        }
        throw new UnsupportedOperationException();
    }
}

