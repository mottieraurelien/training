import React from "react";
import Column from "./domain/column";
import Row from "./domain/row";

interface FromProps {
    columns: Column[];
    rows: Row[];
}

export default function Body({columns, rows}: FromProps) {

    /*
     * RENDERING
     */
    return (
        <tbody>
        {rows.map(function (row: Row) {
            return <tr key={row.getKey()}>
                {columns.map(function (column: Column) {
                    const cellKey: string = column.getKey() + row.getKey();
                    return <td key={cellKey}>{column.getCellContent(row)}</td>
                })}
            </tr>
        })}
        </tbody>
    );

}