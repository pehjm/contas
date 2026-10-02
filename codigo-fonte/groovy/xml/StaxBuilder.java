/*
 * Decompiled with CFR 0.152.
 */
package groovy.xml;

import groovy.lang.Closure;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.util.BuilderSupport;
import java.lang.invoke.MethodHandles;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.vmplugin.v8.IndyInterface;

public class StaxBuilder
extends BuilderSupport {
    private Object writer;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;

    public StaxBuilder(Object xmlStreamWriter) {
        Object object;
        this.writer = object = xmlStreamWriter;
        IndyInterface.bootstrap("invoke", "writeStartDocument", 0, this.writer);
    }

    @Override
    protected Object createNode(Object name) {
        return IndyInterface.bootstrap("invoke", "createNode", 2, this, name, null, null);
    }

    @Override
    protected Object createNode(Object name, Object value) {
        return IndyInterface.bootstrap("invoke", "createNode", 2, this, name, null, value);
    }

    @Override
    protected Object createNode(Object name, Map attributes) {
        return IndyInterface.bootstrap("invoke", "createNode", 2, this, name, attributes, null);
    }

    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        IndyInterface.bootstrap("invoke", "writeStartElement", 0, this.writer, IndyInterface.bootstrap("invoke", "toString", 0, name));
        if (IndyInterface.bootstrap("cast", "()", 0, attributes) != false) {
            public final class _createNode_closure1
            extends Closure
            implements GeneratedClosure {
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;

                public _createNode_closure1(Object _outerInstance, Object _thisObject) {
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object k, Object v) {
                    return IndyInterface.bootstrap("invoke", "writeAttribute", 0, IndyInterface.bootstrap("getProperty", "writer", 12, this), IndyInterface.bootstrap("invoke", "toString", 0, k), IndyInterface.bootstrap("invoke", "toString", 0, v));
                }

                @Generated
                public Object call(Object k, Object v) {
                    return IndyInterface.bootstrap("invoke", "doCall", 2, this, k, v);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _createNode_closure1.class) {
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
            IndyInterface.bootstrap("invoke", "each", 0, attributes, new _createNode_closure1(this, this));
        }
        if (IndyInterface.bootstrap("cast", "()", 0, value) != false) {
            IndyInterface.bootstrap("invoke", "writeCharacters", 0, this.writer, IndyInterface.bootstrap("invoke", "toString", 0, value));
        }
        return name;
    }

    @Override
    protected void nodeCompleted(Object parent, Object node) {
        IndyInterface.bootstrap("invoke", "writeEndElement", 0, this.writer);
        if (IndyInterface.bootstrap("cast", "()", 0, parent) == false) {
            IndyInterface.bootstrap("invoke", "writeEndDocument", 0, this.writer);
            IndyInterface.bootstrap("invoke", "flush", 0, this.writer);
        }
    }

    @Override
    protected void setParent(Object parent, Object child) {
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != StaxBuilder.class) {
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
    public Object getWriter() {
        return this.writer;
    }

    @Generated
    public void setWriter(Object object) {
        this.writer = object;
    }

    public /* synthetic */ void super$3$nodeCompleted(Object object, Object object2) {
        super.nodeCompleted(object, object2);
    }
}

