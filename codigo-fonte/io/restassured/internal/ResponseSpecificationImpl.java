/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.lang.Tuple2;
import io.restassured.assertion.DetailedCookieAssertion;
import io.restassured.assertion.HeaderMatcher;
import io.restassured.assertion.ResponseTimeMatcher;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.internal.ContentParser;
import io.restassured.internal.MapCreator;
import io.restassured.internal.ResponseLogSpecificationImpl;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.internal.SpecificationMerger;
import io.restassured.internal.assertion.BodyMatcher;
import io.restassured.internal.assertion.BodyMatcherGroup;
import io.restassured.internal.assertion.CookieMatcher;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.log.LogRepository;
import io.restassured.internal.util.MatcherErrorMessageBuilder;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseLogSpecification;
import io.restassured.specification.ResponseSpecification;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ResponseSpecificationImpl
implements FilterableResponseSpecification,
GroovyObject {
    private static final String EMPTY = "";
    private static final String DOT;
    private static final String SAFE_REF = "?.";
    private static final String SAFE_INDEX = "?[";
    private static final String SPREAD_REF = "*.";
    private Matcher<Integer> expectedStatusCode;
    private Matcher<String> expectedStatusLine;
    private BodyMatcherGroup bodyMatchers;
    private HamcrestAssertionClosure assertionClosure;
    private Object headerAssertions;
    private Object cookieAssertions;
    private RequestSpecification requestSpecification;
    private Object contentType;
    private Response restAssuredResponse;
    private String bodyRootPath;
    private ResponseParserRegistrar rpr;
    private RestAssuredConfig config;
    private Response response;
    private Tuple2<Matcher<Long>, TimeUnit> expectedResponseTime;
    private LogDetail responseLogDetail;
    private boolean forceDisableEagerAssert;
    private String onFailMessage;
    private Object contentParser;
    private LogRepository logRepository;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729374;
    private static /* synthetic */ SoftReference $callSiteArray;

    public ResponseSpecificationImpl(String bodyRootPath, ResponseSpecification defaultSpec, ResponseParserRegistrar rpr, RestAssuredConfig config, LogRepository logRepository) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        this(bodyRootPath, defaultSpec, rpr, config, null, logRepository);
    }

    /*
     * WARNING - void declaration
     */
    public ResponseSpecificationImpl(String string, ResponseSpecification responseSpecification, ResponseParserRegistrar responseParserRegistrar, RestAssuredConfig restAssuredConfig, Response response, LogRepository logRepository) {
        void logRepository2;
        void defaultSpec;
        void rpr;
        void bodyRootPath;
        void response2;
        void config;
        MetaClass metaClass;
        boolean bl;
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[0].callConstructor(BodyMatcherGroup.class);
        this.bodyMatchers = (BodyMatcherGroup)ScriptBytecodeAdapter.castToType(object, BodyMatcherGroup.class);
        Object object2 = callSiteArray[1].callConstructor(HamcrestAssertionClosure.class, this, this);
        this.assertionClosure = (HamcrestAssertionClosure)ScriptBytecodeAdapter.castToType(object2, HamcrestAssertionClosure.class);
        List list = ScriptBytecodeAdapter.createList(new Object[0]);
        this.headerAssertions = list;
        List list2 = ScriptBytecodeAdapter.createList(new Object[0]);
        this.cookieAssertions = list2;
        this.forceDisableEagerAssert = bl = false;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        callSiteArray[2].call(Validate.class, config, "RestAssuredConfig cannot be null");
        void var14_14 = config;
        this.config = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(var14_14, RestAssuredConfig.class);
        void var15_15 = response2;
        this.response = (Response)ScriptBytecodeAdapter.castToType(var15_15, Response.class);
        if (BytecodeInterface8.disabledStandardMetaClass()) {
            callSiteArray[3].callCurrent((GroovyObject)this, bodyRootPath);
        } else {
            this.rootPath((String)bodyRootPath);
        }
        void var16_16 = rpr;
        this.rpr = (ResponseParserRegistrar)ScriptBytecodeAdapter.castToType(var16_16, ResponseParserRegistrar.class);
        if (!BytecodeInterface8.isOrigZ() || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(defaultSpec, null)) {
                callSiteArray[4].callCurrent((GroovyObject)this, defaultSpec);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(defaultSpec, null)) {
            callSiteArray[5].callCurrent((GroovyObject)this, defaultSpec);
        }
        void var17_17 = logRepository2;
        this.logRepository = (LogRepository)ScriptBytecodeAdapter.castToType(var17_17, LogRepository.class);
    }

    @Override
    public ResponseSpecification body(List<Argument> arguments, Matcher matcher, Object ... additionalKeyMatcherPairs) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            callSiteArray[6].callCurrent((GroovyObject)this, "specify arguments");
        } else {
            this.throwIllegalStateExceptionIfRootPathIsNotDefined("specify arguments");
        }
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[7].callCurrent(this, EMPTY, arguments, matcher, additionalKeyMatcherPairs), ResponseSpecification.class);
    }

    @Override
    public Response validate(Response response) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[8].call((Object)this.assertionClosure, response);
        return response;
    }

    /*
     * WARNING - void declaration
     */
    public ResponseSpecification body(Matcher matcher, Matcher ... additionalMatchers) {
        void var2_2;
        Reference<Matcher> matcher2 = new Reference<Matcher>(matcher);
        Reference<void> additionalMatchers2 = new Reference<void>(var2_2);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[9].callStatic(AssertParameter.class, matcher2.get(), "matcher");
        callSiteArray[10].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, additionalMatchers2, matcher2){
            private /* synthetic */ Reference additionalMatchers;
            private /* synthetic */ Reference matcher;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _body_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.additionalMatchers = reference2 = additionalMatchers;
                this.matcher = reference = matcher;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _body_closure1.$getCallSiteArray();
                callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callConstructor(BodyMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", null, "matcher", this.matcher.get(), "rpr", callSiteArray[3].callGroovyObjectGetProperty(this)})));
                return callSiteArray[4].callSafe(this.additionalMatchers.get(), new GeneratedClosure(this, this.getThisObject()){
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        CallSite[] callSiteArray = _body_closure1_closure16.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                    }

                    public Object doCall(Object hamcrestMatcher) {
                        CallSite[] callSiteArray = _body_closure1_closure16.$getCallSiteArray();
                        return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callConstructor(BodyMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", null, "matcher", hamcrestMatcher, "rpr", callSiteArray[3].callGroovyObjectGetProperty(this)})));
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _body_closure1_closure16.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _body_closure1_closure16.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _body_closure1_closure16.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "add";
                        stringArray[1] = "bodyMatchers";
                        stringArray[2] = "<$constructor$>";
                        stringArray[3] = "rpr";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[4];
                        _body_closure1_closure16.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_body_closure1_closure16.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _body_closure1_closure16.$createCallSiteArray();
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

            public Matcher[] getAdditionalMatchers() {
                CallSite[] callSiteArray = _body_closure1.$getCallSiteArray();
                return (Matcher[])ScriptBytecodeAdapter.castToType(this.additionalMatchers.get(), Matcher[].class);
            }

            public Matcher getMatcher() {
                CallSite[] callSiteArray = _body_closure1.$getCallSiteArray();
                return (Matcher)ScriptBytecodeAdapter.castToType(this.matcher.get(), Matcher.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _body_closure1.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _body_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _body_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _body_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "add";
                stringArray[1] = "bodyMatchers";
                stringArray[2] = "<$constructor$>";
                stringArray[3] = "rpr";
                stringArray[4] = "each";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[5];
                _body_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_body_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _body_closure1.$createCallSiteArray();
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
        return this;
    }

    public ResponseSpecification body(String key, Matcher matcher, Object ... additionalKeyMatcherPairs) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[11].callCurrent(this, key, callSiteArray[12].call(Collections.class), matcher, additionalKeyMatcherPairs), ResponseSpecification.class);
    }

    @Override
    public ResponseSpecification time(Matcher<Long> matcher) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[13].callCurrent(this, matcher, callSiteArray[14].callGetProperty(TimeUnit.class)), ResponseSpecification.class);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public ResponseSpecification time(Matcher<Long> matcher, TimeUnit timeUnit) {
        void var2_2;
        Reference<Matcher<Long>> matcher2 = new Reference<Matcher<Long>>(matcher);
        Reference<void> timeUnit2 = new Reference<void>(var2_2);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[15].callStatic(AssertParameter.class, matcher2.get(), Matcher.class);
        callSiteArray[16].callStatic(AssertParameter.class, (Object)((TimeUnit)timeUnit2.get()), TimeUnit.class);
        callSiteArray[17].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, matcher2, timeUnit2){
            private /* synthetic */ Reference matcher;
            private /* synthetic */ Reference timeUnit;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _time_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.matcher = reference2 = matcher;
                this.timeUnit = reference = timeUnit;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _time_closure2.$getCallSiteArray();
                Object object = callSiteArray[0].callConstructor(Tuple2.class, this.matcher.get(), this.timeUnit.get());
                ScriptBytecodeAdapter.setGroovyObjectProperty((Tuple2)ScriptBytecodeAdapter.castToType(object, Tuple2.class), _time_closure2.class, this, "expectedResponseTime");
                return object;
            }

            public Matcher getMatcher() {
                CallSite[] callSiteArray = _time_closure2.$getCallSiteArray();
                return (Matcher)ScriptBytecodeAdapter.castToType(this.matcher.get(), Matcher.class);
            }

            public TimeUnit getTimeUnit() {
                CallSite[] callSiteArray = _time_closure2.$getCallSiteArray();
                return (TimeUnit)((Object)ScriptBytecodeAdapter.castToType(this.timeUnit.get(), TimeUnit.class));
            }

            public Object doCall() {
                CallSite[] callSiteArray = _time_closure2.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _time_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _time_closure2.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _time_closure2.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "<$constructor$>";
                return new CallSiteArray(_time_closure2.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _time_closure2.$createCallSiteArray();
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
        return this;
    }

    @Override
    public ResponseSpecification statusCode(Matcher<? super Integer> expectedStatusCode) {
        Reference<Matcher<? super Integer>> expectedStatusCode2 = new Reference<Matcher<? super Integer>>(expectedStatusCode);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[18].callStatic(AssertParameter.class, expectedStatusCode2.get(), "expectedStatusCode");
        callSiteArray[19].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, expectedStatusCode2){
            private /* synthetic */ Reference expectedStatusCode;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _statusCode_closure3.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.expectedStatusCode = reference = expectedStatusCode;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _statusCode_closure3.$getCallSiteArray();
                Object t = this.expectedStatusCode.get();
                ScriptBytecodeAdapter.setGroovyObjectProperty(t, ResponseSpecificationImpl.class, (GroovyObject)this.getThisObject(), "expectedStatusCode");
                return t;
            }

            public Matcher getExpectedStatusCode() {
                CallSite[] callSiteArray = _statusCode_closure3.$getCallSiteArray();
                return (Matcher)ScriptBytecodeAdapter.castToType(this.expectedStatusCode.get(), Matcher.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _statusCode_closure3.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _statusCode_closure3.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _statusCode_closure3.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _statusCode_closure3.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[]{};
                return new CallSiteArray(_statusCode_closure3.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _statusCode_closure3.$createCallSiteArray();
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
        return this;
    }

    @Override
    public ResponseSpecification statusCode(int expectedStatusCode) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[20].callStatic(AssertParameter.class, expectedStatusCode, "expectedStatusCode");
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[21].callCurrent((GroovyObject)this, callSiteArray[22].callStatic(Matchers.class, expectedStatusCode)), ResponseSpecification.class);
    }

    @Override
    public ResponseSpecification statusLine(Matcher<? super String> expectedStatusLine) {
        Reference<Matcher<? super String>> expectedStatusLine2 = new Reference<Matcher<? super String>>(expectedStatusLine);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[23].callStatic(AssertParameter.class, expectedStatusLine2.get(), "expectedStatusLine");
        callSiteArray[24].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, expectedStatusLine2){
            private /* synthetic */ Reference expectedStatusLine;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _statusLine_closure4.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.expectedStatusLine = reference = expectedStatusLine;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _statusLine_closure4.$getCallSiteArray();
                Object t = this.expectedStatusLine.get();
                ScriptBytecodeAdapter.setGroovyObjectProperty(t, ResponseSpecificationImpl.class, (GroovyObject)this.getThisObject(), "expectedStatusLine");
                return t;
            }

            public Matcher getExpectedStatusLine() {
                CallSite[] callSiteArray = _statusLine_closure4.$getCallSiteArray();
                return (Matcher)ScriptBytecodeAdapter.castToType(this.expectedStatusLine.get(), Matcher.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _statusLine_closure4.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _statusLine_closure4.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _statusLine_closure4.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _statusLine_closure4.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[]{};
                return new CallSiteArray(_statusLine_closure4.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _statusLine_closure4.$createCallSiteArray();
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
        return this;
    }

    public ResponseSpecification headers(Map expectedHeaders) {
        Reference<Map> expectedHeaders2 = new Reference<Map>(expectedHeaders);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[25].callStatic(AssertParameter.class, expectedHeaders2.get(), "expectedHeaders");
        callSiteArray[26].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, expectedHeaders2){
            private /* synthetic */ Reference expectedHeaders;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _headers_closure5.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.expectedHeaders = reference = expectedHeaders;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _headers_closure5.$getCallSiteArray();
                return callSiteArray[0].call(this.expectedHeaders.get(), new GeneratedClosure(this, this.getThisObject()){
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        CallSite[] callSiteArray = _headers_closure5_closure17.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                    }

                    public Object doCall(Object headerName, Object matcher) {
                        Reference<Object> headerName2 = new Reference<Object>(headerName);
                        CallSite[] callSiteArray = _headers_closure5_closure17.$getCallSiteArray();
                        if (matcher instanceof List) {
                            return callSiteArray[0].call(matcher, new GeneratedClosure(this, this.getThisObject(), headerName2){
                                private /* synthetic */ Reference headerName;
                                private static /* synthetic */ ClassInfo $staticClassInfo;
                                public static transient /* synthetic */ boolean __$stMC;
                                private static /* synthetic */ SoftReference $callSiteArray;
                                {
                                    Reference reference;
                                    CallSite[] callSiteArray = _headers_closure5_closure17_closure18.$getCallSiteArray();
                                    super(_outerInstance, _thisObject);
                                    this.headerName = reference = headerName;
                                }

                                public Object doCall(Object it) {
                                    CallSite[] callSiteArray = _headers_closure5_closure17_closure18.$getCallSiteArray();
                                    return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callConstructor(HeaderMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"headerName", this.headerName.get(), "matcher", it instanceof Matcher ? it : callSiteArray[3].callStatic(Matchers.class, it)})));
                                }

                                public Object getHeaderName() {
                                    CallSite[] callSiteArray = _headers_closure5_closure17_closure18.$getCallSiteArray();
                                    return this.headerName.get();
                                }

                                public Object doCall() {
                                    CallSite[] callSiteArray = _headers_closure5_closure17_closure18.$getCallSiteArray();
                                    return this.doCall(null);
                                }

                                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                    if (this.getClass() != _headers_closure5_closure17_closure18.class) {
                                        return ScriptBytecodeAdapter.initMetaClass(this);
                                    }
                                    ClassInfo classInfo = $staticClassInfo;
                                    if (classInfo == null) {
                                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                                    }
                                    return classInfo.getMetaClass();
                                }

                                public static /* synthetic */ void __$swapInit() {
                                    CallSite[] callSiteArray = _headers_closure5_closure17_closure18.$getCallSiteArray();
                                    $callSiteArray = null;
                                }

                                static {
                                    _headers_closure5_closure17_closure18.__$swapInit();
                                }

                                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                                    stringArray[0] = "leftShift";
                                    stringArray[1] = "headerAssertions";
                                    stringArray[2] = "<$constructor$>";
                                    stringArray[3] = "equalTo";
                                }

                                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                                    String[] stringArray = new String[4];
                                    _headers_closure5_closure17_closure18.$createCallSiteArray_1(stringArray);
                                    return new CallSiteArray(_headers_closure5_closure17_closure18.class, stringArray);
                                }

                                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                                    CallSiteArray callSiteArray;
                                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                        callSiteArray = _headers_closure5_closure17_closure18.$createCallSiteArray();
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
                        return callSiteArray[1].call(callSiteArray[2].callGroovyObjectGetProperty(this), callSiteArray[3].callConstructor(HeaderMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"headerName", headerName2.get(), "matcher", matcher instanceof Matcher ? matcher : callSiteArray[4].callStatic(Matchers.class, matcher)})));
                    }

                    public Object call(Object headerName, Object matcher) {
                        Reference<Object> headerName2 = new Reference<Object>(headerName);
                        CallSite[] callSiteArray = _headers_closure5_closure17.$getCallSiteArray();
                        return callSiteArray[5].callCurrent(this, headerName2.get(), matcher);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _headers_closure5_closure17.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _headers_closure5_closure17.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _headers_closure5_closure17.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "each";
                        stringArray[1] = "leftShift";
                        stringArray[2] = "headerAssertions";
                        stringArray[3] = "<$constructor$>";
                        stringArray[4] = "equalTo";
                        stringArray[5] = "doCall";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[6];
                        _headers_closure5_closure17.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_headers_closure5_closure17.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _headers_closure5_closure17.$createCallSiteArray();
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

            public Map getExpectedHeaders() {
                CallSite[] callSiteArray = _headers_closure5.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.expectedHeaders.get(), Map.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _headers_closure5.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _headers_closure5.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _headers_closure5.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _headers_closure5.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "each";
                return new CallSiteArray(_headers_closure5.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _headers_closure5.$createCallSiteArray();
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
        return this;
    }

    @Override
    public ResponseSpecification headers(String firstExpectedHeaderName, Object firstExpectedHeaderValue, Object ... expectedHeaders) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[27].callStatic(AssertParameter.class, firstExpectedHeaderName, "firstExpectedHeaderName");
        callSiteArray[28].callStatic(AssertParameter.class, firstExpectedHeaderValue, "firstExpectedHeaderValue");
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[29].callCurrent((GroovyObject)this, callSiteArray[30].call(MapCreator.class, callSiteArray[31].callGetProperty(MapCreator.CollisionStrategy.class), firstExpectedHeaderName, firstExpectedHeaderValue, expectedHeaders)), ResponseSpecification.class);
    }

    /*
     * WARNING - void declaration
     */
    public ResponseSpecification header(String headerName, Function mappingFunction, Matcher expectedValueMatcher) {
        void var3_3;
        void var2_2;
        Reference<String> headerName2 = new Reference<String>(headerName);
        Reference<void> mappingFunction2 = new Reference<void>(var2_2);
        Reference<void> expectedValueMatcher2 = new Reference<void>(var3_3);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[32].callStatic(AssertParameter.class, headerName2.get(), "Header name");
        callSiteArray[33].callStatic(AssertParameter.class, (Function)mappingFunction2.get(), "Mapping function");
        callSiteArray[34].callStatic(AssertParameter.class, (Matcher)expectedValueMatcher2.get(), "Hamcrest matcher");
        callSiteArray[35].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, expectedValueMatcher2, headerName2, mappingFunction2){
            private /* synthetic */ Reference expectedValueMatcher;
            private /* synthetic */ Reference headerName;
            private /* synthetic */ Reference mappingFunction;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                Reference reference3;
                CallSite[] callSiteArray = _header_closure6.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.expectedValueMatcher = reference3 = expectedValueMatcher;
                this.headerName = reference2 = headerName;
                this.mappingFunction = reference = mappingFunction;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _header_closure6.$getCallSiteArray();
                return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callConstructor(HeaderMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"headerName", this.headerName.get(), "mappingFunction", this.mappingFunction.get(), "matcher", this.expectedValueMatcher.get()})));
            }

            public Matcher getExpectedValueMatcher() {
                CallSite[] callSiteArray = _header_closure6.$getCallSiteArray();
                return (Matcher)ScriptBytecodeAdapter.castToType(this.expectedValueMatcher.get(), Matcher.class);
            }

            public String getHeaderName() {
                CallSite[] callSiteArray = _header_closure6.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.headerName.get(), String.class);
            }

            public Function getMappingFunction() {
                CallSite[] callSiteArray = _header_closure6.$getCallSiteArray();
                return (Function)ScriptBytecodeAdapter.castToType(this.mappingFunction.get(), Function.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _header_closure6.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _header_closure6.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _header_closure6.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _header_closure6.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "leftShift";
                stringArray[1] = "headerAssertions";
                stringArray[2] = "<$constructor$>";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _header_closure6.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_header_closure6.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _header_closure6.$createCallSiteArray();
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
        return this;
    }

    /*
     * WARNING - void declaration
     */
    public ResponseSpecification header(String headerName, Matcher expectedValueMatcher) {
        void var2_2;
        Reference<String> headerName2 = new Reference<String>(headerName);
        Reference<void> expectedValueMatcher2 = new Reference<void>(var2_2);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[36].callStatic(AssertParameter.class, headerName2.get(), "headerName");
        callSiteArray[37].callStatic(AssertParameter.class, (Matcher)expectedValueMatcher2.get(), "expectedValueMatcher");
        callSiteArray[38].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, expectedValueMatcher2, headerName2){
            private /* synthetic */ Reference expectedValueMatcher;
            private /* synthetic */ Reference headerName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _header_closure7.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.expectedValueMatcher = reference2 = expectedValueMatcher;
                this.headerName = reference = headerName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _header_closure7.$getCallSiteArray();
                return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callConstructor(HeaderMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"headerName", this.headerName.get(), "matcher", this.expectedValueMatcher.get()})));
            }

            public Matcher getExpectedValueMatcher() {
                CallSite[] callSiteArray = _header_closure7.$getCallSiteArray();
                return (Matcher)ScriptBytecodeAdapter.castToType(this.expectedValueMatcher.get(), Matcher.class);
            }

            public String getHeaderName() {
                CallSite[] callSiteArray = _header_closure7.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.headerName.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _header_closure7.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _header_closure7.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _header_closure7.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _header_closure7.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "leftShift";
                stringArray[1] = "headerAssertions";
                stringArray[2] = "<$constructor$>";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _header_closure7.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_header_closure7.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _header_closure7.$createCallSiteArray();
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
        return this;
    }

    @Override
    public ResponseSpecification header(String headerName, String expectedValue) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[39].callCurrent(this, headerName, callSiteArray[40].callStatic(Matchers.class, expectedValue)), ResponseSpecification.class);
    }

    public ResponseSpecification cookies(Map expectedCookies) {
        Reference<Map> expectedCookies2 = new Reference<Map>(expectedCookies);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[41].callStatic(AssertParameter.class, expectedCookies2.get(), "expectedCookies");
        callSiteArray[42].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, expectedCookies2){
            private /* synthetic */ Reference expectedCookies;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _cookies_closure8.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.expectedCookies = reference = expectedCookies;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _cookies_closure8.$getCallSiteArray();
                return callSiteArray[0].call(this.expectedCookies.get(), new GeneratedClosure(this, this.getThisObject()){
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        CallSite[] callSiteArray = _cookies_closure8_closure19.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                    }

                    public Object doCall(Object cookieName, Object matcher) {
                        Reference<Object> cookieName2 = new Reference<Object>(cookieName);
                        CallSite[] callSiteArray = _cookies_closure8_closure19.$getCallSiteArray();
                        if (matcher instanceof List) {
                            return callSiteArray[0].call(matcher, new GeneratedClosure(this, this.getThisObject(), cookieName2){
                                private /* synthetic */ Reference cookieName;
                                private static /* synthetic */ ClassInfo $staticClassInfo;
                                public static transient /* synthetic */ boolean __$stMC;
                                private static /* synthetic */ SoftReference $callSiteArray;
                                {
                                    Reference reference;
                                    CallSite[] callSiteArray = _cookies_closure8_closure19_closure20.$getCallSiteArray();
                                    super(_outerInstance, _thisObject);
                                    this.cookieName = reference = cookieName;
                                }

                                public Object doCall(Object it) {
                                    CallSite[] callSiteArray = _cookies_closure8_closure19_closure20.$getCallSiteArray();
                                    return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callConstructor(CookieMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"cookieName", this.cookieName.get(), "matcher", it instanceof Matcher ? it : callSiteArray[3].callStatic(Matchers.class, it)})));
                                }

                                public Object getCookieName() {
                                    CallSite[] callSiteArray = _cookies_closure8_closure19_closure20.$getCallSiteArray();
                                    return this.cookieName.get();
                                }

                                public Object doCall() {
                                    CallSite[] callSiteArray = _cookies_closure8_closure19_closure20.$getCallSiteArray();
                                    return this.doCall(null);
                                }

                                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                    if (this.getClass() != _cookies_closure8_closure19_closure20.class) {
                                        return ScriptBytecodeAdapter.initMetaClass(this);
                                    }
                                    ClassInfo classInfo = $staticClassInfo;
                                    if (classInfo == null) {
                                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                                    }
                                    return classInfo.getMetaClass();
                                }

                                public static /* synthetic */ void __$swapInit() {
                                    CallSite[] callSiteArray = _cookies_closure8_closure19_closure20.$getCallSiteArray();
                                    $callSiteArray = null;
                                }

                                static {
                                    _cookies_closure8_closure19_closure20.__$swapInit();
                                }

                                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                                    stringArray[0] = "leftShift";
                                    stringArray[1] = "cookieAssertions";
                                    stringArray[2] = "<$constructor$>";
                                    stringArray[3] = "equalTo";
                                }

                                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                                    String[] stringArray = new String[4];
                                    _cookies_closure8_closure19_closure20.$createCallSiteArray_1(stringArray);
                                    return new CallSiteArray(_cookies_closure8_closure19_closure20.class, stringArray);
                                }

                                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                                    CallSiteArray callSiteArray;
                                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                        callSiteArray = _cookies_closure8_closure19_closure20.$createCallSiteArray();
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
                        return callSiteArray[1].call(callSiteArray[2].callGroovyObjectGetProperty(this), callSiteArray[3].callConstructor(CookieMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"cookieName", cookieName2.get(), "matcher", matcher instanceof Matcher ? matcher : callSiteArray[4].callStatic(Matchers.class, matcher)})));
                    }

                    public Object call(Object cookieName, Object matcher) {
                        Reference<Object> cookieName2 = new Reference<Object>(cookieName);
                        CallSite[] callSiteArray = _cookies_closure8_closure19.$getCallSiteArray();
                        return callSiteArray[5].callCurrent(this, cookieName2.get(), matcher);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _cookies_closure8_closure19.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _cookies_closure8_closure19.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _cookies_closure8_closure19.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "each";
                        stringArray[1] = "leftShift";
                        stringArray[2] = "cookieAssertions";
                        stringArray[3] = "<$constructor$>";
                        stringArray[4] = "equalTo";
                        stringArray[5] = "doCall";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[6];
                        _cookies_closure8_closure19.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_cookies_closure8_closure19.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _cookies_closure8_closure19.$createCallSiteArray();
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

            public Map getExpectedCookies() {
                CallSite[] callSiteArray = _cookies_closure8.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.expectedCookies.get(), Map.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _cookies_closure8.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _cookies_closure8.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _cookies_closure8.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _cookies_closure8.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "each";
                return new CallSiteArray(_cookies_closure8.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _cookies_closure8.$createCallSiteArray();
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
        return this;
    }

    @Override
    public ResponseSpecification cookies(String firstExpectedCookieName, Object firstExpectedCookieValue, Object ... expectedCookieNameValuePairs) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[43].callStatic(AssertParameter.class, firstExpectedCookieName, "firstExpectedCookieName");
        callSiteArray[44].callStatic(AssertParameter.class, firstExpectedCookieValue, "firstExpectedCookieValue");
        callSiteArray[45].callStatic(AssertParameter.class, expectedCookieNameValuePairs, "expectedCookieNameValuePairs");
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[46].callCurrent((GroovyObject)this, callSiteArray[47].call(MapCreator.class, callSiteArray[48].callGetProperty(MapCreator.CollisionStrategy.class), firstExpectedCookieName, firstExpectedCookieValue, expectedCookieNameValuePairs)), ResponseSpecification.class);
    }

    /*
     * WARNING - void declaration
     */
    public ResponseSpecification cookie(String cookieName, Matcher expectedValueMatcher) {
        void var2_2;
        Reference<String> cookieName2 = new Reference<String>(cookieName);
        Reference<void> expectedValueMatcher2 = new Reference<void>(var2_2);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[49].callStatic(AssertParameter.class, cookieName2.get(), "cookieName");
        callSiteArray[50].callStatic(AssertParameter.class, (Matcher)expectedValueMatcher2.get(), "expectedValueMatcher");
        callSiteArray[51].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, expectedValueMatcher2, cookieName2){
            private /* synthetic */ Reference expectedValueMatcher;
            private /* synthetic */ Reference cookieName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _cookie_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.expectedValueMatcher = reference2 = expectedValueMatcher;
                this.cookieName = reference = cookieName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _cookie_closure9.$getCallSiteArray();
                return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callConstructor(CookieMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"cookieName", this.cookieName.get(), "matcher", this.expectedValueMatcher.get()})));
            }

            public Matcher getExpectedValueMatcher() {
                CallSite[] callSiteArray = _cookie_closure9.$getCallSiteArray();
                return (Matcher)ScriptBytecodeAdapter.castToType(this.expectedValueMatcher.get(), Matcher.class);
            }

            public String getCookieName() {
                CallSite[] callSiteArray = _cookie_closure9.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.cookieName.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _cookie_closure9.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _cookie_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _cookie_closure9.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _cookie_closure9.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "leftShift";
                stringArray[1] = "cookieAssertions";
                stringArray[2] = "<$constructor$>";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _cookie_closure9.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_cookie_closure9.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _cookie_closure9.$createCallSiteArray();
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
        return this;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public ResponseSpecification cookie(String cookieName, DetailedCookieMatcher detailedCookieMatcher) {
        void var2_2;
        Reference<String> cookieName2 = new Reference<String>(cookieName);
        Reference<void> detailedCookieMatcher2 = new Reference<void>(var2_2);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[52].callStatic(AssertParameter.class, cookieName2.get(), "cookieName");
        callSiteArray[53].callStatic(AssertParameter.class, (DetailedCookieMatcher)detailedCookieMatcher2.get(), "cookieMatcher");
        callSiteArray[54].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, detailedCookieMatcher2, cookieName2){
            private /* synthetic */ Reference detailedCookieMatcher;
            private /* synthetic */ Reference cookieName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.detailedCookieMatcher = reference2 = detailedCookieMatcher;
                this.cookieName = reference = cookieName;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callConstructor(DetailedCookieAssertion.class, ScriptBytecodeAdapter.createMap(new Object[]{"cookieName", this.cookieName.get(), "matcher", this.detailedCookieMatcher.get()})));
            }

            public DetailedCookieMatcher getDetailedCookieMatcher() {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                return (DetailedCookieMatcher)ScriptBytecodeAdapter.castToType(this.detailedCookieMatcher.get(), DetailedCookieMatcher.class);
            }

            public String getCookieName() {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.cookieName.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _cookie_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _cookie_closure10.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _cookie_closure10.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "leftShift";
                stringArray[1] = "cookieAssertions";
                stringArray[2] = "<$constructor$>";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _cookie_closure10.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_cookie_closure10.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _cookie_closure10.$createCallSiteArray();
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
        return this;
    }

    @Override
    public ResponseSpecification cookie(String cookieName) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[55].callStatic(AssertParameter.class, cookieName, "cookieName");
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[56].callCurrent(this, cookieName, callSiteArray[57].call(Matchers.class)), ResponseSpecification.class);
    }

    @Override
    public ResponseSpecification cookie(String cookieName, Object expectedValue) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[58].callCurrent(this, cookieName, callSiteArray[59].callStatic(Matchers.class, expectedValue)), ResponseSpecification.class);
    }

    @Override
    public ResponseSpecification spec(ResponseSpecification responseSpecificationToMerge) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[60].call(SpecificationMerger.class, this, responseSpecificationToMerge);
        return this;
    }

    public ResponseSpecification specification(ResponseSpecification responseSpecificationToMerge) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[61].callCurrent((GroovyObject)this, responseSpecificationToMerge), ResponseSpecification.class);
    }

    @Override
    public ResponseSpecification statusLine(String expectedStatusLine) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[62].callCurrent((GroovyObject)this, callSiteArray[63].callStatic(Matchers.class, expectedStatusLine)), ResponseSpecification.class);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public ResponseSpecification body(String key, List<Argument> arguments, Matcher matcher, Object ... additionalKeyMatcherPairs) {
        void var3_3;
        void var2_2;
        Reference<String> key2 = new Reference<String>(key);
        Reference<void> arguments2 = new Reference<void>(var2_2);
        Reference<void> matcher2 = new Reference<void>(var3_3);
        Reference<Object[]> additionalKeyMatcherPairs2 = new Reference<Object[]>(additionalKeyMatcherPairs);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[64].callStatic(AssertParameter.class, key2.get(), "key");
        callSiteArray[65].callStatic(AssertParameter.class, (Matcher)matcher2.get(), "matcher");
        callSiteArray[66].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, arguments2, matcher2, key2, additionalKeyMatcherPairs2){
            private /* synthetic */ Reference arguments;
            private /* synthetic */ Reference matcher;
            private /* synthetic */ Reference key;
            private /* synthetic */ Reference additionalKeyMatcherPairs;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                CallSite[] callSiteArray = _body_closure11.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.arguments = reference4 = arguments;
                this.matcher = reference3 = matcher;
                this.key = reference2 = key;
                this.additionalKeyMatcherPairs = reference = additionalKeyMatcherPairs;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _body_closure11.$getCallSiteArray();
                callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callConstructor(BodyMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", callSiteArray[3].callCurrent(this, callSiteArray[4].callCurrent((GroovyObject)this, this.key.get()), this.arguments.get()), "matcher", this.matcher.get(), "rpr", callSiteArray[5].callGroovyObjectGetProperty(this)})));
                if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                    if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[6].callGetPropertySafe(this.additionalKeyMatcherPairs.get()), 0)) {
                        Object pairs = callSiteArray[7].call(MapCreator.class, callSiteArray[8].callGetProperty(MapCreator.CollisionStrategy.class), this.additionalKeyMatcherPairs.get());
                        return callSiteArray[9].call(pairs, new GeneratedClosure(this, this.getThisObject()){
                            private static /* synthetic */ ClassInfo $staticClassInfo;
                            public static transient /* synthetic */ boolean __$stMC;
                            private static /* synthetic */ SoftReference $callSiteArray;
                            {
                                CallSite[] callSiteArray = _body_closure11_closure21.$getCallSiteArray();
                                super(_outerInstance, _thisObject);
                            }

                            public Object doCall(Object matchingKey, Object matchingValue) {
                                CallSite[] callSiteArray = _body_closure11_closure21.$getCallSiteArray();
                                Reference<Object> keyWithRoot = new Reference<Object>(null);
                                String cfr_ignored_0 = (String)keyWithRoot.get();
                                Object hamcrestMatcher = null;
                                if (matchingKey instanceof List) {
                                    Object object;
                                    Object object2 = callSiteArray[0].callCurrent(this, callSiteArray[1].callCurrent((GroovyObject)this, ""), matchingKey);
                                    keyWithRoot.set(((String)ScriptBytecodeAdapter.castToType(object2, String.class)));
                                    hamcrestMatcher = object = matchingValue;
                                } else if (matchingValue instanceof MapCreator.ArgsAndValue) {
                                    Object object;
                                    String mergedPath = (String)ScriptBytecodeAdapter.castToType(callSiteArray[2].callCurrent((GroovyObject)this, matchingKey), String.class);
                                    Object object3 = callSiteArray[3].callCurrent(this, mergedPath, callSiteArray[4].callGetProperty(matchingValue));
                                    keyWithRoot.set(((String)ScriptBytecodeAdapter.castToType(object3, String.class)));
                                    hamcrestMatcher = object = callSiteArray[5].callGetProperty(matchingValue);
                                } else {
                                    Object object;
                                    Object object4 = callSiteArray[6].callCurrent((GroovyObject)this, matchingKey);
                                    keyWithRoot.set(((String)ScriptBytecodeAdapter.castToType(object4, String.class)));
                                    hamcrestMatcher = object = matchingValue;
                                }
                                if (hamcrestMatcher instanceof List) {
                                    return callSiteArray[7].call(hamcrestMatcher, new GeneratedClosure(this, this.getThisObject(), keyWithRoot){
                                        private /* synthetic */ Reference keyWithRoot;
                                        private static /* synthetic */ ClassInfo $staticClassInfo;
                                        public static transient /* synthetic */ boolean __$stMC;
                                        private static /* synthetic */ SoftReference $callSiteArray;
                                        {
                                            Reference reference;
                                            CallSite[] callSiteArray = _body_closure11_closure21_closure22.$getCallSiteArray();
                                            super(_outerInstance, _thisObject);
                                            this.keyWithRoot = reference = keyWithRoot;
                                        }

                                        public Object doCall(Object m) {
                                            CallSite[] callSiteArray = _body_closure11_closure21_closure22.$getCallSiteArray();
                                            Object keyToUse = null;
                                            Object matcherToUse = null;
                                            if (m instanceof MapCreator.ArgsAndValue) {
                                                Object object;
                                                Object object2;
                                                keyToUse = object2 = callSiteArray[0].callCurrent(this, this.keyWithRoot.get(), callSiteArray[1].callGetProperty(m));
                                                matcherToUse = object = callSiteArray[2].callGetProperty(m);
                                            } else {
                                                Object object;
                                                T t = this.keyWithRoot.get();
                                                keyToUse = t;
                                                matcherToUse = object = m;
                                            }
                                            return callSiteArray[3].call(callSiteArray[4].callGroovyObjectGetProperty(this), callSiteArray[5].callConstructor(BodyMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", keyToUse, "matcher", matcherToUse, "rpr", callSiteArray[6].callGroovyObjectGetProperty(this)})));
                                        }

                                        public String getKeyWithRoot() {
                                            CallSite[] callSiteArray = _body_closure11_closure21_closure22.$getCallSiteArray();
                                            return (String)ScriptBytecodeAdapter.castToType(this.keyWithRoot.get(), String.class);
                                        }

                                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                            if (this.getClass() != _body_closure11_closure21_closure22.class) {
                                                return ScriptBytecodeAdapter.initMetaClass(this);
                                            }
                                            ClassInfo classInfo = $staticClassInfo;
                                            if (classInfo == null) {
                                                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                                            }
                                            return classInfo.getMetaClass();
                                        }

                                        public static /* synthetic */ void __$swapInit() {
                                            CallSite[] callSiteArray = _body_closure11_closure21_closure22.$getCallSiteArray();
                                            $callSiteArray = null;
                                        }

                                        static {
                                            _body_closure11_closure21_closure22.__$swapInit();
                                        }

                                        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                                            stringArray[0] = "applyArguments";
                                            stringArray[1] = "args";
                                            stringArray[2] = "value";
                                            stringArray[3] = "add";
                                            stringArray[4] = "bodyMatchers";
                                            stringArray[5] = "<$constructor$>";
                                            stringArray[6] = "rpr";
                                        }

                                        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                                            String[] stringArray = new String[7];
                                            _body_closure11_closure21_closure22.$createCallSiteArray_1(stringArray);
                                            return new CallSiteArray(_body_closure11_closure21_closure22.class, stringArray);
                                        }

                                        private static /* synthetic */ CallSite[] $getCallSiteArray() {
                                            CallSiteArray callSiteArray;
                                            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                                callSiteArray = _body_closure11_closure21_closure22.$createCallSiteArray();
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
                                return callSiteArray[8].call(callSiteArray[9].callGroovyObjectGetProperty(this), callSiteArray[10].callConstructor(BodyMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"key", keyWithRoot.get(), "matcher", hamcrestMatcher, "rpr", callSiteArray[11].callGroovyObjectGetProperty(this)})));
                            }

                            public Object call(Object matchingKey, Object matchingValue) {
                                CallSite[] callSiteArray = _body_closure11_closure21.$getCallSiteArray();
                                return callSiteArray[12].callCurrent(this, matchingKey, matchingValue);
                            }

                            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                if (this.getClass() != _body_closure11_closure21.class) {
                                    return ScriptBytecodeAdapter.initMetaClass(this);
                                }
                                ClassInfo classInfo = $staticClassInfo;
                                if (classInfo == null) {
                                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                                }
                                return classInfo.getMetaClass();
                            }

                            public static /* synthetic */ void __$swapInit() {
                                CallSite[] callSiteArray = _body_closure11_closure21.$getCallSiteArray();
                                $callSiteArray = null;
                            }

                            static {
                                _body_closure11_closure21.__$swapInit();
                            }

                            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                                stringArray[0] = "applyArguments";
                                stringArray[1] = "mergeKeyWithRootPath";
                                stringArray[2] = "mergeKeyWithRootPath";
                                stringArray[3] = "applyArguments";
                                stringArray[4] = "args";
                                stringArray[5] = "value";
                                stringArray[6] = "mergeKeyWithRootPath";
                                stringArray[7] = "each";
                                stringArray[8] = "add";
                                stringArray[9] = "bodyMatchers";
                                stringArray[10] = "<$constructor$>";
                                stringArray[11] = "rpr";
                                stringArray[12] = "doCall";
                            }

                            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                                String[] stringArray = new String[13];
                                _body_closure11_closure21.$createCallSiteArray_1(stringArray);
                                return new CallSiteArray(_body_closure11_closure21.class, stringArray);
                            }

                            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                                CallSiteArray callSiteArray;
                                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                    callSiteArray = _body_closure11_closure21.$createCallSiteArray();
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
                    return null;
                }
                if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[10].callGetPropertySafe(this.additionalKeyMatcherPairs.get()), 0)) {
                    Object pairs = callSiteArray[11].call(MapCreator.class, callSiteArray[12].callGetProperty(MapCreator.CollisionStrategy.class), this.additionalKeyMatcherPairs.get());
                    return callSiteArray[13].call(pairs, new /* invalid duplicate definition of identical inner class */);
                }
                return null;
            }

            public List getArguments() {
                CallSite[] callSiteArray = _body_closure11.$getCallSiteArray();
                return (List)ScriptBytecodeAdapter.castToType(this.arguments.get(), List.class);
            }

            public Matcher getMatcher() {
                CallSite[] callSiteArray = _body_closure11.$getCallSiteArray();
                return (Matcher)ScriptBytecodeAdapter.castToType(this.matcher.get(), Matcher.class);
            }

            public String getKey() {
                CallSite[] callSiteArray = _body_closure11.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.key.get(), String.class);
            }

            public Object[] getAdditionalKeyMatcherPairs() {
                CallSite[] callSiteArray = _body_closure11.$getCallSiteArray();
                return (Object[])ScriptBytecodeAdapter.castToType(this.additionalKeyMatcherPairs.get(), Object[].class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _body_closure11.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _body_closure11.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _body_closure11.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _body_closure11.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "add";
                stringArray[1] = "bodyMatchers";
                stringArray[2] = "<$constructor$>";
                stringArray[3] = "applyArguments";
                stringArray[4] = "mergeKeyWithRootPath";
                stringArray[5] = "rpr";
                stringArray[6] = "length";
                stringArray[7] = "createMapFromObjects";
                stringArray[8] = "MERGE";
                stringArray[9] = "each";
                stringArray[10] = "length";
                stringArray[11] = "createMapFromObjects";
                stringArray[12] = "MERGE";
                stringArray[13] = "each";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[14];
                _body_closure11.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_body_closure11.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _body_closure11.$createCallSiteArray();
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
        return this;
    }

    @Override
    public ResponseLogSpecification log() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseLogSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[67].callConstructor(ResponseLogSpecificationImpl.class, ScriptBytecodeAdapter.createMap(new Object[]{"responseSpecification", this, "logRepository", this.logRepository})), ResponseLogSpecification.class);
    }

    @Override
    public ResponseSpecificationImpl logDetail(LogDetail logDetail) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        LogDetail logDetail2 = logDetail;
        this.responseLogDetail = (LogDetail)((Object)ScriptBytecodeAdapter.castToType((Object)logDetail2, LogDetail.class));
        return this;
    }

    @Override
    public ResponseSpecification onFailMessage(String message) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        String string = message;
        this.onFailMessage = (String)ScriptBytecodeAdapter.castToType(string, String.class);
        return this;
    }

    @Override
    public LogDetail getLogDetail() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this.responseLogDetail;
    }

    @Override
    public RequestSender when() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this.requestSpecification;
    }

    @Override
    public ResponseSpecification response() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public RequestSpecification given() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this.requestSpecification;
    }

    @Override
    public ResponseSpecification that() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public RequestSpecification request() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this.requestSpecification;
    }

    @Override
    public ResponseSpecification parser(String contentType, Parser parser) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[68].call(this.rpr, contentType, (Object)parser);
        return this;
    }

    @Override
    public ResponseSpecification and() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public RequestSpecification with() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (RequestSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[69].callCurrent(this), RequestSpecification.class);
        }
        return this.given();
    }

    @Override
    public ResponseSpecification then() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public ResponseSpecification expect() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this;
    }

    @Override
    public ResponseSpecification rootPath(String rootPath) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[70].callCurrent(this, rootPath, ScriptBytecodeAdapter.createList(new Object[0])), ResponseSpecification.class);
    }

    @Override
    public ResponseSpecification noRootPath() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[71].callCurrent((GroovyObject)this, EMPTY), ResponseSpecification.class);
        }
        return this.rootPath(EMPTY);
    }

    @Override
    public ResponseSpecification appendRootPath(String pathToAppend) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[72].callCurrent(this, pathToAppend, ScriptBytecodeAdapter.createList(new Object[0])), ResponseSpecification.class);
    }

    @Override
    public ResponseSpecification appendRootPath(String pathToAppend, List<Argument> arguments) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[73].callStatic(AssertParameter.class, pathToAppend, "Path to append to root path");
        callSiteArray[74].callStatic(AssertParameter.class, arguments, "Arguments for path to append");
        Object mergedPath = null;
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object;
            mergedPath = object = callSiteArray[75].callCurrent((GroovyObject)this, pathToAppend);
        } else {
            String string = this.mergeKeyWithRootPath(pathToAppend);
            mergedPath = string;
        }
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[76].callCurrent(this, mergedPath, arguments), ResponseSpecification.class);
    }

    @Override
    public ResponseSpecification detachRootPath(String pathToDetach) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[77].callStatic(AssertParameter.class, pathToDetach, "Path to detach from root path");
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            callSiteArray[78].callCurrent((GroovyObject)this, "detach path");
        } else {
            this.throwIllegalStateExceptionIfRootPathIsNotDefined("detach path");
        }
        Object object = callSiteArray[79].call(StringUtils.class, pathToDetach);
        pathToDetach = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[80].call((Object)this.bodyRootPath, pathToDetach))) {
            throw (Throwable)callSiteArray[81].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{pathToDetach, this.bodyRootPath, pathToDetach}, new String[]{"Cannot detach path '", "' since root path '", "' doesn't end with '", "'."}));
        }
        Object object2 = callSiteArray[82].call(StringUtils.class, this.bodyRootPath, pathToDetach);
        this.bodyRootPath = (String)ScriptBytecodeAdapter.castToType(object2, String.class);
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[83].call((Object)this.bodyRootPath, "."))) {
            Object object3 = callSiteArray[84].call(this.bodyRootPath, 0, callSiteArray[85].call(callSiteArray[86].call(this.bodyRootPath), 1));
            this.bodyRootPath = (String)ScriptBytecodeAdapter.castToType(object3, String.class);
        }
        return this;
    }

    @Override
    public ResponseSpecification rootPath(String rootPath, List<Argument> arguments) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[87].callStatic(AssertParameter.class, rootPath, "Root path");
        callSiteArray[88].callStatic(AssertParameter.class, arguments, "Arguments");
        Object object = callSiteArray[89].callCurrent(this, rootPath, arguments);
        this.bodyRootPath = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        return this;
    }

    @Override
    public ResponseSpecification root(String rootPath, List<Argument> arguments) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return (ResponseSpecification)ScriptBytecodeAdapter.castToType(callSiteArray[90].callCurrent(this, rootPath, arguments), ResponseSpecification.class);
    }

    public boolean hasBodyAssertionsDefined() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(callSiteArray[91].call(this.bodyMatchers));
    }

    public boolean hasAssertionsDefined() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return DefaultTypeTransformation.booleanUnbox(callSiteArray[92].callCurrent(this)) || !DefaultTypeTransformation.booleanUnbox(callSiteArray[93].call(this.headerAssertions)) || !DefaultTypeTransformation.booleanUnbox(callSiteArray[94].call(this.cookieAssertions)) || ScriptBytecodeAdapter.compareNotEqual(this.expectedStatusCode, null) || ScriptBytecodeAdapter.compareNotEqual(this.expectedStatusLine, null) || ScriptBytecodeAdapter.compareNotEqual(this.contentType, null) || ScriptBytecodeAdapter.compareNotEqual(this.expectedResponseTime, null);
        }
        return this.hasBodyAssertionsDefined() || !DefaultTypeTransformation.booleanUnbox(callSiteArray[95].call(this.headerAssertions)) || !DefaultTypeTransformation.booleanUnbox(callSiteArray[96].call(this.cookieAssertions)) || ScriptBytecodeAdapter.compareNotEqual(this.expectedStatusCode, null) || ScriptBytecodeAdapter.compareNotEqual(this.expectedStatusLine, null) || ScriptBytecodeAdapter.compareNotEqual(this.contentType, null) || ScriptBytecodeAdapter.compareNotEqual(this.expectedResponseTime, null);
    }

    @Override
    public ResponseSpecification defaultParser(Parser parser) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[97].callStatic(AssertParameter.class, (Object)parser, "Parser");
        Parser parser2 = parser;
        ScriptBytecodeAdapter.setProperty((Object)parser2, null, this.rpr, "defaultParser");
        return this;
    }

    @Override
    public ResponseSpecification contentType(ContentType contentType) {
        Reference<ContentType> contentType2 = new Reference<ContentType>(contentType);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[98].callStatic(AssertParameter.class, (Object)contentType2.get(), "contentType");
        callSiteArray[99].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, contentType2){
            private /* synthetic */ Reference contentType;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _contentType_closure12.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.contentType = reference = contentType;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _contentType_closure12.$getCallSiteArray();
                Object t = this.contentType.get();
                ScriptBytecodeAdapter.setGroovyObjectProperty(t, ResponseSpecificationImpl.class, (GroovyObject)this.getThisObject(), "contentType");
                return t;
            }

            public ContentType getContentType() {
                CallSite[] callSiteArray = _contentType_closure12.$getCallSiteArray();
                return (ContentType)((Object)ScriptBytecodeAdapter.castToType(this.contentType.get(), ContentType.class));
            }

            public Object doCall() {
                CallSite[] callSiteArray = _contentType_closure12.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _contentType_closure12.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _contentType_closure12.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _contentType_closure12.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[]{};
                return new CallSiteArray(_contentType_closure12.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _contentType_closure12.$createCallSiteArray();
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
        return this;
    }

    @Override
    public ResponseSpecification contentType(String contentType) {
        Reference<String> contentType2 = new Reference<String>(contentType);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[100].callStatic(AssertParameter.class, contentType2.get(), "contentType");
        callSiteArray[101].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, contentType2){
            private /* synthetic */ Reference contentType;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _contentType_closure13.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.contentType = reference = contentType;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _contentType_closure13.$getCallSiteArray();
                Object t = this.contentType.get();
                ScriptBytecodeAdapter.setGroovyObjectProperty(t, ResponseSpecificationImpl.class, (GroovyObject)this.getThisObject(), "contentType");
                return t;
            }

            public String getContentType() {
                CallSite[] callSiteArray = _contentType_closure13.$getCallSiteArray();
                return (String)ScriptBytecodeAdapter.castToType(this.contentType.get(), String.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _contentType_closure13.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _contentType_closure13.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _contentType_closure13.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _contentType_closure13.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[]{};
                return new CallSiteArray(_contentType_closure13.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _contentType_closure13.$createCallSiteArray();
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
        return this;
    }

    @Override
    public ResponseSpecification contentType(Matcher<? super String> contentType) {
        Reference<Matcher<? super String>> contentType2 = new Reference<Matcher<? super String>>(contentType);
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[102].callStatic(AssertParameter.class, contentType2.get(), "contentType");
        callSiteArray[103].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, contentType2){
            private /* synthetic */ Reference contentType;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _contentType_closure14.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.contentType = reference = contentType;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _contentType_closure14.$getCallSiteArray();
                Object t = this.contentType.get();
                ScriptBytecodeAdapter.setGroovyObjectProperty(t, ResponseSpecificationImpl.class, (GroovyObject)this.getThisObject(), "contentType");
                return t;
            }

            public Matcher getContentType() {
                CallSite[] callSiteArray = _contentType_closure14.$getCallSiteArray();
                return (Matcher)ScriptBytecodeAdapter.castToType(this.contentType.get(), Matcher.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _contentType_closure14.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _contentType_closure14.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _contentType_closure14.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _contentType_closure14.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[]{};
                return new CallSiteArray(_contentType_closure14.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _contentType_closure14.$createCallSiteArray();
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
        return this;
    }

    @Override
    public Matcher<Integer> getStatusCode() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this.expectedStatusCode;
    }

    @Override
    public Matcher<String> getStatusLine() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this.expectedStatusLine;
    }

    @Override
    public boolean hasHeaderAssertions() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return !DefaultTypeTransformation.booleanUnbox(callSiteArray[104].call(this.headerAssertions));
    }

    @Override
    public boolean hasCookieAssertions() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return !DefaultTypeTransformation.booleanUnbox(callSiteArray[105].call(this.cookieAssertions));
    }

    @Override
    public String getResponseContentType() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareNotEqual(callSiteArray[106].callGroovyObjectGetProperty(this), null) ? callSiteArray[107].call(callSiteArray[108].callGroovyObjectGetProperty(this)) : callSiteArray[109].call(callSiteArray[110].callGetProperty(ContentType.class)), String.class);
        }
        return (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareNotEqual(callSiteArray[111].callGroovyObjectGetProperty(this), null) ? callSiteArray[112].call(callSiteArray[113].callGroovyObjectGetProperty(this)) : callSiteArray[114].call(callSiteArray[115].callGetProperty(ContentType.class)), String.class);
    }

    @Override
    public String getRootPath() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return this.bodyRootPath;
    }

    private String applyArguments(String path, List<Argument> arguments) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[116].callSafe(arguments), 0)) {
                Object numberArgsOfAfterMerge = callSiteArray[117].call(StringUtils.class, path, "%s");
                if (ScriptBytecodeAdapter.compareGreaterThan(numberArgsOfAfterMerge, callSiteArray[118].call(arguments))) {
                    Object object = callSiteArray[119].callConstructor(ArrayList.class, arguments);
                    arguments = (List)ScriptBytecodeAdapter.castToType(object, List.class);
                    int i = 0;
                    while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[120].call(numberArgsOfAfterMerge, callSiteArray[121].call(arguments)))) {
                        callSiteArray[122].call((Object)arguments, callSiteArray[123].callConstructor(Argument.class, "%s"));
                        int n = i;
                        i = DefaultTypeTransformation.intUnbox(callSiteArray[124].call(n));
                    }
                }
                Object object = callSiteArray[125].call(String.class, path, callSiteArray[126].call(callSiteArray[127].call((Object)arguments, new GeneratedClosure(this, this){
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        CallSite[] callSiteArray = _applyArguments_closure15.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                    }

                    public Object doCall(Object it) {
                        CallSite[] callSiteArray = _applyArguments_closure15.$getCallSiteArray();
                        return callSiteArray[0].call(it);
                    }

                    public Object doCall() {
                        CallSite[] callSiteArray = _applyArguments_closure15.$getCallSiteArray();
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _applyArguments_closure15.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _applyArguments_closure15.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _applyArguments_closure15.__$swapInit();
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[1];
                        stringArray[0] = "getArgument";
                        return new CallSiteArray(_applyArguments_closure15.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _applyArguments_closure15.$createCallSiteArray();
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
                }), (Object)new Object[DefaultTypeTransformation.intUnbox(callSiteArray[128].call(arguments))]));
                path = (String)ScriptBytecodeAdapter.castToType(object, String.class);
            }
        } else if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[129].callSafe(arguments), 0)) {
            Object numberArgsOfAfterMerge = callSiteArray[130].call(StringUtils.class, path, "%s");
            if (ScriptBytecodeAdapter.compareGreaterThan(numberArgsOfAfterMerge, callSiteArray[131].call(arguments))) {
                Object object = callSiteArray[132].callConstructor(ArrayList.class, arguments);
                arguments = (List)ScriptBytecodeAdapter.castToType(object, List.class);
                int i = 0;
                while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[133].call(numberArgsOfAfterMerge, callSiteArray[134].call(arguments)))) {
                    callSiteArray[135].call((Object)arguments, callSiteArray[136].callConstructor(Argument.class, "%s"));
                    int n = i;
                    int cfr_ignored_0 = n + 1;
                }
            }
            Object object = callSiteArray[137].call(String.class, path, callSiteArray[138].call(callSiteArray[139].call((Object)arguments, new /* invalid duplicate definition of identical inner class */), (Object)new Object[DefaultTypeTransformation.intUnbox(callSiteArray[140].call(arguments))]));
            path = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        }
        return path;
    }

    private String mergeKeyWithRootPath(String key) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(this.bodyRootPath, null) && ScriptBytecodeAdapter.compareNotEqual(this.bodyRootPath, EMPTY)) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[141].call((Object)this.bodyRootPath, DOT)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[142].call((Object)key, DOT))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[143].call((Object)this.bodyRootPath, callSiteArray[144].callStatic(StringUtils.class, key, DOT)), String.class);
                }
                if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[145].call((Object)this.bodyRootPath, DOT)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[146].call((Object)key, DOT)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[147].call((Object)key, SAFE_REF)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[148].call((Object)key, "[")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[149].call((Object)key, SAFE_INDEX)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[150].call((Object)key, SPREAD_REF))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[151].call(callSiteArray[152].call((Object)this.bodyRootPath, DOT), key), String.class);
                }
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[153].call((Object)this.bodyRootPath, key), String.class);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(this.bodyRootPath, null) && ScriptBytecodeAdapter.compareNotEqual(this.bodyRootPath, EMPTY)) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[154].call((Object)this.bodyRootPath, DOT)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[155].call((Object)key, DOT))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[156].call((Object)this.bodyRootPath, callSiteArray[157].callStatic(StringUtils.class, key, DOT)), String.class);
            }
            if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[158].call((Object)this.bodyRootPath, DOT)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[159].call((Object)key, DOT)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[160].call((Object)key, SAFE_REF)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[161].call((Object)key, "[")) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[162].call((Object)key, SAFE_INDEX)) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[163].call((Object)key, SPREAD_REF))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[164].call(callSiteArray[165].call((Object)this.bodyRootPath, DOT), key), String.class);
            }
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[166].call((Object)this.bodyRootPath, key), String.class);
        }
        return key;
    }

    public void throwIllegalStateExceptionIfRootPathIsNotDefined(String description) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[167].callGroovyObjectGetProperty(this), null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[168].call(callSiteArray[169].callGroovyObjectGetProperty(this)))) {
                throw (Throwable)callSiteArray[170].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{description}, new String[]{"Cannot ", " when root path is empty"}));
            }
        } else if (ScriptBytecodeAdapter.compareEqual(callSiteArray[171].callGroovyObjectGetProperty(this), null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[172].call(callSiteArray[173].callGroovyObjectGetProperty(this)))) {
            throw (Throwable)callSiteArray[174].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{description}, new String[]{"Cannot ", " when root path is empty"}));
        }
    }

    public Object forceDisableEagerAssert() {
        boolean bl;
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        this.forceDisableEagerAssert = bl = true;
        return this;
    }

    public Object forceValidateResponse() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        callSiteArray[175].call(this.response);
        return callSiteArray[176].call((Object)this.assertionClosure, this.response);
    }

    private Object isEagerAssert() {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return !this.forceDisableEagerAssert && ScriptBytecodeAdapter.compareNotEqual(this.response, null);
        }
        return !this.forceDisableEagerAssert && ScriptBytecodeAdapter.compareNotEqual(this.response, null);
    }

    private void validateResponseIfRequired(Closure closure) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[177].callCurrent(this))) {
                callSiteArray[178].call(this.bodyMatchers);
            }
        } else if (DefaultTypeTransformation.booleanUnbox(this.isEagerAssert())) {
            callSiteArray[179].call(this.bodyMatchers);
        }
        callSiteArray[180].call(closure);
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[181].callCurrent(this))) {
                callSiteArray[182].call((Object)this.assertionClosure, this.response);
            }
        } else if (DefaultTypeTransformation.booleanUnbox(this.isEagerAssert())) {
            callSiteArray[183].call((Object)this.assertionClosure, this.response);
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ResponseSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{EMPTY, EMPTY}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ResponseSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{EMPTY, EMPTY}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ResponseSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{EMPTY, EMPTY}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ResponseSpecificationImpl.class) {
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
        CallSite[] callSiteArray = ResponseSpecificationImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        String string;
        long l;
        long l2;
        ResponseSpecificationImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729374 = l2 = 0L;
        __timeStamp = l = 1702030729374L;
        DOT = string = ".";
    }

    public ResponseParserRegistrar getRpr() {
        return this.rpr;
    }

    public void setRpr(ResponseParserRegistrar responseParserRegistrar) {
        this.rpr = responseParserRegistrar;
    }

    public RestAssuredConfig getConfig() {
        return this.config;
    }

    public void setConfig(RestAssuredConfig restAssuredConfig) {
        this.config = restAssuredConfig;
    }

    public LogRepository getLogRepository() {
        return this.logRepository;
    }

    public void setLogRepository(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public /* synthetic */ String this$2$applyArguments(String string, List list) {
        return this.applyArguments(string, list);
    }

    public /* synthetic */ String this$2$mergeKeyWithRootPath(String string) {
        return this.mergeKeyWithRootPath(string);
    }

    public /* synthetic */ Object this$2$isEagerAssert() {
        return this.isEagerAssert();
    }

    public /* synthetic */ void this$2$validateResponseIfRequired(Closure closure) {
        this.validateResponseIfRequired(closure);
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
        stringArray[2] = "notNull";
        stringArray[3] = "rootPath";
        stringArray[4] = "spec";
        stringArray[5] = "spec";
        stringArray[6] = "throwIllegalStateExceptionIfRootPathIsNotDefined";
        stringArray[7] = "body";
        stringArray[8] = "validate";
        stringArray[9] = "notNull";
        stringArray[10] = "validateResponseIfRequired";
        stringArray[11] = "body";
        stringArray[12] = "emptyList";
        stringArray[13] = "time";
        stringArray[14] = "MILLISECONDS";
        stringArray[15] = "notNull";
        stringArray[16] = "notNull";
        stringArray[17] = "validateResponseIfRequired";
        stringArray[18] = "notNull";
        stringArray[19] = "validateResponseIfRequired";
        stringArray[20] = "notNull";
        stringArray[21] = "statusCode";
        stringArray[22] = "equalTo";
        stringArray[23] = "notNull";
        stringArray[24] = "validateResponseIfRequired";
        stringArray[25] = "notNull";
        stringArray[26] = "validateResponseIfRequired";
        stringArray[27] = "notNull";
        stringArray[28] = "notNull";
        stringArray[29] = "headers";
        stringArray[30] = "createMapFromParams";
        stringArray[31] = "MERGE";
        stringArray[32] = "notNull";
        stringArray[33] = "notNull";
        stringArray[34] = "notNull";
        stringArray[35] = "validateResponseIfRequired";
        stringArray[36] = "notNull";
        stringArray[37] = "notNull";
        stringArray[38] = "validateResponseIfRequired";
        stringArray[39] = "header";
        stringArray[40] = "equalTo";
        stringArray[41] = "notNull";
        stringArray[42] = "validateResponseIfRequired";
        stringArray[43] = "notNull";
        stringArray[44] = "notNull";
        stringArray[45] = "notNull";
        stringArray[46] = "cookies";
        stringArray[47] = "createMapFromParams";
        stringArray[48] = "MERGE";
        stringArray[49] = "notNull";
        stringArray[50] = "notNull";
        stringArray[51] = "validateResponseIfRequired";
        stringArray[52] = "notNull";
        stringArray[53] = "notNull";
        stringArray[54] = "validateResponseIfRequired";
        stringArray[55] = "notNull";
        stringArray[56] = "cookie";
        stringArray[57] = "anything";
        stringArray[58] = "cookie";
        stringArray[59] = "equalTo";
        stringArray[60] = "merge";
        stringArray[61] = "spec";
        stringArray[62] = "statusLine";
        stringArray[63] = "equalTo";
        stringArray[64] = "notNull";
        stringArray[65] = "notNull";
        stringArray[66] = "validateResponseIfRequired";
        stringArray[67] = "<$constructor$>";
        stringArray[68] = "registerParser";
        stringArray[69] = "given";
        stringArray[70] = "rootPath";
        stringArray[71] = "rootPath";
        stringArray[72] = "appendRootPath";
        stringArray[73] = "notNull";
        stringArray[74] = "notNull";
        stringArray[75] = "mergeKeyWithRootPath";
        stringArray[76] = "rootPath";
        stringArray[77] = "notNull";
        stringArray[78] = "throwIllegalStateExceptionIfRootPathIsNotDefined";
        stringArray[79] = "trim";
        stringArray[80] = "endsWith";
        stringArray[81] = "<$constructor$>";
        stringArray[82] = "substringBeforeLast";
        stringArray[83] = "endsWith";
        stringArray[84] = "substring";
        stringArray[85] = "minus";
        stringArray[86] = "length";
        stringArray[87] = "notNull";
        stringArray[88] = "notNull";
        stringArray[89] = "applyArguments";
        stringArray[90] = "rootPath";
        stringArray[91] = "containsMatchers";
        stringArray[92] = "hasBodyAssertionsDefined";
        stringArray[93] = "isEmpty";
        stringArray[94] = "isEmpty";
        stringArray[95] = "isEmpty";
        stringArray[96] = "isEmpty";
        stringArray[97] = "notNull";
        stringArray[98] = "notNull";
        stringArray[99] = "validateResponseIfRequired";
        stringArray[100] = "notNull";
        stringArray[101] = "validateResponseIfRequired";
        stringArray[102] = "notNull";
        stringArray[103] = "validateResponseIfRequired";
        stringArray[104] = "isEmpty";
        stringArray[105] = "isEmpty";
        stringArray[106] = "responseContentType";
        stringArray[107] = "toString";
        stringArray[108] = "responseContentType";
        stringArray[109] = "toString";
        stringArray[110] = "ANY";
        stringArray[111] = "responseContentType";
        stringArray[112] = "toString";
        stringArray[113] = "responseContentType";
        stringArray[114] = "toString";
        stringArray[115] = "ANY";
        stringArray[116] = "size";
        stringArray[117] = "countMatches";
        stringArray[118] = "size";
        stringArray[119] = "<$constructor$>";
        stringArray[120] = "minus";
        stringArray[121] = "size";
        stringArray[122] = "add";
        stringArray[123] = "<$constructor$>";
        stringArray[124] = "next";
        stringArray[125] = "format";
        stringArray[126] = "toArray";
        stringArray[127] = "collect";
        stringArray[128] = "size";
        stringArray[129] = "size";
        stringArray[130] = "countMatches";
        stringArray[131] = "size";
        stringArray[132] = "<$constructor$>";
        stringArray[133] = "minus";
        stringArray[134] = "size";
        stringArray[135] = "add";
        stringArray[136] = "<$constructor$>";
        stringArray[137] = "format";
        stringArray[138] = "toArray";
        stringArray[139] = "collect";
        stringArray[140] = "size";
        stringArray[141] = "endsWith";
        stringArray[142] = "startsWith";
        stringArray[143] = "plus";
        stringArray[144] = "substringAfter";
        stringArray[145] = "endsWith";
        stringArray[146] = "startsWith";
        stringArray[147] = "startsWith";
        stringArray[148] = "startsWith";
        stringArray[149] = "startsWith";
        stringArray[150] = "startsWith";
        stringArray[151] = "plus";
        stringArray[152] = "plus";
        stringArray[153] = "plus";
        stringArray[154] = "endsWith";
        stringArray[155] = "startsWith";
        stringArray[156] = "plus";
        stringArray[157] = "substringAfter";
        stringArray[158] = "endsWith";
        stringArray[159] = "startsWith";
        stringArray[160] = "startsWith";
        stringArray[161] = "startsWith";
        stringArray[162] = "startsWith";
        stringArray[163] = "startsWith";
        stringArray[164] = "plus";
        stringArray[165] = "plus";
        stringArray[166] = "plus";
        stringArray[167] = "rootPath";
        stringArray[168] = "isEmpty";
        stringArray[169] = "rootPath";
        stringArray[170] = "<$constructor$>";
        stringArray[171] = "rootPath";
        stringArray[172] = "isEmpty";
        stringArray[173] = "rootPath";
        stringArray[174] = "<$constructor$>";
        stringArray[175] = "asString";
        stringArray[176] = "validate";
        stringArray[177] = "isEagerAssert";
        stringArray[178] = "reset";
        stringArray[179] = "reset";
        stringArray[180] = "call";
        stringArray[181] = "isEagerAssert";
        stringArray[182] = "validate";
        stringArray[183] = "validate";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[184];
        ResponseSpecificationImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(ResponseSpecificationImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ResponseSpecificationImpl.$createCallSiteArray();
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

    public class HamcrestAssertionClosure
    implements GroovyObject {
        private ResponseSpecification responseSpecification;
        public /* synthetic */ ResponseSpecificationImpl this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;
        private static /* synthetic */ SoftReference $callSiteArray;

        public HamcrestAssertionClosure(ResponseSpecificationImpl $p$, ResponseSpecification responseSpecification) {
            MetaClass metaClass;
            ResponseSpecificationImpl responseSpecificationImpl;
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            this.this$0 = responseSpecificationImpl = $p$;
            this.metaClass = metaClass = this.$getStaticMetaClass();
            ResponseSpecification responseSpecification2 = responseSpecification;
            this.responseSpecification = (ResponseSpecification)ScriptBytecodeAdapter.castToType(responseSpecification2, ResponseSpecification.class);
        }

        public Object call(Object response, Object content) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return callSiteArray[0].call(callSiteArray[1].callCurrent(this), response, content);
            }
            return callSiteArray[2].call(this.getClosure(), response, content);
        }

        public Object call(Object response) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                return callSiteArray[3].call(callSiteArray[4].callCurrent(this), response, null);
            }
            return callSiteArray[5].call(this.getClosure(), response, null);
        }

        public Object getResponseContentType() {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            Object object = callSiteArray[6].callGroovyObjectGetProperty(this);
            return DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[7].callGetProperty(ContentType.class);
        }

        private boolean requiresPathParsing() {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            return DefaultTypeTransformation.booleanUnbox(callSiteArray[8].call(callSiteArray[9].callGroovyObjectGetProperty(this)));
        }

        public Object getClosure() {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            return new GeneratedClosure(this, this){
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_getClosure_closure1.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object response, Object content) {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_getClosure_closure1.$getCallSiteArray();
                    return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), response, content, callSiteArray[2].callCurrent(this), callSiteArray[3].callGroovyObjectGetProperty(this));
                }

                public Object call(Object response, Object content) {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_getClosure_closure1.$getCallSiteArray();
                    return callSiteArray[4].callCurrent(this, response, content);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _HamcrestAssertionClosure_getClosure_closure1.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_getClosure_closure1.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _HamcrestAssertionClosure_getClosure_closure1.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "parseResponse";
                    stringArray[1] = "restAssuredResponse";
                    stringArray[2] = "hasBodyAssertionsDefined";
                    stringArray[3] = "rpr";
                    stringArray[4] = "doCall";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[5];
                    _HamcrestAssertionClosure_getClosure_closure1.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_HamcrestAssertionClosure_getClosure_closure1.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _HamcrestAssertionClosure_getClosure_closure1.$createCallSiteArray();
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
        }

        public Object validate(Response response) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[10].callCurrent(this))) {
                List validations = ScriptBytecodeAdapter.createList(new Object[0]);
                try {
                    callSiteArray[11].call((Object)validations, callSiteArray[12].callCurrent((GroovyObject)this, response));
                    callSiteArray[13].call((Object)validations, callSiteArray[14].callCurrent((GroovyObject)this, response));
                    callSiteArray[15].call((Object)validations, callSiteArray[16].callCurrent((GroovyObject)this, response));
                    callSiteArray[17].call((Object)validations, callSiteArray[18].callCurrent((GroovyObject)this, response));
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[19].callCurrent(this))) {
                        Object object = callSiteArray[20].callGroovyObjectGetProperty(this);
                        RestAssuredConfig restAssuredConfig = (RestAssuredConfig)ScriptBytecodeAdapter.castToType(object, RestAssuredConfig.class);
                        RestAssuredConfig cfg = DefaultTypeTransformation.booleanUnbox(object) ? restAssuredConfig : (RestAssuredConfig)ScriptBytecodeAdapter.castToType(callSiteArray[21].callConstructor(RestAssuredConfig.class), RestAssuredConfig.class);
                        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[22].callCurrent(this)) && (!DefaultTypeTransformation.booleanUnbox(callSiteArray[23].callCurrent(this)) || ScriptBytecodeAdapter.compareEqual(callSiteArray[24].callGroovyObjectGetProperty(this), null))) {
                            Object object2 = callSiteArray[25].call(callSiteArray[26].callConstructor(ContentParser.class), response, callSiteArray[27].callGroovyObjectGetProperty(this), cfg, callSiteArray[28].callCurrent(this));
                            ScriptBytecodeAdapter.setGroovyObjectProperty(object2, HamcrestAssertionClosure.class, this, "contentParser");
                        }
                        callSiteArray[29].call((Object)validations, callSiteArray[30].call(callSiteArray[31].callGroovyObjectGetProperty(this), response, callSiteArray[32].callGroovyObjectGetProperty(this), cfg));
                    }
                }
                catch (Throwable e) {
                    callSiteArray[33].callCurrent((GroovyObject)this, response);
                    throw e;
                }
                Object errors = callSiteArray[34].call((Object)validations, new GeneratedClosure(this, this){
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;
                    private static /* synthetic */ SoftReference $callSiteArray;
                    {
                        CallSite[] callSiteArray = _HamcrestAssertionClosure_validate_closure2.$getCallSiteArray();
                        super(_outerInstance, _thisObject);
                    }

                    public Object doCall(Object it) {
                        CallSite[] callSiteArray = _HamcrestAssertionClosure_validate_closure2.$getCallSiteArray();
                        return !DefaultTypeTransformation.booleanUnbox(callSiteArray[0].callGetProperty(it));
                    }

                    public Object doCall() {
                        CallSite[] callSiteArray = _HamcrestAssertionClosure_validate_closure2.$getCallSiteArray();
                        return this.doCall(null);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _HamcrestAssertionClosure_validate_closure2.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public static /* synthetic */ void __$swapInit() {
                        CallSite[] callSiteArray = _HamcrestAssertionClosure_validate_closure2.$getCallSiteArray();
                        $callSiteArray = null;
                    }

                    static {
                        _HamcrestAssertionClosure_validate_closure2.__$swapInit();
                    }

                    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                        stringArray[0] = "success";
                    }

                    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                        String[] stringArray = new String[1];
                        _HamcrestAssertionClosure_validate_closure2.$createCallSiteArray_1(stringArray);
                        return new CallSiteArray(_HamcrestAssertionClosure_validate_closure2.class, stringArray);
                    }

                    private static /* synthetic */ CallSite[] $getCallSiteArray() {
                        CallSiteArray callSiteArray;
                        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                            callSiteArray = _HamcrestAssertionClosure_validate_closure2.$createCallSiteArray();
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
                Object numberOfErrors = callSiteArray[35].call(errors);
                if (ScriptBytecodeAdapter.compareGreaterThan(numberOfErrors, 0)) {
                    callSiteArray[36].callCurrent((GroovyObject)this, response);
                    Object errorMessage = callSiteArray[37].call(callSiteArray[38].call(errors, new GeneratedClosure(this, this){
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;
                        private static /* synthetic */ SoftReference $callSiteArray;
                        {
                            CallSite[] callSiteArray = _HamcrestAssertionClosure_validate_closure3.$getCallSiteArray();
                            super(_outerInstance, _thisObject);
                        }

                        public Object doCall(Object it) {
                            CallSite[] callSiteArray = _HamcrestAssertionClosure_validate_closure3.$getCallSiteArray();
                            return callSiteArray[0].callGetProperty(it);
                        }

                        public Object doCall() {
                            CallSite[] callSiteArray = _HamcrestAssertionClosure_validate_closure3.$getCallSiteArray();
                            return this.doCall(null);
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _HamcrestAssertionClosure_validate_closure3.class) {
                                return ScriptBytecodeAdapter.initMetaClass(this);
                            }
                            ClassInfo classInfo = $staticClassInfo;
                            if (classInfo == null) {
                                $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                            }
                            return classInfo.getMetaClass();
                        }

                        public static /* synthetic */ void __$swapInit() {
                            CallSite[] callSiteArray = _HamcrestAssertionClosure_validate_closure3.$getCallSiteArray();
                            $callSiteArray = null;
                        }

                        static {
                            _HamcrestAssertionClosure_validate_closure3.__$swapInit();
                        }

                        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                            stringArray[0] = "errorMessage";
                        }

                        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                            String[] stringArray = new String[1];
                            _HamcrestAssertionClosure_validate_closure3.$createCallSiteArray_1(stringArray);
                            return new CallSiteArray(_HamcrestAssertionClosure_validate_closure3.class, stringArray);
                        }

                        private static /* synthetic */ CallSite[] $getCallSiteArray() {
                            CallSiteArray callSiteArray;
                            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                                callSiteArray = _HamcrestAssertionClosure_validate_closure3.$createCallSiteArray();
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
                    }), "\n");
                    String s = ScriptBytecodeAdapter.compareGreaterThan(numberOfErrors, 1) ? "s" : ResponseSpecificationImpl.EMPTY;
                    throw (Throwable)callSiteArray[39].callConstructor(AssertionError.class, new GStringImpl(new Object[]{numberOfErrors, s, errorMessage, callSiteArray[40].callGroovyObjectGetProperty(this)}, new String[]{ResponseSpecificationImpl.EMPTY, " expectation", " failed.\n", ResponseSpecificationImpl.EMPTY, ResponseSpecificationImpl.EMPTY}));
                }
                return null;
            }
            return null;
        }

        private String getFormattedOnFailMessage() {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            return (String)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox(callSiteArray[41].callGroovyObjectGetProperty(this)) ? new GStringImpl(new Object[]{callSiteArray[42].callGroovyObjectGetProperty(this)}, new String[]{"\nOn fail message: ", ResponseSpecificationImpl.EMPTY}) : ResponseSpecificationImpl.EMPTY, String.class);
        }

        private void fireFailureListeners(Response response) {
            Reference<Response> response2 = new Reference<Response>(response);
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            callSiteArray[43].call(callSiteArray[44].call(callSiteArray[45].call(callSiteArray[46].callGroovyObjectGetProperty(this))), new GeneratedClosure(this, this, response2){
                private /* synthetic */ Reference response;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_fireFailureListeners_closure4.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.response = reference = response;
                }

                public Object doCall(ResponseValidationFailureListener listener) {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_fireFailureListeners_closure4.$getCallSiteArray();
                    return callSiteArray[0].call(listener, callSiteArray[1].callGroovyObjectGetProperty(this), callSiteArray[2].callGroovyObjectGetProperty(this), this.response.get());
                }

                public Object call(ResponseValidationFailureListener listener) {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_fireFailureListeners_closure4.$getCallSiteArray();
                    return callSiteArray[3].callCurrent((GroovyObject)this, listener);
                }

                public Response getResponse() {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_fireFailureListeners_closure4.$getCallSiteArray();
                    return (Response)ScriptBytecodeAdapter.castToType(this.response.get(), Response.class);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _HamcrestAssertionClosure_fireFailureListeners_closure4.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_fireFailureListeners_closure4.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _HamcrestAssertionClosure_fireFailureListeners_closure4.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "onFailure";
                    stringArray[1] = "requestSpecification";
                    stringArray[2] = "responseSpecification";
                    stringArray[3] = "doCall";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[4];
                    _HamcrestAssertionClosure_fireFailureListeners_closure4.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_HamcrestAssertionClosure_fireFailureListeners_closure4.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _HamcrestAssertionClosure_fireFailureListeners_closure4.$createCallSiteArray();
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

        private Object validateContentType(Response response) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            List errors = ScriptBytecodeAdapter.createList(new Object[0]);
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[47].callGroovyObjectGetProperty(this), null)) {
                    Object actualContentType = callSiteArray[48].call(response);
                    if (callSiteArray[49].callGroovyObjectGetProperty(this) instanceof Matcher) {
                        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[50].call(callSiteArray[51].callGroovyObjectGetProperty(this), actualContentType))) {
                            callSiteArray[52].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", callSiteArray[53].call(String.class, "Expected content-type %s doesn't match actual content-type \"%s\".\n", callSiteArray[54].callGroovyObjectGetProperty(this), actualContentType)}));
                        }
                    } else if (callSiteArray[55].callGroovyObjectGetProperty(this) instanceof String) {
                        Object normalizedExpectedContentType = callSiteArray[56].callCurrent((GroovyObject)this, callSiteArray[57].call(callSiteArray[58].callGroovyObjectGetProperty(this)));
                        Object normalizedActualContentType = callSiteArray[59].callCurrent((GroovyObject)this, actualContentType);
                        if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[60].call(StringUtils.class, normalizedActualContentType, normalizedExpectedContentType))) {
                            callSiteArray[61].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", callSiteArray[62].call(String.class, "Expected content-type \"%s\" doesn't match actual content-type \"%s\".\n", callSiteArray[63].callGroovyObjectGetProperty(this), actualContentType)}));
                        }
                    } else {
                        Object object;
                        Object object2;
                        Object name = callSiteArray[64].call(callSiteArray[65].callGroovyObjectGetProperty(this));
                        Object pattern = ScriptBytecodeAdapter.bitwiseNegate("(^[\\w\\d_\\-]+/[\\w\\d_\\-]+)\\s*(?:;)");
                        Object object3 = actualContentType;
                        Object matcher = callSiteArray[66].call(pattern, DefaultTypeTransformation.booleanUnbox(object3) ? object3 : ResponseSpecificationImpl.EMPTY);
                        Object contentTypeToMatch = null;
                        contentTypeToMatch = DefaultTypeTransformation.booleanUnbox(callSiteArray[67].call(matcher)) ? (object2 = callSiteArray[68].call(matcher, 1)) : (object = actualContentType);
                        if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[69].call(ContentType.class, contentTypeToMatch), callSiteArray[70].callGroovyObjectGetProperty(this))) {
                            callSiteArray[71].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", callSiteArray[72].call(String.class, "Expected content-type \"%s\" doesn't match actual content-type \"%s\".\n", name, actualContentType)}));
                        }
                    }
                }
            } else if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[73].callGroovyObjectGetProperty(this), null)) {
                Object actualContentType = callSiteArray[74].call(response);
                if (callSiteArray[75].callGroovyObjectGetProperty(this) instanceof Matcher) {
                    if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[76].call(callSiteArray[77].callGroovyObjectGetProperty(this), actualContentType))) {
                        callSiteArray[78].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", callSiteArray[79].call(String.class, "Expected content-type %s doesn't match actual content-type \"%s\".\n", callSiteArray[80].callGroovyObjectGetProperty(this), actualContentType)}));
                    }
                } else if (callSiteArray[81].callGroovyObjectGetProperty(this) instanceof String) {
                    Object normalizedExpectedContentType = callSiteArray[82].callCurrent((GroovyObject)this, callSiteArray[83].call(callSiteArray[84].callGroovyObjectGetProperty(this)));
                    Object normalizedActualContentType = callSiteArray[85].callCurrent((GroovyObject)this, actualContentType);
                    if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[86].call(StringUtils.class, normalizedActualContentType, normalizedExpectedContentType))) {
                        callSiteArray[87].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", callSiteArray[88].call(String.class, "Expected content-type \"%s\" doesn't match actual content-type \"%s\".\n", callSiteArray[89].callGroovyObjectGetProperty(this), actualContentType)}));
                    }
                } else {
                    Object object;
                    Object object4;
                    Object name = callSiteArray[90].call(callSiteArray[91].callGroovyObjectGetProperty(this));
                    Object pattern = ScriptBytecodeAdapter.bitwiseNegate("(^[\\w\\d_\\-]+/[\\w\\d_\\-]+)\\s*(?:;)");
                    Object object5 = actualContentType;
                    Object matcher = callSiteArray[92].call(pattern, DefaultTypeTransformation.booleanUnbox(object5) ? object5 : ResponseSpecificationImpl.EMPTY);
                    Object contentTypeToMatch = null;
                    contentTypeToMatch = DefaultTypeTransformation.booleanUnbox(callSiteArray[93].call(matcher)) ? (object4 = callSiteArray[94].call(matcher, 1)) : (object = actualContentType);
                    if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[95].call(ContentType.class, contentTypeToMatch), callSiteArray[96].callGroovyObjectGetProperty(this))) {
                        callSiteArray[97].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", callSiteArray[98].call(String.class, "Expected content-type \"%s\" doesn't match actual content-type \"%s\".\n", name, actualContentType)}));
                    }
                }
            }
            return errors;
        }

        private String normalizeContentType(String actualContentType) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[99].call(StringUtils.class, actualContentType, ScriptBytecodeAdapter.createPojoWrapper((String[])ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[]{" ", "\t"}), String[].class), String[].class), ScriptBytecodeAdapter.createPojoWrapper((String[])ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[]{ResponseSpecificationImpl.EMPTY, ResponseSpecificationImpl.EMPTY}), String[].class), String[].class)), String.class);
        }

        private Object validateStatusCodeAndStatusLine(Response response) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            List errors = ScriptBytecodeAdapter.createList(new Object[0]);
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[100].callGroovyObjectGetProperty(this), null)) {
                    Object actualStatusCode = callSiteArray[101].call(response);
                    if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[102].call(callSiteArray[103].callGroovyObjectGetProperty(this), actualStatusCode))) {
                        Object errorMessage = callSiteArray[104].call(callSiteArray[105].callConstructor(MatcherErrorMessageBuilder.class, "status code"), actualStatusCode, callSiteArray[106].callGroovyObjectGetProperty(this));
                        callSiteArray[107].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", errorMessage}));
                    }
                }
            } else if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[108].callGroovyObjectGetProperty(this), null)) {
                Object actualStatusCode = callSiteArray[109].call(response);
                if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[110].call(callSiteArray[111].callGroovyObjectGetProperty(this), actualStatusCode))) {
                    Object errorMessage = callSiteArray[112].call(callSiteArray[113].callConstructor(MatcherErrorMessageBuilder.class, "status code"), actualStatusCode, callSiteArray[114].callGroovyObjectGetProperty(this));
                    callSiteArray[115].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", errorMessage}));
                }
            }
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[116].callGroovyObjectGetProperty(this), null)) {
                    Object actualStatusLine = callSiteArray[117].call(response);
                    if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[118].call(callSiteArray[119].callGroovyObjectGetProperty(this), actualStatusLine))) {
                        Object errorMessage = callSiteArray[120].call(String.class, "Expected status line %s doesn't match actual status line \"%s\".\n", callSiteArray[121].call(callSiteArray[122].callGroovyObjectGetProperty(this)), actualStatusLine);
                        callSiteArray[123].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", errorMessage}));
                    }
                }
            } else if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[124].callGroovyObjectGetProperty(this), null)) {
                Object actualStatusLine = callSiteArray[125].call(response);
                if (!DefaultTypeTransformation.booleanUnbox(callSiteArray[126].call(callSiteArray[127].callGroovyObjectGetProperty(this), actualStatusLine))) {
                    Object errorMessage = callSiteArray[128].call(String.class, "Expected status line %s doesn't match actual status line \"%s\".\n", callSiteArray[129].call(callSiteArray[130].callGroovyObjectGetProperty(this)), actualStatusLine);
                    callSiteArray[131].call((Object)errors, ScriptBytecodeAdapter.createMap(new Object[]{"success", false, "errorMessage", errorMessage}));
                }
            }
            return errors;
        }

        private Object validateResponseTime(Response response) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            List validations = ScriptBytecodeAdapter.createList(new Object[0]);
            if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[132].callGroovyObjectGetProperty(this), null)) {
                    callSiteArray[133].call((Object)validations, callSiteArray[134].call(callSiteArray[135].callConstructor(ResponseTimeMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"matcher", callSiteArray[136].callGetProperty(callSiteArray[137].callGroovyObjectGetProperty(this)), "timeUnit", callSiteArray[138].callGetProperty(callSiteArray[139].callGroovyObjectGetProperty(this))})), response));
                }
            } else if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[140].callGroovyObjectGetProperty(this), null)) {
                callSiteArray[141].call((Object)validations, callSiteArray[142].call(callSiteArray[143].callConstructor(ResponseTimeMatcher.class, ScriptBytecodeAdapter.createMap(new Object[]{"matcher", callSiteArray[144].callGetProperty(callSiteArray[145].callGroovyObjectGetProperty(this)), "timeUnit", callSiteArray[146].callGetProperty(callSiteArray[147].callGroovyObjectGetProperty(this))})), response));
            }
            return validations;
        }

        private Object validateHeadersAndCookies(Response response) {
            Reference<Response> response2 = new Reference<Response>(response);
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            List validations = ScriptBytecodeAdapter.createList(new Object[0]);
            callSiteArray[148].call((Object)validations, callSiteArray[149].call(callSiteArray[150].callGroovyObjectGetProperty(this), new GeneratedClosure(this, this, response2){
                private /* synthetic */ Reference response;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure5.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.response = reference = response;
                }

                public Object doCall(Object matcher) {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure5.$getCallSiteArray();
                    return callSiteArray[0].call(matcher, callSiteArray[1].call(this.response.get()));
                }

                public Response getResponse() {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure5.$getCallSiteArray();
                    return (Response)ScriptBytecodeAdapter.castToType(this.response.get(), Response.class);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _HamcrestAssertionClosure_validateHeadersAndCookies_closure5.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure5.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _HamcrestAssertionClosure_validateHeadersAndCookies_closure5.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "validateHeader";
                    stringArray[1] = "getHeaders";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[2];
                    _HamcrestAssertionClosure_validateHeadersAndCookies_closure5.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_HamcrestAssertionClosure_validateHeadersAndCookies_closure5.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure5.$createCallSiteArray();
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
            }));
            callSiteArray[151].call((Object)validations, callSiteArray[152].call(callSiteArray[153].callGroovyObjectGetProperty(this), new GeneratedClosure(this, this, response2){
                private /* synthetic */ Reference response;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure6.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.response = reference = response;
                }

                public Object doCall(Object matcher) {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure6.$getCallSiteArray();
                    Object headerWithCookieList = callSiteArray[0].call(callSiteArray[1].call(this.response.get()), "Set-Cookie");
                    Object responseCookies = callSiteArray[2].call(this.response.get());
                    return callSiteArray[3].call(matcher, headerWithCookieList, responseCookies);
                }

                public Response getResponse() {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure6.$getCallSiteArray();
                    return (Response)ScriptBytecodeAdapter.castToType(this.response.get(), Response.class);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _HamcrestAssertionClosure_validateHeadersAndCookies_closure6.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure6.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _HamcrestAssertionClosure_validateHeadersAndCookies_closure6.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "getValues";
                    stringArray[1] = "getHeaders";
                    stringArray[2] = "getDetailedCookies";
                    stringArray[3] = "validateCookies";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[4];
                    _HamcrestAssertionClosure_validateHeadersAndCookies_closure6.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_HamcrestAssertionClosure_validateHeadersAndCookies_closure6.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _HamcrestAssertionClosure_validateHeadersAndCookies_closure6.$createCallSiteArray();
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
            }));
            return validations;
        }

        public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(HamcrestAssertionClosure.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{ResponseSpecificationImpl.EMPTY, ResponseSpecificationImpl.EMPTY}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$1(String name, Object value) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, HamcrestAssertionClosure.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{ResponseSpecificationImpl.EMPTY, ResponseSpecificationImpl.EMPTY}), String.class));
        }

        public /* synthetic */ Object this$dist$get$1(String name) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(HamcrestAssertionClosure.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{ResponseSpecificationImpl.EMPTY, ResponseSpecificationImpl.EMPTY}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            return this.this$0.this$dist$invoke$1(name, args);
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            this.this$0.this$dist$set$1(name, val);
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            return this.this$0.this$dist$get$1(name);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != HamcrestAssertionClosure.class) {
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
            CallSite[] callSiteArray = HamcrestAssertionClosure.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            HamcrestAssertionClosure.__$swapInit();
        }

        public /* synthetic */ boolean this$2$requiresPathParsing() {
            return this.requiresPathParsing();
        }

        public /* synthetic */ String this$2$getFormattedOnFailMessage() {
            return this.getFormattedOnFailMessage();
        }

        public /* synthetic */ void this$2$fireFailureListeners(Response response) {
            this.fireFailureListeners(response);
        }

        public /* synthetic */ Object this$2$validateContentType(Response response) {
            return this.validateContentType(response);
        }

        public /* synthetic */ String this$2$normalizeContentType(String string) {
            return this.normalizeContentType(string);
        }

        public /* synthetic */ Object this$2$validateStatusCodeAndStatusLine(Response response) {
            return this.validateStatusCodeAndStatusLine(response);
        }

        public /* synthetic */ Object this$2$validateResponseTime(Response response) {
            return this.validateResponseTime(response);
        }

        public /* synthetic */ Object this$2$validateHeadersAndCookies(Response response) {
            return this.validateHeadersAndCookies(response);
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
            stringArray[0] = "call";
            stringArray[1] = "getClosure";
            stringArray[2] = "call";
            stringArray[3] = "call";
            stringArray[4] = "getClosure";
            stringArray[5] = "call";
            stringArray[6] = "contentType";
            stringArray[7] = "ANY";
            stringArray[8] = "requiresPathParsing";
            stringArray[9] = "bodyMatchers";
            stringArray[10] = "hasAssertionsDefined";
            stringArray[11] = "addAll";
            stringArray[12] = "validateStatusCodeAndStatusLine";
            stringArray[13] = "addAll";
            stringArray[14] = "validateHeadersAndCookies";
            stringArray[15] = "addAll";
            stringArray[16] = "validateContentType";
            stringArray[17] = "addAll";
            stringArray[18] = "validateResponseTime";
            stringArray[19] = "hasBodyAssertionsDefined";
            stringArray[20] = "config";
            stringArray[21] = "<$constructor$>";
            stringArray[22] = "requiresPathParsing";
            stringArray[23] = "isEagerAssert";
            stringArray[24] = "contentParser";
            stringArray[25] = "parse";
            stringArray[26] = "<$constructor$>";
            stringArray[27] = "rpr";
            stringArray[28] = "isEagerAssert";
            stringArray[29] = "addAll";
            stringArray[30] = "validate";
            stringArray[31] = "bodyMatchers";
            stringArray[32] = "contentParser";
            stringArray[33] = "fireFailureListeners";
            stringArray[34] = "findAll";
            stringArray[35] = "size";
            stringArray[36] = "fireFailureListeners";
            stringArray[37] = "join";
            stringArray[38] = "collect";
            stringArray[39] = "<$constructor$>";
            stringArray[40] = "formattedOnFailMessage";
            stringArray[41] = "onFailMessage";
            stringArray[42] = "onFailMessage";
            stringArray[43] = "each";
            stringArray[44] = "getFailureListeners";
            stringArray[45] = "getFailureConfig";
            stringArray[46] = "config";
            stringArray[47] = "contentType";
            stringArray[48] = "getContentType";
            stringArray[49] = "contentType";
            stringArray[50] = "matches";
            stringArray[51] = "contentType";
            stringArray[52] = "leftShift";
            stringArray[53] = "format";
            stringArray[54] = "contentType";
            stringArray[55] = "contentType";
            stringArray[56] = "normalizeContentType";
            stringArray[57] = "toString";
            stringArray[58] = "contentType";
            stringArray[59] = "normalizeContentType";
            stringArray[60] = "startsWithIgnoreCase";
            stringArray[61] = "leftShift";
            stringArray[62] = "format";
            stringArray[63] = "contentType";
            stringArray[64] = "name";
            stringArray[65] = "contentType";
            stringArray[66] = "matcher";
            stringArray[67] = "find";
            stringArray[68] = "group";
            stringArray[69] = "fromContentType";
            stringArray[70] = "contentType";
            stringArray[71] = "leftShift";
            stringArray[72] = "format";
            stringArray[73] = "contentType";
            stringArray[74] = "getContentType";
            stringArray[75] = "contentType";
            stringArray[76] = "matches";
            stringArray[77] = "contentType";
            stringArray[78] = "leftShift";
            stringArray[79] = "format";
            stringArray[80] = "contentType";
            stringArray[81] = "contentType";
            stringArray[82] = "normalizeContentType";
            stringArray[83] = "toString";
            stringArray[84] = "contentType";
            stringArray[85] = "normalizeContentType";
            stringArray[86] = "startsWithIgnoreCase";
            stringArray[87] = "leftShift";
            stringArray[88] = "format";
            stringArray[89] = "contentType";
            stringArray[90] = "name";
            stringArray[91] = "contentType";
            stringArray[92] = "matcher";
            stringArray[93] = "find";
            stringArray[94] = "group";
            stringArray[95] = "fromContentType";
            stringArray[96] = "contentType";
            stringArray[97] = "leftShift";
            stringArray[98] = "format";
            stringArray[99] = "replaceEach";
            stringArray[100] = "expectedStatusCode";
            stringArray[101] = "getStatusCode";
            stringArray[102] = "matches";
            stringArray[103] = "expectedStatusCode";
            stringArray[104] = "buildError";
            stringArray[105] = "<$constructor$>";
            stringArray[106] = "expectedStatusCode";
            stringArray[107] = "leftShift";
            stringArray[108] = "expectedStatusCode";
            stringArray[109] = "getStatusCode";
            stringArray[110] = "matches";
            stringArray[111] = "expectedStatusCode";
            stringArray[112] = "buildError";
            stringArray[113] = "<$constructor$>";
            stringArray[114] = "expectedStatusCode";
            stringArray[115] = "leftShift";
            stringArray[116] = "expectedStatusLine";
            stringArray[117] = "getStatusLine";
            stringArray[118] = "matches";
            stringArray[119] = "expectedStatusLine";
            stringArray[120] = "format";
            stringArray[121] = "toString";
            stringArray[122] = "expectedStatusLine";
            stringArray[123] = "leftShift";
            stringArray[124] = "expectedStatusLine";
            stringArray[125] = "getStatusLine";
            stringArray[126] = "matches";
            stringArray[127] = "expectedStatusLine";
            stringArray[128] = "format";
            stringArray[129] = "toString";
            stringArray[130] = "expectedStatusLine";
            stringArray[131] = "leftShift";
            stringArray[132] = "expectedResponseTime";
            stringArray[133] = "leftShift";
            stringArray[134] = "validate";
            stringArray[135] = "<$constructor$>";
            stringArray[136] = "first";
            stringArray[137] = "expectedResponseTime";
            stringArray[138] = "second";
            stringArray[139] = "expectedResponseTime";
            stringArray[140] = "expectedResponseTime";
            stringArray[141] = "leftShift";
            stringArray[142] = "validate";
            stringArray[143] = "<$constructor$>";
            stringArray[144] = "first";
            stringArray[145] = "expectedResponseTime";
            stringArray[146] = "second";
            stringArray[147] = "expectedResponseTime";
            stringArray[148] = "addAll";
            stringArray[149] = "collect";
            stringArray[150] = "headerAssertions";
            stringArray[151] = "addAll";
            stringArray[152] = "collect";
            stringArray[153] = "cookieAssertions";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[154];
            HamcrestAssertionClosure.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(HamcrestAssertionClosure.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = HamcrestAssertionClosure.$createCallSiteArray();
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
}

