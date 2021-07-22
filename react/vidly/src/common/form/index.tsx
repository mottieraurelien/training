import React, {FormEvent} from "react";

export default function Form({...props}) {

    /**
     * PROPS
     */
    const {disabled, callback, children, submitLabel} = props;

    /**
     * BEHAVIOUR
     */
    const submit = (event: FormEvent<HTMLFormElement>) => {

        // Prevents the default behaviour of this event which is in our case submitting the data
        // to the server that causes a full page reload (which is something we want to avoid here) :
        event.preventDefault();

        // Call the server, redirecting ... anything specific to the form :
        callback();

    }

    return (
        <form onSubmit={submit}>
            {children}
            <button disabled={disabled} className="btn btn-primary">{submitLabel}</button>
        </form>
    );

}