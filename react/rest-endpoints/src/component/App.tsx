import React, {ChangeEvent, useState} from "react";
import Customer from "../domain/Customer";
import Input from "./Input";
import {patchCustomer} from "../service/customerService";

export default function App() {

    const [customer, setCustomer] = useState<Customer>();

    const change = async ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = input;
        const tempCustomer: any = customer ? {...customer} : {};
        tempCustomer[name] = value;
        const newCustomer: Customer = await patchCustomer(customer);
        setCustomer(newCustomer);
    }

    return <React.Fragment>
        <h1>Edit customer information</h1>
        <Input name="firstname" label="Firstname" value={customer && customer.getFirstname()} placeholder="Firstname"
               change={change}/>
        <Input name="lastname" label="Lastname" value={customer && customer.getLastname()} placeholder="Lastname"
               change={change}/>
        <Input name="salary" label="Salary" value={customer && customer.getSalary()} type="number" placeholder="Salary"
               change={change}/>
    </React.Fragment>

}