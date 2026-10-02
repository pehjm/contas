/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.filter;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.lang.Tuple2;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.config.CsrfConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SessionConfig;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.internal.csrf.CsrfData;
import io.restassured.internal.csrf.CsrfTokenFinder;
import io.restassured.internal.filter.FormAuthFilter;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.spi.AuthFilter;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class FormAuthFilter
implements AuthFilter,
GroovyObject {
    private static final String FIND_INPUT_TAG_WITH_TYPE = "html.depthFirst().grep { it.name() == 'input' && it.@type == '%s' }.collect { it.@name }";
    private static final String FIND_INPUT_VALUE_OF_INPUT_TAG_WITH_NAME = "html.depthFirst().grep { it.name() == 'input' && it.@name == '%s' }.collect { it.@value }";
    private static final String FIND_FORM_ACTION = "html.depthFirst().grep { it.name() == 'form' }.get(0).@action";
    private Object userName;
    private Object password;
    private FormAuthConfig formAuthConfig;
    private SessionConfig sessionConfig;
    private CsrfConfig csrfConfig;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729361;
    private static /* synthetic */ SoftReference $callSiteArray;

    public FormAuthFilter() {
        MetaClass metaClass;
        CallSite[] callSiteArray = FormAuthFilter.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        CallSite[] callSiteArray = FormAuthFilter.$getCallSiteArray();
        String formAction = null;
        String userNameInputField = null;
        String passwordInputField = null;
        CsrfData csrfData = null;
        Map cookiesFromLoginPage = null;
        Reference<List> additionalInputFields = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[0]));
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(this.formAuthConfig, null)) {
                Object object = callSiteArray[0].callConstructor(FormAuthConfig.class);
                this.formAuthConfig = (FormAuthConfig)ScriptBytecodeAdapter.castToType(object, FormAuthConfig.class);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(this.formAuthConfig, null)) {
            Object object = callSiteArray[1].callConstructor(FormAuthConfig.class);
            this.formAuthConfig = (FormAuthConfig)ScriptBytecodeAdapter.castToType(object, FormAuthConfig.class);
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call(this.formAuthConfig)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[3].call(this.csrfConfig))) {
                Object loginPageResponse = null;
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[4].call(this.csrfConfig))) {
                    Object object;
                    loginPageResponse = object = callSiteArray[5].call(callSiteArray[6].call(callSiteArray[7].call(callSiteArray[8].call(callSiteArray[9].call(callSiteArray[10].callStatic(RestAssured.class)))), callSiteArray[11].call(requestSpec)), callSiteArray[12].call(this.csrfConfig));
                    Object object2 = callSiteArray[13].call(loginPageResponse);
                    cookiesFromLoginPage = (Map)ScriptBytecodeAdapter.castToType(object2, Map.class);
                } else {
                    Object object;
                    loginPageResponse = object = callSiteArray[14].call((Object)ctx, callSiteArray[15].call(callSiteArray[16].call(callSiteArray[17].call(callSiteArray[18].callStatic(RestAssured.class), requestSpec))));
                    Object object3 = callSiteArray[19].call(loginPageResponse);
                    cookiesFromLoginPage = (Map)ScriptBytecodeAdapter.castToType(object3, Map.class);
                    if (ScriptBytecodeAdapter.compareEqual(callSiteArray[20].call(loginPageResponse), 302)) {
                        Object object4;
                        loginPageResponse = object4 = callSiteArray[21].call(callSiteArray[22].call(callSiteArray[23].call(callSiteArray[24].call(callSiteArray[25].callStatic(RestAssured.class))), cookiesFromLoginPage), callSiteArray[26].call(loginPageResponse, "Location"));
                    }
                }
                Reference<Object> html = new Reference<Object>(callSiteArray[27].callConstructor(XmlPath.class, callSiteArray[28].callGetProperty(XmlPath.CompatibilityMode.class), callSiteArray[29].call(loginPageResponse)));
                Object object = DefaultTypeTransformation.booleanUnbox(callSiteArray[30].call(this.formAuthConfig)) ? callSiteArray[31].call(this.formAuthConfig) : callSiteArray[32].call(new GeneratedClosure(this, this, html){
                    private /* synthetic */ Reference html;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _filter_closure1.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.html = reference = html;
                    }

                    public Object doCall(Object it) {
                        CallSite[] callSiteArray = _filter_closure1.$getCallSiteArray();
                        String tempFormAction = (String)ScriptBytecodeAdapter.castToType(callSiteArray[0].callStatic(FormAuthFilter.class, new GeneratedClosure(this, this.getThisObject(), this.html){
                            private /* synthetic */ Reference html;
                            private static /* synthetic */ ClassInfo $staticClassInfo;
                            public static transient /* synthetic */ boolean __$stMC;
                            private static /* synthetic */ SoftReference $callSiteArray;
                            {
                                Reference reference;
                                CallSite[] callSiteArray = _filter_closure1_closure7.$getCallSiteArray();
                                super(_outerInstance, _thisObject);
                                this.html = reference = html;
                            }

                            public Object doCall(Object it) {
                                CallSite[] callSiteArray = _filter_closure1_closure7.$getCallSiteArray();
                                return callSiteArray[0].call(this.html.get(), callSiteArray[1].callGroovyObjectGetProperty(this));
                            }

                            public Object getHtml() {
                                CallSite[] callSiteArray = _filter_closure1_closure7.$getCallSiteArray();
                                return this.html.get();
                            }

                            public Object doCall() {
                                CallSite[] callSiteArray = _filter_closure1_closure7.$getCallSiteArray();
                                return this.doCall(null);
                            }

                            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                if (this.getClass() != _filter_closure1_closure7.class) {
                                    return ScriptBytecodeAdapter.initMetaClass(this);
                                }
                                ClassInfo classInfo = $staticClassInfo;
                                if (classInfo == null) {
                                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                                }
                                return classInfo.getMetaClass();
                            }

                            public static /* synthetic */ void __$swapInit() {
                                CallSite[] callSiteArray = _filter_closure1_closure7.$getCallSiteArray();
                                $callSiteArray = null;
                            }

                            static {
                                _filter_closure1_closure7.__$swapInit();
                            }

                            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                                stringArray[0] = "getString";
                                stringArray[1] = "FIND_FORM_ACTION";
                            }

                            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                                String[] stringArray = new String[2];
                                _filter_closure1_closure7.$createCallSiteArray_1(stringArray);
                                return new CallSiteArray(_filter_closure1_closure7.class, stringArray);
                            }

                            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                                CallSiteArray callSiteArray;
                                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                    callSiteArray = _filter_closure1_closure7.$createCallSiteArray();
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
                        return DefaultTypeTransformation.booleanUnbox(callSiteArray[1].call((Object)tempFormAction, "/")) ? tempFormAction : callSiteArray[2].call((Object)"/", tempFormAction);
                    }

                    public Object getHtml() {
                        CallSite[] callSiteArray = _filter_closure1.$getCallSiteArray();
                        return this.html.get();
                    }

                    public Object doCall() {
                        CallSite[] callSiteArray = _filter_closure1.$getCallSiteArray();
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _filter_closure1.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _filter_closure1.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _filter_closure1.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "throwIfException";
                        stringArray[1] = "startsWith";
                        stringArray[2] = "plus";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[3];
                        _filter_closure1.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_filter_closure1.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _filter_closure1.$createCallSiteArray();
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
                formAction = (String)ScriptBytecodeAdapter.castToType(object, String.class);
                Object object5 = DefaultTypeTransformation.booleanUnbox(callSiteArray[33].call(this.formAuthConfig)) ? callSiteArray[34].call(this.formAuthConfig) : callSiteArray[35].callStatic(FormAuthFilter.class, new GeneratedClosure(this, this, html){
                    private /* synthetic */ Reference html;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _filter_closure2.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.html = reference = html;
                    }

                    public Object doCall(Object it) {
                        CallSite[] callSiteArray = _filter_closure2.$getCallSiteArray();
                        return callSiteArray[0].call(this.html.get(), callSiteArray[1].callStatic(String.class, callSiteArray[2].callGetProperty(FormAuthFilter.class), "text"));
                    }

                    public Object getHtml() {
                        CallSite[] callSiteArray = _filter_closure2.$getCallSiteArray();
                        return this.html.get();
                    }

                    public Object doCall() {
                        CallSite[] callSiteArray = _filter_closure2.$getCallSiteArray();
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _filter_closure2.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _filter_closure2.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _filter_closure2.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "getString";
                        stringArray[1] = "format";
                        stringArray[2] = "FIND_INPUT_TAG_WITH_TYPE";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[3];
                        _filter_closure2.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_filter_closure2.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _filter_closure2.$createCallSiteArray();
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
                userNameInputField = (String)ScriptBytecodeAdapter.castToType(object5, String.class);
                Object object6 = DefaultTypeTransformation.booleanUnbox(callSiteArray[36].call(this.formAuthConfig)) ? callSiteArray[37].call(this.formAuthConfig) : callSiteArray[38].callStatic(FormAuthFilter.class, new GeneratedClosure(this, this, html){
                    private /* synthetic */ Reference html;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        Reference reference;
                        CallSite[] callSiteArray = _filter_closure3.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                        this.html = reference = html;
                    }

                    public Object doCall(Object it) {
                        CallSite[] callSiteArray = _filter_closure3.$getCallSiteArray();
                        return callSiteArray[0].call(this.html.get(), callSiteArray[1].callStatic(String.class, callSiteArray[2].callGetProperty(FormAuthFilter.class), "password"));
                    }

                    public Object getHtml() {
                        CallSite[] callSiteArray = _filter_closure3.$getCallSiteArray();
                        return this.html.get();
                    }

                    public Object doCall() {
                        CallSite[] callSiteArray = _filter_closure3.$getCallSiteArray();
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _filter_closure3.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _filter_closure3.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _filter_closure3.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "getString";
                        stringArray[1] = "format";
                        stringArray[2] = "FIND_INPUT_TAG_WITH_TYPE";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[3];
                        _filter_closure3.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_filter_closure3.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _filter_closure3.$createCallSiteArray();
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
                passwordInputField = (String)ScriptBytecodeAdapter.castToType(object6, String.class);
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[39].call(this.csrfConfig))) {
                    Object object7 = callSiteArray[40].call(CsrfTokenFinder.class, this.csrfConfig, loginPageResponse);
                    csrfData = (CsrfData)ScriptBytecodeAdapter.castToType(object7, CsrfData.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[41].call(this.formAuthConfig))) {
                    callSiteArray[42].call(callSiteArray[43].call(this.formAuthConfig), new GeneratedClosure(this, this, html, additionalInputFields){
                        private /* synthetic */ Reference html;
                        private /* synthetic */ Reference additionalInputFields;
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;
                        private static /* synthetic */ SoftReference $callSiteArray;
                        {
                            Reference reference;
                            Reference reference2;
                            CallSite[] callSiteArray = _filter_closure4.$getCallSiteArray();
                            super(_outerInstance, _thisObject);
                            this.html = reference2 = html;
                            this.additionalInputFields = reference = additionalInputFields;
                        }

                        public Object doCall(Object name) {
                            Reference<Object> name2 = new Reference<Object>(name);
                            CallSite[] callSiteArray = _filter_closure4.$getCallSiteArray();
                            String value = (String)ScriptBytecodeAdapter.castToType(callSiteArray[0].callStatic(FormAuthFilter.class, new GeneratedClosure(this, this.getThisObject(), name2, this.html){
                                private /* synthetic */ Reference name;
                                private /* synthetic */ Reference html;
                                private static /* synthetic */ ClassInfo $staticClassInfo;
                                public static transient /* synthetic */ boolean __$stMC;
                                private static /* synthetic */ SoftReference $callSiteArray;
                                {
                                    Reference reference;
                                    Reference reference2;
                                    CallSite[] callSiteArray = _filter_closure4_closure8.$getCallSiteArray();
                                    super(_outerInstance, _thisObject);
                                    this.name = reference2 = name;
                                    this.html = reference = html;
                                }

                                public Object doCall(Object it) {
                                    CallSite[] callSiteArray = _filter_closure4_closure8.$getCallSiteArray();
                                    return callSiteArray[0].call(this.html.get(), callSiteArray[1].callStatic(String.class, callSiteArray[2].callGroovyObjectGetProperty(this), this.name.get()));
                                }

                                public Object getName() {
                                    CallSite[] callSiteArray = _filter_closure4_closure8.$getCallSiteArray();
                                    return this.name.get();
                                }

                                public Object getHtml() {
                                    CallSite[] callSiteArray = _filter_closure4_closure8.$getCallSiteArray();
                                    return this.html.get();
                                }

                                public Object doCall() {
                                    CallSite[] callSiteArray = _filter_closure4_closure8.$getCallSiteArray();
                                    return this.doCall(null);
                                }

                                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                    if (this.getClass() != _filter_closure4_closure8.class) {
                                        return ScriptBytecodeAdapter.initMetaClass(this);
                                    }
                                    ClassInfo classInfo = $staticClassInfo;
                                    if (classInfo == null) {
                                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                                    }
                                    return classInfo.getMetaClass();
                                }

                                public static /* synthetic */ void __$swapInit() {
                                    CallSite[] callSiteArray = _filter_closure4_closure8.$getCallSiteArray();
                                    $callSiteArray = null;
                                }

                                static {
                                    _filter_closure4_closure8.__$swapInit();
                                }

                                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                                    stringArray[0] = "getString";
                                    stringArray[1] = "format";
                                    stringArray[2] = "FIND_INPUT_VALUE_OF_INPUT_TAG_WITH_NAME";
                                }

                                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                                    String[] stringArray = new String[3];
                                    _filter_closure4_closure8.$createCallSiteArray_1(stringArray);
                                    return new CallSiteArray(_filter_closure4_closure8.class, stringArray);
                                }

                                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                                    CallSiteArray callSiteArray;
                                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                        callSiteArray = _filter_closure4_closure8.$createCallSiteArray();
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
                            return callSiteArray[1].call(this.additionalInputFields.get(), callSiteArray[2].callConstructor(Tuple2.class, name2.get(), value));
                        }

                        public Object getHtml() {
                            CallSite[] callSiteArray = _filter_closure4.$getCallSiteArray();
                            return this.html.get();
                        }

                        public List getAdditionalInputFields() {
                            CallSite[] callSiteArray = _filter_closure4.$getCallSiteArray();
                            return (List)ScriptBytecodeAdapter.castToType(this.additionalInputFields.get(), List.class);
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _filter_closure4.class) {
                                return ScriptBytecodeAdapter.initMetaClass(this);
                            }
                            ClassInfo classInfo = $staticClassInfo;
                            if (classInfo == null) {
                                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                            }
                            return classInfo.getMetaClass();
                        }

                        public static /* synthetic */ void __$swapInit() {
                            CallSite[] callSiteArray = _filter_closure4.$getCallSiteArray();
                            $callSiteArray = null;
                        }

                        static {
                            _filter_closure4.__$swapInit();
                        }

                        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                            stringArray[0] = "throwIfException";
                            stringArray[1] = "add";
                            stringArray[2] = "<$constructor$>";
                        }

                        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                            String[] stringArray = new String[3];
                            _filter_closure4.$createCallSiteArray_1(stringArray);
                            return new CallSiteArray(_filter_closure4.class, stringArray);
                        }

                        private static /* synthetic */ CallSite[] $getCallSiteArray() {
                            CallSiteArray callSiteArray;
                            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                callSiteArray = _filter_closure4.$createCallSiteArray();
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
                }
            } else {
                Object object = callSiteArray[44].call(this.formAuthConfig);
                formAction = (String)ScriptBytecodeAdapter.castToType(object, String.class);
                Object object8 = callSiteArray[45].call(this.formAuthConfig);
                userNameInputField = (String)ScriptBytecodeAdapter.castToType(object8, String.class);
                Object object9 = callSiteArray[46].call(this.formAuthConfig);
                passwordInputField = (String)ScriptBytecodeAdapter.castToType(object9, String.class);
                Object var27_27 = null;
                additionalInputFields.set((List)ScriptBytecodeAdapter.castToType(var27_27, List.class));
                Object var28_28 = null;
                csrfData = (CsrfData)ScriptBytecodeAdapter.castToType(var28_28, CsrfData.class);
                Object var29_29 = null;
                cookiesFromLoginPage = (Map)ScriptBytecodeAdapter.castToType(var29_29, Map.class);
            }
        } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[47].call(this.formAuthConfig)) || DefaultTypeTransformation.booleanUnbox(callSiteArray[48].call(this.csrfConfig))) {
            Object loginPageResponse = null;
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[49].call(this.csrfConfig))) {
                Object object;
                loginPageResponse = object = callSiteArray[50].call(callSiteArray[51].call(callSiteArray[52].call(callSiteArray[53].call(callSiteArray[54].call(callSiteArray[55].callStatic(RestAssured.class)))), callSiteArray[56].call(requestSpec)), callSiteArray[57].call(this.csrfConfig));
                Object object10 = callSiteArray[58].call(loginPageResponse);
                cookiesFromLoginPage = (Map)ScriptBytecodeAdapter.castToType(object10, Map.class);
            } else {
                Object object;
                loginPageResponse = object = callSiteArray[59].call((Object)ctx, callSiteArray[60].call(callSiteArray[61].call(callSiteArray[62].call(callSiteArray[63].callStatic(RestAssured.class), requestSpec))));
                Object object11 = callSiteArray[64].call(loginPageResponse);
                cookiesFromLoginPage = (Map)ScriptBytecodeAdapter.castToType(object11, Map.class);
                if (ScriptBytecodeAdapter.compareEqual(callSiteArray[65].call(loginPageResponse), 302)) {
                    Object object12;
                    loginPageResponse = object12 = callSiteArray[66].call(callSiteArray[67].call(callSiteArray[68].call(callSiteArray[69].call(callSiteArray[70].callStatic(RestAssured.class))), cookiesFromLoginPage), callSiteArray[71].call(loginPageResponse, "Location"));
                }
            }
            Reference<Object> html = new Reference<Object>(callSiteArray[72].callConstructor(XmlPath.class, callSiteArray[73].callGetProperty(XmlPath.CompatibilityMode.class), callSiteArray[74].call(loginPageResponse)));
            Object object = DefaultTypeTransformation.booleanUnbox(callSiteArray[75].call(this.formAuthConfig)) ? callSiteArray[76].call(this.formAuthConfig) : callSiteArray[77].call(new /* invalid duplicate definition of identical inner class */);
            formAction = (String)ScriptBytecodeAdapter.castToType(object, String.class);
            Object object13 = DefaultTypeTransformation.booleanUnbox(callSiteArray[78].call(this.formAuthConfig)) ? callSiteArray[79].call(this.formAuthConfig) : callSiteArray[80].callStatic(FormAuthFilter.class, new /* invalid duplicate definition of identical inner class */);
            userNameInputField = (String)ScriptBytecodeAdapter.castToType(object13, String.class);
            Object object14 = DefaultTypeTransformation.booleanUnbox(callSiteArray[81].call(this.formAuthConfig)) ? callSiteArray[82].call(this.formAuthConfig) : callSiteArray[83].callStatic(FormAuthFilter.class, new /* invalid duplicate definition of identical inner class */);
            passwordInputField = (String)ScriptBytecodeAdapter.castToType(object14, String.class);
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[84].call(this.csrfConfig))) {
                Object object15 = callSiteArray[85].call(CsrfTokenFinder.class, this.csrfConfig, loginPageResponse);
                csrfData = (CsrfData)ScriptBytecodeAdapter.castToType(object15, CsrfData.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[86].call(this.formAuthConfig))) {
                callSiteArray[87].call(callSiteArray[88].call(this.formAuthConfig), new /* invalid duplicate definition of identical inner class */);
            }
        } else {
            Object object = callSiteArray[89].call(this.formAuthConfig);
            formAction = (String)ScriptBytecodeAdapter.castToType(object, String.class);
            Object object16 = callSiteArray[90].call(this.formAuthConfig);
            userNameInputField = (String)ScriptBytecodeAdapter.castToType(object16, String.class);
            Object object17 = callSiteArray[91].call(this.formAuthConfig);
            passwordInputField = (String)ScriptBytecodeAdapter.castToType(object17, String.class);
            Object var44_44 = null;
            additionalInputFields.set((List)ScriptBytecodeAdapter.castToType(var44_44, List.class));
            Object var45_45 = null;
            csrfData = (CsrfData)ScriptBytecodeAdapter.castToType(var45_45, CsrfData.class);
            Object var46_46 = null;
            cookiesFromLoginPage = (Map)ScriptBytecodeAdapter.castToType(var46_46, Map.class);
        }
        String string = DefaultTypeTransformation.booleanUnbox(callSiteArray[92].callSafe((Object)formAction, "/")) ? formAction : callSiteArray[93].call((Object)"/", formAction);
        formAction = (String)ScriptBytecodeAdapter.castToType(string, String.class);
        Reference<Object> loginRequestSpec = new Reference<Object>(callSiteArray[94].call(callSiteArray[95].call(callSiteArray[96].call(callSiteArray[97].call(callSiteArray[98].call(callSiteArray[99].call(callSiteArray[100].callStatic(RestAssured.class)))))), userNameInputField, this.userName, passwordInputField, this.password));
        Object uri = callSiteArray[101].callConstructor(URI.class, callSiteArray[102].call(requestSpec));
        String loginUri = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object = callSiteArray[103].call(callSiteArray[104].call(callSiteArray[105].call(callSiteArray[106].call(callSiteArray[107].call(uri), "://"), callSiteArray[108].call(uri)), ScriptBytecodeAdapter.compareEqual(callSiteArray[109].call(uri), -1) ? "" : callSiteArray[110].call((Object)":", callSiteArray[111].call(uri))), formAction);
            loginUri = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        } else {
            Object object = callSiteArray[112].call(callSiteArray[113].call(callSiteArray[114].call(callSiteArray[115].call(callSiteArray[116].call(uri), "://"), callSiteArray[117].call(uri)), ScriptBytecodeAdapter.compareEqual(callSiteArray[118].call(uri), -1) ? "" : callSiteArray[119].call((Object)":", callSiteArray[120].call(uri))), formAction);
            loginUri = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(cookiesFromLoginPage, null)) {
                callSiteArray[121].call(loginRequestSpec.get(), cookiesFromLoginPage);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(cookiesFromLoginPage, null)) {
            callSiteArray[122].call(loginRequestSpec.get(), cookiesFromLoginPage);
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(csrfData, null)) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[123].call((Object)csrfData, callSiteArray[124].callGetProperty(CsrfConfig.CsrfPrioritization.class)))) {
                    callSiteArray[125].call(loginRequestSpec.get(), callSiteArray[126].callGetProperty(csrfData), callSiteArray[127].callGetProperty(csrfData));
                } else {
                    callSiteArray[128].call(loginRequestSpec.get(), callSiteArray[129].callGetProperty(csrfData), callSiteArray[130].callGetProperty(csrfData));
                }
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(csrfData, null)) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[131].call((Object)csrfData, callSiteArray[132].callGetProperty(CsrfConfig.CsrfPrioritization.class)))) {
                callSiteArray[133].call(loginRequestSpec.get(), callSiteArray[134].callGetProperty(csrfData), callSiteArray[135].callGetProperty(csrfData));
            } else {
                callSiteArray[136].call(loginRequestSpec.get(), callSiteArray[137].callGetProperty(csrfData), callSiteArray[138].callGetProperty(csrfData));
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[139].callSafe(this.formAuthConfig))) {
            Object logConfig = callSiteArray[140].call(this.formAuthConfig);
            Object logDetail = callSiteArray[141].call(this.formAuthConfig);
            if (ScriptBytecodeAdapter.compareNotEqual(logDetail, callSiteArray[142].callGetProperty(LogDetail.class))) {
                callSiteArray[143].call(loginRequestSpec.get(), callSiteArray[144].callConstructor((Object)RequestLoggingFilter.class, ArrayUtil.createArray(logDetail, callSiteArray[145].call(logConfig), callSiteArray[146].call(logConfig), callSiteArray[147].call(logConfig), callSiteArray[148].call(logConfig))));
            }
            if (ScriptBytecodeAdapter.compareNotEqual(logDetail, callSiteArray[149].callGetProperty(LogDetail.class))) {
                callSiteArray[150].call(loginRequestSpec.get(), callSiteArray[151].callConstructor(ResponseLoggingFilter.class, logDetail, callSiteArray[152].call(logConfig), callSiteArray[153].call(logConfig)));
            }
        }
        callSiteArray[154].callSafe((Object)additionalInputFields.get(), new GeneratedClosure(this, this, loginRequestSpec){
            private /* synthetic */ Reference loginRequestSpec;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _filter_closure5.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.loginRequestSpec = reference = loginRequestSpec;
            }

            public Object doCall(Object tuple) {
                CallSite[] callSiteArray = _filter_closure5.$getCallSiteArray();
                return callSiteArray[0].call(this.loginRequestSpec.get(), callSiteArray[1].call(tuple), callSiteArray[2].call(tuple));
            }

            public Object getLoginRequestSpec() {
                CallSite[] callSiteArray = _filter_closure5.$getCallSiteArray();
                return this.loginRequestSpec.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _filter_closure5.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _filter_closure5.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _filter_closure5.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "formParam";
                stringArray[1] = "getV1";
                stringArray[2] = "getV2";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _filter_closure5.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_filter_closure5.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _filter_closure5.$createCallSiteArray();
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
        callSiteArray[155].callStatic(FormAuthFilter.class, requestSpec, loginRequestSpec.get());
        Response loggedInResponse = (Response)ScriptBytecodeAdapter.castToType(callSiteArray[156].call(loginRequestSpec.get(), loginUri), Response.class);
        callSiteArray[157].call((Object)requestSpec, callSiteArray[158].call(loggedInResponse));
        return (Response)ScriptBytecodeAdapter.castToType(callSiteArray[159].call(ctx, requestSpec, responseSpec), Response.class);
    }

    public static void applySessionFilterFromOriginalRequestIfDefined(FilterableRequestSpecification requestSpec, RequestSpecification loginRequestSpec) {
        CallSite[] callSiteArray = FormAuthFilter.$getCallSiteArray();
        Object filters = callSiteArray[160].call(requestSpec);
        Object sessionFilterInOriginalRequest = callSiteArray[161].call(filters, new GeneratedClosure(FormAuthFilter.class, FormAuthFilter.class){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _applySessionFilterFromOriginalRequestIfDefined_closure6.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _applySessionFilterFromOriginalRequestIfDefined_closure6.$getCallSiteArray();
                return callSiteArray[0].call(callSiteArray[1].callGetProperty(it), SessionFilter.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _applySessionFilterFromOriginalRequestIfDefined_closure6.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _applySessionFilterFromOriginalRequestIfDefined_closure6.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _applySessionFilterFromOriginalRequestIfDefined_closure6.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _applySessionFilterFromOriginalRequestIfDefined_closure6.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "isAssignableFrom";
                stringArray[1] = "class";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _applySessionFilterFromOriginalRequestIfDefined_closure6.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_applySessionFilterFromOriginalRequestIfDefined_closure6.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _applySessionFilterFromOriginalRequestIfDefined_closure6.$createCallSiteArray();
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
        if (DefaultTypeTransformation.booleanUnbox(sessionFilterInOriginalRequest)) {
            callSiteArray[162].call((Object)loginRequestSpec, SessionFilter.class);
            callSiteArray[163].call((Object)loginRequestSpec, sessionFilterInOriginalRequest);
            Object sessionIdName = callSiteArray[164].call(callSiteArray[165].call(callSiteArray[166].call(requestSpec)));
            Object object = callSiteArray[167].callGetProperty(loginRequestSpec);
            Object cfg = DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[168].callConstructor(RestAssuredConfig.class);
            callSiteArray[169].call((Object)loginRequestSpec, callSiteArray[170].call(cfg, callSiteArray[171].call(callSiteArray[172].call(SessionConfig.class), sessionIdName)));
        }
    }

    public static Object throwIfException(Closure closure) {
        CallSite[] callSiteArray = FormAuthFilter.$getCallSiteArray();
        Object object = callSiteArray[173].call(closure);
        try {
            return object;
        }
        catch (Exception e) {
            throw (Throwable)callSiteArray[174].callConstructor(IllegalArgumentException.class, "Failed to parse login page. Check for errors on the login page or specify FormAuthConfig.", e);
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = FormAuthFilter.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(FormAuthFilter.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = FormAuthFilter.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, FormAuthFilter.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = FormAuthFilter.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(FormAuthFilter.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != FormAuthFilter.class) {
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
        CallSite[] callSiteArray = FormAuthFilter.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        FormAuthFilter.__$swapInit();
        __timeStamp__239_neverHappen1702030729361 = l2 = 0L;
        __timeStamp = l = 1702030729361L;
    }

    public Object getUserName() {
        return this.userName;
    }

    public void setUserName(Object object) {
        this.userName = object;
    }

    public Object getPassword() {
        return this.password;
    }

    public void setPassword(Object object) {
        this.password = object;
    }

    public FormAuthConfig getFormAuthConfig() {
        return this.formAuthConfig;
    }

    public void setFormAuthConfig(FormAuthConfig formAuthConfig) {
        this.formAuthConfig = formAuthConfig;
    }

    public SessionConfig getSessionConfig() {
        return this.sessionConfig;
    }

    public void setSessionConfig(SessionConfig sessionConfig) {
        this.sessionConfig = sessionConfig;
    }

    public CsrfConfig getCsrfConfig() {
        return this.csrfConfig;
    }

    public void setCsrfConfig(CsrfConfig csrfConfig) {
        this.csrfConfig = csrfConfig;
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
        stringArray[0] = "<$constructor$>";
        stringArray[1] = "<$constructor$>";
        stringArray[2] = "requiresParsingOfLoginPage";
        stringArray[3] = "isCsrfEnabled";
        stringArray[4] = "isCsrfEnabled";
        stringArray[5] = "get";
        stringArray[6] = "cookies";
        stringArray[7] = "disableCsrf";
        stringArray[8] = "none";
        stringArray[9] = "auth";
        stringArray[10] = "given";
        stringArray[11] = "getCookies";
        stringArray[12] = "getCsrfTokenPath";
        stringArray[13] = "cookies";
        stringArray[14] = "send";
        stringArray[15] = "none";
        stringArray[16] = "auth";
        stringArray[17] = "spec";
        stringArray[18] = "given";
        stringArray[19] = "cookies";
        stringArray[20] = "statusCode";
        stringArray[21] = "get";
        stringArray[22] = "cookies";
        stringArray[23] = "none";
        stringArray[24] = "auth";
        stringArray[25] = "given";
        stringArray[26] = "getHeader";
        stringArray[27] = "<$constructor$>";
        stringArray[28] = "HTML";
        stringArray[29] = "asString";
        stringArray[30] = "hasFormAction";
        stringArray[31] = "getFormAction";
        stringArray[32] = "call";
        stringArray[33] = "hasUserInputTagName";
        stringArray[34] = "getUserInputTagName";
        stringArray[35] = "throwIfException";
        stringArray[36] = "hasPasswordInputTagName";
        stringArray[37] = "getPasswordInputTagName";
        stringArray[38] = "throwIfException";
        stringArray[39] = "isCsrfEnabled";
        stringArray[40] = "findInHtml";
        stringArray[41] = "hasAdditionalInputFieldNames";
        stringArray[42] = "each";
        stringArray[43] = "getAdditionalInputFieldNames";
        stringArray[44] = "getFormAction";
        stringArray[45] = "getUserInputTagName";
        stringArray[46] = "getPasswordInputTagName";
        stringArray[47] = "requiresParsingOfLoginPage";
        stringArray[48] = "isCsrfEnabled";
        stringArray[49] = "isCsrfEnabled";
        stringArray[50] = "get";
        stringArray[51] = "cookies";
        stringArray[52] = "disableCsrf";
        stringArray[53] = "none";
        stringArray[54] = "auth";
        stringArray[55] = "given";
        stringArray[56] = "getCookies";
        stringArray[57] = "getCsrfTokenPath";
        stringArray[58] = "cookies";
        stringArray[59] = "send";
        stringArray[60] = "none";
        stringArray[61] = "auth";
        stringArray[62] = "spec";
        stringArray[63] = "given";
        stringArray[64] = "cookies";
        stringArray[65] = "statusCode";
        stringArray[66] = "get";
        stringArray[67] = "cookies";
        stringArray[68] = "none";
        stringArray[69] = "auth";
        stringArray[70] = "given";
        stringArray[71] = "getHeader";
        stringArray[72] = "<$constructor$>";
        stringArray[73] = "HTML";
        stringArray[74] = "asString";
        stringArray[75] = "hasFormAction";
        stringArray[76] = "getFormAction";
        stringArray[77] = "call";
        stringArray[78] = "hasUserInputTagName";
        stringArray[79] = "getUserInputTagName";
        stringArray[80] = "throwIfException";
        stringArray[81] = "hasPasswordInputTagName";
        stringArray[82] = "getPasswordInputTagName";
        stringArray[83] = "throwIfException";
        stringArray[84] = "isCsrfEnabled";
        stringArray[85] = "findInHtml";
        stringArray[86] = "hasAdditionalInputFieldNames";
        stringArray[87] = "each";
        stringArray[88] = "getAdditionalInputFieldNames";
        stringArray[89] = "getFormAction";
        stringArray[90] = "getUserInputTagName";
        stringArray[91] = "getPasswordInputTagName";
        stringArray[92] = "startsWith";
        stringArray[93] = "plus";
        stringArray[94] = "formParams";
        stringArray[95] = "and";
        stringArray[96] = "disableCsrf";
        stringArray[97] = "and";
        stringArray[98] = "none";
        stringArray[99] = "auth";
        stringArray[100] = "given";
        stringArray[101] = "<$constructor$>";
        stringArray[102] = "getURI";
        stringArray[103] = "plus";
        stringArray[104] = "plus";
        stringArray[105] = "plus";
        stringArray[106] = "plus";
        stringArray[107] = "getScheme";
        stringArray[108] = "getHost";
        stringArray[109] = "getPort";
        stringArray[110] = "plus";
        stringArray[111] = "getPort";
        stringArray[112] = "plus";
        stringArray[113] = "plus";
        stringArray[114] = "plus";
        stringArray[115] = "plus";
        stringArray[116] = "getScheme";
        stringArray[117] = "getHost";
        stringArray[118] = "getPort";
        stringArray[119] = "plus";
        stringArray[120] = "getPort";
        stringArray[121] = "cookies";
        stringArray[122] = "cookies";
        stringArray[123] = "shouldSendTokenAs";
        stringArray[124] = "FORM";
        stringArray[125] = "formParam";
        stringArray[126] = "inputFieldOrHeaderName";
        stringArray[127] = "token";
        stringArray[128] = "header";
        stringArray[129] = "inputFieldOrHeaderName";
        stringArray[130] = "token";
        stringArray[131] = "shouldSendTokenAs";
        stringArray[132] = "FORM";
        stringArray[133] = "formParam";
        stringArray[134] = "inputFieldOrHeaderName";
        stringArray[135] = "token";
        stringArray[136] = "header";
        stringArray[137] = "inputFieldOrHeaderName";
        stringArray[138] = "token";
        stringArray[139] = "isLoggingEnabled";
        stringArray[140] = "getLogConfig";
        stringArray[141] = "getLogDetail";
        stringArray[142] = "STATUS";
        stringArray[143] = "filter";
        stringArray[144] = "<$constructor$>";
        stringArray[145] = "isPrettyPrintingEnabled";
        stringArray[146] = "defaultStream";
        stringArray[147] = "shouldUrlEncodeRequestUri";
        stringArray[148] = "blacklistedHeaders";
        stringArray[149] = "PARAMS";
        stringArray[150] = "filter";
        stringArray[151] = "<$constructor$>";
        stringArray[152] = "isPrettyPrintingEnabled";
        stringArray[153] = "defaultStream";
        stringArray[154] = "forEach";
        stringArray[155] = "applySessionFilterFromOriginalRequestIfDefined";
        stringArray[156] = "post";
        stringArray[157] = "cookies";
        stringArray[158] = "cookies";
        stringArray[159] = "next";
        stringArray[160] = "getDefinedFilters";
        stringArray[161] = "find";
        stringArray[162] = "noFiltersOfType";
        stringArray[163] = "filter";
        stringArray[164] = "sessionIdName";
        stringArray[165] = "getSessionConfig";
        stringArray[166] = "getConfig";
        stringArray[167] = "config";
        stringArray[168] = "<$constructor$>";
        stringArray[169] = "config";
        stringArray[170] = "sessionConfig";
        stringArray[171] = "sessionIdName";
        stringArray[172] = "sessionConfig";
        stringArray[173] = "call";
        stringArray[174] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[175];
        FormAuthFilter.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(FormAuthFilter.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = FormAuthFilter.$createCallSiteArray();
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

