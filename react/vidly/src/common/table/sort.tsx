import React from "react";
import Sorting from "./domain/sorting";

interface FromProps {
    sorting: Sorting | undefined;
}

export default function Sort({sorting}: FromProps) {

    /*
     * RENDERING
     */
    return (
        <React.Fragment>
            {sorting && sorting.isAsc() && <i className="fa fa-sort-asc" aria-hidden="true"/>}
            {sorting && sorting.isDesc() && <i className="fa fa-sort-desc" aria-hidden="true"/>}
        </React.Fragment>
    );

}