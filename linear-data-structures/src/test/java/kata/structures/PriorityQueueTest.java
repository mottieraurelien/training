package kata.structures;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PriorityQueueTest {

    private static final Comparator<Integer> INTEGER_COMPARATOR = Integer::compareTo;

    @Test
    void should_simply_add_the_item_at_the_beginning_when_adding_a_new_items_to_an_empty_priority_queue() {

        // [Arrange]
        final PriorityQueue<Integer> queue = new PriorityQueue<>(10, INTEGER_COMPARATOR);

        // [Act]
        queue.add(2);

        // [Assert]
        final Integer actual = queue.peek();
        assertThat(actual).isEqualTo(2);

    }

    @Test
    void should_simply_add_the_item_after_the_existing_one_when_the_new_item_has_a_higher_priority() {

        // [Arrange]
        final PriorityQueue<Integer> queue = new PriorityQueue<>(10, INTEGER_COMPARATOR);
        queue.add(2);

        // [Act]
        queue.add(3);

        // [Assert]
        final Integer actual = queue.peek();
        assertThat(actual).isEqualTo(3);

    }

    @Test
    void should_simply_add_the_item_before_the_existing_one_when_the_new_item_has_a_lower_priority() {

        // [Arrange]
        final PriorityQueue<Integer> queue = new PriorityQueue<>(10, INTEGER_COMPARATOR);
        queue.add(2);

        // [Act]
        queue.add(1);

        // [Assert]
        final Integer actual = queue.peek();
        assertThat(actual).isEqualTo(2);

    }

    @Test
    void should_keep_the_items_sorted_when_adding_new_items_to_the_priority_queue() {

        // [Arrange]
        final PriorityQueue<Integer> queue = new PriorityQueue<>(10, INTEGER_COMPARATOR);
        queue.add(9);
        queue.add(5);
        queue.add(3);
        queue.add(7);
        final Integer itemWithTheHighestPrioritySoFar = queue.peek();
        assertThat(itemWithTheHighestPrioritySoFar).isEqualTo(9);

        // [Act]
        queue.add(11);

        // [Assert]
        final Integer newItemWithTheHighestPriority = queue.peek();
        assertThat(newItemWithTheHighestPriority).isEqualTo(11);

    }

    @Test
    void should_throw_an_exception_when_adding_a_new_item_in_a_queue_that_is_already_full() {

        // [Arrange]
        final PriorityQueue<Integer> queue = new PriorityQueue<>(4, INTEGER_COMPARATOR);
        queue.add(9);
        queue.add(5);
        queue.add(3);
        queue.add(7);

        // [Act / Assert]
        assertThatThrownBy(() -> queue.add(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("The queue is already full.");

    }

    @Test
    void should_return_null_when_removing_the_item_with_the_highest_priority_from_an_empty_queue() {

        // [Arrange]
        final PriorityQueue<Integer> queue = new PriorityQueue<>(10, INTEGER_COMPARATOR);

        // [Act]
        final Integer actualItem = queue.remove();

        // [Assert]
        assertThat(actualItem).isNull();

    }

    @Test
    void should_return_three_when_removing_the_item_with_the_highest_priority_from_a_populated_queue() {

        // [Arrange]
        final PriorityQueue<Integer> queue = new PriorityQueue<>(10, INTEGER_COMPARATOR);
        queue.add(9);
        queue.add(3);
        queue.add(5);

        // [Act]
        final Integer actualItemWithTheHighestPriority = queue.remove();

        // [Assert]
        assertThat(actualItemWithTheHighestPriority).isEqualTo(9);

    }

}