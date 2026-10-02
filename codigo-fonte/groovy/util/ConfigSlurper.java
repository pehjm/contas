/*
 * Decompiled with CFR 0.152.
 */
package groovy.util;

import groovy.lang.Closure;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.GroovySystem;
import groovy.lang.MetaClass;
import groovy.lang.MissingMethodException;
import groovy.lang.Reference;
import groovy.lang.Script;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovy.util.ConfigBinding;
import groovy.util.ConfigObject;
import java.beans.Transient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class ConfigSlurper
implements GroovyObject {
    private static final Object ENVIRONMENTS_METHOD;
    private GroovyClassLoader classLoader;
    private Map bindingVars;
    private final Map<String, String> conditionValues;
    private final Stack<Map<String, ConfigObject>> conditionalBlocks;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    public ConfigSlurper() {
        this("");
    }

    public ConfigSlurper(String env) {
        MetaClass metaClass;
        Map map;
        Map map2;
        CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, GroovyClassLoader.class);
        this.classLoader = IndyInterface.bootstrap("cast", "()", 0, callSite);
        this.bindingVars = map2 = ScriptBytecodeAdapter.createMap(new Object[0]);
        this.conditionValues = map = ScriptBytecodeAdapter.createMap(new Object[0]);
        CallSite callSite2 = IndyInterface.bootstrap("init", "<init>", 0, Stack.class);
        this.conditionalBlocks = IndyInterface.bootstrap("cast", "()", 0, callSite2);
        this.metaClass = metaClass = this.$getStaticMetaClass();
        String string = env;
        IndyInterface.bootstrap("invoke", "putAt", 0, this.conditionValues, ENVIRONMENTS_METHOD, string);
    }

    public void registerConditionalBlock(String blockName, String blockValue) {
        if (IndyInterface.bootstrap("cast", "()", 0, blockName) != false) {
            if (IndyInterface.bootstrap("cast", "()", 0, blockValue) == false) {
                IndyInterface.bootstrap("invoke", "remove", 0, this.conditionValues, blockName);
            } else {
                String string = blockValue;
                IndyInterface.bootstrap("invoke", "putAt", 0, this.conditionValues, blockName, string);
            }
        }
    }

    public Map<String, String> getConditionalBlockValues() {
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "unmodifiableMap", 0, Collections.class, this.conditionValues));
    }

    public String getEnvironment() {
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, this.conditionValues, ENVIRONMENTS_METHOD));
    }

    public void setEnvironment(String environment) {
        String string = environment;
        IndyInterface.bootstrap("invoke", "putAt", 0, this.conditionValues, ENVIRONMENTS_METHOD, string);
    }

    public void setBinding(Map vars) {
        Map map;
        this.bindingVars = map = vars;
    }

    public ConfigObject parse(Properties properties) {
        CallSite config = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, ConfigObject.class));
        Object key = null;
        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, IndyInterface.bootstrap("invoke", "keySet", 0, properties)));
        if (callSite != null) {
            while (callSite.hasNext()) {
                key = callSite.next();
                IndyInterface.bootstrap("invoke", "parseKey", 2, this, key, config, properties);
            }
        }
        return config;
    }

    private void parseKey(Object key, ConfigObject config, Properties properties) {
        CallSite tokens = IndyInterface.bootstrap("invoke", "split", 0, key, "\\.");
        ConfigObject current = config;
        Reference<Object> last = new Reference<Object>(null);
        last.get();
        CallSite lastToken = null;
        Boolean foundBase = false;
        Object token = null;
        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, tokens));
        if (callSite != null) {
            while (callSite.hasNext()) {
                token = callSite.next();
                if (IndyInterface.bootstrap("cast", "()", 0, foundBase) != false) {
                    IndyInterface.bootstrap("invoke", "plus", 0, lastToken, IndyInterface.bootstrap("invoke", "plus", 0, ".", token));
                    last.get();
                    continue;
                }
                last.set(current);
                ScriptBytecodeAdapter.getProperty(ConfigSlurper.class, current, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{token}, new String[]{"", ""}))));
                if (!(!(current instanceof ConfigObject))) continue;
                foundBase = true;
            }
        }
        if (current instanceof ConfigObject) {
            if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, last.get(), lastToken)) != false) {
                CallSite flattened = IndyInterface.bootstrap("invoke", "flatten", 0, last.get());
                IndyInterface.bootstrap("invoke", "clear", 0, last.get());
                public final class _parseKey_closure1
                extends Closure
                implements GeneratedClosure {
                    private /* synthetic */ Reference last;
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _parseKey_closure1(Object _outerInstance, Object _thisObject, Reference last) {
                        super(_outerInstance, _thisObject);
                        Reference reference;
                        this.last = reference = last;
                    }

                    public Object doCall(Object k2, Object v2) {
                        Object object = v2;
                        IndyInterface.bootstrap("invoke", "putAt", 0, this.last.get(), k2, object);
                        return object;
                    }

                    @Generated
                    public Object call(Object k2, Object v2) {
                        return IndyInterface.bootstrap("invoke", "doCall", 2, this, k2, v2);
                    }

                    @Generated
                    public Object getLast() {
                        return this.last.get();
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _parseKey_closure1.class) {
                            return ScriptBytecodeAdapter.initMetaClass(this);
                        }
                        ClassInfo classInfo = $staticClassInfo;
                        if (classInfo == null) {
                            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return classInfo.getMetaClass();
                    }

                    public /* synthetic */ MethodHandles.Lookup $getLookup() {
                        return MethodHandles.lookup();
                    }
                }
                IndyInterface.bootstrap("invoke", "each", 0, flattened, new _parseKey_closure1(this, this, last));
                CallSite callSite2 = IndyInterface.bootstrap("invoke", "get", 0, properties, key);
                IndyInterface.bootstrap("invoke", "putAt", 0, last.get(), lastToken, callSite2);
            } else {
                CallSite callSite3 = IndyInterface.bootstrap("invoke", "get", 0, properties, key);
                IndyInterface.bootstrap("invoke", "putAt", 0, last.get(), lastToken, callSite3);
            }
        }
    }

    public ConfigObject parse(String script) {
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "parse", 2, this, IndyInterface.bootstrap("invoke", "parseClass", 0, this.classLoader, script)));
    }

    public ConfigObject parse(Class scriptClass) {
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "parse", 2, this, IndyInterface.bootstrap("invoke", "newInstance", 0, scriptClass)));
    }

    public ConfigObject parse(Script script) {
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "parse", 2, this, script, null));
    }

    public ConfigObject parse(URL scriptLocation) {
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "parse", 2, this, IndyInterface.bootstrap("invoke", "newInstance", 0, IndyInterface.bootstrap("invoke", "parseClass", 0, this.classLoader, IndyInterface.bootstrap("getProperty", "text", 0, scriptLocation))), scriptLocation));
    }

    public ConfigObject parse(Script script, URL location) {
        Reference<CallSite> currentConditionalBlock = new Reference<CallSite>(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, Stack.class)));
        Reference<CallSite> config = new Reference<CallSite>(IndyInterface.bootstrap("cast", "()", 0, location) != false ? IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, ConfigObject.class, location)) : IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, ConfigObject.class)));
        IndyInterface.bootstrap("invoke", "removeMetaClass", 0, IndyInterface.bootstrap("getProperty", "metaClassRegistry", 0, GroovySystem.class), IndyInterface.bootstrap("getProperty", "class", 4, script));
        Reference<CallSite> mc = new Reference<CallSite>(IndyInterface.bootstrap("getProperty", "metaClass", 0, IndyInterface.bootstrap("getProperty", "class", 4, script)));
        Reference<String> prefix = new Reference<String>("");
        Reference<CallSite> stack = new Reference<CallSite>(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, LinkedList.class)));
        IndyInterface.bootstrap("invoke", "leftShift", 0, (LinkedList)((Object)stack.get()), ScriptBytecodeAdapter.createMap(new Object[]{"config", config.get(), "scope", ScriptBytecodeAdapter.createMap(new Object[0])}));
        public final class _parse_closure2
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference stack;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _parse_closure2(Object _outerInstance, Object _thisObject, Reference stack) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.stack = reference = stack;
            }

            public Object doCall(Object co) {
                return IndyInterface.bootstrap("invoke", "leftShift", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get()), ScriptBytecodeAdapter.createMap(new Object[]{"config", co, "scope", IndyInterface.bootstrap("invoke", "clone", 0, IndyInterface.bootstrap("getProperty", "scope", 0, IndyInterface.bootstrap("getProperty", "last", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get()))))}));
            }

            @Generated
            public LinkedList getStack() {
                return IndyInterface.bootstrap("cast", "()", 0, this.stack.get());
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _parse_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public /* synthetic */ MethodHandles.Lookup $getLookup() {
                return MethodHandles.lookup();
            }
        }
        Reference<_parse_closure2> pushStack = new Reference<_parse_closure2>(new _parse_closure2(this, this, stack));
        public final class _parse_closure3
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference stack;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _parse_closure3(Object _outerInstance, Object _thisObject, Reference stack) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.stack = reference = stack;
            }

            public Object doCall(Object name, Object co) {
                CallSite current = IndyInterface.bootstrap("getProperty", "last", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get()));
                Object object = co;
                IndyInterface.bootstrap("invoke", "putAt", 0, IndyInterface.bootstrap("getProperty", "config", 0, current), name, object);
                Object object2 = co;
                IndyInterface.bootstrap("invoke", "putAt", 0, IndyInterface.bootstrap("getProperty", "scope", 0, current), name, object2);
                return object2;
            }

            @Generated
            public Object call(Object name, Object co) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, name, co);
            }

            @Generated
            public LinkedList getStack() {
                return IndyInterface.bootstrap("cast", "()", 0, this.stack.get());
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _parse_closure3.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public /* synthetic */ MethodHandles.Lookup $getLookup() {
                return MethodHandles.lookup();
            }
        }
        Reference<_parse_closure3> assignName = new Reference<_parse_closure3>(new _parse_closure3(this, this, stack));
        public final class _parse_closure4
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference stack;
            private /* synthetic */ Reference assignName;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _parse_closure4(Object _outerInstance, Object _thisObject, Reference stack, Reference assignName) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.stack = reference2 = stack;
                this.assignName = reference = assignName;
            }

            public Object doCall(String name) {
                CallSite current = IndyInterface.bootstrap("getProperty", "last", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get()));
                CallSite result = null;
                if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "get", 0, IndyInterface.bootstrap("getProperty", "config", 0, current), name)) != false) {
                    result = IndyInterface.bootstrap("invoke", "get", 0, IndyInterface.bootstrap("getProperty", "config", 0, current), name);
                } else if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "scope", 0, current), name)) != false) {
                    result = IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "scope", 0, current), name);
                } else {
                    try {
                        result = IndyInterface.bootstrap("invoke", "getProperty", 0, InvokerHelper.class, this.getThisObject(), name);
                    }
                    catch (GroovyRuntimeException e) {
                        result = IndyInterface.bootstrap("init", "<init>", 0, ConfigObject.class);
                        IndyInterface.bootstrap("invoke", "call", 0, this.assignName.get(), name, result);
                    }
                }
                return result;
            }

            @Generated
            public Object call(String name) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, name);
            }

            @Generated
            public LinkedList getStack() {
                return IndyInterface.bootstrap("cast", "()", 0, this.stack.get());
            }

            @Generated
            public Object getAssignName() {
                return this.assignName.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _parse_closure4.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public /* synthetic */ MethodHandles.Lookup $getLookup() {
                return MethodHandles.lookup();
            }
        }
        _parse_closure4 _parse_closure42 = new _parse_closure4(this, this, stack, assignName);
        ScriptBytecodeAdapter.setProperty(_parse_closure42, null, mc.get(), "getProperty");
        Reference<CallSite> overrides = new Reference<CallSite>(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, ConfigObject.class)));
        public final class _parse_closure5
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference currentConditionalBlock;
            private /* synthetic */ Reference stack;
            private /* synthetic */ Reference config;
            private /* synthetic */ Reference overrides;
            private /* synthetic */ Reference pushStack;
            private /* synthetic */ Reference assignName;
            private /* synthetic */ Reference prefix;
            private /* synthetic */ Reference mc;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _parse_closure5(Object _outerInstance, Object _thisObject, Reference currentConditionalBlock, Reference stack, Reference config, Reference overrides, Reference pushStack, Reference assignName, Reference prefix, Reference mc) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                Reference reference3;
                Reference reference4;
                Reference reference5;
                Reference reference6;
                Reference reference7;
                Reference reference8;
                this.currentConditionalBlock = reference8 = currentConditionalBlock;
                this.stack = reference7 = stack;
                this.config = reference6 = config;
                this.overrides = reference5 = overrides;
                this.pushStack = reference4 = pushStack;
                this.assignName = reference3 = assignName;
                this.prefix = reference2 = prefix;
                this.mc = reference = mc;
            }

            public Object doCall(String name, Object args) {
                CallSite result;
                block22: {
                    block20: {
                        block21: {
                            result = null;
                            if (!(ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, args), 1) && IndyInterface.bootstrap("invoke", "getAt", 0, args, false) instanceof Closure)) break block20;
                            if (!ScriptBytecodeAdapter.isCase(name, IndyInterface.bootstrap("invoke", "keySet", 0, IndyInterface.bootstrap("getProperty", "conditionValues", 12, this)))) break block21;
                            try {
                                IndyInterface.bootstrap("invoke", "push", 0, IndyInterface.bootstrap("cast", "()", 0, this.currentConditionalBlock.get()), name);
                                IndyInterface.bootstrap("invoke", "push", 0, IndyInterface.bootstrap("getProperty", "conditionalBlocks", 12, this), ScriptBytecodeAdapter.createMap(new Object[0]));
                                IndyInterface.bootstrap("invoke", "call", 0, IndyInterface.bootstrap("invoke", "getAt", 0, args, false));
                            }
                            catch (Throwable throwable) {
                                IndyInterface.bootstrap("invoke", "pop", 0, IndyInterface.bootstrap("cast", "()", 0, this.currentConditionalBlock.get()));
                                Object entry = null;
                                CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, IndyInterface.bootstrap("invoke", "entrySet", 0, IndyInterface.bootstrap("invoke", "pop", 0, IndyInterface.bootstrap("getProperty", "conditionalBlocks", 12, this)))));
                                if (callSite != null) {
                                    while (callSite.hasNext()) {
                                        entry = callSite.next();
                                        CallSite c = IndyInterface.bootstrap("getProperty", "config", 0, IndyInterface.bootstrap("getProperty", "last", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get())));
                                        IndyInterface.bootstrap("invoke", "merge", 0, ScriptBytecodeAdapter.compareNotEqual(c, this.config.get()) ? c : IndyInterface.bootstrap("cast", "()", 0, this.overrides.get()), IndyInterface.bootstrap("getProperty", "value", 0, entry));
                                    }
                                }
                                throw throwable;
                            }
                            IndyInterface.bootstrap("invoke", "pop", 0, IndyInterface.bootstrap("cast", "()", 0, this.currentConditionalBlock.get()));
                            Object entry = null;
                            CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, IndyInterface.bootstrap("invoke", "entrySet", 0, IndyInterface.bootstrap("invoke", "pop", 0, IndyInterface.bootstrap("getProperty", "conditionalBlocks", 12, this)))));
                            if (callSite != null) {
                                while (callSite.hasNext()) {
                                    entry = callSite.next();
                                    CallSite c = IndyInterface.bootstrap("getProperty", "config", 0, IndyInterface.bootstrap("getProperty", "last", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get())));
                                    IndyInterface.bootstrap("invoke", "merge", 0, ScriptBytecodeAdapter.compareNotEqual(c, this.config.get()) ? c : IndyInterface.bootstrap("cast", "()", 0, this.overrides.get()), IndyInterface.bootstrap("getProperty", "value", 0, entry));
                                }
                            }
                            break block22;
                        }
                        if (ScriptBytecodeAdapter.compareGreaterThan(IndyInterface.bootstrap("invoke", "size", 0, IndyInterface.bootstrap("cast", "()", 0, this.currentConditionalBlock.get())), 0)) {
                            CallSite conditionalBlockKey = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "peek", 0, IndyInterface.bootstrap("cast", "()", 0, this.currentConditionalBlock.get())));
                            if (ScriptBytecodeAdapter.compareEqual(name, IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("getProperty", "conditionValues", 12, this), conditionalBlockKey))) {
                                CallSite co = IndyInterface.bootstrap("init", "<init>", 0, ConfigObject.class);
                                CallSite callSite = co;
                                IndyInterface.bootstrap("invoke", "putAt", 0, IndyInterface.bootstrap("invoke", "peek", 0, IndyInterface.bootstrap("getProperty", "conditionalBlocks", 12, this)), conditionalBlockKey, callSite);
                                IndyInterface.bootstrap("invoke", "call", 0, this.pushStack.get(), co);
                                try {
                                    IndyInterface.bootstrap("invoke", "pop", 0, IndyInterface.bootstrap("cast", "()", 0, this.currentConditionalBlock.get()));
                                    IndyInterface.bootstrap("invoke", "call", 0, IndyInterface.bootstrap("invoke", "getAt", 0, args, false));
                                }
                                finally {
                                    IndyInterface.bootstrap("invoke", "push", 0, IndyInterface.bootstrap("cast", "()", 0, this.currentConditionalBlock.get()), conditionalBlockKey);
                                }
                                IndyInterface.bootstrap("invoke", "removeLast", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get()));
                            }
                        } else {
                            CallSite co = null;
                            co = IndyInterface.bootstrap("invoke", "get", 0, IndyInterface.bootstrap("getProperty", "config", 0, IndyInterface.bootstrap("getProperty", "last", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get()))), name) instanceof ConfigObject ? IndyInterface.bootstrap("invoke", "get", 0, IndyInterface.bootstrap("getProperty", "config", 0, IndyInterface.bootstrap("getProperty", "last", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get()))), name) : IndyInterface.bootstrap("init", "<init>", 0, ConfigObject.class);
                            IndyInterface.bootstrap("invoke", "call", 0, this.assignName.get(), name, co);
                            IndyInterface.bootstrap("invoke", "call", 0, this.pushStack.get(), co);
                            IndyInterface.bootstrap("invoke", "call", 0, IndyInterface.bootstrap("invoke", "getAt", 0, args, false));
                            IndyInterface.bootstrap("invoke", "removeLast", 0, IndyInterface.bootstrap("cast", "()", 0, this.stack.get()));
                        }
                        break block22;
                    }
                    if (ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, args), 2) && IndyInterface.bootstrap("invoke", "getAt", 0, args, true) instanceof Closure) {
                        try {
                            CallSite callSite = IndyInterface.bootstrap("invoke", "plus", 0, name, ".");
                            this.prefix.set(callSite);
                            IndyInterface.bootstrap("invoke", "call", 0, this.assignName.get(), name, IndyInterface.bootstrap("invoke", "getAt", 0, args, false));
                            IndyInterface.bootstrap("invoke", "call", 0, IndyInterface.bootstrap("invoke", "getAt", 0, args, true));
                        }
                        finally {
                            String string = "";
                            this.prefix.set(string);
                        }
                    } else {
                        CallSite mm = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "getMetaMethod", 0, this.mc.get(), name, args));
                        if (IndyInterface.bootstrap("cast", "()", 0, mm) != false) {
                            result = IndyInterface.bootstrap("invoke", "invoke", 0, mm, IndyInterface.bootstrap("getProperty", "delegate", 12, this), args);
                        } else {
                            throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, name, IndyInterface.bootstrap("invoke", "getClass", 2, this), args));
                        }
                    }
                }
                return result;
            }

            @Generated
            public Object call(String name, Object args) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, name, args);
            }

            @Generated
            public Stack getCurrentConditionalBlock() {
                return IndyInterface.bootstrap("cast", "()", 0, this.currentConditionalBlock.get());
            }

            @Generated
            public LinkedList getStack() {
                return IndyInterface.bootstrap("cast", "()", 0, this.stack.get());
            }

            @Generated
            public Object getConfig() {
                return this.config.get();
            }

            @Generated
            public ConfigObject getOverrides() {
                return IndyInterface.bootstrap("cast", "()", 0, this.overrides.get());
            }

            @Generated
            public Object getPushStack() {
                return this.pushStack.get();
            }

            @Generated
            public Object getAssignName() {
                return this.assignName.get();
            }

            @Generated
            public Object getPrefix() {
                return this.prefix.get();
            }

            @Generated
            public Object getMc() {
                return this.mc.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _parse_closure5.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public /* synthetic */ MethodHandles.Lookup $getLookup() {
                return MethodHandles.lookup();
            }
        }
        _parse_closure5 _parse_closure52 = new _parse_closure5(this, this, currentConditionalBlock, stack, config, overrides, pushStack, assignName, prefix, mc);
        ScriptBytecodeAdapter.setProperty(_parse_closure52, null, mc.get(), "invokeMethod");
        CallSite callSite = mc.get();
        ScriptBytecodeAdapter.setGroovyObjectProperty(callSite, ConfigSlurper.class, script, "metaClass");
        public final class _parse_closure6
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference assignName;
            private /* synthetic */ Reference prefix;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _parse_closure6(Object _outerInstance, Object _thisObject, Reference assignName, Reference prefix) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.assignName = reference2 = assignName;
                this.prefix = reference = prefix;
            }

            public Object doCall(String name, Object value) {
                return IndyInterface.bootstrap("invoke", "call", 0, this.assignName.get(), IndyInterface.bootstrap("invoke", "plus", 0, this.prefix.get(), name), value);
            }

            @Generated
            public Object call(String name, Object value) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, name, value);
            }

            @Generated
            public Object getAssignName() {
                return this.assignName.get();
            }

            @Generated
            public Object getPrefix() {
                return this.prefix.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _parse_closure6.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public /* synthetic */ MethodHandles.Lookup $getLookup() {
                return MethodHandles.lookup();
            }
        }
        _parse_closure6 setProperty = new _parse_closure6(this, this, assignName, prefix);
        CallSite binding = IndyInterface.bootstrap("init", "<init>", 0, ConfigBinding.class, setProperty);
        if (IndyInterface.bootstrap("cast", "()", 0, this.bindingVars) != false) {
            IndyInterface.bootstrap("invoke", "putAll", 0, IndyInterface.bootstrap("getProperty", "variables", 0, binding), this.bindingVars);
        }
        CallSite callSite2 = binding;
        ScriptBytecodeAdapter.setGroovyObjectProperty(callSite2, ConfigSlurper.class, script, "binding");
        IndyInterface.bootstrap("invoke", "run", 0, script);
        IndyInterface.bootstrap("invoke", "merge", 0, config.get(), (ConfigObject)((Object)overrides.get()));
        return IndyInterface.bootstrap("cast", "()", 0, config.get());
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ConfigSlurper.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    @Override
    @Generated
    @Internal
    @Transient
    public MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    @Override
    @Generated
    @Internal
    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public static /* synthetic */ MethodHandles.Lookup $getLookup() {
        return MethodHandles.lookup();
    }

    static {
        String string = "environments";
        ENVIRONMENTS_METHOD = string;
    }

    @Generated
    public GroovyClassLoader getClassLoader() {
        return this.classLoader;
    }

    @Generated
    public void setClassLoader(GroovyClassLoader groovyClassLoader) {
        this.classLoader = groovyClassLoader;
    }
}

