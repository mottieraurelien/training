package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueueUsingTwoStacksTest {

    @Test
    void should_reverse_items_order_when_enqueuing_items_in_two_stacks_sequentially() {

        // [Arrange]
        final QueueUsingTwoStacks<Integer> queue = new QueueUsingTwoStacks<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        final Integer actualFirst = queue.dequeue();
        final Integer actualSecond = queue.dequeue();
        queue.enqueue(40);

        // [Act]
        final Integer actualThird = queue.dequeue();
        final Integer actualFourth = queue.dequeue();
        final Integer actualFifth = queue.dequeue();

        // [Assert]
        assertThat(actualFirst).isEqualTo(10);
        assertThat(actualSecond).isEqualTo(20);
        assertThat(actualThird).isEqualTo(30);
        assertThat(actualFourth).isEqualTo(40);
        assertThat(actualFifth).isNull();

    }

    @Test
    void should_return_thirty_when_peeking_from_the_queue() {

        // [Arrange]
        final QueueUsingTwoStacks<Integer> queue = new QueueUsingTwoStacks<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        final Integer actualFirst = queue.dequeue();
        final Integer actualSecond = queue.dequeue();
        queue.enqueue(40);

        // [Act]
        final Integer actual = queue.peek();

        // [Assert]
        assertThat(actualFirst).isEqualTo(10);
        assertThat(actualSecond).isEqualTo(20);
        assertThat(actual).isEqualTo(30);
        final Integer actualThird = queue.dequeue();
        assertThat(actualThird).isEqualTo(30);
        final Integer actualFourth = queue.dequeue();
        assertThat(actualFourth).isEqualTo(40);
        final Integer actualFifth = queue.dequeue();
        assertThat(actualFifth).isNull();

    }

}