/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.GroovyRuntimeException;
import java.io.Serializable;
import java.util.Comparator;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class NumberAwareComparator<T>
implements Comparator<T>,
Serializable {
    private static final long serialVersionUID = 9017657289076651660L;
    private boolean ignoreZeroSign;

    public NumberAwareComparator() {
        this(false);
    }

    public NumberAwareComparator(boolean ignoreZeroSign) {
        this.ignoreZeroSign = ignoreZeroSign;
    }

    @Override
    public int compare(T o1, T o2) {
        try {
            if (this.ignoreZeroSign) {
                if (o1 instanceof Float && 0.0f == ((Float)o1).floatValue() && o2 instanceof Float && 0.0f == ((Float)o2).floatValue()) {
                    return 0;
                }
                if (o1 instanceof Double && 0.0 == (Double)o1 && o2 instanceof Double && 0.0 == (Double)o2) {
                    return 0;
                }
            }
            return DefaultTypeTransformation.compareTo(o1, o2);
        }
        catch (GroovyRuntimeException | ClassCastException | IllegalArgumentException runtimeException) {
            int x1 = o1.hashCode();
            int x2 = o2.hashCode();
            if (x1 == x2 && o1.equals(o2)) {
                return 0;
            }
            if (x1 > x2) {
                return 1;
            }
            return -1;
        }
    }
}

