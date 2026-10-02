/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.parsing;

import io.restassured.internal.http.ContentTypeExtractor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public enum Parser {
    XML("application/xml", "text/xml", "application/xhtml+xml"),
    TEXT("text/plain", "*/*"),
    JSON("application/json", "application/javascript", "text/javascript", "text/json"),
    HTML("text/html");

    private static final String PLUS_XML = "+xml";
    private static final String PLUS_JSON = "+json";
    private static final String PLUS_HTML = "+html";
    private final String[] contentTypes;

    private Parser(String ... contentTypes) {
        this.contentTypes = contentTypes;
    }

    public String getContentType() {
        return this.contentTypes[0];
    }

    public static Parser fromContentType(String contentType) {
        if (contentType == null) {
            return null;
        }
        Parser foundParser = ArrayUtils.contains(Parser.XML.contentTypes, contentType = ContentTypeExtractor.getContentTypeWithoutCharset(contentType.toLowerCase())) || StringUtils.endsWithIgnoreCase(contentType, PLUS_XML) ? XML : (ArrayUtils.contains(Parser.JSON.contentTypes, contentType) || StringUtils.endsWithIgnoreCase(contentType, PLUS_JSON) ? JSON : (ArrayUtils.contains(Parser.TEXT.contentTypes, contentType) ? TEXT : (ArrayUtils.contains(Parser.HTML.contentTypes, contentType) || StringUtils.endsWithIgnoreCase(contentType, PLUS_HTML) ? HTML : null)));
        return foundParser;
    }
}

