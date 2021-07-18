import Genre from "../domain/genre";
import ensure from "../common/utils/ensure";

const genres = [
    new Genre({_id: "5b21ca3eeb7f6fbccd471818", name: "Action"}),
    new Genre({_id: "5b21ca3eeb7f6fbccd471814", name: "Comedy"}),
    new Genre({_id: "5b21ca3eeb7f6fbccd471820", name: "Thriller"})
];

export function getGenres() {
    return genres;
}

export function getGenre(genreId: string): Genre {
    return ensure(genres.find(genre => genre._id === genreId));
}