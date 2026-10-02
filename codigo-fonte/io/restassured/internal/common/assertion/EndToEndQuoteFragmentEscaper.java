/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.common.assertion;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.common.assertion.PathFragmentEscaper;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public abstract class EndToEndQuoteFragmentEscaper
implements PathFragmentEscaper,
GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030687228;
    private static /* synthetic */ SoftReference $callSiteArray;

    public EndToEndQuoteFragmentEscaper() {
        MetaClass metaClass;
        CallSite[] callSiteArray = EndToEndQuoteFragmentEscaper.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public String escape(String pathFragment) {
        CallSite[] callSiteArray = EndToEndQuoteFragmentEscaper.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[0].call(callSiteArray[1].call((Object)"'", pathFragment), "'"), String.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = EndToEndQuoteFragmentEscaper.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(EndToEndQuoteFragmentEscaper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = EndToEndQuoteFragmentEscaper.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, EndToEndQuoteFragmentEscaper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = EndToEndQuoteFragmentEscaper.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(EndToEndQuoteFragmentEscaper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != EndToEndQuoteFragmentEscaper.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    public MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public Object invokeMethod(String string, Object object) {
        return this.getMetaClass().invokeMethod((Object)this, string, object);
    }

    public Object getProperty(String string) {
        return this.getMetaClass().getProperty(this, string);
    }

    public void setProperty(String string, Object object) {
        this.getMetaClass().setProperty(this, string, object);
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = EndToEndQuoteFragmentEscaper.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        EndToEndQuoteFragmentEscaper.__$swapInit();
        __timeStamp__239_neverHappen1702030687228 = l2 = 0L;
        __timeStamp = l = 1702030687228L;
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
        stringArray[0] = "plus";
        stringArray[1] = "plus";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[2];
        EndToEndQuoteFragmentEscaper.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(EndToEndQuoteFragmentEscaper.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = EndToEndQuoteFragmentEscaper.$createCallSiteArray();
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

