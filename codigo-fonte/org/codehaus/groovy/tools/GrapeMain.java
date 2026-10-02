/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.ivy.util.DefaultMessageLogger
 *  org.apache.ivy.util.Message
 */
package org.codehaus.groovy.tools;

import groovy.grape.Grape;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.GroovySystem;
import groovy.lang.MetaClass;
import groovy.lang.MissingMethodException;
import groovy.lang.MissingPropertyException;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovyjarjarpicocli.CommandLine;
import java.beans.Transient;
import java.io.File;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.util.DefaultMessageLogger;
import org.apache.ivy.util.Message;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

@CommandLine.Command(name="grape", description={"Allows for the inspection and management of the local grape cache."}, subcommands={Install.class, Uninstall.class, ListCommand.class, Resolve.class, CommandLine.HelpCommand.class})
public class GrapeMain
implements Runnable,
GroovyObject {
    @CommandLine.Option(paramLabel="<name=value>", names={"-D", "--define"}, description={"define a system property"})
    private final Map<String, String> properties;
    @CommandLine.Option(paramLabel="<url>", names={"-r", "--resolver"}, description={"define a grab resolver (for install)"})
    private final List<String> resolvers;
    @CommandLine.Option(names={"-q", "--quiet"}, description={"Log level 0 - only errors"})
    private boolean quiet;
    @CommandLine.Option(names={"-w", "--warn"}, description={"Log level 1 - errors and warnings"})
    private boolean warn;
    @CommandLine.Option(names={"-i", "--info"}, description={"Log level 2 - info"})
    private boolean info;
    @CommandLine.Option(names={"-V", "--verbose"}, description={"Log level 3 - verbose"})
    private boolean verbose;
    @CommandLine.Option(names={"-d", "--debug"}, description={"Log level 4 - debug"})
    private boolean debug;
    @CommandLine.Unmatched
    private List<String> unmatched;
    private CommandLine parser;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public GrapeMain() {
        MetaClass metaClass;
        CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, LinkedHashMap.class);
        this.properties = IndyInterface.bootstrap("cast", "()", 0, callSite);
        CallSite callSite2 = IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class);
        this.resolvers = IndyInterface.bootstrap("cast", "()", 0, callSite2);
        CallSite callSite3 = IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class);
        this.unmatched = IndyInterface.bootstrap("cast", "()", 0, callSite3);
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static void main(String ... args) {
        CallSite grape = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("init", "<init>", 0, GrapeMain.class));
        CallSite parser = IndyInterface.bootstrap("init", "<init>", 0, CommandLine.class, grape);
        IndyInterface.bootstrap("invoke", "addMixin", 0, parser, "helpOptions", IndyInterface.bootstrap("init", "<init>", 0, HelpOptionsMixin.class));
        public final class _main_closure1
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _main_closure1(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object k, Object v) {
                return ScriptBytecodeAdapter.compareNotEqual(k, "help");
            }

            @Generated
            public Object call(Object k, Object v) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, k, v);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _main_closure1.class) {
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
        public final class _main_closure2
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _main_closure2(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object k, Object v) {
                return IndyInterface.bootstrap("invoke", "addMixin", 0, v, "helpOptions", IndyInterface.bootstrap("init", "<init>", 0, HelpOptionsMixin.class));
            }

            @Generated
            public Object call(Object k, Object v) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, k, v);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _main_closure2.class) {
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
        IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("invoke", "findAll", 0, IndyInterface.bootstrap("getProperty", "subcommands", 0, parser), new _main_closure1(GrapeMain.class, GrapeMain.class)), new _main_closure2(GrapeMain.class, GrapeMain.class));
        CallSite callSite = parser;
        ScriptBytecodeAdapter.setGroovyObjectProperty(callSite, GrapeMain.class, (GroovyObject)((Object)grape), "parser");
        IndyInterface.bootstrap("invoke", "parseWithHandler", 0, parser, IndyInterface.bootstrap("init", "<init>", 0, CommandLine.RunLast.class), args);
    }

    @Override
    public void run() {
        if (IndyInterface.bootstrap("cast", "()", 0, this.unmatched) != false) {
            IndyInterface.bootstrap("invoke", "println", 0, IndyInterface.bootstrap("getProperty", "err", 0, System.class), new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, this.unmatched, false)}, new String[]{"grape: '", "' is not a grape command. See 'grape --help'"}));
        } else {
            IndyInterface.bootstrap("invoke", "usage", 0, this.parser, IndyInterface.bootstrap("getProperty", "out", 0, System.class));
        }
    }

    private void init() {
        public final class _init_closure3
        extends Closure
        implements GeneratedClosure {
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _init_closure3(Object _outerInstance, Object _thisObject) {
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object k, Object v) {
                return IndyInterface.bootstrap("invoke", "setProperty", 0, System.class, k, v);
            }

            @Generated
            public Object call(Object k, Object v) {
                return IndyInterface.bootstrap("invoke", "doCall", 2, this, k, v);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _init_closure3.class) {
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
        IndyInterface.bootstrap("invoke", "each", 0, this.properties, new _init_closure3(this, this));
    }

    private void setupLogging(int defaultLevel) {
        if (this.quiet) {
            CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, DefaultMessageLogger.class, IndyInterface.bootstrap("getProperty", "MSG_ERR", 0, Message.class));
            ScriptBytecodeAdapter.setProperty(callSite, null, Message.class, "defaultLogger");
        } else if (this.warn) {
            CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, DefaultMessageLogger.class, IndyInterface.bootstrap("getProperty", "MSG_WARN", 0, Message.class));
            ScriptBytecodeAdapter.setProperty(callSite, null, Message.class, "defaultLogger");
        } else if (this.info) {
            CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, DefaultMessageLogger.class, IndyInterface.bootstrap("getProperty", "MSG_INFO", 0, Message.class));
            ScriptBytecodeAdapter.setProperty(callSite, null, Message.class, "defaultLogger");
        } else if (this.verbose) {
            CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, DefaultMessageLogger.class, IndyInterface.bootstrap("getProperty", "MSG_VERBOSE", 0, Message.class));
            ScriptBytecodeAdapter.setProperty(callSite, null, Message.class, "defaultLogger");
        } else if (this.debug) {
            CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, DefaultMessageLogger.class, IndyInterface.bootstrap("getProperty", "MSG_DEBUG", 0, Message.class));
            ScriptBytecodeAdapter.setProperty(callSite, null, Message.class, "defaultLogger");
        } else {
            CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, DefaultMessageLogger.class, defaultLevel);
            ScriptBytecodeAdapter.setProperty(callSite, null, Message.class, "defaultLogger");
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        if (!(args instanceof Object[])) {
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(GrapeMain.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), new Object[]{args});
        }
        if (ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) {
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(GrapeMain.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
        }
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(GrapeMain.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectProperty(object, GrapeMain.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        return ScriptBytecodeAdapter.getGroovyObjectProperty(GrapeMain.class, this, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
    }

    @Generated
    private void setupLogging() {
        this.setupLogging(2);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != GrapeMain.class) {
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
    public List<String> getUnmatched() {
        return this.unmatched;
    }

    @Generated
    public void setUnmatched(List<String> list) {
        this.unmatched = list;
    }

    @CommandLine.Command(optionListHeading="%nOptions:%n", sortOptions=false, versionProvider=VersionProvider.class, parameterListHeading="%nParameters:%n", descriptionHeading="%n")
    private static class HelpOptionsMixin
    implements GroovyObject {
        @CommandLine.Option(usageHelp=true, names={"-h", "--help"}, description={"usage information"})
        private boolean isHelpRequested;
        @CommandLine.Option(versionHelp=true, names={"-v", "--version"}, description={"display the Groovy and JVM versions"})
        private boolean isVersionRequested;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        @Generated
        public HelpOptionsMixin() {
            MetaClass metaClass;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(HelpOptionsMixin.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(HelpOptionsMixin.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(HelpOptionsMixin.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
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
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(HelpOptionsMixin.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(HelpOptionsMixin.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(HelpOptionsMixin.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), HelpOptionsMixin.class, IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), HelpOptionsMixin.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(HelpOptionsMixin.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(HelpOptionsMixin.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), HelpOptionsMixin.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != HelpOptionsMixin.class) {
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
        public boolean getIsHelpRequested() {
            return this.isHelpRequested;
        }

        @Generated
        public boolean isIsHelpRequested() {
            return this.isHelpRequested;
        }

        @Generated
        public void setIsHelpRequested(boolean bl) {
            this.isHelpRequested = bl;
        }

        @Generated
        public boolean getIsVersionRequested() {
            return this.isVersionRequested;
        }

        @Generated
        public boolean isIsVersionRequested() {
            return this.isVersionRequested;
        }

        @Generated
        public void setIsVersionRequested(boolean bl) {
            this.isVersionRequested = bl;
        }
    }

    private static class VersionProvider
    implements CommandLine.IVersionProvider,
    GroovyObject {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        @Generated
        public VersionProvider() {
            MetaClass metaClass;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        @Override
        public String[] getVersion() {
            CallSite version = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("getProperty", "version", 0, GroovySystem.class));
            return IndyInterface.bootstrap("cast", "()", 0, ScriptBytecodeAdapter.createList(new Object[]{new GStringImpl(new Object[]{version, IndyInterface.bootstrap("invoke", "getProperty", 0, System.class, "java.version")}, new String[]{"Groovy Version: ", " JVM: ", ""})}));
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(VersionProvider.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(VersionProvider.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(VersionProvider.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
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
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(VersionProvider.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(VersionProvider.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(VersionProvider.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), VersionProvider.class, IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), VersionProvider.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(VersionProvider.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(VersionProvider.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), VersionProvider.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != VersionProvider.class) {
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
    }

    @CommandLine.Command(name="install", header={"Installs a particular grape"}, description={"Installs the specified groovy module or maven artifact. If a version is specified that specific version will be installed, otherwise the most recent version will be used (as if `*` was passed in)."})
    private static class Install
    implements Runnable,
    GroovyObject {
        @CommandLine.Parameters(arity="1", index="0", description={"Which module group the module comes from. Translates directly to a Maven groupId or an Ivy Organization. Any group matching /groovy[x][\\..*]^/ is reserved and may have special meaning to the groovy endorsed modules."})
        private String group;
        @CommandLine.Parameters(arity="1", index="1", description={"The name of the module to load. Translated directly to a Maven artifactId or an Ivy artifact."})
        private String module;
        @CommandLine.Parameters(arity="0..1", index="2", description={"The version of the module to use. Either a literal version `1.1-RC3` or an Ivy Range `[2.2.1,)` meaning 2.2.1 or any greater version)."})
        private String version;
        @CommandLine.Parameters(arity="0..1", index="3", description={"The optional classifier to use (for example, jdk15)."})
        private String classifier;
        @CommandLine.ParentCommand
        private GrapeMain parentCommand;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        @Generated
        public Install() {
            MetaClass metaClass;
            String string;
            this.version = string = "*";
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        @Override
        public void run() {
            IndyInterface.bootstrap("invoke", "init", 0, this.parentCommand);
            IndyInterface.bootstrap("getProperty", "instance", 0, Grape.class);
            IndyInterface.bootstrap("invoke", "setupLogging", 0, this.parentCommand);
            public final class _run_closure1
            extends Closure
            implements GeneratedClosure {
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _run_closure1(Object _outerInstance, Object _thisObject) {
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(String url) {
                    return IndyInterface.bootstrap("invoke", "addResolver", 0, Grape.class, ScriptBytecodeAdapter.createMap(new Object[]{"name", url, "root", url}));
                }

                @Generated
                public Object call(String url) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, url);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _run_closure1.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("getProperty", "resolvers", 4, this.parentCommand), new _run_closure1(this, this));
            try {
                IndyInterface.bootstrap("invoke", "grab", 0, Grape.class, ScriptBytecodeAdapter.createMap(new Object[]{"autoDownload", true, "group", this.group, "module", this.module, "version", this.version, "classifier", this.classifier, "noExceptions", true}));
            }
            catch (Exception ex) {
                IndyInterface.bootstrap("invoke", "println", 0, IndyInterface.bootstrap("getProperty", "err", 0, System.class), new GStringImpl(new Object[]{ex}, new String[]{"An error occured : ", ""}));
            }
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(Install.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(Install.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(Install.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
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
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(Install.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(Install.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(Install.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), Install.class, IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), Install.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(Install.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(Install.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), Install.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != Install.class) {
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
        public String getGroup() {
            return this.group;
        }

        @Generated
        public void setGroup(String string) {
            this.group = string;
        }

        @Generated
        public String getModule() {
            return this.module;
        }

        @Generated
        public void setModule(String string) {
            this.module = string;
        }

        @Generated
        public String getVersion() {
            return this.version;
        }

        @Generated
        public void setVersion(String string) {
            this.version = string;
        }

        @Generated
        public String getClassifier() {
            return this.classifier;
        }

        @Generated
        public void setClassifier(String string) {
            this.classifier = string;
        }

        @Generated
        public GrapeMain getParentCommand() {
            return this.parentCommand;
        }

        @Generated
        public void setParentCommand(GrapeMain grapeMain) {
            this.parentCommand = grapeMain;
        }
    }

    @CommandLine.Command(name="list", header={"Lists all installed grapes"}, description={"Lists locally installed modules (with their full maven name in the case of groovy modules) and versions."})
    private static class ListCommand
    implements Runnable,
    GroovyObject {
        @CommandLine.ParentCommand
        private GrapeMain parentCommand;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        @Generated
        public ListCommand() {
            MetaClass metaClass;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        @Override
        public void run() {
            IndyInterface.bootstrap("invoke", "init", 0, this.parentCommand);
            IndyInterface.bootstrap("invoke", "println", 2, this, "");
            Reference<Integer> moduleCount = new Reference<Integer>(0);
            Reference<Integer> versionCount = new Reference<Integer>(0);
            IndyInterface.bootstrap("getProperty", "instance", 0, Grape.class);
            IndyInterface.bootstrap("invoke", "setupLogging", 0, this.parentCommand);
            public final class _run_closure1
            extends Closure
            implements GeneratedClosure {
                private /* synthetic */ Reference moduleCount;
                private /* synthetic */ Reference versionCount;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _run_closure1(Object _outerInstance, Object _thisObject, Reference moduleCount, Reference versionCount) {
                    super(_outerInstance, _thisObject);
                    Reference reference;
                    Reference reference2;
                    this.moduleCount = reference2 = moduleCount;
                    this.versionCount = reference = versionCount;
                }

                public Object doCall(String groupName, Map group) {
                    Reference<String> groupName2 = new Reference<String>(groupName);
                    public final class _closure2
                    extends Closure
                    implements GeneratedClosure {
                        private /* synthetic */ Reference groupName;
                        private /* synthetic */ Reference moduleCount;
                        private /* synthetic */ Reference versionCount;
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;

                        public _closure2(Object _outerInstance, Object _thisObject, Reference groupName, Reference moduleCount, Reference versionCount) {
                            super(_outerInstance, _thisObject);
                            Reference reference;
                            Reference reference2;
                            Reference reference3;
                            this.groupName = reference3 = groupName;
                            this.moduleCount = reference2 = moduleCount;
                            this.versionCount = reference = versionCount;
                        }

                        public Object doCall(String moduleName, List<String> versions) {
                            IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{IndyInterface.bootstrap("cast", "()", 0, this.groupName.get()), moduleName, versions}, new String[]{"", " ", "  ", ""}));
                            CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, this.moduleCount.get());
                            this.moduleCount.set(IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "next", 0, callSite)));
                            CallSite callSite2 = IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("cast", "()", 0, this.versionCount.get()), IndyInterface.bootstrap("invoke", "size", 0, versions));
                            this.versionCount.set(IndyInterface.bootstrap("cast", "()", 0, callSite2));
                            return callSite2;
                        }

                        @Generated
                        public Object call(String moduleName, List<String> versions) {
                            return IndyInterface.bootstrap("invoke", "doCall", 2, this, moduleName, versions);
                        }

                        @Generated
                        public String getGroupName() {
                            return IndyInterface.bootstrap("cast", "()", 0, this.groupName.get());
                        }

                        @Generated
                        public Integer getModuleCount() {
                            return IndyInterface.bootstrap("cast", "()", 0, this.moduleCount.get());
                        }

                        @Generated
                        public Integer getVersionCount() {
                            return IndyInterface.bootstrap("cast", "()", 0, this.versionCount.get());
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _closure2.class) {
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
                    return IndyInterface.bootstrap("invoke", "each", 0, group, new _closure2(this, this.getThisObject(), groupName2, this.moduleCount, this.versionCount));
                }

                @Generated
                public Object call(String groupName, Map group) {
                    Reference<String> groupName2 = new Reference<String>(groupName);
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, groupName2.get(), group);
                }

                @Generated
                public Integer getModuleCount() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.moduleCount.get());
                }

                @Generated
                public Integer getVersionCount() {
                    return IndyInterface.bootstrap("cast", "()", 0, this.versionCount.get());
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _run_closure1.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, IndyInterface.bootstrap("invoke", "enumerateGrapes", 0, Grape.class), new _run_closure1(this, this, moduleCount, versionCount));
            IndyInterface.bootstrap("invoke", "println", 2, this, "");
            IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{moduleCount.get()}, new String[]{"", " Grape modules cached"}));
            IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{versionCount.get()}, new String[]{"", " Grape module versions cached"}));
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(ListCommand.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(ListCommand.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(ListCommand.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
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
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(ListCommand.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(ListCommand.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(ListCommand.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), ListCommand.class, IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), ListCommand.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(ListCommand.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(ListCommand.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), ListCommand.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ListCommand.class) {
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
        public GrapeMain getParentCommand() {
            return this.parentCommand;
        }

        @Generated
        public void setParentCommand(GrapeMain grapeMain) {
            this.parentCommand = grapeMain;
        }
    }

    @CommandLine.Command(name="resolve", header={"Enumerates the jars used by a grape"}, description={"Prints the file locations of the jars representing the artifacts for the specified module(s) and the respective transitive dependencies.", "", "Parameters:", "      <group>     Which module group the module comes from. Translates directly", "                    to a Maven groupId or an Ivy Organization. Any group", "                    matching /groovy[x][\\..*]^/ is reserved and may have", "                    special meaning to the groovy endorsed modules.", "      <module>    The name of the module to load. Translated directly to a", "                    Maven artifactId or an Ivy artifact.", "      <version>   The version of the module to use. Either a literal version", "                    `1.1-RC3` or an Ivy Range `[2.2.1,)` meaning 2.2.1 or any", "                    greater version)."}, customSynopsis={"grape resolve [-adhisv] (<groupId> <artifactId> <version>)+"})
    private static class Resolve
    implements Runnable,
    GroovyObject {
        @CommandLine.Option(names={"-a", "--ant"}, description={"Express dependencies in a format applicable for an ant script"})
        private boolean ant;
        @CommandLine.Option(names={"-d", "--dos"}, description={"Express dependencies in a format applicable for a windows batch file"})
        private boolean dos;
        @CommandLine.Option(names={"-s", "--shell"}, description={"Express dependencies in a format applicable for a unix shell script"})
        private boolean shell;
        @CommandLine.Option(names={"-i", "--ivy"}, description={"Express dependencies in an ivy-like format"})
        private boolean ivyFormatRequested;
        @CommandLine.Parameters(hidden=true)
        private List<String> args;
        @CommandLine.ParentCommand
        private GrapeMain parentCommand;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        @Generated
        public Resolve() {
            MetaClass metaClass;
            CallSite callSite = IndyInterface.bootstrap("init", "<init>", 0, ArrayList.class);
            this.args = IndyInterface.bootstrap("cast", "()", 0, callSite);
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        @Override
        public void run() {
            block23: {
                IndyInterface.bootstrap("invoke", "init", 0, this.parentCommand);
                IndyInterface.bootstrap("getProperty", "instance", 0, Grape.class);
                IndyInterface.bootstrap("invoke", "setupLogging", 0, this.parentCommand, IndyInterface.bootstrap("getProperty", "MSG_ERR", 0, Message.class));
                if (ScriptBytecodeAdapter.compareNotEqual(IndyInterface.bootstrap("invoke", "mod", 0, IndyInterface.bootstrap("invoke", "size", 0, this.args), 3), 0)) {
                    IndyInterface.bootstrap("invoke", "println", 2, this, "There needs to be a multiple of three arguments: (group module version)+");
                    return;
                }
                if (ScriptBytecodeAdapter.compareLessThan(IndyInterface.bootstrap("invoke", "size", 0, this.args), 3)) {
                    IndyInterface.bootstrap("invoke", "println", 2, this, "At least one Grape reference is required");
                    return;
                }
                String before = null;
                String between = null;
                String after = null;
                if (this.ant) {
                    before = "<pathelement location=\"";
                    between = "\">\n<pathelement location=\"";
                    after = "\">";
                } else if (this.dos) {
                    before = "set CLASSPATH=";
                    between = ";";
                    after = "";
                } else if (this.shell) {
                    before = "export CLASSPATH=";
                    between = ":";
                    after = "";
                } else if (this.ivyFormatRequested) {
                    before = "<dependency ";
                    between = "\">\n<dependency ";
                    after = "\">";
                } else {
                    before = "";
                    between = "\n";
                    after = "\n";
                }
                CallSite iter = IndyInterface.bootstrap("invoke", "iterator", 0, this.args);
                List params = ScriptBytecodeAdapter.createList(new Object[]{ScriptBytecodeAdapter.createMap(new Object[0])});
                List depsInfo = ScriptBytecodeAdapter.createList(new Object[0]);
                if (this.ivyFormatRequested) {
                    IndyInterface.bootstrap("invoke", "leftShift", 0, params, depsInfo);
                }
                while (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "hasNext", 0, iter)) != false) {
                    IndyInterface.bootstrap("invoke", "add", 0, params, ScriptBytecodeAdapter.createMap(new Object[]{"group", IndyInterface.bootstrap("invoke", "next", 0, iter), "module", IndyInterface.bootstrap("invoke", "next", 0, iter), "version", IndyInterface.bootstrap("invoke", "next", 0, iter)}));
                }
                try {
                    Reference<List> results = new Reference<List>(ScriptBytecodeAdapter.createList(new Object[0]));
                    CallSite uris = IndyInterface.bootstrap("invoke", "resolve", 16, Grape.class, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{params}, new int[]{0}));
                    if (!this.ivyFormatRequested) {
                        CallSite uri = null;
                        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, uris));
                        if (callSite != null) {
                            while (callSite.hasNext()) {
                                uri = IndyInterface.bootstrap("cast", "()", 0, callSite.next());
                                if (ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "scheme", 0, uri), "file")) {
                                    results.set((List)((Object)IndyInterface.bootstrap("invoke", "plus", 0, results.get(), IndyInterface.bootstrap("getProperty", "path", 0, IndyInterface.bootstrap("init", "<init>", 0, File.class, uri)))));
                                    continue;
                                }
                                results.set((List)((Object)IndyInterface.bootstrap("invoke", "plus", 0, results.get(), IndyInterface.bootstrap("invoke", "toASCIIString", 0, uri))));
                            }
                        }
                    } else {
                        public final class _run_closure1
                        extends Closure
                        implements GeneratedClosure {
                            private /* synthetic */ Reference results;
                            private static /* synthetic */ ClassInfo $staticClassInfo;
                            public static transient /* synthetic */ boolean __$stMC;

                            public _run_closure1(Object _outerInstance, Object _thisObject, Reference results) {
                                super(_outerInstance, _thisObject);
                                Reference reference;
                                this.results = reference = results;
                            }

                            public Object doCall(Object dep) {
                                CallSite callSite = IndyInterface.bootstrap("invoke", "plus", 0, this.results.get(), IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, IndyInterface.bootstrap("invoke", "plus", 0, "org=\"", IndyInterface.bootstrap("getProperty", "group", 0, dep)), "\" name=\""), IndyInterface.bootstrap("getProperty", "module", 0, dep)), "\" revision=\""), IndyInterface.bootstrap("getProperty", "revision", 0, dep)));
                                this.results.set(callSite);
                                return callSite;
                            }

                            @Generated
                            public Object getResults() {
                                return this.results.get();
                            }

                            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                if (this.getClass() != _run_closure1.class) {
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
                        IndyInterface.bootstrap("invoke", "each", 0, depsInfo, new _run_closure1(this, this, results));
                    }
                    if (IndyInterface.bootstrap("cast", "()", 0, results.get()) != false) {
                        IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{before, IndyInterface.bootstrap("invoke", "join", 0, results.get(), between), after}, new String[]{"", "", "", ""}));
                        break block23;
                    }
                    IndyInterface.bootstrap("invoke", "println", 2, this, "Nothing was resolved");
                }
                catch (Exception e) {
                    IndyInterface.bootstrap("invoke", "println", 0, IndyInterface.bootstrap("getProperty", "err", 0, System.class), new GStringImpl(new Object[]{IndyInterface.bootstrap("getProperty", "message", 0, e)}, new String[]{"Error in resolve:\n\t", ""}));
                    if (IndyInterface.bootstrap("cast", "()", 0, ScriptBytecodeAdapter.findRegex(IndyInterface.bootstrap("getProperty", "message", 0, e), "unresolved dependency")) != false) {
                        IndyInterface.bootstrap("invoke", "println", 2, this, "Perhaps the grape is not installed?");
                    }
                }
            }
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(Resolve.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(Resolve.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(Resolve.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
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
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(Resolve.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(Resolve.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(Resolve.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), Resolve.class, IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), Resolve.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(Resolve.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(Resolve.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), Resolve.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != Resolve.class) {
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
        public List<String> getArgs() {
            return this.args;
        }

        @Generated
        public void setArgs(List<String> list) {
            this.args = list;
        }

        @Generated
        public GrapeMain getParentCommand() {
            return this.parentCommand;
        }

        @Generated
        public void setParentCommand(GrapeMain grapeMain) {
            this.parentCommand = grapeMain;
        }
    }

    @CommandLine.Command(name="uninstall", description={"Uninstalls a particular grape (non-transitively removes the respective jar file from the grape cache)."})
    private static class Uninstall
    implements Runnable,
    GroovyObject {
        @CommandLine.Parameters(arity="1", index="0", description={"Which module group the module comes from. Translates directly to a Maven groupId or an Ivy Organization. Any group matching /groovy[x][\\..*]^/ is reserved and may have special meaning to the groovy endorsed modules."})
        private String group;
        @CommandLine.Parameters(arity="1", index="1", description={"The name of the module to load. Translated directly to a Maven artifactId or an Ivy artifact."})
        private String module;
        @CommandLine.Parameters(arity="1", index="2", description={"The version of the module to use. Either a literal version `1.1-RC3` or an Ivy Range `[2.2.1,)` meaning 2.2.1 or any greater version)."})
        private String version;
        @CommandLine.ParentCommand
        private GrapeMain parentCommand;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        @Generated
        public Uninstall() {
            MetaClass metaClass;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        @Override
        public void run() {
            IndyInterface.bootstrap("invoke", "init", 0, this.parentCommand);
            IndyInterface.bootstrap("getProperty", "instance", 0, Grape.class);
            IndyInterface.bootstrap("invoke", "setupLogging", 0, this.parentCommand);
            public final class _run_closure1
            extends Closure
            implements GeneratedClosure {
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _run_closure1(Object _outerInstance, Object _thisObject) {
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(String groupName, Map g) {
                    Reference<String> groupName2 = new Reference<String>(groupName);
                    public final class _closure4
                    extends Closure
                    implements GeneratedClosure {
                        private /* synthetic */ Reference groupName;
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;

                        public _closure4(Object _outerInstance, Object _thisObject, Reference groupName) {
                            super(_outerInstance, _thisObject);
                            Reference reference;
                            this.groupName = reference = groupName;
                        }

                        public Object doCall(String moduleName, List<String> versions) {
                            return ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "group", 12, this), IndyInterface.bootstrap("cast", "()", 0, this.groupName.get())) && ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "module", 12, this), moduleName) && ScriptBytecodeAdapter.isCase(IndyInterface.bootstrap("getProperty", "version", 12, this), versions);
                        }

                        @Generated
                        public Object call(String moduleName, List<String> versions) {
                            return IndyInterface.bootstrap("invoke", "doCall", 2, this, moduleName, versions);
                        }

                        @Generated
                        public String getGroupName() {
                            return IndyInterface.bootstrap("cast", "()", 0, this.groupName.get());
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _closure4.class) {
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
                    return IndyInterface.bootstrap("invoke", "any", 0, g, new _closure4(this, this.getThisObject(), groupName2));
                }

                @Generated
                public Object call(String groupName, Map g) {
                    Reference<String> groupName2 = new Reference<String>(groupName);
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, groupName2.get(), g);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _run_closure1.class) {
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
            if (IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "find", 0, IndyInterface.bootstrap("invoke", "enumerateGrapes", 0, Grape.class), new _run_closure1(this, this))) == false) {
                IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{this.group, this.module, this.version}, new String[]{"uninstall did not find grape matching: ", " ", " ", ""}));
                public final class _run_closure2
                extends Closure
                implements GeneratedClosure {
                    private static /* synthetic */ ClassInfo $staticClassInfo;
                    public static transient /* synthetic */ boolean __$stMC;

                    public _run_closure2(Object _outerInstance, Object _thisObject) {
                        super(_outerInstance, _thisObject);
                    }

                    public Object doCall(String groupName, Map g) {
                        Reference<String> groupName2 = new Reference<String>(groupName);
                        public final class _closure5
                        extends Closure
                        implements GeneratedClosure {
                            private /* synthetic */ Reference groupName;
                            private static /* synthetic */ ClassInfo $staticClassInfo;
                            public static transient /* synthetic */ boolean __$stMC;

                            public _closure5(Object _outerInstance, Object _thisObject, Reference groupName) {
                                super(_outerInstance, _thisObject);
                                Reference reference;
                                this.groupName = reference = groupName;
                            }

                            public Object doCall(String moduleName, List<String> versions) {
                                return DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "contains", 0, IndyInterface.bootstrap("cast", "()", 0, this.groupName.get()), IndyInterface.bootstrap("getProperty", "group", 12, this))) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "contains", 0, moduleName, IndyInterface.bootstrap("getProperty", "module", 12, this))) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "contains", 0, IndyInterface.bootstrap("getProperty", "group", 12, this), IndyInterface.bootstrap("cast", "()", 0, this.groupName.get()))) || DefaultTypeTransformation.booleanUnbox(IndyInterface.bootstrap("invoke", "contains", 0, IndyInterface.bootstrap("getProperty", "module", 12, this), moduleName));
                            }

                            @Generated
                            public Object call(String moduleName, List<String> versions) {
                                return IndyInterface.bootstrap("invoke", "doCall", 2, this, moduleName, versions);
                            }

                            @Generated
                            public String getGroupName() {
                                return IndyInterface.bootstrap("cast", "()", 0, this.groupName.get());
                            }

                            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                                if (this.getClass() != _closure5.class) {
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
                        return IndyInterface.bootstrap("invoke", "any", 0, g, new _closure5(this, this.getThisObject(), groupName2));
                    }

                    @Generated
                    public Object call(String groupName, Map g) {
                        Reference<String> groupName2 = new Reference<String>(groupName);
                        return IndyInterface.bootstrap("invoke", "doCall", 2, this, groupName2.get(), g);
                    }

                    protected /* synthetic */ MetaClass $getStaticMetaClass() {
                        if (this.getClass() != _run_closure2.class) {
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
                CallSite fuzzyMatches = IndyInterface.bootstrap("invoke", "findAll", 0, IndyInterface.bootstrap("invoke", "enumerateGrapes", 0, Grape.class), new _run_closure2(this, this));
                if (IndyInterface.bootstrap("cast", "()", 0, fuzzyMatches) != false) {
                    IndyInterface.bootstrap("invoke", "println", 2, this, "possible matches:");
                    public final class _run_closure3
                    extends Closure
                    implements GeneratedClosure {
                        private static /* synthetic */ ClassInfo $staticClassInfo;
                        public static transient /* synthetic */ boolean __$stMC;

                        public _run_closure3(Object _outerInstance, Object _thisObject) {
                            super(_outerInstance, _thisObject);
                        }

                        public Object doCall(String groupName, Map g) {
                            return IndyInterface.bootstrap("invoke", "println", 2, this, new GStringImpl(new Object[]{groupName, g}, new String[]{"    ", ": ", ""}));
                        }

                        @Generated
                        public Object call(String groupName, Map g) {
                            return IndyInterface.bootstrap("invoke", "doCall", 2, this, groupName, g);
                        }

                        protected /* synthetic */ MetaClass $getStaticMetaClass() {
                            if (this.getClass() != _run_closure3.class) {
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
                    IndyInterface.bootstrap("invoke", "each", 0, fuzzyMatches, new _run_closure3(this, this));
                }
                return;
            }
            IndyInterface.bootstrap("invoke", "uninstallArtifact", 0, IndyInterface.bootstrap("getProperty", "instance", 0, Grape.class), this.group, this.module, this.version);
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(Uninstall.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(Uninstall.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(Uninstall.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
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
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(Uninstall.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!ScriptBytecodeAdapter.compareEqual(IndyInterface.bootstrap("getProperty", "length", 0, IndyInterface.bootstrap("cast", "()", 0, args)), 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(Uninstall.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{IndyInterface.bootstrap("invoke", "getAt", 0, IndyInterface.bootstrap("cast", "()", 0, args), false)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(Uninstall.class, GrapeMain.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)IndyInterface.bootstrap("init", "<init>", 0, MissingMethodException.class, IndyInterface.bootstrap("getProperty", "method", 0, notFound), Uninstall.class, IndyInterface.bootstrap("getProperty", "arguments", 0, notFound));
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), Uninstall.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(Uninstall.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), IndyInterface.bootstrap("invoke", "getClass", 2, this), IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(Uninstall.class, GrapeMain.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)((Object)IndyInterface.bootstrap("init", "<init>", 0, MissingPropertyException.class, IndyInterface.bootstrap("getProperty", "property", 0, notFound), Uninstall.class, IndyInterface.bootstrap("getProperty", "cause", 0, notFound)));
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != Uninstall.class) {
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
        public String getGroup() {
            return this.group;
        }

        @Generated
        public void setGroup(String string) {
            this.group = string;
        }

        @Generated
        public String getModule() {
            return this.module;
        }

        @Generated
        public void setModule(String string) {
            this.module = string;
        }

        @Generated
        public String getVersion() {
            return this.version;
        }

        @Generated
        public void setVersion(String string) {
            this.version = string;
        }

        @Generated
        public GrapeMain getParentCommand() {
            return this.parentCommand;
        }

        @Generated
        public void setParentCommand(GrapeMain grapeMain) {
            this.parentCommand = grapeMain;
        }
    }
}

