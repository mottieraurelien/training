/**
 * API settings.
 */
export const api_endpoint: string = process.env.REACT_APP_API_ENDPOINT || "http://localhost:3900/api/";
export const api_timeout_milliseconds: number = +(process.env.REACT_APP_API_TIMEOUT || "5000");