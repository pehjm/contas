/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.multipart;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.specification.MultiPartSpecification;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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
public class MultiPartSpecificationImpl
implements MultiPartSpecification,
GroovyObject {
    private static final String NONE = "<none>";
    private static final String INPUT_STREAM = "<inputstream>";
    private Object content;
    private String controlName;
    private String mimeType;
    private String charset;
    private String fileName;
    private boolean controlNameSpecifiedExplicitly;
    private boolean fileNameSpecifiedExplicitly;
    private Map<String, String> headers;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729266;
    private static /* synthetic */ SoftReference $callSiteArray;

    public MultiPartSpecificationImpl() {
        MetaClass metaClass;
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    @Override
    public Object getContent() {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        return this.content;
    }

    @Override
    public String getControlName() {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        return this.controlName;
    }

    @Override
    public String getMimeType() {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        return this.mimeType;
    }

    @Override
    public String getCharset() {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        return this.charset;
    }

    @Override
    public String getFileName() {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        return this.fileName;
    }

    @Override
    public Map<String, String> getHeaders() {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType(callSiteArray[0].call(Collections.class, this.headers), Map.class);
    }

    @Override
    public boolean hasFileName() {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareNotEqual(this.fileName, null);
        }
        return ScriptBytecodeAdapter.compareNotEqual(this.fileName, null);
    }

    public void setFileName(String fileName) {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        Object object = callSiteArray[1].call(StringUtils.class, fileName);
        this.fileName = (String)ScriptBytecodeAdapter.castToType(object, String.class);
    }

    public String toString() {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        Object[] objectArray = new Object[6];
        String string = this.controlName;
        objectArray[0] = DefaultTypeTransformation.booleanUnbox(string) ? string : NONE;
        String string2 = this.mimeType;
        objectArray[1] = DefaultTypeTransformation.booleanUnbox(string2) ? string2 : NONE;
        String string3 = this.charset;
        objectArray[2] = DefaultTypeTransformation.booleanUnbox(string3) ? string3 : NONE;
        String string4 = this.fileName;
        objectArray[3] = DefaultTypeTransformation.booleanUnbox(string4) ? string4 : NONE;
        objectArray[4] = this.content instanceof InputStream ? INPUT_STREAM : this.content;
        objectArray[5] = this.headers;
        return (String)ScriptBytecodeAdapter.castToType(new GStringImpl(objectArray, new String[]{"controlName=", ", mimeType=", ", charset=", ", fileName=", ", content=", ", headers=", ""}), String.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(MultiPartSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, MultiPartSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(MultiPartSpecificationImpl.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != MultiPartSpecificationImpl.class) {
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
        CallSite[] callSiteArray = MultiPartSpecificationImpl.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        MultiPartSpecificationImpl.__$swapInit();
        __timeStamp__239_neverHappen1702030729266 = l2 = 0L;
        __timeStamp = l = 1702030729266L;
    }

    public void setContent(Object object) {
        this.content = object;
    }

    public void setControlName(String string) {
        this.controlName = string;
    }

    public void setMimeType(String string) {
        this.mimeType = string;
    }

    public void setCharset(String string) {
        this.charset = string;
    }

    public boolean getControlNameSpecifiedExplicitly() {
        return this.controlNameSpecifiedExplicitly;
    }

    public boolean isControlNameSpecifiedExplicitly() {
        return this.controlNameSpecifiedExplicitly;
    }

    public void setControlNameSpecifiedExplicitly(boolean bl) {
        this.controlNameSpecifiedExplicitly = bl;
    }

    public boolean getFileNameSpecifiedExplicitly() {
        return this.fileNameSpecifiedExplicitly;
    }

    public boolean isFileNameSpecifiedExplicitly() {
        return this.fileNameSpecifiedExplicitly;
    }

    public void setFileNameSpecifiedExplicitly(boolean bl) {
        this.fileNameSpecifiedExplicitly = bl;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
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
        stringArray[0] = "unmodifiableMap";
        stringArray[1] = "trimToNull";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[2];
        MultiPartSpecificationImpl.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(MultiPartSpecificationImpl.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = MultiPartSpecificationImpl.$createCallSiteArray();
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

