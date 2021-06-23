package kata.structures;

import kata.data.Node;

public class LinkedListQueue<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    public LinkedListQueue() {
        this.first = this.last = null;
    }

    /**
     * Enqueue based on addLast => O(1).
     */
    public void enqueue(final T item) {

        if (item == null) throw new IllegalArgumentException("Item must not be null.");

        if (this.isEmpty()) {
            this.first = this.last = new Node<>(item, null);
        } else {
            final Node<T> previousLast = this.last;
            this.last = new Node<>(item, null);
            previousLast.point(this.last);
        }

        this.size++;

    }

    /**
     * Enqueue based on removeFirst => O(1).
     */
    public T dequeue() {

        if (this.isEmpty()) throw new IllegalStateException("Queue is already empty.");

        final T item = this.first.getValue();
        if (this.first == this.last) {
            this.first = this.last = null;
        } else {
            this.first = this.first.getNext();
        }

        this.size--;

        return item;
    }

    /**
     * Enqueue based on getFirst => O(1).
     */
    public T peek() {
        if (this.isEmpty()) return null;
        return this.first.getValue();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean oneLeft() {
        return this.size == 1;
    }

}