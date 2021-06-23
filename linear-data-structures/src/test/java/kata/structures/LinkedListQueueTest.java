package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LinkedListQueueTest {

    @Test
    void should_throw_an_exception_when_trying_to_add_a_null_item_to_the_queue() {

        // [Arrange]
        final LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // [Act / Assert]
        assertThatThrownBy(() -> queue.enqueue(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Item must not be null.");

    }

    @Test
    void should_consider_the_item_as_the_new_last_node_when_adding_a_new_item_to_the_queue() {

        // [Arrange]
        final LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // [Act]
        queue.enqueue(10);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(20);

        // [Assert]
        final Integer firstItem = queue.dequeue();
        assertThat(firstItem).isEqualTo(10);
        final Integer secondItem = queue.dequeue();
        assertThat(secondItem).isEqualTo(30);
        final Integer thirdItem = queue.dequeue();
        assertThat(thirdItem).isEqualTo(40);
        final Integer fourthItem = queue.dequeue();
        assertThat(fourthItem).isEqualTo(20);

    }

    @Test
    void should_throw_an_exception_when_trying_to_dequeue_from_an_empty_queue() {

        // [Arrange]
        final LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // [Act / Assert]
        assertThatThrownBy(queue::dequeue)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Queue is already empty.");

    }

    @Test
    void should_return_null_when_peeking_from_an_empty_list() {

        // [Arrange]
        final LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // [Act]
        final Integer actualItem = queue.peek();

        // [Assert]
        assertThat(actualItem).isNull();

    }

    @Test
    void should_return_ten_when_peeking_from_a_populated_queue() {

        // [Arrange]
        final LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(10);
        queue.enqueue(8);
        queue.enqueue(6);

        // [Act]
        final Integer actualItem = queue.peek();

        // [Assert]
        assertThat(actualItem).isEqualTo(10);

    }

}