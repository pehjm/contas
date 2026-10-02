/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.tools;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.util.Arrays;
import org.codehaus.groovy.tools.LoaderConfiguration;
import org.codehaus.groovy.tools.RootLoader;

public class GroovyStarter {
    static void printUsage() {
        System.out.println("possible programs are 'groovyc','groovy','console', and 'groovysh'");
        System.exit(1);
    }

    public static void main(String[] args) {
        try {
            GroovyStarter.rootLoader(args);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void rootLoader(String[] args) {
        String confOverride;
        String conf = System.getProperty("groovy.starter.conf", null);
        LoaderConfiguration lc = new LoaderConfiguration();
        boolean hadMain = false;
        boolean hadConf = false;
        boolean hadCP = false;
        int argsOffset = 0;
        block16: while (!(args.length - argsOffset <= 0 || hadMain && hadConf && hadCP)) {
            switch (args[argsOffset]) {
                case "--classpath": {
                    if (hadCP) break;
                    if (args.length == argsOffset + 1) {
                        GroovyStarter.exit("classpath parameter needs argument");
                    }
                    lc.addClassPath(args[argsOffset + 1]);
                    argsOffset += 2;
                    hadCP = true;
                    continue block16;
                }
                case "--main": {
                    if (hadMain) break;
                    if (args.length == argsOffset + 1) {
                        GroovyStarter.exit("main parameter needs argument");
                    }
                    lc.setMainClass(args[argsOffset + 1]);
                    argsOffset += 2;
                    hadMain = true;
                    continue block16;
                }
                case "--conf": {
                    if (hadConf) break;
                    if (args.length == argsOffset + 1) {
                        GroovyStarter.exit("conf parameter needs argument");
                    }
                    conf = args[argsOffset + 1];
                    argsOffset += 2;
                    hadConf = true;
                    continue block16;
                }
            }
            break;
        }
        if ((confOverride = System.getProperty("groovy.starter.conf.override", null)) != null) {
            conf = confOverride;
        }
        if (lc.getMainClass() == null && conf == null) {
            GroovyStarter.exit("no configuration file or main class specified");
        }
        String[] mainArgs = Arrays.copyOfRange(args, argsOffset, args.length);
        if (conf != null) {
            try {
                lc.configure(new FileInputStream(conf));
            }
            catch (Exception e) {
                System.err.println("exception while configuring main class loader:");
                GroovyStarter.exit(e);
            }
        }
        ClassLoader loader = GroovyStarter.getLoader(lc);
        Method m = null;
        try {
            Class<?> c = loader.loadClass(lc.getMainClass());
            m = c.getMethod("main", String[].class);
        }
        catch (ReflectiveOperationException | SecurityException e2) {
            GroovyStarter.exit(e2);
        }
        try {
            m.invoke(null, new Object[]{mainArgs});
        }
        catch (IllegalArgumentException | ReflectiveOperationException e3) {
            GroovyStarter.exit(e3);
        }
    }

    private static ClassLoader getLoader(LoaderConfiguration lc) {
        return AccessController.doPrivileged(() -> new RootLoader(lc));
    }

    private static void exit(Exception e) {
        e.printStackTrace();
        System.exit(1);
    }

    private static void exit(String text) {
        System.err.println(text);
        System.exit(1);
    }
}

