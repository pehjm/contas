/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.mapping;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.common.mapper.DataToDeserialize;
import io.restassured.internal.common.ObjectDeserializationContextImpl;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import java.lang.ref.SoftReference;
import java.lang.reflect.Type;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;

public class ObjectMapperDeserializationContextImpl
extends ObjectDeserializationContextImpl
implements ObjectMapperDeserializationContext,
GroovyObject {
    private Object contentType;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729394;
    private static /* synthetic */ SoftReference $callSiteArray;

    public ObjectMapperDeserializationContextImpl() {
        MetaClass metaClass;
        CallSite[] callSiteArray = ObjectMapperDeserializationContextImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public String getContentType() {
        CallSite[] callSiteArray = ObjectMapperDeserializationContextImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.contentType, String.class);
    }

    public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
        CallSite[] callSiteArray = ObjectMapperDeserializationContextImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ObjectMapperDeserializationContextImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$2(String name, Object value) {
        CallSite[] callSiteArray = ObjectMapperDeserializationContextImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ObjectMapperDeserializationContextImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$2(String name) {
        CallSite[] callSiteArray = ObjectMapperDeserializationContextImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ObjectMapperDeserializationContextImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ObjectMapperDeserializationContextImpl.class) {
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
        CallSite[] callSiteArray = ObjectMapperDeserializationContextImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        ObjectMapperDeserializationContextImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729394 = l2 = 0L;
        __timeStamp = l = 1702030729394L;
    }

    public void setContentType(Object object) {
        this.contentType = object;
    }

    public /* synthetic */ void super$1$notify() {
        super.notify();
    }

    public /* synthetic */ String super$2$getCharset() {
        return super.getCharset();
    }

    public /* synthetic */ int super$1$hashCode() {
        return super.hashCode();
    }

    public /* synthetic */ Object super$1$clone() {
        return super.clone();
    }

    public /* synthetic */ Type super$2$getType() {
        return super.getType();
    }

    public /* synthetic */ void super$2$setCharset(String string) {
        super.setCharset(string);
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

    public /* synthetic */ boolean super$1$equals(Object object) {
        return super.equals(object);
    }

    public /* synthetic */ DataToDeserialize super$2$getDataToDeserialize() {
        return super.getDataToDeserialize();
    }

    public /* synthetic */ void super$1$finalize() {
        super.finalize();
    }

    public /* synthetic */ Class super$1$getClass() {
        return super.getClass();
    }

    public /* synthetic */ void super$2$setDataToDeserialize(DataToDeserialize dataToDeserialize) {
        super.setDataToDeserialize(dataToDeserialize);
    }

    public /* synthetic */ String super$1$toString() {
        return super.toString();
    }

    public /* synthetic */ void super$2$setType(Type type) {
        super.setType(type);
    }

    public /* synthetic */ void super$1$notifyAll() {
        super.notifyAll();
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[]{};
        return new CallSiteArray(ObjectMapperDeserializationContextImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ObjectMapperDeserializationContextImpl.$createCallSiteArray();
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

