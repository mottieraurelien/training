import java.util.*;

import static java.util.Comparator.reverseOrder;

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

    /**
     * O(N LOG N) as well but a bit faster, 108ms to 114ms to run the test with the large list.
     */
    public int findUsingAlternative() {

        final Comparator<Integer> fromMaxToMin = reverseOrder();
        final Set<Integer> sortedNumbers = new TreeSet<>(fromMaxToMin);
        // addAll on a TreeSet => O(n log n).
        sortedNumbers.addAll(this.numbers);
        final List<Integer> extractedSortedNumbers = new ArrayList<>(sortedNumbers);
        return extractedSortedNumbers.get(0) + extractedSortedNumbers.get(1);

    }

}