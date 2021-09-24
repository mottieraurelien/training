import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class MaxSumTest {

    @ParameterizedTest
    @MethodSource("scenariosSmallLists")
    void should_return_the_right_max_sum_when_the_list_is_small(final List<Integer> smallListOfNumbers, final int expected) {

        // [Arrange]
        final MaxSum maxSum = new MaxSum(smallListOfNumbers);

        // [Act]
        final int actual = maxSum.find();

        // [Assert]
        assertThat(actual).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("scenariosLargeLists")
    void should_return_the_right_max_sum_when_the_list_is_large(final List<Integer> largeListOfNumbers, final int expected) {

        // [Arrange]
        final MaxSum maxSum = new MaxSum(largeListOfNumbers);

        // [Act]
        final int actual = maxSum.find();

        // [Assert]
        assertThat(actual).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("scenariosLargeLists")
    void should_return_the_right_max_sum_when_the_list_is_large_using_an_alternative(final List<Integer> largeListOfNumbers, final int expected) {

        // [Arrange]
        final MaxSum maxSum = new MaxSum(largeListOfNumbers);

        // [Act]
        final int actual = maxSum.findUsingAlternative();

        // [Assert]
        assertThat(actual).isEqualTo(expected);

    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenariosSmallLists() {
        return Stream.of(
                of(new ArrayList<>(), 0),
                of(List.of(1), 1),
                of(List.of(1, 2), 3),
                of(List.of(5, 9, 7, 11), 20),
                of(List.of(11, 20, 17, 19, 23, 4, 18), 43),
                of(List.of(21, 35, 84, 15, 55, 2), 139)
        );
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenariosLargeLists() {
        return Stream.of(
                of(generateFirstList(), 199),
                of(generateSecondList(), 1999),
                of(generateThirdList(), 19999)
        );
    }

    private static List<Integer> generateFirstList() {
        final List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) numbers.add(i);
        Collections.shuffle(numbers);
        return numbers;
    }

    private static List<Integer> generateSecondList() {
        final List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) numbers.add(i);
        Collections.shuffle(numbers);
        return numbers;
    }

    private static List<Integer> generateThirdList() {
        final List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) numbers.add(i);
        Collections.shuffle(numbers);
        return numbers;
    }

}