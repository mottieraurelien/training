package structure;

import data.TrieNode;

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
        this.root = new TrieNode<>();
        this.spliterator = spliterator;
    }

    public Trie(final Function<T, T[]> spliterator, final Iterable<T> values) {
        this.root = new TrieNode<>();
        this.spliterator = spliterator;
        this.load(values);
    }

    public void add(final T value) {
        TrieNode<T> node = this.root;
        final T[] pieces = spliterator.apply(value);
        for (final T piece : pieces) {
            final Optional<TrieNode<T>> optionalChild = node.getChild(piece);
            if (optionalChild.isPresent()) node = optionalChild.get();
            else node = node.add(piece);
        }
    }

    public boolean contains(final T value) {
        TrieNode<T> node = this.root;
        final T[] pieces = spliterator.apply(value);
        for (final T piece : pieces) {
            final Optional<TrieNode<T>> child = node.getChild(piece);
            if (child.isEmpty()) return false;
            node = child.get();
        }
        return true;
    }

    private void load(final Iterable<T> values) {
        for (final T value : values) {
            this.add(value);
        }
    }

}