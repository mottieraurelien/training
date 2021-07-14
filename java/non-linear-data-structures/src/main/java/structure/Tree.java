package structure;

import data.TreeNode;

import java.util.*;

import static java.lang.Math.max;
import static java.util.Collections.min;

/**
 * As long as the type is comparable, then we can insert the items at the right place.
 *
 * @param <T> Not necessarily a number, but a type that implements the Comparable interface.
 */
public class Tree<T extends Comparable<T>> {

    private final TreeNode<T> root;

    public Tree(final TreeNode<T> root) {
        this.root = root;
    }

    public void insert(final T value) {
        final TreeNode<T> newChildNode = new TreeNode<>(value);
        this.insert(newChildNode);
    }

    public void insert(final TreeNode<T> node) {

        TreeNode<T> parentNode = this.root;
        final T value = node.getValue();

        while (parentNode != null) {

            final T parent = parentNode.getValue();
            final boolean parentEquals = parent.compareTo(value) == 0;
            final boolean parentIsLower = parent.compareTo(value) < 1;

            // If the value equals one of our nodes, then we don't need to insert the value again :
            if (parentEquals) return;

            if (parentIsLower) {

                if (parentNode.rightChildIsNull()) {
                    parentNode.setRightChild(node);
                    return;
                }
                parentNode = parentNode.getRightChild();

            } else {

                if (parentNode.leftChildIsNull()) {
                    parentNode.setLeftChild(node);
                    return;
                }
                parentNode = parentNode.getLeftChild();

            }

        }

    }

    /**
     * Recursive research.
     */
    public boolean find(TreeNode<T> node, final T value) {

        if (node == null) return false;

        final T parent = node.getValue();

        final boolean parentEquals = parent.compareTo(value) == 0;
        final boolean parentIsLower = parent.compareTo(value) < 0;

        if (parentEquals)
            return true;

        else if (parentIsLower)
            // Parent's value is lower than the item, we need to switch on the RIGHT side and keep digging deeper.
            return this.find(node.getRightChild(), value);

        // Parent's value is greater than the item, we need to switch on the LEFT side and keep digging deeper.
        return this.find(node.getLeftChild(), value);

    }

    public void traverseBreadthFirst(TreeNode<T> root, final List<T> values) {

        final Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            final TreeNode<T> node = queue.remove();
            values.add(node.getValue());

            if (node.hasLeftChild())
                queue.add(node.getLeftChild());

            if (node.hasRightChild())
                queue.add(node.getRightChild());

        }

    }

    /**
     * Reminder : PRE-ORDER means root > left > right.
     */
    public void traverseDepthFirstPreOrder(TreeNode<T> node, final List<T> values) {

        if (node == null) return;

        // Step 1 : first, we can add the node value (sub-tree root) :
        final T value = node.getValue();
        values.add(value);

        // Step 2 : then, we dig as much as we can on the left sub-tree :
        this.traverseDepthFirstPreOrder(node.getLeftChild(), values);

        // Step 3 : finally, we dig as much as we can on the right sub-tree :
        this.traverseDepthFirstPreOrder(node.getRightChild(), values);

    }

    /**
     * Reminder : IN-ORDER means left > root > right.
     */
    public void traverseDepthFirstInOrder(final TreeNode<T> node, final List<T> values) {

        if (node == null) return;

        // Step 1 : first, we dig as much as we can on the left sub-tree :
        this.traverseDepthFirstInOrder(node.getLeftChild(), values);

        // Step 2 : then, we can add the node value (sub-tree root) :
        final T value = node.getValue();
        values.add(value);

        // Step 3 : finally, we dig as much as we can on the right sub-tree :
        this.traverseDepthFirstInOrder(node.getRightChild(), values);

    }

    /**
     * Reminder : POST-ORDER means left > right > root.
     */
    public void traverseDepthFirstPostOrder(TreeNode<T> node, final List<T> values) {

        if (node == null) return;

        // Step 1 : first, we dig as much as we can on the left sub-tree :
        this.traverseDepthFirstPostOrder(node.getLeftChild(), values);

        // Step 2 : then, we dig as much as we can on the right sub-tree :
        this.traverseDepthFirstPostOrder(node.getRightChild(), values);

        // Step 3 : finally, we can add the node value (sub-tree root) :
        final T value = node.getValue();
        values.add(value);

    }

    /**
     * Computes the height of a tree based on the DFS
     * post-order traversal.
     */
    public int getHeightFrom(final TreeNode<T> subtreeParent) {

        if (subtreeParent == null) return -1;
        if (subtreeParent.leftChildIsNull() || subtreeParent.rightChildIsNull()) return 0;

        // Step 1 : first, we dig as much as we can on the left sub-tree :
        final int leftHeight = this.getHeightFrom(subtreeParent.getLeftChild());

        // Step 2 : then, we dig as much as we can on the right sub-tree :
        final int rightHeight = this.getHeightFrom(subtreeParent.getRightChild());

        // Step 3 : finally, we can add the node value (sub-tree root) :
        return 1 + max(leftHeight, rightHeight);

    }

    /**
     * Finds the minimum of a tree based on the DFS
     * post-order traversal.
     */
    public T getMinimumFrom(final TreeNode<T> node) {

        if (node == null) return null;
        if (node.isLeaf()) return node.getValue();

        // Step 1 : first, we dig as much as we can on the left sub-tree :
        final T leftMinimum = this.getMinimumFrom(node.getLeftChild());

        // Step 2 : then, we dig as much as we can on the right sub-tree :
        final T rightMinimum = this.getMinimumFrom(node.getRightChild());

        // Step 3 : finally, we compare the values and retain the min one :
        final List<T> values = new ArrayList<>();
        values.add(node.getValue());
        if (leftMinimum != null) values.add(leftMinimum);
        if (rightMinimum != null) values.add(rightMinimum);
        return min(values);

    }

    public T getMaximumFrom(final TreeNode<T> node) {

        if (node == null) return null;
        if (node.isLeaf()) return node.getValue();

        // Step 1 : first, we dig as much as we can on the left sub-tree :
        final T leftMaximum = this.getMaximumFrom(node.getLeftChild());

        // Step 2 : then, we dig as much as we can on the right sub-tree :
        final T rightMaximum = this.getMaximumFrom(node.getRightChild());

        // Step 3 : finally, we compare the values and retain the min one :
        final List<T> values = new ArrayList<>();
        values.add(node.getValue());
        if (leftMaximum != null) values.add(leftMaximum);
        if (rightMaximum != null) values.add(rightMaximum);
        return Collections.max(values);

    }

    public boolean isBinarySearchTree(final TreeNode<T> node, final T minValue, final T maxValue) {

        if (node == null) return true;

        // Step 1 : first, we check the node value :
        final T value = node.getValue();
        final boolean valueIsHigherThanMinimum = minValue.compareTo(value) < 0;
        final boolean valueIsLowerThanMaximum = value.compareTo(maxValue) < 0;
        if (!valueIsHigherThanMinimum || !valueIsLowerThanMaximum) return false;

        // Step 2 : second, we dig as much as we can on the left sub-tree :
        final boolean leftNodeValid = this.isBinarySearchTree(node.getLeftChild(), minValue, value);

        // Step 3 : third,  we dig as much as we can on the right sub-tree :
        final boolean rightNodeValid = this.isBinarySearchTree(node.getRightChild(), value, maxValue);

        // Step 4 : finally, we check the sub-trees :
        return leftNodeValid && rightNodeValid;

    }

    public void findNodesAtKthDistance(final TreeNode<T> node, int distance, final List<T> nodes) {

        if (node == null) return;

        if (distance == 0) {
            nodes.add(node.getValue());
            return;
        }

        distance--;
        this.findNodesAtKthDistance(node.getLeftChild(), distance, nodes);
        this.findNodesAtKthDistance(node.getRightChild(), distance, nodes);

    }

    public int size(final TreeNode<T> node) {

        if (node == null) return 0;

        final int leftSize = this.size(node.getLeftChild());
        final int rightSize = this.size(node.getRightChild());

        return 1 + leftSize + rightSize;

    }

    public int countLeaves(final TreeNode<T> node) {

        if (node == null) return 0;
        if (node.isLeaf()) return 1;

        final int leftSize = this.countLeaves(node.getLeftChild());
        final int rightSize = this.countLeaves(node.getRightChild());

        return leftSize + rightSize;

    }

    public boolean contains(final TreeNode<T> node, final T value) {

        if (node == null) return false;

        final boolean same = value.compareTo(node.getValue()) == 0;
        if (same) return true;

        return contains(node.getLeftChild(), value) || contains(node.getRightChild(), value);

    }

    public boolean areSiblings(final TreeNode<T> node, final T firstValue, final T secondValue) {

        if (node == null) return false;

        // Two scenarios :
        //  - first value equals to the left child and the second value equals to the right child.
        //  - opposite.
        // It could have been simplified if we sort firstValue and secondValue so that we only need to check the first scenario.
        final boolean result = node.hasLeftChild() && node.hasRightChild()
                && (
                (firstValue.compareTo(node.getLeftChild().getValue()) == 0 && secondValue.compareTo(node.getRightChild().getValue()) == 0)
                        || (secondValue.compareTo(node.getLeftChild().getValue()) == 0 && firstValue.compareTo(node.getRightChild().getValue()) == 0)
        );

        return result
                || areSiblings(node.getLeftChild(), firstValue, secondValue)
                || areSiblings(node.getRightChild(), firstValue, secondValue);

    }

    public void getAncestors(final TreeNode<T> node, final T value, final List<T> ancestors) {

        if (node == null || !this.contains(node, value)) return;
        final boolean valueIsFound = value.compareTo(node.getValue()) == 0;
        if (valueIsFound) return;

        final T ancestor = node.getValue();
        ancestors.add(ancestor);

        this.getAncestors(node.getLeftChild(), value, ancestors);

        this.getAncestors(node.getRightChild(), value, ancestors);
    }

    public void reverse(final TreeNode<T> node) {

        if (node == null) return;

        final TreeNode<T> oldLeftChild = node.getLeftChild();
        final TreeNode<T> oldRightChild = node.getRightChild();
        node.setLeftChild(oldRightChild);
        node.setRightChild(oldLeftChild);

        this.reverse(node.getLeftChild());
        this.reverse(node.getRightChild());

    }

    /**
     * Checks if two trees are equal based on the DFS pre-order traversal.
     */
    public boolean equals(final Tree<T> anotherTree) {
        if (anotherTree == null) return false;
        return equals(this.root, anotherTree.root);
    }

    private boolean equals(final TreeNode<T> leftNode, final TreeNode<T> rightNode) {

        if (leftNode == null && rightNode == null) return true;

        if (leftNode != null && rightNode != null)
            return leftNode.equals(rightNode)
                    && this.equals(leftNode.getLeftChild(), rightNode.getLeftChild())
                    && this.equals(leftNode.getRightChild(), rightNode.getRightChild());

        return false;

    }

}