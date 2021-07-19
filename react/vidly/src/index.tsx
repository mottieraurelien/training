import React from "react";
import ReactDOM from 'react-dom';
import {BrowserRouter} from "react-router-dom";
import Vidly from "./components/vidly";
import "./index.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "font-awesome/css/font-awesome.min.css";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter><Vidly/></BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root')
);