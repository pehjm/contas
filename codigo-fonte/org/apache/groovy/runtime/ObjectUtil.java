/*
 * Decompiled with CFR 0.152.
 */
package org.apache.groovy.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import org.codehaus.groovy.GroovyBugError;
import org.codehaus.groovy.runtime.ArrayUtil;

@Deprecated
public class ObjectUtil {
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();

    public static <T> T cloneObject(T object) throws Throwable {
        if (null == object) {
            return null;
        }
        Class<?> clazz = object.getClass();
        if (!(object instanceof Cloneable)) {
            throw new CloneNotSupportedException(clazz.getName());
        }
        if (clazz.isArray()) {
            if (clazz.getComponentType().isPrimitive()) {
                if (byte[].class == clazz) {
                    return (T)ArrayUtil.cloneArray((byte[])object);
                }
                if (short[].class == clazz) {
                    return (T)ArrayUtil.cloneArray((short[])object);
                }
                if (int[].class == clazz) {
                    return (T)ArrayUtil.cloneArray((int[])object);
                }
                if (char[].class == clazz) {
                    return (T)ArrayUtil.cloneArray((char[])object);
                }
                if (long[].class == clazz) {
                    return (T)ArrayUtil.cloneArray((long[])object);
                }
                if (float[].class == clazz) {
                    return (T)ArrayUtil.cloneArray((float[])object);
                }
                if (double[].class == clazz) {
                    return (T)ArrayUtil.cloneArray((double[])object);
                }
                if (boolean[].class == clazz) {
                    return (T)ArrayUtil.cloneArray((boolean[])object);
                }
                throw new GroovyBugError(clazz.getName() + " is not an array of primitive type");
            }
            return (T)ArrayUtil.cloneArray((Object[])object);
        }
        Method cloneMethod = clazz.getMethod("clone", new Class[0]);
        MethodHandle cloneMethodHandle = LOOKUP.in(clazz).unreflect(cloneMethod);
        return (T)cloneMethodHandle.invokeWithArguments(object);
    }

    public static MethodHandle getCloneObjectMethodHandle() {
        return MethodHandleHolder.CLONE_OBJECT_METHOD_HANDLE;
    }

    private ObjectUtil() {
    }

    private static class MethodHandleHolder {
        private static final MethodHandle CLONE_OBJECT_METHOD_HANDLE;

        private MethodHandleHolder() {
        }

        static {
            Method cloneObjectMethod;
            Class<ObjectUtil> objectUtilClass = ObjectUtil.class;
            try {
                cloneObjectMethod = objectUtilClass.getDeclaredMethod("cloneObject", Object.class);
            }
            catch (NoSuchMethodException e) {
                throw new GroovyBugError("Failed to find `cloneObject` method in class `" + objectUtilClass.getName() + "`", e);
            }
            try {
                CLONE_OBJECT_METHOD_HANDLE = LOOKUP.in(objectUtilClass).unreflect(cloneObjectMethod);
            }
            catch (IllegalAccessException e) {
                throw new GroovyBugError("Failed to create method handle for " + cloneObjectMethod);
            }
        }
    }
}

