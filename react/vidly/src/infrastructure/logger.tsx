import * as Sentry from "@sentry/react";
import {Integrations} from "@sentry/tracing";
import {sentry_dsn, sentry_rate} from "./config";

export function init() {
    if (sentry_dsn && sentry_rate) {
        Sentry.init({
            dsn: sentry_dsn,
            integrations: [new Integrations.BrowserTracing()],
            tracesSampleRate: +sentry_rate
        });
    }
}

export default function log(error: any) {
    if (Sentry) Sentry.captureException(error);
}