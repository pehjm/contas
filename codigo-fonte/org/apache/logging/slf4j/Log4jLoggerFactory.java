/*
 * Decompiled with CFR 0.152.
 */
package org.apache.logging.slf4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.LoggingException;
import org.apache.logging.log4j.spi.AbstractLoggerAdapter;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.slf4j.Log4jLogger;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class Log4jLoggerFactory
extends AbstractLoggerAdapter<Logger>
implements ILoggerFactory {
    private static final String FQCN = Log4jLoggerFactory.class.getName();
    private static final String PACKAGE = "org.slf4j";
    private static final String TO_SLF4J_CONTEXT = "org.apache.logging.slf4j.SLF4JLoggerContext";

    @Override
    protected Logger newLogger(String name, LoggerContext context) {
        String key = "ROOT".equals(name) ? "" : name;
        return new Log4jLogger(this.validateContext(context).getLogger(key), name);
    }

    @Override
    protected LoggerContext getContext() {
        Class<?> anchor = StackLocatorUtil.getCallerClass(FQCN, PACKAGE);
        return anchor == null ? LogManager.getContext() : this.getContext(StackLocatorUtil.getCallerClass(anchor));
    }

    private LoggerContext validateContext(LoggerContext context) {
        if (TO_SLF4J_CONTEXT.equals(context.getClass().getName())) {
            throw new LoggingException("log4j-slf4j-impl cannot be present with log4j-to-slf4j");
        }
        return context;
    }
}

