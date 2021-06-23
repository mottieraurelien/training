package kata.structures.example;

import java.util.Map;

import static java.util.Collections.max;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Stream.of;

public class FirstMostRepeatedCharacter<T> {

    private final T[] inputs;

    public FirstMostRepeatedCharacter(final T[] inputs) {
        this.inputs = inputs;
    }

    /**
     * O(n) It was just an exercise that forced me to use a map to solve this problem.
     */
    public T find() {

        // Look over the inputs a first time to compute their occurrences :
        final Map<T, Long> map = of(inputs).collect(groupingBy(identity(), counting()));

        // Look over the inputs a second time to identify the most repeated character :
        return max(map.entrySet(), comparingByValue()).getKey();

    }

}