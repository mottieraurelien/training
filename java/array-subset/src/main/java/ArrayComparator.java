import java.util.Comparator;

public class ArrayComparator<T> {

    private final Comparator<T> comparator;
    private final T[] smallest;
    private final T[] largest;

    public ArrayComparator(final Comparator<T> comparator, final T[] firstArray, final T[] secondArray) {
        this.comparator = comparator;
        if (firstArray.length <= secondArray.length) {
            this.smallest = firstArray;
            this.largest = secondArray;
        } else {
            this.smallest = secondArray;
            this.largest = firstArray;
        }
    }

    /**
     * ASSUMPTION : firstArray(n) and secondArray(m) already sorted, with n<=m.
     * <p>
     * SOLUTION :
     * - Since the arrays are already sorted, we can operate a binary search meaning O(log m) for each element of the smallest array.
     * - Since the smallest array contains n items, we would need to operate n times the binary search.
     * - Each binary search is O(log m).
     * - >>> Final time complexity result : O(n log m).
     * <p>
     * REMARK 1 : If the arrays were not sorted, time complexity would be O(n log n) + O(m log m) + O(n log m).
     * <p>
     * REMARK 2 : If our arrays would not contain any duplicated values, we could have used a Set and target O(m+n).
     */
    public boolean isSubset() {

        for (final T item : this.smallest)
            if (!binarySearch(item)) return false;

        return true;

    }

    private boolean binarySearch(final T target) {
        int left = 0;
        int right = this.largest.length - 1;
        while (left <= right) {
            final int middle = (left + right) / 2;
            final T value = this.largest[middle];
            if (value.equals(target))
                return true;
            final boolean keepDiggingOnTheLeftSide = this.comparator.compare(target, value) < 0;
            if (keepDiggingOnTheLeftSide) right = middle - 1;
            else left = middle + 1;
        }
        return false;
    }

}