/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.common.assertion;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.common.assertion.PathFragmentEscaper;
import java.lang.ref.SoftReference;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public abstract class HyphenQuoteFragmentEscaper
implements PathFragmentEscaper,
GroovyObject {
    private static Object indexStartChar;
    private static Object indexEndChar;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030687226;
    private static /* synthetic */ SoftReference $callSiteArray;

    public HyphenQuoteFragmentEscaper() {
        MetaClass metaClass;
        CallSite[] callSiteArray = HyphenQuoteFragmentEscaper.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    /*
     * Unable to fully structure code
     */
    public String escape(String pathFragment) {
        block6: {
            var2_2 = HyphenQuoteFragmentEscaper.$getCallSiteArray();
            indexOfStart = null;
            indexOfEnd = null;
            if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && !HyphenQuoteFragmentEscaper.__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) break block6;
            if (!DefaultTypeTransformation.booleanUnbox(var2_2[0].call(var2_2[1].call(pathFragment), HyphenQuoteFragmentEscaper.indexEndChar))) ** GOTO lbl-1000
            indexOfStart = var5_5 = var2_2[2].call((Object)pathFragment, HyphenQuoteFragmentEscaper.indexStartChar);
            if (ScriptBytecodeAdapter.compareGreaterThan(var5_5, 1)) {
                v0 = true;
            } else lbl-1000:
            // 2 sources

            {
                v0 = false;
            }
            if (v0 != false && ScriptBytecodeAdapter.compareGreaterThan(var2_2[3].call((Object)pathFragment, HyphenQuoteFragmentEscaper.indexEndChar), indexOfStart) != false) {
                toEscape = var2_2[4].call(StringUtils.class, pathFragment, HyphenQuoteFragmentEscaper.indexStartChar);
                indexLookup = var2_2[5].call(HyphenQuoteFragmentEscaper.indexStartChar, var2_2[6].call(StringUtils.class, pathFragment, HyphenQuoteFragmentEscaper.indexStartChar));
                return (String)ScriptBytecodeAdapter.castToType(var2_2[7].call(var2_2[8].callCurrent((GroovyObject)this, toEscape), indexLookup), String.class);
            }
            return (String)ScriptBytecodeAdapter.castToType(var2_2[9].callCurrent((GroovyObject)this, pathFragment), String.class);
        }
        if (!DefaultTypeTransformation.booleanUnbox(var2_2[10].call(var2_2[11].call(pathFragment), HyphenQuoteFragmentEscaper.indexEndChar))) ** GOTO lbl-1000
        indexOfStart = var8_8 = var2_2[12].call((Object)pathFragment, HyphenQuoteFragmentEscaper.indexStartChar);
        if (ScriptBytecodeAdapter.compareGreaterThan(var8_8, 1)) {
            v1 = true;
        } else lbl-1000:
        // 2 sources

        {
            v1 = false;
        }
        if (v1 != false && ScriptBytecodeAdapter.compareGreaterThan(var2_2[13].call((Object)pathFragment, HyphenQuoteFragmentEscaper.indexEndChar), indexOfStart) != false) {
            toEscape = var2_2[14].call(StringUtils.class, pathFragment, HyphenQuoteFragmentEscaper.indexStartChar);
            indexLookup = var2_2[15].call(HyphenQuoteFragmentEscaper.indexStartChar, var2_2[16].call(StringUtils.class, pathFragment, HyphenQuoteFragmentEscaper.indexStartChar));
            return (String)ScriptBytecodeAdapter.castToType(var2_2[17].call(var2_2[18].callCurrent((GroovyObject)this, toEscape), indexLookup), String.class);
        }
        return (String)ScriptBytecodeAdapter.castToType(this.doEscape(pathFragment), String.class);
    }

    private Object doEscape(String str) {
        CallSite[] callSiteArray = HyphenQuoteFragmentEscaper.$getCallSiteArray();
        return callSiteArray[19].call(callSiteArray[20].call((Object)"'", str), "'");
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = HyphenQuoteFragmentEscaper.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(HyphenQuoteFragmentEscaper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = HyphenQuoteFragmentEscaper.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, HyphenQuoteFragmentEscaper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = HyphenQuoteFragmentEscaper.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(HyphenQuoteFragmentEscaper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != HyphenQuoteFragmentEscaper.class) {
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
        CallSite[] callSiteArray = HyphenQuoteFragmentEscaper.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        HyphenQuoteFragmentEscaper.__$swapInit();
        __timeStamp__239_neverHappen1702030687226 = l2 = 0L;
        __timeStamp = l = 1702030687226L;
        String string = "]";
        indexEndChar = string;
        String string2 = "[";
        indexStartChar = string2;
    }

    public /* synthetic */ Object this$2$doEscape(String string) {
        return this.doEscape(string);
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
        stringArray[0] = "endsWith";
        stringArray[1] = "trim";
        stringArray[2] = "indexOf";
        stringArray[3] = "indexOf";
        stringArray[4] = "substringBeforeLast";
        stringArray[5] = "plus";
        stringArray[6] = "substringAfterLast";
        stringArray[7] = "plus";
        stringArray[8] = "doEscape";
        stringArray[9] = "doEscape";
        stringArray[10] = "endsWith";
        stringArray[11] = "trim";
        stringArray[12] = "indexOf";
        stringArray[13] = "indexOf";
        stringArray[14] = "substringBeforeLast";
        stringArray[15] = "plus";
        stringArray[16] = "substringAfterLast";
        stringArray[17] = "plus";
        stringArray[18] = "doEscape";
        stringArray[19] = "plus";
        stringArray[20] = "plus";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[21];
        HyphenQuoteFragmentEscaper.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(HyphenQuoteFragmentEscaper.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = HyphenQuoteFragmentEscaper.$createCallSiteArray();
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

