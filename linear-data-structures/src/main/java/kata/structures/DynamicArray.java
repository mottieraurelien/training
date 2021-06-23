package kata.structures;

import java.util.Comparator;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;

public class DynamicArray<T> {

    private T[] items;
    private int size;
    private final int initialCapacity;

    @SuppressWarnings("unchecked")
    public DynamicArray(final int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.items = (T[]) new Object[this.initialCapacity];
        this.size = 0;
    }

    public DynamicArray(final T[] items, final int size) {
        this.initialCapacity = items.length;
        this.items = items;
        this.size = size;
    }

    public void insert(final T item) {

        if (this.mustGrow()) {
            this.grow();
        }

        this.insertAt(item, this.size);

    }

    public int indexOf(final T item) {
        for (int i = 0; i <= this.getLastIndex(); i++) {
            if (this.items[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(final T item) {
        return this.indexOf(item) > -1;
    }

    @SuppressWarnings("unchecked")
    public void removeAt(final int index) {

        if (index > this.getLastIndex()) {
            throw new IllegalArgumentException("Index must be between [0," + this.getLastIndex() + "[.");
        }

        final int newLength = this.getLength();
        final T[] newItems = (T[]) new Object[newLength];
        int newItemsCounter = 0;
        for (int i = 0; i < this.getLastIndex(); i++) {
            if (i != index) {
                newItems[newItemsCounter] = this.items[i];
                newItemsCounter++;
            }
        }
        this.items = newItems;
        this.size--;
    }

    public T max(final Comparator<T> comparator) {
        return stream(this.items)
                .max(comparator)
                .orElseThrow(() -> new UnsupportedOperationException("Could not find any max item."));
    }

    @SuppressWarnings("unchecked")
    public DynamicArray<T> intersect(final DynamicArray<T> otherNumbers) {

        // Case 1 : one of them is empty so they cannot have anything in common :
        if (this.isEmpty() || otherNumbers.isEmpty()) return new DynamicArray<>(0);

        // Case 2 : both are not empty, meaning that we need to look over the items...
        // Let's be smart and look over the the smallest array > O(m), m being the number of item in the smallest array.
        final DynamicArray<T> smallestDynamicArray = this.getLastIndex() < otherNumbers.getLastIndex() ? this : otherNumbers;
        final DynamicArray<T> biggestDynamicArray = this.getLastIndex() < otherNumbers.getLastIndex() ? otherNumbers : this;

        // Best scenario : every smallestArray item are in the the biggestArray.
        final T[] intersectArray = (T[]) new Object[smallestDynamicArray.size()];
        int intersectCount = 0;

        // Let's look over the items now :
        for (int i = 0; i < smallestDynamicArray.size(); i++) {
            final T currentItem = smallestDynamicArray.items[i];
            if (biggestDynamicArray.contains(currentItem)) {
                intersectArray[intersectCount++] = currentItem;
            }
        }

        return new DynamicArray<>(intersectArray, intersectCount);

    }

    public DynamicArray<T> reverse() {

        final DynamicArray<T> reversedDynamicArray = new DynamicArray<>(this.size);

        for (int i = this.getLastIndex(); i >= 0; i--) {
            final T currentItem = this.items[i];
            reversedDynamicArray.insert(currentItem);
        }

        return reversedDynamicArray;

    }

    public void insertAt(final T item, final int index) {

        if (index >= this.getLength()) {
            throw new IndexOutOfBoundsException("The array is not large enough to welcome the item to insert at this specific index.");
        }

        if (index <= this.getLastIndex()) {
            // We need to insert this new item in the "middle" of the array.
            // But can we move the next items ? Is the array large enough ?
            if (this.mustGrow()) {
                this.grow();
            }
            // We can now move the existing items to let the position empty for the new item we want to insert :
            if (this.getLastIndex() + 1 - index >= 0)
                arraycopy(this.items, index, this.items, index + 1, this.getLastIndex() + 1 - index);
        }

        this.items[index] = item;
        this.size++;

    }

    private int getLength() {
        return this.items.length;
    }

    public int size() {
        return this.size;
    }

    private int getLastIndex() {
        return this.size - 1;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private boolean mustGrow() {
        return this.size >= this.getLength();
    }

    private void grow() {
        final int currentLength = this.getLength();
        final int newLength = currentLength + this.initialCapacity;
        this.items = copyOf(this.items, newLength);
    }

}