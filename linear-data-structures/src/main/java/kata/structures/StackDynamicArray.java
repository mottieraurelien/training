package kata.structures;

import static java.util.Arrays.copyOf;

public class StackDynamicArray<T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private T[] items;
    private int lastPopulatedIndex;
    private int nextFreeIndex;
    private final int initialCapacity;

    @SuppressWarnings("unchecked")
    public StackDynamicArray(int initialCapacity) {
        if (initialCapacity == 0) initialCapacity = DEFAULT_INITIAL_CAPACITY;
        this.initialCapacity = initialCapacity;
        this.items = (T[]) new Object[this.initialCapacity];
        this.lastPopulatedIndex = -1;
        this.nextFreeIndex = 0;
    }

    public StackDynamicArray(final T[] items) {
        if (items == null || items.length == 0)
            throw new IllegalArgumentException("Cannot initialise stack from an empty array.");
        this.items = items;
        this.initialCapacity = items.length;
        this.lastPopulatedIndex = items.length - 1;
        this.nextFreeIndex = items.length;
    }

    public void push(final T item) {
        if (item == null) throw new IllegalArgumentException("Pushing null is not allowed.");
        if (mustGrow()) this.grow();
        this.items[this.nextFreeIndex] = item;
        this.lastPopulatedIndex = this.nextFreeIndex++;
    }

    public T pop() {
        if (this.isEmpty()) return null;
        final T item = this.items[this.lastPopulatedIndex];
        this.nextFreeIndex = this.lastPopulatedIndex--;
        return item;
    }

    public T peek() {
        if (this.isEmpty()) return null;
        return this.items[this.lastPopulatedIndex];
    }

    public int size() {
        return this.nextFreeIndex;
    }

    public boolean isEmpty() {
        return this.nextFreeIndex == 0;
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    private boolean mustGrow() {
        return this.nextFreeIndex >= this.items.length;
    }

    private void grow() {
        final int capacity = this.items.length;
        final int newCapacity = capacity + this.initialCapacity;
        this.items = copyOf(this.items, newCapacity);
    }

}