/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.common.path;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.util.UUID;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ObjectConverter
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030687233;
    private static /* synthetic */ SoftReference $callSiteArray;

    public ObjectConverter() {
        MetaClass metaClass;
        CallSite[] callSiteArray = ObjectConverter.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static <T> T convertObjectTo(Object object, Class<T> explicitType) {
        Object object2;
        CallSite[] callSiteArray = ObjectConverter.$getCallSiteArray();
        Object returnObject = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object3;
            if (ScriptBytecodeAdapter.compareEqual(object, null)) {
                Object object4;
                returnObject = object4 = null;
                return (T)returnObject;
            }
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(callSiteArray[1].call(object), explicitType))) {
                String toString = (String)ScriptBytecodeAdapter.castToType(callSiteArray[2].call(object), String.class);
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[3].call(explicitType, Integer.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[4].call(explicitType, Integer.TYPE))) {
                    Object object5;
                    returnObject = object5 = callSiteArray[5].call(Integer.class, toString);
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[6].call(explicitType, Boolean.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call(explicitType, Boolean.TYPE))) {
                    Object object6;
                    returnObject = object6 = callSiteArray[8].call(Boolean.class, toString);
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[9].call(explicitType, Character.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[10].call(explicitType, Character.TYPE))) {
                    Object object7;
                    returnObject = object7 = callSiteArray[11].call((Object)toString, 0);
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[12].call(explicitType, Byte.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[13].call(explicitType, Byte.TYPE))) {
                    Object object8;
                    returnObject = object8 = callSiteArray[14].call(Byte.class, toString);
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[15].call(explicitType, Short.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[16].call(explicitType, Short.TYPE))) {
                    Object object9;
                    returnObject = object9 = callSiteArray[17].call(Short.class, toString);
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[18].call(explicitType, Float.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[19].call(explicitType, Float.TYPE))) {
                    Object object10;
                    returnObject = object10 = callSiteArray[20].call(Float.class, toString);
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[21].call(explicitType, Double.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[22].call(explicitType, Double.TYPE))) {
                    Object object11;
                    returnObject = object11 = callSiteArray[23].call(Double.class, toString);
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[24].call(explicitType, Long.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[25].call(explicitType, Long.TYPE))) {
                    Object object12;
                    returnObject = object12 = callSiteArray[26].call(Long.class, toString);
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[27].call(explicitType, BigDecimal.class))) {
                    Object object13;
                    returnObject = object13 = callSiteArray[28].callConstructor(BigDecimal.class, toString);
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[29].call(explicitType, String.class))) {
                    String string = toString;
                    returnObject = string;
                    return (T)returnObject;
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[30].call(explicitType, UUID.class))) {
                    Object object14;
                    returnObject = object14 = callSiteArray[31].call(UUID.class, toString);
                    return (T)returnObject;
                }
                try {
                    Object object15;
                    returnObject = object15 = callSiteArray[32].call(explicitType, object);
                    return (T)returnObject;
                }
                catch (ClassCastException e) {
                    throw (Throwable)callSiteArray[33].callConstructor(ClassCastException.class, callSiteArray[34].call(callSiteArray[35].call((Object)"Cannot convert ", callSiteArray[36].call(object)), new GStringImpl(new Object[]{explicitType}, new String[]{" to ", "."})));
                }
            }
            returnObject = object3 = callSiteArray[37].call(explicitType, object);
            return (T)returnObject;
        }
        if (ScriptBytecodeAdapter.compareEqual(object, null)) {
            Object object16;
            returnObject = object16 = null;
            return (T)returnObject;
        }
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[38].call(callSiteArray[39].call(object), explicitType))) {
            String toString = (String)ScriptBytecodeAdapter.castToType(callSiteArray[40].call(object), String.class);
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[41].call(explicitType, Integer.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[42].call(explicitType, Integer.TYPE))) {
                Object object17;
                returnObject = object17 = callSiteArray[43].call(Integer.class, toString);
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[44].call(explicitType, Boolean.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[45].call(explicitType, Boolean.TYPE))) {
                Object object18;
                returnObject = object18 = callSiteArray[46].call(Boolean.class, toString);
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[47].call(explicitType, Character.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[48].call(explicitType, Character.TYPE))) {
                Object object19;
                returnObject = object19 = callSiteArray[49].call((Object)toString, 0);
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[50].call(explicitType, Byte.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[51].call(explicitType, Byte.TYPE))) {
                Object object20;
                returnObject = object20 = callSiteArray[52].call(Byte.class, toString);
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[53].call(explicitType, Short.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[54].call(explicitType, Short.TYPE))) {
                Object object21;
                returnObject = object21 = callSiteArray[55].call(Short.class, toString);
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[56].call(explicitType, Float.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[57].call(explicitType, Float.TYPE))) {
                Object object22;
                returnObject = object22 = callSiteArray[58].call(Float.class, toString);
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[59].call(explicitType, Double.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[60].call(explicitType, Double.TYPE))) {
                Object object23;
                returnObject = object23 = callSiteArray[61].call(Double.class, toString);
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[62].call(explicitType, Long.class)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[63].call(explicitType, Long.TYPE))) {
                Object object24;
                returnObject = object24 = callSiteArray[64].call(Long.class, toString);
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[65].call(explicitType, BigDecimal.class))) {
                Object object25;
                returnObject = object25 = callSiteArray[66].callConstructor(BigDecimal.class, toString);
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[67].call(explicitType, String.class))) {
                String string = toString;
                returnObject = string;
                return (T)returnObject;
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[68].call(explicitType, UUID.class))) {
                Object object26;
                returnObject = object26 = callSiteArray[69].call(UUID.class, toString);
                return (T)returnObject;
            }
            try {
                Object object27;
                returnObject = object27 = callSiteArray[70].call(explicitType, object);
                return (T)returnObject;
            }
            catch (ClassCastException e) {
                throw (Throwable)callSiteArray[71].callConstructor(ClassCastException.class, callSiteArray[72].call(callSiteArray[73].call((Object)"Cannot convert ", callSiteArray[74].call(object)), new GStringImpl(new Object[]{explicitType}, new String[]{" to ", "."})));
            }
        }
        returnObject = object2 = callSiteArray[75].call(explicitType, object);
        return (T)returnObject;
    }

    public static boolean canConvert(Object object, Class type) {
        CallSite[] callSiteArray = ObjectConverter.$getCallSiteArray();
        callSiteArray[76].callStatic(ObjectConverter.class, object, type);
        boolean bl = true;
        try {
            return bl;
        }
        catch (Exception e) {
            boolean bl2 = false;
            return bl2;
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = ObjectConverter.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ObjectConverter.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = ObjectConverter.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ObjectConverter.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = ObjectConverter.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ObjectConverter.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ObjectConverter.class) {
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
        CallSite[] callSiteArray = ObjectConverter.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        ObjectConverter.__$swapInit();
        __timeStamp__239_neverHappen1702030687233 = l2 = 0L;
        __timeStamp = l = 1702030687233L;
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
        stringArray[0] = "isAssignableFrom";
        stringArray[1] = "getClass";
        stringArray[2] = "toString";
        stringArray[3] = "isAssignableFrom";
        stringArray[4] = "isAssignableFrom";
        stringArray[5] = "parseInt";
        stringArray[6] = "isAssignableFrom";
        stringArray[7] = "isAssignableFrom";
        stringArray[8] = "parseBoolean";
        stringArray[9] = "isAssignableFrom";
        stringArray[10] = "isAssignableFrom";
        stringArray[11] = "charAt";
        stringArray[12] = "isAssignableFrom";
        stringArray[13] = "isAssignableFrom";
        stringArray[14] = "parseByte";
        stringArray[15] = "isAssignableFrom";
        stringArray[16] = "isAssignableFrom";
        stringArray[17] = "parseShort";
        stringArray[18] = "isAssignableFrom";
        stringArray[19] = "isAssignableFrom";
        stringArray[20] = "parseFloat";
        stringArray[21] = "isAssignableFrom";
        stringArray[22] = "isAssignableFrom";
        stringArray[23] = "parseDouble";
        stringArray[24] = "isAssignableFrom";
        stringArray[25] = "isAssignableFrom";
        stringArray[26] = "parseLong";
        stringArray[27] = "isAssignableFrom";
        stringArray[28] = "<$constructor$>";
        stringArray[29] = "isAssignableFrom";
        stringArray[30] = "isAssignableFrom";
        stringArray[31] = "fromString";
        stringArray[32] = "cast";
        stringArray[33] = "<$constructor$>";
        stringArray[34] = "plus";
        stringArray[35] = "plus";
        stringArray[36] = "getClass";
        stringArray[37] = "cast";
        stringArray[38] = "isAssignableFrom";
        stringArray[39] = "getClass";
        stringArray[40] = "toString";
        stringArray[41] = "isAssignableFrom";
        stringArray[42] = "isAssignableFrom";
        stringArray[43] = "parseInt";
        stringArray[44] = "isAssignableFrom";
        stringArray[45] = "isAssignableFrom";
        stringArray[46] = "parseBoolean";
        stringArray[47] = "isAssignableFrom";
        stringArray[48] = "isAssignableFrom";
        stringArray[49] = "charAt";
        stringArray[50] = "isAssignableFrom";
        stringArray[51] = "isAssignableFrom";
        stringArray[52] = "parseByte";
        stringArray[53] = "isAssignableFrom";
        stringArray[54] = "isAssignableFrom";
        stringArray[55] = "parseShort";
        stringArray[56] = "isAssignableFrom";
        stringArray[57] = "isAssignableFrom";
        stringArray[58] = "parseFloat";
        stringArray[59] = "isAssignableFrom";
        stringArray[60] = "isAssignableFrom";
        stringArray[61] = "parseDouble";
        stringArray[62] = "isAssignableFrom";
        stringArray[63] = "isAssignableFrom";
        stringArray[64] = "parseLong";
        stringArray[65] = "isAssignableFrom";
        stringArray[66] = "<$constructor$>";
        stringArray[67] = "isAssignableFrom";
        stringArray[68] = "isAssignableFrom";
        stringArray[69] = "fromString";
        stringArray[70] = "cast";
        stringArray[71] = "<$constructor$>";
        stringArray[72] = "plus";
        stringArray[73] = "plus";
        stringArray[74] = "getClass";
        stringArray[75] = "cast";
        stringArray[76] = "convertObjectTo";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[77];
        ObjectConverter.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(ObjectConverter.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ObjectConverter.$createCallSiteArray();
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

