/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.object;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsEqual;

public class HasEqualValues<T>
extends TypeSafeDiagnosingMatcher<T> {
    private final T expectedObject;
    private final List<FieldMatcher> fieldMatchers;

    public HasEqualValues(T expectedObject) {
        super(expectedObject.getClass());
        this.expectedObject = expectedObject;
        this.fieldMatchers = HasEqualValues.fieldMatchers(expectedObject);
    }

    @Override
    protected boolean matchesSafely(T item, Description mismatch) {
        for (FieldMatcher fieldMatcher : this.fieldMatchers) {
            if (fieldMatcher.matches(item, mismatch)) continue;
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(this.expectedObject.getClass().getSimpleName()).appendText(" has values ").appendList("[", ", ", "]", this.fieldMatchers);
    }

    private static List<FieldMatcher> fieldMatchers(Object expectedObject) {
        ArrayList<FieldMatcher> result = new ArrayList<FieldMatcher>();
        for (Field field : expectedObject.getClass().getFields()) {
            result.add(new FieldMatcher(field, expectedObject));
        }
        return result;
    }

    private static Object uncheckedGet(Field field, Object object) {
        try {
            return field.get(object);
        }
        catch (Exception e) {
            throw new AssertionError((Object)String.format("IllegalAccess, reading field '%s' from %s", field.getName(), object));
        }
    }

    private static class FieldMatcher
    extends DiagnosingMatcher<Object> {
        private final Field field;
        private final Matcher<Object> matcher;

        public FieldMatcher(Field field, Object expectedObject) {
            this.field = field;
            this.matcher = IsEqual.equalTo(HasEqualValues.uncheckedGet(field, expectedObject));
        }

        @Override
        protected boolean matches(Object item, Description mismatch) {
            Object actual = HasEqualValues.uncheckedGet(this.field, item);
            if (!this.matcher.matches(actual)) {
                mismatch.appendText("'").appendText(this.field.getName()).appendText("' ");
                this.matcher.describeMismatch(actual, mismatch);
                return false;
            }
            return true;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText(this.field.getName()).appendText(": ").appendDescriptionOf(this.matcher);
        }
    }
}

