/*
 * Decompiled with CFR 0.152.
 */
package org.apache.xmlbeans;

import java.util.Map;
import java.util.Properties;

public class SystemProperties {
    private static Map<Object, Object> propertyH;

    public static String getProperty(String key) {
        if (propertyH == null) {
            try {
                propertyH = System.getProperties();
            }
            catch (SecurityException ex) {
                propertyH = new Properties();
                return null;
            }
        }
        return (String)propertyH.get(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String result = SystemProperties.getProperty(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    public static void setPropertyH(Map<Object, Object> aPropertyH) {
        propertyH = aPropertyH;
    }
}

