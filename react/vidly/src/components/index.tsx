import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import Movies from "./movie/";
import Customers from "./customer";
import Rentals from "./rental";
import Navbar from "../common/navbar";
import {NotFound} from "./error";
import MovieForm from "./movie/form";

export default function Vidly() {

    /*
     * RENDERING
     */
    return (
        <React.Fragment>
            <Navbar/>
            <main className="container m-3">
                <Switch>
                    <Route path="/movies/:id" component={MovieForm}/>
                    <Route path="/movies" component={Movies}/>
                    <Route path="/customers" component={Customers}/>
                    <Route path="/rentals" component={Rentals}/>
                    <Route path="/not-found" component={NotFound}/>
                    <Redirect from="/" exact to="/movies"/>
                    <Redirect to="/not-found"/>
                </Switch>
            </main>
        </React.Fragment>
    );

}