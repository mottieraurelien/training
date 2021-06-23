package kata.structures.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class FirstUniqueCharacterFinderTest {

    @ParameterizedTest
    @MethodSource("scenarios")
    void should_return_the_first_unique_character(final String input, final Character expected) {

        // [Arrange]
        final FirstUniqueCharacterFinder finder = new FirstUniqueCharacterFinder(input);

        // [Act]
        final Character actual = finder.find();

        // [Assert]
        assertThat(actual).isEqualTo(expected);

    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenarios() {
        return Stream.of(
                of("atest", 'a'),
                of("ssoisworking", 'w'),
                of("blablabla", null)
        );
    }

}