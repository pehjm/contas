/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.control.messages;

import java.io.PrintWriter;
import org.codehaus.groovy.control.Janitor;
import org.codehaus.groovy.control.ProcessingUnit;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.messages.Message;

public class SimpleMessage
extends Message {
    protected Object data;
    protected String message;
    protected ProcessingUnit owner;

    public SimpleMessage(String message, ProcessingUnit owner) {
        this(message, null, owner);
    }

    public SimpleMessage(String message, Object data, ProcessingUnit owner) {
        this.message = message;
        this.owner = owner;
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public void write(PrintWriter writer, Janitor janitor) {
        if (this.owner instanceof SourceUnit) {
            writer.println(((SourceUnit)this.owner).getName() + ": " + this.message);
        } else {
            writer.println(this.message);
        }
    }
}

