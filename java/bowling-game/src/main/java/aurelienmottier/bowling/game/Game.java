package aurelienmottier.bowling.game;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * I also could have used pointers to compute the total, but I wanted to keep my solution very clear.
 * The main purpose of this kata was to practice TDD.
 */
public class Game {

    static final Integer MAXIMUM_POINTS_PER_REGULAR_FRAME = 10;
    private static final Pattern SPLITTER = compile("");

    private int total;
    private int bonus;

    public Game(final String dashboard) {
        final List<Frame> frames = this.split(dashboard);
        this.computeTotalFrom(frames);
    }

    public int total() {
        return this.total;
    }

    private List<Frame> split(final String sequenceOfShots) {

        final List<Frame> frames = new ArrayList<>();

        final String[] shots = SPLITTER.split(sequenceOfShots);

        Shot previousShot = null;

        int frameCounter = 0;
        Frame frame = null;

        for (final String shotLabel : shots) {
            final Shot shot = Shot.from(shotLabel);
            if (previousShot != null && previousShot.isRegular() && !shot.isStrike()) {
                frame = new Frame(previousShot, shot);
                frames.add(frame);
                frameCounter++;
                previousShot = null;
            } else if (shot.isStrike()) {
                frame = new Frame(shot);
                frames.add(frame);
                frameCounter++;
                previousShot = null;
            } else {
                previousShot = shot;
            }

            if (frameCounter == 10 && previousShot != null)
                this.bonus = shot.getPoints();

        }

        return frames;

    }

    private void computeTotalFrom(final List<Frame> frames) {

        for (int i = 0; i < frames.size(); i++) {

            final Frame frame = frames.get(i);
            final Frame nextFrame = i < frames.size() - 1 ? frames.get(i + 1) : null;

            final int strikeBonus = frame.endedByStrike() && nextFrame != null ? nextFrame.score() : 0;
            final int spareBonus = frame.endedBySpare() && nextFrame != null ? nextFrame.firstShotPoints() : 0;

            this.total += frame.score() + strikeBonus + spareBonus;

        }

        this.total += this.bonus;

    }

}