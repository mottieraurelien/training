package kata.structures;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapLinearProbingTest {

    private static final Integer DEFAULT_CAPACITY = 5;
    private static final Function<Integer, Integer> HASHCODER = key -> key % DEFAULT_CAPACITY;

    @Test
    void should_compute_the_index_only_one_time_when_putting_an_item_and_the_first_slot_is_empty() {

        // [Arrange]
        final HashMapLinearProbing<Integer, String> map = new HashMapLinearProbing<>(DEFAULT_CAPACITY, HASHCODER);
        assertThat(map.size()).isEqualTo(0);

        // [Act]
        map.put(1, "Test 1");

        // [Assert]
        assertThat(map.size()).isEqualTo(1);
        final String actualValue = map.get(1);
        assertThat(actualValue).isEqualTo("Test 1");

    }

    @Test
    void should_compute_the_index_a_second_time_when_putting_an_item_and_the_first_slot_is_already_occupied() {

        // [Arrange]
        final HashMapLinearProbing<Integer, String> map = new HashMapLinearProbing<>(DEFAULT_CAPACITY, HASHCODER);
        map.put(1, "Test 1");
        assertThat(map.size()).isEqualTo(1);

        // [Act]
        map.put(2, "Test 2");

        // [Assert]
        assertThat(map.size()).isEqualTo(2);
        final String actualFirstValue = map.get(1);
        assertThat(actualFirstValue).isEqualTo("Test 1");
        final String actualSecondValue = map.get(2);
        assertThat(actualSecondValue).isEqualTo("Test 2");

    }

    @Test
    void should_compute_the_index_a_third_time_when_putting_an_item_and_the_first_and_second_slots_are_already_occupied() {

        // [Arrange]
        final HashMapLinearProbing<Integer, String> map = new HashMapLinearProbing<>(DEFAULT_CAPACITY, HASHCODER);
        map.put(1, "Test 1");
        map.put(11, "Test 11");
        assertThat(map.size()).isEqualTo(2);

        // [Act]
        map.put(111, "Test 111");

        // [Assert]
        assertThat(map.size()).isEqualTo(3);
        final String actualFirstValue = map.get(1);
        assertThat(actualFirstValue).isEqualTo("Test 1");
        final String actualSecondValue = map.get(11);
        assertThat(actualSecondValue).isEqualTo("Test 11");
        final String actualThirdValue = map.get(111);
        assertThat(actualThirdValue).isEqualTo("Test 111");

    }

    @Test
    void should_grow_the_internal_array_when_putting_a_new_item_and_the_map_is_already_full() {

        // [Arrange]
        final HashMapLinearProbing<Integer, String> map = new HashMapLinearProbing<>(DEFAULT_CAPACITY, HASHCODER);
        map.put(1, "Test 1");
        map.put(2, "Test 2");
        map.put(3, "Test 3");
        map.put(4, "Test 4");
        map.put(5, "Test 5");
        assertThat(map.size()).isEqualTo(DEFAULT_CAPACITY);

        // [Act]
        map.put(6, "Test 6");

        // [Assert]
        assertThat(map.size()).isEqualTo(DEFAULT_CAPACITY + 1);

        final String actualFirstValue = map.get(1);
        assertThat(actualFirstValue).isEqualTo("Test 1");

        final String actualSecondValue = map.get(2);
        assertThat(actualSecondValue).isEqualTo("Test 2");

        final String actualThirdValue = map.get(3);
        assertThat(actualThirdValue).isEqualTo("Test 3");

        final String actualFourthValue = map.get(4);
        assertThat(actualFourthValue).isEqualTo("Test 4");

        final String actualFifthValue = map.get(5);
        assertThat(actualFifthValue).isEqualTo("Test 5");

        final String actualSixthValue = map.get(6);
        assertThat(actualSixthValue).isEqualTo("Test 6");

    }

    @Test
    void should_remove_the_item_using_the_first_computed_index_when_the_first_indexed_item_is_the_right_one() {

        // [Arrange]
        final HashMapLinearProbing<Integer, String> map = new HashMapLinearProbing<>(DEFAULT_CAPACITY, HASHCODER);
        map.put(1, "Test 1");

        // [Act]
        map.remove(1);

        // [Assert]
        assertThat(map.size()).isEqualTo(0);
        final String actualValue = map.get(1);
        assertThat(actualValue).isNull();

    }

    @Test
    void should_remove_the_item_using_the_third_computed_index_when_the_first_and_second_indexed_items_are_not_the_right_one() {

        // [Arrange]
        final HashMapLinearProbing<Integer, String> map = new HashMapLinearProbing<>(DEFAULT_CAPACITY, HASHCODER);
        map.put(1, "Test 1");
        map.put(11, "Test 11");
        map.put(111, "Test 111");

        // [Act]
        map.remove(111);

        // [Assert]
        assertThat(map.size()).isEqualTo(2);
        final String actualValue = map.get(111);
        assertThat(actualValue).isNull();
        assertThat(map.get(1)).isEqualTo("Test 1");
        assertThat(map.get(11)).isEqualTo("Test 11");

    }

}