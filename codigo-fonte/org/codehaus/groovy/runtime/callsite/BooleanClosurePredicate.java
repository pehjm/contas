/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime.callsite;

import groovy.lang.Closure;
import java.util.function.Predicate;
import org.codehaus.groovy.runtime.callsite.BooleanReturningMethodInvoker;

public class BooleanClosurePredicate<T>
implements Predicate<T> {
    private final BooleanReturningMethodInvoker bmi;
    private final Closure wrapped;

    public BooleanClosurePredicate(Closure wrapped) {
        this.wrapped = wrapped;
        this.bmi = new BooleanReturningMethodInvoker("call");
    }

    @Override
    public boolean test(T arg) {
        return this.bmi.invoke(this.wrapped, arg);
    }
}

