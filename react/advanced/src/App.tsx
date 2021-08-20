import React, {useState} from "react";
import MoviePage from "./context/MoviePage";
import UserContext from "./context/UserContext";

export default function App() {

    const [user] = useState({name: "John"});
    const [theme] = useState("dark");
    const [language] = useState("en");

    return <UserContext.Provider value={{user, theme, language}}>
        <MoviePage/>
    </UserContext.Provider>

}