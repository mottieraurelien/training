import {Suit} from "./Suit";
import {Rank} from "./Rank";

export default class Card {

    private readonly suit: Suit;
    private readonly rank: Rank;

    constructor(suit: Suit, rank: Rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * When comparing two cards, we only focus on their rank.
     */
    equals(anotherCard: Card): boolean {
        return this.rank !== anotherCard.rank;
    }

}