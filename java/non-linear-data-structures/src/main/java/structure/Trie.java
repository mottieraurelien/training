package structure;

import data.TrieNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Arrays.copyOf;
import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Our trie contains values.
 * <p>
 * Each value contains pieces and the best analogy would be "each word contains letters", meaning that :
 * values = words,
 * value = word,
 * pieces = letters,
 * piece = letter.
 * <p>
 * I inject spliterator and joiner Functions (and an Iterable) to ensure a safe generic and "high performance" Trie data structure.
 * <p>
 * Spliterator = how to break a word into letters,
 * Joiner = how to build a word from letters.
 * <p>
 * This example could also work with numbers and figures, but it can also be way more complex (bike and parts, computer and components, ...)
 */
public class Trie<T> {

    private int numberOfEnds;
    private int numberOfNodes;
    private T[] shortestEnd;

    private final TrieNode<T> root;
    private final Function<T, T[]> spliterator;
    private final Function<T[], T> joiner;

    public Trie(final Function<T, T[]> spliterator, final Function<T[], T> joiner) {
        this.root = new TrieNode<>(null);
        this.spliterator = spliterator;
        this.joiner = joiner;
    }

    public Trie(final Function<T, T[]> spliterator, final Function<T[], T> joiner, final Iterable<T> values) {
        this.root = new TrieNode<>(null);
        this.spliterator = spliterator;
        this.joiner = joiner;
        this.load(values);
    }

    public int getNumberOfEnds() {
        return this.numberOfEnds;
    }

    public int getNumberOfNodes() {
        return this.numberOfNodes;
    }

    public void add(final T value) {
        if (value == null) return;
        TrieNode<T> node = this.root;
        final T[] pieces = this.spliterator.apply(value);
        if (this.shortestEnd == null) this.shortestEnd = pieces;
        else if (pieces.length < this.shortestEnd.length) this.shortestEnd = pieces;
        for (final T piece : pieces) {
            final Optional<TrieNode<T>> next = node.getChild(piece);
            if (next.isPresent()) node = next.get();
            else node = node.add(piece);
            this.numberOfNodes++;
        }
        node.isNowAnEnd();
        this.numberOfEnds++;
    }

    public boolean contains(final T input) {
        if (input == null) return false;
        final T[] pieces = this.spliterator.apply(input);
        final Optional<TrieNode<T>> node = this.recursiveFindLastNodeOf(this.root, pieces, 0);
        return node.isPresent() && node.get().isAnEnd();
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
        final T[] pieces = this.spliterator.apply(value);
        if (pieces.length == 0) return;
        this.removeWithPostOrder(this.root, pieces, 0);
    }

    public T findLongestCommonPrefix(final T[] emptyArray) {

        final Collection<T> longestCommonPrefixPieces = new ArrayList<>();
        final int maxLength = this.shortestEnd.length;

        TrieNode<T> node = this.root;
        while (longestCommonPrefixPieces.size() < maxLength) {
            if (node.hasNotOnlyOneChild()) break;
            node = node.getTheOnlyChild();
            longestCommonPrefixPieces.add(node.getValue());
        }

        final T[] pieces = longestCommonPrefixPieces.toArray(emptyArray);
        return this.joiner.apply(pieces);
    }

    /**
     * Basically the core of the auto-complete feature.
     */
    public Collection<T> suggestions(final T prefix, final int maximumNumberOfSuggestions) {
        final Collection<T> suggestions = new ArrayList<>();
        if (prefix == null) return suggestions;
        final T[] pieces = this.spliterator.apply(prefix);
        final Optional<TrieNode<T>> prefixLastNode = this.recursiveFindLastNodeOf(this.root, pieces, 0);
        if (prefixLastNode.isEmpty()) return suggestions;
        final TrieNode<T> node = prefixLastNode.get();
        if (node.isOrphanParent()) return suggestions;
        this.suggestions(suggestions, maximumNumberOfSuggestions, pieces, node, true);
        return suggestions;
    }

    private void suggestions(final Collection<T> suggestions, final int maximumNumberOfSuggestions, final T[] pieces, final TrieNode<T> node, final boolean starting) {
        if (suggestions.size() == maximumNumberOfSuggestions) return;

        final T[] newPieces = copyOf(pieces, pieces.length + (starting ? 0 : 1));
        newPieces[pieces.length - (starting ? 1 : 0)] = node.getValue();

        if (node.isAnEnd()) {
            final T suggestion = this.joiner.apply(newPieces);
            suggestions.add(suggestion);
        }

        for (final TrieNode<T> child : node.getChildren()) {
            this.suggestions(suggestions, maximumNumberOfSuggestions, newPieces, child, false);
        }

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
            node.isNotAnEndAnymore();
            return;
        }
        final T piece = pieces[index];
        final Optional<TrieNode<T>> optionalChild = node.getChild(piece);
        if (optionalChild.isEmpty()) return;
        final TrieNode<T> child = optionalChild.get();
        final int nextIndex = index + 1;
        this.removeWithPostOrder(child, pieces, nextIndex);
        if (child.isOrphanParent() && !child.isAnEnd()) node.remove(child);
    }

    private Optional<TrieNode<T>> recursiveFindLastNodeOf(final TrieNode<T> node, final T[] pieces, final int index) {

        if (pieces.length == index)
            return of(node);

        final T piece = pieces[index];
        final Optional<TrieNode<T>> child = node.getChild(piece);

        if (child.isEmpty())
            return empty();

        return this.recursiveFindLastNodeOf(child.get(), pieces, index + 1);
    }

    private void load(final Iterable<T> values) {
        for (final T value : values) {
            this.add(value);
        }
    }

}