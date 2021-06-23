package kata.structures.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class FirstRepeatedCharacterTest {

    @ParameterizedTest
    @MethodSource("scenarios")
    void should_return_the_first_repeated_character(final String input, final Character expected) {

        // [Arrange]
        final FirstRepeatedCharacterFinder finder = new FirstRepeatedCharacterFinder(input);

        // [Act]
        final Character actual = finder.find();

        // [Assert]
        assertThat(actual).isEqualTo(expected);

    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenarios() {
        return Stream.of(
                of("atest", 't'),
                of("ssoisworking", 's'),
                of("blablabla", 'b'),
                of("malitzschkendorf", null)
        );
    }

}