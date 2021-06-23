package kata.structures;

import kata.data.Entry;

import java.util.function.Function;

import static java.lang.reflect.Array.newInstance;
import static kata.data.Entry.of;

/**
 * HashMap implementation using the chaining technique to handle collisions.
 */
public class HashMapChaining<K, V> {

    private int size;

    private final LinkedList<Entry<K, V>>[] entries;
    private final Function<K, Integer> hashcoder;

    @SuppressWarnings("unchecked")
    public HashMapChaining(final int initialCapacity, final Function<K, Integer> hashcoder) {
        this.hashcoder = hashcoder;
        this.entries = (LinkedList<Entry<K, V>>[]) newInstance(LinkedList.class, initialCapacity);
    }

    /**
     * O(1) ?
     */
    public void put(final K key, final V value) {

        final int index = this.hashcoder.apply(key);
        LinkedList<Entry<K, V>> chainedEntries = this.entries[index];
        if (chainedEntries == null) chainedEntries = this.entries[index] = new LinkedList<>();

        final Entry<K, V> existingEntry = this.filter(chainedEntries, key);
        if (existingEntry != null) {
            existingEntry.update(value);
            return;
        }

        final Entry<K, V> entry = of(key, value);
        chainedEntries.addLast(entry);
        this.size++;
    }

    /**
     * O(1) ?
     */
    public V get(final K key) {
        final int index = this.hashcoder.apply(key);
        final LinkedList<Entry<K, V>> chainedEntries = this.entries[index];
        final Entry<K, V> entry = this.filter(chainedEntries, key);
        return entry != null ? entry.getValue() : null;
    }

    /**
     * O(1) ?
     */
    public void remove(final K key) {
        final int index = this.hashcoder.apply(key);
        final LinkedList<Entry<K, V>> chainedEntries = this.entries[index];
        if (chainedEntries != null && !chainedEntries.isEmpty()) {
            chainedEntries.removeFirst();
            this.size--;
        }
    }

    public int size() {
        return this.size;
    }

    private Entry<K, V> filter(final LinkedList<Entry<K, V>> chainedEntries, final K key) {
        if (chainedEntries != null) {
            for (final Entry<K, V> chainedEntry : chainedEntries) {
                if (chainedEntry.getKey() == key) {
                    return chainedEntry;
                }
            }
        }
        return null;
    }

}