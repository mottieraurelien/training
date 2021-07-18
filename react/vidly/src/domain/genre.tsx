import Group from "../common/menu/domain/group";

export default class Genre implements Group {

    _id: string;
    name: string;

    constructor(genre: any) {
        this._id = genre["_id"];
        this.name = genre["name"];
    }

    getId(): string {
        return this._id;
    }

    getLabel(): string {
        return this.name;
    }

}