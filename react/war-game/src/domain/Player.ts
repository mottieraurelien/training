import Card from "./Card";

interface PlayerInterface {
    name: string;
    cards?: Card[];
    lastPlayedCard?: Card;
}

export default class Player {

    private readonly name: string;
    private readonly cards: Card[];
    private readonly lastPlayedCard?: Card;

    constructor({name, cards, lastPlayedCard}: PlayerInterface) {
        this.name = name;
        this.cards = cards || [];
        this.lastPlayedCard = lastPlayedCard;
    }

    getName(): string {
        return this.name;
    }

    getCards(): Card[] {
        return this.cards;
    }

    getLastPlayedCard(): Card | undefined {
        return this.lastPlayedCard;
    }

    hasNotPlayedYet(): boolean {
        return !this.lastPlayedCard;
    }

    receive(card: Card): void {
        this.cards.push(card);
    }

    /**
     * The player plays the card on top of his deck.
     *
     * "SHIFT" means REMOVING the last card from the top of the deck.
     */
    play(): Player {
        const card: Card | undefined = this.cards.shift();
        if (!card) throw new Error("The player cannot play since he has already lost.");
        const lastPlayedCard = card;
        return new Player({...this, lastPlayedCard});
    }

    /**
     * The player adds the winning cards at the bottom of his deck.
     *
     * "UNSHIFT" means ADDING the cards from the bottom of the deck so that the player
     * does not play the cards he just won... #cheater
     */
    win(cards: Card[]): void {
        cards.forEach(card => this.cards.unshift(card));
    }

    getNumberOfCards(): number {
        return this.cards.length;
    }

}