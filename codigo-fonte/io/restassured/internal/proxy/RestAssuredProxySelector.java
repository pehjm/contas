/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.proxy;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.specification.ProxySpecification;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RestAssuredProxySelector
extends ProxySelector
implements GroovyObject {
    private ProxySelector delegatingProxySelector;
    private ProxySpecification proxySpecification;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729394;
    private static /* synthetic */ SoftReference $callSiteArray;

    public RestAssuredProxySelector() {
        MetaClass metaClass;
        CallSite[] callSiteArray = RestAssuredProxySelector.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Override
    public List<Proxy> select(URI uri) {
        CallSite[] callSiteArray = RestAssuredProxySelector.$getCallSiteArray();
        callSiteArray[0].callStatic(AssertParameter.class, uri, URI.class);
        Object proxies = null;
        if (DefaultTypeTransformation.booleanUnbox(this.proxySpecification)) {
            List list = ScriptBytecodeAdapter.createList(new Object[]{callSiteArray[1].callConstructor(Proxy.class, callSiteArray[2].callGetProperty(Proxy.Type.class), callSiteArray[3].callConstructor(InetSocketAddress.class, callSiteArray[4].callGetProperty(this.proxySpecification), callSiteArray[5].callGetProperty(this.proxySpecification)))});
            proxies = list;
        } else {
            Object object;
            proxies = object = callSiteArray[6].call((Object)this.delegatingProxySelector, uri);
        }
        return (List)ScriptBytecodeAdapter.castToType(proxies, List.class);
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        CallSite[] callSiteArray = RestAssuredProxySelector.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(this.proxySpecification)) {
            throw (Throwable)ioe;
        }
        callSiteArray[7].call(this.delegatingProxySelector, uri, sa, ioe);
    }

    public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
        CallSite[] callSiteArray = RestAssuredProxySelector.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RestAssuredProxySelector.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$2(String name, Object value) {
        CallSite[] callSiteArray = RestAssuredProxySelector.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, RestAssuredProxySelector.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$2(String name) {
        CallSite[] callSiteArray = RestAssuredProxySelector.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(RestAssuredProxySelector.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RestAssuredProxySelector.class) {
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
        CallSite[] callSiteArray = RestAssuredProxySelector.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        RestAssuredProxySelector.__$swapInit();
        __timeStamp__239_neverHappen1702030729394 = l2 = 0L;
        __timeStamp = l = 1702030729394L;
    }

    public ProxySelector getDelegatingProxySelector() {
        return this.delegatingProxySelector;
    }

    public void setDelegatingProxySelector(ProxySelector proxySelector) {
        this.delegatingProxySelector = proxySelector;
    }

    public ProxySpecification getProxySpecification() {
        return this.proxySpecification;
    }

    public void setProxySpecification(ProxySpecification proxySpecification) {
        this.proxySpecification = proxySpecification;
    }

    public /* synthetic */ void super$1$notify() {
        super.notify();
    }

    public /* synthetic */ int super$1$hashCode() {
        return super.hashCode();
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

    public /* synthetic */ boolean super$1$equals(Object object) {
        return super.equals(object);
    }

    public /* synthetic */ void super$1$finalize() {
        super.finalize();
    }

    public /* synthetic */ Class super$1$getClass() {
        return super.getClass();
    }

    public /* synthetic */ String super$1$toString() {
        return super.toString();
    }

    public /* synthetic */ void super$1$notifyAll() {
        super.notifyAll();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
        stringArray[0] = "notNull";
        stringArray[1] = "<$constructor$>";
        stringArray[2] = "HTTP";
        stringArray[3] = "<$constructor$>";
        stringArray[4] = "host";
        stringArray[5] = "port";
        stringArray[6] = "select";
        stringArray[7] = "connectFailed";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[8];
        RestAssuredProxySelector.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(RestAssuredProxySelector.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = RestAssuredProxySelector.$createCallSiteArray();
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

