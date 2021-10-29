package aurelienmottier.bowling.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    @Test
    void should_compute_the_total_score_when_providing_a_basic_list_of_frames() {

        // [Arrange]
        final String input = "90909090909090909090";
        final Game game = new Game(input);

        // [Act]
        final int actualTotalScore = game.total();

        // [Assert]
        final int expectedTotalScore = 90;
        assertEquals(expectedTotalScore, actualTotalScore);

    }

    @Test
    void should_compute_the_total_score_when_providing_a_list_of_frames_that_contains_spares_and_strikes() {

        // [Arrange]
        final String input = "14456/5/X017/6/X2/6";
        final Game game = new Game(input);

        // [Act]
        final int actualTotalScore = game.total();

        // [Assert]
        final int expectedTotalScore = 133;
        assertEquals(expectedTotalScore, actualTotalScore);

    }

}