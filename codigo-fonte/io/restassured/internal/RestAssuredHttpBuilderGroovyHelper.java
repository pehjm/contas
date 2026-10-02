/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import java.util.Collection;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RestAssuredHttpBuilderGroovyHelper
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729342;
    private static /* synthetic */ SoftReference $callSiteArray;

    public RestAssuredHttpBuilderGroovyHelper() {
        MetaClass metaClass;
        CallSite[] callSiteArray = RestAssuredHttpBuilderGroovyHelper.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static Collection<String> flattenToString(Collection collection) {
        CallSite[] callSiteArray = RestAssuredHttpBuilderGroovyHelper.$getCallSiteArray();
        return (Collection)ScriptBytecodeAdapter.castToType(callSiteArray[0].call(callSiteArray[1].call(collection), new GeneratedClosure(RestAssuredHttpBuilderGroovyHelper.class, RestAssuredHttpBuilderGroovyHelper.class){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _flattenToString_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _flattenToString_closure1.$getCallSiteArray();
                return callSiteArray[0].callSafe(it);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _flattenToString_closure1.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _flattenToString_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _flattenToString_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _flattenToString_closure1.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "toString";
                return new CallSiteArray(_flattenToString_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _flattenToString_closure1.$createCallSiteArray();
                    $callSiteArray = new SoftReference<CallSiteArray>(callSiteArray);
                }
                return callSiteArray.array;
            }

            static /* synthetic */ Class class$(String string) {
                try {
                    return Class.forName(string);
                }
                catch (ClassNotFoundException classNotFoundException) {
                    throw new NoClassDefFoundError(classNotFoundException.getMessage());
                }
            }
        }), Collection.class);
    }

    public static Closure createClosureThatCalls(Object assertionClosure) {
        Reference<Object> assertionClosure2 = new Reference<Object>(assertionClosure);
        CallSite[] callSiteArray = RestAssuredHttpBuilderGroovyHelper.$getCallSiteArray();
        return new GeneratedClosure(RestAssuredHttpBuilderGroovyHelper.class, RestAssuredHttpBuilderGroovyHelper.class, assertionClosure2){
            private /* synthetic */ Reference assertionClosure;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _createClosureThatCalls_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.assertionClosure = reference = assertionClosure;
            }

            public Object doCall(Object response, Object content) {
                CallSite[] callSiteArray = _createClosureThatCalls_closure2.$getCallSiteArray();
                return callSiteArray[0].call(this.assertionClosure.get(), response, content);
            }

            public Object call(Object response, Object content) {
                CallSite[] callSiteArray = _createClosureThatCalls_closure2.$getCallSiteArray();
                return callSiteArray[1].callCurrent(this, response, content);
            }

            public Object getAssertionClosure() {
                CallSite[] callSiteArray = _createClosureThatCalls_closure2.$getCallSiteArray();
                return this.assertionClosure.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _createClosureThatCalls_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _createClosureThatCalls_closure2.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _createClosureThatCalls_closure2.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "call";
                stringArray[1] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _createClosureThatCalls_closure2.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_createClosureThatCalls_closure2.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _createClosureThatCalls_closure2.$createCallSiteArray();
                    $callSiteArray = new SoftReference<CallSiteArray>(callSiteArray);
                }
                return callSiteArray.array;
            }

            static /* synthetic */ Class class$(String string) {
                try {
                    return Class.forName(string);
                }
                catch (ClassNotFoundException classNotFoundException) {
                    throw new NoClassDefFoundError(classNotFoundException.getMessage());
                }
            }
        };
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = RestAssuredHttpBuilderGroovyHelper.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RestAssuredHttpBuilderGroovyHelper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = RestAssuredHttpBuilderGroovyHelper.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, RestAssuredHttpBuilderGroovyHelper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = RestAssuredHttpBuilderGroovyHelper.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(RestAssuredHttpBuilderGroovyHelper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RestAssuredHttpBuilderGroovyHelper.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    @Override
    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    @Override
    public /* synthetic */ void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    @Override
    public /* synthetic */ Object invokeMethod(String string, Object object) {
        return this.getMetaClass().invokeMethod((Object)this, string, object);
    }

    @Override
    public /* synthetic */ Object getProperty(String string) {
        return this.getMetaClass().getProperty(this, string);
    }

    @Override
    public /* synthetic */ void setProperty(String string, Object object) {
        this.getMetaClass().setProperty(this, string, object);
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = RestAssuredHttpBuilderGroovyHelper.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        RestAssuredHttpBuilderGroovyHelper.__$swapInit();
        __timeStamp__239_neverHappen1702030729342 = l2 = 0L;
        __timeStamp = l = 1702030729342L;
    }

    public /* synthetic */ void super$1$notify() {
        super.notify();
    }

    public /* synthetic */ int super$1$hashCode() {
        return super.hashCode();
    }

    public /* synthetic */ String super$1$toString() {
        return super.toString();
    }

    public /* synthetic */ Object super$1$clone() {
        return super.clone();
    }

    public /* synthetic */ void super$1$wait(long l, int n) {
        super.wait(l, n);
    }

    public /* synthetic */ void super$1$wait(long l) {
        super.wait(l);
    }

    public /* synthetic */ void super$1$wait() {
        super.wait();
    }

    public /* synthetic */ void super$1$notifyAll() {
        super.notifyAll();
    }

    public /* synthetic */ boolean super$1$equals(Object object) {
        return super.equals(object);
    }

    public /* synthetic */ void super$1$finalize() {
        super.finalize();
    }

    public /* synthetic */ Class super$1$getClass() {
        return super.getClass();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
        stringArray[0] = "collect";
        stringArray[1] = "flatten";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[2];
        RestAssuredHttpBuilderGroovyHelper.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(RestAssuredHttpBuilderGroovyHelper.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = RestAssuredHttpBuilderGroovyHelper.$createCallSiteArray();
            $callSiteArray = new SoftReference<CallSiteArray>(callSiteArray);
        }
        return callSiteArray.array;
    }

    static /* synthetic */ Class class$(String string) {
        try {
            return Class.forName(string);
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw new NoClassDefFoundError(classNotFoundException.getMessage());
        }
    }
}

