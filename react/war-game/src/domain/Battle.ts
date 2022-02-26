import Player from "./Player";
import Card from "./Card";

interface BattleInterface {
    playerOne: Player;
    playerTwo: Player;
    pot?: Card[];
}

export default class Battle {

    private readonly playerOne: Player;
    private readonly playerTwo: Player;
    private readonly pot: Card[];

    constructor({playerOne, playerTwo, pot}: BattleInterface) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.pot = pot || [];
    }

    getPot(): Card[] {
        return this.pot;
    }

    fight(): Player | undefined {

        // During a round, both players play their first card.
        const cardFromPlayerOne: Card | undefined = this.playerOne.getLastPlayedCard();
        const cardFromPlayerTwo: Card | undefined = this.playerTwo.getLastPlayedCard();

        // Checks that both players played :
        if (!cardFromPlayerOne || !cardFromPlayerTwo) throw new Error("An error occurred : both players should have played.");

        // Both players put their card in the pot :
        this.pot.push(cardFromPlayerOne);
        this.pot.push(cardFromPlayerTwo);

        // The battle may already have a quick winner :
        if (cardFromPlayerOne.isGreaterThan(cardFromPlayerTwo)) return this.playerOne;
        if (cardFromPlayerTwo.isGreaterThan(cardFromPlayerOne)) return this.playerTwo;

        // Otherwise, it means they played cards with the same rank, so a long battle needs to start now!

    }


}