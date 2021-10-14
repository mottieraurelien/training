package data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class TrieNode<T> {

    private final Map<T, TrieNode<T>> children;

    public TrieNode() {
        this.children = new HashMap<>();
    }

    public Optional<TrieNode<T>> getChild(final T childValue) {
        return ofNullable(this.children.get(childValue));
    }

    public TrieNode<T> add(final T childValue) {
        final TrieNode<T> childNode = new TrieNode<>();
        this.children.put(childValue, childNode);
        return childNode;
    }

}