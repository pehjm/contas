/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.tools;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.codehaus.groovy.tools.GrapeUtil;
import org.codehaus.groovy.tools.LoaderConfiguration;

public class RootLoader
extends URLClassLoader {
    private static final String ORG_W3C_DOM_NODE = "org.w3c.dom.Node";
    private final Map<String, Class<?>> customClasses = new HashMap();

    public RootLoader(ClassLoader parent) {
        this(new URL[0], parent);
    }

    public RootLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        try {
            this.customClasses.put(ORG_W3C_DOM_NODE, super.loadClass(ORG_W3C_DOM_NODE, false));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public RootLoader(LoaderConfiguration lc) {
        this(Optional.ofNullable(RootLoader.class.getClassLoader()).orElseGet(ClassLoader::getSystemClassLoader));
        Thread.currentThread().setContextClassLoader(this);
        for (URL url : lc.getClassPathUrls()) {
            this.addURL(url);
        }
        String groovyHome = System.getProperty("groovy.home");
        for (String url : lc.getGrabUrls()) {
            Map<String, Object> grabParts = GrapeUtil.getIvyParts(url);
            String group = (String)grabParts.get("group");
            String module = (String)grabParts.get("module");
            String version = (String)grabParts.get("version");
            File jar = new File(groovyHome + "/repo/" + group + "/" + module + "/jars/" + module + "-" + version + ".jar");
            try {
                this.addURL(jar.toURI().toURL());
            }
            catch (MalformedURLException malformedURLException) {}
        }
    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = this.findLoadedClass(name);
        if (c != null) {
            return c;
        }
        c = this.customClasses.get(name);
        if (c != null) {
            return c;
        }
        try {
            c = super.findClass(name);
        }
        catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        if (c == null) {
            c = super.loadClass(name, resolve);
        }
        if (resolve) {
            this.resolveClass(c);
        }
        return c;
    }

    @Override
    public URL getResource(String name) {
        URL url = this.findResource(name);
        if (url == null) {
            url = super.getResource(name);
        }
        return url;
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
}

