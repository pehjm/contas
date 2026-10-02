/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.tools.ast;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.beans.Transient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.security.CodeSource;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.ast.TestHarnessOperation;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

class TestHarnessClassLoader
extends GroovyClassLoader
implements GroovyObject {
    private final ASTTransformation transform;
    private final CompilePhase phase;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    public TestHarnessClassLoader(ASTTransformation transform, CompilePhase phase) {
        CompilePhase compilePhase;
        ASTTransformation aSTTransformation;
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        this.transform = aSTTransformation = transform;
        this.phase = compilePhase = phase;
    }

    @Override
    protected CompilationUnit createCompilationUnit(CompilerConfiguration config, CodeSource codeSource) {
        CallSite cu = IndyInterface.bootstrap("cast", "()", 0, ScriptBytecodeAdapter.invokeMethodOnSuperN(TestHarnessClassLoader.class, this, "createCompilationUnit", new Object[]{config, codeSource}));
        IndyInterface.bootstrap("invoke", "addPhaseOperation", 0, cu, IndyInterface.bootstrap("init", "<init>", 0, TestHarnessOperation.class, this.transform), IndyInterface.bootstrap("getProperty", "phaseNumber", 0, this.phase));
        return cu;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TestHarnessClassLoader.class) {
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

    public /* synthetic */ CompilationUnit super$5$createCompilationUnit(CompilerConfiguration compilerConfiguration, CodeSource codeSource) {
        return super.createCompilationUnit(compilerConfiguration, codeSource);
    }
}

