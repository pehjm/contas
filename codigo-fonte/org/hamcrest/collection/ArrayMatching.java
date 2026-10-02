/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hamcrest.Matcher;
import org.hamcrest.collection.ArrayAsIterableMatcher;
import org.hamcrest.collection.HasItemInArray;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.core.IsEqual;
import org.hamcrest.internal.NullSafety;

public class ArrayMatching {
    public static <T> Matcher<T[]> hasItemInArray(Matcher<? super T> elementMatcher) {
        return new HasItemInArray<T>(elementMatcher);
    }

    public static <T> Matcher<T[]> hasItemInArray(T element) {
        return ArrayMatching.hasItemInArray(IsEqual.equalTo(element));
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Matcher<? super E> ... itemMatchers) {
        return ArrayMatching.arrayContainingInAnyOrder(Arrays.asList(itemMatchers));
    }

    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Collection<Matcher<? super E>> itemMatchers) {
        return new ArrayAsIterableMatcher(new IsIterableContainingInAnyOrder(itemMatchers), itemMatchers, "in any order");
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContainingInAnyOrder(E ... items) {
        return ArrayMatching.arrayContainingInAnyOrder(ArrayMatching.asEqualMatchers(items));
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContaining(E ... items) {
        return ArrayMatching.arrayContaining(ArrayMatching.asEqualMatchers(items));
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContaining(Matcher<? super E> ... itemMatchers) {
        List<Matcher<? super E>> nullSafeWithExplicitTypeMatchers = NullSafety.nullSafe(itemMatchers);
        return ArrayMatching.arrayContaining(nullSafeWithExplicitTypeMatchers);
    }

    public static <E> Matcher<E[]> arrayContaining(List<Matcher<? super E>> itemMatchers) {
        return new ArrayAsIterableMatcher<E>(new IsIterableContainingInOrder<E>(itemMatchers), itemMatchers, "");
    }

    public static <E> List<Matcher<? super E>> asEqualMatchers(E[] items) {
        ArrayList<Matcher<Matcher<E>>> matchers = new ArrayList<Matcher<Matcher<E>>>();
        for (E item : items) {
            matchers.add(IsEqual.equalTo(item));
        }
        return matchers;
    }
}

