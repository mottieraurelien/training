package data;

public class TreeNode<T> {

    private final T value;

    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(final T value) {
        this.value = value;
    }

    public void left(final TreeNode<T> left) {
        this.left = left;
    }

    public void right(final TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getLeft() {
        return this.left;
    }

    public TreeNode<T> getRight() {
        return this.right;
    }

    public boolean leftEmpty() {
        return this.left == null;
    }

    public boolean rightEmpty() {
        return this.right == null;
    }

    public T getValue() {
        return this.value;
    }

}