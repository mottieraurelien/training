export default class Genre {

    _id: string;
    name: string;

    constructor(genre: any) {
        this._id = genre["_id"];
        this.name = genre["name"];
    }

}