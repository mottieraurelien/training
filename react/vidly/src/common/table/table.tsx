import Header from "./header";
import Body from "./body";
import React from "react";
import Column from "./domain/column";
import Sorting from "./domain/sorting";
import Row from "./domain/row";

interface FromProps {
    columns: Column[];
    rows: Row[];
    sort: Function;
    sorting: Sorting | undefined;
}

export default function Table({columns, rows, sort, sorting}: FromProps) {

    /*
     * RENDERING
     */
    return (
        <table className="table">
            <Header columns={columns} sort={sort} sorting={sorting}/>
            <Body columns={columns} rows={rows}/>
        </table>
    );

}