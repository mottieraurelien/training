import Joi from "joi";
import Validateable from "../../form/domain/validateable";

export default class Credentials extends Validateable {

    email: string;
    password: string;

    constructor(credentials: any, schema?: any) {
        const emailValidationOptions: any = {tlds: {allow: false}};
        const defaultSchema: any = {
            email: Joi.string().email(emailValidationOptions).required().label("Username"),
            password: Joi.string().min(5).label("Password")
        };
        super(schema ? {...schema, ...defaultSchema} : defaultSchema);
        this.email = credentials["email"];
        this.password = credentials["password"];
    }

}