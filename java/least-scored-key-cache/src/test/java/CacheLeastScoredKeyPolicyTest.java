import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CacheLeastScoredKeyPolicyTest {

    private static final int DEFAULT_CAPACITY = 3;

    @Test
    void should_put_the_entry_() {

        // [Arrange]
        final CacheLeastScoredKeyPolicy<Integer, String> cache = new CacheLeastScoredKeyPolicy<>(DEFAULT_CAPACITY);
        cache.put(1, "Hello", BigDecimal.ONE);
        cache.put(2, "World", BigDecimal.ONE);
        cache.put(3, "!", BigDecimal.ONE);
        assertThat(cache.get(2)).isEqualTo("World");
        final int size = cache.size();
        assertThat(size).isEqualTo(3);

        // [Act]
        cache.put(2, "YO", BigDecimal.ONE);

        // [Assert]
        assertThat(cache.get(2)).isEqualTo("YO");
        final int actualSize = cache.size();
        assertThat(actualSize).isEqualTo(3);

    }

    @Test
    void should_put_the_entry_two() {

        // [Arrange]
        final CacheLeastScoredKeyPolicy<Integer, String> cache = new CacheLeastScoredKeyPolicy<>(DEFAULT_CAPACITY);
        cache.put(1, "Hello", BigDecimal.ONE);
        cache.put(2, "World", BigDecimal.ONE);
        cache.put(3, "Hey", BigDecimal.ONE);
        final int size = cache.size();
        assertThat(size).isEqualTo(3);
        assertThat(cache.get(1)).isEqualTo("Hello");
        assertThat(cache.get(2)).isEqualTo("World");
        assertThat(cache.get(3)).isEqualTo("Hey");
        assertThat(cache.get(4)).isNull();

        // [Act]
        cache.put(4, "YO", BigDecimal.ONE);

        // [Assert]
        assertThat(cache.get(4)).isEqualTo("YO");
        final int actualSize = cache.size();
        assertThat(actualSize).isEqualTo(3);

    }

    @Test
    void should_put_the_entry_tsdfsdfwo() {

        // [Arrange]
        final CacheLeastScoredKeyPolicy<Integer, String> cache = new CacheLeastScoredKeyPolicy<>(DEFAULT_CAPACITY);
        cache.put(1, "Hello", BigDecimal.ONE);
        final int size = cache.size();
        assertThat(size).isEqualTo(1);
        assertThat(cache.get(1)).isEqualTo("Hello");

        // [Act]
        cache.put(1, "World", BigDecimal.ONE);

        // [Assert]
        assertThat(cache.get(1)).isEqualTo("World");
        final int actualSize = cache.size();
        assertThat(actualSize).isEqualTo(1);

    }

}
