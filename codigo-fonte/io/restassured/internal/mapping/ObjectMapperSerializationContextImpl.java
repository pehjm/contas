/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.mapping;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.mapper.ObjectMapperSerializationContext;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class ObjectMapperSerializationContextImpl
implements ObjectMapperSerializationContext,
GroovyObject {
    private Object object;
    private Object contentType;
    private Object charset;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729293;
    private static /* synthetic */ SoftReference $callSiteArray;

    public ObjectMapperSerializationContextImpl() {
        MetaClass metaClass;
        CallSite[] callSiteArray = ObjectMapperSerializationContextImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public Object getObjectToSerialize() {
        CallSite[] callSiteArray = ObjectMapperSerializationContextImpl.$getCallSiteArray();
        return this.object;
    }

    public Object getObjectToSerializeAs(Class expectedType) {
        CallSite[] callSiteArray = ObjectMapperSerializationContextImpl.$getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call((Object)expectedType, callSiteArray[1].call(this.object)))) {
            throw (Throwable)callSiteArray[2].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{expectedType}, new String[]{"Object to serialize is not of required type ", ""}));
        }
        return callSiteArray[3].call((Object)expectedType, this.object);
    }

    public String getContentType() {
        CallSite[] callSiteArray = ObjectMapperSerializationContextImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.contentType, String.class);
    }

    public String getCharset() {
        CallSite[] callSiteArray = ObjectMapperSerializationContextImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.charset, String.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = ObjectMapperSerializationContextImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ObjectMapperSerializationContextImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = ObjectMapperSerializationContextImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ObjectMapperSerializationContextImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = ObjectMapperSerializationContextImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ObjectMapperSerializationContextImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ObjectMapperSerializationContextImpl.class) {
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
        CallSite[] callSiteArray = ObjectMapperSerializationContextImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        ObjectMapperSerializationContextImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729293 = l2 = 0L;
        __timeStamp = l = 1702030729293L;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setContentType(Object object) {
        this.contentType = object;
    }

    public void setCharset(Object object) {
        this.charset = object;
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
        stringArray[2] = "<$constructor$>";
        stringArray[3] = "cast";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[4];
        ObjectMapperSerializationContextImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(ObjectMapperSerializationContextImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ObjectMapperSerializationContextImpl.$createCallSiteArray();
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

