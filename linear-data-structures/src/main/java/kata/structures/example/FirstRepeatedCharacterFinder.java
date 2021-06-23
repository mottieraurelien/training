package kata.structures.example;

import java.util.HashSet;
import java.util.Set;

public class FirstRepeatedCharacterFinder {

    private final String sentence;
    private final Set<Character> repeatedCharacters;

    public FirstRepeatedCharacterFinder(final String sentence) {
        this.sentence = sentence;
        this.repeatedCharacters = new HashSet<>();
    }

    /**
     * Alternative : looking over the chars through an index, and compare this index with the
     * lastIndexOf(theSameChar) : if they are the same it means that the character is unique,
     * else it means that the current character is repeated.
     */
    public Character find() {

        final char[] characters = this.sentence.toCharArray();
        for (Character character : characters) {
            if (this.repeatedCharacters.contains(character)) return character;
            this.repeatedCharacters.add(character);
        }

        return null;
    }

}