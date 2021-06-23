package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MinStackTest {

    @Test
    void should_return_one_when_the_minimum_item_is_one_in_the_stack() {

        // [Arrange]
        final MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(5);
        minStack.push(1);
        minStack.push(10);

        // [Act]
        final Integer actualMin = minStack.min();

        // [Assert]
        assertThat(actualMin).isEqualTo(1);

    }

    @Test
    void should_return_two_when_the_minimum_item_is_now_two_in_the_stack() {

        // [Arrange]
        final MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(5);
        minStack.push(1);
        minStack.push(10);
        final Integer firstPopped = minStack.pop();
        final Integer secondPopped = minStack.pop();

        // [Act]
        final Integer actualMin = minStack.min();

        // [Assert]
        assertThat(firstPopped).isEqualTo(10);
        assertThat(secondPopped).isEqualTo(1);
        assertThat(actualMin).isEqualTo(2);

    }

}