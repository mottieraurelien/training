import React, {useEffect, useState} from "react";
import Battle from "../domain/Battle";
import Player from "../domain/Player";

export default function useEffectOnBattle(battle: Battle | undefined, setBattle: Function) {

    const [quickWinner, setQuickWinner] = useState<Player>();

    useEffect(() => {

        // We don't things to get too fast (it's nice when we can see who is going to win).
        const timer = setTimeout(() => {
            if (!battle) return;
            const winner: Player | undefined = battle.fight();
            if (winner) {
                setQuickWinner(winner);
                setBattle(undefined);
            }
        }, 1000);

        return () => {
            clearTimeout(timer);
        };

    }, [battle])

    return {quickWinner, setQuickWinner};

}