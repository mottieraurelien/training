import React from "react";
import UserContext from "./context/UserContext";
import MoviePage from "./context/MoviePageClass";

export default class App extends React.Component {

    state = {
        user: {name: "John"},
        theme: "dark",
        language: "en"
    };

    render() {
        // Our App component provides the UserContext to the MoviePage
        // components (tree) and all its children, even deep in the tree :
        return <UserContext.Provider value={this.state}>
            <MoviePage/>
        </UserContext.Provider>;
    }

}