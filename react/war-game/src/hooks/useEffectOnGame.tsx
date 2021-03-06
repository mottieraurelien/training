import {useEffect, useState} from "react";
import Player from "../domain/Player";
import Game from "../domain/Game";

export default function useEffectOnGame(playerOne: Player, playerTwo: Player) {

    const [game, setGame] = useState<Game>();

    useEffect(() => {
        const game: Game = new Game({playerOne, playerTwo});
        setGame(game);
    }, [])

    return {game, setGame};
}