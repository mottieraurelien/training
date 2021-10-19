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

        if (words.length == 3) {
            final String wordInTheMiddle = words[1];
            return firstWord + SPACE + wordInTheMiddle + SPACE + lastWord;
        }

        // Starting from four words in the sentence, the sequence can change
        // since we have more than four words "in the middle".
        final List<String> reversedWords = new ArrayList<>();

        // The place of the first word does not change :
        reversedWords.add(firstWord);

        // The words in the middle must be changed :
        final Stack<String> middle = new Stack<>();
        for (int i = 1; i <= words.length - 2; i++)
            middle.push(words[i]);
        while (!middle.isEmpty())
            reversedWords.add(middle.pop());

        // The place of the last word does not change :
        reversedWords.add(lastWord);

        return join(SPACE, reversedWords);
    }

}