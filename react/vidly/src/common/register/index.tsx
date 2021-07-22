import React, {ChangeEvent, useMemo, useState} from "react";
import Form from "../form";
import Input from "../input";
import ValidationReport from "../form/domain/validationReport";
import Account from "./domain/account";

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

    const register = () => {
        // TODO Call server
        // TODO Redirection
    }

    /**
     * RENDERING
     */
    return (
        <React.Fragment>
            <h1>Register</h1>
            <Form callback={register} disabled={disableSubmit} submitLabel="Register">
                <Input name="username" label="Username" value={account && account.username}
                       type="email" placeholder="email@example.com" change={change}
                       error={report && report.getErrorMessage("username")}/>
                <Input name="password" label="Password" value={account && account.password}
                       type="password"  placeholder="Password" change={change}
                       error={report && report.getErrorMessage("password")}/>
                <Input name="name" label="Name" value={account && account.name}
                       placeholder="Name" change={change}
                       error={report && report.getErrorMessage("name")}/>
            </Form>
        </React.Fragment>
    );

}