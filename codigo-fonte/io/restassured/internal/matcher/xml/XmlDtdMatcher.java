/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.matcher.xml;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.matcher.xml.LoadFromClasspathSupport;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class XmlDtdMatcher
extends BaseMatcher<String>
implements GroovyObject {
    private Object dtd;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729395;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    private XmlDtdMatcher(Object object) {
        void dtd;
        MetaClass metaClass;
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        callSiteArray[0].callStatic(AssertParameter.class, dtd, "dtd");
        void var4_4 = dtd;
        this.dtd = var4_4;
    }

    public static Matcher<String> matchesDtd(String dtd) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        callSiteArray[1].callStatic(AssertParameter.class, dtd, "dtd");
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Matcher)ScriptBytecodeAdapter.castToType(callSiteArray[2].callConstructor(XmlDtdMatcher.class, callSiteArray[3].callStatic(XmlDtdMatcher.class, dtd)), Matcher.class);
        }
        return (Matcher)ScriptBytecodeAdapter.castToType(callSiteArray[4].callConstructor(XmlDtdMatcher.class, XmlDtdMatcher.toInputStream(dtd)), Matcher.class);
    }

    public static Matcher<String> matchesDtd(InputStream dtd) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        return (Matcher)ScriptBytecodeAdapter.castToType(callSiteArray[5].callConstructor(XmlDtdMatcher.class, dtd), Matcher.class);
    }

    public static Matcher<String> matchesDtd(File dtd) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        callSiteArray[6].callStatic(AssertParameter.class, dtd, "file");
        return (Matcher)ScriptBytecodeAdapter.castToType(callSiteArray[7].callConstructor(XmlDtdMatcher.class, callSiteArray[8].callConstructor(FileInputStream.class, dtd)), Matcher.class);
    }

    public static Matcher<String> matchesDtd(URL url) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        callSiteArray[9].callStatic(AssertParameter.class, url, "url");
        return (Matcher)ScriptBytecodeAdapter.castToType(callSiteArray[10].callConstructor(XmlDtdMatcher.class, callSiteArray[11].call(url)), Matcher.class);
    }

    @Override
    public boolean matches(Object item) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        DocumentBuilderFactory factory = (DocumentBuilderFactory)ScriptBytecodeAdapter.castToType(callSiteArray[12].call(DocumentBuilderFactory.class), DocumentBuilderFactory.class);
        DocumentBuilder db = (DocumentBuilder)ScriptBytecodeAdapter.castToType(callSiteArray[13].call(factory), DocumentBuilder.class);
        Document doc = (Document)ScriptBytecodeAdapter.castToType(callSiteArray[14].call((Object)db, callSiteArray[15].callStatic(XmlDtdMatcher.class, item)), Document.class);
        DOMSource source = (DOMSource)ScriptBytecodeAdapter.castToType(callSiteArray[16].callConstructor(DOMSource.class, doc), DOMSource.class);
        TransformerFactory tf = (TransformerFactory)ScriptBytecodeAdapter.castToType(callSiteArray[17].call(TransformerFactory.class), TransformerFactory.class);
        Transformer transformer = (Transformer)ScriptBytecodeAdapter.castToType(callSiteArray[18].call(tf), Transformer.class);
        File file = (File)ScriptBytecodeAdapter.castToType(callSiteArray[19].callCurrent((GroovyObject)this, this.dtd), File.class);
        try {
            callSiteArray[20].call(transformer, callSiteArray[21].callGetProperty(OutputKeys.class), callSiteArray[22].call(file));
            StringWriter writer = (StringWriter)ScriptBytecodeAdapter.castToType(callSiteArray[23].callConstructor(StringWriter.class), StringWriter.class);
            StreamResult result = (StreamResult)ScriptBytecodeAdapter.castToType(callSiteArray[24].callConstructor(StreamResult.class, writer), StreamResult.class);
            callSiteArray[25].call(transformer, source, result);
            callSiteArray[26].call((Object)factory, true);
            Object object = callSiteArray[27].call(factory);
            db = (DocumentBuilder)ScriptBytecodeAdapter.castToType(object, DocumentBuilder.class);
            callSiteArray[28].call((Object)db, callSiteArray[29].callConstructor(ExceptionThrowingErrorHandler.class));
            callSiteArray[30].call((Object)db, callSiteArray[31].callConstructor(InputSource.class, callSiteArray[32].callConstructor(StringReader.class, callSiteArray[33].call(writer))));
        }
        catch (Throwable throwable) {
            callSiteArray[35].call(file);
            throw throwable;
        }
        callSiteArray[34].call(file);
        return true;
    }

    private File writeToTempFile(Object dtd) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        InputStream inputStream = (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[36].callCurrent((GroovyObject)this, dtd), InputStream.class);
        Object file = callSiteArray[37].call(File.class, "restassured", "temp");
        callSiteArray[38].call(file);
        OutputStream out = (OutputStream)ScriptBytecodeAdapter.castToType(callSiteArray[39].callConstructor(FileOutputStream.class, file), OutputStream.class);
        int read = 0;
        byte[] bytes = new byte[1024];
        while (true) {
            Object object = callSiteArray[40].call((Object)inputStream, (Object)bytes);
            read = DefaultTypeTransformation.intUnbox(object);
            if (!ScriptBytecodeAdapter.compareNotEqual(object, -1)) break;
            callSiteArray[41].call(out, bytes, 0, read);
        }
        callSiteArray[42].call(inputStream);
        callSiteArray[43].call(out);
        callSiteArray[44].call(out);
        return (File)ScriptBytecodeAdapter.castToType(file, File.class);
    }

    private InputStream getInputStream(Object dtd) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        if (dtd instanceof URL) {
            URLConnection uc = (URLConnection)ScriptBytecodeAdapter.castToType(callSiteArray[45].call(dtd), URLConnection.class);
            return (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[46].call(uc), InputStream.class);
        }
        return (InputStream)ScriptBytecodeAdapter.castToType(dtd, InputStream.class);
    }

    @Override
    public void describeTo(Description description) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        callSiteArray[47].call((Object)description, "the supplied DTD");
    }

    private static ByteArrayInputStream toInputStream(String dtd) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        return (ByteArrayInputStream)ScriptBytecodeAdapter.castToType(callSiteArray[48].callConstructor(ByteArrayInputStream.class, callSiteArray[49].call(dtd)), ByteArrayInputStream.class);
    }

    public static Matcher<String> matchesDtdInClasspath(String path) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        callSiteArray[50].callStatic(AssertParameter.class, path, "Path that points to the DTD in classpath");
        InputStream stream = (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[51].call(LoadFromClasspathSupport.class, path), InputStream.class);
        return (Matcher)ScriptBytecodeAdapter.castToType(callSiteArray[52].callStatic(XmlDtdMatcher.class, stream), Matcher.class);
    }

    public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(XmlDtdMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$2(String name, Object value) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, XmlDtdMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$2(String name) {
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(XmlDtdMatcher.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != XmlDtdMatcher.class) {
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
        CallSite[] callSiteArray = XmlDtdMatcher.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        XmlDtdMatcher.__$swapInit();
        __timeStamp__239_neverHappen1702030729395 = l2 = 0L;
        __timeStamp = l = 1702030729395L;
    }

    public Object getDtd() {
        return this.dtd;
    }

    public void setDtd(Object object) {
        this.dtd = object;
    }

    public /* synthetic */ File this$3$writeToTempFile(Object object) {
        return this.writeToTempFile(object);
    }

    public /* synthetic */ InputStream this$3$getInputStream(Object object) {
        return this.getInputStream(object);
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
        stringArray[3] = "toInputStream";
        stringArray[4] = "<$constructor$>";
        stringArray[5] = "<$constructor$>";
        stringArray[6] = "notNull";
        stringArray[7] = "<$constructor$>";
        stringArray[8] = "<$constructor$>";
        stringArray[9] = "notNull";
        stringArray[10] = "<$constructor$>";
        stringArray[11] = "toString";
        stringArray[12] = "newInstance";
        stringArray[13] = "newDocumentBuilder";
        stringArray[14] = "parse";
        stringArray[15] = "toInputStream";
        stringArray[16] = "<$constructor$>";
        stringArray[17] = "newInstance";
        stringArray[18] = "newTransformer";
        stringArray[19] = "writeToTempFile";
        stringArray[20] = "setOutputProperty";
        stringArray[21] = "DOCTYPE_SYSTEM";
        stringArray[22] = "getPath";
        stringArray[23] = "<$constructor$>";
        stringArray[24] = "<$constructor$>";
        stringArray[25] = "transform";
        stringArray[26] = "setValidating";
        stringArray[27] = "newDocumentBuilder";
        stringArray[28] = "setErrorHandler";
        stringArray[29] = "<$constructor$>";
        stringArray[30] = "parse";
        stringArray[31] = "<$constructor$>";
        stringArray[32] = "<$constructor$>";
        stringArray[33] = "toString";
        stringArray[34] = "delete";
        stringArray[35] = "delete";
        stringArray[36] = "getInputStream";
        stringArray[37] = "createTempFile";
        stringArray[38] = "deleteOnExit";
        stringArray[39] = "<$constructor$>";
        stringArray[40] = "read";
        stringArray[41] = "write";
        stringArray[42] = "close";
        stringArray[43] = "flush";
        stringArray[44] = "close";
        stringArray[45] = "openConnection";
        stringArray[46] = "getInputStream";
        stringArray[47] = "appendText";
        stringArray[48] = "<$constructor$>";
        stringArray[49] = "getBytes";
        stringArray[50] = "notNull";
        stringArray[51] = "loadFromClasspath";
        stringArray[52] = "matchesDtd";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[53];
        XmlDtdMatcher.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(XmlDtdMatcher.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = XmlDtdMatcher.$createCallSiteArray();
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

    private static class ExceptionThrowingErrorHandler
    implements ErrorHandler,
    GroovyObject {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;
        private static /* synthetic */ SoftReference $callSiteArray;

        public ExceptionThrowingErrorHandler() {
            MetaClass metaClass;
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        public void warning(SAXParseException exception) {
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            throw (Throwable)exception;
        }

        public void error(SAXParseException exception) {
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            throw (Throwable)exception;
        }

        public void fatalError(SAXParseException exception) {
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            throw (Throwable)exception;
        }

        public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ExceptionThrowingErrorHandler.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$1(String name, Object value) {
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, ExceptionThrowingErrorHandler.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$1(String name) {
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(ExceptionThrowingErrorHandler.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(ExceptionThrowingErrorHandler.class, XmlDtdMatcher.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, ExceptionThrowingErrorHandler.class, XmlDtdMatcher.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(ExceptionThrowingErrorHandler.class, XmlDtdMatcher.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ExceptionThrowingErrorHandler.class) {
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
            CallSite[] callSiteArray = ExceptionThrowingErrorHandler.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            ExceptionThrowingErrorHandler.__$swapInit();
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

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[]{};
            return new CallSiteArray(ExceptionThrowingErrorHandler.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = ExceptionThrowingErrorHandler.$createCallSiteArray();
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

