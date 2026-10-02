/*
 * Decompiled with CFR 0.152.
 */
package io.restassured.specification;

public class Argument {
    private final Object argument;

    public Argument(Object argument) {
        this.argument = argument;
    }

    public Object getArgument() {
        return this.argument;
    }

    public static Argument withArg(Object arg) {
        return new Argument(arg);
    }

    public static Argument arg(Object arg) {
        return new Argument(arg);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Argument argument1 = (Argument)o;
        return !(this.argument != null ? !this.argument.equals(argument1.argument) : argument1.argument != null);
    }

    public int hashCode() {
        return this.argument != null ? this.argument.hashCode() : 0;
    }
}

