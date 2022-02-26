import React, {useEffect, useState} from "react";
import Battle from "../domain/Battle";
import Player from "../domain/Player";
import Card from "../domain/Card";

export default function useEffectOnQuickWinner(battle: Battle | undefined,
                                               quickWinner: Player | undefined, setQuickWinner: Function) {

    const [battleWinner, setBattleWinner] = useState<Player>();

    useEffect(() => {

        // We don't things to get too fast (it's nice when we can see who is going to win).
        const timer = setTimeout(() => {

            // If there is a quick winner then he wins the pot :
            if (battle && quickWinner) {
                const cards: Card[] = battle.getPot();
                quickWinner.win(cards);
                setQuickWinner(undefined);
            }

            // TODO : what if no quick winner ?

        }, 1000);

        return () => {
            clearTimeout(timer);
        };

    }, [battle, quickWinner])

    return {battleWinner, setBattleWinner};

}