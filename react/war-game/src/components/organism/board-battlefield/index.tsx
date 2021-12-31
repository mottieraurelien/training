import React, {CSSProperties, FormEvent} from "react";
import BoardBattlefieldPlayedCard from "../../molecule/board-battlefield-played-card";
import Game from "../../../domain/Game";
import Player from "../../../domain/Player";
import "./index.css";

interface Props {
    game: Game | undefined;
    setGame: Function;
    playerOne: Player;
    playerTwo: Player;
}

export default function BoardBattlefield({game, setGame, playerOne, playerTwo}: Props) {

    const hasStarted: boolean = !!game && game.hasStarted();
    const styles: CSSProperties = hasStarted ? {height: "150px"} : {height: "50px"};
    const stylesCarpet: CSSProperties = {backgroundImage: "url(/carpet.jpg)"};

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (!game) return;
        const preparedGame: Game = game.prepare();
        setGame(preparedGame);
    }

    return <div className="board-battlefield" style={styles}>

        {!hasStarted && <form onSubmit={handleSubmit}>
            <input type="submit" value="Play" disabled={!game}/>
        </form>}

        {hasStarted && <div className="board-battlefield-started" style={stylesCarpet}>
            <BoardBattlefieldPlayedCard card={playerOne.getLastPlayedCard()}/>
            <BoardBattlefieldPlayedCard card={playerTwo.getLastPlayedCard()}/>
        </div>}

    </div>;

}