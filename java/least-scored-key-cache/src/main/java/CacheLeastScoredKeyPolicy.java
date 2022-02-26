import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.math.BigDecimal.*;
import static java.util.Objects.requireNonNull;

/**
 * Functional requirements :
 * - The solution will be based on a fixed-size cache, meaning that the cache can be full at some point,
 * - The solution will evict the right entry before adding a new one when the cache is already full,
 * - The solution will consider the {weight} as being a BigDecimal to offer a wide range of weight,
 * - The solution will consider the {score} as being a BigDecimal since being the result of an accurate computation,
 * - The solution will consider the {current_time} and {last_access_time} as being nano timestamps (since offered by Java, but we still implemented both formulas),
 * <p>
 * Technical requirements :
 * - Your data structure should be optimized for the computational complexity of get(key).
 * <p>
 * Solution :
 * - Using an HashMap with a pair of two objects as value (the weighted key and its associated value),
 * - get(key) ensures O(1) in all cases since we replace the old entry that associates a key with the same hash.
 * - put(key, value, weight) ensures O(n), worst case, since we may need to look over all the weighted keys and figure that "the last one" is the least scored.
 * - The space complexity is O(1) since the solution does not use extra space.
 */
public class CacheLeastScoredKeyPolicy<K, V> {

    private int size;
    private final int initialCapacity;
    private final Map<K, Pair<WeightedKey<K>, V>> cache;

    public CacheLeastScoredKeyPolicy(final int initialCapacity) {
        if (initialCapacity < 1) throw new IllegalArgumentException("Let's cache one item at least...");
        this.size = 0;
        this.initialCapacity = initialCapacity;
        this.cache = new HashMap<>();
    }

    public V get(final K key) {
        requireNonNull(key);
        final Pair<WeightedKey<K>, V> pair = this.cache.get(key);
        if (pair == null) return null;
        pair.key.accessed();
        return pair.value;
    }

    public void put(final K key, final V value, final BigDecimal weight) {
        requireNonNull(key);
        requireNonNull(weight);
        final boolean cacheContainsKey = this.cache.containsKey(key);
        if (!cacheContainsKey && this.hasAlreadyReachedItsCapacity()) this.invalidateTheLeastScoredKey();
        final WeightedKey<K> weightedKey = new WeightedKey<>(key, weight);
        final Pair<WeightedKey<K>, V> pair = new Pair<>(weightedKey, value);
        if (cacheContainsKey)
            this.cache.replace(key, pair);
        else {
            this.cache.put(key, pair);
            this.size++;
        }
    }

    public int size() {
        return this.size;
    }

    private boolean hasAlreadyReachedItsCapacity() {
        return this.size == this.initialCapacity;
    }

    private void invalidateTheLeastScoredKey() {
        if (this.size == 1)
            this.cache.clear();
        final K leastScoredKey = this.identifyTheLeastScoredKey();
        this.cache.remove(leastScoredKey);
        this.size--;
    }

    private K identifyTheLeastScoredKey() {
        final BigDecimal now = valueOf(System.nanoTime());
        final Iterator<Pair<WeightedKey<K>, V>> iterator = this.cache.values().iterator();
        final Pair<WeightedKey<K>, V> pair = iterator.next();
        WeightedKey<K> leastWeightedKeySoFar = pair.key;
        BigDecimal lowestScore = leastWeightedKeySoFar.score(now);
        while (iterator.hasNext()) {
            final WeightedKey<K> weightedKey = iterator.next().key;
            final BigDecimal score = weightedKey.score(now);
            if (score.compareTo(lowestScore) < 0) {
                lowestScore = score;
                leastWeightedKeySoFar = weightedKey;
            }
        }
        return leastWeightedKeySoFar.key;
    }

    private record Pair<Y, U>(Y key, U value) {
    }

    private static class WeightedKey<E> {
        private static final MathContext DEFAULT_MATH_CONTEXT = MathContext.DECIMAL32;
        private static final BigDecimal MINUS_HUNDRED = new BigDecimal("100").negate();
        private final E key;
        private final BigDecimal weight;
        private BigDecimal lastAccessTime;

        public WeightedKey(final E key, final BigDecimal weight) {
            this.key = key;
            this.weight = weight;
            this.lastAccessTime = ZERO;
        }

        private void accessed() {
            this.lastAccessTime = valueOf(System.nanoTime());
        }

        private BigDecimal score(final BigDecimal currentTime) {
            if (ZERO.compareTo(this.weight) == 0)
                // Since 0 divided by {anything} will also lead to :
                return ZERO;
            if (currentTime.compareTo(this.lastAccessTime) == 0) {
                // Formula : weight / -100.
                return this.weight.divide(MINUS_HUNDRED, DEFAULT_MATH_CONTEXT);
            }
            // Formula : weight / ln(current_time - last_access_time + 1).
            // Notice : the trailing "+ 1" prevents the case when by dividing by 0 (thank you).
            final BigDecimal divisor = currentTime.subtract(lastAccessTime).add(ONE);
            final BigDecimal lnDivisor = valueOf(Math.log(divisor.doubleValue()));
            return this.weight.divide(lnDivisor, DEFAULT_MATH_CONTEXT);
        }
    }

}