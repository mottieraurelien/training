import React from "react";

export default function Select({...props}) {

    /**
     * PROPS
     */
    const {name, label, error, value, change, items} = props;

    /**
     * RENDERING
     */
    return (
        <React.Fragment>
            <div className="form-group my-3">
                <label htmlFor={name}>{label}</label>
                <select id={name}
                        name={name}
                        className="form-select"
                        aria-label={name}
                        value={value}
                        onChange={change}>
                    {
                        items.map(function (item: any) {
                            const {_id, name} = item;
                            return <option key={_id} value={_id}>{name}</option>;
                        })
                    }
                </select>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
        </React.Fragment>
    );

}