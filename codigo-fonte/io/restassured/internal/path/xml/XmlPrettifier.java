/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.xml;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.util.Node;
import groovy.xml.XmlNodePrinter;
import groovy.xml.XmlParser;
import groovy.xml.XmlUtil;
import groovy.xml.slurpersupport.GPathResult;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class XmlPrettifier
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030715484;
    private static /* synthetic */ SoftReference $callSiteArray;

    public XmlPrettifier() {
        MetaClass metaClass;
        CallSite[] callSiteArray = XmlPrettifier.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    /*
     * WARNING - void declaration
     */
    public static String prettify(XmlParser xmlParser, Object xml) {
        void var1_1;
        Reference<XmlParser> xmlParser2 = new Reference<XmlParser>(xmlParser);
        Reference<void> xml2 = new Reference<void>(var1_1);
        CallSite[] callSiteArray = XmlPrettifier.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[0].callStatic(XmlPrettifier.class, new GeneratedClosure(XmlPrettifier.class, XmlPrettifier.class, xmlParser2, xml2){
            private /* synthetic */ Reference xmlParser;
            private /* synthetic */ Reference xml;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _prettify_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.xmlParser = reference2 = xmlParser;
                this.xml = reference = xml;
            }

            public Object doCall(StringWriter stringWriter) {
                CallSite[] callSiteArray = _prettify_closure1.$getCallSiteArray();
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(StringUtils.class, this.xml.get()))) {
                    return "";
                }
                Node node = (Node)ScriptBytecodeAdapter.castToType(callSiteArray[1].call(this.xmlParser.get(), this.xml.get()), Node.class);
                Object printer = callSiteArray[2].callConstructor(XmlNodePrinter.class, callSiteArray[3].callConstructor(PrintWriter.class, stringWriter));
                callSiteArray[4].call(printer, callSiteArray[5].call(this.xmlParser.get()));
                callSiteArray[6].call(printer, !DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call(this.xmlParser.get())));
                return callSiteArray[8].call(printer, node);
            }

            public Object call(StringWriter stringWriter) {
                CallSite[] callSiteArray = _prettify_closure1.$getCallSiteArray();
                return callSiteArray[9].callCurrent((GroovyObject)this, stringWriter);
            }

            public XmlParser getXmlParser() {
                CallSite[] callSiteArray = _prettify_closure1.$getCallSiteArray();
                return (XmlParser)ScriptBytecodeAdapter.castToType(this.xmlParser.get(), XmlParser.class);
            }

            public Object getXml() {
                CallSite[] callSiteArray = _prettify_closure1.$getCallSiteArray();
                return this.xml.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _prettify_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _prettify_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _prettify_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "isBlank";
                stringArray[1] = "parseText";
                stringArray[2] = "<$constructor$>";
                stringArray[3] = "<$constructor$>";
                stringArray[4] = "setNamespaceAware";
                stringArray[5] = "isNamespaceAware";
                stringArray[6] = "setPreserveWhitespace";
                stringArray[7] = "isTrimWhitespace";
                stringArray[8] = "print";
                stringArray[9] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[10];
                _prettify_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_prettify_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _prettify_closure1.$createCallSiteArray();
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
        }), String.class);
    }

    public static String prettify(GPathResult gPathResult) {
        Reference<GPathResult> gPathResult2 = new Reference<GPathResult>(gPathResult);
        CallSite[] callSiteArray = XmlPrettifier.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[1].callStatic(XmlPrettifier.class, new GeneratedClosure(XmlPrettifier.class, XmlPrettifier.class, gPathResult2){
            private /* synthetic */ Reference gPathResult;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _prettify_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.gPathResult = reference = gPathResult;
            }

            public Object doCall(StringWriter stringWriter) {
                CallSite[] callSiteArray = _prettify_closure2.$getCallSiteArray();
                return callSiteArray[0].call(XmlUtil.class, this.gPathResult.get(), stringWriter);
            }

            public Object call(StringWriter stringWriter) {
                CallSite[] callSiteArray = _prettify_closure2.$getCallSiteArray();
                return callSiteArray[1].callCurrent((GroovyObject)this, stringWriter);
            }

            public GPathResult getgPathResult() {
                CallSite[] callSiteArray = _prettify_closure2.$getCallSiteArray();
                return (GPathResult)ScriptBytecodeAdapter.castToType(this.gPathResult.get(), GPathResult.class);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _prettify_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _prettify_closure2.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _prettify_closure2.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "serialize";
                stringArray[1] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _prettify_closure2.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_prettify_closure2.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _prettify_closure2.$createCallSiteArray();
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
        }), String.class);
    }

    private static Object doPrettify(Closure<String> closure) {
        CallSite[] callSiteArray = XmlPrettifier.$getCallSiteArray();
        Object stringWriter = callSiteArray[2].callConstructor(StringWriter.class);
        callSiteArray[3].call(closure, stringWriter);
        Object body = callSiteArray[4].call(stringWriter);
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[5].call(body, "\r\n"))) {
            Object object;
            body = object = callSiteArray[6].call(body, 0, callSiteArray[7].call(callSiteArray[8].call(body), 2));
        } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[9].call(body, "\n"))) {
            Object object;
            body = object = callSiteArray[10].call(body, 0, callSiteArray[11].call(callSiteArray[12].call(body), 1));
        }
        return body;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = XmlPrettifier.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(XmlPrettifier.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = XmlPrettifier.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, XmlPrettifier.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = XmlPrettifier.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(XmlPrettifier.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != XmlPrettifier.class) {
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
        CallSite[] callSiteArray = XmlPrettifier.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        XmlPrettifier.__$swapInit();
        __timeStamp__239_neverHappen1702030715484 = l2 = 0L;
        __timeStamp = l = 1702030715484L;
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
        stringArray[0] = "doPrettify";
        stringArray[1] = "doPrettify";
        stringArray[2] = "<$constructor$>";
        stringArray[3] = "call";
        stringArray[4] = "toString";
        stringArray[5] = "endsWith";
        stringArray[6] = "substring";
        stringArray[7] = "minus";
        stringArray[8] = "length";
        stringArray[9] = "endsWith";
        stringArray[10] = "substring";
        stringArray[11] = "minus";
        stringArray[12] = "length";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[13];
        XmlPrettifier.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(XmlPrettifier.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = XmlPrettifier.$createCallSiteArray();
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

