import React, {CSSProperties, FormEvent} from "react";
import Game from "../../../domain/Game";
import "./index.css";

interface Props {
    game: Game | undefined;
    setGame: Function;
}

export default function BoardBattlefield({game, setGame}: Props) {

    const hasStarted: boolean = !!game && game.hasStarted();
    const styles: CSSProperties = hasStarted ? {height: "150px"} : {height: "50px"};
    const stylesCarpet: CSSProperties = {backgroundImage: "url(/carpet.jpg)"};

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (!game) return;
        const newGame: Game = game.prepare();
        setGame(newGame);
    }

    return <div className="board-battlefield" style={styles}>

        {!hasStarted && <form onSubmit={handleSubmit}>
            <input type="submit" value="Play" disabled={!game}/>
        </form>}

        {hasStarted && <div className="board-battlefield-started" style={stylesCarpet}>

        </div>}

    </div>;

}