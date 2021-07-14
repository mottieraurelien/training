package structure;

import data.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

class TreeTest {

    @Test
    void should_insert_the_nodes_at_the_right_place_when_building_a_whole_binary_search_tree() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(10);
        final Tree<Integer> tree = new Tree<>(root);

        // [Act]
        tree.insert(5);
        tree.insert(15);
        tree.insert(6);
        tree.insert(1);
        tree.insert(8);
        tree.insert(12);
        tree.insert(18);
        tree.insert(17);

        // [Assert]
        assertThat(root.getValue()).isEqualTo(10);
        assertLeftBranch(root);
        assertRightBranch(root);

    }

    @Test
    void should_not_insert_an_item_when_it_already_exists_in_the_tree() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(10);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(5);
        tree.insert(15);
        tree.insert(6);
        tree.insert(1);
        tree.insert(8);
        tree.insert(12);
        tree.insert(18);
        tree.insert(17);

        // [Act]
        tree.insert(12);

        // [Assert]
        assertThat(root.getValue()).isEqualTo(10);
        assertLeftBranch(root);
        assertRightBranch(root);

    }

    @Test
    void should_not_insert_anything_when_the_root_is_null() {

        // [Arrange]
        final TreeNode<Integer> root = null;
        final Tree<Integer> tree = new Tree<>(root);

        // [Act]
        tree.insert(12);

        // [Assert]
        assertThat(root).isNull();

    }

    @Test
    void should_return_true_when_the_item_to_find_is_the_root() {

        // [Arrange]
        final Integer rootValue = 10;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);

        // [Act]
        final boolean actual = tree.find(root, rootValue);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_true_when_the_item_to_find_is_a_child() {

        // [Arrange]
        final Integer rootValue = 10;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(5);
        tree.insert(15);
        tree.insert(6);
        tree.insert(1);
        tree.insert(8);
        tree.insert(12);
        tree.insert(18);
        tree.insert(17);

        // [Act]
        final boolean actual = tree.find(root, 18);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_the_item_does_not_exist_in_the_tree() {

        // [Arrange]
        final Integer rootValue = 10;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(5);
        tree.insert(15);
        tree.insert(6);
        tree.insert(1);
        tree.insert(8);
        tree.insert(12);
        tree.insert(18);
        tree.insert(17);

        // [Act]
        final boolean actual = tree.find(root, 16);

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_traverse_the_nodes_at_the_same_level_before_moving_to_the_next_level_when_implementing_the_breadth_first_search() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final List<Integer> actual = new ArrayList<>();
        tree.traverseBreadthFirst(root, actual);

        // [Assert]
        assertThat(actual).containsExactly(20, 10, 30, 6, 14, 24, 3, 8, 26);

    }

    @Test
    void should_traverse_the_nodes_from_root_to_left_then_right_when_implementing_the_depth_first_search_using_pre_order_traversal() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final List<Integer> actual = new ArrayList<>();
        tree.traverseDepthFirstPreOrder(root, actual);

        // [Assert]
        assertThat(actual).containsExactly(20, 10, 6, 3, 8, 14, 30, 24, 26);

    }

    @Test
    void should_traverse_the_nodes_from_left_to_root_then_right_when_implementing_the_depth_first_search_using_in_order_traversal() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final List<Integer> actual = new ArrayList<>();
        tree.traverseDepthFirstInOrder(root, actual);

        // [Assert]
        assertThat(actual).containsExactly(3, 6, 8, 10, 14, 20, 24, 26, 30);

    }

    @Test
    void should_traverse_the_nodes_from_left_to_right_then_root_when_implementing_the_depth_first_search_using_post_order_traversal() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final List<Integer> actual = new ArrayList<>();
        tree.traverseDepthFirstPostOrder(root, actual);

        // [Assert]
        assertThat(actual).containsExactly(3, 8, 6, 14, 10, 26, 24, 30, 20);

    }

    @Test
    void should_return_three_when_computing_the_height_of_the_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final int actual = tree.getHeightFrom(root);

        // [Assert]
        assertThat(actual).isEqualTo(3);

    }

    @Test
    void should_return_two_when_computing_the_height_of_a_subtree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        final TreeNode<Integer> node = new TreeNode<>(10);
        tree.insert(node);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final int actual = tree.getHeightFrom(node);

        // [Assert]
        assertThat(actual).isEqualTo(2);

    }

    @Test
    void should_return_negative_one_when_the_tree_is_empty() {

        // [Arrange]
        final TreeNode<Integer> root = null;
        final Tree<Integer> tree = new Tree<>(root);

        // [Act]
        final int actual = tree.getHeightFrom(root);

        // [Assert]
        assertThat(actual).isEqualTo(-1);

    }

    @Test
    void should_return_four_when_finding_the_minimum_of_the_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(4);
        tree.insert(3);
        tree.insert(8);

        // [Act]
        final Integer actual = tree.getMinimumFrom(root);

        // [Assert]
        assertThat(actual).isEqualTo(3);

    }

    @Test
    void should_return_twenty_two_when_computing_the_height_of_a_subtree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        final TreeNode<Integer> node = new TreeNode<>(30);
        tree.insert(node);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);
        tree.insert(22);

        // [Act]
        final Integer actual = tree.getMinimumFrom(node);

        // [Assert]
        assertThat(actual).isEqualTo(22);

    }

    @Test
    void should_return_null_when_the_tree_is_empty() {

        // [Arrange]
        final TreeNode<Integer> root = null;
        final Tree<Integer> tree = new Tree<>(root);

        // [Act]
        final Integer actual = tree.getMinimumFrom(root);

        // [Assert]
        assertThat(actual).isNull();

    }

    @Test
    void should_return_true_when_the_trees_are_identical() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(20);
        final Tree<Integer> tree = build(root);
        final TreeNode<Integer> anotherRoot = new TreeNode<>(20);
        final Tree<Integer> anotherTree = build(anotherRoot);

        // [Act]
        final boolean actual = tree.equals(anotherTree);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_their_roots_are_different() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(20);
        final Tree<Integer> tree = build(root);
        final TreeNode<Integer> anotherRoot = new TreeNode<>(21);
        final Tree<Integer> anotherTree = build(anotherRoot);

        // [Act]
        final boolean actual = tree.equals(anotherTree);

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_false_when_their_nodes_are_different() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(20);
        final Tree<Integer> tree = build(root);
        final TreeNode<Integer> anotherRoot = new TreeNode<>(20);
        final Tree<Integer> anotherTree = buildAnother(anotherRoot);

        // [Act]
        final boolean actual = tree.equals(anotherTree);

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_true_when_the_tree_is_a_binary_search_tree() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(20);
        final Tree<Integer> tree = build(root);

        // [Act]
        final boolean actual = tree.isBinarySearchTree(root, MIN_VALUE, MAX_VALUE);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_the_tree_is_not_a_binary_search_tree() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(20);
        final Tree<Integer> tree = build(root);
        // To check this scenario, let's swap just the root children for instance :
        final TreeNode<Integer> oldLeftChild = root.getLeftChild();
        root.setLeftChild(root.getRightChild());
        root.setRightChild(oldLeftChild);

        // [Act]
        final boolean actual = tree.isBinarySearchTree(root, MIN_VALUE, MAX_VALUE);

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_three_height_and_twenty_six_when_getting_the_nodes_at_distance_three_from_the_root() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(20);
        final Tree<Integer> tree = build(root);
        final List<Integer> actual = new ArrayList<>();
        final int distance = 3;

        // [Act]
        tree.findNodesAtKthDistance(root, distance, actual);

        // [Assert]
        assertThat(actual).hasSize(3);
        assertThat(actual.get(0)).isEqualTo(3);
        assertThat(actual.get(1)).isEqualTo(8);
        assertThat(actual.get(2)).isEqualTo(26);

    }

    @Test
    void should_return_twenty_four_when_getting_the_nodes_at_distance_one_from_the_parent_node_thirty() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        final TreeNode<Integer> node = new TreeNode<>(30);
        tree.insert(node);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);
        tree.insert(22);
        final List<Integer> actual = new ArrayList<>();
        final int distance = 1;

        // [Act]
        tree.findNodesAtKthDistance(node, distance, actual);

        // [Assert]
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0)).isEqualTo(24);

    }

    @Test
    void should_return_nine_when_getting_the_size_of_the_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = build(root);

        // [Act]
        final int actual = tree.size(root);

        // [Assert]
        assertThat(actual).isEqualTo(9);

    }

    @Test
    void should_return_three_when_getting_the_size_of_a_subtree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        final TreeNode<Integer> node = new TreeNode<>(30);
        tree.insert(node);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final int actual = tree.size(node);

        // [Assert]
        assertThat(actual).isEqualTo(3);

    }

    @Test
    void should_return_four_when_counting_the_number_of_leaves_in_the_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final int actual = tree.countLeaves(root);

        // [Assert]
        assertThat(actual).isEqualTo(4);

    }

    @Test
    void should_return_three_when_counting_the_number_of_leaves_in_the_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        final TreeNode<Integer> node = new TreeNode<>(10);
        tree.insert(node);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final int actual = tree.countLeaves(node);

        // [Assert]
        assertThat(actual).isEqualTo(3);

    }

    @Test
    void should_return_thirty_when_finding_the_max_in_the_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final int actual = tree.getMaximumFrom(root);

        // [Assert]
        assertThat(actual).isEqualTo(30);

    }

    @Test
    void should_return_fourteen_when_finding_the_max_in_a_subtree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        final TreeNode<Integer> node = new TreeNode<>(10);
        tree.insert(node);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final int actual = tree.getMaximumFrom(node);

        // [Assert]
        assertThat(actual).isEqualTo(14);

    }

    @Test
    void should_return_true_since_the_tree_contains_the_root() {

        // [Arrange]
        final Integer rootValue = 10;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);

        // [Act]
        final boolean actual = tree.contains(root, rootValue);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_true_when_the_tree_contains_the_targeted_child() {

        // [Arrange]
        final Integer rootValue = 10;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(5);
        tree.insert(15);
        tree.insert(6);
        tree.insert(1);
        tree.insert(8);
        tree.insert(12);
        tree.insert(18);
        tree.insert(17);

        // [Act]
        final boolean actual = tree.contains(root, 18);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_true_when_the_tree_does_not_contain_the_targeted_child() {

        // [Arrange]
        final Integer rootValue = 10;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(5);
        tree.insert(15);
        tree.insert(6);
        tree.insert(1);
        tree.insert(8);
        tree.insert(12);
        tree.insert(18);
        tree.insert(17);

        // [Act]
        final boolean actual = tree.contains(root, 16);

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_true_when_two_values_are_siblings_in_a_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final boolean actual = tree.areSiblings(root, 6, 14);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_two_values_are_not_siblings_in_a_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        final boolean actual = tree.areSiblings(root, 3, 14);

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_all_parents_to_root_when_finding_the_ancestors_of_a_leaf() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);
        final List<Integer> ancestors = new ArrayList<>();

        // [Act]
        tree.getAncestors(root, 8, ancestors);

        // [Assert]
        assertThat(ancestors).containsExactlyInAnyOrder(6, 10, 20);

    }

    @Test
    void should_return_the_root_when_finding_the_ancestor_of_a_root_child() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);
        final List<Integer> ancestors = new ArrayList<>();

        // [Act]
        tree.getAncestors(root, 10, ancestors);

        // [Assert]
        assertThat(ancestors).containsExactly(20);

    }

    @Test
    void should_return_an_empty_list_when_trying_to_find_the_ancestor_of_a_child_that_does_not_exist_in_the_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);
        final List<Integer> ancestors = new ArrayList<>();

        // [Act]
        tree.getAncestors(root, 2, ancestors);

        // [Assert]
        assertThat(ancestors).isEmpty();

    }

    @Test
    void should_swap_left_children_with_right_children_when_reversing_a_whole_binary_tree() {

        // [Arrange]
        final Integer rootValue = 20;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);

        // [Act]
        tree.reverse(root);

        // [Assert]
        assertThat(root.getValue()).isEqualTo(20);
        assertLeftReversedBranch(root);
        assertRightReversedBranch(root);

    }

    private Tree<Integer> build(final TreeNode<Integer> root) {
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);
        return tree;
    }

    private Tree<Integer> buildAnother(final TreeNode<Integer> root) {
        final Tree<Integer> tree = new Tree<>(root);
        tree.insert(10);
        tree.insert(30);
        tree.insert(6);
        tree.insert(14);
        tree.insert(24);
        tree.insert(3);
        tree.insert(8);
        tree.insert(26);
        tree.insert(22);
        return tree;
    }

    /**
     * Asserts focused on the left branch only of our BST.
     */
    private void assertLeftBranch(final TreeNode<Integer> root) {

        final TreeNode<Integer> childFive = root.getLeftChild();
        assertThat(childFive).isNotNull();
        assertThat(childFive.getValue()).isEqualTo(5);

        final TreeNode<Integer> childOne = childFive.getLeftChild();
        assertThat(childOne).isNotNull();
        assertThat(childOne.getValue()).isEqualTo(1);
        final TreeNode<Integer> childOneLeftNull = childOne.getLeftChild();
        assertThat(childOneLeftNull).isNull();
        final TreeNode<Integer> childOneRightNull = childOne.getRightChild();
        assertThat(childOneRightNull).isNull();

        final TreeNode<Integer> childSix = childFive.getRightChild();
        assertThat(childSix).isNotNull();
        assertThat(childSix.getValue()).isEqualTo(6);
        final TreeNode<Integer> childSixLeftNull = childSix.getLeftChild();
        assertThat(childSixLeftNull).isNull();

        final TreeNode<Integer> childEight = childSix.getRightChild();
        assertThat(childEight).isNotNull();
        assertThat(childEight.getValue()).isEqualTo(8);
        final TreeNode<Integer> childEightLeftNull = childEight.getLeftChild();
        assertThat(childEightLeftNull).isNull();
        final TreeNode<Integer> childEightRightNull = childEight.getRightChild();
        assertThat(childEightRightNull).isNull();

    }

    /**
     * Asserts focused on the right branch only of our BST.
     */
    private void assertRightBranch(final TreeNode<Integer> root) {

        final TreeNode<Integer> childFifteen = root.getRightChild();
        assertThat(childFifteen).isNotNull();
        assertThat(childFifteen.getValue()).isEqualTo(15);

        final TreeNode<Integer> childTwelve = childFifteen.getLeftChild();
        assertThat(childTwelve).isNotNull();
        assertThat(childTwelve.getValue()).isEqualTo(12);
        final TreeNode<Integer> childTwelveLeftNull = childTwelve.getLeftChild();
        assertThat(childTwelveLeftNull).isNull();
        final TreeNode<Integer> childTwelveRightNull = childTwelve.getRightChild();
        assertThat(childTwelveRightNull).isNull();

        final TreeNode<Integer> childEighteen = childFifteen.getRightChild();
        assertThat(childEighteen).isNotNull();
        assertThat(childEighteen.getValue()).isEqualTo(18);
        final TreeNode<Integer> childEighteenRightNull = childEighteen.getRightChild();
        assertThat(childEighteenRightNull).isNull();

        final TreeNode<Integer> childSeventeen = childEighteen.getLeftChild();
        assertThat(childSeventeen).isNotNull();
        assertThat(childSeventeen.getValue()).isEqualTo(17);
        final TreeNode<Integer> childSeventeenLeftNull = childSeventeen.getLeftChild();
        assertThat(childSeventeenLeftNull).isNull();
        final TreeNode<Integer> childSeventeenRightNull = childSeventeen.getRightChild();
        assertThat(childSeventeenRightNull).isNull();

    }

    /**
     * Asserts focused on the left reversed branch only of our BST.
     */
    private void assertLeftReversedBranch(final TreeNode<Integer> root) {

        final TreeNode<Integer> childThirty = root.getLeftChild();
        assertThat(childThirty).isNotNull();
        assertThat(childThirty.getValue()).isEqualTo(30);

        final TreeNode<Integer> leftChildThirty = childThirty.getLeftChild();
        assertThat(leftChildThirty).isNull();

        final TreeNode<Integer> childTwentyFour = childThirty.getRightChild();
        assertThat(childTwentyFour).isNotNull();
        assertThat(childTwentyFour.getValue()).isEqualTo(24);

        final TreeNode<Integer> childTwentySix = childTwentyFour.getLeftChild();
        assertThat(childTwentySix).isNotNull();
        assertThat(childTwentySix.getValue()).isEqualTo(26);

        final TreeNode<Integer> rightChildTwentyFour = childTwentyFour.getRightChild();
        assertThat(rightChildTwentyFour).isNull();

    }

    /**
     * Asserts focused on the right reversed branch only of our BST.
     */
    private void assertRightReversedBranch(final TreeNode<Integer> root) {

        final TreeNode<Integer> childTen = root.getRightChild();
        assertThat(childTen).isNotNull();
        assertThat(childTen.getValue()).isEqualTo(10);

        final TreeNode<Integer> childFourteen = childTen.getLeftChild();
        assertThat(childFourteen).isNotNull();
        assertThat(childFourteen.getValue()).isEqualTo(14);

        final TreeNode<Integer> leftChildFourteen = childFourteen.getLeftChild();
        assertThat(leftChildFourteen).isNull();

        final TreeNode<Integer> rightChildFourteen = childFourteen.getRightChild();
        assertThat(rightChildFourteen).isNull();

        final TreeNode<Integer> childSix = childTen.getRightChild();
        assertThat(childSix).isNotNull();
        assertThat(childSix.getValue()).isEqualTo(6);

        final TreeNode<Integer> childEight = childSix.getLeftChild();
        assertThat(childEight).isNotNull();
        assertThat(childEight.getValue()).isEqualTo(8);

        final TreeNode<Integer> childThree = childSix.getRightChild();
        assertThat(childThree).isNotNull();
        assertThat(childThree.getValue()).isEqualTo(3);

    }

}