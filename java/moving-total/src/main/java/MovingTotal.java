import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MovingTotal {

    private static final Integer GROUP_SIZE = 3;

    private final Queue<Integer> buffer = new LinkedList<>();
    private final Set<Integer> totals = new HashSet<>();

    public void append(final int[] numbers) {
        for (final Integer number : numbers) {
            this.buffer.add(number);
            if (this.buffer.size() == GROUP_SIZE) {
                final int total = this.buffer.stream().mapToInt(Integer::intValue).sum();
                this.totals.add(total);
                this.buffer.remove();
            }
        }
    }

    public boolean contains(final int total) {
        return this.totals.contains(total);
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