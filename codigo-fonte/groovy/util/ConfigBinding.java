/*
 * Decompiled with CFR 0.152.
 */
package groovy.util;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import java.lang.invoke.MethodHandles;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;

public class ConfigBinding
extends Binding {
    private Object callable;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;

    public ConfigBinding(Closure c) {
        Closure closure = c;
        this.callable = closure;
    }

    @Override
    public void setVariable(String name, Object value) {
        ScriptBytecodeAdapter.invokeClosure(this.callable, new Object[]{name, value});
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ConfigBinding.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    public static /* synthetic */ MethodHandles.Lookup $getLookup() {
        return MethodHandles.lookup();
    }

    @Generated
    public Object getCallable() {
        return this.callable;
    }

    @Generated
    public void setCallable(Object object) {
        this.callable = object;
    }

    public /* synthetic */ void super$3$setVariable(String string, Object object) {
        super.setVariable(string, object);
    }
}

