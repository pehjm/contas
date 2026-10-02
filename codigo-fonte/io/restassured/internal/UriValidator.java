/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.lang3.StringUtils;

public class UriValidator {
    public static boolean isUri(String potentialUri) {
        if (StringUtils.isBlank(potentialUri)) {
            return false;
        }
        try {
            URI uri = new URI(potentialUri);
            return uri.getScheme() != null && uri.getHost() != null;
        }
        catch (URISyntaxException e) {
            return false;
        }
    }
}

