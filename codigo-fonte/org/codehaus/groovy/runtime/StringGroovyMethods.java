/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import groovy.lang.EmptyRange;
import groovy.lang.GString;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.IntRange;
import groovy.lang.Range;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.FromString;
import groovy.transform.stc.PickFirstResolver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.groovy.io.StringBuilderWriter;
import org.apache.groovy.lang.annotation.Incubating;
import org.codehaus.groovy.ast.tools.ClosureUtils;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.DefaultGroovyMethodsSupport;
import org.codehaus.groovy.runtime.FormatHelper;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.IOGroovyMethods;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.RangeInfo;
import org.codehaus.groovy.runtime.RegexSupport;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.util.CharSequenceReader;

public class StringGroovyMethods
extends DefaultGroovyMethodsSupport {
    static String lineSeparator;

    public static boolean asBoolean(CharSequence chars) {
        if (chars != null) {
            return chars.length() > 0;
        }
        return false;
    }

    public static boolean asBoolean(Matcher matcher) {
        if (matcher != null) {
            RegexSupport.setLastMatcher(matcher);
            return matcher.find(0);
        }
        return false;
    }

    public static <T> T asType(CharSequence self, Class<T> c) {
        return StringGroovyMethods.asType(self.toString(), c);
    }

    public static <T> T asType(GString self, Class<T> c) {
        if (c == File.class) {
            return (T)new File(self.toString());
        }
        if (Number.class.isAssignableFrom(c) || c.isPrimitive()) {
            return StringGroovyMethods.asType(self.toString(), c);
        }
        return DefaultGroovyMethods.asType(self, c);
    }

    public static <T> T asType(String self, Class<T> c) {
        if (c == List.class) {
            return (T)StringGroovyMethods.toList(self);
        }
        if (c == BigDecimal.class) {
            return (T)StringGroovyMethods.toBigDecimal(self);
        }
        if (c == BigInteger.class) {
            return (T)StringGroovyMethods.toBigInteger(self);
        }
        if (c == Long.class || c == Long.TYPE) {
            return (T)StringGroovyMethods.toLong(self);
        }
        if (c == Integer.class || c == Integer.TYPE) {
            return (T)StringGroovyMethods.toInteger(self);
        }
        if (c == Short.class || c == Short.TYPE) {
            return (T)StringGroovyMethods.toShort(self);
        }
        if (c == Byte.class || c == Byte.TYPE) {
            return (T)Byte.valueOf(self.trim());
        }
        if (c == Character.class || c == Character.TYPE) {
            return (T)StringGroovyMethods.toCharacter(self);
        }
        if (c == Double.class || c == Double.TYPE) {
            return (T)StringGroovyMethods.toDouble(self);
        }
        if (c == Float.class || c == Float.TYPE) {
            return (T)StringGroovyMethods.toFloat(self);
        }
        if (c == File.class) {
            return (T)new File(self);
        }
        if (c.isEnum()) {
            return (T)InvokerHelper.invokeMethod(c, "valueOf", new Object[]{self});
        }
        return DefaultGroovyMethods.asType(self, c);
    }

    public static Pattern bitwiseNegate(CharSequence self) {
        return Pattern.compile(self.toString());
    }

    public static StringBuilder append(StringBuilder sb, GStringImpl gs) {
        return sb.append(gs.toString());
    }

    public static String uncapitalize(CharSequence self) {
        if (self.length() == 0) {
            return "";
        }
        return "" + Character.toLowerCase(self.charAt(0)) + self.subSequence(1, self.length());
    }

    public static String capitalize(CharSequence self) {
        if (self.length() == 0) {
            return "";
        }
        return "" + Character.toUpperCase(self.charAt(0)) + self.subSequence(1, self.length());
    }

    public static String center(CharSequence self, Number numberOfChars) {
        return StringGroovyMethods.center(self, numberOfChars, " ");
    }

    public static String center(CharSequence self, Number numberOfChars, CharSequence padding) {
        String semiPad;
        int numChars = numberOfChars.intValue();
        if (numChars <= self.length()) {
            return self.toString();
        }
        int charsToAdd = numChars - self.length();
        String string = semiPad = charsToAdd % 2 == 1 ? StringGroovyMethods.getPadding(padding, charsToAdd / 2 + 1) : StringGroovyMethods.getPadding(padding, charsToAdd / 2);
        if (charsToAdd % 2 == 0) {
            return semiPad + self + semiPad;
        }
        return semiPad.substring(0, charsToAdd / 2) + self + semiPad;
    }

    public static boolean contains(CharSequence self, CharSequence text) {
        int idx = self.toString().indexOf(text.toString());
        return idx >= 0;
    }

    public static int count(CharSequence self, CharSequence text) {
        int answer = 0;
        int idx = 0;
        while ((idx = self.toString().indexOf(text.toString(), idx)) >= answer) {
            ++answer;
            ++idx;
        }
        return answer;
    }

    public static String denormalize(CharSequence self) {
        CharSequence cs;
        int len;
        if (lineSeparator == null) {
            StringBuilderWriter sw = new StringBuilderWriter(2);
            try (BufferedWriter bw = new BufferedWriter(sw);){
                bw.newLine();
                bw.flush();
                lineSeparator = ((Object)sw).toString();
            }
            catch (IOException ioe) {
                lineSeparator = "\n";
            }
        }
        if ((len = self.length()) < 1) {
            return self.toString();
        }
        StringBuilder sb = new StringBuilder(110 * len / 100);
        int i = 0;
        CharSequence charSequence = cs = self instanceof GString ? self.toString() : self;
        block11: while (i < len) {
            char ch = cs.charAt(i++);
            switch (ch) {
                case '\r': {
                    sb.append(lineSeparator);
                    if (i >= len || cs.charAt(i) != '\n') continue block11;
                    ++i;
                    continue block11;
                }
                case '\n': {
                    sb.append(lineSeparator);
                    continue block11;
                }
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static CharSequence drop(CharSequence self, int num) {
        if (num <= 0) {
            return self;
        }
        if (self.length() <= num) {
            return self.subSequence(0, 0);
        }
        return self.subSequence(num, self.length());
    }

    public static String drop(GString self, int num) {
        return StringGroovyMethods.drop(self.toString(), num);
    }

    public static String drop(String self, int num) {
        return (String)StringGroovyMethods.drop((CharSequence)self, num);
    }

    public static String dropWhile(CharSequence self, @ClosureParams(value=FromString.class, conflictResolutionStrategy=PickFirstResolver.class, options={"String", "Character"}) Closure condition) {
        Iterator<Character> selfIter = ClosureUtils.hasSingleCharacterArg(condition) ? new CharacterIterator(self) : new StringIterator(self);
        return DefaultGroovyMethods.join(DefaultGroovyMethods.dropWhile(selfIter, condition), "");
    }

    @Deprecated
    public static /* bridge */ /* synthetic */ CharSequence dropWhile(CharSequence self, @ClosureParams(value=FromString.class, conflictResolutionStrategy=PickFirstResolver.class, options={"String", "Character"}) Closure condition) {
        return StringGroovyMethods.dropWhile(self, condition);
    }

    public static String dropWhile(GString self, @ClosureParams(value=FromString.class, conflictResolutionStrategy=PickFirstResolver.class, options={"String", "Character"}) Closure condition) {
        return StringGroovyMethods.dropWhile(self.toString(), condition);
    }

    public static <T> T eachLine(CharSequence self, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        return StringGroovyMethods.eachLine(self.toString(), 0, closure);
    }

    public static <T> T eachLine(CharSequence self, int firstLine, @ClosureParams(value=FromString.class, options={"String", "String,Integer"}) Closure<T> closure) throws IOException {
        int count = firstLine;
        T result = null;
        for (String line : new LineIterable(self)) {
            result = DefaultGroovyMethods.callClosureForLine(closure, line, count);
            ++count;
        }
        return result;
    }

    public static String collectReplacements(String self, @ClosureParams(value=FromString.class, conflictResolutionStrategy=PickFirstResolver.class, options={"String", "Character"}) Closure<String> transform) {
        if (self == null) {
            return self;
        }
        StringBuilder sb = null;
        int len = self.length();
        for (int i = 0; i < len; ++i) {
            char ch = self.charAt(i);
            String replacement = transform.call(ClosureUtils.hasSingleCharacterArg(transform) ? Character.valueOf(ch) : Character.toString(ch));
            if (replacement != null) {
                if (sb == null) {
                    sb = new StringBuilder((int)(1.1 * (double)len));
                    sb.append(self, 0, i);
                }
                sb.append(replacement);
                continue;
            }
            if (sb == null) continue;
            sb.append(ch);
        }
        return sb == null ? self : sb.toString();
    }

    public static String collectReplacements(String self, List<Function<Character, Optional<String>>> transforms) {
        if (self == null) {
            return self;
        }
        StringBuilder sb = null;
        int len = self.length();
        for (int i = 0; i < len; ++i) {
            Function<Character, Optional<String>> next;
            char ch = self.charAt(i);
            Optional<Object> replacement = Optional.empty();
            Iterator<Function<Character, Optional<String>>> iterator = transforms.iterator();
            while (iterator.hasNext() && !(replacement = (next = iterator.next()).apply(Character.valueOf(ch))).isPresent()) {
            }
            if (replacement.isPresent()) {
                if (sb == null) {
                    sb = new StringBuilder((int)(1.1 * (double)len));
                    sb.append(self, 0, i);
                }
                sb.append((String)replacement.get());
                continue;
            }
            if (sb == null) continue;
            sb.append(ch);
        }
        return sb == null ? self : sb.toString();
    }

    public static <T extends CharSequence> T eachMatch(T self, CharSequence regex, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}) Closure closure) {
        StringGroovyMethods.eachMatch(self.toString(), regex.toString(), closure);
        return self;
    }

    public static <T extends CharSequence> T eachMatch(T self, Pattern pattern, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}) Closure closure) {
        StringGroovyMethods.eachMatch(self.toString(), pattern, closure);
        return self;
    }

    public static String eachMatch(String self, Pattern pattern, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}) Closure closure) {
        Matcher m = pattern.matcher(self);
        DefaultGroovyMethods.each(m, closure);
        return self;
    }

    public static String eachMatch(String self, String regex, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}) Closure closure) {
        return StringGroovyMethods.eachMatch(self, Pattern.compile(regex), closure);
    }

    public static String expand(CharSequence self) {
        return StringGroovyMethods.expand(self, 8);
    }

    public static String expand(CharSequence self, int tabStop) {
        if (self.length() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String line : new LineIterable(self)) {
            builder.append(StringGroovyMethods.expandLine(line, tabStop));
            builder.append("\n");
        }
        if (self.charAt(self.length() - 1) != '\n') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static String expandLine(CharSequence self, int tabStop) {
        int index;
        String s = self.toString();
        while ((index = s.indexOf(9)) != -1) {
            StringBuilder builder = new StringBuilder(s);
            int count = tabStop - index % tabStop;
            builder.deleteCharAt(index);
            for (int i = 0; i < count; ++i) {
                builder.insert(index, " ");
            }
            s = builder.toString();
        }
        return s;
    }

    public static String find(CharSequence self, CharSequence regex) {
        return StringGroovyMethods.find(self, Pattern.compile(regex.toString()));
    }

    public static String find(CharSequence self, CharSequence regex, @ClosureParams(value=FromString.class, options={"java.util.List<java.lang.String>", "java.lang.String[]"}) Closure closure) {
        return StringGroovyMethods.find(self, Pattern.compile(regex.toString()), closure);
    }

    public static String find(CharSequence self, Pattern pattern) {
        Matcher matcher = pattern.matcher(self.toString());
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    public static String find(CharSequence self, Pattern pattern, @ClosureParams(value=FromString.class, options={"java.util.List<java.lang.String>", "java.lang.String[]"}) Closure closure) {
        Matcher matcher = pattern.matcher(self.toString());
        if (matcher.find()) {
            return StringGroovyMethods.getReplacement(matcher, closure);
        }
        return null;
    }

    public static List<String> findAll(CharSequence self, CharSequence regex) {
        return StringGroovyMethods.findAll(self, Pattern.compile(regex.toString()));
    }

    public static <T> List<T> findAll(CharSequence self, CharSequence regex, @ClosureParams(value=FromString.class, options={"java.util.List<java.lang.String>", "java.lang.String[]"}) Closure<T> closure) {
        return StringGroovyMethods.findAll(self, Pattern.compile(regex.toString()), closure);
    }

    public static List<String> findAll(CharSequence self, Pattern pattern) {
        Matcher matcher = pattern.matcher(self.toString());
        boolean hasGroup = StringGroovyMethods.hasGroup(matcher);
        ArrayList<String> list = new ArrayList<String>();
        Iterator iter = StringGroovyMethods.iterator(matcher);
        while (iter.hasNext()) {
            if (hasGroup) {
                list.add((String)((List)iter.next()).get(0));
                continue;
            }
            list.add((String)iter.next());
        }
        return new ArrayList<String>(list);
    }

    public static <T> List<T> findAll(CharSequence self, Pattern pattern, @ClosureParams(value=FromString.class, options={"java.util.List<java.lang.String>", "java.lang.String[]"}) Closure<T> closure) {
        Matcher matcher = pattern.matcher(self.toString());
        return DefaultGroovyMethods.collect(matcher, closure);
    }

    private static int findMinimumLeadingSpaces(String line, int count) {
        int index;
        int length = line.length();
        for (index = 0; index < length && index < count && Character.isWhitespace(line.charAt(index)); ++index) {
        }
        return index;
    }

    public static String getAt(CharSequence self, Collection indices) {
        StringBuilder answer = new StringBuilder();
        for (Object value : indices) {
            if (value instanceof Range) {
                answer.append(StringGroovyMethods.getAt(self, (Range)value));
                continue;
            }
            if (value instanceof Collection) {
                answer.append(StringGroovyMethods.getAt(self, (Collection)value));
                continue;
            }
            int idx = DefaultTypeTransformation.intUnbox(value);
            answer.append(StringGroovyMethods.getAt(self, idx));
        }
        return answer.toString();
    }

    public static String getAt(CharSequence self, EmptyRange range) {
        return "";
    }

    public static CharSequence getAt(CharSequence self, int index) {
        index = StringGroovyMethods.normaliseIndex(index, self.length());
        return self.subSequence(index, index + 1);
    }

    public static String getAt(GString self, int index) {
        return StringGroovyMethods.getAt(self.toString(), index);
    }

    public static CharSequence getAt(CharSequence self, IntRange range) {
        return StringGroovyMethods.getAt(self, (Range)range);
    }

    public static String getAt(GString self, IntRange range) {
        return StringGroovyMethods.getAt(self, (Range)range);
    }

    public static CharSequence getAt(CharSequence self, Range range) {
        RangeInfo info = StringGroovyMethods.subListBorders(self.length(), range);
        CharSequence sequence = self.subSequence(info.from, info.to);
        return info.reverse ? StringGroovyMethods.reverse(sequence) : sequence;
    }

    public static String getAt(GString self, Range range) {
        return StringGroovyMethods.getAt(self.toString(), range);
    }

    public static List getAt(Matcher self, Collection indices) {
        ArrayList<Object> result = new ArrayList<Object>();
        if (indices instanceof IntRange) {
            int size = (int)StringGroovyMethods.size(self);
            RangeInfo info = StringGroovyMethods.subListBorders(size, (Range)indices);
            indices = new IntRange(((IntRange)indices).getInclusive(), info.from, info.to - 1);
        }
        for (Object value : indices) {
            if (value instanceof Range) {
                result.addAll(StringGroovyMethods.getAt(self, (Collection)((Range)value)));
                continue;
            }
            int idx = DefaultTypeTransformation.intUnbox(value);
            result.add(StringGroovyMethods.getAt(self, idx));
        }
        return result;
    }

    public static Object getAt(Matcher self, int index) {
        try {
            int count = StringGroovyMethods.getCount(self);
            if (index < -count || index >= count) {
                throw new IndexOutOfBoundsException("index is out of range " + -count + ".." + (count - 1) + " (index = " + index + ")");
            }
            index = StringGroovyMethods.normaliseIndex(index, count);
            Iterator iter = StringGroovyMethods.iterator(self);
            Object result = null;
            for (int i = 0; i <= index; ++i) {
                result = iter.next();
            }
            return result;
        }
        catch (IllegalStateException ex) {
            return null;
        }
    }

    public static boolean matchesPartially(Matcher self) {
        return self.matches() || self.hitEnd();
    }

    public static String getAt(String self, int index) {
        index = StringGroovyMethods.normaliseIndex(index, self.length());
        return self.substring(index, index + 1);
    }

    public static String getAt(String self, IntRange range) {
        return StringGroovyMethods.getAt(self, (Range)range);
    }

    public static String getAt(String self, Range range) {
        RangeInfo info = StringGroovyMethods.subListBorders(self.length(), range);
        String answer = self.substring(info.from, info.to);
        if (info.reverse) {
            answer = StringGroovyMethods.reverse(answer);
        }
        return answer;
    }

    public static char[] getChars(CharSequence self) {
        return self.toString().toCharArray();
    }

    public static int getCount(Matcher self) {
        int counter = 0;
        self.reset();
        while (self.find()) {
            ++counter;
        }
        return counter;
    }

    private static String getPadding(CharSequence padding, int length) {
        if (padding.length() < length) {
            return StringGroovyMethods.multiply(padding, length / padding.length() + 1).substring(0, length);
        }
        return "" + padding.subSequence(0, length);
    }

    private static String getReplacement(Matcher matcher, Closure<?> closure) {
        if (StringGroovyMethods.hasGroup(matcher)) {
            Class<String> elementType;
            List<String> groups = StringGroovyMethods.getGroups(matcher);
            Class<?>[] paramTypes = closure.getParameterTypes();
            if (paramTypes.length == 1 && paramTypes[0].isArray() && (elementType = paramTypes[0].getComponentType()).isAssignableFrom(String.class)) {
                return FormatHelper.toString(closure.call(groups.toArray()));
            }
            return FormatHelper.toString(closure.call((Object)groups));
        }
        return FormatHelper.toString(closure.call((Object)matcher.group(0)));
    }

    private static List<String> getGroups(Matcher matcher) {
        int n = matcher.groupCount() + 1;
        ArrayList<String> list = new ArrayList<String>(n);
        for (int i = 0; i < n; ++i) {
            list.add(matcher.group(i));
        }
        return list;
    }

    public static boolean hasGroup(Matcher self) {
        return self.groupCount() > 0;
    }

    public static boolean isAllWhitespace(CharSequence self) {
        int n = self.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isWhitespace(self.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static boolean isBigDecimal(CharSequence self) {
        try {
            new BigDecimal(self.toString().trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isBigInteger(CharSequence self) {
        try {
            new BigInteger(self.toString().trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isCase(CharSequence caseValue, Object switchValue) {
        if (switchValue == null) {
            return caseValue == null;
        }
        return caseValue.toString().equals(switchValue.toString());
    }

    public static boolean isCase(Pattern caseValue, Object switchValue) {
        if (switchValue == null) {
            return caseValue == null;
        }
        Matcher matcher = caseValue.matcher(switchValue.toString());
        if (matcher.matches()) {
            RegexSupport.setLastMatcher(matcher);
            return true;
        }
        return false;
    }

    public static boolean isDouble(CharSequence self) {
        try {
            Double.valueOf(self.toString().trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isFloat(CharSequence self) {
        try {
            Float.valueOf(self.toString().trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isInteger(CharSequence self) {
        try {
            Integer.valueOf(self.toString().trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isLong(CharSequence self) {
        try {
            Long.valueOf(self.toString().trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isNotCase(CharSequence caseValue, Object switchValue) {
        return !StringGroovyMethods.isCase(caseValue, switchValue);
    }

    public static boolean isNotCase(Pattern caseValue, Object switchValue) {
        return !StringGroovyMethods.isCase(caseValue, switchValue);
    }

    public static boolean isNumber(CharSequence self) {
        return StringGroovyMethods.isBigDecimal(self);
    }

    public static Iterator iterator(final Matcher self) {
        self.reset();
        return new Iterator(){
            private boolean done;
            private boolean found;

            @Override
            public boolean hasNext() {
                if (this.done) {
                    return false;
                }
                if (!this.found) {
                    this.found = self.find();
                    if (!this.found) {
                        this.done = true;
                    }
                }
                return this.found;
            }

            public Object next() {
                if (!this.found && !this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.found = false;
                if (StringGroovyMethods.hasGroup(self)) {
                    return StringGroovyMethods.getGroups(self);
                }
                return self.group();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static StringBuilder leftShift(CharSequence self, Object value) {
        return new StringBuilder(self).append(value);
    }

    public static StringBuffer leftShift(String self, Object value) {
        return new StringBuffer(self).append(value);
    }

    public static StringBuffer leftShift(StringBuffer self, Object value) {
        self.append(value);
        return self;
    }

    public static StringBuilder leftShift(StringBuilder self, Object value) {
        self.append(value);
        return self;
    }

    public static boolean matches(CharSequence self, Pattern pattern) {
        return pattern.matcher(self).matches();
    }

    public static String minus(CharSequence self, Object target) {
        String text;
        String s = self.toString();
        int index = s.indexOf(text = DefaultGroovyMethods.toString(target));
        if (index == -1) {
            return s;
        }
        int end = index + text.length();
        if (s.length() > end) {
            return s.substring(0, index) + s.substring(end);
        }
        return s.substring(0, index);
    }

    public static String minus(CharSequence self, Pattern pattern) {
        return pattern.matcher(self).replaceFirst("");
    }

    public static String multiply(CharSequence self, Number factor) {
        int size = factor.intValue();
        if (size == 0) {
            return "";
        }
        if (size < 0) {
            throw new IllegalArgumentException("multiply() should be called with a number of 0 or greater not: " + size);
        }
        StringBuilder answer = new StringBuilder(self);
        for (int i = 1; i < size; ++i) {
            answer.append(self);
        }
        return answer.toString();
    }

    public static String next(CharSequence self) {
        StringBuilder buffer = new StringBuilder(self);
        if (buffer.length() == 0) {
            buffer.append('\u0000');
        } else {
            char last = buffer.charAt(buffer.length() - 1);
            if (last == '\uffff') {
                buffer.append('\u0000');
            } else {
                char next = last;
                next = (char)(next + '\u0001');
                buffer.setCharAt(buffer.length() - 1, next);
            }
        }
        return buffer.toString();
    }

    public static String normalize(CharSequence self) {
        String s = self.toString();
        int nx = s.indexOf(13);
        if (nx < 0) {
            return s;
        }
        int len = s.length();
        StringBuilder sb = new StringBuilder(len);
        int i = 0;
        do {
            sb.append(s, i, nx);
            sb.append('\n');
        } while ((i = nx + 1) < len && (s.charAt(i) != '\n' || ++i < len) && (nx = s.indexOf(13, i)) > 0);
        sb.append(s, i, len);
        return sb.toString();
    }

    public static String padLeft(CharSequence self, Number numberOfChars) {
        return StringGroovyMethods.padLeft(self, numberOfChars, " ");
    }

    public static String padLeft(CharSequence self, Number numberOfChars, CharSequence padding) {
        int numChars = numberOfChars.intValue();
        if (numChars <= self.length()) {
            return self.toString();
        }
        return StringGroovyMethods.getPadding(padding.toString(), numChars - self.length()) + self;
    }

    public static String padRight(CharSequence self, Number numberOfChars) {
        return StringGroovyMethods.padRight(self, numberOfChars, " ");
    }

    public static String padRight(CharSequence self, Number numberOfChars, CharSequence padding) {
        int numChars = numberOfChars.intValue();
        if (numChars <= self.length()) {
            return self.toString();
        }
        return self + StringGroovyMethods.getPadding(padding.toString(), numChars - self.length());
    }

    public static String plus(CharSequence left, Object right) {
        return left + DefaultGroovyMethods.toString(right);
    }

    public static String plus(Number left, String right) {
        return DefaultGroovyMethods.toString(left) + right;
    }

    public static String plus(String left, CharSequence right) {
        return left + right;
    }

    public static String plus(StringBuffer left, String right) {
        return left + right;
    }

    public static String previous(CharSequence self) {
        StringBuilder buffer = new StringBuilder(self);
        if (buffer.length() == 0) {
            throw new IllegalArgumentException("the string is empty");
        }
        char last = buffer.charAt(buffer.length() - 1);
        if (last == '\u0000') {
            buffer.deleteCharAt(buffer.length() - 1);
        } else {
            char next = last;
            next = (char)(next - '\u0001');
            buffer.setCharAt(buffer.length() - 1, next);
        }
        return buffer.toString();
    }

    public static void putAt(StringBuffer self, EmptyRange range, Object value) {
        RangeInfo info = StringGroovyMethods.subListBorders(self.length(), range);
        self.replace(info.from, info.to, value.toString());
    }

    public static void putAt(StringBuffer self, IntRange range, Object value) {
        RangeInfo info = StringGroovyMethods.subListBorders(self.length(), range);
        self.replace(info.from, info.to, value.toString());
    }

    public static List<String> readLines(CharSequence self) {
        return DefaultGroovyMethods.toList(new LineIterable(self));
    }

    public static String replaceAll(CharSequence self, CharSequence regex, CharSequence replacement) {
        return self.toString().replaceAll(regex.toString(), replacement.toString());
    }

    public static String replaceAll(CharSequence self, CharSequence regex, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}) Closure closure) {
        return StringGroovyMethods.replaceAll(self, Pattern.compile(regex.toString()), closure);
    }

    public static String replaceAll(CharSequence self, Pattern pattern, CharSequence replacement) {
        return pattern.matcher(self).replaceAll(replacement.toString());
    }

    public static String replaceAll(CharSequence self, Pattern pattern, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}) Closure closure) {
        String s = self.toString();
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            StringBuffer sb = new StringBuffer(s.length() + 16);
            do {
                String replacement = StringGroovyMethods.getReplacement(matcher, closure);
                matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
            } while (matcher.find());
            matcher.appendTail(sb);
            return sb.toString();
        }
        return s;
    }

    public static String replaceFirst(CharSequence self, CharSequence regex, CharSequence replacement) {
        return self.toString().replaceFirst(regex.toString(), replacement.toString());
    }

    public static String replaceFirst(CharSequence self, CharSequence regex, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}) Closure closure) {
        return StringGroovyMethods.replaceFirst(self, Pattern.compile(regex.toString()), closure);
    }

    public static String replaceFirst(CharSequence self, Pattern pattern, CharSequence replacement) {
        return pattern.matcher(self).replaceFirst(replacement.toString());
    }

    public static String replaceFirst(CharSequence self, Pattern pattern, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}) Closure closure) {
        String s = self.toString();
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            StringBuffer sb = new StringBuffer(s.length() + 16);
            String replacement = StringGroovyMethods.getReplacement(matcher, closure);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
            matcher.appendTail(sb);
            return sb.toString();
        }
        return s;
    }

    public static String replace(CharSequence self, Map<CharSequence, CharSequence> replacements) {
        return StringGroovyMethods.replace(self, -1, replacements);
    }

    public static String replace(CharSequence self, int capacity, Map<CharSequence, CharSequence> replacements) {
        if (self == null) {
            return null;
        }
        String text = self.toString();
        if (replacements == null || replacements.isEmpty() || text.isEmpty()) {
            return text;
        }
        ReplaceState state = new ReplaceState(replacements);
        StringGroovyMethods.nextMatch(text, state);
        if (state.textIndex == -1) {
            return text;
        }
        StringBuilder buf = new StringBuilder(StringGroovyMethods.guessCapacity(capacity, replacements));
        state.start = 0;
        while (state.textIndex != -1) {
            for (int i = state.start; i < state.textIndex; ++i) {
                buf.append(text.charAt(i));
            }
            buf.append(state.value(state.replaceIndex));
            state.start = state.textIndex + state.key(state.replaceIndex).length();
            state.textIndex = -1;
            state.replaceIndex = -1;
            StringGroovyMethods.nextMatch(text, state);
        }
        int textLength = text.length();
        for (int i = state.start; i < textLength; ++i) {
            buf.append(text.charAt(i));
        }
        return buf.toString();
    }

    private static int guessCapacity(int capacity, Map<CharSequence, CharSequence> replacements) {
        if (capacity >= 0) {
            return capacity;
        }
        boolean possiblyBigger = false;
        for (Map.Entry<CharSequence, CharSequence> entry : replacements.entrySet()) {
            if (entry.getValue().length() <= entry.getKey().length()) continue;
            possiblyBigger = true;
            break;
        }
        return possiblyBigger ? replacements.size() * 3 / 2 : replacements.size();
    }

    private static void nextMatch(String text, ReplaceState state) {
        for (int i = 0; i < state.numReplacements(); ++i) {
            if (state.noMoreMatches[i] || state.key(i) == null || state.key(i).length() == 0 || state.value(i) == null) continue;
            state.tempIndex = text.indexOf(state.key(i).toString(), state.start);
            if (state.tempIndex == -1) {
                state.noMoreMatches[i] = true;
                continue;
            }
            if (state.textIndex != -1 && state.tempIndex >= state.textIndex) continue;
            state.textIndex = state.tempIndex;
            state.replaceIndex = i;
        }
    }

    public static String reverse(CharSequence self) {
        return new StringBuilder(self).reverse().toString();
    }

    public static void setIndex(Matcher self, int index) {
        block4: {
            block5: {
                block3: {
                    int count = StringGroovyMethods.getCount(self);
                    if (index < -count || index >= count) {
                        throw new IndexOutOfBoundsException("index is out of range " + -count + ".." + (count - 1) + " (index = " + index + ")");
                    }
                    if (index != 0) break block3;
                    self.reset();
                    break block4;
                }
                if (index <= 0) break block5;
                self.reset();
                for (int i = 0; i < index; ++i) {
                    self.find();
                }
                break block4;
            }
            if (index >= 0) break block4;
            self.reset();
            index += StringGroovyMethods.getCount(self);
            for (int i = 0; i < index; ++i) {
                self.find();
            }
        }
    }

    public static int size(CharSequence self) {
        return self.length();
    }

    public static long size(Matcher self) {
        return StringGroovyMethods.getCount(self);
    }

    @Deprecated
    public static int size(String self) {
        return self.length();
    }

    @Deprecated
    public static int size(StringBuffer self) {
        return self.length();
    }

    public static Boolean isAtLeast(String left, String right) {
        return DefaultGroovyMethods.isAtLeast(new BigDecimal(left), right);
    }

    public static String[] split(CharSequence self) {
        StringTokenizer st = new StringTokenizer(self.toString());
        int n = st.countTokens();
        String[] strings = new String[n];
        for (int i = 0; i < n; ++i) {
            strings[i] = st.nextToken();
        }
        return strings;
    }

    public static <T> T splitEachLine(CharSequence self, CharSequence regex, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) throws IOException {
        return StringGroovyMethods.splitEachLine(self, Pattern.compile(regex.toString()), closure);
    }

    public static <T> T splitEachLine(CharSequence self, Pattern pattern, @ClosureParams(value=FromString.class, options={"List<String>", "String[]"}, conflictResolutionStrategy=PickFirstResolver.class) Closure<T> closure) {
        T result = null;
        for (String line : new LineIterable(self)) {
            List<String> vals = Arrays.asList(pattern.split(line));
            result = closure.call((Object)vals);
        }
        return result;
    }

    public static String stripIndent(CharSequence self) {
        if (self.length() == 0) {
            return self.toString();
        }
        int runningCount = -1;
        for (String line : new LineIterable(self)) {
            if (StringGroovyMethods.isAllWhitespace(line)) continue;
            if (runningCount == -1) {
                runningCount = line.length();
            }
            if ((runningCount = StringGroovyMethods.findMinimumLeadingSpaces(line, runningCount)) != 0) continue;
            break;
        }
        return StringGroovyMethods.stripIndent(self, runningCount == -1 ? 0 : runningCount);
    }

    @Incubating
    public static String stripIndent(CharSequence self, boolean forceGroovyBehavior) {
        if (!forceGroovyBehavior) {
            try {
                MethodHandle mh = MethodHandles.lookup().findVirtual(self.getClass(), "stripIndent", MethodType.methodType(String.class));
                return (String)mh.bindTo(self).invokeWithArguments(new Object[0]);
            }
            catch (IllegalAccessException | NoSuchMethodException mh) {
            }
            catch (Throwable t) {
                throw new GroovyRuntimeException(t);
            }
        }
        return StringGroovyMethods.stripIndent(self);
    }

    public static String stripIndent(CharSequence self, int numChars) {
        if (self.length() == 0 || numChars <= 0) {
            return self.toString();
        }
        StringBuilder builder = new StringBuilder();
        for (String line : new LineIterable(self)) {
            if (!StringGroovyMethods.isAllWhitespace(line)) {
                builder.append(StringGroovyMethods.stripIndentFromLine(line, numChars));
            }
            builder.append("\n");
        }
        if (self.charAt(self.length() - 1) != '\n') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    private static String stripIndentFromLine(String line, int numChars) {
        int length = line.length();
        return numChars <= length ? line.substring(numChars) : "";
    }

    public static String stripMargin(CharSequence self) {
        return StringGroovyMethods.stripMargin(self, '|');
    }

    public static String stripMargin(CharSequence self, char marginChar) {
        if (self.length() == 0) {
            return self.toString();
        }
        StringBuilder builder = new StringBuilder();
        for (String line : new LineIterable(self)) {
            builder.append(StringGroovyMethods.stripMarginFromLine(line, marginChar));
            builder.append("\n");
        }
        if (self.charAt(self.length() - 1) != '\n') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static String stripMargin(CharSequence self, CharSequence marginChar) {
        String mc = marginChar.toString();
        if (mc.length() == 0) {
            return StringGroovyMethods.stripMargin(self, '|');
        }
        return StringGroovyMethods.stripMargin(self, mc.charAt(0));
    }

    private static String stripMarginFromLine(String line, char marginChar) {
        int index;
        int length = line.length();
        for (index = 0; index < length && line.charAt(index) <= ' '; ++index) {
        }
        return index < length && line.charAt(index) == marginChar ? line.substring(index + 1) : line;
    }

    public static CharSequence take(CharSequence self, int num) {
        if (num < 0) {
            return self.subSequence(0, 0);
        }
        if (self.length() <= num) {
            return self;
        }
        return self.subSequence(0, num);
    }

    public static String take(GString self, int num) {
        return StringGroovyMethods.take(self.toString(), num);
    }

    public static String take(String self, int num) {
        return (String)StringGroovyMethods.take((CharSequence)self, num);
    }

    public static String takeWhile(CharSequence self, @ClosureParams(value=FromString.class, conflictResolutionStrategy=PickFirstResolver.class, options={"String", "Character"}) Closure condition) {
        Iterator<Character> selfIter = ClosureUtils.hasSingleCharacterArg(condition) ? new CharacterIterator(self) : new StringIterator(self);
        return DefaultGroovyMethods.join(DefaultGroovyMethods.takeWhile(selfIter, condition), "");
    }

    @Deprecated
    public static /* bridge */ /* synthetic */ CharSequence takeWhile(CharSequence self, @ClosureParams(value=FromString.class, conflictResolutionStrategy=PickFirstResolver.class, options={"String", "Character"}) Closure condition) {
        return StringGroovyMethods.takeWhile(self, condition);
    }

    public static String takeWhile(GString self, @ClosureParams(value=FromString.class, conflictResolutionStrategy=PickFirstResolver.class, options={"String", "Character"}) Closure condition) {
        return StringGroovyMethods.takeWhile(self.toString(), condition);
    }

    public static BigDecimal toBigDecimal(CharSequence self) {
        return new BigDecimal(self.toString().trim());
    }

    public static BigInteger toBigInteger(CharSequence self) {
        return new BigInteger(self.toString().trim());
    }

    public static Boolean toBoolean(String self) {
        String trimmed = self.trim();
        if ("true".equalsIgnoreCase(trimmed) || "y".equalsIgnoreCase(trimmed) || "1".equals(trimmed)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Character toCharacter(String self) {
        return Character.valueOf(self.charAt(0));
    }

    public static Double toDouble(CharSequence self) {
        return Double.valueOf(self.toString().trim());
    }

    public static Float toFloat(CharSequence self) {
        return Float.valueOf(self.toString().trim());
    }

    public static Integer toInteger(CharSequence self) {
        return Integer.valueOf(self.toString().trim());
    }

    public static List<String> tokenize(CharSequence self) {
        return InvokerHelper.asList(new StringTokenizer(self.toString()));
    }

    public static List<String> tokenize(CharSequence self, Character delimiter) {
        return StringGroovyMethods.tokenize(self, delimiter.toString());
    }

    public static List<String> tokenize(CharSequence self, CharSequence delimiters) {
        return InvokerHelper.asList(new StringTokenizer(self.toString(), delimiters.toString()));
    }

    public static List<String> toList(CharSequence self) {
        String s = self.toString();
        int n = s.length();
        ArrayList<String> answer = new ArrayList<String>(n);
        for (int i = 0; i < n; ++i) {
            answer.add(s.substring(i, i + 1));
        }
        return answer;
    }

    public static Long toLong(CharSequence self) {
        return Long.valueOf(self.toString().trim());
    }

    public static Set<String> toSet(CharSequence self) {
        return new HashSet<String>(StringGroovyMethods.toList(self));
    }

    public static Short toShort(CharSequence self) {
        return Short.valueOf(self.toString().trim());
    }

    public static String tr(CharSequence self, CharSequence sourceSet, CharSequence replacementSet) throws ClassNotFoundException {
        return (String)InvokerHelper.invokeStaticMethod("org.codehaus.groovy.util.StringUtil", "tr", (Object)new Object[]{self.toString(), sourceSet.toString(), replacementSet.toString()});
    }

    public static String unexpand(CharSequence self) {
        return StringGroovyMethods.unexpand(self, 8);
    }

    public static String unexpand(CharSequence self, int tabStop) {
        if (self.length() == 0) {
            return self.toString();
        }
        StringBuilder builder = new StringBuilder();
        for (String line : new LineIterable(self)) {
            builder.append(StringGroovyMethods.unexpandLine(line, tabStop));
            builder.append("\n");
        }
        if (self.charAt(self.length() - 1) != '\n') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static String unexpandLine(CharSequence self, int tabStop) {
        StringBuilder builder = new StringBuilder(self.toString());
        int index = 0;
        while (index + tabStop < builder.length()) {
            int count;
            String piece = builder.substring(index, index + tabStop);
            for (count = 0; count < tabStop && Character.isWhitespace(piece.charAt(tabStop - (count + 1))); ++count) {
            }
            if (count > 0) {
                piece = piece.substring(0, tabStop - count) + '\t';
                builder.replace(index, index + tabStop, piece);
                index = index + tabStop - (count - 1);
                continue;
            }
            index += tabStop;
        }
        return builder.toString();
    }

    public static boolean startsWithAny(CharSequence self, CharSequence ... prefixes) {
        String str = self.toString();
        for (CharSequence prefix : prefixes) {
            if (!str.startsWith(prefix.toString())) continue;
            return true;
        }
        return false;
    }

    public static boolean endsWithAny(CharSequence self, CharSequence ... suffixes) {
        String str = self.toString();
        for (CharSequence suffix : suffixes) {
            if (!str.endsWith(suffix.toString())) continue;
            return true;
        }
        return false;
    }

    public static boolean isBlank(CharSequence self) {
        if (self == null) {
            return true;
        }
        return self.toString().matches("\\s*");
    }

    public static CharSequence takeRight(CharSequence self, int num) {
        if (num < 0) {
            return self.subSequence(0, 0);
        }
        int begin = Math.max(0, self.length() - num);
        return self.subSequence(begin, self.length());
    }

    public static String takeRight(String self, int num) {
        return (String)StringGroovyMethods.takeRight((CharSequence)self, num);
    }

    public static String takeRight(GString self, int num) {
        return StringGroovyMethods.takeRight(self.toString(), num);
    }

    public static CharSequence takeAfter(CharSequence self, CharSequence searchString) {
        if (searchString == null || searchString.toString().isEmpty() || self.length() <= searchString.length()) {
            return self.subSequence(0, 0);
        }
        String s = self.toString();
        int index = s.indexOf(searchString.toString());
        return index == -1 ? self.subSequence(0, 0) : self.subSequence(index + searchString.length(), self.length());
    }

    public static String takeAfter(String self, CharSequence searchString) {
        return (String)StringGroovyMethods.takeAfter((CharSequence)self, searchString);
    }

    public static String takeAfter(GString self, CharSequence searchString) {
        return StringGroovyMethods.takeAfter(self.toString(), searchString);
    }

    public static CharSequence takeBefore(CharSequence self, CharSequence searchString) {
        if (searchString == null || searchString.toString().isEmpty() || self.length() <= searchString.length()) {
            return self.subSequence(0, 0);
        }
        String s = self.toString();
        int index = s.indexOf(searchString.toString());
        return index == -1 ? self.subSequence(0, 0) : self.subSequence(0, index);
    }

    public static String takeBefore(GString self, String searchString) {
        return StringGroovyMethods.takeBefore(self.toString(), searchString);
    }

    public static String takeBefore(String self, String searchString) {
        return (String)StringGroovyMethods.takeBefore((CharSequence)self, (CharSequence)searchString);
    }

    public static CharSequence dropRight(CharSequence self, int num) {
        if (num < 0) {
            return self;
        }
        if (num >= self.length()) {
            return self.subSequence(0, 0);
        }
        return StringGroovyMethods.take(self, self.length() - num);
    }

    public static String dropRight(String self, int num) {
        return (String)StringGroovyMethods.dropRight((CharSequence)self, num);
    }

    public static String dropRight(GString self, int num) {
        return StringGroovyMethods.dropRight(self.toString(), num);
    }

    public static CharSequence takeBetween(CharSequence self, CharSequence from, CharSequence to) {
        String f;
        if (from == null || to == null || from.length() == 0 || to.length() == 0 || from.length() > self.length() || to.length() > self.length()) {
            return self.subSequence(0, 0);
        }
        String s = self.toString();
        int fi = s.indexOf(f = from.toString());
        if (fi == -1) {
            return self.subSequence(0, 0);
        }
        String t = to.toString();
        int ti = s.indexOf(t, fi + from.length());
        if (ti == -1) {
            return self.subSequence(0, 0);
        }
        return self.subSequence(fi + from.length(), ti);
    }

    public static String takeBetween(String self, CharSequence from, CharSequence to) {
        return (String)StringGroovyMethods.takeBetween((CharSequence)self, from, to);
    }

    public static String takeBetween(GString self, CharSequence from, CharSequence to) {
        return StringGroovyMethods.takeBetween(self.toString(), from, to);
    }

    public static CharSequence takeBetween(CharSequence self, CharSequence enclosure) {
        return StringGroovyMethods.takeBetween(self, enclosure, enclosure);
    }

    public static String takeBetween(String self, CharSequence enclosure) {
        return (String)StringGroovyMethods.takeBetween((CharSequence)self, enclosure);
    }

    public static String takeBetween(GString self, CharSequence enclosure) {
        return StringGroovyMethods.takeBetween(self.toString(), enclosure);
    }

    public static CharSequence takeBetween(CharSequence self, CharSequence from, CharSequence to, int occurrence) {
        if (from == null || to == null || from.length() > self.length() || to.length() > self.length() || to.length() + from.length() >= self.length() || occurrence < 0) {
            return self.subSequence(0, 0);
        }
        String s = self.toString();
        String f = from.toString();
        int start = 0;
        for (int counter = 0; counter <= occurrence; ++counter) {
            int fi = s.indexOf(f, start);
            if (fi == -1) {
                return self.subSequence(0, 0);
            }
            int ti = s.indexOf(to.toString(), fi + f.length());
            if (ti == -1) {
                return self.subSequence(0, 0);
            }
            if (counter == occurrence) {
                return self.subSequence(fi + f.length(), ti);
            }
            start = ti + to.length() + 1;
        }
        return self.subSequence(0, 0);
    }

    public static String takeBetween(String self, CharSequence from, CharSequence to, int occurrence) {
        return (String)StringGroovyMethods.takeBetween((CharSequence)self, from, to, occurrence);
    }

    public static String takeBetween(GString self, CharSequence from, CharSequence to, int occurrence) {
        return StringGroovyMethods.takeBetween(self.toString(), from, to, occurrence);
    }

    public static CharSequence takeBetween(CharSequence self, CharSequence enclosure, int occurrence) {
        return StringGroovyMethods.takeBetween(self, enclosure, enclosure, occurrence);
    }

    public static String takeBetween(String self, CharSequence enclosure, int occurrence) {
        return (String)StringGroovyMethods.takeBetween((CharSequence)self, enclosure, occurrence);
    }

    public static String takeBetween(GString self, CharSequence enclosure, int occurrence) {
        return StringGroovyMethods.takeBetween(self.toString(), enclosure, occurrence);
    }

    public static boolean startsWithIgnoreCase(CharSequence self, CharSequence searchString) {
        if (searchString == null || searchString.length() == 0 || self.length() < searchString.length()) {
            return false;
        }
        String s = StringGroovyMethods.take(self.toString(), searchString.length());
        return s.equalsIgnoreCase(searchString.toString());
    }

    public static boolean endsWithIgnoreCase(CharSequence self, CharSequence searchString) {
        if (searchString == null || searchString.length() == 0 || self.length() < searchString.length()) {
            return false;
        }
        String s = StringGroovyMethods.takeRight(self.toString(), searchString.length());
        return s.equalsIgnoreCase(searchString.toString());
    }

    public static boolean containsIgnoreCase(CharSequence self, CharSequence searchString) {
        if (searchString == null || searchString.length() == 0 || self.length() < searchString.length()) {
            return false;
        }
        return self.toString().toLowerCase().contains(searchString.toString().toLowerCase());
    }

    private static final class CharacterIterator
    implements Iterator<Character> {
        private final CharSequence delegate;
        private final int length;
        private int index;

        public CharacterIterator(CharSequence delegate) {
            this.delegate = delegate;
            this.length = delegate.length();
        }

        @Override
        public boolean hasNext() {
            return this.index < this.length;
        }

        @Override
        public Character next() {
            return Character.valueOf(this.delegate.charAt(this.index++));
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported for CharSequence iterators");
        }
    }

    private static final class StringIterator
    implements Iterator<String> {
        private final CharSequence delegate;
        private final int length;
        private int index;

        public StringIterator(CharSequence delegate) {
            this.delegate = delegate;
            this.length = delegate.length();
        }

        @Override
        public boolean hasNext() {
            return this.index < this.length;
        }

        @Override
        public String next() {
            return Character.toString(this.delegate.charAt(this.index++));
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported for CharSequence iterators");
        }
    }

    private static final class LineIterable
    implements Iterable<String> {
        private final CharSequence delegate;

        public LineIterable(CharSequence cs) {
            this.delegate = cs instanceof GString ? cs.toString() : cs;
        }

        @Override
        public Iterator<String> iterator() {
            return IOGroovyMethods.iterator(new CharSequenceReader(this.delegate));
        }
    }

    private static class ReplaceState {
        int textIndex = -1;
        int tempIndex = -1;
        int replaceIndex = -1;
        int start = -1;
        final boolean[] noMoreMatches;
        private final List<Map.Entry<CharSequence, CharSequence>> replacementsList;

        public ReplaceState(Map<CharSequence, CharSequence> replacements) {
            this.noMoreMatches = new boolean[replacements.size()];
            this.replacementsList = DefaultGroovyMethods.toList(replacements.entrySet());
        }

        CharSequence key(int i) {
            return this.replacementsList.get(i).getKey();
        }

        CharSequence value(int i) {
            return this.replacementsList.get(i).getValue();
        }

        int numReplacements() {
            return this.replacementsList.size();
        }
    }
}

