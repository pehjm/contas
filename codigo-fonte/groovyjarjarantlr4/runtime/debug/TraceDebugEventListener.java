/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime.debug;

import groovyjarjarantlr4.runtime.Token;
import groovyjarjarantlr4.runtime.debug.BlankDebugEventListener;
import groovyjarjarantlr4.runtime.tree.TreeAdaptor;

public class TraceDebugEventListener
extends BlankDebugEventListener {
    TreeAdaptor adaptor;

    public TraceDebugEventListener(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public void enterRule(String ruleName) {
        System.out.println("enterRule " + ruleName);
    }

    public void exitRule(String ruleName) {
        System.out.println("exitRule " + ruleName);
    }

    @Override
    public void enterSubRule(int decisionNumber) {
        System.out.println("enterSubRule");
    }

    @Override
    public void exitSubRule(int decisionNumber) {
        System.out.println("exitSubRule");
    }

    @Override
    public void location(int line, int pos) {
        System.out.println("location " + line + ":" + pos);
    }

    @Override
    public void consumeNode(Object t) {
        int ID = this.adaptor.getUniqueID(t);
        String text = this.adaptor.getText(t);
        int type = this.adaptor.getType(t);
        System.out.println("consumeNode " + ID + " " + text + " " + type);
    }

    @Override
    public void LT(int i, Object t) {
        int ID = this.adaptor.getUniqueID(t);
        String text = this.adaptor.getText(t);
        int type = this.adaptor.getType(t);
        System.out.println("LT " + i + " " + ID + " " + text + " " + type);
    }

    @Override
    public void nilNode(Object t) {
        System.out.println("nilNode " + this.adaptor.getUniqueID(t));
    }

    @Override
    public void createNode(Object t) {
        int ID = this.adaptor.getUniqueID(t);
        String text = this.adaptor.getText(t);
        int type = this.adaptor.getType(t);
        System.out.println("create " + ID + ": " + text + ", " + type);
    }

    @Override
    public void createNode(Object node, Token token) {
        int ID = this.adaptor.getUniqueID(node);
        String text = this.adaptor.getText(node);
        int tokenIndex = token.getTokenIndex();
        System.out.println("create " + ID + ": " + tokenIndex);
    }

    @Override
    public void becomeRoot(Object newRoot, Object oldRoot) {
        System.out.println("becomeRoot " + this.adaptor.getUniqueID(newRoot) + ", " + this.adaptor.getUniqueID(oldRoot));
    }

    @Override
    public void addChild(Object root, Object child) {
        System.out.println("addChild " + this.adaptor.getUniqueID(root) + ", " + this.adaptor.getUniqueID(child));
    }

    @Override
    public void setTokenBoundaries(Object t, int tokenStartIndex, int tokenStopIndex) {
        System.out.println("setTokenBoundaries " + this.adaptor.getUniqueID(t) + ", " + tokenStartIndex + ", " + tokenStopIndex);
    }
}

