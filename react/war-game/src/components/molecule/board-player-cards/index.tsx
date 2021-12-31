import React from "react";
import Card from "../../../domain/Card";
import Player from "../../../domain/Player";
import "./index.css";

interface Props {
    player: Player;
    setPlayer: Function;
    canPlay: boolean;
}

export default function BoardPlayerCards({player, setPlayer, canPlay}: Props) {

    const name: string = player.getName();
    const cards: Card[] = player.getCards();

    const handleClick = () => {
        if (!canPlay) return;
        const playerHasPlayed: Player = player.play();
        setPlayer(playerHasPlayed);
    }

    const styles: string = "board-player-cards " + (canPlay ? "clickable" : "no-pointer-events");

    return <div className={styles} onClick={handleClick}>
        {cards && cards.length > 0 && cards.map((card, index) => {
            return <img key={name + "-" + index} src="/card.png" alt="Deck" title="Deck"/>;
        })}
    </div>

}