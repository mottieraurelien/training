import React from "react";
import withTooltip from "./WithTooltip";

interface FromProps {
    showTooltip: boolean;
}

function Movie({showTooltip}: FromProps) {
    return <div>Movie{showTooltip && <div>Some Tooltip displayed !</div>}</div>;
}

export default withTooltip(Movie);