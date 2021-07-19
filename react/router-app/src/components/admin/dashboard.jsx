import React from "react";
import {Route, Switch} from "react-router-dom";
import SideBar from "./sidebar";
import Users from "./users";
import Posts from "./posts";

const Dashboard = ({match}) => {
    return (
        <div>
            <h1>Admin Dashboard</h1>
            <SideBar/>
            <div className="content">
                <Switch>
                    <Route path="/admin/users" component={Users}/>
                    <Route path="/admin/posts" component={Posts}/>
                </Switch>
            </div>
        </div>
    );
};

export default Dashboard;
