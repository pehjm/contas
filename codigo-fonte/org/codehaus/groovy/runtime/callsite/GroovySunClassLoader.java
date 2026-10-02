/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime.callsite;

import groovyjarjarasm.asm.ClassReader;
import groovyjarjarasm.asm.ClassVisitor;
import groovyjarjarasm.asm.ClassWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.codehaus.groovy.reflection.SunClassLoader;

public class GroovySunClassLoader
extends SunClassLoader {
    public static final SunClassLoader sunVM = GroovySunClassLoader.doPrivileged(() -> {
        try {
            if (SunClassLoader.sunVM != null) {
                return new GroovySunClassLoader();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return null;
    });

    private static <T> T doPrivileged(PrivilegedAction<T> action) {
        return AccessController.doPrivileged(action);
    }

    protected GroovySunClassLoader() throws Throwable {
        this(1);
    }

    protected GroovySunClassLoader(int parsingOptions) throws Throwable {
        this.loadAbstract(parsingOptions);
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.MetaClassSite");
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.MetaMethodSite");
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite");
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite");
        this.loadFromRes("org.codehaus.groovy.runtime.callsite.StaticMetaMethodSite");
    }

    private void loadAbstract(int parsingOptions) throws IOException {
        try (InputStream asStream = GroovySunClassLoader.class.getClassLoader().getResourceAsStream(GroovySunClassLoader.resName("org.codehaus.groovy.runtime.callsite.AbstractCallSite"));){
            ClassReader reader = new ClassReader(asStream);
            ClassWriter cw = new ClassWriter(3);
            ClassVisitor cv = new ClassVisitor(589824, cw){

                @Override
                public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                    super.visit(version, access, name, signature, "sun/reflect/GroovyMagic", interfaces);
                }
            };
            reader.accept(cv, parsingOptions);
            this.define(cw.toByteArray(), "org.codehaus.groovy.runtime.callsite.AbstractCallSite");
        }
    }
}

