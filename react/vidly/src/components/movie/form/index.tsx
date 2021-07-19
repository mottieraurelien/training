import React from "react";

export default function MovieForm({...props}) {

    const {id} = props.match.params;

    const save = () => {
        props.history.push("/movies");
    }

    /*
     * RENDERING
     */
    return (
        <React.Fragment>
            <h1>Movie Form {id}</h1>
            <button className="btn btn-primary" onClick={() => save()}>Save</button>
        </React.Fragment>
    );

}