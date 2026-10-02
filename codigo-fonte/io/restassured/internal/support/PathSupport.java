/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.support;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import java.lang.ref.SoftReference;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class PathSupport
implements GroovyObject {
    private static final String SLASH;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729344;
    private static /* synthetic */ SoftReference $callSiteArray;

    public PathSupport() {
        MetaClass metaClass;
        CallSite[] callSiteArray = PathSupport.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static String mergeAndRemoveDoubleSlash(String thisOne, String otherOne) {
        CallSite[] callSiteArray = PathSupport.$getCallSiteArray();
        Object object = callSiteArray[0].call(thisOne);
        thisOne = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        Object object2 = callSiteArray[1].call(otherOne);
        otherOne = (String)ScriptBytecodeAdapter.castToType(object2, String.class);
        boolean otherOneStartsWithSlash = DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call((Object)otherOne, SLASH));
        boolean thisOneEndsWithSlash = DefaultTypeTransformation.booleanUnbox(callSiteArray[3].call((Object)thisOne, SLASH));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (thisOneEndsWithSlash && otherOneStartsWithSlash) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[4].call((Object)thisOne, callSiteArray[5].callStatic(StringUtils.class, otherOne, SLASH)), String.class);
            }
            if (thisOneEndsWithSlash && DefaultTypeTransformation.booleanUnbox(callSiteArray[6].callStatic(PathSupport.class, otherOne))) {
                String string;
                thisOne = string = "";
            } else if (!thisOneEndsWithSlash && !otherOneStartsWithSlash && !DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call((Object)ScriptBytecodeAdapter.compareEqual(otherOne, ""), ScriptBytecodeAdapter.compareEqual(thisOne, "")))) {
                return (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{thisOne, otherOne}, new String[]{"", "/", ""}), String.class);
            }
        } else {
            if (thisOneEndsWithSlash && otherOneStartsWithSlash) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[8].call((Object)thisOne, callSiteArray[9].callStatic(StringUtils.class, otherOne, SLASH)), String.class);
            }
            if (thisOneEndsWithSlash && PathSupport.isFullyQualified(otherOne)) {
                String string;
                thisOne = string = "";
            } else if (!thisOneEndsWithSlash && !otherOneStartsWithSlash && !DefaultTypeTransformation.booleanUnbox(callSiteArray[10].call((Object)ScriptBytecodeAdapter.compareEqual(otherOne, ""), ScriptBytecodeAdapter.compareEqual(thisOne, "")))) {
                return (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{thisOne, otherOne}, new String[]{"", "/", ""}), String.class);
            }
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[11].call((Object)thisOne, otherOne), String.class);
    }

    public static boolean isFullyQualified(String targetUri) {
        CallSite[] callSiteArray = PathSupport.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[12].call(StringUtils.class, targetUri))) {
            return false;
        }
        Object indexOfFirstSlash = callSiteArray[13].call((Object)targetUri, "/");
        Object indexOfScheme = callSiteArray[14].call((Object)targetUri, "://");
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(indexOfScheme, -1) : ScriptBytecodeAdapter.compareEqual(indexOfScheme, -1)) {
            return false;
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareLessThan(indexOfScheme, indexOfFirstSlash);
        }
        return ScriptBytecodeAdapter.compareLessThan(indexOfScheme, indexOfFirstSlash);
    }

    public static String getPath(String targetUri) {
        CallSite[] callSiteArray = PathSupport.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[15].call(StringUtils.class, targetUri))) {
            return targetUri;
        }
        Object path = callSiteArray[16].call(StringUtils.class, targetUri, "?");
        Object indexOfScheme = callSiteArray[17].call(path, "://");
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(indexOfScheme, -1)) {
                return (String)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox(callSiteArray[18].call(StringUtils.class, path, "/")) ? path : callSiteArray[19].call((Object)"/", path), String.class);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(indexOfScheme, -1)) {
            return (String)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox(callSiteArray[20].call(StringUtils.class, path, "/")) ? path : callSiteArray[21].call((Object)"/", path), String.class);
        }
        Object indexOfPath = callSiteArray[22].call(StringUtils.class, targetUri, "/", callSiteArray[23].call(indexOfScheme, 3));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(indexOfPath, -1) : ScriptBytecodeAdapter.compareEqual(indexOfPath, -1)) {
            return "/";
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[24].call(StringUtils.class, callSiteArray[25].call((Object)targetUri, indexOfPath), "?"), String.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = PathSupport.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(PathSupport.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = PathSupport.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, PathSupport.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = PathSupport.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(PathSupport.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != PathSupport.class) {
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
        CallSite[] callSiteArray = PathSupport.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        String string;
        long l;
        long l2;
        PathSupport.__$swapInit();
        __timeStamp__239_neverHappen1702030729344 = l2 = 0L;
        __timeStamp = l = 1702030729344L;
        SLASH = string = "/";
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
        stringArray[0] = "trim";
        stringArray[1] = "trim";
        stringArray[2] = "startsWith";
        stringArray[3] = "endsWith";
        stringArray[4] = "plus";
        stringArray[5] = "substringAfter";
        stringArray[6] = "isFullyQualified";
        stringArray[7] = "xor";
        stringArray[8] = "plus";
        stringArray[9] = "substringAfter";
        stringArray[10] = "xor";
        stringArray[11] = "plus";
        stringArray[12] = "isBlank";
        stringArray[13] = "indexOf";
        stringArray[14] = "indexOf";
        stringArray[15] = "isBlank";
        stringArray[16] = "substringBefore";
        stringArray[17] = "indexOf";
        stringArray[18] = "startsWith";
        stringArray[19] = "plus";
        stringArray[20] = "startsWith";
        stringArray[21] = "plus";
        stringArray[22] = "indexOf";
        stringArray[23] = "plus";
        stringArray[24] = "substringBefore";
        stringArray[25] = "substring";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[26];
        PathSupport.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(PathSupport.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = PathSupport.$createCallSiteArray();
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

