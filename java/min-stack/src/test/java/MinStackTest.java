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

    @Test
    void should_handle_edge_cases_when_popping_all_items_from_the_stack() {

        // [Arrange]
        final MinStack<Integer> minStack = new MinStack<>();

        // [Act / Assert - Part 1]
        minStack.push(2147483646);
        assertThat(minStack.size()).isEqualTo(1);
        assertThat(minStack.top()).isEqualTo(2147483646);
        assertThat(minStack.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 2]
        minStack.push(2147483646);
        assertThat(minStack.size()).isEqualTo(2);
        assertThat(minStack.top()).isEqualTo(2147483646);
        assertThat(minStack.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 3]
        minStack.push(2147483647);
        assertThat(minStack.size()).isEqualTo(3);
        assertThat(minStack.top()).isEqualTo(2147483647);
        assertThat(minStack.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 4]
        final Integer firstPop = minStack.pop();
        assertThat(firstPop).isEqualTo(2147483647);
        assertThat(minStack.size()).isEqualTo(2);
        assertThat(minStack.top()).isEqualTo(2147483646);
        assertThat(minStack.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 5]
        final Integer secondPop = minStack.pop();
        assertThat(secondPop).isEqualTo(2147483646);
        assertThat(minStack.size()).isEqualTo(1);
        assertThat(minStack.top()).isEqualTo(2147483646);
        assertThat(minStack.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 6]
        final Integer thirdPop = minStack.pop();
        assertThat(thirdPop).isEqualTo(2147483646);
        assertThat(minStack.size()).isEqualTo(0);
        assertThat(minStack.top()).isNull();
        assertThat(minStack.getMin()).isNull();

        // [Act / Assert - Part 7]
        minStack.push(2147483647);
        assertThat(minStack.size()).isEqualTo(1);
        assertThat(minStack.top()).isEqualTo(2147483647);
        assertThat(minStack.getMin()).isEqualTo(2147483647);

        // [Act / Assert - Part 8]
        minStack.push(-2147483648);
        assertThat(minStack.size()).isEqualTo(2);
        assertThat(minStack.top()).isEqualTo(-2147483648);
        assertThat(minStack.getMin()).isEqualTo(-2147483648);

        // [Act / Assert - Part 9]
        final Integer fourthPop = minStack.pop();
        assertThat(fourthPop).isEqualTo(-2147483648);
        assertThat(minStack.size()).isEqualTo(1);
        assertThat(minStack.top()).isEqualTo(2147483647);
        assertThat(minStack.getMin()).isEqualTo(2147483647);

    }

}