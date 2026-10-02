/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.internal.print;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.NoParameterValue;
import io.restassured.internal.support.Prettifier;
import io.restassured.parsing.Parser;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

public class RequestPrinter {
    private static final String TAB = "\t";
    private static final String EQUALS = "=";
    private static final String NONE = "<none>";
    private static final String BLACKLISTED = "[ BLACKLISTED ]";

    public static String print(FilterableRequestSpecification requestSpec, String requestMethod, String completeRequestUri, LogDetail logDetail, Set<String> blacklistedHeaders, PrintStream stream, boolean shouldPrettyPrint) {
        StringBuilder builder = new StringBuilder();
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.METHOD) {
            RequestPrinter.addSingle(builder, "Request method:", requestMethod);
        }
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.URI) {
            RequestPrinter.addSingle(builder, "Request URI:", completeRequestUri);
        }
        if (logDetail == LogDetail.ALL) {
            RequestPrinter.addProxy(requestSpec, builder);
        }
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.PARAMS) {
            RequestPrinter.addMapDetails(builder, "Request params:", requestSpec.getRequestParams());
            RequestPrinter.addMapDetails(builder, "Query params:", requestSpec.getQueryParams());
            RequestPrinter.addMapDetails(builder, "Form params:", requestSpec.getFormParams());
            RequestPrinter.addMapDetails(builder, "Path params:", requestSpec.getNamedPathParams());
        }
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.HEADERS) {
            RequestPrinter.addHeaders(requestSpec, blacklistedHeaders, builder);
        }
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.COOKIES) {
            RequestPrinter.addCookies(requestSpec, builder);
        }
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.PARAMS) {
            RequestPrinter.addMultiParts(requestSpec, builder);
        }
        if (logDetail == LogDetail.ALL || logDetail == LogDetail.BODY) {
            RequestPrinter.addBody(requestSpec, builder, shouldPrettyPrint);
        }
        String logString = StringUtils.removeEnd(builder.toString(), SystemUtils.LINE_SEPARATOR);
        stream.println(logString);
        return logString;
    }

    public static String print(FilterableRequestSpecification requestSpec, String requestMethod, String completeRequestUri, Set<LogDetail> logDetails, Set<String> blacklistedHeaders, PrintStream stream, boolean shouldPrettyPrint) {
        StringBuilder builder = new StringBuilder();
        if (logDetails.contains((Object)LogDetail.ALL)) {
            return RequestPrinter.print(requestSpec, requestMethod, completeRequestUri, LogDetail.ALL, blacklistedHeaders, stream, shouldPrettyPrint);
        }
        if (logDetails.contains((Object)LogDetail.METHOD)) {
            RequestPrinter.addSingle(builder, "Request method:", requestMethod);
        }
        if (logDetails.contains((Object)LogDetail.URI)) {
            RequestPrinter.addSingle(builder, "Request URI:", completeRequestUri);
        }
        if (logDetails.contains((Object)LogDetail.PARAMS)) {
            RequestPrinter.addMapDetails(builder, "Request params:", requestSpec.getRequestParams());
            RequestPrinter.addMapDetails(builder, "Query params:", requestSpec.getQueryParams());
            RequestPrinter.addMapDetails(builder, "Form params:", requestSpec.getFormParams());
            RequestPrinter.addMapDetails(builder, "Path params:", requestSpec.getNamedPathParams());
        }
        if (logDetails.contains((Object)LogDetail.HEADERS)) {
            RequestPrinter.addHeaders(requestSpec, blacklistedHeaders, builder);
        }
        if (logDetails.contains((Object)LogDetail.COOKIES)) {
            RequestPrinter.addCookies(requestSpec, builder);
        }
        if (logDetails.contains((Object)LogDetail.PARAMS)) {
            RequestPrinter.addMultiParts(requestSpec, builder);
        }
        if (logDetails.contains((Object)LogDetail.BODY)) {
            RequestPrinter.addBody(requestSpec, builder, shouldPrettyPrint);
        }
        String logString = StringUtils.removeEnd(builder.toString(), SystemUtils.LINE_SEPARATOR);
        stream.println(logString);
        return logString;
    }

    private static void addProxy(FilterableRequestSpecification requestSpec, StringBuilder builder) {
        builder.append("Proxy:");
        ProxySpecification proxySpec = requestSpec.getProxySpecification();
        RequestPrinter.appendThreeTabs(builder);
        if (proxySpec == null) {
            builder.append(NONE);
        } else {
            builder.append(proxySpec.toString());
        }
        builder.append(SystemUtils.LINE_SEPARATOR);
    }

    private static void addBody(FilterableRequestSpecification requestSpec, StringBuilder builder, boolean shouldPrettyPrint) {
        builder.append("Body:");
        if (requestSpec.getBody() != null) {
            Object body = shouldPrettyPrint ? new Prettifier().getPrettifiedBodyIfPossible(requestSpec) : requestSpec.getBody();
            builder.append(SystemUtils.LINE_SEPARATOR).append(body);
        } else {
            RequestPrinter.appendTab(RequestPrinter.appendTwoTabs(builder)).append(NONE);
        }
    }

    private static void addCookies(FilterableRequestSpecification requestSpec, StringBuilder builder) {
        builder.append("Cookies:");
        Cookies cookies = requestSpec.getCookies();
        if (!cookies.exist()) {
            RequestPrinter.appendTwoTabs(builder).append(NONE).append(SystemUtils.LINE_SEPARATOR);
        }
        int i = 0;
        for (Cookie cookie : cookies) {
            if (i++ == 0) {
                RequestPrinter.appendTwoTabs(builder);
            } else {
                RequestPrinter.appendFourTabs(builder);
            }
            builder.append(cookie).append(SystemUtils.LINE_SEPARATOR);
        }
    }

    private static void addHeaders(FilterableRequestSpecification requestSpec, Set<String> blacklistedHeaders, StringBuilder builder) {
        builder.append("Headers:");
        Headers headers = requestSpec.getHeaders();
        if (!headers.exist()) {
            RequestPrinter.appendTwoTabs(builder).append(NONE).append(SystemUtils.LINE_SEPARATOR);
        } else {
            int i = 0;
            for (Header header : headers) {
                if (i++ == 0) {
                    RequestPrinter.appendTwoTabs(builder);
                } else {
                    RequestPrinter.appendFourTabs(builder);
                }
                Header processedHeader = header;
                if (blacklistedHeaders.contains(header.getName())) {
                    processedHeader = new Header(header.getName(), BLACKLISTED);
                }
                builder.append(processedHeader).append(SystemUtils.LINE_SEPARATOR);
            }
        }
    }

    private static void addMultiParts(FilterableRequestSpecification requestSpec, StringBuilder builder) {
        builder.append("Multiparts:");
        List<MultiPartSpecification> multiParts = requestSpec.getMultiPartParams();
        if (multiParts.isEmpty()) {
            RequestPrinter.appendTwoTabs(builder).append(NONE).append(SystemUtils.LINE_SEPARATOR);
        } else {
            for (int i = 0; i < multiParts.size(); ++i) {
                MultiPartSpecification multiPart = multiParts.get(i);
                if (i == 0) {
                    RequestPrinter.appendTwoTabs(builder);
                } else {
                    RequestPrinter.appendFourTabs(builder.append(SystemUtils.LINE_SEPARATOR));
                }
                builder.append("------------");
                RequestPrinter.appendFourTabs(RequestPrinter.appendFourTabs(builder.append(SystemUtils.LINE_SEPARATOR)).append("Content-Disposition: ").append(requestSpec.getContentType().replace("multipart/", "")).append("; name = ").append(multiPart.getControlName()).append(multiPart.hasFileName() ? "; filename = " + multiPart.getFileName() : "").append(SystemUtils.LINE_SEPARATOR)).append("Content-Type: ").append(multiPart.getMimeType());
                Map<String, String> headers = multiPart.getHeaders();
                if (!headers.isEmpty()) {
                    Set<Map.Entry<String, String>> headerEntries = headers.entrySet();
                    for (Map.Entry<String, String> headerEntry : headerEntries) {
                        RequestPrinter.appendFourTabs(RequestPrinter.appendFourTabs(builder.append(SystemUtils.LINE_SEPARATOR)).append(headerEntry.getKey()).append(": ").append(headerEntry.getValue()));
                    }
                }
                builder.append(SystemUtils.LINE_SEPARATOR);
                if (multiPart.getContent() instanceof InputStream) {
                    RequestPrinter.appendFourTabs(builder.append(SystemUtils.LINE_SEPARATOR)).append("<inputstream>");
                    continue;
                }
                Parser parser = Parser.fromContentType(multiPart.getMimeType());
                String prettified = new Prettifier().prettify(multiPart.getContent().toString(), parser);
                String prettifiedIndented = StringUtils.replace(prettified, SystemUtils.LINE_SEPARATOR, SystemUtils.LINE_SEPARATOR + TAB + TAB + TAB + TAB);
                RequestPrinter.appendFourTabs(builder.append(SystemUtils.LINE_SEPARATOR)).append(prettifiedIndented);
            }
            builder.append(SystemUtils.LINE_SEPARATOR);
        }
    }

    private static void addSingle(StringBuilder builder, String str, String requestPath) {
        RequestPrinter.appendTab(builder.append(str)).append(requestPath).append(SystemUtils.LINE_SEPARATOR);
    }

    private static void addMapDetails(StringBuilder builder, String title, Map<String, ?> map) {
        RequestPrinter.appendTab(builder.append(title));
        if (map.isEmpty()) {
            builder.append(NONE).append(SystemUtils.LINE_SEPARATOR);
        } else {
            int i = 0;
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                if (i++ != 0) {
                    RequestPrinter.appendFourTabs(builder);
                }
                Object value = entry.getValue();
                builder.append(entry.getKey());
                if (!(value instanceof NoParameterValue)) {
                    builder.append(EQUALS).append(value);
                }
                builder.append(SystemUtils.LINE_SEPARATOR);
            }
        }
    }

    private static StringBuilder appendFourTabs(StringBuilder builder) {
        RequestPrinter.appendTwoTabs(RequestPrinter.appendTwoTabs(builder));
        return builder;
    }

    private static StringBuilder appendTwoTabs(StringBuilder builder) {
        RequestPrinter.appendTab(RequestPrinter.appendTab(builder));
        return builder;
    }

    private static StringBuilder appendThreeTabs(StringBuilder builder) {
        RequestPrinter.appendTwoTabs(RequestPrinter.appendTab(builder));
        return builder;
    }

    private static StringBuilder appendTab(StringBuilder builder) {
        return builder.append(TAB);
    }
}

