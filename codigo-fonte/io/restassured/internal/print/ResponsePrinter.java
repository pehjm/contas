/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.print;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import java.io.PrintStream;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

public class ResponsePrinter {
    private static final String BLACKLISTED = "[ BLACKLISTED ]";
    private static final String HEADER_NAME_AND_VALUE_SEPARATOR = ": ";

    public static String print(ResponseOptions responseOptions, ResponseBody responseBody, PrintStream stream, LogDetail logDetail, boolean shouldPrettyPrint, Set<String> blacklistedHeaders) {
        Cookies cookies;
        StringBuilder builder = new StringBuilder();
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.STATUS) {
            builder.append(responseOptions.statusLine());
        }
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.HEADERS) {
            Headers headers = responseOptions.headers();
            if (headers.exist()) {
                ResponsePrinter.appendNewLineIfAll(logDetail, builder).append(ResponsePrinter.toString(headers, blacklistedHeaders));
            }
        } else if (logDetail == LogDetail.COOKIES && (cookies = responseOptions.detailedCookies()).exist()) {
            ResponsePrinter.appendNewLineIfAll(logDetail, builder).append(cookies.toString());
        }
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.BODY) {
            String responseBodyToAppend = shouldPrettyPrint ? new Prettifier().getPrettifiedBodyIfPossible(responseOptions, responseBody) : responseBody.asString();
            if (logDetail == LogDetail.ALL && !StringUtils.isBlank(responseBodyToAppend)) {
                builder.append(SystemUtils.LINE_SEPARATOR).append(SystemUtils.LINE_SEPARATOR);
            }
            builder.append(responseBodyToAppend);
        }
        String response = builder.toString();
        stream.println(response);
        return response;
    }

    private static String toString(Headers headers, Set<String> blacklistedHeaders) {
        if (!headers.exist()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Header header : headers) {
            StringBuilder headerStringBuilder = builder.append(header.getName()).append(HEADER_NAME_AND_VALUE_SEPARATOR);
            if (blacklistedHeaders.contains(header.getName())) {
                headerStringBuilder.append(BLACKLISTED);
            } else {
                headerStringBuilder.append(header.getValue());
            }
            headerStringBuilder.append(SystemUtils.LINE_SEPARATOR);
        }
        builder.delete(builder.length() - SystemUtils.LINE_SEPARATOR.length(), builder.length());
        return builder.toString();
    }

    private static StringBuilder appendNewLineIfAll(LogDetail logDetail, StringBuilder builder) {
        if (logDetail == LogDetail.ALL) {
            builder.append(SystemUtils.LINE_SEPARATOR);
        }
        return builder;
    }
}

