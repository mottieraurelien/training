package aurelienmottier.bowling.game;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;

public enum Shot {

    ZERO("0", true, false, false, 0),
    ONE("1", true, false, false, 1),
    TWO("2", true, false, false, 2),
    THREE("3", true, false, false, 3),
    FOUR("4", true, false, false, 4),
    FIVE("5", true, false, false, 5),
    SIX("6", true, false, false, 6),
    SEVEN("7", true, false, false, 7),
    EIGHT("8", true, false, false, 8),
    NINE("9", true, false, false, 9),
    SPARE("/", false, true, false, null),
    STRIKE("X", false, false, true, null);

    /**
     * To not clone the backing array every time (enum.values()), we store
     * everything once in a map (or could have been set as well).
     */
    private static final Map<String, Shot> SHOT_TYPES = of(Shot.values())
            .collect(toMap(Shot::getLabel, identity()));

    private final String label;
    private final boolean isRegular;
    private final boolean isSpare;
    private final boolean isStrike;
    private final Integer points;

    Shot(final String label, final boolean isRegular, final boolean isSpare, final boolean isStrike, final Integer points) {
        this.label = label;
        this.isRegular = isRegular;
        this.isSpare = isSpare;
        this.isStrike = isStrike;
        this.points = points;
    }

    public static Shot from(final String label) {
        return SHOT_TYPES.get(label);
    }

    public String getLabel() {
        return this.label;
    }

    public boolean isRegular() {
        return this.isRegular;
    }

    public boolean isSpare() {
        return this.isSpare;
    }

    public boolean isStrike() {
        return this.isStrike;
    }

    public Integer getPoints() {
        return this.points;
    }

}