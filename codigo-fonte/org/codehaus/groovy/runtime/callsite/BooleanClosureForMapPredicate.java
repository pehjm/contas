/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime.callsite;

import groovy.lang.Closure;
import java.util.Map;
import java.util.function.Predicate;
import org.codehaus.groovy.runtime.callsite.BooleanReturningMethodInvoker;

public class BooleanClosureForMapPredicate<K, V>
implements Predicate<Map.Entry<K, V>> {
    private final BooleanReturningMethodInvoker bmi;
    private final Closure wrapped;
    private final int numberOfArguments;

    public BooleanClosureForMapPredicate(Closure wrapped) {
        this.wrapped = wrapped;
        this.bmi = new BooleanReturningMethodInvoker("call");
        this.numberOfArguments = wrapped.getMaximumNumberOfParameters();
    }

    private boolean call(Object ... args) {
        return this.bmi.invoke(this.wrapped, args);
    }

    @Override
    public boolean test(Map.Entry<K, V> entry) {
        if (this.numberOfArguments == 2) {
            return this.call(entry.getKey(), entry.getValue());
        }
        return this.call(entry);
    }
}

