import React from "react";
import Sort from "./sort";
import Sorting, {SortingOrder} from "./domain/sorting";
import Column from "./domain/column";

interface FromProps {
    columns: Column[];
    sort: Function;
    sorting: Sorting | undefined;
}

export default function Header({columns, sort, sorting}: FromProps) {

    /*
     * BEHAVIOUR
     */
    const triggerSort = (property: string) => {
        const oldSorting: Sorting | undefined = sorting;
        let newSorting: Sorting;
        if (oldSorting && oldSorting.getProperty() === property) {
            newSorting = new Sorting(oldSorting.getProperty(), oldSorting.getOrder());
            newSorting.toggle();
        } else {
            newSorting = new Sorting(property, SortingOrder.DEFAULT);
        }
        sort(newSorting);
    }

    /*
     * RENDERING
     */
    return (
        <thead>
        <tr>
            {columns.map(function (column: Column) {
                if (column.isSortable())
                    return <th key={column.getKey()} scope="col" onClick={() => triggerSort(column.getKey())}
                               className="clickable">
                        {column.getLabel()}{sorting && sorting.getProperty() === column.getKey() &&
                    <Sort sorting={sorting}/>}
                    </th>;
                return <th key={column.getKey()} scope="col"/>;
            })}
        </tr>
        </thead>
    );

}