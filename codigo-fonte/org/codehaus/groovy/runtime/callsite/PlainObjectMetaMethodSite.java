/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyRuntimeException;
import groovy.lang.MetaClass;
import groovy.lang.MetaMethod;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.MetaMethodSite;

public abstract class PlainObjectMetaMethodSite
extends MetaMethodSite {
    public PlainObjectMetaMethodSite(CallSite site, MetaClass metaClass, MetaMethod metaMethod, Class[] params) {
        super(site, metaClass, metaMethod, params);
    }

    protected static Object doInvoke(Object receiver, Object[] args, Method reflect) throws Throwable {
        try {
            return reflect.invoke(receiver, args);
        }
        catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof GroovyRuntimeException) {
                throw ScriptBytecodeAdapter.unwrap((GroovyRuntimeException)cause);
            }
            throw cause;
        }
    }
}

