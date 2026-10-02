/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GString;
import groovy.lang.GroovyInterceptable;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.GroovySystem;
import groovy.lang.MetaClass;
import groovy.lang.MetaClassRegistry;
import groovy.lang.MissingMethodException;
import groovy.lang.MissingPropertyException;
import groovy.lang.Script;
import groovy.lang.SpreadMap;
import groovy.lang.SpreadMapEvaluatingException;
import groovy.lang.Tuple;
import java.beans.Introspector;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.groovy.internal.util.UncheckedThrow;
import org.apache.groovy.runtime.ObjectUtil;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.FormatHelper;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.codehaus.groovy.runtime.MethodClosure;
import org.codehaus.groovy.runtime.NullObject;
import org.codehaus.groovy.runtime.RegexSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.StringGroovyMethods;
import org.codehaus.groovy.runtime.metaclass.MetaClassRegistryImpl;
import org.codehaus.groovy.runtime.metaclass.MissingMethodExecutionFailed;
import org.codehaus.groovy.runtime.powerassert.PowerAssertionError;
import org.codehaus.groovy.runtime.wrappers.PojoWrapper;

public class InvokerHelper {
    public static final Object[] EMPTY_ARGS = new Object[0];
    protected static final Object[] EMPTY_ARGUMENTS = EMPTY_ARGS;
    protected static final Class[] EMPTY_TYPES = new Class[0];
    public static final MetaClassRegistry metaRegistry = GroovySystem.getMetaClassRegistry();
    public static final String MAIN_METHOD_NAME = "main";

    public static void removeClass(Class clazz) {
        metaRegistry.removeMetaClass(clazz);
        ClassInfo.remove(clazz);
        Introspector.flushFromCaches(clazz);
    }

    public static Object invokeMethodSafe(Object object, String methodName, Object arguments) {
        if (object != null) {
            return InvokerHelper.invokeMethod(object, methodName, arguments);
        }
        return null;
    }

    public static Object invokeStaticMethod(String klass, String methodName, Object arguments) throws ClassNotFoundException {
        Class<?> type = Class.forName(klass);
        return InvokerHelper.invokeStaticMethod(type, methodName, arguments);
    }

    public static Object invokeStaticNoArgumentsMethod(Class type, String methodName) {
        return InvokerHelper.invokeStaticMethod(type, methodName, (Object)EMPTY_ARGS);
    }

    public static Object invokeConstructorOf(String klass, Object arguments) throws ClassNotFoundException {
        Class<?> type = Class.forName(klass);
        return InvokerHelper.invokeConstructorOf(type, arguments);
    }

    public static Object invokeNoArgumentsConstructorOf(Class type) {
        return InvokerHelper.invokeConstructorOf(type, (Object)EMPTY_ARGS);
    }

    public static Object invokeClosure(Object closure, Object arguments) {
        return InvokerHelper.invokeMethod(closure, "doCall", arguments);
    }

    public static List asList(Object value) {
        if (value == null) {
            return Collections.EMPTY_LIST;
        }
        if (value instanceof List) {
            return (List)value;
        }
        if (value.getClass().isArray()) {
            return Arrays.asList((Object[])value);
        }
        if (value instanceof Enumeration) {
            Enumeration e = (Enumeration)value;
            ArrayList answer = new ArrayList();
            while (e.hasMoreElements()) {
                answer.add(e.nextElement());
            }
            return answer;
        }
        return Collections.singletonList(value);
    }

    public static Object getAttribute(Object object, String attribute) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof Class) {
            return metaRegistry.getMetaClass((Class)object).getAttribute(object, attribute);
        }
        if (object instanceof GroovyObject) {
            return ((GroovyObject)object).getMetaClass().getAttribute(object, attribute);
        }
        return metaRegistry.getMetaClass(object.getClass()).getAttribute(object, attribute);
    }

    public static void setAttribute(Object object, String attribute, Object newValue) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof Class) {
            metaRegistry.getMetaClass((Class)object).setAttribute(object, attribute, newValue);
        } else if (object instanceof GroovyObject) {
            ((GroovyObject)object).getMetaClass().setAttribute(object, attribute, newValue);
        } else {
            metaRegistry.getMetaClass(object.getClass()).setAttribute(object, attribute, newValue);
        }
    }

    public static Object getProperty(Object object, String property) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof GroovyObject) {
            GroovyObject pogo = (GroovyObject)object;
            return pogo.getProperty(property);
        }
        if (object instanceof Class) {
            Class c = (Class)object;
            return metaRegistry.getMetaClass(c).getProperty(object, property);
        }
        return ((MetaClassRegistryImpl)metaRegistry).getMetaClass(object).getProperty(object, property);
    }

    public static Object getPropertySafe(Object object, String property) {
        if (object != null) {
            return InvokerHelper.getProperty(object, property);
        }
        return null;
    }

    public static void setProperty(Object object, String property, Object newValue) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof GroovyObject) {
            GroovyObject pogo = (GroovyObject)object;
            pogo.setProperty(property, newValue);
        } else if (object instanceof Class) {
            metaRegistry.getMetaClass((Class)object).setProperty(object, property, newValue);
        } else {
            ((MetaClassRegistryImpl)GroovySystem.getMetaClassRegistry()).getMetaClass(object).setProperty(object, property, newValue);
        }
    }

    public static void setProperty2(Object newValue, Object object, String property) {
        InvokerHelper.setProperty(object, property, newValue);
    }

    public static void setGroovyObjectProperty(Object newValue, GroovyObject object, String property) {
        object.setProperty(property, newValue);
    }

    public static Object getGroovyObjectProperty(GroovyObject object, String property) {
        return object.getProperty(property);
    }

    public static void setPropertySafe2(Object newValue, Object object, String property) {
        if (object != null) {
            InvokerHelper.setProperty2(newValue, object, property);
        }
    }

    public static Closure getMethodPointer(Object object, String methodName) {
        if (object == null) {
            throw new NullPointerException("Cannot access method pointer for '" + methodName + "' on null object");
        }
        return new MethodClosure(object, methodName);
    }

    public static Object unaryMinus(Object value) {
        if (value instanceof Integer) {
            Integer number = (Integer)value;
            return -number.intValue();
        }
        if (value instanceof Long) {
            Long number = (Long)value;
            return -number.longValue();
        }
        if (value instanceof BigInteger) {
            return ((BigInteger)value).negate();
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal)value).negate();
        }
        if (value instanceof Double) {
            Double number = (Double)value;
            return -number.doubleValue();
        }
        if (value instanceof Float) {
            Float number = (Float)value;
            return Float.valueOf(-number.floatValue());
        }
        if (value instanceof Short) {
            Short number = (Short)value;
            return -number.shortValue();
        }
        if (value instanceof Byte) {
            Byte number = (Byte)value;
            return -number.byteValue();
        }
        if (value instanceof ArrayList) {
            ArrayList<Object> newlist = new ArrayList<Object>();
            for (Object o : (ArrayList)value) {
                newlist.add(InvokerHelper.unaryMinus(o));
            }
            return newlist;
        }
        return InvokerHelper.invokeMethod(value, "negative", EMPTY_ARGS);
    }

    public static Object unaryPlus(Object value) {
        if (value instanceof Integer || value instanceof Long || value instanceof BigInteger || value instanceof BigDecimal || value instanceof Double || value instanceof Float || value instanceof Short || value instanceof Byte) {
            return value;
        }
        if (value instanceof ArrayList) {
            ArrayList<Object> newlist = new ArrayList<Object>();
            for (Object o : (ArrayList)value) {
                newlist.add(InvokerHelper.unaryPlus(o));
            }
            return newlist;
        }
        return InvokerHelper.invokeMethod(value, "positive", EMPTY_ARGS);
    }

    public static Matcher findRegex(Object left, Object right) {
        String regexToCompareTo;
        String stringToCompare = left instanceof String ? (String)left : FormatHelper.toString(left);
        if (right instanceof String) {
            regexToCompareTo = (String)right;
        } else {
            if (right instanceof Pattern) {
                Pattern pattern = (Pattern)right;
                return pattern.matcher(stringToCompare);
            }
            regexToCompareTo = FormatHelper.toString(right);
        }
        return Pattern.compile(regexToCompareTo).matcher(stringToCompare);
    }

    public static boolean matchRegex(Object left, Object right) {
        if (left == null || right == null) {
            return false;
        }
        Pattern pattern = right instanceof Pattern ? (Pattern)right : Pattern.compile(FormatHelper.toString(right));
        String stringToCompare = FormatHelper.toString(left);
        Matcher matcher = pattern.matcher(stringToCompare);
        RegexSupport.setLastMatcher(matcher);
        return matcher.matches();
    }

    public static Tuple createTuple(Object[] array) {
        return new Tuple<Object>(array);
    }

    public static SpreadMap spreadMap(Object value) {
        if (value instanceof Map) {
            Object[] values = new Object[((Map)value).keySet().size() * 2];
            int index = 0;
            for (Object key : ((Map)value).keySet()) {
                values[index++] = key;
                values[index++] = ((Map)value).get(key);
            }
            return new SpreadMap(values);
        }
        throw new SpreadMapEvaluatingException("Cannot spread the map " + FormatHelper.typeName(value) + ", value " + value);
    }

    public static List createList(Object[] values) {
        ArrayList answer = new ArrayList(values.length);
        Collections.addAll(answer, values);
        return answer;
    }

    public static int initialCapacity(int initialEntryCnt) {
        if (0 == initialEntryCnt) {
            return 16;
        }
        return Integer.highestOneBit(initialEntryCnt) << 1;
    }

    public static Map createMap(Object[] values) {
        LinkedHashMap<Object, Object> answer = new LinkedHashMap<Object, Object>(InvokerHelper.initialCapacity(values.length / 2));
        int i = 0;
        int n = values.length;
        while (i < n - 1) {
            if (values[i] instanceof SpreadMap && values[i + 1] instanceof Map) {
                Map smap = (Map)values[i + 1];
                Iterator iterator = smap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry e;
                    Map.Entry entry = e = iterator.next();
                    answer.put(entry.getKey(), entry.getValue());
                }
                i += 2;
                continue;
            }
            answer.put(values[i++], values[i++]);
        }
        return answer;
    }

    public static void assertFailed(Object expression, Object message) {
        throw InvokerHelper.createAssertError(expression, message);
    }

    public static AssertionError createAssertError(Object expression, Object message) {
        if (message == null || "".equals(message)) {
            return new PowerAssertionError(expression.toString());
        }
        return new AssertionError((Object)(message + ". Expression: " + expression));
    }

    public static Object runScript(Class scriptClass, String[] args) {
        Binding context = new Binding(args);
        Script script = InvokerHelper.createScript(scriptClass, context);
        return InvokerHelper.invokeMethod(script, "run", EMPTY_ARGS);
    }

    public static Script createScript(final Class scriptClass, Binding context) {
        if (scriptClass == null) {
            return new NullScript(context);
        }
        try {
            Script script;
            if (Script.class.isAssignableFrom(scriptClass)) {
                script = InvokerHelper.newScript(scriptClass, context);
            } else {
                try {
                    Class<?> glBinding = scriptClass.getClassLoader().loadClass(Binding.class.getName());
                    Constructor contextualConstructor = scriptClass.getDeclaredConstructor(glBinding);
                    Object binding = glBinding.getDeclaredConstructor(Map.class).newInstance(context.getVariables());
                    final Object scriptx = contextualConstructor.newInstance(binding);
                    script = new Script(){

                        @Override
                        public Object run() {
                            return InvokerHelper.invokeMethod(scriptx, "run", EMPTY_ARGUMENTS);
                        }
                    };
                }
                catch (ClassNotFoundException | NoSuchMethodException | SecurityException ignore) {
                    script = new Script(context){

                        @Override
                        public Object run() {
                            Object[] mainArgs = new Object[]{new String[0]};
                            try {
                                Object args = this.getProperty("args");
                                if (args instanceof String[]) {
                                    mainArgs[0] = args;
                                }
                            }
                            catch (MissingPropertyException missingPropertyException) {
                                // empty catch block
                            }
                            return InvokerHelper.invokeStaticMethod(scriptClass, InvokerHelper.MAIN_METHOD_NAME, (Object)mainArgs);
                        }
                    };
                    MetaClass smc = InvokerHelper.getMetaClass(scriptClass);
                    context.getVariables().forEach((key, value) -> {
                        String name = key.toString();
                        if (!name.startsWith("_")) {
                            InvokerHelper.setPropertySafe(scriptClass, smc, name, value);
                        }
                    });
                }
            }
            return script;
        }
        catch (Exception e) {
            throw new GroovyRuntimeException("Failed to create Script instance for class: " + scriptClass + ". Reason: " + e, e);
        }
    }

    public static Script newScript(Class<? extends Script> scriptClass, Binding context) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Script script;
        try {
            Constructor<? extends Script> constructor = scriptClass.getConstructor(Binding.class);
            script = constructor.newInstance(context);
        }
        catch (NoSuchMethodException e) {
            script = scriptClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            script.setBinding(context);
        }
        return script;
    }

    public static void setProperties(Object object, Map map) {
        MetaClass mc = InvokerHelper.getMetaClass(object);
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry o;
            Map.Entry entry = o = iterator.next();
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            InvokerHelper.setPropertySafe(object, mc, key, value);
        }
    }

    private static void setPropertySafe(Object object, MetaClass mc, String key, Object value) {
        block3: {
            try {
                mc.setProperty(object, key, value);
            }
            catch (MissingPropertyException missingPropertyException) {
            }
            catch (InvokerInvocationException iie) {
                Throwable cause = iie.getCause();
                if (cause instanceof IllegalArgumentException) break block3;
                throw iie;
            }
        }
    }

    public static List createRange(Object from, Object to, boolean exclusiveLeft, boolean exclusiveRight) {
        try {
            return ScriptBytecodeAdapter.createRange(from, to, exclusiveLeft, exclusiveRight);
        }
        catch (Error | RuntimeException re) {
            throw re;
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static List createRange(Object from, Object to, boolean inclusive) {
        return InvokerHelper.createRange(from, to, false, !inclusive);
    }

    public static Object bitwiseNegate(Object value) {
        if (value instanceof Integer) {
            Integer number = (Integer)value;
            return ~number.intValue();
        }
        if (value instanceof Long) {
            Long number = (Long)value;
            return number ^ 0xFFFFFFFFFFFFFFFFL;
        }
        if (value instanceof BigInteger) {
            return ((BigInteger)value).not();
        }
        if (value instanceof String) {
            return StringGroovyMethods.bitwiseNegate(value.toString());
        }
        if (value instanceof GString) {
            return StringGroovyMethods.bitwiseNegate(value.toString());
        }
        if (value instanceof ArrayList) {
            ArrayList<Object> newlist = new ArrayList<Object>();
            for (Object o : (ArrayList)value) {
                newlist.add(InvokerHelper.bitwiseNegate(o));
            }
            return newlist;
        }
        return InvokerHelper.invokeMethod(value, "bitwiseNegate", EMPTY_ARGS);
    }

    public static MetaClassRegistry getMetaRegistry() {
        return metaRegistry;
    }

    public static MetaClass getMetaClass(Object object) {
        if (object instanceof GroovyObject) {
            return ((GroovyObject)object).getMetaClass();
        }
        if (object instanceof Class) {
            return metaRegistry.getMetaClass((Class)object);
        }
        return ((MetaClassRegistryImpl)GroovySystem.getMetaClassRegistry()).getMetaClass(object);
    }

    public static MetaClass getMetaClass(Class cls) {
        return metaRegistry.getMetaClass(cls);
    }

    public static Object invokeMethod(Object object, String methodName, Object arguments) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof Class) {
            Class theClass = (Class)object;
            MetaClass metaClass = metaRegistry.getMetaClass(theClass);
            return metaClass.invokeStaticMethod(object, methodName, InvokerHelper.asArray(arguments));
        }
        if ("clone".equals(methodName) && 0 == InvokerHelper.asArray(arguments).length) {
            try {
                return ObjectUtil.cloneObject(object);
            }
            catch (Throwable t) {
                UncheckedThrow.rethrow(t);
            }
        }
        if (!(object instanceof GroovyObject)) {
            return InvokerHelper.invokePojoMethod(object, methodName, arguments);
        }
        return InvokerHelper.invokePogoMethod(object, methodName, arguments);
    }

    static Object invokePojoMethod(Object object, String methodName, Object arguments) {
        MetaClass metaClass = InvokerHelper.getMetaClass(object);
        return metaClass.invokeMethod(object, methodName, InvokerHelper.asArray(arguments));
    }

    static Object invokePogoMethod(Object object, String methodName, Object arguments) {
        GroovyObject groovy = (GroovyObject)object;
        boolean intercepting = groovy instanceof GroovyInterceptable;
        try {
            if (intercepting) {
                return groovy.invokeMethod(methodName, InvokerHelper.asUnwrappedArray(arguments));
            }
            return groovy.getMetaClass().invokeMethod(object, methodName, InvokerHelper.asArray(arguments));
        }
        catch (MissingMethodException e) {
            if (e instanceof MissingMethodExecutionFailed) {
                throw (MissingMethodException)e.getCause();
            }
            if (!intercepting && e.getMethod().equals(methodName) && object.getClass() == e.getType()) {
                return groovy.invokeMethod(methodName, InvokerHelper.asUnwrappedArray(arguments));
            }
            throw e;
        }
    }

    public static Object invokeSuperMethod(Object object, String methodName, Object arguments) {
        if (object == null) {
            throw new NullPointerException("Cannot invoke method " + methodName + "() on null object");
        }
        Class<?> theClass = object.getClass();
        MetaClass metaClass = metaRegistry.getMetaClass(theClass.getSuperclass());
        return metaClass.invokeMethod(object, methodName, InvokerHelper.asArray(arguments));
    }

    public static Object invokeStaticMethod(Class type, String method, Object arguments) {
        MetaClass metaClass = metaRegistry.getMetaClass(type);
        return metaClass.invokeStaticMethod(type, method, InvokerHelper.asArray(arguments));
    }

    public static Object invokeConstructorOf(Class type, Object arguments) {
        MetaClass metaClass = metaRegistry.getMetaClass(type);
        return metaClass.invokeConstructor(InvokerHelper.asArray(arguments));
    }

    public static Object[] asArray(Object arguments) {
        if (arguments == null) {
            return EMPTY_ARGUMENTS;
        }
        if (arguments instanceof Object[]) {
            return (Object[])arguments;
        }
        return new Object[]{arguments};
    }

    public static Object[] asUnwrappedArray(Object arguments) {
        Object[] args = InvokerHelper.asArray(arguments);
        for (int i = 0; i < args.length; ++i) {
            if (!(args[i] instanceof PojoWrapper)) continue;
            args[i] = ((PojoWrapper)args[i]).unwrap();
        }
        return args;
    }

    public static Iterator<Object> asIterator(Object o) {
        return (Iterator)InvokerHelper.invokeMethod(o, "iterator", EMPTY_ARGS);
    }

    @Deprecated
    public static String toString(Object arguments) {
        return FormatHelper.toString(arguments);
    }

    @Deprecated
    public static String inspect(Object self) {
        return FormatHelper.inspect(self);
    }

    @Deprecated
    public static void write(Writer out, Object object) throws IOException {
        FormatHelper.write(out, object);
    }

    @Deprecated
    public static void append(Appendable out, Object object) throws IOException {
        FormatHelper.append(out, object);
    }

    @Deprecated
    protected static String format(Object arguments, boolean verbose) {
        return FormatHelper.format(arguments, verbose);
    }

    @Deprecated
    public static String format(Object arguments, boolean verbose, int maxSize) {
        return FormatHelper.format(arguments, verbose, maxSize);
    }

    @Deprecated
    public static String format(Object arguments, boolean verbose, int maxSize, boolean safe) {
        return FormatHelper.format(arguments, verbose, maxSize, safe);
    }

    @Deprecated
    public static String escapeBackslashes(String orig) {
        return FormatHelper.escapeBackslashes(orig);
    }

    @Deprecated
    public static String toTypeString(Object[] arguments) {
        return FormatHelper.toTypeString(arguments);
    }

    @Deprecated
    public static String toTypeString(Object[] arguments, int maxSize) {
        return FormatHelper.toTypeString(arguments, maxSize);
    }

    @Deprecated
    public static String toMapString(Map arg) {
        return FormatHelper.toMapString(arg);
    }

    @Deprecated
    public static String toMapString(Map arg, int maxSize) {
        return FormatHelper.toMapString(arg, maxSize);
    }

    @Deprecated
    public static String toListString(Collection arg) {
        return FormatHelper.toListString(arg);
    }

    @Deprecated
    public static String toListString(Collection arg, int maxSize) {
        return FormatHelper.toListString(arg, maxSize);
    }

    @Deprecated
    public static String toListString(Collection arg, int maxSize, boolean safe) {
        return FormatHelper.toListString(arg, maxSize, safe);
    }

    @Deprecated
    public static String toArrayString(Object[] arguments) {
        return FormatHelper.toArrayString(arguments);
    }

    @Deprecated
    public static String toArrayString(Object[] arguments, int maxSize, boolean safe) {
        return FormatHelper.toArrayString(arguments, maxSize, safe);
    }

    static class NullScript
    extends Script {
        public NullScript(Binding context) {
            super(context);
        }

        public NullScript() {
            this(new Binding());
        }

        @Override
        public Object run() {
            return null;
        }
    }
}

