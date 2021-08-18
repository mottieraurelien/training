import React, {useState} from "react";
import useDocumentTitle from "../hooks/useDocumentTitle";

export default function Counter() {

    const [count, setCount] = useState<number>(0);
    const [name, setName] = useState<string>("");

    useDocumentTitle(`${name} has clicked ${count} times.`);

    return <React.Fragment>
        <input type="text" onChange={e => setName(e.target.value)}/>
        <div>{name} has clicked {count} times.</div>
        <button onClick={() => setCount(count + 1)}>Increment</button>
    </React.Fragment>
}