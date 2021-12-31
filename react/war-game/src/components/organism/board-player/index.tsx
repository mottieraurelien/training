import React from "react";
import Player from "../../../domain/Player";
import BoardPlayerProfile from "../../molecule/board-player-profile";
import BoardPlayerCards from "../../molecule/board-player-cards";
import "./index.css";

interface Props {
    player: Player;
    setPlayer: Function;
    canPlay: boolean;
}

export default function BoardPlayer({player, setPlayer, canPlay}: Props) {
    return <div className="board-player">
        <BoardPlayerProfile player={player}/>
        <BoardPlayerCards player={player} setPlayer={setPlayer} canPlay={canPlay}/>
    </div>
}