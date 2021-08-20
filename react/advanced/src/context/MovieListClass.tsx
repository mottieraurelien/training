import React, {Component} from "react";
import UserContext from "./UserContext";

export default class MovieList extends Component {

    render() {
        return <UserContext.Consumer>
            {context =>
                <div>
                    Movie List {context.user.name}
                </div>
            }
        </UserContext.Consumer>
    }

}