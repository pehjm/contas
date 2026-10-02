/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.beans.PropertyUtil;
import org.hamcrest.core.IsEqual;

public class SamePropertyValuesAs<T>
extends DiagnosingMatcher<T> {
    private final T expectedBean;
    private final Set<String> propertyNames;
    private final List<PropertyMatcher> propertyMatchers;
    private final List<String> ignoredFields;

    public SamePropertyValuesAs(T expectedBean, List<String> ignoredProperties) {
        PropertyDescriptor[] descriptors = PropertyUtil.propertyDescriptorsFor(expectedBean, Object.class);
        this.expectedBean = expectedBean;
        this.ignoredFields = ignoredProperties;
        this.propertyNames = SamePropertyValuesAs.propertyNamesFrom(descriptors, ignoredProperties);
        this.propertyMatchers = SamePropertyValuesAs.propertyMatchersFor(expectedBean, descriptors, ignoredProperties);
    }

    @Override
    protected boolean matches(Object actual, Description mismatch) {
        return SamePropertyValuesAs.isNotNull(actual, mismatch) && this.isCompatibleType(actual, mismatch) && this.hasNoExtraProperties(actual, mismatch) && this.hasMatchingValues(actual, mismatch);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("same property values as " + this.expectedBean.getClass().getSimpleName()).appendList(" [", ", ", "]", this.propertyMatchers);
        if (!this.ignoredFields.isEmpty()) {
            description.appendText(" ignoring ").appendValueList("[", ", ", "]", this.ignoredFields);
        }
    }

    private boolean isCompatibleType(Object actual, Description mismatchDescription) {
        if (this.expectedBean.getClass().isAssignableFrom(actual.getClass())) {
            return true;
        }
        mismatchDescription.appendText("is incompatible type: " + actual.getClass().getSimpleName());
        return false;
    }

    private boolean hasNoExtraProperties(Object actual, Description mismatchDescription) {
        Set<String> actualPropertyNames = SamePropertyValuesAs.propertyNamesFrom(PropertyUtil.propertyDescriptorsFor(actual, Object.class), this.ignoredFields);
        actualPropertyNames.removeAll(this.propertyNames);
        if (!actualPropertyNames.isEmpty()) {
            mismatchDescription.appendText("has extra properties called " + actualPropertyNames);
            return false;
        }
        return true;
    }

    private boolean hasMatchingValues(Object actual, Description mismatchDescription) {
        for (PropertyMatcher propertyMatcher : this.propertyMatchers) {
            if (propertyMatcher.matches(actual)) continue;
            propertyMatcher.describeMismatch(actual, mismatchDescription);
            return false;
        }
        return true;
    }

    private static <T> List<PropertyMatcher> propertyMatchersFor(T bean, PropertyDescriptor[] descriptors, List<String> ignoredFields) {
        ArrayList<PropertyMatcher> result = new ArrayList<PropertyMatcher>(descriptors.length);
        for (PropertyDescriptor propertyDescriptor : descriptors) {
            if (!SamePropertyValuesAs.isIgnored(ignoredFields, propertyDescriptor)) continue;
            result.add(new PropertyMatcher(propertyDescriptor, bean));
        }
        return result;
    }

    private static Set<String> propertyNamesFrom(PropertyDescriptor[] descriptors, List<String> ignoredFields) {
        HashSet<String> result = new HashSet<String>();
        for (PropertyDescriptor propertyDescriptor : descriptors) {
            if (!SamePropertyValuesAs.isIgnored(ignoredFields, propertyDescriptor)) continue;
            result.add(propertyDescriptor.getDisplayName());
        }
        return result;
    }

    private static boolean isIgnored(List<String> ignoredFields, PropertyDescriptor propertyDescriptor) {
        return !ignoredFields.contains(propertyDescriptor.getDisplayName());
    }

    private static Object readProperty(Method method, Object target) {
        try {
            return method.invoke(target, PropertyUtil.NO_ARGUMENTS);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not invoke " + method + " on " + target, e);
        }
    }

    public static <B> Matcher<B> samePropertyValuesAs(B expectedBean, String ... ignoredProperties) {
        return new SamePropertyValuesAs<B>(expectedBean, Arrays.asList(ignoredProperties));
    }

    private static class PropertyMatcher
    extends DiagnosingMatcher<Object> {
        private final Method readMethod;
        private final Matcher<Object> matcher;
        private final String propertyName;

        public PropertyMatcher(PropertyDescriptor descriptor, Object expectedObject) {
            this.propertyName = descriptor.getDisplayName();
            this.readMethod = descriptor.getReadMethod();
            this.matcher = IsEqual.equalTo(SamePropertyValuesAs.readProperty(this.readMethod, expectedObject));
        }

        @Override
        public boolean matches(Object actual, Description mismatch) {
            Object actualValue = SamePropertyValuesAs.readProperty(this.readMethod, actual);
            if (!this.matcher.matches(actualValue)) {
                mismatch.appendText(this.propertyName + " ");
                this.matcher.describeMismatch(actualValue, mismatch);
                return false;
            }
            return true;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText(this.propertyName + ": ").appendDescriptionOf(this.matcher);
        }
    }
}

