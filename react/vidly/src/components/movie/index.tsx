import React, {ChangeEvent, useEffect, useState} from "react";
import {getMovies, saveMovie, removeMovie} from "../../services/movieService";
import Movie from "../../domain/movie";
import {getGenres} from "../../services/genreService";
import Genre from "../../domain/genre";
import Groups from "../../common/menu";
import Sorting, {SortingOrder} from "../../common/table/domain/sorting";
import {orderBy} from "../../common/utils/array";
import {Link} from "react-router-dom";
import Input from "../../common/input";
import MoviesTable from "./table";
import Pagination from "../../common/table/pagination";

export default function Movies() {

    /*
     * STATE
     */
    const [genres, setGenres] = useState<Genre[]>([]);
    const [selectedGenreId, setSelectedGenreId] = useState<string>();
    const [searchQuery, setSearchQuery] = useState<string>();
    const [movies, setMovies] = useState<Movie[]>();
    const [pageSize] = useState<number>(4);
    const [selectedPage, setSelectedPage] = useState<number>(1);
    const [sorting, setSorting] = useState<Sorting>(new Sorting("title", SortingOrder.DEFAULT));

    /**
     * DATA LOADING
     */
    useEffect(() => {
        const fetch = async () => {
            const genres = await getGenres();
            if (genres) setGenres(genres);
        }
        fetch();
    }, []);
    useEffect(() => {
        const fetch = async () => {
            const movies = await getMovies();
            if (movies) setMovies(movies);
        }
        fetch();
    }, []);

    /*
     * BEHAVIOUR
     */
    const remove = async (movieId: string) => {
        if (!movies) throw Error("Movies are still not loaded.");
        const remainingMovies: Movie[] = movies.filter(movie => movie._id !== movieId);
        setMovies(remainingMovies);
        await removeMovie(movieId);
    }
    const like = async (movieId: string) => {
        if (!movies) throw Error("Movies are still not loaded.");
        const index: number = movies.findIndex(movie => movie._id === movieId);
        const existingMovie: Movie = movies[index];
        const updatedMovie: Movie = new Movie({...existingMovie, liked: !existingMovie.liked});
        const updatedMovies: Movie[] = [...movies];
        updatedMovies[index] = updatedMovie;
        setMovies(updatedMovies);
        await saveMovie(updatedMovie);
    }
    const sort = (newSorting: Sorting) => {
        setSorting(newSorting);
    }
    const selectPage = (pageNumber: number) => {
        setSelectedPage(pageNumber);
    }
    const selectGenre = (genreId: string) => {
        setSelectedPage(1);
        setSearchQuery(undefined);
        setSelectedGenreId(genreId);
    }
    const getFilteredSortedPagedMovies = () => {
        if (!movies) return {data: [], number: 0};
        // Applying genre filter :
        const filteredMoviesByGenre: Movie[] = selectedGenreId ? movies.filter(movie => movie.genre._id === selectedGenreId) : movies;
        // Applying title filter (case insensitive) :
        const filteredMoviesByTitle: Movie[] = searchQuery ? movies.filter(movie => movie.title.toLocaleLowerCase().indexOf(searchQuery) > -1) : filteredMoviesByGenre;
        // Applying sort :
        if (sorting) filteredMoviesByTitle.sort(orderBy(sorting));
        // Applying pagination :
        const skip: number = (selectedPage - 1) * pageSize;
        return {number: filteredMoviesByTitle.length, data: filteredMoviesByTitle.slice(skip, pageSize * selectedPage)};
    }
    const updateSearchQuery = ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {
        const {value} = input;
        setSelectedPage(1);
        setSelectedGenreId(undefined);
        setSearchQuery(value.toLocaleLowerCase());
    }

    /*
     * RENDERING
     */
    const {data, number} = getFilteredSortedPagedMovies();
    return (
        <React.Fragment>
            <div className="row">
                <div className="col-3">
                    <Groups groups={genres}
                            selectedGroupId={selectedGenreId}
                            selectGroup={selectGenre}/>
                </div>
                <div className="col">
                    <div><Link className="btn btn-primary" to="/movies/new">New Movie</Link></div>
                    {movies && number === 0 && <p>There are no movies in the database.</p>}
                    {movies && number > 0 &&
                    <React.Fragment>
                        <div className="mt-3 mb-2"><span>Showing {number} movies in the database.</span></div>
                        <Input name="moviesFilter" placeholder="Search..." value={searchQuery}
                               change={updateSearchQuery}/>
                        <MoviesTable movies={data} remove={remove} like={like} sort={sort} sorting={sorting}/>
                        <Pagination pageSize={pageSize} rowsNumber={number} selectedPage={selectedPage}
                                    selectPage={selectPage}/>
                    </React.Fragment>
                    }
                </div>
            </div>
        </React.Fragment>
    );

}