import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MinStackIntegerTest {

    @Test
    void should_compare_the_current_min_with_the_new_integer_when_pushing_a_new_integer_onto_the_stack() {

        // [Arrange]
        final MinStackInteger minStackInteger = new MinStackInteger();
        minStackInteger.push(-2);
        minStackInteger.push(0);

        // [Act]
        minStackInteger.push(-3);

        // [Assert - Part 1]
        assertThat(minStackInteger.size()).isEqualTo(3);
        assertThat(minStackInteger.getMin()).isEqualTo(-3);

        // [Assert - Part 2]
        final Integer actual = minStackInteger.pop();
        assertThat(actual).isEqualTo(-3);
        assertThat(minStackInteger.top()).isEqualTo(0);

        // [Assert - Part 3]
        assertThat(minStackInteger.getMin()).isEqualTo(-2);

    }

    @Test
    void should_handle_edge_cases_when_popping_all_items_from_the_stack() {

        // [Arrange]
        final MinStackInteger minStackInteger = new MinStackInteger();

        // [Act / Assert - Part 1]
        minStackInteger.push(2147483646);
        assertThat(minStackInteger.size()).isEqualTo(1);
        assertThat(minStackInteger.top()).isEqualTo(2147483646);
        assertThat(minStackInteger.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 2]
        minStackInteger.push(2147483646);
        assertThat(minStackInteger.size()).isEqualTo(2);
        assertThat(minStackInteger.top()).isEqualTo(2147483646);
        assertThat(minStackInteger.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 3]
        minStackInteger.push(2147483647);
        assertThat(minStackInteger.size()).isEqualTo(3);
        assertThat(minStackInteger.top()).isEqualTo(2147483647);
        assertThat(minStackInteger.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 4]
        final Integer firstPop = minStackInteger.pop();
        assertThat(firstPop).isEqualTo(2147483647);
        assertThat(minStackInteger.size()).isEqualTo(2);
        assertThat(minStackInteger.top()).isEqualTo(2147483646);
        assertThat(minStackInteger.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 5]
        final Integer secondPop = minStackInteger.pop();
        assertThat(secondPop).isEqualTo(2147483646);
        assertThat(minStackInteger.size()).isEqualTo(1);
        assertThat(minStackInteger.top()).isEqualTo(2147483646);
        assertThat(minStackInteger.getMin()).isEqualTo(2147483646);

        // [Act / Assert - Part 6]
        final Integer thirdPop = minStackInteger.pop();
        assertThat(thirdPop).isEqualTo(2147483646);
        assertThat(minStackInteger.size()).isEqualTo(0);
        assertThat(minStackInteger.top()).isNull();
        assertThat(minStackInteger.getMin()).isNull();

        // [Act / Assert - Part 7]
        minStackInteger.push(2147483647);
        assertThat(minStackInteger.size()).isEqualTo(1);
        assertThat(minStackInteger.top()).isEqualTo(2147483647);
        assertThat(minStackInteger.getMin()).isEqualTo(2147483647);

        // [Act / Assert - Part 8]
        minStackInteger.push(-2147483648);
        assertThat(minStackInteger.size()).isEqualTo(2);
        assertThat(minStackInteger.top()).isEqualTo(-2147483648);
        assertThat(minStackInteger.getMin()).isEqualTo(-2147483648);

        // [Act / Assert - Part 9]
        final Integer fourthPop = minStackInteger.pop();
        assertThat(fourthPop).isEqualTo(-2147483648);
        assertThat(minStackInteger.size()).isEqualTo(1);
        assertThat(minStackInteger.top()).isEqualTo(2147483647);
        assertThat(minStackInteger.getMin()).isEqualTo(2147483647);

    }

}