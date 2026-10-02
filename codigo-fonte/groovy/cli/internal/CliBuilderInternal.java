/*
 * Decompiled with CFR 0.152.
 */
package groovy.cli.internal;

import groovy.cli.CliBuilderException;
import groovy.cli.TypedOption;
import groovy.cli.internal.OptionAccessor;
import groovy.lang.Closure;
import groovy.lang.GString;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.MissingMethodException;
import groovy.lang.MissingPropertyException;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovyjarjarpicocli.CommandLine;
import java.beans.Transient;
import java.io.PrintWriter;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class CliBuilderInternal
implements GroovyObject {
    private String usage;
    private String name;
    private Boolean posix;
    private boolean expandArgumentFiles;
    private boolean stopAtNonOption;
    private boolean acceptLongOptionsWithSingleHyphen;
    private PrintWriter writer;
    private PrintWriter errorWriter;
    private String header;
    private String footer;
    private int width;
    private final CommandLine.Model.ParserSpec parser;
    private final CommandLine.Model.UsageMessageSpec usageMessage;
    private Map<String, TypedOption> savedTypeOptions;
    private CommandLine.Model.CommandSpec commandSpec;
    private static final int COMMONS_CLI_UNLIMITED_VALUES = -2;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public CliBuilderInternal() {
        MetaClass metaClass;
        boolean bl;
        boolean bl2;
        boolean bl3;
        String string;
        String string2;
        this.usage = string2 = "groovy";
        this.name = string = "groovy";
        boolean bl4 = true;
        this.posix = bl4;
        this.expandArgumentFiles = bl3 = true;
        this.stopAtNonOption = bl2 = true;
        this.acceptLongOptionsWithSingleHyphen = bl = false;
        CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, PrintWriter.class, IndyInterface.bootstrap("getProperty", "out", 0, System.class));
        this.writer = IndyInterface.bootstrap("cast", "()", 0, callSite);
        CallSite callSite2 = IndyInterface.bootstrap("init", "<init>", 0, PrintWriter.class, IndyInterface.bootstrap("getProperty", "err", 0, System.class));
        this.errorWriter = IndyInterface.bootstrap("cast", "()", 0, callSite2);
        Object var9_9 = null;
        this.header = IndyInterface.bootstrap("cast", "()", 0, var9_9);
        Object var10_10 = null;
        this.footer = IndyInterface.bootstrap("cast", "()", 0, var10_10);
        CallSite callSite3 = IndyInterface.bootstrap("getProperty", "DEFAULT_USAGE_WIDTH", 0, CommandLine.Model.UsageMessageSpec.class);
        this.width = DefaultTypeTransformation.intUnbox(callSite3);
        CallSite callSite4 = IndyInterface.bootstrap("invoke", "toggleBooleanFlags", 0, IndyInterface.bootstrap("invoke", "overwrittenOptionsAllowed", 0, IndyInterface.bootstrap("invoke", "limitSplit", 0, IndyInterface.bootstrap("invoke", "aritySatisfiedByAttachedOptionParam", 0, IndyInterface.bootstrap("invoke", "unmatchedOptionsArePositionalParams", 0, IndyInterface.bootstrap("invoke", "stopAtPositional", 0, IndyInterface.bootstrap("init", "<init>", 0, CommandLine.Model.ParserSpec.class), true), true), true), true), true), false);
        this.parser = IndyInterface.bootstrap("cast", "()", 0, callSite4);
        CallSite callSite5 = IndyInterface.bootstrap("init", "<init>", 0, CommandLine.Model.UsageMessageSpec.class);
        this.usageMessage = IndyInterface.bootstrap("cast", "()", 0, callSite5);
        CallSite callSite6 = IndyInterface.bootstrap("init", "<init>", 0, HashMap.class);
        this.savedTypeOptions = IndyInterface.bootstrap("cast", "()", 0, callSite6);
        CallSite callSite7 = IndyInterface.bootstrap("invoke", "create", 0, CommandLine.Model.CommandSpec.class);
        this.commandSpec = IndyInterface.bootstrap("cast", "()", 0, callSite7);
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public void setUsage(String usage) {
        String string;
        this.usage = string = usage;
        IndyInterface.bootstrap("invoke", "customSynopsis", 0, this.usageMessage, usage);
    }

    public void setFooter(String footer) {
        String string;
        this.footer = string = footer;
        IndyInterface.bootstrap("invoke", "footer", 0, this.usageMessage, footer);
    }

    public void setHeader(String header) {
        String string;
        this.header = string = header;
        IndyInterface.bootstrap("invoke", "description", 0, this.usageMessage, header);
    }

    public void setWidth(int width) {
        int n;
        this.width = n = width;
        IndyInterface.bootstrap("invoke", "width", 0, this.usageMessage, width);
    }

    public void setExpandArgumentFiles(boolean expand) {
        boolean bl;
        this.expandArgumentFiles = bl = expand;
        IndyInterface.bootstrap("invoke", "expandAtFiles", 0, this.parser, expand);
    }

    public void setPosix(Boolean posix) {
        Boolean bl;
        this.posix = bl = posix;
        Boolean bl2 = posix;
        IndyInterface.bootstrap("invoke", "posixClusteredShortOptionsAllowed", 0, this.parser, IndyInterface.bootstrap("cast", "()", 0, bl2) != false ? bl2 : Boolean.valueOf(false));
    }

    public void setStopAtNonOption(boolean stopAtNonOption) {
        boolean bl;
        this.stopAtNonOption = bl = stopAtNonOption;
        IndyInterface.bootstrap("invoke", "stopAtPositional", 0, this.parser, stopAtNonOption);
        IndyInterface.bootstrap("invoke", "unmatchedOptionsArePositionalParams", 0, this.parser, stopAtNonOption);
    }

    public void setWriter(PrintWriter writer) {
        PrintWriter printWriter;
        PrintWriter printWriter2;
        this.writer = printWriter2 = writer;
        this.errorWriter = printWriter = writer;
    }

    public <T> TypedOption<T> option(Map args, Class<T> type, String description) {
        CallSite callSite = IndyInterface.bootstrap("getProperty", "opt", 0, args);
        Object name = IndyInterface.bootstrap("cast", "()", 0, callSite) != false ? callSite : "_";
        Class<T> clazz = type;
        ScriptBytecodeAdapter.setProperty(clazz, null, args, "type");
        IndyInterface.bootstrap("invoke", "remove", 0, args, "opt");
        return IndyInterface.bootstrap("cast", "()", 0, ScriptBytecodeAdapter.invokeMethodOnCurrentN(CliBuilderInternal.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), new Object[]{args, description}));
    }

    @Override
    public Object invokeMethod(String name, Object args) {
        if (args instanceof Object[]) {
            if (ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("invoke", "size", 0, args), 1) && (IndyInterface.bootstrap("invoke", "getAt", 0, args, false) instanceof String || IndyInterface.bootstrap("invoke", "getAt", 0, args, false) instanceof GString)) {
                CallSite option = IndyInterface.bootstrap("invoke", "option", 2, this, name, ScriptBytecodeAdapter.createMap(new Object[0]), IndyInterface.bootstrap("invoke", "getAt", 0, args, false));
                IndyInterface.bootstrap("invoke", "addOption", 0, this.commandSpec, option);
                return IndyInterface.bootstrap("invoke", "create", 2, this, option, null, null, null);
            }
            if (ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("invoke", "size", 0, args), 1) && IndyInterface.bootstrap("invoke", "getAt", 0, args, false) instanceof CommandLine.Model.OptionSpec && ScriptBytecodeAdapter.compareEqual(name, "leftShift")) {
                CommandLine.Model.OptionSpec option = (CommandLine.Model.OptionSpec)((Object)IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("invoke", "getAt", 0, args, false), CommandLine.Model.OptionSpec.class));
                IndyInterface.bootstrap("invoke", "addOption", 0, this.commandSpec, option);
                return IndyInterface.bootstrap("invoke", "create", 2, this, option, null, null, null);
            }
            if (ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("invoke", "size", 0, args), 2) && IndyInterface.bootstrap("invoke", "getAt", 0, args, false) instanceof Map) {
                Map m = (Map)((Object)IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("invoke", "getAt", 0, args, false), Map.class));
                if (DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("getProperty", "type", 0, m)) && !(IndyInterface.bootstrap("getProperty", "type", 0, m) instanceof Class)) {
                    throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, CliBuilderException.class, "'type' must be a Class"));
                }
                CallSite option = IndyInterface.bootstrap("invoke", "option", 2, this, name, m, IndyInterface.bootstrap("invoke", "getAt", 0, args, true));
                IndyInterface.bootstrap("invoke", "addOption", 0, this.commandSpec, option);
                return IndyInterface.bootstrap("invoke", "create", 2, this, option, IndyInterface.bootstrap("getProperty", "type", 0, m), IndyInterface.bootstrap("invoke", "defaultValue", 0, option), IndyInterface.bootstrap("invoke", "converters", 0, option));
            }
        }
        return IndyInterface.bootstrap("invoke", "invokeMethod", 0, IndyInterface.bootstrap("invoke", "getMetaClass", 0, InvokerHelper.class, this), this, name, args);
    }

    private TypedOption create(CommandLine.Model.OptionSpec o, Class theType, Object defaultValue, Object convert) {
        public final class _create_closure1
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _create_closure1(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object a, Object b) {
                return IndyInterface.bootstrap("invoke", "minus", 0, IndyInterface.bootstrap("invoke", "length", 0, a), IndyInterface.bootstrap("invoke", "length", 0, b));
            }

            @Generated
            public Object call(Object a, Object b) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, a, b);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _create_closure1.class) {
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
        CallSite opt = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "first", 0, IndyInterface.bootstrap("invoke", "sort", 0, IndyInterface.bootstrap("invoke", "names", 0, o), new _create_closure1(this, this))));
        opt = IndyInterface.bootstrap("cast", "()", 0, ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("invoke", "length", 1, opt), 2) ? IndyInterface.bootstrap("invoke", "substring", 0, opt, true) : null);
        public final class _create_closure2
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _create_closure2(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object a, Object b) {
                return IndyInterface.bootstrap("invoke", "minus", 0, IndyInterface.bootstrap("invoke", "length", 0, b), IndyInterface.bootstrap("invoke", "length", 0, a));
            }

            @Generated
            public Object call(Object a, Object b) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, a, b);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _create_closure2.class) {
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
        CallSite longOpt = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "first", 0, IndyInterface.bootstrap("invoke", "sort", 0, IndyInterface.bootstrap("invoke", "names", 0, o), new _create_closure2(this, this))));
        longOpt = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "startsWith", 1, longOpt, "--")) != false ? IndyInterface.bootstrap("invoke", "substring", 0, longOpt, 2) : null);
        CallSite result = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, TypedOption.class));
        if (ScriptBytecodeAdapter.compareNotEqual(opt, null)) {
            IndyInterface.bootstrap("invoke", "put", 0, result, "opt", opt);
        }
        IndyInterface.bootstrap("invoke", "put", 0, result, "longOpt", longOpt);
        IndyInterface.bootstrap("invoke", "put", 0, result, "cliOption", o);
        if (IndyInterface.bootstrap("cast", "()", 0, defaultValue) != false) {
            IndyInterface.bootstrap("invoke", "put", 0, result, "defaultValue", defaultValue);
        }
        if (IndyInterface.bootstrap("cast", "()", 0, convert) != false) {
            if (IndyInterface.bootstrap("cast", "()", 0, theType) != false) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, CliBuilderException.class, "You can't specify 'type' when using 'convert'"));
            }
            IndyInterface.bootstrap("invoke", "put", 0, result, "convert", convert);
            IndyInterface.bootstrap("invoke", "put", 0, result, "type", convert instanceof Class ? convert : IndyInterface.bootstrap("invoke", "getClass", 0, convert));
        } else {
            IndyInterface.bootstrap("invoke", "put", 0, result, "type", theType);
        }
        CallSite callSite = result;
        CallSite callSite2 = longOpt;
        IndyInterface.bootstrap("invoke", "putAt", 0, this.savedTypeOptions, IndyInterface.bootstrap("cast", "()", 0, callSite2) != false ? callSite2 : opt, callSite);
        return IndyInterface.bootstrap("cast", "()", 0, result);
    }

    public OptionAccessor parse(Object args) {
        CallSite commandLine = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "createCommandLine", 2, this));
        CallSite accessor = IndyInterface.bootstrap("init", "<init>", 0, OptionAccessor.class, IndyInterface.bootstrap("invoke", "parseArgs", 0, commandLine, ScriptBytecodeAdapter.createPojoWrapper((String[])IndyInterface.bootstrap("invoke", "asType", 0, args, String[].class), String[].class)));
        Map<String, TypedOption> map = this.savedTypeOptions;
        ScriptBytecodeAdapter.setProperty(map, null, accessor, "savedTypeOptions");
        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, accessor);
        try {
            return callSite;
        }
        catch (CommandLine.ParameterException pe) {
            IndyInterface.bootstrap("invoke", "println", 0, this.errorWriter, IndyInterface.bootstrap("invoke", "plus", 0, "error: ", IndyInterface.bootstrap("getProperty", "message", 0, pe)));
            IndyInterface.bootstrap("invoke", "printUsage", 2, this, IndyInterface.bootstrap("getProperty", "commandLine", 0, pe), this.errorWriter);
            CallSite callSite2 = IndyInterface.bootstrap("cast", "()", 0, null);
            return callSite2;
        }
    }

    private CommandLine createCommandLine() {
        IndyInterface.bootstrap("invoke", "parser", 0, this.commandSpec, this.parser);
        IndyInterface.bootstrap("invoke", "usageMessage", 0, IndyInterface.bootstrap("invoke", "name", 0, this.commandSpec, this.name), this.usageMessage);
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "empty", 0, IndyInterface.bootstrap("invoke", "positionalParameters", 0, this.commandSpec))) != false) {
            IndyInterface.bootstrap("invoke", "addPositional", 0, this.commandSpec, IndyInterface.bootstrap("invoke", "build", 0, IndyInterface.bootstrap("invoke", "hidden", 0, IndyInterface.bootstrap("invoke", "paramLabel", 0, IndyInterface.bootstrap("invoke", "arity", 0, IndyInterface.bootstrap("invoke", "type", 0, IndyInterface.bootstrap("invoke", "builder", 0, CommandLine.Model.PositionalParamSpec.class), String[].class), "*"), "P"), true)));
        }
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, CommandLine.class, this.commandSpec));
    }

    public void usage() {
        CallSite callSite = IndyInterface.bootstrap("invoke", "commandLine", 0, this.commandSpec);
        IndyInterface.bootstrap("invoke", "printUsage", 2, this, IndyInterface.bootstrap("cast", "()", 0, callSite) != false ? callSite : IndyInterface.bootstrap("invoke", "createCommandLine", 2, this), this.writer);
    }

    private void printUsage(CommandLine commandLine, PrintWriter pw) {
        IndyInterface.bootstrap("invoke", "usage", 0, commandLine, pw);
        IndyInterface.bootstrap("invoke", "flush", 0, pw);
    }

    public CommandLine.Model.OptionSpec option(Object shortname, Map details, Object description) {
        Reference<Object> builder = new Reference<Object>(null);
        CommandLine.Model.OptionSpec.Builder cfr_ignored_0 = builder.get();
        if (ScriptBytecodeAdapter.compareEqual(shortname, "_")) {
            builder.set(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "description", 0, IndyInterface.bootstrap("invoke", "builder", 0, CommandLine.Model.OptionSpec.class, new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "longOpt", 0, details)}, new String[]{"--", ""})), description)));
            if (this.acceptLongOptionsWithSingleHyphen) {
                IndyInterface.bootstrap("invoke", "names", 0, builder.get(), new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "longOpt", 0, details)}, new String[]{"-", ""}), new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "longOpt", 0, details)}, new String[]{"--", ""}));
            }
            IndyInterface.bootstrap("invoke", "remove", 0, details, "longOpt");
        } else {
            builder.set(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "description", 0, IndyInterface.bootstrap("invoke", "builder", 0, CommandLine.Model.OptionSpec.class, new GStringImpl(new Object[]{shortname}, new String[]{"-", ""})), description)));
        }
        public final class _option_closure3
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference builder;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _option_closure3(Object _outerInstance, Object _thisObject, Reference builder) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.builder = reference = builder;
            }

            public Object doCall(Object key, Object value) {
                if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasProperty", 0, IndyInterface.bootstrap("cast", "()", 0, this.builder.get()), key)) != false) {
                    Object object = value;
                    IndyInterface.bootstrap("invoke", "putAt", 0, IndyInterface.bootstrap("cast", "()", 0, this.builder.get()), key, object);
                    return object;
                }
                if (ScriptBytecodeAdapter.compareNotEqual(key, "opt")) {
                    return IndyInterface.bootstrap("invoke", "invokeMethod", 0, IndyInterface.bootstrap("cast", "()", 0, this.builder.get()), key, value);
                }
                return null;
            }

            @Generated
            public Object call(Object key, Object value) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, key, value);
            }

            @Generated
            public CommandLine.Model.OptionSpec.Builder getBuilder() {
                return IndyInterface.bootstrap("cast", "()", 0, this.builder.get());
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _option_closure3.class) {
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
        IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("invoke", "commons2picocli", 2, this, shortname, details), new _option_closure3(this, this, builder));
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "type", 0, builder.get())) == false && IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "arity", 0, builder.get())) == false && ScriptBytecodeAdapter.compareGreaterThan(IndyInterface.bootstrap("getProperty", "length", 1, IndyInterface.bootstrap("invoke", "converters", 0, builder.get())), 0)) {
            IndyInterface.bootstrap("invoke", "type", 0, IndyInterface.bootstrap("invoke", "arity", 0, builder.get(), "1"), IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "convert", 0, details)) != false ? Object.class : String[].class);
        }
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "build", 0, builder.get()));
    }

    private Map commons2picocli(Object shortname, Map m) {
        Reference<Object> shortname2 = new Reference<Object>(shortname);
        if (DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("getProperty", "args", 0, m)) && DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("getProperty", "optionalArg", 0, m))) {
            GStringImpl gStringImpl = new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "args", 0, m)}, new String[]{"0..", ""});
            ScriptBytecodeAdapter.setProperty(gStringImpl, null, m, "arity");
            IndyInterface.bootstrap("invoke", "remove", 0, m, "args");
            IndyInterface.bootstrap("invoke", "remove", 0, m, "optionalArg");
        }
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "defaultValue", 0, m)) == false) {
            IndyInterface.bootstrap("invoke", "remove", 0, m, "defaultValue");
        }
        public final class _commons2picocli_closure4
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference shortname;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _commons2picocli_closure4(Object _outerInstance, Object _thisObject, Reference shortname) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.shortname = reference = shortname;
            }

            public Object doCall(Object k, Object v) {
                if (ScriptBytecodeAdapter.compareEqual(k, "args") && ScriptBytecodeAdapter.compareEqual(v, "+")) {
                    return ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"arity", "1..*"})});
                }
                if (ScriptBytecodeAdapter.compareEqual(k, "args") && ScriptBytecodeAdapter.compareEqual(v, 0)) {
                    return ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"arity", "0"})});
                }
                if (ScriptBytecodeAdapter.compareEqual(k, "args")) {
                    return ScriptBytecodeAdapter.compareEqual(v, IndyInterface.bootstrap("getProperty", "COMMONS_CLI_UNLIMITED_VALUES", 12, this)) ? ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"arity", "*"})}) : ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"arity", new GStringImpl(new Object[]{v}, new String[]{"", ""})})});
                }
                if (ScriptBytecodeAdapter.compareEqual(k, "optionalArg")) {
                    return IndyInterface.bootstrap("cast", "()", 0, v) != false ? ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"arity", "0..1"})}) : ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"arity", "1"})});
                }
                if (ScriptBytecodeAdapter.compareEqual(k, "argName")) {
                    return ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"paramLabel", new GStringImpl(new Object[]{v}, new String[]{"<", ">"})})});
                }
                if (ScriptBytecodeAdapter.compareEqual(k, "longOpt")) {
                    return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "acceptLongOptionsWithSingleHyphen", 12, this)) != false ? ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"names", (String[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[]{new GStringImpl(new Object[]{this.shortname.get()}, new String[]{"-", ""}), new GStringImpl(new Object[]{v}, new String[]{"-", ""}), new GStringImpl(new Object[]{v}, new String[]{"--", ""})}), String[].class)})}) : ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"names", (String[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[]{new GStringImpl(new Object[]{this.shortname.get()}, new String[]{"-", ""}), new GStringImpl(new Object[]{v}, new String[]{"--", ""})}), String[].class)})});
                }
                if (ScriptBytecodeAdapter.compareEqual(k, "valueSeparator")) {
                    return ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"splitRegex", new GStringImpl(new Object[]{v}, new String[]{"", ""})})});
                }
                if (ScriptBytecodeAdapter.compareEqual(k, "convert")) {
                    return ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{"converters", (CommandLine.ITypeConverter[])IndyInterface.bootstrap("invoke", "asType", 0, ScriptBytecodeAdapter.createList(new Object[]{v}), CommandLine.ITypeConverter[].class)})});
                }
                return ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[]{k, v})});
            }

            @Generated
            public Object call(Object k, Object v) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, k, v);
            }

            @Generated
            public Object getShortname() {
                return this.shortname.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _commons2picocli_closure4.class) {
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
        Map result = (Map)((Object)IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("invoke", "sum", 0, IndyInterface.bootstrap("invoke", "collectMany", 0, m, new _commons2picocli_closure4(this, this, shortname2))), Map.class));
        return IndyInterface.bootstrap("cast", "()", 0, result);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        if (!(args instanceof Object[])) {
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(CliBuilderInternal.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), new Object[]{args});
        }
        if (ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) {
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(CliBuilderInternal.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
        }
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(CliBuilderInternal.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectProperty(object, CliBuilderInternal.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        return ScriptBytecodeAdapter.getGroovyObjectProperty(CliBuilderInternal.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != CliBuilderInternal.class) {
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

    @Generated
    public String getUsage() {
        return this.usage;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public void setName(String string) {
        this.name = string;
    }

    @Generated
    public Boolean getPosix() {
        return this.posix;
    }

    @Generated
    public boolean getExpandArgumentFiles() {
        return this.expandArgumentFiles;
    }

    @Generated
    public boolean isExpandArgumentFiles() {
        return this.expandArgumentFiles;
    }

    @Generated
    public boolean getStopAtNonOption() {
        return this.stopAtNonOption;
    }

    @Generated
    public boolean isStopAtNonOption() {
        return this.stopAtNonOption;
    }

    @Generated
    public boolean getAcceptLongOptionsWithSingleHyphen() {
        return this.acceptLongOptionsWithSingleHyphen;
    }

    @Generated
    public boolean isAcceptLongOptionsWithSingleHyphen() {
        return this.acceptLongOptionsWithSingleHyphen;
    }

    @Generated
    public void setAcceptLongOptionsWithSingleHyphen(boolean bl) {
        this.acceptLongOptionsWithSingleHyphen = bl;
    }

    @Generated
    public PrintWriter getWriter() {
        return this.writer;
    }

    @Generated
    public PrintWriter getErrorWriter() {
        return this.errorWriter;
    }

    @Generated
    public void setErrorWriter(PrintWriter printWriter) {
        this.errorWriter = printWriter;
    }

    @Generated
    public String getHeader() {
        return this.header;
    }

    @Generated
    public String getFooter() {
        return this.footer;
    }

    @Generated
    public int getWidth() {
        return this.width;
    }

    @Generated
    public final CommandLine.Model.ParserSpec getParser() {
        return this.parser;
    }

    @Generated
    public final CommandLine.Model.UsageMessageSpec getUsageMessage() {
        return this.usageMessage;
    }

    @Generated
    public Map<String, TypedOption> getSavedTypeOptions() {
        return this.savedTypeOptions;
    }

    @Generated
    public void setSavedTypeOptions(Map<String, TypedOption> map) {
        this.savedTypeOptions = map;
    }

    public /* synthetic */ Object super$1$invokeMethod(String string, Object object) {
        return GroovyObject.super.invokeMethod(string, object);
    }

    private static class ArgSpecAttributes
    implements GroovyObject {
        private Class type;
        private Class[] auxiliaryTypes;
        private String label;
        private CommandLine.Model.IGetter getter;
        private CommandLine.Model.ISetter setter;
        private Object initialValue;
        private boolean hasInitialValue;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        @Generated
        public ArgSpecAttributes() {
            MetaClass metaClass;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(ArgSpecAttributes.class, CliBuilderInternal.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(ArgSpecAttributes.class, CliBuilderInternal.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(ArgSpecAttributes.class, CliBuilderInternal.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var5_5;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        /*
         * Unable to fully structure code
         */
        public static /* synthetic */ Object $static_methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(ArgSpecAttributes.class, CliBuilderInternal.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(ArgSpecAttributes.class, CliBuilderInternal.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(ArgSpecAttributes.class, CliBuilderInternal.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), ArgSpecAttributes.class, IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, CliBuilderInternal.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, CliBuilderInternal.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), ArgSpecAttributes.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(ArgSpecAttributes.class, CliBuilderInternal.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(ArgSpecAttributes.class, CliBuilderInternal.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), ArgSpecAttributes.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ArgSpecAttributes.class) {
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

        @Generated
        public Class getType() {
            return this.type;
        }

        @Generated
        public void setType(Class clazz) {
            this.type = clazz;
        }

        @Generated
        public Class[] getAuxiliaryTypes() {
            return this.auxiliaryTypes;
        }

        @Generated
        public void setAuxiliaryTypes(Class ... classArray) {
            this.auxiliaryTypes = classArray;
        }

        @Generated
        public String getLabel() {
            return this.label;
        }

        @Generated
        public void setLabel(String string) {
            this.label = string;
        }

        @Generated
        public CommandLine.Model.IGetter getGetter() {
            return this.getter;
        }

        @Generated
        public void setGetter(CommandLine.Model.IGetter iGetter) {
            this.getter = iGetter;
        }

        @Generated
        public CommandLine.Model.ISetter getSetter() {
            return this.setter;
        }

        @Generated
        public void setSetter(CommandLine.Model.ISetter iSetter) {
            this.setter = iSetter;
        }

        @Generated
        public Object getInitialValue() {
            return this.initialValue;
        }

        @Generated
        public void setInitialValue(Object object) {
            this.initialValue = object;
        }

        @Generated
        public boolean getHasInitialValue() {
            return this.hasInitialValue;
        }

        @Generated
        public boolean isHasInitialValue() {
            return this.hasInitialValue;
        }

        @Generated
        public void setHasInitialValue(boolean bl) {
            this.hasInitialValue = bl;
        }
    }
}

