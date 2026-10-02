/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.path.json;

import groovy.io.LineColumnReader;
import groovy.json.JsonException;
import groovy.json.JsonLexer;
import groovy.json.JsonToken;
import groovy.json.JsonTokenType;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.path.json.config.JsonPathConfig;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class ConfigurableJsonSlurper
implements GroovyObject {
    private static ThreadLocal<JsonPathConfig.NumberReturnType> numberReturnType;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030698422;
    private static /* synthetic */ SoftReference $callSiteArray;

    /*
     * WARNING - void declaration
     */
    public ConfigurableJsonSlurper(JsonPathConfig.NumberReturnType numberReturnType) {
        void numberReturnType2;
        MetaClass metaClass;
        CallSite[] callSiteArray = ConfigurableJsonSlurper.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
        callSiteArray[0].call(callSiteArray[1].callGetProperty(ConfigurableJsonSlurper.class), numberReturnType2);
    }

    static {
        long l;
        long l2;
        ConfigurableJsonSlurper.__$swapInit();
        __timeStamp__239_neverHappen1702030698422 = l2 = 0L;
        __timeStamp = l = 1702030698422L;
        Object object = ConfigurableJsonSlurper.$getCallSiteArray()[2].callConstructor(ThreadLocal.class);
        numberReturnType = (ThreadLocal)ScriptBytecodeAdapter.castToType(object, ThreadLocal.class);
        Reference<Object> original = new Reference<Object>(ConfigurableJsonSlurper.$getCallSiteArray()[3].call(ConfigurableJsonSlurper.$getCallSiteArray()[4].callGetProperty(JsonToken.class), "getValue"));
        GeneratedClosure generatedClosure = new GeneratedClosure(ConfigurableJsonSlurper.class, ConfigurableJsonSlurper.class, original){
            private /* synthetic */ Reference original;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private static /* synthetic */ SoftReference $callSiteArray;
            {
                Reference reference;
                CallSite[] callSiteArray = __clinit__closure1.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                this.original = reference = original;
            }

            public Object doCall() {
                CallSite[] callSiteArray = __clinit__closure1.$getCallSiteArray();
                Object result = callSiteArray[0].call(this.original.get(), callSiteArray[1].callGetProperty(this));
                JsonPathConfig.NumberReturnType numberReturnType = (JsonPathConfig.NumberReturnType)((Object)ScriptBytecodeAdapter.castToType(callSiteArray[2].call(callSiteArray[3].callGetProperty(this)), JsonPathConfig.NumberReturnType.class));
                if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[4].call((Object)numberReturnType)) && result instanceof BigDecimal) {
                        Object object;
                        Object object2;
                        result = ScriptBytecodeAdapter.compareGreaterThan(result, callSiteArray[5].callGetProperty(Float.class)) || ScriptBytecodeAdapter.compareEqual((Object)numberReturnType, callSiteArray[6].callGetProperty(JsonPathConfig.NumberReturnType.class)) ? (object2 = callSiteArray[7].call(result)) : (object = callSiteArray[8].call(result));
                    } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[9].call(callSiteArray[10].callGetProperty(JsonPathConfig.NumberReturnType.class), (Object)numberReturnType)) && (result instanceof Integer || result instanceof Long)) {
                        Object object;
                        result = object = callSiteArray[11].callConstructor(BigInteger.class, callSiteArray[12].call(result));
                    }
                } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[13].call((Object)numberReturnType)) && result instanceof BigDecimal) {
                    Object object;
                    Object object3;
                    result = ScriptBytecodeAdapter.compareGreaterThan(result, callSiteArray[14].callGetProperty(Float.class)) || ScriptBytecodeAdapter.compareEqual((Object)numberReturnType, callSiteArray[15].callGetProperty(JsonPathConfig.NumberReturnType.class)) ? (object3 = callSiteArray[16].call(result)) : (object = callSiteArray[17].call(result));
                } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[18].call(callSiteArray[19].callGetProperty(JsonPathConfig.NumberReturnType.class), (Object)numberReturnType)) && (result instanceof Integer || result instanceof Long)) {
                    Object object;
                    result = object = callSiteArray[20].callConstructor(BigInteger.class, callSiteArray[21].call(result));
                }
                return result;
            }

            public Object getOriginal() {
                CallSite[] callSiteArray = __clinit__closure1.$getCallSiteArray();
                return this.original.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != __clinit__closure1.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return classInfo.getMetaClass();
            }

            public static /* synthetic */ void __$swapInit() {
                CallSite[] callSiteArray = __clinit__closure1.$getCallSiteArray();
                $callSiteArray = null;
            }

            static {
                __clinit__closure1.__$swapInit();
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArray) {
                stringArray[0] = "invoke";
                stringArray[1] = "delegate";
                stringArray[2] = "get";
                stringArray[3] = "numberReturnType";
                stringArray[4] = "isFloatOrDouble";
                stringArray[5] = "MAX_VALUE";
                stringArray[6] = "DOUBLE";
                stringArray[7] = "doubleValue";
                stringArray[8] = "floatValue";
                stringArray[9] = "equals";
                stringArray[10] = "BIG_INTEGER";
                stringArray[11] = "<$constructor$>";
                stringArray[12] = "toString";
                stringArray[13] = "isFloatOrDouble";
                stringArray[14] = "MAX_VALUE";
                stringArray[15] = "DOUBLE";
                stringArray[16] = "doubleValue";
                stringArray[17] = "floatValue";
                stringArray[18] = "equals";
                stringArray[19] = "BIG_INTEGER";
                stringArray[20] = "<$constructor$>";
                stringArray[21] = "toString";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] stringArray = new String[22];
                __clinit__closure1.$createCallSiteArray_1(stringArray);
                return new CallSiteArray(__clinit__closure1.class, stringArray);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                    callSiteArray = __clinit__closure1.$createCallSiteArray();
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
        };
        ScriptBytecodeAdapter.setProperty(generatedClosure, null, ConfigurableJsonSlurper.$getCallSiteArray()[5].callGetProperty(JsonToken.class), "getValue");
    }

    public Object parseText(String text) {
        CallSite[] callSiteArray = ConfigurableJsonSlurper.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(text, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[6].call(text), 0)) {
                throw (Throwable)callSiteArray[7].callConstructor(IllegalArgumentException.class, "The JSON input text should neither be null nor empty.");
            }
        } else if (ScriptBytecodeAdapter.compareEqual(text, null) || ScriptBytecodeAdapter.compareEqual(callSiteArray[8].call(text), 0)) {
            throw (Throwable)callSiteArray[9].callConstructor(IllegalArgumentException.class, "The JSON input text should neither be null nor empty.");
        }
        return callSiteArray[10].callCurrent((GroovyObject)this, callSiteArray[11].callConstructor(LineColumnReader.class, callSiteArray[12].callConstructor(StringReader.class, text)));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Object parse(Reader reader) {
        CallSite[] callSiteArray = ConfigurableJsonSlurper.$getCallSiteArray();
        Object content = null;
        JsonLexer lexer = (JsonLexer)ScriptBytecodeAdapter.castToType(callSiteArray[13].callConstructor(JsonLexer.class, reader), JsonLexer.class);
        JsonToken token = (JsonToken)ScriptBytecodeAdapter.castToType(callSiteArray[14].call(lexer), JsonToken.class);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass() ? ScriptBytecodeAdapter.compareEqual(token, null) : ScriptBytecodeAdapter.compareEqual(token, null)) {
            return null;
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[15].call(token), callSiteArray[16].callGetProperty(JsonTokenType.class))) {
                Object object = callSiteArray[17].callCurrent((GroovyObject)this, lexer);
                return object;
            }
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[18].call(token), callSiteArray[19].callGetProperty(JsonTokenType.class))) {
                Object object = callSiteArray[20].callCurrent((GroovyObject)this, lexer);
                return object;
            }
            if (!ScriptBytecodeAdapter.compareGreaterThanEqual(callSiteArray[21].call(callSiteArray[22].call(token)), callSiteArray[23].call(callSiteArray[24].callGetProperty(JsonTokenType.class)))) throw (Throwable)callSiteArray[26].callConstructor(JsonException.class, callSiteArray[27].call(callSiteArray[28].call(callSiteArray[29].call(callSiteArray[30].call(callSiteArray[31].call(callSiteArray[32].call(callSiteArray[33].call(callSiteArray[34].call(callSiteArray[35].call(callSiteArray[36].call(callSiteArray[37].call(callSiteArray[38].call((Object)"A JSON payload should start with ", callSiteArray[39].call(callSiteArray[40].callGetProperty(JsonTokenType.class))), ", "), callSiteArray[41].call(callSiteArray[42].callGetProperty(JsonTokenType.class))), " or a primitive value.\n"), "Instead, '"), callSiteArray[43].call(token)), "' was found "), "on line: "), callSiteArray[44].call(token)), ", "), "column: "), callSiteArray[45].call(token)));
            Object object = callSiteArray[25].call(token);
            return object;
        }
        if (ScriptBytecodeAdapter.compareEqual(callSiteArray[46].call(token), callSiteArray[47].callGetProperty(JsonTokenType.class))) {
            Object object = callSiteArray[48].callCurrent((GroovyObject)this, lexer);
            return object;
        }
        if (ScriptBytecodeAdapter.compareEqual(callSiteArray[49].call(token), callSiteArray[50].callGetProperty(JsonTokenType.class))) {
            Object object = callSiteArray[51].callCurrent((GroovyObject)this, lexer);
            return object;
        }
        if (!ScriptBytecodeAdapter.compareGreaterThanEqual(callSiteArray[52].call(callSiteArray[53].call(token)), callSiteArray[54].call(callSiteArray[55].callGetProperty(JsonTokenType.class)))) throw (Throwable)callSiteArray[57].callConstructor(JsonException.class, callSiteArray[58].call(callSiteArray[59].call(callSiteArray[60].call(callSiteArray[61].call(callSiteArray[62].call(callSiteArray[63].call(callSiteArray[64].call(callSiteArray[65].call(callSiteArray[66].call(callSiteArray[67].call(callSiteArray[68].call(callSiteArray[69].call((Object)"A JSON payload should start with ", callSiteArray[70].call(callSiteArray[71].callGetProperty(JsonTokenType.class))), ", "), callSiteArray[72].call(callSiteArray[73].callGetProperty(JsonTokenType.class))), " or a primitive value.\n"), "Instead, '"), callSiteArray[74].call(token)), "' was found "), "on line: "), callSiteArray[75].call(token)), ", "), "column: "), callSiteArray[76].call(token)));
        Object object = callSiteArray[56].call(token);
        return object;
    }

    private List parseArray(JsonLexer lexer) {
        CallSite[] callSiteArray = ConfigurableJsonSlurper.$getCallSiteArray();
        List content = (List)ScriptBytecodeAdapter.castToType(callSiteArray[77].callConstructor(ArrayList.class), List.class);
        JsonToken currentToken = null;
        while (1 != 0) {
            Object object = callSiteArray[78].call(lexer);
            currentToken = (JsonToken)ScriptBytecodeAdapter.castToType(object, JsonToken.class);
            if (ScriptBytecodeAdapter.compareEqual(currentToken, null)) {
                throw (Throwable)callSiteArray[79].callConstructor(JsonException.class, callSiteArray[80].call(callSiteArray[81].call(callSiteArray[82].call(callSiteArray[83].call(callSiteArray[84].call(callSiteArray[85].call((Object)"Expected a value on line: ", callSiteArray[86].call(callSiteArray[87].call(lexer))), ", "), "column: "), callSiteArray[88].call(callSiteArray[89].call(lexer))), ".\n"), "But got an unterminated array."));
            }
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[90].call(currentToken), callSiteArray[91].callGetProperty(JsonTokenType.class))) {
                callSiteArray[92].call((Object)content, callSiteArray[93].callCurrent((GroovyObject)this, lexer));
            } else if (ScriptBytecodeAdapter.compareEqual(callSiteArray[94].call(currentToken), callSiteArray[95].callGetProperty(JsonTokenType.class))) {
                callSiteArray[96].call((Object)content, callSiteArray[97].callCurrent((GroovyObject)this, lexer));
            } else if (ScriptBytecodeAdapter.compareGreaterThanEqual(callSiteArray[98].call(callSiteArray[99].call(currentToken)), callSiteArray[100].call(callSiteArray[101].callGetProperty(JsonTokenType.class)))) {
                callSiteArray[102].call((Object)content, callSiteArray[103].call(currentToken));
            } else {
                if (ScriptBytecodeAdapter.compareEqual(callSiteArray[104].call(currentToken), callSiteArray[105].callGetProperty(JsonTokenType.class))) {
                    return content;
                }
                throw (Throwable)callSiteArray[106].callConstructor(JsonException.class, callSiteArray[107].call(callSiteArray[108].call(callSiteArray[109].call(callSiteArray[110].call(callSiteArray[111].call(callSiteArray[112].call(callSiteArray[113].call(callSiteArray[114].call(callSiteArray[115].call((Object)"Expected a value, an array, or an object ", "on line: "), callSiteArray[116].call(currentToken)), ", "), "column: "), callSiteArray[117].call(currentToken)), ".\n"), "But got '"), callSiteArray[118].call(currentToken)), "' instead."));
            }
            Object object2 = callSiteArray[119].call(lexer);
            currentToken = (JsonToken)ScriptBytecodeAdapter.castToType(object2, JsonToken.class);
            if (ScriptBytecodeAdapter.compareEqual(currentToken, null)) {
                throw (Throwable)callSiteArray[120].callConstructor(JsonException.class, callSiteArray[121].call(callSiteArray[122].call(callSiteArray[123].call(callSiteArray[124].call(callSiteArray[125].call(callSiteArray[126].call(callSiteArray[127].call(callSiteArray[128].call(callSiteArray[129].call(callSiteArray[130].call(callSiteArray[131].call(callSiteArray[132].call((Object)"Expected ", callSiteArray[133].call(callSiteArray[134].callGetProperty(JsonTokenType.class))), " "), "or "), callSiteArray[135].call(callSiteArray[136].callGetProperty(JsonTokenType.class))), " "), "on line: "), callSiteArray[137].call(callSiteArray[138].call(lexer))), ", "), "column: "), callSiteArray[139].call(callSiteArray[140].call(lexer))), ".\n"), "But got an unterminated array."));
            }
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[141].call(currentToken), callSiteArray[142].callGetProperty(JsonTokenType.class))) break;
            if (!ScriptBytecodeAdapter.compareNotEqual(callSiteArray[143].call(currentToken), callSiteArray[144].callGetProperty(JsonTokenType.class))) continue;
            throw (Throwable)callSiteArray[145].callConstructor(JsonException.class, callSiteArray[146].call(callSiteArray[147].call(callSiteArray[148].call(callSiteArray[149].call(callSiteArray[150].call(callSiteArray[151].call(callSiteArray[152].call(callSiteArray[153].call(callSiteArray[154].call(callSiteArray[155].call(callSiteArray[156].call((Object)"Expected a value or ", callSiteArray[157].call(callSiteArray[158].callGetProperty(JsonTokenType.class))), " "), "on line: "), callSiteArray[159].call(currentToken)), " "), "column: "), callSiteArray[160].call(currentToken)), ".\n"), "But got '"), callSiteArray[161].call(currentToken)), "' instead."));
        }
        return content;
    }

    private Map parseObject(JsonLexer lexer) {
        CallSite[] callSiteArray = ConfigurableJsonSlurper.$getCallSiteArray();
        Map content = (Map)ScriptBytecodeAdapter.castToType(callSiteArray[162].callConstructor(LinkedHashMap.class), Map.class);
        JsonToken previousToken = null;
        JsonToken currentToken = null;
        while (1 != 0) {
            JsonToken jsonToken;
            Object object = callSiteArray[163].call(lexer);
            currentToken = (JsonToken)ScriptBytecodeAdapter.castToType(object, JsonToken.class);
            if (ScriptBytecodeAdapter.compareEqual(currentToken, null)) {
                throw (Throwable)callSiteArray[164].callConstructor(JsonException.class, callSiteArray[165].call(callSiteArray[166].call(callSiteArray[167].call(callSiteArray[168].call(callSiteArray[169].call(callSiteArray[170].call((Object)"Expected a String key on line: ", callSiteArray[171].call(callSiteArray[172].call(lexer))), ", "), "column: "), callSiteArray[173].call(callSiteArray[174].call(lexer))), ".\n"), "But got an unterminated object."));
            }
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[175].call(currentToken), callSiteArray[176].callGetProperty(JsonTokenType.class))) {
                return content;
            }
            if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[177].call(currentToken), callSiteArray[178].callGetProperty(JsonTokenType.class))) {
                throw (Throwable)callSiteArray[179].callConstructor(JsonException.class, callSiteArray[180].call(callSiteArray[181].call(callSiteArray[182].call(callSiteArray[183].call(callSiteArray[184].call(callSiteArray[185].call(callSiteArray[186].call(callSiteArray[187].call(callSiteArray[188].call(callSiteArray[189].call(callSiteArray[190].call((Object)"Expected ", callSiteArray[191].call(callSiteArray[192].callGetProperty(JsonTokenType.class))), " key "), "on line: "), callSiteArray[193].call(currentToken)), ", "), "column: "), callSiteArray[194].call(currentToken)), ".\n"), "But got '"), callSiteArray[195].call(currentToken)), "' instead."));
            }
            String mapKey = (String)ScriptBytecodeAdapter.castToType(callSiteArray[196].call(currentToken), String.class);
            Object object2 = callSiteArray[197].call(lexer);
            currentToken = (JsonToken)ScriptBytecodeAdapter.castToType(object2, JsonToken.class);
            if (ScriptBytecodeAdapter.compareEqual(currentToken, null)) {
                throw (Throwable)callSiteArray[198].callConstructor(JsonException.class, callSiteArray[199].call(callSiteArray[200].call(callSiteArray[201].call(callSiteArray[202].call(callSiteArray[203].call(callSiteArray[204].call(callSiteArray[205].call(callSiteArray[206].call(callSiteArray[207].call((Object)"Expected a ", callSiteArray[208].call(callSiteArray[209].callGetProperty(JsonTokenType.class))), " "), "on line: "), callSiteArray[210].call(callSiteArray[211].call(lexer))), ", "), "column: "), callSiteArray[212].call(callSiteArray[213].call(lexer))), ".\n"), "But got an unterminated object."));
            }
            if (ScriptBytecodeAdapter.compareNotEqual(callSiteArray[214].call(currentToken), callSiteArray[215].callGetProperty(JsonTokenType.class))) {
                throw (Throwable)callSiteArray[216].callConstructor(JsonException.class, callSiteArray[217].call(callSiteArray[218].call(callSiteArray[219].call(callSiteArray[220].call(callSiteArray[221].call(callSiteArray[222].call(callSiteArray[223].call(callSiteArray[224].call(callSiteArray[225].call(callSiteArray[226].call(callSiteArray[227].call((Object)"Expected ", callSiteArray[228].call(callSiteArray[229].callGetProperty(JsonTokenType.class))), " "), "on line: "), callSiteArray[230].call(currentToken)), ", "), "column: "), callSiteArray[231].call(currentToken)), ".\n"), "But got '"), callSiteArray[232].call(currentToken)), "' instead."));
            }
            Object object3 = callSiteArray[233].call(lexer);
            currentToken = (JsonToken)ScriptBytecodeAdapter.castToType(object3, JsonToken.class);
            if (ScriptBytecodeAdapter.compareEqual(currentToken, null)) {
                throw (Throwable)callSiteArray[234].callConstructor(JsonException.class, callSiteArray[235].call(callSiteArray[236].call(callSiteArray[237].call(callSiteArray[238].call(callSiteArray[239].call(callSiteArray[240].call(callSiteArray[241].call((Object)"Expected a value ", "on line: "), callSiteArray[242].call(callSiteArray[243].call(lexer))), ", "), "column: "), callSiteArray[244].call(callSiteArray[245].call(lexer))), ".\n"), "But got an unterminated object."));
            }
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[246].call(currentToken), callSiteArray[247].callGetProperty(JsonTokenType.class))) {
                callSiteArray[248].call(content, mapKey, callSiteArray[249].callCurrent((GroovyObject)this, lexer));
            } else if (ScriptBytecodeAdapter.compareEqual(callSiteArray[250].call(currentToken), callSiteArray[251].callGetProperty(JsonTokenType.class))) {
                callSiteArray[252].call(content, mapKey, callSiteArray[253].callCurrent((GroovyObject)this, lexer));
            } else if (ScriptBytecodeAdapter.compareGreaterThanEqual(callSiteArray[254].call(callSiteArray[255].call(currentToken)), callSiteArray[256].call(callSiteArray[257].callGetProperty(JsonTokenType.class)))) {
                callSiteArray[258].call(content, mapKey, callSiteArray[259].call(currentToken));
            } else {
                throw (Throwable)callSiteArray[260].callConstructor(JsonException.class, callSiteArray[261].call(callSiteArray[262].call(callSiteArray[263].call(callSiteArray[264].call(callSiteArray[265].call(callSiteArray[266].call(callSiteArray[267].call(callSiteArray[268].call(callSiteArray[269].call((Object)"Expected a value, an array, or an object ", "on line: "), callSiteArray[270].call(currentToken)), ", "), "column: "), callSiteArray[271].call(currentToken)), ".\n"), "But got '"), callSiteArray[272].call(currentToken)), "' instead."));
            }
            previousToken = jsonToken = currentToken;
            Object object4 = callSiteArray[273].call(lexer);
            currentToken = (JsonToken)ScriptBytecodeAdapter.castToType(object4, JsonToken.class);
            if (ScriptBytecodeAdapter.compareEqual(currentToken, null)) {
                throw (Throwable)callSiteArray[274].callConstructor(JsonException.class, callSiteArray[275].call(callSiteArray[276].call(callSiteArray[277].call(callSiteArray[278].call(callSiteArray[279].call(callSiteArray[280].call(callSiteArray[281].call(callSiteArray[282].call(callSiteArray[283].call(callSiteArray[284].call(callSiteArray[285].call((Object)"Expected ", callSiteArray[286].call(callSiteArray[287].callGetProperty(JsonTokenType.class))), " or "), callSiteArray[288].call(callSiteArray[289].callGetProperty(JsonTokenType.class))), " "), "on line: "), callSiteArray[290].call(previousToken)), ", "), "column: "), callSiteArray[291].call(previousToken)), ".\n"), "But got an unterminated object."));
            }
            if (ScriptBytecodeAdapter.compareEqual(callSiteArray[292].call(currentToken), callSiteArray[293].callGetProperty(JsonTokenType.class))) break;
            if (!ScriptBytecodeAdapter.compareNotEqual(callSiteArray[294].call(currentToken), callSiteArray[295].callGetProperty(JsonTokenType.class))) continue;
            throw (Throwable)callSiteArray[296].callConstructor(JsonException.class, callSiteArray[297].call(callSiteArray[298].call(callSiteArray[299].call(callSiteArray[300].call(callSiteArray[301].call(callSiteArray[302].call(callSiteArray[303].call(callSiteArray[304].call(callSiteArray[305].call(callSiteArray[306].call(callSiteArray[307].call((Object)"Expected a value or ", callSiteArray[308].call(callSiteArray[309].callGetProperty(JsonTokenType.class))), " "), "on line: "), callSiteArray[310].call(currentToken)), ", "), "column: "), callSiteArray[311].call(currentToken)), ".\n"), "But got '"), callSiteArray[312].call(currentToken)), "' instead."));
        }
        return content;
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = ConfigurableJsonSlurper.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ConfigurableJsonSlurper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = ConfigurableJsonSlurper.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ConfigurableJsonSlurper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = ConfigurableJsonSlurper.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ConfigurableJsonSlurper.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ConfigurableJsonSlurper.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            $staticClassInfo = classInfo = ClassInfo.getClassInfo(this.getClass());
        }
        return classInfo.getMetaClass();
    }

    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass = this.metaClass;
        if (metaClass != null) {
            return metaClass;
        }
        this.metaClass = this.$getStaticMetaClass();
        return this.metaClass;
    }

    public /* synthetic */ void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public /* synthetic */ Object invokeMethod(String string, Object object) {
        return this.getMetaClass().invokeMethod((Object)this, string, object);
    }

    public /* synthetic */ Object getProperty(String string) {
        return this.getMetaClass().getProperty(this, string);
    }

    public /* synthetic */ void setProperty(String string, Object object) {
        this.getMetaClass().setProperty(this, string, object);
    }

    public static /* synthetic */ void __$swapInit() {
        CallSite[] callSiteArray = ConfigurableJsonSlurper.$getCallSiteArray();
        $callSiteArray = null;
    }

    public /* synthetic */ List this$2$parseArray(JsonLexer jsonLexer) {
        return this.parseArray(jsonLexer);
    }

    public /* synthetic */ Map this$2$parseObject(JsonLexer jsonLexer) {
        return this.parseObject(jsonLexer);
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
        stringArray[0] = "set";
        stringArray[1] = "numberReturnType";
        stringArray[2] = "<$constructor$>";
        stringArray[3] = "getMetaMethod";
        stringArray[4] = "metaClass";
        stringArray[5] = "metaClass";
        stringArray[6] = "length";
        stringArray[7] = "<$constructor$>";
        stringArray[8] = "length";
        stringArray[9] = "<$constructor$>";
        stringArray[10] = "parse";
        stringArray[11] = "<$constructor$>";
        stringArray[12] = "<$constructor$>";
        stringArray[13] = "<$constructor$>";
        stringArray[14] = "nextToken";
        stringArray[15] = "getType";
        stringArray[16] = "OPEN_CURLY";
        stringArray[17] = "parseObject";
        stringArray[18] = "getType";
        stringArray[19] = "OPEN_BRACKET";
        stringArray[20] = "parseArray";
        stringArray[21] = "ordinal";
        stringArray[22] = "getType";
        stringArray[23] = "ordinal";
        stringArray[24] = "NULL";
        stringArray[25] = "getValue";
        stringArray[26] = "<$constructor$>";
        stringArray[27] = "plus";
        stringArray[28] = "plus";
        stringArray[29] = "plus";
        stringArray[30] = "plus";
        stringArray[31] = "plus";
        stringArray[32] = "plus";
        stringArray[33] = "plus";
        stringArray[34] = "plus";
        stringArray[35] = "plus";
        stringArray[36] = "plus";
        stringArray[37] = "plus";
        stringArray[38] = "plus";
        stringArray[39] = "getLabel";
        stringArray[40] = "OPEN_CURLY";
        stringArray[41] = "getLabel";
        stringArray[42] = "OPEN_BRACKET";
        stringArray[43] = "getText";
        stringArray[44] = "getStartLine";
        stringArray[45] = "getStartColumn";
        stringArray[46] = "getType";
        stringArray[47] = "OPEN_CURLY";
        stringArray[48] = "parseObject";
        stringArray[49] = "getType";
        stringArray[50] = "OPEN_BRACKET";
        stringArray[51] = "parseArray";
        stringArray[52] = "ordinal";
        stringArray[53] = "getType";
        stringArray[54] = "ordinal";
        stringArray[55] = "NULL";
        stringArray[56] = "getValue";
        stringArray[57] = "<$constructor$>";
        stringArray[58] = "plus";
        stringArray[59] = "plus";
        stringArray[60] = "plus";
        stringArray[61] = "plus";
        stringArray[62] = "plus";
        stringArray[63] = "plus";
        stringArray[64] = "plus";
        stringArray[65] = "plus";
        stringArray[66] = "plus";
        stringArray[67] = "plus";
        stringArray[68] = "plus";
        stringArray[69] = "plus";
        stringArray[70] = "getLabel";
        stringArray[71] = "OPEN_CURLY";
        stringArray[72] = "getLabel";
        stringArray[73] = "OPEN_BRACKET";
        stringArray[74] = "getText";
        stringArray[75] = "getStartLine";
        stringArray[76] = "getStartColumn";
        stringArray[77] = "<$constructor$>";
        stringArray[78] = "nextToken";
        stringArray[79] = "<$constructor$>";
        stringArray[80] = "plus";
        stringArray[81] = "plus";
        stringArray[82] = "plus";
        stringArray[83] = "plus";
        stringArray[84] = "plus";
        stringArray[85] = "plus";
        stringArray[86] = "getLine";
        stringArray[87] = "getReader";
        stringArray[88] = "getColumn";
        stringArray[89] = "getReader";
        stringArray[90] = "getType";
        stringArray[91] = "OPEN_CURLY";
        stringArray[92] = "add";
        stringArray[93] = "parseObject";
        stringArray[94] = "getType";
        stringArray[95] = "OPEN_BRACKET";
        stringArray[96] = "add";
        stringArray[97] = "parseArray";
        stringArray[98] = "ordinal";
        stringArray[99] = "getType";
        stringArray[100] = "ordinal";
        stringArray[101] = "NULL";
        stringArray[102] = "add";
        stringArray[103] = "getValue";
        stringArray[104] = "getType";
        stringArray[105] = "CLOSE_BRACKET";
        stringArray[106] = "<$constructor$>";
        stringArray[107] = "plus";
        stringArray[108] = "plus";
        stringArray[109] = "plus";
        stringArray[110] = "plus";
        stringArray[111] = "plus";
        stringArray[112] = "plus";
        stringArray[113] = "plus";
        stringArray[114] = "plus";
        stringArray[115] = "plus";
        stringArray[116] = "getStartLine";
        stringArray[117] = "getStartColumn";
        stringArray[118] = "getText";
        stringArray[119] = "nextToken";
        stringArray[120] = "<$constructor$>";
        stringArray[121] = "plus";
        stringArray[122] = "plus";
        stringArray[123] = "plus";
        stringArray[124] = "plus";
        stringArray[125] = "plus";
        stringArray[126] = "plus";
        stringArray[127] = "plus";
        stringArray[128] = "plus";
        stringArray[129] = "plus";
        stringArray[130] = "plus";
        stringArray[131] = "plus";
        stringArray[132] = "plus";
        stringArray[133] = "getLabel";
        stringArray[134] = "CLOSE_BRACKET";
        stringArray[135] = "getLabel";
        stringArray[136] = "COMMA";
        stringArray[137] = "getLine";
        stringArray[138] = "getReader";
        stringArray[139] = "getColumn";
        stringArray[140] = "getReader";
        stringArray[141] = "getType";
        stringArray[142] = "CLOSE_BRACKET";
        stringArray[143] = "getType";
        stringArray[144] = "COMMA";
        stringArray[145] = "<$constructor$>";
        stringArray[146] = "plus";
        stringArray[147] = "plus";
        stringArray[148] = "plus";
        stringArray[149] = "plus";
        stringArray[150] = "plus";
        stringArray[151] = "plus";
        stringArray[152] = "plus";
        stringArray[153] = "plus";
        stringArray[154] = "plus";
        stringArray[155] = "plus";
        stringArray[156] = "plus";
        stringArray[157] = "getLabel";
        stringArray[158] = "CLOSE_BRACKET";
        stringArray[159] = "getStartLine";
        stringArray[160] = "getStartColumn";
        stringArray[161] = "getText";
        stringArray[162] = "<$constructor$>";
        stringArray[163] = "nextToken";
        stringArray[164] = "<$constructor$>";
        stringArray[165] = "plus";
        stringArray[166] = "plus";
        stringArray[167] = "plus";
        stringArray[168] = "plus";
        stringArray[169] = "plus";
        stringArray[170] = "plus";
        stringArray[171] = "getLine";
        stringArray[172] = "getReader";
        stringArray[173] = "getColumn";
        stringArray[174] = "getReader";
        stringArray[175] = "getType";
        stringArray[176] = "CLOSE_CURLY";
        stringArray[177] = "getType";
        stringArray[178] = "STRING";
        stringArray[179] = "<$constructor$>";
        stringArray[180] = "plus";
        stringArray[181] = "plus";
        stringArray[182] = "plus";
        stringArray[183] = "plus";
        stringArray[184] = "plus";
        stringArray[185] = "plus";
        stringArray[186] = "plus";
        stringArray[187] = "plus";
        stringArray[188] = "plus";
        stringArray[189] = "plus";
        stringArray[190] = "plus";
        stringArray[191] = "getLabel";
        stringArray[192] = "STRING";
        stringArray[193] = "getStartLine";
        stringArray[194] = "getStartColumn";
        stringArray[195] = "getText";
        stringArray[196] = "getValue";
        stringArray[197] = "nextToken";
        stringArray[198] = "<$constructor$>";
        stringArray[199] = "plus";
        stringArray[200] = "plus";
        stringArray[201] = "plus";
        stringArray[202] = "plus";
        stringArray[203] = "plus";
        stringArray[204] = "plus";
        stringArray[205] = "plus";
        stringArray[206] = "plus";
        stringArray[207] = "plus";
        stringArray[208] = "getLabel";
        stringArray[209] = "COLON";
        stringArray[210] = "getLine";
        stringArray[211] = "getReader";
        stringArray[212] = "getColumn";
        stringArray[213] = "getReader";
        stringArray[214] = "getType";
        stringArray[215] = "COLON";
        stringArray[216] = "<$constructor$>";
        stringArray[217] = "plus";
        stringArray[218] = "plus";
        stringArray[219] = "plus";
        stringArray[220] = "plus";
        stringArray[221] = "plus";
        stringArray[222] = "plus";
        stringArray[223] = "plus";
        stringArray[224] = "plus";
        stringArray[225] = "plus";
        stringArray[226] = "plus";
        stringArray[227] = "plus";
        stringArray[228] = "getLabel";
        stringArray[229] = "COLON";
        stringArray[230] = "getStartLine";
        stringArray[231] = "getStartColumn";
        stringArray[232] = "getText";
        stringArray[233] = "nextToken";
        stringArray[234] = "<$constructor$>";
        stringArray[235] = "plus";
        stringArray[236] = "plus";
        stringArray[237] = "plus";
        stringArray[238] = "plus";
        stringArray[239] = "plus";
        stringArray[240] = "plus";
        stringArray[241] = "plus";
        stringArray[242] = "getLine";
        stringArray[243] = "getReader";
        stringArray[244] = "getColumn";
        stringArray[245] = "getReader";
        stringArray[246] = "getType";
        stringArray[247] = "OPEN_CURLY";
        stringArray[248] = "put";
        stringArray[249] = "parseObject";
        stringArray[250] = "getType";
        stringArray[251] = "OPEN_BRACKET";
        stringArray[252] = "put";
        stringArray[253] = "parseArray";
        stringArray[254] = "ordinal";
        stringArray[255] = "getType";
        stringArray[256] = "ordinal";
        stringArray[257] = "NULL";
        stringArray[258] = "put";
        stringArray[259] = "getValue";
        stringArray[260] = "<$constructor$>";
        stringArray[261] = "plus";
        stringArray[262] = "plus";
        stringArray[263] = "plus";
        stringArray[264] = "plus";
        stringArray[265] = "plus";
        stringArray[266] = "plus";
        stringArray[267] = "plus";
        stringArray[268] = "plus";
        stringArray[269] = "plus";
        stringArray[270] = "getStartLine";
        stringArray[271] = "getStartColumn";
        stringArray[272] = "getText";
        stringArray[273] = "nextToken";
        stringArray[274] = "<$constructor$>";
        stringArray[275] = "plus";
        stringArray[276] = "plus";
        stringArray[277] = "plus";
        stringArray[278] = "plus";
        stringArray[279] = "plus";
        stringArray[280] = "plus";
        stringArray[281] = "plus";
        stringArray[282] = "plus";
        stringArray[283] = "plus";
        stringArray[284] = "plus";
        stringArray[285] = "plus";
        stringArray[286] = "getLabel";
        stringArray[287] = "CLOSE_CURLY";
        stringArray[288] = "getLabel";
        stringArray[289] = "COMMA";
        stringArray[290] = "getEndLine";
        stringArray[291] = "getEndColumn";
        stringArray[292] = "getType";
        stringArray[293] = "CLOSE_CURLY";
        stringArray[294] = "getType";
        stringArray[295] = "COMMA";
        stringArray[296] = "<$constructor$>";
        stringArray[297] = "plus";
        stringArray[298] = "plus";
        stringArray[299] = "plus";
        stringArray[300] = "plus";
        stringArray[301] = "plus";
        stringArray[302] = "plus";
        stringArray[303] = "plus";
        stringArray[304] = "plus";
        stringArray[305] = "plus";
        stringArray[306] = "plus";
        stringArray[307] = "plus";
        stringArray[308] = "getLabel";
        stringArray[309] = "CLOSE_CURLY";
        stringArray[310] = "getStartLine";
        stringArray[311] = "getStartColumn";
        stringArray[312] = "getText";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[313];
        ConfigurableJsonSlurper.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(ConfigurableJsonSlurper.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ConfigurableJsonSlurper.$createCallSiteArray();
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

