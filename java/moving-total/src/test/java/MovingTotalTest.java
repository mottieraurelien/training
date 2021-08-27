import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovingTotalTest {

    @Test
    void should_contain_six_and_nine_when_adding_one_two_three_four() {

        // [Arrange]
        final MovingTotal movingTotal = new MovingTotal();

        // [Act]
        movingTotal.append(new int[]{1, 2, 3, 4});

        // [Assert]
        assertThat(movingTotal.contains(6)).isTrue();
        assertThat(movingTotal.contains(9)).isTrue();
        assertThat(movingTotal.contains(12)).isFalse();
        assertThat(movingTotal.contains(7)).isFalse();

    }

    @Test
    void should_contain_six_nine_and_twelve_when_adding_five_to_the_existing_one_two_three_four() {

        // [Arrange]
        final MovingTotal movingTotal = new MovingTotal();
        movingTotal.append(new int[]{1, 2, 3, 4});

        // [Act]
        movingTotal.append(new int[]{5});

        // [Assert]
        assertThat(movingTotal.contains(6)).isTrue();
        assertThat(movingTotal.contains(9)).isTrue();
        assertThat(movingTotal.contains(12)).isTrue();
        assertThat(movingTotal.contains(7)).isFalse();

    }

}
