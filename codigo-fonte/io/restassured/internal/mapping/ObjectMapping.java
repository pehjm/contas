/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.mapping;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import io.restassured.common.mapper.DataToDeserialize;
import io.restassured.common.mapper.resolver.ObjectMapperResolver;
import io.restassured.config.EncoderConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.http.ContentTypeExtractor;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.internal.mapping.Jackson1Mapper;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.internal.mapping.JakartaEEMapper;
import io.restassured.internal.mapping.JaxbMapper;
import io.restassured.internal.mapping.JohnzonMapper;
import io.restassured.internal.mapping.JsonbMapper;
import io.restassured.internal.mapping.ObjectMapperDeserializationContextImpl;
import io.restassured.internal.mapping.ObjectMapperSerializationContextImpl;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson1ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.path.json.mapper.factory.JohnzonObjectMapperFactory;
import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import io.restassured.path.xml.mapper.factory.JAXBObjectMapperFactory;
import io.restassured.path.xml.mapper.factory.JakartaEEObjectMapperFactory;
import io.restassured.response.ResponseBodyData;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.reflect.Type;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
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
public class ObjectMapping
implements GroovyObject {
    private static /* synthetic */ ClassInfo $staticClassInfo;
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ long __timeStamp;
    public static /* synthetic */ long __timeStamp__239_neverHappen1702030729372;
    private static /* synthetic */ SoftReference $callSiteArray;

    public ObjectMapping() {
        MetaClass metaClass;
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        this.metaClass = metaClass = this.$getStaticMetaClass();
    }

    public static <T> T deserialize(ResponseBodyData response, Type cls, String contentType, String defaultContentType, String charset, ObjectMapperType mapperType, ObjectMapperConfig objectMapperConfig) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        callSiteArray[0].call(Validate.class, objectMapperConfig, "String mapper configuration wasn't found, cannot deserialize.");
        Object deserializationCtx = callSiteArray[1].callStatic(ObjectMapping.class, response, cls, contentType, charset);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[2].call(objectMapperConfig))) {
                return (T)callSiteArray[3].call(callSiteArray[4].call(objectMapperConfig), callSiteArray[5].callStatic(ObjectMapping.class, response, cls, contentType, charset));
            }
            if (ScriptBytecodeAdapter.compareNotEqual((Object)mapperType, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[6].call(objectMapperConfig))) {
                ObjectMapperType mapperTypeToUse = (ObjectMapperType)((Object)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual((Object)mapperType, null) ? callSiteArray[7].call(objectMapperConfig) : mapperType, ObjectMapperType.class));
                return (T)callSiteArray[8].callStatic(ObjectMapping.class, deserializationCtx, (Object)mapperTypeToUse, objectMapperConfig);
            }
        } else {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[9].call(objectMapperConfig))) {
                return (T)callSiteArray[10].call(callSiteArray[11].call(objectMapperConfig), callSiteArray[12].callStatic(ObjectMapping.class, response, cls, contentType, charset));
            }
            if (ScriptBytecodeAdapter.compareNotEqual((Object)mapperType, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[13].call(objectMapperConfig))) {
                ObjectMapperType mapperTypeToUse = (ObjectMapperType)((Object)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual((Object)mapperType, null) ? callSiteArray[14].call(objectMapperConfig) : mapperType, ObjectMapperType.class));
                return (T)callSiteArray[15].callStatic(ObjectMapping.class, deserializationCtx, (Object)mapperTypeToUse, objectMapperConfig);
            }
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[16].callStatic(StringUtils.class, contentType, "json"))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[17].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[18].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[19].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[20].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[21].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[22].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[23].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[24].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[25].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[26].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[27].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[28].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[29].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[30].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[31].call(objectMapperConfig));
                }
                throw (Throwable)callSiteArray[32].callConstructor(IllegalStateException.class, "Cannot parse object because no JSON deserializer found in classpath. Please put either Jackson (Databind) or Gson in the classpath.");
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[33].callStatic(StringUtils.class, contentType, "xml"))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[34].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[35].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[36].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[37].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[38].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[39].call(objectMapperConfig));
                }
                throw (Throwable)callSiteArray[40].callConstructor(IllegalStateException.class, "Cannot parse object because no XML deserializer found in classpath. Please put a JAXB compliant object mapper in classpath.");
            }
            if (ScriptBytecodeAdapter.compareNotEqual(defaultContentType, null)) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[41].callStatic(StringUtils.class, defaultContentType, "json"))) {
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[42].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[43].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[44].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[45].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[46].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[47].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[48].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[49].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[50].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[51].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[52].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[53].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[54].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[55].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[56].call(objectMapperConfig));
                    }
                } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[57].callStatic(StringUtils.class, defaultContentType, "xml"))) {
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[58].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[59].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[60].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[61].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[62].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[63].call(objectMapperConfig));
                    }
                }
            }
        } else {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[64].callStatic(StringUtils.class, contentType, "json"))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[65].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[66].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[67].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[68].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[69].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[70].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[71].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[72].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[73].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[74].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[75].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[76].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[77].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[78].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[79].call(objectMapperConfig));
                }
                throw (Throwable)callSiteArray[80].callConstructor(IllegalStateException.class, "Cannot parse object because no JSON deserializer found in classpath. Please put either Jackson (Databind) or Gson in the classpath.");
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[81].callStatic(StringUtils.class, contentType, "xml"))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[82].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[83].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[84].call(objectMapperConfig));
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[85].callStatic(ObjectMapperResolver.class))) {
                    return (T)callSiteArray[86].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[87].call(objectMapperConfig));
                }
                throw (Throwable)callSiteArray[88].callConstructor(IllegalStateException.class, "Cannot parse object because no XML deserializer found in classpath. Please put a JAXB compliant object mapper in classpath.");
            }
            if (ScriptBytecodeAdapter.compareNotEqual(defaultContentType, null)) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[89].callStatic(StringUtils.class, defaultContentType, "json"))) {
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[90].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[91].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[92].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[93].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[94].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[95].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[96].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[97].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[98].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[99].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[100].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[101].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[102].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[103].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[104].call(objectMapperConfig));
                    }
                } else if (DefaultTypeTransformation.booleanUnbox(callSiteArray[105].callStatic(StringUtils.class, defaultContentType, "xml"))) {
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[106].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[107].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[108].call(objectMapperConfig));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(callSiteArray[109].callStatic(ObjectMapperResolver.class))) {
                        return (T)callSiteArray[110].callStatic(ObjectMapping.class, deserializationCtx, callSiteArray[111].call(objectMapperConfig));
                    }
                }
            }
        }
        throw (Throwable)callSiteArray[112].callConstructor(IllegalStateException.class, callSiteArray[113].call(String.class, "Cannot parse object because no supported Content-Type was specified in response. Content-Type was '%s'.", contentType));
    }

    private static <T> T deserializeWithObjectMapper(ObjectMapperDeserializationContext ctx, ObjectMapperType mapperType, ObjectMapperConfig config) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[114].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[115].callStatic(ObjectMapperResolver.class))) {
                return (T)callSiteArray[116].callStatic(ObjectMapping.class, ctx, callSiteArray[117].call(config));
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[118].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[119].callStatic(ObjectMapperResolver.class))) {
                return (T)callSiteArray[120].callStatic(ObjectMapping.class, ctx, callSiteArray[121].call(config));
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[122].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[123].callStatic(ObjectMapperResolver.class))) {
                return (T)callSiteArray[124].callStatic(ObjectMapping.class, ctx, callSiteArray[125].call(config));
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[126].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[127].callStatic(ObjectMapperResolver.class))) {
                return (T)callSiteArray[128].callStatic(ObjectMapping.class, ctx, callSiteArray[129].call(config));
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[130].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[131].callStatic(ObjectMapperResolver.class))) {
                return (T)callSiteArray[132].callStatic(ObjectMapping.class, ctx, callSiteArray[133].call(config));
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[134].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[135].callStatic(ObjectMapperResolver.class))) {
                return (T)callSiteArray[136].callStatic(ObjectMapping.class, ctx, callSiteArray[137].call(config));
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[138].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[139].callStatic(ObjectMapperResolver.class))) {
                return (T)callSiteArray[140].callStatic(ObjectMapping.class, ctx, callSiteArray[141].call(config));
            }
            Object lowerCase = callSiteArray[142].call(callSiteArray[143].call((Object)mapperType));
            throw (Throwable)callSiteArray[144].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{mapperType, lowerCase}, new String[]{"Cannot map response body with mapper ", " because ", " doesn't exist in the classpath."}));
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[145].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[146].callStatic(ObjectMapperResolver.class))) {
            return (T)callSiteArray[147].callStatic(ObjectMapping.class, ctx, callSiteArray[148].call(config));
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[149].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[150].callStatic(ObjectMapperResolver.class))) {
            return (T)callSiteArray[151].callStatic(ObjectMapping.class, ctx, callSiteArray[152].call(config));
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[153].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[154].callStatic(ObjectMapperResolver.class))) {
            return (T)callSiteArray[155].callStatic(ObjectMapping.class, ctx, callSiteArray[156].call(config));
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[157].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[158].callStatic(ObjectMapperResolver.class))) {
            return (T)callSiteArray[159].callStatic(ObjectMapping.class, ctx, callSiteArray[160].call(config));
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[161].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[162].callStatic(ObjectMapperResolver.class))) {
            return (T)callSiteArray[163].callStatic(ObjectMapping.class, ctx, callSiteArray[164].call(config));
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[165].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[166].callStatic(ObjectMapperResolver.class))) {
            return (T)callSiteArray[167].callStatic(ObjectMapping.class, ctx, callSiteArray[168].call(config));
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[169].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[170].callStatic(ObjectMapperResolver.class))) {
            return (T)callSiteArray[171].callStatic(ObjectMapping.class, ctx, callSiteArray[172].call(config));
        }
        Object lowerCase = callSiteArray[173].call(callSiteArray[174].call((Object)mapperType));
        throw (Throwable)callSiteArray[175].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{mapperType, lowerCase}, new String[]{"Cannot map response body with mapper ", " because ", " doesn't exist in the classpath."}));
    }

    public static String serialize(Object object, String contentType, String charset, ObjectMapperType mapperType, ObjectMapperConfig config, EncoderConfig encoderConfig) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        callSiteArray[176].callStatic(AssertParameter.class, object, "String to serialize");
        callSiteArray[177].callStatic(AssertParameter.class, config, "Object mapper configuration not found, cannot serialize object.");
        callSiteArray[178].callStatic(AssertParameter.class, encoderConfig, "Encoder configuration not found, cannot serialize object.");
        Object serializationCtx = callSiteArray[179].callStatic(ObjectMapping.class, object, contentType, charset);
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[180].call(config))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[181].call(callSiteArray[182].call(config), callSiteArray[183].callStatic(ObjectMapping.class, object, contentType, charset)), String.class);
            }
            if (ScriptBytecodeAdapter.compareNotEqual((Object)mapperType, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[184].call(config))) {
                ObjectMapperType objectMapperType = mapperType;
                ObjectMapperType objectMapperType2 = DefaultTypeTransformation.booleanUnbox((Object)objectMapperType) ? objectMapperType : callSiteArray[185].call(config);
                mapperType = (ObjectMapperType)((Object)ScriptBytecodeAdapter.castToType((Object)objectMapperType2, ObjectMapperType.class));
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[186].callStatic(ObjectMapping.class, serializationCtx, (Object)mapperType, config), String.class);
            }
        } else {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[187].call(config))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[188].call(callSiteArray[189].call(config), callSiteArray[190].callStatic(ObjectMapping.class, object, contentType, charset)), String.class);
            }
            if (ScriptBytecodeAdapter.compareNotEqual((Object)mapperType, null) || DefaultTypeTransformation.booleanUnbox(callSiteArray[191].call(config))) {
                ObjectMapperType objectMapperType = mapperType;
                ObjectMapperType objectMapperType3 = DefaultTypeTransformation.booleanUnbox((Object)objectMapperType) ? objectMapperType : callSiteArray[192].call(config);
                mapperType = (ObjectMapperType)((Object)ScriptBytecodeAdapter.castToType((Object)objectMapperType3, ObjectMapperType.class));
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[193].callStatic(ObjectMapping.class, serializationCtx, (Object)mapperType, config), String.class);
            }
        }
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual(contentType, null) || ScriptBytecodeAdapter.compareEqual(contentType, callSiteArray[194].call(callSiteArray[195].callGetProperty(ContentType.class)))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[196].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[197].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[198].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[199].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[200].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[201].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[202].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[203].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[204].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[205].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[206].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[207].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[208].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[209].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[210].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[211].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[212].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[213].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[214].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[215].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[216].call(config)), String.class);
                }
                throw (Throwable)callSiteArray[217].callConstructor(IllegalArgumentException.class, "Cannot serialize because no JSON or XML serializer found in classpath.");
            }
            Object ct = callSiteArray[218].call(contentType);
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[219].callStatic(StringUtils.class, ct, "json")) || ScriptBytecodeAdapter.compareEqual(callSiteArray[220].call(callSiteArray[221].call(encoderConfig), callSiteArray[222].call(ContentTypeExtractor.class, ct)), callSiteArray[223].callGetProperty(ContentType.class))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[224].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[225].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[226].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[227].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[228].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[229].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[230].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[231].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[232].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[233].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[234].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[235].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[236].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[237].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[238].call(config)), String.class);
                }
                throw (Throwable)callSiteArray[239].callConstructor(IllegalStateException.class, "Cannot serialize object because no JSON serializer found in classpath. Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath.");
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[240].callStatic(StringUtils.class, ct, "xml")) || ScriptBytecodeAdapter.compareEqual(callSiteArray[241].call(callSiteArray[242].call(encoderConfig), callSiteArray[243].call(ContentTypeExtractor.class, ct)), callSiteArray[244].callGetProperty(ContentType.class))) {
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[245].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[246].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[247].call(config)), String.class);
                }
                if (DefaultTypeTransformation.booleanUnbox(callSiteArray[248].callStatic(ObjectMapperResolver.class))) {
                    return (String)ScriptBytecodeAdapter.castToType(callSiteArray[249].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[250].call(config)), String.class);
                }
                throw (Throwable)callSiteArray[251].callConstructor(IllegalStateException.class, "Cannot serialize object because no XML serializer found in classpath. Please put a JAXB or JakartaEE compliant object mapper in classpath.");
            }
            Object errorMessage = new GStringImpl(new Object[]{contentType}, new String[]{"Cannot serialize because cannot determine how to serialize content-type ", ""});
            Object encoderType = callSiteArray[252].call(callSiteArray[253].call(encoderConfig), callSiteArray[254].call(ContentTypeExtractor.class, ct));
            if (DefaultTypeTransformation.booleanUnbox(encoderType)) {
                Object object2;
                errorMessage = object2 = callSiteArray[255].call(errorMessage, new GStringImpl(new Object[]{callSiteArray[256].call(encoderType)}, new String[]{" as ", " (no serializer supports this format)"}));
            }
            throw (Throwable)callSiteArray[257].callConstructor(IllegalArgumentException.class, errorMessage);
        }
        if (ScriptBytecodeAdapter.compareEqual(contentType, null) || ScriptBytecodeAdapter.compareEqual(contentType, callSiteArray[258].call(callSiteArray[259].callGetProperty(ContentType.class)))) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[260].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[261].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[262].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[263].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[264].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[265].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[266].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[267].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[268].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[269].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[270].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[271].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[272].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[273].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[274].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[275].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[276].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[277].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[278].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[279].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[280].call(config)), String.class);
            }
            throw (Throwable)callSiteArray[281].callConstructor(IllegalArgumentException.class, "Cannot serialize because no JSON or XML serializer found in classpath.");
        }
        Object ct = callSiteArray[282].call(contentType);
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[283].callStatic(StringUtils.class, ct, "json")) || ScriptBytecodeAdapter.compareEqual(callSiteArray[284].call(callSiteArray[285].call(encoderConfig), callSiteArray[286].call(ContentTypeExtractor.class, ct)), callSiteArray[287].callGetProperty(ContentType.class))) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[288].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[289].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[290].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[291].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[292].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[293].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[294].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[295].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[296].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[297].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[298].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[299].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[300].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[301].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[302].call(config)), String.class);
            }
            throw (Throwable)callSiteArray[303].callConstructor(IllegalStateException.class, "Cannot serialize object because no JSON serializer found in classpath. Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath.");
        }
        if (DefaultTypeTransformation.booleanUnbox(callSiteArray[304].callStatic(StringUtils.class, ct, "xml")) || ScriptBytecodeAdapter.compareEqual(callSiteArray[305].call(callSiteArray[306].call(encoderConfig), callSiteArray[307].call(ContentTypeExtractor.class, ct)), callSiteArray[308].callGetProperty(ContentType.class))) {
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[309].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[310].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[311].call(config)), String.class);
            }
            if (DefaultTypeTransformation.booleanUnbox(callSiteArray[312].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[313].callStatic(ObjectMapping.class, serializationCtx, callSiteArray[314].call(config)), String.class);
            }
            throw (Throwable)callSiteArray[315].callConstructor(IllegalStateException.class, "Cannot serialize object because no XML serializer found in classpath. Please put a JAXB or JakartaEE compliant object mapper in classpath.");
        }
        Object errorMessage = new GStringImpl(new Object[]{contentType}, new String[]{"Cannot serialize because cannot determine how to serialize content-type ", ""});
        Object encoderType = callSiteArray[316].call(callSiteArray[317].call(encoderConfig), callSiteArray[318].call(ContentTypeExtractor.class, ct));
        if (DefaultTypeTransformation.booleanUnbox(encoderType)) {
            Object object3;
            errorMessage = object3 = callSiteArray[319].call(errorMessage, new GStringImpl(new Object[]{callSiteArray[320].call(encoderType)}, new String[]{" as ", " (no serializer supports this format)"}));
        }
        throw (Throwable)callSiteArray[321].callConstructor(IllegalArgumentException.class, errorMessage);
    }

    private static String serializeWithObjectMapper(ObjectMapperSerializationContext ctx, ObjectMapperType mapperType, ObjectMapperConfig config) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        if (!BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[324].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[325].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[326].callStatic(ObjectMapping.class, ctx, callSiteArray[327].call(config)), String.class);
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[328].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[329].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[330].callStatic(ObjectMapping.class, ctx, callSiteArray[331].call(config)), String.class);
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[332].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[333].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[334].callStatic(ObjectMapping.class, ctx, callSiteArray[335].call(config)), String.class);
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[336].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[337].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[338].callStatic(ObjectMapping.class, ctx, callSiteArray[339].call(config)), String.class);
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[340].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[341].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[342].callStatic(ObjectMapping.class, ctx, callSiteArray[343].call(config)), String.class);
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[344].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[345].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[346].callStatic(ObjectMapping.class, ctx, callSiteArray[347].call(config)), String.class);
            }
            if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[348].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[349].callStatic(ObjectMapperResolver.class))) {
                return (String)ScriptBytecodeAdapter.castToType(callSiteArray[350].callStatic(ObjectMapping.class, ctx, callSiteArray[351].call(config)), String.class);
            }
            Object lowerCase = callSiteArray[352].call(callSiteArray[353].call((Object)mapperType));
            throw (Throwable)callSiteArray[354].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{mapperType, lowerCase}, new String[]{"Cannot serialize object with mapper ", " because ", " doesn't exist in the classpath."}));
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[355].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[356].callStatic(ObjectMapperResolver.class))) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[357].callStatic(ObjectMapping.class, ctx, callSiteArray[358].call(config)), String.class);
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[359].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[360].callStatic(ObjectMapperResolver.class))) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[361].callStatic(ObjectMapping.class, ctx, callSiteArray[362].call(config)), String.class);
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[363].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[364].callStatic(ObjectMapperResolver.class))) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[365].callStatic(ObjectMapping.class, ctx, callSiteArray[366].call(config)), String.class);
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[367].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[368].callStatic(ObjectMapperResolver.class))) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[369].callStatic(ObjectMapping.class, ctx, callSiteArray[370].call(config)), String.class);
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[371].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[372].callStatic(ObjectMapperResolver.class))) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[373].callStatic(ObjectMapping.class, ctx, callSiteArray[374].call(config)), String.class);
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[375].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[376].callStatic(ObjectMapperResolver.class))) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[377].callStatic(ObjectMapping.class, ctx, callSiteArray[378].call(config)), String.class);
        }
        if (ScriptBytecodeAdapter.compareEqual((Object)mapperType, callSiteArray[379].callGetProperty(ObjectMapperType.class)) && DefaultTypeTransformation.booleanUnbox(callSiteArray[380].callStatic(ObjectMapperResolver.class))) {
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[381].callStatic(ObjectMapping.class, ctx, callSiteArray[382].call(config)), String.class);
        }
        Object lowerCase = callSiteArray[383].call(callSiteArray[384].call((Object)mapperType));
        throw (Throwable)callSiteArray[385].callConstructor(IllegalArgumentException.class, new GStringImpl(new Object[]{mapperType, lowerCase}, new String[]{"Cannot serialize object with mapper ", " because ", " doesn't exist in the classpath."}));
    }

    private static String serializeWithGson(ObjectMapperSerializationContext ctx, GsonObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[386].call(callSiteArray[387].callConstructor(GsonMapper.class, factory), ctx), String.class);
    }

    private static String serializeWithJackson1(ObjectMapperSerializationContext ctx, Jackson1ObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[388].call(callSiteArray[389].callConstructor(Jackson1Mapper.class, factory), ctx), String.class);
    }

    private static String serializeWithJackson2(ObjectMapperSerializationContext ctx, Jackson2ObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[390].call(callSiteArray[391].callConstructor(Jackson2Mapper.class, factory), ctx), String.class);
    }

    private static String serializeWithJaxb(ObjectMapperSerializationContext ctx, JAXBObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[392].call(callSiteArray[393].callConstructor(JaxbMapper.class, factory), ctx), String.class);
    }

    private static String serializeWithJakartaEE(ObjectMapperSerializationContext ctx, JakartaEEObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[394].call(callSiteArray[395].callConstructor(JakartaEEMapper.class, factory), ctx), String.class);
    }

    private static String serializeWithJohnzon(ObjectMapperSerializationContext ctx, JohnzonObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[396].call(callSiteArray[397].callConstructor(JohnzonMapper.class, factory), ctx), String.class);
    }

    private static String serializeWithJsonb(ObjectMapperSerializationContext ctx, JsonbObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(callSiteArray[398].call(callSiteArray[399].callConstructor(JsonbMapper.class, factory), ctx), String.class);
    }

    private static Object parseWithJaxb(ObjectMapperDeserializationContext ctx, JAXBObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return callSiteArray[400].call(callSiteArray[401].callConstructor(JaxbMapper.class, factory), ctx);
    }

    private static Object parseWithJakartaEE(ObjectMapperDeserializationContext ctx, JakartaEEObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return callSiteArray[402].call(callSiteArray[403].callConstructor(JakartaEEMapper.class, factory), ctx);
    }

    private static Object parseWithGson(ObjectMapperDeserializationContext ctx, GsonObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return callSiteArray[404].call(callSiteArray[405].callConstructor(GsonMapper.class, factory), ctx);
    }

    public static Object parseWithJackson1(ObjectMapperDeserializationContext ctx, Jackson1ObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return callSiteArray[406].call(callSiteArray[407].callConstructor(Jackson1Mapper.class, factory), ctx);
    }

    public static Object parseWithJackson2(ObjectMapperDeserializationContext ctx, Jackson2ObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return callSiteArray[408].call(callSiteArray[409].callConstructor(Jackson2Mapper.class, factory), ctx);
    }

    public static Object parseWithJohnzon(ObjectMapperDeserializationContext ctx, JohnzonObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return callSiteArray[410].call(callSiteArray[411].callConstructor(JohnzonMapper.class, factory), ctx);
    }

    public static Object parseWithJsonb(ObjectMapperDeserializationContext ctx, JsonbObjectMapperFactory factory) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return callSiteArray[412].call(callSiteArray[413].callConstructor(JsonbMapper.class, factory), ctx);
    }

    private static ObjectMapperDeserializationContext deserializationContext(ResponseBodyData responseData, Type cls, Object contentType, Object charset) {
        Reference<ResponseBodyData> responseData2 = new Reference<ResponseBodyData>(responseData);
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        Object ctx = callSiteArray[414].callConstructor(ObjectMapperDeserializationContextImpl.class);
        Type type = cls;
        ScriptBytecodeAdapter.setProperty(type, null, ctx, "type");
        Object object = charset;
        ScriptBytecodeAdapter.setProperty(object, null, ctx, "charset");
        Object object2 = contentType;
        ScriptBytecodeAdapter.setProperty(object2, null, ctx, "contentType");
        1 var10_10 = new 1(ObjectMapping.class, responseData2);
        ScriptBytecodeAdapter.setProperty(var10_10, null, ctx, "dataToDeserialize");
        return (ObjectMapperDeserializationContext)ScriptBytecodeAdapter.castToType(ctx, ObjectMapperDeserializationContext.class);
    }

    private static ObjectMapperSerializationContext serializationContext(Object object, Object contentType, Object charset) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        Object ctx = callSiteArray[415].callConstructor(ObjectMapperSerializationContextImpl.class);
        Object object2 = charset;
        ScriptBytecodeAdapter.setProperty(object2, null, ctx, "charset");
        Object object3 = contentType;
        ScriptBytecodeAdapter.setProperty(object3, null, ctx, "contentType");
        Object object4 = object;
        ScriptBytecodeAdapter.setProperty(object4, null, ctx, "object");
        return (ObjectMapperSerializationContext)ScriptBytecodeAdapter.castToType(ctx, ObjectMapperSerializationContext.class);
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ObjectMapping.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        Object object = value;
        ScriptBytecodeAdapter.setGroovyObjectField(object, ObjectMapping.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectField(ObjectMapping.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ObjectMapping.class) {
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
        CallSite[] callSiteArray = ObjectMapping.$getCallSiteArray();
        $callSiteArray = null;
    }

    static {
        long l;
        long l2;
        ObjectMapping.__$swapInit();
        __timeStamp__239_neverHappen1702030729372 = l2 = 0L;
        __timeStamp = l = 1702030729372L;
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
        stringArray[0] = "notNull";
        stringArray[1] = "deserializationContext";
        stringArray[2] = "hasDefaultObjectMapper";
        stringArray[3] = "deserialize";
        stringArray[4] = "defaultObjectMapper";
        stringArray[5] = "deserializationContext";
        stringArray[6] = "hasDefaultObjectMapperType";
        stringArray[7] = "defaultObjectMapperType";
        stringArray[8] = "deserializeWithObjectMapper";
        stringArray[9] = "hasDefaultObjectMapper";
        stringArray[10] = "deserialize";
        stringArray[11] = "defaultObjectMapper";
        stringArray[12] = "deserializationContext";
        stringArray[13] = "hasDefaultObjectMapperType";
        stringArray[14] = "defaultObjectMapperType";
        stringArray[15] = "deserializeWithObjectMapper";
        stringArray[16] = "containsIgnoreCase";
        stringArray[17] = "isJackson2InClassPath";
        stringArray[18] = "parseWithJackson2";
        stringArray[19] = "jackson2ObjectMapperFactory";
        stringArray[20] = "isJackson1InClassPath";
        stringArray[21] = "parseWithJackson1";
        stringArray[22] = "jackson1ObjectMapperFactory";
        stringArray[23] = "isGsonInClassPath";
        stringArray[24] = "parseWithGson";
        stringArray[25] = "gsonObjectMapperFactory";
        stringArray[26] = "isJohnzonInClassPath";
        stringArray[27] = "parseWithJohnzon";
        stringArray[28] = "johnzonObjectMapperFactory";
        stringArray[29] = "isYassonInClassPath";
        stringArray[30] = "parseWithJsonb";
        stringArray[31] = "jsonbObjectMapperFactory";
        stringArray[32] = "<$constructor$>";
        stringArray[33] = "containsIgnoreCase";
        stringArray[34] = "isJakartaEEInClassPath";
        stringArray[35] = "parseWithJakartaEE";
        stringArray[36] = "jakartaEEObjectMapperFactory";
        stringArray[37] = "isJAXBInClassPath";
        stringArray[38] = "parseWithJaxb";
        stringArray[39] = "jaxbObjectMapperFactory";
        stringArray[40] = "<$constructor$>";
        stringArray[41] = "containsIgnoreCase";
        stringArray[42] = "isJackson2InClassPath";
        stringArray[43] = "parseWithJackson2";
        stringArray[44] = "jackson2ObjectMapperFactory";
        stringArray[45] = "isJackson1InClassPath";
        stringArray[46] = "parseWithJackson1";
        stringArray[47] = "jackson1ObjectMapperFactory";
        stringArray[48] = "isGsonInClassPath";
        stringArray[49] = "parseWithGson";
        stringArray[50] = "gsonObjectMapperFactory";
        stringArray[51] = "isJohnzonInClassPath";
        stringArray[52] = "parseWithJohnzon";
        stringArray[53] = "johnzonObjectMapperFactory";
        stringArray[54] = "isYassonInClassPath";
        stringArray[55] = "parseWithJsonb";
        stringArray[56] = "jsonbObjectMapperFactory";
        stringArray[57] = "containsIgnoreCase";
        stringArray[58] = "isJakartaEEInClassPath";
        stringArray[59] = "parseWithJakartaEE";
        stringArray[60] = "jakartaEEObjectMapperFactory";
        stringArray[61] = "isJAXBInClassPath";
        stringArray[62] = "parseWithJaxb";
        stringArray[63] = "jaxbObjectMapperFactory";
        stringArray[64] = "containsIgnoreCase";
        stringArray[65] = "isJackson2InClassPath";
        stringArray[66] = "parseWithJackson2";
        stringArray[67] = "jackson2ObjectMapperFactory";
        stringArray[68] = "isJackson1InClassPath";
        stringArray[69] = "parseWithJackson1";
        stringArray[70] = "jackson1ObjectMapperFactory";
        stringArray[71] = "isGsonInClassPath";
        stringArray[72] = "parseWithGson";
        stringArray[73] = "gsonObjectMapperFactory";
        stringArray[74] = "isJohnzonInClassPath";
        stringArray[75] = "parseWithJohnzon";
        stringArray[76] = "johnzonObjectMapperFactory";
        stringArray[77] = "isYassonInClassPath";
        stringArray[78] = "parseWithJsonb";
        stringArray[79] = "jsonbObjectMapperFactory";
        stringArray[80] = "<$constructor$>";
        stringArray[81] = "containsIgnoreCase";
        stringArray[82] = "isJakartaEEInClassPath";
        stringArray[83] = "parseWithJakartaEE";
        stringArray[84] = "jakartaEEObjectMapperFactory";
        stringArray[85] = "isJAXBInClassPath";
        stringArray[86] = "parseWithJaxb";
        stringArray[87] = "jaxbObjectMapperFactory";
        stringArray[88] = "<$constructor$>";
        stringArray[89] = "containsIgnoreCase";
        stringArray[90] = "isJackson2InClassPath";
        stringArray[91] = "parseWithJackson2";
        stringArray[92] = "jackson2ObjectMapperFactory";
        stringArray[93] = "isJackson1InClassPath";
        stringArray[94] = "parseWithJackson1";
        stringArray[95] = "jackson1ObjectMapperFactory";
        stringArray[96] = "isGsonInClassPath";
        stringArray[97] = "parseWithGson";
        stringArray[98] = "gsonObjectMapperFactory";
        stringArray[99] = "isJohnzonInClassPath";
        stringArray[100] = "parseWithJohnzon";
        stringArray[101] = "johnzonObjectMapperFactory";
        stringArray[102] = "isYassonInClassPath";
        stringArray[103] = "parseWithJsonb";
        stringArray[104] = "jsonbObjectMapperFactory";
        stringArray[105] = "containsIgnoreCase";
        stringArray[106] = "isJakartaEEInClassPath";
        stringArray[107] = "parseWithJakartaEE";
        stringArray[108] = "jakartaEEObjectMapperFactory";
        stringArray[109] = "isJAXBInClassPath";
        stringArray[110] = "parseWithJaxb";
        stringArray[111] = "jaxbObjectMapperFactory";
        stringArray[112] = "<$constructor$>";
        stringArray[113] = "format";
        stringArray[114] = "JACKSON_2";
        stringArray[115] = "isJackson2InClassPath";
        stringArray[116] = "parseWithJackson2";
        stringArray[117] = "jackson2ObjectMapperFactory";
        stringArray[118] = "JACKSON_1";
        stringArray[119] = "isJackson1InClassPath";
        stringArray[120] = "parseWithJackson1";
        stringArray[121] = "jackson1ObjectMapperFactory";
        stringArray[122] = "GSON";
        stringArray[123] = "isGsonInClassPath";
        stringArray[124] = "parseWithGson";
        stringArray[125] = "gsonObjectMapperFactory";
        stringArray[126] = "JAKARTA_EE";
        stringArray[127] = "isJakartaEEInClassPath";
        stringArray[128] = "parseWithJakartaEE";
        stringArray[129] = "jakartaEEObjectMapperFactory";
        stringArray[130] = "JAXB";
        stringArray[131] = "isJAXBInClassPath";
        stringArray[132] = "parseWithJaxb";
        stringArray[133] = "jaxbObjectMapperFactory";
        stringArray[134] = "JOHNZON";
        stringArray[135] = "isJohnzonInClassPath";
        stringArray[136] = "parseWithJohnzon";
        stringArray[137] = "johnzonObjectMapperFactory";
        stringArray[138] = "JSONB";
        stringArray[139] = "isYassonInClassPath";
        stringArray[140] = "parseWithJsonb";
        stringArray[141] = "jsonbObjectMapperFactory";
        stringArray[142] = "toLowerCase";
        stringArray[143] = "toString";
        stringArray[144] = "<$constructor$>";
        stringArray[145] = "JACKSON_2";
        stringArray[146] = "isJackson2InClassPath";
        stringArray[147] = "parseWithJackson2";
        stringArray[148] = "jackson2ObjectMapperFactory";
        stringArray[149] = "JACKSON_1";
        stringArray[150] = "isJackson1InClassPath";
        stringArray[151] = "parseWithJackson1";
        stringArray[152] = "jackson1ObjectMapperFactory";
        stringArray[153] = "GSON";
        stringArray[154] = "isGsonInClassPath";
        stringArray[155] = "parseWithGson";
        stringArray[156] = "gsonObjectMapperFactory";
        stringArray[157] = "JAKARTA_EE";
        stringArray[158] = "isJakartaEEInClassPath";
        stringArray[159] = "parseWithJakartaEE";
        stringArray[160] = "jakartaEEObjectMapperFactory";
        stringArray[161] = "JAXB";
        stringArray[162] = "isJAXBInClassPath";
        stringArray[163] = "parseWithJaxb";
        stringArray[164] = "jaxbObjectMapperFactory";
        stringArray[165] = "JOHNZON";
        stringArray[166] = "isJohnzonInClassPath";
        stringArray[167] = "parseWithJohnzon";
        stringArray[168] = "johnzonObjectMapperFactory";
        stringArray[169] = "JSONB";
        stringArray[170] = "isYassonInClassPath";
        stringArray[171] = "parseWithJsonb";
        stringArray[172] = "jsonbObjectMapperFactory";
        stringArray[173] = "toLowerCase";
        stringArray[174] = "toString";
        stringArray[175] = "<$constructor$>";
        stringArray[176] = "notNull";
        stringArray[177] = "notNull";
        stringArray[178] = "notNull";
        stringArray[179] = "serializationContext";
        stringArray[180] = "hasDefaultObjectMapper";
        stringArray[181] = "serialize";
        stringArray[182] = "defaultObjectMapper";
        stringArray[183] = "serializationContext";
        stringArray[184] = "hasDefaultObjectMapperType";
        stringArray[185] = "defaultObjectMapperType";
        stringArray[186] = "serializeWithObjectMapper";
        stringArray[187] = "hasDefaultObjectMapper";
        stringArray[188] = "serialize";
        stringArray[189] = "defaultObjectMapper";
        stringArray[190] = "serializationContext";
        stringArray[191] = "hasDefaultObjectMapperType";
        stringArray[192] = "defaultObjectMapperType";
        stringArray[193] = "serializeWithObjectMapper";
        stringArray[194] = "toString";
        stringArray[195] = "ANY";
        stringArray[196] = "isJackson2InClassPath";
        stringArray[197] = "serializeWithJackson2";
        stringArray[198] = "jackson2ObjectMapperFactory";
        stringArray[199] = "isJackson1InClassPath";
        stringArray[200] = "serializeWithJackson1";
        stringArray[201] = "jackson1ObjectMapperFactory";
        stringArray[202] = "isGsonInClassPath";
        stringArray[203] = "serializeWithGson";
        stringArray[204] = "gsonObjectMapperFactory";
        stringArray[205] = "isJakartaEEInClassPath";
        stringArray[206] = "serializeWithJakartaEE";
        stringArray[207] = "jakartaEEObjectMapperFactory";
        stringArray[208] = "isJAXBInClassPath";
        stringArray[209] = "serializeWithJaxb";
        stringArray[210] = "jaxbObjectMapperFactory";
        stringArray[211] = "isJohnzonInClassPath";
        stringArray[212] = "serializeWithJohnzon";
        stringArray[213] = "johnzonObjectMapperFactory";
        stringArray[214] = "isYassonInClassPath";
        stringArray[215] = "serializeWithJsonb";
        stringArray[216] = "jsonbObjectMapperFactory";
        stringArray[217] = "<$constructor$>";
        stringArray[218] = "toLowerCase";
        stringArray[219] = "containsIgnoreCase";
        stringArray[220] = "get";
        stringArray[221] = "contentEncoders";
        stringArray[222] = "getContentTypeWithoutCharset";
        stringArray[223] = "JSON";
        stringArray[224] = "isJackson2InClassPath";
        stringArray[225] = "serializeWithJackson2";
        stringArray[226] = "jackson2ObjectMapperFactory";
        stringArray[227] = "isJackson1InClassPath";
        stringArray[228] = "serializeWithJackson1";
        stringArray[229] = "jackson1ObjectMapperFactory";
        stringArray[230] = "isGsonInClassPath";
        stringArray[231] = "serializeWithGson";
        stringArray[232] = "gsonObjectMapperFactory";
        stringArray[233] = "isJohnzonInClassPath";
        stringArray[234] = "serializeWithJohnzon";
        stringArray[235] = "johnzonObjectMapperFactory";
        stringArray[236] = "isYassonInClassPath";
        stringArray[237] = "serializeWithJsonb";
        stringArray[238] = "jsonbObjectMapperFactory";
        stringArray[239] = "<$constructor$>";
        stringArray[240] = "containsIgnoreCase";
        stringArray[241] = "get";
        stringArray[242] = "contentEncoders";
        stringArray[243] = "getContentTypeWithoutCharset";
        stringArray[244] = "XML";
        stringArray[245] = "isJakartaEEInClassPath";
        stringArray[246] = "serializeWithJakartaEE";
        stringArray[247] = "jakartaEEObjectMapperFactory";
        stringArray[248] = "isJAXBInClassPath";
        stringArray[249] = "serializeWithJaxb";
        stringArray[250] = "jaxbObjectMapperFactory";
        stringArray[251] = "<$constructor$>";
        stringArray[252] = "get";
        stringArray[253] = "contentEncoders";
        stringArray[254] = "getContentTypeWithoutCharset";
        stringArray[255] = "plus";
        stringArray[256] = "name";
        stringArray[257] = "<$constructor$>";
        stringArray[258] = "toString";
        stringArray[259] = "ANY";
        stringArray[260] = "isJackson2InClassPath";
        stringArray[261] = "serializeWithJackson2";
        stringArray[262] = "jackson2ObjectMapperFactory";
        stringArray[263] = "isJackson1InClassPath";
        stringArray[264] = "serializeWithJackson1";
        stringArray[265] = "jackson1ObjectMapperFactory";
        stringArray[266] = "isGsonInClassPath";
        stringArray[267] = "serializeWithGson";
        stringArray[268] = "gsonObjectMapperFactory";
        stringArray[269] = "isJakartaEEInClassPath";
        stringArray[270] = "serializeWithJakartaEE";
        stringArray[271] = "jakartaEEObjectMapperFactory";
        stringArray[272] = "isJAXBInClassPath";
        stringArray[273] = "serializeWithJaxb";
        stringArray[274] = "jaxbObjectMapperFactory";
        stringArray[275] = "isJohnzonInClassPath";
        stringArray[276] = "serializeWithJohnzon";
        stringArray[277] = "johnzonObjectMapperFactory";
        stringArray[278] = "isYassonInClassPath";
        stringArray[279] = "serializeWithJsonb";
        stringArray[280] = "jsonbObjectMapperFactory";
        stringArray[281] = "<$constructor$>";
        stringArray[282] = "toLowerCase";
        stringArray[283] = "containsIgnoreCase";
        stringArray[284] = "get";
        stringArray[285] = "contentEncoders";
        stringArray[286] = "getContentTypeWithoutCharset";
        stringArray[287] = "JSON";
        stringArray[288] = "isJackson2InClassPath";
        stringArray[289] = "serializeWithJackson2";
        stringArray[290] = "jackson2ObjectMapperFactory";
        stringArray[291] = "isJackson1InClassPath";
        stringArray[292] = "serializeWithJackson1";
        stringArray[293] = "jackson1ObjectMapperFactory";
        stringArray[294] = "isGsonInClassPath";
        stringArray[295] = "serializeWithGson";
        stringArray[296] = "gsonObjectMapperFactory";
        stringArray[297] = "isJohnzonInClassPath";
        stringArray[298] = "serializeWithJohnzon";
        stringArray[299] = "johnzonObjectMapperFactory";
        stringArray[300] = "isYassonInClassPath";
        stringArray[301] = "serializeWithJsonb";
        stringArray[302] = "jsonbObjectMapperFactory";
        stringArray[303] = "<$constructor$>";
        stringArray[304] = "containsIgnoreCase";
        stringArray[305] = "get";
        stringArray[306] = "contentEncoders";
        stringArray[307] = "getContentTypeWithoutCharset";
        stringArray[308] = "XML";
        stringArray[309] = "isJakartaEEInClassPath";
        stringArray[310] = "serializeWithJakartaEE";
        stringArray[311] = "jakartaEEObjectMapperFactory";
        stringArray[312] = "isJAXBInClassPath";
        stringArray[313] = "serializeWithJaxb";
        stringArray[314] = "jaxbObjectMapperFactory";
        stringArray[315] = "<$constructor$>";
        stringArray[316] = "get";
        stringArray[317] = "contentEncoders";
        stringArray[318] = "getContentTypeWithoutCharset";
        stringArray[319] = "plus";
        stringArray[320] = "name";
        stringArray[321] = "<$constructor$>";
        stringArray[322] = "serializeWithJaxb";
        stringArray[323] = "jaxbObjectMapperFactory";
        stringArray[324] = "JACKSON_2";
        stringArray[325] = "isJackson2InClassPath";
        stringArray[326] = "serializeWithJackson2";
        stringArray[327] = "jackson2ObjectMapperFactory";
        stringArray[328] = "JACKSON_1";
        stringArray[329] = "isJackson1InClassPath";
        stringArray[330] = "serializeWithJackson1";
        stringArray[331] = "jackson1ObjectMapperFactory";
        stringArray[332] = "GSON";
        stringArray[333] = "isGsonInClassPath";
        stringArray[334] = "serializeWithGson";
        stringArray[335] = "gsonObjectMapperFactory";
        stringArray[336] = "JAKARTA_EE";
        stringArray[337] = "isJakartaEEInClassPath";
        stringArray[338] = "serializeWithJakartaEE";
        stringArray[339] = "jakartaEEObjectMapperFactory";
        stringArray[340] = "JAXB";
        stringArray[341] = "isJAXBInClassPath";
        stringArray[342] = "serializeWithJaxb";
        stringArray[343] = "jaxbObjectMapperFactory";
        stringArray[344] = "JOHNZON";
        stringArray[345] = "isJohnzonInClassPath";
        stringArray[346] = "serializeWithJohnzon";
        stringArray[347] = "johnzonObjectMapperFactory";
        stringArray[348] = "JSONB";
        stringArray[349] = "isYassonInClassPath";
        stringArray[350] = "serializeWithJsonb";
        stringArray[351] = "jsonbObjectMapperFactory";
        stringArray[352] = "toLowerCase";
        stringArray[353] = "toString";
        stringArray[354] = "<$constructor$>";
        stringArray[355] = "JACKSON_2";
        stringArray[356] = "isJackson2InClassPath";
        stringArray[357] = "serializeWithJackson2";
        stringArray[358] = "jackson2ObjectMapperFactory";
        stringArray[359] = "JACKSON_1";
        stringArray[360] = "isJackson1InClassPath";
        stringArray[361] = "serializeWithJackson1";
        stringArray[362] = "jackson1ObjectMapperFactory";
        stringArray[363] = "GSON";
        stringArray[364] = "isGsonInClassPath";
        stringArray[365] = "serializeWithGson";
        stringArray[366] = "gsonObjectMapperFactory";
        stringArray[367] = "JAKARTA_EE";
        stringArray[368] = "isJakartaEEInClassPath";
        stringArray[369] = "serializeWithJakartaEE";
        stringArray[370] = "jakartaEEObjectMapperFactory";
        stringArray[371] = "JAXB";
        stringArray[372] = "isJAXBInClassPath";
        stringArray[373] = "serializeWithJaxb";
        stringArray[374] = "jaxbObjectMapperFactory";
        stringArray[375] = "JOHNZON";
        stringArray[376] = "isJohnzonInClassPath";
        stringArray[377] = "serializeWithJohnzon";
        stringArray[378] = "johnzonObjectMapperFactory";
        stringArray[379] = "JSONB";
        stringArray[380] = "isYassonInClassPath";
        stringArray[381] = "serializeWithJsonb";
        stringArray[382] = "jsonbObjectMapperFactory";
        stringArray[383] = "toLowerCase";
        stringArray[384] = "toString";
        stringArray[385] = "<$constructor$>";
        stringArray[386] = "serialize";
        stringArray[387] = "<$constructor$>";
        stringArray[388] = "serialize";
        stringArray[389] = "<$constructor$>";
        stringArray[390] = "serialize";
        stringArray[391] = "<$constructor$>";
        stringArray[392] = "serialize";
        stringArray[393] = "<$constructor$>";
        stringArray[394] = "serialize";
        stringArray[395] = "<$constructor$>";
        stringArray[396] = "serialize";
        stringArray[397] = "<$constructor$>";
        stringArray[398] = "serialize";
        stringArray[399] = "<$constructor$>";
        stringArray[400] = "deserialize";
        stringArray[401] = "<$constructor$>";
        stringArray[402] = "deserialize";
        stringArray[403] = "<$constructor$>";
        stringArray[404] = "deserialize";
        stringArray[405] = "<$constructor$>";
        stringArray[406] = "deserialize";
        stringArray[407] = "<$constructor$>";
        stringArray[408] = "deserialize";
        stringArray[409] = "<$constructor$>";
        stringArray[410] = "deserialize";
        stringArray[411] = "<$constructor$>";
        stringArray[412] = "deserialize";
        stringArray[413] = "<$constructor$>";
        stringArray[414] = "<$constructor$>";
        stringArray[415] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] stringArray = new String[416];
        ObjectMapping.$createCallSiteArray_1(stringArray);
        return new CallSiteArray(ObjectMapping.class, stringArray);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
            callSiteArray = ObjectMapping.$createCallSiteArray();
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

    public class 1
    implements DataToDeserialize,
    GroovyObject {
        public /* synthetic */ Reference responseData;
        public /* synthetic */ Class this$0;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private transient /* synthetic */ MetaClass metaClass;
        private static /* synthetic */ SoftReference $callSiteArray;

        /* synthetic */ 1(Class p0, Reference p1) {
            MetaClass metaClass;
            Reference reference;
            Class clazz;
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            this.this$0 = clazz = p0;
            this.responseData = reference = p1;
            this.metaClass = metaClass = this.$getStaticMetaClass();
        }

        public String asString() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return (String)ScriptBytecodeAdapter.castToType(callSiteArray[0].call(this.responseData.get()), String.class);
        }

        public byte[] asByteArray() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return (byte[])ScriptBytecodeAdapter.castToType(callSiteArray[1].call(this.responseData.get()), byte[].class);
        }

        public InputStream asInputStream() {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return (InputStream)ScriptBytecodeAdapter.castToType(callSiteArray[2].call(this.responseData.get()), InputStream.class);
        }

        public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodOnCurrentN(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void this$dist$set$1(String name, Object value) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            Object object = value;
            ScriptBytecodeAdapter.setGroovyObjectField(object, 1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object this$dist$get$1(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.getGroovyObjectField(1.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object methodMissing(String name, Object args) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.invokeMethodN(1.class, ObjectMapping.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
        }

        public /* synthetic */ void propertyMissing(String name, Object val) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            Object object = val;
            ScriptBytecodeAdapter.setField(object, 1.class, ObjectMapping.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        public /* synthetic */ Object propertyMissing(String name) {
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            return ScriptBytecodeAdapter.getField(1.class, ObjectMapping.class, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), String.class));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != 1.class) {
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
            CallSite[] callSiteArray = 1.$getCallSiteArray();
            $callSiteArray = null;
        }

        static {
            1.__$swapInit();
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
            stringArray[0] = "asString";
            stringArray[1] = "asByteArray";
            stringArray[2] = "asInputStream";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] stringArray = new String[3];
            1.$createCallSiteArray_1(stringArray);
            return new CallSiteArray(1.class, stringArray);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            if ($callSiteArray == null || (callSiteArray = (CallSiteArray)$callSiteArray.get()) == null) {
                callSiteArray = 1.$createCallSiteArray();
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
}

