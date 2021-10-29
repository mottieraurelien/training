package aurelienmottier.bowling.game;

import java.util.regex.Pattern;

import static aurelienmottier.bowling.game.Shot.from;
import static java.util.regex.Pattern.compile;

/**
 * I also could have used pointers to compute the total, but I wanted to keep my solution very clear.
 * The main purpose of this kata was to practice TDD.
 */
public class Game {

    static final Integer MAXIMUM_POINTS_PER_REGULAR_FRAME = 10;
    private static final Pattern SPLITTER = compile("");

    private int total;

    public Game(final String shotsSequence) {
        this.compute(shotsSequence);
    }

    public int total() {
        return this.total;
    }

    /**
     * Time complexity is O(n).
     */
    private void compute(final String shotsSequence) {

        final String[] shots = SPLITTER.split(shotsSequence);

        Shot previousShot = null;
        Frame previousFrame = null;

        for (final String shotLabel : shots) {

            final Shot shot = from(shotLabel);

            Frame frame = null;

            if (shot.isStrike())
                frame = new Frame(shot);

            if (shot.isSpare() || (previousShot != null && previousShot.isRegular() && shot.isRegular()))
                frame = new Frame(previousShot, shot);

            if (frame == null) {
                previousShot = shot;
                continue;
            }

            this.updateTotal(previousFrame, frame);

            previousShot = null;
            previousFrame = frame;

        }

        if (previousShot != null)
            this.total += previousShot.getPoints();

    }

    private void updateTotal(final Frame previousFrame, final Frame frame) {
        final int strikeBonus = previousFrame != null && previousFrame.endedByStrike() ? frame.score() : 0;
        final int spareBonus = previousFrame != null && previousFrame.endedBySpare() ? frame.firstShotPoints() : 0;
        this.total += frame.score() + strikeBonus + spareBonus;
    }

}