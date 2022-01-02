import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MinStackTest {

    @Test
    void should_compare_the_current_min_with_the_new_item_when_pushing_a_new_item_onto_the_stack() {

        // [Arrange]
        final MinStack<Integer> minStack = new MinStack<>();
        minStack.push(-2);
        minStack.push(0);

        // [Act]
        minStack.push(-3);

        // [Assert - Part 1]
        assertThat(minStack.size()).isEqualTo(3);
        assertThat(minStack.getMin()).isEqualTo(-3);

        // [Assert - Part 2]
        final Integer actual = minStack.pop();
        assertThat(actual).isEqualTo(-3);
        assertThat(minStack.top()).isEqualTo(0);

        // [Assert - Part 3]
        assertThat(minStack.getMin()).isEqualTo(-2);

    }

}