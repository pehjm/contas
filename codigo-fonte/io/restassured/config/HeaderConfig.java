/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.internal.common.assertion.AssertParameter;
import java.util.HashMap;
import java.util.Map;

public class HeaderConfig
implements Config {
    private static final String ACCEPT_HEADER_NAME = "accept";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private final Map<String, Boolean> headersToOverwrite;
    private final boolean isUserDefined;

    public HeaderConfig() {
        this(HeaderConfig.newHashMapReturningFalseByDefault(CONTENT_TYPE_HEADER_NAME, ACCEPT_HEADER_NAME), false);
    }

    private HeaderConfig(Map<String, Boolean> headersToOverwrite, boolean isUserDefined) {
        this.headersToOverwrite = headersToOverwrite;
        this.isUserDefined = isUserDefined;
    }

    public HeaderConfig overwriteHeadersWithName(String headerName, String ... additionalHeaderNames) {
        AssertParameter.notNull(headerName, "Header name");
        Map<String, Boolean> map = HeaderConfig.newHashMapReturningFalseByDefault(headerName);
        if (additionalHeaderNames != null && additionalHeaderNames.length > 0) {
            for (String additionalHeaderName : additionalHeaderNames) {
                map.put(additionalHeaderName.toUpperCase(), true);
            }
        }
        return new HeaderConfig(map, true);
    }

    public HeaderConfig mergeHeadersWithName(String headerName, String ... additionalHeaderNames) {
        AssertParameter.notNull(headerName, "Header name");
        Map<String, Boolean> map = HeaderConfig.newHashMapReturningFalseByDefault(new String[0]);
        map.put(headerName, false);
        if (additionalHeaderNames != null && additionalHeaderNames.length > 0) {
            for (String additionalHeaderName : additionalHeaderNames) {
                map.put(additionalHeaderName.toUpperCase(), false);
            }
        }
        return new HeaderConfig(map, true);
    }

    public boolean shouldOverwriteHeaderWithName(String headerName) {
        AssertParameter.notNull(headerName, "Header name");
        return this.headersToOverwrite.get(headerName.toUpperCase());
    }

    public static HeaderConfig headerConfig() {
        return new HeaderConfig();
    }

    private static Map<String, Boolean> newHashMapReturningFalseByDefault(final String ... headerNamesToOverwrite) {
        return new HashMap<String, Boolean>(){
            {
                for (String headerName : headerNamesToOverwrite) {
                    this.put(headerName.toUpperCase(), true);
                }
            }

            @Override
            public Boolean get(Object key) {
                Boolean aBoolean = (Boolean)super.get(key);
                if (aBoolean == null) {
                    return Boolean.FALSE;
                }
                return aBoolean;
            }
        };
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserDefined;
    }
}

