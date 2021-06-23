package kata.structures;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapChainingTest {

    private static final Integer DEFAULT_CAPACITY = 16;

    /**
     * If needed, to handle negative keys (since we work with integers below) :
     * - [ Math.abs(key) % DEFAULT_CAPACITY ]
     * - [ √(key²) % DEFAULT_CAPACITY ]
     */
    private static final Function<Integer, Integer> HASHCODER = key -> key % DEFAULT_CAPACITY;

    @Test
    void should_initialise_a_linked_list_when_putting_a_new_item_that_does_not_any_entry_yet() {

        // [Arrange]
        final Integer key = 1;
        final String value = "Aurelien";
        final HashMapChaining<Integer, String> map = build();

        // [Act]
        map.put(key, value);

        // [Assert]
        final String actualValue = map.get(key);
        assertThat(actualValue).isEqualTo(value);

    }

    @Test
    void should_add_last_the_item_to_the_linked_list_when_putting_a_new_item_that_associates_an_existing_entry() {

        // [Arrange]
        final HashMapChaining<Integer, String> map = build();
        map.put(1, "Aurelien");
        map.put(2, "Tin");

        // [Act]
        map.put(11, "Momo");

        // [Assert]
        final int size = map.size();
        assertThat(size).isEqualTo(3);
        final String actualFirstValue = map.get(1);
        assertThat(actualFirstValue).isEqualTo("Aurelien");
        final String actualSecondValue = map.get(2);
        assertThat(actualSecondValue).isEqualTo("Tin");
        final String actualThirdValue = map.get(11);
        assertThat(actualThirdValue).isEqualTo("Momo");

    }

    @Test
    void should_update_the_value_when_putting_a_new_item_that_associates_the_exact_same_key() {

        // [Arrange]
        final HashMapChaining<Integer, String> map = build();
        map.put(1, "Aurelien");
        map.put(2, "Tin");

        // [Act]
        map.put(1, "John");

        // [Assert]
        final int size = map.size();
        assertThat(size).isEqualTo(2);
        final String actualFirstValue = map.get(1);
        assertThat(actualFirstValue).isEqualTo("John");
        final String actualSecondValue = map.get(2);
        assertThat(actualSecondValue).isEqualTo("Tin");

    }

    @Test
    void should_not_alter_anything_when_removing_items_for_which_no_item_associates_the_provided_key() {

        // [Arrange]
        final HashMapChaining<Integer, String> map = build();
        map.put(1, "Aurelien");
        map.put(2, "Tin");

        // [Act]
        map.remove(5);

        // [Assert]
        final int size = map.size();
        assertThat(size).isEqualTo(2);
        final String actualFirstValue = map.get(1);
        assertThat(actualFirstValue).isEqualTo("Aurelien");
        final String actualSecondValue = map.get(2);
        assertThat(actualSecondValue).isEqualTo("Tin");

    }

    @Test
    void should_clean_the_map_from_an_item_when_removing_an_item_that_associates_a_key_that_exists_in_the_map() {

        // [Arrange]
        final HashMapChaining<Integer, String> map = build();
        map.put(11, "Momo");
        map.put(1, "Aurelien");
        map.put(2, "Tin");
        int size = map.size();
        assertThat(size).isEqualTo(3);
        String actualFirstValue = map.get(1);
        assertThat(actualFirstValue).isEqualTo("Aurelien");
        String actualSecondValue = map.get(2);
        assertThat(actualSecondValue).isEqualTo("Tin");
        String actualThirdValue = map.get(11);
        assertThat(actualThirdValue).isEqualTo("Momo");

        // [Act]
        map.remove(1);

        // [Assert]
        size = map.size();
        assertThat(size).isEqualTo(2);
        actualFirstValue = map.get(1);
        assertThat(actualFirstValue).isNull();
        actualSecondValue = map.get(2);
        assertThat(actualSecondValue).isEqualTo("Tin");
        actualThirdValue = map.get(11);
        assertThat(actualThirdValue).isEqualTo("Momo");

    }

    @Test
    void should_return_null_when_trying_to_find_an_item_that_associates_a_key_that_exists_in_the_map() {

        // [Arrange]
        final HashMapChaining<Integer, String> map = build();
        map.put(1, "Aurelien");
        map.put(2, "Tin");

        // [Act]
        final String actual = map.get(3);

        // [Assert]
        assertThat(actual).isNull();

    }

    @Test
    void should_return_the_right_value_when_finding_an_item_from_a_slot_that_contains_many_entries() {

        // [Arrange]
        final HashMapChaining<Integer, String> map = build();
        map.put(11111, "Little");
        map.put(1111, "Tiny");
        map.put(111, "Momo");
        map.put(11, "Tin");
        map.put(1, "Aurelien");

        // [Act]
        final String actual = map.get(111);

        // [Assert]
        assertThat(actual).isEqualTo("Momo");

    }

    private static HashMapChaining<Integer, String> build() {
        return new HashMapChaining<>(DEFAULT_CAPACITY, HASHCODER);
    }

}