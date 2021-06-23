package kata.structures;

import kata.structures.example.PairWithTargetedSumFinder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PairWithTargetedSumFinderTest {

    @Test
    void should_return_the_right_indexes_when_finding_numbers_for_which_their_sum_equals_the_target() {

        // [Arrange]
        final Integer[] inputs = new Integer[]{2, 7, 11, 15};
        final PairWithTargetedSumFinder<Integer> finder = new PairWithTargetedSumFinder<>(inputs);

        // [Act]
        final Integer[] actualIndexes = finder.find(9); // 2+7=9

        // [Assert]
        assertThat(actualIndexes).hasSize(2);
        assertThat(actualIndexes).containsExactly(0, 1);

    }

    @Test
    void should_return_null_when_there_is_not_any_sum_that_equals_the_target() {

        // [Arrange]
        final Integer[] inputs = new Integer[]{2, 3, 4, 8};
        final PairWithTargetedSumFinder<Integer> finder = new PairWithTargetedSumFinder<>(inputs);

        // [Act]
        final Integer[] actualIndexes = finder.find(9);

        // [Assert]
        assertThat(actualIndexes).isNull();

    }

}