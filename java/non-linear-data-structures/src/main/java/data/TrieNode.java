package data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class TrieNode<T> {

    private final T value;
    private boolean isLeaf;
    private final Map<T, TrieNode<T>> children;

    public TrieNode(final T value) {
        this.value = value;
        this.isLeaf = false;
        this.children = new HashMap<>();
    }

    public Optional<TrieNode<T>> getChild(final T childValue) {
        return ofNullable(this.children.get(childValue));
    }

    public TrieNode<T> add(final T childValue) {
        final TrieNode<T> childNode = new TrieNode<>(childValue);
        this.children.put(childValue, childNode);
        return childNode;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }

    public void isNowLeaf() {
        this.isLeaf = true;
    }

    public T getValue() {
        return this.value;
    }

    public Collection<TrieNode<T>> getChildren() {
        return this.children.values();
    }

    @Override
    public String toString() {
        return "value=" + this.value;
    }

}