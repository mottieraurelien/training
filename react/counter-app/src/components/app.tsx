import React, {useMemo, useState} from "react";
import Navbar from "./navbar";
import Counter from "./counter";
import Item from "../domain/Item";

export default function App() {

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
            <Navbar total={total}/>
            <main className="container">
                <Counter items={items} reset={reset} remove={remove} increment={increment} decrement={decrement}/>
            </main>
        </React.Fragment>
    )

}