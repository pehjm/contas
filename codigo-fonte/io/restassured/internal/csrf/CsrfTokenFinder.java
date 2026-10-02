/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.csrf;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.config.CsrfConfig;
import io.restassured.internal.csrf.CsrfData;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import java.lang.ref.SoftReference;
import java.util.List;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class CsrfTokenFinder
implements GroovyObject {
    private static final String FIND_INPUT_FIELD_WITH_NAME = "html.depthFirst().grep { it.name() == 'input' && it.@name == '%s' }.collect { it.@value }.get(0)";
    private static final String FIND_META_FIELD_WITH_NAME = "html.depthFirst().grep { it.name() == 'meta' && it.@name == '%s' }.collect { it.@content }.get(0)";
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729390;
    private static /* synthetic */ SoftReference $callSiteArray;

    public CsrfTokenFinder() {
        MetaClass metaClass;
        CallSite[] callSiteArray = CsrfTokenFinder.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static CsrfData findInHtml(CsrfConfig csrfConfig, Response pageThatContainsCsrfToken) {
        List list;
        List list2;
        Reference<CsrfConfig> csrfConfig2 = new Reference<CsrfConfig>(csrfConfig);
        CallSite[] callSiteArray = CsrfTokenFinder.$getCallSiteArray();
        Reference<Object> htmlPath = new Reference<Object>(callSiteArray[0].call(pageThatContainsCsrfToken));
        List csrfFinders = null;
        csrfFinders = DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call((Object)csrfConfig2.get(), callSiteArray[2].callGetProperty(CsrfConfig.CsrfPrioritization.class))) ? (list2 = ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.getMethodPointer(CsrfTokenFinder.class, "findCsrfHeaderToken"), ScriptBytecodeAdapter.getMethodPointer(CsrfTokenFinder.class, "findCsrfFormToken")})) : (list = ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.getMethodPointer(CsrfTokenFinder.class, "findCsrfFormToken"), ScriptBytecodeAdapter.getMethodPointer(CsrfTokenFinder.class, "findCsrfHeaderToken")}));
        return (CsrfData)ScriptBytecodeAdapter.castToType(callSiteArray[3].call(csrfFinders, ScriptBytecodeAdapter.createPojoWrapper(null, CsrfData.class), new GeneratedClosure(CsrfTokenFinder.class, CsrfTokenFinder.class, htmlPath, csrfConfig2){
            private /* synthetic */ Reference htmlPath;
            private /* synthetic */ Reference csrfConfig;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _findInHtml_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.htmlPath = reference2 = htmlPath;
                this.csrfConfig = reference = csrfConfig;
            }

            public Object doCall(Object csrfToken, Object fn) {
                CallSite[] callSiteArray = _findInHtml_closure1.$getCallSiteArray();
                Object object = csrfToken;
                return DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[0].call(fn, this.csrfConfig.get(), this.htmlPath.get());
            }

            public Object call(Object csrfToken, Object fn) {
                CallSite[] callSiteArray = _findInHtml_closure1.$getCallSiteArray();
                return callSiteArray[1].callCurrent(this, csrfToken, fn);
            }

            public Object getHtmlPath() {
                CallSite[] callSiteArray = _findInHtml_closure1.$getCallSiteArray();
                return this.htmlPath.get();
            }

            public CsrfConfig getCsrfConfig() {
                CallSite[] callSiteArray = _findInHtml_closure1.$getCallSiteArray();
                return (CsrfConfig)ScriptBytecodeAdapter.castToType(this.csrfConfig.get(), CsrfConfig.class);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _findInHtml_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _findInHtml_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _findInHtml_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "call";
                stringArray[1] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _findInHtml_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_findInHtml_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _findInHtml_closure1.$createCallSiteArray();
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
        }), CsrfData.class);
    }

    private static Object findCsrfFormToken(CsrfConfig csrfConfig, XmlPath htmlPath) {
        Reference<XmlPath> htmlPath2 = new Reference<XmlPath>(htmlPath);
        CallSite[] callSiteArray = CsrfTokenFinder.$getCallSiteArray();
        Reference<Object> csrfFieldName = new Reference<Object>(callSiteArray[4].call(csrfConfig));
        String csrfToken = (String)ScriptBytecodeAdapter.castToType(callSiteArray[5].callStatic(CsrfTokenFinder.class, new GeneratedClosure(CsrfTokenFinder.class, CsrfTokenFinder.class, htmlPath2, csrfFieldName){
            private /* synthetic */ Reference htmlPath;
            private /* synthetic */ Reference csrfFieldName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _findCsrfFormToken_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.htmlPath = reference2 = htmlPath;
                this.csrfFieldName = reference = csrfFieldName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _findCsrfFormToken_closure2.$getCallSiteArray();
                return callSiteArray[0].call(this.htmlPath.get(), callSiteArray[1].callStatic(String.class, callSiteArray[2].callGetProperty(CsrfTokenFinder.class), this.csrfFieldName.get()));
            }

            public XmlPath getHtmlPath() {
                CallSite[] callSiteArray = _findCsrfFormToken_closure2.$getCallSiteArray();
                return (XmlPath)ScriptBytecodeAdapter.castToType(this.htmlPath.get(), XmlPath.class);
            }

            public Object getCsrfFieldName() {
                CallSite[] callSiteArray = _findCsrfFormToken_closure2.$getCallSiteArray();
                return this.csrfFieldName.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _findCsrfFormToken_closure2.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _findCsrfFormToken_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _findCsrfFormToken_closure2.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _findCsrfFormToken_closure2.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "getString";
                stringArray[1] = "format";
                stringArray[2] = "FIND_INPUT_FIELD_WITH_NAME";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _findCsrfFormToken_closure2.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_findCsrfFormToken_closure2.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _findCsrfFormToken_closure2.$createCallSiteArray();
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
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(csrfToken, null) : ScriptBytecodeAdapter.compareEqual(csrfToken, null)) {
            return null;
        }
        return callSiteArray[6].callConstructor(CsrfData.class, csrfFieldName.get(), csrfToken, callSiteArray[7].callGetProperty(CsrfConfig.CsrfPrioritization.class));
    }

    private static Object findCsrfHeaderToken(CsrfConfig csrfConfig, XmlPath htmlPath) {
        Reference<XmlPath> htmlPath2 = new Reference<XmlPath>(htmlPath);
        CallSite[] callSiteArray = CsrfTokenFinder.$getCallSiteArray();
        Reference<Object> metaTagName = new Reference<Object>(callSiteArray[8].call(csrfConfig));
        String csrfToken = (String)ScriptBytecodeAdapter.castToType(callSiteArray[9].callStatic(CsrfTokenFinder.class, new GeneratedClosure(CsrfTokenFinder.class, CsrfTokenFinder.class, htmlPath2, metaTagName){
            private /* synthetic */ Reference htmlPath;
            private /* synthetic */ Reference metaTagName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _findCsrfHeaderToken_closure3.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.htmlPath = reference2 = htmlPath;
                this.metaTagName = reference = metaTagName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _findCsrfHeaderToken_closure3.$getCallSiteArray();
                return callSiteArray[0].call(this.htmlPath.get(), callSiteArray[1].callStatic(String.class, callSiteArray[2].callGetProperty(CsrfTokenFinder.class), this.metaTagName.get()));
            }

            public XmlPath getHtmlPath() {
                CallSite[] callSiteArray = _findCsrfHeaderToken_closure3.$getCallSiteArray();
                return (XmlPath)ScriptBytecodeAdapter.castToType(this.htmlPath.get(), XmlPath.class);
            }

            public Object getMetaTagName() {
                CallSite[] callSiteArray = _findCsrfHeaderToken_closure3.$getCallSiteArray();
                return this.metaTagName.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _findCsrfHeaderToken_closure3.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _findCsrfHeaderToken_closure3.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _findCsrfHeaderToken_closure3.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _findCsrfHeaderToken_closure3.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "getString";
                stringArray[1] = "format";
                stringArray[2] = "FIND_META_FIELD_WITH_NAME";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _findCsrfHeaderToken_closure3.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_findCsrfHeaderToken_closure3.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _findCsrfHeaderToken_closure3.$createCallSiteArray();
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
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(csrfToken, null) : ScriptBytecodeAdapter.compareEqual(csrfToken, null)) {
            return null;
        }
        return callSiteArray[10].callConstructor(CsrfData.class, callSiteArray[11].call(csrfConfig), csrfToken, callSiteArray[12].callGetProperty(CsrfConfig.CsrfPrioritization.class));
    }

    private static Object nullIfException(Closure closure) {
        CallSite[] callSiteArray = CsrfTokenFinder.$getCallSiteArray();
        Object object = callSiteArray[13].call(closure);
        try {
            return object;
        }
        catch (Exception ignored) {
            Object var4_4 = null;
            return var4_4;
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = CsrfTokenFinder.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(CsrfTokenFinder.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = CsrfTokenFinder.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, CsrfTokenFinder.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = CsrfTokenFinder.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(CsrfTokenFinder.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != CsrfTokenFinder.class) {
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
        CallSite[] callSiteArray = CsrfTokenFinder.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        CsrfTokenFinder.__$swapInit();
        __timeStamp__239_neverHappen1702030729390 = l2 = 0L;
        __timeStamp = l = 1702030729390L;
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
        stringArray[0] = "htmlPath";
        stringArray[1] = "isCsrfPrioritization";
        stringArray[2] = "HEADER";
        stringArray[3] = "inject";
        stringArray[4] = "getCsrfInputFieldName";
        stringArray[5] = "nullIfException";
        stringArray[6] = "<$constructor$>";
        stringArray[7] = "FORM";
        stringArray[8] = "getCsrfMetaTagName";
        stringArray[9] = "nullIfException";
        stringArray[10] = "<$constructor$>";
        stringArray[11] = "getCsrfHeaderName";
        stringArray[12] = "HEADER";
        stringArray[13] = "call";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[14];
        CsrfTokenFinder.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(CsrfTokenFinder.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = CsrfTokenFinder.$createCallSiteArray();
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

