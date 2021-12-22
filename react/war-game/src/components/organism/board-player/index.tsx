import React from "react";
import Player from "../../../domain/Player";
import BoardPlayerProfile from "../../molecule/board-player-profile";
import BoardPlayerCards from "../../molecule/board-player-cards";
import "./index.css";

interface Props {
    player: Player;
}

export default function BoardPlayer({player}: Props) {
    return <div className="board-player">
        <BoardPlayerProfile player={player}/>
        <BoardPlayerCards player={player}/>
    </div>
}