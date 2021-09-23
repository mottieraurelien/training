import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.List.of;

public class TwoSum {

    private final List<Integer> numbers;

    public TwoSum(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * Time complexity : O(n)
     */
    public List<Integer> target(final Integer targetedSum) {

        final Map<Integer, Integer> deltas = new HashMap<>();

        for (final Integer number : this.numbers) {
            if (deltas.containsKey(number)) return of(deltas.get(number), number);
            deltas.put(targetedSum - number, number);
        }

        return emptyList();

    }

}