/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.tools.ast;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.beans.Transient;
import java.lang.invoke.MethodHandles;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

class TestHarnessOperation
implements CompilationUnit.IPrimaryClassNodeOperation,
GroovyObject {
    private final ASTTransformation transform;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    public TestHarnessOperation(ASTTransformation transform) {
        ASTTransformation aSTTransformation;
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        this.transform = aSTTransformation = transform;
    }

    @Override
    public void call(SourceUnit source, GeneratorContext ignoredContext, ClassNode ignoredNode) {
        IndyInterface.bootstrap("invoke", "visit", 0, this.transform, null, source);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TestHarnessOperation.class) {
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

