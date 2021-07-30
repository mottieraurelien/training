import axios, {AxiosRequestConfig, AxiosResponse} from "axios";
import config from "../config/config.json";
import log from "./logger";

/**
 * The HTTP client instance from axios that we are gonna use when calling the backend.
 * The instance will suggest a consistent way to call the backend, for every HTTP request sent to the backend.
 */
const client = axios.create({
    baseURL: config["api"]["endpoint"],
    timeout: config["api"]["timeout"],
    // auth...
    // headers...
});

/**
 * In case an error occurred when calling the backend, our HTTP client will catch
 * the error (before the UI) so that we can adapt the error message.
 */
client.interceptors.response.use(undefined, error => {
    log(error);
    return Promise.reject(error);
});

/**
 * Everytime we call the backend, the request will include our token in a custom header.
 */
export function setToken(token: string | null) {
    client.interceptors.request.use(function (clientConfig: AxiosRequestConfig) {
        clientConfig.headers["x-auth-token"] = token;
        return clientConfig
    });
}

/**
 * [GET]
 */
export async function get(uri: string): Promise<AxiosResponse> {
    return await client.get(uri);
}

/**
 * [POST]
 */
export async function post(uri: string, data: any): Promise<AxiosResponse> {
    return await client.post(uri, data);
}

/**
 * [PUT]
 */
export async function put(uri: string, data: any): Promise<AxiosResponse> {
    return await client.put(uri, data);
}

/**
 * [REMOVE]
 */
export async function remove(uri: string): Promise<AxiosResponse> {
    return await client.delete(uri);
}