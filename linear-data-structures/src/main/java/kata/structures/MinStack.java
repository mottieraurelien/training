package kata.structures;

import kata.data.Node;

public class MinStack {

    private final Stack<Integer> numbers;
    private final Stack<Integer> mins;

    public MinStack() {
        this.numbers = new Stack<>();
        this.mins = new Stack<>();
    }

    public void push(final Integer number) {
        this.numbers.push(number);
        final Node<Integer> min = this.mins.peek();
        if (min == null || number < min.getValue()) this.mins.push(number);
    }

    public Integer pop() {
        if (this.numbers.isEmpty()) return null;
        final Integer number = this.numbers.pop().getValue();
        if (number.equals(this.mins.peek().getValue())) this.mins.pop();
        return number;
    }

    public Integer min() {
        if (this.mins.isEmpty()) return null;
        return this.mins.peek().getValue();
    }

}