import Player from "./Player";
import {suits} from "./Suit";
import {ranks} from "./Rank";
import Card from "./Card";
import Stack from "../structure/Stack";
import Battle from "./Battle";

interface GameInterface {
    playerOne: Player;
    playerTwo: Player;
    players?: Player[];
    started?: boolean;
}

export default class Game {

    private readonly playerOne: Player;
    private readonly playerTwo: Player;
    private readonly players: Player[];

    private started: boolean;

    constructor({playerOne, playerTwo, players, started}: GameInterface) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.players = players || [this.playerOne, this.playerTwo];
        this.started = started || false;
    }

    hasStarted(): boolean {
        return this.started;
    }

    prepare(): Game {
        const cards = Game.create();
        this.distribute(cards);
        const started = true;
        return new Game({...this, started});
    }

    private static create(): Card[] {
        const deck: Card[] = [];
        for (const suit of suits) {
            for (const rank of ranks) {
                const card: Card = new Card(suit, rank);
                deck.push(card);
            }
        }
        return deck;
    }

    private distribute(cards: Card[]): void {
        const shuffledCard: Card[] = cards.sort(() => 0.5 - Math.random());
        const deck: Stack<Card> = new Stack<Card>(shuffledCard);
        while (deck.isNotEmpty()) {
            this.players.forEach(player => player.receive(deck.pop()));
        }
    }

    battle(): void {

        // Both players will face each other :
        const battle: Battle = new Battle(this.playerOne, this.playerTwo);

        // The first round can now start :
        // TODO

        // Let's check if we have a quick winner :
        // TODO

        // Otherwise, they need to start the and keep playing as long as there is no winner :
        // TODO

    }

}