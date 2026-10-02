/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.config;

import io.restassured.config.Config;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.common.assertion.AssertParameter;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.lang3.Validate;

public class LogConfig
implements Config {
    private final PrintStream defaultPrintStream;
    private final boolean prettyPrintingEnabled;
    private final LogDetail logDetailIfValidationFails;
    private final boolean urlEncodeRequestUri;
    private final boolean isUserDefined;
    private final Set<String> headerBlacklist;

    public LogConfig() {
        this(System.out, true, null, true, new TreeSet<String>(String.CASE_INSENSITIVE_ORDER), false);
    }

    public LogConfig(PrintStream defaultPrintStream, boolean prettyPrintingEnabled) {
        this(defaultPrintStream, prettyPrintingEnabled, null, true, new HashSet<String>(), true);
    }

    private LogConfig(PrintStream defaultPrintStream, boolean prettyPrintingEnabled, LogDetail logDetailIfValidationFails, boolean urlEncodeRequestUri, Set<String> headerBlacklist, boolean isUserDefined) {
        Validate.notNull(defaultPrintStream, "Stream to write logs to cannot be null", new Object[0]);
        Validate.notNull(defaultPrintStream, "Stream to write logs to cannot be null", new Object[0]);
        this.defaultPrintStream = defaultPrintStream;
        this.prettyPrintingEnabled = prettyPrintingEnabled;
        this.logDetailIfValidationFails = logDetailIfValidationFails;
        this.isUserDefined = isUserDefined;
        this.urlEncodeRequestUri = urlEncodeRequestUri;
        this.headerBlacklist = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        this.headerBlacklist.addAll(headerBlacklist);
    }

    public PrintStream defaultStream() {
        return this.defaultPrintStream;
    }

    public Set<String> blacklistedHeaders() {
        return Collections.unmodifiableSet(this.headerBlacklist);
    }

    public LogConfig defaultStream(PrintStream printStream) {
        return new LogConfig(printStream, true, this.logDetailIfValidationFails, this.urlEncodeRequestUri, this.headerBlacklist, true);
    }

    public boolean isPrettyPrintingEnabled() {
        return this.prettyPrintingEnabled;
    }

    public boolean isLoggingOfRequestAndResponseIfValidationFailsEnabled() {
        return this.logDetailIfValidationFails != null;
    }

    public LogDetail logDetailOfRequestAndResponseIfValidationFails() {
        return this.logDetailIfValidationFails;
    }

    public LogConfig enablePrettyPrinting(boolean shouldEnable) {
        return new LogConfig(this.defaultPrintStream, shouldEnable, this.logDetailIfValidationFails, this.urlEncodeRequestUri, this.headerBlacklist, true);
    }

    public LogConfig enableLoggingOfRequestAndResponseIfValidationFails() {
        return this.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    public LogConfig enableLoggingOfRequestAndResponseIfValidationFails(LogDetail logDetail) {
        return new LogConfig(this.defaultPrintStream, this.prettyPrintingEnabled, logDetail, this.urlEncodeRequestUri, this.headerBlacklist, true);
    }

    public LogConfig urlEncodeRequestUri(boolean urlEncodeRequestUri) {
        return new LogConfig(this.defaultPrintStream, this.prettyPrintingEnabled, this.logDetailIfValidationFails, urlEncodeRequestUri, this.headerBlacklist, true);
    }

    public LogConfig blacklistHeader(String header, String ... otherHeaders) {
        AssertParameter.notNull(header, "header");
        TreeSet<String> newHeaderBlackList = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        newHeaderBlackList.addAll(this.headerBlacklist);
        newHeaderBlackList.add(header);
        if (otherHeaders != null && otherHeaders.length > 0) {
            Collections.addAll(newHeaderBlackList, otherHeaders);
        }
        return new LogConfig(this.defaultPrintStream, this.prettyPrintingEnabled, this.logDetailIfValidationFails, this.urlEncodeRequestUri, newHeaderBlackList, true);
    }

    public LogConfig blacklistHeaders(Collection<String> headers) {
        AssertParameter.notNull(headers, "headers");
        Set<String> newHeaderBlackList = headers.stream().filter(Objects::nonNull).collect(Collectors.toSet());
        return new LogConfig(this.defaultPrintStream, this.prettyPrintingEnabled, this.logDetailIfValidationFails, this.urlEncodeRequestUri, newHeaderBlackList, true);
    }

    public LogConfig blacklistDefaultSensitiveHeaders() {
        this.headerBlacklist.add("Authorization");
        this.headerBlacklist.add("Proxy-Authorization");
        this.headerBlacklist.add("Cookie");
        return new LogConfig(this.defaultPrintStream, this.prettyPrintingEnabled, this.logDetailIfValidationFails, this.urlEncodeRequestUri, this.headerBlacklist, true);
    }

    public boolean shouldUrlEncodeRequestUri() {
        return this.urlEncodeRequestUri;
    }

    public static LogConfig logConfig() {
        return new LogConfig();
    }

    public LogConfig and() {
        return this;
    }

    @Override
    public boolean isUserConfigured() {
        return this.isUserDefined;
    }
}

