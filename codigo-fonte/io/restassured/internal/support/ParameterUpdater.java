/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.support;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.config.ParamConfig;
import io.restassured.internal.NoParameterValue;
import io.restassured.internal.common.assertion.AssertParameter;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ParameterUpdater
implements GroovyObject {
    private final Serializer serializer;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729285;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public ParameterUpdater(Serializer serializer) {
        void serializer2;
        MetaClass metaClass;
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var4_4 = serializer2;
        this.serializer = (Serializer)ScriptBytecodeAdapter.castToType(var4_4, Serializer.class);
    }

    /*
     * WARNING - void declaration
     */
    public void updateParameters(ParamConfig.UpdateStrategy strategy, Map<String, Object> from, Map<String, Object> to) {
        void var3_3;
        Reference<ParamConfig.UpdateStrategy> strategy2 = new Reference<ParamConfig.UpdateStrategy>(strategy);
        Reference<void> to2 = new Reference<void>(var3_3);
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        callSiteArray[0].callStatic(AssertParameter.class, from, "Map to copy from");
        callSiteArray[1].callStatic(AssertParameter.class, (Map)to2.get(), "Map to copy to");
        callSiteArray[2].callStatic(AssertParameter.class, (Object)strategy2.get(), ParamConfig.UpdateStrategy.class);
        callSiteArray[3].call(from, new GeneratedClosure(this, this, to2, strategy2){
            private /* synthetic */ Reference to;
            private /* synthetic */ Reference strategy;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _updateParameters_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.to = reference2 = to;
                this.strategy = reference = strategy;
            }

            public Object doCall(Object key, Object value) {
                CallSite[] callSiteArray = _updateParameters_closure1.$getCallSiteArray();
                if (value instanceof Collection) {
                    return callSiteArray[0].callCurrent(this, this.strategy.get(), this.to.get(), key, value);
                }
                return callSiteArray[1].callCurrent(this, this.strategy.get(), this.to.get(), key, value);
            }

            public Object call(Object key, Object value) {
                CallSite[] callSiteArray = _updateParameters_closure1.$getCallSiteArray();
                return callSiteArray[2].callCurrent(this, key, value);
            }

            public Map getTo() {
                CallSite[] callSiteArray = _updateParameters_closure1.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.to.get(), Map.class);
            }

            public ParamConfig.UpdateStrategy getStrategy() {
                CallSite[] callSiteArray = _updateParameters_closure1.$getCallSiteArray();
                return (ParamConfig.UpdateStrategy)((Object)ScriptBytecodeAdapter.castToType(this.strategy.get(), ParamConfig.UpdateStrategy.class));
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _updateParameters_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _updateParameters_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _updateParameters_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "updateCollectionParameter";
                stringArray[1] = "updateStandardParameter";
                stringArray[2] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _updateParameters_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_updateParameters_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _updateParameters_closure1.$createCallSiteArray();
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
    }

    public void updateCollectionParameter(ParamConfig.UpdateStrategy strategy, Map<String, Object> to, String key, Collection<Object> values) {
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(values, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[4].call(values))) {
                callSiteArray[5].call(to, key, callSiteArray[6].callConstructor(NoParameterValue.class));
                return;
            }
        } else if (ScriptBytecodeAdapter.compareEqual(values, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call(values))) {
            callSiteArray[8].call(to, key, callSiteArray[9].callConstructor(NoParameterValue.class));
            return;
        }
        Object convertedValues = callSiteArray[10].call(values, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _updateCollectionParameter_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _updateCollectionParameter_closure2.$getCallSiteArray();
                return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), it);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _updateCollectionParameter_closure2.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _updateCollectionParameter_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _updateCollectionParameter_closure2.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _updateCollectionParameter_closure2.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "serializeIfNeeded";
                stringArray[1] = "serializer";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _updateCollectionParameter_closure2.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_updateCollectionParameter_closure2.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _updateCollectionParameter_closure2.$createCallSiteArray();
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
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual((Object)strategy, callSiteArray[11].callGetProperty(ParamConfig.UpdateStrategy.class))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[12].call(to, key))) {
                    Object currentValue = callSiteArray[13].call(to, key);
                    if (currentValue instanceof Collection) {
                        callSiteArray[14].call(currentValue, convertedValues);
                    } else {
                        callSiteArray[15].call(to, key, callSiteArray[16].call(ScriptBytecodeAdapter.createList(new Object[]{currentValue, convertedValues})));
                    }
                } else {
                    callSiteArray[17].call(to, key, callSiteArray[18].callConstructor(LinkedList.class, convertedValues));
                }
            } else {
                callSiteArray[19].call(to, key, convertedValues);
            }
        } else if (ScriptBytecodeAdapter.compareEqual((Object)strategy, callSiteArray[20].callGetProperty(ParamConfig.UpdateStrategy.class))) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[21].call(to, key))) {
                Object currentValue = callSiteArray[22].call(to, key);
                if (currentValue instanceof Collection) {
                    callSiteArray[23].call(currentValue, convertedValues);
                } else {
                    callSiteArray[24].call(to, key, callSiteArray[25].call(ScriptBytecodeAdapter.createList(new Object[]{currentValue, convertedValues})));
                }
            } else {
                callSiteArray[26].call(to, key, callSiteArray[27].callConstructor(LinkedList.class, convertedValues));
            }
        } else {
            callSiteArray[28].call(to, key, convertedValues);
        }
    }

    public void updateZeroToManyParameters(ParamConfig.UpdateStrategy strategy, Map<String, Object> to, String parameterName, Object ... parameterValues) {
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[29].callStatic(ParameterUpdater.class, (Object)parameterValues))) {
                callSiteArray[30].callCurrent(this, (Object)strategy, to, parameterName);
            } else if (ScriptBytecodeAdapter.compareEqual(callSiteArray[31].callGetProperty(parameterValues), 1)) {
                callSiteArray[32].callCurrent(this, (Object)strategy, to, parameterName, callSiteArray[33].call((Object)parameterValues, 0));
            } else {
                callSiteArray[34].callCurrent(this, (Object)strategy, to, parameterName, callSiteArray[35].callStatic(Arrays.class, (Object)parameterValues));
            }
        } else if (ParameterUpdater.isEmpty(parameterValues)) {
            callSiteArray[36].callCurrent(this, (Object)strategy, to, parameterName);
        } else if (ScriptBytecodeAdapter.compareEqual(callSiteArray[37].callGetProperty(parameterValues), 1)) {
            callSiteArray[38].callCurrent(this, (Object)strategy, to, parameterName, BytecodeInterface8.objectArrayGet(parameterValues, 0));
        } else {
            callSiteArray[39].callCurrent(this, (Object)strategy, to, parameterName, callSiteArray[40].callStatic(Arrays.class, (Object)parameterValues));
        }
    }

    public void updateStandardParameter(ParamConfig.UpdateStrategy strategy, Map<String, Object> to, String key) {
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        callSiteArray[41].callCurrent(this, (Object)strategy, to, key, null);
    }

    public void updateStandardParameter(ParamConfig.UpdateStrategy strategy, Map<String, Object> to, String key, Object value) {
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(value, null)) {
                callSiteArray[42].call(to, key, callSiteArray[43].callConstructor(NoParameterValue.class));
                return;
            }
        } else if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            callSiteArray[44].call(to, key, callSiteArray[45].callConstructor(NoParameterValue.class));
            return;
        }
        Object newValue = callSiteArray[46].call((Object)this.serializer, value);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual((Object)strategy, callSiteArray[47].callGetProperty(ParamConfig.UpdateStrategy.class))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[48].call(to, key))) {
                    Object currentValue = callSiteArray[49].call(to, key);
                    if (currentValue instanceof List) {
                        callSiteArray[50].call(currentValue, newValue);
                    } else {
                        callSiteArray[51].call(to, key, ScriptBytecodeAdapter.createList(new Object[]{currentValue, newValue}));
                    }
                } else {
                    callSiteArray[52].call(to, key, newValue);
                }
            } else {
                callSiteArray[53].call(to, key, newValue);
            }
        } else if (ScriptBytecodeAdapter.compareEqual((Object)strategy, callSiteArray[54].callGetProperty(ParamConfig.UpdateStrategy.class))) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[55].call(to, key))) {
                Object currentValue = callSiteArray[56].call(to, key);
                if (currentValue instanceof List) {
                    callSiteArray[57].call(currentValue, newValue);
                } else {
                    callSiteArray[58].call(to, key, ScriptBytecodeAdapter.createList(new Object[]{currentValue, newValue}));
                }
            } else {
                callSiteArray[59].call(to, key, newValue);
            }
        } else {
            callSiteArray[60].call(to, key, newValue);
        }
    }

    private static boolean isEmpty(Object ... objects) {
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareEqual(objects, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[61].callGetProperty(objects), 0) || ScriptBytecodeAdapter.compareEqual(callSiteArray[62].callGetProperty(objects), 1) && callSiteArray[63].call((Object)objects, 0) instanceof NoParameterValue;
        }
        return ScriptBytecodeAdapter.compareEqual(objects, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[64].callGetProperty(objects), 0) || ScriptBytecodeAdapter.compareEqual(callSiteArray[65].callGetProperty(objects), 1) && BytecodeInterface8.objectArrayGet(objects, 0) instanceof NoParameterValue;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ParameterUpdater.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ParameterUpdater.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ParameterUpdater.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ParameterUpdater.class) {
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
        CallSite[] callSiteArray = ParameterUpdater.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        ParameterUpdater.__$swapInit();
        __timeStamp__239_neverHappen1702030729285 = l2 = 0L;
        __timeStamp = l = 1702030729285L;
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
        stringArray[0] = "notNull";
        stringArray[1] = "notNull";
        stringArray[2] = "notNull";
        stringArray[3] = "each";
        stringArray[4] = "isEmpty";
        stringArray[5] = "put";
        stringArray[6] = "<$constructor$>";
        stringArray[7] = "isEmpty";
        stringArray[8] = "put";
        stringArray[9] = "<$constructor$>";
        stringArray[10] = "collect";
        stringArray[11] = "MERGE";
        stringArray[12] = "containsKey";
        stringArray[13] = "get";
        stringArray[14] = "addAll";
        stringArray[15] = "put";
        stringArray[16] = "flatten";
        stringArray[17] = "put";
        stringArray[18] = "<$constructor$>";
        stringArray[19] = "put";
        stringArray[20] = "MERGE";
        stringArray[21] = "containsKey";
        stringArray[22] = "get";
        stringArray[23] = "addAll";
        stringArray[24] = "put";
        stringArray[25] = "flatten";
        stringArray[26] = "put";
        stringArray[27] = "<$constructor$>";
        stringArray[28] = "put";
        stringArray[29] = "isEmpty";
        stringArray[30] = "updateStandardParameter";
        stringArray[31] = "length";
        stringArray[32] = "updateStandardParameter";
        stringArray[33] = "getAt";
        stringArray[34] = "updateCollectionParameter";
        stringArray[35] = "asList";
        stringArray[36] = "updateStandardParameter";
        stringArray[37] = "length";
        stringArray[38] = "updateStandardParameter";
        stringArray[39] = "updateCollectionParameter";
        stringArray[40] = "asList";
        stringArray[41] = "updateStandardParameter";
        stringArray[42] = "put";
        stringArray[43] = "<$constructor$>";
        stringArray[44] = "put";
        stringArray[45] = "<$constructor$>";
        stringArray[46] = "serializeIfNeeded";
        stringArray[47] = "MERGE";
        stringArray[48] = "containsKey";
        stringArray[49] = "get";
        stringArray[50] = "leftShift";
        stringArray[51] = "put";
        stringArray[52] = "put";
        stringArray[53] = "put";
        stringArray[54] = "MERGE";
        stringArray[55] = "containsKey";
        stringArray[56] = "get";
        stringArray[57] = "leftShift";
        stringArray[58] = "put";
        stringArray[59] = "put";
        stringArray[60] = "put";
        stringArray[61] = "length";
        stringArray[62] = "length";
        stringArray[63] = "getAt";
        stringArray[64] = "length";
        stringArray[65] = "length";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[66];
        ParameterUpdater.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(ParameterUpdater.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ParameterUpdater.$createCallSiteArray();
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

    public static interface Serializer {
        public String serializeIfNeeded(Object var1);
    }
}

