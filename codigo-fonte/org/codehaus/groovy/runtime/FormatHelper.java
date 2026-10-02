/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.GroovyRuntimeException;
import groovy.lang.GroovySystem;
import groovy.lang.MetaClassRegistry;
import groovy.lang.Range;
import groovy.lang.Writable;
import groovy.transform.NamedParam;
import groovy.transform.NamedParams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.groovy.io.StringBuilderWriter;
import org.codehaus.groovy.control.ResolveVisitor;
import org.codehaus.groovy.runtime.NullObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.w3c.dom.Element;

public class FormatHelper {
    private static final String SQ = "'";
    private static final String DQ = "\"";
    private static final Object[] EMPTY_ARGS = new Object[0];
    private static final int ITEM_ALLOCATE_SIZE = 5;
    public static final MetaClassRegistry metaRegistry = GroovySystem.getMetaClassRegistry();
    private static final String XMLUTIL_CLASS_FULL_NAME = "groovy.xml.XmlUtil";
    private static final String SERIALIZE_METHOD_NAME = "serialize";
    static final Set<String> DEFAULT_IMPORT_PKGS = new HashSet<String>();
    static final Set<String> DEFAULT_IMPORT_CLASSES = new HashSet<String>();

    private FormatHelper() {
    }

    public static String toString(Object arguments) {
        return FormatHelper.format(arguments, false, -1, false);
    }

    public static String inspect(Object self) {
        return FormatHelper.format(self, true);
    }

    public static String format(Object arguments, boolean verbose) {
        return FormatHelper.format(arguments, verbose, -1);
    }

    public static String format(Object arguments, boolean inspect, boolean escapeBackslashes) {
        return FormatHelper.format(arguments, inspect, -1);
    }

    public static String format(Object arguments, boolean verbose, int maxSize) {
        return FormatHelper.format(arguments, verbose, maxSize, false);
    }

    public static String format(Object arguments, boolean inspect, boolean escapeBackslashes, int maxSize) {
        return FormatHelper.format(arguments, inspect, escapeBackslashes, maxSize, false);
    }

    public static String toString(@NamedParams(value={@NamedParam(value="safe", type=Boolean.class), @NamedParam(value="maxSize", type=Integer.class), @NamedParam(value="verbose", type=Boolean.class), @NamedParam(value="escapeBackslashes", type=Boolean.class), @NamedParam(value="inspect", type=Boolean.class)}) Map<String, Object> options, Object arguments) {
        Object maxSize;
        Object safe = options.get("safe");
        if (!(safe instanceof Boolean)) {
            safe = false;
        }
        if (!((maxSize = options.get("maxSize")) instanceof Integer)) {
            maxSize = -1;
        }
        Object verbose = options.get("verbose");
        Object inspect = options.get("inspect");
        Object escapeBackslashes = options.get("escapeBackslashes");
        if (!(inspect instanceof Boolean)) {
            inspect = false;
        }
        if (!(escapeBackslashes instanceof Boolean)) {
            escapeBackslashes = false;
        }
        if (!(verbose instanceof Boolean)) {
            verbose = false;
        }
        if (Boolean.TRUE.equals(verbose)) {
            inspect = true;
            escapeBackslashes = true;
        }
        return FormatHelper.format(arguments, (Boolean)inspect, (Boolean)escapeBackslashes, (Integer)maxSize, (Boolean)safe);
    }

    public static String format(Object arguments, boolean verbose, int maxSize, boolean safe) {
        return FormatHelper.format(arguments, verbose, verbose, maxSize, safe);
    }

    public static String format(Object arguments, boolean inspect, boolean escapeBackslashes, int maxSize, boolean safe) {
        if (arguments == null) {
            NullObject nullObject = NullObject.getNullObject();
            return (String)nullObject.getMetaClass().invokeMethod((Object)nullObject, "toString", EMPTY_ARGS);
        }
        if (arguments.getClass().isArray()) {
            if (arguments instanceof Object[]) {
                return FormatHelper.toArrayString((Object[])arguments, inspect, escapeBackslashes, maxSize, safe);
            }
            if (arguments instanceof char[]) {
                return new String((char[])arguments);
            }
            return FormatHelper.formatCollection(DefaultTypeTransformation.arrayAsCollection(arguments), inspect, escapeBackslashes, maxSize, safe);
        }
        if (arguments instanceof Range) {
            Range range = (Range)arguments;
            try {
                if (inspect) {
                    return range.inspect();
                }
                return range.toString();
            }
            catch (RuntimeException ex) {
                if (!safe) {
                    throw ex;
                }
                return FormatHelper.handleFormattingException(arguments, ex);
            }
            catch (Exception ex) {
                if (!safe) {
                    throw new GroovyRuntimeException(ex);
                }
                return FormatHelper.handleFormattingException(arguments, ex);
            }
        }
        if (arguments instanceof Collection) {
            return FormatHelper.formatCollection((Collection)arguments, inspect, escapeBackslashes, maxSize, safe);
        }
        if (arguments instanceof Map) {
            return FormatHelper.formatMap((Map)arguments, inspect, escapeBackslashes, maxSize, safe);
        }
        if (arguments instanceof Element) {
            try {
                Method serialize = Class.forName(XMLUTIL_CLASS_FULL_NAME).getMethod(SERIALIZE_METHOD_NAME, Element.class);
                return (String)serialize.invoke(null, arguments);
            }
            catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        if (arguments instanceof CharSequence) {
            String arg;
            String string = arg = escapeBackslashes ? FormatHelper.escapeBackslashes(arguments.toString()) : arguments.toString();
            if (arguments instanceof String) {
                if (!inspect) {
                    return arg;
                }
                return !escapeBackslashes && FormatHelper.multiline(arg) ? "'''" + arg + "'''" : SQ + arg.replace(SQ, "\\'") + SQ;
            }
            if (!inspect) {
                return arg;
            }
            return !escapeBackslashes && FormatHelper.multiline(arg) ? "\"\"\"" + arg + "\"\"\"" : DQ + arg.replace(DQ, "\\\"") + DQ;
        }
        try {
            return arguments.toString();
        }
        catch (RuntimeException ex) {
            if (!safe) {
                throw ex;
            }
            return FormatHelper.handleFormattingException(arguments, ex);
        }
        catch (Exception ex) {
            if (!safe) {
                throw new GroovyRuntimeException(ex);
            }
            return FormatHelper.handleFormattingException(arguments, ex);
        }
    }

    private static boolean multiline(String s) {
        return s.contains("\n") || s.contains("\r");
    }

    public static String escapeBackslashes(String orig) {
        return orig.replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t").replace("\f", "\\f");
    }

    private static String handleFormattingException(Object item, Exception ex) {
        String hash;
        try {
            hash = Integer.toHexString(item.hashCode());
        }
        catch (Exception ignored) {
            hash = "????";
        }
        return "<" + FormatHelper.typeName(item) + "@" + hash + ">";
    }

    private static String formatMap(Map map, boolean inspect, boolean escapeBackslashes, int maxSize, boolean safe) {
        if (map.isEmpty()) {
            return "[:]";
        }
        StringBuilder buffer = new StringBuilder(5 * map.size() * 2);
        buffer.append('[');
        boolean first = true;
        for (Map.Entry o : map.entrySet()) {
            if (first) {
                first = false;
            } else {
                buffer.append(", ");
            }
            if (maxSize != -1 && buffer.length() > maxSize) {
                buffer.append("...");
                break;
            }
            Map.Entry entry = o;
            if (entry.getKey() == map) {
                buffer.append("(this Map)");
            } else {
                buffer.append(FormatHelper.format(entry.getKey(), inspect, escapeBackslashes, FormatHelper.sizeLeft(maxSize, buffer), safe));
            }
            buffer.append(":");
            if (entry.getValue() == map) {
                buffer.append("(this Map)");
                continue;
            }
            buffer.append(FormatHelper.format(entry.getValue(), inspect, escapeBackslashes, FormatHelper.sizeLeft(maxSize, buffer), safe));
        }
        buffer.append(']');
        return buffer.toString();
    }

    private static int sizeLeft(int maxSize, StringBuilder buffer) {
        return maxSize == -1 ? maxSize : Math.max(0, maxSize - buffer.length());
    }

    private static String formatCollection(Collection collection, boolean inspect, boolean escapeBackslashes, int maxSize, boolean safe) {
        StringBuilder buffer = new StringBuilder(5 * collection.size());
        buffer.append('[');
        boolean first = true;
        for (Object item : collection) {
            if (first) {
                first = false;
            } else {
                buffer.append(", ");
            }
            if (maxSize != -1 && buffer.length() > maxSize) {
                buffer.append("...");
                break;
            }
            if (item == collection) {
                buffer.append("(this Collection)");
                continue;
            }
            buffer.append(FormatHelper.format(item, inspect, escapeBackslashes, FormatHelper.sizeLeft(maxSize, buffer), safe));
        }
        buffer.append(']');
        return buffer.toString();
    }

    public static String toTypeString(Object[] arguments) {
        return FormatHelper.toTypeString(arguments, -1);
    }

    public static String toTypeString(Object[] arguments, int maxSize) {
        if (arguments == null) {
            return "null";
        }
        StringBuilder argBuf = new StringBuilder();
        for (int i = 0; i < arguments.length; ++i) {
            if (maxSize != -1 && argBuf.length() > maxSize) {
                argBuf.append("...");
                break;
            }
            if (i > 0) {
                argBuf.append(", ");
            }
            argBuf.append(arguments[i] != null ? FormatHelper.typeName(arguments[i]) : "null");
        }
        return argBuf.toString();
    }

    static String typeName(Object argument) {
        Class<?> aClass = argument.getClass();
        String pkgName = aClass.getPackage() == null ? "" : aClass.getPackage().getName();
        boolean useShort = DEFAULT_IMPORT_PKGS.contains(pkgName) || DEFAULT_IMPORT_CLASSES.contains(aClass.getName());
        return useShort ? aClass.getSimpleName() : aClass.getName();
    }

    public static String toMapString(Map arg) {
        return FormatHelper.toMapString(arg, -1);
    }

    public static String toMapString(Map arg, int maxSize) {
        return FormatHelper.formatMap(arg, false, false, maxSize, false);
    }

    public static String toListString(Collection arg) {
        return FormatHelper.toListString(arg, -1);
    }

    public static String toListString(Collection arg, int maxSize) {
        return FormatHelper.toListString(arg, maxSize, false);
    }

    public static String toListString(Collection arg, int maxSize, boolean safe) {
        return FormatHelper.formatCollection(arg, false, false, maxSize, safe);
    }

    public static String toArrayString(Object[] arguments) {
        return FormatHelper.toArrayString(arguments, false, false, -1, false);
    }

    private static String toArrayString(Object[] array, boolean inspect, boolean escapeBackslashes, int maxSize, boolean safe) {
        if (array == null) {
            return "null";
        }
        boolean first = true;
        StringBuilder argBuf = new StringBuilder(array.length);
        argBuf.append('[');
        for (Object item : array) {
            if (first) {
                first = false;
            } else {
                argBuf.append(", ");
            }
            if (maxSize != -1 && argBuf.length() > maxSize) {
                argBuf.append("...");
                break;
            }
            if (item == array) {
                argBuf.append("(this array)");
                continue;
            }
            argBuf.append(FormatHelper.format(item, inspect, escapeBackslashes, FormatHelper.sizeLeft(maxSize, argBuf), safe));
        }
        argBuf.append(']');
        return argBuf.toString();
    }

    public static String toArrayString(Object[] arguments, int maxSize, boolean safe) {
        return FormatHelper.toArrayString(arguments, false, false, maxSize, safe);
    }

    public static void write(Writer out, Object object) throws IOException {
        if (object instanceof String) {
            out.write((String)object);
        } else if (object instanceof Object[]) {
            out.write(FormatHelper.toArrayString((Object[])object));
        } else if (object instanceof Map) {
            out.write(FormatHelper.toMapString((Map)object));
        } else if (object instanceof Collection) {
            out.write(FormatHelper.toListString((Collection)object));
        } else if (object instanceof Writable) {
            Writable writable = (Writable)object;
            writable.writeTo(out);
        } else if (object instanceof InputStream || object instanceof Reader) {
            Reader reader = object instanceof InputStream ? new InputStreamReader((InputStream)object) : (Reader)object;
            try (Reader r = reader;){
                int i;
                char[] chars = new char[8192];
                while ((i = r.read(chars)) != -1) {
                    out.write(chars, 0, i);
                }
            }
        } else {
            out.write(FormatHelper.toString(object));
        }
    }

    public static void append(Appendable out, Object object) throws IOException {
        if (object instanceof String) {
            out.append((String)object);
        } else if (object instanceof Object[]) {
            out.append(FormatHelper.toArrayString((Object[])object));
        } else if (object instanceof Map) {
            out.append(FormatHelper.toMapString((Map)object));
        } else if (object instanceof Collection) {
            out.append(FormatHelper.toListString((Collection)object));
        } else if (object instanceof Writable) {
            Writable writable = (Writable)object;
            StringBuilderWriter stringWriter = new StringBuilderWriter();
            writable.writeTo(stringWriter);
            out.append(((Object)stringWriter).toString());
        } else if (object instanceof InputStream || object instanceof Reader) {
            try (Reader reader = object instanceof InputStream ? new InputStreamReader((InputStream)object) : (Reader)object;){
                int i;
                char[] chars = new char[8192];
                while ((i = reader.read(chars)) != -1) {
                    for (int j = 0; j < i; ++j) {
                        out.append(chars[j]);
                    }
                }
            }
        } else {
            out.append(FormatHelper.toString(object));
        }
    }

    static {
        for (String pkgName : ResolveVisitor.DEFAULT_IMPORTS) {
            DEFAULT_IMPORT_PKGS.add(pkgName.substring(0, pkgName.length() - 1));
        }
        DEFAULT_IMPORT_CLASSES.add("java.math.BigDecimal");
        DEFAULT_IMPORT_CLASSES.add("java.math.BigInteger");
    }
}

