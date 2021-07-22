import {getGenre} from "./fakeGenreService";
import Movie from "../domain/movie";

const movies: Movie[] = [
    new Movie({
        _id: "5b21ca3eeb7f6fbccd471815",
        title: "Terminator",
        genre: getGenre("5b21ca3eeb7f6fbccd471818"),
        stock: 6,
        rate: 2.5,
        publishDate: "2018-01-03T19:04:28.809Z"
    }),
    new Movie({
        _id: "5b21ca3eeb7f6fbccd471816",
        title: "Die Hard",
        genre: getGenre("5b21ca3eeb7f6fbccd471818"),
        stock: 5,
        rate: 2.5
    }),
    new Movie({
        _id: "5b21ca3eeb7f6fbccd471817",
        title: "Get Out",
        genre: getGenre("5b21ca3eeb7f6fbccd471820"),
        stock: 8,
        rate: 3.5
    }),
    new Movie({
        _id: "5b21ca3eeb7f6fbccd471819",
        title: "Trip to Italy",
        genre: getGenre("5b21ca3eeb7f6fbccd471814"),
        stock: 7,
        rate: 3.5
    }),
    new Movie({
        _id: "5b21ca3eeb7f6fbccd47181a",
        title: "Airplane",
        genre: getGenre("5b21ca3eeb7f6fbccd471814"),
        stock: 7,
        rate: 3.5
    }),
    new Movie({
        _id: "5b21ca3eeb7f6fbccd47181b",
        title: "Wedding Crashers",
        genre: getGenre("5b21ca3eeb7f6fbccd471814"),
        stock: 7,
        rate: 3.5
    }),
    new Movie({
        _id: "5b21ca3eeb7f6fbccd47181e",
        title: "Gone Girl",
        genre: getGenre("5b21ca3eeb7f6fbccd471820"),
        stock: 7,
        rate: 4.5
    }),
    new Movie({
        _id: "5b21ca3eeb7f6fbccd47181f",
        title: "The Sixth Sense",
        genre: getGenre("5b21ca3eeb7f6fbccd471820"),
        stock: 4,
        rate: 3.5
    }),
    new Movie({
        _id: "5b21ca3eeb7f6fbccd471821",
        title: "The Avengers",
        genre: getGenre("5b21ca3eeb7f6fbccd471818"),
        stock: 7,
        rate: 3.5
    })
];

export function getMovies(): Movie[] {
    return movies;
}

export function getMoviesFrom(genreId: string | undefined): Movie[] {
    if (!genreId) return movies;
    return movies.filter(movie => movie.genre._id === genreId);
}

export function getMovie(movieId: string): Movie | undefined {
    return movies.find(movie => movie._id === movieId);
}

export function persist(newMovie: Movie): Movie {

    const movie: Movie = movies.find(movie => movie._id === newMovie._id) || new Movie({});

    movie.genre = newMovie.genre;
    movie.title = newMovie.title;
    movie.stock = newMovie.stock;
    movie.rate = newMovie.rate;

    if (!movie._id) {
        movie._id = Date.now().toString();
        movies.push(movie);
    }

    return movie;
}