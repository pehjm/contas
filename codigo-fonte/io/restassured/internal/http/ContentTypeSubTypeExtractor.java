/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.http;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class ContentTypeSubTypeExtractor
implements GroovyObject {
    private static final String COMMA;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729296;
    private static /* synthetic */ SoftReference $callSiteArray;

    public ContentTypeSubTypeExtractor() {
        MetaClass metaClass;
        CallSite[] callSiteArray = ContentTypeSubTypeExtractor.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    /*
     * WARNING - void declaration
     */
    public static String getSubTypeValueFromContentType(String contentType, String subType) {
        void var1_1;
        Reference<String> contentType2 = new Reference<String>(contentType);
        Reference<void> subType2 = new Reference<void>(var1_1);
        CallSite[] callSiteArray = ContentTypeSubTypeExtractor.$getCallSiteArray();
        Reference<Object> foundSubType = new Reference<Object>(null);
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[0].callStatic(StringUtils.class, contentType2.get()))) {
            Object t = null;
            foundSubType.set(t);
        } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call(StringUtils.class, contentType2.get(), (String)subType2.get()))) {
            callSiteArray[2].call(callSiteArray[3].call((Object)contentType2.get(), COMMA), new GeneratedClosure(ContentTypeSubTypeExtractor.class, ContentTypeSubTypeExtractor.class, foundSubType, subType2, contentType2){
                private /* synthetic */ Reference foundSubType;
                private /* synthetic */ Reference subType;
                private /* synthetic */ Reference contentType;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    Reference reference2;
                    Reference reference3;
                    CallSite[] callSiteArray = _getSubTypeValueFromContentType_closure1.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.foundSubType = reference3 = foundSubType;
                    this.subType = reference2 = subType;
                    this.contentType = reference = contentType;
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _getSubTypeValueFromContentType_closure1.$getCallSiteArray();
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(StringUtils.class, this.contentType.get(), this.subType.get()))) {
                        Object equalsSign = callSiteArray[1].call(it, "=");
                        if (ScriptBytecodeAdapter.compareNotEqual(equalsSign, null) && ScriptBytecodeAdapter.compareEqual(callSiteArray[2].callGetProperty(equalsSign), 2) && DefaultTypeTransformation.booleanUnbox(callSiteArray[3].call(callSiteArray[4].call(callSiteArray[5].call(equalsSign, 0)), this.subType.get()))) {
                            Object object = callSiteArray[6].callSafe(callSiteArray[7].call(equalsSign, 1));
                            this.foundSubType.set(object);
                            Object object2 = callSiteArray[8].call(StringUtils.class, this.foundSubType.get(), "\"");
                            this.foundSubType.set(object2);
                            Object object3 = callSiteArray[9].call(StringUtils.class, this.foundSubType.get(), "\"");
                            this.foundSubType.set(object3);
                            return object3;
                        }
                        return null;
                    }
                    return null;
                }

                public Object getFoundSubType() {
                    CallSite[] callSiteArray = _getSubTypeValueFromContentType_closure1.$getCallSiteArray();
                    return this.foundSubType.get();
                }

                public String getSubType() {
                    CallSite[] callSiteArray = _getSubTypeValueFromContentType_closure1.$getCallSiteArray();
                    return (String)ScriptBytecodeAdapter.castToType(this.subType.get(), String.class);
                }

                public String getContentType() {
                    CallSite[] callSiteArray = _getSubTypeValueFromContentType_closure1.$getCallSiteArray();
                    return (String)ScriptBytecodeAdapter.castToType(this.contentType.get(), String.class);
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _getSubTypeValueFromContentType_closure1.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _getSubTypeValueFromContentType_closure1.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _getSubTypeValueFromContentType_closure1.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _getSubTypeValueFromContentType_closure1.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "containsIgnoreCase";
                    stringArray[1] = "split";
                    stringArray[2] = "length";
                    stringArray[3] = "equalsIgnoreCase";
                    stringArray[4] = "trim";
                    stringArray[5] = "getAt";
                    stringArray[6] = "trim";
                    stringArray[7] = "getAt";
                    stringArray[8] = "removeStart";
                    stringArray[9] = "removeEnd";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[10];
                    _getSubTypeValueFromContentType_closure1.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_getSubTypeValueFromContentType_closure1.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _getSubTypeValueFromContentType_closure1.$createCallSiteArray();
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
        return (String)ScriptBytecodeAdapter.castToType(foundSubType.get(), String.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = ContentTypeSubTypeExtractor.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ContentTypeSubTypeExtractor.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = ContentTypeSubTypeExtractor.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ContentTypeSubTypeExtractor.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = ContentTypeSubTypeExtractor.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ContentTypeSubTypeExtractor.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ContentTypeSubTypeExtractor.class) {
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
        CallSite[] callSiteArray = ContentTypeSubTypeExtractor.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        String string;
        long l;
        long l2;
        ContentTypeSubTypeExtractor.__$swapInit();
        __timeStamp__239_neverHappen1702030729296 = l2 = 0L;
        __timeStamp = l = 1702030729296L;
        COMMA = string = ";";
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
        stringArray[0] = "isBlank";
        stringArray[1] = "containsIgnoreCase";
        stringArray[2] = "each";
        stringArray[3] = "split";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[4];
        ContentTypeSubTypeExtractor.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(ContentTypeSubTypeExtractor.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ContentTypeSubTypeExtractor.$createCallSiteArray();
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

