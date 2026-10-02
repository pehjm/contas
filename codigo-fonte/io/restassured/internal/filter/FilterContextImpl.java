/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.filter;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSender;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ReflectionMethodInvoker;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FilterContextImpl
implements FilterContext,
GroovyObject {
    private Iterator<Filter> filters;
    private Object requestUri;
    private Object substitutedPath;
    private Object originalPath;
    private String method;
    private Object assertionClosure;
    private Map<String, Object> properties;
    private Object internalRequestUri;
    private Object[] unnamedPathParams;
    private String userDefinedPath;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729358;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public FilterContextImpl(String string, String string2, String string3, String string4, String string5, Object[] objectArray, String string6, Object object, Iterator<Filter> iterator, Map<String, Object> map) {
        void properties;
        void assertionClosure;
        void method;
        void fullSubstitutedPath;
        void fullOriginalPath;
        void requestUri;
        void filters;
        void internalRequestUri;
        void unnamedPathParams;
        void userDefinedPath;
        MetaClass metaClass;
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        void var13_13 = userDefinedPath;
        this.userDefinedPath = (String)ScriptBytecodeAdapter.castToType(var13_13, String.class);
        void var14_14 = unnamedPathParams;
        this.unnamedPathParams = (Object[])ScriptBytecodeAdapter.castToType(var14_14, Object[].class);
        void var15_15 = internalRequestUri;
        this.internalRequestUri = var15_15;
        void var16_16 = filters;
        this.filters = (Iterator)ScriptBytecodeAdapter.castToType(var16_16, Iterator.class);
        void var17_17 = requestUri;
        this.requestUri = var17_17;
        void var18_18 = fullOriginalPath;
        this.originalPath = var18_18;
        void var19_19 = fullSubstitutedPath;
        this.substitutedPath = var19_19;
        void var20_20 = method;
        this.method = (String)ScriptBytecodeAdapter.castToType(var20_20, String.class);
        void var21_21 = assertionClosure;
        this.assertionClosure = var21_21;
        void var22_22 = properties;
        this.properties = (Map)ScriptBytecodeAdapter.castToType(var22_22, Map.class);
    }

    @Override
    public Response next(FilterableRequestSpecification request, FilterableResponseSpecification response) {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(this.filters))) {
            Object next = callSiteArray[1].call(this.filters);
            Object filterContext = callSiteArray[2].call((RequestSpecificationImpl)ScriptBytecodeAdapter.asType(request, RequestSpecificationImpl.class), this.assertionClosure, this.filters, this.properties);
            return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[3].call(next, request, response, filterContext), Response.class);
        }
        return (Response)ScriptBytecodeAdapter.castToType(null, Response.class);
    }

    public String getInternalRequestURI() {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.internalRequestUri, String.class);
    }

    @Override
    public Response send(RequestSender requestSender) {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[4].call(ReflectionMethodInvoker.class, requestSender, callSiteArray[5].call(callSiteArray[6].call(this.method)), this.internalRequestUri), Response.class);
    }

    @Override
    public void setValue(String name, Object value) {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        callSiteArray[7].call(this.properties, name, value);
    }

    @Override
    public boolean hasValue(String name) {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareNotEqual(callSiteArray[8].callCurrent((GroovyObject)this, name), null);
        }
        return ScriptBytecodeAdapter.compareNotEqual(this.getValue(name), null);
    }

    @Override
    public boolean hasValue(String name, Object value) {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return DefaultTypeTransformation.booleanUnbox(callSiteArray[9].callCurrent((GroovyObject)this, name)) && ScriptBytecodeAdapter.compareEqual(callSiteArray[10].callCurrent((GroovyObject)this, name), value);
        }
        return this.hasValue(name) && ScriptBytecodeAdapter.compareEqual(this.getValue(name), value);
    }

    public Object getValue(String name) {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        return callSiteArray[11].call(this.properties, name);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(FilterContextImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, FilterContextImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(FilterContextImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != FilterContextImpl.class) {
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
        CallSite[] callSiteArray = FilterContextImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        FilterContextImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729358 = l2 = 0L;
        __timeStamp = l = 1702030729358L;
    }

    public Object getAssertionClosure() {
        return this.assertionClosure;
    }

    public void setAssertionClosure(Object object) {
        this.assertionClosure = object;
    }

    public Map<String, Object> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, Object> map) {
        this.properties = map;
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
        stringArray[0] = "hasNext";
        stringArray[1] = "next";
        stringArray[2] = "newFilterContext";
        stringArray[3] = "filter";
        stringArray[4] = "invoke";
        stringArray[5] = "toLowerCase";
        stringArray[6] = "toString";
        stringArray[7] = "put";
        stringArray[8] = "getValue";
        stringArray[9] = "hasValue";
        stringArray[10] = "getValue";
        stringArray[11] = "get";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[12];
        FilterContextImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(FilterContextImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = FilterContextImpl.$createCallSiteArray();
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

