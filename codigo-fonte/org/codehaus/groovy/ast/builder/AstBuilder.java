/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.ast.builder;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.lang.GString;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.beans.Transient;
import java.lang.invoke.MethodHandles;
import java.util.List;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.builder.AstSpecificationCompiler;
import org.codehaus.groovy.ast.builder.AstStringCompiler;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class AstBuilder
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public AstBuilder() {
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public List<ASTNode> buildFromCode(CompilePhase ignoredPhase, boolean ignoredStatementsOnly, Closure ignoredBlock) {
        throw (Throwable)new IllegalStateException("AstBuilder.build(CompilePhase, boolean, Closure):List<ASTNode> should never be called at runtime.\nAre you sure you are using it correctly?");
    }

    public List<ASTNode> buildFromString(CompilePhase phase, boolean statementsOnly, String source) {
        String string = source;
        if ((string == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, string)) == false || ScriptBytecodeAdapter.compareEqual("", source.trim())) {
            throw (Throwable)new IllegalArgumentException("A source must be specified");
        }
        return new AstStringCompiler().compile(source, phase, statementsOnly);
    }

    private List<ASTNode> buildFromBlock(CompilePhase phase, boolean statementsOnly, String source) {
        String string = source;
        if ((string == null ? (Object)false : IndyInterface.bootstrap("cast", "()", 0, string)) == false || ScriptBytecodeAdapter.compareEqual("", source.trim())) {
            throw (Throwable)new IllegalArgumentException("A source must be specified");
        }
        GString labelledSource = new GStringImpl(new Object[]{System.currentTimeMillis()}, new String[]{"__synthesized__label__", "__:"}).plus(source);
        List<ASTNode> result = new AstStringCompiler().compile((String)((Object)IndyInterface.bootstrap("cast", "()", 0, labelledSource)), phase, statementsOnly);
        public final class _buildFromBlock_closure1
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _buildFromBlock_closure1(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public ASTNode doCall(Object node) {
                if (node instanceof BlockStatement) {
                    return IndyInterface.bootstrap("cast", "()", 0, DefaultGroovyMethods.getAt(((BlockStatement)((Object)IndyInterface.bootstrap("cast", "()", 0, node))).getStatements(), 0));
                }
                return IndyInterface.bootstrap("cast", "()", 0, node);
            }

            @Generated
            public Object call(Object args) {
                return this.doCall(args);
            }

            @Override
            @Generated
            public Object call() {
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _buildFromBlock_closure1.class) {
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
        return DefaultGroovyMethods.collect(result, new _buildFromBlock_closure1(this, this));
    }

    public List<ASTNode> buildFromSpec(@DelegatesTo(value=AstSpecificationCompiler.class) Closure specification) {
        if (specification == null) {
            throw (Throwable)new IllegalArgumentException("Null: specification");
        }
        AstSpecificationCompiler properties = new AstSpecificationCompiler(specification);
        return properties.getExpression();
    }

    @Generated
    public List<ASTNode> buildFromCode(CompilePhase ignoredPhase, Closure ignoredBlock) {
        return this.buildFromCode(ignoredPhase, true, ignoredBlock);
    }

    @Generated
    public List<ASTNode> buildFromCode(Closure ignoredBlock) {
        return this.buildFromCode((CompilePhase)((Object)IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "CLASS_GENERATION", 0, CompilePhase.class))), true, ignoredBlock);
    }

    @Generated
    public List<ASTNode> buildFromString(CompilePhase phase, String source) {
        return this.buildFromString(phase, true, source);
    }

    @Generated
    public List<ASTNode> buildFromString(String source) {
        return this.buildFromString((CompilePhase)((Object)IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "CLASS_GENERATION", 0, CompilePhase.class))), true, source);
    }

    @Generated
    private List<ASTNode> buildFromBlock(CompilePhase phase, String source) {
        return this.buildFromBlock(phase, true, source);
    }

    @Generated
    private List<ASTNode> buildFromBlock(String source) {
        return this.buildFromBlock((CompilePhase)((Object)IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "CLASS_GENERATION", 0, CompilePhase.class))), true, source);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != AstBuilder.class) {
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

