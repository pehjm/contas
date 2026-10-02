/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal;

import io.restassured.internal.common.assertion.AssertParameter;
import io.restassured.internal.http.ContentTypeExtractor;
import io.restassured.parsing.Parser;
import java.util.HashMap;
import java.util.Map;

public class ResponseParserRegistrar {
    private final Map<String, String> additional = new HashMap<String, String>(){
        {
            this.put("application/rss+xml", "application/xml");
            this.put("atom+xml", "application/xml");
            this.put("xop+xml", "application/xml");
            this.put("xslt+xml", "application/xml");
            this.put("rdf+xml", "application/xml");
            this.put("atomcat+xml", "application/xml");
            this.put("atomsvc+xml", "application/xml");
            this.put("auth-policy+xml", "application/xml");
        }
    };
    private Parser defaultParser = null;

    public ResponseParserRegistrar() {
    }

    public ResponseParserRegistrar(ResponseParserRegistrar rpr) {
        this.additional.putAll(rpr.additional);
        this.defaultParser = rpr.defaultParser;
    }

    public Parser getParser(String contentType) {
        String contentTypeWithoutCharset = ContentTypeExtractor.getContentTypeWithoutCharset(contentType);
        String parserAsString = this.additional.get(contentTypeWithoutCharset);
        Parser parser = parserAsString == null ? Parser.fromContentType(contentType) : Parser.fromContentType(parserAsString);
        return parser == null ? this.defaultParser : parser;
    }

    public Parser getNonDefaultParser(String contentType) {
        String contentTypeWithoutCharset = ContentTypeExtractor.getContentTypeWithoutCharset(contentType);
        String parserAsString = this.additional.get(contentTypeWithoutCharset);
        Parser parser = parserAsString == null ? null : Parser.fromContentType(parserAsString);
        return parser == null ? this.defaultParser : parser;
    }

    public void registerParser(String contentType, Parser parser) {
        AssertParameter.notNull(parser, "Parser");
        AssertParameter.notNull(contentType, "contentType");
        String contentTypeWithoutCharset = ContentTypeExtractor.getContentTypeWithoutCharset(contentType);
        this.additional.put(contentTypeWithoutCharset, parser.getContentType());
    }

    public void registerDefaultParser(Parser parser) {
        AssertParameter.notNull(parser, "Parser");
        this.defaultParser = parser;
    }

    public void unregisterParser(String contentType) {
        AssertParameter.notNull(contentType, "contentType");
        this.additional.remove(contentType);
    }

    public boolean hasCustomParser(String contentType) {
        if (this.defaultParser != null) {
            return true;
        }
        return this.hasCustomParserExcludingDefaultParser(contentType);
    }

    public boolean hasCustomParserExcludingDefaultParser(String contentType) {
        Parser parser = this.getNonDefaultParser(contentType);
        return parser != null && (parser == Parser.XML || parser == Parser.JSON || parser == Parser.HTML);
    }
}

