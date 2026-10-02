/*
 * Decompiled with CFR 0.152.
 */
package groovyjarjarantlr4.runtime.tree;

public interface PositionTrackingStream<T> {
    public T getKnownPositionElement(boolean var1);

    public boolean hasPositionInformation(T var1);
}

