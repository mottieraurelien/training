export default class Customer {
    private readonly _id: number;
    private readonly firstname: string;
    private readonly lastname: string;
    private readonly salary: number;

    constructor(customer: Customer) {
        this._id = 7;
        this.firstname = customer.firstname;
        this.lastname = customer.lastname;
        this.salary = customer.salary;
    }

    getId(): number {
        return this._id;
    }

    getFirstname(): string {
        return this.firstname;
    }

    getLastname(): string {
        return this.lastname;
    }

    getSalary(): number {
        return this.salary;
    }

}