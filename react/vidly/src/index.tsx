import React from 'react';
import ReactDOM from 'react-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import "font-awesome/css/font-awesome.min.css";
import Movies from "./components/movies";

ReactDOM.render(
    <React.StrictMode>
        <Movies/>
    </React.StrictMode>,
    document.getElementById('root')
);