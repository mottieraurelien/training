package kata.structures.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringReverserTest {

    @Test
    void should_reverse_a_string_when_popping_all_items_one_by_one() {

        // [Arrange]
        final StringReverser reverser = new StringReverser();

        // [Act]
        final String actual = reverser.reverse("abcd");

        // [Assert]
        assertThat(actual).isEqualTo("dcba");

    }

}