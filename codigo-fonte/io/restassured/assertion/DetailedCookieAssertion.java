/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.assertion;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.internal.assertion.CookieMatcher;
import java.lang.ref.SoftReference;
import java.util.List;
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
public class DetailedCookieAssertion
implements GroovyObject {
    private String cookieName;
    private Matcher<? super Cookie> matcher;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729368;
    private static /* synthetic */ SoftReference $callSiteArray;

    public DetailedCookieAssertion() {
        MetaClass metaClass;
        CallSite[] callSiteArray = DetailedCookieAssertion.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public Object validateCookies(List<String> headerWithCookieList, Cookies responseCookies) {
        Object object;
        CallSite[] callSiteArray = DetailedCookieAssertion.$getCallSiteArray();
        Object success = null;
        CharSequence errorMessage = "";
        Cookies cookiesInHeader = (Cookies)ScriptBytecodeAdapter.castToType(callSiteArray[0].call(CookieMatcher.class, headerWithCookieList), Cookies.class);
        Cookie cookie = (Cookie)ScriptBytecodeAdapter.castToType(callSiteArray[1].call((Object)cookiesInHeader, this.cookieName), Cookie.class);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(cookie, null)) {
                Object object2 = callSiteArray[2].call((Object)responseCookies, this.cookieName);
                cookie = (Cookie)ScriptBytecodeAdapter.castToType(object2, Cookie.class);
            } else if (ScriptBytecodeAdapter.compareEqual(callSiteArray[3].call(cookie), null) && ScriptBytecodeAdapter.compareNotEqual(callSiteArray[4].call((Object)responseCookies, this.cookieName), null)) {
                Cookie cookieFromResponse = (Cookie)ScriptBytecodeAdapter.castToType(callSiteArray[5].call((Object)responseCookies, this.cookieName), Cookie.class);
                Object expiryDate = callSiteArray[6].call(cookieFromResponse);
                if (ScriptBytecodeAdapter.compareNotEqual(expiryDate, null)) {
                    Object object3 = callSiteArray[7].call(callSiteArray[8].call(callSiteArray[9].callConstructor(Cookie.Builder.class, cookie), expiryDate));
                    cookie = (Cookie)ScriptBytecodeAdapter.castToType(object3, Cookie.class);
                }
            }
        } else if (ScriptBytecodeAdapter.compareEqual(cookie, null)) {
            Object object4 = callSiteArray[10].call((Object)responseCookies, this.cookieName);
            cookie = (Cookie)ScriptBytecodeAdapter.castToType(object4, Cookie.class);
        } else if (ScriptBytecodeAdapter.compareEqual(callSiteArray[11].call(cookie), null) && ScriptBytecodeAdapter.compareNotEqual(callSiteArray[12].call((Object)responseCookies, this.cookieName), null)) {
            Cookie cookieFromResponse = (Cookie)ScriptBytecodeAdapter.castToType(callSiteArray[13].call((Object)responseCookies, this.cookieName), Cookie.class);
            Object expiryDate = callSiteArray[14].call(cookieFromResponse);
            if (ScriptBytecodeAdapter.compareNotEqual(expiryDate, null)) {
                Object object5 = callSiteArray[15].call(callSiteArray[16].call(callSiteArray[17].callConstructor(Cookie.Builder.class, cookie), expiryDate));
                cookie = (Cookie)ScriptBytecodeAdapter.castToType(object5, Cookie.class);
            }
        }
        success = object = callSiteArray[18].call(this.matcher, cookie);
        if (!DefaultTypeTransformation.booleanUnbox(success)) {
            Object expectedDescription = callSiteArray[19].call(CookieMatcher.class, this.matcher);
            Object mismatchDescription = callSiteArray[20].call(CookieMatcher.class, this.matcher, cookie);
            GStringImpl gStringImpl = new GStringImpl(new Object[]{this.cookieName, expectedDescription, mismatchDescription}, new String[]{"Expected cookie \"", "\" was not ", ", ", ".\n"});
            errorMessage = gStringImpl;
        }
        return ScriptBytecodeAdapter.createMap(new Object[]{"success", success, "errorMessage", errorMessage});
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = DetailedCookieAssertion.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(DetailedCookieAssertion.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = DetailedCookieAssertion.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, DetailedCookieAssertion.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = DetailedCookieAssertion.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(DetailedCookieAssertion.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != DetailedCookieAssertion.class) {
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
        CallSite[] callSiteArray = DetailedCookieAssertion.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        DetailedCookieAssertion.__$swapInit();
        __timeStamp__239_neverHappen1702030729368 = l2 = 0L;
        __timeStamp = l = 1702030729368L;
    }

    public String getCookieName() {
        return this.cookieName;
    }

    public void setCookieName(String string) {
        this.cookieName = string;
    }

    public Matcher<? super Cookie> getMatcher() {
        return this.matcher;
    }

    public void setMatcher(Matcher<? super Cookie> matcher) {
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
        stringArray[0] = "getCookies";
        stringArray[1] = "get";
        stringArray[2] = "get";
        stringArray[3] = "getExpiryDate";
        stringArray[4] = "get";
        stringArray[5] = "get";
        stringArray[6] = "getExpiryDate";
        stringArray[7] = "build";
        stringArray[8] = "setExpiryDate";
        stringArray[9] = "<$constructor$>";
        stringArray[10] = "get";
        stringArray[11] = "getExpiryDate";
        stringArray[12] = "get";
        stringArray[13] = "get";
        stringArray[14] = "getExpiryDate";
        stringArray[15] = "build";
        stringArray[16] = "setExpiryDate";
        stringArray[17] = "<$constructor$>";
        stringArray[18] = "matches";
        stringArray[19] = "getExpectedDescription";
        stringArray[20] = "getMismatchDescription";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[21];
        DetailedCookieAssertion.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(DetailedCookieAssertion.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = DetailedCookieAssertion.$createCallSiteArray();
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

