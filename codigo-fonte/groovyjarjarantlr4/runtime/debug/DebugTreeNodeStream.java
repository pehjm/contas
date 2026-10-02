/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime.debug;

import groovyjarjarantlr4.runtime.TokenStream;
import groovyjarjarantlr4.runtime.debug.DebugEventListener;
import groovyjarjarantlr4.runtime.tree.TreeAdaptor;
import groovyjarjarantlr4.runtime.tree.TreeNodeStream;

public class DebugTreeNodeStream
implements TreeNodeStream {
    protected DebugEventListener dbg;
    protected TreeAdaptor adaptor;
    protected TreeNodeStream input;
    protected boolean initialStreamState = true;
    protected int lastMarker;

    public DebugTreeNodeStream(TreeNodeStream input, DebugEventListener dbg2) {
        this.input = input;
        this.adaptor = input.getTreeAdaptor();
        this.input.setUniqueNavigationNodes(true);
        this.setDebugListener(dbg2);
    }

    public void setDebugListener(DebugEventListener dbg2) {
        this.dbg = dbg2;
    }

    @Override
    public TreeAdaptor getTreeAdaptor() {
        return this.adaptor;
    }

    @Override
    public void consume() {
        Object node = this.input.LT(1);
        this.input.consume();
        this.dbg.consumeNode(node);
    }

    @Override
    public Object get(int i) {
        return this.input.get(i);
    }

    @Override
    public Object LT(int i) {
        Object node = this.input.LT(i);
        int ID = this.adaptor.getUniqueID(node);
        String text = this.adaptor.getText(node);
        int type = this.adaptor.getType(node);
        this.dbg.LT(i, node);
        return node;
    }

    @Override
    public int LA(int i) {
        Object node = this.input.LT(i);
        int ID = this.adaptor.getUniqueID(node);
        String text = this.adaptor.getText(node);
        int type = this.adaptor.getType(node);
        this.dbg.LT(i, node);
        return type;
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
    public void reset() {
    }

    @Override
    public Object getTreeSource() {
        return this.input;
    }

    @Override
    public String getSourceName() {
        return this.getTokenStream().getSourceName();
    }

    @Override
    public TokenStream getTokenStream() {
        return this.input.getTokenStream();
    }

    @Override
    public void setUniqueNavigationNodes(boolean uniqueNavigationNodes) {
        this.input.setUniqueNavigationNodes(uniqueNavigationNodes);
    }

    @Override
    public void replaceChildren(Object parent, int startChildIndex, int stopChildIndex, Object t) {
        this.input.replaceChildren(parent, startChildIndex, stopChildIndex, t);
    }

    @Override
    public String toString(Object start, Object stop) {
        return this.input.toString(start, stop);
    }
}

