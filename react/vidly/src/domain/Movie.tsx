import Genre from "./Genre";

export default class Movie {

    _id: string;
    title: string;
    genre: Genre;
    stock: number;
    rate: number;
    publishDate: Date;
    liked: boolean;

    constructor(movie: any) {
        this._id = movie["_id"];
        this.title = movie["title"];
        this.genre = movie["genre"];
        this.stock = movie["stock"];
        this.rate = movie["rate"];
        this.publishDate = movie["publishDate"];
        this.liked = movie["liked"];
    }

}