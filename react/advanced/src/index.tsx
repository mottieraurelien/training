import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import ClassApp from './ClassApp';
import './index.css';

ReactDOM.render(
    <React.StrictMode>
        <App/>
        <ClassApp/>
    </React.StrictMode>,
    document.getElementById('root')
);