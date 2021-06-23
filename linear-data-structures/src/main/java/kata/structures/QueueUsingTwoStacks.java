package kata.structures;

import kata.data.Node;

public class QueueUsingTwoStacks<T> {

    private final Stack<T> first;
    private final Stack<T> second;

    public QueueUsingTwoStacks() {
        this.first = new Stack<>();
        this.second = new Stack<>();
    }

    public void enqueue(final T item) {
        this.first.push(item);
    }

    public T dequeue() {
        if (this.second.isEmpty()) this.buffering();
        final Node<T> itemNode = this.second.pop();
        return itemNode != null ? itemNode.getValue() : null;
    }

    public T peek() {
        if (this.second.isEmpty()) this.buffering();
        final Node<T> itemNode = this.second.peek();
        return itemNode != null ? itemNode.getValue() : null;
    }

    private void buffering() {
        while (this.first.isNotEmpty()) {
            final Node<T> item = this.first.pop();
            this.second.push(item.getValue());
        }
    }

}