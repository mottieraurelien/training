import React from "react";
import auth from "../../services/authService";

export default class Logout extends React.Component {

    componentDidMount() {
        auth.logout();
        window.location.href = "/";
    }

    // This Logout component is a renderless component.
    render() {
        return null;
    }

}