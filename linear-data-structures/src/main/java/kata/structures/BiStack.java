package kata.structures;

public class BiStack<T> {

    private int nextFreeIndexOne;
    private int lastPopulatedIndexOne;

    private int nextFreeIndexTwo;
    private int lastPopulatedIndexTwo;

    private final T[] items;
    private final int size;

    @SuppressWarnings("unchecked")
    public BiStack(final int initialCapacity) {
        if (initialCapacity <= 0) throw new IllegalArgumentException("initialCapacity must be greater than 0.");
        this.items = (T[]) new Object[initialCapacity];
        this.size = initialCapacity;
        this.nextFreeIndexOne = 0;
        this.lastPopulatedIndexOne = -1;
        this.nextFreeIndexTwo = this.size - 1;
        this.lastPopulatedIndexTwo = this.size;
    }

    public void pushOne(final T item) {
        if (this.isOneFull()) throw new StackOverflowError("Stack one is full.");
        this.items[this.nextFreeIndexOne] = item;
        this.lastPopulatedIndexOne = this.nextFreeIndexOne;
        this.nextFreeIndexOne++;
    }

    public void pushTwo(final T item) {
        if (this.isTwoFull()) throw new StackOverflowError("Stack two is full.");
        this.items[this.nextFreeIndexTwo] = item;
        this.lastPopulatedIndexTwo = this.nextFreeIndexTwo;
        this.nextFreeIndexTwo--;
    }

    public T popOne() {
        if (this.isOneEmpty()) return null;
        final T item = this.items[this.lastPopulatedIndexOne--];
        this.nextFreeIndexOne--;
        return item;
    }

    public T popTwo() {
        if (this.isTwoEmpty()) return null;
        final T item = this.items[this.lastPopulatedIndexTwo++];
        this.nextFreeIndexTwo++;
        return item;
    }

    public boolean isOneEmpty() {
        return this.nextFreeIndexOne == 0;
    }

    public boolean isTwoEmpty() {
        return this.nextFreeIndexTwo == this.size - 1;
    }

    public boolean isOneFull() {
        return this.nextFreeIndexOne == this.lastPopulatedIndexTwo;
    }

    public boolean isTwoFull() {
        return this.nextFreeIndexTwo == this.lastPopulatedIndexOne;
    }

}