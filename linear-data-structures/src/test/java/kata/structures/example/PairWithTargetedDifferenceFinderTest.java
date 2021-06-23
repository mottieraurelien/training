package kata.structures.example;

import kata.data.Pair;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static kata.data.Pair.of;
import static org.assertj.core.api.Assertions.assertThat;

class PairWithTargetedDifferenceFinderTest {

    @Test
    void should_return_pairs_with_the_exact_same_difference() {

        // [Arrange]
        final Integer[] inputs = new Integer[]{1, 7, 5, 9, 2, 12, 3};
        final PairWithTargetedDifferenceFinder<Integer> finder = new PairWithTargetedDifferenceFinder<>(inputs);

        // [Act]
        final Set<Pair<Integer, Integer>> actual = finder.find(2);

        // [Assert]
        assertThat(actual).hasSize(4);
        assertThat(actual).containsExactlyInAnyOrder(
                of(1, 3),
                of(3, 5),
                of(5, 7),
                of(7, 9)
        );

    }

}