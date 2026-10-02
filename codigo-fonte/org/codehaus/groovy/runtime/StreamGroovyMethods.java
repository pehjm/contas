/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.codehaus.groovy.runtime.NullObject;

public class StreamGroovyMethods {
    private StreamGroovyMethods() {
    }

    public static <T> Stream<T> plus(Stream<? extends T> lhs, Collection<? extends T> rhs) {
        return Stream.concat(lhs, rhs.stream());
    }

    public static <T> Stream<T> plus(Stream<? extends T> lhs, Iterable<? extends T> rhs) {
        return Stream.concat(lhs, StreamGroovyMethods.stream(rhs));
    }

    public static <T> Stream<T> plus(Stream<? extends T> lhs, Stream<? extends T> rhs) {
        return Stream.concat(lhs, rhs);
    }

    public static <T> Stream<T> stream(T self) {
        return Stream.of(self);
    }

    public static <T> Stream<T> stream(T[] self) {
        return Arrays.stream(self);
    }

    public static Stream<Integer> stream(int[] self) {
        return Arrays.stream(self).boxed();
    }

    public static Stream<Long> stream(long[] self) {
        return Arrays.stream(self).boxed();
    }

    public static Stream<Double> stream(double[] self) {
        return Arrays.stream(self).boxed();
    }

    public static Stream<Character> stream(char[] self) {
        return IntStream.range(0, self.length).mapToObj(i -> Character.valueOf(self[i]));
    }

    public static Stream<Byte> stream(byte[] self) {
        return IntStream.range(0, self.length).mapToObj(i -> self[i]);
    }

    public static Stream<Short> stream(short[] self) {
        return IntStream.range(0, self.length).mapToObj(i -> self[i]);
    }

    public static Stream<Boolean> stream(boolean[] self) {
        return IntStream.range(0, self.length).mapToObj(i -> self[i]);
    }

    public static Stream<Float> stream(float[] self) {
        return IntStream.range(0, self.length).mapToObj(i -> Float.valueOf(self[i]));
    }

    public static <T> Stream<T> stream(final Enumeration<T> self) {
        return StreamGroovyMethods.stream(new Spliterators.AbstractSpliterator<T>(Long.MAX_VALUE, 16){

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                while (self.hasMoreElements()) {
                    action.accept(self.nextElement());
                }
            }

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                if (self.hasMoreElements()) {
                    action.accept(self.nextElement());
                    return true;
                }
                return false;
            }
        });
    }

    public static <T> Stream<T> stream(Iterable<T> self) {
        return StreamSupport.stream(self.spliterator(), false);
    }

    public static <T> Stream<T> stream(Iterator<T> self) {
        return StreamGroovyMethods.stream(Spliterators.spliteratorUnknownSize(self, 16));
    }

    public static <T> Stream<T> stream(Spliterator<T> self) {
        return StreamSupport.stream(self, false);
    }

    public static <T> Stream<T> stream(NullObject self) {
        return Stream.empty();
    }

    public static <T> Stream<T> stream(Optional<T> self) {
        return self.map(Stream::of).orElseGet(Stream::empty);
    }

    public static IntStream stream(OptionalInt self) {
        if (!self.isPresent()) {
            return IntStream.empty();
        }
        return IntStream.of(self.getAsInt());
    }

    public static LongStream stream(OptionalLong self) {
        if (!self.isPresent()) {
            return LongStream.empty();
        }
        return LongStream.of(self.getAsLong());
    }

    public static DoubleStream stream(OptionalDouble self) {
        if (!self.isPresent()) {
            return DoubleStream.empty();
        }
        return DoubleStream.of(self.getAsDouble());
    }

    public static IntStream intStream(int[] self) {
        return Arrays.stream(self);
    }

    public static LongStream longStream(long[] self) {
        return Arrays.stream(self);
    }

    public static DoubleStream doubleStream(double[] self) {
        return Arrays.stream(self);
    }

    public static <T> T[] toArray(Stream<? extends T> self, Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        return self.toArray(length -> (Object[])Array.newInstance(type, length));
    }

    public static <T> List<T> toList(Stream<T> self) {
        return self.collect(Collectors.toList());
    }

    public static <T> List<T> toList(BaseStream<T, ? extends BaseStream> self) {
        return StreamGroovyMethods.stream(self.iterator()).collect(Collectors.toList());
    }

    public static <T> Set<T> toSet(Stream<T> self) {
        return self.collect(Collectors.toSet());
    }

    public static <T> Set<T> toSet(BaseStream<T, ? extends BaseStream> self) {
        return StreamGroovyMethods.stream(self.iterator()).collect(Collectors.toSet());
    }
}

