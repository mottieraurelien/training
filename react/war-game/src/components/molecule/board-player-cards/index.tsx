import React from "react";
import Card from "../../../domain/Card";
import Player from "../../../domain/Player";
import "./index.css";

interface Props {
    player: Player;
}

export default function BoardPlayerCards({player}: Props) {

    const name: string = player.getName();
    const cards: Card[] = player.getCards();

    const handleClick = () => {
        // TODO : play the card and setPlayedCardPlayerOne or setPlayedCardPlayerTwo
        const playedCard = cards && cards[0];
    }

    return <div className="board-player-cards clickable" onClick={handleClick}>
        {cards && cards.length > 0 && cards.map((card, index) => {
            return <img key={name + "-" + index} src="/card.png" alt="Deck" title="Deck"/>;
        })}
    </div>

}