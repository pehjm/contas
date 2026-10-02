/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.util;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.beans.Transient;
import java.lang.invoke.MethodHandles;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.StringGroovyMethods;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class StringUtil
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;

    @Generated
    public StringUtil() {
        MetaClass metaClass;
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    /*
     * WARNING - void declaration
     */
    public static String tr(String text, String source, String replacement) {
        void var2_2;
        Reference<String> source2 = new Reference<String>(source);
        Reference<void> replacement2 = new Reference<void>(var2_2);
        if (text == null || source2.get() == null || (String)replacement2.get() == null) {
            return text;
        }
        if (text.isEmpty() || source2.get().isEmpty() || ((String)replacement2.get()).isEmpty()) {
            return text;
        }
        source2.set(StringUtil.expandHyphen(source2.get()));
        replacement2.set((void)StringUtil.expandHyphen((String)replacement2.get()));
        replacement2.set((void)StringGroovyMethods.padRight((String)replacement2.get(), StringGroovyMethods.size((CharSequence)source2.get()), StringGroovyMethods.getAt((String)replacement2.get(), -1)));
        public final class _tr_closure1
        extends Closure
        implements GeneratedClosure {
            private /* synthetic */ Reference source;
            private /* synthetic */ Reference replacement;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;

            public _tr_closure1(Object _outerInstance, Object _thisObject, Reference source, Reference replacement) {
                super(_outerInstance, _thisObject);
                Reference reference;
                Reference reference2;
                this.source = reference2 = source;
                this.replacement = reference = replacement;
            }

            public String doCall(String character) {
                if (((String)((Object)IndyInterface.bootstrap("cast", "()", 0, this.source.get()))).contains(character)) {
                    return StringGroovyMethods.getAt((String)((Object)IndyInterface.bootstrap("cast", "()", 0, this.replacement.get())), ((String)((Object)IndyInterface.bootstrap("cast", "()", 0, this.source.get()))).lastIndexOf(character));
                }
                return character;
            }

            @Generated
            public String call(String character) {
                return this.doCall(character);
            }

            @Generated
            public String getSource() {
                return IndyInterface.bootstrap("cast", "()", 0, this.source.get());
            }

            @Generated
            public String getReplacement() {
                return IndyInterface.bootstrap("cast", "()", 0, this.replacement.get());
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _tr_closure1.class) {
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
        return DefaultGroovyMethods.join(DefaultGroovyMethods.collect(text, new _tr_closure1(StringUtil.class, StringUtil.class, source2, replacement2)), "");
    }

    private static String expandHyphen(String text) {
        if (text.contains("-")) {
            public final class _expandHyphen_closure2
            extends Closure
            implements GeneratedClosure {
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _expandHyphen_closure2(Object _outerInstance, Object _thisObject) {
                    super(_outerInstance, _thisObject);
                }

                public String doCall(Object _, Object start, Object until) {
                    return DefaultGroovyMethods.join(ScriptBytecodeAdapter.createRange(start, until, false, false), "");
                }

                @Generated
                public String call(Object _, Object start, Object until) {
                    return this.doCall(_, start, until);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _expandHyphen_closure2.class) {
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
            return StringGroovyMethods.replaceAll((CharSequence)text, (CharSequence)"(.)-(.)", (Closure)new _expandHyphen_closure2(StringUtil.class, StringUtil.class));
        }
        return text;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != StringUtil.class) {
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

