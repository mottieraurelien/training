package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BiStackTest {

    @Test
    void should_add_first_when_pushing_items_to_the_first_stack() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(10);

        // [Act]
        biStack.pushOne(10);
        biStack.pushOne(20);
        biStack.pushOne(30);

        // [Assert]
        final Integer actualFirstItem = biStack.popOne();
        assertThat(actualFirstItem).isEqualTo(30);
        final Integer actualSecondItem = biStack.popOne();
        assertThat(actualSecondItem).isEqualTo(20);
        final Integer actualThirdItem = biStack.popOne();
        assertThat(actualThirdItem).isEqualTo(10);
        final Integer actualFourthItem = biStack.popOne();
        assertThat(actualFourthItem).isNull();

    }

    @Test
    void should_add_last_when_pushing_items_to_the_second_stack() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(10);

        // [Act]
        biStack.pushTwo(10);
        biStack.pushTwo(20);
        biStack.pushTwo(30);

        // [Assert]
        final Integer actualFirstItem = biStack.popTwo();
        assertThat(actualFirstItem).isEqualTo(30);
        final Integer actualSecondItem = biStack.popTwo();
        assertThat(actualSecondItem).isEqualTo(20);
        final Integer actualThirdItem = biStack.popTwo();
        assertThat(actualThirdItem).isEqualTo(10);
        final Integer actualFourthItem = biStack.popTwo();
        assertThat(actualFourthItem).isNull();

    }

    @Test
    void should_return_false_when_the_first_stack_is_not_empty() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(10);
        biStack.pushOne(10);
        biStack.pushOne(20);
        biStack.pushOne(30);

        // [Act]
        final boolean actual = biStack.isOneEmpty();

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_true_when_the_first_stack_is_empty() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(10);

        // [Act]
        final boolean actual = biStack.isOneEmpty();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_true_when_the_first_stack_is_empty_after_popping_everything_from_the_first_stack() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(10);
        biStack.pushOne(10);
        biStack.pushOne(20);
        biStack.pushOne(30);
        assertThat(biStack.isOneEmpty()).isFalse();
        biStack.popOne();
        biStack.popOne();
        biStack.popOne();

        // [Act]
        final boolean actual = biStack.isOneEmpty();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_the_second_stack_is_not_empty() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(10);
        biStack.pushTwo(10);
        biStack.pushTwo(20);
        biStack.pushTwo(30);

        // [Act]
        final boolean actual = biStack.isTwoEmpty();

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_true_when_the_second_stack_is_empty() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(10);

        // [Act]
        final boolean actual = biStack.isTwoEmpty();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_true_when_the_second_stack_is_empty_after_popping_everything_from_the_second_stack() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(10);
        biStack.pushTwo(10);
        biStack.pushTwo(20);
        biStack.pushTwo(30);
        assertThat(biStack.isTwoEmpty()).isFalse();
        biStack.popTwo();
        biStack.popTwo();
        biStack.popTwo();

        // [Act]
        final boolean actual = biStack.isTwoEmpty();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_true_when_the_first_stack_is_full() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(6);
        biStack.pushOne(1);
        biStack.pushOne(2);
        biStack.pushTwo(9);
        biStack.pushTwo(8);
        biStack.pushTwo(7);
        biStack.pushTwo(6);

        // [Act]
        final boolean actual = biStack.isOneFull();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_the_first_stack_is_not_full() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(6);
        biStack.pushOne(1);
        biStack.pushOne(2);
        biStack.pushTwo(8);
        biStack.pushTwo(7);
        biStack.pushTwo(6);

        // [Act]
        final boolean actual = biStack.isOneFull();

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_true_when_the_second_stack_is_full() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(6);
        biStack.pushOne(1);
        biStack.pushOne(2);
        biStack.pushOne(3);
        biStack.pushOne(4);
        biStack.pushOne(5);
        biStack.pushTwo(9);

        // [Act]
        final boolean actual = biStack.isTwoFull();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_the_second_stack_is_not_full() {

        // [Arrange]
        final BiStack<Integer> biStack = new BiStack<>(6);
        biStack.pushTwo(9);

        // [Act]
        final boolean actual = biStack.isTwoFull();

        // [Assert]
        assertThat(actual).isFalse();

    }

}