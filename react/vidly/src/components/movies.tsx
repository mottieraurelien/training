import React, {useState} from 'react';
import {getMovies} from "../services/fakeMovieService";
import MovieTable from "./movieTable";
import Movie from "../domain/Movie";

export default function Movies() {

    /*
     * STATE
     */
    const [movies, setMovies] = useState(getMovies());

    /*
     * BEHAVIOUR
     */
    const remove = (movieId: string) => {
        const remainingMovies = movies.filter(movie => movie._id !== movieId);
        setMovies(remainingMovies);
    }
    const like = (movieId: string) => {
        const updatedMovies = movies.map(movie => movie._id === movieId ? new Movie({
            ...movie,
            liked: !movie.liked
        }) : movie);
        setMovies(updatedMovies);
    }

    /*
     * RENDERING
     */
    return (
        <main className="container">
            {movies && movies.length === 0 && <p>There are no movies in the database.</p>}
            {movies && movies.length > 0 && <MovieTable movies={movies} remove={remove} like={like}/>}
        </main>
    );

}