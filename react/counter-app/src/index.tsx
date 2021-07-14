import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Counter from "./components/counter";

ReactDOM.render(
    <React.StrictMode>
        <Counter/>
    </React.StrictMode>,
    document.getElementById('root')
);