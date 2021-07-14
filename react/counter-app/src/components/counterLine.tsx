import React from 'react';

export default function CounterLine({...props}) {

    /*
     * STATE
     */
    const {item, remove, increment, decrement} = props;

    /*
     * RENDERING
     */
    return (
        <div key={item.id}>
            <span className={item.format()}>{item.label()}</span>
            <button className="btn btn-secondary btn-sm m-1" onClick={() => increment(item)}>+</button>
            <button className="btn btn-secondary btn-sm m-1" onClick={() => decrement(item)} disabled={item.isDisabled()}>-</button>
            <button className="btn btn-danger btn-sm m-1" onClick={() => remove(item)}>Delete</button>
        </div>
    );

}