/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import java.io.Serializable;
import java.lang.reflect.Method;
import org.codehaus.groovy.runtime.ConversionHandler;

public class ConvertedClosure
extends ConversionHandler
implements Serializable {
    private static final long serialVersionUID = 1162833713450835227L;
    private final String methodName;

    public ConvertedClosure(Closure closure) {
        this(closure, null);
    }

    public ConvertedClosure(Closure closure, String methodName) {
        super(closure);
        this.methodName = methodName;
    }

    @Override
    public Object invokeCustom(Object proxy, Method method, Object[] args) throws Throwable {
        if (this.methodName != null && !this.methodName.equals(method.getName())) {
            return null;
        }
        Object result = ((Closure)this.getDelegate()).call(args);
        return result;
    }
}

