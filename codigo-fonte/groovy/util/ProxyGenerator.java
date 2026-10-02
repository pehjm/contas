/*
 * Decompiled with CFR 0.152.
 */
package groovy.util;

import groovy.lang.Closure;
import groovy.lang.DelegatingMetaClass;
import groovy.lang.GroovyObject;
import groovy.lang.GroovySystem;
import groovy.lang.MetaClass;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.runtime.ProxyGeneratorAdapter;
import org.codehaus.groovy.runtime.memoize.LRUCache;
import org.codehaus.groovy.runtime.typehandling.GroovyCastException;
import org.codehaus.groovy.transform.trait.Traits;

public class ProxyGenerator {
    private static final Map<Object, Object> EMPTY_CLOSURE_MAP = Collections.emptyMap();
    public static final ProxyGenerator INSTANCE;
    private final LRUCache<CacheKey, ProxyGeneratorAdapter> adapterCache = new LRUCache(Integer.getInteger("groovy.adapter.cache.default.size", 64));
    private boolean debug;
    private boolean emptyMethods;
    private ClassLoader override;

    public boolean getDebug() {
        return this.debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean getEmptyMethods() {
        return this.emptyMethods;
    }

    public void setEmptyMethods(boolean emptyMethods) {
        this.emptyMethods = emptyMethods;
    }

    public ClassLoader getOverride() {
        return this.override;
    }

    public void setOverride(ClassLoader override) {
        this.override = override;
    }

    public GroovyObject instantiateAggregateFromBaseClass(Class clazz) {
        return this.instantiateAggregateFromBaseClass((Map)null, clazz);
    }

    public GroovyObject instantiateAggregateFromBaseClass(Map map, Class clazz) {
        return this.instantiateAggregateFromBaseClass(map, clazz, null);
    }

    public GroovyObject instantiateAggregateFromBaseClass(Closure cl, Class clazz) {
        HashMap<String, Closure> m = new HashMap<String, Closure>();
        m.put("*", cl);
        return this.instantiateAggregateFromBaseClass(m, clazz, null);
    }

    public GroovyObject instantiateAggregateFromBaseClass(Class clazz, Object[] constructorArgs) {
        return this.instantiateAggregate(null, null, clazz, constructorArgs);
    }

    public GroovyObject instantiateAggregateFromBaseClass(Map map, Class clazz, Object[] constructorArgs) {
        return this.instantiateAggregate(map, null, clazz, constructorArgs);
    }

    public GroovyObject instantiateAggregateFromInterface(Class clazz) {
        return this.instantiateAggregateFromInterface(null, clazz);
    }

    public GroovyObject instantiateAggregateFromInterface(Map map, Class clazz) {
        ArrayList<Class> interfaces = new ArrayList<Class>();
        interfaces.add(clazz);
        return this.instantiateAggregate(map, interfaces);
    }

    public GroovyObject instantiateAggregate(List<Class> interfaces) {
        return this.instantiateAggregate(null, interfaces);
    }

    public GroovyObject instantiateAggregate(Map closureMap, List<Class> interfaces) {
        return this.instantiateAggregate(closureMap, interfaces, null);
    }

    public GroovyObject instantiateAggregate(Map closureMap, List<Class> interfaces, Class clazz) {
        return this.instantiateAggregate(closureMap, interfaces, clazz, null);
    }

    public GroovyObject instantiateAggregate(Map closureMap, List<Class> interfaces, Class clazz, Object[] constructorArgs) {
        if (clazz != null && Modifier.isFinal(clazz.getModifiers())) {
            throw new GroovyCastException("Cannot coerce a map to class " + clazz.getName() + " because it is a final class");
        }
        Map<Object, Object> map = closureMap != null ? closureMap : EMPTY_CLOSURE_MAP;
        ProxyGeneratorAdapter adapter = this.createAdapter(map, interfaces, null, clazz);
        return adapter.proxy(map, constructorArgs);
    }

    public GroovyObject instantiateDelegate(Object delegate) {
        return this.instantiateDelegate(null, delegate);
    }

    public GroovyObject instantiateDelegate(List<Class> interfaces, Object delegate) {
        return this.instantiateDelegate(null, interfaces, delegate);
    }

    public GroovyObject instantiateDelegate(Map closureMap, List<Class> interfaces, Object delegate) {
        return this.instantiateDelegateWithBaseClass(closureMap, interfaces, delegate, null);
    }

    public GroovyObject instantiateDelegateWithBaseClass(Map closureMap, List<Class> interfaces, Object delegate) {
        return this.instantiateDelegateWithBaseClass(closureMap, interfaces, delegate, delegate.getClass());
    }

    public GroovyObject instantiateDelegateWithBaseClass(Map closureMap, List<Class> interfaces, Object delegate, Class baseClass) {
        return this.instantiateDelegateWithBaseClass(closureMap, interfaces, delegate, baseClass, null);
    }

    public GroovyObject instantiateDelegateWithBaseClass(Map closureMap, List<Class> interfaces, Object delegate, Class baseClass, String name) {
        Map<Object, Object> map = closureMap != null ? closureMap : EMPTY_CLOSURE_MAP;
        ProxyGeneratorAdapter adapter = this.createAdapter(map, interfaces, delegate.getClass(), baseClass);
        return adapter.delegatingProxy(delegate, map, null);
    }

    private ProxyGeneratorAdapter createAdapter(Map<Object, Object> closureMap, List<Class> interfaceList, Class delegateClass, Class baseClass) {
        Object key2;
        Class[] interfaces;
        Class[] classArray = interfaces = interfaceList != null ? interfaceList.toArray(MetaClassHelper.EMPTY_CLASS_ARRAY) : MetaClassHelper.EMPTY_CLASS_ARRAY;
        Class base = baseClass != null ? baseClass : (interfaces.length > 0 ? interfaces[0] : Object.class);
        HashSet<String> methodNames = closureMap.isEmpty() ? Collections.emptySet() : new HashSet<String>();
        for (Object key2 : closureMap.keySet()) {
            methodNames.add(key2.toString());
        }
        boolean useDelegate = delegateClass != null;
        key2 = new CacheKey(base, useDelegate ? delegateClass : Object.class, methodNames, interfaces, this.emptyMethods, useDelegate);
        return this.adapterCache.getAndPut((CacheKey)key2, k -> {
            ClassLoader classLoader = useDelegate ? delegateClass.getClassLoader() : base.getClassLoader();
            return new ProxyGeneratorAdapter(closureMap, base, interfaces, classLoader, this.emptyMethods, useDelegate ? delegateClass : null);
        });
    }

    private static void setMetaClass(MetaClass metaClass) {
        DelegatingMetaClass newMetaClass = new DelegatingMetaClass(metaClass){

            @Override
            public Object invokeStaticMethod(Object object, String methodName, Object[] arguments) {
                return InvokerHelper.invokeMethod(INSTANCE, methodName, arguments);
            }
        };
        GroovySystem.getMetaClassRegistry().setMetaClass(ProxyGenerator.class, newMetaClass);
    }

    static {
        ProxyGenerator.setMetaClass(GroovySystem.getMetaClassRegistry().getMetaClass(ProxyGenerator.class));
        INSTANCE = new ProxyGenerator();
    }

    private static final class CacheKey {
        private static final Comparator<Class> INTERFACE_COMPARATOR = (o1, o2) -> {
            if (Traits.isTrait(o1)) {
                return -1;
            }
            if (Traits.isTrait(o2)) {
                return 1;
            }
            return o1.getName().compareTo(o2.getName());
        };
        private final boolean emptyMethods;
        private final boolean useDelegate;
        private final Set<String> methods;
        private final ClassReference delegateClass;
        private final ClassReference baseClass;
        private final ClassReference[] interfaces;

        private CacheKey(Class baseClass, Class delegateClass, Set<String> methods, Class[] interfaces, boolean emptyMethods, boolean useDelegate) {
            this.useDelegate = useDelegate;
            this.baseClass = new ClassReference(baseClass);
            this.delegateClass = new ClassReference(delegateClass);
            this.emptyMethods = emptyMethods;
            ClassReference[] classReferenceArray = this.interfaces = interfaces == null ? null : new ClassReference[interfaces.length];
            if (interfaces != null) {
                Class[] interfacesCopy = new Class[interfaces.length];
                System.arraycopy(interfaces, 0, interfacesCopy, 0, interfaces.length);
                Arrays.sort(interfacesCopy, INTERFACE_COMPARATOR);
                for (int i = 0; i < interfacesCopy.length; ++i) {
                    Class anInterface = interfacesCopy[i];
                    this.interfaces[i] = new ClassReference(anInterface);
                }
            }
            this.methods = methods;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            CacheKey cacheKey = (CacheKey)o;
            if (this.emptyMethods != cacheKey.emptyMethods) {
                return false;
            }
            if (this.useDelegate != cacheKey.useDelegate) {
                return false;
            }
            if (!Objects.equals(this.baseClass, cacheKey.baseClass)) {
                return false;
            }
            if (!Objects.equals(this.delegateClass, cacheKey.delegateClass)) {
                return false;
            }
            if (!Arrays.equals(this.interfaces, cacheKey.interfaces)) {
                return false;
            }
            return Objects.equals(this.methods, cacheKey.methods);
        }

        public int hashCode() {
            int result = this.emptyMethods ? 1 : 0;
            result = 31 * result + (this.useDelegate ? 1 : 0);
            result = 31 * result + (this.methods != null ? this.methods.hashCode() : 0);
            result = 31 * result + (this.baseClass != null ? this.baseClass.hashCode() : 0);
            result = 31 * result + (this.delegateClass != null ? this.delegateClass.hashCode() : 0);
            result = 31 * result + (this.interfaces != null ? Arrays.hashCode(this.interfaces) : 0);
            return result;
        }

        private static class ClassReference
        extends WeakReference<Class> {
            public ClassReference(Class referent) {
                super(referent);
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || this.getClass() != o.getClass()) {
                    return false;
                }
                Class thisClass = (Class)this.get();
                ClassReference that = (ClassReference)o;
                if (thisClass == null) {
                    return false;
                }
                return thisClass.equals(that.get());
            }

            public int hashCode() {
                Class thisClass = (Class)this.get();
                if (thisClass == null) {
                    return 0;
                }
                return thisClass.hashCode();
            }
        }
    }
}

