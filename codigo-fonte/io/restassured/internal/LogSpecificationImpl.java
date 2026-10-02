/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import java.io.PrintStream;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class LogSpecificationImpl
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729390;
    private static /* synthetic */ SoftReference $callSiteArray;

    public LogSpecificationImpl() {
        MetaClass metaClass;
        CallSite[] callSiteArray = LogSpecificationImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public PrintStream getPrintStream(RequestSpecification requestSpecification) {
        CallSite[] callSiteArray = LogSpecificationImpl.$getCallSiteArray();
        Object stream = callSiteArray[0].callSafe(callSiteArray[1].callCurrent((GroovyObject)this, requestSpecification));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(stream, null)) {
                Object object;
                stream = object = callSiteArray[2].callGetProperty(System.class);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(stream, null)) {
            Object object;
            stream = object = callSiteArray[3].callGetProperty(System.class);
        }
        return (PrintStream)ScriptBytecodeAdapter.castToType(stream, PrintStream.class);
    }

    public boolean shouldUrlEncodeRequestUri(RequestSpecification requestSpecification) {
        CallSite[] callSiteArray = LogSpecificationImpl.$getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(callSiteArray[4].callSafe(callSiteArray[5].callCurrent((GroovyObject)this, requestSpecification)));
    }

    public boolean shouldPrettyPrint(RequestSpecification requestSpecification) {
        CallSite[] callSiteArray = LogSpecificationImpl.$getCallSiteArray();
        Object prettyPrintingEnabled = callSiteArray[6].callSafe(callSiteArray[7].callCurrent((GroovyObject)this, requestSpecification));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(prettyPrintingEnabled, null) : ScriptBytecodeAdapter.compareEqual(prettyPrintingEnabled, null)) {
            return true;
        }
        return DefaultTypeTransformation.booleanUnbox(prettyPrintingEnabled);
    }

    private LogConfig getLogConfig(RequestSpecification requestSpecification) {
        CallSite[] callSiteArray = LogSpecificationImpl.$getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(requestSpecification)) {
            throw (Throwable)callSiteArray[8].callConstructor(IllegalStateException.class, "Cannot configure logging since request specification is not defined. You may be misusing the API.");
        }
        RestAssuredConfig config = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(callSiteArray[9].callGetProperty(requestSpecification), RestAssuredConfig.class);
        return (LogConfig)ScriptBytecodeAdapter.castToType(callSiteArray[10].callGetPropertySafe(config), LogConfig.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = LogSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(LogSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = LogSpecificationImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, LogSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = LogSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(LogSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != LogSpecificationImpl.class) {
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
        CallSite[] callSiteArray = LogSpecificationImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        LogSpecificationImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729390 = l2 = 0L;
        __timeStamp = l = 1702030729390L;
    }

    public /* synthetic */ LogConfig this$2$getLogConfig(RequestSpecification requestSpecification) {
        return this.getLogConfig(requestSpecification);
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
        stringArray[0] = "defaultStream";
        stringArray[1] = "getLogConfig";
        stringArray[2] = "out";
        stringArray[3] = "out";
        stringArray[4] = "shouldUrlEncodeRequestUri";
        stringArray[5] = "getLogConfig";
        stringArray[6] = "isPrettyPrintingEnabled";
        stringArray[7] = "getLogConfig";
        stringArray[8] = "<$constructor$>";
        stringArray[9] = "restAssuredConfig";
        stringArray[10] = "logConfig";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[11];
        LogSpecificationImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(LogSpecificationImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = LogSpecificationImpl.$createCallSiteArray();
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

