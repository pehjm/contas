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
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class genArrayAccess
extends Script {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;

    public genArrayAccess() {
    }

    public genArrayAccess(Binding context) {
        super(context);
    }

    public static void main(String ... args) {
        IndyInterface.bootstrap("invoke", "runScript", 0, InvokerHelper.class, genArrayAccess.class, args);
    }

    @Override
    public Object run() {
        return IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "genInners", 2, this)}, new String[]{"\npackage org.codehaus.groovy.runtime.dgmimpl;\n\nimport groovy.lang.MetaClassImpl;\nimport groovy.lang.MetaMethod;\nimport org.codehaus.groovy.reflection.CachedClass;\nimport org.codehaus.groovy.reflection.ReflectionCache;\nimport org.codehaus.groovy.runtime.callsite.CallSite;\nimport org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite;\n\npublic class ArrayOperations {\n  ", "\n}\n"}));
    }

    public Object genInners() {
        Reference<String> res = new Reference<String>("");
        Map primitives = ScriptBytecodeAdapter.createMap(new Object[]{"boolean", "Boolean", "byte", "Byte", "char", "Character", "short", "Short", "int", "Integer", "long", "Long", "float", "Float", "double", "Double"});
        public final class _genInners_closure1
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference res;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _genInners_closure1(Object _outerInstance, Object _thisObject, Reference res) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.res = reference = res;
            }

            public Object doCall(Object primName, Object clsName) {
                CallSite callSite = IndyInterface.bootstrap("invoke", "plus", 0, this.res.get(), new GStringImpl(new Object[]{clsName, primName, clsName, primName, primName, primName, primName, primName, primName, primName, primName, primName, clsName, primName, clsName, primName, primName, clsName, primName, clsName, primName, clsName, primName, clsName, primName, primName, clsName, primName}, new String[]{"\n         public static class ", "ArrayGetAtMetaMethod extends ArrayGetAtMetaMethod {\n            private static final CachedClass ARR_CLASS = ReflectionCache.getCachedClass(", "[].class);\n\n            public Class getReturnType() {\n                return ", ".class;\n            }\n\n            public final CachedClass getDeclaringClass() {\n                return ARR_CLASS;\n            }\n\n            public Object invoke(Object object, Object[] args) {\n                final ", "[] objects = (", "[]) object;\n                return objects[normaliseIndex((Integer) args[0], objects.length)];\n            }\n\n            public CallSite createPojoCallSite(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params, Object receiver, Object[] args) {\n                if (!(args [0] instanceof Integer))\n                  return PojoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);\n                else\n                    return new PojoMetaMethodSite(site, metaClass, metaMethod, params) {\n                        public Object invoke(Object receiver, Object[] args) {\n                            final ", "[] objects = (", "[]) receiver;\n                            return objects[normaliseIndex((Integer) args[0], objects.length)];\n                        }\n\n                        public Object callBinop(Object receiver, Object arg) {\n                            if ((receiver instanceof ", "[] && arg instanceof Integer)\n                                    && checkMetaClass()) {\n                                final ", "[] objects = (", "[]) receiver;\n                                return objects[normaliseIndex((Integer) arg, objects.length)];\n                            }\n                            else\n                              return super.callBinop(receiver,arg);\n                        }\n\n                        public Object invokeBinop(Object receiver, Object arg) {\n                            final ", "[] objects = (", "[]) receiver;\n                            return objects[normaliseIndex((Integer) arg, objects.length)];\n                        }\n                    };\n            }\n         }\n\n\n        public static class ", "ArrayPutAtMetaMethod extends ArrayPutAtMetaMethod {\n            private static final CachedClass OBJECT_CLASS = ReflectionCache.OBJECT_CLASS;\n            private static final CachedClass ARR_CLASS = ReflectionCache.getCachedClass(", "[].class);\n            private static final CachedClass [] PARAM_CLASS_ARR = new CachedClass[] {INTEGER_CLASS, OBJECT_CLASS};\n\n            public ", "ArrayPutAtMetaMethod() {\n                parameterTypes = PARAM_CLASS_ARR;\n            }\n\n            public final CachedClass getDeclaringClass() {\n                return ARR_CLASS;\n            }\n\n            public Object invoke(Object object, Object[] args) {\n                final ", "[] objects = (", "[]) object;\n                final int index = normaliseIndex((Integer) args[0], objects.length);\n                Object newValue = args[1];\n                if (!(newValue instanceof ", ")) {\n                    Number n = (Number) newValue;\n                    objects[index] = ((Number)newValue).", "Value();\n                }\n                else\n                  objects[index] = ((", ")args[1]).", "Value();\n                return null;\n            }\n\n            public CallSite createPojoCallSite(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params, Object receiver, Object[] args) {\n                if (!(args [0] instanceof Integer) || !(args [1] instanceof ", "))\n                  return PojoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);\n                else\n                    return new PojoMetaMethodSite(site, metaClass, metaMethod, params) {\n                        public Object call(Object receiver, Object[] args) {\n                            if ((receiver instanceof ", "[] && args[0] instanceof Integer && args[1] instanceof ", " )\n                                    && checkMetaClass()) {\n                                final ", "[] objects = (", "[]) receiver;\n                                objects[normaliseIndex((Integer) args[0], objects.length)] = ((", ")args[1]).", "Value();\n                                return null;\n                            }\n                            else\n                              return super.call(receiver,args);\n                        }\n                    };\n            }\n        }\n\n       "}));
                this.res.set(callSite);
                return callSite;
            }

            @Generated
            public Object call(Object primName, Object clsName) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, primName, clsName);
            }

            @Generated
            public Object getRes() {
                return this.res.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _genInners_closure1.class) {
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
        IndyInterface.bootstrap("invoke", "each", 0, primitives, new _genInners_closure1(this, this, res));
        return res.get();
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != genArrayAccess.class) {
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

