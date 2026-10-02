/*
 * Decompiled with CFR 0.152.
 */
package groovy.util;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovy.util.IFileNameFinder;
import java.beans.Transient;
import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.List;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class FileNameByRegexFinder
implements IFileNameFinder,
GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public FileNameByRegexFinder() {
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Override
    public List<String> getFileNames(String basedir, String pattern) {
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getFileNames", 2, this, basedir, pattern, ""));
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public List<String> getFileNames(String basedir, String pattern, String excludesPattern) {
        void var3_3;
        Reference<String> pattern2 = new Reference<String>(pattern);
        Reference<void> excludesPattern2 = new Reference<void>(var3_3);
        Reference<List> result = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[0]));
        public final class _getFileNames_closure1
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference pattern;
            private /* synthetic */ Reference excludesPattern;
            private /* synthetic */ Reference result;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _getFileNames_closure1(Object _outerInstance, Object _thisObject, Reference pattern, Reference excludesPattern, Reference result) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                Reference reference3;
                this.pattern = reference3 = pattern;
                this.excludesPattern = reference2 = excludesPattern;
                this.result = reference = result;
            }

            public Object doCall(Object it) {
                if (DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.findRegex(IndyInterface.bootstrap("getProperty", "path", 0, it), IndyInterface.bootstrap("cast", "()", 0, this.pattern.get()))) && (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("cast", "()", 0, this.excludesPattern.get())) == false || IndyInterface.bootstrap("cast", "()", 0, ScriptBytecodeAdapter.findRegex(IndyInterface.bootstrap("getProperty", "path", 0, it), IndyInterface.bootstrap("cast", "()", 0, this.excludesPattern.get()))) == false)) {
                    return IndyInterface.bootstrap("invoke", "leftShift", 0, this.result.get(), IndyInterface.bootstrap("getProperty", "absolutePath", 0, it));
                }
                return null;
            }

            @Generated
            public String getPattern() {
                return IndyInterface.bootstrap("cast", "()", 0, this.pattern.get());
            }

            @Generated
            public String getExcludesPattern() {
                return IndyInterface.bootstrap("cast", "()", 0, this.excludesPattern.get());
            }

            @Generated
            public Object getResult() {
                return this.result.get();
            }

            @Generated
            public Object doCall() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getFileNames_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public /* synthetic */ MethodHandles.Lookup $getLookup() {
                return MethodHandles.lookup();
            }
        }
        IndyInterface.bootstrap("invoke", "eachFileRecurse", 0, IndyInterface.bootstrap("init", "<init>", 0, File.class, basedir), new _getFileNames_closure1(this, this, pattern2, excludesPattern2, result));
        return IndyInterface.bootstrap("cast", "()", 0, result.get());
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != FileNameByRegexFinder.class) {
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
}

