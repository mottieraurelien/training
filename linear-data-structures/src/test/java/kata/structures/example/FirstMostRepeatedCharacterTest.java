package kata.structures.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirstMostRepeatedCharacterTest {

    @Test
    void should_return_seven_as_the_first_most_repeated_character() {

        // [Arrange]
        final Integer[] input = new Integer[]{1, 2, 2, 3, 3, 3, 4, 5, 5, 6, 7, 7, 7, 8, 8};
        final FirstMostRepeatedCharacter<Integer> finder = new FirstMostRepeatedCharacter<>(input);

        // [Act]
        final Integer actual = finder.find();

        // [Assert]
        assertThat(actual).isEqualTo(3);

    }

}