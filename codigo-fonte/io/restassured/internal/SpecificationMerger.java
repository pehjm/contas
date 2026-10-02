/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.authentication.ExplicitNoAuthScheme;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SessionConfig;
import io.restassured.http.Cookies;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.spi.AuthFilter;
import java.lang.ref.SoftReference;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class SpecificationMerger
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729352;
    private static /* synthetic */ SoftReference $callSiteArray;

    public SpecificationMerger() {
        MetaClass metaClass;
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static void merge(ResponseSpecificationImpl thisOne, ResponseSpecificationImpl with) {
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        callSiteArray[0].callStatic(AssertParameter.class, thisOne, "Specification to merge");
        callSiteArray[1].callStatic(AssertParameter.class, with, "Specification to merge with");
        Object object = callSiteArray[2].callGetProperty(with);
        ScriptBytecodeAdapter.setProperty(object, null, thisOne, "contentType");
        Object object2 = callSiteArray[3].callGetProperty(callSiteArray[4].callGetProperty(with));
        ScriptBytecodeAdapter.setProperty(object2, null, callSiteArray[5].callGetProperty(thisOne), "defaultParser");
        callSiteArray[6].call(callSiteArray[7].callGetProperty(callSiteArray[8].callGetProperty(thisOne)), callSiteArray[9].callGetProperty(callSiteArray[10].callGetProperty(with)));
        Object object3 = callSiteArray[11].callGetProperty(with);
        ScriptBytecodeAdapter.setProperty(object3, null, thisOne, "responseLogDetail");
        callSiteArray[12].call(callSiteArray[13].callGetProperty(thisOne), callSiteArray[14].callGetProperty(with));
        Object object4 = callSiteArray[15].callGetProperty(with);
        ScriptBytecodeAdapter.setProperty(object4, null, thisOne, "bodyRootPath");
        callSiteArray[16].call(callSiteArray[17].callGetProperty(thisOne), callSiteArray[18].callGetProperty(with));
        Object object5 = callSiteArray[19].callGetProperty(with);
        ScriptBytecodeAdapter.setProperty(object5, null, thisOne, "expectedStatusCode");
        Object object6 = callSiteArray[20].callGetProperty(with);
        ScriptBytecodeAdapter.setProperty(object6, null, thisOne, "expectedStatusLine");
        Object object7 = callSiteArray[21].callGetProperty(with);
        ScriptBytecodeAdapter.setProperty(object7, null, thisOne, "expectedResponseTime");
        callSiteArray[22].call(callSiteArray[23].callGetProperty(thisOne), callSiteArray[24].callGetProperty(with));
    }

    public static void merge(RequestSpecificationImpl thisOne, RequestSpecificationImpl with) {
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        callSiteArray[25].callStatic(AssertParameter.class, thisOne, "Specification to merge");
        callSiteArray[26].callStatic(AssertParameter.class, with, "Specification to merge with");
        Object object = ScriptBytecodeAdapter.getGroovyObjectField(SpecificationMerger.class, with, "port");
        ScriptBytecodeAdapter.setGroovyObjectField(object, SpecificationMerger.class, thisOne, "port");
        Object object2 = callSiteArray[27].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object2, SpecificationMerger.class, thisOne, "baseUri");
        Object object3 = callSiteArray[28].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object3, SpecificationMerger.class, thisOne, "basePath");
        callSiteArray[29].call(callSiteArray[30].callGroovyObjectGetProperty(thisOne), callSiteArray[31].callGroovyObjectGetProperty(with));
        callSiteArray[32].call(callSiteArray[33].callGroovyObjectGetProperty(thisOne), callSiteArray[34].callGroovyObjectGetProperty(with));
        callSiteArray[35].call(callSiteArray[36].callGroovyObjectGetProperty(thisOne), callSiteArray[37].callGroovyObjectGetProperty(with));
        callSiteArray[38].call(callSiteArray[39].callGroovyObjectGetProperty(thisOne), callSiteArray[40].callGroovyObjectGetProperty(with));
        callSiteArray[41].call(callSiteArray[42].callGroovyObjectGetProperty(thisOne), callSiteArray[43].callGroovyObjectGetProperty(with));
        Object object4 = callSiteArray[44].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object4, SpecificationMerger.class, thisOne, "authenticationScheme");
        callSiteArray[45].callStatic(SpecificationMerger.class, thisOne, with);
        callSiteArray[46].call((Object)thisOne, callSiteArray[47].callGroovyObjectGetProperty(with));
        Object object5 = callSiteArray[48].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object5, SpecificationMerger.class, thisOne, "requestBody");
        callSiteArray[49].callStatic(SpecificationMerger.class, thisOne, with);
        Object object6 = callSiteArray[50].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object6, SpecificationMerger.class, thisOne, "urlEncodingEnabled");
        Object object7 = callSiteArray[51].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object7, SpecificationMerger.class, thisOne, "allowContentType");
        Object object8 = callSiteArray[52].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object8, SpecificationMerger.class, thisOne, "addCsrfFilter");
        Object object9 = callSiteArray[53].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object9, SpecificationMerger.class, thisOne, "proxySpecification");
        Object object10 = callSiteArray[54].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object10, SpecificationMerger.class, thisOne, "method");
        Object object11 = callSiteArray[55].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object11, SpecificationMerger.class, thisOne, "unnamedPathParamsTuples");
        Object object12 = callSiteArray[56].callGroovyObjectGetProperty(with);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object12, SpecificationMerger.class, thisOne, "path");
        callSiteArray[57].callStatic(SpecificationMerger.class, thisOne, with);
        callSiteArray[58].call((Object)thisOne, callSiteArray[59].callGroovyObjectGetProperty(with));
    }

    private static Object mergeConfig(RequestSpecificationImpl thisOne, RequestSpecificationImpl other) {
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        RestAssuredConfig thisConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(callSiteArray[60].call(thisOne), RestAssuredConfig.class);
        Reference<RestAssuredConfig> otherConfig = new Reference<RestAssuredConfig>((RestAssuredConfig)ScriptBytecodeAdapter.castToType(callSiteArray[61].call(other), RestAssuredConfig.class));
        Object thisIsUserConfigured = callSiteArray[62].call(thisConfig);
        Object otherIsUserConfigured = callSiteArray[63].call(otherConfig.get());
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(thisIsUserConfigured) && DefaultTypeTransformation.booleanUnbox(otherIsUserConfigured)) {
                Reference<Map> configsToUse = new Reference<Map>(ScriptBytecodeAdapter.createMap(new Object[0]));
                callSiteArray[64].call(callSiteArray[65].callGetProperty(thisConfig), new GeneratedClosure(SpecificationMerger.class, SpecificationMerger.class, configsToUse, otherConfig){
                    private /* synthetic */ Reference configsToUse;
                    private /* synthetic */ Reference otherConfig;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        Reference reference2;
                        CallSite[] callSiteArray = _mergeConfig_closure1.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.configsToUse = reference2 = configsToUse;
                        this.otherConfig = reference = otherConfig;
                    }

                    public Object doCall(Object configType, Object thisTempConfig) {
                        CallSite[] callSiteArray = _mergeConfig_closure1.$getCallSiteArray();
                        Object otherTempConfig = callSiteArray[0].call(callSiteArray[1].callGetProperty(this.otherConfig.get()), configType);
                        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call(otherTempConfig))) {
                            return callSiteArray[3].call(this.configsToUse.get(), configType, otherTempConfig);
                        }
                        return callSiteArray[4].call(this.configsToUse.get(), configType, thisTempConfig);
                    }

                    public Object call(Object configType, Object thisTempConfig) {
                        CallSite[] callSiteArray = _mergeConfig_closure1.$getCallSiteArray();
                        return callSiteArray[5].callCurrent(this, configType, thisTempConfig);
                    }

                    public Object getConfigsToUse() {
                        CallSite[] callSiteArray = _mergeConfig_closure1.$getCallSiteArray();
                        return this.configsToUse.get();
                    }

                    public RestAssuredConfig getOtherConfig() {
                        CallSite[] callSiteArray = _mergeConfig_closure1.$getCallSiteArray();
                        return (RestAssuredConfig)ScriptBytecodeAdapter.castToType(this.otherConfig.get(), RestAssuredConfig.class);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _mergeConfig_closure1.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _mergeConfig_closure1.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _mergeConfig_closure1.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "get";
                        stringArray[1] = "configs";
                        stringArray[2] = "isUserConfigured";
                        stringArray[3] = "put";
                        stringArray[4] = "put";
                        stringArray[5] = "doCall";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[6];
                        _mergeConfig_closure1.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_mergeConfig_closure1.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _mergeConfig_closure1.$createCallSiteArray();
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
                });
                Object newConfig = callSiteArray[66].callConstructor(RestAssuredConfig.class);
                callSiteArray[67].call(callSiteArray[68].callGetProperty(newConfig), configsToUse.get());
                Object object = newConfig;
                ScriptBytecodeAdapter.setGroovyObjectProperty(object, SpecificationMerger.class, thisOne, "restAssuredConfig");
                return object;
            }
            if (!DefaultTypeTransformation.booleanUnbox(thisIsUserConfigured) && DefaultTypeTransformation.booleanUnbox(otherIsUserConfigured)) {
                RestAssuredConfig restAssuredConfig = otherConfig.get();
                ScriptBytecodeAdapter.setGroovyObjectProperty(restAssuredConfig, SpecificationMerger.class, thisOne, "restAssuredConfig");
                return restAssuredConfig;
            }
            return null;
        }
        if (DefaultTypeTransformation.booleanUnbox(thisIsUserConfigured) && DefaultTypeTransformation.booleanUnbox(otherIsUserConfigured)) {
            Reference<Map> configsToUse = new Reference<Map>(ScriptBytecodeAdapter.createMap(new Object[0]));
            callSiteArray[69].call(callSiteArray[70].callGetProperty(thisConfig), new /* invalid duplicate definition of identical inner class */);
            Object newConfig = callSiteArray[71].callConstructor(RestAssuredConfig.class);
            callSiteArray[72].call(callSiteArray[73].callGetProperty(newConfig), configsToUse.get());
            Object object = newConfig;
            ScriptBytecodeAdapter.setGroovyObjectProperty(object, SpecificationMerger.class, thisOne, "restAssuredConfig");
            return object;
        }
        if (!DefaultTypeTransformation.booleanUnbox(thisIsUserConfigured) && DefaultTypeTransformation.booleanUnbox(otherIsUserConfigured)) {
            RestAssuredConfig restAssuredConfig = otherConfig.get();
            ScriptBytecodeAdapter.setGroovyObjectProperty(restAssuredConfig, SpecificationMerger.class, thisOne, "restAssuredConfig");
            return restAssuredConfig;
        }
        return null;
    }

    private static Object mergeSessionId(RequestSpecificationImpl thisOne, RequestSpecificationImpl with) {
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        Object thisOneConfig = callSiteArray[74].callGroovyObjectGetProperty(thisOne);
        Object thisOneCookies = callSiteArray[75].callGroovyObjectGetProperty(thisOne);
        Object otherConfig = callSiteArray[76].callGroovyObjectGetProperty(with);
        Object otherCookies = callSiteArray[77].callGroovyObjectGetProperty(with);
        Reference<Object> oldSessionIdName = new Reference<Object>(callSiteArray[78].callGetProperty(SessionConfig.class));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(thisOneConfig, null)) {
                Object object = callSiteArray[79].call(callSiteArray[80].callGetProperty(thisOneConfig));
                oldSessionIdName.set(object);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(thisOneConfig, null)) {
            Object object = callSiteArray[81].call(callSiteArray[82].callGetProperty(thisOneConfig));
            oldSessionIdName.set(object);
        }
        Object shouldRemoveSessionFromThis = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(otherConfig, null)) {
                Object object;
                shouldRemoveSessionFromThis = object = callSiteArray[83].call(otherCookies, oldSessionIdName.get());
            } else {
                Object object;
                Object otherSessionIdName = callSiteArray[84].call(callSiteArray[85].callGetProperty(otherConfig));
                shouldRemoveSessionFromThis = object = callSiteArray[86].call(otherCookies, otherSessionIdName);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(otherConfig, null)) {
            Object object;
            shouldRemoveSessionFromThis = object = callSiteArray[87].call(otherCookies, oldSessionIdName.get());
        } else {
            Object object;
            Object otherSessionIdName = callSiteArray[88].call(callSiteArray[89].callGetProperty(otherConfig));
            shouldRemoveSessionFromThis = object = callSiteArray[90].call(otherCookies, otherSessionIdName);
        }
        if (DefaultTypeTransformation.booleanUnbox(shouldRemoveSessionFromThis)) {
            Object cookieList = callSiteArray[91].call(thisOneCookies, new GeneratedClosure(SpecificationMerger.class, SpecificationMerger.class, oldSessionIdName){
                private /* synthetic */ Reference oldSessionIdName;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    CallSite[] callSiteArray = _mergeSessionId_closure2.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.oldSessionIdName = reference = oldSessionIdName;
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _mergeSessionId_closure2.$getCallSiteArray();
                    return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(callSiteArray[1].call(it), this.oldSessionIdName.get()));
                }

                public Object getOldSessionIdName() {
                    CallSite[] callSiteArray = _mergeSessionId_closure2.$getCallSiteArray();
                    return this.oldSessionIdName.get();
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _mergeSessionId_closure2.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _mergeSessionId_closure2.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _mergeSessionId_closure2.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _mergeSessionId_closure2.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "equalsIgnoreCase";
                    stringArray[1] = "getName";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[2];
                    _mergeSessionId_closure2.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_mergeSessionId_closure2.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _mergeSessionId_closure2.$createCallSiteArray();
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
            });
            Object object = callSiteArray[92].callConstructor(Cookies.class, cookieList);
            ScriptBytecodeAdapter.setGroovyObjectProperty(object, SpecificationMerger.class, thisOne, "cookies");
            return object;
        }
        return null;
    }

    private static Object mergeFilters(RequestSpecificationImpl thisOne, RequestSpecificationImpl with) {
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        Reference<Object> thisFilters = new Reference<Object>(callSiteArray[93].callGroovyObjectGetProperty(thisOne));
        Object withFilters = callSiteArray[94].callGroovyObjectGetProperty(with);
        GeneratedClosure instanceOfAuthFilter = new GeneratedClosure(SpecificationMerger.class, SpecificationMerger.class){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _mergeFilters_closure3.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _mergeFilters_closure3.$getCallSiteArray();
                return it instanceof AuthFilter;
            }

            public Object doCall() {
                CallSite[] callSiteArray = _mergeFilters_closure3.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _mergeFilters_closure3.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _mergeFilters_closure3.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _mergeFilters_closure3.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[]{};
                return new CallSiteArray(_mergeFilters_closure3.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _mergeFilters_closure3.$createCallSiteArray();
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
        };
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[95].call(thisFilters.get(), instanceOfAuthFilter)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[96].call(withFilters, instanceOfAuthFilter)) || callSiteArray[97].callGroovyObjectGetProperty(with) instanceof ExplicitNoAuthScheme) {
                callSiteArray[98].call(thisFilters.get(), instanceOfAuthFilter);
            }
        } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[99].call(thisFilters.get(), instanceOfAuthFilter)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[100].call(withFilters, instanceOfAuthFilter)) || callSiteArray[101].callGroovyObjectGetProperty(with) instanceof ExplicitNoAuthScheme) {
            callSiteArray[102].call(thisFilters.get(), instanceOfAuthFilter);
        }
        Object toAdd = callSiteArray[103].call(withFilters, new GeneratedClosure(SpecificationMerger.class, SpecificationMerger.class, thisFilters){
            private /* synthetic */ Reference thisFilters;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _mergeFilters_closure4.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.thisFilters = reference = thisFilters;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _mergeFilters_closure4.$getCallSiteArray();
                return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(this.thisFilters.get(), it));
            }

            public Object getThisFilters() {
                CallSite[] callSiteArray = _mergeFilters_closure4.$getCallSiteArray();
                return this.thisFilters.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _mergeFilters_closure4.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _mergeFilters_closure4.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _mergeFilters_closure4.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _mergeFilters_closure4.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "contains";
                return new CallSiteArray(_mergeFilters_closure4.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _mergeFilters_closure4.$createCallSiteArray();
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
        });
        return callSiteArray[104].call(thisFilters.get(), toAdd);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(SpecificationMerger.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, SpecificationMerger.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(SpecificationMerger.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != SpecificationMerger.class) {
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
        CallSite[] callSiteArray = SpecificationMerger.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        SpecificationMerger.__$swapInit();
        __timeStamp__239_neverHappen1702030729352 = l2 = 0L;
        __timeStamp = l = 1702030729352L;
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
        stringArray[0] = "notNull";
        stringArray[1] = "notNull";
        stringArray[2] = "contentType";
        stringArray[3] = "defaultParser";
        stringArray[4] = "rpr";
        stringArray[5] = "rpr";
        stringArray[6] = "putAll";
        stringArray[7] = "additional";
        stringArray[8] = "rpr";
        stringArray[9] = "additional";
        stringArray[10] = "rpr";
        stringArray[11] = "responseLogDetail";
        stringArray[12] = "add";
        stringArray[13] = "bodyMatchers";
        stringArray[14] = "bodyMatchers";
        stringArray[15] = "bodyRootPath";
        stringArray[16] = "addAll";
        stringArray[17] = "cookieAssertions";
        stringArray[18] = "cookieAssertions";
        stringArray[19] = "expectedStatusCode";
        stringArray[20] = "expectedStatusLine";
        stringArray[21] = "expectedResponseTime";
        stringArray[22] = "addAll";
        stringArray[23] = "headerAssertions";
        stringArray[24] = "headerAssertions";
        stringArray[25] = "notNull";
        stringArray[26] = "notNull";
        stringArray[27] = "baseUri";
        stringArray[28] = "basePath";
        stringArray[29] = "putAll";
        stringArray[30] = "requestParameters";
        stringArray[31] = "requestParameters";
        stringArray[32] = "putAll";
        stringArray[33] = "queryParameters";
        stringArray[34] = "queryParams";
        stringArray[35] = "putAll";
        stringArray[36] = "formParameters";
        stringArray[37] = "formParams";
        stringArray[38] = "putAll";
        stringArray[39] = "namedPathParameters";
        stringArray[40] = "pathParams";
        stringArray[41] = "addAll";
        stringArray[42] = "multiParts";
        stringArray[43] = "multiParts";
        stringArray[44] = "authenticationScheme";
        stringArray[45] = "mergeSessionId";
        stringArray[46] = "cookies";
        stringArray[47] = "cookies";
        stringArray[48] = "requestBody";
        stringArray[49] = "mergeFilters";
        stringArray[50] = "urlEncodingEnabled";
        stringArray[51] = "allowContentType";
        stringArray[52] = "addCsrfFilter";
        stringArray[53] = "proxySpecification";
        stringArray[54] = "method";
        stringArray[55] = "unnamedPathParamValues";
        stringArray[56] = "path";
        stringArray[57] = "mergeConfig";
        stringArray[58] = "headers";
        stringArray[59] = "requestHeaders";
        stringArray[60] = "restAssuredConfig";
        stringArray[61] = "restAssuredConfig";
        stringArray[62] = "isUserConfigured";
        stringArray[63] = "isUserConfigured";
        stringArray[64] = "each";
        stringArray[65] = "configs";
        stringArray[66] = "<$constructor$>";
        stringArray[67] = "putAll";
        stringArray[68] = "configs";
        stringArray[69] = "each";
        stringArray[70] = "configs";
        stringArray[71] = "<$constructor$>";
        stringArray[72] = "putAll";
        stringArray[73] = "configs";
        stringArray[74] = "config";
        stringArray[75] = "cookies";
        stringArray[76] = "config";
        stringArray[77] = "cookies";
        stringArray[78] = "DEFAULT_SESSION_ID_NAME";
        stringArray[79] = "sessionIdName";
        stringArray[80] = "sessionConfig";
        stringArray[81] = "sessionIdName";
        stringArray[82] = "sessionConfig";
        stringArray[83] = "hasCookieWithName";
        stringArray[84] = "sessionIdName";
        stringArray[85] = "sessionConfig";
        stringArray[86] = "hasCookieWithName";
        stringArray[87] = "hasCookieWithName";
        stringArray[88] = "sessionIdName";
        stringArray[89] = "sessionConfig";
        stringArray[90] = "hasCookieWithName";
        stringArray[91] = "findAll";
        stringArray[92] = "<$constructor$>";
        stringArray[93] = "filters";
        stringArray[94] = "filters";
        stringArray[95] = "any";
        stringArray[96] = "any";
        stringArray[97] = "authenticationScheme";
        stringArray[98] = "removeAll";
        stringArray[99] = "any";
        stringArray[100] = "any";
        stringArray[101] = "authenticationScheme";
        stringArray[102] = "removeAll";
        stringArray[103] = "findAll";
        stringArray[104] = "addAll";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[105];
        SpecificationMerger.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(SpecificationMerger.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = SpecificationMerger.$createCallSiteArray();
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

