package structure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.String.join;
import static java.lang.System.nanoTime;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.regex.Pattern.compile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

/**
 * Reference laptop : Intel 7300HQ (4C/4T) + 16GB of RAM (2400Mhz) + low end SSD 120GB (SATAIII).
 */
class TrieTest {

    private static final Function<Long, Long> FROM_NANOSECOND_TO_MICROSECOND = duration -> duration / 1000;
    private static final Function<Long, Long> FROM_NANOSECOND_TO_SECOND = duration -> duration / 1000000000;

    private static final Pattern PATTERN = compile("");
    private static final Function<String[], String> JOINER = strings -> join("", strings);
    private static final Function<String, String[]> SPLITERATOR = string -> PATTERN.split(string, 0);

    private static final List<String> SAMPLE_DICTIONARY = asList(
            "boy", "book", "border", "car", "care",
            "cat", "dog", "doctor", "fine", "finest",
            "figure", "pick", "pickle", "picture", "zoo"
    );

    private static final Trie<String> LARGE_TRIE = new Trie<>(SPLITERATOR, JOINER);
    private static final Path FULL_DICTIONARY = get("src/test/resources/words.txt");

    @BeforeAll
    public static void loadTheCompleteEnglishDictionaryThatContainsThreeHundredSeventyThousandsWords() throws IOException {
        final long start = nanoTime();
        Files.lines(FULL_DICTIONARY, UTF_8).forEach(LARGE_TRIE::add);
        final long end = nanoTime();
        final Long actualDurationInSecond = FROM_NANOSECOND_TO_SECOND.apply(end - start);
        assertThat(actualDurationInSecond).isLessThan(1);
    }

    @Test
    void should_load_the_dictionary_when_the_sample_is_not_empty() {

        // [Act]
        final Trie<String> actual = new Trie<>(SPLITERATOR, JOINER, SAMPLE_DICTIONARY);

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
    void should_load_the_dictionary_when_the_file_is_not_empty() {

        // [Arrange / Act]
        // Nothing since the large trie is built before all unit tests, meaning only one.

        // [Assert] that the shortest word is present.
        long start = nanoTime();
        final boolean actualFirstShortestWord = LARGE_TRIE.contains("a");
        long end = nanoTime();
        assertThat(actualFirstShortestWord).isTrue();
        Long actualDurationInMicrosecond = FROM_NANOSECOND_TO_MICROSECOND.apply(end - start);
        assertThat(actualDurationInMicrosecond).isLessThan(100);

        // [Assert] that the longest word is present.
        start = nanoTime();
        final boolean actualUniqueLongestWord = LARGE_TRIE.contains("dichlorodiphenyltrichloroethane");
        end = nanoTime();
        assertThat(actualUniqueLongestWord).isTrue();
        actualDurationInMicrosecond = FROM_NANOSECOND_TO_MICROSECOND.apply(end - start);
        assertThat(actualDurationInMicrosecond).isLessThan(100);

    }

    @Test
    void should_return_true_when_the_trie_contains_the_word() {

        // [Act]
        final Trie<String> actual = new Trie<>(SPLITERATOR, JOINER, SAMPLE_DICTIONARY);

        // [Assert]
        assertThat(actual.contains("boy")).isTrue();

    }

    @Test
    void should_return_false_when_the_trie_does_not_contain_the_word() {

        // [Act]
        final Trie<String> actual = new Trie<>(SPLITERATOR, JOINER, SAMPLE_DICTIONARY);

        // [Assert]
        assertThat(actual.contains("testing")).isFalse();

    }

    @Test
    void should_return_false_when_the_word_is_null() {

        // [Act]
        final Trie<String> actual = new Trie<>(SPLITERATOR, JOINER, SAMPLE_DICTIONARY);

        // [Assert]
        assertThat(actual.contains(null)).isFalse();

    }

    @Test
    void should_first_print_the_node_value_when_traversing_the_trie_using_pre_order_approach() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, singletonList("testing"));

        // [Act]
        final Collection<String> actual = trie.traversePreOrder();

        // [Assert]
        assertThat(actual).containsExactly("t", "e", "s", "t", "i", "n", "g");

    }

    @Test
    void should_print_the_node_after_traversing_the_nodes_value_when_traversing_the_trie_using_post_order_approach() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, singletonList("testing"));

        // [Act]
        final Collection<String> actual = trie.traversePostOrder();

        // [Assert]
        assertThat(actual).containsExactly("g", "n", "i", "t", "s", "e", "t");

    }

    @Test
    void should_just_un_flag_the_leaf_when_the_last_node_of_the_word_has_children() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, SAMPLE_DICTIONARY);

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
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, SAMPLE_DICTIONARY);

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
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, asList("car", "care"));
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
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, asList("car", "care"));
        assertThat(trie.contains("car")).isTrue();
        assertThat(trie.contains("care")).isTrue();

        // [Act]
        trie.remove("");

        // [Assert]
        assertThat(trie.contains("car")).isTrue();
        assertThat(trie.contains("care")).isTrue();

    }

    @Test
    void should_not_return_any_suggestion_when_the_word_is_null() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, asList("bi", "book", "bonjour", "boy", "car", "care"));

        // [Act]
        final Collection<String> actual = trie.suggestions(null, 2);

        // [Assert]
        assertThat(actual).isEmpty();

    }

    @Test
    void should_not_return_any_suggestion_when_the_word_empty() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, asList("bi", "book", "bonjour", "boy", "car", "care"));

        // [Act]
        final Collection<String> actual = trie.suggestions("", 2);

        // [Assert]
        assertThat(actual).isEmpty();

    }

    @Test
    void should_not_return_any_suggestion_when_the_word_is_a_leaf() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, asList("bi", "book", "bonjour", "boy", "car", "care"));

        // [Act]
        final Collection<String> actual = trie.suggestions("care", 2);

        // [Assert]
        assertThat(actual).isEmpty();

    }

    @Test
    void should_return_the_expected_number_of_suggestion_when_the_word_is_not_a_leaf() {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, asList("bi", "bonjour", "book", "boy", "car", "care"));

        // [Act]
        final Collection<String> actual = trie.suggestions("bo", 2);

        // [Assert] that actual contains only two suggestions (not "boy" since we asked maximum 2 suggestions).
        assertThat(actual).containsExactly("bonjour", "book");

    }

    @Test
    void should_return_the_expected_number_of_suggestion_when_the_dictionary_contains_a_lot_of_matching_words() {

        // [Act]
        final int maximumNumberOfSuggestions = 8;
        final long start = nanoTime();
        final Collection<String> actual = LARGE_TRIE.suggestions("boa", maximumNumberOfSuggestions);
        final long end = nanoTime();

        // [Assert]
        assertThat(actual).containsExactly("boa", "boaedon", "boagane", "boanbura", "boanergean", "boanerges", "boanergism", "boanthropy");
        final Long actualDuration = FROM_NANOSECOND_TO_MICROSECOND.apply(end - start);
        assertThat(actualDuration).isLessThan(500);

    }

    @Test
    void should_return_update_the_statistics_when_adding_a_new_word() {

        // [Act]
        final int actualNumberOfWords = LARGE_TRIE.getNumberOfEnds();
        final int actualNumberOfLetters = LARGE_TRIE.getNumberOfNodes();

        // [Assert]
        assertThat(actualNumberOfWords).isEqualTo(370103);
        // Some letters are not the end of words that's why we have more than three million and half nodes.
        assertThat(actualNumberOfLetters).isEqualTo(3494697);

    }

    @ParameterizedTest
    @MethodSource("scenarios")
    void should_find_the_longest_common_prefix_when_providing_valid_and_invalid_words(final Iterable<String> inputs,
                                                                                      final String expectedLongestPrefix) {

        // [Arrange]
        final Trie<String> trie = new Trie<>(SPLITERATOR, JOINER, inputs);

        // [Act]
        final String actualLongestPrefix = trie.findLongestCommonPrefix(new String[0]);

        // [Assert]
        assertThat(actualLongestPrefix).isEqualTo(expectedLongestPrefix);

    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenarios() {
        return Stream.of(
                of(asList("card", "care"), "car"),
                of(asList("car", "care"), "car"),
                of(singletonList("car"), "car"),
                of(asList("car", "dog"), ""),
                of(asList("boanbura", "boanergean", "boanergism"), "boan")
        );
    }

}