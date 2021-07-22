import React, {ChangeEvent, useMemo, useState} from "react";
import Credentials from "./domain/credentials";
import Input from "../input";
import ValidationReport from "../form/domain/validationReport";
import Form from "../form";

export default function Login() {

    /**
     * STATE
     */
    const [credentials, setCredentials] = useState<Credentials>();
    const [report, setReport] = useState<ValidationReport>();
    const disableSubmit = useMemo(() => !credentials || credentials.validate().hasAnError(), [credentials]);

    /**
     * BEHAVIOUR
     */
    const change = ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = input;
        const tempCredentials: any = credentials ? {...credentials} : {};
        tempCredentials[name] = value;
        const newCredentials: Credentials = new Credentials(tempCredentials);
        setReport(newCredentials.validateOnly(input, report));
        setCredentials(newCredentials);
    }

    const login = () => {
        // TODO Call server
        // TODO Redirection
    }

    /**
     * RENDERING
     */
    return (
        <React.Fragment>
            <h1>Login</h1>
            <Form callback={login} disabled={disableSubmit} submitLabel="Login">
                <Input name="username" label="Username" value={credentials && credentials.username}
                       type="email" placeholder="email@example.com" change={change}
                       error={report && report.getErrorMessage("username")}/>
                <Input name="password" label="Password" value={credentials && credentials.password}
                       type="password" placeholder="Password" change={change}
                       error={report && report.getErrorMessage("password")}/>
            </Form>
        </React.Fragment>
    );

}