/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime;

import groovyjarjarantlr4.runtime.RuleReturnScope;
import groovyjarjarantlr4.runtime.Token;

public class ParserRuleReturnScope
extends RuleReturnScope {
    public Token start;
    public Token stop;

    @Override
    public Object getStart() {
        return this.start;
    }

    @Override
    public Object getStop() {
        return this.stop;
    }

    @Override
    public Object getTree() {
        return null;
    }
}

