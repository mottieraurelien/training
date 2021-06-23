package kata.structures.example;

import static java.util.Arrays.sort;

public class PairWithTargetedSumFinder<T extends Number> {

    private final int size;
    private final T[] inputs;

    public PairWithTargetedSumFinder(final T[] inputs) {
        this.inputs = inputs;
        this.size = this.inputs.length;
    }

    public Integer[] find(final int targetedSum) {

        sort(this.inputs);

        for (int i = 0; i < this.size; i++) {
            final T input = this.inputs[i];
            for (int j = i; j < this.size; j++) {
                final T current = this.inputs[j];
                final int sum = input.intValue() + current.intValue();
                if (sum == targetedSum) return new Integer[]{i, j};
            }
        }

        return null;

    }

}