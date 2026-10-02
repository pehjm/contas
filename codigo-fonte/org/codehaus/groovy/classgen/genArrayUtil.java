/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.classgen;

import groovy.lang.Binding;
import groovy.lang.MetaClass;
import groovy.lang.Script;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class genArrayUtil
extends Script {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;

    public genArrayUtil() {
    }

    public genArrayUtil(Binding context) {
        super(context);
    }

    public static void main(String ... args) {
        IndyInterface.bootstrap("invoke", "runScript", 0, InvokerHelper.class, genArrayUtil.class, args);
    }

    @Override
    public Object run() {
        return IndyInterface.bootstrap("invoke", "print", 2, this, new GStringImpl(new Object[]{IndyInterface.bootstrap("invoke", "genMethods", 2, this)}, new String[]{"\n\npublic class ArrayUtil {\n   ", "\n}\n\n"}));
    }

    public Object genMethods() {
        String res = "";
        Object i = null;
        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, ScriptBytecodeAdapter.createRange(1, 250, false, false)));
        if (callSite != null) {
            while (callSite.hasNext()) {
                i = callSite.next();
                IndyInterface.bootstrap("invoke", "plus", 0, res, IndyInterface.bootstrap("invoke", "plus", 0, "\n\n", IndyInterface.bootstrap("invoke", "genMethod", 2, this, i)));
            }
        }
        return res;
    }

    public Object genMethod(int paramNum) {
        Object res = "public static Object [] createArray (";
        Object k = null;
        CallSite callSite = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, ScriptBytecodeAdapter.createRange(0, paramNum, false, true)));
        if (callSite != null) {
            while (callSite.hasNext()) {
                k = callSite.next();
                IndyInterface.bootstrap("invoke", "plus", 0, res, IndyInterface.bootstrap("invoke", "plus", 0, "Object arg", k));
                if (!ScriptBytecodeAdapter.compareNotEqual(k, IndyInterface.bootstrap("invoke", "minus", 0, paramNum, true))) continue;
                res = IndyInterface.bootstrap("invoke", "plus", 0, res, ", ");
            }
        }
        res = IndyInterface.bootstrap("invoke", "plus", 0, res, ") {\n");
        res = IndyInterface.bootstrap("invoke", "plus", 0, res, "return new Object [] {\n");
        Object k2 = null;
        CallSite callSite2 = IndyInterface.bootstrap("cast", "()", 0, IndyInterface.bootstrap("invoke", "iterator", 0, ScriptBytecodeAdapter.createRange(0, paramNum, false, true)));
        if (callSite2 != null) {
            while (callSite2.hasNext()) {
                k2 = callSite2.next();
                IndyInterface.bootstrap("invoke", "plus", 0, res, IndyInterface.bootstrap("invoke", "plus", 0, "arg", k2));
                if (!ScriptBytecodeAdapter.compareNotEqual(k2, IndyInterface.bootstrap("invoke", "minus", 0, paramNum, true))) continue;
                res = IndyInterface.bootstrap("invoke", "plus", 0, res, ", ");
            }
        }
        res = IndyInterface.bootstrap("invoke", "plus", 0, res, "};\n");
        res = IndyInterface.bootstrap("invoke", "plus", 0, res, "}");
        return res;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != genArrayUtil.class) {
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
}

