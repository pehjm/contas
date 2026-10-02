/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime.debug;

import groovyjarjarantlr4.runtime.RecognitionException;
import groovyjarjarantlr4.runtime.Token;
import groovyjarjarantlr4.runtime.debug.DebugEventListener;

public class DebugEventRepeater
implements DebugEventListener {
    protected DebugEventListener listener;

    public DebugEventRepeater(DebugEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void enterRule(String grammarFileName, String ruleName) {
        this.listener.enterRule(grammarFileName, ruleName);
    }

    @Override
    public void exitRule(String grammarFileName, String ruleName) {
        this.listener.exitRule(grammarFileName, ruleName);
    }

    @Override
    public void enterAlt(int alt) {
        this.listener.enterAlt(alt);
    }

    @Override
    public void enterSubRule(int decisionNumber) {
        this.listener.enterSubRule(decisionNumber);
    }

    @Override
    public void exitSubRule(int decisionNumber) {
        this.listener.exitSubRule(decisionNumber);
    }

    @Override
    public void enterDecision(int decisionNumber, boolean couldBacktrack) {
        this.listener.enterDecision(decisionNumber, couldBacktrack);
    }

    @Override
    public void exitDecision(int decisionNumber) {
        this.listener.exitDecision(decisionNumber);
    }

    @Override
    public void location(int line, int pos) {
        this.listener.location(line, pos);
    }

    @Override
    public void consumeToken(Token token) {
        this.listener.consumeToken(token);
    }

    @Override
    public void consumeHiddenToken(Token token) {
        this.listener.consumeHiddenToken(token);
    }

    @Override
    public void LT(int i, Token t) {
        this.listener.LT(i, t);
    }

    @Override
    public void mark(int i) {
        this.listener.mark(i);
    }

    @Override
    public void rewind(int i) {
        this.listener.rewind(i);
    }

    @Override
    public void rewind() {
        this.listener.rewind();
    }

    @Override
    public void beginBacktrack(int level) {
        this.listener.beginBacktrack(level);
    }

    @Override
    public void endBacktrack(int level, boolean successful) {
        this.listener.endBacktrack(level, successful);
    }

    @Override
    public void recognitionException(RecognitionException e) {
        this.listener.recognitionException(e);
    }

    @Override
    public void beginResync() {
        this.listener.beginResync();
    }

    @Override
    public void endResync() {
        this.listener.endResync();
    }

    @Override
    public void semanticPredicate(boolean result, String predicate) {
        this.listener.semanticPredicate(result, predicate);
    }

    @Override
    public void commence() {
        this.listener.commence();
    }

    @Override
    public void terminate() {
        this.listener.terminate();
    }

    @Override
    public void consumeNode(Object t) {
        this.listener.consumeNode(t);
    }

    @Override
    public void LT(int i, Object t) {
        this.listener.LT(i, t);
    }

    @Override
    public void nilNode(Object t) {
        this.listener.nilNode(t);
    }

    @Override
    public void errorNode(Object t) {
        this.listener.errorNode(t);
    }

    @Override
    public void createNode(Object t) {
        this.listener.createNode(t);
    }

    @Override
    public void createNode(Object node, Token token) {
        this.listener.createNode(node, token);
    }

    @Override
    public void becomeRoot(Object newRoot, Object oldRoot) {
        this.listener.becomeRoot(newRoot, oldRoot);
    }

    @Override
    public void addChild(Object root, Object child) {
        this.listener.addChild(root, child);
    }

    @Override
    public void setTokenBoundaries(Object t, int tokenStartIndex, int tokenStopIndex) {
        this.listener.setTokenBoundaries(t, tokenStartIndex, tokenStopIndex);
    }
}

