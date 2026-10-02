/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.filter;

import io.restassured.filter.Filter;

public interface OrderedFilter
extends Filter {
    public static final int DEFAULT_PRECEDENCE = 1000;
    public static final int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;
    public static final int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    public int getOrder();
}

