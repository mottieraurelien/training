import config from "../config/config.json";
import * as Sentry from "@sentry/react";
import {Integrations} from "@sentry/tracing";

export function init() {
    Sentry.init({
        dsn: config["sentry"]["dsn"],
        integrations: [new Integrations.BrowserTracing()],
        tracesSampleRate: config["sentry"]["rate"]
    });
}

export default function log(error: any) {
    Sentry.captureException(error);
}