/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.matcher.xml;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.matcher.xml.LoadFromClasspathSupport;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.w3c.dom.ls.LSResourceResolver;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class XmlXsdMatcher
extends BaseMatcher<String>
implements GroovyObject {
    private Object xsd;
    private Object resourceResolver;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729392;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    private XmlXsdMatcher(Object object) {
        void xsd;
        MetaClass metaClass;
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        callSiteArray[0].callStatic(AssertParameter.class, xsd, "xsd");
        void var4_4 = xsd;
        this.xsd = var4_4;
    }

    public XmlXsdMatcher using(LSResourceResolver resourceResolver) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        callSiteArray[1].callStatic(AssertParameter.class, resourceResolver, LSResourceResolver.class);
        Object matcher = callSiteArray[2].callConstructor(XmlXsdMatcher.class, this.xsd);
        callSiteArray[3].call(matcher, resourceResolver);
        return (XmlXsdMatcher)ScriptBytecodeAdapter.castToType(matcher, XmlXsdMatcher.class);
    }

    public XmlXsdMatcher with(LSResourceResolver resourceResolver) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        return (XmlXsdMatcher)ScriptBytecodeAdapter.castToType(callSiteArray[4].callCurrent((GroovyObject)this, resourceResolver), XmlXsdMatcher.class);
    }

    public static XmlXsdMatcher matchesXsd(String xsd) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        callSiteArray[5].callStatic(AssertParameter.class, xsd, "xsd");
        return (XmlXsdMatcher)ScriptBytecodeAdapter.castToType(callSiteArray[6].callConstructor(XmlXsdMatcher.class, callSiteArray[7].callConstructor(StreamSource.class, callSiteArray[8].callConstructor(StringReader.class, callSiteArray[9].call(xsd)))), XmlXsdMatcher.class);
    }

    public static XmlXsdMatcher matchesXsd(InputStream xsd) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        callSiteArray[10].callStatic(AssertParameter.class, xsd, "xsd");
        return (XmlXsdMatcher)ScriptBytecodeAdapter.castToType(callSiteArray[11].callConstructor(XmlXsdMatcher.class, callSiteArray[12].callConstructor(StreamSource.class, xsd)), XmlXsdMatcher.class);
    }

    public static XmlXsdMatcher matchesXsd(Reader xsd) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        callSiteArray[13].callStatic(AssertParameter.class, xsd, "xsd");
        return (XmlXsdMatcher)ScriptBytecodeAdapter.castToType(callSiteArray[14].callConstructor(XmlXsdMatcher.class, callSiteArray[15].callConstructor(StreamSource.class, xsd)), XmlXsdMatcher.class);
    }

    public static XmlXsdMatcher matchesXsd(File xsd) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        callSiteArray[16].callStatic(AssertParameter.class, xsd, "xsd");
        return (XmlXsdMatcher)ScriptBytecodeAdapter.castToType(callSiteArray[17].callConstructor(XmlXsdMatcher.class, xsd), XmlXsdMatcher.class);
    }

    public static XmlXsdMatcher matchesXsd(URL url) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        callSiteArray[18].callStatic(AssertParameter.class, url, "url");
        return (XmlXsdMatcher)ScriptBytecodeAdapter.castToType(callSiteArray[19].callConstructor(XmlXsdMatcher.class, url), XmlXsdMatcher.class);
    }

    @Override
    public boolean matches(Object item) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        Object factory = callSiteArray[20].call(SchemaFactory.class, callSiteArray[21].callGetProperty(XMLConstants.class));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(this.resourceResolver, null)) {
                callSiteArray[22].call(factory, this.resourceResolver);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(this.resourceResolver, null)) {
            callSiteArray[23].call(factory, this.resourceResolver);
        }
        Object schema = callSiteArray[24].call(factory, this.xsd);
        Object validator = callSiteArray[25].call(schema);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareEqual(callSiteArray[26].call(validator, callSiteArray[27].callConstructor(StreamSource.class, callSiteArray[28].callConstructor(StringReader.class, item))), null);
        }
        return ScriptBytecodeAdapter.compareEqual(callSiteArray[29].call(validator, callSiteArray[30].callConstructor(StreamSource.class, callSiteArray[31].callConstructor(StringReader.class, item))), null);
    }

    @Override
    public void describeTo(Description description) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        callSiteArray[32].call((Object)description, "the supplied XSD");
    }

    public static XmlXsdMatcher matchesXsdInClasspath(String path) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        callSiteArray[33].callStatic(AssertParameter.class, path, "Path that points to the XSD in classpath");
        InputStream stream = (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[34].call(LoadFromClasspathSupport.class, path), InputStream.class);
        return (XmlXsdMatcher)ScriptBytecodeAdapter.castToType(callSiteArray[35].callStatic(XmlXsdMatcher.class, stream), XmlXsdMatcher.class);
    }

    public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(XmlXsdMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$2(String name, Object value) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, XmlXsdMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$2(String name) {
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(XmlXsdMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != XmlXsdMatcher.class) {
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
        CallSite[] callSiteArray = XmlXsdMatcher.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        XmlXsdMatcher.__$swapInit();
        __timeStamp__239_neverHappen1702030729392 = l2 = 0L;
        __timeStamp = l = 1702030729392L;
    }

    public Object getXsd() {
        return this.xsd;
    }

    public void setXsd(Object object) {
        this.xsd = object;
    }

    public Object getResourceResolver() {
        return this.resourceResolver;
    }

    public void setResourceResolver(Object object) {
        this.resourceResolver = object;
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

    public /* synthetic */ void super$2$describeMismatch(Object object, Description description) {
        super.describeMismatch(object, description);
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

    public /* synthetic */ void super$2$_dont_implement_Matcher___instead_extend_BaseMatcher_() {
        super._dont_implement_Matcher___instead_extend_BaseMatcher_();
    }

    public /* synthetic */ void super$1$finalize() {
        super.finalize();
    }

    public /* synthetic */ Class super$1$getClass() {
        return super.getClass();
    }

    public /* synthetic */ String super$2$toString() {
        return super.toString();
    }

    public /* synthetic */ void super$1$notifyAll() {
        super.notifyAll();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
        stringArray[0] = "notNull";
        stringArray[1] = "notNull";
        stringArray[2] = "<$constructor$>";
        stringArray[3] = "setResourceResolver";
        stringArray[4] = "using";
        stringArray[5] = "notNull";
        stringArray[6] = "<$constructor$>";
        stringArray[7] = "<$constructor$>";
        stringArray[8] = "<$constructor$>";
        stringArray[9] = "trim";
        stringArray[10] = "notNull";
        stringArray[11] = "<$constructor$>";
        stringArray[12] = "<$constructor$>";
        stringArray[13] = "notNull";
        stringArray[14] = "<$constructor$>";
        stringArray[15] = "<$constructor$>";
        stringArray[16] = "notNull";
        stringArray[17] = "<$constructor$>";
        stringArray[18] = "notNull";
        stringArray[19] = "<$constructor$>";
        stringArray[20] = "newInstance";
        stringArray[21] = "W3C_XML_SCHEMA_NS_URI";
        stringArray[22] = "setResourceResolver";
        stringArray[23] = "setResourceResolver";
        stringArray[24] = "newSchema";
        stringArray[25] = "newValidator";
        stringArray[26] = "validate";
        stringArray[27] = "<$constructor$>";
        stringArray[28] = "<$constructor$>";
        stringArray[29] = "validate";
        stringArray[30] = "<$constructor$>";
        stringArray[31] = "<$constructor$>";
        stringArray[32] = "appendText";
        stringArray[33] = "notNull";
        stringArray[34] = "loadFromClasspath";
        stringArray[35] = "matchesXsd";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[36];
        XmlXsdMatcher.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(XmlXsdMatcher.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = XmlXsdMatcher.$createCallSiteArray();
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

