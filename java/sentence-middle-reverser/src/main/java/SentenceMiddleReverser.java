import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import static java.lang.String.join;
import static java.util.regex.Pattern.compile;

public class SentenceMiddleReverser {

    private static final String EMPTY = "";
    private static final String SPACE = " ";
    private static final Pattern SPLITTER = compile(SPACE);

    private final String[] words;

    public SentenceMiddleReverser(final String sentence) {
        this.words = SPLITTER.split(sentence);
    }

    public String reverse() {

        if (words.length == 0)
            return EMPTY;

        final String firstWord = words[0];

        if (words.length == 1)
            return firstWord;

        final String lastWord = words[words.length - 1];

        if (words.length == 2)
            return firstWord + SPACE + lastWord;

        final String wordInTheMiddle = words[1];

        if (words.length == 3)
            return firstWord + SPACE + wordInTheMiddle + SPACE + lastWord;

        // Starting from four words in the sentence, the sequence can change.
        final List<String> reversedWords = new ArrayList<>();
        reversedWords.add(firstWord);

        final Stack<String> middle = new Stack<>();
        for (int i = 1; i <= words.length - 2; i++) {
            final String word = words[i];
            middle.push(word);
        }

        while (!middle.isEmpty()) {
            reversedWords.add(middle.pop());
        }

        reversedWords.add(lastWord);

        return join(SPACE, reversedWords);
    }

}