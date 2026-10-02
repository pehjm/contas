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
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class genMathModification
extends Script {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;

    public genMathModification() {
    }

    public genMathModification(Binding context) {
        super(context);
    }

    public static void main(String ... args) {
        IndyInterface.bootstrap("invoke", "runScript", 0, InvokerHelper.class, genMathModification.class, args);
    }

    @Override
    public Object run() {
        List ops = ScriptBytecodeAdapter.createList(new Object[]{"plus", "minus", "multiply", "div", "or", "and", "xor", "intdiv", "mod", "leftShift", "rightShift", "rightShiftUnsigned"});
        Reference<Map> numbers = new Reference<Map>(ScriptBytecodeAdapter.createMap(new Object[]{"Byte", "byte", "Short", "short", "Integer", "int", "Long", "long", "Float", "float", "Double", "double"}));
        public final class _run_closure1
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference numbers;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _run_closure1(Object _outerInstance, Object _thisObject, Reference numbers) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.numbers = reference = numbers;
            }

            public Object doCall(Object op) {
                Reference<Object> op2 = new Reference<Object>(op);
                public final class _closure4
                extends Closure
                implements GeneratedClosure {
                    private /* synthetic */ Reference op;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _closure4(Object _outerInstance, Object _thisObject, Reference op) {
                        super(_outerInstance, _thisObject);
                        Reference reference;
                        this.op = reference = op;
                    }

                    public Object doCall(Object wrappedType, Object type) {
                        return IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{type, this.op.get()}, new String[]{"public boolean ", "_", ";"}));
                    }

                    @Generated
                    public Object call(Object wrappedType, Object type) {
                        return IndyInterface.bootstrap("invoke", "doCall", 2, this, wrappedType, type);
                    }

                    @Generated
                    public Object getOp() {
                        return this.op.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _closure4.class) {
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
                return IndyInterface.bootstrap("invoke", "each", 0, this.numbers.get(), new _closure4(this, this.getThisObject(), op2));
            }

            @Generated
            public Object getNumbers() {
                return this.numbers.get();
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
        IndyInterface.bootstrap("invoke", "each", 0, ops, new _run_closure1(this, this, numbers));
        public final class _run_closure2
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference numbers;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _run_closure2(Object _outerInstance, Object _thisObject, Reference numbers) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.numbers = reference = numbers;
            }

            public Object doCall(Object op) {
                Reference<Object> op2 = new Reference<Object>(op);
                IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{op2.get()}, new String[]{"if (\"", "\".equals(name)) {"}));
                public final class _closure5
                extends Closure
                implements GeneratedClosure {
                    private /* synthetic */ Reference op;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _closure5(Object _outerInstance, Object _thisObject, Reference op) {
                        super(_outerInstance, _thisObject);
                        Reference reference;
                        this.op = reference = op;
                    }

                    public Object doCall(Object wrappedType, Object type) {
                        return IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{wrappedType, type, this.op.get()}, new String[]{"if (klazz==", ".class) {\n                ", "_", " = true;\n            }"}));
                    }

                    @Generated
                    public Object call(Object wrappedType, Object type) {
                        return IndyInterface.bootstrap("invoke", "doCall", 2, this, wrappedType, type);
                    }

                    @Generated
                    public Object getOp() {
                        return this.op.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _closure5.class) {
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
                IndyInterface.bootstrap("invoke", "each", 0, this.numbers.get(), new _closure5(this, this.getThisObject(), op2));
                IndyInterface.bootstrap("invoke", "println", 2, this, "if (klazz==Object.class) {");
                public final class _closure6
                extends Closure
                implements GeneratedClosure {
                    private /* synthetic */ Reference op;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _closure6(Object _outerInstance, Object _thisObject, Reference op) {
                        super(_outerInstance, _thisObject);
                        Reference reference;
                        this.op = reference = op;
                    }

                    public Object doCall(Object wrappedType, Object type) {
                        return IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{type, this.op.get()}, new String[]{"", "_", " = true;"}));
                    }

                    @Generated
                    public Object call(Object wrappedType, Object type) {
                        return IndyInterface.bootstrap("invoke", "doCall", 2, this, wrappedType, type);
                    }

                    @Generated
                    public Object getOp() {
                        return this.op.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _closure6.class) {
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
                IndyInterface.bootstrap("invoke", "each", 0, this.numbers.get(), new _closure6(this, this.getThisObject(), op2));
                IndyInterface.bootstrap("invoke", "println", 2, this, "}");
                return IndyInterface.bootstrap("invoke", "println", 2, this, "}");
            }

            @Generated
            public Object getNumbers() {
                return this.numbers.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _run_closure2.class) {
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
        IndyInterface.bootstrap("invoke", "each", 0, ops, new _run_closure2(this, this, numbers));
        public final class _run_closure3
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference numbers;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _run_closure3(Object _outerInstance, Object _thisObject, Reference numbers) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.numbers = reference = numbers;
            }

            public Object doCall(Object op) {
                Reference<Object> op2 = new Reference<Object>(op);
                public final class _closure7
                extends Closure
                implements GeneratedClosure {
                    private /* synthetic */ Reference numbers;
                    private /* synthetic */ Reference op;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _closure7(Object _outerInstance, Object _thisObject, Reference numbers, Reference op) {
                        super(_outerInstance, _thisObject);
                        Reference reference;
                        Reference reference2;
                        this.numbers = reference2 = numbers;
                        this.op = reference = op;
                    }

                    /*
                     * WARNING - void declaration
                     */
                    public Object doCall(Object wrappedType1, Object type1) {
                        void var2_2;
                        Reference<Object> wrappedType12 = new Reference<Object>(wrappedType1);
                        Reference<void> type12 = new Reference<void>(var2_2);
                        public final class _closure8
                        extends Closure
                        implements GeneratedClosure {
                            private /* synthetic */ Reference wrappedType1;
                            private /* synthetic */ Reference op;
                            private /* synthetic */ Reference type1;
                            private static /* synthetic */ ClassInfo $staticClassInfo;
                            public static transient /* synthetic */ boolean __$stMC;

                            public _closure8(Object _outerInstance, Object _thisObject, Reference wrappedType1, Reference op, Reference type1) {
                                super(_outerInstance, _thisObject);
                                Reference reference;
                                Reference reference2;
                                Reference reference3;
                                this.wrappedType1 = reference3 = wrappedType1;
                                this.op = reference2 = op;
                                this.type1 = reference = type1;
                            }

                            public Object doCall(Object wrappedType2, Object type2) {
                                CallSite math = IndyInterface.bootstrap("invoke", "getMath", 2, this, this.wrappedType1.get(), wrappedType2);
                                if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, math, this.op.get())) != false) {
                                    IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "resType", 0, math), this.op.get(), this.type1.get(), type2, this.type1.get(), this.op.get(), this.op.get(), ScriptBytecodeAdapter.compareNotEqual(IndyInterface.bootstrap("getProperty", "resType", 0, math), this.type1.get()) ? IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, "((", IndyInterface.bootstrap("getProperty", "resType", 0, math)), ")op1)") : "op1", IndyInterface.bootstrap("invoke", "getAt", 0, math, this.op.get()), ScriptBytecodeAdapter.compareNotEqual(IndyInterface.bootstrap("getProperty", "resType", 0, math), type2) ? IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, "((", IndyInterface.bootstrap("getProperty", "resType", 0, math)), ")op2)") : "op2"}, new String[]{"public static ", " ", "(", " op1, ", " op2) {\n                   if (instance.", "_", ") {\n                      return ", "Slow(op1, op2);\n                   }\n                   else {\n                      return ", " ", " ", ";\n                   }\n                }"}));
                                    return IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "resType", 0, math), this.op.get(), this.type1.get(), type2, this.op.get(), IndyInterface.bootstrap("getProperty", "resType", 0, math)}, new String[]{"private static ", " ", "Slow(", " op1,", " op2) {\n                      return ((Number)InvokerHelper.invokeMethod(op1, \"", "\", op2)).", "Value();\n                }"}));
                                }
                                return null;
                            }

                            @Generated
                            public Object call(Object wrappedType2, Object type2) {
                                return IndyInterface.bootstrap("invoke", "doCall", 2, this, wrappedType2, type2);
                            }

                            @Generated
                            public Object getWrappedType1() {
                                return this.wrappedType1.get();
                            }

                            @Generated
                            public Object getOp() {
                                return this.op.get();
                            }

                            @Generated
                            public Object getType1() {
                                return this.type1.get();
                            }

                            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                if (this.getClass() != _closure8.class) {
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
                        return IndyInterface.bootstrap("invoke", "each", 0, this.numbers.get(), new _closure8(this, this.getThisObject(), wrappedType12, this.op, type12));
                    }

                    /*
                     * WARNING - void declaration
                     */
                    @Generated
                    public Object call(Object wrappedType1, Object type1) {
                        void var2_2;
                        Reference<Object> wrappedType12 = new Reference<Object>(wrappedType1);
                        Reference<void> type12 = new Reference<void>(var2_2);
                        return IndyInterface.bootstrap("invoke", "doCall", 2, this, wrappedType12.get(), type12.get());
                    }

                    @Generated
                    public Object getNumbers() {
                        return this.numbers.get();
                    }

                    @Generated
                    public Object getOp() {
                        return this.op.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _closure7.class) {
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
                return IndyInterface.bootstrap("invoke", "each", 0, this.numbers.get(), new _closure7(this, this.getThisObject(), this.numbers, op2));
            }

            @Generated
            public Object getNumbers() {
                return this.numbers.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _run_closure3.class) {
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
        return IndyInterface.bootstrap("invoke", "each", 0, ops, new _run_closure3(this, this, numbers));
    }

    public Object isFloatingPoint(Object number) {
        return ScriptBytecodeAdapter.compareEqual(number, "Double") || ScriptBytecodeAdapter.compareEqual(number, "Float");
    }

    public Object isLong(Object number) {
        return ScriptBytecodeAdapter.compareEqual(number, "Long");
    }

    public Object getMath(Object left, Object right) {
        if (DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isFloatingPoint", 2, this, left)) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isFloatingPoint", 2, this, right))) {
            return ScriptBytecodeAdapter.createMap(new Object[]{"resType", "double", "plus", "+", "minus", "-", "multiply", "*", "div", "/"});
        }
        if (DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isLong", 2, this, left)) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isLong", 2, this, right))) {
            return ScriptBytecodeAdapter.createMap(new Object[]{"resType", "long", "plus", "+", "minus", "-", "multiply", "*", "div", "/", "or", "|", "and", "&", "xor", "^", "intdiv", "/", "mod", "%", "leftShift", "<<", "rightShift", ">>", "rightShiftUnsigned", ">>>"});
        }
        return ScriptBytecodeAdapter.createMap(new Object[]{"resType", "int", "plus", "+", "minus", "-", "multiply", "*", "div", "/", "or", "|", "and", "&", "xor", "^", "intdiv", "/", "mod", "%", "leftShift", "<<", "rightShift", ">>", "rightShiftUnsigned", ">>>"});
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != genMathModification.class) {
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

