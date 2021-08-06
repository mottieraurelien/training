/**
 * Token settings :
 */
export const token_header_key: string = process.env.REACT_APP_TOKEN_HEADER_KEY || "x-auth-token";

/**
 * API settings.
 */
export const api_endpoint: string = process.env.REACT_APP_API_ENDPOINT || "http://localhost:3900/api/";
export const api_timeout_milliseconds: number = +(process.env.REACT_APP_API_TIMEOUT || "5000");

/**
 * Sentry settings :
 */
export const sentry_dsn: string | undefined = process.env.REACT_APP_SENTRY_DSN;
export const sentry_rate: string | undefined = process.env.REACT_APP_SENTRY_RATE;