import React, {useState} from 'react';
import Player from "../../../domain/Player";
import BoardPlayer from "../../organism/board-player";
import BoardBattlefield from "../../organism/board-battlefield";
import useEffectOnGame from "../../../hooks/useEffectOnGame";
import useEffectOnPlayers from "../../../hooks/useEffectOnPlayers";
import useEffectOnBattle from "../../../hooks/useEffectOnBattle";
import useEffectOnQuickWinner from "../../../hooks/useEffectOnQuickWinner";
import "./index.css";

export default function Board() {

    const [playerOne, setPlayerOne] = useState<Player>(new Player({name: "Bubee"}));
    const [playerTwo, setPlayerTwo] = useState<Player>(new Player({name: "Momo"}));

    const {game, setGame} = useEffectOnGame(playerOne, playerTwo);
    const {battle, setBattle} = useEffectOnPlayers(playerOne, playerTwo);
    const {quickWinner, setQuickWinner} = useEffectOnBattle(battle, setBattle);
    const {battleWinner, setBattleWinner} = useEffectOnQuickWinner(battle, quickWinner, setQuickWinner);

    return <div className="board">
        <BoardPlayer player={playerOne} setPlayer={setPlayerOne} canPlay={!battle}/>
        <BoardBattlefield game={game} setGame={setGame} playerOne={playerOne} playerTwo={playerTwo}/>
        <BoardPlayer player={playerTwo} setPlayer={setPlayerTwo} canPlay={!battle}/>
    </div>;

}