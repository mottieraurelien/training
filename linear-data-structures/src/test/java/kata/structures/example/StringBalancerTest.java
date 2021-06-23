package kata.structures.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringBalancerTest {

    @ParameterizedTest
    @ValueSource(strings = {"(1 + 2)", "((1 + 2))", "(([1] + 2))", "(([1] + {2}))", "(([1] + {2}))[1]"})
    void should_return_true_when_a_string_expression_is_balanced(final String inputString) {

        // [Arrange]
        final StringBalancer balancer = new StringBalancer();

        // [Act]
        final boolean actual = balancer.isBalanced(inputString);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @ParameterizedTest
    @ValueSource(strings = {"(1 + 2>", ")1 + 2("})
    void should_return_false_when_a_string_expression_is_not_balanced(final String inputString) {

        // [Arrange]
        final StringBalancer balancer = new StringBalancer();

        // [Act]
        final boolean actual = balancer.isBalanced(inputString);

        // [Assert]
        assertThat(actual).isFalse();

    }

}