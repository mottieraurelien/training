import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class SentenceMiddleReverserTest {

    @ParameterizedTest
    @MethodSource("scenarios")
    void should_reverse_the_words_in_the_middle_of_the_sentence_only_when_the_sentence_contains_more_than_three_words(
            final String sentence, final String expected) {

        // [Arrange]
        final SentenceMiddleReverser reverser = new SentenceMiddleReverser(sentence);

        // [Act]
        final String actual = reverser.reverse();

        // [Assert]
        assertThat(actual).isEqualTo(expected);

    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenarios() {
        return Stream.of(
                of("", ""),
                of("Hello", "Hello"),
                of("Hello World", "Hello World"),
                of("Hello My World", "Hello My World"),
                of("Hello My Beautiful World", "Hello Beautiful My World"),
                of("Twinkle twinkle little star how I wonder what you are",
                        "Twinkle you what wonder I how star little twinkle are"
                )
        );
    }


}