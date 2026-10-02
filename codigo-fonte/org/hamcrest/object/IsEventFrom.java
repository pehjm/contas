/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.object;

import java.util.EventObject;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsEventFrom
extends TypeSafeDiagnosingMatcher<EventObject> {
    private final Class<?> eventClass;
    private final Object source;

    public IsEventFrom(Class<?> eventClass, Object source) {
        this.eventClass = eventClass;
        this.source = source;
    }

    @Override
    public boolean matchesSafely(EventObject item, Description mismatchDescription) {
        if (!this.eventClass.isInstance(item)) {
            mismatchDescription.appendText("item type was " + item.getClass().getName());
            return false;
        }
        if (!this.eventHasSameSource(item)) {
            mismatchDescription.appendText("source was ").appendValue(item.getSource());
            return false;
        }
        return true;
    }

    private boolean eventHasSameSource(EventObject ev) {
        return ev.getSource() == this.source;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an event of type ").appendText(this.eventClass.getName()).appendText(" from ").appendValue(this.source);
    }

    public static Matcher<EventObject> eventFrom(Class<? extends EventObject> eventClass, Object source) {
        return new IsEventFrom(eventClass, source);
    }

    public static Matcher<EventObject> eventFrom(Object source) {
        return IsEventFrom.eventFrom(EventObject.class, source);
    }
}

