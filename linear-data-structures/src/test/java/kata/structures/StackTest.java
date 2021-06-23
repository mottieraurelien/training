package kata.structures;

import kata.data.Node;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StackTest {

    @Test
    void should_add_items_on_top_of_the_stack_when_pushing_items() {

        // [Arrange]
        final Stack<Integer> stack = new Stack<>();

        // [Act]
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // [Assert]
        final Node<Integer> firstItem = stack.peek();
        assertThat(firstItem.getValue()).isEqualTo(30);

        final Node<Integer> secondItem = firstItem.getNext();
        assertThat(secondItem).isNotNull();
        assertThat(secondItem.getValue()).isEqualTo(20);

        final Node<Integer> thirdItem = secondItem.getNext();
        assertThat(thirdItem).isNotNull();
        assertThat(thirdItem.getValue()).isEqualTo(10);

        final Node<Integer> fourthItem = thirdItem.getNext();
        assertThat(fourthItem).isNull();

    }

    @Test
    void should_return_null_when_popping_from_an_empty_list() {

        // [Arrange]
        final Stack<Integer> stack = new Stack<>();

        // [Act]
        final Node<Integer> popped = stack.pop();

        // [Assert]
        assertThat(popped).isNull();

    }

    @Test
    void should_throw_an_exception_when_pushing_null() {

        // [Arrange]
        final Stack<Integer> stack = new Stack<>();

        // [Act / Assert]
        assertThatThrownBy(() -> stack.push(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pushing null is not allowed.");

    }

}