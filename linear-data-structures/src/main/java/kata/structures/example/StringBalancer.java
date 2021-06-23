package kata.structures.example;

import kata.data.Node;
import kata.structures.Stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;

public class StringBalancer {

    private static final Map<Character, Character> SPECIAL_CHARS = new HashMap<>();

    static {
        SPECIAL_CHARS.put('(', ')');
        SPECIAL_CHARS.put('[', ']');
        SPECIAL_CHARS.put('{', '}');
        SPECIAL_CHARS.put('<', '>');
    }

    private static final Set<Character> CLOSING_SPECIAL_CHARS = new HashSet<>(asList(')', '}', ']', '>'));

    private final Stack<Character> stack;

    public StringBalancer() {
        this.stack = new Stack<>();
    }

    public boolean isBalanced(final String input) {

        for (char character : input.toCharArray()) {

            // If leading : push on top
            if (isOpening(character)) {

                // Let's just push the char on top of our Stack :
                this.stack.push(character);

            } else if (isClosing(character)) {

                // String started with a closing char...
                if (this.stack.isEmpty()) return false;

                // We need to pop the char on top of the stack and compare it with the current char
                // If they match, awesome = continue
                // If they don't match, return false.
                final Node<Character> top = this.stack.pop();
                final Character actualPreviousOpeningCharacter = top.getValue();
                final Character expectedClosingChar = SPECIAL_CHARS.get(actualPreviousOpeningCharacter);
                if (expectedClosingChar != character) return false;

            }

            // Regular char : ignore it.

        }

        return this.stack.isEmpty();

    }

    private static boolean isOpening(final Character character) {
        return SPECIAL_CHARS.containsKey(character);
    }

    private static boolean isClosing(final Character character) {
        return CLOSING_SPECIAL_CHARS.contains(character);
    }

}