/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.xml;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import io.restassured.internal.common.path.ObjectConverter;
import io.restassured.path.xml.element.Node;
import io.restassured.path.xml.element.PathElement;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.List;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class NodeBase
implements PathElement,
GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030715479;
    private static /* synthetic */ SoftReference $callSiteArray;

    public NodeBase() {
        MetaClass metaClass;
        CallSite[] callSiteArray = NodeBase.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public abstract Object get(String var1);

    public abstract <T> List<T> getList(String var1);

    protected static <T> T get(String name, Object iterator, boolean forceList) {
        CallSite[] callSiteArray = NodeBase.$getCallSiteArray();
        List found = ScriptBytecodeAdapter.createList(new Object[0]);
        while (DefaultTypeTransformation.booleanUnbox(callSiteArray[0].call(iterator))) {
            Object next = callSiteArray[1].call(iterator);
            if (!ScriptBytecodeAdapter.compareEqual(callSiteArray[2].call(next), name)) continue;
            callSiteArray[3].call((Object)found, next);
        }
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (forceList) {
                return (T)callSiteArray[4].call(Collections.class, found);
            }
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[5].call(found), 1)) {
                return (T)callSiteArray[6].call((Object)found, 0);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[7].call(found))) {
                return null;
            }
            return (T)callSiteArray[8].call(Collections.class, found);
        }
        if (forceList) {
            return (T)callSiteArray[9].call(Collections.class, found);
        }
        if (ScriptBytecodeAdapter.compareEqual(callSiteArray[10].call(found), 1)) {
            return (T)callSiteArray[11].call((Object)found, 0);
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[12].call(found))) {
            return null;
        }
        return (T)callSiteArray[13].call(Collections.class, found);
    }

    public abstract Object getPath(String var1);

    public Object getPath(String path, Class explicitType) {
        Object object;
        Object object2;
        CallSite[] callSiteArray = NodeBase.$getCallSiteArray();
        Object object3 = null;
        object3 = __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? (object2 = callSiteArray[14].callCurrent((GroovyObject)this, path)) : (object = this.getPath(path));
        return callSiteArray[15].call(ObjectConverter.class, object3, explicitType);
    }

    @Override
    public Node getNode(String name) {
        CallSite[] callSiteArray = NodeBase.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (Node)ScriptBytecodeAdapter.castToType(callSiteArray[16].callCurrent((GroovyObject)this, name), Node.class);
        }
        return (Node)ScriptBytecodeAdapter.castToType(this.get(name), Node.class);
    }

    @Override
    public List<Node> getNodes(String name) {
        CallSite[] callSiteArray = NodeBase.$getCallSiteArray();
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return (List)ScriptBytecodeAdapter.castToType(callSiteArray[17].callCurrent((GroovyObject)this, name), List.class);
        }
        return this.getList(name);
    }

    public abstract Object getBackingGroovyObject();

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = NodeBase.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(NodeBase.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = NodeBase.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, NodeBase.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = NodeBase.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(NodeBase.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != NodeBase.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    @Override
    public MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    @Override
    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    @Override
    public Object invokeMethod(String string, Object object) {
        return this.getMetaClass().invokeMethod((Object)this, string, object);
    }

    @Override
    public Object getProperty(String string) {
        return this.getMetaClass().getProperty(this, string);
    }

    @Override
    public void setProperty(String string, Object object) {
        this.getMetaClass().setProperty(this, string, object);
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = NodeBase.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        NodeBase.__$swapInit();
        __timeStamp__239_neverHappen1702030715479 = l2 = 0L;
        __timeStamp = l = 1702030715479L;
    }

    public /* synthetic */ void super$1$notify() {
        super.notify();
    }

    public /* synthetic */ int super$1$hashCode() {
        return super.hashCode();
    }

    public /* synthetic */ String super$1$toString() {
        return super.toString();
    }

    public /* synthetic */ Object super$1$clone() {
        return super.clone();
    }

    public /* synthetic */ void super$1$wait(long l, int n) {
        super.wait(l, n);
    }

    public /* synthetic */ void super$1$wait(long l) {
        super.wait(l);
    }

    public /* synthetic */ void super$1$wait() {
        super.wait();
    }

    public /* synthetic */ void super$1$notifyAll() {
        super.notifyAll();
    }

    public /* synthetic */ boolean super$1$equals(Object object) {
        return super.equals(object);
    }

    public /* synthetic */ void super$1$finalize() {
        super.finalize();
    }

    public /* synthetic */ Class super$1$getClass() {
        return super.getClass();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
        stringArray[0] = "hasNext";
        stringArray[1] = "next";
        stringArray[2] = "name";
        stringArray[3] = "leftShift";
        stringArray[4] = "unmodifiableList";
        stringArray[5] = "size";
        stringArray[6] = "get";
        stringArray[7] = "isEmpty";
        stringArray[8] = "unmodifiableList";
        stringArray[9] = "unmodifiableList";
        stringArray[10] = "size";
        stringArray[11] = "get";
        stringArray[12] = "isEmpty";
        stringArray[13] = "unmodifiableList";
        stringArray[14] = "getPath";
        stringArray[15] = "convertObjectTo";
        stringArray[16] = "get";
        stringArray[17] = "getList";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[18];
        NodeBase.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(NodeBase.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = NodeBase.$createCallSiteArray();
            $callSiteArray = new SoftReference<CallSiteArray>(callSiteArray);
        }
        return callSiteArray.array;
    }

    static /* synthetic */ Class class$(String string) {
        try {
            return Class.forName(string);
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw new NoClassDefFoundError(classNotFoundException.getMessage());
        }
    }
}

