import axios, {AxiosInstance, AxiosRequestConfig, AxiosResponse} from "axios";
import {api_timeout_milliseconds} from "./config";

/**
 * The HTTP client.ts instance from axios that we are gonna use when calling the backend.
 * The instance will suggest a consistent way to call the backend, for every HTTP request sent to the backend.
 */
const client: AxiosInstance = axios.create({
    timeout: api_timeout_milliseconds
});

/**
 * [GET]
 */
export async function get<T>(uri: string, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return await client.get<T>(uri, config);
}