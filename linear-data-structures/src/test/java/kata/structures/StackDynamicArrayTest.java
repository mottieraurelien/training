package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StackDynamicArrayTest {

    @Test
    void should_throw_an_exception_when_creating_a_stack_from_an_empty_array() {

        // [Act / Assert]
        final Integer[] emptyArray = new Integer[0];
        assertThatThrownBy(() -> new StackDynamicArray<>(emptyArray))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot initialise stack from an empty array.");

    }

    @Test
    void should_add_items_on_top_of_the_stack_when_pushing_items() {

        // [Arrange]
        final StackDynamicArray<Integer> stack = new StackDynamicArray<>(2);

        // [Act]
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // [Assert]
        assertThat(stack.isEmpty()).isFalse();

        final Integer firstItem = stack.pop();
        assertThat(firstItem).isEqualTo(30);

        final Integer secondItem = stack.pop();
        assertThat(secondItem).isEqualTo(20);

        final Integer thirdItem = stack.pop();
        assertThat(thirdItem).isEqualTo(10);

        final Integer fourthItem = stack.pop();
        assertThat(fourthItem).isNull();

        assertThat(stack.isEmpty()).isTrue();

    }

    @Test
    void should_return_null_when_popping_from_an_empty_list() {

        // [Arrange]
        final StackDynamicArray<Integer> stack = new StackDynamicArray<>(10);

        // [Act]
        final Integer popped = stack.pop();

        // [Assert]
        assertThat(popped).isNull();

    }

    @Test
    void should_return_null_when_peeking_from_an_empty_list() {

        // [Arrange]
        final StackDynamicArray<Integer> stack = new StackDynamicArray<>(10);

        // [Act]
        final Integer popped = stack.peek();

        // [Assert]
        assertThat(popped).isNull();

    }

    @Test
    void should_return_the_first_item_without_removing_it_when_peeking_from_a_populated_list() {

        // [Arrange]
        final Integer[] items = new Integer[]{10, 20, 30};
        final StackDynamicArray<Integer> stack = new StackDynamicArray<>(items);

        // [Act]
        final Integer popped = stack.peek();

        // [Assert]
        assertThat(popped).isEqualTo(30);
        assertThat(stack.size()).isEqualTo(3);

    }

    @Test
    void should_throw_an_exception_when_pushing_null() {

        // [Arrange]
        final StackDynamicArray<Integer> stack = new StackDynamicArray<>(10);

        // [Act / Assert]
        assertThatThrownBy(() -> stack.push(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pushing null is not allowed.");

    }

}