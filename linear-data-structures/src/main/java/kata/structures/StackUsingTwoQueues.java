package kata.structures;

public class StackUsingTwoQueues<T> {

    private final LinkedListQueue<T> queue;
    private final LinkedListQueue<T> buffer;

    public StackUsingTwoQueues() {
        this.queue = new LinkedListQueue<>();
        this.buffer = new LinkedListQueue<>();
    }

    // Push = O(1)
    public void push(final T item) {
        this.queue.enqueue(item);
    }

    /*
     * Push = O(n) since we need to dequeue all items from the queue and keep the last one.
     */
    public T pop() {
        this.retain();
        final T item = this.queue.dequeue();
        this.revert();
        return item;
    }

    /*
     * Peek = same here, except that we preserve the queue (no non-reversible action).
     */
    public T peek() {
        this.retain();
        final T item = this.queue.peek();
        this.revert();
        return item;
    }

    private void retain() {
        while (!this.queue.oneLeft()) {
            final T item = this.queue.dequeue();
            this.buffer.enqueue(item);
        }
    }

    private void revert() {
        final boolean queueWasEmpty = this.queue.isEmpty();
        while (!this.buffer.isEmpty()) {
            final T item = this.buffer.dequeue();
            this.queue.enqueue(item);
        }
        if (!queueWasEmpty) this.queue.enqueue(this.queue.dequeue());
    }

}