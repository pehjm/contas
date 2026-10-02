/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime;

import groovyjarjarantlr4.runtime.BufferedTokenStream;
import groovyjarjarantlr4.runtime.Token;
import groovyjarjarantlr4.runtime.TokenSource;

public class CommonTokenStream
extends BufferedTokenStream {
    protected int channel = 0;

    public CommonTokenStream() {
    }

    public CommonTokenStream(TokenSource tokenSource) {
        super(tokenSource);
    }

    public CommonTokenStream(TokenSource tokenSource, int channel) {
        this(tokenSource);
        this.channel = channel;
    }

    @Override
    public void consume() {
        if (this.p == -1) {
            this.setup();
        }
        ++this.p;
        this.sync(this.p);
        while (((Token)this.tokens.get(this.p)).getChannel() != this.channel) {
            ++this.p;
            this.sync(this.p);
        }
    }

    @Override
    protected Token LB(int k) {
        if (k == 0 || this.p - k < 0) {
            return null;
        }
        int i = this.p;
        for (int n = 1; n <= k; ++n) {
            i = this.skipOffTokenChannelsReverse(i - 1);
        }
        if (i < 0) {
            return null;
        }
        return (Token)this.tokens.get(i);
    }

    @Override
    public Token LT(int k) {
        if (this.p == -1) {
            this.setup();
        }
        if (k == 0) {
            return null;
        }
        if (k < 0) {
            return this.LB(-k);
        }
        int i = this.p;
        for (int n = 1; n < k; ++n) {
            i = this.skipOffTokenChannels(i + 1);
        }
        if (i > this.range) {
            this.range = i;
        }
        return (Token)this.tokens.get(i);
    }

    protected int skipOffTokenChannels(int i) {
        this.sync(i);
        while (((Token)this.tokens.get(i)).getChannel() != this.channel) {
            this.sync(++i);
        }
        return i;
    }

    protected int skipOffTokenChannelsReverse(int i) {
        while (i >= 0 && ((Token)this.tokens.get(i)).getChannel() != this.channel) {
            --i;
        }
        return i;
    }

    @Override
    public void reset() {
        super.reset();
        this.p = this.skipOffTokenChannels(0);
    }

    @Override
    protected void setup() {
        this.p = 0;
        this.sync(0);
        int i = 0;
        while (((Token)this.tokens.get(i)).getChannel() != this.channel) {
            this.sync(++i);
        }
        this.p = i;
    }

    public int getNumberOfOnChannelTokens() {
        int n = 0;
        this.fill();
        for (int i = 0; i < this.tokens.size(); ++i) {
            Token t = (Token)this.tokens.get(i);
            if (t.getChannel() == this.channel) {
                ++n;
            }
            if (t.getType() == -1) break;
        }
        return n;
    }

    @Override
    public void setTokenSource(TokenSource tokenSource) {
        super.setTokenSource(tokenSource);
        this.channel = 0;
    }
}

