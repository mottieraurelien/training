import Credentials from "../common/login/domain/credentials";
import * as http from "../infrastructure/client";
import jwt_decode from "jwt-decode";
import Account from "../common/register/domain/account";

const resource = "auth/";
const localStorageTokenKey = "token";

http.setToken(getToken());

async function login(credentials: Credentials) {
    const {email, password} = credentials;
    const {data: jwt} = await http.post(resource, {email, password});
    localStorage.setItem(localStorageTokenKey, jwt);
}

function loginUsing(token: string) {
    localStorage.setItem(localStorageTokenKey, token);
}

function logout() {
    localStorage.removeItem(localStorageTokenKey);
}

function getAccount(): Account | null {
    const jwt = getToken();
    if (!jwt) return null;
    const account: any = jwt_decode(jwt);
    return new Account(account);
}

function getToken(): string | null {
    return localStorage.getItem(localStorageTokenKey);
}

function isAuthenticated(): boolean {
    return !!getToken();
}

function isAdmin(): boolean {
    const account = getAccount();
    return (account && account.isAdmin) || false;
}

const auth = {
    login,
    loginUsing,
    logout,
    getAccount,
    isAuthenticated,
    isAdmin
};

export default auth;