/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.xml;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.xml.StreamingMarkupBuilder;
import groovy.xml.slurpersupport.GPathResult;
import io.restassured.internal.path.xml.XmlRenderer;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class XmlRenderer
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030715480;
    private static /* synthetic */ SoftReference $callSiteArray;

    public XmlRenderer() {
        MetaClass metaClass;
        CallSite[] callSiteArray = XmlRenderer.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static String render(GPathResult result) {
        CallSite[] callSiteArray = XmlRenderer.$getCallSiteArray();
        Reference<Object> stringBuilder = new Reference<Object>(callSiteArray[0].callConstructor(StringBuilder.class));
        callSiteArray[1].call((Object)result, new GeneratedClosure(XmlRenderer.class, XmlRenderer.class, stringBuilder){
            private /* synthetic */ Reference stringBuilder;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _render_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.stringBuilder = reference = stringBuilder;
            }

            public Object doCall(Object it) {
                Reference<Object> it2 = new Reference<Object>(it);
                CallSite[] callSiteArray = _render_closure1.$getCallSiteArray();
                return callSiteArray[0].call(this.stringBuilder.get(), callSiteArray[1].call(callSiteArray[2].callConstructor(StreamingMarkupBuilder.class), new GeneratedClosure(this, this.getThisObject(), it2){
                    private /* synthetic */ Reference it;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _render_closure1_closure2.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.it = reference = it;
                    }

                    public Object doCall(Object xml) {
                        CallSite[] callSiteArray = _render_closure1_closure2.$getCallSiteArray();
                        return callSiteArray[0].call(callSiteArray[1].callGetProperty(xml), this.it.get());
                    }

                    public Object getIt() {
                        CallSite[] callSiteArray = _render_closure1_closure2.$getCallSiteArray();
                        return this.it.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _render_closure1_closure2.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _render_closure1_closure2.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _render_closure1_closure2.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "yield";
                        stringArray[1] = "mkp";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[2];
                        _render_closure1_closure2.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_render_closure1_closure2.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _render_closure1_closure2.$createCallSiteArray();
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
                }));
            }

            public Object getStringBuilder() {
                CallSite[] callSiteArray = _render_closure1.$getCallSiteArray();
                return this.stringBuilder.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _render_closure1.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _render_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _render_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _render_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "leftShift";
                stringArray[1] = "bind";
                stringArray[2] = "<$constructor$>";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _render_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_render_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _render_closure1.$createCallSiteArray();
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
        });
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[2].call(stringBuilder.get()), String.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = XmlRenderer.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(XmlRenderer.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = XmlRenderer.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, XmlRenderer.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = XmlRenderer.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(XmlRenderer.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != XmlRenderer.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    public /* synthetic */ void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public /* synthetic */ Object invokeMethod(String string, Object object) {
        return this.getMetaClass().invokeMethod((Object)this, string, object);
    }

    public /* synthetic */ Object getProperty(String string) {
        return this.getMetaClass().getProperty(this, string);
    }

    public /* synthetic */ void setProperty(String string, Object object) {
        this.getMetaClass().setProperty(this, string, object);
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = XmlRenderer.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        XmlRenderer.__$swapInit();
        __timeStamp__239_neverHappen1702030715480 = l2 = 0L;
        __timeStamp = l = 1702030715480L;
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
        stringArray[0] = "<$constructor$>";
        stringArray[1] = "each";
        stringArray[2] = "toString";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[3];
        XmlRenderer.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(XmlRenderer.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = XmlRenderer.$createCallSiteArray();
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

