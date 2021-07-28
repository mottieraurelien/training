import {get} from '../infrastructure/client';
import Genre from "../domain/genre";

const resource = "genres/";

export async function getGenres() {
    const {data: genres} = await get(resource);
    if (!genres || genres.length === 0) throw Error("Something went wrong when loading the genres.");
    return genres.map(function (genre: any) {
        return new Genre(genre);
    });
}

export function getGenre(genres: Genre[] | undefined, genreId: string): Genre {
    if (!genres || genres.length === 0) throw Error("Genres are still not loaded.");
    const genre: Genre | undefined = genres.find(genre => genre._id === genreId);
    if (!genre) throw Error(`Cannot find any genre with id[${genreId}].`);
    return genre;
}