import Credentials from "../../login/domain/credentials";
import Joi from "joi";

export default class Account extends Credentials {

    name: string;

    constructor(account: any) {
        const schema: any = {name: Joi.string().required().label("Name")};
        super(account, schema);
        this.name = account["name"];
    }

}