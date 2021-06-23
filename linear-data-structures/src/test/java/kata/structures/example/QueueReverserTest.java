package kata.structures.example;

import kata.structures.CircularArrayQueue;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class QueueReverserTest {

    private static final Comparator<Integer> INTEGER_COMPARATOR = Integer::compareTo;

    @Test
    void should_reverse_a_queue_when_dequeueing_all_items_one_by_one() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        final QueueReverser<Integer> reverser = new QueueReverser<>(Integer.class, queue);

        // [Act]
        final Integer[] actual = reverser.reverse();

        // [Assert]
        assertThat(actual).hasSize(3);
        assertThat(actual[0]).isEqualTo(30);
        assertThat(actual[1]).isEqualTo(20);
        assertThat(actual[2]).isEqualTo(10);

    }

    @Test
    void should_reverse_a_part_of_a_queue_when_specifying_an_index() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);
        final QueueReverser<Integer> reverser = new QueueReverser<>(Integer.class, queue);

        // [Act]
        final Integer[] actual = reverser.reverse(3, INTEGER_COMPARATOR);

        // [Assert]
        assertThat(actual).hasSize(5);
        assertThat(actual[0]).isEqualTo(30);
        assertThat(actual[1]).isEqualTo(20);
        assertThat(actual[2]).isEqualTo(10);
        assertThat(actual[3]).isEqualTo(40);
        assertThat(actual[4]).isEqualTo(50);

    }

}