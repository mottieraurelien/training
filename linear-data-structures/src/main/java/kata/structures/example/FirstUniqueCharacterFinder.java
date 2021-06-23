package kata.structures.example;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterFinder {

    private final String sentence;
    private final Map<Character, Integer> occurrencesByCharacter;

    public FirstUniqueCharacterFinder(final String sentence) {
        this.sentence = sentence;
        this.occurrencesByCharacter = new HashMap<>();
    }

    /**
     * Alternative : looking over the chars through an index, and compare this index with the
     * lastIndexOf(theSameChar) : if they are the same it means that the character is unique,
     * else it means that the current character is repeated.
     */
    public Character find() {

        final char[] chars = this.sentence.toCharArray();
        final int size = chars.length;
        if (size == 0) return null;

        final char[] characters = this.sentence.toCharArray();

        // Look over the characters a first time, to count their occurrences :
        for (Character character : characters) {
            Integer nbOccurrences = this.occurrencesByCharacter.get(character);
            if (nbOccurrences == null) nbOccurrences = 0;
            this.occurrencesByCharacter.put(character, ++nbOccurrences);
        }

        // Look over the characters a second time, to identify the character that associates a single occurrence :
        Character firstUniqueCharacter = null;
        for (Character character : characters) {
            final int nbOccurrence = this.occurrencesByCharacter.get(character);
            if (nbOccurrence == 1) {
                firstUniqueCharacter = character;
                break;
            }
        }

        return firstUniqueCharacter;

    }

}
