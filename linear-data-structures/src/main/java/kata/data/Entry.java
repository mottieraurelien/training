package kata.data;

public class Entry<K, V> {

    private final K key;
    private V value;

    private Entry(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Entry<K, V> of(final K key, final V value) {
        return new Entry<>(key, value);
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void update(final V newValue) {
        this.value = newValue;
    }

}