/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime.callsite;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.CachedField;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import org.codehaus.groovy.runtime.callsite.AbstractCallSite;
import org.codehaus.groovy.runtime.callsite.CallSite;

public class GetEffectivePogoFieldSite
extends AbstractCallSite {
    private final MetaClass metaClass;
    private final CachedField effective;

    public GetEffectivePogoFieldSite(CallSite site, MetaClass metaClass, CachedField effective) {
        super(site);
        this.metaClass = metaClass;
        this.effective = effective;
    }

    @Override
    public final CallSite acceptGetProperty(Object receiver) {
        return this.isEffective(receiver) ? this : this.createGetPropertySite(receiver);
    }

    @Override
    public final CallSite acceptGroovyObjectGetProperty(Object receiver) {
        return this.isEffective(receiver) ? this : this.createGroovyObjectGetPropertySite(receiver);
    }

    @Override
    public final Object callGetProperty(Object receiver) throws Throwable {
        return this.isEffective(receiver) ? this.getProperty(receiver) : this.createGetPropertySite(receiver).getProperty(receiver);
    }

    @Override
    public final Object callGroovyObjectGetProperty(Object receiver) throws Throwable {
        return this.isEffective(receiver) ? this.getProperty(receiver) : this.createGroovyObjectGetPropertySite(receiver).getProperty(receiver);
    }

    @Override
    public final Object getProperty(Object receiver) {
        return this.effective.getProperty(receiver);
    }

    private boolean isEffective(Object receiver) {
        return !GroovyCategorySupport.hasCategoryInCurrentThread() && receiver instanceof GroovyObject && ((GroovyObject)receiver).getMetaClass() == this.metaClass;
    }
}

