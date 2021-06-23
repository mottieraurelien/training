package kata.structures;

import kata.data.Entry;

import java.util.function.Function;

import static java.lang.reflect.Array.newInstance;
import static java.util.Arrays.copyOf;
import static kata.data.Entry.of;

public class HashMapLinearProbing<K, V> {

    private static final int GROWING_FACTOR = 2;

    private int size;
    private boolean isFull;
    private int initialCapacity;
    private Entry<K, V>[] entries;

    private final Function<K, Integer> hashcoder;

    @SuppressWarnings("unchecked")
    public HashMapLinearProbing(final int initialCapacity, final Function<K, Integer> hashcoder) {
        this.hashcoder = hashcoder;
        this.initialCapacity = initialCapacity;
        this.entries = (Entry<K, V>[]) newInstance(Entry.class, initialCapacity);
    }

    public void put(final K key, final V value) {

        if (this.isFull) this.grow();

        final Entry<K, V> entry = of(key, value);
        final int index = this.probe(key);
        this.entries[index] = entry;

        this.isFull = ++this.size == this.initialCapacity;

    }

    public V get(final K key) {
        final int index = this.probe(key);
        final Entry<K, V> entry = this.entries[index];
        return entry == null ? null : entry.getValue();
    }

    public void remove(final K key) {

        final int index = this.probe(key);
        final Entry<K, V> entry = this.entries[index];

        if (entry != null) {
            this.entries[index] = null;
            this.isFull = false;
            this.size--;
        }

    }

    public int size() {
        return this.size;
    }

    /**
     * Linear probing strategy. We checked that the array was not full before.
     * <p>
     * Best case : O(1) if the first computed index leads to an empty slot.
     * Worst case : O(n) if the first computed index is 0 and the array is full...
     * <p>
     * That's also the reason we call it "linear probing" cause "linear" means O(n).
     */
    private int probe(final K key) {
        int index = this.hashcoder.apply(key);
        Entry<K, V> entry = this.entries[index];
        while (entry != null && key != entry.getKey()) {
            entry = this.entries[++index];
        }
        return index;
    }

    /**
     * Growing strategy for ArrayList > 1.5
     * <p>
     * Growing strategy for HashMap > 2, since it's very time-expensive so we allocate more space to not
     * have to do it many times...
     */
    private void grow() {
        this.initialCapacity = this.initialCapacity * GROWING_FACTOR;
        this.entries = copyOf(this.entries, this.initialCapacity);
    }

}