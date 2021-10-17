import Customer from "../domain/Customer";
import {patch} from "../infrastructure/client";

const resource = "customers/";

export async function patchCustomer(customer: Customer): Promise<Customer> {
    const {data: patchedCustomer}: any = await patch(resource + customer.getId(), customer);
    return new Customer(patchedCustomer);
}