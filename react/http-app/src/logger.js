import config from "./config.json";
import * as Sentry from "@sentry/react";
import {Integrations} from "@sentry/tracing";

function init() {
    Sentry.init({
        dsn: config["sentry"]["dsn"],
        integrations: [new Integrations.BrowserTracing()],
        tracesSampleRate: config["sentry"]["rate"]
    });
}

function log(error) {
    Sentry.captureException(error);
}

export default {
    init,
    log
};