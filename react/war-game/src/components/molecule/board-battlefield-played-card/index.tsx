import React from "react";
import Card from "../../../domain/Card";
import "./index.css";

interface Props {
    card: Card | undefined;
}

export default function BoardBattlefieldPlayedCard({card}: Props) {

    if (!card) return null;

    const label: string = card.getRank() + " of " + card.getSuit();

    return <div className="board-battlefield-played-card no-pointer-events">
        <img style={card.getStyles()} alt={label} title={label}/>
    </div>

}