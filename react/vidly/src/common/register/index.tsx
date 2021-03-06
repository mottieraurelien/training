import React, {ChangeEvent, useMemo, useState} from "react";
import Form from "../form";
import Input from "../input";
import ValidationReport from "../form/domain/validationReport";
import Account from "./domain/account";
import user from "../../services/userService";
import auth from "../../services/authService";
import {token_header_key} from "../../infrastructure/config";

export default function Register() {

    /**
     * STATE
     */
    const [account, setAccount] = useState<Account>();
    const [report, setReport] = useState<ValidationReport>();
    const disableSubmit = useMemo(() => !account || account.validate().hasAnError(), [account]);

    /**
     * BEHAVIOUR
     */
    const change = ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = input;
        const tempAccount: any = account ? {...account} : {};
        tempAccount[name] = value;
        const newAccount: Account = new Account(tempAccount);
        setReport(newAccount.validateOnly(input, report));
        setAccount(newAccount);
    }

    const submit = async () => {
        try {
            if (account) {
                const {headers} = await user.register(account);
                const token = headers[token_header_key];
                auth.loginUsing(token);
                // Our NavBar has already been mounted so we need to full reload our website to update the NavBar.
                // It means that we won't use props.history but directly :
                window.location.href = "/";
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
     * RENDERING+++++++
     */
    return (
        <React.Fragment>
            <h1>Register</h1>
            <Form callback={submit} disabled={disableSubmit} submitLabel="Register">
                <Input name="email" label="Username" value={account && account.email}
                       type="email" placeholder="email@example.com" change={change}
                       error={report && report.getErrorMessage("email")}/>
                <Input name="password" label="Password" value={account && account.password}
                       type="password" placeholder="Password" change={change}
                       error={report && report.getErrorMessage("password")}/>
                <Input name="name" label="Name" value={account && account.name}
                       placeholder="Name" change={change}
                       error={report && report.getErrorMessage("name")}/>
            </Form>
        </React.Fragment>
    );

}