import React from "react";
import {Link, NavLink} from "react-router-dom";

export default function Navbar() {

    /*
     * RENDERING
     */
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">

            <Link className="navbar-brand px-2" to="/">Vidly</Link>
            <button className="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"/>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <div className="navbar-nav mr-auto">
                    <NavLink className="nav-item nav-link" to="/movies">Movies</NavLink>
                    <NavLink className="nav-item nav-link" to="/customers">Customers</NavLink>
                    <NavLink className="nav-item nav-link" to="/rentals">Rentals</NavLink>
                    <NavLink className="nav-item nav-link" to="/login">Login</NavLink>
                    <NavLink className="nav-item nav-link" to="/register">Register</NavLink>
                </div>
            </div>

        </nav>
    );

}