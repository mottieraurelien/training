package data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class TrieNode<T> {

    private final T value;
    private boolean isAnEnd;
    private final Map<T, TrieNode<T>> children;

    public TrieNode(final T value) {
        this.value = value;
        this.isAnEnd = false;
        this.children = new HashMap<>();
    }

    public T getValue() {
        return this.value;
    }

    public boolean isAnEnd() {
        return this.isAnEnd;
    }

    public void isNowAnEnd() {
        this.isAnEnd = true;
    }

    public void isNotAnEndAnymore() {
        this.isAnEnd = false;
    }

    public Collection<TrieNode<T>> getChildren() {
        return this.children.values();
    }

    public boolean isOrphanParent() {
        return this.children.isEmpty();
    }

    public Optional<TrieNode<T>> getChild(final T childValue) {
        return ofNullable(this.children.get(childValue));
    }

    public TrieNode<T> add(final T childValue) {
        final TrieNode<T> childNode = new TrieNode<>(childValue);
        this.children.put(childValue, childNode);
        return childNode;
    }

    public void remove(final TrieNode<T> child) {
        this.children.remove(child.value);
    }

    @Override
    public String toString() {
        return "value=" + this.value;
    }

}