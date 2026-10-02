/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime.debug;

import groovyjarjarantlr4.runtime.RecognitionException;
import groovyjarjarantlr4.runtime.Token;
import groovyjarjarantlr4.runtime.TokenStream;
import groovyjarjarantlr4.runtime.debug.DebugEventListener;
import groovyjarjarantlr4.runtime.tree.TreeAdaptor;

public class DebugTreeAdaptor
implements TreeAdaptor {
    protected DebugEventListener dbg;
    protected TreeAdaptor adaptor;

    public DebugTreeAdaptor(DebugEventListener dbg2, TreeAdaptor adaptor) {
        this.dbg = dbg2;
        this.adaptor = adaptor;
    }

    @Override
    public Object create(Token payload) {
        if (payload.getTokenIndex() < 0) {
            return this.create(payload.getType(), payload.getText());
        }
        Object node = this.adaptor.create(payload);
        this.dbg.createNode(node, payload);
        return node;
    }

    @Override
    public Object errorNode(TokenStream input, Token start, Token stop, RecognitionException e) {
        Object node = this.adaptor.errorNode(input, start, stop, e);
        if (node != null) {
            this.dbg.errorNode(node);
        }
        return node;
    }

    @Override
    public Object dupTree(Object tree) {
        Object t = this.adaptor.dupTree(tree);
        this.simulateTreeConstruction(t);
        return t;
    }

    protected void simulateTreeConstruction(Object t) {
        this.dbg.createNode(t);
        int n = this.adaptor.getChildCount(t);
        for (int i = 0; i < n; ++i) {
            Object child = this.adaptor.getChild(t, i);
            this.simulateTreeConstruction(child);
            this.dbg.addChild(t, child);
        }
    }

    @Override
    public Object dupNode(Object treeNode) {
        Object d = this.adaptor.dupNode(treeNode);
        this.dbg.createNode(d);
        return d;
    }

    @Override
    public Object nil() {
        Object node = this.adaptor.nil();
        this.dbg.nilNode(node);
        return node;
    }

    @Override
    public boolean isNil(Object tree) {
        return this.adaptor.isNil(tree);
    }

    @Override
    public void addChild(Object t, Object child) {
        if (t == null || child == null) {
            return;
        }
        this.adaptor.addChild(t, child);
        this.dbg.addChild(t, child);
    }

    @Override
    public Object becomeRoot(Object newRoot, Object oldRoot) {
        Object n = this.adaptor.becomeRoot(newRoot, oldRoot);
        this.dbg.becomeRoot(newRoot, oldRoot);
        return n;
    }

    @Override
    public Object rulePostProcessing(Object root) {
        return this.adaptor.rulePostProcessing(root);
    }

    public void addChild(Object t, Token child) {
        Object n = this.create(child);
        this.addChild(t, n);
    }

    @Override
    public Object becomeRoot(Token newRoot, Object oldRoot) {
        Object n = this.create(newRoot);
        this.adaptor.becomeRoot(n, oldRoot);
        this.dbg.becomeRoot(newRoot, oldRoot);
        return n;
    }

    @Override
    public Object create(int tokenType, Token fromToken) {
        Object node = this.adaptor.create(tokenType, fromToken);
        this.dbg.createNode(node);
        return node;
    }

    @Override
    public Object create(int tokenType, Token fromToken, String text) {
        Object node = this.adaptor.create(tokenType, fromToken, text);
        this.dbg.createNode(node);
        return node;
    }

    @Override
    public Object create(int tokenType, String text) {
        Object node = this.adaptor.create(tokenType, text);
        this.dbg.createNode(node);
        return node;
    }

    @Override
    public int getType(Object t) {
        return this.adaptor.getType(t);
    }

    @Override
    public void setType(Object t, int type) {
        this.adaptor.setType(t, type);
    }

    @Override
    public String getText(Object t) {
        return this.adaptor.getText(t);
    }

    @Override
    public void setText(Object t, String text) {
        this.adaptor.setText(t, text);
    }

    @Override
    public Token getToken(Object t) {
        return this.adaptor.getToken(t);
    }

    @Override
    public void setTokenBoundaries(Object t, Token startToken, Token stopToken) {
        this.adaptor.setTokenBoundaries(t, startToken, stopToken);
        if (t != null && startToken != null && stopToken != null) {
            this.dbg.setTokenBoundaries(t, startToken.getTokenIndex(), stopToken.getTokenIndex());
        }
    }

    @Override
    public int getTokenStartIndex(Object t) {
        return this.adaptor.getTokenStartIndex(t);
    }

    @Override
    public int getTokenStopIndex(Object t) {
        return this.adaptor.getTokenStopIndex(t);
    }

    @Override
    public Object getChild(Object t, int i) {
        return this.adaptor.getChild(t, i);
    }

    @Override
    public void setChild(Object t, int i, Object child) {
        this.adaptor.setChild(t, i, child);
    }

    @Override
    public Object deleteChild(Object t, int i) {
        return this.adaptor.deleteChild(t, i);
    }

    @Override
    public int getChildCount(Object t) {
        return this.adaptor.getChildCount(t);
    }

    @Override
    public int getUniqueID(Object node) {
        return this.adaptor.getUniqueID(node);
    }

    @Override
    public Object getParent(Object t) {
        return this.adaptor.getParent(t);
    }

    @Override
    public int getChildIndex(Object t) {
        return this.adaptor.getChildIndex(t);
    }

    @Override
    public void setParent(Object t, Object parent) {
        this.adaptor.setParent(t, parent);
    }

    @Override
    public void setChildIndex(Object t, int index) {
        this.adaptor.setChildIndex(t, index);
    }

    @Override
    public void replaceChildren(Object parent, int startChildIndex, int stopChildIndex, Object t) {
        this.adaptor.replaceChildren(parent, startChildIndex, stopChildIndex, t);
    }

    public DebugEventListener getDebugListener() {
        return this.dbg;
    }

    public void setDebugListener(DebugEventListener dbg2) {
        this.dbg = dbg2;
    }

    public TreeAdaptor getTreeAdaptor() {
        return this.adaptor;
    }
}

