/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.internal;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsNull;

public class NullSafety {
    public static <E> List<Matcher<? super E>> nullSafe(Matcher<? super E>[] itemMatchers) {
        ArrayList<Matcher<Matcher<Object>>> matchers = new ArrayList<Matcher<Matcher<Object>>>(itemMatchers.length);
        for (Matcher<E> matcher : itemMatchers) {
            matchers.add(matcher == null ? IsNull.nullValue() : matcher);
        }
        return matchers;
    }
}

