package kata.structures;

public class CircularArrayQueue<T> {

    private int headIndex;
    private int freeIndex;

    private int count;
    private final T[] items;
    private final int maxSize;

    @SuppressWarnings("unchecked")
    public CircularArrayQueue(final Integer initialCapacity) {
        this.items = (T[]) new Object[initialCapacity];
        this.maxSize = initialCapacity;
        this.headIndex = -1;
        this.count = 0;
        this.freeIndex = 0;
    }

    public void enqueue(final T item) {
        if (item == null) throw new IllegalArgumentException("Cannot enqueue a null item.");
        if (this.isFull()) throw new IllegalStateException("Cannot enqueue anymore, queue is full.");
        if (this.isEmpty()) this.headIndex = freeIndex;
        this.count++;
        this.items[this.freeIndex] = item;
        this.freeIndex = (this.freeIndex + 1) % this.maxSize;
    }

    public T dequeue() {
        if (this.isEmpty()) return null;
        final T item = this.items[this.headIndex];
        this.items[this.headIndex] = null;
        this.headIndex = (this.headIndex + 1) % this.maxSize;
        this.count--;
        return item;
    }

    public T peek() {
        if (this.isEmpty()) return null;
        return this.items[this.headIndex];
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public boolean isFull() {
        return this.count == this.items.length;
    }

    public int size() {
        return this.count;
    }

}