import Genre from "./genre";
import Row from "../common/table/domain/row";
import Validateable from "../common/form/domain/validateable";
import Joi from "joi";

export default class Movie extends Validateable implements Row {

    _id: string;
    title: string;
    genre: Genre;
    numberInStock: number;
    dailyRentalRate: number;
    publishDate: Date;
    liked: boolean;

    constructor(movie: any) {
        const schema: any = {
            title: Joi.string().required().label("Title"),
            genre: Joi.any().required().label("Genre"),
            numberInStock: Joi.number().min(0).max(100).required().label("Number in stock"),
            dailyRentalRate: Joi.number().min(0).max(10).required().label("Daily rental rate"),
        };
        super(schema);
        this._id = movie["_id"];
        this.title = movie["title"];
        this.genre = movie["genre"];
        this.numberInStock = movie["numberInStock"];
        this.dailyRentalRate = movie["dailyRentalRate"];
        this.publishDate = movie["publishDate"];
        this.liked = movie["liked"];
    }

    getKey(): string {
        return this._id;
    }

    getContent(): any {
        return this;
    }

}