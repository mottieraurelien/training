import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import logger from "./logger"
import "./index.css";
import "bootstrap/dist/css/bootstrap.css";

logger.init();
ReactDOM.render(<App/>, document.getElementById("root"));