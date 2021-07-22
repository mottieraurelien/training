import React from "react";
import {range} from "../utils/array";

interface FromProps {
    rowsNumber: number;
    pageSize: number;
    selectedPage: number;
    selectPage: Function;
}

export default function Pagination({rowsNumber, pageSize, selectedPage, selectPage}: FromProps) {

    const pagesNumber: number = Math.ceil(rowsNumber / pageSize);
    if (pagesNumber < 2) return null;
    const pages: number[] = range(pagesNumber, 1);

    /*
     * RENDERING
     */
    return (
        <nav aria-label="Pagination" className="mt-4">
            <ul className="pagination">
                {pages.map(pageNumber =>
                    <li key={pageNumber}
                        className={pageNumber === selectedPage ? "page-item clickable active" : "page-item clickable"}>
                        <button className="page-link" onClick={() => selectPage(pageNumber)}>{pageNumber}</button>
                    </li>
                )
                }
            </ul>
        </nav>

    );

}