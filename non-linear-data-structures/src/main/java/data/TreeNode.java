package data;

public class TreeNode<T extends Comparable<T>> {

    private final T value;

    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;

    public TreeNode(final T value) {
        this.value = value;
    }

    public TreeNode<T> getLeftChild() {
        return this.leftChild;
    }

    public TreeNode<T> getRightChild() {
        return this.rightChild;
    }

    public void setLeftChild(final TreeNode<T> left) {
        this.leftChild = left;
    }

    public void setRightChild(final TreeNode<T> right) {
        this.rightChild = right;
    }

    public boolean leftChildIsNull() {
        return this.leftChild == null;
    }

    public boolean rightChildIsNull() {
        return this.rightChild == null;
    }

    public boolean hasLeftChild() {
        return !this.leftChildIsNull();
    }

    public boolean hasRightChild() {
        return !this.rightChildIsNull();
    }

    public boolean isLeaf() {
        return this.leftChildIsNull() && this.rightChildIsNull();
    }

    public T getValue() {
        return this.value;
    }

    public boolean equals(final TreeNode<T> anotherTreeNode) {
        final T value = this.value;
        final T anotherValue = anotherTreeNode.getValue();
        return value.compareTo(anotherValue) == 0;
    }

}