package kata.structures;

import kata.data.Node;

public class Stack<T> {

    Node<T> first;
    Node<T> last;
    int size;

    public void push(final T item) {

        if (item == null) throw new IllegalArgumentException("Pushing null is not allowed.");

        if (this.first == null) {
            this.first = this.last = new Node<>(item, null);
        } else {
            this.first = new Node<>(item, this.first);
        }
        this.size++;

    }

    public Node<T> peek() {
        return this.first;
    }

    public Node<T> pop() {

        if (this.first == null) return null;

        final Node<T> item = this.first;
        if (this.first == this.last) {
            this.first = this.last = null;
        } else {
            this.first = this.first.getNext();
        }

        this.size--;
        return item;

    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

}