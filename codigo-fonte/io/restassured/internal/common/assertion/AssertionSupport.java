/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.common.assertion;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.common.assertion.EndToEndQuoteFragmentEscaper;
import io.restassured.internal.common.assertion.GetAtPathFragmentEscaper;
import io.restassured.internal.common.assertion.HyphenQuoteFragmentEscaper;
import io.restassured.internal.common.assertion.PathFragmentEscaper;
import java.lang.ref.SoftReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class AssertionSupport
implements GroovyObject {
    private static Object closureStartFragment;
    private static Object closureEndFragment;
    private static Object listGetterFragment;
    private static Object listIndexStartFragment;
    private static Object listIndexEndFragment;
    private static Object space;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030687229;
    private static /* synthetic */ SoftReference $callSiteArray;

    public AssertionSupport() {
        MetaClass metaClass;
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static Object escapePath(Object key, PathFragmentEscaper ... pathFragmentEscapers) {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        Object pathFragments = callSiteArray[0].call(key, "(?<=\\')");
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[1].call(pathFragments))) {
                String pathFragment = (String)ScriptBytecodeAdapter.castToType(callSiteArray[2].call(pathFragments, i), String.class);
                if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[3].callSafe((Object)pathFragment, "'")) || DefaultTypeTransformation.booleanUnbox(callSiteArray[4].callSafe((Object)pathFragment, "**"))) {
                    Object dotFragments = callSiteArray[5].call((Object)pathFragment, "\\.");
                    int k = 0;
                    while (ScriptBytecodeAdapter.compareLessThan(k, callSiteArray[6].call(dotFragments))) {
                        String dotFragment = (String)ScriptBytecodeAdapter.castToType(callSiteArray[7].call(dotFragments, k), String.class);
                        int j = 0;
                        while (ScriptBytecodeAdapter.compareLessThan(j, callSiteArray[8].callGetProperty(pathFragmentEscapers))) {
                            Object escaper = callSiteArray[9].call((Object)pathFragmentEscapers, j);
                            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[10].call(escaper, dotFragment))) {
                                Object object = callSiteArray[11].call(escaper, callSiteArray[12].call(callSiteArray[13].call(dotFragments, k)));
                                callSiteArray[14].call(dotFragments, k, object);
                                break;
                            }
                            int n = j;
                            j = DefaultTypeTransformation.intUnbox(callSiteArray[15].call(n));
                        }
                        int n = k;
                        k = DefaultTypeTransformation.intUnbox(callSiteArray[16].call(n));
                    }
                    Object object = callSiteArray[17].call(dotFragments, ".");
                    callSiteArray[18].call(pathFragments, i, object);
                }
                int n = i;
                i = DefaultTypeTransformation.intUnbox(callSiteArray[19].call(n));
            }
        } else {
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[20].call(pathFragments))) {
                String pathFragment = (String)ScriptBytecodeAdapter.castToType(callSiteArray[21].call(pathFragments, i), String.class);
                if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[22].callSafe((Object)pathFragment, "'")) || DefaultTypeTransformation.booleanUnbox(callSiteArray[23].callSafe((Object)pathFragment, "**"))) {
                    Object dotFragments = callSiteArray[24].call((Object)pathFragment, "\\.");
                    int k = 0;
                    while (ScriptBytecodeAdapter.compareLessThan(k, callSiteArray[25].call(dotFragments))) {
                        String dotFragment = (String)ScriptBytecodeAdapter.castToType(callSiteArray[26].call(dotFragments, k), String.class);
                        int j = 0;
                        while (ScriptBytecodeAdapter.compareLessThan(j, callSiteArray[27].callGetProperty(pathFragmentEscapers))) {
                            Object escaper = BytecodeInterface8.objectArrayGet(pathFragmentEscapers, j);
                            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[28].call(escaper, dotFragment))) {
                                Object object = callSiteArray[29].call(escaper, callSiteArray[30].call(callSiteArray[31].call(dotFragments, k)));
                                callSiteArray[32].call(dotFragments, k, object);
                                break;
                            }
                            int n = j;
                            int cfr_ignored_0 = n + 1;
                        }
                        int n = k;
                        int cfr_ignored_1 = n + 1;
                    }
                    Object object = callSiteArray[33].call(dotFragments, ".");
                    callSiteArray[34].call(pathFragments, i, object);
                }
                int n = i;
                int cfr_ignored_2 = n + 1;
            }
        }
        return callSiteArray[35].call(pathFragments, "");
    }

    public static Object hyphen() {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        return new 1(AssertionSupport.class);
    }

    public static Object properties() {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        return new 2(AssertionSupport.class);
    }

    public static Object classKeyword() {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        return new 3(AssertionSupport.class);
    }

    public static Object attributeGetter() {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        return new 4(AssertionSupport.class);
    }

    public static Object doubleStar() {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        return new 5(AssertionSupport.class);
    }

    public static Object colon() {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        return new 6(AssertionSupport.class);
    }

    public static Object integer() {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        return new 7(AssertionSupport.class);
    }

    private static boolean startsWithDigit(Object pathFragment) {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[36].call(StringUtils.class, pathFragment))) {
            return false;
        }
        return DefaultTypeTransformation.booleanUnbox(callSiteArray[37].call(Character.class, callSiteArray[38].call(pathFragment, 0)));
    }

    public static String generateWhitespace(int number) {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? number < 1 : number < 1) {
        }
        StringBuilder builder = (StringBuilder)ScriptBytecodeAdapter.castToType(callSiteArray[39].callConstructor(StringBuilder.class), StringBuilder.class);
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, number)) {
                callSiteArray[40].call((Object)builder, " ");
                int n = i;
                i = DefaultTypeTransformation.intUnbox(callSiteArray[41].call(n));
            }
        } else {
            int i = 0;
            while (i < number) {
                callSiteArray[42].call((Object)builder, " ");
                int n = i;
                int cfr_ignored_0 = n + 1;
            }
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[43].call(builder), String.class);
    }

    private static boolean containsAny(String str, Object searchChars) {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(str, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[44].call(str), 0) || ScriptBytecodeAdapter.compareEqual(searchChars, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[45].call(searchChars)) : ScriptBytecodeAdapter.compareEqual(str, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[46].call(str), 0) || ScriptBytecodeAdapter.compareEqual(searchChars, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[47].call(searchChars))) {
            return false;
        }
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[48].call(str))) {
                char ch = DefaultTypeTransformation.charUnbox(callSiteArray[49].call((Object)str, i));
                int j = 0;
                while (ScriptBytecodeAdapter.compareLessThan(j, callSiteArray[50].call(searchChars))) {
                    if (ScriptBytecodeAdapter.compareEqual(callSiteArray[51].call(searchChars, j), Character.valueOf(ch))) {
                        return true;
                    }
                    int n = j;
                    j = DefaultTypeTransformation.intUnbox(callSiteArray[52].call(n));
                }
                int n = i;
                i = DefaultTypeTransformation.intUnbox(callSiteArray[53].call(n));
            }
        } else {
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[54].call(str))) {
                char ch = DefaultTypeTransformation.charUnbox(callSiteArray[55].call((Object)str, i));
                int j = 0;
                while (ScriptBytecodeAdapter.compareLessThan(j, callSiteArray[56].call(searchChars))) {
                    if (ScriptBytecodeAdapter.compareEqual(callSiteArray[57].call(searchChars, j), Character.valueOf(ch))) {
                        return true;
                    }
                    int n = j;
                    int cfr_ignored_0 = n + 1;
                }
                int n = i;
                int cfr_ignored_1 = n + 1;
            }
        }
        return false;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(AssertionSupport.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, AssertionSupport.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(AssertionSupport.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != AssertionSupport.class) {
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
        CallSite[] callSiteArray = AssertionSupport.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        AssertionSupport.__$swapInit();
        __timeStamp__239_neverHappen1702030687229 = l2 = 0L;
        __timeStamp = l = 1702030687229L;
        String string = " ";
        space = string;
        String string2 = "]";
        listIndexEndFragment = string2;
        String string3 = "[";
        listIndexStartFragment = string3;
        String string4 = "(";
        listGetterFragment = string4;
        String string5 = "}";
        closureEndFragment = string5;
        String string6 = "{";
        closureStartFragment = string6;
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
        stringArray[0] = "split";
        stringArray[1] = "size";
        stringArray[2] = "getAt";
        stringArray[3] = "endsWith";
        stringArray[4] = "contains";
        stringArray[5] = "split";
        stringArray[6] = "size";
        stringArray[7] = "getAt";
        stringArray[8] = "length";
        stringArray[9] = "getAt";
        stringArray[10] = "shouldEscape";
        stringArray[11] = "escape";
        stringArray[12] = "trim";
        stringArray[13] = "getAt";
        stringArray[14] = "putAt";
        stringArray[15] = "next";
        stringArray[16] = "next";
        stringArray[17] = "join";
        stringArray[18] = "putAt";
        stringArray[19] = "next";
        stringArray[20] = "size";
        stringArray[21] = "getAt";
        stringArray[22] = "endsWith";
        stringArray[23] = "contains";
        stringArray[24] = "split";
        stringArray[25] = "size";
        stringArray[26] = "getAt";
        stringArray[27] = "length";
        stringArray[28] = "shouldEscape";
        stringArray[29] = "escape";
        stringArray[30] = "trim";
        stringArray[31] = "getAt";
        stringArray[32] = "putAt";
        stringArray[33] = "join";
        stringArray[34] = "putAt";
        stringArray[35] = "join";
        stringArray[36] = "isEmpty";
        stringArray[37] = "isDigit";
        stringArray[38] = "charAt";
        stringArray[39] = "<$constructor$>";
        stringArray[40] = "append";
        stringArray[41] = "next";
        stringArray[42] = "append";
        stringArray[43] = "toString";
        stringArray[44] = "length";
        stringArray[45] = "isEmpty";
        stringArray[46] = "length";
        stringArray[47] = "isEmpty";
        stringArray[48] = "length";
        stringArray[49] = "charAt";
        stringArray[50] = "size";
        stringArray[51] = "getAt";
        stringArray[52] = "next";
        stringArray[53] = "next";
        stringArray[54] = "length";
        stringArray[55] = "charAt";
        stringArray[56] = "size";
        stringArray[57] = "getAt";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[58];
        AssertionSupport.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(AssertionSupport.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = AssertionSupport.$createCallSiteArray();
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

    public class 1
    extends HyphenQuoteFragmentEscaper {
        public /* synthetic */ Class this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 1(Class p0) {
            Class clazz;
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            this.this$0 = clazz = p0;
        }

        public boolean shouldEscape(String pathFragment) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call((Object)pathFragment, "'")) && DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call((Object)pathFragment, "-")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[3].call((Object)pathFragment, "[-")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[4].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[5].callGetProperty(this), callSiteArray[6].callGetProperty(this), callSiteArray[7].callGetProperty(this)})));
            }
            return !DefaultTypeTransformation.booleanUnbox(callSiteArray[8].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[9].call((Object)pathFragment, "'")) && DefaultTypeTransformation.booleanUnbox(callSiteArray[10].call((Object)pathFragment, "-")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[11].call((Object)pathFragment, "[-")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[12].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[13].callGetProperty(this), callSiteArray[14].callGetProperty(this), callSiteArray[15].callGetProperty(this)})));
        }

        public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$2(String name, Object value) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$2(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(1.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, 1.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(1.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return classInfo.getMetaClass();
        }

        public static /* synthetic */ void __$swapInit() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            1.__$swapInit();
        }

        public /* synthetic */ int super$1$hashCode() {
            return super.hashCode();
        }

        public /* synthetic */ void super$2$setProperty(String string, Object object) {
            super.setProperty(string, object);
        }

        public /* synthetic */ void super$1$finalize() {
            super.finalize();
        }

        public /* synthetic */ Object super$2$this$dist$invoke$1(String string, Object object) {
            return super.this$dist$invoke$1(string, object);
        }

        public /* synthetic */ String super$1$toString() {
            return super.toString();
        }

        public /* synthetic */ void super$2$setMetaClass(MetaClass metaClass) {
            super.setMetaClass(metaClass);
        }

        public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
            return super.$getStaticMetaClass();
        }

        public /* synthetic */ void super$1$notify() {
            super.notify();
        }

        public /* synthetic */ Object super$2$invokeMethod(String string, Object object) {
            return super.invokeMethod(string, object);
        }

        public /* synthetic */ Object super$1$clone() {
            return super.clone();
        }

        public /* synthetic */ void super$2$this$dist$set$1(String string, Object object) {
            super.this$dist$set$1(string, object);
        }

        public /* synthetic */ String super$2$escape(String string) {
            return super.escape(string);
        }

        public /* synthetic */ MetaClass super$2$getMetaClass() {
            return super.getMetaClass();
        }

        public /* synthetic */ Object super$2$this$dist$get$1(String string) {
            return super.this$dist$get$1(string);
        }

        public /* synthetic */ void super$1$notifyAll() {
            super.notifyAll();
        }

        public /* synthetic */ Object super$2$getProperty(String string) {
            return super.getProperty(string);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
            stringArray[0] = "startsWith";
            stringArray[1] = "endsWith";
            stringArray[2] = "contains";
            stringArray[3] = "contains";
            stringArray[4] = "containsAny";
            stringArray[5] = "closureStartFragment";
            stringArray[6] = "closureEndFragment";
            stringArray[7] = "listGetterFragment";
            stringArray[8] = "startsWith";
            stringArray[9] = "endsWith";
            stringArray[10] = "contains";
            stringArray[11] = "contains";
            stringArray[12] = "containsAny";
            stringArray[13] = "closureStartFragment";
            stringArray[14] = "closureEndFragment";
            stringArray[15] = "listGetterFragment";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[16];
            1.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(1.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 1.$createCallSiteArray();
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

    public class 2
    extends GetAtPathFragmentEscaper {
        public /* synthetic */ Class this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 2(Class p0) {
            Class clazz;
            CallSite[] callSiteArray = 2.$getCallSiteArray();
            this.this$0 = clazz = p0;
        }

        public boolean shouldEscape(String pathFragment) {
            CallSite[] callSiteArray = 2.$getCallSiteArray();
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call((Object)pathFragment, "'")) && DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call((Object)pathFragment, "properties")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[3].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[4].callGetProperty(this), callSiteArray[5].callGetProperty(this), callSiteArray[6].callGetProperty(this), callSiteArray[7].callGetProperty(this), callSiteArray[8].callGetProperty(this), callSiteArray[9].callGetProperty(this)})));
            }
            return !DefaultTypeTransformation.booleanUnbox(callSiteArray[10].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[11].call((Object)pathFragment, "'")) && DefaultTypeTransformation.booleanUnbox(callSiteArray[12].call((Object)pathFragment, "properties")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[13].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[14].callGetProperty(this), callSiteArray[15].callGetProperty(this), callSiteArray[16].callGetProperty(this), callSiteArray[17].callGetProperty(this), callSiteArray[18].callGetProperty(this), callSiteArray[19].callGetProperty(this)})));
        }

        public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
            CallSite[] callSiteArray = 2.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(2.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$2(String name, Object value) {
            CallSite[] callSiteArray = 2.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 2.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$2(String name) {
            CallSite[] callSiteArray = 2.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(2.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 2.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(2.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 2.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, 2.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 2.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(2.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return classInfo.getMetaClass();
        }

        public static /* synthetic */ void __$swapInit() {
            CallSite[] callSiteArray = 2.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            2.__$swapInit();
        }

        public /* synthetic */ int super$1$hashCode() {
            return super.hashCode();
        }

        public /* synthetic */ void super$2$setProperty(String string, Object object) {
            super.setProperty(string, object);
        }

        public /* synthetic */ void super$1$finalize() {
            super.finalize();
        }

        public /* synthetic */ Object super$2$this$dist$invoke$1(String string, Object object) {
            return super.this$dist$invoke$1(string, object);
        }

        public /* synthetic */ String super$1$toString() {
            return super.toString();
        }

        public /* synthetic */ void super$2$setMetaClass(MetaClass metaClass) {
            super.setMetaClass(metaClass);
        }

        public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
            return super.$getStaticMetaClass();
        }

        public /* synthetic */ void super$1$notify() {
            super.notify();
        }

        public /* synthetic */ Object super$2$invokeMethod(String string, Object object) {
            return super.invokeMethod(string, object);
        }

        public /* synthetic */ Object super$1$clone() {
            return super.clone();
        }

        public /* synthetic */ void super$2$this$dist$set$1(String string, Object object) {
            super.this$dist$set$1(string, object);
        }

        public /* synthetic */ String super$2$escape(String string) {
            return super.escape(string);
        }

        public /* synthetic */ MetaClass super$2$getMetaClass() {
            return super.getMetaClass();
        }

        public /* synthetic */ Object super$2$this$dist$get$1(String string) {
            return super.this$dist$get$1(string);
        }

        public /* synthetic */ void super$1$notifyAll() {
            super.notifyAll();
        }

        public /* synthetic */ Object super$2$getProperty(String string) {
            return super.getProperty(string);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
            stringArray[0] = "startsWith";
            stringArray[1] = "endsWith";
            stringArray[2] = "contains";
            stringArray[3] = "containsAny";
            stringArray[4] = "closureStartFragment";
            stringArray[5] = "closureEndFragment";
            stringArray[6] = "listGetterFragment";
            stringArray[7] = "listIndexStartFragment";
            stringArray[8] = "space";
            stringArray[9] = "listIndexEndFragment";
            stringArray[10] = "startsWith";
            stringArray[11] = "endsWith";
            stringArray[12] = "contains";
            stringArray[13] = "containsAny";
            stringArray[14] = "closureStartFragment";
            stringArray[15] = "closureEndFragment";
            stringArray[16] = "listGetterFragment";
            stringArray[17] = "listIndexStartFragment";
            stringArray[18] = "space";
            stringArray[19] = "listIndexEndFragment";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[20];
            2.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(2.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 2.$createCallSiteArray();
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

    public class 3
    extends GetAtPathFragmentEscaper {
        public /* synthetic */ Class this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 3(Class p0) {
            Class clazz;
            CallSite[] callSiteArray = 3.$getCallSiteArray();
            this.this$0 = clazz = p0;
        }

        public boolean shouldEscape(String pathFragment) {
            CallSite[] callSiteArray = 3.$getCallSiteArray();
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call((Object)pathFragment, "'")) && DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call((Object)pathFragment, "class")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[3].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[4].callGetProperty(this), callSiteArray[5].callGetProperty(this), callSiteArray[6].callGetProperty(this), callSiteArray[7].callGetProperty(this), callSiteArray[8].callGetProperty(this), callSiteArray[9].callGetProperty(this)})));
            }
            return !DefaultTypeTransformation.booleanUnbox(callSiteArray[10].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[11].call((Object)pathFragment, "'")) && DefaultTypeTransformation.booleanUnbox(callSiteArray[12].call((Object)pathFragment, "class")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[13].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[14].callGetProperty(this), callSiteArray[15].callGetProperty(this), callSiteArray[16].callGetProperty(this), callSiteArray[17].callGetProperty(this), callSiteArray[18].callGetProperty(this), callSiteArray[19].callGetProperty(this)})));
        }

        public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
            CallSite[] callSiteArray = 3.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(3.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$2(String name, Object value) {
            CallSite[] callSiteArray = 3.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 3.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$2(String name) {
            CallSite[] callSiteArray = 3.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(3.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 3.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(3.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 3.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, 3.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 3.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(3.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return classInfo.getMetaClass();
        }

        public static /* synthetic */ void __$swapInit() {
            CallSite[] callSiteArray = 3.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            3.__$swapInit();
        }

        public /* synthetic */ int super$1$hashCode() {
            return super.hashCode();
        }

        public /* synthetic */ void super$2$setProperty(String string, Object object) {
            super.setProperty(string, object);
        }

        public /* synthetic */ void super$1$finalize() {
            super.finalize();
        }

        public /* synthetic */ Object super$2$this$dist$invoke$1(String string, Object object) {
            return super.this$dist$invoke$1(string, object);
        }

        public /* synthetic */ String super$1$toString() {
            return super.toString();
        }

        public /* synthetic */ void super$2$setMetaClass(MetaClass metaClass) {
            super.setMetaClass(metaClass);
        }

        public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
            return super.$getStaticMetaClass();
        }

        public /* synthetic */ void super$1$notify() {
            super.notify();
        }

        public /* synthetic */ Object super$2$invokeMethod(String string, Object object) {
            return super.invokeMethod(string, object);
        }

        public /* synthetic */ Object super$1$clone() {
            return super.clone();
        }

        public /* synthetic */ void super$2$this$dist$set$1(String string, Object object) {
            super.this$dist$set$1(string, object);
        }

        public /* synthetic */ String super$2$escape(String string) {
            return super.escape(string);
        }

        public /* synthetic */ MetaClass super$2$getMetaClass() {
            return super.getMetaClass();
        }

        public /* synthetic */ Object super$2$this$dist$get$1(String string) {
            return super.this$dist$get$1(string);
        }

        public /* synthetic */ void super$1$notifyAll() {
            super.notifyAll();
        }

        public /* synthetic */ Object super$2$getProperty(String string) {
            return super.getProperty(string);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
            stringArray[0] = "startsWith";
            stringArray[1] = "endsWith";
            stringArray[2] = "contains";
            stringArray[3] = "containsAny";
            stringArray[4] = "closureStartFragment";
            stringArray[5] = "closureEndFragment";
            stringArray[6] = "listGetterFragment";
            stringArray[7] = "listIndexStartFragment";
            stringArray[8] = "space";
            stringArray[9] = "listIndexEndFragment";
            stringArray[10] = "startsWith";
            stringArray[11] = "endsWith";
            stringArray[12] = "contains";
            stringArray[13] = "containsAny";
            stringArray[14] = "closureStartFragment";
            stringArray[15] = "closureEndFragment";
            stringArray[16] = "listGetterFragment";
            stringArray[17] = "listIndexStartFragment";
            stringArray[18] = "space";
            stringArray[19] = "listIndexEndFragment";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[20];
            3.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(3.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 3.$createCallSiteArray();
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

    public class 4
    extends EndToEndQuoteFragmentEscaper {
        public /* synthetic */ Class this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 4(Class p0) {
            Class clazz;
            CallSite[] callSiteArray = 4.$getCallSiteArray();
            this.this$0 = clazz = p0;
        }

        public boolean shouldEscape(String pathFragment) {
            CallSite[] callSiteArray = 4.$getCallSiteArray();
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call((Object)pathFragment, "@")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[2].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[3].callGetProperty(this), callSiteArray[4].callGetProperty(this), callSiteArray[5].callGetProperty(this)})));
            }
            return DefaultTypeTransformation.booleanUnbox(callSiteArray[6].call((Object)pathFragment, "@")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[8].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[9].callGetProperty(this), callSiteArray[10].callGetProperty(this), callSiteArray[11].callGetProperty(this)})));
        }

        public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
            CallSite[] callSiteArray = 4.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(4.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$2(String name, Object value) {
            CallSite[] callSiteArray = 4.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 4.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$2(String name) {
            CallSite[] callSiteArray = 4.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(4.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 4.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(4.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 4.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, 4.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 4.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(4.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return classInfo.getMetaClass();
        }

        public static /* synthetic */ void __$swapInit() {
            CallSite[] callSiteArray = 4.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            4.__$swapInit();
        }

        public /* synthetic */ int super$1$hashCode() {
            return super.hashCode();
        }

        public /* synthetic */ void super$2$setProperty(String string, Object object) {
            super.setProperty(string, object);
        }

        public /* synthetic */ void super$1$finalize() {
            super.finalize();
        }

        public /* synthetic */ Object super$2$this$dist$invoke$1(String string, Object object) {
            return super.this$dist$invoke$1(string, object);
        }

        public /* synthetic */ String super$1$toString() {
            return super.toString();
        }

        public /* synthetic */ void super$2$setMetaClass(MetaClass metaClass) {
            super.setMetaClass(metaClass);
        }

        public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
            return super.$getStaticMetaClass();
        }

        public /* synthetic */ void super$1$notify() {
            super.notify();
        }

        public /* synthetic */ Object super$2$invokeMethod(String string, Object object) {
            return super.invokeMethod(string, object);
        }

        public /* synthetic */ Object super$1$clone() {
            return super.clone();
        }

        public /* synthetic */ void super$2$this$dist$set$1(String string, Object object) {
            super.this$dist$set$1(string, object);
        }

        public /* synthetic */ String super$2$escape(String string) {
            return super.escape(string);
        }

        public /* synthetic */ MetaClass super$2$getMetaClass() {
            return super.getMetaClass();
        }

        public /* synthetic */ Object super$2$this$dist$get$1(String string) {
            return super.this$dist$get$1(string);
        }

        public /* synthetic */ void super$1$notifyAll() {
            super.notifyAll();
        }

        public /* synthetic */ Object super$2$getProperty(String string) {
            return super.getProperty(string);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
            stringArray[0] = "startsWith";
            stringArray[1] = "endsWith";
            stringArray[2] = "containsAny";
            stringArray[3] = "closureStartFragment";
            stringArray[4] = "closureEndFragment";
            stringArray[5] = "space";
            stringArray[6] = "startsWith";
            stringArray[7] = "endsWith";
            stringArray[8] = "containsAny";
            stringArray[9] = "closureStartFragment";
            stringArray[10] = "closureEndFragment";
            stringArray[11] = "space";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[12];
            4.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(4.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 4.$createCallSiteArray();
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

    public class 5
    extends EndToEndQuoteFragmentEscaper {
        public /* synthetic */ Class this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 5(Class p0) {
            Class clazz;
            CallSite[] callSiteArray = 5.$getCallSiteArray();
            this.this$0 = clazz = p0;
        }

        public boolean shouldEscape(String pathFragment) {
            CallSite[] callSiteArray = 5.$getCallSiteArray();
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return ScriptBytecodeAdapter.compareEqual(pathFragment, "**");
            }
            return ScriptBytecodeAdapter.compareEqual(pathFragment, "**");
        }

        public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
            CallSite[] callSiteArray = 5.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(5.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$2(String name, Object value) {
            CallSite[] callSiteArray = 5.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 5.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$2(String name) {
            CallSite[] callSiteArray = 5.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(5.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 5.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(5.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 5.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, 5.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 5.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(5.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return classInfo.getMetaClass();
        }

        public static /* synthetic */ void __$swapInit() {
            CallSite[] callSiteArray = 5.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            5.__$swapInit();
        }

        public /* synthetic */ int super$1$hashCode() {
            return super.hashCode();
        }

        public /* synthetic */ void super$2$setProperty(String string, Object object) {
            super.setProperty(string, object);
        }

        public /* synthetic */ void super$1$finalize() {
            super.finalize();
        }

        public /* synthetic */ Object super$2$this$dist$invoke$1(String string, Object object) {
            return super.this$dist$invoke$1(string, object);
        }

        public /* synthetic */ String super$1$toString() {
            return super.toString();
        }

        public /* synthetic */ void super$2$setMetaClass(MetaClass metaClass) {
            super.setMetaClass(metaClass);
        }

        public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
            return super.$getStaticMetaClass();
        }

        public /* synthetic */ void super$1$notify() {
            super.notify();
        }

        public /* synthetic */ Object super$2$invokeMethod(String string, Object object) {
            return super.invokeMethod(string, object);
        }

        public /* synthetic */ Object super$1$clone() {
            return super.clone();
        }

        public /* synthetic */ void super$2$this$dist$set$1(String string, Object object) {
            super.this$dist$set$1(string, object);
        }

        public /* synthetic */ String super$2$escape(String string) {
            return super.escape(string);
        }

        public /* synthetic */ MetaClass super$2$getMetaClass() {
            return super.getMetaClass();
        }

        public /* synthetic */ Object super$2$this$dist$get$1(String string) {
            return super.this$dist$get$1(string);
        }

        public /* synthetic */ void super$1$notifyAll() {
            super.notifyAll();
        }

        public /* synthetic */ Object super$2$getProperty(String string) {
            return super.getProperty(string);
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[]{};
            return new CallSiteArray(5.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 5.$createCallSiteArray();
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

    public class 6
    extends HyphenQuoteFragmentEscaper {
        public /* synthetic */ Class this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 6(Class p0) {
            Class clazz;
            CallSite[] callSiteArray = 6.$getCallSiteArray();
            this.this$0 = clazz = p0;
        }

        public boolean shouldEscape(String pathFragment) {
            CallSite[] callSiteArray = 6.$getCallSiteArray();
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call((Object)pathFragment, "'")) && DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call((Object)pathFragment, ":")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[3].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[4].callGetProperty(this), callSiteArray[5].callGetProperty(this), callSiteArray[6].callGetProperty(this)})));
            }
            return !DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call((Object)pathFragment, "'")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[8].call((Object)pathFragment, "'")) && DefaultTypeTransformation.booleanUnbox(callSiteArray[9].call((Object)pathFragment, ":")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[10].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[11].callGetProperty(this), callSiteArray[12].callGetProperty(this), callSiteArray[13].callGetProperty(this)})));
        }

        public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
            CallSite[] callSiteArray = 6.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(6.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$2(String name, Object value) {
            CallSite[] callSiteArray = 6.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 6.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$2(String name) {
            CallSite[] callSiteArray = 6.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(6.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 6.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(6.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 6.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, 6.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 6.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(6.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 6.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return classInfo.getMetaClass();
        }

        public static /* synthetic */ void __$swapInit() {
            CallSite[] callSiteArray = 6.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            6.__$swapInit();
        }

        public /* synthetic */ int super$1$hashCode() {
            return super.hashCode();
        }

        public /* synthetic */ void super$2$setProperty(String string, Object object) {
            super.setProperty(string, object);
        }

        public /* synthetic */ void super$1$finalize() {
            super.finalize();
        }

        public /* synthetic */ Object super$2$this$dist$invoke$1(String string, Object object) {
            return super.this$dist$invoke$1(string, object);
        }

        public /* synthetic */ String super$1$toString() {
            return super.toString();
        }

        public /* synthetic */ void super$2$setMetaClass(MetaClass metaClass) {
            super.setMetaClass(metaClass);
        }

        public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
            return super.$getStaticMetaClass();
        }

        public /* synthetic */ void super$1$notify() {
            super.notify();
        }

        public /* synthetic */ Object super$2$invokeMethod(String string, Object object) {
            return super.invokeMethod(string, object);
        }

        public /* synthetic */ Object super$1$clone() {
            return super.clone();
        }

        public /* synthetic */ void super$2$this$dist$set$1(String string, Object object) {
            super.this$dist$set$1(string, object);
        }

        public /* synthetic */ String super$2$escape(String string) {
            return super.escape(string);
        }

        public /* synthetic */ MetaClass super$2$getMetaClass() {
            return super.getMetaClass();
        }

        public /* synthetic */ Object super$2$this$dist$get$1(String string) {
            return super.this$dist$get$1(string);
        }

        public /* synthetic */ void super$1$notifyAll() {
            super.notifyAll();
        }

        public /* synthetic */ Object super$2$getProperty(String string) {
            return super.getProperty(string);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
            stringArray[0] = "startsWith";
            stringArray[1] = "endsWith";
            stringArray[2] = "contains";
            stringArray[3] = "containsAny";
            stringArray[4] = "closureStartFragment";
            stringArray[5] = "closureEndFragment";
            stringArray[6] = "listGetterFragment";
            stringArray[7] = "startsWith";
            stringArray[8] = "endsWith";
            stringArray[9] = "contains";
            stringArray[10] = "containsAny";
            stringArray[11] = "closureStartFragment";
            stringArray[12] = "closureEndFragment";
            stringArray[13] = "listGetterFragment";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[14];
            6.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(6.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 6.$createCallSiteArray();
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

    public class 7
    extends EndToEndQuoteFragmentEscaper {
        public /* synthetic */ Class this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 7(Class p0) {
            Class clazz;
            CallSite[] callSiteArray = 7.$getCallSiteArray();
            this.this$0 = clazz = p0;
        }

        public boolean shouldEscape(String pathFragment) {
            CallSite[] callSiteArray = 7.$getCallSiteArray();
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return (DefaultTypeTransformation.booleanUnbox(callSiteArray[0].callCurrent((GroovyObject)this, pathFragment)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call(NumberUtils.class, pathFragment))) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[2].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[3].callGetProperty(this), callSiteArray[4].callGetProperty(this), callSiteArray[5].callGetProperty(this), callSiteArray[6].callGetProperty(this), callSiteArray[7].callGetProperty(this), callSiteArray[8].callGetProperty(this)})));
            }
            return (DefaultTypeTransformation.booleanUnbox(callSiteArray[9].callCurrent((GroovyObject)this, pathFragment)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[10].call(NumberUtils.class, pathFragment))) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[11].callCurrent(this, pathFragment, ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[12].callGetProperty(this), callSiteArray[13].callGetProperty(this), callSiteArray[14].callGetProperty(this), callSiteArray[15].callGetProperty(this), callSiteArray[16].callGetProperty(this), callSiteArray[17].callGetProperty(this)})));
        }

        public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
            CallSite[] callSiteArray = 7.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(7.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$2(String name, Object value) {
            CallSite[] callSiteArray = 7.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 7.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$2(String name) {
            CallSite[] callSiteArray = 7.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(7.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 7.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(7.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 7.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, 7.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 7.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(7.class, AssertionSupport.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 7.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return classInfo.getMetaClass();
        }

        public static /* synthetic */ void __$swapInit() {
            CallSite[] callSiteArray = 7.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            7.__$swapInit();
        }

        public /* synthetic */ int super$1$hashCode() {
            return super.hashCode();
        }

        public /* synthetic */ void super$2$setProperty(String string, Object object) {
            super.setProperty(string, object);
        }

        public /* synthetic */ void super$1$finalize() {
            super.finalize();
        }

        public /* synthetic */ Object super$2$this$dist$invoke$1(String string, Object object) {
            return super.this$dist$invoke$1(string, object);
        }

        public /* synthetic */ String super$1$toString() {
            return super.toString();
        }

        public /* synthetic */ void super$2$setMetaClass(MetaClass metaClass) {
            super.setMetaClass(metaClass);
        }

        public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
            return super.$getStaticMetaClass();
        }

        public /* synthetic */ void super$1$notify() {
            super.notify();
        }

        public /* synthetic */ Object super$2$invokeMethod(String string, Object object) {
            return super.invokeMethod(string, object);
        }

        public /* synthetic */ Object super$1$clone() {
            return super.clone();
        }

        public /* synthetic */ void super$2$this$dist$set$1(String string, Object object) {
            super.this$dist$set$1(string, object);
        }

        public /* synthetic */ String super$2$escape(String string) {
            return super.escape(string);
        }

        public /* synthetic */ MetaClass super$2$getMetaClass() {
            return super.getMetaClass();
        }

        public /* synthetic */ Object super$2$this$dist$get$1(String string) {
            return super.this$dist$get$1(string);
        }

        public /* synthetic */ void super$1$notifyAll() {
            super.notifyAll();
        }

        public /* synthetic */ Object super$2$getProperty(String string) {
            return super.getProperty(string);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
            stringArray[0] = "startsWithDigit";
            stringArray[1] = "isDigits";
            stringArray[2] = "containsAny";
            stringArray[3] = "closureStartFragment";
            stringArray[4] = "closureEndFragment";
            stringArray[5] = "space";
            stringArray[6] = "listGetterFragment";
            stringArray[7] = "listIndexStartFragment";
            stringArray[8] = "listIndexEndFragment";
            stringArray[9] = "startsWithDigit";
            stringArray[10] = "isDigits";
            stringArray[11] = "containsAny";
            stringArray[12] = "closureStartFragment";
            stringArray[13] = "closureEndFragment";
            stringArray[14] = "space";
            stringArray[15] = "listGetterFragment";
            stringArray[16] = "listIndexStartFragment";
            stringArray[17] = "listIndexEndFragment";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[18];
            7.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(7.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 7.$createCallSiteArray();
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
}

