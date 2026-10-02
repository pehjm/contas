/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime;

import groovyjarjarantlr4.runtime.CharStream;
import groovyjarjarantlr4.runtime.Token;

public class ClassicToken
implements Token {
    protected String text;
    protected int type;
    protected int line;
    protected int charPositionInLine;
    protected int channel = 0;
    protected int index;

    public ClassicToken(int type) {
        this.type = type;
    }

    public ClassicToken(Token oldToken) {
        this.text = oldToken.getText();
        this.type = oldToken.getType();
        this.line = oldToken.getLine();
        this.charPositionInLine = oldToken.getCharPositionInLine();
        this.channel = oldToken.getChannel();
    }

    public ClassicToken(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public ClassicToken(int type, String text, int channel) {
        this.type = type;
        this.text = text;
        this.channel = channel;
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
        return this.text;
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
        return null;
    }

    @Override
    public void setInputStream(CharStream input) {
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
        return "[@" + this.getTokenIndex() + ",'" + txt + "',<" + this.type + ">" + channelStr + "," + this.line + ":" + this.getCharPositionInLine() + "]";
    }
}

