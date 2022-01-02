import java.util.Deque;
import java.util.LinkedList;

public class MinStack<T extends Comparable<T>> {

    private T min;
    private final Deque<T> items;

    MinStack() {
        this.items = new LinkedList<>();
    }

    /**
     * push = adding a new first item to a linked list, the size of the linked list does not matter => 0(1).
     */
    public void push(final T item) {
        if (this.min == null || item.compareTo(this.min) < 0) this.min = item;
        this.items.addFirst(item);
    }

    /**
     * pop = removing the first item and potentially finding the new min item from an unsorted list => O(n).
     */
    public T pop() {
        if (this.items.isEmpty()) return null;
        final T item = this.items.removeFirst();
        final boolean theRemovedItemWasOurMin = item.compareTo(this.min) == 0;
        if (theRemovedItemWasOurMin) this.findNewMinFromTheRemainingItems();
        return item;
    }

    /**
     * top : just getting the first item from a linked list, the size of the linked list does not matter => 0(1).
     */
    public T top() {
        if (this.items.isEmpty()) return null;
        return this.items.getFirst();
    }

    /**
     * getMin : just getting an attribute from our class, the size of the linked list does not matter => 0(1).
     */
    public T getMin() {
        return this.min;
    }

    public int size() {
        return this.items.size();
    }

    private void findNewMinFromTheRemainingItems() {
        if (this.items.isEmpty()) {
            this.min = null;
            return;
        }
        T currentMin = this.items.getFirst();
        for (final T current : this.items) if (current.compareTo(currentMin) < 0) currentMin = current;
        this.min = currentMin;
    }

}