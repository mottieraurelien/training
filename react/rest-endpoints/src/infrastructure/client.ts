import {api_endpoint, api_timeout_milliseconds} from "./config";
import axios, {AxiosInstance, AxiosResponse} from "axios";

/**
 * The HTTP client instance from axios that we are gonna use when calling the backend.
 * The instance will suggest a consistent way to call the backend, for every HTTP request sent to the backend.
 */
const client: AxiosInstance = axios.create({
    baseURL: api_endpoint,
    timeout: api_timeout_milliseconds
});


/**
 * [PATCH]
 */
export async function patch(uri: string, data: any): Promise<AxiosResponse> {
    return await client.patch(uri, data);
}