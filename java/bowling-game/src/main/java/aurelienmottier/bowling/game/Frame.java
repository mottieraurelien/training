package aurelienmottier.bowling.game;

import static aurelienmottier.bowling.game.Game.MAXIMUM_POINTS_PER_REGULAR_FRAME;

public class Frame {

    private final Shot firstShot;
    private final Shot secondShot;
    private final int numberOfSticksDown;

    public Frame(final Shot... shots) {

        this.firstShot = shots[0];
        this.secondShot = shots.length > 1 ? shots[1] : Shot.ZERO;

        final boolean success = this.firstShot.isStrike() || this.secondShot.isSpare();
        this.numberOfSticksDown = success ? MAXIMUM_POINTS_PER_REGULAR_FRAME : this.firstShot.getPoints() + this.secondShot.getPoints();

    }

    public boolean endedBySpare() {
        return this.secondShot.isSpare();
    }

    public boolean endedByStrike() {
        return this.firstShot.isStrike();
    }

    public int score() {
        return this.numberOfSticksDown;
    }

    public int firstShotPoints() {
        if (this.endedByStrike()) return this.numberOfSticksDown;
        return this.firstShot.getPoints();
    }

}