package structure;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class TrieTest {

    private static final Function<String, String[]> SPLITERATOR = string -> string.split("", 0);

    private static final List<String> SAMPLE = asList(
            "boy", "book", "border", "cat", "dog",
            "doctor", "fine", "finest", "figure", "pick",
            "pickle", "picture"
    );

    @Test
    void should_load_the_dictionary_when_the_sample_is_not_empty() {

        // [Act]
        final Trie<String> actual = new Trie<>(SPLITERATOR, SAMPLE);

        // [Assert]
        assertThat(actual.contains("boy")).isTrue();
        assertThat(actual.contains("book")).isTrue();
        assertThat(actual.contains("border")).isTrue();
        assertThat(actual.contains("cat")).isTrue();
        assertThat(actual.contains("dog")).isTrue();
        assertThat(actual.contains("doctor")).isTrue();
        assertThat(actual.contains("fine")).isTrue();
        assertThat(actual.contains("finest")).isTrue();
        assertThat(actual.contains("figure")).isTrue();
        assertThat(actual.contains("pick")).isTrue();
        assertThat(actual.contains("pickle")).isTrue();
        assertThat(actual.contains("picture")).isTrue();

    }

    @Test
    void should_load_the_dictionary_when_the_file_is_not_empty() throws IOException {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR);
        final Path dictionaryPath = Paths.get("src/test/resources/words.txt");

        // [Act]
        long start = System.nanoTime();
        Files.lines(dictionaryPath, UTF_8).forEach(trie::add);
        long end = System.nanoTime();
        System.out.println("Dictionary loaded in " + (end - start) / 1000000 + "ms");

        // [Assert] that the shortest word is present.
        start = System.nanoTime();
        final boolean actualShortestWord = trie.contains("Z");
        end = System.nanoTime();
        System.out.println("Shortest word found in " + (end - start) / 1000 + "µs");
        assertThat(actualShortestWord).isTrue();

        // [Assert] that the longest word is present.
        start = System.nanoTime();
        final boolean actualLongestWord = trie.contains("trinitrophenylmethylnitramine");
        end = System.nanoTime();
        System.out.println("Longest word found in " + (end - start) / 1000 + "µs");
        assertThat(actualLongestWord).isTrue();

    }

}