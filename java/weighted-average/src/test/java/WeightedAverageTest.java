import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.of;

class WeightedAverageTest {

    @Test
    void should_throw_an_exception_when_arguments_are_null() {

        // [Arrange]
        final WeightedAverage weightedAverage = new WeightedAverage(null, null);

        // [Act / Assert]
        assertThatThrownBy(weightedAverage::mean)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void should_throw_an_exception_when_arguments_do_not_have_the_same_size() {

        // [Arrange]
        final int[] twoNumbers = new int[]{1, 2};
        final int[] threeWeights = new int[]{1, 2, 3};
        final WeightedAverage weightedAverage = new WeightedAverage(twoNumbers, threeWeights);

        // [Act / Assert]
        assertThatThrownBy(weightedAverage::mean)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest
    @MethodSource("scenariosSmallIntegers")
    void should_return_the_right_means_when_the_integers_are_small(final int[] smallNumbers, final int[] smallWeights,
                                                                   final double expected) {

        // [Arrange]
        final WeightedAverage weightedAverage = new WeightedAverage(smallNumbers, smallWeights);

        // [Act]
        final double actual = weightedAverage.mean();

        // [Assert]
        assertThat(actual).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("scenariosLargeIntegers")
    void should_return_the_right_means_when_the_integers_are_large(final int[] largeNumbers, final int[] largeWeights,
                                                                   final double expected) {

        // [Arrange]
        final WeightedAverage weightedAverage = new WeightedAverage(largeNumbers, largeWeights);

        // [Act]
        final double actual = weightedAverage.mean();

        // [Assert]
        assertThat(actual).isEqualTo(expected);

    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenariosSmallIntegers() {
        return Stream.of(
                of(new int[]{1, 2}, new int[]{3, 4}, 1.5714285714285714),
                of(new int[]{3, 6}, new int[]{4, 2}, 4.0),
                of(new int[]{10, 15}, new int[]{12, 17}, 12.931034482758621),
                of(new int[]{5, 20}, new int[]{7, 15}, 15.227272727272727)
        );
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenariosLargeIntegers() {
        return Stream.of(
                of(new int[]{441, 299}, new int[]{343, 444}, 360.8881829733164),
                of(new int[]{1234, 2345}, new int[]{3210, 2104}, 1673.884079789236),
                of(new int[]{12345, 23456}, new int[]{15846, 17875}, 18234.775659084844)
        );
    }

}