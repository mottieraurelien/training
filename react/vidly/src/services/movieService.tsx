import {get, post, put, remove} from '../infrastructure/client';
import Movie from "../domain/movie";

const resource = "movies/";

export async function getMovies(): Promise<Movie[] | undefined> {
    const {data: movies} = await get(resource);
    return movies.map(function (movie: any) {
        return new Movie(movie);
    });
}

export async function getMovie(id: string): Promise<Movie> {
    const {data: movie} = await get(resource + id);
    return new Movie(movie);
}

export async function removeMovie(id: string): Promise<void> {
    await remove("a" + resource + id);
}

export async function saveMovie(newMovie: Movie): Promise<Movie> {

    const movie: Movie = newMovie._id ? await getMovie(newMovie._id) : new Movie({});

    movie.genre = newMovie.genre;
    movie.title = newMovie.title;
    movie.numberInStock = newMovie.numberInStock;
    movie.dailyRentalRate = newMovie.dailyRentalRate;

    if (!movie._id) {
        movie._id = Date.now().toString();
        const {data: newMovie} = await post(resource, movie);
        return new Movie(newMovie);
    }

    const {data: updatedMovie} = await put(resource + movie._id, movie);
    return new Movie(updatedMovie);

}