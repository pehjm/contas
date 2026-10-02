/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.GString;
import groovy.lang.GroovyObject;
import groovy.transform.Pure;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Locale;
import org.apache.groovy.ast.tools.ImmutablePropertyUtils;
import org.codehaus.groovy.runtime.GStringUtil;

public class GStringImpl
extends GString {
    private static final long serialVersionUID = 3581289038662723858L;
    private final String[] strings;
    private final boolean frozen;
    private boolean cacheable;
    private String cachedStringLiteral;

    public GStringImpl(Object[] values, String[] strings) {
        this(values, strings, GStringImpl.checkValuesStringConstant(values), null, false);
    }

    protected GStringImpl(Object[] values, String[] strings, boolean cacheable, String cachedStringLiteral, boolean frozen) {
        super(frozen ? (Object[])values.clone() : values);
        this.strings = frozen ? (String[])strings.clone() : strings;
        this.cacheable = cacheable;
        this.cachedStringLiteral = cachedStringLiteral;
        this.frozen = frozen;
    }

    @Override
    public GString plus(GString that) {
        GString thatFrozen = that instanceof GStringImpl ? ((GStringImpl)that).freeze() : that;
        return GStringUtil.plusImpl(super.getValues(), thatFrozen.getValues(), this.strings, thatFrozen.getStrings());
    }

    @Override
    public Writer writeTo(Writer out) throws IOException {
        return GStringUtil.writeToImpl(out, super.getValues(), this.strings);
    }

    @Override
    public void build(GroovyObject builder) {
        GStringUtil.buildImpl(builder, super.getValues(), this.strings);
    }

    @Override
    protected int calcInitialCapacity() {
        return GStringUtil.calcInitialCapacityImpl(super.getValues(), this.strings);
    }

    public GString freeze() {
        return new GStringImpl(super.getValues(), this.strings, this.cacheable, this.cachedStringLiteral, true);
    }

    @Override
    public String[] getStrings() {
        if (this.frozen) {
            return (String[])this.strings.clone();
        }
        this.cacheable = false;
        this.cachedStringLiteral = null;
        return this.strings;
    }

    @Override
    public Object[] getValues() {
        if (this.frozen) {
            return (Object[])super.getValues().clone();
        }
        this.cacheable = false;
        this.cachedStringLiteral = null;
        return super.getValues();
    }

    public String trim() {
        return this.toString().trim();
    }

    @Override
    public boolean isEmpty() {
        return this.toString().isEmpty();
    }

    public int codePointAt(int index) {
        return this.toString().codePointAt(index);
    }

    public int codePointBefore(int index) {
        return this.toString().codePointBefore(index);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return this.toString().codePointCount(beginIndex, endIndex);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return this.toString().offsetByCodePoints(index, codePointOffset);
    }

    @Override
    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        this.toString().getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    public byte[] getBytes(Charset charset) {
        return this.toString().getBytes(charset);
    }

    public boolean contentEquals(StringBuffer sb) {
        return this.toString().contentEquals(sb);
    }

    public boolean contentEquals(CharSequence cs) {
        return this.toString().contentEquals(cs);
    }

    public boolean equalsIgnoreCase(String anotherString) {
        return this.toString().equalsIgnoreCase(anotherString);
    }

    public int compareTo(String anotherString) {
        return this.toString().compareTo(anotherString);
    }

    public int compareToIgnoreCase(String str) {
        return this.toString().compareToIgnoreCase(str);
    }

    public boolean regionMatches(int toffset, String other, int ooffset, int len) {
        return this.toString().regionMatches(toffset, other, ooffset, len);
    }

    public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) {
        return this.toString().regionMatches(ignoreCase, toffset, other, ooffset, len);
    }

    public boolean startsWith(String prefix, int toffset) {
        return this.toString().startsWith(prefix, toffset);
    }

    public boolean startsWith(String prefix) {
        return this.toString().startsWith(prefix);
    }

    public boolean endsWith(String suffix) {
        return this.toString().endsWith(suffix);
    }

    public int indexOf(int ch) {
        return this.toString().indexOf(ch);
    }

    public int indexOf(int ch, int fromIndex) {
        return this.toString().indexOf(ch, fromIndex);
    }

    public int lastIndexOf(int ch) {
        return this.toString().lastIndexOf(ch);
    }

    public int lastIndexOf(int ch, int fromIndex) {
        return this.toString().lastIndexOf(ch, fromIndex);
    }

    public int indexOf(String str) {
        return this.toString().indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return this.toString().indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return this.toString().lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return this.toString().lastIndexOf(str, fromIndex);
    }

    public String substring(int beginIndex) {
        return this.toString().substring(beginIndex);
    }

    public String substring(int beginIndex, int endIndex) {
        return this.toString().substring(beginIndex, endIndex);
    }

    public String concat(String str) {
        return this.toString().concat(str);
    }

    public String replace(char oldChar, char newChar) {
        return this.toString().replace(oldChar, newChar);
    }

    public boolean matches(String regex) {
        return this.toString().matches(regex);
    }

    public boolean contains(CharSequence s) {
        return this.toString().contains(s);
    }

    public String replaceFirst(String regex, String replacement) {
        return this.toString().replaceFirst(regex, replacement);
    }

    public String replaceAll(String regex, String replacement) {
        return this.toString().replaceAll(regex, replacement);
    }

    public String replace(CharSequence target, CharSequence replacement) {
        return this.toString().replace(target, replacement);
    }

    public String[] split(String regex, int limit) {
        return this.toString().split(regex, limit);
    }

    public String[] split(String regex) {
        return this.toString().split(regex);
    }

    public String toLowerCase(Locale locale) {
        return this.toString().toLowerCase(locale);
    }

    public String toLowerCase() {
        return this.toString().toLowerCase();
    }

    public String toUpperCase(Locale locale) {
        return this.toString().toUpperCase(locale);
    }

    public String toUpperCase() {
        return this.toString().toUpperCase();
    }

    public char[] toCharArray() {
        return this.toString().toCharArray();
    }

    public String intern() {
        return this.toString().intern();
    }

    @Override
    public String toString() {
        if (null != this.cachedStringLiteral) {
            return this.cachedStringLiteral;
        }
        String str = super.toString();
        if (this.cacheable) {
            this.cachedStringLiteral = str;
        }
        return str;
    }

    private static boolean checkValuesStringConstant(Object[] values) {
        for (Object value : values) {
            if (null == value || ImmutablePropertyUtils.builtinOrMarkedImmutableClass(value.getClass()) || GStringImpl.toStringMarkedPure(value.getClass()) || value instanceof GStringImpl && ((GStringImpl)value).cacheable) continue;
            return false;
        }
        return true;
    }

    private static boolean toStringMarkedPure(Class<?> clazz) {
        Method toStringMethod;
        try {
            toStringMethod = clazz.getMethod("toString", new Class[0]);
        }
        catch (NoSuchMethodException | SecurityException e) {
            return false;
        }
        return toStringMethod.isAnnotationPresent(Pure.class);
    }
}

