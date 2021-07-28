import axios, {AxiosResponse} from "axios";
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
    // headers: {'X-Custom-Header': 'foobar'}...
});

/**
 * In case an error occurred when calling the backend, our HTTP client will catch
 * the error (before the UI) so that we can adapt the error message.
 */
client.interceptors.response.use(undefined, error => {
    log(error);
});

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