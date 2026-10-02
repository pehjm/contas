/*
 * Decompiled with CFR 0.152.
 */
package groovy.util;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.FromString;
import java.beans.Transient;
import java.io.File;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.ResourceGroovyMethods;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class FileTreeBuilder
implements GroovyObject {
    private File baseDir;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    public FileTreeBuilder(File baseDir) {
        File file;
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        this.baseDir = file = baseDir;
    }

    @Generated
    public FileTreeBuilder() {
        this((File)((Object)IndyInterface.bootstrap("init", "<init>", 0, File.class, ".")));
    }

    public File file(String name, CharSequence contents) {
        return ResourceGroovyMethods.leftShift(new File(this.baseDir, name), contents);
    }

    public File file(String name, byte ... contents) {
        return ResourceGroovyMethods.leftShift(new File(this.baseDir, name), contents);
    }

    public File file(String name, File source) {
        return this.file(name, ResourceGroovyMethods.getBytes(source));
    }

    public File file(String name, @ClosureParams(value=FromString.class, options={"File"}) @DelegatesTo(strategy=1, value=File.class) Closure spec) {
        File file = new File(this.baseDir, name);
        return IndyInterface.bootstrap("cast", "()", 0, DefaultGroovyMethods.tap(file, spec));
    }

    public File dir(String name) {
        File file = new File(this.baseDir, name);
        file.mkdirs();
        return file;
    }

    public File dir(String name, @DelegatesTo(strategy=1, value=FileTreeBuilder.class) Closure spec) {
        File oldBase = this.baseDir;
        File newBase = this.dir(name);
        try {
            File file;
            this.baseDir = file = newBase;
            CallSite clone = IndyInterface.bootstrap("cast", "()", 0, spec.clone());
            FileTreeBuilder fileTreeBuilder = this;
            ((Closure)((Object)clone)).setDelegate(fileTreeBuilder);
            int n = Closure.DELEGATE_FIRST;
            ((Closure)((Object)clone)).setResolveStrategy(n);
            ((Closure)((Object)clone)).call();
        }
        finally {
            File file;
            this.baseDir = file = oldBase;
        }
        return newBase;
    }

    public File call(@DelegatesTo(strategy=1, value=FileTreeBuilder.class) Closure spec) {
        CallSite clone = IndyInterface.bootstrap("cast", "()", 0, spec.clone());
        FileTreeBuilder fileTreeBuilder = this;
        ((Closure)((Object)clone)).setDelegate(fileTreeBuilder);
        int n = Closure.DELEGATE_FIRST;
        ((Closure)((Object)clone)).setResolveStrategy(n);
        ((Closure)((Object)clone)).call();
        return this.baseDir;
    }

    public Object methodMissing(String name, Object args) {
        if (args instanceof Object[] && ((CallSite)IndyInterface.bootstrap("cast", "()", 0, args)).length == 1) {
            CallSite arg = IndyInterface.bootstrap("cast", "()", 0, args)[0];
            if (arg instanceof Closure) {
                return this.dir(name, (Closure)((Object)IndyInterface.bootstrap("cast", "()", 0, arg)));
            }
            if (arg instanceof CharSequence) {
                return this.file(name, ((CharSequence)((Object)arg)).toString());
            }
            if (arg instanceof byte[]) {
                return this.file(name, (byte[])IndyInterface.bootstrap("cast", "()", 0, arg));
            }
            if (arg instanceof File) {
                return this.file(name, (File)((Object)IndyInterface.bootstrap("cast", "()", 0, arg)));
            }
        }
        return null;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != FileTreeBuilder.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    @Override
    @Generated
    @Internal
    @Transient
    public MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    @Override
    @Generated
    @Internal
    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public static /* synthetic */ MethodHandles.Lookup $getLookup() {
        return MethodHandles.lookup();
    }

    @Generated
    public File getBaseDir() {
        return this.baseDir;
    }

    @Generated
    public void setBaseDir(File file) {
        this.baseDir = file;
    }
}

