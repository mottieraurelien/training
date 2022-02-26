import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayComparatorTest {

    private static final Comparator<String> COMPARATOR = Comparator.naturalOrder();

    @Test
    void should_return_true_when_the_first_array_is_a_subset_of_the_second_one() {

        // [Arrange]
        final String[] firstArray = new String[]{"A", "D", "E"};
        final String[] secondArray = new String[]{"A", "A", "D", "E"};
        final ArrayComparator<String> comparator = new ArrayComparator<>(COMPARATOR, firstArray, secondArray);

        // [Act]
        final boolean actual = comparator.isSubset();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_true_when_the_second_array_is_a_subset_of_the_first_one() {

        // [Arrange]
        final String[] firstArray = new String[]{"A", "B", "C", "D", "E"};
        final String[] secondArray = new String[]{"A", "E", "D"};
        final ArrayComparator<String> comparator = new ArrayComparator<>(COMPARATOR, firstArray, secondArray);

        // [Act]
        final boolean actual = comparator.isSubset();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_the_first_array_is_not_a_subset_of_the_second_one() {

        // [Arrange]
        final String[] firstArray = new String[]{"A", "D", "Z"};
        final String[] secondArray = new String[]{"A", "B", "C", "D", "E"};
        final ArrayComparator<String> comparator = new ArrayComparator<>(COMPARATOR, firstArray, secondArray);

        // [Act]
        final boolean actual = comparator.isSubset();

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_false_when_the_second_array_is_not_a_subset_of_the_first_one() {

        // [Arrange]
        final String[] firstArray = new String[]{"A", "D", "Z"};
        final String[] secondArray = new String[]{"A", "B", "C", "D", "E"};
        final ArrayComparator<String> comparator = new ArrayComparator<>(COMPARATOR, firstArray, secondArray);

        // [Act]
        final boolean actual = comparator.isSubset();

        // [Assert]
        assertThat(actual).isFalse();

    }

}