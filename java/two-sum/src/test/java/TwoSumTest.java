import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

class TwoSumTest {

    @Test
    void should_return_four_and_five_when_the_targeted_sum_is_nine() {

        // [Arrange]
        final List<Integer> numbers = of(1, 3, 4, 5, 7, 10, 11);
        final TwoSum twoSum = new TwoSum(numbers);

        // [Act]
        final List<Integer> actual = twoSum.target(9);

        // [Arrange]
        assertThat(actual).containsExactly(4, 5);

    }

    @Test
    void should_return_an_empty_list_when_there_is_no_solution_to_match_the_targeted_sum() {

        // [Arrange]
        final List<Integer> numbers = of(1, 3, 4, 5, 7, 10, 11);
        final TwoSum twoSum = new TwoSum(numbers);

        // [Act]
        final List<Integer> actual = twoSum.target(0);

        // [Arrange]
        assertThat(actual).isEmpty();

    }

}