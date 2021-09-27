import java.util.List;

public class MaxSum {

    private final List<Integer> numbers;

    public MaxSum(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * O(N LOG N), 114ms to 120ms to run the test with the large list.
     */
    public int find() {

        // To not have to compute a "complex" sum for simple scenarios :
        if (this.numbers == null || this.numbers.isEmpty()) return 0;
        if (this.numbers.size() == 1) return this.numbers.get(0);
        if (this.numbers.size() == 2) return this.numbers.get(0) + this.numbers.get(1);

        // .sorted() => Arrays.sort() behind => O(n log n).
        final int allOfThemExceptTheTwoLastOnes = this.numbers.size() - 2;
        return this.numbers.stream()
                .sorted(Integer::compareTo)
                .skip(allOfThemExceptTheTwoLastOnes)
                .reduce(0, Integer::sum);

    }

}