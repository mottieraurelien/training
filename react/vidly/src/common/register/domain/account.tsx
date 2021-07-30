import Credentials from "../../login/domain/credentials";
import Joi from "joi";

export default class Account extends Credentials {

    _id: string;
    name: string;
    isAdmin: boolean;

    constructor(account: any) {
        const schema: any = {name: Joi.string().required().label("Name")};
        super(account, schema);
        this._id = account["_id"];
        this.name = account["name"];
        this.isAdmin = account["isAdmin"];
    }

}