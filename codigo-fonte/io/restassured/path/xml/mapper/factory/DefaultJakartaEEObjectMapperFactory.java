/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.xml.bind.JAXBContext
 *  jakarta.xml.bind.JAXBException
 */
package io.restassured.path.xml.mapper.factory;

import io.restassured.path.xml.mapper.factory.JakartaEEObjectMapperFactory;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import java.lang.reflect.Type;

public class DefaultJakartaEEObjectMapperFactory
implements JakartaEEObjectMapperFactory {
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

