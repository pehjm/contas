/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime.debug;

import groovyjarjarantlr4.runtime.RecognitionException;
import groovyjarjarantlr4.runtime.Token;
import groovyjarjarantlr4.runtime.debug.DebugEventListener;
import java.util.ArrayList;
import java.util.List;

public class DebugEventHub
implements DebugEventListener {
    protected List<DebugEventListener> listeners = new ArrayList<DebugEventListener>();

    public DebugEventHub(DebugEventListener listener) {
        this.listeners.add(listener);
    }

    public DebugEventHub(DebugEventListener a, DebugEventListener b) {
        this.listeners.add(a);
        this.listeners.add(b);
    }

    public void addListener(DebugEventListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void enterRule(String grammarFileName, String ruleName) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.enterRule(grammarFileName, ruleName);
        }
    }

    @Override
    public void exitRule(String grammarFileName, String ruleName) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.exitRule(grammarFileName, ruleName);
        }
    }

    @Override
    public void enterAlt(int alt) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.enterAlt(alt);
        }
    }

    @Override
    public void enterSubRule(int decisionNumber) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.enterSubRule(decisionNumber);
        }
    }

    @Override
    public void exitSubRule(int decisionNumber) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.exitSubRule(decisionNumber);
        }
    }

    @Override
    public void enterDecision(int decisionNumber, boolean couldBacktrack) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.enterDecision(decisionNumber, couldBacktrack);
        }
    }

    @Override
    public void exitDecision(int decisionNumber) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.exitDecision(decisionNumber);
        }
    }

    @Override
    public void location(int line, int pos) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.location(line, pos);
        }
    }

    @Override
    public void consumeToken(Token token) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.consumeToken(token);
        }
    }

    @Override
    public void consumeHiddenToken(Token token) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.consumeHiddenToken(token);
        }
    }

    @Override
    public void LT(int index, Token t) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.LT(index, t);
        }
    }

    @Override
    public void mark(int index) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.mark(index);
        }
    }

    @Override
    public void rewind(int index) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.rewind(index);
        }
    }

    @Override
    public void rewind() {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.rewind();
        }
    }

    @Override
    public void beginBacktrack(int level) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.beginBacktrack(level);
        }
    }

    @Override
    public void endBacktrack(int level, boolean successful) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.endBacktrack(level, successful);
        }
    }

    @Override
    public void recognitionException(RecognitionException e) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.recognitionException(e);
        }
    }

    @Override
    public void beginResync() {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.beginResync();
        }
    }

    @Override
    public void endResync() {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.endResync();
        }
    }

    @Override
    public void semanticPredicate(boolean result, String predicate) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.semanticPredicate(result, predicate);
        }
    }

    @Override
    public void commence() {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.commence();
        }
    }

    @Override
    public void terminate() {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.terminate();
        }
    }

    @Override
    public void consumeNode(Object t) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.consumeNode(t);
        }
    }

    @Override
    public void LT(int index, Object t) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.LT(index, t);
        }
    }

    @Override
    public void nilNode(Object t) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.nilNode(t);
        }
    }

    @Override
    public void errorNode(Object t) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.errorNode(t);
        }
    }

    @Override
    public void createNode(Object t) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.createNode(t);
        }
    }

    @Override
    public void createNode(Object node, Token token) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.createNode(node, token);
        }
    }

    @Override
    public void becomeRoot(Object newRoot, Object oldRoot) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.becomeRoot(newRoot, oldRoot);
        }
    }

    @Override
    public void addChild(Object root, Object child) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.addChild(root, child);
        }
    }

    @Override
    public void setTokenBoundaries(Object t, int tokenStartIndex, int tokenStopIndex) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            DebugEventListener listener = this.listeners.get(i);
            listener.setTokenBoundaries(t, tokenStartIndex, tokenStopIndex);
        }
    }
}

