/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import org.hamcrest.Condition;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.beans.PropertyUtil;

public class HasPropertyWithValue<T>
extends TypeSafeDiagnosingMatcher<T> {
    private static final Condition.Step<PropertyDescriptor, Method> WITH_READ_METHOD = HasPropertyWithValue.withReadMethod();
    private final String propertyName;
    private final Matcher<Object> valueMatcher;
    private final String messageFormat;

    public HasPropertyWithValue(String propertyName, Matcher<?> valueMatcher) {
        this(propertyName, valueMatcher, " property '%s' ");
    }

    public HasPropertyWithValue(String propertyName, Matcher<?> valueMatcher, String messageFormat) {
        this.propertyName = propertyName;
        this.valueMatcher = HasPropertyWithValue.nastyGenericsWorkaround(valueMatcher);
        this.messageFormat = messageFormat;
    }

    @Override
    public boolean matchesSafely(T bean, Description mismatch) {
        return this.propertyOn(bean, mismatch).and(WITH_READ_METHOD).and(this.withPropertyValue(bean)).matching(this.valueMatcher, String.format(this.messageFormat, this.propertyName));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("hasProperty(").appendValue(this.propertyName).appendText(", ").appendDescriptionOf(this.valueMatcher).appendText(")");
    }

    private Condition<PropertyDescriptor> propertyOn(T bean, Description mismatch) {
        PropertyDescriptor property = PropertyUtil.getPropertyDescriptor(this.propertyName, bean);
        if (property == null) {
            mismatch.appendText("No property \"" + this.propertyName + "\"");
            return Condition.notMatched();
        }
        return Condition.matched(property, mismatch);
    }

    private Condition.Step<Method, Object> withPropertyValue(final T bean) {
        return new Condition.Step<Method, Object>(){

            @Override
            public Condition<Object> apply(Method readMethod, Description mismatch) {
                try {
                    return Condition.matched(readMethod.invoke(bean, PropertyUtil.NO_ARGUMENTS), mismatch);
                }
                catch (InvocationTargetException e) {
                    mismatch.appendText("Calling '").appendText(readMethod.toString()).appendText("': ").appendValue(e.getTargetException().getMessage());
                    return Condition.notMatched();
                }
                catch (Exception e) {
                    throw new IllegalStateException("Calling: '" + readMethod + "' should not have thrown " + e);
                }
            }
        };
    }

    private static Matcher<Object> nastyGenericsWorkaround(Matcher<?> valueMatcher) {
        return valueMatcher;
    }

    private static Condition.Step<PropertyDescriptor, Method> withReadMethod() {
        return new Condition.Step<PropertyDescriptor, Method>(){

            @Override
            public Condition<Method> apply(PropertyDescriptor property, Description mismatch) {
                Method readMethod = property.getReadMethod();
                if (null == readMethod) {
                    mismatch.appendText("property \"" + property.getName() + "\" is not readable");
                    return Condition.notMatched();
                }
                return Condition.matched(readMethod, mismatch);
            }
        };
    }

    public static <T> Matcher<T> hasProperty(String propertyName, Matcher<?> valueMatcher) {
        return new HasPropertyWithValue<T>(propertyName, valueMatcher);
    }

    public static <T> Matcher<T> hasPropertyAtPath(String path, Matcher<T> valueMatcher) {
        List<String> properties = Arrays.asList(path.split("\\."));
        ListIterator<String> iterator = properties.listIterator(properties.size());
        Matcher<T> ret = valueMatcher;
        while (iterator.hasPrevious()) {
            ret = new HasPropertyWithValue<T>(iterator.previous(), ret, "%s.");
        }
        return ret;
    }
}

