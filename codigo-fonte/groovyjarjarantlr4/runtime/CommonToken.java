/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime;

import groovyjarjarantlr4.runtime.CharStream;
import groovyjarjarantlr4.runtime.Token;
import java.io.Serializable;

public class CommonToken
implements Token,
Serializable {
    protected int type;
    protected int line;
    protected int charPositionInLine = -1;
    protected int channel = 0;
    protected transient CharStream input;
    protected String text;
    protected int index = -1;
    protected int start;
    protected int stop;

    public CommonToken(int type) {
        this.type = type;
    }

    public CommonToken(CharStream input, int type, int channel, int start, int stop) {
        this.input = input;
        this.type = type;
        this.channel = channel;
        this.start = start;
        this.stop = stop;
    }

    public CommonToken(int type, String text) {
        this.type = type;
        this.channel = 0;
        this.text = text;
    }

    public CommonToken(Token oldToken) {
        this.text = oldToken.getText();
        this.type = oldToken.getType();
        this.line = oldToken.getLine();
        this.index = oldToken.getTokenIndex();
        this.charPositionInLine = oldToken.getCharPositionInLine();
        this.channel = oldToken.getChannel();
        this.input = oldToken.getInputStream();
        if (oldToken instanceof CommonToken) {
            this.start = ((CommonToken)oldToken).start;
            this.stop = ((CommonToken)oldToken).stop;
        }
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String getText() {
        if (this.text != null) {
            return this.text;
        }
        if (this.input == null) {
            return null;
        }
        int n = this.input.size();
        if (this.start < n && this.stop < n) {
            return this.input.substring(this.start, this.stop);
        }
        return "<EOF>";
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public int getCharPositionInLine() {
        return this.charPositionInLine;
    }

    @Override
    public void setCharPositionInLine(int charPositionInLine) {
        this.charPositionInLine = charPositionInLine;
    }

    @Override
    public int getChannel() {
        return this.channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public int getStartIndex() {
        return this.start;
    }

    public void setStartIndex(int start) {
        this.start = start;
    }

    public int getStopIndex() {
        return this.stop;
    }

    public void setStopIndex(int stop) {
        this.stop = stop;
    }

    @Override
    public int getTokenIndex() {
        return this.index;
    }

    @Override
    public void setTokenIndex(int index) {
        this.index = index;
    }

    @Override
    public CharStream getInputStream() {
        return this.input;
    }

    @Override
    public void setInputStream(CharStream input) {
        this.input = input;
    }

    public String toString() {
        String txt;
        String channelStr = "";
        if (this.channel > 0) {
            channelStr = ",channel=" + this.channel;
        }
        if ((txt = this.getText()) != null) {
            txt = txt.replaceAll("\n", "\\\\n");
            txt = txt.replaceAll("\r", "\\\\r");
            txt = txt.replaceAll("\t", "\\\\t");
        } else {
            txt = "<no text>";
        }
        return "[@" + this.getTokenIndex() + "," + this.start + ":" + this.stop + "='" + txt + "',<" + this.type + ">" + channelStr + "," + this.line + ":" + this.getCharPositionInLine() + "]";
    }
}

