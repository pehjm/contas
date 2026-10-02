/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyObject;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.MetaClassImpl;
import groovy.lang.MetaMethod;
import java.lang.reflect.Method;
import org.codehaus.groovy.reflection.CachedMethod;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import org.codehaus.groovy.runtime.MetaClassHelper;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite;
import org.codehaus.groovy.vmplugin.VMPlugin;
import org.codehaus.groovy.vmplugin.VMPluginFactory;

public class PogoMetaMethodSite
extends PlainObjectMetaMethodSite {
    private static final VMPlugin VM_PLUGIN = VMPluginFactory.getPlugin();
    private final int version;
    private final boolean skipVersionCheck;

    public PogoMetaMethodSite(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params) {
        super(site, metaClass, metaMethod, params);
        this.version = metaClass.getVersion();
        this.skipVersionCheck = metaClass.getClass() == MetaClassImpl.class;
    }

    public Object invoke(Object receiver, Object[] args) throws Throwable {
        MetaClassHelper.unwrap(args);
        try {
            return this.metaMethod.doMethodInvoke(receiver, args);
        }
        catch (GroovyRuntimeException gre) {
            throw ScriptBytecodeAdapter.unwrap(gre);
        }
    }

    @Override
    public Object callCurrent(GroovyObject receiver, Object[] args) throws Throwable {
        if (this.checkCall((Object)receiver, args)) {
            try {
                return this.invoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCallCurrent(this, receiver, args);
    }

    @Override
    public Object call(Object receiver, Object[] args) throws Throwable {
        if (this.checkCall(receiver, args)) {
            try {
                return this.invoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
        return CallSiteArray.defaultCall(this, receiver, args);
    }

    private boolean nonParamCheck(Object receiver) {
        try {
            return !GroovyCategorySupport.hasCategoryInCurrentThread() && ((GroovyObject)receiver).getMetaClass() == this.metaClass && (this.skipVersionCheck || ((MetaClassImpl)this.metaClass).getVersion() == this.version);
        }
        catch (NullPointerException e) {
            if (receiver == null) {
                return false;
            }
            throw e;
        }
        catch (ClassCastException e) {
            if (!(receiver instanceof GroovyObject)) {
                return false;
            }
            throw e;
        }
    }

    protected boolean checkCall(Object receiver, Object[] args) {
        return this.nonParamCheck(receiver) && MetaClassHelper.sameClasses(this.params, args);
    }

    protected boolean checkCall(Object receiver) {
        return this.nonParamCheck(receiver) && MetaClassHelper.sameClasses(this.params);
    }

    protected boolean checkCall(Object receiver, Object arg1) {
        return this.nonParamCheck(receiver) && MetaClassHelper.sameClasses(this.params, arg1);
    }

    protected boolean checkCall(Object receiver, Object arg1, Object arg2) {
        return this.nonParamCheck(receiver) && MetaClassHelper.sameClasses(this.params, arg1, arg2);
    }

    protected boolean checkCall(Object receiver, Object arg1, Object arg2, Object arg3) {
        return this.nonParamCheck(receiver) && MetaClassHelper.sameClasses(this.params, arg1, arg2, arg3);
    }

    protected boolean checkCall(Object receiver, Object arg1, Object arg2, Object arg3, Object arg4) {
        return this.nonParamCheck(receiver) && MetaClassHelper.sameClasses(this.params, arg1, arg2, arg3, arg4);
    }

    public static CallSite createPogoMetaMethodSite(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params, Object[] args) {
        if (metaMethod.getClass() == CachedMethod.class) {
            return PogoMetaMethodSite.createCachedMethodSite(site, metaClass, (CachedMethod)metaMethod, params, args);
        }
        return PogoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);
    }

    private static CallSite createNonAwareCallSite(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params, Object[] args) {
        if (metaMethod.correctArguments(args) == args && PogoMetaMethodSite.noWrappers(args)) {
            if (PogoMetaMethodSite.noCoerce(metaMethod, args)) {
                return new PogoMetaMethodSiteNoUnwrap(site, metaClass, metaMethod, params);
            }
            return new PogoMetaMethodSiteNoUnwrapNoCoerce(site, metaClass, metaMethod, params);
        }
        return new PogoMetaMethodSite(site, metaClass, metaMethod, params);
    }

    public static CallSite createCachedMethodSite(CallSite site, MetaClassImpl metaClass, CachedMethod metaMethod, Class[] params, Object[] args) {
        if (metaMethod.correctArguments(args) == args && PogoMetaMethodSite.noWrappers(args)) {
            if (PogoMetaMethodSite.noCoerce(metaMethod, args)) {
                return new PogoCachedMethodSiteNoUnwrap(site, metaClass, metaMethod, params);
            }
            return metaMethod.createPogoMetaMethodSite(site, metaClass, params);
        }
        return new PogoCachedMethodSite(site, metaClass, metaMethod, params);
    }

    public static class PogoMetaMethodSiteNoUnwrap
    extends PogoMetaMethodSite {
        public PogoMetaMethodSiteNoUnwrap(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params) {
            super(site, metaClass, metaMethod, params);
        }

        @Override
        public final Object invoke(Object receiver, Object[] args) throws Throwable {
            try {
                return this.metaMethod.doMethodInvoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
    }

    public static class PogoMetaMethodSiteNoUnwrapNoCoerce
    extends PogoMetaMethodSite {
        public PogoMetaMethodSiteNoUnwrapNoCoerce(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params) {
            super(site, metaClass, metaMethod, params);
        }

        @Override
        public final Object invoke(Object receiver, Object[] args) throws Throwable {
            try {
                return this.metaMethod.invoke(receiver, args);
            }
            catch (GroovyRuntimeException gre) {
                throw ScriptBytecodeAdapter.unwrap(gre);
            }
        }
    }

    public static class PogoCachedMethodSiteNoUnwrap
    extends PogoCachedMethodSite {
        public PogoCachedMethodSiteNoUnwrap(CallSite site, MetaClassImpl metaClass, CachedMethod metaMethod, Class[] params) {
            super(site, metaClass, metaMethod, params);
        }

        @Override
        public final Object invoke(Object receiver, Object[] args) throws Throwable {
            args = this.metaMethod.coerceArgumentsToClasses(args);
            return PogoCachedMethodSiteNoUnwrap.doInvoke(receiver, args, this.reflect);
        }
    }

    public static class PogoCachedMethodSite
    extends PogoMetaMethodSite {
        final Method reflect;

        public PogoCachedMethodSite(CallSite site, MetaClassImpl metaClass, CachedMethod metaMethod, Class[] params) {
            super(site, metaClass, VM_PLUGIN.transformMetaMethod(metaClass, metaMethod), params);
            this.reflect = ((CachedMethod)this.metaMethod).setAccessible();
        }

        @Override
        public Object invoke(Object receiver, Object[] args) throws Throwable {
            MetaClassHelper.unwrap(args);
            args = this.metaMethod.coerceArgumentsToClasses(args);
            return PogoCachedMethodSite.doInvoke(receiver, args, this.reflect);
        }
    }

    public static class PogoCachedMethodSiteNoUnwrapNoCoerce
    extends PogoCachedMethodSite {
        public PogoCachedMethodSiteNoUnwrapNoCoerce(CallSite site, MetaClassImpl metaClass, CachedMethod metaMethod, Class[] params) {
            super(site, metaClass, metaMethod, params);
        }

        @Override
        public final Object invoke(Object receiver, Object[] args) throws Throwable {
            return PogoCachedMethodSiteNoUnwrapNoCoerce.doInvoke(receiver, args, this.reflect);
        }
    }
}

