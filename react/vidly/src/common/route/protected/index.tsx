import React from "react";
import {Redirect, Route} from "react-router-dom";
import auth from "../../../services/authService";

export default function ProtectedRoute({component: Component, render, ...other}: any) {

    return <Route {...other}
                  render={(props) => {

                      // If user is already authenticated then he can visit the targeted webpage :
                      if (auth.isAuthenticated()) return Component ? <Component {...props}/> : render(props);

                      // Otherwise, he will be redirected to the login page.
                      // After logging, he will be redirected to :
                      // 1 - the webpage he wanted to visit (the one that required to be logged in),
                      // 2 - the homepage (default redirection after logging).
                      return <Redirect to={{
                          pathname: "/login",
                          state: {
                              from: props.location
                          }
                      }}/>

                  }}/>

}