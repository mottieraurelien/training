/**
 * HTTP client settings.
 */
export const api_timeout_milliseconds: number = +(process.env.REACT_APP_API_TIMEOUT || "5000");