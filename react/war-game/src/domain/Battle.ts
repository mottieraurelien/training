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

    fight() {

        // // During a round, both players play their first card.
        // const cardFromPlayerOne: Card = this.playerOne.getLastPlayedCard();
        // const cardFromPlayerTwo: Card = this.playerTwo.getLastPlayedCard();
        //
        // // Both players put their card in the pot :
        // this.pot.push(cardFromPlayerOne);
        // this.pot.push(cardFromPlayerTwo);
        //
        // // The battle may already have a quick winner :
        // const winningRound: boolean = !cardFromPlayerOne.equals(cardFromPlayerTwo);

    }


}