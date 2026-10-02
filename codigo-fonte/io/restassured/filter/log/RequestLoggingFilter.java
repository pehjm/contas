/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter.log;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang3.Validate;

public class RequestLoggingFilter
implements Filter {
    private static final boolean SHOW_URL_ENCODED_URI = true;
    private final LogDetail logDetail;
    private final PrintStream stream;
    private final boolean shouldPrettyPrint;
    private final boolean showUrlEncodedUri;
    private final Set<String> blacklistedHeaders;
    private final Set<LogDetail> logDetailSet = new HashSet<LogDetail>();

    public RequestLoggingFilter() {
        this(LogDetail.ALL, System.out);
    }

    public RequestLoggingFilter(LogDetail logDetail) {
        this(logDetail, System.out);
    }

    public RequestLoggingFilter(PrintStream printStream) {
        this(LogDetail.ALL, printStream);
    }

    public RequestLoggingFilter(LogDetail logDetail, PrintStream stream) {
        this(logDetail, true, stream);
    }

    public RequestLoggingFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream) {
        this(logDetail, shouldPrettyPrint, stream, true);
    }

    public RequestLoggingFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream, boolean showUrlEncodedUri) {
        this(logDetail, shouldPrettyPrint, stream, showUrlEncodedUri, new TreeSet<String>(String.CASE_INSENSITIVE_ORDER));
    }

    public RequestLoggingFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream, boolean showUrlEncodedUri, Set<String> blacklistedHeaders) {
        Validate.notNull(stream, "Print stream cannot be null", new Object[0]);
        Validate.notNull(blacklistedHeaders, "Blacklisted headers cannot be null", new Object[0]);
        Validate.notNull(logDetail, "Log details cannot be null", new Object[0]);
        if (logDetail == LogDetail.STATUS) {
            throw new IllegalArgumentException(String.format("%s is not a valid %s for a request.", new Object[]{LogDetail.STATUS, LogDetail.class.getSimpleName()}));
        }
        this.stream = stream;
        this.logDetail = logDetail;
        TreeSet<String> caseInsensitiveBlacklistedHeaders = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitiveBlacklistedHeaders.addAll(blacklistedHeaders);
        this.blacklistedHeaders = caseInsensitiveBlacklistedHeaders;
        this.shouldPrettyPrint = shouldPrettyPrint;
        this.showUrlEncodedUri = showUrlEncodedUri;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        String uri = requestSpec.getURI();
        if (!this.showUrlEncodedUri) {
            uri = UrlDecoder.urlDecode(uri, Charset.forName(requestSpec.getConfig().getEncoderConfig().defaultQueryParameterCharset()), true);
        }
        if (this.logDetailSet.isEmpty()) {
            RequestPrinter.print(requestSpec, requestSpec.getMethod(), uri, this.logDetail, this.blacklistedHeaders, this.stream, this.shouldPrettyPrint);
        } else {
            RequestPrinter.print(requestSpec, requestSpec.getMethod(), uri, this.logDetailSet, this.blacklistedHeaders, this.stream, this.shouldPrettyPrint);
        }
        return ctx.next(requestSpec, responseSpec);
    }

    public static RequestLoggingFilter logRequestTo(PrintStream stream) {
        return new RequestLoggingFilter(stream);
    }

    public static RequestLoggingFilter with(LogDetail ... logDetails) {
        return new RequestLoggingFilter().addLog(logDetails);
    }

    private RequestLoggingFilter addLog(LogDetail ... logDetails) {
        this.logDetailSet.addAll(Arrays.asList(logDetails));
        return this;
    }
}

