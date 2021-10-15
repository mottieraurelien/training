package structure;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class TrieTest {

    private static final Pattern PATTERN = Pattern.compile("");
    private static final Function<String, String[]> SPLITERATOR = string -> PATTERN.split(string, 0);

    private static final List<String> SAMPLE_DICTIONARY = asList(
            "boy", "book", "border", "car", "care",
            "cat", "dog", "doctor", "fine", "finest",
            "figure", "pick", "pickle", "picture", "zoo"
    );

    private static final Path FULL_DICTIONARY = get("src/test/resources/words.txt");

    @Test
    void should_load_the_dictionary_when_the_sample_is_not_empty() {

        // [Act]
        final Trie<String> actual = new Trie<>(SPLITERATOR, SAMPLE_DICTIONARY);

        // [Assert]
        assertThat(actual.contains("boy")).isTrue();
        assertThat(actual.contains("book")).isTrue();
        assertThat(actual.contains("border")).isTrue();
        assertThat(actual.contains("car")).isTrue();
        assertThat(actual.contains("care")).isTrue();
        assertThat(actual.contains("cat")).isTrue();
        assertThat(actual.contains("dog")).isTrue();
        assertThat(actual.contains("doctor")).isTrue();
        assertThat(actual.contains("fine")).isTrue();
        assertThat(actual.contains("finest")).isTrue();
        assertThat(actual.contains("figure")).isTrue();
        assertThat(actual.contains("pick")).isTrue();
        assertThat(actual.contains("pickle")).isTrue();
        assertThat(actual.contains("picture")).isTrue();

        // Non-existing word (since not complete, only prefixes) :
        assertThat(actual.contains("c")).isFalse();
        assertThat(actual.contains("ca")).isFalse();
        assertThat(actual.contains("boo")).isFalse();
        assertThat(actual.contains("pictur")).isFalse();
        assertThat(actual.contains("pictionary")).isFalse();

    }

    @Test
    void should_load_the_dictionary_when_the_file_is_not_empty() throws IOException {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR);

        // [Act]
        long start = System.nanoTime();
        Files.lines(FULL_DICTIONARY, UTF_8).forEach(trie::add);
        long end = System.nanoTime();
        System.out.println("Dictionary loaded in " + (end - start) / 1000000 + "ms");

        // [Assert] that the shortest word is present.
        start = System.nanoTime();
        final boolean actualShortestWord = trie.contains("monkey");
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

    @Test
    void should_return_true_when_the_trie_contains_the_word() {

        // [Act]
        final Trie<String> actual = new Trie<>(SPLITERATOR, SAMPLE_DICTIONARY);

        // [Assert]
        assertThat(actual.contains("boy")).isTrue();

    }

    @Test
    void should_return_false_when_the_trie_does_not_contain_the_word() {

        // [Act]
        final Trie<String> actual = new Trie<>(SPLITERATOR, SAMPLE_DICTIONARY);

        // [Assert]
        assertThat(actual.contains("testing")).isFalse();

    }

    @Test
    void should_return_false_when_the_word_is_null() {

        // [Act]
        final Trie<String> actual = new Trie<>(SPLITERATOR, SAMPLE_DICTIONARY);

        // [Assert]
        assertThat(actual.contains(null)).isFalse();

    }

    @Test
    void should_first_print_the_node_value_when_traversing_the_trie_using_pre_order_approach() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, singletonList("testing"));

        // [Act]
        final Collection<String> actual = trie.traversePreOrder();

        // [Assert]
        assertThat(actual).containsExactly("t", "e", "s", "t", "i", "n", "g");

    }

    @Test
    void should_print_the_node_after_traversing_the_nodes_value_when_traversing_the_trie_using_post_order_approach() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, singletonList("testing"));

        // [Act]
        final Collection<String> actual = trie.traversePostOrder();

        // [Assert]
        assertThat(actual).containsExactly("g", "n", "i", "t", "s", "e", "t");

    }

    @Test
    void should_just_un_flag_the_leaf_when_the_last_node_of_the_word_has_children() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, SAMPLE_DICTIONARY);

        // [Act]
        trie.remove("car");

        // [Assert]
        // We just un-flagged {r} as not being a leaf anymore :
        assertThat(trie.contains("car")).isFalse();
        // But we check that we did not remove the node {r} since it has still a child {e} :
        assertThat(trie.contains("care")).isTrue();

    }

    @Test
    void should_remove_physically_the_node_when_the_last_node_of_the_word_has_not_any_children() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, SAMPLE_DICTIONARY);

        // [Act]
        trie.remove("care");

        // [Assert]
        assertThat(trie.contains("car")).isTrue();
        // We physically removed the node {e} since it has no children anymore since
        // we don't have any word in our dictionary that starts with care* :
        assertThat(trie.contains("care")).isFalse();

    }

    @Test
    void should_not_remove_anything_when_removing_null_from_the_trie() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, asList("car", "care"));
        assertThat(trie.contains("car")).isTrue();
        assertThat(trie.contains("care")).isTrue();

        // [Act]
        trie.remove(null);

        // [Assert]
        assertThat(trie.contains("car")).isTrue();
        assertThat(trie.contains("care")).isTrue();

    }

    @Test
    void should_not_remove_anything_when_removing_empty_string_from_the_trie() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, asList("car", "care"));
        assertThat(trie.contains("car")).isTrue();
        assertThat(trie.contains("care")).isTrue();

        // [Act]
        trie.remove("");

        // [Assert]
        assertThat(trie.contains("car")).isTrue();
        assertThat(trie.contains("care")).isTrue();

    }

}