/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime.callsite;

import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.reflection.CachedField;
import org.codehaus.groovy.runtime.GroovyCategorySupport;
import org.codehaus.groovy.runtime.callsite.AbstractCallSite;
import org.codehaus.groovy.runtime.callsite.CallSite;

class GetEffectivePojoFieldSite
extends AbstractCallSite {
    private final MetaClassImpl metaClass;
    private final CachedField effective;
    private final int metaClassVersion;

    public GetEffectivePojoFieldSite(CallSite site, MetaClassImpl metaClass, CachedField effective) {
        super(site);
        this.metaClass = metaClass;
        this.effective = effective;
        this.metaClassVersion = metaClass.getVersion();
    }

    @Override
    public final CallSite acceptGetProperty(Object receiver) {
        if (GroovyCategorySupport.hasCategoryInCurrentThread() || this.metaClass.getTheClass() != receiver.getClass() || this.metaClass.getVersion() != this.metaClassVersion) {
            return this.createGetPropertySite(receiver);
        }
        return this;
    }

    @Override
    public final Object getProperty(Object receiver) {
        return this.effective.getProperty(receiver);
    }
}

