/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.Map;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

public final class IsMapWithSize<K, V>
extends FeatureMatcher<Map<? extends K, ? extends V>, Integer> {
    public IsMapWithSize(Matcher<? super Integer> sizeMatcher) {
        super(sizeMatcher, "a map with size", "map size");
    }

    @Override
    protected Integer featureValueOf(Map<? extends K, ? extends V> actual) {
        return actual.size();
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> aMapWithSize(Matcher<? super Integer> sizeMatcher) {
        return new IsMapWithSize<K, V>(sizeMatcher);
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> aMapWithSize(int size) {
        return IsMapWithSize.aMapWithSize(IsEqual.equalTo(size));
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> anEmptyMap() {
        return IsMapWithSize.aMapWithSize(IsEqual.equalTo(0));
    }
}

