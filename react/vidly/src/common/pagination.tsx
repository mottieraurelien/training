import React from "react";
import range from "../utils/array";

export default function Pagination({...props}) {

    const {entities, pageSize, selectedPage, selectPage} = props;

    const pagesNumber: number = Math.ceil(entities.length / pageSize);
    if (pagesNumber < 2) return null;
    const pages: number[] = range(pagesNumber, 1);

    return (
        <nav aria-label="Pagination">
            <ul className="pagination">
                {pages.map(pageNumber =>
                    <li key={pageNumber}
                        className={pageNumber === selectedPage ? "page-item pointer active" : "page-item pointer"}>
                        <button className="page-link" onClick={() => selectPage(pageNumber)}>{pageNumber}</button>
                    </li>
                )
                }
            </ul>
        </nav>

    );

}