package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CircularArrayQueueTest {

    @Test
    void should_throw_an_exception_when_enqueueing_a_null_item() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);

        // [Act / Assert]
        assertThatThrownBy(() -> queue.enqueue(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot enqueue a null item.");

    }

    @Test
    void should_throw_an_exception_when_enqueueing_in_a_full_queue() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(1);
        queue.enqueue(10);

        // [Act / Assert]
        assertThatThrownBy(() -> queue.enqueue(20))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot enqueue anymore, queue is full.");

    }

    @Test
    void should_add_the_item_to_the_queue_when_enqueueing_an_item() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);

        // [Act]
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // [Assert]
        final int actualSize = queue.size();
        assertThat(actualSize).isEqualTo(actualSize);

    }

    @Test
    void should_add_the_item_in_a_circular_way_when_the_queue_tail_if_full_and_the_head_has_been_clear() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        final Integer firstItem = queue.dequeue();
        final Integer secondItem = queue.dequeue();

        // [Act]
        queue.enqueue(40);
        final Integer thirdItem = queue.dequeue();
        final Integer fourthItem = queue.dequeue();
        final Integer fifthItem = queue.dequeue();

        // [Assert]
        assertThat(queue.isEmpty()).isTrue();
        assertThat(firstItem).isEqualTo(10);
        assertThat(secondItem).isEqualTo(20);
        assertThat(thirdItem).isEqualTo(30);
        assertThat(fourthItem).isEqualTo(40);
        assertThat(fifthItem).isNull();

    }

    @Test
    void should_throw_an_exception_when_the_queue_tail_if_full_even_after_rolling_over() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        final Integer firstItem = queue.dequeue();
        final Integer secondItem = queue.dequeue();
        queue.enqueue(40);
        queue.enqueue(50);

        // [Act / Assert]
        assertThatThrownBy(() -> queue.enqueue(60))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot enqueue anymore, queue is full.");
        assertThat(queue.isFull()).isTrue();
        assertThat(firstItem).isEqualTo(10);
        assertThat(secondItem).isEqualTo(20);

    }

    @Test
    void should_remove_the_first_added_item_when_dequeueing_an_item() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // [Act]
        final Integer firstItem = queue.dequeue();
        final Integer secondItem = queue.dequeue();
        final Integer thirdItem = queue.dequeue();
        final Integer fourthItem = queue.dequeue();

        // [Assert]
        assertThat(firstItem).isEqualTo(10);
        assertThat(secondItem).isEqualTo(20);
        assertThat(thirdItem).isEqualTo(30);
        assertThat(fourthItem).isNull();

    }

    @Test
    void should_return_null_when_dequeueing_an_item_from_an_empty_queue() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);

        // [Act]
        final Integer item = queue.dequeue();

        // [Assert]
        assertThat(item).isNull();

    }

    @Test
    void should_return_the_first_added_item_when_peeking_an_item() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // [Act]
        final Integer item = queue.peek();

        // [Assert]
        assertThat(item).isEqualTo(10);

    }

    @Test
    void should_return_null_when_peeking_an_item_from_an_empty_queue() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);

        // [Act]
        final Integer item = queue.peek();

        // [Assert]
        assertThat(item).isNull();

    }

    @Test
    void should_return_true_when_the_queue_is_empty() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);
        queue.enqueue(10);
        queue.enqueue(20);
        final Integer firstItem = queue.dequeue();
        final Integer secondItem = queue.dequeue();

        // [Act]
        final boolean actual = queue.isEmpty();

        // [Assert]
        assertThat(firstItem).isEqualTo(10);
        assertThat(secondItem).isEqualTo(20);
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_the_queue_is_not_empty() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10);
        queue.enqueue(10);
        queue.enqueue(20);
        final Integer firstItem = queue.dequeue();

        // [Act]
        final boolean actual = queue.isEmpty();

        // [Assert]
        assertThat(firstItem).isEqualTo(10);
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_true_when_the_queue_is_full() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(4);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);

        // [Act]
        final boolean actual = queue.isFull();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_the_queue_is_not_full() {

        // [Arrange]
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(4);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // [Act]
        final boolean actual = queue.isFull();

        // [Assert]
        assertThat(actual).isFalse();

    }

}