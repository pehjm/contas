/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.Writable;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import org.codehaus.groovy.runtime.ResourceGroovyMethods;

public class WritableFile
extends File
implements Writable {
    private static final long serialVersionUID = 4157767752861425917L;
    private final String encoding;

    public WritableFile(File delegate) {
        this(delegate, null);
    }

    public WritableFile(File delegate, String encoding) {
        super(delegate.toURI());
        this.encoding = encoding;
    }

    @Override
    public Writer writeTo(Writer out) throws IOException {
        try (BufferedReader reader = this.encoding == null ? ResourceGroovyMethods.newReader(this) : ResourceGroovyMethods.newReader(this, this.encoding);){
            int c = ((Reader)reader).read();
            while (c != -1) {
                out.write(c);
                c = ((Reader)reader).read();
            }
        }
        return out;
    }
}

