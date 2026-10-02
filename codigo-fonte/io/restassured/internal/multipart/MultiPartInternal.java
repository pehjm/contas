/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.multipart;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.NoParameterValue;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.nio.charset.Charset;
import java.util.Map;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MultiPartInternal
implements GroovyObject {
    public static final String OCTET_STREAM = "application/octet-stream";
    private static final String TEXT_PLAIN = "text/plain";
    private Object content;
    private String controlName;
    private String fileName;
    private String mimeType;
    private String charset;
    private Map<String, String> headers;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729366;
    private static /* synthetic */ SoftReference $callSiteArray;

    public MultiPartInternal() {
        MetaClass metaClass;
        Map map;
        CallSite[] callSiteArray = MultiPartInternal.$getCallSiteArray();
        this.headers = map = ScriptBytecodeAdapter.createMap(new Object[0]);
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public Object getContentBody() {
        CallSite[] callSiteArray = MultiPartInternal.$getCallSiteArray();
        if (this.content instanceof NoParameterValue) {
            String string = "";
            this.content = string;
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (this.content instanceof File) {
                String string = this.mimeType;
                return callSiteArray[0].callConstructor(FileBody.class, this.content, this.fileName, DefaultTypeTransformation.booleanUnbox(string) ? string : OCTET_STREAM, this.charset);
            }
            if (this.content instanceof InputStream) {
                return callSiteArray[1].callCurrent(this);
            }
            if (this.content instanceof byte[]) {
                Object object;
                this.content = object = callSiteArray[2].callConstructor(ByteArrayInputStream.class, this.content);
                return callSiteArray[3].callCurrent(this);
            }
            if (this.content instanceof String) {
                return callSiteArray[4].callCurrent((GroovyObject)this, this.content);
            }
            if (ScriptBytecodeAdapter.compareNotEqual(this.content, null)) {
                return callSiteArray[5].callCurrent((GroovyObject)this, callSiteArray[6].call(this.content));
            }
            throw (Throwable)callSiteArray[7].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{this.content}, new String[]{"Illegal content: ", ""}));
        }
        if (this.content instanceof File) {
            String string = this.mimeType;
            return callSiteArray[8].callConstructor(FileBody.class, this.content, this.fileName, DefaultTypeTransformation.booleanUnbox(string) ? string : OCTET_STREAM, this.charset);
        }
        if (this.content instanceof InputStream) {
            return this.returnInputStreamBody();
        }
        if (this.content instanceof byte[]) {
            Object object;
            this.content = object = callSiteArray[9].callConstructor(ByteArrayInputStream.class, this.content);
            return this.returnInputStreamBody();
        }
        if (this.content instanceof String) {
            return callSiteArray[10].callCurrent((GroovyObject)this, this.content);
        }
        if (ScriptBytecodeAdapter.compareNotEqual(this.content, null)) {
            return callSiteArray[11].callCurrent((GroovyObject)this, callSiteArray[12].call(this.content));
        }
        throw (Throwable)callSiteArray[13].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{this.content}, new String[]{"Illegal content: ", ""}));
    }

    public String getMimeType() {
        CallSite[] callSiteArray = MultiPartInternal.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (this.content instanceof File) {
                String string = this.mimeType;
                return DefaultTypeTransformation.booleanUnbox(string) ? string : OCTET_STREAM;
            }
            if (this.content instanceof InputStream) {
                String string = this.mimeType;
                return DefaultTypeTransformation.booleanUnbox(string) ? string : OCTET_STREAM;
            }
            if (this.content instanceof byte[]) {
                String string = this.mimeType;
                return DefaultTypeTransformation.booleanUnbox(string) ? string : OCTET_STREAM;
            }
            if (this.content instanceof String) {
                String string = this.mimeType;
                return DefaultTypeTransformation.booleanUnbox(string) ? string : TEXT_PLAIN;
            }
            if (ScriptBytecodeAdapter.compareNotEqual(this.content, null)) {
                String string = this.mimeType;
                return DefaultTypeTransformation.booleanUnbox(string) ? string : TEXT_PLAIN;
            }
            return this.mimeType;
        }
        if (this.content instanceof File) {
            String string = this.mimeType;
            return DefaultTypeTransformation.booleanUnbox(string) ? string : OCTET_STREAM;
        }
        if (this.content instanceof InputStream) {
            String string = this.mimeType;
            return DefaultTypeTransformation.booleanUnbox(string) ? string : OCTET_STREAM;
        }
        if (this.content instanceof byte[]) {
            String string = this.mimeType;
            return DefaultTypeTransformation.booleanUnbox(string) ? string : OCTET_STREAM;
        }
        if (this.content instanceof String) {
            String string = this.mimeType;
            return DefaultTypeTransformation.booleanUnbox(string) ? string : TEXT_PLAIN;
        }
        if (ScriptBytecodeAdapter.compareNotEqual(this.content, null)) {
            String string = this.mimeType;
            return DefaultTypeTransformation.booleanUnbox(string) ? string : TEXT_PLAIN;
        }
        return this.mimeType;
    }

    private Object returnStringBody(String content) {
        CallSite[] callSiteArray = MultiPartInternal.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            String string = this.mimeType;
            return callSiteArray[14].callConstructor(StringBody.class, content, DefaultTypeTransformation.booleanUnbox(string) ? string : TEXT_PLAIN, ScriptBytecodeAdapter.compareEqual(this.charset, null) ? null : callSiteArray[15].call(Charset.class, this.charset));
        }
        String string = this.mimeType;
        return callSiteArray[16].callConstructor(StringBody.class, content, DefaultTypeTransformation.booleanUnbox(string) ? string : TEXT_PLAIN, ScriptBytecodeAdapter.compareEqual(this.charset, null) ? null : callSiteArray[17].call(Charset.class, this.charset));
    }

    private Object returnInputStreamBody() {
        CallSite[] callSiteArray = MultiPartInternal.$getCallSiteArray();
        String string = this.mimeType;
        return callSiteArray[18].callConstructor(InputStreamBody.class, this.content, DefaultTypeTransformation.booleanUnbox(string) ? string : OCTET_STREAM, this.fileName);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = MultiPartInternal.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(MultiPartInternal.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = MultiPartInternal.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, MultiPartInternal.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = MultiPartInternal.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(MultiPartInternal.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != MultiPartInternal.class) {
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
        CallSite[] callSiteArray = MultiPartInternal.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        MultiPartInternal.__$swapInit();
        __timeStamp__239_neverHappen1702030729366 = l2 = 0L;
        __timeStamp = l = 1702030729366L;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object object) {
        this.content = object;
    }

    public String getControlName() {
        return this.controlName;
    }

    public void setControlName(String string) {
        this.controlName = string;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String string) {
        this.fileName = string;
    }

    public void setMimeType(String string) {
        this.mimeType = string;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String string) {
        this.charset = string;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public /* synthetic */ Object this$2$returnStringBody(String string) {
        return this.returnStringBody(string);
    }

    public /* synthetic */ Object this$2$returnInputStreamBody() {
        return this.returnInputStreamBody();
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
        stringArray[1] = "returnInputStreamBody";
        stringArray[2] = "<$constructor$>";
        stringArray[3] = "returnInputStreamBody";
        stringArray[4] = "returnStringBody";
        stringArray[5] = "returnStringBody";
        stringArray[6] = "toString";
        stringArray[7] = "<$constructor$>";
        stringArray[8] = "<$constructor$>";
        stringArray[9] = "<$constructor$>";
        stringArray[10] = "returnStringBody";
        stringArray[11] = "returnStringBody";
        stringArray[12] = "toString";
        stringArray[13] = "<$constructor$>";
        stringArray[14] = "<$constructor$>";
        stringArray[15] = "forName";
        stringArray[16] = "<$constructor$>";
        stringArray[17] = "forName";
        stringArray[18] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[19];
        MultiPartInternal.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(MultiPartInternal.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = MultiPartInternal.$createCallSiteArray();
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

