package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StackUsingTwoQueuesTest {

    @Test
    void should_throw_an_exception_when_trying_to_add_a_null_item_to_the_stack() {

        // [Arrange]
        final StackUsingTwoQueues<Integer> stack = new StackUsingTwoQueues<>();

        // [Act / Assert]
        assertThatThrownBy(() -> stack.push(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Item must not be null.");

    }

    @Test
    void should_consider_the_item_as_the_new_node_to_pop_when_adding_a_new_item_to_the_stack() {

        // [Arrange]
        final StackUsingTwoQueues<Integer> stack = new StackUsingTwoQueues<>();

        // [Act]
        stack.push(10);
        stack.push(30);
        stack.push(40);
        stack.push(20);

        // [Assert]
        final Integer firstItem = stack.pop();
        assertThat(firstItem).isEqualTo(20);
        final Integer secondItem = stack.pop();
        assertThat(secondItem).isEqualTo(40);
        final Integer thirdItem = stack.pop();
        assertThat(thirdItem).isEqualTo(30);
        final Integer fourthItem = stack.pop();
        assertThat(fourthItem).isEqualTo(10);

    }

    @Test
    void should_throw_an_exception_when_trying_to_pop_from_an_empty_stack() {

        // [Arrange]
        final StackUsingTwoQueues<Integer> stack = new StackUsingTwoQueues<>();

        // [Act / Assert]
        assertThatThrownBy(stack::pop)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Queue is already empty.");

    }

    @Test
    void should_throw_an_exception_when_peeking_from_an_empty_stack() {

        // [Arrange]
        final StackUsingTwoQueues<Integer> stack = new StackUsingTwoQueues<>();

        // [Act / Assert]
        assertThatThrownBy(stack::peek)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Queue is already empty.");

    }

    @Test
    void should_return_ten_when_peeking_from_a_populated_stack() {

        // [Arrange]
        final StackUsingTwoQueues<Integer> stack = new StackUsingTwoQueues<>();
        stack.push(10);
        stack.push(8);
        stack.push(6);

        // [Act]
        final Integer actualItem = stack.peek();

        // [Assert]
        assertThat(actualItem).isEqualTo(6);

    }

}