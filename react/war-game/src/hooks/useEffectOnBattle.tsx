import {useEffect, useState} from "react";
import Player from "../domain/Player";
import Battle from "../domain/Battle";

export default function useEffectOnBattle(playerOne: Player, playerTwo: Player) {

    const [battle, setBattle] = useState<Battle>();

    useEffect(() => {
        if (playerOne.hasNotPlayedYet() || playerTwo.hasNotPlayedYet()) return;
        const newBattle: Battle = new Battle({playerOne, playerTwo});
        setBattle(newBattle);
    }, [playerOne, playerTwo])

    return {battle, setBattle};
}