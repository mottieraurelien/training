package aurelienmottier.least.recently.used.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * [SOLUTION 1]
 * <p>
 * If we use a dedicated data structure to store our keys (linked list here), then :
 *  - GET is O(1),
 *  - PUT is O(1),
 *  - But space complexity is O(N) since we need extra space for the keys.
 *  <p>
 *  Another solution would be embedding extra information related to the key in an object.
 *  GET would remain O(1), PUT O(1) if cache not full otherwise O(log n) worst case.
 *  Space complexity would be O(1).
 */
public class CacheLeastRecentlyUsedPolicy<K, V> {

    private final int capacity;
    private final LinkedList<K> keys;
    private final Map<K, V> cache;
    private final Function<LinkedList<K>, K> policy;

    public CacheLeastRecentlyUsedPolicy(final int capacity, final Function<LinkedList<K>, K> policy) {
        this.capacity = capacity;
        this.policy = policy;
        this.cache = new HashMap<>();
        this.keys = new LinkedList<>();
    }

    void put(final K key, final V value) {
        this.check(key);
        if (this.isFull()) this.applyPolicy();
        this.cache.put(key, value);
        this.keys.add(key);
    }

    V get(final K key) {
        this.check(key);
        this.keys.removeFirstOccurrence(key);
        this.keys.add(key);
        return this.cache.get(key);
    }

    int size() {
        return this.cache.size();
    }

    private boolean isFull() {
        return this.size() == this.capacity;
    }

    private void applyPolicy() {
        final K itemToEvict = this.policy.apply(this.keys);
        this.cache.remove(itemToEvict);
    }

    private void check(final K key) {
        requireNonNull(key);
    }

}