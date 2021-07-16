import React from "react";

export default function Like({...props}) {

    const {id, liked, like} = props;
    const classes = liked ? "pointer fa fa-heart" : "pointer fa fa-heart-o";

    return (
        <React.Fragment>
            <i onClick={() => like(id)} className={classes} aria-hidden="true"/>
        </React.Fragment>
    );

}