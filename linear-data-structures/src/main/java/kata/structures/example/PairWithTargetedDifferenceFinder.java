package kata.structures.example;

import kata.data.Pair;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.sort;
import static kata.data.Pair.of;

public class PairWithTargetedDifferenceFinder<T extends Number> {

    private final int size;
    private final T[] inputs;

    public PairWithTargetedDifferenceFinder(final T[] inputs) {
        this.inputs = inputs;
        this.size = this.inputs.length;
    }

    /**
     * O(n).
     */
    public Set<Pair<T, T>> find(final int targetedDifference) {

        sort(this.inputs);

        final Set<Pair<T, T>> pairs = new HashSet<>();

        for (int i = 0; i < this.size; i++) {
            final T input = this.inputs[i];
            for (int j = i; j < this.size; j++) {
                final T current = this.inputs[j];
                final int difference = current.intValue() - input.intValue();
                if (difference == targetedDifference) pairs.add(of(input, current));
            }
        }

        return pairs;

    }

}