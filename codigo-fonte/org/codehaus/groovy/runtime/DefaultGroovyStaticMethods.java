/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import org.codehaus.groovy.reflection.ReflectionUtils;
import org.codehaus.groovy.runtime.RegexSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class DefaultGroovyStaticMethods {
    public static Thread start(Thread self, Closure closure) {
        return DefaultGroovyStaticMethods.createThread(null, false, closure);
    }

    public static Thread start(Thread self, String name, Closure closure) {
        return DefaultGroovyStaticMethods.createThread(name, false, closure);
    }

    public static Thread startDaemon(Thread self, Closure closure) {
        return DefaultGroovyStaticMethods.createThread(null, true, closure);
    }

    public static Thread startDaemon(Thread self, String name, Closure closure) {
        return DefaultGroovyStaticMethods.createThread(name, true, closure);
    }

    private static Thread createThread(String name, boolean daemon, Closure closure) {
        Thread thread;
        Thread thread2 = thread = name != null ? new Thread(closure, name) : new Thread(closure);
        if (daemon) {
            thread.setDaemon(true);
        }
        thread.start();
        return thread;
    }

    public static String dumpAll(Thread self) {
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        return Arrays.stream(threadMxBean.dumpAllThreads(true, true)).map(ThreadInfo::toString).collect(Collectors.joining(""));
    }

    public static List<Thread> allThreads(Thread self) {
        ThreadGroup parentGroup;
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        while ((parentGroup = rootGroup.getParent()) != null) {
            rootGroup = parentGroup;
        }
        Thread[] threads = new Thread[rootGroup.activeCount()];
        while (rootGroup.enumerate(threads, true) == threads.length) {
            threads = new Thread[threads.length * 2];
        }
        return Collections.unmodifiableList(Arrays.stream(threads).filter(Objects::nonNull).collect(Collectors.toList()));
    }

    public static Matcher getLastMatcher(Matcher self) {
        return RegexSupport.getLastMatcher();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void sleepImpl(long millis, Closure closure) {
        long start = System.currentTimeMillis();
        long rest = millis;
        boolean interrupted = false;
        block6: while (true) {
            while (rest > 0L) {
                try {
                    Thread.sleep(rest);
                    rest = 0L;
                }
                catch (InterruptedException e) {
                    interrupted = true;
                    if (closure != null && DefaultTypeTransformation.castToBoolean(closure.call((Object)e))) {
                        if (!interrupted) return;
                        Thread.currentThread().interrupt();
                        return;
                    }
                    try {
                        long current = System.currentTimeMillis();
                        rest = millis + start - current;
                        continue block6;
                    }
                    catch (Throwable throwable) {
                        throw throwable;
                        return;
                    }
                }
            }
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void sleep(Object self, long milliseconds) {
        DefaultGroovyStaticMethods.sleepImpl(milliseconds, null);
    }

    public static void sleep(Object self, long milliseconds, Closure onInterrupt) {
        DefaultGroovyStaticMethods.sleepImpl(milliseconds, onInterrupt);
    }

    public static ResourceBundle getBundle(ResourceBundle self, String bundleName) {
        return DefaultGroovyStaticMethods.getBundle(self, bundleName, Locale.getDefault());
    }

    public static ResourceBundle getBundle(ResourceBundle self, String bundleName, Locale locale) {
        ClassLoader targetCL;
        Class c = ReflectionUtils.getCallingClass();
        ClassLoader classLoader = targetCL = c != null ? c.getClassLoader() : null;
        if (targetCL == null) {
            targetCL = ClassLoader.getSystemClassLoader();
        }
        return ResourceBundle.getBundle(bundleName, locale, targetCL);
    }

    public static File createTempDir(File self) throws IOException {
        return DefaultGroovyStaticMethods.createTempDir(self, "groovy-generated-", "tmpdir-");
    }

    public static File createTempDir(File self, String prefix) throws IOException {
        return DefaultGroovyStaticMethods.createTempDirNio(prefix);
    }

    public static File createTempDir(File self, String prefix, String suffix) throws IOException {
        return DefaultGroovyStaticMethods.createTempDirNio(prefix + suffix);
    }

    private static File createTempDirNio(String prefix) throws IOException {
        Path tempPath = Files.createTempDirectory(prefix, new FileAttribute[0]);
        return tempPath.toFile();
    }

    public static long currentTimeSeconds(System self) {
        return System.currentTimeMillis() / 1000L;
    }
}

