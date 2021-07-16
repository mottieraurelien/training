import React, {useState} from 'react';
import {getMovies} from "../services/fakeMovieService";
import MovieTable from "./movieTable";
import Movie from "../domain/Movie";
import Pagination from '../common/pagination';
import {getGenres} from "../services/fakeGenreService";
import Genre from "../domain/Genre";
import Groups from "../common/groups";

export default function Movies() {

    /*
     * STATE
     */
    const [genres] = useState<Genre[]>(getGenres());
    const [selectedGenreId, setSelectedGenreId] = useState<string>();
    const [movies, setMovies] = useState<Movie[]>(getMovies());
    const [pageSize] = useState<number>(4);
    const [selectedPage, setSelectedPage] = useState<number>(1);

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
    const selectPage = (pageNumber: number) => {
        setSelectedPage(pageNumber);
    }
    const selectGenre = (genreId: string) => {
        setSelectedPage(1);
        setSelectedGenreId(genreId);
    }

    // Applying genre filter :
    const filteredMovies: Movie[] = selectedGenreId ? movies.filter(movie => movie.genre._id === selectedGenreId) : movies;

    // Applying pagination :
    const skip: number = (selectedPage - 1) * pageSize;
    const paginatedMovies: Movie[] = filteredMovies.slice(skip, pageSize * selectedPage);

    /*
     * RENDERING
     */
    return (
        <main className="container">
            {movies && movies.length === 0 && <p>There are no movies in the database.</p>}
            {movies && movies.length > 0
            && <div className="row">
                <div className="col-3">
                    <Groups groups={genres}
                            selectedGroupId={selectedGenreId}
                            selectGroup={selectGenre}/>
                </div>
                <div className="col">
                    <span>Showing {movies.length} movies in the database.</span><br/><br/>
                    <MovieTable movies={paginatedMovies} remove={remove} like={like}/>
                    <Pagination pageSize={pageSize} entities={filteredMovies} selectedPage={selectedPage}
                                selectPage={selectPage}/>
                </div>
            </div>
            }
        </main>
    );

}