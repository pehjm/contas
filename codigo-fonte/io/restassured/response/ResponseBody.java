/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.response;

import io.restassured.response.ResponseBodyExtractionOptions;

public interface ResponseBody<T extends ResponseBody<T>>
extends ResponseBodyExtractionOptions {
    public String print();

    public String prettyPrint();

    public T peek();

    public T prettyPeek();
}

