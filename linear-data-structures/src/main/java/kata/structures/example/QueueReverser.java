package kata.structures.example;

import kata.structures.CircularArrayQueue;
import kata.structures.PriorityQueue;
import kata.structures.StackDynamicArray;

import java.util.Comparator;

import static java.lang.reflect.Array.newInstance;

public class QueueReverser<T> {

    private final int size;
    private final Class<T> itemClass;
    private final CircularArrayQueue<T> queue;

    public QueueReverser(final Class<T> itemClass, final CircularArrayQueue<T> queue) {
        this.queue = queue;
        this.size = queue.size();
        this.itemClass = itemClass;
    }

    @SuppressWarnings("unchecked")
    public T[] reverse() {

        if (queue.isEmpty()) return (T[]) newInstance(this.itemClass, 0);

        final StackDynamicArray<T> stack = new StackDynamicArray<>(this.size);

        while (queue.isNotEmpty()) {
            final T item = queue.dequeue();
            stack.push(item);
        }

        int index = 0;
        final T[] items = (T[]) newInstance(this.itemClass, this.size);
        while (stack.isNotEmpty()) {
            final T item = stack.pop();
            items[index++] = item;
        }

        return items;

    }

    @SuppressWarnings("unchecked")
    public T[] reverse(final int k, final Comparator<T> comparator) {

        if (k < 0 || k >= this.size) throw new IllegalArgumentException("K is not valid.");
        if (this.queue.isEmpty()) return (T[]) newInstance(this.itemClass, 0);

        int count = 0;
        final PriorityQueue<T> priorityQueue = new PriorityQueue<>(k, comparator);
        while (this.queue.isNotEmpty() && count < k) {
            final T item = queue.dequeue();
            priorityQueue.add(item);
            count++;
        }

        count = 0;
        final T[] items = (T[]) newInstance(this.itemClass, this.size);
        while (priorityQueue.isNotEmpty()) {
            items[count++] = priorityQueue.remove();
        }

        count = k;
        while (this.queue.isNotEmpty()) {
            items[count++] = this.queue.dequeue();
        }

        return items;

    }

}