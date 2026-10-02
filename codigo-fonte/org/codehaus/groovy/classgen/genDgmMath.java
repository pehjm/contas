/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.classgen;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.lang.Script;
import groovy.transform.Generated;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.List;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class genDgmMath
extends Script {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;

    public genDgmMath() {
    }

    public genDgmMath(Binding context) {
        super(context);
    }

    public static void main(String ... args) {
        IndyInterface.bootstrap("invoke", "runScript", 0, InvokerHelper.class, genDgmMath.class, args);
    }

    @Override
    public Object run() {
        Reference<List> types = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[]{"Integer", "Long", "Float", "Double"}));
        IndyInterface.bootstrap("invoke", "println", 2, this, "\npublic CallSite createPojoCallSite(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params, Object receiver, Object[] args) {\n    NumberMath m = NumberMath.getMath((Number)receiver, (Number)args[0]);\n");
        public final class _run_closure1
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference types;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _run_closure1(Object _outerInstance, Object _thisObject, Reference types) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.types = reference = types;
            }

            public Object doCall(Object a) {
                Reference<Object> a2 = new Reference<Object>(a);
                IndyInterface.bootstrap("invoke", "print", 2, this, new GStringImpl(new Object[]{a2.get()}, new String[]{"\n    if (receiver instanceof ", ") {"}));
                public final class _closure2
                extends Closure
                implements GeneratedClosure {
                    private /* synthetic */ Reference a;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _closure2(Object _outerInstance, Object _thisObject, Reference a) {
                        super(_outerInstance, _thisObject);
                        Reference reference;
                        this.a = reference = a;
                    }

                    public Object doCall(Object b) {
                        return IndyInterface.bootstrap("invoke", "print", 2, this, new GStringImpl(new Object[]{b, IndyInterface.bootstrap("invoke", "getMath", 2, this, this.a.get(), b), this.a.get(), b, IndyInterface.bootstrap("invoke", "getMath", 2, this, this.a.get(), b), this.a.get(), b}, new String[]{"\n        if (args[0] instanceof ", ")\n            return new NumberNumberCallSite (site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]){\n                public final Object invoke(Object receiver, Object[] args) {\n                    return ", ".INSTANCE.addImpl((", ")receiver,(", ")args[0]);\n                }\n\n                public final Object invokeBinop(Object receiver, Object arg) {\n                    return ", ".INSTANCE.addImpl((", ")receiver,(", ")arg);\n                }\n            };\n        "}));
                    }

                    @Generated
                    public Object getA() {
                        return this.a.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _closure2.class) {
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
                IndyInterface.bootstrap("invoke", "each", 0, this.types.get(), new _closure2(this, this.getThisObject(), a2));
                return IndyInterface.bootstrap("invoke", "println", 2, this, "}");
            }

            @Generated
            public Object getTypes() {
                return this.types.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _run_closure1.class) {
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
        IndyInterface.bootstrap("invoke", "each", 0, types.get(), new _run_closure1(this, this, types));
        IndyInterface.bootstrap("invoke", "println", 2, this, "\n    return new NumberNumberCallSite (site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]){\n        public final Object invoke(Object receiver, Object[] args) {\n            return math.addImpl((Number)receiver,(Number)args[0]);\n        }\n\n        public final Object invokeBinop(Object receiver, Object arg) {\n            return math.addImpl((Number)receiver,(Number)arg);\n        }\n}\n");
        Object i = null;
        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, ScriptBytecodeAdapter.createRange(2, 256, false, false)));
        if (callSite != null) {
            while (callSite.hasNext()) {
                i = callSite.next();
                IndyInterface.bootstrap("invoke", "print", 2, this, new GStringImpl(new Object[]{i}, new String[]{"public Object invoke", " (Object receiver, "}));
                IndyInterface.bootstrap("invoke", "printParams", 2, this, i);
                IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{i}, new String[]{"Object a", ") {"}));
                IndyInterface.bootstrap("invoke", "print", 2, this, "  return invoke (receiver, new Object[] {");
                IndyInterface.bootstrap("invoke", "printArgs", 2, this, i);
                IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{i}, new String[]{"a", "} );"}));
                IndyInterface.bootstrap("invoke", "println", 2, this, "}");
            }
        }
        return null;
    }

    public Object getMath(Object a, Object b) {
        if (ScriptBytecodeAdapter.compareEqual(a, "Double") || ScriptBytecodeAdapter.compareEqual(b, "Double") || ScriptBytecodeAdapter.compareEqual(a, "Float") || ScriptBytecodeAdapter.compareEqual(b, "Float")) {
            return "FloatingPointMath";
        }
        if (ScriptBytecodeAdapter.compareEqual(a, "Long") || ScriptBytecodeAdapter.compareEqual(b, "Long")) {
            return "LongMath";
        }
        return "IntegerMath";
    }

    private void printParams(int i) {
        Object j = null;
        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, ScriptBytecodeAdapter.createRange(1, IndyInterface.bootstrap("invoke", "minus", 0, i, true), false, false)));
        if (callSite != null) {
            while (callSite.hasNext()) {
                j = callSite.next();
                IndyInterface.bootstrap("invoke", "print", 2, this, new GStringImpl(new Object[]{j}, new String[]{"Object a", ", "}));
            }
        }
    }

    private void printArgs(int i) {
        Object j = null;
        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, ScriptBytecodeAdapter.createRange(1, IndyInterface.bootstrap("invoke", "minus", 0, i, true), false, false)));
        if (callSite != null) {
            while (callSite.hasNext()) {
                j = callSite.next();
                IndyInterface.bootstrap("invoke", "print", 2, this, new GStringImpl(new Object[]{j}, new String[]{"a", ", "}));
            }
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != genDgmMath.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    public static /* synthetic */ MethodHandles.Lookup $getLookup() {
        return MethodHandles.lookup();
    }
}

