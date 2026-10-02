/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.assertion;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.response.Response;
import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ResponseTimeMatcher
implements GroovyObject {
    private Matcher<Long> matcher;
    private TimeUnit timeUnit;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729283;
    private static /* synthetic */ SoftReference $callSiteArray;

    public ResponseTimeMatcher() {
        MetaClass metaClass;
        CallSite[] callSiteArray = ResponseTimeMatcher.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public Object validate(Response response) {
        CallSite[] callSiteArray = ResponseTimeMatcher.$getCallSiteArray();
        CharSequence errorMessage = "";
        Boolean success = true;
        Object time = callSiteArray[0].call((Object)response, (Object)this.timeUnit);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareLessThanEqual(time, -1)) {
                String string;
                errorMessage = string = "No time was recorded, cannot perform response time validation.";
                boolean bl = false;
                success = bl;
            } else if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call(this.matcher, time))) {
                Object timeMillis = callSiteArray[2].call(response);
                boolean bl = false;
                success = bl;
                GStringImpl gStringImpl = new GStringImpl(new Object[]{this.matcher, callSiteArray[3].call(callSiteArray[4].call((Object)this.timeUnit)), timeMillis, time, callSiteArray[5].call(callSiteArray[6].call((Object)this.timeUnit))}, new String[]{"Expected response time was not ", " ", ", was ", " milliseconds (", " ", ")."});
                errorMessage = gStringImpl;
            }
        } else if (ScriptBytecodeAdapter.compareLessThanEqual(time, -1)) {
            String string;
            errorMessage = string = "No time was recorded, cannot perform response time validation.";
            boolean bl = false;
            success = bl;
        } else if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call(this.matcher, time))) {
            Object timeMillis = callSiteArray[8].call(response);
            boolean bl = false;
            success = bl;
            GStringImpl gStringImpl = new GStringImpl(new Object[]{this.matcher, callSiteArray[9].call(callSiteArray[10].call((Object)this.timeUnit)), timeMillis, time, callSiteArray[11].call(callSiteArray[12].call((Object)this.timeUnit))}, new String[]{"Expected response time was not ", " ", ", was ", " milliseconds (", " ", ")."});
            errorMessage = gStringImpl;
        }
        return ScriptBytecodeAdapter.createMap(new Object[]{"success", success, "errorMessage", errorMessage});
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = ResponseTimeMatcher.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ResponseTimeMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = ResponseTimeMatcher.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ResponseTimeMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = ResponseTimeMatcher.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ResponseTimeMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ResponseTimeMatcher.class) {
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
        CallSite[] callSiteArray = ResponseTimeMatcher.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        ResponseTimeMatcher.__$swapInit();
        __timeStamp__239_neverHappen1702030729283 = l2 = 0L;
        __timeStamp = l = 1702030729283L;
    }

    public Matcher<Long> getMatcher() {
        return this.matcher;
    }

    public void setMatcher(Matcher<Long> matcher) {
        this.matcher = matcher;
    }

    public TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
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
        stringArray[0] = "getTimeIn";
        stringArray[1] = "matches";
        stringArray[2] = "getTime";
        stringArray[3] = "toLowerCase";
        stringArray[4] = "toString";
        stringArray[5] = "toLowerCase";
        stringArray[6] = "toString";
        stringArray[7] = "matches";
        stringArray[8] = "getTime";
        stringArray[9] = "toLowerCase";
        stringArray[10] = "toString";
        stringArray[11] = "toLowerCase";
        stringArray[12] = "toString";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[13];
        ResponseTimeMatcher.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(ResponseTimeMatcher.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ResponseTimeMatcher.$createCallSiteArray();
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

