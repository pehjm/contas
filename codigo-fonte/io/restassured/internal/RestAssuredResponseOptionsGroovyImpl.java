/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.lang.Writable;
import groovy.xml.StreamingMarkupBuilder;
import io.restassured.common.mapper.DataToDeserialize;
import io.restassured.common.mapper.TypeRef;
import io.restassured.config.DecoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.time.TimingFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.assertion.CookieMatcher;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.http.CharsetExtractor;
import io.restassured.internal.mapping.ObjectMapperDeserializationContextImpl;
import io.restassured.internal.mapping.ObjectMapping;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.internal.support.CloseHTTPClientConnectionInputStreamWrapper;
import io.restassured.internal.support.Prettifier;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;
import io.restassured.response.ResponseOptions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RestAssuredResponseOptionsGroovyImpl
implements GroovyObject {
    private static final String CANNOT_PARSE_MSG = "Failed to parse response.";
    public static final String BINARY = "binary";
    private static final long NO_RESPONSE_TIME;
    private Object responseHeaders;
    private Cookies cookies;
    private Object content;
    private Object contentType;
    private Object statusLine;
    private Object statusCode;
    private Object sessionIdName;
    private Map filterContextProperties;
    private Object connectionManager;
    private String defaultContentType;
    private ResponseParserRegistrar rpr;
    private DecoderConfig decoderConfig;
    private boolean hasExpectations;
    private RestAssuredConfig config;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729268;
    private static /* synthetic */ SoftReference $callSiteArray;

    public RestAssuredResponseOptionsGroovyImpl() {
        MetaClass metaClass;
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public void parseResponse(Object httpResponse, Object content, Object hasBodyAssertions, ResponseParserRegistrar responseParserRegistrar) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[0].callCurrent((GroovyObject)this, httpResponse);
        callSiteArray[1].callCurrent((GroovyObject)this, httpResponse);
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            callSiteArray[2].callCurrent(this);
        } else {
            this.parseCookies();
        }
        callSiteArray[3].callCurrent((GroovyObject)this, httpResponse);
        if (DefaultTypeTransformation.booleanUnbox(hasBodyAssertions)) {
            callSiteArray[4].callCurrent((GroovyObject)this, content);
        } else {
            Object object;
            this.content = object = content;
        }
        Object object = hasBodyAssertions;
        this.hasExpectations = DefaultTypeTransformation.booleanUnbox(object);
        ResponseParserRegistrar responseParserRegistrar2 = responseParserRegistrar;
        this.rpr = (ResponseParserRegistrar)ScriptBytecodeAdapter.castToType(responseParserRegistrar2, ResponseParserRegistrar.class);
        Object object2 = callSiteArray[5].callSafe(callSiteArray[6].callGetProperty(responseParserRegistrar));
        this.defaultContentType = (String)ScriptBytecodeAdapter.castToType(object2, String.class);
    }

    public Object parseStatus(Object httpResponse) {
        Object object;
        Object object2;
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        this.statusLine = object2 = callSiteArray[7].call(callSiteArray[8].callGetProperty(httpResponse));
        this.statusCode = object = callSiteArray[9].callGetProperty(callSiteArray[10].callGetProperty(httpResponse));
        return object;
    }

    public Object parseContentType(Object httpResponse) {
        Object object;
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        this.contentType = object = callSiteArray[11].callSafe(callSiteArray[12].callGetProperty(httpResponse));
        Object object2 = object;
        try {
            return object2;
        }
        catch (IllegalArgumentException e) {
            String string = "";
            this.contentType = string;
            String string2 = string;
            return string2;
        }
    }

    public Object parseCookies() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[13].call(callSiteArray[14].callGroovyObjectGetProperty(this), "Set-Cookie"))) {
            Object object = callSiteArray[15].call(CookieMatcher.class, callSiteArray[16].call(callSiteArray[17].callGroovyObjectGetProperty(this), "Set-Cookie"));
            this.cookies = (Cookies)ScriptBytecodeAdapter.castToType(object, Cookies.class);
            return object;
        }
        return null;
    }

    public Object parseHeaders(Object httpResponse) {
        Object object;
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Reference<List> headerList = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[0]));
        callSiteArray[18].call(callSiteArray[19].callGetProperty(httpResponse), new GeneratedClosure(this, this, headerList){
            private /* synthetic */ Reference headerList;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _parseHeaders_closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.headerList = reference = headerList;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _parseHeaders_closure1.$getCallSiteArray();
                Object name = callSiteArray[0].call(it);
                Object value = callSiteArray[1].call(it);
                return callSiteArray[2].call(this.headerList.get(), callSiteArray[3].callConstructor(Header.class, name, value));
            }

            public Object getHeaderList() {
                CallSite[] callSiteArray = _parseHeaders_closure1.$getCallSiteArray();
                return this.headerList.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _parseHeaders_closure1.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _parseHeaders_closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _parseHeaders_closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _parseHeaders_closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "getName";
                stringArray[1] = "getValue";
                stringArray[2] = "leftShift";
                stringArray[3] = "<$constructor$>";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[4];
                _parseHeaders_closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_parseHeaders_closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _parseHeaders_closure1.$createCallSiteArray();
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
        this.responseHeaders = object = callSiteArray[20].callConstructor(Headers.class, headerList.get());
        return object;
    }

    /*
     * Unable to fully structure code
     */
    private Object parseContent(Object content) {
        var2_2 = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (!(content instanceof InputStream)) ** GOTO lbl10
        this.content = var3_3 = var2_2[21].callCurrent((GroovyObject)this, content);
        var4_4 = var3_3;
        return var4_4;
lbl10:
        // 1 sources

        if (!(content instanceof Writable)) ** GOTO lbl17
        this.content = var5_5 = var2_2[22].callCurrent((GroovyObject)this, content);
        var6_6 = var5_5;
        return var6_6;
lbl17:
        // 1 sources

        if (!(content instanceof String)) ** GOTO lbl24
        this.content = var7_7 = content;
        var8_8 = var7_7;
        return var8_8;
lbl24:
        // 1 sources

        this.content = var9_9 = var2_2[23].callCurrent((GroovyObject)this, content);
        var10_10 = var9_9;
        try {
            return var10_10;
        }
        catch (IllegalStateException e) {
            throw (Throwable)var2_2[24].callConstructor(IllegalStateException.class, RestAssuredResponseOptionsGroovyImpl.CANNOT_PARSE_MSG, e);
        }
    }

    public Object toString(Writable node) {
        Reference<Writable> node2 = new Reference<Writable>(node);
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Object writer = callSiteArray[25].callConstructor(StringWriter.class);
        callSiteArray[26].call(writer, callSiteArray[27].call(callSiteArray[28].callConstructor(StreamingMarkupBuilder.class), new GeneratedClosure(this, this, node2){
            private /* synthetic */ Reference node;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _toString_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.node = reference = node;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _toString_closure2.$getCallSiteArray();
                return callSiteArray[0].call(callSiteArray[1].callGroovyObjectGetProperty(this), this.node.get());
            }

            public Writable getNode() {
                CallSite[] callSiteArray = _toString_closure2.$getCallSiteArray();
                return (Writable)ScriptBytecodeAdapter.castToType(this.node.get(), Writable.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _toString_closure2.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _toString_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _toString_closure2.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _toString_closure2.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "yield";
                stringArray[1] = "mkp";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _toString_closure2.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_toString_closure2.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _toString_closure2.$createCallSiteArray();
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
        return callSiteArray[29].call(writer);
    }

    public String print() {
        Object object;
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Object string = null;
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object2;
            string = object2 = callSiteArray[30].callCurrent(this);
        } else {
            String string2 = this.asString();
            string = string2;
        }
        this.content = object = string;
        callSiteArray[31].callCurrent((GroovyObject)this, string);
        return (String)ScriptBytecodeAdapter.castToType(string, String.class);
    }

    public String prettyPrint(ResponseOptions responseOptions, ResponseBody responseBody) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Object body = callSiteArray[32].callCurrent(this, responseOptions, responseBody);
        callSiteArray[33].callCurrent((GroovyObject)this, body);
        return (String)ScriptBytecodeAdapter.castToType(body, String.class);
    }

    public Object peek(ResponseOptions responseOptions, ResponseBody responseBody) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Set blacklistedHeaders = null;
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object = callSiteArray[34].callSafe(callSiteArray[35].callGetPropertySafe(callSiteArray[36].callCurrent(this)));
            Object object2 = DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[37].call(Collections.class);
            blacklistedHeaders = (Set)ScriptBytecodeAdapter.castToType(object2, Set.class);
        } else {
            Object object = callSiteArray[38].callSafe(callSiteArray[39].callGetPropertySafe(this.getConfig()));
            Object object3 = DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[40].call(Collections.class);
            blacklistedHeaders = (Set)ScriptBytecodeAdapter.castToType(object3, Set.class);
        }
        return callSiteArray[41].call((Object)ResponsePrinter.class, ArrayUtil.createArray(responseOptions, responseBody, callSiteArray[42].callGetProperty(System.class), callSiteArray[43].callGetProperty(LogDetail.class), false, blacklistedHeaders));
    }

    public Object prettyPeek(ResponseOptions responseOptions, ResponseBody responseBody) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Set blacklistedHeaders = null;
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object = callSiteArray[44].callSafe(callSiteArray[45].callGetPropertySafe(callSiteArray[46].callCurrent(this)));
            Object object2 = DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[47].call(Collections.class);
            blacklistedHeaders = (Set)ScriptBytecodeAdapter.castToType(object2, Set.class);
        } else {
            Object object = callSiteArray[48].callSafe(callSiteArray[49].callGetPropertySafe(this.getConfig()));
            Object object3 = DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[50].call(Collections.class);
            blacklistedHeaders = (Set)ScriptBytecodeAdapter.castToType(object3, Set.class);
        }
        return callSiteArray[51].call((Object)ResponsePrinter.class, ArrayUtil.createArray(responseOptions, responseBody, callSiteArray[52].callGetProperty(System.class), callSiteArray[53].callGetProperty(LogDetail.class), true, blacklistedHeaders));
    }

    public String asString() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[54].callCurrent((GroovyObject)this, false), String.class);
        }
        return this.asString(false);
    }

    public String asPrettyString(ResponseOptions responseOptions, ResponseBody responseBody) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[55].call(callSiteArray[56].callConstructor(Prettifier.class), responseOptions, responseBody), String.class);
    }

    public String asString(boolean forcePlatformDefaultCharsetIfNoCharsetIsSpecifiedInResponse) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[57].callCurrent((GroovyObject)this, callSiteArray[58].callCurrent((GroovyObject)this, forcePlatformDefaultCharsetIfNoCharsetIsSpecifiedInResponse)), String.class);
        }
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[59].callCurrent((GroovyObject)this, this.findCharset(forcePlatformDefaultCharsetIfNoCharsetIsSpecifiedInResponse)), String.class);
    }

    public boolean isInputStream() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return this.content instanceof InputStream;
    }

    public InputStream asInputStream() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(this.content, null) || this.content instanceof InputStream) {
                return (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[60].callConstructor(CloseHTTPClientConnectionInputStreamWrapper.class, callSiteArray[61].call(this.config), this.connectionManager, this.content), InputStream.class);
            }
            return this.content instanceof String ? (ByteArrayInputStream)ScriptBytecodeAdapter.castToType(callSiteArray[62].callConstructor(ByteArrayInputStream.class, callSiteArray[63].callCurrent((GroovyObject)this, this.content)), ByteArrayInputStream.class) : (ByteArrayInputStream)ScriptBytecodeAdapter.castToType(callSiteArray[64].callConstructor(ByteArrayInputStream.class, this.content), ByteArrayInputStream.class);
        }
        if (ScriptBytecodeAdapter.compareEqual(this.content, null) || this.content instanceof InputStream) {
            return (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[65].callConstructor(CloseHTTPClientConnectionInputStreamWrapper.class, callSiteArray[66].call(this.config), this.connectionManager, this.content), InputStream.class);
        }
        return this.content instanceof String ? (ByteArrayInputStream)ScriptBytecodeAdapter.castToType(callSiteArray[67].callConstructor(ByteArrayInputStream.class, callSiteArray[68].callCurrent((GroovyObject)this, this.content)), ByteArrayInputStream.class) : (ByteArrayInputStream)ScriptBytecodeAdapter.castToType(callSiteArray[69].callConstructor(ByteArrayInputStream.class, this.content), ByteArrayInputStream.class);
    }

    public byte[] convertStringToByteArray(Object string) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (byte[])ScriptBytecodeAdapter.castToType(callSiteArray[70].call(string, callSiteArray[71].callCurrent(this)), byte[].class);
        }
        return (byte[])ScriptBytecodeAdapter.castToType(callSiteArray[72].call(string, this.findCharset()), byte[].class);
    }

    public byte[] asByteArray() {
        Object object;
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object2;
            if (ScriptBytecodeAdapter.compareEqual(this.content, null)) {
                return new byte[0];
            }
            if (this.hasExpectations) {
                return (byte[])ScriptBytecodeAdapter.castToType(this.content instanceof byte[] ? this.content : callSiteArray[73].call(this.content, callSiteArray[74].callCurrent(this)), byte[].class);
            }
            if (this.content instanceof byte[]) {
                return (byte[])ScriptBytecodeAdapter.castToType(this.content, byte[].class);
            }
            if (this.content instanceof String) {
                return (byte[])ScriptBytecodeAdapter.castToType(callSiteArray[75].callCurrent((GroovyObject)this, this.content), byte[].class);
            }
            this.content = object2 = callSiteArray[76].callStatic(RestAssuredResponseOptionsGroovyImpl.class, this.content);
            return (byte[])ScriptBytecodeAdapter.castToType(this.content, byte[].class);
        }
        if (ScriptBytecodeAdapter.compareEqual(this.content, null)) {
            return new byte[0];
        }
        if (this.hasExpectations) {
            return (byte[])ScriptBytecodeAdapter.castToType(this.content instanceof byte[] ? this.content : callSiteArray[77].call(this.content, this.findCharset()), byte[].class);
        }
        if (this.content instanceof byte[]) {
            return (byte[])ScriptBytecodeAdapter.castToType(this.content, byte[].class);
        }
        if (this.content instanceof String) {
            return (byte[])ScriptBytecodeAdapter.castToType(callSiteArray[78].callCurrent((GroovyObject)this, this.content), byte[].class);
        }
        this.content = object = callSiteArray[79].callStatic(RestAssuredResponseOptionsGroovyImpl.class, this.content);
        return (byte[])ScriptBytecodeAdapter.castToType(this.content, byte[].class);
    }

    public <T> T as(Type cls, ResponseBodyData responseBodyData) {
        Reference<Type> cls2 = new Reference<Type>(cls);
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Object charset = null;
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object;
            charset = object = callSiteArray[80].callCurrent(this);
        } else {
            String string = this.findCharset();
            charset = string;
        }
        String contentTypeToChose = (String)ScriptBytecodeAdapter.castToType(callSiteArray[81].callCurrent((GroovyObject)this, new GeneratedClosure(this, this, cls2){
            private /* synthetic */ Reference cls;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _as_closure3.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.cls = reference = cls;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _as_closure3.$getCallSiteArray();
                throw (Throwable)callSiteArray[0].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{this.cls.get(), this.cls.get()}, new String[]{"Cannot parse content to ", " because no content-type was present in the response and no default parser has been set.\nYou can specify a default parser using e.g.:\nRestAssured.defaultParser = Parser.JSON;\n\nor you can specify an explicit ObjectMapper using as(", ", <ObjectMapper>);"}));
            }

            public Type getCls() {
                CallSite[] callSiteArray = _as_closure3.$getCallSiteArray();
                return (Type)ScriptBytecodeAdapter.castToType(this.cls.get(), Type.class);
            }

            public Object doCall() {
                CallSite[] callSiteArray = _as_closure3.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _as_closure3.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _as_closure3.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _as_closure3.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "<$constructor$>";
                return new CallSiteArray(_as_closure3.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _as_closure3.$createCallSiteArray();
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
        return (T)callSiteArray[82].call((Object)ObjectMapping.class, ArrayUtil.createArray(responseBodyData, cls2.get(), contentTypeToChose, this.defaultContentType, charset, null, callSiteArray[83].call(this.config)));
    }

    public <T> T as(Type cls, ObjectMapperType mapperType, ResponseBodyData responseBodyData) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[84].callStatic(AssertParameter.class, (Object)mapperType, "Object mapper type");
        Object charset = null;
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object;
            charset = object = callSiteArray[85].callCurrent(this);
        } else {
            String string = this.findCharset();
            charset = string;
        }
        return (T)callSiteArray[86].call((Object)ObjectMapping.class, ArrayUtil.createArray(responseBodyData, cls, null, this.defaultContentType, charset, (Object)mapperType, callSiteArray[87].call(this.config)));
    }

    public <T> T as(Type cls, ObjectMapper mapper) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[88].callStatic(AssertParameter.class, mapper, "Object mapper");
        Object ctx = callSiteArray[89].callCurrent((GroovyObject)this, cls);
        return (T)callSiteArray[90].call((Object)mapper, ctx);
    }

    public <T> T as(TypeRef<T> typeRef, ResponseBodyData responseBodyData) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[91].callStatic(AssertParameter.class, typeRef, "Type ref");
        return (T)callSiteArray[92].callCurrent(this, callSiteArray[93].call(typeRef), responseBodyData);
    }

    public String findCharset() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[94].callCurrent((GroovyObject)this, false), String.class);
        }
        return this.findCharset(false);
    }

    public String findCharset(boolean forcePlatformDefaultCharsetIfNoCharsetIsSpecifiedInResponse) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        String charset = (String)ScriptBytecodeAdapter.castToType(callSiteArray[95].call(CharsetExtractor.class, DefaultTypeTransformation.booleanUnbox(callSiteArray[96].callStatic(StringUtils.class, this.contentType)) ? this.defaultContentType : this.contentType), String.class);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(charset, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[97].call(callSiteArray[98].call(charset), ""))) {
                if (ScriptBytecodeAdapter.compareEqual(this.decoderConfig, null) || forcePlatformDefaultCharsetIfNoCharsetIsSpecifiedInResponse) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[99].call(callSiteArray[100].call(Charset.class)), String.class);
                }
                Object object = callSiteArray[101].call((Object)this.decoderConfig, this.contentType);
                charset = (String)ScriptBytecodeAdapter.castToType(object, String.class);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(charset, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[102].call(callSiteArray[103].call(charset), ""))) {
            if (ScriptBytecodeAdapter.compareEqual(this.decoderConfig, null) || forcePlatformDefaultCharsetIfNoCharsetIsSpecifiedInResponse) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[104].call(callSiteArray[105].call(Charset.class)), String.class);
            }
            Object object = callSiteArray[106].call((Object)this.decoderConfig, this.contentType);
            charset = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[107].call(StringUtils.class, charset, BINARY))) {
            Object object = callSiteArray[108].call((Object)this.decoderConfig, this.contentType);
            charset = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        }
        return charset;
    }

    public Cookies detailedCookies() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(this.cookies, null)) {
                return (Cookies)ScriptBytecodeAdapter.castToType(callSiteArray[109].callConstructor(Cookies.class), Cookies.class);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(this.cookies, null)) {
            return (Cookies)ScriptBytecodeAdapter.castToType(callSiteArray[110].callConstructor(Cookies.class), Cookies.class);
        }
        return this.cookies;
    }

    public Cookies getDetailedCookies() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Cookies)ScriptBytecodeAdapter.castToType(callSiteArray[111].callCurrent(this), Cookies.class);
        }
        return this.detailedCookies();
    }

    public Cookie detailedCookie(String name) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Cookie)ScriptBytecodeAdapter.castToType(callSiteArray[112].call(callSiteArray[113].callCurrent(this), name), Cookie.class);
        }
        return (Cookie)ScriptBytecodeAdapter.castToType(callSiteArray[114].call((Object)this.detailedCookies(), name), Cookie.class);
    }

    public Cookie getDetailedCookie(String name) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Cookie)ScriptBytecodeAdapter.castToType(callSiteArray[115].callCurrent((GroovyObject)this, name), Cookie.class);
        }
        return this.detailedCookie(name);
    }

    public Headers headers() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Object object = this.responseHeaders;
        return (Headers)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox(object) ? object : callSiteArray[116].callConstructor(Headers.class), Headers.class);
    }

    public Headers getHeaders() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Headers)ScriptBytecodeAdapter.castToType(callSiteArray[117].callCurrent(this), Headers.class);
        }
        return this.headers();
    }

    public String header(String name) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[118].callStatic(AssertParameter.class, name, "name");
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[119].call(this.responseHeaders, name), String.class);
    }

    public String getHeader(String name) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[120].callCurrent((GroovyObject)this, name), String.class);
        }
        return this.header(name);
    }

    public Map<String, String> cookies() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Reference<Map> cookieMap = new Reference<Map>(ScriptBytecodeAdapter.createMap(new Object[0]));
        callSiteArray[121].call((Object)this.cookies, new GeneratedClosure(this, this, cookieMap){
            private /* synthetic */ Reference cookieMap;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _cookies_closure4.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.cookieMap = reference = cookieMap;
            }

            public Object doCall(Object cookie) {
                CallSite[] callSiteArray = _cookies_closure4.$getCallSiteArray();
                return callSiteArray[0].call(this.cookieMap.get(), callSiteArray[1].callGetProperty(cookie), callSiteArray[2].callGetProperty(cookie));
            }

            public Object getCookieMap() {
                CallSite[] callSiteArray = _cookies_closure4.$getCallSiteArray();
                return this.cookieMap.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _cookies_closure4.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _cookies_closure4.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _cookies_closure4.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "put";
                stringArray[1] = "name";
                stringArray[2] = "value";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[3];
                _cookies_closure4.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_cookies_closure4.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _cookies_closure4.$createCallSiteArray();
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
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[122].call(Collections.class, cookieMap.get()), Map.class);
    }

    public Map<String, String> getCookies() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[123].callCurrent(this), Map.class);
        }
        return this.cookies();
    }

    public String cookie(String name) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[124].callStatic(AssertParameter.class, name, "name");
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(this.cookies, null) ? null : callSiteArray[125].call((Object)this.cookies, name), String.class);
        }
        return (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual(this.cookies, null) ? null : callSiteArray[126].call((Object)this.cookies, name), String.class);
    }

    public String getCookie(String name) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[127].callCurrent((GroovyObject)this, name), String.class);
        }
        return this.cookie(name);
    }

    public String contentType() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.contentType, String.class);
    }

    public ResponseParserRegistrar getRpr() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return this.rpr;
    }

    public RestAssuredConfig getConfig() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return this.config;
    }

    public String getContentType() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.contentType, String.class);
    }

    public String statusLine() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.statusLine, String.class);
    }

    public int statusCode() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Object object = this.statusCode;
        return DefaultTypeTransformation.intUnbox(DefaultTypeTransformation.booleanUnbox(object) ? object : Integer.valueOf(-1));
    }

    public String getStatusLine() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[128].callCurrent(this), String.class);
        }
        return this.statusLine();
    }

    public String sessionId() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[129].callCurrent(this), String.class);
        }
        return this.getSessionId();
    }

    public String getSessionId() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[130].callCurrent((GroovyObject)this, this.sessionIdName), String.class);
    }

    public int getStatusCode() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return DefaultTypeTransformation.intUnbox(callSiteArray[131].callCurrent(this));
        }
        return this.statusCode();
    }

    public JsonPath jsonPath() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (JsonPath)ScriptBytecodeAdapter.castToType(callSiteArray[132].callCurrent((GroovyObject)this, callSiteArray[133].call(callSiteArray[134].call(callSiteArray[135].call(callSiteArray[136].call(callSiteArray[137].call(callSiteArray[138].callStatic(JsonPathConfig.class), callSiteArray[139].callCurrent(this)), callSiteArray[140].call(callSiteArray[141].call(this.config))), callSiteArray[142].call(callSiteArray[143].call(this.config))), callSiteArray[144].call(callSiteArray[145].call(this.config))), callSiteArray[146].call(callSiteArray[147].call(this.config)))), JsonPath.class);
        }
        return (JsonPath)ScriptBytecodeAdapter.castToType(callSiteArray[148].callCurrent((GroovyObject)this, callSiteArray[149].call(callSiteArray[150].call(callSiteArray[151].call(callSiteArray[152].call(callSiteArray[153].call(callSiteArray[154].callStatic(JsonPathConfig.class), this.findCharset()), callSiteArray[155].call(callSiteArray[156].call(this.config))), callSiteArray[157].call(callSiteArray[158].call(this.config))), callSiteArray[159].call(callSiteArray[160].call(this.config))), callSiteArray[161].call(callSiteArray[162].call(this.config)))), JsonPath.class);
    }

    public JsonPath jsonPath(JsonPathConfig config) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[163].callStatic(AssertParameter.class, config, "JsonPathConfig");
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (JsonPath)ScriptBytecodeAdapter.castToType(callSiteArray[164].call(callSiteArray[165].callConstructor(JsonPath.class, callSiteArray[166].callCurrent(this)), config), JsonPath.class);
        }
        return (JsonPath)ScriptBytecodeAdapter.castToType(callSiteArray[167].call(callSiteArray[168].callConstructor(JsonPath.class, this.asString()), config), JsonPath.class);
    }

    public XmlPath xmlPath() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return (XmlPath)ScriptBytecodeAdapter.castToType(callSiteArray[169].callCurrent((GroovyObject)this, callSiteArray[170].callGetProperty(XmlPath.CompatibilityMode.class)), XmlPath.class);
    }

    public XmlPath xmlPath(XmlPathConfig config) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return (XmlPath)ScriptBytecodeAdapter.castToType(callSiteArray[171].callCurrent(this, callSiteArray[172].callGetProperty(XmlPath.CompatibilityMode.class), config), XmlPath.class);
    }

    public XmlPath xmlPath(XmlPath.CompatibilityMode compatibilityMode) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[173].callStatic(AssertParameter.class, (Object)compatibilityMode, "Compatibility mode");
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (XmlPath)ScriptBytecodeAdapter.castToType(callSiteArray[174].callCurrent((GroovyObject)this, (Object)compatibilityMode), XmlPath.class);
        }
        return (XmlPath)ScriptBytecodeAdapter.castToType(this.newXmlPath(compatibilityMode), XmlPath.class);
    }

    public XmlPath htmlPath() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return (XmlPath)ScriptBytecodeAdapter.castToType(callSiteArray[175].callCurrent((GroovyObject)this, callSiteArray[176].callGetProperty(XmlPath.CompatibilityMode.class)), XmlPath.class);
    }

    public <T> T path(String path, String ... arguments) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[177].callStatic(AssertParameter.class, path, "Path");
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[178].callGetPropertySafe(arguments), 0)) {
                Object object = callSiteArray[179].call(String.class, path, arguments);
                path = (String)ScriptBytecodeAdapter.castToType(object, String.class);
            }
        } else if (ScriptBytecodeAdapter.compareGreaterThan(callSiteArray[180].callGetPropertySafe(arguments), 0)) {
            Object object = callSiteArray[181].call(String.class, path, arguments);
            path = (String)ScriptBytecodeAdapter.castToType(object, String.class);
        }
        Object contentType = callSiteArray[182].callCurrent((GroovyObject)this, new GeneratedClosure(this, this){
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                CallSite[] callSiteArray = _path_closure5.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _path_closure5.$getCallSiteArray();
                throw (Throwable)callSiteArray[0].callConstructor(IllegalStateException.class, "Cannot invoke the path method because no content-type was present in the response and no default parser has been set.\n\nYou can specify a default parser using e.g.:\nRestAssured.defaultParser = Parser.JSON;\n");
            }

            public Object doCall() {
                CallSite[] callSiteArray = _path_closure5.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _path_closure5.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _path_closure5.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _path_closure5.__$swapInit();
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[1];
                stringArray[0] = "<$constructor$>";
                return new CallSiteArray(_path_closure5.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _path_closure5.$createCallSiteArray();
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
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[183].callStatic(StringUtils.class, contentType, "xml"))) {
                return (T)callSiteArray[184].call(callSiteArray[185].callCurrent(this), path);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[186].callStatic(StringUtils.class, contentType, "json"))) {
                return (T)callSiteArray[187].call(callSiteArray[188].callCurrent(this), path);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[189].callStatic(StringUtils.class, contentType, "html"))) {
                return (T)callSiteArray[190].callCurrent((GroovyObject)this, callSiteArray[191].callGetProperty(XmlPath.CompatibilityMode.class));
            }
        } else {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[192].callStatic(StringUtils.class, contentType, "xml"))) {
                return (T)callSiteArray[193].call((Object)this.xmlPath(), path);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[194].callStatic(StringUtils.class, contentType, "json"))) {
                return (T)callSiteArray[195].call((Object)this.jsonPath(), path);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[196].callStatic(StringUtils.class, contentType, "html"))) {
                return (T)callSiteArray[197].callCurrent((GroovyObject)this, callSiteArray[198].callGetProperty(XmlPath.CompatibilityMode.class));
            }
        }
        throw (Throwable)callSiteArray[199].callConstructor(IllegalStateException.class, new GStringImpl(new Object[]{contentType}, new String[]{"Cannot determine which path implementation to use because the content-type ", " doesn't map to a path implementation."}));
    }

    public long time() {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[200].callSafe((Object)this.filterContextProperties, callSiteArray[201].callGetProperty(TimingFilter.class)))) {
            return DefaultTypeTransformation.longUnbox(callSiteArray[202].call((Object)this.filterContextProperties, callSiteArray[203].callGetProperty(TimingFilter.class)));
        }
        return NO_RESPONSE_TIME;
    }

    public long timeIn(TimeUnit timeUnit) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[204].callStatic(AssertParameter.class, (Object)timeUnit, TimeUnit.class);
        Object time = null;
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object;
            time = object = callSiteArray[205].callCurrent(this);
        } else {
            long l = this.time();
            time = l;
        }
        if (!BytecodeInterface8.isOrigL() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(time, NO_RESPONSE_TIME) && ScriptBytecodeAdapter.compareNotEqual((Object)timeUnit, callSiteArray[206].callGetProperty(TimeUnit.class))) {
                Object object;
                time = object = callSiteArray[207].call((Object)timeUnit, time, callSiteArray[208].callGetProperty(TimeUnit.class));
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(time, NO_RESPONSE_TIME) && ScriptBytecodeAdapter.compareNotEqual((Object)timeUnit, callSiteArray[209].callGetProperty(TimeUnit.class))) {
            Object object;
            time = object = callSiteArray[210].call((Object)timeUnit, time, callSiteArray[211].callGetProperty(TimeUnit.class));
        }
        return DefaultTypeTransformation.longUnbox(time);
    }

    private Object convertToByteArray(InputStream stream) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        ByteArrayOutputStream buffer = (ByteArrayOutputStream)ScriptBytecodeAdapter.castToType(callSiteArray[212].callConstructor(ByteArrayOutputStream.class), ByteArrayOutputStream.class);
        int nRead = 0;
        byte[] data = new byte[16384];
        try {
            while (true) {
                Object object = callSiteArray[213].call(stream, data, 0, callSiteArray[214].callGetProperty(data));
                nRead = DefaultTypeTransformation.intUnbox(object);
                if (!ScriptBytecodeAdapter.compareNotEqual(object, -1)) break;
                callSiteArray[215].call(buffer, data, 0, nRead);
            }
            callSiteArray[216].call(buffer);
        }
        catch (Throwable throwable) {
            callSiteArray[218].call(stream);
            throw throwable;
        }
        callSiteArray[217].call(stream);
        return callSiteArray[219].call(buffer);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private String convertToString(Reader reader) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(reader, null) : ScriptBytecodeAdapter.compareEqual(reader, null)) {
            return "";
        }
        Writer writer = (Writer)ScriptBytecodeAdapter.castToType(callSiteArray[220].callConstructor(StringWriter.class), Writer.class);
        char[] buffer = new char[1024];
        try {
            int n = 0;
            while (true) {
                Object object = callSiteArray[221].call((Object)reader, (Object)buffer);
                n = DefaultTypeTransformation.intUnbox(object);
                if (ScriptBytecodeAdapter.compareNotEqual(object, -1)) {
                    callSiteArray[222].call(writer, buffer, 0, n);
                    continue;
                }
                break;
            }
        }
        catch (Throwable throwable) {
            callSiteArray[224].callSafe(reader);
            throw throwable;
        }
        callSiteArray[223].callSafe(reader);
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[225].call(writer), String.class);
    }

    private static byte[] convertStreamToByteArray(InputStream is) throws IOException {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        ByteArrayOutputStream buffer = (ByteArrayOutputStream)ScriptBytecodeAdapter.castToType(callSiteArray[226].callConstructor(ByteArrayOutputStream.class), ByteArrayOutputStream.class);
        try {
            int nRead = 0;
            byte[] data = new byte[16384];
            while (true) {
                Object object = callSiteArray[227].call(is, data, 0, callSiteArray[228].callGetProperty(data));
                nRead = DefaultTypeTransformation.intUnbox(object);
                if (!ScriptBytecodeAdapter.compareNotEqual(object, -1)) break;
                callSiteArray[229].call(buffer, data, 0, nRead);
            }
            callSiteArray[230].call(buffer);
        }
        catch (Throwable throwable) {
            callSiteArray[233].callSafe(buffer);
            callSiteArray[234].callSafe(is);
            throw throwable;
        }
        callSiteArray[231].callSafe(buffer);
        callSiteArray[232].callSafe(is);
        return (byte[])ScriptBytecodeAdapter.castToType(callSiteArray[235].call(buffer), byte[].class);
    }

    private String findContentType(Closure closure) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Object contentTypeToChose = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(this.contentType, "")) {
                if (ScriptBytecodeAdapter.compareNotEqual(this.defaultContentType, null)) {
                    String string = this.defaultContentType;
                    contentTypeToChose = string;
                } else {
                    callSiteArray[236].call(closure);
                }
            } else {
                Object object;
                Object object2;
                contentTypeToChose = DefaultTypeTransformation.booleanUnbox(callSiteArray[237].call((Object)this.rpr, this.contentType)) ? (object2 = callSiteArray[238].callGetProperty(callSiteArray[239].call((Object)this.rpr, this.contentType))) : (object = this.contentType);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(this.contentType, "")) {
            if (ScriptBytecodeAdapter.compareNotEqual(this.defaultContentType, null)) {
                String string = this.defaultContentType;
                contentTypeToChose = string;
            } else {
                callSiteArray[240].call(closure);
            }
        } else {
            Object object;
            Object object3;
            contentTypeToChose = DefaultTypeTransformation.booleanUnbox(callSiteArray[241].call((Object)this.rpr, this.contentType)) ? (object3 = callSiteArray[242].callGetProperty(callSiteArray[243].call((Object)this.rpr, this.contentType))) : (object = this.contentType);
        }
        return (String)ScriptBytecodeAdapter.castToType(contentTypeToChose, String.class);
    }

    private Object newXmlPath(XmlPath.CompatibilityMode xml) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return callSiteArray[244].callCurrent(this, (Object)xml, callSiteArray[245].call(callSiteArray[246].call(callSiteArray[247].call(callSiteArray[248].call(callSiteArray[249].call(callSiteArray[250].callStatic(XmlPathConfig.class), callSiteArray[251].callCurrent(this)), callSiteArray[252].call(callSiteArray[253].call(this.config))), callSiteArray[254].call(callSiteArray[255].call(this.config))), callSiteArray[256].call(callSiteArray[257].call(this.config))), callSiteArray[258].call(callSiteArray[259].call(this.config))));
        }
        return callSiteArray[260].callCurrent(this, (Object)xml, callSiteArray[261].call(callSiteArray[262].call(callSiteArray[263].call(callSiteArray[264].call(callSiteArray[265].call(callSiteArray[266].callStatic(XmlPathConfig.class), this.findCharset()), callSiteArray[267].call(callSiteArray[268].call(this.config))), callSiteArray[269].call(callSiteArray[270].call(this.config))), callSiteArray[271].call(callSiteArray[272].call(this.config))), callSiteArray[273].call(callSiteArray[274].call(this.config))));
    }

    private Object newXmlPath(XmlPath.CompatibilityMode mode, XmlPathConfig config) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        callSiteArray[275].callStatic(AssertParameter.class, config, "XmlPathConfig");
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return callSiteArray[276].call(callSiteArray[277].callConstructor(XmlPath.class, (Object)mode, callSiteArray[278].callCurrent(this)), config);
        }
        return callSiteArray[279].call(callSiteArray[280].callConstructor(XmlPath.class, (Object)mode, this.asString()), config);
    }

    public Object charsetToString(Object charset) {
        Object object;
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(this.content, null) : ScriptBytecodeAdapter.compareEqual(this.content, null)) {
            return "";
        }
        if (this.content instanceof String) {
            return this.content;
        }
        if (this.content instanceof byte[]) {
            return callSiteArray[281].callConstructor(String.class, this.content, charset);
        }
        this.content = object = callSiteArray[282].callStatic(RestAssuredResponseOptionsGroovyImpl.class, this.content);
        return callSiteArray[283].callConstructor(String.class, this.content, charset);
    }

    private ObjectMapperDeserializationContext createObjectMapperDeserializationContext(Type cls) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Object ctx = callSiteArray[284].callConstructor(ObjectMapperDeserializationContextImpl.class);
        Type type = cls;
        ScriptBytecodeAdapter.setProperty(type, null, ctx, "type");
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object = callSiteArray[285].callCurrent(this);
            ScriptBytecodeAdapter.setProperty(object, null, ctx, "charset");
        } else {
            String string = this.findCharset();
            ScriptBytecodeAdapter.setProperty(string, null, ctx, "charset");
        }
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object = callSiteArray[286].callCurrent(this);
            ScriptBytecodeAdapter.setProperty(object, null, ctx, "contentType");
        } else {
            String string = this.contentType();
            ScriptBytecodeAdapter.setProperty(string, null, ctx, "contentType");
        }
        1 var9_9 = new 1(this);
        ScriptBytecodeAdapter.setProperty(var9_9, null, ctx, "dataToDeserialize");
        return (ObjectMapperDeserializationContext)ScriptBytecodeAdapter.castToType(ctx, ObjectMapperDeserializationContext.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RestAssuredResponseOptionsGroovyImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, RestAssuredResponseOptionsGroovyImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(RestAssuredResponseOptionsGroovyImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RestAssuredResponseOptionsGroovyImpl.class) {
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
        CallSite[] callSiteArray = RestAssuredResponseOptionsGroovyImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        RestAssuredResponseOptionsGroovyImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729268 = l2 = 0L;
        __timeStamp = l = 1702030729268L;
        int n = -1;
        NO_RESPONSE_TIME = n;
    }

    public Object getResponseHeaders() {
        return this.responseHeaders;
    }

    public void setResponseHeaders(Object object) {
        this.responseHeaders = object;
    }

    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object object) {
        this.content = object;
    }

    public void setContentType(Object object) {
        this.contentType = object;
    }

    public void setStatusLine(Object object) {
        this.statusLine = object;
    }

    public void setStatusCode(Object object) {
        this.statusCode = object;
    }

    public Object getSessionIdName() {
        return this.sessionIdName;
    }

    public void setSessionIdName(Object object) {
        this.sessionIdName = object;
    }

    public Map getFilterContextProperties() {
        return this.filterContextProperties;
    }

    public void setFilterContextProperties(Map map) {
        this.filterContextProperties = map;
    }

    public Object getConnectionManager() {
        return this.connectionManager;
    }

    public void setConnectionManager(Object object) {
        this.connectionManager = object;
    }

    public String getDefaultContentType() {
        return this.defaultContentType;
    }

    public void setDefaultContentType(String string) {
        this.defaultContentType = string;
    }

    public void setRpr(ResponseParserRegistrar responseParserRegistrar) {
        this.rpr = responseParserRegistrar;
    }

    public DecoderConfig getDecoderConfig() {
        return this.decoderConfig;
    }

    public void setDecoderConfig(DecoderConfig decoderConfig) {
        this.decoderConfig = decoderConfig;
    }

    public boolean getHasExpectations() {
        return this.hasExpectations;
    }

    public boolean isHasExpectations() {
        return this.hasExpectations;
    }

    public void setHasExpectations(boolean bl) {
        this.hasExpectations = bl;
    }

    public void setConfig(RestAssuredConfig restAssuredConfig) {
        this.config = restAssuredConfig;
    }

    public /* synthetic */ Object this$2$parseContent(Object object) {
        return this.parseContent(object);
    }

    public /* synthetic */ Object this$2$convertToByteArray(InputStream inputStream) {
        return this.convertToByteArray(inputStream);
    }

    public /* synthetic */ String this$2$convertToString(Reader reader) {
        return this.convertToString(reader);
    }

    public /* synthetic */ String this$2$findContentType(Closure closure) {
        return this.findContentType(closure);
    }

    public /* synthetic */ Object this$2$newXmlPath(XmlPath.CompatibilityMode compatibilityMode) {
        return this.newXmlPath(compatibilityMode);
    }

    public /* synthetic */ Object this$2$newXmlPath(XmlPath.CompatibilityMode compatibilityMode, XmlPathConfig xmlPathConfig) {
        return this.newXmlPath(compatibilityMode, xmlPathConfig);
    }

    public /* synthetic */ ObjectMapperDeserializationContext this$2$createObjectMapperDeserializationContext(Type type) {
        return this.createObjectMapperDeserializationContext(type);
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
        stringArray[0] = "parseHeaders";
        stringArray[1] = "parseContentType";
        stringArray[2] = "parseCookies";
        stringArray[3] = "parseStatus";
        stringArray[4] = "parseContent";
        stringArray[5] = "getContentType";
        stringArray[6] = "defaultParser";
        stringArray[7] = "toString";
        stringArray[8] = "statusLine";
        stringArray[9] = "statusCode";
        stringArray[10] = "statusLine";
        stringArray[11] = "toString";
        stringArray[12] = "contentType";
        stringArray[13] = "hasHeaderWithName";
        stringArray[14] = "headers";
        stringArray[15] = "getCookies";
        stringArray[16] = "getValues";
        stringArray[17] = "headers";
        stringArray[18] = "each";
        stringArray[19] = "headers";
        stringArray[20] = "<$constructor$>";
        stringArray[21] = "convertToByteArray";
        stringArray[22] = "toString";
        stringArray[23] = "convertToString";
        stringArray[24] = "<$constructor$>";
        stringArray[25] = "<$constructor$>";
        stringArray[26] = "leftShift";
        stringArray[27] = "bind";
        stringArray[28] = "<$constructor$>";
        stringArray[29] = "toString";
        stringArray[30] = "asString";
        stringArray[31] = "println";
        stringArray[32] = "asPrettyString";
        stringArray[33] = "println";
        stringArray[34] = "blacklistedHeaders";
        stringArray[35] = "logConfig";
        stringArray[36] = "getConfig";
        stringArray[37] = "emptySet";
        stringArray[38] = "blacklistedHeaders";
        stringArray[39] = "logConfig";
        stringArray[40] = "emptySet";
        stringArray[41] = "print";
        stringArray[42] = "out";
        stringArray[43] = "ALL";
        stringArray[44] = "blacklistedHeaders";
        stringArray[45] = "logConfig";
        stringArray[46] = "getConfig";
        stringArray[47] = "emptySet";
        stringArray[48] = "blacklistedHeaders";
        stringArray[49] = "logConfig";
        stringArray[50] = "emptySet";
        stringArray[51] = "print";
        stringArray[52] = "out";
        stringArray[53] = "ALL";
        stringArray[54] = "asString";
        stringArray[55] = "getPrettifiedBodyIfPossible";
        stringArray[56] = "<$constructor$>";
        stringArray[57] = "charsetToString";
        stringArray[58] = "findCharset";
        stringArray[59] = "charsetToString";
        stringArray[60] = "<$constructor$>";
        stringArray[61] = "getConnectionConfig";
        stringArray[62] = "<$constructor$>";
        stringArray[63] = "convertStringToByteArray";
        stringArray[64] = "<$constructor$>";
        stringArray[65] = "<$constructor$>";
        stringArray[66] = "getConnectionConfig";
        stringArray[67] = "<$constructor$>";
        stringArray[68] = "convertStringToByteArray";
        stringArray[69] = "<$constructor$>";
        stringArray[70] = "getBytes";
        stringArray[71] = "findCharset";
        stringArray[72] = "getBytes";
        stringArray[73] = "getBytes";
        stringArray[74] = "findCharset";
        stringArray[75] = "convertStringToByteArray";
        stringArray[76] = "convertStreamToByteArray";
        stringArray[77] = "getBytes";
        stringArray[78] = "convertStringToByteArray";
        stringArray[79] = "convertStreamToByteArray";
        stringArray[80] = "findCharset";
        stringArray[81] = "findContentType";
        stringArray[82] = "deserialize";
        stringArray[83] = "getObjectMapperConfig";
        stringArray[84] = "notNull";
        stringArray[85] = "findCharset";
        stringArray[86] = "deserialize";
        stringArray[87] = "getObjectMapperConfig";
        stringArray[88] = "notNull";
        stringArray[89] = "createObjectMapperDeserializationContext";
        stringArray[90] = "deserialize";
        stringArray[91] = "notNull";
        stringArray[92] = "as";
        stringArray[93] = "getType";
        stringArray[94] = "findCharset";
        stringArray[95] = "getCharsetFromContentType";
        stringArray[96] = "isBlank";
        stringArray[97] = "equals";
        stringArray[98] = "trim";
        stringArray[99] = "toString";
        stringArray[100] = "defaultCharset";
        stringArray[101] = "defaultCharsetForContentType";
        stringArray[102] = "equals";
        stringArray[103] = "trim";
        stringArray[104] = "toString";
        stringArray[105] = "defaultCharset";
        stringArray[106] = "defaultCharsetForContentType";
        stringArray[107] = "equalsIgnoreCase";
        stringArray[108] = "defaultCharsetForContentType";
        stringArray[109] = "<$constructor$>";
        stringArray[110] = "<$constructor$>";
        stringArray[111] = "detailedCookies";
        stringArray[112] = "get";
        stringArray[113] = "detailedCookies";
        stringArray[114] = "get";
        stringArray[115] = "detailedCookie";
        stringArray[116] = "<$constructor$>";
        stringArray[117] = "headers";
        stringArray[118] = "notNull";
        stringArray[119] = "getValue";
        stringArray[120] = "header";
        stringArray[121] = "each";
        stringArray[122] = "unmodifiableMap";
        stringArray[123] = "cookies";
        stringArray[124] = "notNull";
        stringArray[125] = "getValue";
        stringArray[126] = "getValue";
        stringArray[127] = "cookie";
        stringArray[128] = "statusLine";
        stringArray[129] = "getSessionId";
        stringArray[130] = "cookie";
        stringArray[131] = "statusCode";
        stringArray[132] = "jsonPath";
        stringArray[133] = "numberReturnType";
        stringArray[134] = "gsonObjectMapperFactory";
        stringArray[135] = "jackson2ObjectMapperFactory";
        stringArray[136] = "jackson1ObjectMapperFactory";
        stringArray[137] = "charset";
        stringArray[138] = "jsonPathConfig";
        stringArray[139] = "findCharset";
        stringArray[140] = "jackson1ObjectMapperFactory";
        stringArray[141] = "getObjectMapperConfig";
        stringArray[142] = "jackson2ObjectMapperFactory";
        stringArray[143] = "getObjectMapperConfig";
        stringArray[144] = "gsonObjectMapperFactory";
        stringArray[145] = "getObjectMapperConfig";
        stringArray[146] = "numberReturnType";
        stringArray[147] = "getJsonConfig";
        stringArray[148] = "jsonPath";
        stringArray[149] = "numberReturnType";
        stringArray[150] = "gsonObjectMapperFactory";
        stringArray[151] = "jackson2ObjectMapperFactory";
        stringArray[152] = "jackson1ObjectMapperFactory";
        stringArray[153] = "charset";
        stringArray[154] = "jsonPathConfig";
        stringArray[155] = "jackson1ObjectMapperFactory";
        stringArray[156] = "getObjectMapperConfig";
        stringArray[157] = "jackson2ObjectMapperFactory";
        stringArray[158] = "getObjectMapperConfig";
        stringArray[159] = "gsonObjectMapperFactory";
        stringArray[160] = "getObjectMapperConfig";
        stringArray[161] = "numberReturnType";
        stringArray[162] = "getJsonConfig";
        stringArray[163] = "notNull";
        stringArray[164] = "using";
        stringArray[165] = "<$constructor$>";
        stringArray[166] = "asString";
        stringArray[167] = "using";
        stringArray[168] = "<$constructor$>";
        stringArray[169] = "xmlPath";
        stringArray[170] = "XML";
        stringArray[171] = "newXmlPath";
        stringArray[172] = "XML";
        stringArray[173] = "notNull";
        stringArray[174] = "newXmlPath";
        stringArray[175] = "xmlPath";
        stringArray[176] = "HTML";
        stringArray[177] = "notNull";
        stringArray[178] = "length";
        stringArray[179] = "format";
        stringArray[180] = "length";
        stringArray[181] = "format";
        stringArray[182] = "findContentType";
        stringArray[183] = "containsIgnoreCase";
        stringArray[184] = "get";
        stringArray[185] = "xmlPath";
        stringArray[186] = "containsIgnoreCase";
        stringArray[187] = "get";
        stringArray[188] = "jsonPath";
        stringArray[189] = "containsIgnoreCase";
        stringArray[190] = "newXmlPath";
        stringArray[191] = "HTML";
        stringArray[192] = "containsIgnoreCase";
        stringArray[193] = "get";
        stringArray[194] = "containsIgnoreCase";
        stringArray[195] = "get";
        stringArray[196] = "containsIgnoreCase";
        stringArray[197] = "newXmlPath";
        stringArray[198] = "HTML";
        stringArray[199] = "<$constructor$>";
        stringArray[200] = "containsKey";
        stringArray[201] = "RESPONSE_TIME_MILLISECONDS";
        stringArray[202] = "get";
        stringArray[203] = "RESPONSE_TIME_MILLISECONDS";
        stringArray[204] = "notNull";
        stringArray[205] = "time";
        stringArray[206] = "MILLISECONDS";
        stringArray[207] = "convert";
        stringArray[208] = "MILLISECONDS";
        stringArray[209] = "MILLISECONDS";
        stringArray[210] = "convert";
        stringArray[211] = "MILLISECONDS";
        stringArray[212] = "<$constructor$>";
        stringArray[213] = "read";
        stringArray[214] = "length";
        stringArray[215] = "write";
        stringArray[216] = "flush";
        stringArray[217] = "close";
        stringArray[218] = "close";
        stringArray[219] = "toByteArray";
        stringArray[220] = "<$constructor$>";
        stringArray[221] = "read";
        stringArray[222] = "write";
        stringArray[223] = "close";
        stringArray[224] = "close";
        stringArray[225] = "toString";
        stringArray[226] = "<$constructor$>";
        stringArray[227] = "read";
        stringArray[228] = "length";
        stringArray[229] = "write";
        stringArray[230] = "flush";
        stringArray[231] = "close";
        stringArray[232] = "close";
        stringArray[233] = "close";
        stringArray[234] = "close";
        stringArray[235] = "toByteArray";
        stringArray[236] = "call";
        stringArray[237] = "hasCustomParserExcludingDefaultParser";
        stringArray[238] = "contentType";
        stringArray[239] = "getNonDefaultParser";
        stringArray[240] = "call";
        stringArray[241] = "hasCustomParserExcludingDefaultParser";
        stringArray[242] = "contentType";
        stringArray[243] = "getNonDefaultParser";
        stringArray[244] = "newXmlPath";
        stringArray[245] = "jaxbObjectMapperFactory";
        stringArray[246] = "declareNamespaces";
        stringArray[247] = "properties";
        stringArray[248] = "features";
        stringArray[249] = "charset";
        stringArray[250] = "xmlPathConfig";
        stringArray[251] = "findCharset";
        stringArray[252] = "features";
        stringArray[253] = "getXmlConfig";
        stringArray[254] = "properties";
        stringArray[255] = "getXmlConfig";
        stringArray[256] = "declaredNamespaces";
        stringArray[257] = "getXmlConfig";
        stringArray[258] = "jaxbObjectMapperFactory";
        stringArray[259] = "getObjectMapperConfig";
        stringArray[260] = "newXmlPath";
        stringArray[261] = "jaxbObjectMapperFactory";
        stringArray[262] = "declareNamespaces";
        stringArray[263] = "properties";
        stringArray[264] = "features";
        stringArray[265] = "charset";
        stringArray[266] = "xmlPathConfig";
        stringArray[267] = "features";
        stringArray[268] = "getXmlConfig";
        stringArray[269] = "properties";
        stringArray[270] = "getXmlConfig";
        stringArray[271] = "declaredNamespaces";
        stringArray[272] = "getXmlConfig";
        stringArray[273] = "jaxbObjectMapperFactory";
        stringArray[274] = "getObjectMapperConfig";
        stringArray[275] = "notNull";
        stringArray[276] = "using";
        stringArray[277] = "<$constructor$>";
        stringArray[278] = "asString";
        stringArray[279] = "using";
        stringArray[280] = "<$constructor$>";
        stringArray[281] = "<$constructor$>";
        stringArray[282] = "convertStreamToByteArray";
        stringArray[283] = "<$constructor$>";
        stringArray[284] = "<$constructor$>";
        stringArray[285] = "findCharset";
        stringArray[286] = "contentType";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[287];
        RestAssuredResponseOptionsGroovyImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(RestAssuredResponseOptionsGroovyImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = RestAssuredResponseOptionsGroovyImpl.$createCallSiteArray();
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

    public class 1
    implements DataToDeserialize,
    GroovyObject {
        public /* synthetic */ RestAssuredResponseOptionsGroovyImpl this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 1(RestAssuredResponseOptionsGroovyImpl p0) {
            MetaClass metaClass;
            RestAssuredResponseOptionsGroovyImpl restAssuredResponseOptionsGroovyImpl;
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            this.this$0 = restAssuredResponseOptionsGroovyImpl = p0;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        public String asString() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[0].call(this.this$0), String.class);
        }

        public byte[] asByteArray() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return (byte[])ScriptBytecodeAdapter.castToType(callSiteArray[1].call(this.this$0), byte[].class);
        }

        public InputStream asInputStream() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[2].call(this.this$0), InputStream.class);
        }

        public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$1(String name, Object value) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$1(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return this.this$0.this$dist$invoke$1(name, args);
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            this.this$0.this$dist$set$1(name, val);
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return this.this$0.this$dist$get$1(name);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 1.class) {
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
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            1.__$swapInit();
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
            stringArray[0] = "asString";
            stringArray[1] = "asByteArray";
            stringArray[2] = "asInputStream";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[3];
            1.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(1.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 1.$createCallSiteArray();
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

