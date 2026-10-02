/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.xml;

import groovy.lang.Binding;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.MetaClass;
import groovy.lang.MissingPropertyException;
import groovy.lang.Reference;
import groovy.xml.slurpersupport.Attributes;
import groovy.xml.slurpersupport.FilteredNodeChildren;
import groovy.xml.slurpersupport.GPathResult;
import groovy.xml.slurpersupport.Node;
import groovy.xml.slurpersupport.NodeChild;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.internal.common.assertion.AssertionSupport;
import io.restassured.path.xml.element.PathElement;
import java.io.CharArrayWriter;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class XMLAssertion
implements Assertion,
GroovyObject {
    private static final String DOT;
    private static final String EXPLICIT_LIST_CONVERSION = "list()";
    private String key;
    private Map<String, Object> params;
    private Object isInvocationFragment;
    private Object fragments;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030715465;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$io$restassured$internal$path$xml$NodeChildrenImpl;
    private static /* synthetic */ Class $class$io$restassured$internal$path$xml$NodeImpl;

    public XMLAssertion() {
        MetaClass metaClass;
        Object object;
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        this.isInvocationFragment = object = ScriptBytecodeAdapter.bitwiseNegate(".*\\(\\d*\\)|.*(\\{|\\}).*");
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public Object getResult(Object object, boolean shouldConvertToJavaObject, boolean rootEvaluation) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        Object objectToUse = object instanceof Node ? callSiteArray[0].callConstructor(NodeChild.class, object, null, null) : object;
        if (rootEvaluation) {
            String string = DefaultTypeTransformation.booleanUnbox(callSiteArray[1].callSafe((Object)this.key, DOT)) ? callSiteArray[2].call((Object)this.key, 1) : this.key;
            this.key = (String)ScriptBytecodeAdapter.castToType(string, String.class);
        }
        Object object2 = callSiteArray[3].callStatic(AssertionSupport.class, ArrayUtil.createArray(this.key, callSiteArray[4].callStatic(AssertionSupport.class), callSiteArray[5].callStatic(AssertionSupport.class), callSiteArray[6].callStatic(AssertionSupport.class), callSiteArray[7].callStatic(AssertionSupport.class), callSiteArray[8].callStatic(AssertionSupport.class)));
        this.key = (String)ScriptBytecodeAdapter.castToType(object2, String.class);
        Object indexOfDot = callSiteArray[9].call((Object)this.key, ".");
        Object evaluationString = null;
        Boolean isRootOnly = null;
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            boolean bl = rootEvaluation ? ScriptBytecodeAdapter.compareLessThan(indexOfDot, 0) : false;
            isRootOnly = bl;
        } else {
            boolean bl = rootEvaluation ? ScriptBytecodeAdapter.compareLessThan(indexOfDot, 0) : false;
            isRootOnly = bl;
        }
        if (!DefaultTypeTransformation.booleanUnbox(isRootOnly)) {
            Object object3;
            this.fragments = object3 = callSiteArray[10].call((Object)this.key, "\\.");
            Object firstFragment = callSiteArray[11].call(this.fragments, 0);
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[12].callCurrent((GroovyObject)this, firstFragment)) || !DefaultTypeTransformation.booleanUnbox(callSiteArray[13].callCurrent((GroovyObject)this, firstFragment))) {
                Object object4;
                Object object5 = callSiteArray[14].call((Object)this.key, DOT);
                evaluationString = object4 = DefaultTypeTransformation.booleanUnbox(object5) ? object5 : callSiteArray[15].call((Object)DOT, this.key);
            } else if (rootEvaluation) {
                Object object6;
                evaluationString = object6 = callSiteArray[16].call((Object)this.key, indexOfDot);
            } else {
                String string = DefaultTypeTransformation.booleanUnbox(callSiteArray[17].call((Object)this.key, ".")) ? this.key : callSiteArray[18].call((Object)".", this.key);
                evaluationString = string;
            }
        } else {
            String string = "";
            evaluationString = string;
        }
        Object result = null;
        String rootObjectVariableName = "restAssuredXmlRootObject";
        try {
            Object object7;
            result = object7 = callSiteArray[19].callCurrent(this, rootObjectVariableName, objectToUse, new GStringImpl(new Object[]{rootObjectVariableName, evaluationString}, new String[]{"", "", ""}));
        }
        catch (MissingPropertyException e) {
            String error = (String)ScriptBytecodeAdapter.castToType(callSiteArray[20].call(String.class, "The parameter \"%s\" was used but not defined. Define parameters using the XmlPath.params(...) function", callSiteArray[21].callGetProperty(e)), String.class);
            throw (Throwable)callSiteArray[22].callConstructor(IllegalArgumentException.class, error, e);
        }
        catch (Exception e) {
            Object errorMessage = callSiteArray[23].call(e);
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[24].call(errorMessage, "No signature of method:"))) {
                GStringImpl gStringImpl = new GStringImpl(new Object[]{this.key}, new String[]{"Path ", " is invalid."});
                errorMessage = gStringImpl;
            } else {
                Object object8;
                boolean hasRootObjectVariableName = DefaultTypeTransformation.booleanUnbox(callSiteArray[25].call(StringUtils.class, errorMessage, rootObjectVariableName));
                String replacement = rootEvaluation ? callSiteArray[26].call(callSiteArray[27].call(callSiteArray[28].call(this.fragments, 0)), ".") : "";
                errorMessage = object8 = callSiteArray[29].call(callSiteArray[30].call(callSiteArray[31].call(callSiteArray[32].call(callSiteArray[33].call(e), "startup failed:", "Invalid path:"), "Script1.groovy: 1: ", ""), callSiteArray[34].call((Object)rootObjectVariableName, "."), replacement), "Object.", replacement);
                Object originalIndicatorIndex = callSiteArray[35].call(errorMessage, "^");
                if (ScriptBytecodeAdapter.compareGreaterThan(originalIndicatorIndex, 0)) {
                    int lhs = DefaultTypeTransformation.intUnbox(rootEvaluation ? callSiteArray[36].call(replacement) : Integer.valueOf(0));
                    int indicatorDelta = DefaultTypeTransformation.intUnbox(callSiteArray[37].call((Object)lhs, hasRootObjectVariableName ? callSiteArray[38].call(callSiteArray[39].call(rootObjectVariableName), 1) : callSiteArray[40].call("Object.")));
                    if (ScriptBytecodeAdapter.compareGreaterThan(indicatorDelta, 0)) {
                        Object object9;
                        errorMessage = object9 = callSiteArray[41].call(StringUtils.class, errorMessage, "^", callSiteArray[42].call(callSiteArray[43].callStatic(AssertionSupport.class, indicatorDelta), "^"));
                    } else if (ScriptBytecodeAdapter.compareLessThan(indicatorDelta, 0)) {
                        Object object10;
                        errorMessage = object10 = callSiteArray[44].call(StringUtils.class, errorMessage, callSiteArray[45].call(callSiteArray[46].callStatic(AssertionSupport.class, callSiteArray[47].call(Math.class, indicatorDelta)), "^"), "^");
                    }
                }
            }
            throw (Throwable)callSiteArray[48].callConstructor(IllegalArgumentException.class, errorMessage);
        }
        Object convertedObject = shouldConvertToJavaObject ? callSiteArray[49].callCurrent((GroovyObject)this, result) : result;
        return callSiteArray[50].callCurrent((GroovyObject)this, convertedObject);
    }

    @Override
    public Object getResult(Object object, Object config) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        return callSiteArray[51].callCurrent(this, object, true, true);
    }

    public Object getChildResultAsJavaObject(Object object) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        return callSiteArray[52].callCurrent(this, object, true, false);
    }

    private Object isDoubleStarFragment(String fragment) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        Object trimmed = callSiteArray[53].call(fragment);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            return ScriptBytecodeAdapter.compareEqual(trimmed, "**") || ScriptBytecodeAdapter.compareEqual(trimmed, "'**'");
        }
        return ScriptBytecodeAdapter.compareEqual(trimmed, "**") || ScriptBytecodeAdapter.compareEqual(trimmed, "'**'");
    }

    private Object preventTreatingRootObjectAsAList(Object javaObject) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (javaObject instanceof List && ScriptBytecodeAdapter.compareEqual(callSiteArray[54].call(javaObject), 1) && ScriptBytecodeAdapter.compareNotEqual(callSiteArray[55].call(this.fragments, -1), EXPLICIT_LIST_CONVERSION)) {
                Object object;
                javaObject = object = callSiteArray[56].call(javaObject, 0);
            }
        } else if (javaObject instanceof List && ScriptBytecodeAdapter.compareEqual(callSiteArray[57].call(javaObject), 1) && ScriptBytecodeAdapter.compareNotEqual(callSiteArray[58].call(this.fragments, -1), EXPLICIT_LIST_CONVERSION)) {
            Object object;
            javaObject = object = callSiteArray[59].call(javaObject, 0);
        }
        return javaObject;
    }

    public boolean isPathFragment(String fragment) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        return !DefaultTypeTransformation.booleanUnbox(callSiteArray[60].call(callSiteArray[61].call(this.isInvocationFragment, fragment)));
    }

    private Object convertToJavaObject(Object result) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        Object returnValue = null;
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[62].call(Attributes.class, callSiteArray[63].call(result)))) {
            Object object;
            returnValue = object = callSiteArray[64].callCurrent(this, result, true, false);
        } else if (result instanceof Node) {
            Object object;
            returnValue = object = callSiteArray[65].callCurrent((GroovyObject)this, result);
        } else if (result instanceof FilteredNodeChildren) {
            Object object;
            returnValue = object = callSiteArray[66].callCurrent(this, result, false, true);
        } else if (result instanceof NodeChild) {
            Object object;
            Object object2;
            Object object3 = callSiteArray[67].callCurrent(this, result, false, false);
            returnValue = object3 instanceof PathElement ? (object2 = callSiteArray[68].call(object3, 0)) : (object = object3);
        } else {
            Object object;
            Object object4;
            Object object5;
            returnValue = result instanceof GPathResult ? (object5 = callSiteArray[69].callCurrent(this, result, false, false)) : (result instanceof List ? (object4 = callSiteArray[70].callCurrent((GroovyObject)this, result)) : (object = result));
        }
        return returnValue;
    }

    private Object handleList(List result) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[71].call(result), 1) && ScriptBytecodeAdapter.compareNotEqual(callSiteArray[72].call(this.fragments, -1), EXPLICIT_LIST_CONVERSION)) {
                return callSiteArray[73].callCurrent((GroovyObject)this, callSiteArray[74].call((Object)result, 0));
            }
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[75].call(result))) {
                callSiteArray[76].call(result, i, callSiteArray[77].callCurrent((GroovyObject)this, callSiteArray[78].call((Object)result, i)));
                int n = i;
                i = DefaultTypeTransformation.intUnbox(callSiteArray[79].call(n));
            }
        } else {
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[80].call(result), 1) && ScriptBytecodeAdapter.compareNotEqual(callSiteArray[81].call(this.fragments, -1), EXPLICIT_LIST_CONVERSION)) {
                return callSiteArray[82].callCurrent((GroovyObject)this, callSiteArray[83].call((Object)result, 0));
            }
            int i = 0;
            while (ScriptBytecodeAdapter.compareLessThan(i, callSiteArray[84].call(result))) {
                callSiteArray[85].call(result, i, callSiteArray[86].callCurrent((GroovyObject)this, callSiteArray[87].call((Object)result, i)));
                int n = i;
                int cfr_ignored_0 = n + 1;
            }
        }
        return result;
    }

    private Object nodeToJavaObject(Object node) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        Object nodeImpl = callSiteArray[88].callConstructor((Object)XMLAssertion.$get$$class$io$restassured$internal$path$xml$NodeImpl(), ScriptBytecodeAdapter.createMap(new Object[]{"name", callSiteArray[89].call(node), "groovyNode", node}));
        callSiteArray[90].callCurrent(this, nodeImpl, node);
        Object child = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(callSiteArray[91].call(callSiteArray[92].call(node)), Iterator.class);
        while (iterator.hasNext()) {
            child = iterator.next();
            if (child instanceof Node) {
                Object object = callSiteArray[93].callCurrent((GroovyObject)this, (Object)child);
                callSiteArray[94].call(callSiteArray[95].callGetProperty(nodeImpl), object);
                continue;
            }
            Object var7_7 = child;
            ScriptBytecodeAdapter.setProperty(var7_7, null, nodeImpl, "value");
        }
        return nodeImpl;
    }

    private Object addAttributes(Object nodeImpl, Object node) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        Object attributes = callSiteArray[96].call(node);
        Object object = callSiteArray[97].callCurrent((GroovyObject)this, attributes);
        ScriptBytecodeAdapter.setProperty(object, null, nodeImpl, "attributes");
        return object;
    }

    private Object toJavaObject(Object nodes, Object isAttributes, Object forceList) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[98].call(nodes), 1) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[99].callCurrent(this, nodes, isAttributes))) {
                return callSiteArray[100].call(nodes);
            }
            return callSiteArray[101].callCurrent(this, nodes, isAttributes, forceList);
        }
        if (ScriptBytecodeAdapter.compareEqual(callSiteArray[102].call(nodes), 1) && !DefaultTypeTransformation.booleanUnbox(callSiteArray[103].callCurrent(this, nodes, isAttributes))) {
            return callSiteArray[104].call(nodes);
        }
        return callSiteArray[105].callCurrent(this, nodes, isAttributes, forceList);
    }

    private boolean hasChildren(Object nodes, Object isAttributes) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(isAttributes)) {
            return false;
        }
        return !DefaultTypeTransformation.booleanUnbox(callSiteArray[106].call(callSiteArray[107].call(nodes)));
    }

    private Object toJavaList(Object nodes, Object isAttributes, Object forceList) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        Reference<Object> nodeList = new Reference<Object>(null);
        nodeList.get();
        if (DefaultTypeTransformation.booleanUnbox(forceList)) {
            List list = ScriptBytecodeAdapter.createList(new Object[0]);
            nodeList.set(list);
        } else if (nodes instanceof NodeChild) {
            Object nodeImpl = callSiteArray[108].callConstructor((Object)XMLAssertion.$get$$class$io$restassured$internal$path$xml$NodeImpl(), ScriptBytecodeAdapter.createMap(new Object[]{"name", callSiteArray[109].call(nodes), "groovyNode", nodes}));
            callSiteArray[110].callCurrent(this, nodeImpl, nodes);
            Object object = nodeImpl;
            nodeList.set(object);
        } else {
            Object object = callSiteArray[111].callConstructor((Object)XMLAssertion.$get$$class$io$restassured$internal$path$xml$NodeChildrenImpl(), ScriptBytecodeAdapter.createMap(new Object[]{"groovyNodes", nodes}));
            nodeList.set(object);
        }
        if (DefaultTypeTransformation.booleanUnbox(isAttributes)) {
            Reference<Object> temp = new Reference<Object>(null);
            temp.get();
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[112].call(nodes))) {
                Object t = null;
                temp.set(t);
            } else {
                List list = ScriptBytecodeAdapter.createList(new Object[0]);
                temp.set(list);
            }
            callSiteArray[113].call(nodes, new GeneratedClosure(this, this, temp){
                private /* synthetic */ Reference temp;
                private static /* synthetic */ ClassInfo $staticClassInfo;
                public static transient /* synthetic */ boolean __$stMC;
                private static /* synthetic */ SoftReference $callSiteArray;
                {
                    Reference reference;
                    CallSite[] callSiteArray = _toJavaList_closure1.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    this.temp = reference = temp;
                }

                public Object doCall(Object it) {
                    CallSite[] callSiteArray = _toJavaList_closure1.$getCallSiteArray();
                    CharArrayWriter caw = (CharArrayWriter)ScriptBytecodeAdapter.castToType(callSiteArray[0].callConstructor(CharArrayWriter.class), CharArrayWriter.class);
                    callSiteArray[1].call(it, caw);
                    callSiteArray[2].call(caw);
                    return callSiteArray[3].call(this.temp.get(), callSiteArray[4].call(caw));
                }

                public Object getTemp() {
                    CallSite[] callSiteArray = _toJavaList_closure1.$getCallSiteArray();
                    return this.temp.get();
                }

                public Object doCall() {
                    CallSite[] callSiteArray = _toJavaList_closure1.$getCallSiteArray();
                    return this.doCall(null);
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != _toJavaList_closure1.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    ClassInfo classInfo = $staticClassInfo;
                    if (classInfo == null) {
                        $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return classInfo.getMetaClass();
                }

                public static /* synthetic */ void __$swapInit() {
                    CallSite[] callSiteArray = _toJavaList_closure1.$getCallSiteArray();
                    $callSiteArray = null;
                }

                static {
                    _toJavaList_closure1.__$swapInit();
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                    stringArray[0] = "<$constructor$>";
                    stringArray[1] = "writeTo";
                    stringArray[2] = "close";
                    stringArray[3] = "leftShift";
                    stringArray[4] = "toString";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String[] stringArray = new String[5];
                    _toJavaList_closure1.$createCallSiteArray_1(stringArray);
                    return new CallSiteArray(_toJavaList_closure1.class, stringArray);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray callSiteArray;
                    if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                        callSiteArray = _toJavaList_closure1.$createCallSiteArray();
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
            });
            return temp.get();
        }
        callSiteArray[114].call(callSiteArray[115].call(nodes), new GeneratedClosure(this, this, nodeList){
            private /* synthetic */ Reference nodeList;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = _toJavaList_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.nodeList = reference = nodeList;
            }

            public Object doCall(Object it) {
                CallSite[] callSiteArray = _toJavaList_closure2.$getCallSiteArray();
                Object object = callSiteArray[0].callCurrent((GroovyObject)this, it);
                return callSiteArray[1].call(this.nodeList.get(), object);
            }

            public Object getNodeList() {
                CallSite[] callSiteArray = _toJavaList_closure2.$getCallSiteArray();
                return this.nodeList.get();
            }

            public Object doCall() {
                CallSite[] callSiteArray = _toJavaList_closure2.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != _toJavaList_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = _toJavaList_closure2.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                _toJavaList_closure2.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "convertToJavaObject";
                stringArray[1] = "leftShift";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[2];
                _toJavaList_closure2.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(_toJavaList_closure2.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = _toJavaList_closure2.$createCallSiteArray();
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
        });
        return nodeList.get();
    }

    private Object eval(Object root, Object object, Object expr) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        Map newParams = null;
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(this.params, null)) {
                Object object2 = callSiteArray[116].callConstructor(HashMap.class, this.params);
                newParams = (Map)ScriptBytecodeAdapter.castToType(object2, Map.class);
            } else {
                Object object3 = callSiteArray[117].callConstructor(HashMap.class);
                newParams = (Map)ScriptBytecodeAdapter.castToType(object3, Map.class);
            }
        } else if (ScriptBytecodeAdapter.compareNotEqual(this.params, null)) {
            Object object4 = callSiteArray[118].callConstructor(HashMap.class, this.params);
            newParams = (Map)ScriptBytecodeAdapter.castToType(object4, Map.class);
        } else {
            Object object5 = callSiteArray[119].callConstructor(HashMap.class);
            newParams = (Map)ScriptBytecodeAdapter.castToType(object5, Map.class);
        }
        callSiteArray[120].call(newParams, root, object);
        GroovyShell sh = (GroovyShell)ScriptBytecodeAdapter.castToType(callSiteArray[121].callConstructor(GroovyShell.class, callSiteArray[122].callConstructor(Binding.class, newParams)), GroovyShell.class);
        Object res = callSiteArray[123].call((Object)sh, expr);
        callSiteArray[124].call(sh);
        return res;
    }

    @Override
    public String description() {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        return "XML path";
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(XMLAssertion.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, XMLAssertion.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(XMLAssertion.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != XMLAssertion.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    @Override
    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    @Override
    public /* synthetic */ void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    @Override
    public /* synthetic */ Object invokeMethod(String string, Object object) {
        return this.getMetaClass().invokeMethod((Object)this, string, object);
    }

    @Override
    public /* synthetic */ Object getProperty(String string) {
        return this.getMetaClass().getProperty(this, string);
    }

    @Override
    public /* synthetic */ void setProperty(String string, Object object) {
        this.getMetaClass().setProperty(this, string, object);
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = XMLAssertion.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        String string;
        long l;
        long l2;
        XMLAssertion.__$swapInit();
        __timeStamp__239_neverHappen1702030715465 = l2 = 0L;
        __timeStamp = l = 1702030715465L;
        DOT = string = ".";
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String string) {
        this.key = string;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> map) {
        this.params = map;
    }

    public /* synthetic */ Object this$2$isDoubleStarFragment(String string) {
        return this.isDoubleStarFragment(string);
    }

    public /* synthetic */ Object this$2$preventTreatingRootObjectAsAList(Object object) {
        return this.preventTreatingRootObjectAsAList(object);
    }

    public /* synthetic */ Object this$2$convertToJavaObject(Object object) {
        return this.convertToJavaObject(object);
    }

    public /* synthetic */ Object this$2$handleList(List list) {
        return this.handleList(list);
    }

    public /* synthetic */ Object this$2$nodeToJavaObject(Object object) {
        return this.nodeToJavaObject(object);
    }

    public /* synthetic */ Object this$2$addAttributes(Object object, Object object2) {
        return this.addAttributes(object, object2);
    }

    public /* synthetic */ Object this$2$toJavaObject(Object object, Object object2, Object object3) {
        return this.toJavaObject(object, object2, object3);
    }

    public /* synthetic */ boolean this$2$hasChildren(Object object, Object object2) {
        return this.hasChildren(object, object2);
    }

    public /* synthetic */ Object this$2$toJavaList(Object object, Object object2, Object object3) {
        return this.toJavaList(object, object2, object3);
    }

    public /* synthetic */ Object this$2$eval(Object object, Object object2, Object object3) {
        return this.eval(object, object2, object3);
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
        stringArray[0] = "<$constructor$>";
        stringArray[1] = "startsWith";
        stringArray[2] = "substring";
        stringArray[3] = "escapePath";
        stringArray[4] = "hyphen";
        stringArray[5] = "attributeGetter";
        stringArray[6] = "doubleStar";
        stringArray[7] = "colon";
        stringArray[8] = "classKeyword";
        stringArray[9] = "indexOf";
        stringArray[10] = "split";
        stringArray[11] = "getAt";
        stringArray[12] = "isDoubleStarFragment";
        stringArray[13] = "isPathFragment";
        stringArray[14] = "startsWith";
        stringArray[15] = "plus";
        stringArray[16] = "substring";
        stringArray[17] = "startsWith";
        stringArray[18] = "plus";
        stringArray[19] = "eval";
        stringArray[20] = "format";
        stringArray[21] = "property";
        stringArray[22] = "<$constructor$>";
        stringArray[23] = "getMessage";
        stringArray[24] = "startsWith";
        stringArray[25] = "contains";
        stringArray[26] = "plus";
        stringArray[27] = "toString";
        stringArray[28] = "getAt";
        stringArray[29] = "replace";
        stringArray[30] = "replace";
        stringArray[31] = "replace";
        stringArray[32] = "replace";
        stringArray[33] = "getMessage";
        stringArray[34] = "plus";
        stringArray[35] = "indexOf";
        stringArray[36] = "length";
        stringArray[37] = "minus";
        stringArray[38] = "plus";
        stringArray[39] = "length";
        stringArray[40] = "length";
        stringArray[41] = "replace";
        stringArray[42] = "plus";
        stringArray[43] = "generateWhitespace";
        stringArray[44] = "replace";
        stringArray[45] = "plus";
        stringArray[46] = "generateWhitespace";
        stringArray[47] = "abs";
        stringArray[48] = "<$constructor$>";
        stringArray[49] = "convertToJavaObject";
        stringArray[50] = "preventTreatingRootObjectAsAList";
        stringArray[51] = "getResult";
        stringArray[52] = "getResult";
        stringArray[53] = "trim";
        stringArray[54] = "size";
        stringArray[55] = "getAt";
        stringArray[56] = "get";
        stringArray[57] = "size";
        stringArray[58] = "getAt";
        stringArray[59] = "get";
        stringArray[60] = "matches";
        stringArray[61] = "matcher";
        stringArray[62] = "isAssignableFrom";
        stringArray[63] = "getClass";
        stringArray[64] = "toJavaObject";
        stringArray[65] = "nodeToJavaObject";
        stringArray[66] = "toJavaObject";
        stringArray[67] = "toJavaObject";
        stringArray[68] = "get";
        stringArray[69] = "toJavaObject";
        stringArray[70] = "handleList";
        stringArray[71] = "size";
        stringArray[72] = "getAt";
        stringArray[73] = "convertToJavaObject";
        stringArray[74] = "get";
        stringArray[75] = "size";
        stringArray[76] = "set";
        stringArray[77] = "convertToJavaObject";
        stringArray[78] = "get";
        stringArray[79] = "next";
        stringArray[80] = "size";
        stringArray[81] = "getAt";
        stringArray[82] = "convertToJavaObject";
        stringArray[83] = "get";
        stringArray[84] = "size";
        stringArray[85] = "set";
        stringArray[86] = "convertToJavaObject";
        stringArray[87] = "get";
        stringArray[88] = "<$constructor$>";
        stringArray[89] = "name";
        stringArray[90] = "addAttributes";
        stringArray[91] = "iterator";
        stringArray[92] = "children";
        stringArray[93] = "convertToJavaObject";
        stringArray[94] = "leftShift";
        stringArray[95] = "children";
        stringArray[96] = "attributes";
        stringArray[97] = "convertToJavaObject";
        stringArray[98] = "size";
        stringArray[99] = "hasChildren";
        stringArray[100] = "text";
        stringArray[101] = "toJavaList";
        stringArray[102] = "size";
        stringArray[103] = "hasChildren";
        stringArray[104] = "text";
        stringArray[105] = "toJavaList";
        stringArray[106] = "isEmpty";
        stringArray[107] = "children";
        stringArray[108] = "<$constructor$>";
        stringArray[109] = "name";
        stringArray[110] = "addAttributes";
        stringArray[111] = "<$constructor$>";
        stringArray[112] = "isEmpty";
        stringArray[113] = "each";
        stringArray[114] = "each";
        stringArray[115] = "nodeIterator";
        stringArray[116] = "<$constructor$>";
        stringArray[117] = "<$constructor$>";
        stringArray[118] = "<$constructor$>";
        stringArray[119] = "<$constructor$>";
        stringArray[120] = "put";
        stringArray[121] = "<$constructor$>";
        stringArray[122] = "<$constructor$>";
        stringArray[123] = "evaluate";
        stringArray[124] = "resetLoadedClasses";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[125];
        XMLAssertion.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(XMLAssertion.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = XMLAssertion.$createCallSiteArray();
            $callSiteArray = new SoftReference<CallSiteArray>(callSiteArray);
        }
        return callSiteArray.array;
    }

    private static /* synthetic */ Class $get$$class$io$restassured$internal$path$xml$NodeChildrenImpl() {
        Class clazz = $class$io$restassured$internal$path$xml$NodeChildrenImpl;
        if (clazz == null) {
            clazz = $class$io$restassured$internal$path$xml$NodeChildrenImpl = XMLAssertion.class$("io.restassured.internal.path.xml.NodeChildrenImpl");
        }
        return clazz;
    }

    private static /* synthetic */ Class $get$$class$io$restassured$internal$path$xml$NodeImpl() {
        Class clazz = $class$io$restassured$internal$path$xml$NodeImpl;
        if (clazz == null) {
            clazz = $class$io$restassured$internal$path$xml$NodeImpl = XMLAssertion.class$("io.restassured.internal.path.xml.NodeImpl");
        }
        return clazz;
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

