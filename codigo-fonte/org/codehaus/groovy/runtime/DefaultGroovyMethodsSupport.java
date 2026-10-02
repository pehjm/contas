/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.runtime;

import groovy.lang.EmptyRange;
import groovy.lang.IntRange;
import groovy.lang.NumberRange;
import groovy.lang.Range;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Logger;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.NullObject;
import org.codehaus.groovy.runtime.RangeInfo;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class DefaultGroovyMethodsSupport {
    private static final Logger LOG = Logger.getLogger(DefaultGroovyMethodsSupport.class.getName());
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    protected static RangeInfo subListBorders(int size, Range range) {
        if (range instanceof IntRange) {
            return ((IntRange)range).subListBorders(size);
        }
        if (range instanceof NumberRange) {
            return ((NumberRange)range).subListBorders(size);
        }
        int from = DefaultGroovyMethodsSupport.normaliseIndex(DefaultTypeTransformation.intUnbox(range.getFrom()), size);
        int to = DefaultGroovyMethodsSupport.normaliseIndex(DefaultTypeTransformation.intUnbox(range.getTo()), size);
        boolean reverse = range.isReverse();
        if (from > to) {
            int tmp = to;
            to = from;
            from = tmp;
            reverse = !reverse;
        }
        return new RangeInfo(from, to + 1, reverse);
    }

    protected static RangeInfo subListBorders(int size, EmptyRange range) {
        int from = DefaultGroovyMethodsSupport.normaliseIndex(DefaultTypeTransformation.intUnbox(range.getFrom()), size);
        return new RangeInfo(from, from, false);
    }

    protected static IntRange subListRange(RangeInfo info, IntRange range) {
        int from = info.from;
        int to = info.to - 1;
        if (!info.reverse) {
            if (Boolean.FALSE.equals(range.getInclusiveLeft())) {
                --from;
            }
            if (Boolean.FALSE.equals(range.getInclusiveRight())) {
                ++to;
            }
        } else {
            if (Boolean.FALSE.equals(range.getInclusiveLeft())) {
                ++to;
            }
            if (Boolean.FALSE.equals(range.getInclusiveRight())) {
                --from;
            }
        }
        boolean inclusiveLeft = info.reverse ? range.getInclusiveRight() : range.getInclusiveLeft();
        boolean inclusiveRight = info.reverse ? range.getInclusiveLeft() : range.getInclusiveRight();
        return new IntRange(inclusiveLeft, inclusiveRight, from, to);
    }

    protected static int normaliseIndex(int i, int size) {
        int temp = i;
        if (i < 0) {
            i += size;
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative array index [" + temp + "] too large for array size " + size);
        }
        return i;
    }

    public static void closeWithWarning(Closeable closeable) {
        DefaultGroovyMethodsSupport.tryClose(closeable, true);
    }

    static Throwable tryClose(AutoCloseable closeable, boolean logWarning) {
        Exception thrown;
        block3: {
            thrown = null;
            if (closeable != null) {
                try {
                    closeable.close();
                }
                catch (Exception e) {
                    thrown = e;
                    if (!logWarning) break block3;
                    LOG.warning("Caught exception during close(): " + e);
                }
            }
        }
        return thrown;
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    protected static <T> Collection<T> cloneSimilarCollection(Collection<T> orig, int newCapacity) {
        Collection<T> answer = (Collection<T>)DefaultGroovyMethodsSupport.cloneObject(orig);
        if (answer != null) {
            return answer;
        }
        answer = DefaultGroovyMethodsSupport.createSimilarCollection(orig, newCapacity);
        answer.addAll(orig);
        return answer;
    }

    private static Object cloneObject(Object orig) {
        if (orig instanceof Cloneable) {
            try {
                return InvokerHelper.invokeMethod(orig, "clone", EMPTY_OBJECT_ARRAY);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return null;
    }

    protected static Collection createSimilarOrDefaultCollection(Object object) {
        if (object instanceof Collection) {
            return DefaultGroovyMethodsSupport.createSimilarCollection((Collection)object);
        }
        return new ArrayList();
    }

    protected static <T> Collection<T> createSimilarCollection(Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return DefaultGroovyMethodsSupport.createSimilarCollection((Collection)iterable);
        }
        return new ArrayList();
    }

    protected static <T> Collection<T> createSimilarCollection(Collection<T> collection) {
        return DefaultGroovyMethodsSupport.createSimilarCollection(collection, collection.size());
    }

    protected static <T> Collection<T> createSimilarCollection(Collection<T> orig, int newCapacity) {
        if (orig instanceof Set) {
            return DefaultGroovyMethodsSupport.createSimilarSet((Set)orig);
        }
        if (orig instanceof List) {
            return DefaultGroovyMethodsSupport.createSimilarList((List)orig, newCapacity);
        }
        if (orig instanceof Queue) {
            return DefaultGroovyMethodsSupport.createSimilarQueue((Queue)orig);
        }
        return new ArrayList(newCapacity);
    }

    protected static <T> List<T> createSimilarList(List<T> orig, int newCapacity) {
        if (orig instanceof LinkedList) {
            return new LinkedList();
        }
        if (orig instanceof Stack) {
            return new Stack();
        }
        if (orig instanceof Vector) {
            return new Vector();
        }
        if (orig instanceof CopyOnWriteArrayList) {
            return new CopyOnWriteArrayList();
        }
        return new ArrayList(newCapacity);
    }

    protected static <T> T[] createSimilarArray(T[] orig, int newCapacity) {
        Class<?> type = orig.getClass();
        if (type == Object[].class) {
            return new Object[newCapacity];
        }
        return (Object[])Array.newInstance(type.getComponentType(), newCapacity);
    }

    protected static <T> Set<T> createSimilarSet(Set<T> orig) {
        if (orig instanceof SortedSet) {
            Comparator comparator = ((SortedSet)orig).comparator();
            if (orig instanceof ConcurrentSkipListSet) {
                return new ConcurrentSkipListSet(comparator);
            }
            return new TreeSet(comparator);
        }
        if (orig instanceof CopyOnWriteArraySet) {
            return new CopyOnWriteArraySet();
        }
        return new LinkedHashSet();
    }

    protected static <T> Queue<T> createSimilarQueue(Queue<T> orig) {
        if (orig instanceof ArrayBlockingQueue) {
            ArrayBlockingQueue queue = (ArrayBlockingQueue)orig;
            return new ArrayBlockingQueue(queue.size() + queue.remainingCapacity());
        }
        if (orig instanceof ArrayDeque) {
            return new ArrayDeque();
        }
        if (orig instanceof ConcurrentLinkedQueue) {
            return new ConcurrentLinkedQueue();
        }
        if (orig instanceof DelayQueue) {
            return new DelayQueue();
        }
        if (orig instanceof LinkedBlockingDeque) {
            return new LinkedBlockingDeque();
        }
        if (orig instanceof LinkedBlockingQueue) {
            return new LinkedBlockingQueue();
        }
        if (orig instanceof PriorityBlockingQueue) {
            return new PriorityBlockingQueue();
        }
        if (orig instanceof PriorityQueue) {
            return new PriorityQueue(11, ((PriorityQueue)orig).comparator());
        }
        if (orig instanceof SynchronousQueue) {
            return new SynchronousQueue();
        }
        return new LinkedList();
    }

    protected static <K, V> Map<K, V> createSimilarMap(Map<K, V> orig) {
        if (orig instanceof SortedMap) {
            Comparator comparator = ((SortedMap)orig).comparator();
            if (orig instanceof ConcurrentSkipListMap) {
                return new ConcurrentSkipListMap(comparator);
            }
            return new TreeMap(comparator);
        }
        if (orig instanceof ConcurrentHashMap) {
            return new ConcurrentHashMap();
        }
        if (orig instanceof Hashtable) {
            if (orig instanceof Properties) {
                return new Properties();
            }
            return new Hashtable();
        }
        if (orig instanceof IdentityHashMap) {
            return new IdentityHashMap();
        }
        if (orig instanceof WeakHashMap) {
            return new WeakHashMap();
        }
        return new LinkedHashMap();
    }

    protected static <K, V> Map<K, V> cloneSimilarMap(Map<K, V> orig) {
        Map answer = (Map)DefaultGroovyMethodsSupport.cloneObject(orig);
        if (answer != null) {
            return answer;
        }
        if (orig instanceof SortedMap) {
            if (orig instanceof ConcurrentSkipListMap) {
                return new ConcurrentSkipListMap<K, V>(orig);
            }
            return new TreeMap<K, V>(orig);
        }
        if (orig instanceof ConcurrentHashMap) {
            return new ConcurrentHashMap<K, V>(orig);
        }
        if (orig instanceof Hashtable) {
            if (orig instanceof Properties) {
                Properties map = new Properties();
                map.putAll(orig);
                return map;
            }
            return new Hashtable<K, V>(orig);
        }
        if (orig instanceof IdentityHashMap) {
            return new IdentityHashMap<K, V>(orig);
        }
        if (orig instanceof WeakHashMap) {
            return new WeakHashMap<K, V>(orig);
        }
        return new LinkedHashMap<K, V>(orig);
    }

    protected static boolean sameType(Collection[] cols) {
        LinkedList all = new LinkedList();
        for (Collection col : cols) {
            all.addAll(col);
        }
        if (all.isEmpty()) {
            return true;
        }
        Object first = all.get(0);
        Class baseClass = first instanceof Number ? Number.class : (first == null ? NullObject.class : first.getClass());
        for (Collection col : cols) {
            for (Object o : col) {
                if (baseClass.isInstance(o)) continue;
                return false;
            }
        }
        return true;
    }

    protected static void writeUTF16BomIfRequired(Writer writer, String charset) throws IOException {
        DefaultGroovyMethodsSupport.writeUTF16BomIfRequired(writer, Charset.forName(charset));
    }

    protected static void writeUTF16BomIfRequired(Writer writer, Charset charset) throws IOException {
        if ("UTF-16BE".equals(charset.name())) {
            DefaultGroovyMethodsSupport.writeUtf16Bom(writer, true);
        } else if ("UTF-16LE".equals(charset.name())) {
            DefaultGroovyMethodsSupport.writeUtf16Bom(writer, false);
        }
    }

    protected static void writeUTF16BomIfRequired(OutputStream stream, String charset) throws IOException {
        DefaultGroovyMethodsSupport.writeUTF16BomIfRequired(stream, Charset.forName(charset));
    }

    protected static void writeUTF16BomIfRequired(OutputStream stream, Charset charset) throws IOException {
        if ("UTF-16BE".equals(charset.name())) {
            DefaultGroovyMethodsSupport.writeUtf16Bom(stream, true);
        } else if ("UTF-16LE".equals(charset.name())) {
            DefaultGroovyMethodsSupport.writeUtf16Bom(stream, false);
        }
    }

    private static void writeUtf16Bom(OutputStream stream, boolean bigEndian) throws IOException {
        if (bigEndian) {
            stream.write(-2);
            stream.write(-1);
        } else {
            stream.write(-1);
            stream.write(-2);
        }
    }

    private static void writeUtf16Bom(Writer writer, boolean bigEndian) throws IOException {
        if (bigEndian) {
            writer.write(-2);
            writer.write(-1);
        } else {
            writer.write(-1);
            writer.write(-2);
        }
    }
}

