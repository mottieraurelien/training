import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class ChangeDateFormatTest {

    @ParameterizedTest
    @MethodSource("scenarios")
    void should_ignore_the_wrong_dates_when_providing_a_list_of_mixed_dates(final List<String> dates, final List<String> expected) {

        // [Arrange]
        final ChangeDateFormat changeDateFormat = new ChangeDateFormat(dates);

        // [Act]
        final List<String> actual = changeDateFormat.apply();

        // [Assert]
        assertThat(actual.size()).isEqualTo(expected.size());
        assertThat(actual).containsAll(expected);

    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> scenarios() {
        return Stream.of(
                of(
                        List.of("2010/03/30", "15/12/2016", "11-15-2012", "20130720"), List.of("20100330", "20161215", "20121115")
                )
        );
    }

}