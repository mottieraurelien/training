package kata.structures;

import kata.data.Node;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LinkedListTest {

    @Test
    void should_add_the_item_at_the_first_position_when_adding_an_item_to_the_last_position_in_an_empty_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();

        // [Act]
        linkedList.addLast(5);

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(5);
        assertThat(actualLastNode.getValue()).isEqualTo(5);
        assertThat(actualFirstNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(1);

    }

    @Test
    void should_append_the_item_to_the_linked_list_when_adding_the_item_at_last_position() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        linkedList.addLast(30);

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(10);
        assertThat(actualSecondNode.getValue()).isEqualTo(20);
        assertThat(actualThirdNode.getValue()).isEqualTo(30);
        assertThat(actualThirdNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(3);

    }

    @Test
    void should_add_the_item_at_the_first_position_when_adding_an_item_to_the_first_position_in_an_empty_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();

        // [Act]
        linkedList.addFirst(5);

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(5);
        assertThat(actualLastNode.getValue()).isEqualTo(5);
        assertThat(actualFirstNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(1);

    }

    @Test
    void should_add_the_item_to_the_beginning_of_the_linked_list_when_adding_the_item_at_first_position() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        linkedList.addFirst(5);

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(5);
        assertThat(actualSecondNode.getValue()).isEqualTo(10);
        assertThat(actualThirdNode.getValue()).isEqualTo(20);
        assertThat(actualThirdNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(3);

    }

    @Test
    void should_throw_an_exception_when_trying_to_removing_the_first_item_from_an_empty_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();

        // [Act / Assert]
        assertThatThrownBy(linkedList::removeFirst)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Cannot remove the first item of an empty linked list.");

    }

    @Test
    void should_remove_the_item_at_the_first_position_when_removing_the_only_item_in_an_list_with_only_one_item() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(5);

        // [Act]
        linkedList.removeFirst();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode).isNull();
        assertThat(actualLastNode).isNull();
        assertThat(linkedList.isEmpty()).isTrue();

    }

    @Test
    void should_remove_the_item_at_the_first_position_when_removing_the_first_item_of_a_populated_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(5);
        linkedList.addLast(10);
        linkedList.addLast(15);
        linkedList.addLast(20);
        linkedList.addLast(30);

        // [Act]
        linkedList.removeFirst();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualFourthNode = actualThirdNode.getNext();
        final Node<Integer> actualFifthNode = actualFourthNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(10);
        assertThat(actualSecondNode.getValue()).isEqualTo(15);
        assertThat(actualThirdNode.getValue()).isEqualTo(20);
        assertThat(actualFourthNode.getValue()).isEqualTo(30);
        assertThat(actualFifthNode).isNull();
        assertThat(actualFourthNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(4);

    }

    @Test
    void should_throw_an_exception_when_trying_to_remove_the_last_item_from_an_empty_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();

        // [Act / Assert]
        assertThatThrownBy(linkedList::removeLast)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Cannot remove the last item of an empty linked list.");

    }

    @Test
    void should_remove_the_item_at_the_last_position_when_removing_the_only_item_in_an_list_with_only_one_item() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(5);

        // [Act]
        linkedList.removeLast();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode).isNull();
        assertThat(actualLastNode).isNull();
        assertThat(linkedList.isEmpty()).isTrue();

    }

    @Test
    void should_remove_the_item_at_the_last_position_when_removing_the_last_item_of_a_populated_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(30);
        linkedList.addFirst(20);
        linkedList.addFirst(15);
        linkedList.addFirst(10);
        linkedList.addFirst(5);

        // [Act]
        linkedList.removeLast();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualFourthNode = actualThirdNode.getNext();
        final Node<Integer> actualFifthNode = actualFourthNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();

        assertThat(actualFirstNode.getValue()).isEqualTo(5);
        assertThat(actualSecondNode.getValue()).isEqualTo(10);
        assertThat(actualThirdNode.getValue()).isEqualTo(15);
        assertThat(actualFourthNode.getValue()).isEqualTo(20);

        assertThat(actualFifthNode).isNull();
        assertThat(actualFourthNode).isEqualTo(actualLastNode);

        assertThat(linkedList.size()).isEqualTo(4);

    }

    @Test
    void should_return_false_when_the_list_does_not_contain_the_item() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        final boolean actual = linkedList.contains(7);

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_true_when_the_list_contains_the_item() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        final boolean actual = linkedList.contains(10);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_minus_one_when_finding_the_index_of_an_item_that_is_not_in_the_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        final int actual = linkedList.indexOf(7);

        // [Assert]
        assertThat(actual).isEqualTo(-1);

    }

    @Test
    void should_return_the_right_index_when_finding_the_index_of_an_item_that_is_in_the_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        final int actual = linkedList.indexOf(5);

        // [Assert]
        assertThat(actual).isEqualTo(0);

    }

    @Test
    void should_not_do_anything_when_reversing_an_empty_linked_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        Node<Integer> first = linkedList.getFirst();
        Node<Integer> last = linkedList.getLast();
        assertThat(first).isEqualTo(last).isNull();

        // [Act]
        linkedList.reverse();

        // [Assert]
        first = linkedList.getFirst();
        last = linkedList.getLast();
        assertThat(first).isEqualTo(last).isNull();

    }

    @Test
    void should_move_all_items_when_reversing_a_populated_linked_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);

        // [Act]
        linkedList.reverse();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualFourthNode = actualThirdNode.getNext();
        final Node<Integer> actualFifthNode = actualFourthNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();

        assertThat(actualFirstNode.getValue()).isEqualTo(40);
        assertThat(actualSecondNode.getValue()).isEqualTo(30);
        assertThat(actualThirdNode.getValue()).isEqualTo(20);
        assertThat(actualFourthNode.getValue()).isEqualTo(10);
        assertThat(actualFifthNode.getValue()).isEqualTo(5);

        assertThat(actualFifthNode.getNext()).isNull();
        assertThat(actualFifthNode).isEqualTo(actualLastNode);

        assertThat(linkedList.size()).isEqualTo(5);

    }

    @Test
    void should_return_fifty_when_finding_the_kth_item_one_from_the_end() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheEnd(1);

        // [Assert]
        assertThat(actualValue).isEqualTo(50);

    }

    @Test
    void should_return_twenty_when_finding_the_kth_item_four_from_the_end() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheEnd(4);

        // [Assert]
        assertThat(actualValue).isEqualTo(20);

    }

    @Test
    void should_return_null_when_finding_a_kth_item_from_the_end_that_is_too_high() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheEnd(10);

        // [Assert]
        assertThat(actualValue).isNull();

    }

    @Test
    void should_return_null_when_finding_a_kth_item_from_the_end_that_is_zero() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheEnd(0);

        // [Assert]
        assertThat(actualValue).isNull();

    }

    @Test
    void should_return_null_when_finding_a_kth_item_from_the_end_that_is_negative() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheEnd(-1);

        // [Assert]
        assertThat(actualValue).isNull();

    }

    @Test
    void should_return_ten_when_finding_the_kth_item_one_from_the_beginning() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheBeginning(1);

        // [Assert]
        assertThat(actualValue).isEqualTo(10);

    }

    @Test
    void should_return_thirty_when_finding_the_kth_item_two_from_the_beginning() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheBeginning(3);

        // [Assert]
        assertThat(actualValue).isEqualTo(30);

    }

    @Test
    void should_return_null_when_finding_a_kth_item_from_the_beginning_that_is_too_high() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheBeginning(10);

        // [Assert]
        assertThat(actualValue).isNull();

    }

    @Test
    void should_return_null_when_finding_a_kth_item_from_the_beginning_that_is_zero() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheBeginning(0);

        // [Assert]
        assertThat(actualValue).isNull();

    }

    @Test
    void should_return_null_when_finding_a_kth_item_from_the_beginning_that_is_negative() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Integer actualValue = linkedList.getKthFromTheBeginning(-1);

        // [Assert]
        assertThat(actualValue).isNull();

    }

    @Test
    void should_return_one_node_when_finding_the_middle_of_a_linked_list_with_an_odd_size() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);

        // [Act]
        final Node<Integer>[] actualMiddleNodes = linkedList.getMiddleNodes();

        // [Assert]
        assertThat(actualMiddleNodes).hasSize(1);
        final Node<Integer> uniqueMiddleNode = actualMiddleNodes[0];
        assertThat(uniqueMiddleNode).isNotNull();
        assertThat(uniqueMiddleNode.getValue()).isEqualTo(30);

    }

    @Test
    void should_return_two_nodes_when_finding_the_middle_of_a_linked_list_with_an_even_size() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addLast(50);
        linkedList.addLast(60);

        // [Act]
        final Node<Integer>[] actualMiddleNodes = linkedList.getMiddleNodes();

        // [Assert]
        assertThat(actualMiddleNodes).hasSize(2);
        final Node<Integer> firstMiddleNode = actualMiddleNodes[0];
        final Node<Integer> secondMiddleNode = actualMiddleNodes[1];
        assertThat(firstMiddleNode).isNotNull();
        assertThat(secondMiddleNode).isNotNull();
        assertThat(firstMiddleNode.getValue()).isEqualTo(30);
        assertThat(secondMiddleNode.getValue()).isEqualTo(40);

    }

    @Test
    void should_return_true_if_the_linked_list_has_a_loop() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        final Node<Integer> firstNode = new Node<>(10, null);
        final Node<Integer> secondNode = new Node<>(20, null);
        final Node<Integer> thirdNode = new Node<>(30, null);
        final Node<Integer> fourthNode = new Node<>(40, null);
        // Our loop on the last node :
        final Node<Integer> fifthNode = new Node<>(50, thirdNode);
        linkedList.addLast(firstNode);
        linkedList.addLast(secondNode);
        linkedList.addLast(thirdNode);
        linkedList.addLast(fourthNode);
        linkedList.addLast(fifthNode);

        // [Act]
        final boolean actual = linkedList.isLooping();

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_if_the_linked_list_has_an_end() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>();
        final Node<Integer> firstNode = new Node<>(10, null);
        final Node<Integer> secondNode = new Node<>(20, null);
        final Node<Integer> thirdNode = new Node<>(30, null);
        final Node<Integer> fourthNode = new Node<>(40, null);
        linkedList.addLast(firstNode);
        linkedList.addLast(secondNode);
        linkedList.addLast(thirdNode);
        linkedList.addLast(fourthNode);

        // [Act]
        final boolean actual = linkedList.isLooping();

        // [Assert]
        assertThat(actual).isFalse();

    }

}