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

}