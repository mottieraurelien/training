import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Made from a real exercise given to a candidate from Google.
 * https://www.youtube.com/watch?v=V0xjK_6ZoEY&ab_channel=KeepOnCoding
 */
class PrintTreeTest {

    @Test
    void should_go_deep_first_when_printing_a_tree_that_contains_relations() {

        // [Arrange]
        final List<Relation<String>> relations = asList(
                new Relation<>("animal", "mammal"),
                new Relation<>("animal", "bird"),
                new Relation<>("life form", "animal"),
                new Relation<>("cat", "lion"),
                new Relation<>("mammal", "cat"),
                new Relation<>("animal", "fish")
        );
        final PrintTree<String> tree = new PrintTree<>(relations);

        // [Act]
        final List<String> actual = tree.process();

        // [Assert]
        assertThat(actual).containsExactly(
                "life form",
                " animal",
                "  mammal",
                "   cat",
                "    lion",
                "  bird",
                "  fish"
        );

    }

}
