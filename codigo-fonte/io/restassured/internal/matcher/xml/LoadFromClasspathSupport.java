/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.matcher.xml;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class LoadFromClasspathSupport
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729284;
    private static /* synthetic */ SoftReference $callSiteArray;

    public LoadFromClasspathSupport() {
        MetaClass metaClass;
        CallSite[] callSiteArray = LoadFromClasspathSupport.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static InputStream loadFromClasspath(String path) {
        CallSite[] callSiteArray = LoadFromClasspathSupport.$getCallSiteArray();
        InputStream stream = (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[0].call(callSiteArray[1].call(callSiteArray[2].call(Thread.class)), path), InputStream.class);
        if (!DefaultTypeTransformation.booleanUnbox(stream)) {
            Object object = callSiteArray[3].call(callSiteArray[4].callStatic(LoadFromClasspathSupport.class), path);
            stream = (InputStream)ScriptBytecodeAdapter.castToType(object, InputStream.class);
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(stream) && DefaultTypeTransformation.booleanUnbox(callSiteArray[5].call((Object)path, "/"))) {
                Object object = callSiteArray[6].call(callSiteArray[7].call(callSiteArray[8].call(Thread.class)), callSiteArray[9].call((Object)path, 1));
                stream = (InputStream)ScriptBytecodeAdapter.castToType(object, InputStream.class);
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(stream) && DefaultTypeTransformation.booleanUnbox(callSiteArray[10].call((Object)path, "/"))) {
            Object object = callSiteArray[11].call(callSiteArray[12].call(callSiteArray[13].call(Thread.class)), callSiteArray[14].call((Object)path, 1));
            stream = (InputStream)ScriptBytecodeAdapter.castToType(object, InputStream.class);
        }
        return stream;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = LoadFromClasspathSupport.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(LoadFromClasspathSupport.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = LoadFromClasspathSupport.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, LoadFromClasspathSupport.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = LoadFromClasspathSupport.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(LoadFromClasspathSupport.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != LoadFromClasspathSupport.class) {
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
        CallSite[] callSiteArray = LoadFromClasspathSupport.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        LoadFromClasspathSupport.__$swapInit();
        __timeStamp__239_neverHappen1702030729284 = l2 = 0L;
        __timeStamp = l = 1702030729284L;
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
        stringArray[0] = "getResourceAsStream";
        stringArray[1] = "getContextClassLoader";
        stringArray[2] = "currentThread";
        stringArray[3] = "getResourceAsStream";
        stringArray[4] = "getClass";
        stringArray[5] = "startsWith";
        stringArray[6] = "getResourceAsStream";
        stringArray[7] = "getContextClassLoader";
        stringArray[8] = "currentThread";
        stringArray[9] = "substring";
        stringArray[10] = "startsWith";
        stringArray[11] = "getResourceAsStream";
        stringArray[12] = "getContextClassLoader";
        stringArray[13] = "currentThread";
        stringArray[14] = "substring";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[15];
        LoadFromClasspathSupport.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(LoadFromClasspathSupport.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = LoadFromClasspathSupport.$createCallSiteArray();
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

