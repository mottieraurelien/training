package kata.structures.example;

import kata.structures.Stack;

public class StringReverser {

    private final Stack<Character> stack;

    public StringReverser() {
        this.stack = new Stack<>();
    }

    public String reverse(final String input) {

        for (char c : input.toCharArray()) {
            this.stack.push(c);
        }

        final StringBuilder builder = new StringBuilder();
        while (this.stack.isNotEmpty()) {
            builder.append(this.stack.pop().getValue());
        }

        return builder.toString();
    }

}