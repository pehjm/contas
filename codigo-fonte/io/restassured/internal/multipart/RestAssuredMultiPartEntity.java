/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.multipart;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.common.assertion.AssertParameter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.nio.charset.Charset;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class RestAssuredMultiPartEntity
implements HttpEntity,
GroovyObject {
    private static final char[] MULTIPART_CHARS;
    private final MultipartEntityBuilder builder;
    private volatile HttpEntity entity;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729364;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public RestAssuredMultiPartEntity(String string, String string2, HttpMultipartMode httpMultipartMode, String string3) {
        void boundary;
        void charset;
        void mode;
        void subType;
        MetaClass metaClass;
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        callSiteArray[0].callStatic(AssertParameter.class, subType, "Multipart sub type");
        if (!BytecodeInterface8.isOrigZ() || BytecodeInterface8.disabledStandardMetaClass()) {
            Object object = callSiteArray[1].call(callSiteArray[2].call(callSiteArray[3].call(callSiteArray[4].call(callSiteArray[5].call(MultipartEntityBuilder.class), mode), ScriptBytecodeAdapter.compareEqual(charset, null) ? null : callSiteArray[6].call(Charset.class, callSiteArray[7].call(charset))), callSiteArray[8].call(subType)), boundary);
            this.builder = (MultipartEntityBuilder)ScriptBytecodeAdapter.castToType(object, MultipartEntityBuilder.class);
        } else {
            Object object = callSiteArray[9].call(callSiteArray[10].call(callSiteArray[11].call(callSiteArray[12].call(callSiteArray[13].call(MultipartEntityBuilder.class), mode), ScriptBytecodeAdapter.compareEqual(charset, null) ? null : callSiteArray[14].call(Charset.class, callSiteArray[15].call(charset))), callSiteArray[16].call(subType)), boundary);
            this.builder = (MultipartEntityBuilder)ScriptBytecodeAdapter.castToType(object, MultipartEntityBuilder.class);
        }
        Object var9_9 = null;
        this.entity = (HttpEntity)ScriptBytecodeAdapter.castToType(var9_9, HttpEntity.class);
    }

    private HttpEntity getEntity() {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(this.entity, null)) {
                Object object = callSiteArray[17].call(this.builder);
                this.entity = (HttpEntity)ScriptBytecodeAdapter.castToType(object, HttpEntity.class);
            }
        } else if (ScriptBytecodeAdapter.compareEqual(this.entity, null)) {
            Object object = callSiteArray[18].call(this.builder);
            this.entity = (HttpEntity)ScriptBytecodeAdapter.castToType(object, HttpEntity.class);
        }
        return this.entity;
    }

    public void addPart(FormBodyPart bodyPart) {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        callSiteArray[19].call((Object)this.builder, bodyPart);
        Object var3_3 = null;
        this.entity = (HttpEntity)ScriptBytecodeAdapter.castToType(var3_3, HttpEntity.class);
    }

    public void addPart(String name, ContentBody contentBody) {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        callSiteArray[20].callCurrent((GroovyObject)this, callSiteArray[21].callConstructor(FormBodyPart.class, name, contentBody));
    }

    public boolean isRepeatable() {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return DefaultTypeTransformation.booleanUnbox(callSiteArray[22].call(callSiteArray[23].callCurrent(this)));
        }
        return DefaultTypeTransformation.booleanUnbox(callSiteArray[24].call(this.getEntity()));
    }

    public boolean isChunked() {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return DefaultTypeTransformation.booleanUnbox(callSiteArray[25].call(callSiteArray[26].callCurrent(this)));
        }
        return DefaultTypeTransformation.booleanUnbox(callSiteArray[27].call(this.getEntity()));
    }

    public boolean isStreaming() {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return DefaultTypeTransformation.booleanUnbox(callSiteArray[28].call(callSiteArray[29].callCurrent(this)));
        }
        return DefaultTypeTransformation.booleanUnbox(callSiteArray[30].call(this.getEntity()));
    }

    public long getContentLength() {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return DefaultTypeTransformation.longUnbox(callSiteArray[31].call(callSiteArray[32].callCurrent(this)));
        }
        return DefaultTypeTransformation.longUnbox(callSiteArray[33].call(this.getEntity()));
    }

    public Header getContentType() {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Header)ScriptBytecodeAdapter.castToType(callSiteArray[34].call(callSiteArray[35].callCurrent(this)), Header.class);
        }
        return (Header)ScriptBytecodeAdapter.castToType(callSiteArray[36].call(this.getEntity()), Header.class);
    }

    public Header getContentEncoding() {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Header)ScriptBytecodeAdapter.castToType(callSiteArray[37].call(callSiteArray[38].callCurrent(this)), Header.class);
        }
        return (Header)ScriptBytecodeAdapter.castToType(callSiteArray[39].call(this.getEntity()), Header.class);
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[40].callCurrent(this))) {
                throw (Throwable)callSiteArray[41].callConstructor(UnsupportedOperationException.class, "Streaming entity does not implement #consumeContent()");
            }
        } else if (this.isStreaming()) {
            throw (Throwable)callSiteArray[42].callConstructor(UnsupportedOperationException.class, "Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        throw (Throwable)callSiteArray[43].callConstructor(UnsupportedOperationException.class, "Multipart form entity does not implement #getContent()");
    }

    public void writeTo(OutputStream outstream) throws IOException {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            callSiteArray[44].call(callSiteArray[45].callCurrent(this), outstream);
        } else {
            callSiteArray[46].call((Object)this.getEntity(), outstream);
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RestAssuredMultiPartEntity.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, RestAssuredMultiPartEntity.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(RestAssuredMultiPartEntity.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RestAssuredMultiPartEntity.class) {
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
        CallSite[] callSiteArray = RestAssuredMultiPartEntity.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        RestAssuredMultiPartEntity.__$swapInit();
        __timeStamp__239_neverHappen1702030729364 = l2 = 0L;
        __timeStamp = l = 1702030729364L;
        Object object = RestAssuredMultiPartEntity.$getCallSiteArray()[47].call("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        MULTIPART_CHARS = (char[])ScriptBytecodeAdapter.castToType(object, char[].class);
    }

    public /* synthetic */ HttpEntity this$2$getEntity() {
        return this.getEntity();
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
        stringArray[1] = "setBoundary";
        stringArray[2] = "setMimeSubtype";
        stringArray[3] = "setCharset";
        stringArray[4] = "setMode";
        stringArray[5] = "create";
        stringArray[6] = "forName";
        stringArray[7] = "trim";
        stringArray[8] = "trim";
        stringArray[9] = "setBoundary";
        stringArray[10] = "setMimeSubtype";
        stringArray[11] = "setCharset";
        stringArray[12] = "setMode";
        stringArray[13] = "create";
        stringArray[14] = "forName";
        stringArray[15] = "trim";
        stringArray[16] = "trim";
        stringArray[17] = "build";
        stringArray[18] = "build";
        stringArray[19] = "addPart";
        stringArray[20] = "addPart";
        stringArray[21] = "<$constructor$>";
        stringArray[22] = "isRepeatable";
        stringArray[23] = "getEntity";
        stringArray[24] = "isRepeatable";
        stringArray[25] = "isChunked";
        stringArray[26] = "getEntity";
        stringArray[27] = "isChunked";
        stringArray[28] = "isStreaming";
        stringArray[29] = "getEntity";
        stringArray[30] = "isStreaming";
        stringArray[31] = "getContentLength";
        stringArray[32] = "getEntity";
        stringArray[33] = "getContentLength";
        stringArray[34] = "getContentType";
        stringArray[35] = "getEntity";
        stringArray[36] = "getContentType";
        stringArray[37] = "getContentEncoding";
        stringArray[38] = "getEntity";
        stringArray[39] = "getContentEncoding";
        stringArray[40] = "isStreaming";
        stringArray[41] = "<$constructor$>";
        stringArray[42] = "<$constructor$>";
        stringArray[43] = "<$constructor$>";
        stringArray[44] = "writeTo";
        stringArray[45] = "getEntity";
        stringArray[46] = "writeTo";
        stringArray[47] = "toCharArray";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[48];
        RestAssuredMultiPartEntity.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(RestAssuredMultiPartEntity.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = RestAssuredMultiPartEntity.$createCallSiteArray();
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

