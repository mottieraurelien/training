import axios from "axios";
import config from "./config.json";
import logger from "./logger";

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
client.interceptors.response.use(null, error => {

    // Logging into Sentry :
    logger.log(error);

    // Getting details :
    const {response} = error;
    const {message} = error.toJSON();
    const isHandledException = response && response.status >= 400 && response.status < 600;

    // If the error comes from the backend (handled by the backend) then we can
    // assume that it contains an HTTP status code and an error message that we
    // can directly show to the user :
    if (isHandledException) {
        const errorMessage = "[" + response.status + "] " + message;
        return {hasError: true, errorMessage};
    }

    // Otherwise, it means that it could be too technical, so we need to
    // adapt the error message :
    const errorMessage = "[No HTTP status code] " + (message || "An unexpected error occurred.");
    return {hasError: true, errorMessage};

});

/**
 * [GET]
 */
export async function get(uri) {
    return await client.get(uri);
}

/**
 * [POST]
 */
export async function post(uri, data) {
    return await client.post(uri, data);
}

/**
 * [PUT]
 */
export async function put(uri, data) {
    return await client.put(uri, data);
}

/**
 * [REMOVE]
 */
export async function remove(uri) {
    return await client.delete(uri);
}