package structure;

import data.TreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTreeTest {

    @Test
    void should_insert_the_nodes_at_the_right_place_when_building_a_whole_binary_search_tree() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(10);
        final BinarySearchTree<Integer> bst = new BinarySearchTree<>(root);

        // [Act]
        bst.insert(5);
        bst.insert(15);
        bst.insert(6);
        bst.insert(1);
        bst.insert(8);
        bst.insert(12);
        bst.insert(18);
        bst.insert(17);

        // [Assert]
        assertThat(root.getValue()).isEqualTo(10);
        assertLeftBranch(root);
        assertRightBranch(root);

    }

    @Test
    void should_not_insert_an_item_when_it_already_exists_in_the_tree() {

        // [Arrange]
        final TreeNode<Integer> root = new TreeNode<>(10);
        final BinarySearchTree<Integer> bst = new BinarySearchTree<>(root);
        bst.insert(5);
        bst.insert(15);
        bst.insert(6);
        bst.insert(1);
        bst.insert(8);
        bst.insert(12);
        bst.insert(18);
        bst.insert(17);

        // [Act]
        bst.insert(12);

        // [Assert]
        assertThat(root.getValue()).isEqualTo(10);
        assertLeftBranch(root);
        assertRightBranch(root);

    }

    @Test
    void should_return_true_when_the_item_to_find_is_the_root() {

        // [Arrange]
        final Integer rootValue = 10;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final BinarySearchTree<Integer> bst = new BinarySearchTree<>(root);

        // [Act]
        final boolean actual = bst.find(root, rootValue);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_true_when_the_item_to_find_is_a_child() {

        // [Arrange]
        final Integer rootValue = 10;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final BinarySearchTree<Integer> bst = new BinarySearchTree<>(root);
        bst.insert(5);
        bst.insert(15);
        bst.insert(6);
        bst.insert(1);
        bst.insert(8);
        bst.insert(12);
        bst.insert(18);
        bst.insert(17);

        // [Act]
        final boolean actual = bst.find(root, 18);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_false_when_the_item_does_not_exist_in_the_tree() {

        // [Arrange]
        final Integer rootValue = 10;
        final TreeNode<Integer> root = new TreeNode<>(rootValue);
        final BinarySearchTree<Integer> bst = new BinarySearchTree<>(root);
        bst.insert(5);
        bst.insert(15);
        bst.insert(6);
        bst.insert(1);
        bst.insert(8);
        bst.insert(12);
        bst.insert(18);
        bst.insert(17);

        // [Act]
        final boolean actual = bst.find(root, 16);

        // [Assert]
        assertThat(actual).isFalse();

    }

    /**
     * Asserts focused on the left branch only of our BST.
     */
    private void assertLeftBranch(final TreeNode<Integer> root) {

        final TreeNode<Integer> childFive = root.getLeft();
        assertThat(childFive.getValue()).isEqualTo(5);

        final TreeNode<Integer> childOne = childFive.getLeft();
        assertThat(childOne.getValue()).isEqualTo(1);
        final TreeNode<Integer> childOneLeftNull = childOne.getLeft();
        assertThat(childOneLeftNull).isNull();
        final TreeNode<Integer> childOneRightNull = childOne.getRight();
        assertThat(childOneRightNull).isNull();

        final TreeNode<Integer> childSix = childFive.getRight();
        assertThat(childSix.getValue()).isEqualTo(6);
        final TreeNode<Integer> childSixLeftNull = childSix.getLeft();
        assertThat(childSixLeftNull).isNull();

        final TreeNode<Integer> childEight = childSix.getRight();
        assertThat(childEight.getValue()).isEqualTo(8);
        final TreeNode<Integer> childEightLeftNull = childEight.getLeft();
        assertThat(childEightLeftNull).isNull();
        final TreeNode<Integer> childEightRightNull = childEight.getRight();
        assertThat(childEightRightNull).isNull();

    }

    /**
     * Asserts focused on the right branch only of our BST.
     */
    private void assertRightBranch(final TreeNode<Integer> root) {

        final TreeNode<Integer> childFifteen = root.getRight();
        assertThat(childFifteen.getValue()).isEqualTo(15);

        final TreeNode<Integer> childTwelve = childFifteen.getLeft();
        assertThat(childTwelve.getValue()).isEqualTo(12);
        final TreeNode<Integer> childTwelveLeftNull = childTwelve.getLeft();
        assertThat(childTwelveLeftNull).isNull();
        final TreeNode<Integer> childTwelveRightNull = childTwelve.getRight();
        assertThat(childTwelveRightNull).isNull();

        final TreeNode<Integer> childEighteen = childFifteen.getRight();
        assertThat(childEighteen.getValue()).isEqualTo(18);
        final TreeNode<Integer> childEighteenRightNull = childEighteen.getRight();
        assertThat(childEighteenRightNull).isNull();

        final TreeNode<Integer> childSeventeen = childEighteen.getLeft();
        assertThat(childSeventeen.getValue()).isEqualTo(17);
        final TreeNode<Integer> childSeventeenLeftNull = childSeventeen.getLeft();
        assertThat(childSeventeenLeftNull).isNull();
        final TreeNode<Integer> childSeventeenRightNull = childSeventeen.getRight();
        assertThat(childSeventeenRightNull).isNull();

    }

}