import React, {Component} from "react";
import NavBar from "./components/navbar";
import {Redirect, Route, Switch} from "react-router-dom";
import Products from "./components/products";
import Posts from "./components/posts";
import NotFound from "./components/notFound";
import Dashboard from "./components/admin/dashboard";
import ProductDetails from "./components/productDetails";
import "./App.css";

class App extends Component {
    render() {
        return (
            <div>
                <NavBar/>
                <div className="content">
                    <Switch>
                        <Route path="/products/:id" component={ProductDetails}/>
                        <Route path="/products" render={(props) => <Products sortBy={"newest"} {...props}/>}/>
                        <Route path="/posts/:year?/:month?" component={Posts}/>
                        <Route path="/admin" component={Dashboard}/>
                        <Redirect from="/messages" to="/posts" />
                        <Route path="/404" component={NotFound}/>
                        <Route exact path="/"/>
                        <Redirect to="/404"/>
                    </Switch>
                </div>
            </div>
        );
    }
}

export default App;
