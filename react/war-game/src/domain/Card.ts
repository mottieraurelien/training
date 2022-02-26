import {Suit} from "./Suit";
import {Rank} from "./Rank";
import {CSSProperties} from "react";

export default class Card {

    private readonly suit: Suit;
    private readonly rank: Rank;
    private readonly styles: CSSProperties;

    constructor(suit: Suit, rank: Rank) {
        this.suit = suit;
        this.rank = rank;
        this.styles = this.buildStyles();
    }

    buildStyles(): CSSProperties {
        let content = "url(/cards/";
        if (this.rank <= 10) content += this.rank;
        else content += Rank[this.rank].toLowerCase();
        content += "_of_" + Suit[this.suit].toLowerCase() + ".png)";
        return {content};
    }

    getSuit(): Suit {
        return this.suit;
    }

    getRank(): Rank {
        return this.rank;
    }

    getStyles(): CSSProperties {
        return this.styles;
    }

    /**
     * When comparing two cards, we only focus on their rank.
     */
    isGreaterThan(anotherCard: Card): boolean {
        const currentRank: number = this.rank;
        const anotherRank: number = anotherCard.rank;
        return currentRank > anotherRank;
    }

}