import React, {ChangeEvent, useMemo, useState} from "react";
import Credentials from "./domain/credentials";
import Input from "../input";
import ValidationReport from "../form/domain/validationReport";
import Form from "../form";
import auth from "../../services/authService";
import {Redirect} from "react-router-dom";

export default function Login({...props}) {

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

    const submit = async () => {
        try {
            if (credentials) {
                await auth.login(credentials);
                // Our NavBar has already been mounted so we need to full reload our website to update the NavBar
                // meaning that we won't use props.history, but window.location !
                // However, if the visitor got redirected because he wanted to visit a protected page, then we will
                // need to redirect him back to this protected page (otherwise, default redirection if homepage).
                const {state} = props.location;
                window.location.href = state ? state.from.pathname : "/";
            }
        } catch (ex) {
            const {response} = ex;
            if (response && response.status === 400) {
                const newReport: ValidationReport = new ValidationReport();
                newReport.push("email", response.data);
                setReport(newReport);
            }
        }
    }

    /**
     * RENDERING
     */

    // If the visitor is already authenticated, no need to login again :
    if (auth.isAuthenticated()) return <Redirect to="/"/>;

    // Otherwise, he can login :
    return (
        <React.Fragment>
            <h1>Login</h1>
            <Form callback={submit} disabled={disableSubmit} submitLabel="Login">
                <Input name="email" label="Username" value={credentials && credentials.email}
                       type="email" placeholder="email@example.com" change={change}
                       error={report && report.getErrorMessage("email")}/>
                <Input name="password" label="Password" value={credentials && credentials.password}
                       type="password" placeholder="Password" change={change}
                       error={report && report.getErrorMessage("password")}/>
            </Form>
        </React.Fragment>
    );

}