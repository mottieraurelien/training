import React, {ChangeEventHandler} from "react";

interface FromProps {
    name: string;
    change: ChangeEventHandler<HTMLInputElement>;
    type?: string;
    placeholder?: string;
    label?: string;
    value?: any;
    error?: string;
}

export default function Input({type = "text", placeholder, name, label, value, error, change}: FromProps) {

    /**
     * RENDERING
     */
    return (
        <React.Fragment>
            <div className="form-group my-3">
                {label && <label htmlFor={name}>{label}</label>}
                <input
                    id={name}
                    name={name}
                    value={value || ""}
                    onChange={change}
                    type={type}
                    placeholder={placeholder}
                    className="form-control mt-1"/>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
        </React.Fragment>
    );

}