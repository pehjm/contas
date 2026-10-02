/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime.debug;

import groovyjarjarantlr4.runtime.Token;
import groovyjarjarantlr4.runtime.TokenSource;
import groovyjarjarantlr4.runtime.TokenStream;
import groovyjarjarantlr4.runtime.debug.DebugEventListener;

public class DebugTokenStream
implements TokenStream {
    protected DebugEventListener dbg;
    public TokenStream input;
    protected boolean initialStreamState = true;
    protected int lastMarker;

    public DebugTokenStream(TokenStream input, DebugEventListener dbg2) {
        this.input = input;
        this.setDebugListener(dbg2);
        input.LT(1);
    }

    public void setDebugListener(DebugEventListener dbg2) {
        this.dbg = dbg2;
    }

    @Override
    public void consume() {
        if (this.initialStreamState) {
            this.consumeInitialHiddenTokens();
        }
        int a = this.input.index();
        Token t = this.input.LT(1);
        this.input.consume();
        int b = this.input.index();
        this.dbg.consumeToken(t);
        if (b > a + 1) {
            for (int i = a + 1; i < b; ++i) {
                this.dbg.consumeHiddenToken(this.input.get(i));
            }
        }
    }

    protected void consumeInitialHiddenTokens() {
        int firstOnChannelTokenIndex = this.input.index();
        for (int i = 0; i < firstOnChannelTokenIndex; ++i) {
            this.dbg.consumeHiddenToken(this.input.get(i));
        }
        this.initialStreamState = false;
    }

    @Override
    public Token LT(int i) {
        if (this.initialStreamState) {
            this.consumeInitialHiddenTokens();
        }
        this.dbg.LT(i, this.input.LT(i));
        return this.input.LT(i);
    }

    @Override
    public int LA(int i) {
        if (this.initialStreamState) {
            this.consumeInitialHiddenTokens();
        }
        this.dbg.LT(i, this.input.LT(i));
        return this.input.LA(i);
    }

    @Override
    public Token get(int i) {
        return this.input.get(i);
    }

    @Override
    public int mark() {
        this.lastMarker = this.input.mark();
        this.dbg.mark(this.lastMarker);
        return this.lastMarker;
    }

    @Override
    public int index() {
        return this.input.index();
    }

    @Override
    public int range() {
        return this.input.range();
    }

    @Override
    public void rewind(int marker) {
        this.dbg.rewind(marker);
        this.input.rewind(marker);
    }

    @Override
    public void rewind() {
        this.dbg.rewind();
        this.input.rewind(this.lastMarker);
    }

    @Override
    public void release(int marker) {
    }

    @Override
    public void seek(int index) {
        this.input.seek(index);
    }

    @Override
    public int size() {
        return this.input.size();
    }

    @Override
    public TokenSource getTokenSource() {
        return this.input.getTokenSource();
    }

    @Override
    public String getSourceName() {
        return this.getTokenSource().getSourceName();
    }

    public String toString() {
        return this.input.toString();
    }

    @Override
    public String toString(int start, int stop) {
        return this.input.toString(start, stop);
    }

    @Override
    public String toString(Token start, Token stop) {
        return this.input.toString(start, stop);
    }
}

