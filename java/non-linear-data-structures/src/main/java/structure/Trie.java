package structure;

import data.TrieNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

/**
 * Our trie contains values.
 * <p>
 * Each value contains pieces and the best analogy would be "each word contains letters", meaning that :
 * values = words,
 * value = word,
 * pieces = letters,
 * piece = letter.
 * <p>
 * I inject a spliterator Function (and an Iterable) to ensure a very generic and "high performance" Trie data structure here.
 */
public class Trie<T> {

    private final TrieNode<T> root;
    private final Function<T, T[]> spliterator;

    public Trie(final Function<T, T[]> spliterator) {
        this.root = new TrieNode<>(null);
        this.spliterator = spliterator;
    }

    public Trie(final Function<T, T[]> spliterator, final Iterable<T> values) {
        this.root = new TrieNode<>(null);
        this.spliterator = spliterator;
        this.load(values);
    }

    public void add(final T value) {
        TrieNode<T> node = this.root;
        final T[] pieces = spliterator.apply(value);
        for (final T piece : pieces) {
            final Optional<TrieNode<T>> next = node.getChild(piece);
            if (next.isPresent()) node = next.get();
            else node = node.add(piece);
        }
        node.nowContainsTheLastPiece();
    }

    public boolean contains(final T value) {
        if (value == null) return false;
        TrieNode<T> node = this.root;
        final T[] pieces = spliterator.apply(value);
        for (final T piece : pieces) {
            final Optional<TrieNode<T>> next = node.getChild(piece);
            if (next.isEmpty()) return false;
            node = next.get();
        }
        return node.containsLastPiece();
    }

    public Collection<T> traversePreOrder() {
        final Collection<T> values = new ArrayList<>();
        this.traversePreOrder(values, this.root);
        return values;
    }

    public Collection<T> traversePostOrder() {
        final Collection<T> values = new ArrayList<>();
        this.traversePostOrder(values, this.root);
        return values;
    }

    public void remove(final T value) {
        if (value == null) return;
        final T[] pieces = spliterator.apply(value);
        if (pieces.length == 0) return;
        this.removeWithPostOrder(this.root, pieces, 0);
    }

    private void traversePreOrder(final Collection<T> values, final TrieNode<T> node) {
        if (node != this.root) values.add(node.getValue());
        for (final TrieNode<T> child : node.getChildren())
            this.traversePreOrder(values, child);
    }

    private void traversePostOrder(final Collection<T> values, final TrieNode<T> node) {
        for (final TrieNode<T> child : node.getChildren())
            this.traversePostOrder(values, child);
        if (node != this.root) values.add(node.getValue());
    }

    private void removeWithPostOrder(final TrieNode<T> node, final T[] pieces, final int index) {
        if (index == pieces.length) {
            node.doesNotContainTheLastPieceAnymore();
            return;
        }
        final T piece = pieces[index];
        final Optional<TrieNode<T>> optionalChild = node.getChild(piece);
        if (optionalChild.isEmpty()) return;
        final TrieNode<T> child = optionalChild.get();
        final int nextIndex = index + 1;
        this.removeWithPostOrder(child, pieces, nextIndex);
        if (child.isOrphanParent() && !child.containsLastPiece()) node.remove(child);
    }

    private void load(final Iterable<T> values) {
        for (final T value : values) {
            this.add(value);
        }
    }

}