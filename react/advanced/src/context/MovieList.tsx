import React, {useContext} from "react";
import UserContext from "./UserContext";

export default function MovieList() {

    const context: any = useContext(UserContext);

    return <div>Movie List {context.user.name} </div>
}