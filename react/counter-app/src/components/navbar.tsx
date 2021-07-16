import React from "react";

export default function Navbar({...props}) {

    const {total} = props;

    /*
     * RENDERING
     */
    return (
        <nav className="navbar navbar-light bg-light d-flex justify-content-start">
            <span className="navbar-brand mb-0 h1">Navbar</span>
            <span className="badge badge-pill badge-secondary">{total}</span>
        </nav>
    );

}