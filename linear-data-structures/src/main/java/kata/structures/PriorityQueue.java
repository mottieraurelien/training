package kata.structures;

import java.util.Comparator;

public class PriorityQueue<T> {

    private int size;

    private final T[] items;
    private final Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public PriorityQueue(final int initialCapacity, final Comparator<T> comparator) {
        this.items = (T[]) new Object[initialCapacity];
        this.comparator = comparator;
    }

    public void add(final T item) {

        if (this.isFull()) throw new IllegalStateException("The queue is already full.");

        // We look over the items to shift them is needed.
        final int index = shiftItemsToInsert(item);

        // Now we know here we need to insert our new item :
        this.items[index] = item;

        this.size++;

    }

    public T remove() {
        if (this.isEmpty()) return null;
        final T itemWithTheHighestPriority = this.items[this.size - 1];
        this.items[--this.size] = null;
        return itemWithTheHighestPriority;
    }

    public T peek() {
        if (this.isEmpty()) return null;
        return this.items[this.size - 1];
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private boolean isFull() {
        return this.size == this.items.length;
    }

    private int shiftItemsToInsert(final T item) {
        int index;
        for (index = this.size - 1; index >= 0; index--) {
            final T current = this.items[index];
            final boolean shouldShiftCurrentItem = comparator.compare(item, current) < 0;
            if (shouldShiftCurrentItem) this.items[index + 1] = this.items[index];
            else break;
        }
        return ++index;
    }

}