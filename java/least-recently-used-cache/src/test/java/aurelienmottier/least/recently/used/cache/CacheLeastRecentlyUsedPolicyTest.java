package aurelienmottier.least.recently.used.cache;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class CacheLeastRecentlyUsedPolicyTest {

    private static final Function<LinkedList<String>, String> LEAST_RECENTLY_USED_POLICY = LinkedList::removeFirst;

    @Test
    void should_add_an_item_to_the_cache_when_the_cache_is_empty() {

        // [Arrange]
        final String key = "A";
        final String value = "Apple";
        final int maxSize = 3;
        final CacheLeastRecentlyUsedPolicy<String, String> cache = new CacheLeastRecentlyUsedPolicy<>(maxSize, LEAST_RECENTLY_USED_POLICY);

        // [Act]
        cache.put(key, value);

        // [Assert]
        final int actualCacheSize = cache.size();
        assertThat(actualCacheSize).isEqualTo(1);

        final String actualValue = cache.get(key);
        assertThat(actualValue).isEqualTo(value);

    }

    @Test
    void should_remove_the_least_recently_used_item_from_the_cache_when_the_cache_is_already_full() {

        // [Arrange]
        final int maxSize = 3;
        final CacheLeastRecentlyUsedPolicy<String, String> cache = new CacheLeastRecentlyUsedPolicy<>(maxSize, LEAST_RECENTLY_USED_POLICY);
        cache.put("A", "Apple");
        cache.put("F", "FaceBook");
        cache.put("G", "Google");

        // [Act]
        cache.put("N", "Netflix");

        // [Assert]
        final int actualCacheSize = cache.size();
        assertThat(actualCacheSize).isEqualTo(3);

        final String actualNetflixValue = cache.get("N");
        assertThat(actualNetflixValue).isEqualTo("Netflix");

        final String actualGoogleValue = cache.get("G");
        assertThat(actualGoogleValue).isEqualTo("Google");

        final String actualFacebookValue = cache.get("F");
        assertThat(actualFacebookValue).isEqualTo("FaceBook");

        final String actualAppleValue = cache.get("A");
        assertThat(actualAppleValue).isNull();

    }

    @Test
    void should_refresh_the_cache_cache_when_getting_an_item() {

        // [Arrange]
        final int maxSize = 3;
        final CacheLeastRecentlyUsedPolicy<String, String> cache = new CacheLeastRecentlyUsedPolicy<>(maxSize, LEAST_RECENTLY_USED_POLICY);
        cache.put("A", "Apple");
        cache.put("F", "FaceBook");
        cache.put("G", "Google");
        cache.get("A");

        // [Act]
        cache.put("N", "Netflix");

        // [Assert]
        final int actualCacheSize = cache.size();
        assertThat(actualCacheSize).isEqualTo(3);

        final String actualNetflixValue = cache.get("N");
        assertThat(actualNetflixValue).isEqualTo("Netflix");

        final String actualGoogleValue = cache.get("G");
        assertThat(actualGoogleValue).isEqualTo("Google");

        final String actualFacebookValue = cache.get("F");
        assertThat(actualFacebookValue).isNull();

        final String actualAppleValue = cache.get("A");
        assertThat(actualAppleValue).isEqualTo("Apple");

    }

    @Test
    void should_apply_a_different_policy_when_the_cache_is_already_full() {

        // [Arrange]
        final int maxSize = 3;
        final CacheLeastRecentlyUsedPolicy<String, String> cache = new CacheLeastRecentlyUsedPolicy<>(maxSize, LEAST_RECENTLY_USED_POLICY);
        cache.put("A", "Apple");
        cache.put("F", "FaceBook");
        cache.put("G", "Google");

        // [Act]
        cache.put("N", "Netflix");

        // [Assert]
        final int actualCacheSize = cache.size();
        assertThat(actualCacheSize).isEqualTo(3);

        final String actualNetflixValue = cache.get("N");
        assertThat(actualNetflixValue).isEqualTo("Netflix");

        final String actualGoogleValue = cache.get("G");
        assertThat(actualGoogleValue).isEqualTo("Google");

        final String actualFacebookValue = cache.get("F");
        assertThat(actualFacebookValue).isEqualTo("FaceBook");

        final String actualAppleValue = cache.get("A");
        assertThat(actualAppleValue).isNull();

    }

}