import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;

public class ChangeDateFormat {

    private static final DateTimeFormatter YYYY_MM_DD = ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter DD_MM_YYYY = ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter MM_DD_YYYY = ofPattern("MM-dd-yyyy");

    private static final DateTimeFormatter TARGETED_FORMAT = ofPattern("yyyyMMdd");

    private final List<String> dates;

    public ChangeDateFormat(final List<String> dates) {
        this.dates = dates;
    }

    public List<String> apply() {
        return this.dates.stream()
                .map(ChangeDateFormat::parse)
                .filter(Objects::nonNull)
                .map(TARGETED_FORMAT::format)
                .collect(toList());
    }

    private static LocalDate parse(final String date) {

        final LocalDate fromFirstFormat = format(date, YYYY_MM_DD);
        if (fromFirstFormat != null) return fromFirstFormat;

        final LocalDate fromSecondFormat = format(date, DD_MM_YYYY);
        if (fromSecondFormat != null) return fromSecondFormat;

        return format(date, MM_DD_YYYY);

    }

    private static LocalDate format(final String date, final DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(date, formatter);
        } catch (final RuntimeException e) {
            return null;
        }
    }

}