package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DynamicArrayTest {

    @Test
    void should_initialise_the_array_with_slots_when_creating_a_new_array_with_a_specific_dimension() {

        // [Arrange]
        final int inputLength = 3;

        // [Act]
        final DynamicArray<Integer> array = new DynamicArray<>(inputLength);

        // [Assert]
        assertThat(array).isNotNull();
        assertThat(array.size()).isEqualTo(0);

    }

    @Test
    void should_append_to_array_when_adding_a_new_item_without_specifying_a_specific_index() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray<Integer> array = new DynamicArray<>(inputLength);

        // [Act]
        array.insert(10);

        // [Assert]
        assertThat(array).isNotNull();
        assertThat(array.size()).isEqualTo(1);

    }

    @Test
    void should_resize_the_array_when_adding_a_new_item_and_no_left_space() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray<Integer> array = new DynamicArray<>(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);

        // [Act]
        array.insert(40);

        // [Assert]
        assertThat(array).isNotNull();
        assertThat(array.size()).isEqualTo(4);

    }

    @Test
    void should_not_move_any_item_when_removing_the_last_item_from_the_array() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray<Integer> array = new DynamicArray<>(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);
        array.insert(40);

        // [Act]
        array.removeAt(3);

        // [Assert]
        assertThat(array).isNotNull();
        assertThat(array.size()).isEqualTo(3);

    }

    @Test
    void should_throw_an_exception_when_trying_to_remove_an_item_from_an_index_that_is_not_contained_in_the_array() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray<Integer> array = new DynamicArray<>(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);
        array.insert(40);
        final int lastIndex = 3;

        // [Act / Assert]
        final int wrongIndex = 100;
        assertThatThrownBy(() -> array.removeAt(wrongIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Index must be between [0," + lastIndex + "[.");

    }

    @Test
    void should_return_zero_when_finding_the_index_of_the_first_item() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray<Integer> array = new DynamicArray<>(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);

        // [Act]
        final int actualIndex = array.indexOf(10);

        // [Assert]
        assertThat(actualIndex).isEqualTo(0);

    }

    @Test
    void should_return_minus_one_when_trying_to_find_the_index_of_an_item_that_does_not_exist_in_the_array() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray<Integer> array = new DynamicArray<>(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);

        // [Act]
        final int actualIndex = array.indexOf(100);

        // [Assert]
        assertThat(actualIndex).isEqualTo(-1);

    }

    @Test
    void should_return_forty_when_finding_the_maximum_item_in_the_array() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray<Integer> array = new DynamicArray<>(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);
        array.insert(60);

        // [Act]
        final int actualMax = array.max(Integer::compareTo);

        // [Assert]
        assertThat(actualMax).isEqualTo(80);

    }

    @Test
    void should_return_an_empty_array_when_intersecting_an_empty_array_with_a_populated_one() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray<Integer> dynamicArray = new DynamicArray<>(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(60);
        final DynamicArray<Integer> emptyDynamicArray = new DynamicArray<>(0);

        // [Act]
        final DynamicArray<Integer> intersect = dynamicArray.intersect(emptyDynamicArray);

        // [Assert]
        assertThat(intersect).isNotNull();
        assertThat(intersect.size()).isEqualTo(0);

    }

    @Test
    void should_return_an_empty_array_when_intersecting_a_populated_array_with_an_empty_one() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray<Integer> dynamicArray = new DynamicArray<>(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(60);
        final DynamicArray<Integer> emptyDynamicArray = new DynamicArray<>(0);

        // [Act]
        final DynamicArray<Integer> intersect = emptyDynamicArray.intersect(dynamicArray);

        // [Assert]
        assertThat(intersect).isNotNull();
        assertThat(intersect.size()).isEqualTo(0);

    }

    @Test
    void should_return_an_array_with_common_items_only_when_intersecting_two_populated_arrays() {

        // [Arrange]
        final DynamicArray<Integer> dynamicArray = new DynamicArray<>(4);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(60);
        final DynamicArray<Integer> anotherDynamicArray = new DynamicArray<>(7);
        anotherDynamicArray.insert(10);
        anotherDynamicArray.insert(20);
        anotherDynamicArray.insert(30);
        anotherDynamicArray.insert(40);
        anotherDynamicArray.insert(50);
        anotherDynamicArray.insert(60);
        anotherDynamicArray.insert(70);

        // [Act]
        final DynamicArray<Integer> intersect = anotherDynamicArray.intersect(dynamicArray);

        // [Assert]
        assertThat(intersect).isNotNull();
        assertThat(intersect.size()).isEqualTo(3);
        assertThat(intersect.indexOf(20)).isEqualTo(0);
        assertThat(intersect.indexOf(50)).isEqualTo(1);
        assertThat(intersect.indexOf(60)).isEqualTo(2);

        assertThat(intersect.contains(10)).isFalse();
        assertThat(intersect.contains(30)).isFalse();
        assertThat(intersect.contains(40)).isFalse();
        assertThat(intersect.contains(70)).isFalse();
        assertThat(intersect.contains(80)).isFalse();

    }

    @Test
    void should_return_a_resized_array_with_reversed_values_when_reversing_an_array() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray<Integer> dynamicArray = new DynamicArray<>(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(60);

        // [Act]
        final DynamicArray<Integer> reversed = dynamicArray.reverse();

        // [Assert]
        assertThat(reversed).isNotNull();
        assertThat(reversed.size()).isEqualTo(4);
        assertThat(reversed.indexOf(60)).isEqualTo(0);
        assertThat(reversed.indexOf(80)).isEqualTo(1);
        assertThat(reversed.indexOf(50)).isEqualTo(2);
        assertThat(reversed.indexOf(20)).isEqualTo(3);

    }

    @Test
    void should_insert_an_item_at_the_beginning_of_an_array_and_move_the_next_ones_when_inserting_the_item_at_index_zero() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray<Integer> dynamicArray = new DynamicArray<>(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);

        // [Act]
        dynamicArray.insertAt(100, 0);

        // [Assert]
        assertThat(dynamicArray.size()).isEqualTo(4);
        assertThat(dynamicArray.indexOf(100)).isEqualTo(0);
        assertThat(dynamicArray.indexOf(20)).isEqualTo(1);
        assertThat(dynamicArray.indexOf(50)).isEqualTo(2);
        assertThat(dynamicArray.indexOf(80)).isEqualTo(3);

    }

    @Test
    void should_resize_the_array_plus_insert_an_item_at_the_beginning_of_an_array_and_move_the_next_ones_when_inserting_the_item_at_index_zero() {

        // [Arrange]
        final int inputLength = 2;
        final DynamicArray<Integer> dynamicArray = new DynamicArray<>(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(90);

        // [Act]
        dynamicArray.insertAt(100, 0);

        // [Assert]
        assertThat(dynamicArray.size()).isEqualTo(5);
        assertThat(dynamicArray.indexOf(100)).isEqualTo(0);
        assertThat(dynamicArray.indexOf(20)).isEqualTo(1);
        assertThat(dynamicArray.indexOf(50)).isEqualTo(2);
        assertThat(dynamicArray.indexOf(80)).isEqualTo(3);
        assertThat(dynamicArray.indexOf(90)).isEqualTo(4);

    }

    @Test
    void should_insert_an_item_in_the_middle_and_move_the_next_ones_when_inserting_the_item_int_middle() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray<Integer> dynamicArray = new DynamicArray<>(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);

        // [Act]
        dynamicArray.insertAt(100, 2);

        // [Assert]
        assertThat(dynamicArray.size()).isEqualTo(4);
        assertThat(dynamicArray.indexOf(20)).isEqualTo(0);
        assertThat(dynamicArray.indexOf(50)).isEqualTo(1);
        assertThat(dynamicArray.indexOf(100)).isEqualTo(2);
        assertThat(dynamicArray.indexOf(80)).isEqualTo(3);

    }

    @Test
    void should_resize_the_array_plus_insert_an_item_in_the_middle_and_move_the_next_ones_when_inserting_the_item_int_middle() {

        // [Arrange]
        final int inputLength = 2;
        final DynamicArray<Integer> dynamicArray = new DynamicArray<>(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(90);

        // [Act]
        dynamicArray.insertAt(100, 2);

        // [Assert]
        assertThat(dynamicArray.size()).isEqualTo(5);
        assertThat(dynamicArray.indexOf(20)).isEqualTo(0);
        assertThat(dynamicArray.indexOf(50)).isEqualTo(1);
        assertThat(dynamicArray.indexOf(100)).isEqualTo(2);
        assertThat(dynamicArray.indexOf(80)).isEqualTo(3);
        assertThat(dynamicArray.indexOf(90)).isEqualTo(4);

    }

    @Test
    void should_throw_an_exception_when_insert_an_item_at_a_wrong_position() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray<Integer> array = new DynamicArray<>(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);
        array.insert(40);

        // [Act / Assert]
        final int wrongIndex = 10;
        assertThatThrownBy(() -> array.insertAt(99, wrongIndex))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("The array is not large enough to welcome the item to insert at this specific index.");

    }

}