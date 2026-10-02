/*
 * Decompiled with CFR 0.152.
 */
package org.apache.xmlbeans.impl.common;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SystemProperties;

public class SystemCache {
    private static SystemCache INSTANCE = new SystemCache();
    private ThreadLocal<SoftReference> tl_saxLoaders = new ThreadLocal();

    public static synchronized void set(SystemCache instance) {
        INSTANCE = instance;
    }

    public static SystemCache get() {
        return INSTANCE;
    }

    public SchemaTypeLoader getFromTypeLoaderCache(ClassLoader cl) {
        return null;
    }

    public void addToTypeLoaderCache(SchemaTypeLoader stl, ClassLoader cl) {
    }

    public void clearThreadLocals() {
        this.tl_saxLoaders.remove();
    }

    public Object getSaxLoader() {
        SoftReference s = this.tl_saxLoaders.get();
        return s == null ? null : s.get();
    }

    public void setSaxLoader(Object saxLoader) {
        this.tl_saxLoaders.set(new SoftReference<Object>(saxLoader));
    }

    static {
        String cacheClass = SystemProperties.getProperty("xmlbean.systemcacheimpl");
        Object impl = null;
        if (cacheClass != null) {
            try {
                impl = Class.forName(cacheClass).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                if (!(impl instanceof SystemCache)) {
                    throw new ClassCastException("Value for system property \"xmlbean.systemcacheimpl\" points to a class (" + cacheClass + ") which does not derive from SystemCache");
                }
            }
            catch (ClassNotFoundException cnfe) {
                throw new RuntimeException("Cache class " + cacheClass + " specified by \"xmlbean.systemcacheimpl\" was not found.", cnfe);
            }
            catch (InstantiationException | NoSuchMethodException | InvocationTargetException ie) {
                throw new RuntimeException("Could not instantiate class " + cacheClass + " as specified by \"xmlbean.systemcacheimpl\". An empty constructor may be missing.", ie);
            }
            catch (IllegalAccessException iae) {
                throw new RuntimeException("Could not instantiate class " + cacheClass + " as specified by \"xmlbean.systemcacheimpl\". A public empty constructor may be missing.", iae);
            }
        }
        if (impl != null) {
            INSTANCE = impl;
        }
    }
}

