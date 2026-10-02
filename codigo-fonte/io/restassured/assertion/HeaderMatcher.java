/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.assertion;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.hamcrest.Matcher;

public class HeaderMatcher
implements GroovyObject {
    private Object headerName;
    private Object mappingFunction;
    private Matcher matcher;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729297;
    private static /* synthetic */ SoftReference $callSiteArray;

    public HeaderMatcher() {
        MetaClass metaClass;
        CallSite[] callSiteArray = HeaderMatcher.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public Object validateHeader(Object headers) {
        CallSite[] callSiteArray = HeaderMatcher.$getCallSiteArray();
        Boolean success = true;
        CharSequence message = "";
        Object value = callSiteArray[0].call(headers, this.headerName);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(this.mappingFunction, null)) {
                Object object;
                value = object = callSiteArray[1].call(this.mappingFunction, value);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(this.mappingFunction, null)) {
            Object object;
            value = object = callSiteArray[2].call(this.mappingFunction, value);
        }
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[3].call((Object)this.matcher, value))) {
            Object headersString = callSiteArray[4].call(headers);
            boolean bl = false;
            success = bl;
            GStringImpl gStringImpl = new GStringImpl(new Object[]{this.headerName, this.matcher, value, headersString}, new String[]{"Expected header \"", "\" was not ", ", was \"", "\". Headers are:\n", "\n"});
            message = gStringImpl;
        }
        return ScriptBytecodeAdapter.createMap(new Object[]{"success", success, "errorMessage", message});
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = HeaderMatcher.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(HeaderMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = HeaderMatcher.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, HeaderMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = HeaderMatcher.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(HeaderMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != HeaderMatcher.class) {
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
        CallSite[] callSiteArray = HeaderMatcher.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        HeaderMatcher.__$swapInit();
        __timeStamp__239_neverHappen1702030729297 = l2 = 0L;
        __timeStamp = l = 1702030729297L;
    }

    public Object getHeaderName() {
        return this.headerName;
    }

    public void setHeaderName(Object object) {
        this.headerName = object;
    }

    public Object getMappingFunction() {
        return this.mappingFunction;
    }

    public void setMappingFunction(Object object) {
        this.mappingFunction = object;
    }

    public Matcher getMatcher() {
        return this.matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
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
        stringArray[0] = "getValue";
        stringArray[1] = "apply";
        stringArray[2] = "apply";
        stringArray[3] = "matches";
        stringArray[4] = "toString";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[5];
        HeaderMatcher.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(HeaderMatcher.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = HeaderMatcher.$createCallSiteArray();
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

