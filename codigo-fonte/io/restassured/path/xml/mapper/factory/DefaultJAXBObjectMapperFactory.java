/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.xml.bind.JAXBContext
 *  javax.xml.bind.JAXBException
 */
package io.restassured.path.xml.mapper.factory;

import io.restassured.path.xml.mapper.factory.JAXBObjectMapperFactory;
import java.lang.reflect.Type;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class DefaultJAXBObjectMapperFactory
implements JAXBObjectMapperFactory {
    @Override
    public JAXBContext create(Type cls, String charset) {
        try {
            if (cls instanceof Class) {
                return JAXBContext.newInstance((Class[])new Class[]{(Class)cls});
            }
            throw new RuntimeException("JAXB does not support type" + cls);
        }
        catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}

