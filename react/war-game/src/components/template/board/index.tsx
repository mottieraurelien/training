import React, {useState} from 'react';
import Player from "../../../domain/Player";
import BoardPlayer from "../../organism/board-player";
import useEffectOnGame from "../../../hooks/useEffectOnGame";
import BoardBattlefield from "../../organism/board-battlefield";
import useEffectOnPlayers from "../../../hooks/useEffectOnPlayers";
import "./index.css";

export default function Board() {

    const [playerOne, setPlayerOne] = useState<Player>(new Player({name: "Bubee"}));
    const [playerTwo, setPlayerTwo] = useState<Player>(new Player({name: "Momo"}));

    const {game, setGame} = useEffectOnGame(playerOne, playerTwo);
    const {battle, setBattle} = useEffectOnPlayers(playerOne, playerTwo);

    return <div className="board">
        <BoardPlayer player={playerOne} setPlayer={setPlayerOne} canPlay={!battle}/>
        <BoardBattlefield game={game} setGame={setGame} playerOne={playerOne} playerTwo={playerTwo}/>
        <BoardPlayer player={playerTwo} setPlayer={setPlayerTwo} canPlay={!battle}/>
    </div>;

}