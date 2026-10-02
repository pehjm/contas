/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.collection;

import java.util.Map;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsAnything;
import org.hamcrest.core.IsEqual;

public class IsMapContaining<K, V>
extends TypeSafeMatcher<Map<? extends K, ? extends V>> {
    private final Matcher<? super K> keyMatcher;
    private final Matcher<? super V> valueMatcher;

    public IsMapContaining(Matcher<? super K> keyMatcher, Matcher<? super V> valueMatcher) {
        this.keyMatcher = keyMatcher;
        this.valueMatcher = valueMatcher;
    }

    @Override
    public boolean matchesSafely(Map<? extends K, ? extends V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!this.keyMatcher.matches(entry.getKey()) || !this.valueMatcher.matches(entry.getValue())) continue;
            return true;
        }
        return false;
    }

    @Override
    public void describeMismatchSafely(Map<? extends K, ? extends V> map, Description mismatchDescription) {
        mismatchDescription.appendText("map was ").appendValueList("[", ", ", "]", map.entrySet());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("map containing [").appendDescriptionOf(this.keyMatcher).appendText("->").appendDescriptionOf(this.valueMatcher).appendText("]");
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> hasEntry(Matcher<? super K> keyMatcher, Matcher<? super V> valueMatcher) {
        return new IsMapContaining<K, V>(keyMatcher, valueMatcher);
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> hasEntry(K key, V value) {
        return new IsMapContaining<K, V>(IsEqual.equalTo(key), IsEqual.equalTo(value));
    }

    public static <K> Matcher<Map<? extends K, ?>> hasKey(Matcher<? super K> keyMatcher) {
        return new IsMapContaining<K, Object>(keyMatcher, IsAnything.anything());
    }

    public static <K> Matcher<Map<? extends K, ?>> hasKey(K key) {
        return new IsMapContaining<K, Object>(IsEqual.equalTo(key), IsAnything.anything());
    }

    public static <V> Matcher<Map<?, ? extends V>> hasValue(Matcher<? super V> valueMatcher) {
        return new IsMapContaining<Object, V>(IsAnything.anything(), valueMatcher);
    }

    public static <V> Matcher<Map<?, ? extends V>> hasValue(V value) {
        return new IsMapContaining<Object, V>(IsAnything.anything(), IsEqual.equalTo(value));
    }
}

