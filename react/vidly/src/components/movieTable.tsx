import React from 'react';
import Movie from "../domain/Movie";
import MovieTableRow from "./movieTableRow";

export default function MovieTable({...props}) {

    /*
     * STATE
     */
    const {movies, remove, like} = props;

    /*
     * RENDERING
     */
    return (
        <React.Fragment>
            <span>Showing {movies.length} movies in the database.</span><br/><br/>
            <table className="table">
                <thead>
                <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Stock</th>
                    <th scope="col">Rate</th>
                    <th scope="col"/>
                    <th scope="col"/>
                </tr>
                </thead>
                <tbody>
                {movies.map(function (movie: Movie) {
                    return <MovieTableRow key={movie._id} movie={movie} remove={remove} like={like}/>
                })}
                </tbody>
            </table>
        </React.Fragment>
    );

}