package structure;

import data.TreeNode;

import static java.util.Objects.requireNonNull;

/**
 * As long as the type is comparable, then we can insert the items at the right place.
 *
 * @param <T> Not necessarily a number, but a type that implements the Comparable interface.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private final TreeNode<T> root;

    public BinarySearchTree(final TreeNode<T> root) {
        requireNonNull(root);
        this.root = root;
    }

    public void insert(final T item) {

        TreeNode<T> parentNode = this.root;
        final TreeNode<T> newChildNode = new TreeNode<>(item);

        while (parentNode != null) {

            final T parent = parentNode.getValue();
            final boolean parentEquals = parent.compareTo(item) == 0;
            final boolean parentIsLower = parent.compareTo(item) < 1;

            // If the item equals one of our nodes, then we don't need to insert the item again :
            if (parentEquals) return;

            if (parentIsLower) {

                if (parentNode.rightEmpty()) {
                    parentNode.right(newChildNode);
                    return;
                }
                parentNode = parentNode.getRight();

            } else {

                if (parentNode.leftEmpty()) {
                    parentNode.left(newChildNode);
                    return;
                }
                parentNode = parentNode.getLeft();

            }

        }

    }

    /**
     * Recursive research.
     */
    public boolean find(TreeNode<T> node, final T item) {

        if (node == null) return false;

        final T parent = node.getValue();

        final boolean parentEquals = parent.compareTo(item) == 0;
        final boolean parentIsLower = parent.compareTo(item) < 0;

        if (parentEquals)
            return true;

        else if (parentIsLower)
            // Parent's value is lower than the item, we need to switch on the RIGHT side and keep digging deeper.
            return this.find(node.getRight(), item);

        else
            // Parent's value is greater than the item, we need to switch on the LEFT side and keep digging deeper.
            return this.find(node.getLeft(), item);

    }

}