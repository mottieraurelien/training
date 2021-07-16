import React from 'react';

export default function CounterLine({...props}) {

    const {item, remove, increment, decrement} = props;

    /*
     * RENDERING
     */
    return (
        <div className="row">
            <div className="col-1">
                <span className={item.format()}>{item.label()}</span>
            </div>
            <div className="col">
                <button className="btn btn-secondary btn-sm" onClick={() => increment(item)}>+</button>
                <button className="btn btn-secondary btn-sm m-2" onClick={() => decrement(item)} disabled={item.isDisabled()}>-</button>
                <button className="btn btn-danger btn-sm" onClick={() => remove(item)}>Delete</button>
            </div>
        </div>
    );

}