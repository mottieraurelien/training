import Player from "./Player";
import Card from "./Card";

export default class Battle {

    private readonly playerOne: Player;
    private readonly playerTwo: Player;
    private readonly pot: Card[];

    constructor(playerOne: Player, playerTwo: Player) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.pot = [];
    }

    fight() {

        // During a round, both players play their first card.
        const cardFromPlayerOne: Card = this.playerOne.play();
        const cardFromPlayerTwo: Card = this.playerTwo.play();

        // Both players put their card in the pot :
        this.pot.push(cardFromPlayerOne);
        this.pot.push(cardFromPlayerTwo);

        // The battle may already have a quick winner :
        const winningRound: boolean = !cardFromPlayerOne.equals(cardFromPlayerTwo);

    }


}