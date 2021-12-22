import Card from "./Card";

export default class Player {

    private readonly name: string;
    private readonly cards: Card[];

    constructor(name: string) {
        this.name = name;
        this.cards = [];
    }

    getName(): string {
        return this.name;
    }

    getCards(): Card[] {
        return this.cards;
    }

    receive(card: Card): void {
        this.cards.push(card);
    }

    /**
     * The player plays the card on top of his deck.
     *
     * "SHIFT" means REMOVING the last card from the top of the deck.
     */
    play(): Card {
        const card: Card | undefined = this.cards.shift();
        if (!card) throw new Error("The player cannot play since he has already lost.");
        return card;
    }

    /**
     * The player adds the winning cards at the bottom of his deck.
     *
     * "UNSHIFT" means ADDING the cards from the bottom of the deck so that the player
     * does not play the cards he just won... #cheater
     */
    win(cards: Card[]): void {
        // unshift > to add at the beginning.
    }

    getNumberOfCards(): number {
        return this.cards.length;
    }

}