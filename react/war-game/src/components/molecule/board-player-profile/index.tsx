import React from "react";
import Player from "../../../domain/Player";
import "./index.css";

interface Props {
    player: Player;
}

export default function BoardPlayerProfile({player}: Props) {

    const image: string = "/" + player.getName() + ".png";

    return <div className="board-player-profile">
        <img src={image} alt={player.getName()} title={player.getName()}/>
    </div>

}