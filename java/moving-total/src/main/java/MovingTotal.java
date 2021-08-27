import java.util.*;
import java.util.function.Predicate;

import static java.lang.Integer.compare;
import static java.util.Objects.hash;
import static java.util.stream.Collectors.toList;

public class MovingTotal {

    private static final Integer GROUP_SIZE = 3;

    private final Set<Number> container = new TreeSet<>();

    public void append(final int[] values) {
        for (final int value : values) container.add(new Number(value, false));
        this.compute();
    }

    public boolean contains(final int total) {
        final Predicate<Number> theTargetedTotal = number -> number.isTotal && number.value == total;
        return container.stream().anyMatch(theTargetedTotal);
    }

    private void compute() {
        final List<Number> numbers = container.stream().filter(number -> !number.isTotal).collect(toList());
        if (numbers.size() < GROUP_SIZE) return;
        final Queue<Integer> buffer = new LinkedList<>();
        for (final Number number : numbers) {
            buffer.add(number.value);
            if (buffer.size() == GROUP_SIZE) {
                final int total = buffer.stream().mapToInt(Integer::intValue).sum();
                container.add(new Number(total, true));
                buffer.remove();
            }
        }
    }

    static class Number implements Comparable<Number> {

        private final int value;
        private final boolean isTotal;

        Number(final int value, final boolean isTotal) {
            this.value = value;
            this.isTotal = isTotal;
        }

        @Override
        public int compareTo(final Number another) {
            return compare(this.value, another.value);
        }

        @Override
        public boolean equals(final Object anotherObject) {
            if (this == anotherObject) return true;
            if (anotherObject == null || getClass() != anotherObject.getClass()) return false;
            final Number number = (Number) anotherObject;
            return this.value == number.value;
        }

        @Override
        public int hashCode() {
            return hash(this.value);
        }

    }

    public static void main(final String[] args) {

        final MovingTotal movingTotal = new MovingTotal();

        movingTotal.append(new int[]{1, 2, 3, 4});

        System.out.println(movingTotal.contains(6));
        System.out.println(movingTotal.contains(9));
        System.out.println(movingTotal.contains(12));
        System.out.println(movingTotal.contains(7));

        movingTotal.append(new int[]{5});

        System.out.println(movingTotal.contains(6));
        System.out.println(movingTotal.contains(9));
        System.out.println(movingTotal.contains(12));
        System.out.println(movingTotal.contains(7));

    }

}