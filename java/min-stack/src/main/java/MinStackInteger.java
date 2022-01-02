import java.util.Deque;
import java.util.LinkedList;

public class MinStackInteger {

    private Integer min;
    private final Deque<Integer> items;

    MinStackInteger() {
        this.items = new LinkedList<>();
    }

    /**
     * push = adding a new first integer to a linked list, the size of the linked list does not matter => 0(1).
     */
    public void push(final Integer item) {
        if (item == null) throw new IllegalArgumentException("Cannot push any null integer in the stack.");
        if (this.min == null || item.compareTo(this.min) < 0) this.min = item;
        this.items.addFirst(item);
    }

    /**
     * pop = removing the first item and potentially finding the new min item from an unsorted list => O(n).
     */
    public Integer pop() {
        final Integer item = this.items.removeFirst();
        final boolean theRemovedItemWasOurMin = item.compareTo(this.min) == 0;
        if (theRemovedItemWasOurMin) this.findNewMinFromTheRemainingItems();
        return item;
    }

    /**
     * top : just getting the first item from a linked list, the size of the linked list does not matter => 0(1).
     */
    public Integer top() {
        return this.items.getFirst();
    }

    /**
     * getMin : just getting an attribute from our class, the size of the linked list does not matter => 0(1).
     */
    public Integer getMin() {
        return this.min;
    }

    public int size() {
        return this.items.size();
    }

    private void findNewMinFromTheRemainingItems() {
        Integer currentMin = this.items.getFirst();
        for (final Integer current : this.items) if (current.compareTo(currentMin) < 0) currentMin = current;
        this.min = currentMin;
    }

}