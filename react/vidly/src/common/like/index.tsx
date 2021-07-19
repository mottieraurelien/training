import React from "react";

interface FromProps {
    id: number | string;
    liked: boolean;
    like: Function;
}

export default function Like({id, liked, like}: FromProps) {

    const classes = liked ? "clickable fa fa-heart" : "clickable fa fa-heart-o";

    /*
     * RENDERING
     */
    return (
        <React.Fragment>
            <i onClick={() => like(id)} className={classes} aria-hidden="true"/>
        </React.Fragment>
    );

}