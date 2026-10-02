/*
 * Decompiled with CFR 0.152.
 */
package groovy.cli.internal;

import groovy.cli.TypedOption;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovyjarjarpicocli.CommandLine;
import java.beans.Transient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.StringGroovyMethods;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class OptionAccessor
implements GroovyObject {
    private CommandLine.ParseResult parseResult;
    private Map<String, TypedOption> savedTypeOptions;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    public OptionAccessor(CommandLine.ParseResult parseResult) {
        CommandLine.ParseResult parseResult2;
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
        this.parseResult = parseResult2 = parseResult;
    }

    public boolean hasOption(TypedOption typedOption) {
        CallSite callSite = IndyInterface.bootstrap("getProperty", "longOpt", 0, typedOption);
        return DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "hasMatchedOption", 0, this.parseResult, IndyInterface.bootstrap("cast", "()", 0, callSite) != false ? callSite : (String)((Object)IndyInterface.bootstrap("invoke", "asType", 0, IndyInterface.bootstrap("getProperty", "opt", 0, typedOption), String.class))));
    }

    public <T> T defaultValue(String name) {
        CallSite type = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "type", 1, IndyInterface.bootstrap("invoke", "getAt", 0, this.savedTypeOptions, name)));
        CallSite value = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "defaultValue", 1, IndyInterface.bootstrap("invoke", "getAt", 0, this.savedTypeOptions, name))) != false ? IndyInterface.bootstrap("invoke", "defaultValue", 0, IndyInterface.bootstrap("invoke", "getAt", 0, this.savedTypeOptions, name)) : null);
        return (T)(IndyInterface.bootstrap("cast", "()", 0, value) != false ? IndyInterface.bootstrap("invoke", "getTypedValue", 2, this, type, name, value) : null);
    }

    public <T> T getOptionValue(TypedOption<T> typedOption) {
        return (T)IndyInterface.bootstrap("invoke", "getOptionValue", 2, this, typedOption, null);
    }

    public <T> T getOptionValue(TypedOption<T> typedOption, T defaultValue) {
        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "longOpt", 0, typedOption));
        CallSite optionName = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("cast", "()", 0, callSite) != false ? callSite : IndyInterface.bootstrap("getProperty", "opt", 0, typedOption));
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasMatchedOption", 0, this.parseResult, optionName)) != false) {
            return (T)IndyInterface.bootstrap("invoke", "matchedOptionValue", 0, this.parseResult, optionName, defaultValue);
        }
        CallSite option = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "findOption", 0, IndyInterface.bootstrap("invoke", "commandSpec", 0, this.parseResult), optionName));
        return (T)(IndyInterface.bootstrap("cast", "()", 0, option) != false ? IndyInterface.bootstrap("getProperty", "value", 0, option) : defaultValue);
    }

    public <T> T getAt(TypedOption<T> typedOption) {
        return (T)IndyInterface.bootstrap("invoke", "getAt", 2, this, typedOption, null);
    }

    public <T> T getAt(TypedOption<T> typedOption, T defaultValue) {
        return (T)IndyInterface.bootstrap("invoke", "getOptionValue", 2, this, typedOption, defaultValue);
    }

    private <T> T getTypedValue(Class<T> type, String optionName, String optionValue) {
        if (ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "min", 1, IndyInterface.bootstrap("getProperty", "arity", 1, IndyInterface.bootstrap("getProperty", "cliOption", 1, IndyInterface.bootstrap("invoke", "getAt", 0, this.savedTypeOptions, optionName)))), 0)) {
            return (T)IndyInterface.bootstrap("invoke", "hasMatchedOption", 0, this.parseResult, optionName);
        }
        CallSite convert = IndyInterface.bootstrap("getProperty", "convert", 1, IndyInterface.bootstrap("invoke", "getAt", 0, this.savedTypeOptions, optionName));
        return (T)IndyInterface.bootstrap("invoke", "getValue", 2, this, type, optionValue, convert);
    }

    private <T> T getValue(Class<T> type, String optionValue, Closure convert) {
        if (IndyInterface.bootstrap("cast", "()", 0, type) == false) {
            return (T)optionValue;
        }
        if (DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isAssignableFrom", 0, Closure.class, type)) && DefaultTypeTransformation.booleanUnbox(convert)) {
            return (T)IndyInterface.bootstrap("invoke", "call", 0, convert, optionValue);
        }
        if (ScriptBytecodeAdapter.compareEqual(type, Boolean.class) || ScriptBytecodeAdapter.compareEqual(type, IndyInterface.bootstrap("getProperty", "TYPE", 0, Boolean.class))) {
            return (T)IndyInterface.bootstrap("invoke", "cast", 0, type, IndyInterface.bootstrap("invoke", "parseBoolean", 0, Boolean.class, optionValue));
        }
        return (T)IndyInterface.bootstrap("invoke", "asType", 0, StringGroovyMethods.class, optionValue, ScriptBytecodeAdapter.createPojoWrapper(type, Class.class));
    }

    public Properties getOptionProperties(String name) {
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasMatchedOption", 0, this.parseResult, name)) == false) {
            return IndyInterface.bootstrap("cast", "()", 0, null);
        }
        CallSite keyValues = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "stringValues", 0, IndyInterface.bootstrap("invoke", "matchedOption", 0, this.parseResult, name)));
        Reference<CallSite> result = new Reference<CallSite>(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, Properties.class)));
        public final class _getOptionProperties_closure1
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference result;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _getOptionProperties_closure1(Object _outerInstance, Object _thisObject, Reference result) {
                super(_outerInstance, _thisObject);
                Reference reference;
                this.result = reference = result;
            }

            public Object doCall(Object k, Object v) {
                return IndyInterface.bootstrap("invoke", "setProperty", 0, IndyInterface.bootstrap("cast", "()", 0, this.result.get()), k, v);
            }

            @Generated
            public Object call(Object k, Object v) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, k, v);
            }

            @Generated
            public Properties getResult() {
                return IndyInterface.bootstrap("cast", "()", 0, this.result.get());
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _getOptionProperties_closure1.class) {
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
        IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("invoke", "toSpreadMap", 0, keyValues), new _getOptionProperties_closure1(this, this, result));
        return (Properties)((Object)result.get());
    }

    @Override
    public Object invokeMethod(String name, Object args) {
        if (ScriptBytecodeAdapter.compareEqual(name, "hasOption")) {
            name = "hasMatchedOption";
            args = IndyInterface.bootstrap("invoke", "toArray", 0, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, args, false)}));
        }
        if (ScriptBytecodeAdapter.compareEqual(name, "getOptionValue")) {
            name = "matchedOptionValue";
            args = IndyInterface.bootstrap("invoke", "toArray", 0, ScriptBytecodeAdapter.createList(new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, args, false), null}));
        }
        return IndyInterface.bootstrap("invoke", "invokeMethod", 0, IndyInterface.bootstrap("invoke", "getMetaClass", 0, InvokerHelper.class, this.parseResult), this.parseResult, name, args);
    }

    @Override
    public Object getProperty(String name) {
        if (ScriptBytecodeAdapter.compareEqual(name, "parseResult")) {
            return this.parseResult;
        }
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasMatchedOption", 0, this.parseResult, name)) != false) {
            CallSite result = IndyInterface.bootstrap("invoke", "matchedOptionValue", 0, this.parseResult, name, null);
            CallSite userSpecifiedType = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "type", 1, IndyInterface.bootstrap("invoke", "getAt", 0, this.savedTypeOptions, name)));
            if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "isArray", 1, userSpecifiedType)) != false) {
                return result;
            }
            CallSite derivedType = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "type", 0, IndyInterface.bootstrap("invoke", "matchedOption", 0, this.parseResult, name)));
            if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "isArray", 0, derivedType)) != false) {
                return IndyInterface.bootstrap("cast", "()", 0, result) != false ? IndyInterface.bootstrap("invoke", "getAt", 0, result, false) : null;
            }
            if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "isAssignableFrom", 0, Collection.class, derivedType)) != false) {
                return IndyInterface.bootstrap("invoke", "first", 1, (Collection)((Object)IndyInterface.bootstrap("invoke", "asType", 0, result, Collection.class)));
            }
            if (IndyInterface.bootstrap("cast", "()", 0, userSpecifiedType) == false && ScriptBytecodeAdapter.compareEqual(result, "") && ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "min", 0, IndyInterface.bootstrap("invoke", "arity", 0, IndyInterface.bootstrap("invoke", "matchedOption", 0, this.parseResult, name))), 0)) {
                return true;
            }
            return IndyInterface.bootstrap("invoke", "get", 0, IndyInterface.bootstrap("invoke", "typedValues", 0, IndyInterface.bootstrap("invoke", "matchedOption", 0, this.parseResult, name)), false);
        }
        if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "findOption", 0, IndyInterface.bootstrap("invoke", "commandSpec", 0, this.parseResult), name)) != false) {
            CallSite option = IndyInterface.bootstrap("invoke", "findOption", 0, IndyInterface.bootstrap("invoke", "commandSpec", 0, this.parseResult), name);
            CallSite result = IndyInterface.bootstrap("getProperty", "value", 0, option);
            CallSite longOpt = IndyInterface.bootstrap("invoke", "longestName", 0, option);
            longOpt = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "startsWith", 1, longOpt, "--")) != false ? IndyInterface.bootstrap("invoke", "substring", 0, longOpt, 2) : longOpt;
            CallSite userSpecifiedType = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "type", 1, IndyInterface.bootstrap("invoke", "getAt", 0, this.savedTypeOptions, longOpt)));
            if (DefaultTypeTransformation.booleanUnbox(userSpecifiedType) && ScriptBytecodeAdapter.compareNotEqual(Boolean.class, userSpecifiedType)) {
                return result;
            }
            return IndyInterface.bootstrap("cast", "()", 0, result) != false ? result : Boolean.valueOf(false);
        }
        if (ScriptBytecodeAdapter.compareGreaterThan(IndyInterface.bootstrap("invoke", "size", 0, name), 1) && DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "endsWith", 0, name, "s"))) {
            CallSite singularName = IndyInterface.bootstrap("invoke", "getAt", 0, name, ScriptBytecodeAdapter.createRange(0, -2, false, false));
            if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasMatchedOption", 0, this.parseResult, singularName)) != false) {
                CallSite type = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "type", 0, IndyInterface.bootstrap("invoke", "matchedOption", 0, this.parseResult, singularName)));
                if (DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isArray", 0, type)) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isAssignableFrom", 0, Collection.class, type)) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "isAssignableFrom", 0, Map.class, type))) {
                    return IndyInterface.bootstrap("invoke", "matchedOptionValue", 0, this.parseResult, singularName, null);
                }
                return IndyInterface.bootstrap("invoke", "stringValues", 0, IndyInterface.bootstrap("invoke", "matchedOption", 0, this.parseResult, singularName));
            }
        }
        return false;
    }

    public List<String> arguments() {
        return IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasMatchedPositional", 0, this.parseResult, false)) != false ? IndyInterface.bootstrap("invoke", "stringValues", 0, IndyInterface.bootstrap("invoke", "matchedPositional", 0, this.parseResult, false)) : ScriptBytecodeAdapter.createList(new Object[0]));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != OptionAccessor.class) {
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
    public CommandLine.ParseResult getParseResult() {
        return this.parseResult;
    }

    @Generated
    public void setParseResult(CommandLine.ParseResult parseResult) {
        this.parseResult = parseResult;
    }

    @Generated
    public Map<String, TypedOption> getSavedTypeOptions() {
        return this.savedTypeOptions;
    }

    @Generated
    public void setSavedTypeOptions(Map<String, TypedOption> map) {
        this.savedTypeOptions = map;
    }

    public /* synthetic */ Object super$1$getProperty(String string) {
        return GroovyObject.super.getProperty(string);
    }

    public /* synthetic */ Object super$1$invokeMethod(String string, Object object) {
        return GroovyObject.super.invokeMethod(string, object);
    }
}

