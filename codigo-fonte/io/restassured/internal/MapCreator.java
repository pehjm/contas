/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.internal.NoParameterValue;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.specification.Argument;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
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
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MapCreator
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729369;
    private static /* synthetic */ SoftReference $callSiteArray;

    public MapCreator() {
        MetaClass metaClass;
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static Map<String, Object> createMapFromParams(CollisionStrategy collisionStrategy, String firstParam, Object firstValue, Object ... parameters) {
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[0].callStatic(MapCreator.class, collisionStrategy, callSiteArray[1].callStatic(MapCreator.class, firstParam, firstValue, parameters)), Map.class);
    }

    public static Map<String, Object> createMapFromParams(CollisionStrategy collisionStrategy, String firstParam, Object ... parameters) {
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[2].callStatic(MapCreator.class, collisionStrategy, callSiteArray[3].callStatic(MapCreator.class, firstParam, parameters)), Map.class);
        }
        return MapCreator.createMapFromObjects(collisionStrategy, new Object[]{MapCreator.createArgumentArray(firstParam, parameters)});
    }

    public static Map<String, Object> createMapFromObjects(CollisionStrategy collisionStrategy, Object ... parameters) {
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(parameters, null) || ScriptBytecodeAdapter.compareLessThan(callSiteArray[4].callGetProperty(parameters), 2)) {
                throw (Throwable)callSiteArray[5].callConstructor(IllegalArgumentException.class, "You must supply at least one key and one value.");
            }
            if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[6].call(callSiteArray[7].callGetProperty(parameters), 2), 0) && ScriptBytecodeAdapter.compareNotEqual(callSiteArray[8].call(callSiteArray[9].callGetProperty(parameters), 3), 0)) {
                throw (Throwable)callSiteArray[10].callConstructor(IllegalArgumentException.class, "You must supply the same number of keys as values.");
            }
        } else {
            if (ScriptBytecodeAdapter.compareEqual(parameters, null) || ScriptBytecodeAdapter.compareLessThan(callSiteArray[11].callGetProperty(parameters), 2)) {
                throw (Throwable)callSiteArray[12].callConstructor(IllegalArgumentException.class, "You must supply at least one key and one value.");
            }
            if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[13].call(callSiteArray[14].callGetProperty(parameters), 2), 0) && ScriptBytecodeAdapter.compareNotEqual(callSiteArray[15].call(callSiteArray[16].callGetProperty(parameters), 3), 0)) {
                throw (Throwable)callSiteArray[17].callConstructor(IllegalArgumentException.class, "You must supply the same number of keys as values.");
            }
        }
        int step = 0;
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareGreaterThanEqual(callSiteArray[18].callGetProperty(parameters), 3) && DefaultTypeTransformation.booleanUnbox(callSiteArray[19].callStatic(MapCreator.class, callSiteArray[20].call((Object)parameters, 1))) && callSiteArray[21].call((Object)parameters, 2) instanceof Matcher) {
                int n;
                step = n = 3;
            } else {
                int n;
                if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[22].call(callSiteArray[23].callGetProperty(parameters), 2), 0)) {
                    throw (Throwable)callSiteArray[24].callConstructor(IllegalArgumentException.class, "You must supply the same number of keys as values.");
                }
                step = n = 2;
            }
        } else if (ScriptBytecodeAdapter.compareGreaterThanEqual(callSiteArray[25].callGetProperty(parameters), 3) && DefaultTypeTransformation.booleanUnbox(callSiteArray[26].callStatic(MapCreator.class, BytecodeInterface8.objectArrayGet(parameters, 1))) && BytecodeInterface8.objectArrayGet(parameters, 2) instanceof Matcher) {
            int n;
            step = n = 3;
        } else {
            int n;
            if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[27].call(callSiteArray[28].callGetProperty(parameters), 2), 0)) {
                throw (Throwable)callSiteArray[29].callConstructor(IllegalArgumentException.class, "You must supply the same number of keys as values.");
            }
            step = n = 2;
        }
        int argumentsDefined = 0;
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            boolean bl = ScriptBytecodeAdapter.compareEqual(step, 3);
            argumentsDefined = bl ? 1 : 0;
        } else {
            int n;
            argumentsDefined = n = step == 3 ? 1 : 0;
        }
        Map map = (Map)ScriptBytecodeAdapter.castToType(callSiteArray[30].callConstructor(LinkedHashMap.class), Map.class);
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[31].callGetProperty(parameters))) {
                Object key = callSiteArray[32].call((Object)parameters, i);
                Object args = null;
                Object val = null;
                if (argumentsDefined == 0) {
                    Object object;
                    Object object2;
                    args = object2 = null;
                    val = object = callSiteArray[33].call((Object)parameters, callSiteArray[34].call((Object)i, 1));
                } else {
                    Object object;
                    Object object3;
                    args = object3 = callSiteArray[35].call((Object)parameters, callSiteArray[36].call((Object)i, 1));
                    val = object = callSiteArray[37].call((Object)parameters, callSiteArray[38].call((Object)i, 2));
                    if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[39].callStatic(MapCreator.class, args))) {
                        throw (Throwable)callSiteArray[40].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{args, key, callSiteArray[41].callGetProperty(Argument.class)}, new String[]{"Illegal argument '", "' passed to body expectation '", "', a list of ", " is required."}));
                    }
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[42].call((Object)map, key)) && ScriptBytecodeAdapter.compareEqual(collisionStrategy, callSiteArray[43].callGetProperty(CollisionStrategy.class))) {
                    Object currentValue = callSiteArray[44].call((Object)map, key);
                    Object value = argumentsDefined != 0 ? callSiteArray[45].callConstructor(ArgsAndValue.class, ScriptBytecodeAdapter.createMap(new Object[]{"args", args, "value", val})) : val;
                    if (currentValue instanceof List) {
                        callSiteArray[46].call(currentValue, value);
                    } else {
                        callSiteArray[47].call(map, key, ScriptBytecodeAdapter.createList(new Object[]{currentValue, value}));
                    }
                } else if (argumentsDefined != 0) {
                    callSiteArray[48].call(map, key, callSiteArray[49].callConstructor(ArgsAndValue.class, ScriptBytecodeAdapter.createMap(new Object[]{"args", args, "value", val})));
                } else {
                    callSiteArray[50].call(map, key, val);
                }
                i = DefaultTypeTransformation.intUnbox(callSiteArray[51].call((Object)i, step));
            }
        } else {
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[52].callGetProperty(parameters))) {
                Object key = BytecodeInterface8.objectArrayGet(parameters, i);
                Object args = null;
                Object val = null;
                if (argumentsDefined == 0) {
                    Object object;
                    Object object4;
                    args = object4 = null;
                    val = object = BytecodeInterface8.objectArrayGet(parameters, i + 1);
                } else {
                    Object object;
                    Object object5;
                    args = object5 = BytecodeInterface8.objectArrayGet(parameters, i + 1);
                    val = object = BytecodeInterface8.objectArrayGet(parameters, i + 2);
                    if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[53].callStatic(MapCreator.class, args))) {
                        throw (Throwable)callSiteArray[54].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{args, key, callSiteArray[55].callGetProperty(Argument.class)}, new String[]{"Illegal argument '", "' passed to body expectation '", "', a list of ", " is required."}));
                    }
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[56].call((Object)map, key)) && ScriptBytecodeAdapter.compareEqual(collisionStrategy, callSiteArray[57].callGetProperty(CollisionStrategy.class))) {
                    Object currentValue = callSiteArray[58].call((Object)map, key);
                    Object value = argumentsDefined != 0 ? callSiteArray[59].callConstructor(ArgsAndValue.class, ScriptBytecodeAdapter.createMap(new Object[]{"args", args, "value", val})) : val;
                    if (currentValue instanceof List) {
                        callSiteArray[60].call(currentValue, value);
                    } else {
                        callSiteArray[61].call(map, key, ScriptBytecodeAdapter.createList(new Object[]{currentValue, value}));
                    }
                } else if (argumentsDefined != 0) {
                    callSiteArray[62].call(map, key, callSiteArray[63].callConstructor(ArgsAndValue.class, ScriptBytecodeAdapter.createMap(new Object[]{"args", args, "value", val})));
                } else {
                    callSiteArray[64].call(map, key, val);
                }
                int cfr_ignored_0 = i + step;
            }
        }
        return map;
    }

    private static boolean isRestAssuredArguments(Object args) {
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return args instanceof List && DefaultTypeTransformation.booleanUnbox(callSiteArray[65].call(args, new GeneratedClosure(MapCreator.class, MapCreator.class){
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    CallSite[] callSiteArray = _isRestAssuredArguments_closure1.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _isRestAssuredArguments_closure1.$getCallSiteArray();
                    return it instanceof Argument;
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _isRestAssuredArguments_closure1.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _isRestAssuredArguments_closure1.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _isRestAssuredArguments_closure1.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _isRestAssuredArguments_closure1.__$swapInit();
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[]{};
                    return new CallSiteArray(_isRestAssuredArguments_closure1.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _isRestAssuredArguments_closure1.$createCallSiteArray();
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
        return args instanceof List && DefaultTypeTransformation.booleanUnbox(callSiteArray[66].call(args, new /* invalid duplicate definition of identical inner class */));
    }

    private static Object[] createArgumentArray(String firstParam, Object ... parameters) {
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        callSiteArray[67].callStatic(AssertParameter.class, firstParam, "firstParam");
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(parameters, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[68].callGetProperty(parameters), 0)) {
                return (Object[])ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createMap(new Object[]{"firstParam", callSiteArray[69].callConstructor(NoParameterValue.class)}), Object[].class);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(parameters, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[70].callGetProperty(parameters), 0)) {
            return (Object[])ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createMap(new Object[]{"firstParam", callSiteArray[71].callConstructor(NoParameterValue.class)}), Object[].class);
        }
        Reference<Object> params = new Reference<Object>(null);
        if (!BytecodeInterface8.isOrigInt() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            List list = ScriptBytecodeAdapter.createList(new Object[]{firstParam, callSiteArray[72].call((Object)parameters, 0)});
            params.set(list);
        } else {
            List list = ScriptBytecodeAdapter.createList(new Object[]{firstParam, BytecodeInterface8.objectArrayGet(parameters, 0)});
            params.set(list);
        }
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[73].callGetProperty(parameters), 1)) {
                callSiteArray[74].call(callSiteArray[75].call((Object)parameters, ScriptBytecodeAdapter.createRange(1, -1, true)), new GeneratedClosure(MapCreator.class, MapCreator.class, params){
                    private /* synthetic */ Reference params;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _createArgumentArray_closure2.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.params = reference = params;
                    }

                    public Object doCall(Object it) {
                        CallSite[] callSiteArray = _createArgumentArray_closure2.$getCallSiteArray();
                        return callSiteArray[0].call(this.params.get(), it);
                    }

                    public Object getParams() {
                        CallSite[] callSiteArray = _createArgumentArray_closure2.$getCallSiteArray();
                        return this.params.get();
                    }

                    public Object doCall() {
                        CallSite[] callSiteArray = _createArgumentArray_closure2.$getCallSiteArray();
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _createArgumentArray_closure2.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _createArgumentArray_closure2.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _createArgumentArray_closure2.__$swapInit();
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[1];
                        stringArray[0] = "leftShift";
                        return new CallSiteArray(_createArgumentArray_closure2.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _createArgumentArray_closure2.$createCallSiteArray();
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
        } else if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[76].callGetProperty(parameters), 1)) {
            callSiteArray[77].call(callSiteArray[78].call((Object)parameters, ScriptBytecodeAdapter.createRange(1, -1, true)), new /* invalid duplicate definition of identical inner class */);
        }
        return (Object[])ScriptBytecodeAdapter.asType(params.get(), Object[].class);
    }

    private static Object[] createArgumentArrayFromKeyAndValue(String firstParam, Object firstValue, Object ... parameters) {
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        callSiteArray[79].callStatic(AssertParameter.class, firstParam, "firstParam");
        callSiteArray[80].callStatic(AssertParameter.class, firstValue, "firstValue");
        Reference<List> params = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[]{firstParam, firstValue}));
        callSiteArray[81].call((Object)parameters, new GeneratedClosure(MapCreator.class, MapCreator.class, params){
            private /* synthetic */ Reference params;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _createArgumentArrayFromKeyAndValue_closure3.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.params = reference = params;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _createArgumentArrayFromKeyAndValue_closure3.$getCallSiteArray();
                return callSiteArray[0].call(this.params.get(), it);
            }

            public Object getParams() {
                CallSite[] callSiteArray = _createArgumentArrayFromKeyAndValue_closure3.$getCallSiteArray();
                return this.params.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _createArgumentArrayFromKeyAndValue_closure3.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _createArgumentArrayFromKeyAndValue_closure3.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _createArgumentArrayFromKeyAndValue_closure3.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _createArgumentArrayFromKeyAndValue_closure3.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "leftShift";
                return new CallSiteArray(_createArgumentArrayFromKeyAndValue_closure3.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _createArgumentArrayFromKeyAndValue_closure3.$createCallSiteArray();
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
        return (Object[])ScriptBytecodeAdapter.asType(params.get(), Object[].class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(MapCreator.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, MapCreator.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(MapCreator.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != MapCreator.class) {
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
        CallSite[] callSiteArray = MapCreator.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        MapCreator.__$swapInit();
        __timeStamp__239_neverHappen1702030729369 = l2 = 0L;
        __timeStamp = l = 1702030729369L;
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
        stringArray[0] = "createMapFromObjects";
        stringArray[1] = "createArgumentArrayFromKeyAndValue";
        stringArray[2] = "createMapFromObjects";
        stringArray[3] = "createArgumentArray";
        stringArray[4] = "length";
        stringArray[5] = "<$constructor$>";
        stringArray[6] = "mod";
        stringArray[7] = "length";
        stringArray[8] = "mod";
        stringArray[9] = "length";
        stringArray[10] = "<$constructor$>";
        stringArray[11] = "length";
        stringArray[12] = "<$constructor$>";
        stringArray[13] = "mod";
        stringArray[14] = "length";
        stringArray[15] = "mod";
        stringArray[16] = "length";
        stringArray[17] = "<$constructor$>";
        stringArray[18] = "length";
        stringArray[19] = "isRestAssuredArguments";
        stringArray[20] = "getAt";
        stringArray[21] = "getAt";
        stringArray[22] = "mod";
        stringArray[23] = "length";
        stringArray[24] = "<$constructor$>";
        stringArray[25] = "length";
        stringArray[26] = "isRestAssuredArguments";
        stringArray[27] = "mod";
        stringArray[28] = "length";
        stringArray[29] = "<$constructor$>";
        stringArray[30] = "<$constructor$>";
        stringArray[31] = "length";
        stringArray[32] = "getAt";
        stringArray[33] = "getAt";
        stringArray[34] = "plus";
        stringArray[35] = "getAt";
        stringArray[36] = "plus";
        stringArray[37] = "getAt";
        stringArray[38] = "plus";
        stringArray[39] = "isRestAssuredArguments";
        stringArray[40] = "<$constructor$>";
        stringArray[41] = "name";
        stringArray[42] = "containsKey";
        stringArray[43] = "MERGE";
        stringArray[44] = "get";
        stringArray[45] = "<$constructor$>";
        stringArray[46] = "leftShift";
        stringArray[47] = "put";
        stringArray[48] = "put";
        stringArray[49] = "<$constructor$>";
        stringArray[50] = "put";
        stringArray[51] = "plus";
        stringArray[52] = "length";
        stringArray[53] = "isRestAssuredArguments";
        stringArray[54] = "<$constructor$>";
        stringArray[55] = "name";
        stringArray[56] = "containsKey";
        stringArray[57] = "MERGE";
        stringArray[58] = "get";
        stringArray[59] = "<$constructor$>";
        stringArray[60] = "leftShift";
        stringArray[61] = "put";
        stringArray[62] = "put";
        stringArray[63] = "<$constructor$>";
        stringArray[64] = "put";
        stringArray[65] = "every";
        stringArray[66] = "every";
        stringArray[67] = "notNull";
        stringArray[68] = "length";
        stringArray[69] = "<$constructor$>";
        stringArray[70] = "length";
        stringArray[71] = "<$constructor$>";
        stringArray[72] = "getAt";
        stringArray[73] = "length";
        stringArray[74] = "each";
        stringArray[75] = "getAt";
        stringArray[76] = "length";
        stringArray[77] = "each";
        stringArray[78] = "getAt";
        stringArray[79] = "notNull";
        stringArray[80] = "notNull";
        stringArray[81] = "each";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[82];
        MapCreator.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(MapCreator.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = MapCreator.$createCallSiteArray();
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

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class CollisionStrategy
    extends Enum<CollisionStrategy>
    implements GroovyObject {
        public static final /* enum */ CollisionStrategy MERGE;
        public static final /* enum */ CollisionStrategy OVERWRITE;
        public static final CollisionStrategy MIN_VALUE;
        public static final CollisionStrategy MAX_VALUE;
        private static final /* synthetic */ CollisionStrategy[] $VALUES;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;
        private static /* synthetic */ SoftReference $callSiteArray;

        public CollisionStrategy() {
            MetaClass metaClass;
            CallSite[] callSiteArray = CollisionStrategy.$getCallSiteArray();
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        public static final CollisionStrategy[] values() {
            CallSite[] callSiteArray = CollisionStrategy.$getCallSiteArray();
            return (CollisionStrategy[])ScriptBytecodeAdapter.castToType($VALUES.clone(), CollisionStrategy[].class);
        }

        public /* synthetic */ CollisionStrategy next() {
            CallSite[] callSiteArray = CollisionStrategy.$getCallSiteArray();
            Object ordinal = callSiteArray[0].call(callSiteArray[1].callCurrent(this));
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                if (ScriptBytecodeAdapter.compareGreaterThanEqual(ordinal, callSiteArray[2].call($VALUES))) {
                    int n = 0;
                    ordinal = n;
                }
            } else if (ScriptBytecodeAdapter.compareGreaterThanEqual(ordinal, callSiteArray[3].call($VALUES))) {
                int n = 0;
                ordinal = n;
            }
            return (CollisionStrategy)ScriptBytecodeAdapter.castToType(callSiteArray[4].call((Object)$VALUES, ordinal), CollisionStrategy.class);
        }

        public /* synthetic */ CollisionStrategy previous() {
            CallSite[] callSiteArray = CollisionStrategy.$getCallSiteArray();
            Object ordinal = callSiteArray[5].call(callSiteArray[6].callCurrent(this));
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                if (ScriptBytecodeAdapter.compareLessThan(ordinal, 0)) {
                    Object object;
                    ordinal = object = callSiteArray[7].call(callSiteArray[8].call($VALUES), 1);
                }
            } else if (ScriptBytecodeAdapter.compareLessThan(ordinal, 0)) {
                Object object;
                ordinal = object = callSiteArray[9].call(callSiteArray[10].call($VALUES), 1);
            }
            return (CollisionStrategy)ScriptBytecodeAdapter.castToType(callSiteArray[11].call((Object)$VALUES, ordinal), CollisionStrategy.class);
        }

        public static CollisionStrategy valueOf(String name) {
            CallSite[] callSiteArray = CollisionStrategy.$getCallSiteArray();
            return (CollisionStrategy)ScriptBytecodeAdapter.castToType(callSiteArray[12].callStatic(CollisionStrategy.class, CollisionStrategy.class, name), CollisionStrategy.class);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public static final /* synthetic */ CollisionStrategy $INIT(Object ... para) {
            CallSite[] callSiteArray = CollisionStrategy.$getCallSiteArray();
            Object[] objectArray = ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{para}, new int[]{0});
            int n = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(objectArray, 1, CollisionStrategy.class);
            Object[] objectArray2 = objectArray;
            if ((n & 1) != 0) {
                objectArray2 = (Object[])objectArray2[0];
            }
            switch (n >> 8) {
                case 0: {
                    return new CollisionStrategy();
                }
                default: {
                    throw new IllegalArgumentException("illegal constructor number");
                }
            }
        }

        static {
            CollisionStrategy collisionStrategy;
            CollisionStrategy collisionStrategy2;
            CollisionStrategy.__$swapInit();
            Object object = CollisionStrategy.$getCallSiteArray()[13].callStatic(CollisionStrategy.class, "MERGE", 0);
            MERGE = (CollisionStrategy)ScriptBytecodeAdapter.castToType(object, CollisionStrategy.class);
            Object object2 = CollisionStrategy.$getCallSiteArray()[14].callStatic(CollisionStrategy.class, "OVERWRITE", 1);
            OVERWRITE = (CollisionStrategy)ScriptBytecodeAdapter.castToType(object2, CollisionStrategy.class);
            MIN_VALUE = collisionStrategy2 = MERGE;
            MAX_VALUE = collisionStrategy = OVERWRITE;
            CollisionStrategy[] collisionStrategyArray = new CollisionStrategy[]{MERGE, OVERWRITE};
            $VALUES = collisionStrategyArray;
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != CollisionStrategy.class) {
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
            CallSite[] callSiteArray = CollisionStrategy.$getCallSiteArray();
            $callSiteArray = null;
        }

        public /* synthetic */ void super$1$notify() {
            super.notify();
        }

        public /* synthetic */ int super$2$hashCode() {
            return super.hashCode();
        }

        public /* synthetic */ Object super$2$clone() {
            return super.clone();
        }

        public /* synthetic */ int super$2$ordinal() {
            return super.ordinal();
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

        public /* synthetic */ int super$2$compareTo(Object object) {
            return super.compareTo(object);
        }

        public /* synthetic */ boolean super$2$equals(Object object) {
            return super.equals(object);
        }

        public /* synthetic */ void super$2$finalize() {
            super.finalize();
        }

        public /* synthetic */ Class super$1$getClass() {
            return super.getClass();
        }

        public /* synthetic */ String super$2$name() {
            return super.name();
        }

        public /* synthetic */ String super$2$toString() {
            return super.toString();
        }

        public /* synthetic */ Class super$2$getDeclaringClass() {
            return super.getDeclaringClass();
        }

        public /* synthetic */ int super$2$compareTo(Enum enum_) {
            return super.compareTo(enum_);
        }

        public /* synthetic */ void super$1$notifyAll() {
            super.notifyAll();
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
            stringArray[0] = "next";
            stringArray[1] = "ordinal";
            stringArray[2] = "size";
            stringArray[3] = "size";
            stringArray[4] = "getAt";
            stringArray[5] = "previous";
            stringArray[6] = "ordinal";
            stringArray[7] = "minus";
            stringArray[8] = "size";
            stringArray[9] = "minus";
            stringArray[10] = "size";
            stringArray[11] = "getAt";
            stringArray[12] = "valueOf";
            stringArray[13] = "$INIT";
            stringArray[14] = "$INIT";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[15];
            CollisionStrategy.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(CollisionStrategy.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = CollisionStrategy.$createCallSiteArray();
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

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class ArgsAndValue
    implements GroovyObject {
        private List<Argument> args;
        private Object value;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;
        private static /* synthetic */ SoftReference $callSiteArray;

        public ArgsAndValue() {
            MetaClass metaClass;
            CallSite[] callSiteArray = ArgsAndValue.$getCallSiteArray();
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
            CallSite[] callSiteArray = ArgsAndValue.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ArgsAndValue.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$1(String name, Object value) {
            CallSite[] callSiteArray = ArgsAndValue.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, ArgsAndValue.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$1(String name) {
            CallSite[] callSiteArray = ArgsAndValue.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(ArgsAndValue.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = ArgsAndValue.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(ArgsAndValue.class, MapCreator.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = ArgsAndValue.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, ArgsAndValue.class, MapCreator.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = ArgsAndValue.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(ArgsAndValue.class, MapCreator.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ArgsAndValue.class) {
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
            CallSite[] callSiteArray = ArgsAndValue.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            ArgsAndValue.__$swapInit();
        }

        public List<Argument> getArgs() {
            return this.args;
        }

        public void setArgs(List<Argument> list) {
            this.args = list;
        }

        public Object getValue() {
            return this.value;
        }

        public void setValue(Object object) {
            this.value = object;
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

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[]{};
            return new CallSiteArray(ArgsAndValue.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = ArgsAndValue.$createCallSiteArray();
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

