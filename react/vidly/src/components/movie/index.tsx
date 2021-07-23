import React, {ChangeEvent, useState} from "react";
import {getMovies} from "../../services/fakeMovieService";
import Movie from "../../domain/movie";
import {getGenres} from "../../services/fakeGenreService";
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
    const [genres] = useState<Genre[]>(getGenres());
    const [selectedGenreId, setSelectedGenreId] = useState<string>();
    const [searchQuery, setSearchQuery] = useState<string>();
    const [movies, setMovies] = useState<Movie[]>(getMovies());
    const [pageSize] = useState<number>(4);
    const [selectedPage, setSelectedPage] = useState<number>(1);
    const [sorting, setSorting] = useState<Sorting>(new Sorting("title", SortingOrder.DEFAULT));

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