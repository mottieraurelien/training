import React from "react";
import Column from "../../common/table/domain/column";
import Movie from "../../domain/movie";
import Like from "../../common/like";
import Table from "../../common/table/table";
import Sorting from "../../common/table/domain/sorting";
import {Link} from "react-router-dom";

interface FromProps {
    movies: Movie[];
    remove: Function;
    like: Function;
    sort: Function;
    sorting: Sorting | undefined;
}

export default function MoviesTable({movies, remove, like, sort, sorting}: FromProps) {

    const columns: Column[] = [
        new Column("title", (movie: Movie) => <Link to={`/movies/${movie._id}`}>{movie.title}</Link>, "Title", true),
        new Column("genre.name", (movie: Movie) => movie.genre.name, "Genre", true),
        new Column("stock", (movie: Movie) => movie.stock, "Stock", true),
        new Column("rate", (movie: Movie) => movie.rate, "Rate", true),
        new Column("like", (movie: Movie) => <Like id={movie._id} liked={movie.liked} like={like}/>),
        new Column("delete", (movie: Movie) => <button className="btn btn-danger btn-sm"
                                                       onClick={() => remove(movie._id)}>Delete</button>)
    ];

    /*
     * RENDERING
     */
    return <Table columns={columns} rows={movies} sort={sort} sorting={sorting}/>;

}