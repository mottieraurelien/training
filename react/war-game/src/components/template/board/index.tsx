import React, {useMemo, useState} from 'react';
import Player from "../../../domain/Player";
import BoardPlayer from "../../organism/board-player";
import useEffectOnGame from "../../../hooks/useEffectOnGame";
import BoardBattlefield from "../../organism/board-battlefield";
import "./index.css";

export default function Board() {

    const [playerOne, setPlayerOne] = useState<Player>(new Player("Bubee"));
    const [playerTwo, setPlayerTwo] = useState<Player>(new Player("Momo"));

    const {game, setGame} = useEffectOnGame(playerOne, playerTwo);

    const numberOfCardsPlayerOne: number = useMemo(() => playerOne && playerOne.getNumberOfCards(), [playerOne]);
    const numberOfCardsPlayerTwo: number = useMemo(() => playerTwo && playerTwo.getNumberOfCards(), [playerTwo]);

    return <div className="board">
        <BoardPlayer player={playerOne}/>
        <BoardBattlefield game={game} setGame={setGame}/>
        <BoardPlayer player={playerTwo}/>
    </div>;

}