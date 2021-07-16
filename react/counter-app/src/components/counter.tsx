import React from 'react';
import CounterLine from "./counterLine";
import Item from "../domain/Item";

export default function Counter({...props}) {

    const {items, reset, remove, increment, decrement} = props;

    /*
     * RENDERING
     */
    return (
        <React.Fragment>
            <button className="btn btn-primary btn-sm m-2" onClick={reset}>Reset</button>
            <br/>
            {items.map(function (item: Item) {
                return <CounterLine key={item.id}
                                    item={item}
                                    remove={remove}
                                    increment={increment}
                                    decrement={decrement}/>
            })}

        </React.Fragment>
    );

}