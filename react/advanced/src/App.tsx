import React from "react";
import Movie from "./hoc/Movie";
import Counter from "./components/counter";
import Users from "./components/users";

export default function App() {
    return <React.Fragment>
        <Movie id={1}/>
        <Counter/>
        <Users/>
    </React.Fragment>
}