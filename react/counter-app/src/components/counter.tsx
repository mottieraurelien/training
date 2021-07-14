import React, {useMemo, useState} from 'react';
import Item from "../domain/Item";
import CounterLine from "./counterLine";

export default function Counter() {

    /*
     * STATE
     */
    const [items, setItems] = useState([new Item(0, 0), new Item(1, 0), new Item(2, 0), new Item(3, 0)]);
    const sum = (subTotal: number, item: Item) => subTotal + item.quantity;
    const total = useMemo(() => items.reduce(sum, 0), [items]);

    /*
     * BEHAVIOUR
     */
    const increment = (itemToUpdate: Item) => {
        setItems(
            items.map(item =>
                item === itemToUpdate ? new Item(item.id, ++item.quantity) : item)
        );
    }
    const decrement = (itemToUpdate: Item) => {
        setItems(
            items.map(item =>
                item === itemToUpdate ? new Item(item.id, --item.quantity) : item)
        );
    }
    const remove = (itemToRemove: Item) => {
        const remainingItems = items.filter(item => item !== itemToRemove)
        setItems(remainingItems);

    }
    const reset = () => {
        setItems([new Item(0, 0), new Item(1, 0), new Item(2, 0), new Item(3, 0)]);
    }

    /*
     * RENDERING
     */
    return (
        <React.Fragment>
            <nav className="navbar navbar-light bg-light d-flex justify-content-start">
                <span className="navbar-brand mb-0 h1">Navbar</span>
                <span className="badge badge-pill badge-secondary">{total}</span>
            </nav>
            <div>
                <button className="btn btn-primary btn-sm m-2" onClick={() => reset()}>Reset</button>
                <br/>
                {items.map((item) => {
                    return <CounterLine item={item}
                                        remove={remove}
                                        increment={increment}
                                        decrement={decrement}/>
                })}
            </div>
        </React.Fragment>
    );

}