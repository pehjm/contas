/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.io.EncodingAwareBufferedWriter;
import groovy.io.FileType;
import groovy.io.FileVisitResult;
import groovy.io.GroovyPrintWriter;
import groovy.lang.Closure;
import groovy.lang.MetaClass;
import groovy.lang.Writable;
import groovy.transform.NamedParam;
import groovy.transform.NamedParams;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.FromString;
import groovy.transform.stc.PickFirstResolver;
import groovy.transform.stc.SimpleType;
import groovy.util.CharsetToolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.DefaultGroovyMethodsSupport;
import org.codehaus.groovy.runtime.FormatHelper;
import org.codehaus.groovy.runtime.IOGroovyMethods;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.WritableFile;
import org.codehaus.groovy.runtime.callsite.BooleanReturningMethodInvoker;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class ResourceGroovyMethods
extends DefaultGroovyMethodsSupport {
    public static long size(File self) {
        return self.length();
    }

    public static long directorySize(File self) throws IOException, IllegalArgumentException {
        final long[] size = new long[]{0L};
        ResourceGroovyMethods.eachFileRecurse(self, FileType.FILES, new Closure<Void>(null){
            private static final long serialVersionUID = 7688764529326404277L;

            public void doCall(Object[] args) {
                size[0] = size[0] + ((File)args[0]).length();
            }
        });
        return size[0];
    }

    public static ObjectOutputStream newObjectOutputStream(File file) throws IOException {
        return new ObjectOutputStream(new FileOutputStream(file));
    }

    public static <T> T withObjectOutputStream(File file, @ClosureParams(value=SimpleType.class, options={"java.io.ObjectOutputStream"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withStream(ResourceGroovyMethods.newObjectOutputStream(file), closure);
    }

    public static ObjectInputStream newObjectInputStream(File file) throws IOException {
        return new ObjectInputStream(new FileInputStream(file));
    }

    public static ObjectInputStream newObjectInputStream(File file, ClassLoader classLoader) throws IOException {
        return IOGroovyMethods.newObjectInputStream(new FileInputStream(file), classLoader);
    }

    public static void eachObject(File self, Closure closure) throws IOException, ClassNotFoundException {
        IOGroovyMethods.eachObject(ResourceGroovyMethods.newObjectInputStream(self), closure);
    }

    public static <T> T withObjectInputStream(File file, @ClosureParams(value=SimpleType.class, options={"java.io.ObjectInputStream"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withStream(ResourceGroovyMethods.newObjectInputStream(file), closure);
    }

    public static <T> T withObjectInputStream(File file, ClassLoader classLoader, @ClosureParams(value=SimpleType.class, options={"java.io.ObjectInputStream"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withStream(ResourceGroovyMethods.newObjectInputStream(file, classLoader), closure);
    }

    public static <T> T eachLine(File self, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        return ResourceGroovyMethods.eachLine(self, 1, closure);
    }

    public static <T> T eachLine(File self, String charset, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        return ResourceGroovyMethods.eachLine(self, charset, 1, closure);
    }

    public static <T> T eachLine(File self, int firstLine, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.eachLine(ResourceGroovyMethods.newReader(self), firstLine, closure);
    }

    public static <T> T eachLine(File self, String charset, int firstLine, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.eachLine(ResourceGroovyMethods.newReader(self, charset), firstLine, closure);
    }

    public static <T> T eachLine(URL url, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        return ResourceGroovyMethods.eachLine(url, 1, closure);
    }

    public static <T> T eachLine(URL url, int firstLine, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.eachLine(url.openConnection().getInputStream(), firstLine, closure);
    }

    public static <T> T eachLine(URL url, String charset, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        return ResourceGroovyMethods.eachLine(url, charset, 1, closure);
    }

    public static <T> T eachLine(URL url, String charset, int firstLine, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.eachLine(ResourceGroovyMethods.newReader(url, charset), firstLine, closure);
    }

    public static <T> T splitEachLine(File self, String regex, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) throws IOException {
        return IOGroovyMethods.splitEachLine((Reader)ResourceGroovyMethods.newReader(self), regex, closure);
    }

    public static <T> T splitEachLine(File self, Pattern pattern, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) throws IOException {
        return IOGroovyMethods.splitEachLine((Reader)ResourceGroovyMethods.newReader(self), pattern, closure);
    }

    public static <T> T splitEachLine(File self, String regex, String charset, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) throws IOException {
        return IOGroovyMethods.splitEachLine((Reader)ResourceGroovyMethods.newReader(self, charset), regex, closure);
    }

    public static <T> T splitEachLine(File self, Pattern pattern, String charset, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) throws IOException {
        return IOGroovyMethods.splitEachLine((Reader)ResourceGroovyMethods.newReader(self, charset), pattern, closure);
    }

    public static <T> T splitEachLine(URL self, String regex, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) throws IOException {
        return IOGroovyMethods.splitEachLine((Reader)ResourceGroovyMethods.newReader(self), regex, closure);
    }

    public static <T> T splitEachLine(URL self, Pattern pattern, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) throws IOException {
        return IOGroovyMethods.splitEachLine((Reader)ResourceGroovyMethods.newReader(self), pattern, closure);
    }

    public static <T> T splitEachLine(URL self, String regex, String charset, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) throws IOException {
        return IOGroovyMethods.splitEachLine((Reader)ResourceGroovyMethods.newReader(self, charset), regex, closure);
    }

    public static <T> T splitEachLine(URL self, Pattern pattern, String charset, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) throws IOException {
        return IOGroovyMethods.splitEachLine((Reader)ResourceGroovyMethods.newReader(self, charset), pattern, closure);
    }

    public static List<String> readLines(File file) throws IOException {
        return IOGroovyMethods.readLines(ResourceGroovyMethods.newReader(file));
    }

    public static List<String> readLines(File file, String charset) throws IOException {
        return IOGroovyMethods.readLines(ResourceGroovyMethods.newReader(file, charset));
    }

    public static List<String> readLines(URL self) throws IOException {
        return IOGroovyMethods.readLines(ResourceGroovyMethods.newReader(self));
    }

    public static List<String> readLines(URL self, String charset) throws IOException {
        return IOGroovyMethods.readLines(ResourceGroovyMethods.newReader(self, charset));
    }

    public static String getText(File file, String charset) throws IOException {
        return IOGroovyMethods.getText(ResourceGroovyMethods.newReader(file, charset));
    }

    public static String getText(File file) throws IOException {
        return IOGroovyMethods.getText(ResourceGroovyMethods.newReader(file));
    }

    public static String getText(URL url) throws IOException {
        return ResourceGroovyMethods.getText(url, CharsetToolkit.getDefaultSystemCharset().name());
    }

    public static String getText(URL url, @NamedParams(value={@NamedParam(value="connectTimeout", type=Integer.class), @NamedParam(value="readTimeout", type=Integer.class), @NamedParam(value="useCaches", type=Boolean.class), @NamedParam(value="allowUserInteraction", type=Boolean.class), @NamedParam(value="requestProperties", type=Map.class)}) Map<String, ?> parameters) throws IOException {
        return ResourceGroovyMethods.getText(url, parameters, CharsetToolkit.getDefaultSystemCharset().name());
    }

    public static String getText(URL url, String charset) throws IOException {
        BufferedReader reader = ResourceGroovyMethods.newReader(url, charset);
        return IOGroovyMethods.getText(reader);
    }

    public static String getText(URL url, @NamedParams(value={@NamedParam(value="connectTimeout", type=Integer.class), @NamedParam(value="readTimeout", type=Integer.class), @NamedParam(value="useCaches", type=Boolean.class), @NamedParam(value="allowUserInteraction", type=Boolean.class), @NamedParam(value="requestProperties", type=Map.class)}) Map<String, ?> parameters, String charset) throws IOException {
        BufferedReader reader = ResourceGroovyMethods.newReader(url, parameters, charset);
        return IOGroovyMethods.getText(reader);
    }

    public static byte[] getBytes(File file) throws IOException {
        return IOGroovyMethods.getBytes(new FileInputStream(file));
    }

    public static byte[] getBytes(URL url) throws IOException {
        return IOGroovyMethods.getBytes(url.openConnection().getInputStream());
    }

    public static byte[] getBytes(URL url, @NamedParams(value={@NamedParam(value="connectTimeout", type=Integer.class), @NamedParam(value="readTimeout", type=Integer.class), @NamedParam(value="useCaches", type=Boolean.class), @NamedParam(value="allowUserInteraction", type=Boolean.class), @NamedParam(value="requestProperties", type=Map.class)}) Map<String, ?> parameters) throws IOException {
        return IOGroovyMethods.getBytes(ResourceGroovyMethods.configuredInputStream(parameters, url));
    }

    public static void setBytes(File file, byte[] bytes) throws IOException {
        IOGroovyMethods.setBytes(new FileOutputStream(file), bytes);
    }

    public static File createParentDirectories(File self) throws IOException {
        Files.createDirectories(self.getParentFile().toPath(), new FileAttribute[0]).toFile();
        return self;
    }

    public static void write(File file, String text) throws IOException {
        ResourceGroovyMethods.write(file, text, false);
    }

    public static void write(File file, String text, boolean writeBom) throws IOException {
        ResourceGroovyMethods.write(file, text, Charset.defaultCharset().name(), writeBom);
    }

    public static void setText(File file, String text) throws IOException {
        ResourceGroovyMethods.write(file, text);
    }

    public static void setText(File file, String text, String charset) throws IOException {
        ResourceGroovyMethods.write(file, text, charset);
    }

    public static File leftShift(File file, Object text) throws IOException {
        ResourceGroovyMethods.append(file, text);
        return file;
    }

    public static File leftShift(File file, byte[] bytes) throws IOException {
        ResourceGroovyMethods.append(file, bytes);
        return file;
    }

    public static File leftShift(File file, InputStream data) throws IOException {
        ResourceGroovyMethods.append(file, data);
        return file;
    }

    public static void write(File file, String text, String charset) throws IOException {
        ResourceGroovyMethods.write(file, text, charset, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void write(File file, String text, String charset, boolean writeBom) throws IOException {
        OutputStreamWriter writer = null;
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (writeBom) {
                ResourceGroovyMethods.writeUTF16BomIfRequired((OutputStream)out, charset);
            }
            writer = new OutputStreamWriter((OutputStream)out, charset);
            writer.write(text);
            ((Writer)writer).flush();
            OutputStreamWriter temp = writer;
            writer = null;
            ((Writer)temp).close();
        }
        catch (Throwable throwable) {
            ResourceGroovyMethods.closeWithWarning(writer);
            throw throwable;
        }
        ResourceGroovyMethods.closeWithWarning(writer);
    }

    public static void append(File file, Object text) throws IOException {
        ResourceGroovyMethods.append(file, text, false);
    }

    public static void append(File file, Object text, boolean writeBom) throws IOException {
        ResourceGroovyMethods.append(file, text, Charset.defaultCharset().name(), writeBom);
    }

    public static void append(File file, Reader reader) throws IOException {
        ResourceGroovyMethods.append(file, reader, false);
    }

    public static void append(File file, Writer writer) throws IOException {
        ResourceGroovyMethods.append(file, writer, false);
    }

    public static void append(File file, Writer writer, boolean writeBom) throws IOException {
        ResourceGroovyMethods.appendBuffered(file, writer, writeBom);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void appendBuffered(File file, Object text, boolean writeBom) throws IOException {
        BufferedWriter writer = null;
        try {
            boolean shouldWriteBom = writeBom && !file.exists();
            writer = ResourceGroovyMethods.newWriter(file, true);
            if (shouldWriteBom) {
                ResourceGroovyMethods.writeUTF16BomIfRequired((Writer)writer, Charset.defaultCharset().name());
            }
            FormatHelper.write(writer, text);
            writer.flush();
            BufferedWriter temp = writer;
            writer = null;
            ((Writer)temp).close();
        }
        catch (Throwable throwable) {
            ResourceGroovyMethods.closeWithWarning(writer);
            throw throwable;
        }
        ResourceGroovyMethods.closeWithWarning(writer);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void append(File file, byte[] bytes) throws IOException {
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file, true);
            ((OutputStream)stream).write(bytes, 0, bytes.length);
            stream.flush();
            FileOutputStream temp = stream;
            stream = null;
            ((OutputStream)temp).close();
        }
        catch (Throwable throwable) {
            ResourceGroovyMethods.closeWithWarning(stream);
            throw throwable;
        }
        ResourceGroovyMethods.closeWithWarning(stream);
    }

    public static void append(File self, InputStream stream) throws IOException {
        FileOutputStream out = new FileOutputStream(self, true);
        try {
            IOGroovyMethods.leftShift((OutputStream)out, stream);
        }
        finally {
            ResourceGroovyMethods.closeWithWarning(out);
        }
    }

    public static void append(File file, Object text, String charset) throws IOException {
        ResourceGroovyMethods.append(file, text, charset, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void append(File file, Object text, String charset, boolean writeBom) throws IOException {
        OutputStreamWriter writer = null;
        try {
            boolean shouldWriteBom = writeBom && !file.exists();
            FileOutputStream out = new FileOutputStream(file, true);
            if (shouldWriteBom) {
                ResourceGroovyMethods.writeUTF16BomIfRequired((OutputStream)out, charset);
            }
            writer = new OutputStreamWriter((OutputStream)out, charset);
            FormatHelper.write(writer, text);
            ((Writer)writer).flush();
            OutputStreamWriter temp = writer;
            writer = null;
            ((Writer)temp).close();
        }
        catch (Throwable throwable) {
            ResourceGroovyMethods.closeWithWarning(writer);
            throw throwable;
        }
        ResourceGroovyMethods.closeWithWarning(writer);
    }

    public static void append(File file, Writer writer, String charset) throws IOException {
        ResourceGroovyMethods.append(file, writer, charset, false);
    }

    public static void append(File file, Writer writer, String charset, boolean writeBom) throws IOException {
        ResourceGroovyMethods.appendBuffered(file, writer, charset, writeBom);
    }

    public static void append(File file, Reader reader, boolean writeBom) throws IOException {
        ResourceGroovyMethods.append(file, reader, Charset.defaultCharset().name(), writeBom);
    }

    public static void append(File file, Reader reader, String charset) throws IOException {
        ResourceGroovyMethods.append(file, reader, charset, false);
    }

    public static void append(File file, Reader reader, String charset, boolean writeBom) throws IOException {
        ResourceGroovyMethods.appendBuffered(file, reader, charset, writeBom);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void appendBuffered(File file, Object text, String charset, boolean writeBom) throws IOException {
        BufferedWriter writer = null;
        try {
            boolean shouldWriteBom = writeBom && !file.exists();
            writer = ResourceGroovyMethods.newWriter(file, charset, true);
            if (shouldWriteBom) {
                ResourceGroovyMethods.writeUTF16BomIfRequired((Writer)writer, charset);
            }
            FormatHelper.write(writer, text);
            writer.flush();
            BufferedWriter temp = writer;
            writer = null;
            ((Writer)temp).close();
        }
        catch (Throwable throwable) {
            ResourceGroovyMethods.closeWithWarning(writer);
            throw throwable;
        }
        ResourceGroovyMethods.closeWithWarning(writer);
    }

    private static void checkDir(File dir) throws FileNotFoundException, IllegalArgumentException {
        if (!dir.exists()) {
            throw new FileNotFoundException(dir.getAbsolutePath());
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("The provided File object is not a directory: " + dir.getAbsolutePath());
        }
    }

    public static void eachFile(File self, FileType fileType, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.checkDir(self);
        File[] files = self.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (fileType != FileType.ANY && (fileType == FileType.FILES || !file.isDirectory()) && (fileType == FileType.DIRECTORIES || !file.isFile())) continue;
            closure.call((Object)file);
        }
    }

    public static void eachFile(File self, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.eachFile(self, FileType.ANY, closure);
    }

    public static void eachDir(File self, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.eachFile(self, FileType.DIRECTORIES, closure);
    }

    public static void eachFileRecurse(File self, FileType fileType, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.checkDir(self);
        File[] files = self.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                if (fileType != FileType.FILES) {
                    closure.call((Object)file);
                }
                ResourceGroovyMethods.eachFileRecurse(file, fileType, closure);
                continue;
            }
            if (fileType == FileType.DIRECTORIES) continue;
            closure.call((Object)file);
        }
    }

    public static void traverse(File self, @NamedParams(value={@NamedParam(value="type", type=FileType.class), @NamedParam(value="preDir", type=Closure.class), @NamedParam(value="preRoot", type=Boolean.class), @NamedParam(value="postDir", type=Closure.class), @NamedParam(value="postRoot", type=Boolean.class), @NamedParam(value="visitRoot", type=Boolean.class), @NamedParam(value="maxDepth", type=Integer.class), @NamedParam(value="filter"), @NamedParam(value="nameFilter"), @NamedParam(value="excludeFilter"), @NamedParam(value="excludeNameFilter"), @NamedParam(value="sort", type=Closure.class)}) Map<String, ?> options, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        Object closureResult;
        Object maxDepthValue = options.remove("maxDepth");
        int maxDepth = maxDepthValue == null ? -1 : DefaultGroovyMethods.asType(maxDepthValue, Number.class).intValue();
        Object preRootValue = options.get("preRoot");
        boolean preRoot = preRootValue == null ? false : DefaultGroovyMethods.asType(preRootValue, Boolean.class);
        Object postRootValue = options.get("postRoot");
        boolean postRoot = postRootValue == null ? false : DefaultGroovyMethods.asType(postRootValue, Boolean.class);
        Object visitRootValue = options.get("visitRoot");
        boolean visitRoot = visitRootValue == null ? false : DefaultGroovyMethods.asType(visitRootValue, Boolean.class);
        Closure pre = (Closure)options.get("preDir");
        Closure post = (Closure)options.get("postDir");
        FileType type = (FileType)((Object)options.get("type"));
        Object filter = options.get("filter");
        Object nameFilter = options.get("nameFilter");
        Object excludeFilter = options.get("excludeFilter");
        Object excludeNameFilter = options.get("excludeNameFilter");
        Object preResult = null;
        if (preRoot && pre != null) {
            preResult = pre.call((Object)self);
        }
        if (preResult == FileVisitResult.TERMINATE || preResult == FileVisitResult.SKIP_SUBTREE) {
            return;
        }
        FileVisitResult terminated = ResourceGroovyMethods.traverse(self, options, closure, maxDepth);
        if (type != FileType.FILES && visitRoot && closure != null && ResourceGroovyMethods.notFiltered(self, filter, nameFilter, excludeFilter, excludeNameFilter) && (closureResult = closure.call((Object)self)) == FileVisitResult.TERMINATE) {
            return;
        }
        if (postRoot && post != null && terminated != FileVisitResult.TERMINATE) {
            post.call((Object)self);
        }
    }

    private static boolean notFiltered(File file, Object filter, Object nameFilter, Object excludeFilter, Object excludeNameFilter) {
        if (filter == null && nameFilter == null && excludeFilter == null && excludeNameFilter == null) {
            return true;
        }
        if (filter != null && nameFilter != null) {
            throw new IllegalArgumentException("Can't set both 'filter' and 'nameFilter'");
        }
        if (excludeFilter != null && excludeNameFilter != null) {
            throw new IllegalArgumentException("Can't set both 'excludeFilter' and 'excludeNameFilter'");
        }
        Object filterToUse = null;
        Object filterParam = null;
        if (filter != null) {
            filterToUse = filter;
            filterParam = file;
        } else if (nameFilter != null) {
            filterToUse = nameFilter;
            filterParam = file.getName();
        }
        Object excludeFilterToUse = null;
        Object excludeParam = null;
        if (excludeFilter != null) {
            excludeFilterToUse = excludeFilter;
            excludeParam = file;
        } else if (excludeNameFilter != null) {
            excludeFilterToUse = excludeNameFilter;
            excludeParam = file.getName();
        }
        MetaClass filterMC = filterToUse == null ? null : InvokerHelper.getMetaClass(filterToUse);
        MetaClass excludeMC = excludeFilterToUse == null ? null : InvokerHelper.getMetaClass(excludeFilterToUse);
        boolean included = filterToUse == null || DefaultTypeTransformation.castToBoolean(filterMC.invokeMethod(filterToUse, "isCase", filterParam));
        boolean excluded = excludeFilterToUse != null && DefaultTypeTransformation.castToBoolean(excludeMC.invokeMethod(excludeFilterToUse, "isCase", excludeParam));
        return included && !excluded;
    }

    public static void traverse(File self, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.traverse(self, new HashMap(), closure);
    }

    public static void traverse(File self, @NamedParams(value={@NamedParam(value="type", type=FileType.class), @NamedParam(value="preDir", type=Closure.class), @NamedParam(value="preRoot", type=Boolean.class), @NamedParam(value="postDir", type=Closure.class), @NamedParam(value="postRoot", type=Boolean.class), @NamedParam(value="visitRoot", type=Boolean.class), @NamedParam(value="maxDepth", type=Integer.class), @NamedParam(value="filter"), @NamedParam(value="nameFilter"), @NamedParam(value="excludeFilter"), @NamedParam(value="excludeNameFilter"), @NamedParam(value="sort", type=Closure.class)}) Map<String, ?> options) throws FileNotFoundException, IllegalArgumentException {
        Closure visit = (Closure)options.remove("visit");
        ResourceGroovyMethods.traverse(self, options, visit);
    }

    private static FileVisitResult traverse(File self, Map<String, ?> options, Closure closure, int maxDepth) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.checkDir(self);
        Closure pre = (Closure)options.get("preDir");
        Closure post = (Closure)options.get("postDir");
        FileType type = (FileType)((Object)options.get("type"));
        Object filter = options.get("filter");
        Object nameFilter = options.get("nameFilter");
        Object excludeFilter = options.get("excludeFilter");
        Object excludeNameFilter = options.get("excludeNameFilter");
        Closure sort = (Closure)options.get("sort");
        File[] origFiles = self.listFiles();
        if (origFiles != null) {
            List<File> files = Arrays.asList(origFiles);
            if (sort != null) {
                files = DefaultGroovyMethods.sort(files, sort);
            }
            for (File file : files) {
                Object closureResult;
                if (file.isDirectory()) {
                    FileVisitResult terminated;
                    if (type != FileType.FILES && closure != null && ResourceGroovyMethods.notFiltered(file, filter, nameFilter, excludeFilter, excludeNameFilter)) {
                        closureResult = closure.call((Object)file);
                        if (closureResult == FileVisitResult.SKIP_SIBLINGS) break;
                        if (closureResult == FileVisitResult.TERMINATE) {
                            return FileVisitResult.TERMINATE;
                        }
                    }
                    if (maxDepth == 0) continue;
                    Object preResult = null;
                    if (pre != null) {
                        preResult = pre.call((Object)file);
                    }
                    if (preResult == FileVisitResult.SKIP_SIBLINGS) break;
                    if (preResult == FileVisitResult.TERMINATE) {
                        return FileVisitResult.TERMINATE;
                    }
                    if (preResult != FileVisitResult.SKIP_SUBTREE && (terminated = ResourceGroovyMethods.traverse(file, options, closure, maxDepth - 1)) == FileVisitResult.TERMINATE) {
                        return terminated;
                    }
                    Object postResult = null;
                    if (post != null) {
                        postResult = post.call((Object)file);
                    }
                    if (postResult == FileVisitResult.SKIP_SIBLINGS) break;
                    if (postResult != FileVisitResult.TERMINATE) continue;
                    return FileVisitResult.TERMINATE;
                }
                if (type == FileType.DIRECTORIES || closure == null || !ResourceGroovyMethods.notFiltered(file, filter, nameFilter, excludeFilter, excludeNameFilter)) continue;
                closureResult = closure.call((Object)file);
                if (closureResult == FileVisitResult.SKIP_SIBLINGS) break;
                if (closureResult != FileVisitResult.TERMINATE) continue;
                return FileVisitResult.TERMINATE;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public static void eachFileRecurse(File self, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.eachFileRecurse(self, FileType.ANY, closure);
    }

    public static void eachDirRecurse(File self, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.eachFileRecurse(self, FileType.DIRECTORIES, closure);
    }

    public static void eachFileMatch(File self, FileType fileType, Object nameFilter, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.checkDir(self);
        File[] files = self.listFiles();
        if (files == null) {
            return;
        }
        BooleanReturningMethodInvoker bmi = new BooleanReturningMethodInvoker("isCase");
        for (File currentFile : files) {
            if (fileType != FileType.ANY && (fileType == FileType.FILES || !currentFile.isDirectory()) && (fileType == FileType.DIRECTORIES || !currentFile.isFile()) || !bmi.invoke(nameFilter, currentFile.getName())) continue;
            closure.call((Object)currentFile);
        }
    }

    public static void eachFileMatch(File self, Object nameFilter, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.eachFileMatch(self, FileType.ANY, nameFilter, closure);
    }

    public static void eachDirMatch(File self, Object nameFilter, @ClosureParams(value=SimpleType.class, options={"java.io.File"}) Closure closure) throws FileNotFoundException, IllegalArgumentException {
        ResourceGroovyMethods.eachFileMatch(self, FileType.DIRECTORIES, nameFilter, closure);
    }

    public static boolean deleteDir(File self) {
        if (!self.exists()) {
            return true;
        }
        if (!self.isDirectory()) {
            return false;
        }
        File[] files = self.listFiles();
        if (files == null) {
            return false;
        }
        boolean result = true;
        for (File file : files) {
            if (file.isDirectory()) {
                if (ResourceGroovyMethods.deleteDir(file)) continue;
                result = false;
                continue;
            }
            if (file.delete()) continue;
            result = false;
        }
        if (!self.delete()) {
            result = false;
        }
        return result;
    }

    public static boolean renameTo(File self, String newPathName) {
        return self.renameTo(new File(newPathName));
    }

    public static String relativePath(File self, File to) throws IOException {
        int same;
        String fromPath = self.getCanonicalPath();
        String toPath = to.getCanonicalPath();
        String[] fromPathStack = ResourceGroovyMethods.getPathStack(fromPath);
        String[] toPathStack = ResourceGroovyMethods.getPathStack(toPath);
        if (0 < toPathStack.length && 0 < fromPathStack.length) {
            if (!fromPathStack[0].equals(toPathStack[0])) {
                return ResourceGroovyMethods.getPath(Arrays.asList(toPathStack));
            }
        } else {
            return ResourceGroovyMethods.getPath(Arrays.asList(toPathStack));
        }
        int minLength = Math.min(fromPathStack.length, toPathStack.length);
        for (same = 1; same < minLength && fromPathStack[same].equals(toPathStack[same]); ++same) {
        }
        ArrayList<String> relativePathStack = new ArrayList<String>();
        for (int i = same; i < fromPathStack.length; ++i) {
            relativePathStack.add("..");
        }
        relativePathStack.addAll(Arrays.asList(toPathStack).subList(same, toPathStack.length));
        return ResourceGroovyMethods.getPath(relativePathStack);
    }

    public static File asWritable(File file) {
        return new WritableFile(file);
    }

    public static <T> T asType(File f, Class<T> c) {
        if (c == Writable.class) {
            return (T)ResourceGroovyMethods.asWritable(f);
        }
        return DefaultGroovyMethods.asType(f, c);
    }

    public static File asWritable(File file, String encoding) {
        return new WritableFile(file, encoding);
    }

    public static BufferedReader newReader(File file) throws IOException {
        CharsetToolkit toolkit = new CharsetToolkit(file);
        return toolkit.getReader();
    }

    public static BufferedReader newReader(File file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(file), charset));
    }

    public static <T> T withReader(File file, @ClosureParams(value=SimpleType.class, options={"java.io.BufferedReader"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withReader(ResourceGroovyMethods.newReader(file), closure);
    }

    public static <T> T withReader(File file, String charset, @ClosureParams(value=SimpleType.class, options={"java.io.BufferedReader"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withReader(ResourceGroovyMethods.newReader(file, charset), closure);
    }

    public static BufferedOutputStream newOutputStream(File file) throws IOException {
        return new BufferedOutputStream(new FileOutputStream(file));
    }

    public static DataOutputStream newDataOutputStream(File file) throws IOException {
        return new DataOutputStream(new FileOutputStream(file));
    }

    public static Object withOutputStream(File file, @ClosureParams(value=SimpleType.class, options={"java.io.OutputStream"}) Closure closure) throws IOException {
        return IOGroovyMethods.withStream(ResourceGroovyMethods.newOutputStream(file), closure);
    }

    public static Object withInputStream(File file, @ClosureParams(value=SimpleType.class, options={"java.io.InputStream"}) Closure closure) throws IOException {
        return IOGroovyMethods.withStream(ResourceGroovyMethods.newInputStream(file), closure);
    }

    public static <T> T withInputStream(URL url, @ClosureParams(value=SimpleType.class, options={"java.io.InputStream"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withStream(ResourceGroovyMethods.newInputStream(url), closure);
    }

    public static <T> T withDataOutputStream(File file, @ClosureParams(value=SimpleType.class, options={"java.io.DataOutputStream"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withStream(ResourceGroovyMethods.newDataOutputStream(file), closure);
    }

    public static <T> T withDataInputStream(File file, @ClosureParams(value=SimpleType.class, options={"java.io.DataInputStream"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withStream(ResourceGroovyMethods.newDataInputStream(file), closure);
    }

    public static BufferedWriter newWriter(File file) throws IOException {
        return new BufferedWriter(new FileWriter(file));
    }

    public static BufferedWriter newWriter(File file, boolean append) throws IOException {
        return new BufferedWriter(new FileWriter(file, append));
    }

    public static BufferedWriter newWriter(File file, String charset, boolean append) throws IOException {
        return ResourceGroovyMethods.newWriter(file, charset, append, false);
    }

    public static BufferedWriter newWriter(File file, String charset, boolean append, boolean writeBom) throws IOException {
        boolean shouldWriteBom;
        boolean bl = shouldWriteBom = writeBom && !file.exists();
        if (append) {
            FileOutputStream stream = new FileOutputStream(file, append);
            if (shouldWriteBom) {
                ResourceGroovyMethods.writeUTF16BomIfRequired((OutputStream)stream, charset);
            }
            return new EncodingAwareBufferedWriter(new OutputStreamWriter((OutputStream)stream, charset));
        }
        FileOutputStream stream = new FileOutputStream(file);
        if (shouldWriteBom) {
            ResourceGroovyMethods.writeUTF16BomIfRequired((OutputStream)stream, charset);
        }
        return new EncodingAwareBufferedWriter(new OutputStreamWriter((OutputStream)stream, charset));
    }

    public static BufferedWriter newWriter(File file, String charset) throws IOException {
        return ResourceGroovyMethods.newWriter(file, charset, false);
    }

    public static <T> T withWriter(File file, @ClosureParams(value=SimpleType.class, options={"java.io.BufferedWriter"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withWriter(ResourceGroovyMethods.newWriter(file), closure);
    }

    public static <T> T withWriter(File file, String charset, @ClosureParams(value=SimpleType.class, options={"java.io.BufferedWriter"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withWriter(ResourceGroovyMethods.newWriter(file, charset), closure);
    }

    public static <T> T withWriterAppend(File file, String charset, @ClosureParams(value=SimpleType.class, options={"java.io.BufferedWriter"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withWriter(ResourceGroovyMethods.newWriter(file, charset, true), closure);
    }

    public static <T> T withWriterAppend(File file, @ClosureParams(value=SimpleType.class, options={"java.io.BufferedWriter"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withWriter(ResourceGroovyMethods.newWriter(file, true), closure);
    }

    public static PrintWriter newPrintWriter(File file) throws IOException {
        return new GroovyPrintWriter(ResourceGroovyMethods.newWriter(file));
    }

    public static PrintWriter newPrintWriter(File file, String charset) throws IOException {
        return new GroovyPrintWriter(ResourceGroovyMethods.newWriter(file, charset));
    }

    public static <T> T withPrintWriter(File file, @ClosureParams(value=SimpleType.class, options={"java.io.PrintWriter"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withWriter(ResourceGroovyMethods.newPrintWriter(file), closure);
    }

    public static <T> T withPrintWriter(File file, String charset, @ClosureParams(value=SimpleType.class, options={"java.io.PrintWriter"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withWriter(ResourceGroovyMethods.newPrintWriter(file, charset), closure);
    }

    public static <T> T withReader(URL url, @ClosureParams(value=SimpleType.class, options={"java.io.Reader"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withReader(url.openConnection().getInputStream(), closure);
    }

    public static <T> T withReader(URL url, String charset, @ClosureParams(value=SimpleType.class, options={"java.io.Reader"}) Closure<T> closure) throws IOException {
        return IOGroovyMethods.withReader(url.openConnection().getInputStream(), charset, closure);
    }

    public static BufferedInputStream newInputStream(File file) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    private static InputStream configuredInputStream(Map<String, ?> parameters, URL url) throws IOException {
        URLConnection connection = url.openConnection();
        if (parameters != null) {
            if (parameters.containsKey("connectTimeout")) {
                connection.setConnectTimeout(DefaultGroovyMethods.asType(parameters.get("connectTimeout"), Integer.class));
            }
            if (parameters.containsKey("readTimeout")) {
                connection.setReadTimeout(DefaultGroovyMethods.asType(parameters.get("readTimeout"), Integer.class));
            }
            if (parameters.containsKey("useCaches")) {
                connection.setUseCaches(DefaultGroovyMethods.asType(parameters.get("useCaches"), Boolean.class));
            }
            if (parameters.containsKey("allowUserInteraction")) {
                connection.setAllowUserInteraction(DefaultGroovyMethods.asType(parameters.get("allowUserInteraction"), Boolean.class));
            }
            if (parameters.containsKey("requestProperties")) {
                Map properties = (Map)parameters.get("requestProperties");
                for (Map.Entry entry : properties.entrySet()) {
                    connection.setRequestProperty((String)entry.getKey(), ((CharSequence)entry.getValue()).toString());
                }
            }
        }
        return connection.getInputStream();
    }

    public static BufferedInputStream newInputStream(URL url) throws IOException {
        return new BufferedInputStream(ResourceGroovyMethods.configuredInputStream(null, url));
    }

    public static BufferedInputStream newInputStream(URL url, @NamedParams(value={@NamedParam(value="connectTimeout", type=Integer.class), @NamedParam(value="readTimeout", type=Integer.class), @NamedParam(value="useCaches", type=Boolean.class), @NamedParam(value="allowUserInteraction", type=Boolean.class), @NamedParam(value="requestProperties", type=Map.class)}) Map<String, ?> parameters) throws IOException {
        return new BufferedInputStream(ResourceGroovyMethods.configuredInputStream(parameters, url));
    }

    public static BufferedReader newReader(URL url) throws IOException {
        return IOGroovyMethods.newReader(ResourceGroovyMethods.configuredInputStream(null, url));
    }

    public static BufferedReader newReader(URL url, @NamedParams(value={@NamedParam(value="connectTimeout", type=Integer.class), @NamedParam(value="readTimeout", type=Integer.class), @NamedParam(value="useCaches", type=Boolean.class), @NamedParam(value="allowUserInteraction", type=Boolean.class), @NamedParam(value="requestProperties", type=Map.class)}) Map<String, ?> parameters) throws IOException {
        return IOGroovyMethods.newReader(ResourceGroovyMethods.configuredInputStream(parameters, url));
    }

    public static BufferedReader newReader(URL url, String charset) throws IOException {
        return new BufferedReader(new InputStreamReader(ResourceGroovyMethods.configuredInputStream(null, url), charset));
    }

    public static BufferedReader newReader(URL url, @NamedParams(value={@NamedParam(value="connectTimeout", type=Integer.class), @NamedParam(value="readTimeout", type=Integer.class), @NamedParam(value="useCaches", type=Boolean.class), @NamedParam(value="allowUserInteraction", type=Boolean.class), @NamedParam(value="requestProperties", type=Map.class)}) Map<String, ?> parameters, String charset) throws IOException {
        return new BufferedReader(new InputStreamReader(ResourceGroovyMethods.configuredInputStream(parameters, url), charset));
    }

    public static DataInputStream newDataInputStream(File file) throws FileNotFoundException {
        return new DataInputStream(new FileInputStream(file));
    }

    public static void eachByte(File self, @ClosureParams(value=SimpleType.class, options={"byte"}) Closure closure) throws IOException {
        BufferedInputStream is = ResourceGroovyMethods.newInputStream(self);
        IOGroovyMethods.eachByte(is, closure);
    }

    public static void eachByte(File self, int bufferLen, @ClosureParams(value=FromString.class, options={"byte[],Integer"}) Closure closure) throws IOException {
        BufferedInputStream is = ResourceGroovyMethods.newInputStream(self);
        IOGroovyMethods.eachByte(is, bufferLen, closure);
    }

    public static void eachByte(URL url, @ClosureParams(value=SimpleType.class, options={"byte"}) Closure closure) throws IOException {
        InputStream is = url.openConnection().getInputStream();
        IOGroovyMethods.eachByte(is, closure);
    }

    public static void eachByte(URL url, int bufferLen, @ClosureParams(value=FromString.class, options={"byte[],Integer"}) Closure closure) throws IOException {
        InputStream is = url.openConnection().getInputStream();
        IOGroovyMethods.eachByte(is, bufferLen, closure);
    }

    public static Writable filterLine(File self, @ClosureParams(value=SimpleType.class, options={"java.lang.String"}) Closure closure) throws IOException {
        return IOGroovyMethods.filterLine(ResourceGroovyMethods.newReader(self), closure);
    }

    public static Writable filterLine(File self, String charset, @ClosureParams(value=SimpleType.class, options={"java.lang.String"}) Closure closure) throws IOException {
        return IOGroovyMethods.filterLine(ResourceGroovyMethods.newReader(self, charset), closure);
    }

    public static void filterLine(File self, Writer writer, @ClosureParams(value=SimpleType.class, options={"java.lang.String"}) Closure closure) throws IOException {
        IOGroovyMethods.filterLine(ResourceGroovyMethods.newReader(self), writer, closure);
    }

    public static void filterLine(File self, Writer writer, String charset, @ClosureParams(value=SimpleType.class, options={"java.lang.String"}) Closure closure) throws IOException {
        IOGroovyMethods.filterLine(ResourceGroovyMethods.newReader(self, charset), writer, closure);
    }

    public static Writable filterLine(URL self, @ClosureParams(value=SimpleType.class, options={"java.lang.String"}) Closure predicate) throws IOException {
        return IOGroovyMethods.filterLine(ResourceGroovyMethods.newReader(self), predicate);
    }

    public static Writable filterLine(URL self, String charset, @ClosureParams(value=SimpleType.class, options={"java.lang.String"}) Closure predicate) throws IOException {
        return IOGroovyMethods.filterLine(ResourceGroovyMethods.newReader(self, charset), predicate);
    }

    public static void filterLine(URL self, Writer writer, @ClosureParams(value=SimpleType.class, options={"java.lang.String"}) Closure predicate) throws IOException {
        IOGroovyMethods.filterLine(ResourceGroovyMethods.newReader(self), writer, predicate);
    }

    public static void filterLine(URL self, Writer writer, String charset, @ClosureParams(value=SimpleType.class, options={"java.lang.String"}) Closure predicate) throws IOException {
        IOGroovyMethods.filterLine(ResourceGroovyMethods.newReader(self, charset), writer, predicate);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] readBytes(File file) throws IOException {
        byte[] bytes = new byte[(int)file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fileInputStream);
        try {
            dis.readFully(bytes);
            DataInputStream temp = dis;
            dis = null;
            ((InputStream)temp).close();
        }
        finally {
            ResourceGroovyMethods.closeWithWarning(dis);
        }
        return bytes;
    }

    public static URI toURI(CharSequence self) throws URISyntaxException {
        return new URI(self.toString());
    }

    public static URI toURI(String self) throws URISyntaxException {
        return new URI(self);
    }

    public static URL toURL(CharSequence self) throws MalformedURLException {
        return new URL(self.toString());
    }

    public static URL toURL(String self) throws MalformedURLException {
        return new URL(self);
    }

    private static String[] getPathStack(String path) {
        String normalizedPath = path.replace(File.separatorChar, '/');
        return normalizedPath.split("/");
    }

    private static String getPath(List pathStack) {
        return ResourceGroovyMethods.getPath(pathStack, '/');
    }

    private static String getPath(List pathStack, char separatorChar) {
        StringBuilder buffer = new StringBuilder();
        Iterator iter = pathStack.iterator();
        if (iter.hasNext()) {
            buffer.append(iter.next());
        }
        while (iter.hasNext()) {
            buffer.append(separatorChar);
            buffer.append(iter.next());
        }
        return buffer.toString();
    }
}

