import React, {useState} from 'react';
import {getMovies} from "../services/fakeMovieService";
import MovieTable from "./movieTable";
import Movie from "../domain/Movie";
import Pagination from '../common/pagination';

export default function Movies() {

    /*
     * STATE
     */
    const [movies, setMovies] = useState<Movie[]>(getMovies());
    const [pageSize] = useState<number>(4);
    const [activePage, setActivePage] = useState<number>(1);
    const [paginatedMovies, setPaginatedMovies] = useState<Movie[]>(movies.slice(0, pageSize));

    /*
     * BEHAVIOUR
     */
    const remove = (movieId: string) => {
        const remainingMovies: Movie[] = movies.filter(movie => movie._id !== movieId);
        setMovies(remainingMovies);
    }
    const like = (movieId: string) => {
        const updatedMovies: Movie[] = movies.map(movie => movie._id === movieId ? new Movie({
            ...movie,
            liked: !movie.liked
        }) : movie);
        setMovies(updatedMovies);
    }
    const goTo = (pageNumber: number) => {
        const skip: number = (pageNumber - 1) * pageSize;
        setPaginatedMovies(movies.slice(skip, pageSize * pageNumber));
        setActivePage(pageNumber);
    }

    /*
     * RENDERING
     */
    return (
        <main className="container">
            {movies && movies.length === 0 && <p>There are no movies in the database.</p>}
            {movies && movies.length > 0
            && <React.Fragment>
                <span>Showing {movies.length} movies in the database.</span><br/><br/>
                <MovieTable movies={paginatedMovies} remove={remove} like={like}/>
                <Pagination pageSize={pageSize} entities={movies} activePage={activePage} goTo={goTo}/>
            </React.Fragment>
            }
        </main>
    );

}