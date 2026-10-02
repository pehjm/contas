/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.json;

import groovy.lang.Binding;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.MetaClass;
import io.restassured.internal.common.assertion.Assertion;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JSONAssertion
implements Assertion,
GroovyObject {
    private String key;
    private Map<String, Object> params;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030698431;
    private static /* synthetic */ SoftReference $callSiteArray;

    public JSONAssertion() {
        MetaClass metaClass;
        CallSite[] callSiteArray = JSONAssertion.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Override
    public Object getResult(Object object, Object config) {
        CallSite[] callSiteArray = JSONAssertion.$getCallSiteArray();
        Object result = callSiteArray[0].callCurrent((GroovyObject)this, object);
        return result;
    }

    /*
     * Exception decompiling
     */
    public Object getAsJsonObject(Object object) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [8[CATCHBLOCK]], but top level block is 2[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    public String description() {
        CallSite[] callSiteArray = JSONAssertion.$getCallSiteArray();
        return "JSON path";
    }

    private Object eval(Object root, Object object, Object expr) {
        CallSite[] callSiteArray = JSONAssertion.$getCallSiteArray();
        Map newParams = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(this.params, null)) {
                Object object2 = callSiteArray[41].callConstructor(HashMap.class, this.params);
                newParams = (Map)ScriptBytecodeAdapter.castToType(object2, Map.class);
            } else {
                Object object3 = callSiteArray[42].callConstructor(HashMap.class);
                newParams = (Map)ScriptBytecodeAdapter.castToType(object3, Map.class);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(this.params, null)) {
            Object object4 = callSiteArray[43].callConstructor(HashMap.class, this.params);
            newParams = (Map)ScriptBytecodeAdapter.castToType(object4, Map.class);
        } else {
            Object object5 = callSiteArray[44].callConstructor(HashMap.class);
            newParams = (Map)ScriptBytecodeAdapter.castToType(object5, Map.class);
        }
        callSiteArray[45].call(newParams, root, object);
        GroovyShell sh = (GroovyShell)ScriptBytecodeAdapter.castToType(callSiteArray[46].callConstructor(GroovyShell.class, callSiteArray[47].callConstructor(Binding.class, newParams)), GroovyShell.class);
        Object res = callSiteArray[48].call((Object)sh, expr);
        callSiteArray[49].call(sh);
        return res;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = JSONAssertion.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(JSONAssertion.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = JSONAssertion.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, JSONAssertion.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = JSONAssertion.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(JSONAssertion.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != JSONAssertion.class) {
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
        CallSite[] callSiteArray = JSONAssertion.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        JSONAssertion.__$swapInit();
        __timeStamp__239_neverHappen1702030698431 = l2 = 0L;
        __timeStamp = l = 1702030698431L;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String string) {
        this.key = string;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> map) {
        this.params = map;
    }

    public /* synthetic */ Object this$2$eval(Object object, Object object2, Object object3) {
        return this.eval(object, object2, object3);
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
        stringArray[0] = "getAsJsonObject";
        stringArray[1] = "escapePath";
        stringArray[2] = "hyphen";
        stringArray[3] = "attributeGetter";
        stringArray[4] = "integer";
        stringArray[5] = "properties";
        stringArray[6] = "classKeyword";
        stringArray[7] = "eval";
        stringArray[8] = "getMessage";
        stringArray[9] = "startsWith";
        stringArray[10] = "endsWith";
        stringArray[11] = "format";
        stringArray[12] = "property";
        stringArray[13] = "<$constructor$>";
        stringArray[14] = "getMessage";
        stringArray[15] = "equals";
        stringArray[16] = "startsWith";
        stringArray[17] = "endsWith";
        stringArray[18] = "replace";
        stringArray[19] = "replace";
        stringArray[20] = "getMessage";
        stringArray[21] = "generateWhitespace";
        stringArray[22] = "length";
        stringArray[23] = "<$constructor$>";
        stringArray[24] = "eval";
        stringArray[25] = "getMessage";
        stringArray[26] = "startsWith";
        stringArray[27] = "endsWith";
        stringArray[28] = "format";
        stringArray[29] = "property";
        stringArray[30] = "<$constructor$>";
        stringArray[31] = "getMessage";
        stringArray[32] = "equals";
        stringArray[33] = "startsWith";
        stringArray[34] = "endsWith";
        stringArray[35] = "replace";
        stringArray[36] = "replace";
        stringArray[37] = "getMessage";
        stringArray[38] = "generateWhitespace";
        stringArray[39] = "length";
        stringArray[40] = "<$constructor$>";
        stringArray[41] = "<$constructor$>";
        stringArray[42] = "<$constructor$>";
        stringArray[43] = "<$constructor$>";
        stringArray[44] = "<$constructor$>";
        stringArray[45] = "put";
        stringArray[46] = "<$constructor$>";
        stringArray[47] = "<$constructor$>";
        stringArray[48] = "evaluate";
        stringArray[49] = "resetLoadedClasses";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[50];
        JSONAssertion.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(JSONAssertion.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = JSONAssertion.$createCallSiteArray();
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

