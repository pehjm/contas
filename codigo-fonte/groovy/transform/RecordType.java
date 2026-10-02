/*
 * Decompiled with CFR 0.152.
 */
package groovy.transform;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.MissingMethodException;
import groovy.lang.MissingPropertyException;
import groovy.transform.AnnotationCollector;
import groovy.transform.AnnotationCollectorMode;
import groovy.transform.CompileStatic;
import groovy.transform.DefaultsMode;
import groovy.transform.Generated;
import groovy.transform.Internal;
import groovy.transform.KnownImmutable;
import groovy.transform.PropertyOptions;
import groovy.transform.RecordBase;
import groovy.transform.RecordOptions;
import groovy.transform.TupleConstructor;
import groovy.transform.stc.POJO;
import java.beans.Transient;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

@AnnotationCollector(serializeClass=CollectorHelper.class, mode=AnnotationCollectorMode.PREFER_EXPLICIT_MERGED)
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface RecordType {

    public static final class CollectorHelper
    implements GroovyObject {
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;

        @Generated
        public CollectorHelper() {
            MetaClass metaClass;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        @Generated
        public static Object[][] value() {
            return new Object[][]{{RecordBase.class, ScriptBytecodeAdapter.createMap(new Object[0])}, {RecordOptions.class, ScriptBytecodeAdapter.createMap(new Object[0])}, {TupleConstructor.class, ScriptBytecodeAdapter.createMap(new Object[]{"namedVariant", true, "force", true, "defaultsMode", DefaultsMode.AUTO})}, {PropertyOptions.class, ScriptBytecodeAdapter.createMap(new Object[0])}, {KnownImmutable.class, ScriptBytecodeAdapter.createMap(new Object[0])}, {POJO.class, ScriptBytecodeAdapter.createMap(new Object[0])}, {CompileStatic.class, ScriptBytecodeAdapter.createMap(new Object[0])}};
        }

        /*
         * Unable to fully structure code
         */
        public /* synthetic */ Object methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(CollectorHelper.class, RecordType.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var3_3;
lbl8:
            // 1 sources

            if (!(((CallSite)IndyInterface.bootstrap("cast", "()", 0, args)).length == 1)) ** GOTO lbl14
            var4_4 = ScriptBytecodeAdapter.invokeMethodN(CollectorHelper.class, RecordType.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{BytecodeInterface8.objectArrayGet((Object[])IndyInterface.bootstrap("cast", "()", 0, args), 0)});
            return var4_4;
lbl14:
            // 1 sources

            var5_5 = ScriptBytecodeAdapter.invokeMethodN(CollectorHelper.class, RecordType.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var5_5;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)new MissingMethodException(notFound.getMethod(), this.getClass(), notFound.getArguments());
            }
        }

        /*
         * Unable to fully structure code
         */
        public static /* synthetic */ Object $static_methodMissing(String name, Object args) {
            if (!(args instanceof Object[] == false)) ** GOTO lbl8
            var2_2 = ScriptBytecodeAdapter.invokeMethodN(CollectorHelper.class, RecordType.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{args});
            return var2_2;
lbl8:
            // 1 sources

            if (!(((CallSite)IndyInterface.bootstrap("cast", "()", 0, args)).length == 1)) ** GOTO lbl14
            var3_3 = ScriptBytecodeAdapter.invokeMethodN(CollectorHelper.class, RecordType.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), new Object[]{BytecodeInterface8.objectArrayGet((Object[])IndyInterface.bootstrap("cast", "()", 0, args), 0)});
            return var3_3;
lbl14:
            // 1 sources

            var4_4 = ScriptBytecodeAdapter.invokeMethodN(CollectorHelper.class, RecordType.class, (String)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
            try {
                return var4_4;
            }
            catch (MissingMethodException notFound) {
                throw (Throwable)new MissingMethodException(notFound.getMethod(), CollectorHelper.class, notFound.getArguments());
            }
        }

        public /* synthetic */ void propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, RecordType.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)new MissingPropertyException(notFound.getProperty(), this.getClass(), notFound.getCause());
            }
        }

        public static /* synthetic */ void $static_propertyMissing(String name, Object value) {
            try {
                Object object = value;
                ScriptBytecodeAdapter.setProperty(object, null, RecordType.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)new MissingPropertyException(notFound.getProperty(), CollectorHelper.class, notFound.getCause());
            }
        }

        public /* synthetic */ Object propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(CollectorHelper.class, RecordType.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)new MissingPropertyException(notFound.getProperty(), this.getClass(), notFound.getCause());
            }
        }

        public static /* synthetic */ Object $static_propertyMissing(String name) {
            Object object = ScriptBytecodeAdapter.getProperty(CollectorHelper.class, RecordType.class, (String)((Object)IndyInterface.bootstrap("cast", "()", 0, new GStringImpl(new Object[]{name}, new String[]{"", ""}))));
            try {
                return object;
            }
            catch (MissingPropertyException notFound) {
                throw (Throwable)new MissingPropertyException(notFound.getProperty(), CollectorHelper.class, notFound.getCause());
            }
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != CollectorHelper.class) {
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
}

