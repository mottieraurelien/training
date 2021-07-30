import Account from "../common/register/domain/account";
import {post} from "../infrastructure/client";

const resource = "users/";

async function register(account: Account) {
    return await post(resource, account);
}

const user = {
    register
}

export default user;